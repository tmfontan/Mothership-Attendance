/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.lang.*;
import java.lang.Math;
import java.math.BigInteger;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  CSCI 4311 Networks & Telecommunications
 *  Programming Assignment 2
 *
 *  Filename:    ThreadStarter.java
 *
 *  @author      Tyler Fontana
 *  @date        April 1 2020
 * 
 *  A class responsible for starting
 *  the corresponding Server & Client Class
 *  Instances. If the Client is Creating
 *  a game, then a Server and Client Class
 *  is created. If the Client is joining a
 *  game, then only a Client Class is created.
 */

// Start of the ThreadStarter Class
public class ThreadStarter implements Runnable {

    public static String[] arguments;
    
    public ThreadStarter() {

    }
    
    public static void setArgumentArray(String[] args) {
        arguments = args;
    }
    
    public static void main(String[] args) {
        
        Thread thread = new Thread(new ThreadStarter());
        
        if (args[0].equalsIgnoreCase("Server")) {
            thread.setName("Server");
            thread.start();
        }
        else if (args[0].equalsIgnoreCase("Client")) {
            thread.setName("Client");
            setArgumentArray(args);
            
            thread.start();
        }
        
        System.out.println("THREADSTARTER 6");
    }


    /*
    *   The run() method is executed by 
    *   the Threads created in the previous
    *   methods. Depending on the assigned
    *   thread names, the thread will either
    *   create a corresponding Server or Client
    *   Class.
    */
    public void run() {      
        // Get Reference to Current Thread
    	Thread thread = Thread.currentThread();
        // Store Thread Name
    	String threadName = thread.getName();

        System.out.println("2 We Are Getting Here in ThreadStarter.");
        // If threadName is Server, Create
        // new Server Thread.
    	if (threadName.equals("Server")) {
            ServerInformationTracker serverTracker = new ServerInformationTracker();
            Server server = serverTracker.getCurrentServerInstance();
            
            server.main(null);
    	}
        // If threadName is Client, Create
        // new Client Thread
        else if (arguments[0] == "Client") {
            try {
                // Run Client Class
                Client.main(arguments);
            } catch (UnknownHostException ex) {
                Logger.getLogger(ThreadStarter.class.getName()).log(Level.SEVERE, null, ex);
            }
    	}
    }
}
