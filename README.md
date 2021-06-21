Coverage: 62.5% TBD
# Hobby-Project

This project aimed to create a list-type of application-based of our hobbies. The application would be able to CREATE / UPDATE / DELETE certain records stored in our database, using SQL and Eclipse as our database and IDE. The Hobby-Project is designed to store details on "Teams" and "Characters", store them into the database whilst also being able to be modified by CRUD functionality. Each table will have different sets of data but will each also have a unique primary key. The tables will then be linked use Foreign Keys so that they have a relationship.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system. 

### Prerequisites

What things you need to install the software and how to install them

To be able to build the Hobby-Project you would need:
1) An IDE - In this case a Java IDE is necessary, so we have gone with Eclipse IDE. The latest version is recommended being Eclipse 2021.
2) A build tool - In this case, we have gone with Maven which helps our project build, dependencies, and documentation.
3) A Framework – With building a standalone application we would need a framework. We are specifically using Spring Framework in this case which can be downloaded from the IDE’s marketplace. 
3) A Database Management System - This will allow us to store data and records for our IMS. SQL workbench is recommended, however, GCP is also a viable option.
4) Git - We will be using Git to commit our changes in our local machine back up to GitHub where our repository is and create feature branches to push up into as well.
5) A Web Browser - As we are building a Spring Boot Application, we need to use a browser to run our front-end, we recommend using `Google Chrome` as it has built-in dependencies compared to other web browsers.
6) Testing - To test our Hobby-Project we will need to incorporate a testing framework into our program via dependencies. We shall be using JUNIT and Mockito to test our code.

### Installing

A step by step series of examples that tell you how to get a development environment running

First step is to install all of our Pre-requisites

First step is to install the majority of our pre-requisites. Once they have been installed, we can start running. 

```
1)	Clone the repository down to your local machine by opening up Git on your desktop. By using the command `git clone` as shown below;

> `git clone` https://github.com/KevinD-QA/Hobby-Project.git

2)	Next open the Eclipse IDE, click the file then open projects.
3)	Find your cloned repo and open the project onto Eclipse.
4)	Once the project opens successfully, the next is to set up the database.
5)	Open up SQL workbench and create a new connection.
6)	Naming the connection appropriately, create a username and password as well as keep track of the port ID (we will be needing these later).
7)	Going back to Eclipse, within the application.properties file underneath src/main./resources. Enter the port address as well as the username and password you created.

Now you are set up to run the environment. When first running the project, you will have to navigate to a web browser, we recommend you use Google Chrome as stated in the pre-requisites. Once Google Chrome has been opened, in the search bar enter `localhost:8080/homepage.html` this will take you to the main page of the application. On the main page you will be greeted with 


```

## Running the tests

This project has been Unit tested by using Junit and Mockito. To ensure best practices and less "smelly code" we have also incorporated SonarQube as well.


### Unit Tests 

The tests run to test a variety of different methods and functions. To ensure single responsibility, tests were created so that each test class corresponded with a class of its own. 
>E.G HeroControllerTest with HeroController.

These tests can be run in Eclipse by navigating to the `src/test/java` folder in the project and selecting `Coverage As` and running `JUnit Test`. This will run tests for the whole project which will then give an overall coverage, meaning how much of the code in the project has been run and tested.

If we would like to run more isolated tests. We can navigate into the `src/test/java` folder and select any of the tests inside and repeat the same process by right-clicking into the class and running JUnit tests from there.

As a prerequisite, I used Mocikto to “mock” certain items. To use Mockito, a dependency will have to be added shown below;

```
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-core</artifactId>
			<version>3.7.7</version>
			<scope>test</scope>
		</dependency>
```
Using Mockito is very helpful in helping us further test methods by calling parameters throughout the test.

## Deployment

To deploy on a live server, we can use GCP to connect our database onto a cloud server which can be run continuously and not have to be saved locally. Also, we can create a jar file. By aggregating all our files we can then distribute them and have other users run the IMS system on their machines.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Kevin Doan* - *Initial work* - [KevinDoan] (https://github.com/KevinD-QA)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

[![Quality Gate Status](http://localhost:9000/api/project_badges/measure?project=sonar.hobby-project&metric=alert_status)](http://localhost:9000/dashboard?id=sonar.hobby-project)

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc
