# Fruitpedia 
Web development project in the course DAT076. A webpage that allows registered users to create lists of fruits. 
Only the users imagination sets the limit. 

## File Structure 
This project consists of the following main parts: 
* A database 
* Xhtml pages 
* CSS files 
* Backend logic written in Java 


# File Structure 
Below we intend to explain the structure of our project. 

## Backend logic 

### Model 
This package is the layer that communicates with our database, it sets and gets information from there. 

### View 
This section is responsible for sending and receving information from the view. 
The accounts classes handels logic for aspects such as updating a users password or ensuring that there is a user logged in. 

A core function is also that the user can submit new fruits to the database, and add fruits to their own personal list,
this dataflow is also controlled by the View-packags. The central classes are FruitsController and AccountsController. 

### Utils
Theese files are used for navigation logic with our lists and to enable diffrent kind of messages to the diffrent views. 

## Other 

### Rescorces
This folder cointains all graphical aspects such as CSS-files, video and images that are used by the webpage. 

## Template 
A template page is used for the top-navigaton banner, the banner is a template to achive code-reusage. 

# Authors

* **Linnéa Bark**
* **Kevin Brunström** 
* **Matilda Sjöblom** 

