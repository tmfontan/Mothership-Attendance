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
public class MessageResponseTracker {
  
    private static int removeStudentResponse = 0;
    private static int addStudentResponseStudentsInClassAddByUsernameSegment = 0;
    private static int addStudentResponseStudentsInClassAddByStudentIDSegment = 0;
    private static int addStudentResponseAllStudentsSegment = 0;

    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized static int getRemoveStudentResponseValue() {
        // Return the Synchronized Boolean Variable.
        return removeStudentResponse;
    }
    
   
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized static void setRemoveStudentResponseValue(int value) {
        // Set the Synchronized Boolean Variable.
        removeStudentResponse = value;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized static int getAddStudentResponseStudentsInClassAddByUsernameSegmentValue() {
        // Return the Synchronized Boolean Variable.
        return addStudentResponseStudentsInClassAddByUsernameSegment;
    }
    
   
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized static void setAddStudentResponseStudentsInClassAddByUsernameSegmentValue(int value) {
        // Set the Synchronized Boolean Variable.
        addStudentResponseStudentsInClassAddByUsernameSegment = value;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized static int getAddStudentResponseStudentsInClassAddByStudentIDSegmentValue() {
        // Return the Synchronized Boolean Variable.
        return addStudentResponseStudentsInClassAddByStudentIDSegment;
    }
    
   
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized static void setAddStudentResponseStudentsInClassAddByStudentIDSegmentValue(int value) {
        // Set the Synchronized Boolean Variable.
        addStudentResponseStudentsInClassAddByStudentIDSegment = value;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized static int getAddStudentResponseAllStudentsSegmentValue() {
        // Return the Synchronized Boolean Variable.
        return addStudentResponseAllStudentsSegment;
    }
    
   
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized static void setAddStudentResponseAllStudentsSegmentValue(int value) {
        // Set the Synchronized Boolean Variable.
        addStudentResponseAllStudentsSegment = value;
    }
}
