Flight Booking Microservices – Final Project

This project demonstrates an end-to-end microservices architecture for a Flight Booking System built using Spring Boot.
It includes multiple independently deployable services that communicate through REST, Kafka messaging, and API Gateway routing.

 What This System Can Do?

✔ Search available flights
✔ Book flight tickets (one-way/round trip)
✔ View ticket details using PNR
✔ Cancel tickets
✔ Inter-service communication using OpenFeign
✔ Kafka event publishing on ticket booking
✔ Gateway routing & centralized configuration
✔ Service discovery via Eureka

 Microservices Included
 
Service	                         Purpose
Flight Service            	Manages flights & inventory
Booking Service          	Handles bookings, ticket generation & cancellation
Config Server             	Centralized configuration management
API Gateway	                 Single entry point; request routing & filters
Eureka Server	              Service discovery & health registration
Circuit Breaker             	Resilience & fallback handling (Hystrix/Resilience4J)
Kafka Broker	              Sends ticket-booked event messages



 Architecture Diagram:
 
Client → API Gateway → [Flight Service] ←→ [Booking Service]
                      ↘                ↘
                       ↘                 ↘
                      Eureka Registry     Kafka Broker
                            ↑                  ↓
                      Config Server -----> Circuit Breaker

Tech Stack:

Category	             Technology
Language	               Java 17
Framework	               Spring Boot 3.x
Inter-service calls   	OpenFeign
Service Registry	       Eureka
API Gateway	         Spring Cloud Gateway
Config Management	    Spring Cloud Config
Messaging	             Apache Kafka
Resilience           	Circuit Breaker
Testing	              JMeter, Postman/Newman
Code Quality	          SonarCloud
Coverage	              Jacoco
Build Tool	              Maven
Containerization	      Docker / Docker Compose


 Testing & Quality Tools:

Tool                                 	Usage
Postman + Newman	               Automated API testing via command line
JMeter	                           Performance & load testing
Jacoco	                             Unit test coverage report
SonarCloud	                        Code quality analysis


 Running with Docker:

    Kafka & Zookeeper included using docker-compose:

              docker-compose up -d


Verify:

         docker ps

 Running Microservices:
Step	                      Service
1	                        Config Server
2	                     Eureka Service Registry
3                      	Flight Service
4                    	Booking Service
5	                       API Gateway
6                	Circuit Breaker
7	               Kafka event consumers (optional)

Run each service:

mvn spring-boot:run

 API Endpoints (Examples):

Service                  	HTTP Method	Endpoint
Flight                  	POST	/flight/search
Flight	                  POST	/flight/airline/inventory/add
Booking	                  POST	/booking/book/{flightId}
Booking                  	DELETE	/booking/delete/{pnr}
Booking               	GET	/booking/getByPnr/{pnr}
Gateway	                    GET	All requests via gateway

 Kafka Events:
 
Topic	Publisher	Consumer
ticket-booked	Booking Service	Any Email/Notification Service (future scope)

SonarCloud + Coverage"

Tool	                        Status
SonarCloud	           Active (syed562_FlightBookingMicroservices)
Jacoco	                     Used for test coverage
Exclusions	            Applied for non-testable modules (gateway, config, eureka, circuit breaker)


DATABASE DESIGN 

<img width="1459" height="946" alt="image" src="https://github.com/user-attachments/assets/1b203bc2-7a07-4fc2-a57c-8b145eac3cf2" />
<img width="1289" height="812" alt="image" src="https://github.com/user-attachments/assets/ee83caa6-4f62-40d8-8105-df26d2938858" />



Table / Collection	Fields	Relations
Flights	to be added	
Bookings	to be added	
Passengers	to be added	


 Author

Syed Sabiha
Microservices | Spring Boot | DevOps | Cloud | Distributed Systems
