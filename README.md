[![Build Status](https://travis-ci.org/DaniilsLoputevs/job4j_parser_sql.ru.svg?branch=master)](https://travis-ci.org/DaniilsLoputevs/job4j_parser_sql.ru)
[![codecov](https://codecov.io/gh/DaniilsLoputevs/Parser_SQL.ru/branch/master/graph/badge.svg)](https://codecov.io/gh/DaniilsLoputevs/Parser_SQL.ru)

# Parser_SQL.ru

### About the project
Parser of vacancies for the site https://www.sql.ru/forum/job. 
It is used to search for topics with vacancies, using the word Java in the name, parsing and found topics in the Postgres database.

### Features:
* updating data on a schedule;
* checking and deleting from the database, vacancies deleted on the site;
* logging of performed actions: the number of deletions, additions and updates;
* if the record is already in the database, it will be forced to update the number of views, the number of messages and the date of the last message;

##### Settings:
* JDBC connection detail
* Run intervals(in minutes)
* parsing address
  data for connecting to the database are set in the configuration file - app.properties.

### Technologies
* Java Core
* quartz-scheduler
* jsoup
* log4j/slf4j
* jdbc (Postgres)
* Maven
