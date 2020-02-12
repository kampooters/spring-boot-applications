# About the Application
This project holds the following features
##### Features
  * **CRUD operations:**  for Person Model
  * **Webfront for API interaction :** Swagger 2.0 is used for APIs documentation and testing
  * **Security:** APIs are secured with oAuth2 as its beleived that oAuth is the most performant security approach for ROA based architectures
  * **No need for installation :** 
    * Spring boot is used as framework. So just run the the jar and test the APIs
  * **Live Tracing**  
    * Logger is used for live issue tracing
  * **InMemory Database :** H2 Database as its portable and no need for installation
  * **Build Tool :** Maven as build tool 
  * **Logger :** Logback
  


## Getting Started
Please follow the instructions to test the functionality

### Installation
#### Clone repository
* clone the git repo through following command
```git clone https://github.com/kampooters/spring-boot-applications.git```

#### Run from .jar file
* Go to folder ```\spring-boot-applications\crud-with-h2-mybatis-oauth2.0\app\target```
* There will a jar file ```app-0.0.1-SNAPSHOT.jar```
* Run the following command
     ```
     java -jar app-0.0.1-SNAPSHOT.jar
     ```
* Now hit the following URL in your favourite browser to open the swagger interface
    ```
    http://localhost:8080/swagger-ui.html#/
    ```
