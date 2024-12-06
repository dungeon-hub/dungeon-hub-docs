# Role Management ✏️

The Dungeon Hub Bot has multiple systems in place to aid with the assignment and management of roles. This guide will
cover the various commands and features available to you as a server administrator.

## Role Actions

It is possible to set roles to automatically be applied to users based on a certain criteria.
A list of all possible actions can be found [here](Role-Action.md#possible-values). \
The role actions for all roles are checked on a user's roles are synced.

## Role Requirements

`This feature is currently under active development and will be released soon.`

## Role Groups

Role groups are a way to group roles together and apply them all at once. If the user has a specific role that has role
groups, those are given to the user as well. \
Role groups are applied after the role actions have been processed. \
You can think of role groups similar to a tree structure:

### Structure

![](role-groups.png) { border-effect="rounded" }

You can also take a look at these two example use-cases:

### Examples { collapsible="true" }

![](role-groups-dh-carriers.png) { border-effect="rounded" }

This example ensures that all service team members have the general role `Service Team` and the carry-type specific roles, such as `Dungeon Carrier`. \
This ensures that permissions can be set up for all service team members, while also allowing for specific permissions for each carry type. It also allows to easily ping all service team members at once, as well as it gives an easier overview over
all service team members.

![](role-groups-dh-staff.png) { border-effect="rounded" }

