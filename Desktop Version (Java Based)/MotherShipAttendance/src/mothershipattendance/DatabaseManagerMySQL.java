/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *  This Class is Used in Order to Manipulate and Query Data From the Remote Database Used Within
 *  the Mothership Attendance Application. Most of the Methods Contained within this Class Use the Global
 *  String Variables Declared at the Top of the Class in order to Query or Modify the Database's Contents.
 *  The Remote Database is Controlled Via the MyPHPAdmin MySQL Website. The User can Visit this Site Via
 *  the Developer Button Located within the MainScreen GUI Window of Either the "Instructor" or "Student"
 *  Application Versions. However, they will need to know the Login Credentials in order to Access the 
 *  Website and invoke Changes within the Remote Database. The Login Credentials For the Website Are 
 *  Already Included Within this Class Via the getConnection() Method. So, Theoretically, this 
 *  Application Should Be Able to Connect to the Remote Database Regardless of Which Type of Machine 
 *  it is Currently Operating on. This Class makes use of the External Driver JDBC File:
 *  "com.mysql.cj.jdbc.Driver" in Order to Implement the Connection with the Remote Mothership Attendance 
 *  Application. This File Can be Found Via the External Jars Folder Located in the Applications Root Directory.
 *  Most of the Data Exchanged Within the Remote Database can be Manipulated Via the Management Screen Within
 *  the Instructor's Version of the Application.
 * 
 *  @date           November 20 2020
 *  @author         Tyler Fontana
 *  @version        1.0.2
 */

public class DatabaseManagerMySQL {
    
    /**
     *  Create a Custom Connection Object Which Will be Used in Establishing
     *  the Connection to the MyPHPAdmin Remote Database File. This Object can
     *  only be Referenced From Within this Class.
     */
    private Connection connection = null;
    
    /**
     *  Create a Custom Statement Object Which Will Be Used in Order to Execute
     *  the Specified Global and Local Database Query / Modification Statement
     *  Strings.
     */
    public Statement statement;
    
    /** --------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  The Following Global String Variables Are Database Query / Modification Statements Which are Applicable to the Tables 
     *  Present Within the Remote Database "Z3C5Rp1ZSv". The Keywords within the Statements are Separated From the rest of 
     *  the Data Via Indentation, and Are Shown using Total Capitalization. The Data Following the Keywords is shown on a 
     *  Subsequent Indented Line and Formatted to keep the Query Conditions Visible within the Shown Section of the Current
     *  Machine's Window Screen. The "INSERT", "DELETE", "SELECT", and "UPDATE" Statements will be Separated Via Comments 
     *  Designations. This Formatting is Done in Order to promote Easier Readability and Comprehension for any other 
     *  Programmers Future / Present working on the Application.
     */
    
    /** --------------------------------------------------------------------------------------------------------------------- **/
    /**                                                     SELECT STATEMENTS                                                 **/
    /** --------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in order to Select the "Instructor" Account Information From the Remote Database Which is 
     *  Associated With the Specified Parameter "Username" String. This Query is Typically Invoked Whenever the Current User 
     *  is Attempting to Login to His / Her "Instructor" Profile Account.
     */
    
    /** --------------------------------------------------------------------------------------------------------------------- **/
    
    private final String SELECT_INSTRUCTOR_CREDINTIALS =            "SELECT " +
                                                                                "Username, Password " +
                                                                    "FROM " +
                                                                                "Instructor";
    
    /** --------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in order to Select the "Student" Account Information From the Remote Database Which is 
     *  Associated With the Specified Parameter "Username" String. This Query is Typically Invoked Whenever the Current User 
     *  is Attempting to Login to His / Her "Student" Profile Account.
     */
    
    /** --------------------------------------------------------------------------------------------------------------------- **/
    
    private final String SELECT_STUDENT_CREDINTIALS =               "SELECT " +
                                                                                "Username, Password " +
                                                                    "FROM " +
                                                                                "Student";

    
    /** --------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query Statement is Used in Order to Retrieve the Miscellaneous Information Associated With the Student
     *  Account Using the Parameter Username Value. This Query will Typically be Invoked Either After the Application has
     *  Determined that the User Input Credential Values are Valid, or the User Changes His / Her Account Profile Information
     *  Via the Profile Tab within the Main Screen GUI Window of the Application.
     */
    
    /** --------------------------------------------------------------------------------------------------------------------- **/
    
    private final String SELECT_STUDENT_INFORMATION_BY_USERNAME =           "SELECT " +
                                                                                        "* " +
                                                                            "FROM " +
                                                                                        "Student " +
                                                                            "WHERE " +
                                                                                        "Username = ?";
    
    /** --------------------------------------------------------------------------------------------------------------------- **/
            
    /**
     *  This Database Query Statement is Used in Order to Retrieve the Miscellaneous Information Associated With the Instructor
     *  Account Using the Parameter Username Value. This Query will Typically be Invoked Either After the Application has
     *  Determined that the User Input Credential Values are Valid, or the User Changes His / Her Account Profile Information
     *  Via the Profile Tab within the Main Screen GUI Window of the Application.
     */
    
    /** --------------------------------------------------------------------------------------------------------------------- **/
    
    private final String SELECT_INSTRUCTOR_INFORMATION_BY_USERNAME =       "SELECT " +
                                                                                        "* " +
                                                                            "FROM " +
                                                                                        "Instructor " +
                                                                            "WHERE " +
                                                                                        "Username = ?";
    
    /** --------------------------------------------------------------------------------------------------------------------- **/
            
    /**
     *  This Database Query is Used in order to Select a List of all the Student's Currently Present Within the Database Via 
     *  their Unique Primary Key Identifier Number Present Within the "Student Identification Number" Field. While the 'ID' 
     *  Field Can Also Be Used in order to Complete this Task, the "Student Identification Number" Field can also act as a 
     *  Primary Key when Attempting to Retrieve Values. This Query String is Typically Invoked by an "Instructor" Account 
     *  Whenever they Attempt to Retrieve a List of All Available Students Present Within the Database in order to Add Student
     *  Accounts to the currently chosen Class within the Management Tab / Manage Student Access JPanel Option.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String SELECT_STUDENTS_BY_STUDENTID =             "SELECT " +
                                                                                "* " +
                                                                    "FROM " +
                                                                                "`Student` " +
                                                                    "WHERE " +
                                                                                "StudentID = ";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in order to Select a List of all the Class Object IDs Associated With the Current Instructor
     *  Account. This Query is Typically Invoked From the Main Screen: Attendance Records Tab in the Instructor's Version of the
     *  Application. It is Used in Order to Find the Database Row Entry Numbers of the Classes which the Instructor Can Use in
     *  order to Retrieve the Other Miscellaneous Information Concerning Each Class.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String SELECT_CLASSIDS_BY_INSTRUCTORID =          "SELECT " +
                                                                                "ClassID " +
                                                                    "FROM " +
                                                                                "InstructorClassLink " +
                                                                    "WHERE " +
                                                                                "InstructorID = ?";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    //"SELECT * FROM Class WHERE ID = '" + classIds.get(i) + "'"
    
     /**
     *  This Database Query is Used in order to Select a List of all the Class Information Objects Associated With the Current
     *  Instructor's Account. This Query is Typically Invoked From the Main Screen: Attendance Records Tab in the Instructor's 
     *  Version of the Application. It is Used in Order to Find the Names an Miscellaneous Data of the Classes which the
     *  Instructor Can Use in order to Check the Student Attendance Records For.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String SELECT_CLASSINFO_BY_CLASSIDLIST =          "SELECT " +
                                                                                "* " +
                                                                    "FROM " +
                                                                                "Class " +
                                                                    "WHERE " +
                                                                                "ID = ?";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    //"SELECT StudentID FROM Student"
    
     /**
     *  This Database Query is Used in order to Retrieve a List of all the Unique Student Identification Number Values Present
     *  Within the Student Table of the Remote Database. This Query is Typically Executed Upon the Database Whenever an Instructor
     *  Account Attempts to Enroll Student Accounts in an Associated ClassType (Course) Object, or When an Instructor Account
     *  Attempts to Check the Attendance Records of a Specified ClassType (Course) Object.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String SELECT_STUDENTID_FROM_STUDENT =            "SELECT " +
                                                                                "StudentID " +
                                                                    "FROM " +
                                                                                "Student";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
     /**
     *  This Database Query is Used in order to Retrieve a List of all the Unique "Username" Column String Values From Within the
     *  Student Table Present in the Remote Database. This Query is Typically Executed Upon the Database Whenever the Current User
     *  is Attempting to Create a New Account using Either Designation Type. Seeing as the "Username" Value must be a Unique String
     *  Value Within Both the Instructor and Student Tables, we use this Query to Check if the Parameter User Specified Username
     *  Value is Present in the Instructor Table. This Query will be either Followed Up or Trailed By An Additional Query Which
     *  Checks the Contents of the Student Table. Once Again, this is All done to Ensure that the Specified Username Value is
     *  Completely Unique and Can be Used to Retrieve the Desired Account Information Without causing any Duplication Errors.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String SELECT_USERNAME_FROM_INSTRUCTOR =          "SELECT " +
                                                                                "Username " +
                                                                    "FROM " +
                                                                                "Instructor";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in order to Retrieve a List of all the Unique "Username" Column String Values From Within the
     *  Student Table Present in the Remote Database. This Query is Typically Executed Upon the Database Whenever the Current User
     *  is Attempting to Create a New Account using Either Designation Type. Seeing as the "Username" Value must be a Unique String
     *  Value Within Both the Instructor and Student Tables, we use this Query to Check if the Parameter User Specified Username
     *  Value is Present in the Instructor Table. This Query will be either Followed Up or Trailed By An Additional Query Which
     *  Checks the Contents of the Student Table. Once Again, this is All done to Ensure that the Specified Username Value is
     *  Completely Unique and Can be Used to Retrieve the Desired Account Information Without causing any Duplication Errors.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String SELECT_USERNAME_FROM_STUDENT =             "SELECT " +
                                                                                "Username " +
                                                                    "FROM " +
                                                                                "Student";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    //"SELECT last_insert_id()"
    
    /**
     *  This Database Query is Used in Order to Retrieve the Unique Row Entry ID Integer Number that the Remote Database has Just
     *  Assigned to the Most Recently Inserted Table Object. This Query is Typically Invoked After the Successful Insertion of
     *  Either a new "Student" or "Instructor" Account into their Respective Tables Within the Remote Database. After the New
     *  Account has been Inserted, it will have a new Unique Row ID Number Value Due to Either Table's Auto-Increment Feature
     *  Concerning the First Column. Seeing as this Account's Auxiliary Information may be Changed Immediately after the Account
     *  Has Been Created, Via the Main Screen: Edit Profile Tab, We Will need to Have the Actual Row Entry ID Integer Number
     *  instead of the Placeholder Value ("-1") that the New Account is Created With. This Row ID Value will Allow us to Invoke
     *  Changes Within the New Account Regardless of the Fact that it was just Created.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String SELECT_ROWID_LAST_INSERT =                     "SELECT " +
                                                                                    "last_insert_id()";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    //"SELECT * FROM StudentClassLink WHERE ClassID = '" + value.get(0).getAttendanceRecordClassID()  + "'"
    
     /**
     *  This Database Query is Used in order to Select a List of all the Student Class Link Entry Objects That Are Associated
     *  With the User Specified Parameter Class ID Integer Value. This Query is Typically Called Whenever an Instructor Account
     *  Invokes the Attendance Marking Feature During a newly Created Attendance Server.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String SELECT_STUDENTCLASSLINKVALUE_BY_CLASSID =      "SELECT " +
                                                                                    "* " +
                                                                        "FROM " +
                                                                                    "StudentClassLink " +
                                                                        "WHERE " +
                                                                                    "ClassID = ?";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    //"SELECT * FROM Attendance WHERE ClassID = '" + value.get(0).getAttendanceRecordClassID()+ "' AND InstructorID = '" + value.get(0).getAttendanceRecordInstructorID() + 
    //"' AND Date = '" + value.get(0).getAttendanceRecordDate() + "'"
    
     /**
     *  This Database Query is Used in order to Select a List of Attendance Record Object Entries From Within the Remote Database,
     *  that Pertain to the Parameter Information Passed in By the User. The Query is Structured Around Finding the Attendance
     *  Record Objects whose Values Include the Specified Instructor ID Integer Value, Class Type (Course) ID Integer Value, and
     *  String Value Matching the Parameter Date String.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String SELECT_ATTENDANCERECORDS_BY_PARAMETERS =       "SELECT " +
                                                                                    "* " +
                                                                        "FROM " +
                                                                                    "Attendance " +
                                                                        "WHERE " +
                                                                                    "ClassID = ? " +
                                                                        "AND " +
                                                                                    "InstructorID = ? " +
                                                                        "AND " +
                                                                                    "Date = ?";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in order to Retrieve a List of the Student Identification Number Values Associated With the
     *  Current Class Type Object. This Query is Typically Invoked From Within the Instructor's Main Screen GUI: Attendance Records
     *  Tab. The List of Student ID Values is Used in order to Retrieve the Student "Display Name" Values Associated With Each
     *  of the Retrieve Student Identification Number Values. 
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String SELECT_STUDENT_IDS_BY_CLASS =                  "SELECT " +
                                                                                    "StudentID " +
                                                                        "FROM " +
                                                                                    "StudentClassLink " +
                                                                        "WHERE " +
                                                                                    "ClassID = ? ";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    //"SELECT * FROM Attendance WHERE ClassID = '" + classID + "' AND Date = '" + date + "'"
    
    /**
     *  This Database Query is Used in order to Retrieve a List of the Attendance Record Objects Categorized By the Unique Row ID
     *  Integer Number Associated with the Chosen Class Type (Course) Object and the Specified Date String Value. This Query is
     *  Typically Invoked From Within the Instructor's Main Screen GUI: Attendance Record Tab When the User Attempts to Look Up
     *  Attendance Records using the "By Class" Method.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String SELECT_ATTENDANCERECORDS_BY_CLASSID_AND_DATE =         "SELECT " +
                                                                                            "* " +
                                                                                "FROM " +
                                                                                            "Attendance " +
                                                                                "WHERE " +
                                                                                            "ClassID = ? " +
                                                                                "AND " +
                                                                                            "Date = ?";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    
    /** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ **/
    /** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ **/
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    /**                                                      UPDATE STATEMENTS                                                 **/
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in Order to Update the Information of a Pre-Existing Class Table Entry. The Class Entry
     *  Object is Found by Using the Specified Primary Key Parameter ID Value which is Associated With the Current Class
     *  Type. We Use the ID Integer Field as the Primary Key instead of the Class Title or Number seeing as there is a 
     *  Possibility of Multiple Classes Containing the Same Information in the Fields. In fact, two Classes can be Completely
     *  identical in information. This is Why, we assign each Object a Unique Integer ID Number in order to Differentiate
     *  between the entries. This Query Statement is Typically Invoked From Within the Instructor's Version, Management
     *  Tab / Update Class Information JPanel Component.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String UPDATE_CLASSTYPE =                         "UPDATE " +
                                                                        "Class " +
                                                                            "SET " +
                                                                                "ID = ?, Field = ?, ClassNumber = ?, " +
                                                                                "Title = ?, Type = ?, Description = ?, " +
                                                                                "Credits = ?, Section = ?, Semester = ?, " +
                                                                                "Year = ?, Participants =? " +
                                                                    "WHERE " +
                                                                        "ID = ?";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in Order to Update the Information of a Pre-Existing Attendance Record Object Entry Which is
     *  Present Within the Attendance Table. An Attendance Record Object is Used to Store the Attendance Information of
     *  a Single Student Enrolled in a Class. This Object includes Fields For the Date that the Record was Taken, the Attendance
     *  Status of the Student (I.E. Present, Absent, Current Not Enrolled (NE)), and a Field For Comments About the Student's
     *  Performance During that Date. Attendance Records can be Created During the Attendance Marking Process Within the
     *  Attendance Tab of the Instructor's MainScreen GUI Window. They can be Modified From Within the Attendance Records Tab
     *  of the Instructor's MainScreen GUI Window.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String UPDATE_ATTENDANCE_RECORD =                 "UPDATE " +
                                                                        "Attendance " +
                                                                            "SET " +
                                                                                "ID = ?, ClassID = ?, InstructorID = ?, " +
                                                                                "StudentID = ?, Status = ?, Date = ?, " +
                                                                                "Comments = ? " +
                                                                    "WHERE " +
                                                                        "ID = ?";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in Order to Update the Information of a Pre-Existing "Instructor" Profile Account which is
     *  Present Within the Instructor Table of the Remote Database. This Query is Typically Executed Whenever a user Changes
     *  their Identification Information From Within the Profile Tab of the MainScreen GUI Window. (Instructor Version)
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String UPDATE_INSTRUCTOR =                        "UPDATE " +
                                                                        "Instructor " +
                                                                            "SET " +
                                                                                "ID = ?, FirstName = ?, LastName = ?, " +
                                                                                "Username = ?, Password = ?, Email = ?, " +
                                                                                "DisplayName = ?, Phone = ?, Address = ?, " +
                                                                                "Biography = ?, ImageLarge = ?, " +
                                                                                "ImageMedium = ?, ImageSmall = ?, " +
                                                                                "ImageXS = ? " +
                                                                    "WHERE " +
                                                                        "ID = ?";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in Order to Update the Information of a Pre-Existing "Student" Profile Account which is
     *  Present Within the Student Table of the Remote Database. This Query is Typically Executed Whenever a user Changes
     *  their Identification Information From Within the Profile Tab of the MainScreen GUI Window. (Student Version)
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String UPDATE_STUDENT =                     "UPDATE " +
                                                                    "Student " +
                                                                        "SET " +
                                                                            "ID = ?, StudentID = ?, FirstName = ?, " +
                                                                            "LastName = ?, Username = ?, Password = ?, " +
                                                                            "Email = ?, DisplayName = ?, Phone = ?, " +
                                                                            "Address = ?, Biography = ?, ImageLarge = ?, " +
                                                                            "ImageMedium = ?, ImageSmall = ?, " +
                                                                            "ImageXS = ? " +
                                                              "WHERE " +
                                                                    "ID = ?";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ **/
    /** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ **/
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    /**                                                      INSERT STATEMENTS                                                 **/
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in Order to Insert a New "Student" Profile Account Entry into the Remote Database. It is 
     *  Typically Invoked From Within the Account Creation GUI Window (Student Version) When the Current User Attempts to Create 
     *  a New Mothership Attendance using the "Student" Designation.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String INSERT_STUDENT =                           "INSERT " +
                                                                        "INTO " +
                                                                            "Student" +
                                                                                "(ID, StudentID, FirstName, " +
                                                                                "LastName, Username, Password, Email, " +
                                                                                "DisplayName, Phone, Address, Biography, " +
                                                                                "ImageLarge, ImageMedium, ImageSmall, " +
                                                                                "ImageXS) " +
                                                                    "VALUES " +
                                                                                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                                                                                 "?, ?, ?)";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in Order to Insert a New "Instructor" Profile Account Entry into the Remote Database. It is 
     *  Typically Invoked From Within the Account Creation GUI Window (Instructor Version) When the Current User Attempts to 
     *  Create a New Mothership Attendance using the "Instructor" Designation.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String INSERT_INSTRUCTOR =                        "INSERT " +
                                                                        "INTO " +
                                                                            "Instructor" +
                                                                                "(ID, FirstName, LastName, " +
                                                                                "Username, Password, Email, DisplayName, " +
                                                                                "Phone, Address, Biography, ImageLarge, " +
                                                                                "ImageMedium, ImageSmall, ImageXS) " +
                                                                    "VALUES " +
                                                                                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                                                                                "?, ?)";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in Order to Insert a New Attendance Record Entry Object in the Attendance Table Present
     *  Within the Remote Database. An Attendance Record Object is Created Whenever an "Instructor" Starts the Attendance
     *  Server From their Main Screen GUI Window, and uses the Mark Attendance Function in Order to Take Attendance for the
     *  Entire Class of Students. Should a Student Currently Not Be Present Within the Attendance Server, then the Attendance
     *  Marking Method will still Create an Attendance Record for that Student. However, instead of Having the Attendance Status
     *  of "Present" on the New Entry, it will instead contain a value of "Absent".
     */
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String INSERT_STUDENT_ATTENDANCE_RECORD =         "INSERT " +
                                                                        "INTO " +
                                                                            "Attendance" +
                                                                                "(ID, ClassID, InstructorID, " +
                                                                                "StudentID, Status, Date, Comments) " +
                                                                    "VALUES " +
                                                                                "(?, ?, ?, ?, ?, ?, ?)";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in Order to Insert a New Date Record Entry Object into the Date Record Table Present Within
     *  the Remote Database. The Date Record Object is Used in Conjunction with the Attendance Record Objects. It is Essentially
     *  Responsible for Acting as Date Marker for all of the Available Attendance Dates in a Given Class. One Date Record Object
     *  Will be Created for Each Date that the Instructor has Taken Attendance. This Object is Meant to act as an Look-Up Object
     *  Value for when the Current "Instructor" User wishes to Check Attendance Records. Instead of Pulling all the Available
     *  Information Down, Associated with the Chosen Class, from the Attendance Record Table; we Instead Pull Down a List of
     *  Date Record Objects in order to Retrieve the Dates that the Current Instructor has Marked Attendance for the Chosen Class.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String INSERT_DATE_RECORD =                       "INSERT " +
                                                                        "INTO " +
                                                                            "RecordDate" +
                                                                                "(ID, ClassID, Date) " +
                                                                    "VALUES " +
                                                                                "(?, ?, ?)";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in Order to Insert a New Class Entry Object in the Class Table Present Within the Remote
     *  Database. A Class Type Object is Used to Create and Designate the Included Information Needed When Creating a New
     *  Class for the University. Information Fields Include things such as the Class's Field, Title, Number, Section,
     *  Semester, and Year. This Query Statement is Typically Invoked From Within the Instructor's Main Screen: Management
     *  Tab / Create New Class JPanel Component.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String INSERT_CLASSTYPE =                         "INSERT " +
                                                                        "INTO " +
                                                                            "Class" +
                                                                                "(Field, ClassNumber, Title, " +
                                                                                "Type, Description, Credits, Section, " +
                                                                                "Semester, Year, Participants) " +
                                                                    "VALUES " +
                                                                                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in Order to Insert a New Instructor Class Link Entry Object into the Instructor Class
     *  Link Table Present Within the Remote Database. The Instructor Class Link Table is a Linking Table Which is Used to
     *  Connect Corresponding Data Entries From Both the Instructor Account Table and Class Object Table. In short, each
     *  Entry is used to Associated a Specified Instructor's ID Field With the ID Field of the Class Object. Only one
     *  Instructor can be Teaching / Linked to a Class Object at a Given Time. The Linked Instructor can be Changed
     *  Via the Management Tab in the Instructor's Version of the Main Screen GUI Window.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String INSERT_INSTRUCTOR_CLASS_LINK =             "INSERT " +
                                                                        "INTO " +
                                                                            "InstructorClassLink" +
                                                                                "(ID, ClassID, " +
                                                                                "InstructorID) " +
                                                                    "VALUES " +
                                                                                "(?, ?, ?)";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in Order to Insert a New Student Class Link Entry Object into the Student Class
     *  Link Table Present Within the Remote Database. The Student Class Link Table is a Linking Table Which is Used to
     *  Connect Corresponding Data Entries From Both the Student Account Table and Class Object Table. In short, each
     *  Entry is used to Associated a Specified Student's ID Field With the ID Field of the Class Object. Multiple
     *  Students can be present within a single Class, while a single student may be enrolled in Multiple Classes.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String INSERT_STUDENT_CLASS_LINK =                "INSERT " +
                                                                        "INTO " +
                                                                            "StudentClassLink" +
                                                                                "(ID, ClassID, " +
                                                                                "StudentID) " +
                                                                    "VALUES " +
                                                                                "(?, ?, ?)";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ **/
    /** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ **/
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    /**                                                      DELETE STATEMENTS                                                 **/
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in Order to Delete a Pre-Existing Instructor Class Link Object Entry From the Remote Database.
     *  This Query is Typically Executed Whenever the Current Instructor Chooses to "Unlink" a Currently Linked Class Association
     *  From within the "Edit Class Information" JPanel Component Present in the Instructor's Version of the Main Screen:
     *  Management Tab.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String REMOVE_INSTRUCTOR_CLASS_LINK =             "DELETE " +
                                                                        "FROM " +
                                                                            "`InstructorClassLink` " +
                                                                    "WHERE " +
                                                                        "ClassID = ? " +
                                                                    "AND " +
                                                                        "InstructorID = ?";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in Order to Delete all Pre-Existing Attendance Records Which are Associated With the Currently
     *  Chosen Class Object. Currently, this Query Statement is invoked Whenever the Instructor Chooses to Unlink one of the
     *  Class Objects they are Assigned to. However, this Feature is SUBJECT TO CHANGE during later Versions of the Application.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String REMOVE_ATTENDANCE_RECORDS =                "DELETE " +
                                                                        "FROM " +
                                                                            "`Attendance` " +
                                                                    "WHERE " +
                                                                        "ClassID = ? " +
                                                                    "AND " +
                                                                        "InstructorID = ?";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in Order to Delete all Pre-Existing Record Dates Which are Associated With the Currently
     *  Chosen Class Object. Currently, this Query Statement is invoked Whenever the Instructor Chooses to Unlink one of the
     *  Class Objects they are Assigned to. However, this Feature is SUBJECT TO CHANGE during later Versions of the Application.
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String REMOVE_RECORD_DATES =                      "DELETE " +
                                                                        "FROM " +
                                                                            "`RecordDate` " +
                                                                    "WHERE " +
                                                                        "ClassID = ?";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /**
     *  This Database Query is Used in Order to Delete a Pre-Existing Class Object From the Class Table Present Within the Remote
     *  Database. This Method Can be invoked From Within the Instructors Main Screen: Management Tab / Delete Class JPanel. 
     * 
     *  (Note: This Query can only be Invoked by the Instructor Account Who is Currently Linked to the Class Object. They are the
     *  ONLY ACCOUNT that has the Permission to Delete a Class Object other than the Developers.)
     */
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    private final String REMOVE_CLASS =                             "DELETE " +
                                                                        "FROM " +
                                                                            "`Class` " +
                                                                    "WHERE " +
                                                                        "ID = ?";
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ **/
    /** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ **/
    
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    /**                                               END DATABASE QUERY STATEMENTS                                            **/
    /** ---------------------------------------------------------------------------------------------------------------------- **/
    
    /** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ **/
    /** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ **/
    
