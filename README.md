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

There is currently no penalty for numerous failed login attempts which greatly increases the vulnerability of application accounts. However, this feature will be implemented should the TCP Automated Attendance Application ever be used commercially.

Finally, should the user-inputted credentials end up being validated, the user will be presented with the following screen:

![Alt Text](Screenshots/Screenshot_Login_Success.png)

From here, an event queue thread that has been executing in the background; will wait until the synchronized, continue to main screen,  boolean value is changed to true. After this happens it will cause the directory screen to be disposed of, before having the main screen of the application take its place.

## Account Creation
A user can access the account creation window by clicking the "Create Account" JButton Component located at the bottom left of the login window screen. Depending on which designation the user has selected on the Directory Screen, a customized Window with different input requirements will appear. The Instructor's Account Creation Screen will appear as follows:

![Alt Text](Screenshots/Screenshot_ACI.png)

Alternatively, the Student's Version of the Account Creation Screen will be displayed as shown:

![Alt Text](Screenshots/Screenshot_ACS.png)


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
