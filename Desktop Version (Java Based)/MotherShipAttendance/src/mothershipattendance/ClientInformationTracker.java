/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import javax.swing.table.DefaultTableModel;

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

// Start of the ServerInformationTracker Class
public class ClientInformationTracker {
    
    private static Client clientInstance;
    
    public static ClassType currentClassInformation;
    private static String displayName;
    private static String password;
    private static Socket socket;
    private static int portNumber;
    private static StudentListReferenceObject student;
    private static ArrayList<StudentListReferenceObject> clientList = new ArrayList<StudentListReferenceObject>();
    
    public MainScreenStudent mainScreenStudent;
    
    private ArrayList<StudentInformationExchange> rowObjectLink = new ArrayList<StudentInformationExchange>();
    private ArrayList<Server.ServerThread> threadList = new ArrayList<Server.ServerThread>();
    //private ArrayList<StudentListReferenceObject> studentList = new ArrayList<StudentListReferenceObject>();
    private DefaultTableModel model = new DefaultTableModel();
    public static java.util.List<CustomJTableRowDataObjectStudentView> list = new ArrayList<CustomJTableRowDataObjectStudentView>();
    
    private static boolean connected = false;
    private static boolean terminateConnection = false;
    private static boolean lostConnection = false;
    
    // This is the Working Student List
    //public ArrayList<StudentListReferenceObject> studentList = new ArrayList<StudentListReferenceObject>();
    public static java.util.List<StudentListReferenceObject> studentList = Collections.synchronizedList(new ArrayList<>());
    
    public Stack<StudentListReferenceObject> studentStack = new Stack<StudentListReferenceObject>();
    
    private static int connectionRefused = 0;
    private static int connectionRefusedWrongPassword = 0;
    private static int connectionRefusedServerFull = 0;
    
    private static boolean connectedToServerStatus = false;
    private static boolean listAlteration = false;
    private static String listAlterationReason = ""; 
    private static ArrayList<StudentListReferenceObject> removeFromTable = new ArrayList<StudentListReferenceObject>();
    private static ArrayList<StudentListReferenceObject> addToTable = new ArrayList<StudentListReferenceObject>();
    
    private static ArrayList<ChatPanel> messagesFromClients = new ArrayList<>();
    private static ArrayList<ChatPanel> sendMessagesToServer = new ArrayList<>();
    
    public ArrayList<ChatPanel> getMessagesFromClientsList() {
        return messagesFromClients;
    }
    
