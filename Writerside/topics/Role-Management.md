# Role Management ✏️

The Dungeon Hub Bot has multiple systems in place to aid with the assignment and management of roles. This guide will
cover the various commands and features available to you as a server administrator.

## Role Actions

It is possible to set roles to automatically be applied to users based on a certain criteria.
A list of all possible actions can be found [here](Role-Action.md#possible-values). \
The role actions for all roles are checked once a user's roles are synced.

## Role Requirements

Role requirements are used to define which requirements have to be met to receive a given role. \
These consist of a [requirement type](Role-Requirement-Type.md), a comparison, a count (that has to be met) and optionally some extra data.
A list of all possible requirement types can be found [here](Role-Requirement-Type.md#possible-values). \
A role requirement can be added for a role by using [](role-requirements-add.topic).
Multiple role requirements can be defined for a single role, in that case all requirements have to be met to receive the role. \
Role requirements are checked after the role actions have been processed, meaning that the role requirements can override the role actions.

## Role Groups

Role groups are a way to group roles together and apply them all at once. If the user has a specific role that has role
groups, those are given to the user as well. \
Role groups are applied after the role actions and role requirements have been processed. \
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

