/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.io.Serializable;

/**
 *  This Class is Used in Order to Create a New
 *  Custom Object Instance Which is Meant to Represent
 *  a "Class" or "Course" that the Student Accounts
 *  Present Within the Application can be Enrolled
 *  into. These Class Objects are Used in Order to
 *  Provide Information Concerning the Current Course
 *  that a Student is Taking in Addition to Providing
 *  a Reference that the Application can Link Attendance
 *  Records to.
 * 
 *  @date           November 10 2020
 *  @author         Tyler Fontana
 *  @version        1.0.2
 */
public class ClassType implements Serializable {
    
    /**
     *  Variable Meant to Hold the
     *  Unique "Class" or "Course"
     *  Integer Row Entry ID Number
     *  Assigned by the Database.
     */
    public int id;
    /**
     *  Variable Meant to Hold the
     *  Field Type Associated with
     *  the Course.
     * 
     *  (Ex. "CSCI", "MATH", "AERO"
     *  "CHEM" etc.)
     */
    public String field;
    /**
     *  Variable Meant to Hold the
     *  Class Number Associated with
     *  the Course.
     *
     *  (Ex. "1001", "2567", "3100"
     *  "4502" etc.)
     */
    public int classNumber;
    /**
     *  Variable Meant to Hold the
     *  Course Title Associated with
     *  the Course.
     * 
     *  (Ex. "Introductory to Software
     *   Engineering", "Operating Systems",
     *   "Biology I", etc.)
     */
    public String title;
    /**
     *  Variable Meant to Hold the
     *  Lecture Type Associated with
     *  the Course.
     * 
     *  (Ex. "Lecture", "Laboratory",
     *  "Seminar, etc.)
     */
    public String type;
    /**
     *  Variable Meant to Hold the
     *  Description Associated with
     *  the Course. This Field Can
     *  Contains Things Such as
     *  Extraneous Class Information
     *  and Prerequisites.
     */
    public String description;
    /**
     *  The Amount of Hours that
     *  this Course is Rated For.
     * 
     *  (Ex. "3.00", "1.00", "4.00",
     *   "5.00" etc.)
     */
    public String creditHours;
    /**
     *  The Section Number Associated
     *  With the Course.
     * 
     *  (Ex. "001", "002", "003"
     *  "012" etc.)
     */
    public String section;
    /**
     *  The Semester Associated With
     *  the Course.
     *  
     *  (Ex. "Spring", "Fall", "Winter",
     *   "Summer")
     */
    public String semester;
    /**
     *  The Year When the Course Takes
     *  Place. 
     * 
     *  (Note: This Feature May Be
     *  Expanded Upon Further in Later
     *  Versions of the Application.)
     * 
     *  (Ex. "2012", "2015", "2020"
     *   "2021" etc.)
     */
    public int year;
    /**
     *  The Number of Participants or
     *  Students Currently Enrolled
     *  Within the Class.
     */
    public int participants;
    /**
     *  This Boolean Value is Used in
     *  Order to Determine if There Are
     *  Multiple Class Objects Present
     *  Within a List Type Object Which
     *  Contain the Same Class Title. If
     *  This Happens, then the Class Display
     *  Name is Changed Via the toString()
     *  Method Within the Calling GUI Window.
     */
    public boolean namePresent = false;

    
    /**
     *  This Constructor is Used in Order to Create a New Instance of the Custom
     *  ClassType Object. This Object Accept Parameter Values for all of the Values
     *  Listed Above Excluding the Duplicate Status Boolean Value. This Value can
     *  only be Changed via one of the Setter Methods Listed at the End of the Class.
     * 
     *  @param classID                          The Unique Integer Row ID Integer
     *                                          Value that is Assigned to the Class
     *                                          or Course By the Remote Database.
     * 
     *  @param classField                       The Field Type Value that Defines
     *                                          what Major the Class is Part of.
     * 
     *  @param classNumb                        The Class or Course Number.
     * 
     *  @param name                             The Class Title
     * 
     *  @param structureType                    The Lecture Type that the Class
     *                                          or Course Follows.
     * 
     *  @param desc                             The Description of the Class that
     *                                          may Included Auxiliary Information
     *                                          and Course Prerequisites.
     * 
     *  @param hours                            The Total Number of Credit Hours
     *                                          that the Course Counts For.
     * 
     *  @param sectionNum                       The Identification Section Number
     *                                          that is Used to Differentiate
     *                                          Between Classes containing the
     *                                          Same Title / Instructor.
     * 
     *  @param semesterType                     The Semester that the Course
     *                                          Takes Place During.
     * 
     *  @param yearOfClass                      The Year that the Course Takes
     *                                          Place During.
     * 
     *  @param numOfParticipants                The Number of Students That Can
     *                                          Be Enrolled into the Class at
     *                                          any given time. This Value is Used
     *                                          as a Limiter.
     */
    public ClassType(int classID, String classField, int classNumb, String name, String structureType, String desc, String hours, String sectionNum, String semesterType, int yearOfClass, int numOfParticipants) {
        // Set Class Row ID Integer
        id = classID;
        // Set Class Field String
        field = classField;
        // Set Class Number Integer
        classNumber = classNumb;
        // Set Class Title String
        title = name;
        // Set Class Lecture String
        type = structureType;
        // Set Class Decription String
        description = desc;
        // Set Class Credit Hours String
        creditHours = hours;
        // Set Class Section Number String
        section = sectionNum;
        // Set Class Semester String
        semester = semesterType;
        // Set Class Year Integer
        year = yearOfClass;
        // Set Class Student Limit
        participants = numOfParticipants;
    }
    
