/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

/**
 *  This Class is Used to Create a Custom Object
 *  Which Holds the Methods Necessary For Determining
 *  Whether or Not the Parameter Input String Contains
 *  any of the Special Characters or Number Characters
 *  Listed Below.
 * 
 *  @author     Tyler Fontana
 *  @date       October 17 2020
 *  @version    1.0.2
 */
public class StringInputChecker {
    
    /**
     *  Create a String Object Array Which is Composed of all the Available Special Character
     *  Values. The Methods in this Class will use this Array Value in order to Determine if the
     *  Passed in Parameter String Contains any of the Characters Shown Below.
     * 
     *  (Note: We Use the String Object as the Variable Type in this Array in Order to
     *  Make the Comparison Operations Quicker and Easier. String Objects Occupy More
     *  Memory Space than regular Primitive Char Variables. However, Most of the Methods
     *  Contained Within this Class Pass in String Objects as their Parameter Values. Thus,
     *  it Would be Simpler to Compare a String Object With another String Object, instead
     *  of Breaking the String Object Apart into a Series of Char Values and Comparing Each
     *  Char with every Entry of the Array Below. This wouldn't normally be true. However,
     *  the Java Programming Language has Installed Many Useful String Manipulation Methods
     *  which allow this Approach to be the Simplest between the two.)
     */
    public String[] specialChars = {"=", "[", "]", "{", "}", "_", "|", ":", ";", "<", "!", "?", ".", "@", "#", "$", "%", "^",  "&", "*", "(", ")", "~", ",",
         "å", "†", "ç", "ƒ", "©", "™",  "š", "¬", "µ", "ø", "½", "¼",  "¾", "œ", "®", "ß", "Ý", "…",  "‰", "ˆ", "¥", "‡", "Å", "¹", "Ç",  "Î", "Ï",
         "›", "Ó", "•", "Ò", "Â",  "Ø", "Œ", "Í", "", "–", "º", "¡",  "™", "£", "¢", "ƒ", "§", "¶", "ª",  "æ", "Ž", "€", "Ð", "ð", "Þ", "þ",  
         "ý", "°", "·", "±", "Æ", "Ú",  "¿", "‡", "¨", "×", "\"", "'", "/", "\\", "^", ">", "<", "-", "`", "~", "+", "="};
    
    /**
     *  Create a String Object Array Which is Composed of all the Available Special Character
     *  Values, (Excluding the '.' && '@' Characters Seeing as they can be used in an Email Address
     *  Declaration Value). The Methods in this Class will use this Array Value in order to Determine
     *  if the Passed in Parameter String Contains any of the Characters Shown Below.
     * 
     *  (Note: We Use the String Object as the Variable Type in this Array in Order to
     *  Make the Comparison Operations Quicker and Easier. String Objects Occupy More
     *  Memory Space than regular Primitive Char Variables. However, Most of the Methods
     *  Contained Within this Class Pass in String Objects as their Parameter Values. Thus,
     *  it Would be Simpler to Compare a String Object With another String Object, instead
     *  of Breaking the String Object Apart into a Series of Char Values and Comparing Each
     *  Char with every Entry of the Array Below. This wouldn't normally be true. However,
     *  the Java Programming Language has Installed Many Useful String Manipulation Methods
     *  which allow this Approach to be the Simplest between the two.)
     */
    public String[] specialCharsEmail = {"=", "[", "]", "{", "}", "_", "|", ":", ";", "<", "!", "?", "#", "$", "%", "^",  "&", "*", "(", ")", "~", ",",
         "å", "†", "ç", "ƒ", "©", "™",  "š", "¬", "µ", "ø", "½", "¼",  "¾", "œ", "®", "ß", "Ý", "…",  "‰", "ˆ", "¥", "‡", "Å", "¹", "Ç",  "Î", "Ï",
         "›", "Ó", "•", "Ò", "Â",  "Ø", "Œ", "Í", "", "–", "º", "¡",  "™", "£", "¢", "ƒ", "§", "¶", "ª",  "æ", "Ž", "€", "Ð", "ð", "Þ", "þ",  
         "ý", "°", "·", "±", "Æ", "Ú",  "¿", "‡", "¨", "×", "\"", "'", "/", "\\", "^", ">", "<", "-", "`", "~", "+", "="};
    
