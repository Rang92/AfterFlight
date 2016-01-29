# AfterFlight
### Report
Rang Salih - 10690972

### Description
AfterFlight is the best solution to bring you to your final destination after travelling by plane. 
You can create a post and someone, that wants to pick you up from the airport and bring you to your final destination by car for a good price, will see your post.
Or vica versa, you can pick someone up from the airport near your house and bring that person to their final destination, and receive some money.
You can see in the whole market in the Demand page, and all the information that you need is available in the post to make contact.

### Features
* Account
  * Log in
  * Register
* Post service
  * Demand
  	* Airport location
  	* Date
  	* Time
  	* Number of persons
  	* Address of final destination
	* Flight number
	* Phone number or Email address
* Copy phone number or Email address to clipboard
* My Posts
 * Possible to delete own post
* Settings 
  * Change profile picture

### Design
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/AfterFlightClasses.png?raw=true "Click to zoom")

### Classes
* SplashScreenActivity: Creates the startup screen. 
* MainMenu: Creates the navigation drawer and navigation to fragments.
 * MainFragment:
 * AirportPostFragment: Creates listview to choose airport.
 * FinishPostFragment: Creates post object with post information.
 * DateDialogFragment: Creates date picker dialog.
 * TimeDialogFragment: Creates time picker dialog.
 * DemandFragment: Shows listview with posts of all users, connects with parse.
 * MyPostsFragment: Shows listview with posts of current user.
 * SelectedPostFragment: Shows all post information.
 * SettingsFragment: User can change profile picture here.
* ParseApp: Connects with parse.
* Post: Creates post object.
* LoginActivity: The user can login or register. The parse database will check if account exists.
* RegisterActivity: The user can register here, account will be sended to parse database.
* ListViewAdapter: Creates custom listview.

### Screenshots
### SplashScreenActivity
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/SplashScreenActivity.png?raw=true "Click to zoom")

### LoginActivity
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/LoginActivity.png?raw=true "Click to zoom")

### RegisterActivity
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/RegisterActivity.png?raw=true "Click to zoom")

### MainFragment
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/MainFragment.png?raw=true "Click to zoom")

### NavigationDrawer
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/NavigationDrawer.png?raw=true "Click to zoom")

### AirportPostFragment
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/AirportPostFragment.png?raw=true "Click to zoom")

### FinishPostFragment
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/FinishPostFragmentNew.png?raw=true "Click to zoom")

### DatePickerDialog (dialog in FinishPostFragment)
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/DatePickerDialog.png?raw=true "Click to zoom")

### TimePickerDialog (dialog in FinishPostFragment)
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/TimePickerDialog.png?raw=true "Click to zoom")

### DemandFragment
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/DemandFragment.png?raw=true "Click to zoom")

### SelectedPostFragment
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/SelectedPostFragment.png?raw=true "Click to zoom")

### MyPostsFragment
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/MyPostsFragment.png?raw=true "Click to zoom")

### SettingsFragment
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/SettingsFragment.png?raw=true "Click to zoom")

### ProfilePicture(dialog in SelectedPostFragment)
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/ProfilePicture.png?raw=true "Click to zoom")


### Challenges and solutions
#### Week 1
The first day I came up with the idea to create AfterFlight, it sounded like a great idea and it is.
It was the first time for me to use the parse database.
The first thing that I knew about my layout was that I wanted to create a navigation drawer.
In my opinion it is the best way to manage applications accounts, using a navigation drawer with fragments.

* My first problem was sending and receiving data from fragment to fragment. 
I lost a lot of time with sending an object to another fragment, but it was worth it.

* A second problem was creating a search actionbar in a fragment, but that was not possible; based on the information on the internet.
My solution was creating an edittext to search in a listview. The edittext can be used as a search bar.

* The third problem was that I first created a normal listview, but it was better to create a custom cardview to implement in a listview.
A cardview looks better in my opinion and is easier to click on. Adding an image in a item was much easier with a cardview instead of a normal listview.

* Last but not least problem was creating push notification for the accounts to communicate.
I've read a lot about push notification in parse, but I concluded that I did not have enough time for that.
The solution is that the user can add his/her email address or phone number. The email address or phone number can be copied to clipboard with an imagebutton to use in other applications.

Finally, in my opinion I created a good looking app which also works well, and I am happy with my result.














