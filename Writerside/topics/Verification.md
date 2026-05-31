# Verification ✏️

Verification links a Discord user to their Minecraft account (by them supplying their IGN (ingame name)) and keeps their
roles and nickname in sync.

## Linking and unlinking

To link your account, use [](link.topic). You can run it in DMs or in a server. The bot verifies your IGN and confirms
if you have the same discord account as the one you set on Hypixel. \
To remove the link later, use [](unlink.topic).

> After linking or unlinking, roles and nickname are refreshed automatically where possible.
> {style="note"}

Admins can also prompt users with a convenient link UI by posting a helper message using [](send-link-message.topic).

## Syncing

Once a user is linked, their roles and nickname are kept in sync. This is explained further in [](Role-Management.md)

- On demand, members can run [](sync.topic) to apply the correct nickname and roles.
- Staff can force a sync for another user using [](force-sync.topic).
- For large updates, queue many users at once with [](mass-sync.topic) (by role or Hypixel guild).
  - This command also includes the [](mass-sync-list.topic) and [](mass-sync-clear.topic) utilities.

## Quick lookups

- Show the IGN for a Discord user with the [](ign.topic) command.
- Find the Discord user linked to a username with the [](find-user.topic) command.

See also

- [](send.topic) for posting the link-message.
- [](lookup.topic) to check if a user/IGN is flagged, this also checks their linked account if one exists.
