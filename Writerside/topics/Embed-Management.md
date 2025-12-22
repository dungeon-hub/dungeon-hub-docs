# Embed Management ✏️

Work with Discord embeds directly: read existing embeds, send new ones, and modify embeds on bot messages.
Multiple embeds per message are supported (as Discord allows).
Use the [](embed.topic) command to access these features.

## Embed Format

Most [](embed.topic) sub-commands expect embed data as a JSON string. Provide either a single object (one embed) or a JSON array (multiple embeds).
To inspect the exact format, run [](embed-get.topic) on a message with an embed.

Supported input fields when sending/editing:

- `title`: String
- `description`: String
- `url`: String
- `timestamp`: Unix timestamp in milliseconds (number or numeric string)
- `color`: Hex color with leading `#` (for example `#A51770`)
- `footer`: Object with `text` and optional `icon`
- `thumbnail`: Object with `url`
- `author`: Either a string (name) or an object with `name`, optional `url`, optional `icon`
- `fields`: Array of objects `{ name, value, inline }`
- `image`: URL of an image, can be either null or omitted entirely

Notes:

- The `author` field accepts either a single string (used as the name) or an object with properties.

```json
{
  "title": "title",
  "description": "description",
  "url": "url for title",
  "timestamp": "timestamp in epoch milliseconds",
  "color": "color in hex format, with leading #",
  "image": "example image url",
  "footer": {
    "text": "footer text",
    "icon": "icon url"
  },
  "thumbnail": {
    "url": "thumbnail url"
  },
  "author": {
    "name": "author name",
    "url": "author url",
    "icon": "author icon url"
  },
  "fields": [
    {
      "name": "field name",
      "value": "field value",
      "inline": "boolean, if the field should be inline"
    }
  ]
}
```
{collapsible="true" default-state="collapsed" collapsed-title="Format"}

```json
{
  "title": "Example Embed",
  "description": "Example embed, just to try it out",
  "url": "https://example.com/",
  "timestamp": 1732920448245,
  "color": "#A51770",
  "image": "https://static.dungeon-hub.net/blaze.png",
  "footer": {
    "text": "footer text",
    "icon": "https://static.dungeon-hub.net/favicon.gif"
  },
  "thumbnail": {
    "url": "https://static.dungeon-hub.net/enderman.png"
  },
  "author": {
    "name": "author name",
    "url": "https://example.com/",
    "icon": "https://static.dungeon-hub.net/livid.png"
  },
  "fields": [
    {
      "name": "field name",
      "value": "field value",
      "inline": true
    },
    {
      "name": "field name 2",
      "value": "field value 2",
      "inline": false
    }
  ]
}
``` 
{collapsible="true" default-state="collapsed" collapsed-title="Example Embed"}

### CDN links and size limits

- [](embed-get.topic) returns JSON in a code block by default. If the JSON exceeds ~4000 characters or if you select the `cdn` output type, the JSON is uploaded and a CDN link is returned instead.
- [](embed-send.topic) can accept a CDN link produced by `get` or raw JSON.

### URL format

Some fields in the embed can contain a URL. This URL is often a link to an image that should be used, but it can reference any possible url.
If any of the URLs are not valid, sending may fail with an error.
The URL must contain a valid protocol, meaning it must start with e.g. `http://` or `https://`.

### Message Link

The message link is a link to a message in discord, which can be obtained by right-clicking on a message and selecting `Copy Message Link`.
This link is used to identify the message that should be edited or read from.
```regex
(?x)                             # enable comment mode
(?i)                             # ignore case
(?:https?+://)?+                 # 'https://' or 'http://' or ''
(?:(?:canary|ptb)\\.)?+          # 'canary.' or 'ptb.'
discord(?:app)?+\\.com/channels/ # 'discord(app).com/channels/'
(?:(?<server>[0-9]++)|@me)       # '@me' or the server id as named group
/                                # '/'
(?<channel>[0-9]++)              # the textchannel id as named group
/                                # '/'
(?<message>[0-9]++)              # the message id as named group
```
{collapsible="true" default-state="collapsed" collapsed-title="Message Link Regex"}

### Timestamp

The `timestamp` field is a unix timestamp in milliseconds. This is used to display a timestamp in the embed. \
You can convert a date to a timestamp and vice versa by using websites like [this](https://www.epochconverter.com/). \
Please make sure that the timestamp is in milliseconds, as the bot will not convert it for you, resulting in incorrect dates being shown.

### Color

The `color` field is a hex color code, with a leading `#`. This color is used to color the left bar of the embed. \
You can use websites like [this](https://www.w3schools.com/colors/colors_picker.asp) or [this](https://www.color-hex.com/) to find a color you like.
