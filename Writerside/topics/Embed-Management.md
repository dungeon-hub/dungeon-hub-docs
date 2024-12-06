# Embed Management ✏️

The Dungeon Hub Bot contains an advanced feature to read message data from embeds, send new embeds and also edit them
afterward.
This feature fully supports adding multiple embeds onto one message, which is supported by discord.
This feature is mainly enabled through the command [](embed.topic).

## Embed Format

Most sub-commands of [](embed.topic) expect the embed-data to be passed as a json-string.
This can either contain one object, or a json array of multiple object.
You can get an example of the format that is used by running the command [](embed-get.topic) and supplying the message
link of a message with an embed. \
The format that is expected in those arguments looks like this:

```json
{
  "title": "title",
  "description": "description",
  "url": "url for title",
  "timestamp": "timestamp in epoch seconds",
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
{collapsible="true" default-state="expanded" collapsed-title="Format"}

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
