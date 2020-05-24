# Instructions to Run

Follow the following instructions
* Install eclipse and maven
* checkout the repo
* maven build
* Run the application by executing the class 'com.org.telenor.easypaisa.assignment.Application.ja' its main Spring boot initializer class
* Application will run on port 8080
* copy the postman collection under 'com.telenor.easypaisa.test.unit'
* import to post man
* select imported environment in postman
* Now run the all CRUD operation URLs present in collection for "User", "Book", "Category"


# The covered Specifications are 

Create rest web services for Library Management System using Spring Boot 

Entities
# 1-	Users
    * 	Id
    * 	Name
    * 	CNIC
    * 	Mobile
    * 	Address
# 2-	Books
    * 	Id
    * 	Category_id
    * 	Name
    * 	Author_name
# 3-	Categories
    * 	Id
    * 	Name
# APIs to be developed
    * 	APIs for CRUD operations for all entities
    * 	Use file system as data base and give option to configure storage format via configuration file e.g. (json or yml) Hint: Use concepts of Dependency Injection
    * 	All API requests should be logged to file.
# Unit Tests
    * 	Write unit tests for CREATE functions of all entities
# Submission Guidelines
    * 	Please create a public git repository.
    * 	Maintain code commits
    * 	Share the repository link in the email for submission.
