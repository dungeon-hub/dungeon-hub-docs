# Crafts, Transfers, and Reputation

The crafts and transfers (CNT) system connects people requesting an item craft or coin transfer with service team
members who can complete it. A completed request lets the requester give reputation to the member who handled it.

## Posting the request panel

An administrator first posts the CNT panel with [](send-cnt-message.topic). The panel offers value ranges from less
than 3 million coins through more than 400 million. Selecting a range opens a form for the request description, coin
value, and any additional requirements.

The completed request is posted in `CNT_MESSAGES_CHANNEL` when that property is configured. Otherwise, it is returned
where the form was submitted. The bot can mention `CNT_PING_ROLE` when it posts or refreshes a request.

Server administrators can restrict who may claim each value range with the corresponding
`CNT_ROLE_REQUIREMENT_*` properties. [](Server-Property.md) lists all supported ranges.

## Request lifecycle

1. A requester selects a value range and submits the request form.
2. An eligible service team member claims the request.
3. The claimer can unclaim it or mark it as done.
4. Completion records who handled the request and enables the requester to give reputation.

The request message changes its buttons as it moves between unclaimed, claimed, and completed states. Only the
request owner can use its reputation buttons.

## Giving reputation

Use `/rep` or the completed request's button to give one reputation point to its claimer. An optional reason can
describe the service. The bot rejects reputation when:

- You target yourself.
- The target is not a member of the server.
- The target has not completed one of your CNT requests during the last three days.
- You already gave that target reputation during the last three days.

Adding reputation refreshes registered reputation leaderboard messages. Use [](leaderboard-reputation.topic) to view
the interactive leaderboard.

## Moderating reputation

Members with `Manage Messages` can use `/manage-reps list` to inspect active and deactivated entries for a member and
their current reputation sum. `/manage-reps deactivate` disables an entry by ID and refreshes the reputation
leaderboard. Deactivated entries remain visible to moderators but no longer count toward the active total.

## See also

- [](send.topic)
- [](Static-Messages.md)
- [](help.topic)
- [](Role-Management.md)
