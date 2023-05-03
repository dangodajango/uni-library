When each module is started, an embedded database is created.

To connect to the front-end database:
http://localhost:8082/h2-console
username: admin
password: admin

To connect to the back-end database:
http://localhost:8081/h2-console
username: admin
username: admin

The separation is made, for security reasons, 
the front-end database contains tables for user authentication, 
while the back-end database store the information about the library, we don't want to mix them.