/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

/**
 *
 * @author tylerfontana
 */
public class ClassInstanceInformationTracker {
    
    private MainScreen main;
    private MainScreenStudent mainStudent;
    private CredintialsScreen credintialsScreen;
    private AccountCreationStudent studentCreate;
    private AccountCreationInstructor instructorCreate;
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized MainScreen getMainScreenInstance() {
        // Return the Synchronized Boolean Variable.
        return main;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized MainScreenStudent getMainScreenStudentInstance() {
        // Return the Synchronized Boolean Variable.
        return mainStudent;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized CredintialsScreen getCredintialsScreenInstance() {
        // Return the Synchronized Boolean Variable.
        return credintialsScreen;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized AccountCreationStudent getAccountCreationStudentInstance() {
        // Return the Synchronized Boolean Variable.
        return studentCreate;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized AccountCreationInstructor getAccountCreationInstructorInstance() {
        // Return the Synchronized Boolean Variable.
        return instructorCreate;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setMainScreenInstance(MainScreen mainScreen) {
        // Set the Synchronized Boolean Variable.
        main = mainScreen;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setMainScreenStudentInstance(MainScreenStudent mainScreen) {
        // Set the Synchronized Boolean Variable.
        mainStudent = mainScreen;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setCredintialsScreenInstance(CredintialsScreen value) {
        // Set the Synchronized Boolean Variable.
        credintialsScreen = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setAccountCreationStudentInstance(AccountCreationStudent value) {
        // Set the Synchronized Boolean Variable.
        studentCreate = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setAccountCreationInstructorInstance(AccountCreationInstructor value) {
        // Set the Synchronized Boolean Variable.
        instructorCreate = value;
    }
    
}