#### Build and Run Source code
* Go to folder ```\spring-boot-applications\crud-with-h2-mybatis-oauth2.0\```
* Run the following command
     ```
     mvn clean install
     ```
* Go to folder ```\spring-boot-applications\crud-with-h2-mybatis-oauth2.0\app\target```
* Run the following command
     ```
     java -jar app-0.0.1-SNAPSHOT.jar
     ```
* Now hit the following URL in your favourite browser to open the swagger interface
    ```
    http://localhost:8080/swagger-ui.html#/
    ```
    
    
## TEST FUNCTIONALITY
* After starting the application
* Hit the following URL in your favourite browser to open the swagger interface
    ```
    http://localhost:8080/swagger-ui.html#/
    ```
* Now Swagger UI is opened
#### Authentication (oAuth2.0)
* Now first you have to register a user to test the APIs because REST APIs are secure
* Click on   ```auth-controller ``` link, it will list the available APIs to ```register user``` ```get access token``` and ```refresh access token``` 
* Here Firs you have to register the user, So click the ```POST /ns/api/v1/auth/register``` API
* Here provide ```client_id``` a valid email like ```test@gmail.com``` and ```secret``` a random password like ```Test123```
* Now click Button ```Try it out!```
* You will get a response like 
```
{
  "personList": null,
  "code": 200,
  "error": "",
  "timestamp": "2020-02-12T12:27:00.564+0000",
  "message": "",
  "path": "/ns/api/v1/auth/register",
  "accessToken": "9592b524871e46208492cb4a7a6a03ac",
  "refreshToken": "25a0fac380dd4db59f5dbd85414bc2d1",
  "authorizeToken": "ff6a48cc60db497993f8653e7f6c68cb",
  "clientId": "test@gmail.com"
}
```

* Now copy the value of ```accessToken``` and close ```auth-controller ``` by clicking on it
* Now click on ```person-controller ``` it will list the following APIs
```
POST /ns/api/v1/person/create (It will create a user in database)
```
```
DELETE /ns/api/v1/person/delete (It will delete the Person on ID bases)
```
```
GET /ns/api/v1/person/get/{id} (Gets the Person on ID bases)
```
```
GET /ns/api/v1/person/test (A test API)
```
```
PUT /ns/api/v1/person/update (Updates the user on ID bases)
```
#### CRUD Operations (Person Model)
* **Create Person** Now click on ```/ns/api/v1/person/create```. It will explain the request inputs and respnse output
    * Now provide the following JSON in Body with your desired values
    ```
        {
          "age": 20,
          "favourite_color": "GREEN",
          "first_name": "Abdul",
          "hobby": [
            "Book Reading", "Coding", "Chess"
          ],
          "last_name": "Rehman"
        }
    ```
    * Provide the access token value (Got from auth-controller, register API) in ```Authorization``` Header
    * Provide the ```ClientId``` Header value (test@gmail.com) value, same which is used for register API
    * Now click the button ```Try it out!```
    * You will get the response by holding the added user with generated ID like 
    ```
    {
      "personList": [
        {
          "id": 1,
          "first_name": "Abdul",
          "last_name": "Rehman",
          "age": 20,
          "favourite_color": "GREEN",
          "hobby": [
            "Book Reading",
            "Coding",
            "Chess"
          ]
        }
      ],
      "code": 200,
      "error": "",
      "timestamp": "2020-02-12T12:45:05.024+0000",
      "message": "",
      "path": "/ns/api/v1/person/create"
    }
    ```
    * Now you can ```UPDATE```, ```DELETE```, ```GET``` the Person on ```ID``` bases 
    
* **Update Person** Now click on ```/ns/api/v1/person/update```. It will explain the request inputs and response output
    * Now provide the following JSON in Body with your desired values (IMPORTANT: ID must be provided)
    ```
        {
          "age": 20,
          "favourite_color": "GREEN-1",
          "first_name": "Abdul-1",
          "hobby": [
            "Book Reading", "Coding", "Chess-1"
          ],
          "id": 1,
          "last_name": "Rehman-1"
        }
    ```
    * Provide the access token value (Got from auth-controller, register API) in ```Authorization``` Header
    * Provide the ```ClientId``` Header value (test@gmail.com) value, same which is used for register API
    * Now click the button ```Try it out!```
    * You will get the response by holding the added user with generated ID like 
    ```
    {
      "personList": null,
      "code": 200,
      "error": "",
      "timestamp": "2020-02-12T12:53:46.230+0000",
      "message": "",
      "path": "/ns/api/v1/person/update"
    }
    ```
    * Now you can ```DELETE```, ```GET``` the Person on ```ID``` bases 

* **GET Person** Now click on ```/ns/api/v1/person/get```. It will explain the request inputs and response output
    * Now provide ```id``` value which you got at the insert time suppose its 1
    * Provide the access token value (Got from auth-controller, register API) in ```Authorization``` Header
    * Provide the ```ClientId``` Header value (test@gmail.com) value, same which is used for register API
    * Now click the button ```Try it out!```
    * You will get the response by holding the added user with generated ID like 
    ```
    {
      "personList": [
        {
          "id": 1,
          "first_name": "Abdul-1",
          "last_name": "Rehman-1",
          "age": 20,
          "favourite_color": "GREEN-1",
          "hobby": [
            "Book Reading",
            "Coding",
            "Chess",
            "string",
            "Book Reading",
            "Coding",
            "Chess-1"
          ]
        }
      ],
      "code": 200,
      "error": "",
      "timestamp": "2020-02-12T12:53:53.565+0000",
      "message": "",
      "path": "/ns/api/v1/person/get/{id}"
    }
    ```
    * Now you can ```DELETE``` the Person on ```ID``` bases 
    
    * **DELETE Person** Now click on ```/ns/api/v1/person/delete```. It will explain the request inputs and response output
        * Now provide ```id``` value which you got at the inser time suppose its 1
        * Provide the access token value (Got from auth-controller, register API) in ```Authorization``` Header
        * Provide the ```ClientId``` Header value (test@gmail.com) value, same which is used for register API
        * Now click the button ```Try it out!```
        * You will get the response by holding the added user with generated ID like 
        ```
        {
          "personList": null,
          "code": 200,
          "error": "",
          "timestamp": "2020-02-12T12:58:51.242+0000",
          "message": "",
          "path": "/ns/api/v1/person/delete"
        }
        ```
        * Now you can will not get the Person of deleted```ID```



### H2 Database UI Access
* H2 in-memory database is used 
* Once the application is started you can access H2 database on following URL ```http://localhost:8080/h2-console```
* Here are H2 credentials ```sa/password```
* Once you are connected you can view the tables and data populated


     
### Architecture and Design
We have followed the following architectural and design approaches
* **Architecture and Architectural Patterns:** Monolithic Architecture, Layered Pattern, DAL Patterns, MVC
* **Design and Design Patterns:** IOC, Dependency injection, ORM, POJO, DAO, Sigleton, Adapter,Filter, Decorator


### Technical Dependencies
The code for this application is written 
* Java 8
* Spring Boot
* Monolithis Architecture pattern
* H2 In memory database
* Mybatis
* Logback
* SL4j
* oAuth2 (Application mode) RFC specifications
* Maven

### Prerequisites

Following software need to be installed for development and debugging purpose.

```
Java 1.8 for development or changes in code.
Maven
Git
```

### Installation

* [JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html?printOnly=1) - The Java Development Kit provides java libraries for development
* [Maven] - JDK is prerequisites for maven installtion
* Git


### Environment Variables

After installing JDK and Gradle you need to set Environment Variable like

```
JAVA_HOME = C:\Program Files\Java\jdk1.8.0_212
MAVEN_HOME = <maven installation directory>
```


#### API docs
* http://localhost:<port>/swagger-ui.html


## Versioning
0.1

## Authors

* Abdulrehman (abdulrehman.abdul.qau@gmail.com)
