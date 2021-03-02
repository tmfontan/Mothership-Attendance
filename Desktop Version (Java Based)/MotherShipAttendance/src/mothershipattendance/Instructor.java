/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

/**
 *  This Class is Used in Order to Create
 *  a New Instance of the Custom Instructor
 *  Object Value. This Object is Responsible
 *  for Holding the Information Pertaining to
 *  a User's Account should the Account have
 *  been Created Using an "Instructor" 
 *  Designation.
 *  
 *  @date           November 10 2020
 *  @author         Tyler Fontana
 *  @version        1.0.2
 */
public class Instructor {
    
    /**
     *  The Integer Variable Containing the Unique
     *  Row Identification Number that is Assigned
     *  to the Current Instance of the "Instructor"
     *  Account by the Remote Database.
     */
    public int id;
    /**
     *  The String Variable Containing the "First Name"
     *  Value of the Current User who has Created an
     *  Account Under the "Instructor" Designation.
     */
    public String firstName;
    /**
     *  The String Variable Containing the "Last Name"
     *  Value of the Current User who has Created an
     *  Account Under the "Instructor" Designation.
     */
    public String lastName;
    /**
     *  The String Variable Containing the "Email Address"
     *  Value of the Current User who has Created an
     *  Account Under the "Instructor" Designation.
     */
    public String email;
    /**
     *  The String Variable Containing the "Username"
     *  Value of the Current User who has Created an
     *  Account Under the "Instructor" Designation. This
     *  Value acts as the Username Credential Needed
     *  in order to Login to the Account.
     */
    public String username;
    /**
     *  The String Variable Containing the "Username"
     *  Value of the Current User who has Created an
     *  Account Under the "Instructor" Designation. This
     *  Value is Set Once the Account is Created and
     *  Can't Be Changed By the User Afterwards. This
     *  is Done Seeing as the Username Acts as an
     *  Unique Identification Value For Each User
     *  Account ("Instructor" or "Student"). This Value
     *  acts as the Username Credential Needed For the
     *  Account Login Process.
     */
    public String password;
    /**
     *  The String Variable Containing the "Password"
     *  Value of the Current User who has Created an
     *  Account Under the "Instructor" Designation. This
     *  Value is Set Once the Account is Created and
     *  Can't Be Changed By the User Afterwards. This
     *  Value acts as the "Password" Credential Needed
     *  For the Account Login Process.
     *  
     *  (Note: This Feature May Be Subject to Change
     *  in Later Versions of the Application.)
     */
    public String displayName;
    /**
     *  The String Variable Containing the "Phone Number"
     *  Value of the Current User who has Created an
     *  Account Under the "Instructor" Designation.
     */
    public String phone;
    /**
     *  The String Variable Containing the "Physical Address"
     *  Value of the Current User who has Created an
     *  Account Under the "Instructor" Designation. The
     *  Content of this Field Can Be Edited in the Edit
     *  Profile Section of the Main Screen GUI Class.
     */
    public String address;
    /**
     *  The String Variable Containing the "Biography"
     *  Value of the Current User who has Created an
     *  Account Under the "Instructor" Designation. The
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
     *  is Shown on the Pop-Up Edit Profile Screen which
     *  appears after the user has Clicked the Edit Profile
     *  JButton Component Within the MainScreen.
     *
     *  (Note: The User's Profile Image Can Be Changed
     *  Within the Edit Profile Section of the Main Screen
     *  GUI Window Regardless of the Version.)
     */
    public String imageMedium;
    /**
     *  The String Variable Containing the Path Within
     *  the Application's Files to the Large Version of
     *  the User's Chosen Profile Image. This Version
     *  is Shown when Sending Messages Via the Message
     *  Board Part of the Attendance Server Function.
     *
     *  (Note: The User's Profile Image Can Be Changed
     *  Within the Edit Profile Section of the Main Screen
     *  GUI Window Regardless of the Version.)
     */
    public String imageSmall;
    /**
     *  The String Variable Containing the Path Within
     *  the Application's Files to the Large Version of
     *  the User's Chosen Profile Image. This Version
     *  is Shown During the Attendance Server next to
     *  the Display Name of the Current User's
     *  Instructor Account.
     *
     *  (Note: The User's Profile Image Can Be Changed
     *  Within the Edit Profile Section of the Main Screen
     *  GUI Window Regardless of the Version.)
     */
    public String imageXS;