    /**
     *  Create a String Object Array Which is Composed only of the Full Series of
     *  Digit Characters (i.e: Numbers [0 - 9]). The Methods in this Class will use this 
     *  Array Value in order to Determine if the Passed in Parameter String Contains 
     *  any of the Characters Shown Below.
     *  
     *  (Note: We Use the String Object as the Variable Type in this Array in Order to
     *  Make the Comparison Operations Quicker and Easier. String Objects Occupy More
     *  Memory Space than regular Primitive Char Variables. However, Most of the Methods
     *  Contained Within this Class Pass in String Objects as their Parameter Values. Thus,
     *  it Would be Simpler to Compare a String Object With another String Object, instead
     *  of Breaking the String Object Apart into a Series of Char Values and Comparing Each
     *  Char with every Entry of the Array Below. This wouldn't normally be true. However,
     *  the Java Programming Language has Installed Many Useful String Manipulation Methods
     *  which allow this Approach to be the Simplest between the two.)
     */
    public String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    
    /**
     *  Create a String Object Array Which is Composed only of the Full Series of
     *  Alphabetic Characters (i.e: Letters [a - z] && [A - Z]). The Methods in this
     *  Class will use this Array Value in order to Determine if the Passed in Parameter
     *  String Contains any of the Characters Shown Below.
     *  
     *  (Note: We Use the String Object as the Variable Type in this Array in Order to
     *  Make the Comparison Operations Quicker and Easier. String Objects Occupy More
     *  Memory Space than regular Primitive Char Variables. However, Most of the Methods
     *  Contained Within this Class Pass in String Objects as their Parameter Values. Thus,
     *  it Would be Simpler to Compare a String Object With another String Object, instead
     *  of Breaking the String Object Apart into a Series of Char Values and Comparing Each
     *  Char with every Entry of the Array Below. This wouldn't normally be true. However,
     *  the Java Programming Language has Installed Many Useful String Manipulation Methods
     *  which allow this Approach to be the Simplest between the two.)
     */
    public String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
        "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "Z"};

    /**
     *  This Method is Used to Determine if the User Response
     *  Parameter String Contains Any Digit Characters. This
     *  Method is Typically invoked Whenever the Application
     *  is Checking the User Input Fields Dealing with the
     *  User's Name Values seeing as these Fields Cannot
     *  Contain Digit Characters.
     * 
     *  @param value                The User Response String
     *                              Object Value.
     * 
     *  @return                     A Boolean Value that lets
     *                              the Application Know if the
     *                              Parameter String Contains
     *                              any Digit Characters Within
     *                              it.
     *          
     *                              True    -   The Response Contains
     *                                          Digit Characters.
     * 
     *                              False   -   The Response Doesn't
     *                                          Contain Digit Characters.
     */
    public boolean checkForOnlyNumbersInString(String value) {
        /**
         *  Iterate Through Each Entry of the Numbers Global
         *  Array and Check if Each Digit Character is Present
         *  Within the String. If the Current Array Entry is
         *  Present, then Return a Value of True.
         */
        for (int i = 0; i != numbers.length; i++) {
            /**
             *  If the Current Digit Value is Present
             *  Within the String, then Return a
             *  Value of True to Let the Application
             *  Know That a Digit Character is Present.
             */
            if (value.contains(numbers[i])) {
                // Return Method Result Value.
                return true;
            }
        }
        // Return Method Result Value.
        return false;
    }
    
