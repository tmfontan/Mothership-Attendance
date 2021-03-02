/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.io.Serializable;

/**
 *  This Class is Used in Order to Create
 *  a New Instance of the Custom Student
 *  Object Value. This Object is Responsible
 *  for Holding the Information Pertaining to
 *  a User's Account should the Account have
 *  been Created Using a "Student" 
 *  Designation.
 *  
 *  @date           November 10 2020
 *  @author         Tyler Fontana
 *  @version        1.0.2
 */
public class Student implements Serializable {
    
    /**
     *  The Integer Variable Containing the Unique
     *  Row Identification Number that is Assigned
     *  to the Current Instance of the "Student"
     *  Account by the Remote Database.
     */
    public int rowID;
    /**
     *  The Integer Variable Containing the Unique
     *  Student Identification Number which acts as
     *  A Second Unique Identifier for Differentiating
     *  Between Student Accounts in the Database. This
     *  Identification Number will be a Valid Seven
     *  Digit, Unique Number that is Assigned to Each
     *  Student Account by the University Which they
     *  are Enrolled at.  This Value is Set Once the
     *  Account is Created and Can't Be Changed By the
     *  User Afterwards.
     */
    public int studentIdentificationNumber;
    /**
     *  The String Variable Containing the "First Name"
     *  Value of the Current User who has Created an
     *  Account Under the "Student" Designation. This
     *  Value is Set Once the Account is Created and
     *  Can't Be Changed By the User Afterwards.
     */
    public String firstName;
    /**
     *  The String Variable Containing the "Last Name"
     *  Value of the Current User who has Created an
     *  Account Under the "Student" Designation. This
     *  Value is Set Once the Account is Created and
     *  Can't Be Changed By the User Afterwards.
     */
    public String lastName;
    /**
     *  The String Variable Containing the "Email Address"
     *  Value of the Current User who has Created an
     *  Account Under the "Student" Designation. This
     *  Value is Set Once the Account is Created but
     *  Can Be Changed Via the Edit Profile Screen
     *  once the "Student" Account is Created.
     */
    public String email;
    /**
     *  The String Variable Containing the "Username"
     *  Value of the Current User who has Created an
     *  Account Under the "Student" Designation. This
     *  Value is Set Once the Account is Created and
     *  Can't Be Changed By the User Afterwards. This
     *  is Done Seeing as the Username Acts as an
     *  Unique Identification Value For Each User
     *  Account ("Instructor" or "Student"). This Value
     *  acts as the Username Credential Needed For the
     *  Account Login Process.
     */
    public String username;
    /**
     *  The String Variable Containing the "Password"
     *  Value of the Current User who has Created an
     *  Account Under the "Student" Designation. This
     *  Value is Set Once the Account is Created and
     *  Can't Be Changed By the User Afterwards. This
     *  Value acts as the "Password" Credential Needed
     *  For the Account Login Process.
     *  
     *  (Note: This Feature May Be Subject to Change
     *  in Later Versions of the Application.)
     */
    public String password;
    /**
     *  The String Variable Containing the "Display Name"
     *  Value of the Current User who has Created an
     *  Account Under the "Student" Designation. The
     *  Data Contained Within this Field is a Combination
     *  of the First and Last Name String Values. The
     *  Contents of this Field Can't Be Changed Once
     *  the Student Account has been Created.
     */
    public String displayName;
    /**
     *  The String Variable Containing the "Phone Number"
     *  Value of the Current User who has Created an
     *  Account Under the "Student" Designation. The
     *  Content of this Field Can Be Edited in the Edit
     *  Profile Section of the Main Screen GUI Class.
     */
    public String phone;
    /**
     *  The String Variable Containing the "Physical Address"
     *  Value of the Current User who has Created an
     *  Account Under the "Student" Designation. The
     *  Content of this Field Can Be Edited in the Edit
     *  Profile Section of the Main Screen GUI Class.
     */
    public String address;
    /**
     *  The String Variable Containing the "Biography"
     *  Value of the Current User who has Created an
     *  Account Under the "Student" Designation. The
     *  Content of this Field Can Be Edited in the Edit
     *  Profile Section of the Main Screen GUI Class.
     */
    public String biography;
    /**
     *  The String Variable Containing the Path Within
     *  the Application's Files to the Large Version of
     *  the User's Chosen Profile Image. This Version
     *  is Shown on the Edit Profile Tab of the Main Screen
     *  GUI Window Form.
     * 
     *  (Note: The User's Profile Image Can Be Changed
     *  Within the Edit Profile Section of the Main Screen
     *  GUI Window Regardless of the Version.)
     */
    public String imageLarge;
    /**
     *  The String Variable Containing the Path Within
     *  the Application's Files to the Medium Version of
     *  the User's Chosen Profile Image. This Version
     *  is Shown on the Edit Profile Tab of the Main Screen
     *  GUI Window Form.
     * 
     *  (Note: The User's Profile Image Can Be Changed
     *  Within the Edit Profile Section of the Main Screen
     *  GUI Window Regardless of the Version.)
     */
    public String imageMedium;
    /**
     *  The String Variable Containing the Path Within
     *  the Application's Files to the Small Version of
     *  the User's Chosen Profile Image. This Version
     *  is Shown on the Edit Profile Tab of the Main Screen
     *  GUI Window Form.
     * 
     *  (Note: The User's Profile Image Can Be Changed
     *  Within the Edit Profile Section of the Main Screen
     *  GUI Window Regardless of the Version.)
     */
    public String imageSmall;
    /**
     *  The String Variable Containing the Path Within
     *  the Application's Files to the Extra Small Version of
     *  the User's Chosen Profile Image. This Version
     *  is Shown on the Edit Profile Tab of the Main Screen
     *  GUI Window Form.
     * 
     *  (Note: The User's Profile Image Can Be Changed
     *  Within the Edit Profile Section of the Main Screen
     *  GUI Window Regardless of the Version.)
     */
    public String imageXS;
    