    /**
     *  This Getter Method is Responsible for
     *  Returning the Unique Class or Course
     *  Row "ID" Integer which is Assigned to
     *  it via the Remote Database.
     * 
     *  @return         The ID Integer Value.
     */
    public int getClassID() {
        /**
         *  Return Field Instance
         *  Variable Value.
         */
        return id;
    }
    
    /**
     *  This Getter Method is Responsible for
     *  Returning the Field Categorization that
     *  the Course Falls Under.
     * 
     *  @return         The Field Type String
     *                  Value.
     */
    public String getClassField() {
        /**
         *  Return Field Instance
         *  Variable Value.
         */
        return field;
    }
    
    /**
     *  This Getter Method is Responsible for
     *  Returning the Class Number Integer that
     *  the Course is Assigned With.
     * 
     *  @return         The Class Number Integer
     *                  Value.
     */
    public int getClassNumber() {
        /**
         *  Return Field Instance
         *  Variable Value.
         */
        return classNumber;
    }
    
    /**
     *  This Getter Method is Responsible for
     *  Returning the Class Title that
     *  the Course is Labeled as.
     * 
     *  @return         The Class Title String
     *                  Value.
     */
    public String getClassTitle() {
        /**
         *  Return Field Instance
         *  Variable Value.
         */
        return title;
    }
    
    /**
     *  This Getter Method is Responsible for
     *  Returning the Class Lecture Type that
     *  the Course is Structured as.
     * 
     *  @return         The Structure Style String
     *                  Value.
     */
    public String getClassStructureType() {
        /**
         *  Return Field Instance
         *  Variable Value.
         */
        return type;
    }
    
    /**
     *  This Getter Method is Responsible for
     *  Returning the Class Description that
     *  
     * 
     *  @return         The Class Number Integer
     *                  Value.
     */
    public String getClassDescription() {
        /**
         *  Return Field Instance
         *  Variable Value.
         */
        return description;
    }
    
    /**
     *  This Getter Method is Responsible for
     *  Returning the Number of Credit Hours
     *  that the Current Course Instance Counts
     *  For.
     * 
     *  @return         The String Value
     *                  Containing the Credit
     *                  Hours that the Class
     *                  Counts For.
     */
    public String getClassCreditHours() {
        /**
         *  Return Field Instance
         *  Variable Value.
         */
        return creditHours;
    }
    
    /**
     *  This Getter Method is Responsible for
     *  Returning the Class Number Integer
     *  Identifier.
     * 
     *  @return         The Class Number that
     *                  is Associated with the
     *                  Current Course Instance.
     */
    public String getClassSection() {
        /**
         *  Return Field Instance
         *  Variable Value.
         */
        return section;
    }
    
