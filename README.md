# FlightDetailsService
Service for retrieving the flight details. This service will take the user input and will return back the details of flight accordingly. There are six required parameter ORIGIN - DUB , DESTINATION- EDI ,DEPARTUREDATE -> 25-20-2021, ARRIVAL DATE 25-20-2021, NOOFPASSENGER - 1

Use Swagger or Postman to make REST calls e.g. http://localhost:8080/'name_of_the_specific_endpoint'

HOW TO USE

1.) WITH DOCKER : The image is available on Docker Hub. Pull Command - ( docker pull bukane/flight-details-service:latest ) 

2.) WITH GIT : https://github.com/NileshBu/FlightDetailsService.git

BASIC GUIDE TO THE SERVICE 

LOCALLY : RUN THE SPRING BOOT APPLICATION in LOCAL

1.) Use the Endpoint (Get All Flight Details as per User Entry) (ENDPOINT PATTERN --> /api/getFlights

Sample Endpoint : http://localhost:8080/api/getFlights/DUB/EDI/25-20-2021/25-20-2021/1

2.) LOCALLY USING DOCKER :
3.
docker run -p 8080:8080 bukane/flight-details-service




