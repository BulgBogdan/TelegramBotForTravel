# TelegramBotForTravel

The application allows you to use a bot that tells about the inputted city.

### Start Bot

1.	Open tab Database – add Data Source(MySQL) – input (name – “any name”; user – “root”; password – “root”) – ok;
2.	Run script (path: \src\main\java\botTravel\util\bot_travel_script.sql) – choose created Database;
3.	File – Project Structure – Modules – add Hibernate;
4.	Edit data with Postman: http://localhost:8080/city/, but it’s better to use Web pages for edit: http://localhost:8080/jsp/cities/.
5.	All information about Bot in path: \src\main\resources\telegram.properties.

### Stack

* Java 8
* Maven
* Spring Boot
* Spring Data
* Spring MVC
* Hibernate
* MySQL
* Lombok
* JSP
