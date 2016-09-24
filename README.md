# _Hair Salon_

#### _Hair Salon Database Project, September 23rd, 2016_

#### By _**Andrew Fisher**_

## Description

_This App will keep track of Hair Salon Employees and their respective client._

## Specifications

_1. It can ask the user for a employee name._
* _Input Example: Sean_
* _Output Example: Sean_

_2. It can then ask the user to add a client._
* _Input Example: Andrew_
* _Output Example: Andrew_

_3. It can can then associate a stylist with a client._
* _Output Example: Sean is Andrew's stylist_

_4. It can update the stylist information._
* _Input Example: Sean -> Shaun_
* _Output Example: Shaun_

_5. It can update the clients information._
* _Input Example: Andrew -> Drew_
* _Output Example: Drew_

_6. It can take delete the stylist._
* _Output Example: You have fired Shaun._

_7. It can delete the client_
* _Output Example: Drew is no longer a client_

_8. It can list all of a stylist clients_
* _Input Example: Sean_
* _Output Example: [Andrew,Jenna,Nina]_

## Setup/Installation Requirements

* Requires Java, Gradle, POSTGRES and SQL
* Clone to your computer
* In PSQL:
* CREATE DATABASE hair\_salon
* CREATE DATABASE hair\_salon\_test
* Both will include the tables
* CREATE TABLE stylists (id serial PRIMARY KEY, name varchar);
* CREATE TABLE clients (id serial PRIMARY KEY, name varchar, stylistId int);

## Known Bugs

_No known bugs_


## Contact
* _Andrewfishersb@gmail.com_

## Technologies Used

* _Java_
* _Gradle_
* _SQL_
* _HTML/CSS/Bootstrap_


### License

*This software is licensed under the MIT license*

Copyright (c) 2016 **_Andrew Fisher_**
