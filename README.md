# Recipes-of-India

Recipes of India is an Android Application and it was implemented as our Final Project in CECS 453 Mobile Application Development. It is designed for spicy food lovers who would like some suggestion on what can be cooked with the available ingredients in their house/fridge.

Concepts used: Toast, Intents(with Bundles), Menu Overflows, App Chooser, Alert Dialog Boxes, SQLite, Custom List Views, Shared Preferences, Internet Permission.

New concepts learnt and implemented - Expandable List Views with checkboxes, Custom Alert Dialog, Generating .APK file.

Team-mate responsibilities:


1) Aniket:

  -> MainActivity - Containing Menu Overflows(Rate this App, Change Theme, Uninstall App).
     Time required - Approx. 3 hours
  
  -> Rate this App - Custom Alert Dialog Box with Rating Bar with max. 5 stars and multi-line feedback.
     Time required - 1/2 an hour
  
  -> Connection.java - The user entered no. of stars and feedback are inserted in MYSQL(phpMyAdmin) database on an online server.
     Time required - 4 hours
     Debugging consumed some time for the database connection.
  
  -> db.php - Middleware to connect Android to Online Backend Database.
     Time required - 1/2 an hour
  
  -> Implementing various Alert Dialogs to check for conditions wherein user doesn't check any checkboxes, etc. i.e. to handle exceptions, also for specifing that we have received their feedback and some more.
     Time required - 2 hours
  
  -> Generating .APK file for the project.
     Time required - 15 mins.
  

2) Sayli:

  -> Fridge activity - Containing Expandable List Views with checkboxes. Even on collapsing the group names, the checked child checkboxes retains. Also, implementing the logic behind retriveing the recipes based on whether they fulfil 50% minimum available ingredients.
     Time required for Expandable Listview - 2 hours
     Time required to dynamically generating checkboxes so that it remembers the selected items even on group collapse- 3 hours
  
  -> Result activity - Containing Custom List Views with an image thumbnail and text.
     Time required - 1 hour
  
  -> App Chooser - On clicking the above List View items, a Web Browser App chooser will pop up and open the link associated with the recipe from the database.
     Time required - 1/2 an hour
  
  -> SQLite Database(Database Handler.java) - It stores 12 recipes(id, name, link to webpage containing recipe, link to Drawable image).
     Time required - 4 hours
     Spent a lot of time changing the structure of database and trying to execute complex queries with UNIONs and INTERSECT. Same query used to execute in Oracle but the SQLite Cursor returned null.
  
  -> Shared Preferences - To toggle the themes of the App. On restarting the application, the themes is according to the last saved preference.
     Time required - 1/2 an hour.

Both of us contributed equally to the creation of Presentation, Readme files.
