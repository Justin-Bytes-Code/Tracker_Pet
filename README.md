# Tracker Pet
**Intro**

Tracker Pet is a user friendly android app designed to be the bridge between pencil & paper to the digital world of note taking for animals. It allows owners to track their pets health and basic information while supporting multi-animal data support. It's also designed with breeders in mind as well as you can enter every species without the need of a predetermined list. This allows for Tracker Pet to meet all your digital animal notebook needs. All of this at the convenience of your phone with a fully offline database allowing you to always monitor your pets everywhere & anywhere. 

---

**Preview Videos**

<!--Login Video-->
https://github.com/user-attachments/assets/eab388e2-320d-401d-b2c5-c018b0b83f46

<!--Input Animal-->
https://github.com/user-attachments/assets/e29d457e-b87c-4670-b7f7-a297783c5b66

<!--Edit & Delete-->
https://github.com/user-attachments/assets/05b349ff-9c5c-4d51-a66d-bbd1fed50eb4

<!--SMS Enabled-->
https://github.com/user-attachments/assets/44e5b2c1-d9fe-45c5-9f4f-5e25df050d45

<!--SMS Example-->
https://github.com/user-attachments/assets/13bbdb3f-2ff9-4f1f-bfd3-15876aea1a29


---

**Features**

- A Functional Login & Sign Up
- SQLite Tables Database
- Tracking Animal Statistics  (Names, Specices, Birthday, Weights, Goal Weights) 
- Multiple Animal Support (Allowing You To Store All Your Animal Information In One Place)
- Offline & Local Database
- Editing & Update Animal Stats
- Delete Animal Stats
- Simple & User Friendly
- Notfications And Reminders
- Android Dark Mode Support 

---

**It Includes:**

- Full Android Studio Project With Source Code
- Pre-Configured SQLite Database
- APK for Quick Installation
- Complete Login & User Functionality 

---

**How To Install**

1. Download APK. 
2. Navigate to "My Files" 
3. Select Installation Files 
4. Select Tracker Pet APK 
5. (Optional) Follow on screen prompts if phone doesn't allow 3rd party downloads

--- 

**How To Use In Android Studio**

1. Download or Clone Repo.
2. Open the project in Android Studio 
3. Build & run app the app on Emulator or android device

--- 

**How It Can Be Exapanded**

- Add cloud Sync to google drive
- User Specific Animal List
- Add Optinal Data you can input for a Animal 
- Photo Uploads for animals
- Android Widgets

---

**Frequently Asked Questions**

Q: Can I restore my data if I uninstall the app? 
A: As of currently no. This is due to the data being stored Locally on your device. 

Q: Do I need Internet to use the app?
A: No! Everything is completely offline and works locally. 

Q: Why does the notfication not work on phone but works on emulator? 
A: This is due to a time constraint. This was done for a school project where I was left with the hard choice of fixing this feature for everyone to to put it in a realeased state. I choose to have the feature being in a debug test build only for right now. In the future I could add a prompt for the user to put in their information which allows them to recieve SMS notifcations on a timer of 24 hours to get alerts to check back in. 

Q: Does it work in other orientations? 
A: Yes! It does! Everything is designed in a way so it works on any device with any sized screen. 

---

**Rubric**


Listen
CS 360 Project Three Guidelines and Rubric
Competency
In this project, you will demonstrate your mastery of the following competency:

Develop and launch a fully functional mobile application
Scenario
Your app UI design has been received positively by the client, which means you are now ready to proceed to the final stages of the development process. The client is eager to see the completed product ready for launch. To get the app ready, you know that you will spend most of your time creating functional, error-free code that will run the app.

However, you also know there are more steps that you must complete after the coding for the app to be made widely available. To support your client with this part of the process, you must recommend a launch plan that details how to reach the client’s intended users.

Directions
Begin by reviewing any feedback you received on your previous Project One and Project Two work. Next, open the Android Studio project where you developed the UI for your app in Project Two. Before you begin coding, be sure you have incorporated the necessary changes to your UI design. This step will enable your success moving forward. You will use the Android Studio Editor Window to view and write your Java code.

