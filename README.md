GitHub Actions: [![Java CI with Maven](https://github.com/shahnawazkhan988/attsk/actions/workflows/build.yml/badge.svg)](https://github.com/shahnawazkhan988/attsk/actions/workflows/build.yml) Coverall: [![Coverage Status](https://coveralls.io/repos/github/shahnawazkhan988/attsk/badge.svg?branch=main)](https://coveralls.io/github/shahnawazkhan988/attsk?branch=main) Sonarcloud: [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=alert_status)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk) [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=bugs)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk) [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=code_smells)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=coverage)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk) [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk) [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=security_rating)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk)

<h2>Student Registration-is a Rest Apis with the following technologies:</h2>
<ul>
  <li>Java 11</li>
  <li>SpringBoot</li>
  <li>MySql</li>
  <li>H2 database</li>
  <li>junit5</li>
  <li>Mockito</li>
  <li>Coveralls</li>
  <li>SonarCloud</li>
  <li>Postman</li>
  <li>Github Action</li>
  <li>GitHub</li>
  <li>Maven</li>
  </ul>
 
<h2>Introduction</h2>
<p>Test-driven development (TDD) is an established technique for sustainably delivering better software faster. TDD is based on a simple idea: write a failing test before you write production code itself. 
There are two main objective of creating this project. First objective is to use TDD technique, write the tests before the code to test and ensure it meets the functional requirement. 
In this project, I have created REST API named as “Student Registration” which based on Java 11. Further, I have created CI workflow on Github Action and integrate this with Sonar Cloud and Coveralls. Next, to test my application, I have done two types of testing: Unit testing (every single component) and Integration testing (several components together). Finally, I have used virtualized environment (Docker containers) for my application database.  
Second objective of this project is to ensure 100 percent code coverage with using above mentioned tools and techniques.</p>

<h2>Methodologies</h2>
<ul>
  <li>TDD</li>
  <li>BDD</li>
</ul>
<p>Following methodology has been used for creating my student registration application:
1.	Postman is an API client allowing to test the APIs whether it is working or not. In simple words, it makes easy to create, share, test and document APIs. This is done by allowing users to create and save simple and complex HTTP/s requests, as well as read their responses. 
2.	Next, we have controller layer which is a front end layer. I have created my API URI (get, post, put, delete) on controller layer. It handles authentication and HTTP requests.
3.	Then, we have service layer which handles all the business logics and also performs validation and authorization as it is a part of business logic.
4.	Next layer is DAO layer. It contains all the storage logic, such as database queries of the application. It also translates the business objects from and to database rows.
5.	Finally, we have database layer which consist of the database such as MySQL database in my project. All the database related operations like CRUD (Create, Read/Retrieve, Update, and Delete) are performed in this layer.</p> 

<h2>Docker</h2>
<p>Docker is an open platform for developing, shipping, and running applications. Docker enables you to separate your applications from your infrastructure so you can deliver software quickly. I have used following step to integrate Docker container to my application
1.	Get mysql image from docker hub and run command on terminal : docker pull mysql/mysql:latest 
2.	Then create a database and run command on terminal : docker container run --name attsk -p 2346:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_DATABASE=attskdb -d mysql.
3.	For verification, first I connect it from sqlWorkBench and after  the successful connection, I run my application.
</p>
With Maven build and run command: first build using mvn clean install then go target floder and run command through terminal java -jar attsk-0.0.1-SNAPSHOT.jar
