/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ServerInformationTracker {

    private static Server serverInstance;
    private static Client clientInstance;
    
    private static String displayName;
    private static String password;
    private static Socket socket;
    private static ServerSocket serverSocket;
    private static int portNumber;
    private static StudentListReferenceObject student;
    
    private static ArrayList<StudentListReferenceObject> clientList = new ArrayList<StudentListReferenceObject>();
    
    private ArrayList<StudentInformationExchange> rowObjectLink = new ArrayList<StudentInformationExchange>();
    private ArrayList<Server.ServerThread> threadList = new ArrayList<Server.ServerThread>();
    //private ArrayList<StudentListReferenceObject> studentList = new ArrayList<StudentListReferenceObject>();
    private DefaultTableModel model = new DefaultTableModel();
    public static java.util.List<CustomJTableRowDataObjectStudentView> list = Collections.synchronizedList(new ArrayList<CustomJTableRowDataObjectStudentView>());

    // This is the Working Student List
    //public ArrayList<StudentListReferenceObject> studentList = new ArrayList<StudentListReferenceObject>();
    public static java.util.List<StudentListReferenceObject> studentList = Collections.synchronizedList(new ArrayList<>());
    
    public Stack<StudentListReferenceObject> studentStack = new Stack<StudentListReferenceObject>();
    
    public static MainScreen mainScreen;
    
    public static ArrayList<ChatPanel> messagesFromClients = new ArrayList<>();
    
    private static boolean sendJTableList = false;
            
    private static boolean serverRunningStatus = false;
    private static boolean listAlteration = false;
    private static String listAlterationReason = ""; 
    
    private Semaphore mutex = new Semaphore(1);
    private static int listAlterationCounter = 0;
    
    private static ArrayList<StudentListReferenceObject> removeFromTable = new ArrayList<StudentListReferenceObject>();
    private static ArrayList<StudentListReferenceObject> addToTable = new ArrayList<StudentListReferenceObject>();
    
    public MainScreen.JTableUpdater updater;
    
    public boolean tableCreationInProgress = false;
    
    public int chatTextAreaSize = 0;
   
    
    public int getChatTextAreaSize() {
        return chatTextAreaSize;
    }
    
    public void setChatTextAreaSize(int value) {
        chatTextAreaSize = value;
    }
    
    public ArrayList<ChatPanel> getMessagesList() {
        return messagesFromClients;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized ChatPanel modifyMessagesList(int reason, ChatPanel message) {
        
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
    
    public synchronized int getListAlterationCounter() {
        return listAlterationCounter;
    }
    
    public synchronized MainScreen getMainScreenInstance() {
        return mainScreen;
    }
    
    public synchronized void setMainScreenInstance(MainScreen main) {
        mainScreen = main;
    }
    
    public synchronized boolean getTableCreationInProgress() {
        return tableCreationInProgress;
    }
    
    public synchronized void setTableCreationInProgress(boolean value) {
        tableCreationInProgress = value;
    }
    
    public synchronized void setListAlterationCounter() {
        listAlterationCounter = 0;
    }
    
    public synchronized void incrementListAlterationCounter() {
        //try {
            //mutex.acquire();
            //System.out.println("We Are Getting Here.");
            //listAlterationCounter++;
            
            //System.out.println("We Are Getting Here. listAlterationCounter: " + listAlterationCounter);
            //mutex.release();
        //} 
        //catch (InterruptedException e) {
            //e.printStackTrace();
        //} 
        listAlterationCounter++;
        
        /*while (true) {
            if (mutex.availablePermits() == 1) {
                listAlterationCounter++;
                break;
            }
        }*/
    }
    
    public synchronized void acquireLock() {
        
        try {
            System.out.println("Avaliable Permits: " + mutex.availablePermits());
            mutex.acquire();
            System.out.println("Avaliable Permits: " + mutex.availablePermits());
            //mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerInformationTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void releaseLock() {
        mutex.release();
    }
    
    public synchronized void decrementListAlterationCounter() {
        //try {
            //mutex.acquire();
            //listAlterationCounter = 0;
            listAlterationCounter--;
            //if (listAlterationCounter > 0) {
                //listAlterationCounter--;
            //}
            
            //System.out.println("We Are Getting Here. listAlterationCounter: " + listAlterationCounter);
            //mutex.release();
        //} 
        //catch (InterruptedException e) {
            //e.printStackTrace();
        //} 
    }
    
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized Server getCurrentServerInstance() {
        // Return the Synchronized String Variable.
        return serverInstance;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized boolean getSendJTableListStatus() {
        // Return the Synchronized String Variable.
        return sendJTableList;
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
    public synchronized Socket getSocketInstance() {
        // Return the Synchronized String Variable.
        return socket;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized ServerSocket getServerSocketInstance() {
        // Return the Synchronized String Variable.
        return serverSocket;
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
    
    public synchronized void setJTableUpdaterInstance(MainScreen.JTableUpdater table) {
        updater = table;
    }
    
    public synchronized MainScreen.JTableUpdater getJTableUpdaterInstance() {
        return updater;
    }
    
    public ArrayList<CustomJTableRowDataObject> createRowDataObject(ArrayList<StudentListReferenceObject> value) {
        
        //ArrayList<StudentListReferenceObject> temp = clientList;
        try {
            //mutex.acquire();
            
            ArrayList<CustomJTableRowDataObject> rowList = new ArrayList<>(value.size());
            
            for (int i = 0; i < clientList.size(); i++) {
                String image = "" + clientList.get(i).getStudentInformationExchangeObject().getStudentObject().getStudentProfileImageSmall();
                
                rowList.add(new CustomJTableRowDataObject(new javax.swing.ImageIcon(getClass().getResource(image)),
                        clientList.get(i).getStudentInformationExchangeIdentifier(),
                        clientList.get(i).getStudentInformationExchangeObject().getStudentObject().getStudentIdentificationNumber(),
                        clientList.get(i).getStudentInformationExchangeObject().getStudentObject().getStudentEmail(),
                        clientList.get(i).getStudentInformationExchangeObject().getDestinationIPAddress()));
            }
            //System.out.println("RowList Size in ServerTracker: " + rowList.size());
            
            //decrementListAlterationCounter();
            //mutex.release();
            return rowList;
        }
        catch (Exception e) {
            System.out.println("ServerInformationTracker 1");
            e.printStackTrace();
        }
        
        return null;
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
    public synchronized boolean getServerRunningStatus() {
        // Return the Synchronized String Variable.
        return serverRunningStatus;
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
    public synchronized void setCurrentServerInstance(Server value) {
        // Set the Synchronized Boolean Variable.
        serverInstance = value;
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
    public synchronized void setSendJTableListStatus(boolean value) {
        // Set the Synchronized Boolean Variable.
        sendJTableList = value;
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
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public synchronized void setServerSocketInstance(ServerSocket value) {
        // Return the Synchronized String Variable.
        serverSocket = value;
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
        //studentList = clientList;
    } 
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public synchronized void setServerRunningStatus(boolean value) {
        // Set the Synchronized Boolean Variable.
        serverRunningStatus = value;
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
    public synchronized void setCustomJTableRowDataObjectList(ArrayList<CustomJTableRowDataObjectStudentView> value) {
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