App Code Design

Develop code that allows a user to log in. 
As you develop your code, use the Android Emulator feature of Android Studio to test your code. Your app should work for users who don’t have accounts. Focus on the following functionality:
The app should check the username and password against the database when the user attempts to log in.
If the user has never logged into the application before, they should be able to create a new login and password. The application needs to save these to a table in the database.
Develop code to create a database shell and display that database’s information on a user’s screen using a grid. 
The SQLite database you create will depend on the option you selected in Project One. The database will store either inventory items, event details, or daily weight. Any information your application needs to function correctly should be held in the database. But remember, you are only creating the shell for the database so a user can populate its contents later. 
Note that you will need tables in the database to store user information when it is not being displayed in the grid. This database will be persistent so no user information is lost when the app is closed. Be sure to test your code often with the Android Emulator. 
Your completed database should allow a user to accomplish the following actions:
Create: The user should be able to add items to a database.
Delete: The user should be able to remove items from a database.
Update: The user should be able to change the value associated with individual database items (e.g., the number of a specific item in an inventory or the date of an event).
Read: The user should be able to view all the database items displayed as a grid.
Develop code that prompts users for permission to send SMS messaging notifications and makes the app function based on the individual user's response. 
Keep in mind that your application’s user might deny access to the permissions needed for sending text messages via SMS. If they do, the rest of your application should continue to function without this feature. Use the Android Emulator to test both possible user response options. 
When you do, you will look for the following outcomes:
If the user grants permissions, the application should send alerts to the user as SMS messages. The alerts correspond to the specific notification trigger of the application you chose (e.g., low inventory, an upcoming event, or reaching a goal weight).
If the user denies permission, the rest of the application should continue to function without the SMS messaging notification feature.
Employ industry-standard best practices such as in-line comments and appropriate naming conventions to enhance readability of the code. 
This should be evident throughout all the code you create for this project and will be assessed comprehensively. As you code, ask yourself the following questions:
Have I kept my classes concise?
Is my style consistent throughout the code?
Would my naming conventions make sense to anyone else who looked at my code?
Do my in-line comments contain enough useful information?
App Launch Plan

Determine a plan for the launch of the application that outlines all necessary components to support the app. 
Once you have completed the code for your app, a few more steps are required to bring your app to market. In this project, you don’t have to launch your app in the app store, but you must outline a comprehensive launch plan to prepare you to bring your app to market. Later in this course, you will have the opportunity to officially launch your app, if you choose. Your app is an excellent accomplishment to share as you progress along your professional pathway.
As you write your plan, address the following questions:
What will be included in your app’s description, and what kind of icon will best represent your app once it is made available in the app store?
Which versions of Android will your app run on? Have you included the most current version? Note that each version of Android introduces new components that add consideration and challenges in the development.
What permissions will your app ask for? Be sure to only request permissions that are necessary for your app to run. For example, does your manifest ask for permission to record phone audio when your app does not use it?
What is your plan to make money from the app? Consider whether your app will include ads and require a one-time payment or neither. 
What to Submit
To complete this project, you must submit the following items:

App Code Design
Submit a ZIP file of the completed Android Studio project folder for your fully functional, error-free application. Your professor will grade your work in the ZIP file, but you may also compile and submit an APK file. If there are any issues with the code, Android Studio will not generate the APK file. Therefore, you can use the APK file to check your work and demonstrate overall functionality to your instructor.

App Launch Plan
Submit your completed launch plan as a Microsoft Word document of 2-3 pages in length. Use double spacing, 12-point Times New Roman font, and one-inch margins. The file name should include your name and the app option you have been using for the projects.

