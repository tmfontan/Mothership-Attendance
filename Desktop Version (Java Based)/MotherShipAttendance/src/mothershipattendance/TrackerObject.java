/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.awt.Point;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 *  CSCI 4311 Networks & Telecommunications
 *  Programming Assignment 2
 *
 *  Filename:    LockObject.java
 *
 *  @author      Tyler Fontana
 *  @date        April 1 2020
 * 
 *  This is an Object Class that holds
 *  synchronized Variables that can
 *  be accessed across all running threads.
 *  This class is responsible for holding
 *  synchronized check variables that
 *  allow the Client Class to continue program
 *  execution.
 */

// Start of the TrackerObject Class
public class TrackerObject {
  
    // Initializing a Synchronized String.
    private static boolean databaseConnectionStatus = false;
    // Initializing a Synchronized String.
    private static String currentDesignation = "";
    // Initializing a Synchronized String.
    private static boolean showLoadingGIFCredintialsScreen = false;
    private static String iNET4IPAddress;
    
    private static MainScreen main;
    private static MainScreenStudent mainStudent;
    
    private static Student currentStudent;
    private static Instructor currentInstructor;
    private static ClassType currentClassType;
    private static boolean continueToMain;
    private static ArrayList<ClassType> classList = new ArrayList<ClassType>();
    
    public boolean applicationClosing = false;
    public boolean recordsLoadingGIFImageShown = false;
    
    public int unlinkClass = 0;
    public int deleteClass = 0;

    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized static boolean getDatabaseConnectionStatus() {
        // Return the Synchronized Boolean Variable.
        return databaseConnectionStatus;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized boolean getApplicationClosingStatus() {
        // Return the Synchronized Boolean Variable.
        return applicationClosing;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized static MainScreen getMainScreenInstance() {
        // Return the Synchronized Boolean Variable.
        return main;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized static MainScreenStudent getMainScreenStudentInstance() {
        // Return the Synchronized Boolean Variable.
        return mainStudent;
    }
    
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized static boolean getContinueToMainStatus() {
        // Return the Synchronized Boolean Variable.
        return continueToMain;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized boolean getShowLoadingGIFCredintialsScreen() {
        // Return the Synchronized Boolean Variable.
        return showLoadingGIFCredintialsScreen;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized boolean getShowRecordsLoadingGIFStatus() {
        // Return the Synchronized Boolean Variable.
        return recordsLoadingGIFImageShown;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized static String getCurrentDesignation() {
        // Return the Synchronized String Variable.
        return currentDesignation;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized static String getIPAddress() {
        // Return the Synchronized String Variable.
        return iNET4IPAddress;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized static Student getCurrentStudentInformation() {
        // Return the Synchronized String Variable.
        return currentStudent;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized static Instructor getCurrentInstructorInformation() {
        // Return the Synchronized String Variable.
        return currentInstructor;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized static ClassType getChoosenAttendanceClassType() {
        // Return the Synchronized String Variable.
        return currentClassType;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized static ArrayList<ClassType> getClassList() {
        // Return the Synchronized String Variable.
        return classList;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized int getManagementScreenChoice() {
        // Return the Synchronized String Variable.
        return unlinkClass;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized int getManagementScreenChoiceDeleteClass() {
        // Return the Synchronized String Variable.
        return deleteClass;
    }

    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized static void setDatabaseConnectionStatus(boolean value) {
        // Set the Synchronized Boolean Variable.
        databaseConnectionStatus = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized static void setMainScreenInstance(MainScreen mainScreen) {
        // Set the Synchronized Boolean Variable.
        main = mainScreen;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized static void setMainScreenStudentInstance(MainScreenStudent mainScreen) {
        // Set the Synchronized Boolean Variable.
        mainStudent = mainScreen;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized static void setContinueToMainStatus(boolean value) {
        // Set the Synchronized Boolean Variable.
        continueToMain = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized static void setShowLoadingGIFCredintialsScreen(boolean value) {
        // Set the Synchronized Boolean Variable.
        showLoadingGIFCredintialsScreen = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized static void setCurrentDesignation(String designation) {
        // Set the Synchronized String Variable.
        currentDesignation = designation;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized static void setIPAddress(String inet4) {
        // Set the Synchronized String Variable.
        iNET4IPAddress = inet4;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized static void setCurrentStudentInformation(Student s1) {
        // Set the Synchronized Boolean Variable.
        currentStudent = s1;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized static void setCurrentInstructorInformation(Instructor i1) {
        // Set the Synchronized Boolean Variable.
        currentInstructor = i1;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized static void setChoosenAttendanceClassType(ClassType value) {
        // Set the Synchronized Boolean Variable.
        currentClassType = value;
    }
            
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized static void setClassList(ArrayList<ClassType> value) {
        // Set the Synchronized Boolean Variable.
        classList = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setShowRecordsLoadingGIFStatus(boolean value) {
        // Set the Synchronized Boolean Variable.
        recordsLoadingGIFImageShown = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setApplicationClosingStatus(boolean value) {
        // Set the Synchronized Boolean Variable.
        applicationClosing = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setManagementScreenChoice(int value) {
        // Set the Synchronized Boolean Variable.
        unlinkClass = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setManagementScreenChoiceDeleteClass(int value) {
        // Set the Synchronized Boolean Variable.
        deleteClass = value;
    }
}
