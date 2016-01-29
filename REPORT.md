# AfterFlight
### Report
Rang Salih - 10690972

#### Description
AfterFlight is the best solution to bring you to your final destination after travelling by plane. 
You can create a post and someone, that wants to pick you up from the airport and bring you to your final destination by car for a good price, will see your post.
Or vica versa, you can pick someone up from the airport near your house and bring that person to their final destination, and receive some money.
You can see in the whole market in the Demand page, and all the information that you need is available in the post to make contact.

#### Features
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

#### Design
![alt text](https://github.com/Rang92/AfterFlight/blob/master/doc/AfterFlightClasses.png?raw=true "Click to zoom")

#### Classes
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

#### Screenshots
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