Project Three Rubric
Criteria	Exceeds Expectations (100%)	Meets Expectations (85%)	Partially Meets Expectations (55%)	Does Not Meet Expectations (0%)	Value
Log In	Exceeds expectations in an exceptionally clear, insightful, sophisticated, or creative manner	Develops code that allows a user to log in	Shows progress toward meeting expectations, but with errors or omissions; areas for improvement may include allowing a new user to create a login and password, then saving that information to a database	Does not attempt criterion	20
Database	Exceeds expectations in an exceptionally clear, insightful, sophisticated, or creative manner	Develops code to create a database shell and display that database’s information on a user’s screen using a grid	Shows progress toward meeting expectations, but with errors or omissions; areas for improvement may include ensuring that the user can add or remove items from the database or change the key values associated with database items	Does not attempt criterion	20
SMS Notifications	Exceeds expectations in an exceptionally clear, insightful, sophisticated, or creative manner	Develops code that prompts users for permission to send SMS messaging notifications and makes the app function based on the individual user’s response	Shows progress toward meeting expectations, but with errors or omissions; areas for improvement may include ensuring the app continues to function if the user does not grant permission and the app also does not give a user notification if they have not granted permission	Does not attempt criterion	20
Coding Best Practices	Exceeds expectations in an exceptionally clear, insightful, sophisticated, or creative manner	Employs industry-standard best practices such as in-line comments and appropriate naming conventions to enhance readability of the code	Shows progress toward meeting expectations, but with errors or omissions; areas for improvement may include using a consistent style, keeping classes small, or using appropriate naming conventions	Does not attempt criterion	15
Launch	Exceeds expectations in an exceptionally clear, insightful, sophisticated, or creative manner	Determines a plan for the launch of the application that outlines all necessary components to support the app	Shows progress toward meeting expectations, but with errors or omissions; areas for improvement may include identifying which version(s) of Android are best for running the app, identifying business and marketing considerations of a successful launch such as app monetization, or identifying which app permissions are needed to the application to run	Does not attempt criterion	20
Clear Communication	Exceeds expectations with an intentional use of language that promotes a thorough understanding	Consistently and effectively communicates in an organized way to a specific audience	Shows progress toward meeting expectations, but communication is inconsistent or ineffective in a way that negatively impacts understanding	Shows no evidence of consistent, effective, or organized communication	5
Total:	100%
Listen
Dictionary
Translate


---

**Questions**

(You don't have to read this. This is only for a homework assignment with this repository) 

*Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?*

- The original requirement for this project was to create a simple weight tracking app. However, I wanted to make something more unique, and with my professor’s permission, I developed an expanded version that tracks pet health instead. Tracker Pet still fulfills the core requirement of tracking weight and using a database, but it goes further by offering additional features, a more advanced design, and a clean, user-friendly interface. The app was designed to meet the needs of pet owners who want an easy and reliable way to track their animals’ information and progress.

*What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?*

- I made the app with a novice tech user in mind. If my application can't be navigated by a 10 year old kid it needs to be simplified. I also ensured every feature was large by nature to ensure no accidental missclicks of the users. I also provided basic input scrubbing to avoid my user crashing the application.
  
*How did you approach the process of coding your app? What techniques or strategies did you use? How could those techniques or strategies be applied in the future?*

- The first thing I did when coding my app was to design the features and make a list of features it needed and features I wanted. This helps avoid feature creep in the future as well as avoiding the feeling of being "stuck".
  
*How did you test to ensure your code was functional? Why is this process important, and what did it reveal?*

- I was able to test my code using a android emulator included with android studio. This process was the most imporant to me as I wanted to ensure a good user experience. I also tested out every orientation incase a user prefers typing with their phone held to their side. Before pushing a release I made sure to test it on real hardware. to ensure it wasn't just working on the emulator.
  
*Consider the full app design and development process from initial planning to finalization. Where did you have to innovate to overcome a challenge?*

- A place where I really inovated was designing the editing animal. I was able to reuse a lot of the same code from my animal creation which allowed for a more rapid development.
  
*In what specific component of your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?*

- I believe the highlight of my app is the recycler view. This is something that wasn't required at all on the assignment but I knew would make for the best user experience with intuitive design. I was able to full implement this feature to my own resources as well as future proof my app as using a recycler view will give better optmization over the long run with a big data set. 
