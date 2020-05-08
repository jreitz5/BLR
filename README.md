# brown-landlord-review

Our project uses the maven architecture to run and compile our code. It is
required to be downloaded and installed to be used in the CLI.

to compile:
mvn install
mvn package

to run:
./run --gui

view at http://localhost:4567/login

login information (if you don't want to create a profile):
email: william_auten@brown.edu
pass: blrrox

Known bugs:
Sometimes when you try to log in, it does not redirect to the home page, meaning
you just have to log on a second time.

Unfinished features (RIP coding together in CIT):
-User levels: admin and standard
-Admin dashboard: approve and deny posts, and add new landlords / properties to
the database
-Google OAuth signin - we are not hosted, so we could not integrate google OAuth
until it is.

Technologies used:
-Maven (backend compilation and testing)
-Bootstrap (dropdown menus)
-SQL (data storage and retrieval)
-Apache Spark (for serving pages and hosting locally - includes many key
  security features such as xXss protection headers)
-JQuery (for reading values from HTML into JS)


This project was a lot of fun and we are all really proud at how far we came,
especially considering the current situation. I hope you enjoy our app!