    public ArrayList<ChatPanel> getMessagesToServerList() {
        return sendMessagesToServer;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized ChatPanel modifyMessagesFromClientsList(int reason, ChatPanel message) {
        
        ChatPanel entry = new ChatPanel(null, "", "", "");
        
        // Add Element to ClientList ArrayList
        if (reason == 0) {
            messagesFromClients.add(message);
            
            System.out.println("ServerTracker MessageList Size: " + messagesFromClients.size());
            
            return entry;
        }
        // Remove Element From ClientList ArrayList
        else if (reason == 1) {
            
            entry = messagesFromClients.get(0);
            messagesFromClients.remove(0);
            
            System.out.println("ServerTracker MessageList Size: " + messagesFromClients.size());
            
            return entry;
        }
        
        return entry;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized ChatPanel modifyMessagesToServerList(int reason, ChatPanel message) {
        
        ChatPanel entry = new ChatPanel(null, "", "", "");
        
        // Add Element to ClientList ArrayList
        if (reason == 0) {
            sendMessagesToServer.add(message);
            
            System.out.println("ClientTracker MessageList Size: " + sendMessagesToServer.size());
            
            return entry;
        }
        // Remove Element From ClientList ArrayList
        else if (reason == 1) {
            
            entry = sendMessagesToServer.get(0);
            sendMessagesToServer.remove(0);
            
            System.out.println("ClientTracker MessageList Size: " + sendMessagesToServer.size());
            
            return entry;
        }
        
        return entry;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void retrieveOrModifyStudentListReferenceObjectList(int reason, StudentListReferenceObject object, int position) {
        
        // Add Element to ClientList ArrayList
        if (reason == 0) {
            clientList.add(object);
        }
        // Remove Element From ClientList ArrayList
        else if (reason == 1) {
            clientList.remove(position);
        }
        
        System.out.println("ServerInformationTracker 93: " + clientList.size());
        
        listAlteration = true;
        //studentList = clientList;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized Client getCurrentClientInstance() {
        // Return the Synchronized String Variable.
        return clientInstance;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized MainScreenStudent getMainScreenStudentInstance() {
        // Return the Synchronized String Variable.
        return mainScreenStudent;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized void setMainScreenStudentInstance(MainScreenStudent main) {
        // Return the Synchronized String Variable.
        mainScreenStudent = main;
    }
    
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public synchronized void setLostConnnectionStatus(boolean value) {
        lostConnection = value;
    }
    
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public synchronized boolean getLostConnectionStatus() {
        return lostConnection;
    }
    
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public synchronized void setConnectionRefusedStatus(int value) {
        connectionRefused = value;
    }
    
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public synchronized int getConnectionRefusedStatus() {
        return connectionRefused;
    }
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public synchronized void setConnectionRefusedServerFull(int value) {
        connectionRefusedServerFull = value;
    }
    
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public synchronized int getConnectionRefusedServerFull() {
        return connectionRefusedServerFull;
    }
    
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public synchronized void setConnectionRefusedStatusWrongPassword(int value) {
        connectionRefusedWrongPassword = value;
    }
    
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public synchronized int getConnectionRefusedStatusWrongPassword() {
        return connectionRefusedWrongPassword;
    }
    
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public synchronized void setTerminateConnectionStatus(boolean value) {
        terminateConnection = value;
    }
    
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public synchronized boolean getTerminateConnectionStatus() {
        return terminateConnection;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized ClassType getCurrentClassInformation() {
        // Return the Synchronized String Variable.
        return currentClassInformation;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized boolean getConnectionStatus() {
        // Return the Synchronized String Variable.
        return connected;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized Socket getSocketInstance() {
        // Return the Synchronized String Variable.
        return socket;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized String getDisplayName() {
        // Return the Synchronized String Variable.
        return displayName;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized String getPassword() {
        // Return the Synchronized String Variable.
        return password;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized int getPortNumber() {
        // Return the Synchronized String Variable.
        return portNumber;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized boolean getListAlterationStatus() {
        // Return the Synchronized String Variable.
        return listAlteration;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized String getListAlterationReason() {
        // Return the Synchronized String Variable.
        return listAlterationReason;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized ArrayList<StudentListReferenceObject> getStudentListReferenceObjectList() {
        // Return the Synchronized String Variable.
        return clientList;
    }
    
    
    
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    //public synchronized ArrayList<StudentListReferenceObject> getStudentListReferenceObjectListTwo() {
        // Return the Synchronized String Variable.
        //return studentList;
    //}
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized java.util.List<StudentListReferenceObject> getStudentListReferenceObjectListTwo() {
        // Return the Synchronized String Variable.
        return studentList;
    }
    
    
    
    
    
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized boolean getServerConnectionStatus() {
        // Return the Synchronized String Variable.
        return connectedToServerStatus;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized StudentListReferenceObject getStudentListReferenceObjectViewProfile() {
        // Return the Synchronized String Variable.
        return student;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized ArrayList<StudentListReferenceObject> getRemoveFromTableList() {
        // Return the Synchronized String Variable.
        return removeFromTable;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized ArrayList<StudentListReferenceObject> getAddToTableList() {
        // Return the Synchronized String Variable.
        return addToTable;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized void addToRemoveFromTableList(StudentListReferenceObject value) {
        // Return the Synchronized String Variable.
        removeFromTable.add(value);
        System.out.println(removeFromTable.size());
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized void deleteFromRemoveFromTableList(StudentListReferenceObject value) {
        // Return the Synchronized String Variable.
        removeFromTable.remove(value);
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized void deleteFromAddToTableList(StudentListReferenceObject value) {
        // Return the Synchronized String Variable.
        addToTable.remove(value);
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized void addToAddToTableList(StudentListReferenceObject value) {
        // Return the Synchronized String Variable.
        addToTable.add(value);
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setCurrentClientInstance(Client value) {
        // Set the Synchronized Boolean Variable.
        clientInstance = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setCurrentClassInformation(ClassType value) {
        // Set the Synchronized Boolean Variable.
        currentClassInformation = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setConnectedStatus(boolean value) {
        // Set the Synchronized Boolean Variable.
        connected = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setSocketInstance(Socket value) {
        // Set the Synchronized Boolean Variable.
        socket = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setDisplayName(String value) {
        // Set the Synchronized Boolean Variable.
        displayName = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setPassword(String value) {
        // Set the Synchronized Boolean Variable.
        password = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setPortNumber(int value) {
        // Set the Synchronized Boolean Variable.
        portNumber = value;
    }
            
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setListAlterationStatus(boolean value) {
        listAlteration = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setStudentListReferenceObjectList(ArrayList<StudentListReferenceObject> value) {
        // Set the Synchronized Boolean Variable.
        clientList = value;
        studentList = clientList;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public void addToStudentListReferenceObjectStack(StudentListReferenceObject value) {
        // Set the Synchronized Boolean Variable.
        studentStack.add(value);
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public Stack<StudentListReferenceObject> getStudentListReferenceObjectStack() {
        // Set the Synchronized Boolean Variable.
        return studentStack;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setServerConnectionStatus(boolean value) {
        // Set the Synchronized Boolean Variable.
        connectedToServerStatus = value;
    }

    /////////////////////////////////////////////////////////
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized DefaultTableModel getTableModelInstance() {
        // Return the Synchronized String Variable.
        return model;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized void setTableModelInstance(DefaultTableModel mod) {
        // Return the Synchronized String Variable.
        model = mod;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized java.util.List<CustomJTableRowDataObjectStudentView> getCustomJTableRowDataObjectList() {
        // Return the Synchronized String Variable.
        return list;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized void setCustomJTableRowDataObjectList(java.util.List<CustomJTableRowDataObjectStudentView> value) {
        // Return the Synchronized String Variable.
        list = value;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized void setStudentListReferenceObjectForViewProfile(StudentListReferenceObject value) {
        // Return the Synchronized String Variable.
        student = value;
    }
    
}