    /**
     *  This Constructor is Used in Order to Create a New
     *  Custom Student Object. This Object is Used in
     *  Order to Hold All of the Required and Auxiliary
     *  Information Associated With an Student's Account
     *  Present Within the Remote Database.
     * 
     *  @param id               The Unique "ID" Integer Value
     *                          Which Corresponds to the Position
     *                          Row Number of the Student's
     *                          Account Within the Remote Database.
     * 
     *  @param studentID        The Unique Student Identification
     *                          Number Integer Value. This Seven
     *                          Digit Number acts as a Secondary
     *                          Identification Value for
     *                          Differentiating Between Student
     *                          Account Instances.
     * 
     *  @param first            The String Variable Containing the
     *                          "First Name" Value of the Current
     *                          Student User.
     * 
     *  @param last             The String Variable Containing the
     *                          "Last Name" Value of the Current
     *                          Student User.
     * 
     *  @param user             The String Variable Containing the
     *                          "Username" Value of the Current
     *                          Student User.
     * 
     *  @param pass             The String Variable Containing the
     *                          "Password" Value of the Current
     *                          Student User.
     * 
     *  @param mail             The String Variable Containing the
     *                          "Email Address" Value of the Current
     *                          Student User.
     * 
     *  @param display          The String Variable Containing the
     *                          "Display Name" Value of the Current
     *                          Student User.
     * 
     *  @param phoneNum         The String Variable Containing the
     *                          "Phone Number" Value of the Current
     *                          Student User.
     * 
     *  @param add              The String Variable Containing the
     *                          "Physical Address" Value of the Current
     *                          Student User.
     * 
     *  @param bio              The String Variable Containing the
     *                          "Biography" Value of the Current
     *                          Student User.
     * 
     *  @param iconLarge        The String Variable Containing the
     *                          Path to the Large Version of the Student's
     *                          Chosen Profile Picture Within the
     *                          Application's Directory Files.
     * 
     *  @param iconMedium       The String Variable Containing the
     *                          Path to the Medium Version of the Student's
     *                          Chosen Profile Picture Within the
     *                          Application's Directory Files.
     * 
     *  @param iconSmall        The String Variable Containing the
     *                          Path to the Small Version of the Student's
     *                          Chosen Profile Picture Within the
     *                          Application's Directory Files.
     * 
     *  @param xs               The String Variable Containing the
     *                          Path to the Extra Small Version of the
     *                          Student's Chosen Profile Picture Within the
     *                          Application's Directory Files.
     */
    public Student(int id, int studentID, String first, String last, String mail, String user, String pass, String display, String phoneNum, String add, String bio, String iconLarge, String iconMedium, String iconSmall, String xs) {
        /**
         *  Set Unique Row ID Value.
         */
        rowID = id;
        /**
         *  Set Unique Student Identification
         *  Number Integer Value.
         */
        studentIdentificationNumber = studentID;
        /**
         *  Set "First Name" String
         *  Value of the Current
         *  Student Account Instance.
         */
        firstName = first;
        /**
         *  Set "Last Name" String
         *  Value of the Current
         *  Student Account Instance.
         */
        lastName = last;
        /**
         *  Set "Email Address" String
         *  Value of the Current
         *  Student Account Instance.
         */
        email = mail;
        /**
         *  Set "Username" String
         *  Value of the Current
         *  Student Account Instance.
         */
        username = user;
        /**
         *  Set "Password" String
         *  Value of the Current
         *  Student Account Instance.
         */
        password = pass;
        /**
         *  Set "Display" String
         *  Value of the Current
         *  Student Account Instance.
         */
        displayName = display;
        /**
         *  Set "Phone Number" String
         *  Value of the Current
         *  Student Account Instance.
         */
        phone = phoneNum;
        /**
         *  Set "Physical Address" String
         *  Value of the Current
         *  Student Account Instance.
         */
        address = add;
        /**
         *  Set "Biography" String
         *  Value of the Current
         *  Student Account Instance.
         */
        biography = bio;
        /**
         *  Set the String Value Containing
         *  the Directory Path to the Location
         *  of the Account's Chosen Profile
         *  Image (Large Version).
         */
        imageLarge = iconLarge;
        /**
         *  Set the String Value Containing
         *  the Directory Path to the Location
         *  of the Account's Chosen Profile
         *  Image (Medium Version).
         */
        imageMedium = iconMedium;
        /**
         *  Set the String Value Containing
         *  the Directory Path to the Location
         *  of the Account's Chosen Profile
         *  Image (Small Version).
         */
        imageSmall = iconSmall;
        /**
         *  Set the String Value Containing
         *  the Directory Path to the Location
         *  of the Account's Chosen Profile
         *  Image (Extra Small Version).
         */
        imageXS = xs;
    }
    