    /**
     *  This Constructor is Used in Order to Create a New
     *  Custom Instructor Object. This Object is Used in
     *  Order to Hold All of the Required and Auxiliary
     *  Information Associated With an Instructor's Account
     *  Present Within the Remote Database.
     * 
     *  @param rowID            The Unique "ID" Integer Value
     *                          Which Corresponds to the Position
     *                          Row Number of the Instructor's
     *                          Account Within the Remote Database.
     * 
     *  @param first            The String Variable Containing the
     *                          "First Name" Value of the Current
     *                          Instructor User.
     * 
     *  @param last             The String Variable Containing the
     *                          "Last Name" Value of the Current
     *                          Instructor User.
     * 
     *  @param user             The String Variable Containing the
     *                          "Username" Value of the Current
     *                          Instructor User.
     * 
     *  @param pass             The String Variable Containing the
     *                          "Password" Value of the Current
     *                          Instructor User.
     * 
     *  @param mail             The String Variable Containing the
     *                          "Email Address" Value of the Current
     *                          Instructor User.
     * 
     *  @param display          The String Variable Containing the
     *                          "Display Name" Value of the Current
     *                          Instructor User.
     * 
     *  @param phoneNum         The String Variable Containing the
     *                          "Phone Number" Value of the Current
     *                          Instructor User.
     * 
     *  @param add              The String Variable Containing the
     *                          "Physical Address" Value of the Current
     *                          Instructor User.
     * 
     *  @param bio              The String Variable Containing the
     *                          "Biography" Value of the Current
     *                          Instructor User.
     * 
     *  @param iconLarge        The String Variable Containing the
     *                          Path to the Large Version of the Instructor's
     *                          Chosen Profile Picture Within the
     *                          Application's Directory Files.
     * 
     *  @param iconMedium       The String Variable Containing the
     *                          Path to the Medium Version of the Instructor's
     *                          Chosen Profile Picture Within the
     *                          Application's Directory Files.
     * 
     *  @param iconSmall        The String Variable Containing the
     *                          Path to the Small Version of the Instructor's
     *                          Chosen Profile Picture Within the
     *                          Application's Directory Files.
     * 
     *  @param iconXS           The String Variable Containing the
     *                          Path to the Extra Small Version of the
     *                          Instructor's Chosen Profile Picture Within the
     *                          Application's Directory Files.
     */
    public Instructor(int rowID, String first, String last, String user, String pass, String mail, String display, String phoneNum, String add, String bio, String iconLarge, String iconMedium, String iconSmall, String iconXS) {
        /**
         *  Set Unique Row ID Value.
         */
        id = rowID;
        /**
         *  Set "First Name" String
         *  Value of the Current
         *  Instructor Account Instance.
         */
        firstName = first;
        /**
         *  Set "Last Name" String
         *  Value of the Current
         *  Instructor Account Instance.
         */
        lastName = last;
        /**
         *  Set "Email Address" String
         *  Value of the Current
         *  Instructor Account Instance.
         */
        email = mail;
        /**
         *  Set "Username" String
         *  Value of the Current
         *  Instructor Account Instance.
         */
        username = user;
        /**
         *  Set "Password" String
         *  Value of the Current
         *  Instructor Account Instance.
         */
        password = pass;
        /**
         *  Set "Display Name" String
         *  Value of the Current
         *  Instructor Account Instance.
         */
        displayName = display;
        /**
         *  Set "Phone Number" String
         *  Value of the Current
         *  Instructor Account Instance.
         */
        phone = phoneNum;
        /**
         *  Set "Physical Address" String
         *  Value of the Current
         *  Instructor Account Instance.
         */
        address = add;
        /**
         *  Set "Biography" String
         *  Value of the Current
         *  Instructor Account Instance.
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
        imageXS = iconXS;
    }
    
    /**
     *  This Getter Method is Responsible For Returning
     *  The Unique Integer Row "ID" Assigned to the
     *  Instructor Account by the Remote Database. This
     *  Row Number Corresponds to the Account's Entry
     *  Index Present Within the Instructor Table.
     * 
     *  @return         The Unique Row ID Number
     *                  Associated With the Account.
     */
    public int getInstructorID() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return id;
    }
    