    /**
     *  This Getter Method is Responsible for
     *  Returning the Class Semester String
     *  Value.
     * 
     *  @return         The String Containing
     *                  the Semester that the
     *                  Course Takes Place
     *                  During.
     */
    public String getClassSemester() {
        /**
         *  Return Field Instance
         *  Variable Value.
         */
        return semester;
    }
    
    /**
     *  This Getter Method is Responsible for
     *  Returning the Year Integer Value when
     *  the Course takes Place.
     * 
     *  @return         The Integer Value
     *                  Containing the Year
     *                  Value in Which the
     *                  Current Course Instance
     *                  takes place.
     */
    public int getClassYear() {
        /**
         *  Return Field Instance
         *  Variable Value.
         */
        return year;
    }
    
    /**
     *  This Getter Method is Responsible for
     *  Returning the Integer Value Containing
     *  the MAX Number of Students / Particpants
     *  that a Course can Contain.
     * 
     *  @return         The Max Number of
     *                  Participants.
     */
    public int getClassParticipants() {
        /**
         *  Return Field Instance
         *  Variable Value.
         */
        return participants;
    }
    
    /**
     *  This Setter Method is Responsible for
     *  Setting the Unique Row Entry ID Value
     *  of the Current ClassType Object
     *  Instance. This Value is Assigned
     *  by the Remote Database Upon the
     *  Class's Creation within the Database.
     * 
     *  @param value        The Integer ID
     *                      Value Containing the
     *                      Unique Row ID Number.
     */
    public void setClassID(int value) {
        /**
         *  Set Field Instance
         *  Variable Value.
         */
        id = value;
    }
    
    /**
     *  This Setter Method is Responsible for
     *  Setting the String Identification Value
     *  Relating to the "Class Field" Field of
     *  the Current Course Instance. 
     * 
     *  @param value        The Class Field With
     *                      Shows Which Department
     *                      Major the Course Falls
     *                      Under.
     */
    public void setClassField(String value) {
        /**
         *  Set Field Instance
         *  Variable Value.
         */
        field = value;
    }
    
    /**
     *  This Setter Method is Responsible for
     *  Setting the String Identification Value
     *  Relating to the "Class Title" Field of
     *  the Current Course Instance. 
     * 
     *  @param value        The Class Title Which
     *                      is the Main Identifier
     *                      Used to Differentiate
     *                      Between Classes.
     */
    public void setClassTitle(String value) {
        /**
         *  Set Field Instance
         *  Variable Value.
         */
        title = value;
    }
    
    /**
     *  This Setter Method is Responsible for
     *  Setting the String Identification Value
     *  Relating to the "Class Lecture Type" Field of
     *  the Current Course Instance. 
     * 
     *  @param value        The Class Lecture or
     *                      Structure Type which
     *                      Explains how the Class
     *                      Functions in Regards to
     *                      Teaching.
     */
    public void setClassStructureType(String value) {
        /**
         *  Set Field Instance
         *  Variable Value.
         */
        type = value;
    }
    
    /**
     *  This Setter Method is Responsible for
     *  Setting the String Identification Value
     *  Relating to the "Class Description" Field of
     *  the Current Course Instance. 
     * 
     *  @param value        The Class Description
     *                      String which Informs the
     *                      User of What the Class
     *                      Entails in Addition to
     *                      any Prerequisites Needed.
     */
    public void setClassDescription(String value) {
        /**
         *  Set Field Instance
         *  Variable Value.
         */
        description = value;
    }
    
    /**
     *  This Setter Method is Responsible for
     *  Setting the String Identification Value
     *  Relating to the "Class Credit Hours" Field of
     *  the Current Course Instance. 
     * 
     *  @param value        The Class Credit Hours
     *                      Which Determines How
     *                      Much Time the Course is
     *                      Worth on a Students
     *                      Degree Audit.
     */
    public void setClassCreditHours(String value) {
        /**
         *  Set Field Instance
         *  Variable Value.
         */
        creditHours = value;
    }
    
    /**
     *  This Setter Method is Responsible for
     *  Setting the String Identification Value
     *  Relating to the "Class Number" Field of
     *  the Current Course Instance. 
     * 
     *  @param value        The Class Number Integer
     *                      Value which is Used in
     *                      Order to Differentiate
     *                      Between Classes using
     *                      the Same Title.
     */
    public void setClassNumber(int value) {
        /**
         *  Set Field Instance
         *  Variable Value.
         */
        classNumber = value;
    }
    
