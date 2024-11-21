# Warnings

Warnings are an easy way to log wrongful behavior of users, together with an automated way of punishing them based on
the amount of warnings received.

## Displaying your warnings

To display the active warns you currently have, you can use the command [](warns.topic).
> You need to have the `Moderate Members` permission to be able to see warns of other users through this command.
> {style="note"}

To display all warns, even if they [were deactivated](warn-deactivate.topic), you can use the
command [](warn-list-all.topic).

## Managing warnings

It's possible to manage warns using the command [](warn.topic). All warnings that are created have a unique numerical id
that is used across all commands. \
When a warning is [given to a server member](warn-add.topic), [punishments](Warning-Punishment.md) can be automatically
applied to the user.
> Currently, configuration of punishments is unavailable to server admins. If you wish to use them, please
> contact [the development team](mailto:admin@dungeon-hub.net)
> {style="warning"}

[Punishments](Warning-Punishment.md) aren't updated/lifted when a warning is [deactivated](warn-deactivate.topic) or
updated by an [evidence being added](warn-add-evidence.topic).

When warnings are edited, either by them being created, when an evidence is added or when they are deactivated, the
change is logged in the [respective channel](#log-channel).

## Log-Channel

When warnings are managed successfully, a log message containing the relevant information is sent into the log
channel. \
The log channel depends on the [](Warning-Type.md):
If the [warning type](Warning-Type.md) is either `Serious`, `Major` or `Minor`, the message is sent into the channel of
the [server property `MODERATION_LOGS_CHANNEL`](Server-Property.md), otherwise it's sent into the
[`STRIKES_LOG_CHANNEL`](Server-Property.md).
