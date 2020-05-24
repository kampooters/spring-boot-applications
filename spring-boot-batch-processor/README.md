# About the Application
This project holds the following features
##Configurations
The application architecture and design is completly dynamic. Even the Algorithm to calculate the rewards is also dynamic.
Here are the configurations for Algorithm calculation where you can put your requirements at run time

**application.properties file**
````
batch.algorithm.rules.transaction.marchant.factor: Airlines*2,Airlines*2
batch.algorithm.rules.transaction.types: ecom, remaining
batch.algorithm.rules.transaction.ecom: 0-2.49*0, 2.50-4.99*1, 5.00-9.99*2, 10.00-99.99*3, 100.00-299.99*7*%, 300.00-999.99*10*%, 1000-0*15*%
batch.algorithm.rules.transaction.remaining: 0-2.49*1, 2.50-4.99*2, 5.00-9.99*3, 10.00-99.99*4, 100.00-299.99*9*%, 300.00-999.99*11*%, 1000-0*13*%

````

#### Reward Calculation Configurations
#### Description
For Transactions of type: ECOM
```
| Amount(EUR)  | Reward Points  |
|--------------|----------------|
|     0-2.49   |       0        |
| 2.50-4.99    |       1        |
| 5.00-9.99    |       2        |
| 10.00-99.99  |       3        |
|100.00-299.99 |7% of Transaction Amount|
|300.00-999.99 |10% of Transaction Amount|
|1000+         |15% of Transaction Amount|

For all other Transactions

| Amount(EUR)  | Reward Points  |
|--------------|----------------|
|     0-2.49   |       1        |
| 2.50-4.99    |       2        |
| 5.00-9.99    |       3        |
| 10.00-99.99  |       4        |
|100.00-299.99 |9% of Transaction Amount|
|300.00-999.99 |11% of Transaction Amount|
|1000+         |13% of Transaction Amount|
```
Transactions of negative amount lead to deduction of points as per the above rules.

##### Transaction type Configurations
Put the all transactions in the following field with comma separation rules for which you want to put the above rules (all should be in lower case).
you will use the remaining keyword for all rest of the transaction types
````
batch.algorithm.rules.transaction.types: ecom, remaining
````
##### Reward Calculation configuration rules
Put the rules for above mention transaction in following manner (all should be in lower case)

````
batch.algorithm.rules.transaction.ecom: 0-2.49*0, 2.50-4.99*1, 5.00-9.99*2, 10.00-99.99*3, 100.00-299.99*7*%, 300.00-999.99*10*%, 1000-0*15*%
batch.algorithm.rules.transaction.remaining: 0-2.49*1, 2.50-4.99*2, 5.00-9.99*3, 10.00-99.99*4, 100.00-299.99*9*%, 300.00-999.99*11*%, 1000-0*13*%
````

her how will you define the transaction rules 
* put the transaction type at the end of this variable ```batch.algorithm.rules.transaction``` like ```````batch.algorithm.rules.transaction.ecom```````.
* Now define reward rules. Put the all above table rules like following ``300.00-999.99*10*%`` . 
Here ```300.00``` is minimal amount and ``999.99`` is max amount while ```-``` is separator. Then ``10`` is reward factor and ``*`` is separator b/w amounts and reward 
then ```%``` is mathematical operator. If you have infinit max amount then use 0

##### Merchant Rules Configurations
Transactions at merchants of Type `Airlines` get 2x the points.
* Define the merchant configurations like following

````
batch.algorithm.rules.transaction.marchant.factor: Airlines*2
````

##### Reward Point Rules
* Customer cannot have negative reward Points. The minimum number of reward points possible is 0.
* There is no maximum limit to the number of reward points a customer can have
* The reward points can only be of type unsigned integers. Floor the points to the nearest integer if the entitled reward points is not a whole number.   




