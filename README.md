# TCP Automated Attendance (Mothership Attendance)

![Alt Text](Screenshots/Social_Media_Image.jpg)

Mothership Attendance is a Java Swing / AWT application that functions as an attendance recording tool for a group of custom-created attendee accounts. Other than the previously mentioned process, the application also gives user’s access to four separate features depending on their selected account type. A list of these additional features is provided below:

*	Profile Customization
*	Course Creation / Management
*	Attendee Chat Interface
*	Attendance Record Manipulation

The features mentioned above, and their implementation processes will be explained in depth further within the report. If you wish to skip to a particular section, please click on the corresponding link below in the table of contents:

#### Table of Contents
* [Database Implementation](#Database-Implementation)
* [Application Start](#Application-Start)
* [Login Screen](#Login-Screen)
* [Account Creation](#Account-Creation)
* [Welcome Screen](#Welcome-Screen)
* [Profile Customization](#Profile-Customization)
* [Management](#Management)
* [Chat Interface](#Chat-Interface)
* [Attendance Records](#Attendance-Records)
* [Attendance Server](#Attendance-Server)

A video walkthrough of the Mothership Attendance Application can be found at the following link:

[![Mothership Attendance Walkthrough](Screenshots/Youtube_Link.png)](https://youtu.be/7Z4EQI0jJ98)

## Database Implementation
This application uses a remote database connection in conjunction with [MySQL](https://www.mysql.com/) to catalog progress from previous user sessions. The remote database is hosted by the online service [myPHPAdmin](https://www.phpmyadmin.net/) and is composed of seven separate tables. The ER Diagram below displays the names and property values of each table within the database and how they correlate to each other:

![Alt Text](Screenshots/Screenshot_ER_Diagram.png)

### Account Tables
There are two primary types of tables in the ER Diagram above. The "Instructor" and "Student" tables, which have red-colored headers, are classified as Account Tables. This type of table is responsible for holding the data associated with the user-created profiles in the application. While both the Instructor and Student Tables are classified as account type tables, they also accept different types of information in addition to having different primary and foreign key properties. For more information regarding the implementation of the Account Creation Segment, please reference the [Account Creation](#Account-Creation) Section. 

Unlike Instructor Accounts, which can only be identified via their unique database identification number and username properties, Student Accounts have a third unique property, a Student Identification Number (StudentID). Student Profiles are given this third property due to the heavy distribution and frequent reference of their basic profile information in the main window of the application in addition to the fact there will be a higher amount of students compared to the number of instructors. This design concept was implemented seeing as it is much easier to search for and keep track of the student's university/school-issued identification number as opposed to their generic database entry number. While this isn't currently necessary due to the small size of the remote database, should the number of student profiles ever reach an exponential size, it will increase the proficiency of database search algorithms and improve the program's overall efficiency.

A Student Account's display name, email address, and profile image properties are frequently shared and displayed on the Instructor's side of the Application. For instance, during a running instance of the Attendance Server, the Instructor's side of the application will display the following information after a student account has successfully connected:

![Alt Text](Screenshots/Screenshot_IAS.png)

Alternatively, the Student's side of the application will only show the display name and profile picture properties during a running Instance of the Attendance Server:

![Alt Text](Screenshots/Screenshot_SAS.png)

The application contains multiple other segments which similarly use student data.

This design style is implemented to regulate the amount of sensitive data being distributed and enforce the concept of least privilege. While the Student ID Number may not be considered sensitive within the actual scope of the application; should the program be used in a realistic university or school-based setting, it will ensure that the instructor accounts are the only individuals privy to such potentially damaging information.

### Link Tables
The second type of table located within the remote database is classified as a Link Table. These tables, which are classified by a blue-colored header, are responsible for linking the different account type profiles to a specific Class or "Course" database entry. This is done to shorten the range of available students that the application needs to iterate through before finding the target profile's information.

The **Instructor Link** & the **Student Link** Tables only contain three property fields. The first property in each is the database entry number which will be unique in each local table. The second property field is responsible for storing the "Course ID", or course database entry number, that the Student or Instructor profile is associated with. Finally, the last property in each table will be the respective Instructor or Student database entry number within the database.

Records within both tables are automatically deleted should a Student Account be removed from a class or an Instructor Account unlinks their profile from the course. More information about the Instructor course unlinking process can be found in the [Management Course Linking](#Management-Course-Linking) Section.

### Class Table
The Class Table is responsible for holding all of the auxiliary information related to a specific course object entry in the database. These objects were implemented to better group and categorize account profiles within the application. Additionally, they are used during situations when Instructors choose to unlink their account from a particular class while there are still Student Accounts associated with the Course Object. This feature was added in case a scenario such as an Instructor Swapping or Replacement occurs. It is mainly done to preserve the current categorization status of student profiles and their previous Attendance Records.

### Record Date Table
The Record Date Table is responsible for holding the individual dates that attendance has been taken for a specific course. There are multiple attendance records per student, class, and day present within the database. Thus, it would be highly inefficient to iterate through every single entry to find the records for a specific student on a given date. This table was installed to increase the application's efficiency by referencing each particular day that the current Instructor marked attendance for. Having these individual dates referenced allows for the database execution thread to quickly find Student Attendance Records associated with the user's chosen date. The date property within the table is a String variable in the format (XX/XX/XXXX). This table is mainly used during the Attendance Records Tab present within the Instructor's version of the Application.

### Attendance Record Table
The Attendance Record Table is used to hold individual Student Attendance Record Objects which are created each time an instructor marks role in a chosen course. Attendance Record Objects contain seven unique properties which are used to categorize and store information about the statuses and behaviors of Students per class period. 

The ID property is used to reference and differentiate between each separate Attendance Record in the table while the ClassID, StudentID, and InstructorID property fields are used as foreign keys meant to link the current entry to the Class, Student, and Instructor Tables present in the database. The date property is present to link the current attendance record with the date on which it was taken. This date can be found within the Record Date table and is used to promote program efficiency by limiting the record search range.

The status property is capable of holding one of three unique values. The property can hold a value of "Present" if the student has attended the class session while it will otherwise hold a value of "Absent". Finally, the third option, in which the record status is marked as "NYA", stands for the phrase "Not Yet Added." Whenever a student is added to a course that already has past attendance records, the application automatically creates attendance records for that student on the days where role was previously taken. The "NYA" status is applied to these new records so the teacher will know about the student's late enrollment and so the application won't factor these records into the final attendance average.

The final property, comments, is used to store a fixed-length string containing a short reminder or note concerning the student's behavior on that particular day. This value and the attendance status property can be changed anytime by the Instructor via the Attendance Records Tab in their version of the application.

## Application Start
Upon starting the application, users are first greeted with an introductory screen that displays both a progress bar and the project’s logo. The progress bar will continuously load while the application creates instances of certain overhead synchronized variable classes which it will need for the main screen of the application to function. While this occurs, a GIF image displaying multiple nature-scenic images will loop in the background until the current instance of the application has finished its preparation. The figures below show how the application will appear during this portion:

![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_1.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_2.png)
![Alt Text](Screenshots/divider_line_neon.png)

Users will then be shown a directory screen that provides a small set of instructions and allows them to choose between one of two separate designations. Individuals can choose to continue either as an “Instructor” or “Student” account by clicking the corresponding JButton Components. A user can then choose to either log in with a preexisting account or create a new profile via the "Create Account" JButton displayed at the bottom of the login screen. The image below shows how this directory segment of the application will appear:

![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_3.png)
![Alt Text](Screenshots/divider_line_neon.png)

## Login Screen
During the login process, the application will authenticate the user-specified username and password values via a remote database search. Should the specified password value fail to match, or the defined username isn’t found, then the application will display a customized warning message that explains why the login process has failed. The login screen will appear the same way regardless of which designation button the user has clicked. The series of images below display how the login window will appear in addition to one of the few Warning Message Screens and how it will subsequently alter the aesthetic appearance of the GUI:

![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_4.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_5.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_6.png)
![Alt Text](Screenshots/divider_line_neon.png)

### Warning Message Screens
A Warning Message Screen will be triggered during the login process from one of five cases. As previously mentioned, should the username value not be found within the remote database, a warning message will be displayed, containing a yellow and white warning icon, that asks the user to either double-check the provided value or create a new account should the user not already have one. This message will also cause both the username and password JTextField Components to have red backgrounds in addition to creating a secondary warning message beneath the password JTextField on exiting the window. This feature was implemented to explain the cause of the error in case the user has already forgotten the reason after exiting the Warning Message Window. The red backgrounds and bottom error message on the login screen will then disappear once a user clicks into one of the JTextField Components to change its value or re-clicks the login button.

The second warning message will be triggered should the user only provide an invalid password value during the login attempt. The only differences between this Warning Message Screen and the previous one are that only the password JTextField Component's background will be colored red and the secondary warning message will contain different content.

The final three message types will simply be triggered should the user attempt to login without providing any input data. The three cases are when the user fails to provide just a username, just a password, or both a username and password. These three screens will follow the same principles as the screens above except for not changing a field's background color.

The rules mentioned above apply to the same types of input fields and conditions found throughout the entire application. They will all trigger a Warning Message Screen which follows the same principles above determined by the type of error occurring. Those errors will either be caused by a lack of input information, data not being verified, or a system error that will cause the aesthetic properties of the current screen to change. Regarding the system error type Warning Message Screens, should a system error occur, such as a failed database retrieval, lost internet connection, or server shutdown, then only a secondary warning message will be displayed upon exiting the window. The input field aesthetics will not be changed.

##

There is currently no penalty for numerous failed login attempts which greatly increases the vulnerability of the application. However, this feature will be implemented should the TCP Automated Attendance Application ever be used in a commercial setting.

Finally, should the user-inputted credentials end up being validated, the user will be presented with the following screen:

![Alt Text](Screenshots/Screenshot_Login_Success.png)

During this time; there is an event queue thread, separate from the main thread, which is executing in the background of the Directory Screen GUI. This thread is tasked with waiting for the synchronized, continue to main screen boolean value, to be changed from its initial value of false to true. This boolean value will be changed once the user has clicked the OK JButton Component or exited the window shown in the previous authentication success screen. Once this value has been changed and the background event queue thread detects it, it will trigger the disposal of the current Directory Screen Instance in addition to creating a new instance of the Instructor / Student Main Screen GUI. Finally, the background thread will terminate upon finishing this task and disappear with the Directory Screen.

## Account Creation
A user can access the account creation window by clicking the "Create Account" JButton Component located at the bottom left of the login window screen. A custom account creation window will then appear which takes the place of the current login screen instance. The Account Creation Screen will be tailored differently depending on which designation the user has selected. The Instructor's version of the window will ask users to input seven different values of identification data while the Student's version will require eight. The Instructor's version of the Account Creation Screen will appear as follows:

![Alt Text](Screenshots/Screenshot_ACI.png)

Alternatively, the Student's Version of the Account Creation Screen will be displayed as shown:

![Alt Text](Screenshots/Screenshot_ACS.png)

As previously mentioned, both account creation processes require the user to input user identification information in multiple fields. The first two fields are meant to hold the user's first and last names. These values will later be combined to create the Display Name Property which is shown as both the profile name to other users in addition to the header name present in the profile tab. The third field is used to collect the email address that the user wants to associate with their account. Like the display name, this property is used for informational purposes when it is displayed on the screens of other client instances. In the current version of the application, the email address has no functional purpose; though this may be subject to change in later installments. The final four fields are used to determine and verify both the username and password values that the user wishes to use as their identification for the account. While the first username and password fields are simply used to store the user's selected value, the second fields under each component are used to verify the spelling of the chosen value. This feature is implemented to ensure that the user is completely aware of which password and usernames they wish to use. The username value associated with the account will be non-alterable after its creation seeing as the string value will be unique amongst all other Student and Instructor account username values in the database. Currently, there is no way for a user to change their chosen password value. However, this will be changed in later installments as well.

Each of the previously mentioned input fields will also require different specializations depending on the field. The specialization requirements can be found within the tooltip box that appears while hovering the mouse over an input field. For instance, both the First and Last Name fields require user input containing only alphabetic and certain special characters. The fields cannot contain values with numerical characters and only certain special characters such as the ( ' ) and ( - ) which are sometimes part of names. In contrast, the Email Address field allows the user to input numerical characters in addition to allowing more special characters to use. However, this field also has a length requirement of at least ten characters and must include the ( @ ) symbol somewhere within the input. This feature was implemented seeing as most modern-day email addresses are at least ten characters long and must have an ( @ ) symbol to denote the domain. The username and confirm username fields only require the user to input a response that is at least eight characters long and doesn't contain any special characters. This means that a person can use whichever alphabetic and numerical characters that they want in the creation of their username value. Finally, the password and confirm password input fields must contain values that are longer than eleven characters and have at least three numerical characters present within the value. There can be no special characters present within the string. This is done due to both security reasons along with the possibility of not having access to certain special characters on different machines.

The student's version of the account creation screen also has an additional input field with vastly different requirements. Whereas all the previous input fields required the entrance of a string value, the Student Identification Number input field, present within the Student Account Creation Window, will only allow the user to enter a number value inside the range of two million through ten million (2,000,000 - 10,000,000). This field is used to collect the university or school-issued student identification number which is unique to each person. This value can only contain numerical characters and will be frequently used to retrieve the student account's profile information during the main screen of the application.

The length property required in most of these fields was installed to better defend against cyber attacks. For instance, in a brute force attack, a malicious program will iterate over every possible password combination available after discovering the username to an account. Including uppercase and lowercase letters along with numerical characters, means that every single character within a password can be one of sixty-two possibilities. In terms of probability, that essentially means that a malicious program may have to iterate through 62<sup>11</sup> possible combinations before cracking the password. Even with how far computers have progressed, this process would still take even the fastest computers months if not years to complete. By this time, the password value would have been changed or the person executing the malicious program will have given up to find an easier target.

Should a user attempt to progress through either of the account creation windows before inputting all of the required information or the account creation process fails, it will trigger a customized warning message window that follows the same properties and actions as mentioned in the
 [Warning Message Screens](#Warning-Message-Screens) Segment.

Upon successfully creating an account, the user will be presented with the screen below before the background Directory Screen thread disposes of the window and replaces it with a new instance of the Main Screen Window:

![Alt Text](Screenshots/Screenshot_AC_Success.png)

## Welcome Screen
## Profile Customization
## Management Tab
## Chat Interface
## Attendance Records
## Attendance Server

![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_A.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_B.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_C.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_D.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_E.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_F.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_G.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_H.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_I.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_J.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_K.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_L.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_M.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_N.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_O.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_P.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_Q.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_R.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_T.png)
