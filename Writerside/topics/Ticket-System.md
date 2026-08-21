# Ticket System ✏️

The Ticket System provides a comprehensive solution for managing user support requests, carry service orders, and
general inquiries through Discord. Users create tickets via button interactions, and each ticket becomes a private
channel where staff can assist them.

## Key Concepts

### Ticket Panels

A ticket panel is a configuration that defines how tickets behave. Each panel creates a button in Discord that users
click to open a ticket. You can have multiple panels on the same server for different purposes (e.g., "Support", "Carry
Orders", "Bug Reports").

### Ticket States

Tickets move through four distinct states:

- **Creating** - Initial state while the ticket channel is being set up
- **Open** - Ticket is active and ready for support interaction
- **Closed** - Ticket has been resolved and closed, but the channel still exists
- **Deleted** - Ticket has been permanently deleted and the channel removed

### Claiming

Support staff can claim tickets to indicate they are handling a specific request. When a ticket is claimed, the channel can be renamed (if configured) and permissions are updated to reflect the claimer's ownership.

## Setting Up Ticket Panels

Ticket panels are configured through the [Dungeon Hub Dashboard](https://dashboard.dungeon-hub.net). You can access the ticket panel configuration by navigating to your server and selecting "Ticket Panels" from the menu.

For detailed configuration options, see [](Ticket-Panel-Configuration.md).

## Ticket Lifecycle & Visibility Flow

### Creation Process

1. User clicks a ticket creation button in Discord
2. If form questions are configured, a modal dialog appears for the user to fill out
3. The bot validates the form responses and creates the ticket in the database (state: `Creating`)
4. A private Discord channel is created with appropriate permissions
5. The channel is moved to the configured open category
6. An initial welcome message is sent to the channel (configurable via `ticketMessage`)
7. The ticket state is updated to `Open`

### Visibility & Access Control

Who can see and access a ticket depends on its current state:

**Open, Unclaimed Tickets:**
- ✓ Ticket creator (the user who opened it)
- ✓ Support roles (configured in `supportRoles`)
- ✓ Additional roles (configured in `additionalRoles`)
- ✗ Everyone else (the `@everyone` role has VIEW_CHANNEL denied)

**Open, Claimed Tickets:**
- ✓ Ticket creator (the user who opened it)
- ✓ Ticket claimer
- ✓ Additional roles (configured in `additionalRoles`)
- ✗ Support roles (their permissions are omitted while the ticket is claimed)
- ✗ Everyone else (the `@everyone` role has VIEW_CHANNEL denied)

**Closed Tickets:**
- ✗ Ticket creator (loses access when the ticket is closed)
- ✓ Additional roles (maintain access)
- ✓ Support roles when the ticket is unclaimed
- ✓ Ticket claimer (if applicable); support-role permissions are omitted
- ✗ Everyone else

**Deleted Tickets:**
- ✗ All users (the channel is permanently deleted after 3 seconds)

### State Transitions

- **Open → Closed** - Staff or creator closes the ticket using [](ticket-close.topic)
- **Closed → Open** - Staff reopens the ticket using the "Open" button
- **Closed → Deleted** - Staff deletes the closed ticket using the "Delete" button

> Permission changes happen automatically when tickets transition between states. The ticket creator loses access when a ticket is closed. When the ticket is claimed, SupportTeam permissions from `supportRoles` are omitted and the claimer receives support access instead; permissions from `additionalRoles` continue to apply.
> {style="note"}

### Claiming Effects

When a support staff member claims a ticket:
- The channel name updates to use the `claimedChannelName` template (if configured)
- Channel permissions are updated to include the claimer and omit SupportTeam permissions from `supportRoles`; permissions from `additionalRoles` continue to apply
- The claimer is tracked and can be referenced in messages via `{claimer.mention}`

## Transcripts

Transcripts capture the complete message history of a ticket in HTML format. You can configure when and where transcripts are delivered:

- **On Close** - Controlled by `closeTranscriptTarget`
- **On Delete** - Controlled by `deleteTranscriptTarget`

Both settings support four options:
- `None` - No transcript generated
- `User` - Transcript sent via DM to the ticket creator
- `TranscriptChannel` - Transcript posted in the configured transcript channel
- `Both` - Transcript sent to both user and channel

The transcript message sent to users can be customized via the `userTranscriptDm` JSON template.

> Transcripts are uploaded to a CDN and the URL is available via the `{transcript.url}` placeholder.
> {style="note"}

## Forms & Placeholders

### Custom Forms

Ticket panels can include custom form questions that users must answer when creating a ticket. Four question types are supported:

- **Predefined** - Pre-built form questions (e.g., `ign-display`, `carry-difficulty`, `carry-amount`)
- **TextInput** - Short text or paragraph input fields
- **StringSelect** - Dropdown selection from predefined options
- **TextDisplay** - Information text displayed to the user (no input required)

Form responses are stored with the ticket and can be referenced in messages via placeholders like `{ticket.form.1}` through `{ticket.form.5}`.

### Placeholders

Placeholders allow you to dynamically insert user, ticket, and system data into:
- Channel naming templates (`openChannelName`, `claimedChannelName`, `closedChannelName`)
- Ticket welcome messages (`ticketMessage`)
- Transcript DM messages (`userTranscriptDm`)

For a complete list of available placeholders, see [Ticket Placeholders](Ticket-Placeholders.md).

## Carry Integration

The ticket system integrates deeply with the carry service system:

### Related Carry Tier (`relatedCarryTier`)

Linking a ticket panel to a carry tier enables:
- The [](log.topic) command within tickets, allowing staff to log completed carries directly from the ticket
- Access to tier-specific carry data and validation
- Tier name available via `{carry-tier.name}` placeholder

### Related Carry Difficulty (`relatedCarryDifficulty`)

Specifying a carry difficulty further customizes the ticket:
- Visible carry price embeds are automatically displayed in tickets, showing the current pricing (if not provided through the form questions)
- The difficulty name is available via `{carry-difficulty.name}` placeholder

### Predefined Carry Form Questions

Two predefined form questions are available for carry tickets:
- **`carry-difficulty`** - Dropdown to select from available difficulties for the linked carry tier
- **`carry-amount`** - Text input for the number of carries requested

> Both `relatedCarryTier` and `relatedCarryDifficulty` are optional. Use `relatedCarryTier` alone if you want to enable carry logging without specifying a difficulty, or use both together for fully specified carry service tickets (if necessary).
> {style="note"}

## See Also

- [](Ticket-Panel-Configuration.md) - Detailed breakdown of all configuration options
- [](Ticket-Placeholders.md) - Complete placeholder reference
- [](ticket-close.topic) - Close a ticket
- [](ticket-add.topic) - Add a user to a ticket
- [](log.topic) - Log carries within carry tickets
- [Server Properties](Server-Property.md) - Configure `TRANSCRIPTS_CHANNEL` for ticket transcripts
