/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.awt.Point;
import java.net.InetAddress;

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
public class WindowLocationTracker {
  
    // Initializing a Synchronized String.
    private static Point mainWindowLocation;
    // Initializing a Synchronized String.
    private static Point miniWindowLocation;
    // Initializing a Synchronized String.
    private static Point createWindowLocation;
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public static synchronized Point getMainWindowLocation() {
        // Return the Synchronized Boolean Variable.
        return mainWindowLocation;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public static synchronized Point getMiniWindowLocation() {
        // Return the Synchronized Boolean Variable.
        return miniWindowLocation;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public static synchronized Point getCreateWindowLocation() {
        // Return the Synchronized Boolean Variable.
        return createWindowLocation;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public static synchronized void setMainWindowLocation(Point location) {
        // Set the Synchronized String Variable.
        mainWindowLocation = location;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public static synchronized void setMiniWindowLocation(Point location) {
        // Set the Synchronized String Variable.
        miniWindowLocation = location;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public static synchronized void setCreateWindowLocation(Point location) {
        // Set the Synchronized String Variable.
        createWindowLocation = location;
    }
}
