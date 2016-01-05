# AfterFlight
## Project Proposal
Rang Salih - 10690972

AfterFlight - Find someone that wants to pick you up from the airport and make a deal! Or do you want to pick someone up?

#### Features
* Account
  * Log in
  * Forgot password
  * Register
* Settings 
  * Home city
  * Change password
* Chat
  * After post is accepted
* Post service
  * Supply 
  	* Airport location(s)
  	* Work period
  	* Time shift(s)
  	* Car size
  	* Max km to drive
  * Demand
  	* Airport location
  	* Date
  	* Time
  	* Number of persons
  	* Address of final destination
* Accept/decline service

#### Problem solved
AfterFlight is the best solution to bring you to your final destination after travelling by plane. 
You can find someone that wants to pick you up from the airport and bring you to your final destination by car for a good price.
Or vica versa, you can pick someone up from the airport near your house and bring that person to their final destination, and receive some money.
The chat function makes it possible to discuss and decide the price together, and also give each other personal information (like phone number) or your flight number.

#### Sketches
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/screens1.jpeg?raw=true "Click to zoom and turn")

![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/screens2.jpeg?raw=true "Click to zoom and turn")

#### Data sets and data sources
AfterFlight needs a database for accounts(which includes posts), this can be done with the parse(.com) API.

#### Application parts
The Application parts are defined above (see features). After that someone accepted a service (to give or get) a new chat will be opened automatically with associated person.

#### Platform & APIs
AfterFlight will be created in the Android Studio platform with java and xml, and will be combined with the parse API.

#### Potential problems & possibilities to overcome these
Implementing a chat function in the application could make it complex for me. If the chat function does not work, then I have to find another way to bring the traveler and car driver to each other. A solution could be that that simply get each others e-mail address.

#### Similar applications
Uber: AfterFlight will be planned version of Uber, that means that you make a reservation for another day, and the price will be decided by the client and customer.