##Features
#### Application Features
  * **Reward calculation:**  Calculate the Customer rewards by uploading the files (size limit: 5MB) or from local file path (Large files)
  * ** Dynamic and Extensible Architecture** The application architecture is dynamic to accomodate the changes at run time and extensible at any level
  * **Scalability** You can process multiple files of Large datasets for different clinets at run time
  * **Webfront for API interaction :** Swagger 2.0 is used for APIs documentation and testing. Complete detailed documentation for inputs and outputs are provided
  * **Security:** APIs are secured with oAuth2 as its believed that oAuth is the most performant security approach for ROA based architectures
  * **No need for installation :** 
    * Spring boot is used as framework. So just run the the jar and test the APIs
  * **Live Tracing**  
    * Logger is used for live issue tracing
  * **InMemory Database :** H2 Database as its portable and no need for installation
  * **Build Tool :** Maven as build tool 
  * **Logger :** Logback

* #####Code Quality
    * **Correct:** The code is correct and to the point
    * **Readable:** The source code completly structured and documented. The detailed comments and javadocs are provided
    * **Maintainable:** The source code is completely documented and well structured So its easy to maintain
    * **Extensible:** Design patterns are used, So you can easily extend the functionality
    * **Well Tested:** Completely tested with unit tests
    * **Scalable:** The application scalable horizantly and vertically
    * OOP and OOD techniques are used
    * Performant and thread safe data structure used
    * **Reasons Behind Tools**
        * **Spring Boot** Minimum time to market, minimal configurations for development and deployment and even for monitoring and application extensability
         * **Spring batch**  used for large data processing and application scalability
         * **Data Layer** Plugable data layer
         * **Dynamic configurations** The application inputs and outputs are configurable even the alogorithm logic as well
    * Ease of building, running, testing the code
    * Tests presence or absence there of.
    * Overall architecture and it's scalability
    * The reasoning behind the assumptions you made
    * Build tooling
    * Infrastructure setup if any
    * **Performance** 




## Getting Started
Please follow the instructions to test the functionality

### Installation
#### extract repository
* ```extract the zip folder```

#### Run from .jar file
* Go to folder ```\root\app\target```
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
* Go to folder ```\root folder\```
* Run the following command
     ```
     mvn clean install
     ```
* Go to folder ```\app\target```
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

#### Without Authentication
APIs are secured with oAuth2.0.  OAuth2.0 is disabled by default in application.properties file.
* **Note** If you want to enable the oAuth then mark the following field true in application.properties file
    ````spring.application.auth2.enabled = true````
* **Process Customers data**
    * **Small files (5MB)** If file size is 5MB then you can directly upload the file and and process it through following API
        * ````POST /api/v1/customer/process/csv/upload````
            * This API processes the customers file and stores the data in database
    * **Large File (GBs)** If you want to process the large files then use the following API
        * ````POST /api/v1/customer/process/csv/path````
            * **Remote Server:** If you are on remote server then FTP the file on any loaction on server which app is deployed and provide teh absolue path in file_path Header.
            *  **Local Server:** (Local Machine): if you are on local server then provide the local file path like (Windows) D:\Source Code\Workspaces\Practise\spring-assignment\data\transactions.csv
 * **Get Calculated Rewards**
    * **Download CSV** To download the rewards in csv file use the following API but keep in mind you have pass the clientName for which you processed the CSV
        * ````GET /api/v1/customer/reward/download/csv````
    * **Get Rewards in JSON format** To get the rewards in JSON format use the following API but keep in mind you have pass the clientName for which you processed the CSV
        * ````GET /api/v1/customer/reward/get```
        
    
#### Authentication (oAuth2.0)
* If you want to enable the oAuth then mark the following field true in application.properties file
    ````spring.application.auth2.enabled = true````
* Now first you have to register a user to test the APIs because REST APIs are secure
* Click on   ```auth-controller ``` link, it will list the available APIs to ```register user``` ```get access token``` and ```refresh access token``` 
* Here Firs you have to register the user, So click the ```POST /ns/api/v1/auth/register``` API
* Here provide ```client_id``` a valid email like ```test@gmail.com``` and ```secret``` a random password like ```Test123```
* Now click Button ```Try it out!```
* You will get a response like 
```
{
  "data": null,
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

* Now copy the value of ```accessToken``` and  ```clientId``` 
* **Note** You will use these ````accessToken and clientId```` in ``Authorization`` and ``ClientId`` headers for oAuth2.0 authentication

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
* Spring Boot, Spring batch, Spring MVC, Spring REST
* Monolithic Architecture pattern
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
