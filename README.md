# Recipes-of-India

Recipes of India is an Android Application and it was implemented as our Final Project in CECS 453 Mobile Application Development. It is designed for spicy food lovers who would like some suggestion on what can be cooked with the available ingredients in their house/fridge.

Concepts used: Toast, Intents(with Bundles), Menu Overflows, App Chooser, Alert Dialog Boxes, SQLite, Custom List Views, Shared Preferences, Internet Permission.

New concepts learnt and implemented - Expandable List Views with checkboxes, Custom Alert Dialog, Generating .APK file.

Team-mate responsibilities:
1) Aniket:

  -> MainActivity - Containing Menu Overflows(Rate this App, Change Theme, Uninstall App).
  
  -> Rate this App - Custom Alert Dialog Box with Rating Bar with max. 5 stars and multi-line feedback.
  
  -> Connection.java - The user entered no. of stars and feedback are inserted in MYSQL(phpMyAdmin) database on an online server.
  
  -> db.php - Middleware to connect Android to Online Backend Database.
  
  -> Internet Permission to access online database.
  
  -> Implementing various Alert Dialogs to check for conditions wherein user doesn't check any checkboxes, etc. i.e. to handle exceptions.
  
  -> Generating .APK file for the project.

2) Sayli:
  -> Fridge activity containing Expandable List Views with checkboxes, Result activity containing Custom List Views, App Chooser, SQLite Databases 