    /**
     *  This Method is Used in Order to Create a New
     *  Instance of the Database Manager My SQL Class.
     */
    public DatabaseManagerMySQL() {
        // Nothing to Do.
    }
    
    /**
     *  This Method is Responsible for Using the Global
     *  Connection Variable in Order to Establish a
     *  Connection to the Remote MyPHPAdmin Database.
     * 
     *  @return         The Global Connection Object
     *                  Variable After the Connection
     *                  Process has been Attempted.
     */
    public Connection getConnection() {
       
        try {
            /**
             *  Retrieve and Include the MySQL Database Driver
             *  File that is Needed to Establish a Connection
             *  to the Remote Database.
             */
            Class.forName("com.mysql.cj.jdbc.Driver");
            /**
             *  Attempt to Establish Connection With the Remote
             *  Database at the Specified URL. Include the Login
             *  Information Needed to Access the Database Website
             *  and the Driver File needed to Establish the
             *  Connection.
             */
            connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/Z3C5Rp1ZSv", "Z3C5Rp1ZSv", "hM5u298IAf");
        }
        /**
         *  Catch an SQL or Class Not Found Exception
         *  should one occur. The CNF Exception may
         *  Occur Should the JDBC Driver File Not be
         *  Found by the Program in its Specified
         *  Directory. This Can be Fixed by Placing
         *  the File or a New Version of it within the 
         *  Chosen Directory. The SQL Exception may
         *  Occur Should the Application Attempt to 
         *  Access the Remote Database Without being
         *  First Connected to the Internet. Otherwise
         *  it may Occur should the Remote Database
         *  Login Credentials have Changed.
         */
        catch (ClassNotFoundException | SQLException e) {
            /**
             *  Nothing to Do. These Errors Will be
             *  Handled by the Calling Method.
             */
	}
        
        /**
         *  Return the Connection Object Either with
         *  its New Connection Status, or the Definition
         *  of Null. Should the Connection be Null,
         *  the Calling Method will handle the
         *  Subsequent Error.
         */
	return connection;
     }
    
    /**
     *  This Method is Responsible for Closing the Current
     *  Connection to the Remote Database. 
     */
    public void closeConnection() {
        try {
            /**
             *  Attempt to Close the Current Connection
             *  to the Remote Database.
             */
            connection.close();
        }
        /**
         *  Catch an SQL Exception should one Occur.
         *  This May happen should the User Attempt
         *  to Establish a Remote Database Connection
         *  Without being Connected to the Internet or
         *  should the Login Information have changed.
         */
        catch (SQLException e) {
            /**
             *  Nothing to Do. This Error Will be
             *  Handled by the Calling Method.
             */
        }
    }
    
    /**
     *  This Method is Responsible For Using the Global
     *  Statement Variable and the Parameter String Variable
     *  in order to Execute the Specified Query Upon the
     *  Remote Database File.
     * 
     *  @param query            The String Containing the
     *                          Database Query which will
     *                          be Applied to the Statement
     *                          Variable in order to perform
     *                          a Query / Modification to 
     *                          the Remote Database's Contents.
     */
    //public void executeQuery(String query) {
        //try {
            /**
             *  Instantiate the Global Statement Object Using the
             *  already pre-establish Remote Database Connection
             *  as its Parameter. This allows the Statement Object
             *  to Know Where the Database is Located so that it
             *  can execute its parameter Query.
             */
            //this.statement = (Statement) connection.createStatement();
            /**
             *  Assign the Query String Object to the newly Directed
             *  Statement Object then proceed to Execute the Query /
             *  Modification within the Remote Database.
             */
            //statement.executeQuery(query);
        //}
        //catch (SQLException e) {
            //System.out.println("Exception: " + e.getMessage());
        //}
    //}
    
    /**
     *  This Method is Responsible for Attempting to Locate the Parameter Login
     *  Credentials within the "Student" Account Table Present in the Remote Database.
     *  Should the Specified Username and Password Combination be found within the
     *  Remote Database, then the Custom Database Return Object will return a Status
     *  Value Boolean Variable of "True". Otherwise, the Database Return Object Will
     *  Return a Status Value Boolean Variable of "False". If the Custom Object 
     *  has a Overall Result value of "False", then it will also include a String
     *  Error Type Value Which is Responsible for Letting the Calling GUI know
     *  What Type of Error Has Occurred to Cause the Process's Failure. The Process
     *  Failing can happen should an Exception Occur During the Method's Execution
     *  or the Specified Login Credentials Can't be Located.
     * 
     *  @param user                     The Unique Parameter Username String Value that the
     *                                  Current User has Inputted into the Credential's
     *                                  Screen GUI Login Form (Student Version).
     * 
     *  @param pass                     The Unique Parameter Password String Value that the
     *                                  Current User has Inputted into the Credential's
     *                                  Screen GUI Login Form (Student Version).
     * 
     *  @return                         A Custom Database Operation Result Object Which tells
     *                                  the Calling GUI Window Instance whether or not the
     *                                  Overall Process has Failed. Additionally, should the
     *                                  Process Fail, Alert the Calling GUI Window Instance
     *                                  of Why it has Failed.
     */
    public DatabaseOperationResult checkStudentLoginCredintials(String user, String pass) {
        
        /**
         *  Instantiate a New Instance of the Custom Database Operation 
         *  Result Return Object. This Object Will be Used to Provide
         *  the Calling GUI Class with the Overall Result of the
         *  Database Operation in Addition to an Explanation should
         *  the Operation Fail.
         */
        DatabaseOperationResult result = new DatabaseOperationResult(false, "", 0);
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            /**
             *  Apply the Associated Query String Object to the newly
             *  Re-Initialized Statement Object. Next, Execute the
             *  Chosen Query and Store the Returned Results in a
             *  Result Set Object Variable.
             *  
             *  Query Statement: SELECT Username, Password FROM Student
             */
            ResultSet rs = statement.executeQuery(SELECT_STUDENT_CREDINTIALS);
            
            /**
             *  Continuously Loop Until We Have Iterated Through
             *  Every Database Student Account Entry Information
             *  Present Within the Database.
             */
            while(rs.next()) {
                
                /**
                 *  Retrieve the String Value From the Remote Database 
                 *  "Username" Information Column Associated With
                 *  the Current Result Set Entry.
                 */
                String username = rs.getString("Username");
                /**
                 *  Retrieve the String Value From the Remote Database 
                 *  "Password" Information Column Associated With
                 *  the Current Result Set Entry.
                 */
                String password = rs.getString("Password");
                
                /**
                 *  If the String Retrieved From the "Username" Column
                 *  of the Current Result Set Entry matches the Parameter
                 *  "Username" String Provided by the User, and the String 
                 *  Retrieved From the "Password" Column of the Current 
                 *  Result Set Entry matches the Parameter "Password" 
                 *  String Provided by the User, then Let the Calling GUI
                 *  Class Instance Know that the Provided Login Credentials
                 *  Match What was Found in the Remote Database and Provide
                 *  Entry to the Main Screen GUI of the Application.
                 */
                if (username.equalsIgnoreCase(user) && password.equals(pass)) {
                    /**
                     *  Close the Connection Seeing as We Have
                     *  Finished Our Database Login Credentials
                     *  Search Process.
                     */
                    closeConnection();
                    
                    /**
                     *  Set the Return Object to Have a Overall Status Value of
                     *  True, and Set the Error Type String as the Message "Login
                     *  Successful". This will let the Calling GUI know that the
                     *  Provided Credentials have Been Found and Are a Match.
                     */
                    result = new DatabaseOperationResult(true, "Login Successful", 0);
                    /**
                     *  Return the Result of the
                     *  Database Query Process.
                     */
                    return result;
                }
                /**
                 *  If the Provided "Username" Parameter String Has Been Found
                 *  Within the Result Set, but the "Password" Parameter String
                 *  Doesn't Match the Value Contained in the Result Set Entry
                 *  "Password" Column, then Let the Calling GUI Class Instance
                 *  Know that the User Has Entered an Incorrect Password.
                 */
                else if (username.equalsIgnoreCase(user) && !password.equals(pass)) {
                    /**
                     *  Close the Connection Seeing as We Have
                     *  Finished Our Database Login Credentials
                     *  Search Process.
                     */
                    closeConnection();
                    
                    /**
                     *  Set the Return Object to Have a Overall Status Value of
                     *  False, and Set the Error Type String as the Message "Incorrect
                     *  Password". This will let the Calling GUI know that the
                     *  Provided Credentials have Been Found but the Password
                     *  Provided is Incorrect.
                     */
                    result = new DatabaseOperationResult(false, "Incorrect Password", 0);
                    /**
                     *  Return the Result of the
                     *  Database Query Process.
                     */
                    return result;
                }
            }
        }
        /**
         *  Catch a Null Pointer or Communications Exception should
         *  they Occur During the Process. These Types of Exceptions
         *  May Occur Due to the Presence of No Internet Connection
         *  or the Missing Parameter / Null Query Values.
         */
        catch (NullPointerException | CommunicationsException npe) {
            /**
            *  Close the Connection Seeing as We Have
            *  Finished Our Database Login Credentials
            *  Search Process.
            */
            closeConnection();
            
            /**
            *  Set the Return Object to Have a Overall Status Value of
            *  False, and Set the Error Type String as the Message "Connection
            *  Failure". This will let the Calling GUI know that the
            *  Process has Failed Due to a Missing Internet Connection.
            */
            result = new DatabaseOperationResult(false, "Connection Failure", 0);   
            /**
            *  Return the Result of the
            *  Database Query Process.
            */
            return result;
        }
        /**
         *  Catch a SQL Exception Should one Occur During the
         *  Query Process. These Types of Errors Typically
         *  Occur When the Chosen SQL Statements Are Unable
         *  to be Run through the Database, or an Error Occurs
         *  Within the Remote Database During Process Execution.
         */
        catch (SQLException e) {
            /**
            *  Close the Connection Seeing as We Have
            *  Finished Our Database Login Credentials
            *  Search Process.
            */
            closeConnection();
            
            /**
            *  Set the Return Object to Have a Overall Status Value of
            *  False, and Set the Error Type String as the Message "SQL
            *  Exception". This will let the Calling GUI know that the
            *  Process has Failed Due to an Error Occurring in the Database
            *  or Query Initialization.
            */
            result = new DatabaseOperationResult(false, "SQL Exception", 0);
            /**
            *  Return the Result of the
            *  Database Query Process.
            */
            return result;
        }
        
        /**
        *  Close the Connection Seeing as We Have
        *  Finished Our Database Login Credentials
        *  Search Process.
        */
        closeConnection();
        