    /**
     *  This Method is Used in Order to Verify that
     *  the Phone Number Response that the User Has
     *  Provided is a Valid String Containing only
     *  Numerical Characters. This Method is Invoked
     *  Within the Profile Management Screen Where
     *  the User can Add Additional Contact Information
     *  to His or Her Account.
     * 
     *  @param value            The User Response String
     *                          Object Retrieved from the
     *                          Formatted Field GUI Component.
     *                          
     *  @return                 A New String Value With All
     *                          of the Numerical Characters
     *                          being Replaced By the '#'
     *                          Character. If the Return String
     *                          isn't Equal to the Value:
     * 
     *                          (###) - ### - ####
     * 
     *                          Then it is Deemed to Not be
     *                          a Valid Phone Number Response.        
     */
    public String checkPhoneNumberValidation(String value) {
        /**
         *  Replace all Occurrences of the '0'
         *  Numerical Character Within the Parameter 
         *  String with the '#' Character.
         */
        value = value.replace('0', '#');
        /**
         *  Replace all Occurrences of the '1'
         *  Numerical Character Within the Parameter 
         *  String with the '#' Character.
         */
        value = value.replace('1', '#');
        /**
         *  Replace all Occurrences of the '2'
         *  Numerical Character Within the Parameter 
         *  String with the '#' Character.
         */
        value = value.replace('2', '#');
        /**
         *  Replace all Occurrences of the '3'
         *  Numerical Character Within the Parameter 
         *  String with the '#' Character.
         */
        value = value.replace('3', '#');
        /**
         *  Replace all Occurrences of the '4'
         *  Numerical Character Within the Parameter 
         *  String with the '#' Character.
         */
        value = value.replace('4', '#');
        /**
         *  Replace all Occurrences of the '5'
         *  Numerical Character Within the Parameter 
         *  String with the '#' Character.
         */
        value = value.replace('5', '#');
        /**
         *  Replace all Occurrences of the '6'
         *  Numerical Character Within the Parameter 
         *  String with the '#' Character.
         */
        value = value.replace('6', '#');
        /**
         *  Replace all Occurrences of the '7'
         *  Numerical Character Within the Parameter 
         *  String with the '#' Character.
         */
        value = value.replace('7', '#');
        /**
         *  Replace all Occurrences of the '8'
         *  Numerical Character Within the Parameter 
         *  String with the '#' Character.
         */
        value = value.replace('8', '#');
        /**
         *  Replace all Occurrences of the '9'
         *  Numerical Character Within the Parameter 
         *  String with the '#' Character.
         */
        value = value.replace('9', '#');
        
        /**
         *  Return the Newly Formatted User
         *  Response String Value.
         */
        return value;
    }

    /**
     *  This Method is Used to Determine if the User Response
     *  Parameter String Contains Any of the Prohibited Special
     *  Characters Defined Within the Global Special Chars String
     *  Array. This Method is Invoked on Every User Response Value
     *  String seeing as the User Response Values are Unable to
     *  Contain Any of the Characters Present Within this List.
     * 
     *  @param value                The User Response String
     *                              Object Value.
     * 
     *  @return                     A Boolean Value that lets
     *                              the Application Know if the
     *                              Parameter String Contains
     *                              any of the Specified Prohibited 
     *                              Characters Within it.
     *          
     *                              True    -   The Response Contains
     *                                          Prohibited Characters.
     * 
     *                              False   -   The Response Doesn't
     *                                          Contain Prohibited Characters.
     */
    public boolean checkForSpecialCharactersInString(String value) {
        /**
         *  Iterate Through Each Entry of the Special Chars Global
         *  Array and Check if Each Prohibited Character is Present
         *  Within the String. If the Current Array Entry is
         *  Present, then Return a Value of True.
         */
        for (int i = 0; i != specialChars.length; i++) {
            /**
             *  If the Current Prohibited Character Value
             *  is Present Within the String, then Return a
             *  Value of True to Let the Application
             *  Know That the Parameter String Contains
             *  a Prohibited Character.
             */
            if (value.contains(specialChars[i])) {
                // Return Method Result Value.
                return true;
            }
        }
        
        // Return Method Result Value.
        return false;
    }
    