    /**
     *  This Getter Method is Responsible For Returning
     *  The String Variable Containing the "First Name" 
     *  Column Value Present Within the Current
     *  Instructor Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "First Name" Column
     *                  String Value.
     */
    public String getInstructorFirstName() {
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
     *  Instructor Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Last Name" Column
     *                  String Value.
     */
    public String getInstructorLastName() {
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
     *  Instructor Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Email Address" Column
     *                  String Value.
     */
    public String getInstructorEmail() {
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
     *  Instructor Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Username" Column
     *                  String Value.
     */
    public String getInstructorUsername() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return username;
    }
    
    /**
     *  This Getter Method is Responsible For Returning
     *  The String Variable Containing the "First Name" 
     *  Column Value Present Within the Current
     *  Instructor Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Password" Column
     *                  String Value.
     */
    public String getInstructorPassword() {
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
     *  Instructor Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Display Name" Column
     *                  String Value.
     */
    public String getInstructorDisplayName() {
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
     *  Instructor Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Phone Number" Column
     *                  String Value.
     */
    public String getInstructorPhone() {
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
     *  Instructor Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Physical Address" Column
     *                  String Value.
     */
    public String getInstructorAddress() {
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
     *  Instructor Instance's Account within the
     *  Remote Database.
     * 
     *  @return         The "Biography" Column
     *                  String Value.
     */
    public String getInstructorBiography() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return biography;
    }
    
    /**
     *  This Getter Method is Responsible for Returning
     *  the Directory Path Within the Application's Files
     *  that Corresponds to the Instructor Account's
     *  Chosen Profile Image. (Large Version)
     * 
     *  @return         The Directory Path to Where
     *                  the Profile Image is Located.
     *                  (Large Version)
     */
    public String getInstructorProfileImageLarge() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return imageLarge;
    }
    
    /**
     *  This Getter Method is Responsible for Returning
     *  the Directory Path Within the Application's Files
     *  that Corresponds to the Instructor Account's
     *  Chosen Profile Image. (Medium Version)
     * 
     *  @return         The Directory Path to Where
     *                  the Profile Image is Located.
     *                  (Medium Version)
     */
    public String getInstructorProfileImageMedium() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return imageMedium;
    }
    
    /**
     *  This Getter Method is Responsible for Returning
     *  the Directory Path Within the Application's Files
     *  that Corresponds to the Instructor Account's
     *  Chosen Profile Image. (Small Version)
     * 
     *  @return         The Directory Path to Where
     *                  the Profile Image is Located.
     *                  (Small Version)
     */
    public String getInstructorProfileImageSmall() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return imageSmall;
    }
    
    /**
     *  This Getter Method is Responsible for Returning
     *  the Directory Path Within the Application's Files
     *  that Corresponds to the Instructor Account's
     *  Chosen Profile Image. (Extra Small Version)
     * 
     *  @return         The Directory Path to Where
     *                  the Profile Image is Located.
     *                  (Extra Small Version)
     */
    public String getInstructorProfileImageXS() {
        /**
         *  Return the Global
         *  Variable Value.
         */
        return imageXS;
    }
    
    /**
     *  This Setter Method is Used in Order to
     *  Set the Unique Row ID Integer Value
     *  Associated With the Current Instructor
     *  Account's Position in the Remote Database
     *  Instructor Table.
     * 
     *  @param value    The Unique Row ID Integer
     *                  Value set by the Remote
     *                  Database Upon Account
     *                  Creation.
     */
    public void setInstructorID(int value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        id = value;
    }
    
    /**
     *  This Setter Method is Used to Assign
     *  the "First Name" Column String Value Given
     *  by the Remote Database to the Current
     *  Instance of the Instructor Object.
     *  
     *  @param value        The "First Name" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setInstructorFirstName(String value) {
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
     *  Instance of the Instructor Object.
     *  
     *  @param value        The "Last Name" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setInstructorLastName(String value) {
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
     *  Instance of the Instructor Object.
     *  
     *  @param value        The "Email Address" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setInstructorEmail(String value) {
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
     *  Instance of the Instructor Object.
     *  
     *  @param value        The "Username" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setInstructorUsername(String value) {
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
     *  Instance of the Instructor Object.
     *  
     *  @param value        The "Password" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setInstructorPassword(String value) {
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
     *  Instance of the Instructor Object.
     *  
     *  @param value        The "Display Name" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setInstructorDisplayName(String value) {
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
     *  Instance of the Instructor Object.
     *  
     *  @param value        The "First Name" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setInstructorPhone(String value) {
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
     *  Instance of the Instructor Object.
     *  
     *  @param value        The "Physical Address" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setInstructorAddress(String value) {
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
     *  Instance of the Instructor Object.
     *  
     *  @param value        The "Biography" Column
     *                      String Value Provided By
     *                      the Remote Database.
     */
    public void setInstructorBiography(String value) {
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
     *  Instructor's Chosen Profile Image
     *  (Large Version)
     *  
     *  @param value        The Directory Path
     *                      Corresponding to the
     *                      Location of the Instructor's
     *                      Chosen Profile Image
     *                      (Large Version)
     */
    public void setInstructorProfileImageLarge(String value) {
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
     *  Instructor's Chosen Profile Image
     *  (Medium Version)
     *  
     *  @param value        The Directory Path
     *                      Corresponding to the
     *                      Location of the Instructor's
     *                      Chosen Profile Image
     *                      (Medium Version)
     */
    public void setInstructorProfileImageMedium(String value) {
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
     *  Instructor's Chosen Profile Image
     *  (Small Version)
     *  
     *  @param value        The Directory Path
     *                      Corresponding to the
     *                      Location of the Instructor's
     *                      Chosen Profile Image
     *                      (Small Version)
     */
    public void setInstructorProfileImageSmall(String value) {
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
     *  Instructor's Chosen Profile Image
     *  (Extra Small Version)
     *  
     *  @param value        The Directory Path
     *                      Corresponding to the
     *                      Location of the Instructor's
     *                      Chosen Profile Image
     *                      (Extra Small Version)
     */
    public void setInstructorProfileImageXS(String value) {
        /**
         *  Set the Global
         *  Variable Value.
         */
        imageXS = value;
    }
}
