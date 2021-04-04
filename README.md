A) This project creates a server that can be used for file sharing.
Clients can connect to the server and either upload a file from a local
directory or download a file from the server directory to their local
directory.

B) Improvements

C)  Step 1: open the github repository: https://github.com/Josh-Zak/csci2020u-assignment2/tree/main/src/Client
Step 2: click on code and copy the web URL
Step 3: open cmd or Linux Terminal and type in git clone "web URL"
Step 4: open the folder in Intellij (Recommended)
Step 5: Under the project tab, open External Libraries and right click then choose "Open Library Settings"
Step 6: Add the proper SDK version(15.0.2 as newest version) and the required libraries in Global Libraries.
Step 7: Select Apply then OK.
Step 8: On the tool bar, under Run, select Edit Configuration
Step 9: Under Templates, add Application, choose the same java version as your SDK version.
Step 10: Type in the name of the client folder main class(Client) and make sure to do the same thing with server folder(Server as main class)
Step 11: Under Modify options, choose Add VM Options, copy the absolute path to the lib folder in your java version
and fill it in this line --module-path "lib path here" --add-modules javafx.controls,javafx.fxml
Step 12: Hit Apply then OK.
Your program should be able to run now.
Step 13: Under Run, choose run, then run the server class first. If the message display properly, run the client class.
Step 14: When the UI display the list of files and the buttons, type the command in the client console or use the buttons to interact.

D) References:
https://learn.ontariotechu.ca/courses/11612/pages/module-7-multithreading-videos?module_item_id=298626