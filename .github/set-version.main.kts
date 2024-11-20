@file:DependsOn("com.squareup.okhttp3:okhttp:4.11.0")
@file:DependsOn("io.github.z4kn4fein:semver-jvm:1.4.1")

import io.github.z4kn4fein.semver.Version
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory
import kotlin.io.path.Path

val client = OkHttpClient()

val MODEL_URL = "https://repo1.maven.org/maven2/net/dungeon-hub/api/model/maven-metadata.xml"
val CLIENT_URL = "https://repo1.maven.org/maven2/net/dungeon-hub/api/client/maven-metadata.xml"

val FILE_PATH = Path("Writerside/v.list")

println("Finding the latest version...")

fun get(url: String): String? {
    val request = Request.Builder()
        .url(url)
        .build()

    val response = client.newCall(request).execute()

    if (response.code == 200) {
        return response.body!!.string()
    } else {
        return null
    }
}

fun getLatest(url: String): String? {
    val content = get(url)
        ?: return null

    val document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        .parse(content.byteInputStream())

    return XPathFactory.newInstance().newXPath()
        .compile("metadata/versioning/latest")
        .evaluate(document, XPathConstants.STRING) as String
}

val latestModel = getLatest(MODEL_URL)?.let { Version.parse(it) }
val latestClient = getLatest(CLIENT_URL)?.let { Version.parse(it) }

if (latestModel == null || latestClient == null) {
    println("Unable to find latest model or client version - are both published?")
    System.exit(-1)
}

println("Latest model version: $latestModel")
println("Latest client version: $latestClient")

println("Updating api-model version in Writerside/v.list to $latestModel")
println("Updating api-client version in Writerside/v.list to $latestClient")

val file = FILE_PATH.toFile()
val fileContents = file.readText()

file.writeText(
    fileContents.replace("{MODEL_VERSION}", latestModel.toString())
        .replace("{CLIENT_VERSION}", latestClient.toString())
)

println("Done!")