    /**
     *  This Method is Used to Determine if the User Response
     *  Parameter "Email Address" String Contains Any of the Prohibited
     *  Special Characters Defined Within the Global Special Chars Email
     *  String Array. This Method is Invoked on the Email Address User
     *  Response Field Seeing as this Field is Required to Have Certain
     *  Special Characters, which are Omitted Within the Special Chars
     *  Email Global Array Value.
     * 
     *  @param value                The User Response String
     *                              Object Value.
     * 
     *  @return                     A Boolean Value that lets
     *                              the Application Know if the
     *                              Parameter String Contains
     *                              any of the Specified Prohibited 
     *                              Characters Within it.
     *          
     *                              True    -   The Response Contains
     *                                          Prohibited Characters.
     * 
     *                              False   -   The Response Doesn't
     *                                          Contain Prohibited Characters.
     */
    public boolean checkForSpecialCharactersInStringEmail(String value) {
        /**
         *  Iterate Through Each Entry of the Special Chars Email Global
         *  Array and Check if Each Prohibited Character is Present
         *  Within the String. If the Current Array Entry is
         *  Present, then Return a Value of True.
         */
        for (int i = 0; i != specialCharsEmail.length; i++) {
            /**
             *  If the Current Prohibited Character Value
             *  is Present Within the String, then Return a
             *  Value of True to Let the Application
             *  Know That the Parameter String Contains
             *  a Prohibited Email Field Character.
             */
            if (value.contains(specialCharsEmail[i])) {
                // Return Method Result Value.
                return true;
            }
        }
        // Return Method Result Value.
        return false;
    }
    
    /**
     *  This Method is Used in Order to Determine if the
     *  Parameter String Contains Any Alphabetic or Letter
     *  Characters Within it. This Method is Typically Called
     *  on User Response Values Which can Only Contain Numeric
     *  Characters such as the Phone Number or Identification
     *  Number Associated With the Account.
     * 
     *  @param value                The User Response String
     *                              Object Value.
     * 
     *  @return                     A Boolean Value that lets
     *                              the Application Know if the
     *                              Parameter String Contains
     *                              any of the Specified Alphabetic 
     *                              Characters Within it.
     *          
     *                              True    -   The Response Contains
     *                                          Alphabetic Characters.
     * 
     *                              False   -   The Response Doesn't
     *                                          Contain Alphabetic Characters.
     */
    public boolean checkForLetterCharactersInString(String value) {
        /**
         *  Iterate Through Each Entry of the Letters Global
         *  Array and Check if Each Alphabetic Character is Present
         *  Within the String. If the Current Array Entry is
         *  Present, then Return a Value of True.
         */
        for (int i = 0; i != letters.length; i++) {
            /**
             *  If the Current Alphabetic Character Value
             *  is Present Within the String, then Return a
             *  Value of True to Let the Application
             *  Know That the Parameter String Contains
             *  a Alphabetic Character.
             */
            if (value.contains(letters[i])) {
                // Return Method Result Value.
                return true;
            }
        }
        
        // Return Method Result Value.
        return false;
    }
    
    /**
     *  This Method is Used in Order to Determine How Many Numeric
     *  Characters are Present within the Parameter String Value. This
     *  Method is Typically Invoked When Checking Fields Such as the
     *  Current User's Chosen Username or Password Values seeing as
     *  both of these Fields Require the User to Enter a Certain Amount
     *  of Numeric Characters in their Values in order to Increase
     *  Individuality and Uniqueness For the Account's Details.
     * 
     *  @param value                    The User Response String
     *                                  Object Value.
     * 
     *  @return                         An Integer Value that lets
     *                                  the Application Know how Many
     *                                  Numeric Characters are Present
     *                                  Within the Parameter String.
     * 
     */
    public int checkAmountOfNumbersInString(String value) {
        /**
         *  Create an Integer Variable Which Will
         *  Be Used in Order to Count How Many Numerical
         *  Characters are Present Within the Parameter
         *  String.
         */
        int counter = 0;
            
        /**
         *  Iterate Through Each Character of the Parameter
         *  String Value.
         */
        for (int i = 0; i < value.length(); i++) {  
            /**
             *  If the Current Character Within the String is
             *  Equal to Any of the Numerical Character Values
             *  Shown Below, then Increment the Counter Variable.
             */
            if (value.charAt(i) == '0' || value.charAt(i) == '1' || value.charAt(i) == '2' || value.charAt(i) == '3' ||
                value.charAt(i) == '4' || value.charAt(i) == '5' || value.charAt(i) == '6' || value.charAt(i) == '7' ||
                value.charAt(i) == '8' || value.charAt(i) == '9') {  
                // Increment Counter
                counter++;   
            }
        }
        
        /**
         *  Return the Number of Numerical
         *  Characters Present Within the
         *  Parameter String.
         */
        return counter;
    }
    
