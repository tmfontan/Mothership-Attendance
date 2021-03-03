# TCP Automated Attendance (Mothership Attendance)

![Alt Text](Screenshots/Social_Media_Image.jpg)

Mothership Attendance is a Java Swing / AWT application which functions as an attendance recording tool for a group of custom created attendee accounts. Other than the previously mentioned process, the application also gives user’s access to four separate features depending on the their selected account type. A list of these additional features is provided below:

*	Profile Customization
*	Course Creation / Management
*	Attendee Chat Interface
*	Attendance Record Manipulation

The features mentioned above, and their processes of implementation will be explained in depth further on within the report. If you wish to skip to a particular section, please click on the corresponding link below in the table of contents:

#### Table of Contents
* [Database Guide](#Database-Guide)
* [Application Start](#Application-Start)
* [Login Screen](#Login-Screen)
* [Account Creation](#Account-Creation)

## Database Guide
This application implements a remote database connection in order to save user progress from previous sessions. The remote database is hosted by the online service myPHPAdmin and is composed of seven separate tables. The ER Diagram below displays the names and property values of each table within the database and how they correlate to each other:
![Alt Text](ERDiagram.pdf)

## Application Start
Upon starting the application, users are first greeted with an introductory screen that displays both a progress bar and the project’s logo. The progress bar will continuously load while the application creates instances of certain overhead classes which it will need in order to function. While this occurs, a GIF image displaying multiple nature-scenic images will loop in the background until the current instance of the application has finished its preparation. The figures below show how the application may appear during this segment:

![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_1.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_2.png)
![Alt Text](Screenshots/divider_line_neon.png)

Users are then greeted with a directory screen which provides them with a small set of instructions and allows them to choose between one of two separate designations. Individuals can choose to continue as either an “Instructor” or “Student” account whose differing features will be explained in the next section. A user can then choose to either login with a preexisting account or create a new profile via a button displayed at the bottom of the login screen. The image below show how this directory segment of the application will appear:

![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_3.png)
![Alt Text](Screenshots/divider_line_neon.png)

## Login Screen
During the login process, the application will authenticate the specified username and password values via a remote database search. Should the specified password value fail to match, or the specified username isn’t found, then the application will display a customized warning message that explains why the login process has failed. The login screen will appear the same way regardless of which designation button the user has clicked. The series of images below display how the login window will appear in addition to one of the many error catching processes and how it will alter the overall look of the GUI:

![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_4.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_5.png)
![Alt Text](Screenshots/divider_line_neon.png)
![Alt Text](Screenshots/Screenshot_6.png)
![Alt Text](Screenshots/divider_line_neon.png)

## Account Creation

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
