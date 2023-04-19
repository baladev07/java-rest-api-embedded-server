# java-rest-api-embedded-server

This project contains Java-based REST API that allows users to perform CRUD operations on a user entity. The API is built using Java 8, javax.servlet, and an embedded Tomcat server. The API uses Hibernate to interact with a MySQL database for persistence.

# Getting Started
To get started with [Project Name], you will need to have Java and MySQL on your machine. You can download Java from the Oracle website, MySQL from the MySQL website.

Once you have these dependencies installed, you can clone this repository and import the project into your favorite Java IDE. You can then run the API by launching the embedded Tomcat server.

# API Endpoints

The starting point of the web application is javax.servlet.http.HttpServlet class. And this servlet is added in tomcat so tomcat will redirect our `/api` request to the restapiservlet

```java

public static void startServer() throws Exception {
        RestApiServlet restApiServlet=new RestApiServlet();
        Tomcat tomcat=new Tomcat();
        tomcat.setBaseDir(basDir);
        Context context =tomcat.addContext("",basDir);
        tomcat.setPort(8081);
        tomcat.addServlet("","testServlet", restApiServlet);
        context.addServletMappingDecoded("/api/*","testServlet");
        tomcat.start();
        tomcat.getServer().await();
    }
    
```   

when you run the program it will start the tomcat web server at port 8081 and expose out the  all the end points mention below:

This API provides HTTP endpoint's and tools for the following: 
    
    - Create a product: `POST/api/user`
    - Update a product: `PUT/api/user/{id}`
    - Delete a product (by id): `DELETE/api/user/{id}` 
    - Get all users: `GET/api/users`


Creates a new user in the database. The request body should include a JSON object with the following properties:

username (string, required): The username of the new user.
useremail (string, required): The email address of the new user.
The API will return a JSON object with the following properties:

id (integer): The ID of the new user.
username (string): The username of the new user.
useremail (string): The email address of the new user.
Get All Users
GET /api/user

Returns a list of all users in the database. The API will return a JSON array of user objects, where each user object has the following properties:

id (integer): The ID of the user.
username (string): The username of the user.
useremail (string): The email address of the user.
Get a User
GET /api/user/{id}

Returns the user with the specified ID. The API will return a JSON object with the following properties:

id (integer): The ID of the user.
username (string): The username of the user.
useremail (string): The email address of the user.
Update a User
PUT /api/user/{id}

Updates the user with the specified ID. The request body should include a JSON object with the following properties:

username (string): The new username for the user.
useremail (string): The new email address for the user.
The API will return a JSON object with the updated user information, including the ID, username, and email address.

Delete a User
DELETE /api/user/{id}

Deletes the user with the specified ID from the database. The API will return a JSON object with the following properties:

id (integer): The ID of the deleted user.
username (string): The username of the deleted user.
useremail (string): The email address of the deleted user.
Contributing
If you'd like to contribute to [Project Name], please fork the repository and create a pull request with your changes. We welcome contributions of all types, including bug fixes, new features, and documentation improvements.

# Technologies used


# License
[Project Name] is licensed under the MIT license. Please see the LICENSE file for more information.