        /**
        *  Set the Return Object to Have a Overall Status Value of
        *  False, and Set the Error Type String as the Message "Username
        *  Does Not Exist". This will let the Calling GUI know that the
        *  Process has Successfully Iterated through all of the Student
        *  Account Details Retrieved, but Failed in Finding a Username
        *  Matching the Parameter Username Value Passed in.
        */
        result = new DatabaseOperationResult(false, "Username Does Not Exist", 0);
        /**
        *  Return the Result of the
        *  Database Query Process.
        */
        return result;
    }
    
    /**
     *  This Method is Responsible for Attempting to Locate the Parameter Login
     *  Credentials within the "Instructor" Account Table Present in the Remote Database.
     *  Should the Specified Username and Password Combination be found within the
     *  Remote Database, then the Custom Database Return Object will return a Status
     *  Value Boolean Variable of "True". Otherwise, the Database Return Object Will
     *  Return a Status Value Boolean Variable of "False". If the Custom Object 
     *  has a Overall Result value of "False", then it will also include a String
     *  Error Type Value Which is Responsible for Letting the Calling GUI know
     *  What Type of Error Has Occurred to Cause the Process's Failure. The Process
     *  Failing can happen should an Exception Occur During the Method's Execution
     *  or the Specified Login Credentials Can't be Located.
     * 
     *  @param user                     The Unique Parameter Username String Value that the
     *                                  Current User has Inputted into the Credential's
     *                                  Screen GUI Login Form (Instructor Version).
     * 
     *  @param pass                     The Unique Parameter Password String Value that the
     *                                  Current User has Inputted into the Credential's
     *                                  Screen GUI Login Form (Instructor Version).
     * 
     *  @return                         A Custom Database Operation Result Object Which tells
     *                                  the Calling GUI Window Instance whether or not the
     *                                  Overall Process has Failed. Additionally, should the
     *                                  Process Fail, Alert the Calling GUI Window Instance
     *                                  of Why it has Failed.
     */
    public DatabaseOperationResult checkInstructorLoginCredintials(String user, String pass) {
        
        /**
         *  Instantiate a New Instance of the Custom Database Operation 
         *  Result Return Object. This Object Will be Used to Provide
         *  the Calling GUI Class with the Overall Result of the
         *  Database Operation in Addition to an Explanation should
         *  the Operation Fail.
         */
        DatabaseOperationResult result = new DatabaseOperationResult(false, "", 0);
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            /**
             *  Apply the Associated Query String Object to the newly
             *  Re-Initialized Statement Object. Next, Execute the
             *  Chosen Query and Store the Returned Results in a
             *  Result Set Object Variable.
             *  
             *  Query Statement: SELECT Username, Password FROM Instructor
             */
            ResultSet rs = statement.executeQuery(SELECT_INSTRUCTOR_CREDINTIALS);
            
            /**
             *  Continuously Loop Until We Have Iterated Through
             *  Every Database Instructor Account Entry Information
             *  Present Within the Database.
             */
            while(rs.next()) {
                
                System.out.println("Getting HEre.");
                
                /**
                 *  Retrieve the String Value From the Remote Database 
                 *  "Username" Information Column Associated With
                 *  the Current Result Set Entry.
                 */
                String username = rs.getString("Username");
                /**
                 *  Retrieve the String Value From the Remote Database 
                 *  "Password" Information Column Associated With
                 *  the Current Result Set Entry.
                 */
                String password = rs.getString("Password");
                
                /**
                 *  If the String Retrieved From the "Username" Column
                 *  of the Current Result Set Entry matches the Parameter
                 *  "Username" String Provided by the User, and the String 
                 *  Retrieved From the "Password" Column of the Current 
                 *  Result Set Entry matches the Parameter "Password" 
                 *  String Provided by the User, then Let the Calling GUI
                 *  Class Instance Know that the Provided Login Credentials
                 *  Match What was Found in the Remote Database and Provide
                 *  Entry to the Main Screen GUI of the Application.
                 */
                if (username.equalsIgnoreCase(user) && password.equals(pass)) {
                    /**
                     *  Close the Connection Seeing as We Have
                     *  Finished Our Database Login Credentials
                     *  Search Process.
                     */
                    closeConnection();
                    
                    /**
                     *  Set the Return Object to Have a Overall Status Value of
                     *  True, and Set the Error Type String as the Message "Login
                     *  Successful". This will let the Calling GUI know that the
                     *  Provided Credentials have Been Found and Are a Match.
                     */
                    result = new DatabaseOperationResult(true, "Login Successful", 0);
                    /**
                     *  Return the Result of the
                     *  Database Query Process.
                     */
                    return result;
                }
                /**
                 *  If the Provided "Username" Parameter String Has Been Found
                 *  Within the Result Set, but the "Password" Parameter String
                 *  Doesn't Match the Value Contained in the Result Set Entry
                 *  "Password" Column, then Let the Calling GUI Class Instance
                 *  Know that the User Has Entered an Incorrect Password.
                 */
                else if (username.equalsIgnoreCase(user) && !password.equals(pass)) {
                    /**
                     *  Close the Connection Seeing as We Have
                     *  Finished Our Database Login Credentials
                     *  Search Process.
                     */
                    closeConnection();
                    
                    /**
                     *  Set the Return Object to Have a Overall Status Value of
                     *  False, and Set the Error Type String as the Message "Login
                     *  Successful". This will let the Calling GUI know that the
                     *  Provided Credentials have Been Found and Are a Match.
                     */
                    result = new DatabaseOperationResult(false, "Incorrect Password", 0);
                    /**
                     *  Return the Result of the
                     *  Database Query Process.
                     */
                    return result;
                }
            }
        }
        /**
         *  Catch a Null Pointer or Communications Exception should
         *  they Occur During the Process. These Types of Exceptions
         *  May Occur Due to the Presence of No Internet Connection
         *  or the Missing Parameter / Null Query Values.
         */
        catch (NullPointerException | CommunicationsException npe) {
            /**
            *  Close the Connection Seeing as We Have
            *  Finished Our Database Login Credentials
            *  Search Process.
            */
            closeConnection();
           
            /**
            *  Set the Return Object to Have a Overall Status Value of
            *  False, and Set the Error Type String as the Message "Connection
            *  Failure". This will let the Calling GUI know that the
            *  Process has Failed Due to a Missing Internet Connection.
            */
            result = new DatabaseOperationResult(false, "Connection Failure", 0);   
            /**
            *  Return the Result of the
            *  Database Query Process.
            */
            return result;
        }
        /**
         *  Catch a SQL Exception Should one Occur During the
         *  Query Process. These Types of Errors Typically
         *  Occur When the Chosen SQL Statements Are Unable
         *  to be Run through the Database, or an Error Occurs
         *  Within the Remote Database During Process Execution.
         */
        catch (SQLException e) {
            /**
            *  Close the Connection Seeing as We Have
            *  Finished Our Database Login Credentials
            *  Search Process.
            */
            closeConnection();
            
            /**
            *  Set the Return Object to Have a Overall Status Value of
            *  False, and Set the Error Type String as the Message "SQL
            *  Exception". This will let the Calling GUI know that the
            *  Process has Failed Due to an Error Occurring in the Database
            *  or Query Initialization.
            */
            result = new DatabaseOperationResult(false, "SQL Exception", 0);
            /**
            *  Return the Result of the
            *  Database Query Process.
            */
            return result;
        }
        
        /**
        *  Close the Connection Seeing as We Have
        *  Finished Our Database Login Credentials
        *  Search Process.
        */
        closeConnection();
        
        /**
        *  Set the Return Object to Have a Overall Status Value of
        *  False, and Set the Error Type String as the Message "Username
        *  Does Not Exist". This will let the Calling GUI know that the
        *  Process has Successfully Iterated through all of the Instructor
        *  Account Details Retrieved, but Failed in Finding a Username
        *  Matching the Parameter Username Value Passed in.
        */
        result = new DatabaseOperationResult(false, "Username Does Not Exist", 0);
        /**
        *  Return the Result of the
        *  Database Query Process.
        */
        return result;
    }
    
    /**
     *  This Method is Used in Order to Retrieve the Miscellaneous Information 
     *  Associated with the Current Student's Account From the Remote Database.
     *  This Method will only be Invoked Once the Application has Determined
     *  that the Provided Student Username and Password Credentials are a
     *  Match to the Values Found Within the Database. This Method will Retrieve
     *  the Specific Account Information that the User May have Customized
     *  During Previous Login Processes. 
     *  
     *  @param user                     The Unique Username String Object Associated 
     *                                  With the Current Student's Account.
     * 
     *  @return                         A Custom Student Object Which is Capable of
     *                                  Holding all of the Information Present Within
     *                                  the User's Associated Account. If the Student
     *                                  Object being returned is Null, then the Calling
     *                                  GUI Class will inform the User of an Error
     *                                  Occurring.
     */
    public Student getStudentInformation(String user) {
        
        /**
         *  Initialize a Prepared Statement Object Variable Which Will
         *  Be Responsible for Injecting the Specified Parameter Information
         *  into the Global Query String. We Use a Prepared Statement
         *  Here instead of a Regular Statement Object Seeing as we
         *  are Trying to Retrieve Information that Pertains to a Specific
         *  Piece of Information Passed by the Current User.
         */
        PreparedStatement ps = null;
        
        /**
         *  Initialize a Custom Student Object Which will Act
         *  as our Return Variable for the Method. This Object
         *  will either be returned as a Null Value or it will
         *  be returned containing all of the Information
         *  Pertaining to the Student Account that the Method
         *  is Retrieving the Miscellaneous Information From.
         */
        Student student = null;
            
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();

        try {
            
            /**
             *  Set the Prepared Statement Object Value as the Global Statement
             *  String. This Will be the Query that the Database will Execute.
             * 
             *  Query: SELECT * FROM Student WHERE Username = ?
             */
            ps = this.connection.prepareStatement(SELECT_STUDENT_INFORMATION_BY_USERNAME);
            /**
             *  Set the Wildcard Parameter (?) Within the Global Statement String
             *  as the Parameter Username String Passed in By the Current User.
             */
            ps.setString(1, user);
            
            /**
             *  Execute The Query Within the Remote Database and Save The
             *  Returned Row Entries Within a new Result Set Object.
             */
            ResultSet rs = ps.executeQuery();
            
            /**
             *  Continuously Loop Until We Have Iterated Through
             *  Every Student Account Information Entry
             *  Present Within the Result Set Object. Seeing
             *  as we are Expecting to Pull Information Associated
             *  with a Single Student Account, this Loop Will
             *  Only Iterate a Single Time.
             */
            while(rs.next()) {
                
                /**
                 *  Save the Integer "ID" Column Value Associated With 
                 *  the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                int id = rs.getInt("ID");
                /**
                 *  Save the "Student Identification Number" Integer
                 *  Column Value Associated With the Current Result Set
                 *  Entry inside of a Temporary Variable.
                 */
                int studentNumber = rs.getInt("StudentID");
                /**
                 *  Save the String "First Name" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String firstname = rs.getString("FirstName");
                /**
                 *  Save the String "Last Name" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String lastname = rs.getString("LastName");
                /**
                 *  Save the String "Username" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String username = rs.getString("Username");
                /**
                 *  Save the String "Password" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String password = rs.getString("Password");
                /**
                 *  Save the String "Email Address" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String email = rs.getString("Email");
                /**
                 *  Save the String "Display Name" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String displayname = rs.getString("DisplayName");
                /**
                 *  Save the String "Phone Number" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String phone = rs.getString("Phone");
                /**
                 *  Save the String "Physical Address" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String address = rs.getString("Address");
                /**
                 *  Save the String "Biography" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String biography = rs.getString("Biography");
                /**
                 *  Save the String Object Containing the Directory Path
                 *  of the Profile Image Large Value that is Associated
                 *  With the Current Result Set Entry.
                 */
                String imageLarge = rs.getString("ImageLarge");
                /**
                 *  Save the String Object Containing the Directory Path
                 *  of the Profile Image Medium Value that is Associated
                 *  With the Current Result Set Entry.
                 */
                String imageMedium = rs.getString("ImageMedium");
                /**
                 *  Save the String Object Containing the Directory Path
                 *  of the Profile Image Small Value that is Associated
                 *  With the Current Result Set Entry.
                 */
                String imageSmall = rs.getString("ImageSmall");
                /**
                 *  Save the String Object Containing the Directory Path
                 *  of the Profile Image Extra Small Value that is Associated
                 *  With the Current Result Set Entry.
                 */
                String imageXS = rs.getString("ImageXS");
                
                /**
                 *  Use all of the Previously Retrieved Account Information
                 *  Values in Order to Create a Initialize the Custom Student 
                 *  Object Return Value.
                 */
                student = new Student(id, studentNumber, firstname, lastname, email, username, password, displayname, phone, address, biography, imageLarge, imageMedium, imageSmall, imageXS);
                
                /**
                *  Close the Connection Seeing as We Have
                *  Finished Our Database Login Credentials
                *  Search Process.
                */
                closeConnection();
                
                /**
                 *  Return the Custom Student Object with
                 *  all of the Retrieved Data Values to the
                 *  Calling GUI Class Instance.
                 */
                return student;
            }
        }
        /**
         *  Catch a SQL Exception Should one Occur During the
         *  Query Process. These Types of Errors Typically
         *  Occur When the Chosen SQL Statements Are Unable
         *  to be Run through the Database, or an Error Occurs
         *  Within the Remote Database During Process Execution.
         */
        catch (SQLException e) {
            /**
            *  Close the Connection Seeing as We Have
            *  Finished Our Database Login Credentials
            *  Search Process.
            */
            closeConnection();
            /**
             *  Return the Null Student Value to the
             *  Calling GUI Class Instance.
             */
            return student;
        }
        /**
         *  Attempt to Close the Prepared Statement
         *  Object once we have Finished Our SQL
         *  Operation.
         */
        finally {
            try {
                /**
                 *  If the Prepared Statement Object
                 *  Isn't Already Null Once the Operation
                 *  Has Finished, then close the Object.
                 */
                if (ps != null) {
                    // Close Prepared Statement.
                    ps.close();
                }
            } 
            /**
            *  Catch a SQL Exception Should one Occur During the
            *  Query Process. These Types of Errors Typically
            *  Occur When the Chosen SQL Statements Are Unable
            *  to be Run through the Database, or an Error Occurs
            *  Within the Remote Database During Process Execution.
            */
            catch (SQLException e) {
                /**
                 *  If the Operation hasn't Already Been Performed
                 *  Successfully and the Student's Account Information
                 *  hasn't been found, then return the Null Student
                 *  Object Value.
                 */
                if (!student.equals(null)) {
                    /**
                     *  Return the Null Student Object
                     *  Value.
                     */
                    return student;
                }
            }
        }
        
        /**
        *  Close the Connection Seeing as We Have
        *  Finished Our Database Login Credentials
        *  Search Process.
        */
        closeConnection();
        /**
        *  Return the Null Student Value to the
        *  Calling GUI Class Instance.
        */
        return student;
    }
    
    /**
     *  This Method is Used in Order to Retrieve the Miscellaneous Information 
     *  Associated with the Current Instructor's Account From the Remote Database.
     *  This Method will only be Invoked Once the Application has Determined
     *  that the Provided Instructor Username and Password Credentials are a
     *  Match to the Values Found Within the Database. This Method will Retrieve
     *  the Specific Account Information that the User May have Customized
     *  During Previous Login Processes. 
     *  
     *  @param user                     The Unique Username String Object Associated 
     *                                  With the Current Instructor's Account.
     * 
     *  @return                         A Custom Instructor Object Which is Capable of
     *                                  Holding all of the Information Present Within
     *                                  the User's Associated Account. If the Instructor
     *                                  Object being returned is Null, then the Calling
     *                                  GUI Class will inform the User of an Error
     *                                  Occurring.
     */
    public Instructor getInstructorInformation(String user) {
        
        /**
         *  Initialize a Prepared Statement Object Variable Which Will
         *  Be Responsible for Injecting the Specified Parameter Information
         *  into the Global Query String. We Use a Prepared Statement
         *  Here instead of a Regular Statement Object Seeing as we
         *  are Trying to Retrieve Information that Pertains to a Specific
         *  Piece of Information Passed by the Current User.
         */
        PreparedStatement ps = null;
        
        /**
         *  Initialize a Custom Instructor Object Which will Act
         *  as our Return Variable for the Method. This Object
         *  will either be returned as a Null Value or it will
         *  be returned containing all of the Information
         *  Pertaining to the Instructor Account that the Method
         *  is Retrieving the Miscellaneous Information From.
         */
        Instructor instructor = null;
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();

        try {
            
            /**
             *  Set the Prepared Statement Object Value as the Global Statement
             *  String. This Will be the Query that the Database will Execute.
             * 
             *  Query: SELECT * FROM Instructor WHERE Username = ?
             */
            ps = this.connection.prepareStatement(SELECT_INSTRUCTOR_INFORMATION_BY_USERNAME);
            /**
             *  Set the Wildcard Parameter (?) Within the Global Statement String
             *  as the Parameter Username String Passed in By the Current User.
             */
            ps.setString(1, user);
            
            /**
             *  Execute The Query Within the Remote Database and Save The
             *  Returned Row Entries Within a new Result Set Object.
             */
            ResultSet rs = ps.executeQuery();
            
            /**
             *  Continuously Loop Until We Have Iterated Through
             *  Every Instructor Account Information Entry
             *  Present Within the Result Set Object. Seeing
             *  as we are Expecting to Pull Information Associated
             *  with a Single Instructor Account, this Loop Will
             *  Only Iterate a Single Time.
             */
            while(rs.next()) {
                
                /**
                 *  Save the Integer "ID" Column Value Associated With 
                 *  the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                int id = rs.getInt("ID");
                /**
                 *  Save the String "First Name" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String firstname = rs.getString("FirstName");
                /**
                 *  Save the String "Last Name" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String lastname = rs.getString("LastName");
                /**
                 *  Save the String "Username" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String username = rs.getString("Username");
                /**
                 *  Save the String "Password" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String password = rs.getString("Password");
                /**
                 *  Save the String "Email Address" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String email = rs.getString("Email");
                /**
                 *  Save the String "Display Name" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String displayname = rs.getString("DisplayName");
                /**
                 *  Save the String "Phone Number" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String phone = rs.getString("Phone");
                /**
                 *  Save the String "Physical Address" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String address = rs.getString("Address");
                /**
                 *  Save the String "Biography" Column Value Associated 
                 *  With the Current Result Set Entry Inside of
                 *  a Temporary Variable.
                 */
                String biography = rs.getString("Biography");
                /**
                 *  Save the String Object Containing the Directory Path
                 *  of the Profile Image Large Value that is Associated
                 *  With the Current Result Set Entry.
                 */
                String imageLarge = rs.getString("ImageLarge");
                /**
                 *  Save the String Object Containing the Directory Path
                 *  of the Profile Image Medium Value that is Associated
                 *  With the Current Result Set Entry.
                 */
                String imageMedium = rs.getString("ImageMedium");
                /**
                 *  Save the String Object Containing the Directory Path
                 *  of the Profile Image Small Value that is Associated
                 *  With the Current Result Set Entry.
                 */
                String imageSmall = rs.getString("ImageSmall");
                /**
                 *  Save the String Object Containing the Directory Path
                 *  of the Profile Image Extra Small Value that is Associated
                 *  With the Current Result Set Entry.
                 */
                String imageXS = rs.getString("ImageXS");
                
                /**
                 *  Use all of the Previously Retrieved Account Information
                 *  Values in Order to Create and Initialize the Custom Instructor 
                 *  Object Return Value.
                 */
                instructor = new Instructor(id, firstname, lastname, username, password, email, displayname, phone, address, biography, imageLarge, imageMedium, imageSmall, imageXS);
                
                /**
                *  Close the Connection Seeing as We Have
                *  Finished Our Database Login Credentials
                *  Search Process.
                */
                closeConnection();
                
                /**
                *  Return the Instructor Object Value
                *  Containing all of the Previously
                *  Retrieved Column Values to the
                *  Calling GUI Class Instance.
                */
                return instructor;
            }
        }
        /**
         *  Catch a SQL Exception Should one Occur During the
         *  Query Process. These Types of Errors Typically
         *  Occur When the Chosen SQL Statements Are Unable
         *  to be Run through the Database, or an Error Occurs
         *  Within the Remote Database During Process Execution.
         */
        catch (SQLException e) {
            /**
            *  Close the Connection Seeing as We Have
            *  Finished Our Database Login Credentials
            *  Search Process.
            */
            closeConnection();
            /**
             *  Return the Null Student Value to the
             *  Calling GUI Class Instance.
             */
            return instructor;
        }
        /**
         *  Attempt to Close the Prepared Statement
         *  Object once we have Finished Our SQL
         *  Operation.
         */
        finally {
            try {
                /**
                 *  If the Prepared Statement Object
                 *  Isn't Already Null Once the Operation
                 *  Has Finished, then close the Object.
                 */
                if (ps != null) {
                    // Close Prepared Statement.
                    ps.close();
                }
            } 
            /**
            *  Catch a SQL Exception Should one Occur During the
            *  Query Process. These Types of Errors Typically
            *  Occur When the Chosen SQL Statements Are Unable
            *  to be Run through the Database, or an Error Occurs
            *  Within the Remote Database During Process Execution.
            */
            catch (SQLException e) {
                /**
                 *  If the Operation hasn't Already Been Performed
                 *  Successfully and the Instructor's Account Information
                 *  hasn't been found, then return the Null Instructor
                 *  Object Value.
                 */
                if (!instructor.equals(null)) {
                    /**
                     *  Return the Null Instructor Object
                     *  Value.
                     */
                    return instructor;
                }
            }
        }
        
        /**
        *  Close the Connection Seeing as We Have
        *  Finished Our Database Login Credentials
        *  Search Process.
        */
        closeConnection();
        /**
        *  Return the Null Student Value to the
        *  Calling GUI Class Instance.
        */
        return instructor;
    }
    
    /**
     *  This Method is Used in Order to Update the Miscellaneous Information
     *  of a Class Entry that is Associated to the Current User's Instructor
     *  Account. This Method Can only be Invoked From Within the Instructor's
     *  Main Screen: Management Tab / Update Class Information JPanel Component.
     * 
     *  @param type                         The Class Object Which Contains
     *                                      all of the New and Old Information
     *                                      that the User wants to Associate
     *                                      with the Class Entry. Under no
     *                                      Circumstances can the Unique ID
     *                                      Row Entry Integer Number of any
     *                                      Given Class be changed.
     *  
     *  @return                             A Custom Database Operation Result
     *                                      Object which is capable of telling
     *                                      the Calling GUI Class How the Overall
     *                                      Result of the Operation Fared, in
     *                                      Addition to the Error Type should
     *                                      one have occurred during the process.
     */
    public DatabaseOperationResult updateClassInformation(ClassType type) {
        
        /**
         *  Initialize a Prepared Statement Object Variable Which Will
         *  Be Responsible for Injecting the Specified Parameter Information
         *  into the Global Query String. We Use a Prepared Statement
         *  Here instead of a Regular Statement Object Seeing as we
         *  are Trying to Retrieve Information that Pertains to a Specific
         *  Piece of Information Passed by the Current User.
         */
        PreparedStatement ps = null;
        
        /**
         *  Instantiate a New Instance of the Custom Database Operation 
         *  Result Return Object. This Object Will be Used to Provide
         *  the Calling GUI Class with the Overall Result of the
         *  Database Operation in Addition to an Explanation should
         *  the Operation Fail.
         */
        DatabaseOperationResult result = new DatabaseOperationResult(false, "", 0);
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            
            /**
             *  Initialize the Prepared Statement Variable Using the
             *  Global Query Statement Shown Below. We Use a Prepared
             *  Statement in this Method instead of a Regular Statement
             *  Seeing as we plan to Inject User Specified Data into
             *  the Global Query Statement.
             */
            ps = this.connection.prepareStatement(UPDATE_CLASSTYPE);
            /**
             *  Set the First Information Injection Index as the
             *  Integer "ID" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setInt(1, type.getClassID());
            /**
             *  Set the Second Information Injection Index as the
             *  String "Field" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(2, type.getClassField());
            /**
             *  Set the Third Information Injection Index as the
             *  Integer "Class Number" Column Value Associated with 
             *  the Parameter Class Object.
             */
            ps.setInt(3, type.getClassNumber());
            /**
             *  Set the Forth Information Injection Index as the
             *  String "Title" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(4, type.getClassTitle());
            /**
             *  Set the Fifth Information Injection Index as the
             *  String "Structure Type" Column Value Associated 
             *  with the Parameter Class Object.
             */
            ps.setString(5, type.getClassStructureType());
            /**
             *  Set the Sixth Information Injection Index as the
             *  String "Description" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(6, type.getClassDescription());
            /**
             *  Set the Seventh Information Injection Index as the
             *  String "Credit Hours" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(7, type.getClassCreditHours());
            /**
             *  Set the Eighth Information Injection Index as the
             *  String "Section" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(8, type.getClassSection());
            /**
             *  Set the Ninth Information Injection Index as the
             *  String "Semester" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(9, type.getClassSemester());
            /**
             *  Set the Tenth Information Injection Index as the
             *  String "Year" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setInt(10, type.getClassYear());
            /**
             *  Set the Eleventh Information Injection Index as the
             *  Integer "Participants" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setInt(11, type.getClassParticipants());
            /**
             *  Set the Twelfth Information Injection Index as the
             *  Integer "ID" Column Value Associated with the Parameter
             *  Class Object. This Value is the Value within the
             *  Statement String that the "WHERE" Clause uses in
             *  order to Find the Exact Class Object that the
             *  User is Specifying.
             */
            ps.setInt(12, type.getClassID());

            /**
             *  Once we have Finished Injecting all the Required
             *  Information into our Prepared Statement, Execute the
             *  Update Query Within the Database.
             */
            ps.executeUpdate();
            
            /**
             *  Set the Overall Status of Custom
             *  Return Object to the Value of True.
             *  This Lets the GUI Window Know that
             *  the Operation has Ended in Success.
             */
            result.setResult(true);
            /**
             *  Let the Calling GUI Class know that the
             *  Specified Class's Information has been 
             *  Successfully Updated.
             */
            result.setType("Class Successfully Updated");
        } 
        /**
         *  Catch a SQL Exception Should one Occur During the
         *  Query Process. These Types of Errors Typically
         *  Occur When the Chosen SQL Statements Are Unable
         *  to be Run through the Database, or an Error Occurs
         *  Within the Remote Database During Process Execution.
         */
        catch (SQLException e) {
            /**
            *  Close the Connection Seeing as We Have
            *  Finished Our Database Login Credentials
            *  Search Process.
            */
            closeConnection();
            /**
             *  Set the Overall Status of Custom
             *  Return Object to the Value of False.
             *  This Lets the GUI Window Know that
             *  the Operation has Ended in Failure.
             */
            result.setResult(false);
            /**
             *  Let the Calling GUI Class know what
             *  Type of Error Has Occurred so that it
             *  can Alert the User of Why the Process
             *  has Failed.
             */
            result.setType("SQL Exception");
        }
        /**
         *  Attempt to Close the Prepared Statement
         *  Object once we have Finished Our SQL
         *  Operation.
         */
        finally {
            try {
                /**
                 *  If the Prepared Statement Object
                 *  Isn't Already Null Once the Operation
                 *  Has Finished, then close the Object.
                 */
                if (ps != null) {
                    // Close Prepared Statement.
                    ps.close();
                }
            } 
            /**
            *  Catch a SQL Exception Should one Occur During the
            *  Query Process. These Types of Errors Typically
            *  Occur When the Chosen SQL Statements Are Unable
            *  to be Run through the Database, or an Error Occurs
            *  Within the Remote Database During Process Execution.
            */
            catch (SQLException e) {
                /**
                 *  If the Operation hasn't Already Been Performed
                 *  Successfully and the Overall Result Status hasn't
                 *  been set to true, then set the Overall Result
                 *  as the Value of False, and set the Error Type.
                 */
                if (!result.getResult() == true) {
                    /**
                    *  Set the Overall Status of Custom
                    *  Return Object to the Value of False.
                    *  This Lets the GUI Window Know that
                    *  the Operation has Ended in Failure.
                    */
                   result.setResult(false);
                   /**
                    *  Let the Calling GUI Class know what
                    *  Type of Error Has Occurred so that it
                    *  can Alert the User of Why the Process
                    *  has Failed.
                    */
                   result.setType("SQL Exception");
                }
            }
        }
        
        /**
         *  Return the Overall Result of the
         *  Class Update Query.
         */
        return result;
    }
    
    /**
     *  This Method is Used in Order to Update the Miscellaneous Information
     *  Contained Within an Instructor's Account that is Present in the Remote
     *  Database. This Method is Typically Invoked Whenever a User Attempts to
     *  Update their Account Information Via the Main Screen / Edit Profile Tab.
     * 
     *  @param instructor                   The Custom Instructor Object Which
     *                                      is Responsible for Holding Both the
     *                                      Required and Miscellaneous Profile
     *                                      Information Associated With the 
     *                                      Instructor Account.
     *  
     *  @return                             A Custom Database Operation Result
     *                                      Object which is capable of telling
     *                                      the Calling GUI Class How the Overall
     *                                      Result of the Operation Fared, in
     *                                      Addition to the Error Type should
     *                                      one have occurred during the process.
     */
    public DatabaseOperationResult updateInstructorInformation(Instructor instructor) {
        
        /**
         *  Initialize a Prepared Statement Object Variable Which Will
         *  Be Responsible for Injecting the Specified Parameter Information
         *  into the Global Query String. We Use a Prepared Statement
         *  Here instead of a Regular Statement Object Seeing as we
         *  are Trying to Retrieve Information that Pertains to a Specific
         *  Piece of Information Passed by the Current User.
         */
        PreparedStatement ps = null;
        
        /**
         *  Instantiate a New Instance of the Custom Database Operation 
         *  Result Return Object. This Object Will be Used to Provide
         *  the Calling GUI Class with the Overall Result of the
         *  Database Operation in Addition to an Explanation should
         *  the Operation Fail.
         */
        DatabaseOperationResult result = new DatabaseOperationResult(false, "", 0);
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            
            /**
             *  Initiate the Prepared Statement Object Using the
             *  Update Instructor Global String. We Use a Prepared
             *  Statement in this Method seeing as we are Planning
             *  to Inject Data into the Query String.
             */
            ps = this.connection.prepareStatement(UPDATE_INSTRUCTOR);
            /**
             *  Set the First Information Injection Index as the
             *  Integer "ID" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setInt(1, instructor.getInstructorID());
            /**
             *  Set the Second Information Injection Index as the
             *  String "First Name" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(2, instructor.getInstructorFirstName());
            /**
             *  Set the Third Information Injection Index as the
             *  String "Last Name" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(3, instructor.getInstructorLastName());
            /**
             *  Set the Forth Information Injection Index as the
             *  String "Username" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(4, instructor.getInstructorUsername());
            /**
             *  Set the Fifth Information Injection Index as the
             *  String "Password" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(5, instructor.getInstructorPassword());
            /**
             *  Set the Sixth Information Injection Index as the
             *  String "Email Address" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(6, instructor.getInstructorEmail());
            /**
             *  Set the Seventh Information Injection Index as the
             *  String "Display Name" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(7, instructor.getInstructorDisplayName());
            /**
             *  Set the Eighth Information Injection Index as the
             *  String "Phone Number" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(8, instructor.getInstructorPhone());
            /**
             *  Set the Ninth Information Injection Index as the
             *  String "Physical Address" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(9, instructor.getInstructorAddress());
            /**
             *  Set the Tenth Information Injection Index as the
             *  String "Biography" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(10, instructor.getInstructorBiography());
            /**
             *  Set the Eleventh Information Injection Index as the
             *  String Path For the Instructor Account's Specified
             *  Profile Image Large Value.
             */
            ps.setString(11, instructor.getInstructorProfileImageLarge());
            /**
             *  Set the Twelfth Information Injection Index as the
             *  String Path For the Instructor Account's Specified
             *  Profile Image Medium Value.
             */
            ps.setString(12, instructor.getInstructorProfileImageMedium());
            /**
             *  Set the Thirteenth Information Injection Index as the
             *  String Path For the Instructor Account's Specified
             *  Profile Image Small Value.
             */
            ps.setString(13, instructor.getInstructorProfileImageSmall());
            /**
             *  Set the Fourteenth Information Injection Index as the
             *  String Path For the Instructor Account's Specified
             *  Profile Image Extra Small Value.
             */
            ps.setString(14, instructor.getInstructorProfileImageXS());
            /**
             *  Set the Fifteenth Information Injection Index as the
             *  Integer ID Column Value. This Injection Index is the
             *  Variable which the Query Statement will use to Find
             *  the Account that the User Wishes to Update the Information
             *  for.
             */
            ps.setInt(15, instructor.getInstructorID());
            /**
             *  Invoke the Update Process Within the Remote Database.
             */
            ps.executeUpdate();
            
            /**
             *  Set the Overall Status of Custom
             *  Return Object to the Value of True.
             *  This Lets the GUI Window Know that
             *  the Operation has Ended in Success.
             */
            result.setResult(true);
            /**
             *  Let the Calling GUI Class know that the
             *  Specified Instructor's Information has been 
             *  Successfully Updated.
             */
            result.setType("Instructor Account Successfully Updated");
        } 
        /**
         *  Catch a SQL Exception Should one Occur During the
         *  Query Process. These Types of Errors Typically
         *  Occur When the Chosen SQL Statements Are Unable
         *  to be Run through the Database, or an Error Occurs
         *  Within the Remote Database During Process Execution.
         */
        catch (SQLException e) {
            /**
            *  Close the Connection Seeing as We Have
            *  Finished Our Database Query Process.
            */
            closeConnection();
            /**
             *  Set the Overall Status of Custom
             *  Return Object to the Value of False.
             *  This Lets the GUI Window Know that
             *  the Operation has Ended in Failure.
             */
            result.setResult(false);
            /**
             *  Let the Calling GUI Class know what
             *  Type of Error Has Occurred so that it
             *  can Alert the User of Why the Process
             *  has Failed.
             */
            result.setType("SQL Exception");
        }
        /**
         *  Attempt to Close the Prepared Statement
         *  Object once we have Finished Our SQL
         *  Operation.
         */
        finally {
            try {
                /**
                 *  If the Prepared Statement Object
                 *  Isn't Already Null Once the Operation
                 *  Has Finished, then close the Object.
                 */
                if (ps != null) {
                    // Close Prepared Statement.
                    ps.close();
                }
            } 
            /**
            *  Catch a SQL Exception Should one Occur During the
            *  Query Process. These Types of Errors Typically
            *  Occur When the Chosen SQL Statements Are Unable
            *  to be Run through the Database, or an Error Occurs
            *  Within the Remote Database During Process Execution.
            */
            catch (SQLException e) {
                /**
                 *  If the Operation hasn't Already Been Performed
                 *  Successfully and the Overall Result Status hasn't
                 *  been set to true, then set the Overall Result
                 *  as the Value of False, and set the Error Type.
                 */
                if (!result.getResult() == true) {
                    /**
                    *  Set the Overall Status of Custom
                    *  Return Object to the Value of False.
                    *  This Lets the GUI Window Know that
                    *  the Operation has Ended in Failure.
                    */
                   result.setResult(false);
                   /**
                    *  Let the Calling GUI Class know what
                    *  Type of Error Has Occurred so that it
                    *  can Alert the User of Why the Process
                    *  has Failed.
                    */
                   result.setType("SQL Exception");
                }
            }
        }
        
        /**
         *  Return the Overall Result of the
         *  Instructor Update Query.
         */
        return result;
    }
    
    /**
     *  This Method is Used in Order to Update the Miscellaneous Information
     *  Contained Within an Student's Account that is Present in the Remote
     *  Database. This Method is Typically Invoked Whenever a User Attempts to
     *  Update their Account Information Via the Main Screen / Edit Profile Tab.
     * 
     *  @param student                      The Custom Student Object Which
     *                                      is Responsible for Holding Both the
     *                                      Required and Miscellaneous Profile
     *                                      Information Associated With the 
     *                                      Student Account.
     *  
     *  @return                             A Custom Database Operation Result
     *                                      Object which is capable of telling
     *                                      the Calling GUI Class How the Overall
     *                                      Result of the Operation Fared, in
     *                                      Addition to the Error Type should
     *                                      one have occurred during the process.
     */
    public DatabaseOperationResult updateStudentInformation(Student student) {
        
        /**
         *  Initialize a Prepared Statement Object Variable Which Will
         *  Be Responsible for Injecting the Specified Parameter Information
         *  into the Global Query String. We Use a Prepared Statement
         *  Here instead of a Regular Statement Object Seeing as we
         *  are Trying to Retrieve Information that Pertains to a Specific
         *  Piece of Information Passed by the Current User.
         */
        PreparedStatement ps = null;
        
        /**
         *  Instantiate a New Instance of the Custom Database Operation 
         *  Result Return Object. This Object Will be Used to Provide
         *  the Calling GUI Class with the Overall Result of the
         *  Database Operation in Addition to an Explanation should
         *  the Operation Fail.
         */
        DatabaseOperationResult result = new DatabaseOperationResult(false, "", 0);
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            
            /**
             *  Initialize the Prepared Statement Object as the Global
             *  Query String Update Student. We Use a Prepared Statement
             *  Object in this Method seeing as we need to Inject Information
             *  Within the Query String before Execution.
             */
            ps = this.connection.prepareStatement(UPDATE_STUDENT);
            /**
             *  Set the First Information Injection Index as the
             *  Integer "ID" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setInt(1, student.getRowID());
            /**
             *  Set the Second Information Injection Index as the
             *  Integer "Student Identification Number" Column Value 
             *  Associated with the Parameter Class Object.
             */
            ps.setInt(2, student.getStudentIdentificationNumber());
            /**
             *  Set the Third Information Injection Index as the
             *  String "First Name" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(3, student.getStudentFirstName());
            /**
             *  Set the Forth Information Injection Index as the
             *  String "Last Name" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(4, student.getStudentLastName());
            /**
             *  Set the Fifth Information Injection Index as the
             *  String "Username" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(5, student.getStudentUsername());
            /**
             *  Set the Sixth Information Injection Index as the
             *  String "Password" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(6, student.getStudentPassword());
            /**
             *  Set the Seventh Information Injection Index as the
             *  String "Email" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(7, student.getStudentEmail());
            /**
             *  Set the Eighth Information Injection Index as the
             *  String "Display Name" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(8, student.getStudentDisplayName());
            /**
             *  Set the Ninth Information Injection Index as the
             *  String "Phone Number" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(9, student.getStudentPhone());
            /**
             *  Set the Tenth Information Injection Index as the
             *  String "Physical Address" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(10, student.getStudentAddress());
            /**
             *  Set the Eleventh Information Injection Index as the
             *  String "Biography" Column Value Associated with the Parameter
             *  Class Object.
             */
            ps.setString(11, student.getStudentBiography());
            /**
             *  Set the Twelfth Information Injection Index as the
             *  String Path For the Student Account's Specified
             *  Profile Image Large Value.
             */
            ps.setString(12, student.getStudentProfileImageLarge());
            /**
             *  Set the Thirteenth Information Injection Index as the
             *  String Path For the Student Account's Specified
             *  Profile Image Medium Value.
             */
            ps.setString(13, student.getStudentProfileImageMedium());
            /**
             *  Set the Fourteenth Information Injection Index as the
             *  String Path For the Student Account's Specified
             *  Profile Image Small Value.
             */
            ps.setString(14, student.getStudentProfileImageSmall());
            /**
             *  Set the Fifteenth Information Injection Index as the
             *  String Path For the Instructor Account's Specified
             *  Profile Image Extra Small Value.
             */
            ps.setString(15, student.getStudentProfileImageXS());
            /**
             *  Set the Fifteenth Information Injection Index as the
             *  Integer ID Column Value. This Injection Index is the
             *  Variable which the Query Statement will use to Find
             *  the Account that the User Wishes to Update the Information
             *  for.
             */
            ps.setInt(16, student.getRowID());
            /**
             *  Invoke the Update Process Within the Remote Database.
             */
            ps.executeUpdate();
            
             /**
             *  Set the Overall Status of Custom
             *  Return Object to the Value of True.
             *  This Lets the GUI Window Know that
             *  the Operation has Ended in Success.
             */
            result.setResult(true);
            /**
             *  Let the Calling GUI Class know that the
             *  Specified Student's Information has been 
             *  Successfully Updated.
             */
            result.setType("Student Account Successfully Updated");
        } 
        /**
         *  Catch a SQL Exception Should one Occur During the
         *  Query Process. These Types of Errors Typically
         *  Occur When the Chosen SQL Statements Are Unable
         *  to be Run through the Database, or an Error Occurs
         *  Within the Remote Database During Process Execution.
         */
        catch (SQLException e) {
            /**
            *  Close the Connection Seeing as We Have
            *  Finished Our Database Query Process.
            */
            closeConnection();
            /**
             *  Set the Overall Status of Custom
             *  Return Object to the Value of False.
             *  This Lets the GUI Window Know that
             *  the Operation has Ended in Failure.
             */
            result.setResult(false);
            /**
             *  Let the Calling GUI Class know what
             *  Type of Error Has Occurred so that it
             *  can Alert the User of Why the Process
             *  has Failed.
             */
            result.setType("SQL Exception");
        }
        /**
         *  Attempt to Close the Prepared Statement
         *  Object once we have Finished Our SQL
         *  Operation.
         */
        finally {
            try {
                /**
                 *  If the Prepared Statement Object
                 *  Isn't Already Null Once the Operation
                 *  Has Finished, then close the Object.
                 */
                if (ps != null) {
                    // Close Prepared Statement.
                    ps.close();
                }
            } 
            /**
            *  Catch a SQL Exception Should one Occur During the
            *  Query Process. These Types of Errors Typically
            *  Occur When the Chosen SQL Statements Are Unable
            *  to be Run through the Database, or an Error Occurs
            *  Within the Remote Database During Process Execution.
            */
            catch (SQLException e) {
                /**
                 *  If the Operation hasn't Already Been Performed
                 *  Successfully and the Overall Result Status hasn't
                 *  been set to true, then set the Overall Result
                 *  as the Value of False, and set the Error Type.
                 */
                if (!result.getResult() == true) {
                    /**
                    *  Set the Overall Status of Custom
                    *  Return Object to the Value of False.
                    *  This Lets the GUI Window Know that
                    *  the Operation has Ended in Failure.
                    */
                   result.setResult(false);
                   /**
                    *  Let the Calling GUI Class know what
                    *  Type of Error Has Occurred so that it
                    *  can Alert the User of Why the Process
                    *  has Failed.
                    */
                   result.setType("SQL Exception");
                }
            }
        }
        
        /**
         *  Return the Overall Result of the
         *  Student Account Update Query.
         */
        return result;
    }
    
    /**
     *  This Method is Used in Order to Retrieve a List of all the
     *  Unique Class Integer ID Values that are Currently Linked to
     *  the Logged in Instructor Account. This Method is Typically
     *  Invoked From Within the Main Screen: Attendance Records Tab.
     * 
     *  @param id                   The Unique Integer ID Value of
     *                              the Currently Logged in Instructor
     *                              Account. This ID Value Represents
     *                              the Accounts Row Entry Number within
     *                              the Remote Database.
     * 
     *  @return                     An ArrayList Object Containing the
     *                              List of Every Unique Class ID that
     *                              is Linked to the Current Instructor
     *                              Account.
     */
    public ArrayList<Integer> getInstructorClassListIDs(int id) {
        
        /**
         *  Instantiate an ArrayList Object Which will be
         *  Used in Order to Store the Result List of
         *  Class ID's Generated by the Query.
         */
        ArrayList<Integer> classIDs = new ArrayList<>();
        
        /**
         *  Initialize a Prepared Statement Object Variable Which Will
         *  Be Responsible for Injecting the Specified Parameter Information
         *  into the Global Query String. We Use a Prepared Statement
         *  Here instead of a Regular Statement Object Seeing as we
         *  are Trying to Retrieve Information that Pertains to a Specific
         *  Piece of Information Passed by the Current User.
         */
        PreparedStatement ps = null;
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            
            /**
             *  Initialize the Prepared Statement Object as the Global
             *  Query String Update Student. We Use a Prepared Statement
             *  Object in this Method seeing as we need to Inject Information
             *  Within the Query String before Execution.
             * 
             *  Query:  SELECT ClassID FROM InstructorClassLink WHERE 
             *          InstructorID = '" + id + "'"
             */
            ps = this.connection.prepareStatement(SELECT_CLASSIDS_BY_INSTRUCTORID);
            /**
             *  Set the First Information Injection Index as the
             *  Integer "ID" Column Value Associated with the Parameter
             *  Integer Object.
             */
            ps.setInt(1, id);
            
            /**
             *  Execute the Newly Specified Query Statement
             *  and Store the Returned Row Results From
             *  the InstructorClassLink Table in a New
             *  Result Set Object.
             */
            ResultSet rs = ps.executeQuery();
            
            /**
             *  Continuously Loop Until We have Iterated
             *  through Every Data Entry Present Within
             *  the Result Set Object.
             */
            while(rs.next()) {
                /**
                 *  Store the Unique Class ID Integer
                 *  Value Associated with the Current
                 *  Result Set Entry in a Temporary
                 *  Variable.
                 */
                int classId = rs.getInt("ClassID");
                /**
                 *  Add the Temporary Integer Variable
                 *  Value to our Return ArrayList Object.
                 */
                classIDs.add(classId);
            }
        }
        /**
         *  Catch a SQL Exception Should one Occur During the
         *  Query Process. These Types of Errors Typically
         *  Occur When the Chosen SQL Statements Are Unable
         *  to be Run through the Database, or an Error Occurs
         *  Within the Remote Database During Process Execution.
         */
        catch (SQLException e) {
            /**
             *  Seeing as We Have Encountered an Exception, we
             *  Need to Nullify the Class IDs ArrayList. This will
             *  Let the GUI Window Know that an Exception has
             *  Occurred.
             */
            classIDs = null;
        }
        /**
         *  Attempt to Close the Prepared Statement
         *  Object once we have Finished Our SQL
         *  Operation.
         */
        finally {
            try {
                /**
                 *  If the Prepared Statement Object
                 *  Isn't Already Null Once the Operation
                 *  Has Finished, then close the Object.
                 */
                if (ps != null) {
                    // Close Prepared Statement.
                    ps.close();
                }
            } 
            /**
            *  Catch a SQL Exception Should one Occur During the
            *  Query Process. These Types of Errors Typically
            *  Occur When the Chosen SQL Statements Are Unable
            *  to be Run through the Database, or an Error Occurs
            *  Within the Remote Database During Process Execution.
            */
            catch (SQLException e) {
                /**
                *  Seeing as We Have Encountered an Exception, we
                *  Need to Nullify the Class IDs ArrayList. This will
                *  Let the GUI Window Know that an Exception has
                *  Occurred.
                */
               classIDs = null;
            }
        }
        
        /**
         *  Close the Connection to The Remote Database
         *  Seeing as we have Finished our Data Retrieval
         *  Operation.
         */
        closeConnection();
        
        /**
         *  Return the List of ClassIDs or
         *  a Null Variable.
         */
        return classIDs;
    }
    
    /**
     *  This Method is Used in Order to Return an ArrayList of Class Objects
     *  Which are Used in Order for the Instructor Account to Check the
     *  Student Attendance Records Associated With Each Class.
     * 
     *  @param classIds                     The Parameter ArrayList Object
     *                                      Which Holds a List of Previously
     *                                      Retrieved Class ID Integer Values
     *                                      that are Associated With the
     *                                      Current Instructor's Account.
     * 
     *  @return                             An ArrayList Object Containing
     *                                      a List of the ClassTypes Associated
     *                                      With each Integer Class ID Passed
     *                                      in as the Parameter.
     */
    public ArrayList<ClassType> getInstructorClassList(ArrayList<Integer> classIds) {
        
        /**
         *  Create an ArrayList Object Which Will be Used
         *  In order to Hold the Class Information Retrieved
         *  By the Database Query.
         */
        ArrayList<ClassType> classNames = new ArrayList<>();
        
        /**
         *  Initialize a Prepared Statement Object Variable Which Will
         *  Be Responsible for Injecting the Specified Parameter Information
         *  into the Global Query String. We Use a Prepared Statement
         *  Here instead of a Regular Statement Object Seeing as we
         *  are Trying to Retrieve Information that Pertains to a Specific
         *  Piece of Information Passed by the Current User.
         */
        PreparedStatement ps = null;
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();

        try {
            /**
            for (int i = 0; i < classIds.size(); i++) {
                /**
                *  Re-Initialize the Global Statement Variable in Case
                *  it is Currently Still Set From Another Database Query
                *  / Modification Operation.
                *
                this.statement = (Statement) connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM Class WHERE ID = '" + classIds.get(i) + "'");

                while(rs.next()) {
                    
                    int id = rs.getInt("ID");
                    String field = rs.getString("Field");
                    int number = rs.getInt("ClassNumber");
                    String className = rs.getString("Title");
                    String type = rs.getString("Type");
                    String description = rs.getString("Description");
                    String creditHours = rs.getString("Credits");
                    String section = rs.getString("Section");
                    String semester = rs.getString("Semester");
                    int year = rs.getInt("Year");
                    int participants = rs.getInt("Participants");
                    
                    ClassType classType = new ClassType(id, field, number, className, type, description, creditHours, section, semester, year, participants);
                    
                    classNames.add(classType);
                }
            }*/
            
            /**
             *  Loop Through Each of the ClassID Integer
             *  Entries Present Within the Parameter ArrayList
             *  Object.
             */
            for (int i = 0; i < classIds.size(); i++) {
                
                /**
                *  Initialize the Prepared Statement Object as the Global
                *  Query String Update Student. We Use a Prepared Statement
                *  Object in this Method seeing as we need to Inject Information
                *  Within the Query String before Execution.
                * 
                *  Query:  "SELECT * FROM Class WHERE ID = '" + 
                *           classIds.get(i) + "'"
                */
                ps = this.connection.prepareStatement(SELECT_CLASSINFO_BY_CLASSIDLIST);
                
               /**
                *  Set the First Information Injection Index as the
                *  Integer "ID" Column Value Associated with the Parameter
                *  Integer Object.
                */
                ps.setInt(1, classIds.get(i));

               /**
                *  Execute the Newly Specified Query Statement
                *  and Store the Returned Row Results From
                *  the InstructorClassLink Table in a New
                *  Result Set Object.
                */
                ResultSet rs = ps.executeQuery();
               
                /**
                 *  Continuously Iterate through all the
                 *  Returned Result Set Entries. However,
                 *  Seeing as we are only looking for a
                 *  Single Class Object Entry, this While
                 *  Loop should only Iterate a Single Time.
                 */
                while(rs.next()) {
                    
                    /**
                    *   Store the Integer Value From the First
                    *   Column Value "ID" in a Temporary Variable.
                    */
                    int id = rs.getInt("ID");
                    /**
                    *   Store the String Value From the Second
                    *   Column Value "Field" in a Temporary Variable.
                    */
                    String field = rs.getString("Field");
                    /**
                    *   Store the Integer Value From the Third
                    *   Column Value "Class Number" in a Temporary Variable.
                    */
                    int number = rs.getInt("ClassNumber");
                    /**
                    *   Store the String Value From the Forth
                    *   Column Value "Title" in a Temporary Variable.
                    */
                    String className = rs.getString("Title");
                    /**
                    *   Store the String Value From the Fifth
                    *   Column Value "Type" in a Temporary Variable.
                    */
                    String type = rs.getString("Type");
                    /**
                    *   Store the String Value From the Sixth
                    *   Column Value "Description" in a Temporary Variable.
                    */
                    String description = rs.getString("Description");
                    /**
                    *   Store the Integer Value From the Seventh
                    *   Column Value "Credits" in a Temporary Variable.
                    */
                    String creditHours = rs.getString("Credits");
                    /**
                    *   Store the String Value From the Eighth
                    *   Column Value "Section" in a Temporary Variable.
                    */
                    String section = rs.getString("Section");
                    /**
                    *   Store the String Value From the Ninth
                    *   Column Value "Semester" in a Temporary Variable.
                    */
                    String semester = rs.getString("Semester");
                    /**
                    *   Store the Integer Value From the Tenth
                    *   Column Value "Year" in a Temporary Variable.
                    */
                    int year = rs.getInt("Year");
                    /**
                    *   Store the Integer Value From the Eleventh
                    *   Column Value "Participants" in a Temporary Variable.
                    */
                    int participants = rs.getInt("Participants");
                    
                    /**
                     *  Use All of the Previously Created Temporary
                     *  Variables in Order to Create a New Class Type
                     *  Object Variable.
                     */
                    ClassType classType = new ClassType(id, field, number, className, type, description, creditHours, section, semester, year, participants);
                    
                    /**
                     *  Add the Newly Created Class Type Object to
                     *  the New Return ArrayList Object. Additionally
                     *  we will place this Object at the Exact Same
                     *  Index Within the New ArrayList Object as
                     *  its Unique ID Value Was Placed in the Parameter
                     *  ArrayList.
                     */
                    classNames.add(classType);
                }
            }
        }
        /**
         *  Catch a SQL Exception Should one Occur During the
         *  Query Process. These Types of Errors Typically
         *  Occur When the Chosen SQL Statements Are Unable
         *  to be Run through the Database, or an Error Occurs
         *  Within the Remote Database During Process Execution.
         */
        catch (SQLException e) {
            /**
             *  Seeing as We Have Encountered an Exception, we
             *  Need to Nullify the ArrayList of Course Objects.
             *  This will Let the GUI Window Know that an Exception
             *  has Occurred.
             */
            classNames = null;
        }
        /**
         *  Attempt to Close the Prepared Statement
         *  Object once we have Finished Our SQL
         *  Operation.
         */
        finally {
            try {
                /**
                 *  If the Prepared Statement Object
                 *  Isn't Already Null Once the Operation
                 *  Has Finished, then close the Object.
                 */
                if (ps != null) {
                    // Close Prepared Statement.
                    ps.close();
                }
            } 
            /**
            *  Catch a SQL Exception Should one Occur During the
            *  Query Process. These Types of Errors Typically
            *  Occur When the Chosen SQL Statements Are Unable
            *  to be Run through the Database, or an Error Occurs
            *  Within the Remote Database During Process Execution.
            */
            catch (SQLException e) {
                /**
                *  Seeing as We Have Encountered an Exception, we
                *  Need to Nullify the Class Objects ArrayList.
                *  This will Let the GUI Window Know that an
                *  Exception has Occurred.
                */
               classNames = null;
            }
        }
        
        /**
         *  Close the Connection to The Remote Database
         *  Seeing as we have Finished our Data Retrieval
         *  Operation.
         */
        closeConnection();
        
        /**
         *  Return the List of Custom
         *  Class Objects or a Null 
         *  List Variable.
         */
        return classNames;
    }
    
    /**
     *  This Method is Used in Order to Query the Remote Database
     *  and Compare Each of the Returned "Username" Values With
     *  the Parameter String. This Method will attempt to Find the
     *  Parameter Username Value in Both the "Instructor" and
     *  "Student" Tables Present Within the Database. If the
     *  Parameter String Value is Found in Either Table, the
     *  Method will return a Boolean Value of True. Otherwise,
     *  it will return a Value of False.
     * 
     *  @param value            The User Specified "Username"
     *                          String which will be used as
     *                          the Comparison String in order
     *                          to Determine its Uniqueness.
     * 
     *  @return                 A Custom Object Return Value Representing
     *                          Whether or Not the Parameter
     *                          Username String Value is Present
     *                          in Either the "Instructor" or
     *                          "Student" Table.
     * 
     *                          True     -      The Parameter String
     *                                          Value is not Present.
     * 
     *                          False    -      The Parameter String
     *                                          Value is Present.
     */
    public DatabaseOperationResult checkUsernameAvalability(String value) {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        /**
         *  Create New Database Return Object Which will
         *  Let the GUI Window Form Know Whether or Not the
         *  Query Process has been Successful.
         */
        DatabaseOperationResult result = new DatabaseOperationResult(true, "", 0);
        
        try {
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            /**
             *  Set the Type of Query into the Previously Defined Statement
             *  and Run it through the Remote Database. Store Any Results that
             *  returned into the Result Set Object rs.
             * 
             *  Query: SELECT Username FROM Instructor
             */
            ResultSet rs = statement.executeQuery(SELECT_USERNAME_FROM_INSTRUCTOR);
            
            /**
             *  Continuously Loop Through All of the
             *  Entries Returned From the Query in the
             *  Result Set Object.
             */
            while(rs.next()) {
                /**
                 *  Retrieve the Username Value Associated
                 *  With the Current Result Set Entry.
                 */
                String username = rs.getString("Username");
                
                /**
                 *  If the Current Username Value is Equal
                 *  to the Parameter Value, then Return a
                 *  Value of False. This means that the
                 *  User Specified "Username" Value is
                 *  already Present Within the Database.
                 */
                if (username.equalsIgnoreCase(value)) {
                    /**
                     *  Close the Connection to the
                     *  Remote Database Seeing as We
                     *  Have Found Our Result.
                     */
                    closeConnection();
                    /**
                     *  Let the Calling Class Know
                     *  that the Operation has
                     *  Ended in Failure.
                     */
                    result.setResult(false);
                    /**
                     *  Let the Calling Class Know
                     *  that the Operation has Failed
                     *  Seeing as the Username is
                     *  Already Present.
                     */
                    result.setType("Username Present");
                    /**
                     *  Return the Return Value
                     *  Object.
                     */
                    return result;
                }
            }
            
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            /**
             *  Set the Type of Query into the Previously Defined Statement
             *  and Run it through the Remote Database. Store Any Results that
             *  are returned into the Result Set Object rs2.
             * 
             *  Query: SELECT Username FROM Student
             */
            ResultSet rs2 = statement.executeQuery(SELECT_USERNAME_FROM_STUDENT);
            
            /**
             *  Continuously Loop Through All of the
             *  Entries Returned From the Query in the
             *  Result Set Object.
             */
            while(rs2.next()) {
                /**
                 *  Retrieve the Username Value Associated
                 *  With the Current Result Set Entry.
                 */
                String username = rs2.getString("Username");
                
                System.out.println("Username Value: " + username + " Parameter Value: " + value);
                /**
                 *  If the Current Username Value is Equal
                 *  to the Parameter Value, then Return a
                 *  Value of False. This means that the
                 *  User Specified "Username" Value is
                 *  already Present Within the Database.
                 */
                if (username.equalsIgnoreCase(value)) {
                    /**
                     *  Close the Connection to the
                     *  Remote Database Seeing as We
                     *  Have Found Our Result.
                     */
                    closeConnection();
                    /**
                    *  Let the Calling Class Know
                    *  that the Operation has
                    *  Ended in Failure.
                    */
                    result.setResult(false);
                    /**
                     *  Let the Calling Class Know
                     *  that the Operation has Failed
                     *  Seeing as the Username is
                     *  Already Present.
                     */
                    result.setType("Username Present");
                    /**
                     *  Return the Return Value
                     *  Object.
                     */
                    return result;
                }
            }            
        }
        /**
         *  If an SQL Exception Occurs While
         *  Attempting to Find the Result, Return
         *  a Value of False.
         */
        catch (SQLException e) {
            /**
             *  Let the Calling Class Know
             *  that the Operation has
             *  Ended in Failure.
             */
            result.setResult(false);
            /**
             *  Let the Calling Class Know
             *  that the Process has Failed
             *  Due to an Exception Occurring.
             */
            result.setType("SQL Exception");
            /**
             *  Return the Return Value
             *  Object.
             */
            return result;
        }
        
        /**
         *  Close the Connection to
         *  the Remote Database.
         */
        closeConnection();
        
        /**
         *  If the Parameter String Doesn't
         *  Match any of the Returned Values
         *  and an SQL Exception hasn't Occurred,
         *  then Return the Result with a Value
         *  of True.
         */
        return result;
    }
    
    /**
     *  This Method is Responsible For Checking to
     *  see if the User Specified Seven Digit Student 
     *  Identification Number Integer Value is Already
     *  Taken and Associated With Another Student
     *  Account Present in the Database. Seeing as this
     *  Value acts as a Secondary Unique Identifier,
     *  and is Used in Order to Pull Student Information
     *  From the Database, it Means that the Value Cannot 
     *  Be Associated with More than a Single Student
     *  Profile Account.
     * 
     *  @param id               The Unique Student Identification
     *                          Number Associated With the Current
     *                          User's Student Account.
     * 
     *  @return                 A Custom Object Return Value Representing
     *                          Whether or Not the Unique Parameter
     *                          Student Identification Number Integer
     *                          Value is already Present within the
     *                          Student Table in the Remote Database.
     * 
     *                          True     -      The Parameter Integer ID
     *                                          Value is not Present.
     * 
     *                          False    -      The Parameter Integer ID
     *                                          Value is Present.
     */
    public DatabaseOperationResult checkStudentIDAvalability(int id) {
        
        /**
         *  Instantiate a New Instance of the Custom Database Operation 
         *  Result Return Object. This Object Will be Used to Provide
         *  the Calling GUI Class with the Overall Result of the
         *  Database Operation in Addition to an Explanation should
         *  the Operation Fail.
         */
        DatabaseOperationResult result = new DatabaseOperationResult(false, "", 0);
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            /**
             *  Set the Type of Query into the Previously Defined Statement
             *  and Run it through the Remote Database. Store Any Results that
             *  are returned into the Result Set Object rs.
             * 
             *  Query: SELECT StudentID FROM Student
             */
            ResultSet rs = statement.executeQuery(SELECT_STUDENTID_FROM_STUDENT);

            /**
             *  Continuously Loop Through All of the
             *  Entries Returned From the Query in the
             *  Result Set Object.
             */
            while(rs.next()) {
                /**
                 *  Save the Unique Student Identification
                 *  Number Value Associated with the Current
                 *  Result Set Entry into a Temporary Variable.
                 */
                int identity = rs.getInt("StudentID");
                
                /**
                 *  Compare the Student Identification Number
                 *  Associated with the Current Result Set
                 *  Entry to the Parameter Student Identification
                 *  Number Specified By the User. If the Two
                 *  Seven Digit Numbers are Equivalent to Each
                 *  Other, then Return a Result Value of
                 */
                if (identity == id) {
                    /**
                    *  Close the Connection to
                    *  the Remote Database.
                    */
                    closeConnection();
                    
                    /**
                    *  Let the Calling Class Know
                    *  that the Operation has
                    *  Ended in Failure.
                    */
                    result.setResult(false);
                   /**
                    *  Let the Calling Class Know
                    *  that the Process has Failed
                    *  Due to the Specified Student
                    *  Identification Number Value
                    *  Already Being Present.
                    */
                    result.setType("Student ID Present");
                    /**
                    *  Return the Return Value
                    *  Object.
                    */
                    return result;
                }
            }            
        }
        /**
         *  If an SQL Exception Occurs While
         *  Attempting to Find the Result, Return
         *  a Value of False.
         */
        catch (SQLException e) {
            /**
            *  Close the Connection to
            *  the Remote Database.
            */
            closeConnection();
            
            /**
             *  Let the Calling Class Know
             *  that the Operation has
             *  Ended in Failure.
             */
            result.setResult(false);
            /**
             *  Let the Calling Class Know
             *  that the Process has Failed
             *  Due to an Exception Occurring.
             */
            result.setType("SQL Exception");
            /**
             *  Return the Return Value
             *  Object.
             */
            return result;
        }
        
        /**
        *  Close the Connection to
        *  the Remote Database.
        */
        closeConnection();
        
        /**
        *  Let the Calling Class Know
        *  that the Operation has
        *  Ended in Failure.
        */
        result.setResult(true);
       /**
        *  Let the Calling Class Know
        *  that the Process has Failed
        *  Due to an Exception Occurring.
        */
        result.setType("Student ID Not Present");
       /**
        *  Return the Return Value
        *  Object.
        */
        return result;
    }
    
    /**
     *  This Method is Used in Order to Create a New "Instructor" Account Within
     *  the Instructor Table of the Remote Database. This Method Uses the Custom
     *  Object Parameter in order to Create an Injection SQL Query Statement that
     *  will be Executed Upon the Database. This Method Can Only be Invoked 
     *  Providing that the User Specified Information has Passed All of its Previous
     *  Validity Checks. For More Information Concerning what Information Specifications
     *  and Rules the User's Account Information must follow, please refer to the
     *  Credentials Screen GUI Window Class.
     * 
     *  @param instructor                   A Custom Object that Holds All of the
     *                                      Required User Specified Information
     *                                      Retrieved From the Instructor Account
     *                                      Creation GUI Window Form.
     * 
     *  @return                             A Custom Object Which Tells the GUI
     *                                      Window the Overall Result of the Account
     *                                      Creation Process in Addition to the
     *                                      Type of Error or Exception should one
     *                                      Occur. This Value is Used in Order to
     *                                      let the GUI Window Provide the User
     *                                      With a Detailed Message of Why the
     *                                      Process Has Failed.
     */
    public AccountCreationResults insertInstructorAccount(Instructor instructor) {
        
        /**
         *  Instantiate a Custom New Account Creation Results Object Which
         *  will be Responsible for Letting the Calling GUI Class Know Whether
         *  or Not the Account Insertion Process into the Remote Database
         *  was Successful.
         */
        AccountCreationResults result = new AccountCreationResults(false, -1);
        /**
         *  Initialize a Prepared Statement Object Which Will
         *  Be Used in Creating Our Query Statement through
         *  the Use of Data Injection.
         */
        PreparedStatement ps = null;
        /**
         *  Initialize a Temporary Integer Variable. This
         *  Variable will be used in Order to Hold the Newly
         *  Assigned Unique Integer Row ID Value that the
         *  Database Associates with the Newly Created
         *  Instructor Account.
         */
        int row = -1;
        /**
         *  Initialize a Temporary Integer Variable Which Will
         *  Be Used in Order to Determine Whether or Not the
         *  SQL Insertion Query was Successful.
         */
        int status = 0;
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            /**
            *  Initialize the Prepared Statement Object as the Global
            *  String Query Statement Value. We Use a Prepared Statement
            *  Object in this Method seeing as we need to Inject Information
            *  Within the Query String before Execution.
            * 
            *  Query:  "INSERT INTO Instructor(ID, FirstName, LastName, Username
            *           Password, Email, DisplayName, Phone, Address, Biography,
            *           ImageLarge, ImageMedium, ImageSmall, ImageXS) VALUES
            *           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            */
            ps = this.connection.prepareStatement(INSERT_INSTRUCTOR);
            /**
             *  Inject the Integer Row ID Value Associated With the
             *  Parameter Instructor Object. This Value will be a
             *  Default Value of the Type (-1) or (0) Seeing as
             *  a new Row ID Entry Number will be Assigned to the
             *  New Instructor Account Once it has been Inserted
             *  into the Remote Database. This Happens Seeing as
             *  the Row ID Number Field with Both the Instructor
             *  and Student Tables has an Auto-Increment Function
             *  Attached to it. This Feature will automatically
             *  Produce a New Row ID Number Based on the Current
             *  Number of Account Types Already Present in the
             *  Remote Database Instructor Table.
             */
            ps.setInt(1, instructor.getInstructorID());
            /**
             *  Inject the String "First Name" Column Value Associated
             *  With the Parameter Instructor Object into the Second
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(2, instructor.getInstructorFirstName());
            /**
             *  Inject the String "Last Name" Column Value Associated
             *  With the Parameter Instructor Object into the Third
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(3, instructor.getInstructorLastName());
            /**
             *  Inject the String "Username" Column Value Associated
             *  With the Parameter Instructor Object into the Forth
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(4, instructor.getInstructorUsername());
            /**
             *  Inject the String "Password" Column Value Associated
             *  With the Parameter Instructor Object into the Fifth
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(5, instructor.getInstructorPassword());
            /**
             *  Inject the String "Email Address" Column Value Associated
             *  With the Parameter Instructor Object into the Sixth
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(6, instructor.getInstructorEmail());
            /**
             *  Inject the String "Display Name" Column Value Associated
             *  With the Parameter Instructor Object into the Seventh
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(7, instructor.getInstructorDisplayName());
            /**
             *  Inject the String "Phone Number" Column Value Associated
             *  With the Parameter Instructor Object into the Eighth
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(8, instructor.getInstructorPhone());
            /**
             *  Inject the String "Physical Address" Column Value Associated
             *  With the Parameter Instructor Object into the Ninth
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(9, instructor.getInstructorAddress());
            /**
             *  Inject the String "Biography" Column Value Associated
             *  With the Parameter Instructor Object into the Tenth
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(10, instructor.getInstructorBiography());
            /**
             *  Inject the String Containing the Directory Path to the
             *  User's Chosen Profile Image (Large Version) Within the
             *  Eleventh Injection Index Position. This Directory
             *  Path Maps to the Location of the Image Version Present
             *  Within the Application's Files. Seeing as this Account
             *  is Currently in the Process of Being Created, all the
             *  Profile Image Versions will be set as the Default Profile
             *  Image Value, which can be Changed by the User During a
             *  Later Time Period.
             */
            ps.setString(11, instructor.getInstructorProfileImageLarge());
            /**
             *  Inject the String Containing the Directory Path to the
             *  User's Chosen Profile Image (Medium Version) Within the
             *  Twelfth Injection Index Position. This Directory
             *  Path Maps to the Location of the Image Version Present
             *  Within the Application's Files. Seeing as this Account
             *  is Currently in the Process of Being Created, all the
             *  Profile Image Versions will be set as the Default Profile
             *  Image Value, which can be Changed by the User During a
             *  Later Time Period.
             */
            ps.setString(12, instructor.getInstructorProfileImageMedium());
            /**
             *  Inject the String Containing the Directory Path to the
             *  User's Chosen Profile Image (Small Version) Within the
             *  Eleventh Injection Index Position. This Directory
             *  Path Maps to the Location of the Image Version Present
             *  Within the Application's Files. Seeing as this Account
             *  is Currently in the Process of Being Created, all the
             *  Profile Image Versions will be set as the Default Profile
             *  Image Value, which can be Changed by the User During a
             *  Later Time Period.
             */
            ps.setString(13, instructor.getInstructorProfileImageSmall());
            /**
             *  Inject the String Containing the Directory Path to the
             *  User's Chosen Profile Image (Extra Small Version) Within the
             *  Eleventh Injection Index Position. This Directory
             *  Path Maps to the Location of the Image Version Present
             *  Within the Application's Files. Seeing as this Account
             *  is Currently in the Process of Being Created, all the
             *  Profile Image Versions will be set as the Default Profile
             *  Image Value, which can be Changed by the User During a
             *  Later Time Period.
             */
            ps.setString(14, instructor.getInstructorProfileImageXS());
            /**
             *  Execute the Newly Created Query Statement Within the
             *  Remote Database. Save the Number of Rows Affected Within
             *  the Local Integer Variable status. This Value will be
             *  Used to Determine Whether or Not the new Account was
             *  Successfully Added to the Database.
             */
            status = ps.executeUpdate();

            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            /**
             *  Set the Type of Query into the Previously Defined Statement
             *  and Run it through the Remote Database. Store Any Results that
             *  are returned into the Result Set Object rs. The Query Below
             *  is Used to Retrieve the Newly Assigned Row ID Integer Value
             *  which the Remote Database has just Assigned to the Newly
             *  Created Instructor Account.
             * 
             *  Query: "SELECT last_insert_id()"
             */
            ResultSet rs = statement.executeQuery(SELECT_ROWID_LAST_INSERT);
            
            /**
             *  Continuously Loop Until We Have Iterated Through
             *  Every Entry Present Within the Result Set Object.
             *  Seeing as we are Only Expecting a Single Entry
             *  Within the Result Set Object, this Loop Should Only
             *  Iterate a Single Time.
             */
            while(rs.next()) {
                /**
                 *  Retrieve the Row ID Integer Index that Was
                 *  Just Assigned to the Most Recently Inserted
                 *  Table Entry Within the Remote Database. Seeing
                 *  as we just Inserted the Instructor's New Account,
                 *  this Query Value will return the New Row ID
                 *  Value of the Instructor Account. 
                 * 
                 * (Note: The Current Method MUST STILL BE CONNECTED
                 *  TO THE DATABASE IN ORDER FOR THIS QUERY TO WORK.)
                 * 
                 *  Should the Database Connection Fail Before this
                 *  Query Statement is Executed, then the Account Creation
                 *  Process will Succeed, but the User Will have to
                 *  Close the Application and Re-Login in Order to 
                 *  Make Changes to their Account Information.
                 */
                row = rs.getInt("last_insert_id()");
            }
            
            /**
             *  Check to See if the New Instructor Account Has
             *  Been Successfully Inserted into the Database and 
             *  to See if the Second Query Statement Has Returned
             *  the Row ID Value of the Newly Created Account Entry.
             */
            if (status > 0 && row > -1) {
                /**
                *   Let the Calling GUI Class Know that the Insertion
                *   Operation was Successful.
                */
               result.setResult(true);
               /**
                *   Return the Newly Assigned Row ID Integer
                *   Value that the Database has Associated
                *   With the Newly Created Account.
                */
               result.setID(row);
            }  
        } 
        /**
         *  Catch a SQL Exception Should one Occur During the
         *  Query Process. These Types of Errors Typically
         *  Occur When the Chosen SQL Statements Are Unable
         *  to be Run through the Database, or an Error Occurs
         *  Within the Remote Database During Process Execution.
         */
        catch (SQLException e) {
            /**
             *  If the Status Value is the Value
             *  of Zero, then it Means that the
             *  New Account Wasn't Successfully
             *  Inserted. Thus, we need to Let
             *  the Calling GUI Class Know that
             *  the Account Wasn't Created.
             */
            if (status == 0) {
                /**
                *  Let the Calling GUI Class Know that the Insertion
                *  Operation was a Failure.
                */
               result.setResult(false);
            }
        }
       /**
         *  Attempt to Close the Prepared Statement
         *  Object once we have Finished Our SQL
         *  Operation.
         */
        finally {
            try {
                /**
                 *  If the Prepared Statement Object
                 *  Isn't Already Null Once the Operation
                 *  Has Finished, then close the Object.
                 */
                if (ps != null) {
                    // Close Prepared Statement.
                    ps.close();
                }
            } 
            /**
            *  Catch a SQL Exception Should one Occur During the
            *  Query Process. These Types of Errors Typically
            *  Occur When the Chosen SQL Statements Are Unable
            *  to be Run through the Database, or an Error Occurs
            *  Within the Remote Database During Process Execution.
            */
            catch (SQLException e) {
                /**
                *  If the Status Value is the Value
                *  of Zero, then it Means that the
                *  New Account Wasn't Successfully
                *  Inserted. Thus, we need to Let
                *  the Calling GUI Class Know that
                *  the Account Wasn't Created.
                */
               if (status == 0) {
                   /**
                   *  Let the Calling GUI Class Know that the Insertion
                   *  Operation was a Failure.
                   */
                  result.setResult(false);
               }
            }
        }
        
        /**
         *  Close the Connection to Remote
         *  Database.
         */
        closeConnection();
        
        /**
         *  Return the Result of the Account
         *  Creation Process.
         */
        return result;
    }
    
    /**
     *  This Method is Used in Order to Create a New "Student" Account Within
     *  the Student Table of the Remote Database. This Method Uses the Custom
     *  Object Parameter in order to Create an Injection SQL Query Statement that
     *  will be Executed Upon the Database. This Method Can Only be Invoked 
     *  Providing that the User Specified Information has Passed All of its Previous
     *  Validity Checks. For More Information Concerning what Information Specifications
     *  and Rules the User's Account Information must follow, please refer to the
     *  Credentials Screen GUI Window Class.
     * 
     *  @param student                      A Custom Object that Holds All of the
     *                                      Required User Specified Information
     *                                      Retrieved From the Student Account
     *                                      Creation GUI Window Form.
     * 
     *  @return                             A Custom Object Which Tells the GUI
     *                                      Window the Overall Result of the Account
     *                                      Creation Process in Addition to the
     *                                      Type of Error or Exception should one
     *                                      Occur. This Value is Used in Order to
     *                                      let the GUI Window Provide the User
     *                                      With a Detailed Message of Why the
     *                                      Process Has Failed.
     */
    public AccountCreationResults insertStudent(Student student) {
        
        /**
         *  Instantiate a Custom New Account Creation Results Object Which
         *  will be Responsible for Letting the Calling GUI Class Know Whether
         *  or Not the Account Insertion Process into the Remote Database
         *  was Successful.
         */
        AccountCreationResults result = new AccountCreationResults(false, -1);
        /**
         *  Initialize a Prepared Statement Object Which Will
         *  Be Used in Creating Our Query Statement through
         *  the Use of Data Injection.
         */
        PreparedStatement ps = null;
        /**
         *  Initialize a Temporary Integer Variable. This
         *  Variable will be used in Order to Hold the Newly
         *  Assigned Unique Integer Row ID Value that the
         *  Database Associates with the Newly Created
         *  Student Account.
         */
        int row = -1;
        /**
         *  Initialize a Temporary Integer Variable Which Will
         *  Be Used in Order to Determine Whether or Not the
         *  SQL Insertion Query was Successful.
         */
        int status = 0;
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            /**
            *  Initialize the Prepared Statement Object as the Global
            *  String Query Statement Value. We Use a Prepared Statement
            *  Object in this Method seeing as we need to Inject Information
            *  Within the Query String before Execution.
            * 
            *  Query:  "INSERT INTO Student(ID, StudentID, FirstName, LastName,
            *           Username, Password, Email, DisplayName, Phone, Address,
            *           Biography, ImageLarge, ImageMedium, ImageSmall, ImageXS)
            *           VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            */
            ps = this.connection.prepareStatement(INSERT_STUDENT);
            /**
             *  Inject the Integer Row ID Value Associated With the
             *  Parameter Student Object. This Value will be a
             *  Default Value of the Type (-1) or (0) Seeing as
             *  a new Row ID Entry Number will be Assigned to the
             *  New Student Account Once it has been Inserted
             *  into the Remote Database. This Happens Seeing as
             *  the Row ID Number Field with Both the Instructor
             *  and Student Tables has an Auto-Increment Function
             *  Attached to it. This Feature will automatically
             *  Produce a New Row ID Number Based on the Current
             *  Number of Account Types Already Present in the
             *  Remote Database Student Table.
             */
            ps.setInt(1, student.getRowID());
            /**
             *  Inject the Integer "Student Identification Number" Column
             *  Value into the Second Injection Index Present Within
             *  the Query String.
             */
            ps.setInt(2, student.getStudentIdentificationNumber());
            /**
             *  Inject the String "First Name" Column Value Associated
             *  With the Parameter Student Object into the Third
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(3, student.getStudentFirstName());
            /**
             *  Inject the String "Last Name" Column Value Associated
             *  With the Parameter Student Object into the Forth
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(4, student.getStudentLastName());
            /**
             *  Inject the String "Username" Column Value Associated
             *  With the Parameter Student Object into the Fifth
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(5, student.getStudentUsername());
            /**
             *  Inject the String "Password" Column Value Associated
             *  With the Parameter Student Object into the Sixth
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(6, student.getStudentPassword());
            /**
             *  Inject the String "Email Address" Column Value Associated
             *  With the Parameter Student Object into the Seventh
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(7, student.getStudentEmail());
            /**
             *  Inject the String "Display Name" Column Value Associated
             *  With the Parameter Student Object into the Eighth
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(8, student.getStudentDisplayName());
            /**
             *  Inject the String "Phone Number" Column Value Associated
             *  With the Parameter Student Object into the Ninth
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(9, student.getStudentPhone());
            /**
             *  Inject the String "Physical Address" Column Value Associated
             *  With the Parameter Student Object into the Tenth
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(10, student.getStudentAddress());
            /**
             *  Inject the String "Biography" Column Value Associated
             *  With the Parameter Student Object into the Eleventh
             *  Injection Index Position Present Within the Query String.
             */
            ps.setString(11, student.getStudentBiography());
            /**
             *  Inject the String Containing the Directory Path to the
             *  User's Chosen Profile Image (Large Version) Within the
             *  Twelfth Injection Index Position. This Directory
             *  Path Maps to the Location of the Image Version Present
             *  Within the Application's Files. Seeing as this Account
             *  is Currently in the Process of Being Created, all the
             *  Profile Image Versions will be set as the Default Profile
             *  Image Value, which can be Changed by the User During a
             *  Later Time Period.
             */
            ps.setString(12, student.getStudentProfileImageLarge());
            /**
             *  Inject the String Containing the Directory Path to the
             *  User's Chosen Profile Image (Medium Version) Within the
             *  Thirteenth Injection Index Position. This Directory
             *  Path Maps to the Location of the Image Version Present
             *  Within the Application's Files. Seeing as this Account
             *  is Currently in the Process of Being Created, all the
             *  Profile Image Versions will be set as the Default Profile
             *  Image Value, which can be Changed by the User During a
             *  Later Time Period.
             */
            ps.setString(13, student.getStudentProfileImageMedium());
            /**
             *  Inject the String Containing the Directory Path to the
             *  User's Chosen Profile Image (Small Version) Within the
             *  Fourteenth Injection Index Position. This Directory
             *  Path Maps to the Location of the Image Version Present
             *  Within the Application's Files. Seeing as this Account
             *  is Currently in the Process of Being Created, all the
             *  Profile Image Versions will be set as the Default Profile
             *  Image Value, which can be Changed by the User During a
             *  Later Time Period.
             */
            ps.setString(14, student.getStudentProfileImageSmall());
            /**
             *  Inject the String Containing the Directory Path to the
             *  User's Chosen Profile Image (Extra Small Version) Within the
             *  Fifteenth Injection Index Position. This Directory
             *  Path Maps to the Location of the Image Version Present
             *  Within the Application's Files. Seeing as this Account
             *  is Currently in the Process of Being Created, all the
             *  Profile Image Versions will be set as the Default Profile
             *  Image Value, which can be Changed by the User During a
             *  Later Time Period.
             */
            ps.setString(15, student.getStudentProfileImageXS());
            /**
             *  Execute the Newly Created Query Statement Within the
             *  Remote Database. Save the Number of Rows Affected Within
             *  the Local Integer Variable status. This Value will be
             *  Used to Determine Whether or Not the new Account was
             *  Successfully Added to the Database.
             */
            status = ps.executeUpdate();

            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            /**
             *  Set the Type of Query into the Previously Defined Statement
             *  and Run it through the Remote Database. Store Any Results that
             *  are returned into the Result Set Object rs. The Query Below
             *  is Used to Retrieve the Newly Assigned Row ID Integer Value
             *  which the Remote Database has just Assigned to the Newly
             *  Created Student Account.
             * 
             *  Query: "SELECT last_insert_id()"
             */
            ResultSet rs = statement.executeQuery(SELECT_ROWID_LAST_INSERT);
            
            /**
             *  Continuously Loop Until We Have Iterated Through
             *  Every Entry Present Within the Result Set Object.
             *  Seeing as we are Only Expecting a Single Entry
             *  Within the Result Set Object, this Loop Should Only
             *  Iterate a Single Time.
             */
            while(rs.next()) {
                /**
                 *  Retrieve the Row ID Integer Index that Was
                 *  Just Assigned to the Most Recently Inserted
                 *  Table Entry Within the Remote Database. Seeing
                 *  as we just Inserted the Student's New Account,
                 *  this Query Value will return the New Row ID
                 *  Value of the Student Account. 
                 * 
                 * (Note: The Current Method MUST STILL BE CONNECTED
                 *  TO THE DATABASE IN ORDER FOR THIS QUERY TO WORK.)
                 * 
                 *  Should the Database Connection Fail Before this
                 *  Query Statement is Executed, then the Account Creation
                 *  Process will Succeed, but the User Will have to
                 *  Close the Application and Re-Login in Order to 
                 *  Make Changes to their Account Information.
                 */
                row = rs.getInt("last_insert_id()");
            }
            
            /**
             *  Check to See if the New Student Account Has
             *  Been Successfully Inserted into the Database and 
             *  to See if the Second Query Statement Has Returned
             *  the Row ID Value of the Newly Created Account Entry.
             */
            if (status > 0 && row > -1) {
                /**
                *   Let the Calling GUI Class Know that the Insertion
                *   Operation was Successful.
                */
               result.setResult(true);
               /**
                *   Return the Newly Assigned Row ID Integer
                *   Value that the Database has Associated
                *   With the Newly Created Account.
                */
               result.setID(row);
            }  
        } 
        /**
         *  Catch a SQL Exception Should one Occur During the
         *  Query Process. These Types of Errors Typically
         *  Occur When the Chosen SQL Statements Are Unable
         *  to be Run through the Database, or an Error Occurs
         *  Within the Remote Database During Process Execution.
         */
        catch (SQLException e) {
            /**
             *  If the Status Value is the Value
             *  of Zero, then it Means that the
             *  New Account Wasn't Successfully
             *  Inserted. Thus, we need to Let
             *  the Calling GUI Class Know that
             *  the Account Wasn't Created.
             */
            if (status == 0) {
                /**
                *  Let the Calling GUI Class Know that the Insertion
                *  Operation was a Failure.
                */
               result.setResult(false);
            }
        }
       /**
         *  Attempt to Close the Prepared Statement
         *  Object once we have Finished Our SQL
         *  Operation.
         */
        finally {
            try {
                /**
                 *  If the Prepared Statement Object
                 *  Isn't Already Null Once the Operation
                 *  Has Finished, then close the Object.
                 */
                if (ps != null) {
                    // Close Prepared Statement.
                    ps.close();
                }
            } 
            /**
            *  Catch a SQL Exception Should one Occur During the
            *  Query Process. These Types of Errors Typically
            *  Occur When the Chosen SQL Statements Are Unable
            *  to be Run through the Database, or an Error Occurs
            *  Within the Remote Database During Process Execution.
            */
            catch (SQLException e) {
                /**
                *  If the Status Value is the Value
                *  of Zero, then it Means that the
                *  New Account Wasn't Successfully
                *  Inserted. Thus, we need to Let
                *  the Calling GUI Class Know that
                *  the Account Wasn't Created.
                */
               if (status == 0) {
                   /**
                   *  Let the Calling GUI Class Know that the Insertion
                   *  Operation was a Failure.
                   */
                  result.setResult(false);
               }
            }
        }
        
        /**
         *  Close the Connection to Remote
         *  Database.
         */
        closeConnection();
        
        /**
         *  Return the Result of the Account
         *  Creation Process.
         */
        return result;
    }
    
    /**
     *  This Method is Used in Order to Insert a List of Attendance
     *  Record Objects into the Attendance Table Present Within the
     *  Remote Database. This Method is Typically Invoked Whenever
     *  an Instructor Account Uses the Attendance Marking Feature to
     *  Create a List of Attendance Record Objects Pertaining to the
     *  Student Accounts Enrolled in the Specified ClassType 
     *  (Course) Object. The Creation of the Attendance Records is
     *  Based of the Current Class Roster of Student Accounts. If
     *  a Student Enrolled in the Chosen Class is Present During
     *  the Instructor Attendance Server, then their New Attendance
     *  Record Object Will be Marked With a Status String Value of
     *  Present. If the Student isn't Connected to the Attendance
     *  Server when the Instructor Invokes the Attendance Marking
     *  Process, then the A New Attendance Record Will Still Be
     *  Marked For them. However, the Attendance Status Will Instead
     *  Be Marked as the Value of Absent. Should the Instructor Enroll
     *  A Student into the Class Roster After Already having Taken
     *  Attendance During Previous Dates, then the Application will
     *  Automatically Create Attendance Record Values for these newly
     *  Enrolled Students. However, Instead of Having an Attendance Status
     *  of Present or Absent, these new Attendance Records Will All
     *  Contain a Status String Value of "NYE" Which Stands for the
     *  Phrase (Not Yet Added).
     * 
     *  @param value                An ArrayList Object Which is Composed 
     *                              of Attendance Record Objects that Pertain
     *                              to Each Student Within the Class.
     *                              
     *  @return                     A Custom Database Return Object Which
     *                              is Used in Order to Determine the Overall
     *                              Result of the Insertion Process in
     *                              Addition to Categorizing and Saving
     *                              the Details of Any Error / Exception
     *                              That May have Occurred During the 
     *                              Insertion Process.
     */
    public DatabaseOperationResult insertAttendanceRecordList(ArrayList<AttendanceRecord> value) {
        
        /**
         *  Create an ArrayList Object of Custom Student Objects.
         *  This Object will be Used in Order to Create a List of
         *  of All the Student Accounts Currently Enrolled in
         *  the ClassType (Course) Object Chosen By the User.
         */
        ArrayList<Integer> classRoster = new ArrayList<>();
        
        /**
         *  Initialize a New Database Return Object Variable. This
         *  Object will be Responsible for Holding the Overall Return
         *  Value of the Operation in Addition to Details Concerning
         *  Any Errors or Exceptions that may Occur During the Process.
         */
        DatabaseOperationResult result = new DatabaseOperationResult(false, "", 0);
        
        /**
         *  Create an ArrayList Object that is Responsible for
         *  Holding the Attendance Records of those Student
         *  Accounts who were present During the Invoking
         *  of the Attendance Marking Feature in the Instructor's
         *  Main Screen GUI: Attendance Tab.
         */
        ArrayList<AttendanceRecord> presentRecords = new ArrayList<>();
        ArrayList<Integer> attendanceRecordIDs = new ArrayList<>();
        
        /**
         *  Initialize a Prepared Statement Object Which Will
         *  Be Used in Creating Our Query Statement through
         *  the Use of Data Injection.
         */
        PreparedStatement ps = null;
        
        /**
         *  Initialize a Temporary Integer Variable Which Will
         *  Be Used in Order to Determine Whether or Not the
         *  SQL Insertion Query was Successful.
         */
        int status = 0;
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            //this.statement = (Statement) connection.createStatement();
            //ResultSet rs = statement.executeQuery("SELECT * FROM StudentClassLink WHERE ClassID = '" + value.get(0).getAttendanceRecordClassID()  + "'");
            
            /**
             * SELECT_STUDENTCLASSLINKVALUE_BY_CLASSID
             * SELECT_ATTENDANCERECORDS_BY_PARAMETERS
             */
            
           /**
            *  Initialize the Prepared Statement Object as the Global
            *  String Query Statement Value. We Use a Prepared Statement
            *  Object in this Method seeing as we need to Inject Information
            *  Within the Query String before Execution.
            * 
            *  Query:  "SELECT * FROM StudentClassLink WHERE ClassID = ?"
            */
            ps = this.connection.prepareStatement(SELECT_STUDENTCLASSLINKVALUE_BY_CLASSID);
            /**
             *  Inject the Unique Class ID Integer Variable Within
             *  the First Injection Index of the Query String.
             */
            ps.setInt(1, value.get(0).getAttendanceRecordClassID());
            /**
             *  Execute the Newly Created Query Statement Within the
             *  Remote Database. Save the Returned Table Entries Within
             *  the ResultSet Object rs.
             */
            ResultSet rs = ps.executeQuery();
            
            /**
             *  Continuously Loop Until We have Iterated
             *  through Each of the Table Entries Present
             *  Within the ResultSet Object.
             */
            while(rs.next()) {
                /**
                 *  Retrieve the Student ID Integer Value
                 *  Associated With the Current ResultSet
                 *  Entry.
                 */
                int studentID = rs.getInt("StudentID");
                /**
                 *  Add the Retrieved Student ID Value to
                 *  the Class Roster ArrayList. This ArrayList
                 *  will soon be Used to Pull Down the
                 *  Student Account Information Associated
                 *  With Each ID Value in the List.
                 */
                classRoster.add(studentID);
            }
            
            //////////////////////////////////////
            
            ///  See if there are Attendance Records For Today Are Already Present ////
            
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            //this.statement = (Statement) connection.createStatement();
            //SELECT_STUDENTCLASSLINKVALUE_BY_CLASSID
            //rs = statement.executeQuery("SELECT * FROM Attendance WHERE ClassID = '" + value.get(0).getAttendanceRecordClassID() + "' AND InstructorID = '" + value.get(0).getAttendanceRecordInstructorID() + "' AND Date = '" + value.get(0).getAttendanceRecordDate() + "'");
            
            /**
            *  Initialize the Prepared Statement Object as the Global
            *  String Query Statement Value. We Use a Prepared Statement
            *  Object in this Method seeing as we need to Inject Information
            *  Within the Query String before Execution.
            * 
            *  Query:  "SELECT * FROM Attendance WHERE ClassID = ? AND
            *           InstructorID = ? AND Date = ?"
            */
            ps = this.connection.prepareStatement(SELECT_ATTENDANCERECORDS_BY_PARAMETERS);
            /**
             *  Inject the Unique Class ID Integer Variable Within
             *  the First Injection Index of the Query String.
             */
            ps.setInt(1, value.get(0).getAttendanceRecordClassID());
            /**
             *  Inject the Unique Instructor ID Integer Variable Within
             *  the Second Injection Index of the Query String.
             */
            ps.setInt(2, value.get(0).getAttendanceRecordInstructorID());
            /**
             *  Inject the Parameter Date String Variable Within
             *  the Third Injection Index of the Query String.
             */
            ps.setString(3, value.get(0).getAttendanceRecordDate());
            /**
             *  Execute the Newly Created Query Statement Within the
             *  Remote Database. Save the Returned Table Entries Within
             *  the ResultSet Object rs.
             */
            rs = ps.executeQuery();
                 
            /**
             *  Continuously Loop Until We have Iterated
             *  through Each of the Table Entries Present
             *  Within the ResultSet Object.
             */
            while(rs.next()) {
                /**
                 *  Retrieve the Unique Row ID Integer
                 *  Value Associated with the Current
                 *  Attendance Record Entry.
                 */
                int id = rs.getInt("ID");
                /**
                 *  Retrieve the Unique Student Identification
                 *  Number Integer Value Associated
                 *  with the Current Attendance Record Entry.
                 */
                int studentID = rs.getInt("StudentID");
                /**
                 *  Retrieve the Attendance Status String
                 *  Variable that is Associated With the Current
                 *  Result Set Entry.
                 */
                String attendancestatus = rs.getString("Status");
                
                /**
                 *  Store the Three Previously Retrieved Items Within
                 *  a Custom Attendance Record Object.
                 */
                AttendanceRecord record = new AttendanceRecord(0, value.get(0).getAttendanceRecordClassID(), value.get(0).getAttendanceRecordInstructorID(), studentID, attendancestatus, value.get(0).getAttendanceRecordDate(), "");
                /**
                 *  Add the New Attendance Record Unique Integer
                 *  ID Value into the ID ArrayList Object.
                 */
                attendanceRecordIDs.add(id);
                /**
                 *  Store the Newly Created Attendance Record
                 *  Object Within the ArrayList Object Meant to
                 *  Contain the Attendance Records of the Student's
                 *  Present During the Attendance Marking Process
                 *  for the Current Date.
                 */
                presentRecords.add(record);
            }
            
            /////////////////////////////////////////////
            
            // If No Attendance Records For Today Are Already Present ///
            
            /**
             *  If there are No Attendance Records Already Present
             *  Within the Database Associated with the Current Date,
             *  then Proceed as Normal and Create the New Set of Attendance
             *  Records for Each Student Account Currently Enrolled Within
             *  the Class. The Students Currently Present Within the
             *  Attendance Server will be Marked Present While the Others
             *  Will be Marked as Absent. These Attendance Status Values
             *  Can be Changed Later on According to the Instructor's
             *  Preference.
             */
            if (presentRecords.isEmpty()) {
                /**
                 *  Continuously Loop Through the ArrayList Containing
                 *  The Student ID Integer Values of those Student Accounts
                 *  Which are Currently Enrolled in the Class. In this
                 *  Loop, we will automatically set each Student's Attendance
                 *  Status as the Value of Absent. However, this will be
                 *  Changed Shortly and is Only Done in Order to Get the
                 *  Attendance Records Created For the Current Date.
                 */
                for (int i = 0; i < classRoster.size(); i++) { 
                    
                    /**
                     *  Create the Insertion Database Query Using the
                     *  Global String Variable Shown. We Use A Prepared
                     *  Statement Object instead of a Regular Statement
                     *  Seeing as we need to Inject User Specified Data
                     *  Into the Query String Before Execution.
                     */
                    ps = this.connection.prepareStatement(INSERT_STUDENT_ATTENDANCE_RECORD);
                    /**
                     *  Set the Unique Row ID Integer Variable Value "0"
                     *  as the First Injection Index Value within the Query
                     *  String. We inject a Value of '0' here seeing as
                     *  the Row ID Column has an Auto-Increment Function.
                     */
                    ps.setInt(1, 0);
                    /**
                    *  Inject the Unique Class ID Integer Variable Value
                    *  into the Second Injection Index Present Within the
                    *  Query String.
                    */
                    ps.setInt(2, value.get(0).getAttendanceRecordClassID());
                    /**
                    *  Inject the Unique Class ID Integer Variable Value
                    *  into the Second Injection Index Present Within the
                    *  Query String.
                    */
                    ps.setInt(3, value.get(0).getAttendanceRecordInstructorID());
                    /**
                    *  Inject the Unique Student ID Integer Variable Value
                    *  into the Third Injection Index Present Within the
                    *  Query String.
                    */
                    ps.setInt(4, classRoster.get(i));
                    /**
                     *  Set the Forth Injection Index Value as the Attendance
                     *  Status of Absent. This is only a Temporary Value which
                     *  is used in Order to first Create the New Attendance
                     *  Record Values.
                     */
                    ps.setString(5, "Absent");
                    /**
                     *  Set the Fifth Injection Index Value as the String
                     *  Value Containing the Current Date. 
                     */
                    ps.setString(6, value.get(0).getAttendanceRecordDate());
                    /**
                     *  Set the Sixth Injection Index Value as a Blank
                     *  String Value. This Field is used to Hold the
                     *  Instructor's Chosen Comments about the Student's
                     *  Performance in Class During that Particular Date.
                     */
                    ps.setString(7, "");

                    /**
                     *  Execute the Query Statement. Save the
                     *  Number of Rows Affected within the Status
                     *  Integer Variable.
                     */
                    status = ps.executeUpdate();
                }
                
                /**
                *  Re-Initialize the Global Statement Variable in Case
                *  it is Currently Still Set From Another Database Query
                *  / Modification Operation.
                */
                //this.statement = (Statement) connection.createStatement();
                //rs = statement.executeQuery("SELECT * FROM Attendance WHERE ClassID = '" + value.get(0).getAttendanceRecordClassID()+ "' AND InstructorID = '" + value.get(0).getAttendanceRecordInstructorID() + "' AND Date = '" + value.get(0).getAttendanceRecordDate() + "'");
                
                /**
                *  Initialize the Prepared Statement Object as the Global
                *  String Query Statement Value. We Use a Prepared Statement
                *  Object in this Method seeing as we need to Inject Information
                *  Within the Query String before Execution.
                * 
                *  Query:  "SELECT * FROM Attendance WHERE ClassID = ? AND
                *           InstructorID = ? AND Date = ?"
                */
                ps = this.connection.prepareStatement(SELECT_ATTENDANCERECORDS_BY_PARAMETERS);
                /**
                 *  Inject the Unique Class ID Integer Variable Within
                 *  the First Injection Index of the Query String.
                 */
                ps.setInt(1, value.get(0).getAttendanceRecordClassID());
                /**
                 *  Inject the Unique Instructor ID Integer Variable Within
                 *  the Second Injection Index of the Query String.
                 */
                ps.setInt(2, value.get(0).getAttendanceRecordInstructorID());
                /**
                 *  Inject the Parameter Date String Variable Within
                 *  the Third Injection Index of the Query String.
                 */
                ps.setString(3, value.get(0).getAttendanceRecordDate());
                /**
                 *  Execute the Newly Created Query Statement Within the
                 *  Remote Database. Save the Returned Table Entries Within
                 *  the ResultSet Object rs.
                 */
                rs = ps.executeQuery();
            
                /**
                 *  Continuously Loop Through the Returned Attendance
                 *  Record Values. These Objects, are the Attendance Record
                 *  Object we just Created in the Previous Step. We Return
                 *  them Once again so that we can Issue Updates Regarding
                 *  Each Student's Attendance Status for the Current
                 *  Class Period.
                 */
                while(rs.next()) {
                    
                    /**
                     *  Store the Unique Integer Row ID Value
                     *  that is used to Identify the Attendance
                     *  Record Object in a Temporary String.
                     */
                    int id = rs.getInt("ID");
                    /**
                     *  Store the Unique Integer Row ID Value
                     *  that is used to Identify the Associated
                     *  Class Type Object in a Temporary String.
                     */
                    int classID = rs.getInt("ClassID");
                    /**
                     *  Store the Unique Integer Row ID Value
                     *  that is used to Identify the Associated
                     *  Instructor Account in a Temporary String.
                     */
                    int instructorID = rs.getInt("InstructorID");
                    /**
                     *  Store the Unique Integer Row ID Value
                     *  that is used to Identify the Associated
                     *  Student Account in a Temporary String.
                     */
                    int studentID = rs.getInt("StudentID");
                    /**
                     *  Store the Attendance Status String Within
                     *  a Temporary Variable.
                     */
                    String attendancestatus = rs.getString("Status");
                    /**
                     *  Store the String Containing the Attendance
                     *  Record Date Value Within a Temporary Variable.
                     */
                    String date = rs.getString("Date");
                    
                    /**
                     *  Loop through the List of Parameter Attendance
                     *  Record Objects until We Find the one which Matches
                     *  the Student Identification Number of the Current
                     *  Result Set Attendance Record Entry. These Objects 
                     *  Contain the Attendance Record Objects with a Value 
                     *  of "Present" Created From the Students Who were
                     *  Present During the Attendance Marking Process
                     *  Invoked by the Instructor.
                     */
                    for (int i = 0; i < value.size(); i++) {
                        
                        /**
                         *  If the Current Result Set Entry Student ID Value
                         *  is Equivalent to the Student ID Value Present in
                         *  the Current Parameter Attendance Record Object, then
                         *  Update the Contents of the Database Attendance Record
                         *  Entry With the Attendance Status Value of Present.
                         */
                        if (value.get(i).getAttendanceRecordStudentID() == studentID) {
                            /**
                             *  Reset the Prepared Statement Object Value For Our
                             *  Next Query.
                             */
                            ps = null;
        
                            /**
                             *  Initialize the New Query String Value.
                             */
                            ps = this.connection.prepareStatement(UPDATE_ATTENDANCE_RECORD);
                            /**
                             *  Inject the Unique Row ID Number Within the First
                             *  Injection Index of the Query Statement.
                             */
                            ps.setString(1, id + "");
                            /**
                             *  Inject the Unique Class ID Number Within the Second
                             *  Injection Index of the Query Statement.
                             */
                            ps.setString(2, classID + "");
                            /**
                             *  Inject the Unique Instructor Account ID Number Within
                             *  the Third Injection Index of the Query Statement.
                             */
                            ps.setString(3, instructorID + "");
                            /**
                             *  Inject the Unique Student Account ID Number Within
                             *  the Forth Injection Index of the Query Statement.
                             */
                            ps.setString(4, studentID + "");
                            /**
                             *  Inject the String Status Identifier into the
                             *  Query String.
                             */
                            ps.setString(5, "Present");
                            /**
                             *  Inject the String Current Date Identifier into the
                             *  Query String.
                             */
                            ps.setString(6, date);
                            /**
                             *  Inject the Blank Comment String into the
                             *  Query String.
                             */
                            ps.setString(7, "");
                            /**
                             *  Inject the Unique Row ID Integer Variable
                             *  of the Current Attendance Record into the
                             *  Final Injection Index. This Value is the
                             *  Search Value Preceeded by the WHERE Clause
                             *  in the Statement.
                             */
                            ps.setString(8, id + "");

                            /**
                             *  Execute the Update Query on the Current
                             *  Attendance Object Within the Database.
                             */
                            ps.executeUpdate();
            
                        }
                    }  
                }
            }
            /////////////////////////////////////////////
            
            // If Attendance Records For Today Are Already Present, Mark Those Who Were Originally
            // Absent as Present should they now be present within the Server. Those who were present
            // the first time but have now left the Server will still be marked as Present for the Current
            // Date.
            else { 
                /**
                *  Re-Initialize the Global Statement Variable in Case
                *  it is Currently Still Set From Another Database Query
                *  / Modification Operation.
                */
                //this.statement = (Statement) connection.createStatement();
                //rs = statement.executeQuery("SELECT * FROM Attendance WHERE ClassID = '" + value.get(0).getAttendanceRecordClassID()+ "' AND InstructorID = '" + value.get(0).getAttendanceRecordInstructorID() + "' AND Date = '" + value.get(0).getAttendanceRecordDate() + "'");
                
                /**
                *  Initialize the Prepared Statement Object as the Global
                *  String Query Statement Value. We Use a Prepared Statement
                *  Object in this Method seeing as we need to Inject Information
                *  Within the Query String before Execution.
                * 
                *  Query:  "SELECT * FROM Attendance WHERE ClassID = ? AND
                *           InstructorID = ? AND Date = ?"
                */
                ps = this.connection.prepareStatement(SELECT_ATTENDANCERECORDS_BY_PARAMETERS);
                /**
                 *  Inject the Unique Class ID Integer Variable Within
                 *  the First Injection Index of the Query String.
                 */
                ps.setInt(1, value.get(0).getAttendanceRecordClassID());
                /**
                 *  Inject the Unique Instructor ID Integer Variable Within
                 *  the Second Injection Index of the Query String.
                 */
                ps.setInt(2, value.get(0).getAttendanceRecordInstructorID());
                /**
                 *  Inject the Parameter Date String Variable Within
                 *  the Third Injection Index of the Query String.
                 */
                ps.setString(3, value.get(0).getAttendanceRecordDate());
                /**
                 *  Execute the Newly Created Query Statement Within the
                 *  Remote Database. Save the Returned Table Entries Within
                 *  the ResultSet Object rs.
                 */
                rs = ps.executeQuery();
                
                /**
                 *  Continuously Loop Through the Returned Attendance
                 *  Record Values. These Objects are the Attendance Record
                 *  Objects Associated with Today's Date.
                 */
                while(rs.next()) {
                    /**
                     *  Retrieve the Unique Row ID Integer
                     *  Value From the Current Result Set
                     *  Attendance Record Object.
                     */
                    int id = rs.getInt("ID");
                    /**
                     *  Retrieve the Unique Class ID Integer
                     *  Value From the Current Result Set
                     *  Attendance Record Object.
                     */
                    int classID = rs.getInt("ClassID");
                    /**
                     *  Retrieve the Unique Instructor ID Integer
                     *  Value From the Current Result Set
                     *  Attendance Record Object.
                     */
                    int instructorID = rs.getInt("InstructorID");
                    /**
                     *  Retrieve the Unique Student ID Integer
                     *  Value From the Current Result Set
                     *  Attendance Record Object.
                     */
                    int studentID = rs.getInt("StudentID");
                    /**
                     *  Retrieve the Attendance Status String
                     *  Value From the Current Result Set
                     *  Attendance Record Object.
                     */
                    String attendancestatus = rs.getString("Status");
                    /**
                     *  Retrieve the Attendance Record Date String
                     *  Value From the Current Result Set
                     *  Attendance Record Object.
                     */
                    String date = rs.getString("Date");
                    
                    /**
                     *  Loop Through the Parameter Attendance Record
                     *  Objects List that Contains the Attendance
                     *  Record Objects of All the Student's Which
                     *  are Currently Present in the Attendance Server.
                     */
                    for (int i = 0; i < value.size(); i++) {
                        /**
                         *  If the Student ID's between the Two Entries
                         *  Match, and the Attendance Status Field Contains
                         *  a Value of "Absent", then set the Attendance
                         *  Status to the Value of "Present" and Update
                         *  the Contents of the Attendance Record Entry
                         *  Within the Remote Database.
                         */
                        if (value.get(i).getAttendanceRecordStudentID() == studentID && attendancestatus.equalsIgnoreCase("Absent")) {
                            
                            /**
                             *  Re-Initialize the Prepared Statement Object in
                             *  Order to Remove any Previous Query Values that
                             *  may still be Present.
                             */
                            ps = null;
        
                            /**
                             *  Initialize the Prepared Statement Object Using
                             *  the Global Query String Variable.
                             */
                            ps = this.connection.prepareStatement(UPDATE_ATTENDANCE_RECORD);
                            /**
                            *   Set the Unique Row ID Integer Value of the
                            *   Current Attendance Record Object as the First
                            *   Injection Index Value within the Query String.
                            */
                            ps.setString(1, id + "");
                            /**
                            *   Set the Unique Class ID Integer Value of the
                            *   Current Attendance Record Object as the First
                            *   Injection Index Value within the Query String.
                            */
                            ps.setString(2, classID + "");
                            /**
                            *   Set the Unique Instructor ID Integer Value of the
                            *   Current Attendance Record Object as the First
                            *   Injection Index Value within the Query String.
                            */
                            ps.setString(3, instructorID + "");
                            /**
                            *   Set the Unique Student ID Integer Value of the
                            *   Current Attendance Record Object as the First
                            *   Injection Index Value within the Query String.
                            */
                            ps.setString(4, studentID + "");
                            /**
                             *  Set the Attendance Status Variable as the Value
                             *  of Present.
                             */
                            ps.setString(5, "Present");
                            /**
                             *  Set the Attendance Date Variable as the Value
                             *  of the Current Class Date.
                             */
                            ps.setString(6, date);
                            /**
                             *  Inject the Unique Row ID Integer Variable
                             *  of the Current Attendance Record into the
                             *  Final Injection Index. This Value is the
                             *  Search Value Preceeded by the WHERE Clause
                             *  in the Statement.
                             */
                            ps.setString(7, id + "");
                            /**
                            *  Execute the Newly Created Query Statement Within the
                            *  Remote Database. Save the Returned Table Entries Within
                            *  the ResultSet Object rs.
                            */
                            ps.executeUpdate();
                        }
                    }
                }
            }   
            
            /**
             *  Let the Calling GUI Class Know
             *  That the Overall Operation Was
             *  Successful.
             */
            result.setResult(true);
        }
        /**
        *  Catch a SQL Exception Should one Occur During the
        *  Query Process. These Types of Errors Typically
        *  Occur When the Chosen SQL Statements Are Unable
        *  to be Run through the Database, or an Error Occurs
        *  Within the Remote Database During Process Execution.
        */
        catch (SQLException e) {
            /**
            *  If the Status Value is the Value
            *  of Zero, then it Means that the
            *  New Account Wasn't Successfully
            *  Inserted. Thus, we need to Let
            *  the Calling GUI Class Know that
            *  the Account Wasn't Created.
            */
           if (status == 0) {
               /**
               *  Let the Calling GUI Class Know that the 
               *  Attendance Insertion Operation was a Failure.
               */
              result.setResult(false);
           }
        }
        /**
         *  Once We have Completed or Failed to Complete
         *  the Required Attendance Record Search, Insert,
         *  and Updated Query Statements, Attempt to Close the
         *  Prepared Statement Object. 
         */
        finally {

            try {
                /**
                 *  If the Prepared Statement Object isn't
                 *  Already a Null Value, then Close the
                 *  Object.
                 */
                if (ps != null) {
                    // Close Object Instance
                    ps.close();
                }
            }
            /**
            *  Catch a SQL Exception Should one Occur During the
            *  Query Process. These Types of Errors Typically
            *  Occur When the Chosen SQL Statements Are Unable
            *  to be Run through the Database, or an Error Occurs
            *  Within the Remote Database During Process Execution.
            */
            catch (SQLException e) {
                /**
                *  If the Status Value is the Value
                *  of Zero, then it Means that the
                *  New Account Wasn't Successfully
                *  Inserted. Thus, we need to Let
                *  the Calling GUI Class Know that
                *  the Account Wasn't Created.
                */
               if (status == 0) {
                   /**
                   *  Let the Calling GUI Class Know that the 
                   *  Attendance Insertion Operation was a Failure.
                   */
                  result.setResult(false);
               }
            }
        }
        
        /*
        try {
            
            // This Block is used to Get the Student IDs of All The Students
            // Taking this Class. This will be used to create Absent Records
            // once all of the 
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM StudentClassLink WHERE ClassID = '" + value.get(0).getAttendanceRecordClassID()  + "'");
            
            while(rs.next()) {
                int studentID = rs.getInt("StudentID");
                classRoster.add(studentID);
            }
            
            this.statement = (Statement) connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM Attendance WHERE ClassID = '" + value.get(0).getAttendanceRecordClassID() + "' AND InstructorID = '" + value.get(0).getAttendanceRecordInstructorID() + "' AND Date = '" + value.get(0).getAttendanceRecordDate() + "'");
            
            while(rs.next()) {
                int studentID = rs.getInt("StudentID");
                duplicateTracker.add(studentID);
            }
            
            for (int i = 0; i < value.size(); i++) {
                
                boolean isDuplicate = false;
                
                for (int j = 0; j < duplicateTracker.size(); j++) {
                    if (value.get(i).getAttendanceRecordStudentID() == duplicateTracker.get(j)) {
                        isDuplicate = true;
                        duplicateTracker.remove(j);
                        break;
                    }
                }
                
                if (isDuplicate == false) {
                    ps = this.connection.prepareStatement(INSERT_STUDENT_ATTENDANCE_RECORD);
                    ps.setInt(1, 0);
                    ps.setInt(2, value.get(i).getAttendanceRecordClassID());
                    ps.setInt(3, value.get(i).getAttendanceRecordInstructorID());
                    ps.setInt(4, value.get(i).getAttendanceRecordStudentID());
                    ps.setString(5, value.get(i).getAttendanceRecordStatus());
                    ps.setString(6, value.get(i).getAttendanceRecordDate());

                    numRowsInserted = ps.executeUpdate();
                }
            }
            
            this.statement = (Statement) connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM Attendance WHERE ClassID = '" + value.get(0).getAttendanceRecordClassID() + "' AND InstructorID = '" + value.get(0).getAttendanceRecordInstructorID() + "' AND Date = '" + value.get(0).getAttendanceRecordDate() + "'");
            
            duplicateTracker = new ArrayList<>();
            
            while(rs.next()) {
                int studentID = rs.getInt("StudentID");
                duplicateTracker.add(studentID);
            }
            
            System.out.println("DuplicateTracker Size: " + duplicateTracker.size());
            System.out.println("ClassRoster Size: " + classRoster.size());
            
            boolean entryPresent = false;
            
            for (int i = 0; i < classRoster.size(); i++) {
                
                entryPresent = false;
                
                for (int j = 0; j < duplicateTracker.size(); j++) {  
                    
                    if (classRoster.get(i) == duplicateTracker.get(j)) {
                        entryPresent = true;
                        break;
                    }
                    
                }
                
                if (entryPresent == false) {
                    ps = this.connection.prepareStatement(INSERT_STUDENT_ATTENDANCE_RECORD);
                    ps.setInt(1, 0);
                    ps.setInt(2, value.get(0).getAttendanceRecordClassID());
                    ps.setInt(3, value.get(0).getAttendanceRecordInstructorID());
                    ps.setInt(4, classRoster.get(i));
                    ps.setString(5, "Absent");
                    ps.setString(6, value.get(0).getAttendanceRecordDate());
                    
                    numRowsInserted = ps.executeUpdate();
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        */
        
        /**
         *  Close the Connection to Remote
         *  Database.
         */
        closeConnection();
        /**
         *  Return the Overall Result
         *  Value of the Database Operation.
         */
        return result;
    }
    
    /**
     *  This Method is Used in Order to Retrieve a List
     *  of all the Student Accounts Currently Present
     *  Within the Database.
     * 
     *  (Note: This Method is Only Used For Testing
     *  Purposes and is not Invoked Anywhere Else
     *  Within the Application.)
     */
    public void listStudents() {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            /**
             *  Select all of the Student Accounts and Their Associated
             *  Information that are Present Within the Database.
             */
            ResultSet rs = statement.executeQuery("SELECT * FROM Student");
            
            /**
             *  Iterate through all of the Database Entries Present
             *  Within the Result Set Object.
             */
            while(rs.next()) {
                /**
                 *  Retrieve the Integer Row ID 
                 *  Field Value.
                 */
                int id = rs.getInt("ID");
                /**
                 *  Retrieve the String "First Name"
                 *  Field Value.
                 */
                String firstName = rs.getString("FirstName");
                /**
                 *  Retrieve the String "Last Name"
                 *  Field Value.
                 */
                String lastName = rs.getString("LastName");
                /**
                 *  Retrieve the String "Username"
                 *  Field Value.
                 */
                String username = rs.getString("Username");
                /**
                 *  Retrieve the String "Password"
                 *  Field Value.
                 */
                String password = rs.getString("Password");
                /**
                 *  Retrieve the String "Email Address"
                 *  Field Value.
                 */
                String email = rs.getString("Email");
                
                /**
                 *  Print Values Out in a Identifier Output
                 *  String to the Terminal.
                 */
                System.out.println("Student ID: (" + id + ") Name: " + firstName + " " + lastName + " Username: " + username + " Password: " + password + " Email: " + email + "\n");
            }
        }
        // Catch Exception
        catch (SQLException e) {
            // Print Exception Message.
            System.out.println("Exception: " + e.getMessage());
        }
        
        // Close Connection to DB.
        closeConnection();
    }
    
    /**
     *  This Method is Used in Order to Retrieve a List
     *  of all the Instructor Accounts Currently Present
     *  Within the Database.
     * 
     *  (Note: This Method is Only Used For Testing
     *  Purposes and is not Invoked Anywhere Else
     *  Within the Application.)
     */
    public void listInstructors() {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            /**
             *  Select all of the Student Accounts and Their Associated
             *  Information that are Present Within the Database.
             */
            ResultSet rs = statement.executeQuery("SELECT * FROM Instructor");
            
            /**
             *  Iterate through all of the Database Entries Present
             *  Within the Result Set Object.
             */
            while(rs.next()) {
                /**
                 *  Retrieve the Integer Row ID 
                 *  Field Value.
                 */
                int id = rs.getInt("ID");
                /**
                 *  Retrieve the String "First Name"
                 *  Field Value.
                 */
                String firstName = rs.getString("FirstName");
                /**
                 *  Retrieve the String "Last Name"
                 *  Field Value.
                 */
                String lastName = rs.getString("LastName");
                /**
                 *  Retrieve the String "Username"
                 *  Field Value.
                 */
                String username = rs.getString("Username");
                /**
                 *  Retrieve the String "Password"
                 *  Field Value.
                 */
                String password = rs.getString("Password");
                /**
                 *  Retrieve the String "Email Address"
                 *  Field Value.
                 */
                String email = rs.getString("Email");
                
                /**
                 *  Print Values Out in a Identifier Output
                 *  String to the Terminal.
                 */
                System.out.println("Student ID: (" + id + ") Name: " + firstName + " " + lastName + " Username: " + username + " Password: " + password + " Email: " + email + "\n");
            }
        }
        // Catch Exception
        catch (SQLException e) {
            // Print Exception Message.
            System.out.println("Exception: " + e.getMessage());
        }
        
        // Close Connection to DB.
        closeConnection();
    }
    
    /**
     *  This Method is Used In Order to Insert the New
     *  Custom Record Date Object into the Remote Database.
     *  The Record Date Table is Included in the Database
     *  in order to Easier Access the Individual Attendance
     *  Dates that an Instructor has Marked for a Given Class.
     * 
     *  @param classID              The Unique Row ID Integer
     *                              Value of the Class Type
     *                              (Course) Object Associated
     *                              with the Desired Record
     *                              Date.
     * 
     *  @param date                 The Current Date Which Acts
     *                              as an Identifier Key For
     *                              Finding Record Date Entries.
     * 
     *  @return                     A Custom Object Which is Used
     *                              to Return the Overall Result of
     *                              The Database Operation in Addition
     *                              to Details Concerning any Errors
     *                              or Exceptions that May Occur During
     *                              the Process.
     */
    public DatabaseOperationResult insertRecordDate(int classID, String date) {
        
        /**
         *  A Custom Database Operation Result Variable Which will Act as
         *  the Return Variable for the Method.
         */
        DatabaseOperationResult result = new DatabaseOperationResult(false, "", 0);
        
        /**
         *  Initialize a Prepared Statement Object. This
         *  Object will be Used in Order to Create the
         *  Query Required for Creating a New Record Date
         *  Object Within the Remote Database.
         */
        PreparedStatement ps = null;
        
        /**
         *  Initialize a Integer Variable Which is Used
         *  in Order to Store the Amount of Rows Affected
         *  in the Insertion Process.
         */
        int status = 0;
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            /**
             *  Instantiate the Database Query By Initializing
             *  the Prepared Statement Object Using the Global
             *  String Variable.
             */
            ps = this.connection.prepareStatement(INSERT_DATE_RECORD);
            /**
             *  Set the Unique Row ID Integer as
             *  the First Injection Index in the Query.
             */
            ps.setInt(1, 0);
            /**
             *  Set the Unique Class ID Integer as
             *  the Second Injection Index in the Query.
             */
            ps.setInt(2, classID);
            /**
             *  Set the Unique Row ID Integer as
             *  the Third Injection Index in the Query.
             */
            ps.setString(3, date);
            /**
             *  Execute the Query.
             */
            ps.executeUpdate();
        }
        /**
        *  Catch a SQL Exception Should one Occur During the
        *  Query Process. These Types of Errors Typically
        *  Occur When the Chosen SQL Statements Are Unable
        *  to be Run through the Database, or an Error Occurs
        *  Within the Remote Database During Process Execution.
        */
        catch (SQLException e) {
            /**
            *  If the Status Value is the Value
            *  of Zero, then it Means that the
            *  New Record Date Wasn't Successfully
            *  Inserted. Thus, we need to Let
            *  the Calling GUI Class Know that
            *  the Record Date Wasn't Created.
            */
           if (status == 0) {
               /**
               *  Let the Calling GUI Class Know that the 
               *  Record Date Insertion Operation was a Failure.
               */
              result.setResult(false);
              /**
               *  Let the Calling GUI Class Know What Type of
               *  Error has Caused the Insertion Process
               *  to Fail.
               */
               result.setType("SQL Exception");
           }
        }
        /**
         *  Once We have Completed or Failed to Complete
         *  the Required Attendance Record Search, Insert,
         *  and Updated Query Statements, Attempt to Close the
         *  Prepared Statement Object. 
         */
        finally {

            try {
                /**
                 *  If the Prepared Statement Object isn't
                 *  Already a Null Value, then Close the
                 *  Object.
                 */
                if (ps != null) {
                    // Close Object Instance
                    ps.close();
                }
            }
            /**
            *  Catch a SQL Exception Should one Occur During the
            *  Query Process. These Types of Errors Typically
            *  Occur When the Chosen SQL Statements Are Unable
            *  to be Run through the Database, or an Error Occurs
            *  Within the Remote Database During Process Execution.
            */
            catch (SQLException e) {
                /**
                *  If the Status Value is the Value
                *  of Zero, then it Means that the
                *  New Record Date Wasn't Successfully
                *  Inserted. Thus, we need to Let
                *  the Calling GUI Class Know that
                *  the Record Date Wasn't Created.
                */
               if (status == 0) {
                   /**
                   *  Let the Calling GUI Class Know that the 
                   *  Record Date Insertion Operation was a Failure.
                   */
                  result.setResult(false);
                  /**
                   *  Let the Calling GUI Class Know What Type of
                   *  Error has Caused the Insertion Process
                   *  to Fail.
                   */
                  result.setType("SQL Exception");
               }
            }
        }
        
        /**
         *  Return the Custom Operation
         *  Result Object.
         */
        return result;
    }
    
    /**
     *  This Method is Used in Order to Determine Whether or Not a
     *  Record Date Object is Present Within the Database Associated
     *  with the Current Date.
     * 
     *  @param classID              The Unique Row ID Integer Value
     *                              Associated with the Specified Class
     *                              Type Object.
     * 
     *  @param date                 The String Value Containing the
     *                              Date Value Which We are Attempting
     *                              to Find the Record Date For.
     * 
     *  @return                     
     */
    public boolean checkForAttendanceRecordDate(int classID, String date) {
        
        /**
         *  A Custom Database Operation Result Variable Which will Act as
         *  the Return Variable for the Method.
         */
        DatabaseOperationResult result = new DatabaseOperationResult(false, "", 0);
        
        /**
         *  Initialize a Prepared Statement Object. This
         *  Object will be Used in Order to Create the
         *  Query Required for Creating a New Record Date
         *  Object Within the Remote Database.
         */
        PreparedStatement ps = null;
        
        /**
         *  Initialize a Integer Variable Which is Used
         *  in Order to Store the Amount of Rows Affected
         *  in the Selection Process.
         */
        int status = 0;
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();

        try {
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM RecordDate WHERE ClassID = '" + classID + "' AND Date = '" + date + "'");
            
            while(rs.next()) {
                
                int id = rs.getInt("ID");
                int classTypeID = rs.getInt("ClassID");
                String dateTimestamp = rs.getString("Date");
                
                System.out.println(id + classTypeID + dateTimestamp);
                
                closeConnection();
                return true;
            }
        }
        catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        closeConnection();
        
        return false;
    }
    
    /**
     *  This Method is Used in Order to Retrieve a List of Display Name
     *  Values Associated with the Student Identification Numbers in the
     *  Parameter ArrayList Object.
     * 
     *  @param studentIDs               An ArrayList Object Containing a
     *                                  List of All the Student Accounts
     *                                  Currently Enrolled Within the
     *                                  Chosen Instructor Class.
     * 
     *  @return                         An ArrayList Object Containing the
     *                                  Display Name Values of the Student
     *                                  Accounts Associated with the Student
     *                                  Identification Number ArrayList Above.
     */
    public ArrayList<String> getStudentNamesByID(ArrayList<Integer> studentIDs) {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();

        System.out.println("DatabaseMySQL: " + studentIDs.size());
        ArrayList<String> studentNamesList = new ArrayList<String>();
        
        try {
            
            for (int i = 0; i < studentIDs.size(); i++) {
                /**
                *  Re-Initialize the Global Statement Variable in Case
                *  it is Currently Still Set From Another Database Query
                *  / Modification Operation.
                */
                this.statement = (Statement) connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM Student WHERE StudentID = '" + studentIDs.get(i) + "'");
            
                while(rs.next()) {
                    String name = rs.getString("DisplayName");
                    
                    System.out.println("DatabaseMySQL: " + name);
                    studentNamesList.add(name);
                }
                
                System.out.println("Students Name List Size: " + studentNamesList.size());
            }
            
            closeConnection();
            return studentNamesList;
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        
        closeConnection();
        return null;
    }
    
    public ArrayList<String> getAssociatedClassDates(int classID) {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();

        ArrayList<String> classDatesList = new ArrayList<String>();
            
        try {
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM RecordDate WHERE ClassID = '" + classID + "'");

            while(rs.next()) {

                String date = rs.getString("Date");

                classDatesList.add(date);
            }
            
            closeConnection();
        
            return classDatesList;
        }
        catch (SQLException e) {
           e.printStackTrace();
        }
        
        closeConnection();
        
        return null;
    }
    
    public ArrayList<StudentRecordsTabObject> getStudentsByClass(int classID) {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        ArrayList<StudentRecordsTabObject> studentList = new ArrayList<StudentRecordsTabObject>();
        
        try {
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM StudentClassLink WHERE ClassID = '" + classID + "'");

            //SELECT_ATTENDANCERECORDS_BY_CLASSID_AND_DATE
            
            while(rs.next()) {
                int studentID = rs.getInt("StudentID");
                studentList.add(new StudentRecordsTabObject(studentID, ""));
            }

            for (int i = 0; i < studentList.size(); i++) {
                /**
                *  Re-Initialize the Global Statement Variable in Case
                *  it is Currently Still Set From Another Database Query
                *  / Modification Operation.
                */
                this.statement = (Statement) connection.createStatement();
                rs = statement.executeQuery("SELECT * FROM Student WHERE StudentID = '" + studentList.get(i).getStudentIDNumber() + "'");

                while(rs.next()) {
                    
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    
                    String fullName = firstName + " " + lastName;
                    
                    studentList.get(i).setStudentName(fullName);
                    System.out.println(fullName);
                }
            }
            
            closeConnection();
        
            return studentList;
        }
        catch (SQLException e) {
           e.printStackTrace();
        }
        
        /*Map<String, List> mapOfLists = new HashMap<String, List>();
        ArrayList<Integer> listOfStudentIDs = new ArrayList<Integer>();
        ArrayList<String> listOfStudentNames = new ArrayList<String>();
            
        try {
            
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM StudentClassLink WHERE ClassID = '" + classID + "'");

            while(rs.next()) {
                int studentID = rs.getInt("StudentID");
                listOfStudentIDs.add(studentID);
            }

            for (int i = 0; i < listOfStudentIDs.size(); i++) {
                
                this.statement = (Statement) connection.createStatement();
                rs = statement.executeQuery("SELECT * FROM Student WHERE StudentID = '" + listOfStudentIDs.get(i) + "'");

                while(rs.next()) {
                    
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    
                    String fullName = firstName + " " + lastName;
                    
                    listOfStudentNames.add(fullName);
                    System.out.println(fullName);
                }
            }
            
            mapOfLists.put("Student IDs", listOfStudentIDs);
            mapOfLists.put("Student Names", listOfStudentNames);
            
            closeConnection();
        
            return mapOfLists;
        }
        catch (SQLException e) {
           e.printStackTrace();
        }
        */
        closeConnection();
        
        return null;
    }
    
    public ArrayList<String> getAssociatedClassDatesByStudent(int classID, int studentID) {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();

        ArrayList<String> classDatesList = new ArrayList<String>();
        System.out.println(classID + " " + studentID);
        
        try {
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Attendance WHERE ClassID = '" + classID + "' AND StudentID = '" + studentID + "'");
            
            while(rs.next()) {

                String date = rs.getString("Date");
                System.out.println(date);

                classDatesList.add(date);
            }
            
            closeConnection();
        
            return classDatesList;
        }
        catch (SQLException e) {
           e.printStackTrace();
        }
        
        closeConnection();
        
        return null;
    }
    
    public ArrayList<AttendanceRecord> getStudentAttendanceRecordsByClass(int classID, String date) {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();

        ArrayList<AttendanceRecord> attendanceRecords = new ArrayList<AttendanceRecord>();
        
        System.out.println("Class ID: " + classID + " Date: " + date);
        
        try {
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Attendance WHERE ClassID = '" + classID + "' AND Date = '" + date + "'");
            
            while(rs.next()) {
                
                int id = rs.getInt("ID");
                int classid = rs.getInt("ClassID");
                int instructorID = rs.getInt("InstructorID");
                int studentID = rs.getInt("StudentID");
                String status = rs.getString("Status");
                String timestamp = rs.getString("Date");
                String comments = rs.getString("Comments");

                attendanceRecords.add(new AttendanceRecord(id, classid, instructorID, studentID, status, timestamp, comments));
            }
            
            System.out.println("DB Attendance Record List Size: " + attendanceRecords.size());
            closeConnection();
        
            return attendanceRecords;
        }
        catch (SQLException e) {
           e.printStackTrace();
        }
        
        closeConnection();
        
        return null;
    }
    
    public ArrayList<AttendanceRecord> getStudentAttendanceRecordsByStudent(int classID, int student, String date) {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();

        ArrayList<AttendanceRecord> attendanceRecords = new ArrayList<AttendanceRecord>();
        
        try {
            
            ResultSet rs;
            
            if (date.equalsIgnoreCase("ALL DATES")) {
                /**
                *  Re-Initialize the Global Statement Variable in Case
                *  it is Currently Still Set From Another Database Query
                *  / Modification Operation.
                */
                this.statement = (Statement) connection.createStatement();
                rs = statement.executeQuery("SELECT * FROM Attendance WHERE ClassID = '" + classID + "' AND StudentID = '" + student + "'");
            }
            else {
                /**
                *  Re-Initialize the Global Statement Variable in Case
                *  it is Currently Still Set From Another Database Query
                *  / Modification Operation.
                */
                this.statement = (Statement) connection.createStatement();
                rs = statement.executeQuery("SELECT * FROM Attendance WHERE ClassID = '" + classID + "' AND StudentID = '" + student + "' AND Date = '" + date + "'");
            
            }
            
            while(rs.next()) {
                
                int id = rs.getInt("ID");
                int classid = rs.getInt("ClassID");
                int instructorID = rs.getInt("InstructorID");
                int studentID = rs.getInt("StudentID");
                String status = rs.getString("Status");
                String timestamp = rs.getString("Date");
                String comments = rs.getString("Comments");

                attendanceRecords.add(new AttendanceRecord(id, classid, instructorID, studentID, status, timestamp, comments));
            }
            
            closeConnection();
        
            return attendanceRecords;
        }
        catch (SQLException e) {
           e.printStackTrace();
        }
        
        closeConnection();
        
        return null;
    }
    
    public boolean updateAttendanceRecord(AttendanceRecord record) {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        int id = 0;

        try {
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Attendance WHERE ClassID = '" + record.getAttendanceRecordClassID() + "' AND InstructorID = '" + record.getAttendanceRecordInstructorID() + "' AND StudentID = '" + record.getAttendanceRecordStudentID() + "' AND Date = '" + record.getAttendanceRecordDate() + "'");
            
            while(rs.next()) {           
                id = rs.getInt("ID");
            }
            
            PreparedStatement ps = null;
        
            ps = this.connection.prepareStatement(UPDATE_ATTENDANCE_RECORD);
            
            ps.setInt(1, id);
            ps.setInt(2, record.getAttendanceRecordClassID());
            ps.setInt(3, record.getAttendanceRecordInstructorID());
            ps.setInt(4, record.getAttendanceRecordStudentID());
            ps.setString(5, record.getAttendanceRecordStatus());
            ps.setString(6, record.getAttendanceRecordDate());
            ps.setString(7, record.getAttendanceRecordComments());
            ps.setInt(8, id);
            
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        closeConnection();
        
        return true;
    }
    
    public int insertNewClassType(ClassType value) {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        PreparedStatement ps = null;
        int id = -1;
        
        try {
            ps = this.connection.prepareStatement(INSERT_CLASSTYPE);
            
            ps.setString(1, value.getClassField());
            ps.setInt(2, value.getClassNumber());
            ps.setString(3, value.getClassTitle());
            ps.setString(4, value.getClassStructureType());
            ps.setString(5, value.getClassDescription());
            ps.setString(6, value.getClassCreditHours());
            ps.setString(7, value.getClassSection());
            ps.setString(8, value.getClassSemester());
            ps.setInt(9, value.getClassYear());
            ps.setInt(10, value.getClassParticipants());
            ps.executeUpdate();
            
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT last_insert_id()");
            
            while(rs.next()) {
                id = rs.getInt("last_insert_id()");
            }
            
        }
        catch (CommunicationsException ce) {
            ce.printStackTrace();
            return -2;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } 
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        closeConnection();
        return id;
    }
    
    public boolean createInstructorClassLink(int classID, int instructorID) {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        PreparedStatement ps = null;
        
        try {
            ps = this.connection.prepareStatement(INSERT_INSTRUCTOR_CLASS_LINK);
            
            ps.setInt(1, 0);
            ps.setInt(2, classID);
            ps.setInt(3, instructorID);
 
            ps.executeUpdate();

        } 
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        closeConnection();
        return true;
    }
    
    public boolean removeInstructorClassLinkAndConnections(int classID, int instructorID) {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        PreparedStatement ps = null;
        int[] results = {0, 0, 0};
        
        System.out.println("Getting Here in remove Records MYSQL 1");
        
        try {
            
            ps = this.connection.prepareStatement(REMOVE_INSTRUCTOR_CLASS_LINK);
            
            ps.setInt(1, classID);
            ps.setInt(2, instructorID);
 
            results[0] = ps.executeUpdate();

            System.out.println("Getting Here in remove Records MYSQL 2");

            ps = this.connection.prepareStatement(REMOVE_ATTENDANCE_RECORDS);
            
            ps.setInt(1, classID);
            ps.setInt(2, instructorID);
 
            results[1] = ps.executeUpdate();
            
            System.out.println("Getting Here in remove Records MYSQL 3");
            
            ps = this.connection.prepareStatement(REMOVE_RECORD_DATES);
            
            ps.setInt(1, classID);
 
            results[2] = ps.executeUpdate();
            
            System.out.println(results[0] + " " + results[1] + " " + results[2]);
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        closeConnection();
        return true;
    }
    
    public boolean checkForDuplicateClass(ClassType value) {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
            
        try {
            
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Class WHERE Title = '" + value.getClassTitle() + "' AND Semester = '" + value.getClassSemester() + "' AND Section = '" + value.getClassSection() + "'");

            while(rs.next()) {

                int id = rs.getInt("ID");
                String field = rs.getString("Field");
                int number = rs.getInt("ClassNumber");
                String className = rs.getString("Title");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                String creditHours = rs.getString("Credits");
                String section = rs.getString("Section");
                String semester = rs.getString("Semester");
                int year = rs.getInt("Year");
                int participants = rs.getInt("Participants");

                ClassType classType = new ClassType(id, field, number, className, type, description, creditHours, section, semester, year, participants);
                
                if (classType.getClassTitle().equalsIgnoreCase(value.getClassTitle()) 
                    && classType.getClassField().equalsIgnoreCase(value.getClassField()) 
                    && classType.getClassSemester().equalsIgnoreCase(value.getClassSemester()) 
                    && classType.getClassSection().equalsIgnoreCase(value.getClassSection()) 
                    && classType.getClassYear() == value.getClassYear()) {
                    
                    return true;
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        closeConnection();
        
        return false;
    }
    
    public boolean removeClass(int classID, int instructorID) {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        PreparedStatement ps = null;
        int[] results = {0, 0, 0, 0};
        
        try {
            
            ps = this.connection.prepareStatement(REMOVE_CLASS);
            
            ps.setInt(1, classID);
 
            results[0] = ps.executeUpdate();
            
            ps = this.connection.prepareStatement(REMOVE_INSTRUCTOR_CLASS_LINK);
            
            ps.setInt(1, classID);
            ps.setInt(2, instructorID);
 
            results[1] = ps.executeUpdate();

            ps = this.connection.prepareStatement(REMOVE_ATTENDANCE_RECORDS);
            
            ps.setInt(1, classID);
            ps.setInt(2, instructorID);
 
            results[2] = ps.executeUpdate();
            
            ps = this.connection.prepareStatement(REMOVE_RECORD_DATES);
            
            ps.setInt(1, classID);
 
            results[3] = ps.executeUpdate();
            
            System.out.println(results[0] + " " + results[1] + " " + results[2]);
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        closeConnection();
        return true;
    }
    
    public ArrayList<ClassType> findUnlinkedClasses() {
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        ArrayList<ClassType> classTypes = new ArrayList<>();
            
        try {
            
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `Class` WHERE `ID` NOT IN (SELECT `ClassID` FROM `InstructorClassLink`)");

            while(rs.next()) {

                int id = rs.getInt("ID");
                String field = rs.getString("Field");
                int number = rs.getInt("ClassNumber");
                String className = rs.getString("Title");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                String creditHours = rs.getString("Credits");
                String section = rs.getString("Section");
                String semester = rs.getString("Semester");
                int year = rs.getInt("Year");
                int participants = rs.getInt("Participants");

                ClassType classType = new ClassType(id, field, number, className, type, description, creditHours, section, semester, year, participants);
                
                classTypes.add(classType);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return classTypes;
        }
        
        closeConnection();
        return classTypes;
    }
    
    /**
     *  This Method is Used in Order to Retrieve the Student Account
     *  Information Entries Associated with the Unique Row ID Number
     *  of the Corresponding Class Type Object.
     * 
     *  @param classID                  The Unique Integer Row ID Number
     *                                  Associated with the Instructor's
     *                                  Chosen Class Type.
     *                                  
     *  @return                         An ArrayList Object Containing all
     *                                  of the Student Account Information
     *                                  Entries Associated with the Current
     *                                  Class Type Object.
     */
    public ArrayList<Student> getStudentsAssociatedWithClass(int classID) {
        
        /**
         *  Create an ArrayList Object Which will be Used in Order
         *  to Hold the Unique Integer ID Values of the Student
         *  Accounts Currently Enrolled in the Selected Course.
         */
        ArrayList<Integer> studentIDs = new ArrayList<Integer>();
        /**
         *  Create an ArrayList Object Which will be Used in Order
         *  to Hold the Student Account Information Values that
         *  Correspond to the List of Student ID's Declared Above.
         */
        ArrayList<Student> studentByID = new ArrayList<Student>();
        
        /**
         *  Initialize a Prepared Statement Object. This
         *  Object will be Used in Order to Retrieve
         *  the Student ID and Account Information Variables
         *  From Within the Database.
         */
        PreparedStatement ps = null;
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            
            //private final String SELECT_STUDENT_IDS_BY_CLASS = "SELECT * FROM `StudentClassLink` WHERE ClassID = ";
            //private final String SELECT_STUDENTS_BY_STUDENTID = "SELECT * FROM `Student` WHERE StudentID = ";
    
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            //this.statement = (Statement) connection.createStatement();
            //ResultSet rs = statement.executeQuery(SELECT_STUDENT_IDS_BY_CLASS);
            
            /**
             *  Instantiate the Database Query By Initializing
             *  the Prepared Statement Object Using the Global
             *  String Variable.
             */
            ps = this.connection.prepareStatement(SELECT_STUDENT_IDS_BY_CLASS);
            /**
             *  Set the Unique Class Object Row ID Integer as
             *  the Injection Index in the Query.
             */
            ps.setInt(1, 0);
            
            /**
             *  Execute the Query Within the Remote Database
             *  and Store all of the Returned Database Entries
             *  Within the ResultSet Object rs.
             */
            ResultSet rs = ps.executeQuery();
            
            /**
             *  Loop Through All Elements Present
             *  in the Result Set Variable.
             */
            while(rs.next()) {
                /**
                 *  Retrieve the Unique Student
                 *  Identification Number Present
                 *  Within the Current Entry of
                 *  the ResultSet Object.
                 */
                int id = rs.getInt("StudentID");
                /**
                 *  Add the Return Value to the
                 *  ArrayList Object of Student
                 *  ID Integer Values.
                 */
                studentIDs.add(id);
            }

            /**
             *  In this Next Step, we will Loop Through All the
             *  Student ID Integer Entries Present Within the Newly
             *  Created ArrayList Object. For Each Student ID Value
             *  within the ArrayList, we will Retrieve the Student
             *  Account Information Associated With it. This Data
             *  Will then be Added to the Student Object Array.
             */
            for (int i = 0; i < studentIDs.size(); i++) {
                /**
                *  Re-Initialize the Global Statement Variable in Case
                *  it is Currently Still Set From Another Database Query
                *  / Modification Operation.
                */
                //this.statement = (Statement) connection.createStatement();
                //rs = statement.executeQuery(SELECT_STUDENTS_BY_STUDENTID + studentIDs.get(i));
                
                /**
                *  Instantiate the Database Query By Initializing
                *  the Prepared Statement Object Using the Global
                *  String Variable.
                */
                ps = this.connection.prepareStatement(SELECT_STUDENTS_BY_STUDENTID);
               /**
                *  Set the Unique Class Object Row ID Integer as
                *  the Injection Index in the Query.
                */
               ps.setInt(1, 0);

               /**
                *  Execute the Query Within the Remote Database
                *  and Store all of the Returned Database Entries
                *  Within the ResultSet Object rs.
                */
               rs = ps.executeQuery();

                /**
                 *  Loop Through All of the Student Account Entries
                 *  Returned in the Result Set Object. Seeing as
                 *  Each Student Identification Number Value Only
                 *  Corresponds to a Single Student Account, this
                 *  While Loop Statement Will Only Execute Once.
                 */
                while(rs.next()) {
                    /**
                     *  Retrieve the Unique Row ID Integer
                     *  Value Assigned to the Current Student
                     *  Account by the Database.
                     */
                    int id = rs.getInt("ID");
                    /**
                     *  Retrieve the Unique Student Identification
                     *  Number Value Associated with the Current
                     *  Result Set Object Entry.
                     */
                    int studentNumber = rs.getInt("StudentID");
                    /**
                     *  Retrieve the "Username" String Value
                     *  Associated With the Current Result Set
                     *  Object Entry.
                     */
                    String username = rs.getString("Username");
                    /**
                     *  Retrieve the "Email Address" String Value
                     *  Associated With the Current Result Set
                     *  Object Entry.
                     */
                    String email = rs.getString("Email");
                    /**
                     *  Retrieve the "Display Name" String Value
                     *  Associated With the Current Result Set
                     *  Object Entry.
                     */
                    String displayname = rs.getString("DisplayName");
                    /**
                     *  Retrieve the "Phone Number" String Value
                     *  Associated With the Current Result Set
                     *  Object Entry.
                     */
                    String phone = rs.getString("Phone");
                    /**
                     *  Retrieve the "Address" String Value
                     *  Associated With the Current Result Set
                     *  Object Entry.
                     */
                    String address = rs.getString("Address");
                    /**
                     *  Retrieve the String Containing the Directory
                     *  Path Within the Application's Files to the
                     *  Location of the Current Student's Profile
                     *  Image (Small Version).
                     */
                    String imageSmall = rs.getString("ImageSmall");

                    /**
                     *  Use the Previously Retrieved Student Account
                     *  Information to Create a Custom Student Object.
                     */
                    Student student = new Student(id, studentNumber, "", "", email, username, "", displayname, phone, address, "", "", "", imageSmall, "");

                    /**
                     *  Add the new Custom Object to the Student
                     *  Account Object ArrayList.
                     */
                    studentByID.add(student);
                }
            }
            
            /**
             *  Close the Connection to the
             *  Remote Database.
             */
            closeConnection();
            /**
             *  Return the New ArrayList
             *  Object Value.
             */
            return studentByID;
        }
        /**
         *  Catch a Communications Exception
         *  should one Occur. These Types of
         *  Exceptions Typically Happen Due
         *  to the Failure of the User's Internet
         *  Connection Mid Process Execution.
         */
        catch (CommunicationsException ce) {
            /**
             *  Close the Connection to the
             *  Remote Database.
             */
            closeConnection();
            /**
             *  Return a Value of Null Seeing
             *  as we have Encountered an
             *  Exception.
             */
            return null;
        }
        /**
         *  Catch a SQL Exception should
         *  once Occur. These Types of Exceptions
         *  Typically Occur When there is an
         *  Error in the Syntax of the Query
         *  Statement or the Remote Database
         *  Encounters an Internal Error.
         */
        catch (SQLException e) {
            /**
             *  Close the Connection to the
             *  Remote Database.
             */
            closeConnection();
            /**
             *  Return a Value of Null Seeing
             *  as we have Encountered an
             *  Exception.
             */
            return null;
        }
    }
    
    /**
     *  This method is Responsible for Deleting the Chosen
     *  Student From the Currently Selected Class Within the
     *  Students In Class Table Segment of the Manage Students
     *  Panel in the MainScreen Management Tab. This Method
     *  will also Delete All Past Attendance Records Associated With
     *  The Current Student and Class.
     * 
     *  @param classID              The Row ID Number of the Currently
     *                              Selected Class.
     * 
     *  @param studentID            The Unique Identification Number
     *                              of the Student we Are Trying to
     *                              Remove From the Class.
     * 
     *  @param lastStudent          A Boolean Value Letting Us Know
     *                              Whether or Not That the Student We Are
     *                              Trying To Remove is The Last Student
     *                              Within the Class. If this Value is True,
     *                              the method will also Delete All of the
     *                              Attendance Record Dates Associated With
     *                              the Current Class.
     * 
     *  @return                     
     */
    public DatabaseOperationResult removeStudentFromClass(int classID, int studentID, boolean lastStudent) {
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        // Create Custom Query String to Delete The
        // Link Between the Current Class and Student
        String REMOVE_STUDENT_CLASS_LINK = "DELETE FROM `StudentClassLink` WHERE ClassID = " + classID + " AND StudentID = " + studentID + "";
        // Create Custom Query String to Delete All the
        // Previous Attendance Records Associated With
        // The Student About to Be Removed.
        String REMOVE_STUDENT_ATTENDANCE_RECORDS = "DELETE FROM `Attendance` WHERE ClassID = " + classID + " AND StudentID = " + studentID + "";
        // Create Query String to Delete All the Previous
        // Attendance Record Dates For this Class Should
        // the Student We Are Removing be the Last Student
        // in the Class.
        String REMOVE_ASSOCIATED_RECORD_DATES = "DELETE FROM `RecordDate` WHERE ClassID = " + classID;
        
        // Instantiate our Default Return Value Object.
        // This object is responsible for Determining
        // the Overall Result of the Database Operation
        // and Monitoring The Methods Progress Should
        // an Error Occur.
        DatabaseOperationResult returnValue = new DatabaseOperationResult(true, "", 0);
        // Instantiate an Object to Hold
        // our Query Statements.
        PreparedStatement ps = null;
        
        try {
            // Apply Our Student Removal From Class String
            // to our Query Prepared Statement Object.
            ps = this.connection.prepareStatement(REMOVE_STUDENT_CLASS_LINK);
            // Execute the Query to Remove the Selected
            // Student From the Class.
            ps.executeUpdate();
            
            // Let the Calling Class Know That The First
            // Removal Query Has Successfully Executed.
            returnValue.setProgressionStatus(1);
            
            // Apply Our Student Attendance Record Removal String
            // to our Query Prepared Statement Object.
            ps = this.connection.prepareStatement(REMOVE_STUDENT_ATTENDANCE_RECORDS);
            // Execute the Query to Remove All Student
            // Attendance Records For the Current Student
            // Associated With the Chosen Class.
            ps.executeUpdate();
            
            // Let the Calling Class Know That The Second
            // Removal Query Has Successfully Executed.
            returnValue.setProgressionStatus(2);
            
            // If the Student Being Deleted is the last
            // Student in the Class, Delete All Previous
            // Attendance RecordDate Entries for this Class.
            if (lastStudent == true) {
                // Apply Our Record Date Record Removal String
                // to our Query Prepared Statement Object.
                ps = this.connection.prepareStatement(REMOVE_ASSOCIATED_RECORD_DATES);
                // Execute the Query to Remove All Record
                // Date Entries Associated With the Current
                // Class.
                ps.executeUpdate();
            }
            
            // Let the Calling Class Know That The Third
            // Removal Query Has Successfully Executed.
            returnValue.setProgressionStatus(3);
        }
        // Catch Communications Exception should it
        // occur. This Exception is usually caused when
        // the Current User of the Application Loses Connection
        // to the Internet and subsequently the Database.
        catch (CommunicationsException ce) {
            // Let the Calling Class Know That An Exception
            // has Occured And the Operation Has Failed.
            returnValue.setResult(false);
            // Let the Calling Class Know that A Communications
            // Exception was the Reason for the Failure.
            returnValue.setType("Communications Exception");
        }
        // Catch SQL Exception should it occur. This
        // Exception is usually caused when an Error
        // occurs within Remote Database or something
        // is wrong with the chosen Query.
        catch (SQLException e) {
            // Let the Calling Class Know That An Exception
            // has Occured And the Operation Has Failed.
            returnValue.setResult(false);
            // Let the Calling Class Know that A Communications
            // Exception was the Reason for the Failure.
            returnValue.setType("SQL Exception");
        }
        // Regardless of Whether or Not An Exception Has
        // Occured, Close the Prepared Statement.
        finally {
            try {
                // Close Prepared Statement Query
                // Object.
                if (ps != null) {
                    ps.close();
                }
            } 
            // Catch SQL Exception should it occur. This
            // Exception is usually caused when an Error
            // occurs within Remote Database or something
            // is wrong with the chosen Query.
            catch (SQLException e) {
                // Let the Calling Class Know That An Exception
                // has Occured And the Operation Has Failed.
                returnValue.setResult(false);
                // Let the Calling Class Know that A Communications
                // Exception was the Reason for the Failure.
                returnValue.setType("SQLException");
            }
        }
        
        // Close Connection to the Database.
        closeConnection();
        
        // Return the Results of the Database
        // Operations
        return returnValue;
    }  
    
    /**
     *  This Method is Responsible for Determining Whether or
     *  not a Student using the Specified Username String Variable is Present
     *  within the the Remote Database. This Method is invoked from within
     *  the AddStudentWindow JFrame Form. It is one of the Two Database Methods
     *  used within the Class to Find Student Account Presence.
     * 
     *  @param  username        The Username of the Student we are Trying
     *                          to locate.
     * 
     *  @return student         A Student Object containing either the desired
     *                          Student's information or an Error / Student Not
     *                          Found Value.
     */
    public Student findStudentByUsername(String username) {

        // Create Default Student Object. Seeing as we aren't retrieving the Student's
        // Database Entry Number in this Method, we will instead use this Value to Show
        // how the Operation has performed.
        //
        // 0 - Operation Performed Successfully
        // 1 - Student Not Found
        // 2 - Communication's Exception
        // 3 - SQL Exception
        //
        // The -1 within the Student Entry Number Field Will Be Used to
        // to Determine if there is no Student found using the Desired Identification
        // Number.
        Student student = new Student(1, 0, "", "", "", "", "", "", "", "", "", "", "", "", "");

        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            // Create ResultSet Object to Hold the Results of the Database
            // Query.
            ResultSet rs = statement.executeQuery("SELECT * FROM `Student` WHERE Username = \"" + username + "\"");
            
            // Loop through All the Data Rows Returned
            // By the Previous Query.
            while(rs.next()) {
                
                // Get the Student's University Issued
                // Identification Number.
                int studentID = rs.getInt("StudentID");
                // Get the Student's Associated
                // Email Address
                String email = rs.getString("Email");
                // Get the Student's Associated
                // Phone Number
                String phone = rs.getString("Phone");
                // Get the Student's Generated
                // Display Name
                String name = rs.getString("DisplayName");
                // Get the Student's
                // Physical Address
                String address = rs.getString("Address");
                // Get the Small Version of the
                // Student's Chosen Profile Picture.
                String imageSmall = rs.getString("ImageSmall");
                
                // Combine the Pulled Information Into A New Student
                // Object and Set the Local Variable. Set First Student
                // Field as 0 to signify that the Operation was performed
                // Successfully and the Information has been pulled.
                student = new Student(0, studentID, "", "", email, username, "", name, phone, address, "", "", "", imageSmall, "");
            }
        }
        // Catch Communications Exception should it
        // occur. This Exception is usually caused when
        // the Current User of the Application Loses Connection
        // to the Internet and subsequently the Database.
        catch (CommunicationsException ce) {
            // Let the AddStudentWindow know
            // that a CommunicationsException has
            // occured by changing the RowID Value
            // of the Local Student Objects RowID Field
            // to the value of 2.
            student.setRowID(2);
        }
        // Catch SQL Exception should it occur. This
        // Exception is usually caused when an Error
        // occurs within Remote Database or something
        // is wrong with the chosen Query.
        catch (SQLException ex) {
            // Let the AddStudentWindow know
            // that a SQLException has occured
            // by changing the RowID Value of the
            // Local Student Objects RowID Field
            // to the value of 3.
            student.setRowID(3);
        }
        
        // Close the Connection to the Remote
        // Database.
        closeConnection();
        // Whether there is a Student using the
        // specified Username within the Database
        // or not, or an error has been thrown
        // return the Local Student Variable
        return student;
    }
    
    /**
     *  This Method is Responsible for Determining Whether or
     *  not a Student using the Specified Student Identification Number Integer
     *  Variable is Present within the the Remote Database. This Method is
     *  invoked from within the AddStudentWindow JFrame Form. It is one of the 
     *  Two Database Methods used within the Class to Find Student Account Presence.
     * 
     *  @param  studentID       The Unique Identification Number of the
     *                          Student we are Trying to locate.
     * 
     *  @return student         A Student Object containing either the desired
     *                          Student's information or an Error / Student Not
     *                          Found Value.
     */
    public Student findStudentByIdentificationNumber(int studentID) {
        
        // Create Default Student Object. Seeing as we aren't retrieving the Student's
        // Database Entry Number in this Method, we will instead use this Value to Show
        // how the Operation has performed.
        //
        // 0 - Operation Performed Successfully
        // 1 - Student Not Found
        // 2 - Communication's Exception
        // 3 - SQL Exception
        //
        // The -1 within the Student Entry Number Field Will Be Used to
        // to Determine if there is no Student found using the Desired Username.
        Student student = new Student(1, 0, "", "", "", "", "", "", "", "", "", "", "", "", "");
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            // Create ResultSet Object to Hold the Results of the Database
            // Query.
            ResultSet rs = statement.executeQuery("SELECT * FROM `Student` WHERE StudentID = \"" + studentID + "\"");
            
            // Loop through All the Data Rows Returned
            // By the Previous Query.
            while(rs.next()) {
                
                // Get the Student's Associated
                // Phone Number
                String username = rs.getString("Username");
                // Get the Student's Associated
                // Email Address
                String email = rs.getString("Email");
                // Get the Student's Associated
                // Email Address
                String phone = rs.getString("Phone");
                // Get the Student's Generated
                // Display Name
                String name = rs.getString("DisplayName");
                // Get the Student's
                // Physical Address
                String address = rs.getString("Address");
                // Get the Small Version of the
                // Student's Chosen Profile Picture.
                String imageSmall = rs.getString("ImageSmall");
                
                // Combine the Pulled Information Into A New Student
                // Object and Set the Local Variable. Set First Student
                // Field as 0 to signify that the Operation was performed
                // Successfully and the Information has been pulled.
                student = new Student(0, studentID, "", "", email, username, "", name, phone, address, "", "", "", imageSmall, "");
            }
        }
        // Catch Communications Exception should it
        // occur. This Exception is usually caused when
        // the Current User of the Application Loses Connection
        // to the Internet and subsequently the Database.
        catch (CommunicationsException ce) {
            // Let the AddStudentWindow know
            // that a CommunicationsException has
            // occured by changing the RowID Value
            // of the Local Student Objects RowID Field
            // to the value of 2.
            student.setRowID(2);
        }
        // Catch SQL Exception should it occur. This
        // Exception is usually caused when an Error
        // occurs within Remote Database or something
        // is wrong with the chosen Query.
        catch (SQLException ex) {
            // Let the AddStudentWindow know
            // that a SQLException has occured
            // by changing the RowID Value of the
            // Local Student Objects RowID Field
            // to the value of 3.
            student.setRowID(3);
        }
        
        // Close the Connection to the Remote
        // Database.
        closeConnection();
        // Whether there is a Student using the
        // specified Username within the Database
        // or not, or an error has been thrown
        // return the Local Student Variable
        return student;
    }
    
    /**
     *  This method is responsible for Checking Whether the
     *  Chosen Student is already part of the Current Class Roster.
     *  This method is Called From the AddStudentWindow JFrame
     *  Form Which is an extension of the Manage Students Tab
     *  located in the Management Tab of the Applications MainScreen.
     * 
     *  @param studentID        The Unique University Issued Student
     *                          Identification Number Associated With
     *                          the chosen Student.
     * 
     *  @param classID          The Database Row ID Associated With the
     *                          the Currently Selected Class.
     * 
     *  @return                 An Integer Value that is used to Determine
     *                          Whether the Operation Was Performed Successfully
     *                          and if the Student is Already Present Within
     *                          the Class or Not.
     * 
     *  Return Value Range:     (0 - ∞) -   The Student is Present Within the Class.
     *                          (-1)    -   The Student is Not Present Within the Class.
     *                          (-2)    -   A Communications Exception Has occurred.
     *                          (-3)    -   A SQL Exception has occurred.
     * 
     */
    public int checkForStudentInClass(int studentID, int classID) {
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        // The Return Value Number. Changes
        // if Student is Already Present Within
        // the Class or Exception Occurs.
        int id = -1;
        
        try {
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            // Create ResultSet Object to Hold the Results of the Database
            // Query.
            ResultSet rs = statement.executeQuery("SELECT ID FROM `StudentClassLink` WHERE ClassID = \"" + classID + "\" AND StudentID = \"" + studentID + "\"");
            
            // Loop Through All the Rows Returned 
            // By the Query.
            while(rs.next()) {
                // Try to Retrieve the Row ID
                // of the Student Class Link Entry
                // if one is Present in the Result Set.
                id = rs.getInt("ID");
            }
        }
        // Catch Communications Exception should it
        // occur. This Exception is usually caused when
        // the Current User of the Application Loses Connection
        // to the Internet and subsequently the Database.
        catch (CommunicationsException ce) {
            // Let the AddStudentWindow know
            // that a CommunicationsException has
            // occured by changing the return value
            // to -2.
            id = -2;
        }
        // Catch SQL Exception should it occur. This
        // Exception is usually caused when an Error
        // occurs within the Remote Database or something
        // is wrong with the chosen Query.
        catch (SQLException ex) {
            // Let the AddStudentWindow know
            // that a SQL Exception has
            // occured by changing the return value
            // to -3.
            id = -3;
        }
        
        // Close the Connection to the Remote
        // Database.
        closeConnection();
        // Return the Specified ID Value.
        return id;
    }
    
    /**
     *  This method is responsible for Creating
     *  an Entry in the Student Class Link Table
     *  which is used to Determine Whether or Not
     *  A Student is Currently Enrolled in A Class. 
     *  Additionally, should the Current Class Already
     *  Have Previous Attendance Dates Marked for Other
     *  Enrolled Students, this method will create Attendance
     *  Records for the Newly Added Student During those Previous
     *  Dates. The Attendance Status Associated With Each of
     *  these new Attendance Record Entries will be marked
     *  with the Designation "NYA" which means the Student
     *  had not been added to the Class During these Dates.
     *  These Records will then be Excluded During
     *  Future Attendance Grade Calculations. This Method
     *  is Called From the Add Student Window which
     *  is invoked by Clicking the Add Student Button
     *  Located within the Students In Class Table
     *  Segment of the Management Tab, Manage Students
     *  Panel.
     * 
     *  @param studentID        The Unique University Issued Student
     *                          Identification Number Associated With
     *                          the chosen Student.
     * 
     *  @param instructorID     The Database Row Data ID Number Associated With
     *                          the Currently Logged In Instructor.
     * 
     *  @param classID          The Database Row ID Number Associated With the
     *                          the Currently Selected Class.
     * 
     *  @return 
     */
    public DatabaseOperationResult createStudentClassLinkStudentsInClass(int studentID, int instructorID, int classID) {
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        // Create New Database Operation Result Object which
        // is responsible for holding the final result of the following
        // Operations.
        DatabaseOperationResult result = new DatabaseOperationResult(false, "", 0);
        // Create An ArrayList Object which will be
        // used to retrieve the Dates Associated with
        // the Classes Previous Attendance Records Should
        // any Exist.
        ArrayList<String> datesList = new ArrayList<>();
        // Instantiate A Prepared Statement Object
        // which will be used to Query / Update the Database
        // once we inject the specified Data Parameters.
        PreparedStatement ps = null;
        
        try {
            // Set the Prepared Statement Value to Use the Global String
            ps = this.connection.prepareStatement(INSERT_STUDENT_CLASS_LINK);
            
            // Set the First Question Mark
            // Parameter. We Assign Zero for
            // this Value seeing as the Database
            // will Auto Increment the Row ID Value.
            ps.setInt(1, 0);
            // Set the Second Question Mark
            // Parameter.
            ps.setInt(2, classID);
            // Set the Third Question Mark
            // Parameter.
            ps.setInt(3, studentID);
 
            // Execute the Database Update
            // Statement.
            ps.executeUpdate();
            
            // Let the Program Know That the First
            // Step in the Database Modification / Retrival
            // Has Been Completed.
            result.setProgressionStatus(1);
            
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            // Create ResultSet Object to Hold the Results of the Database
            // Query.
            ResultSet rs = statement.executeQuery("SELECT * FROM `RecordDate` WHERE ClassID = \"" + classID + "\"");
            
            // Loop Until All of the Rows of the Query
            // have been iterated through.
            while(rs.next()) {
                // Retrieve the Date of the Current
                // Record Date Entry.
                String date = rs.getString("Date");
                // Add Previous Date to Local ArrayList
                datesList.add(date);
                
                System.out.println("Class ID: " + classID + " Date: " + date);
            }
            
            // Let the Program Know That the Second
            // Step in the Database Modification / Retrival
            // Has Been Completed.
            result.setProgressionStatus(2);
            
            // If the Current Class Has Previous Associated
            // Attendance Record Dates for its Other Students,
            // then Create New Attendance Records Corresponding
            // to those Dates for the newly Added Student.
            if (datesList.isEmpty() == false) {
                
                // Unique Attendance Record Identifier
                // That lets the Instructor Know that the
                // Current Student had not been Added to the
                // Associated Class At this point in time.
                String status = "NYA";
                // Message that will be Placed in the Comments
                // Field, Telling the Instructor the Meaning of the
                // Current Status Statement.
                String comment = "Student Wasn't Added to the Class at this Point in Time.";
                
                // Iterate through the entire ArrayList of
                // Attendance Record Dates, creating a new
                // Record for Each Date.
                for (int i = 0; i < datesList.size(); i++) {
                    
                    // Set the Prepared Statement Value to Use the Global String
                    ps = this.connection.prepareStatement(INSERT_STUDENT_ATTENDANCE_RECORD);
            
                    // Set the First Question Mark
                    // Parameter. We Assign Zero for
                    // this Value seeing as the Database
                    // will Auto Increment the Row ID Value.
                    ps.setInt(1, 0);
                    // Set the Class ID Method Parameter As The
                    // Second Question Mark Parameter.
                    ps.setInt(2, classID);
                    // Set the Instructor ID Method Parameter As The
                    // Third Question Mark Parameter.
                    ps.setInt(3, instructorID);
                    // Set the Student ID Method Parameter As The
                    // Forth Question Mark Parameter.
                    ps.setInt(4, studentID);
                    // Set the Unique Status String As The
                    // Fifth Question Mark Parameter.
                    ps.setString(5, status);
                    // Set the Current Date String As The
                    // Sixth Question Mark Parameter.
                    ps.setString(6, datesList.get(i));
                    // Set the Class ID Method Parameter As The
                    // Seventh Question Mark Parameter.
                    ps.setString(7, comment);
                    
                    // Execute the Database Update
                    // Statement.
                    ps.executeUpdate();
                }
            }
            
            // Let the Add Student Window
            // Know that the Operation has been
            // Completely Successful.
            result.setResult(true);
        }
        // Catch Communications Exception should it
        // occur. This Exception is usually caused when
        // the Current User of the Application Loses Connection
        // to the Internet and subsequently the Database.
        catch (CommunicationsException ce) {
            // Let the Add Student Window
            // Know that the Operation has Failed.
            result.setResult(false);
            // Let the AddStudentWindow know
            // that a CommunicationsException has
            // occured in the Second Communications
            // Exception Catch Block.
            result.setType("CommunicationsException 1");
        }
        // Catch SQL Exception should it occur. This
        // Exception is usually caused when an Error
        // occurs within Remote Database or something
        // is wrong with the chosen Query.
        catch (SQLException ex) {
            // Let the Add Student Window
            // Know that the Operation has Failed.
            result.setResult(false);
            // Let the AddStudentWindow know
            // that a SQL Exception has
            // occured in the First SQL Catch
            // Block
            result.setType("SQLException 1");
        }
        // This Block is Executed on Close
        // Regardless of An Error Occuring.
        finally {
            try {
                // Close the Prepared
                // Statement Object to Insure
                // there are not Complications.
                if (ps != null) {
                    // Close the Prepared Statement.
                    ps.close();
                }
            }
            // Catch Communications Exception should it
            // occur. This Exception is usually caused when
            // the Current User of the Application Loses Connection
            // to the Internet and subsequently the Database.
            catch (CommunicationsException ce) {
                // Only Change the Return Type Value
                // of the Result should an Exception have
                // occured before All of the Operation have
                // been successfully executed.
                if (result.getResult() == false) {
                    // Let the AddStudentWindow know
                    // that a CommunicationsException has
                    // occured in the Second Communications
                    // Exception Catch Block.
                    result.setType("CommunicationsException 2");
                }
            }
            // Catch SQL Exception should it occur. This
            // Exception is usually caused when an Error
            // occurs within Remote Database or something
            // is wrong with the chosen Query.
            catch (SQLException e) {
                // Only Change the Return Type Value
                // of the Result should an Exception have
                // occured before All of the Operation have
                // been successfully executed.
                if (result.getResult() == false) {
                    // Let the Add Student Window
                    // Know that the Operation has Failed.
                    result.setResult(false);
                    // Let the AddStudentWindow know
                    // that a SQL Exception has
                    // occured in the Second SQL Catch
                    // Block
                    result.setType("SQLException 2");
                }
            }
        }
        
        // Close the Connection to the Remote
        // Database.
        closeConnection();
        // Return the Result of the 
        // Database Operation Sequence
        return result;
    }
    
    /**
     *  This Method is Responsible For Retrieving the Information
     *  of All Students Currently Present Within the Remote Database.
     *  Only The Student's Public Information will be Retrieved
     *  (NO PASSWORD RETRIVAL FOR SECURITY PURPOSES). This Method is
     *  Called From Within the Manage Student Access Panel which is part
     *  of the Management Tab in the MainScreen Class. This Method is
     *  Used to Retrieve and Create A List of All Students Capable of
     *  Being Added to the Currently Selected Class.
     * 
     *  @return     studentList         An ArrayList Holding only the
     *                                  Public Information of All the Students
     *                                  Present within the Remote Database.
     * 
     */
    public ArrayList<Student> findAllStudents() {
        
        // Create An ArrayList to Store All of Our Student
        // Objects Within.
        ArrayList<Student> studentList = new ArrayList<>();
        // Boolean Array That is used for Monitoring
        // Exception Occurences in the Method.
        boolean[] exceptionTriggered = {false, false};
        // Instantiate A Null Student Object
        // For us to Set Everytime We Iterate
        // over a new Student Row Within the
        // Database.
        Student student = null;
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        try {
            /**
             *  Re-Initialize the Global Statement Variable in Case
             *  it is Currently Still Set From Another Database Query
             *  / Modification Operation.
             */
            this.statement = (Statement) connection.createStatement();
            // Set and Execute the Query.
            // This will return the Information Associated
            // With Every Student Within the Remote Database.
            ResultSet rs = statement.executeQuery("SELECT * FROM `Student`");
            
            // Iterate Through the Rows Retrieved
            // By the Query Execution.
            while(rs.next()) {
                // Save the Current Student's Unqiue
                // Identification Number Within a
                // Temporary Variable.
                int studentID = rs.getInt("StudentID");
                // Save the Current Student's Unqiue
                // Username Within a Temporary Variable.
                String username = rs.getString("Username");
                // Save the Student's Email Address in
                // a Temporary Variable.
                String email = rs.getString("Email");
                // Save the Student's Display Name (Full Name)
                // in a Temporary Variable.
                String name = rs.getString("DisplayName");
                // Save the Student's Physical Address
                // (Place of Residence) in a Temporary Variable.
                String address = rs.getString("Address");
                // Save the String Representation of the Current
                // Student's Chosen Profile Image (Small Size). This
                // will later be used to show the Student's Chosen
                // Profile Image in the Table.
                String imageSmall = rs.getString("ImageSmall");
                
                // Use the Previously Attained Values in Order to
                // create a New Student Object With only the Required
                // Information.
                student = new Student(0, studentID, "", "", email, username, "", name, "", address, "", "", "", imageSmall, "");
                // Add this Student Object to the
                // Return ArrayList.
                studentList.add(student);
            }
        }
        // Catch Communications Exception should it
        // occur. This Exception is usually caused when
        // the Current User of the Application Loses Connection
        // to the Internet and subsequently the Database.
        catch (CommunicationsException ce) {
            // Let the Program Know that a
            // Communication Exception Has Occured.
            exceptionTriggered[0] = true;
            // Create Default Student Value with
            // -1 as thier Database Row ID Number.
            // This will let the Calling Class Know
            // that A CommunicationsException
            // Has Occured Within the Method.
            student = new Student(-1, 0, "", "", "", "", "", "", "", "", "", "", "", "", "");
        }
        // Catch SQL Exception should it occur. This
        // Exception is usually caused when an Error
        // occurs within Remote Database or something
        // is wrong with the chosen Query.
        catch (SQLException ex) {
            // Let the Program Know that a
            // SQL Exception Has Occured.
            exceptionTriggered[1] = true;
            // Create Default Student Value with
            // -2 as its Database Row ID Number.
            // This will let the Calling Class Know
            // that A SQLException
            // Has Occured within the Method.
            student = new Student(-2, 0, "", "", "", "", "", "", "", "", "", "", "", "", "");
        }
        
        // Close the Database Connection
        closeConnection();
        
        // Check to see if any Exceptions Have Occured During the Method's
        // Execution. If One has Occured, empty the Student List And Add the
        // the Error Message Integer Student Object Created in Either
        // Exception's Catch Statement.
        if (exceptionTriggered[0] == true || exceptionTriggered[1] == true) {
            // Clear the ArrayList of Any Contents
            // it may have.
            studentList.clear();
            // Add the Student Object With the
            // Error Message Integer Created
            // in the Catch Statement.
            studentList.add(student);
        }
        
        // Return the Student
        // Object ArrayList.
        return studentList;
    }
    
    public DatabaseOperationResult createStudentClassLinkAllStudents(int studentID, int classID) {
        
        /**
         *  Connect to the Remote Database and Save the
         *  Current Connection Details in the Global
         *  Connection Reference Variable.
         */
        connection = getConnection();
        
        PreparedStatement ps = null;
        
        try {
            
            ps = this.connection.prepareStatement(INSERT_STUDENT_CLASS_LINK);
            
            ps.setInt(1, 0);
            ps.setInt(2, classID);
            ps.setInt(3, studentID);
 
            ps.executeUpdate();
        }
        catch (CommunicationsException ce) {
            
            closeConnection();
            return new DatabaseOperationResult(false, "CommunicationsException", 0);
        }
        catch (SQLException e) {
            e.printStackTrace();
            
            closeConnection();
            return new DatabaseOperationResult(false, "SQLException", 0);
        } 
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } 
            catch (SQLException e) {
                e.printStackTrace();
                
                closeConnection();
                return new DatabaseOperationResult(false, "SQLException", 0);
            }
        }
        
        closeConnection();
        return new DatabaseOperationResult(true, "Operation Successful", 0);
    
    }
}
