# Role Action ✏️

Role actions describe which action should be taken for a given role, depending on if the server member is linked or not.
This is checked every time the users roles are synced.

## Possible values

- None
- Apply And Remove When Verified
    - This applies the role when a user is verified and removes it when the user is unverified
- Apply And Remove When Unverified
    - This applies the role when a user is unverified and removes it when the user is verified
- Apply When Verified
- Apply When Unverified
- Remove When Verified
- Remove When Unverified
- Apply Always
