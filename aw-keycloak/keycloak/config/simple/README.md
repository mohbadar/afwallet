
## Installing and Configuring Keycloak
Download the standalone server distribution from the [Keycloak website](https://www.keycloak.org/), unpack it and start the server. Follow the [Getting Started](https://www.keycloak.org/docs/latest/getting_started/index.html#creating-the-admin-account) instructions to setup the administrator account.

There are two ways to configure the Keycloak realm for this application:
1. Import the keycloak-setup-config.json
2. Follow the **Create Realm, Client and Users** guide

### Create Realm, Client and Users
>This section is only for those who wish to manually configure the Keycloak server.

#### 1. Create a realm
Follow the [create a realm](https://www.keycloak.org/docs/latest/getting_started/index.html#_create-realm) instructions and create a realm called: `anar`
#### 2. Create a client
Follow steps 1- 3 of the [creating and registering](https://www.keycloak.org/docs/latest/getting_started/index.html#creating-and-registering-the-client) guide and create a new client called: `anar-app`

In the **Valid Redirect URIs** field enter the two URLs: `http://localhost:8081/*` and `http://localhost:4200/*`
> Note the asterisk (*) after the urls!

And in the **Web Origins** fields simply add a `*` (asterisk)
#### 3. Create roles and assign permissions
In the Keycloak administration console create two new roles, named: `user` and `admin`
Edit the `admin` role and enable the **Composite Roles** flag and choose `realm-management` from the **Client Roles** droplist. 
Highlight the `view-users` option in the **Available Roles** block and then click on the "Add selected" button.
#### 4. Create the following users:
| Username | Password | First Name | Last Name | Email | Roles |
| ------ | ------ | ------ | ------ | ------ | ------ |
| badar | password | Mohammad Badar | Hashimi | `badar.hashimi.dev@gmail.com` | ADMIN, WEBUSER, ORGANIZATION |
| jabbar | password | Abdul Jabbar | Hashimi | `jabbar.hashimi@gmail.com` | ORGANIZATION |
| sattar | password | Abdul Sattar | Hashimi | `sattar.hashimi@gmail.com` | WEBUSER |
> It is most important that you enter the username as provided in the table, because they are used in the Spring backend to link the customer orders with the user.

