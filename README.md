GitHub Actions: [![Java CI with Maven](https://github.com/shahnawazkhan988/attsk/actions/workflows/build.yml/badge.svg)](https://github.com/shahnawazkhan988/attsk/actions/workflows/build.yml) Coverall: [![Coverage Status](https://coveralls.io/repos/github/shahnawazkhan988/attsk/badge.svg?branch=main)](https://coveralls.io/github/shahnawazkhan988/attsk?branch=main) Sonarcloud: [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=alert_status)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk) [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=bugs)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk) [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=code_smells)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=coverage)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk) [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk) [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=shahnawazkhan988_attsk&metric=security_rating)](https://sonarcloud.io/dashboard?id=shahnawazkhan988_attsk)

<h2>User Registration-is a Rest Apis with the following technologies:</h2>
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
<h2>Methodologies</h2>
<ul>
  <li>TDD</li>
</ul>
<p>if you want to use your MySQL server on docker then use the following instructions: 1: get mysql image from docker hub using :docker pull mysql/mysql:latest 2: connect to it from any of your sqlWorkBench using: docker container run --name attsk -p 2346:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_DATABASE=attskdb -d mysql.</p>

With Maven build and run command: first build using mvn clean install then go target floder and run command through terminal java -jar attsk-0.0.1-SNAPSHOT.jar
