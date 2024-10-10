FROM registry.jetbrains.team/p/writerside/builder/writerside-builder:242.21870 as build

ARG INSTANCE=Writerside/dhub

RUN mkdir /opt/sources

WORKDIR /opt/sources

ADD Writerside ./Writerside

RUN export DISPLAY=:99 && \
Xvfb :99 & \
/opt/builder/bin/idea.sh helpbuilderinspect -source-dir /opt/sources --product $INSTANCE --runner other --output-dir /opt/wrs-output/

WORKDIR /opt/wrs-output

RUN unzip -O UTF-8 webHelpDHUB2-all.zip -d /opt/wrs-output/unzipped-artifact

FROM httpd:2.4 as http-server

COPY --from=build /opt/wrs-output/unzipped-artifact/ /usr/local/apache2/htdocs/