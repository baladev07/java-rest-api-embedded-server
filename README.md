# Java Rest API with java servlet

This project contains Java-based REST API that allows users to perform CRUD operations on a user entity. The API is built using Java 8, javax.servlet, and an embedded Tomcat server. The API uses Hibernate and JDBC to interact with a MySQL database for persistence.

# Getting Started
To get started with this project, you will need to have Java and MySQL on your machine. You can download Java from the Oracle website, MySQL from the MySQL website.

Once you have these dependencies installed, you can clone this repository and import the project into your favorite Java IDE. You can then run the Application this start the embedded Tomcat server.

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
- Create a User: `POST/api/user`
- Update a User: `PUT/api/user/{id}`
- Delete a User (by id): `DELETE/api/user/{id}` 
- Get all Users: `GET/api/users`

To add multiple end points and validation i have added all the end points in XML file and  used parser to read all the attributes for every request and 

```xml
<request uri="/users" class="com.Users.UsersHandler" QName="UserList" method="GET" EntityName="com.Model.UserObject">
        <Parameter name="" source="" type="" value=""/>
    </request>
```  


# Details

`GET/api/users`

This end point return list of users.

```json
{
    "message": "success",
    "Result": {
        "users": [
            {
                "created_time": 0,
                "user_role": 0,
                "last_modified_time": 0,
                "user_id": 1,
                "user_name": "elon"
            }
        ]
    }
}

```
- 200 - OK: Everything worked as expected.

- 400 - Internal Server Error: something went wrong on API.

To map the request to the class, class instance is created using the address of the class mentioned in xml file.

```java
Class classObj= Class.forName(classname);
APIHandler obj= (APIHandler) classObj.newInstance();

```

To fetch all users execute the query sql connector is used to connect to database and execute the query.

```java
public static Connection getConnection() {
        Connection connection=null;
        try{
            String url = "jdbc:mysql://localhost:3306/test";
            String username = "root";
            connection=DriverManager.getConnection(url, username, "");;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return connection;
    }
```   

# Technologies used

- java 8
- apache tomcat
- mysql connector
- h2
- object mapper
- gradle

# Compile and package

The api was developer to run with an `jar`. In order to generate jar you should run:
```
gradle createJar
```
This command will compile and generate a jar at the target directory.

# Execution

To create a database and populate tables.

- Go to the conf/ from the terminal and execute the following `shell command`
```shell
sh mysql.sh
```
# Run

In order to run the API , run the jar as following:

```command
java -jar Demo.jar
```
# License
This project is licensed under the MIT license. Please see the LICENSE file for more information.
