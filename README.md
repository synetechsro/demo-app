Introduction application
========================
 Demo
------
- very simple ***Swift 4 / Objective-C / Kotlin / Java*** project
- based on **architecture chosen by you**
- add some **animations** (rotation, translation, ...)
- basic **API (REST)** interaction

##### iOS
- use **autolayout**
- simple interaction with ***UITableView***

##### Android
- define **layout in XML**
- use ***RecyclerView***

### Provided Resources ###
- **Design**
	* design of the application
- **Images**
	* image resources for the application
	
### Tasks ###
1. Design the first screen according to the *Android_list.png* or *iOS_list.png* image (according to selected platform).
	- layout screen according to the image
	- design custom TableViewCell/ListItem that will contain transaction image, description, type, amount and arrow
	- download data for the "SYNETECH s.r.o." from REST API -> [https://stub.bbeight.synetech.cz/v1/customers](https://stub.bbeight.synetech.cz/v1/customers)
	- convert transactions to some more useful custom model that you can use to fill in custom items in list
	- (iOS only) add custom table view cells to the table view
    - (Android only) add custom list items to the recycler view

1. Design the transaction detail screen according to the other image.
	- layout screen according to the image
	- change the image depending on the type of the transaction
	- create a *like/favourite* button according to the layout

1. Make it so that when user taps transaction in the table view it shows him its details
	- use proper transition

1. **[OPTIONAL]** Add animations to the application (choose one from options below)
	- change the transition from transaction list to transaction detail to custom-animated transition (eg. make it last 2 times that long)
	- add animation to the cell so that when you tap it it slides under the left/right edge of the screen and after that do the transition

1. **[OPTIONAL]** Add notifications to the application
	- register for custom notification
	- liking sends notification that shows pop up with text "You narcistic piece of shit."