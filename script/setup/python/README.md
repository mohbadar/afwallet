

##### A script that clones Github repositories of users and organizations. #


###### Usage #

| Description                                               | Command                                                                     |
|-----------------------------------------------------------|-----------------------------------------------------------------------------|
| Help                                                      | `./setup.py --help`                                                  |
| Clone all repositories of a single user.                  | `./setup.py --user user -o /tmp/output`                              |
| Clone all repositories of multiple users.                 | `./setup.py --user user1,user2,user3 -o /tmp/output`                 |
| Clone all repositories of a single organization.          | `./setup.py --org organization -o /tmp/output`                       |
| Clone all repositories of multiple organizations.         | `./setup.py --org organization1,organization2 -o /tmp/output`        |
| Clone all repositories of an organization in a hosted Github       | `./setup.py --org organization -o /tmp/output` --api-prefix https://git.company.com/api/v3       |
| Modify the amount of used threads                         | `./setup.py --user user --threads 10 -o /tmp/output`                 |
| Clone all repositories of an organization, along with all repositories of the organization's members.       | `./setup.py --org organization --include-org-members -o /tmp/output` |
| Use Github authentication in the task.                    | `./setup.py --org organization -o /tmp/output --authentication user:token`|
| Clone authenticated repositories that the authenticated user has access to. | `./setup.py -o /tmp/output --authentication user:token --include-authenticated-repos`|
| Include gists.                                            | `./setup.py --user user -o /tmp/output --include-gists`              |
| Save repos as username_reponame                           | `./setup.py --user user -o /tmp/output --prefix-mode underscore`     |
| Save repos as username/reponame                           | `./setup.py --user user -o /tmp/output --prefix-mode directory`      |
| Save repos as reponame                                    | `./setup.py --user user -o /tmp/output --prefix-mode none`           |
| Print gathered URLs only and then exit.                   | `./setup.py --user user --include-gists --echo-urls`                 |


##### Compatibility #
The project is compatible with both Python 2 and Python 3.


#### Requirements #
* Python2 or Python3
* requests
* gitpython

#### Testing
* nosetests -vx

