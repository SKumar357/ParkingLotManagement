# ParkingLotManagement
## Project Description
This is a simple parking-lot management software built in Java. This uses docker so that it is platform independent and can be deployed in environment. 
For In-memory storage, we are using Redis. It is running in port 6379 in a docker container. 
In this implementation we have two instance of the Tomcat webserver running in ports 9000 and 9001 in their respective containers.
Nginx is used a reverse proxy and load balance for the web servers.
Load balancer is configured in round robin method between the two servers.

## Technologies used:
1. Java 11
2. JSP - Servlet
3. Tomcat 9.
4. Docker
5. Nginx
6. Redis


## Requirements:
1. Docker should be installed.
Link to install docker: https://www.docker.com/products/docker-desktop

## Steps to run the project:
1. Run the docker composer file using the command `docker-compose up`
2. Visit `http://localhost:80` in your browser.

Please note that the ports 80, 9000,9001 and 6379 should be available for the project to run. No other services should be running in these ports as we will be using these ports for this project.