    /**
     *  This Setter Method is Responsible for
     *  Setting the String Identification Value
     *  Relating to the "Class Section" Field of
     *  the Current Course Instance. 
     * 
     *  @param value        The Class Section
     *                      Which is Used as an
     *                      Identifier For
     *                      Differentiating
     *                      Between Class Instances.
     */
    public void setClassSection(String value) {
        /**
         *  Set Field Instance
         *  Variable Value.
         */
        section = value;
    }
    
    /**
     *  This Setter Method is Responsible for
     *  Setting the String Identification Value
     *  Relating to the "Class Semester" Field of
     *  the Current Course Instance. 
     * 
     *  @param value        The Class Semester that
     *                      is Used in Order to 
     *                      Determine which Interval
     *                      During the Year the
     *                      Class Takes Place in.
     */
    public void setClassSemester(String value) {
        /**
         *  Set Field Instance
         *  Variable Value.
         */
        semester = value;
    }
    
    /**
     *  This Setter Method is Responsible for
     *  Setting the String Identification Value
     *  Relating to the "Class Year" Field of
     *  the Current Course Instance. 
     * 
     *  @param value        The Class Year that
     *                      is Used in Order to 
     *                      Determine which Time
     *                      Period the Class was
     *                      Held During.
     */
    public void setClassYear(int value) {
        /**
         *  Set Field Instance
         *  Variable Value.
         */
        year = value;
    }
    
    /**
     *  This Setter Method is Responsible for
     *  Setting the String Identification Value
     *  Relating to the "Class Participants" Field of
     *  the Current Course Instance. 
     * 
     *  @param value        The Maximum Number of
     *                      Students Allowed to be
     *                      Enrolled in the Current
     *                      Class Instance at any
     *                      Given Time.             
     */
    public void setClassParticipants(int value) {
        /**
         *  Set Field Instance
         *  Variable Value.
         */
        participants = value;
    }
    
    /**
     *  This Method is Used in Order to Set the Duplicate
     *  Status Boolean Variable Value Contained within
     *  this Class. This Object Field is only set
     *  Providing that the Current Class Instance is
     *  Present Within a List Type Object Containing
     *  other ClassType Objects which use the Same
     *  Course Title.
     * 
     *  @param value            The Boolean Status Value
     *                          Which is Used to Let Mark
     *                          the Object as having a
     *                          Duplicate Course Title.
     */
    public void setDuplicateStatus(boolean value) {
        /**
         *  Set Field Instance
         *  Variable Value.
         */
        namePresent = value;
    }
    
    /**
     *  This Method is Used in Order to Display the Current Object Instance in the Format of a
     *  String. It is invoked whenever a Component within the Main Screen GUI Window Contains
     *  a List of ClassType Objects for the User to choose from.
     * 
     *  @return             This Method Returns the Class Title as the String Value that will 
     *                      be Displayed Within the GUI Component. However, should the Duplicate
     *                      Status Boolean Variable Contain a Value of True, then the ClassType 
     *                      Object Instance will Instead Display a String Value Containing the 
     *                      Course Title, Semester, Year, and Section Number instead. This
     *                      is Done in Order to Allow the User to Differentiate Between
     *                      Two Classes With the Same Course Title When Choosing between Options
     *                      in a GUI Component.
     */
    @Override
    public String toString() {
        /**
         *  Create a Temporary String Value Which
         *  Will be Used in Order to Hold the
         *  Final Calculated Display String Value.
         */
        String details = "";
        
        /**
         *  If the Object's Duplicate Status Boolean
         *  Value has been marked as "True", then print
         *  the Class Title in Addition to its Other
         *  Identifier Fields in Order to Help the User
         *  Differentiate Between Options.
         */
        if (namePresent == true) {
            //  Create Custom Display String.
            details = "  " + title + " (" + semester + " " + year + " " + "| Section: " + section + ")";
        }
        /**
         *  If the Duplicate Status Boolean Variable
         *  Hasn't Been Set as True, then just Print
         *  out the Course Title Instead.
         */
        else {
            //  Create Custom Display String.
            details = "  " + title;
        }

        // Return Display String Value.
        return details;
    }
}