    /**
     *  This Method is Used to Determine if An Error Has Occurred in the
     *  User Response Validation Segment During Both the Instructor and
     *  Student Account Creation Process. This Method Accepts the Boolean
     *  Array Error Values that are Set Within Both the Instructor and
     *  Student Account Creation Classes. Depending on the Values Held
     *  Within these Boolean Array Values, this Method will output a
     *  Corresponding Error Message String Object. If all of the User's
     *  Answers are Considered to Valid, then the Method Will Return
     *  the Default "All Fields Contain Valid Answers." String Phrase
     *  Which will let the Application Know that it May Continue.
     *  
     *  @param v1                       The Boolean Array Value Which
     *                                  Contains the Validation Process
     *                                  Error Results Dealing with the
     *                                  "First Name" Account Creation
     *                                  User Response Field.
     * 
     *  @param v2                       The Boolean Array Value Which
     *                                  Contains the Validation Process
     *                                  Error Results Dealing with the
     *                                  "Last Name" Account Creation
     *                                  User Response Field.
     * 
     *  @param v3                       The Boolean Array Value Which
     *                                  Contains the Validation Process
     *                                  Error Results Dealing with the
     *                                  "Email Address" Account Creation
     *                                  User Response Field.
     * 
     *  @param v4                       The Boolean Array Value Which
     *                                  Contains the Validation Process
     *                                  Error Results Dealing with the
     *                                  "Username" Account Creation
     *                                  User Response Field.
     * 
     *  @param v5                       The Boolean Array Value Which
     *                                  Contains the Validation Process
     *                                  Error Results Dealing with the
     *                                  "Confirm Username" Account Creation
     *                                  User Response Field.
     * 
     *  @param v6                       The Boolean Array Value Which
     *                                  Contains the Validation Process
     *                                  Error Results Dealing with the
     *                                  "Password" Account Creation
     *                                  User Response Field.
     * 
     *  @param v7                       The Boolean Array Value Which
     *                                  Contains the Validation Process
     *                                  Error Results Dealing with the
     *                                  "Confirm Password" Account Creation
     *                                  User Response Field.
     * 
     *  @return                         The String Message Which Lets the
     *                                  Either the Account Creation "Instructor"
     *                                  or "Student" GUI Window Form Know
     *                                  if the Validation Process was Either
     *                                  a Failure or a Success.
     */
    public String determineErrorStatus(boolean[] v1, boolean[] v2, boolean[] v3, boolean[] v4, boolean[] v5, boolean[] v6, boolean[] v7) {
        /**
         *  Create a Counter Variable
         *  Which is Used to Determine How
         *  Many Errors Have Occurred in the
         *  Validation Process if Any. This
         *  Variable is Needed In Order to
         *  Determine Whether or Not to Return
         *  the "Multiple Fields Contain 
         *  Prohibited Characters or Blank 
         *  Responses." Error String Message.
         */
        int counter = 0;
        
        /**
         *  If the "First Name" User Response Field
         *  Contains or is Solely Composed of Whitespace
         *  Characters, then Increment the Error Counter
         *  Variable.
         */
        if (v1[0] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        /**
         *  If the "First Name" User Response Field
         *  Contains or is Solely Composed of Numerical
         *  Digit Characters, then Increment the Error
         *  Counter Variable.
         */
        if (v1[1] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        /**
         *  If the "First Name" User Response Field
         *  Contains or is Solely Composed of Prohibited
         *  Special Characters, then Increment the Error
         *  Counter Variable.
         */
        if (v1[2] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        /**
         *  If the "Last Name" User Response Field
         *  Contains or is Solely Composed of Whitespace
         *  Characters, then Increment the Error Counter
         *  Variable.
         */
        if (v2[0] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        /**
         *  If the "Last Name" User Response Field
         *  Contains or is Solely Composed of Numerical
         *  Digit Characters, then Increment the Error
         *  Counter Variable.
         */
        if (v2[1] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        /**
         *  If the "Last Name" User Response Field
         *  Contains or is Solely Composed of Prohibited
         *  Special Characters, then Increment the Error
         *  Counter Variable.
         */
        if (v2[2] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        /**
         *  If the "Email Address" User Response Field
         *  Contains or is Solely Composed of Whitespace
         *  Characters, then Increment the Error Counter
         *  Variable.
         */
        if (v3[0] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        /**
         *  If the "Email Address" User Response Field
         *  Contains or is Solely Composed of Prohibited
         *  Special Characters, then Increment the Error
         *  Counter Variable.
         */
        if (v3[1] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        /**
         *  If the "Username" User Response Field
         *  Contains or is Solely Composed of Whitespace
         *  Characters, then Increment the Error Counter
         *  Variable.
         */
        if (v4[0] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        /**
         *  If the "Username" User Response Field
         *  Contains or is Solely Composed of Prohibited
         *  Special Characters, then Increment the Error
         *  Counter Variable.
         */
        if (v4[1] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        /**
         *  If the "Confirm Username" User Response Field
         *  Contains or is Solely Composed of Whitespace
         *  Characters, then Increment the Error Counter
         *  Variable.
         */
        if (v5[0] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        /**
         *  If the "Confirm Username" User Response Field
         *  Contains or is Solely Composed of Prohibited
         *  Special Characters, then Increment the Error
         *  Counter Variable.
         */
        if (v5[1] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        /**
         *  If the "Password" User Response Field
         *  Contains or is Solely Composed of Whitespace
         *  Characters, then Increment the Error Counter
         *  Variable.
         */
        if (v6[0] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        /**
         *  If the "Password" User Response Field
         *  Contains or is Solely Composed of Prohibited
         *  Special Characters, then Increment the Error
         *  Counter Variable.
         */
        if (v6[1] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        /**
         *  If the "Confirm Password" User Response Field
         *  Contains or is Solely Composed of Whitespace
         *  Characters, then Increment the Error Counter
         *  Variable.
         */
        if (v7[0] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        /**
         *  If the "Confirm Password" User Response Field
         *  Contains or is Solely Composed of Prohibited
         *  Special Characters, then Increment the Error
         *  Counter Variable.
         */
        if (v7[1] == true) {
            /**
             *  Increment Error Counter
             *  Integer Variable.
             */
            counter++;
        }
        
        /**
         *  If there is More than A Single Error Present
         *  Within the Account Creation Form, then Return
         *  the Custom Error Message Result String.
         */
        if (counter > 1) {
            // Return Custom Error Status Result Message.
            return "Multiple Fields Contain Prohibited Characters or Blank Responses.";
        }
        /**
         *  If the "First Name" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Whitespace Characters,
         *  then return the Custom Error Value String.
         */
        else if (v1[0] == true) {
            // Return Custom Error Status Result Message.
            return "The First Name Field Contains a Blank Answer.";
        }
        /**
         *  If the "First Name" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Numerical Characters,
         *  then return the Custom Error Value String.
         */
        else if (v1[1] == true) {
            // Return Custom Error Status Result Message.
            return "The First Name Field Contains Prohibited Number Characters.";
        }
        /**
         *  If the "First Name" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Prohibited Special
         *  Characters, then return the Custom Error Value String.
         */
        else if (v1[2] == true) {
            // Return Custom Error Status Result Message.
            return "The First Name Field Contains Prohibited Special Characters.";
        }
        /**
         *  If the "Last Name" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Whitespace Characters,
         *  then return the Custom Error Value String.
         */
        else if (v2[0] == true) {
            // Return Custom Error Status Result Message.
            return "The Last Name Field Contains a Blank Answer.";
        }
        /**
         *  If the "Last Name" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Numerical Characters,
         *  then return the Custom Error Value String.
         */
        else if (v2[1] == true) {
            // Return Custom Error Status Result Message.
            return "The Last Name Field Contains Prohibited Number Characters.";
        }
        /**
         *  If the "Last Name" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Prohibited Special
         *  Characters, then return the Custom Error Value String.
         */
        else if (v2[2] == true) {
            // Return Custom Error Status Result Message.
            return "The Last Name Field Contains Prohibited Special Characters.";
        }
        /**
         *  If the "Email Address" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Whitespace Characters,
         *  then return the Custom Error Value String.
         */
        else if (v3[0] == true) {
            // Return Custom Error Status Result Message.
            return "The Email Address Field Contains a Blank Answer.";
        }
        /**
         *  If the "Email Address" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Prohibited Special
         *  Characters, then return the Custom Error Value String.
         */
        else if (v3[1] == true) {
            // Return Custom Error Status Result Message.
            return "The Email Address Field Contains Prohibited Special Characters.";
        }
        /**
         *  If the "Username" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Whitespace Characters,
         *  then return the Custom Error Value String.
         */
        else if (v4[0] == true) {
            // Return Custom Error Status Result Message.
            return "The Username Field Contains a Blank Answer.";
        }
        /**
         *  If the "Username" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Prohibited Special
         *  Characters, then return the Custom Error Value String.
         */
        else if (v4[1] == true) {
            // Return Custom Error Status Result Message.
            return "The Username Field Contains Prohibited Special Characters.";
        }
        /**
         *  If the "Confirm Username" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Whitespace Characters,
         *  then return the Custom Error Value String.
         */
        else if (v5[0] == true) {
            // Return Custom Error Status Result Message.
            return "The Confirm Username Field Contains a Blank Answer.";
        }
        /**
         *  If the "Confirm Username" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Prohibited Special
         *  Characters, then return the Custom Error Value String.
         */
        else if (v5[1] == true) {
            // Return Custom Error Status Result Message.
            return "The Confirm Username Field Contains Prohibited Special Characters.";
        }
        /**
         *  If the "Password" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Whitespace Characters,
         *  then return the Custom Error Value String.
         */
        else if (v6[0] == true) {
            // Return Custom Error Status Result Message.
            return "The Password Field Contains a Blank Answer.";
        }
        /**
         *  If the "Password" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Prohibited Special
         *  Characters, then return the Custom Error Value String.
         */
        else if (v6[1] == true) {
            // Return Custom Error Status Result Message.
            return "The Password Field Contains Prohibited Special Characters.";
        }
        /**
         *  If the "Confirm Password" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Whitespace Characters,
         *  then return the Custom Error Value String.
         */
        else if (v7[0] == true) {
            // Return Custom Error Status Result Message.
            return "The Confirm Password Field Contains a Blank Answer.";
        }
        /**
         *  If the "Confirm Password" User Response Field is the
         *  only Field Containing an Error Due to Containing
         *  or being Solely Composed of Prohibited Special
         *  Characters, then return the Custom Error Value String.
         */
        else if (v7[1] == true) {
            // Return Custom Error Status Result Message.
            return "The Confirm Password Field Contains Prohibited Special Characters.";
        }
        /**
         *  If All of the User Response Values Have Been
         *  Deemed to Be Valid Answers, then Return the
         *  Custom Result Message which lets the Account
         *  Creation GUI Know it Can Continue on with
         *  the Account Creation Process.
         */
        else {
            // Return Full Validation Result Message.
            return "All Fields Contain Valid Answers.";
        }
    }
}
