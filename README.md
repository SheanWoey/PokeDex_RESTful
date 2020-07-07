# PokeDex_RESTful
create a pokedex app with RESTful api - CA education use

This Pokedex Mobile Application has a wealth of information on all the Pokemon creatures from the entire game series. After login to the application, you can see different type of Pokemon on the main list pages. Click on a Pokemon to see a detailed page with Pokedex data, descriptions, skills and more!

Functionality Implemented 
•	Login and Register system with Sharedpreferences. 
•	Get data from XML/JSON web service using AsyncTask for threading
•	Parse Json feed, name, sprite, types, description else and store in PokeDetail object
•	Display ArrayList object in recycleView with adapter
•	Item is clickable and lead to full detail of specific pokemon.
•	In detail page, the image could be download and store into phone storage by click button with onclicklistener on resume. The image will be parse into Bitmap firstly. The detail is display on fragments and navigate by bottom navigation.
•	Image Downloaded notification will show after image is downloaded with NotificationCompat.Builder.
•	A floating button lead to Pokemon Team Creator. Select four favourite pokemon and they will store in Sharedpreferences. Display the team with Sharedpreferences when modify or created.
•	Item in Team Creator could be click and hold. Click to choose pokemon in recycleView and return pokemon id then update team. Hold to look the detail of pokemon. 