    /**
     *  This Getter Method is Responsible For Returning
     *  The Unique Integer Row "ID" Assigned to the
     *  Student Account by the Remote Database. This
     *  Row Number Corresponds to the Account's Entry
     *  Index Present Within the Student Table.
     * 
     *  @return         The Unique Row ID Number
     *                  Associated With the Account.
     */
    public int getRowID() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return rowID;
    }
    
    
    /**
     *  This Getter Method is Responsible for Returning
     *  the Unique Student Identification Number Value
     *  which acts as a Secondary Identifier Used in
     *  order to Differentiate Between Student Accounts.
     *  
     *  @return         The Unique Student Identification
     *                  Number Issued to the Student
     *                  Upon Enrollment in the University.
     */
    public int getStudentIdentificationNumber() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return studentIdentificationNumber;
    }
    
    /**
     *  This Getter Method is Responsible For Returning
     *  The String Variable Containing the "First Name" 
     *  Column Value Present Within the Current
     *  Student Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "First Name" Column
     *                  String Value.
     */
    public String getStudentFirstName() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return firstName;
    }
    
    /**
     *  This Getter Method is Responsible For Returning
     *  The String Variable Containing the "Last Name" 
     *  Column Value Present Within the Current
     *  Student Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Last Name" Column
     *                  String Value.
     */
    public String getStudentLastName() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return lastName;
    }
    
    /**
     *  This Getter Method is Responsible For Returning
     *  The String Variable Containing the "Email Address" 
     *  Column Value Present Within the Current
     *  Student Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Email Address" Column
     *                  String Value.
     */
    public String getStudentEmail() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return email;
    }
    
    /**
     *  This Getter Method is Responsible For Returning
     *  The String Variable Containing the "Username" 
     *  Column Value Present Within the Current
     *  Student Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Username" Column
     *                  String Value.
     */
    public String getStudentUsername() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return username;
    }
    
    /**
     *  This Getter Method is Responsible For Returning
     *  The String Variable Containing the "Password" 
     *  Column Value Present Within the Current
     *  Student Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Password" Column
     *                  String Value.
     */
    public String getStudentPassword() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return password;
    }
    
    /**
     *  This Getter Method is Responsible For Returning
     *  The String Variable Containing the "Display Name" 
     *  Column Value Present Within the Current
     *  Student Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Display Name" Column
     *                  String Value.
     */
    public String getStudentDisplayName() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return displayName;
    }
    
    /**
     *  This Getter Method is Responsible For Returning
     *  The String Variable Containing the "Phone Number" 
     *  Column Value Present Within the Current
     *  Student Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Phone Number" Column
     *                  String Value.
     */
    public String getStudentPhone() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return phone;
    }
    
    /**
     *  This Getter Method is Responsible For Returning
     *  The String Variable Containing the "Physical Address" 
     *  Column Value Present Within the Current
     *  Student Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Physical Address" Column
     *                  String Value.
     */
    public String getStudentAddress() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return address;
    }
    
    /**
     *  This Getter Method is Responsible For Returning
     *  The String Variable Containing the "Biography" 
     *  Column Value Present Within the Current
     *  Student Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Biography" Column
     *                  String Value.
     */
    public String getStudentBiography() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return biography;
    }
    
    /**
     *  This Getter Method is Responsible for Returning
     *  the Directory Path Within the Application's Files
     *  that Corresponds to the Student Account's
     *  Chosen Profile Image. (Large Version)
     * 
     *  @return         The Directory Path to Where
     *                  the Profile Image is Located.
     *                  (Large Version)
     */
    public String getStudentProfileImageLarge() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return imageLarge;
    }
    
    /**
     *  This Getter Method is Responsible for Returning
     *  the Directory Path Within the Application's Files
     *  that Corresponds to the Student Account's
     *  Chosen Profile Image. (Medium Version)
     * 
     *  @return         The Directory Path to Where
     *                  the Profile Image is Located.
     *                  (Medium Version)
     */
    public String getStudentProfileImageMedium() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return imageMedium;
    }
    
    /**
     *  This Getter Method is Responsible for Returning
     *  the Directory Path Within the Application's Files
     *  that Corresponds to the Student Account's
     *  Chosen Profile Image. (Small Version)
     * 
     *  @return         The Directory Path to Where
     *                  the Profile Image is Located.
     *                  (Small Version)
     */
    public String getStudentProfileImageSmall() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return imageSmall;
    }
    
    /**
     *  This Getter Method is Responsible for Returning
     *  the Directory Path Within the Application's Files
     *  that Corresponds to the Student Account's
     *  Chosen Profile Image. (Extra Small Version)
     * 
     *  @return         The Directory Path to Where
     *                  the Profile Image is Located.
     *                  (Extra Small Version)
     */
    public String getStudentProfileImageXS() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return imageXS;
    }
    
    /**
     *  This Setter Method is Used in Order to
     *  Set the Unique Row ID Integer Value
     *  Associated With the Current Student
     *  Account's Position in the Remote Database
     *  Student Table.
     * 
     *  @param value    The Unique Row ID Integer
     *                  Value set by the Remote
     *                  Database Upon Account
     *                  Creation.
     */
    public void setRowID(int value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        rowID = value;
    }
    
    /**
     *  This Setter Method is Used in Order to
     *  Set the Unique Student Identification Number 
     *  Integer Value Associated With the Current 
     *  Student Account's Position in the Remote 
     *  Database Student Table.
     * 
     *  @param value    The Unique Seven Digit Student 
     *                  Identification Number Integer
     *                  Value Assigned to the Student
     *                  by the University.
     */
    public void setStudentIdentificationNumber(int value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        studentIdentificationNumber = value;
    }
    
    /**
     *  This Setter Method is Used to Assign
     *  the "First Name" Column String Value Given
     *  by the Remote Database to the Current
     *  Instance of the Student Object.
     *  
     *  @param value        The "First Name" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setStudentFirstName(String value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        firstName = value;
    }
    
    /**
     *  This Setter Method is Used to Assign
     *  the "Last Name" Column String Value Given
     *  by the Remote Database to the Current
     *  Instance of the Student Object.
     *  
     *  @param value        The "Last Name" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setStudentLastName(String value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        lastName = value;
    }
    
    /**
     *  This Setter Method is Used to Assign
     *  the "Email Address" Column String Value Given
     *  by the Remote Database to the Current
     *  Instance of the Student Object.
     *  
     *  @param value        The "Email Address" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setStudentEmail(String value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        email = value;
    }
    
    /**
     *  This Setter Method is Used to Assign
     *  the "Username" Column String Value Given
     *  by the Remote Database to the Current
     *  Instance of the Student Object.
     *  
     *  @param value        The "Username" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setStudentUsername(String value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        username = value;
    }
    
    /**
     *  This Setter Method is Used to Assign
     *  the "Password" Column String Value Given
     *  by the Remote Database to the Current
     *  Instance of the Student Object.
     *  
     *  @param value        The "Password" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setStudentPassword(String value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        password = value;
    }
    
    /**
     *  This Setter Method is Used to Assign
     *  the "Display Name" Column String Value Given
     *  by the Remote Database to the Current
     *  Instance of the Student Object.
     *  
     *  @param value        The "Display Name" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setStudentDisplayName(String value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        displayName = value;
    }
    
    /**
     *  This Setter Method is Used to Assign
     *  the "Phone Number" Column String Value Given
     *  by the Remote Database to the Current
     *  Instance of the Student Object.
     *  
     *  @param value        The "Phone Number" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setStudentPhone(String value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        phone = value;
    }
    
    /**
     *  This Setter Method is Used to Assign
     *  the "Physical Address" Column String Value Given
     *  by the Remote Database to the Current
     *  Instance of the Student Object.
     *  
     *  @param value        The "Physical Address" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setStudentAddress(String value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        address = value;
    }
    
    /**
     *  This Setter Method is Used to Assign
     *  the "Biography" Column String Value Given
     *  by the Remote Database to the Current
     *  Instance of the Student Object.
     *  
     *  @param value        The "Biography" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setStudentBiography(String value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        biography = value;
    }
    
    /**
     *  This Setter Method is Used to Assign
     *  the Directory Path within the Application
     *  Files that maps to the location of the
     *  Student's Chosen Profile Image
     *  (Large Version)
     *  
     *  @param value        The Directory Path
     *                      Corresponding to the
     *                      Location of the Student's
     *                      Chosen Profile Image
     *                      (Large Version)
     */
    public void setStudentProfileImageLarge(String value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        imageLarge = value;
    }
    
    /**
     *  This Setter Method is Used to Assign
     *  the Directory Path within the Application
     *  Files that maps to the location of the
     *  Student's Chosen Profile Image
     *  (Medium Version)
     *  
     *  @param value        The Directory Path
     *                      Corresponding to the
     *                      Location of the Student's
     *                      Chosen Profile Image
     *                      (Medium Version)
     */
    public void setStudentProfileImageMedium(String value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        imageMedium = value;
    }
    
    /**
     *  This Setter Method is Used to Assign
     *  the Directory Path within the Application
     *  Files that maps to the location of the
     *  Student's Chosen Profile Image
     *  (Small Version)
     *  
     *  @param value        The Directory Path
     *                      Corresponding to the
     *                      Location of the Student's
     *                      Chosen Profile Image
     *                      (Small Version)
     */
    public void setStudentProfileImageSmall(String value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        imageSmall = value;
    }
    
    /**
     *  This Setter Method is Used to Assign
     *  the Directory Path within the Application
     *  Files that maps to the location of the
     *  Student's Chosen Profile Image
     *  (Extra Small Version)
     *  
     *  @param value        The Directory Path
     *                      Corresponding to the
     *                      Location of the Student's
     *                      Chosen Profile Image
     *                      (Extra Small Version)
     */
    public void setStudentProfileImageXS(String value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        imageXS = value;
    }
}
