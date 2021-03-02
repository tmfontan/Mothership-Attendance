/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/*
*	@Author Tyler Fontana
*	@Date 	February 25 2019
*
*	CSCI 4311 - Computer Networks & Telecommunications
*
*	A TCP connection based Server class that implements Thread in
* 	order to cater to multiple clients. Gives clients the ability
* 	to send messages via the Server to other connected clients.
*
*	Instructions:
*
*	Entering "AllUsers" in the Client Program message prompt will 
*	retrieve a list of the current clients connected to the Server 
*	and display them only on the screen of the client that issued 
*	the command.
*
*	Entering "Bye" in the Client Program message prompt will have the 
*	client disconnect from the Server issuing a farwell message on both 
*	the server a connected Client programs.
*/

// Start of Server Class
public class Server {
    
    public static TrackerObject tracker = new TrackerObject();
    
    ArrayList<StudentListReferenceObject> rowObjectLink = new ArrayList<>();
    LinkedList<StudentListReferenceObject> rowObjectLinkTwo = new LinkedList<>();
    StudentListReferenceObject[] listOfStudentReferenceObjects;
    public static ArrayList<Object[][]> studentList;
    
    //StudentListReferenceObject r1 = new StudentListReferenceObject(null, null, null);
    
    public static ServerInformationTracker serverTracker = new ServerInformationTracker();
    // A Arraylist that keeps track of our connected clients.
    private ArrayList<StudentListReferenceObject> clientList;
    // A Arraylist that keeps track of our connected clients.
    public ArrayList<SendTableInformation> sendTableThreadList;
    // Date format that allows us to print a timestamp on our messages.
    private SimpleDateFormat format;
    // Boolean value that notifies us when the Server is functioning.
    private boolean serverStatus;
    private int participants;
    private static String inet;
    private static Instructor instructor;
    private static String displayName;
    private static String password;
    // The unique ID on a specific client on thier own thread.
    private static int nextID;
    // Integer Variable that will hold our port number
    private static int port;
    
    public Semaphore mutex;


    /*
    * 	Contructor that recieves the port number from 
    *	client class.
    *
    *	@param port 	The specified port number passed in
    *					from our client.
    */
    public Server(int port) {
        // Recieves the port number.
        this.port = port;
        // Returns a timestamp in the form of: 
        // Hours : Minutes : Seconds.
        format = new SimpleDateFormat("HH:mm:ss");
        // Initializing our ClientList.
    }

    /*
    * 	Method to start the Server and establish 
    *	our connection to the Client. Creates a new
    *	ServerThread each time a new Client connects
    *	in order for the server to handle multiple 
    * 	connections without crashing.
    */
    public void startServer() {
        mutex = new Semaphore(1);
        
        // Boolean Value that lets us know 
        // the server is running.
        serverStatus = true;
        serverTracker.setServerRunningStatus(true);
        
        clientList = new ArrayList<StudentListReferenceObject>();
        
        //ClientListUpdater updater = new ClientListUpdater();
        //updater.start();
        
        //SendTableInformation sendTableInformation = new SendTableInformation();
        //sendTableInformation.start();
        
        System.out.println("Searching for Avaliable Connections...");
        
        try {
            // Creating a new Socket that contains with the port number selected.
            System.out.println("Port Number: " + serverTracker.getPortNumber());
            ServerSocket socket = new ServerSocket(serverTracker.getPortNumber());
            serverTracker.setServerSocketInstance(socket);
            
            participants = tracker.getChoosenAttendanceClassType().getClassParticipants();
            
            // While Server is still Running, create a new 
            // thread for every new connection.
            while(serverStatus = true) {
                
                // Accept incoming Request From the Socket.
                //if (clientList.size() <= participants) {
                    
                    Socket s = socket.accept();

                    if (clientList.size() == 0) {
                        serverTracker.setSocketInstance(s);
                    }

                    // If Server shuts down, print farewell message
                    // and close the connection.
                    if (serverStatus = false) {
                        serverTracker.setServerRunningStatus(false);
                        System.out.println("The Server is Shutting Down.");
                        break;
                    }
                    
                    // Creates a new Thread for our Connecting Client.
                    ServerThread thread = new ServerThread(s);

                    //System.out.println("CLIENTLIST SIZE IS: " + clientList.size());
                    if (thread.getStatus().equalsIgnoreCase("Verified")) {
                        System.out.println(thread.getUsername() + " is Verified");
                        // Start the Client class.
                        thread.start();
                    }
                    else {
                        // Wrong Password or Server Full
                    } 
                //}
            }
            // Should the Server encouter an error, 
            // close all connections.
            try {
                
                socket.close();
                // Close all of our Client Connections and our
                // input/output streams.
                for(int i = 0; i < clientList.size(); i++) {
                    ServerThread thread2 = clientList.get(i).getServerThreadObject();
                    try {
                        // Closing the Input Stream.
                        thread2.dis.close();
                        // Closing the Output Stream.
                        thread2.dos.close();
                        // Closing the Socket.
                        thread2.socket.close();
                    }
                    // Catch Exception.
                    catch(IOException ioe) {
                        //System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                        ioe.printStackTrace();
                        System.out.println("Input / Output Error has occured. Exception: " + ioe);
                    }
                }
            }
            // Catch Exception.
            catch(Exception e) {
                //System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                e.printStackTrace();
                System.out.println("Exception Encoutered while closing the Server and Client instances: " + e);
            }
            
        }
        // Exception Occured while trying to 
        // start the Server/Client threads.
        catch (IOException ioe) {
            //System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
            String message = "Exception on new ServerSocket: " + ioe + "\n";
            ioe.printStackTrace();
        }
        
    }

    /*
    *	Method that takes a message from the server 
    *	and sends it to all the clients connected.
    *
    *	@param message 	The message to be displayed.
    */
    private synchronized boolean sendToAllClients(ChatPanel panel) {
        // Sends the message with timestamp to all connected clients.
        //String message2 = format.format(new Date()) + " " + message;
        panel.setTimestamp("" + format.format(new Date()));
        
        // Prints the message to the Server's Screen.
        System.out.println(panel.getMessage()); 
        
        serverTracker.modifyMessagesList(0, panel);

        int i = (clientList.size() - 1);

        // Cycles through each of the connected clients in our list and
        // fowards the message to them.
        while (i >= 0) {
            
            ServerThread serverThread = clientList.get(i).getServerThreadObject();
            // If client has disconnected, call method to 
            // remove them from the active list.
            
            if(!serverThread.writeMessage(panel)) {
                
                
                //clientList.remove(i);
                removeClient(i);
                //serverTracker.setListAlterationStatus(true);
                
                //serverTracker.setStudentListReferenceObjectList(clientList);
                //serverTracker.setListAlterationStatus(true);
                //serverTracker.incrementListAlterationCounter();
                
                
                System.out.println("Client" + serverThread.getUsername() + "has been removed from the Client List.");
            }
            i--;
        }
        return true;
    }

    /*
    * 	Method to stop the Server upon completion.
    */
    protected void stopServer() {
        System.out.println("Getting Here in StopServer.");
        serverTracker.setServerRunningStatus(false);
        serverStatus = false;
        
        try {
            if (serverTracker.getSocketInstance() != null) {
                Socket socket = serverTracker.getSocketInstance();
                socket.close();
            }
            
            if (serverTracker.getServerSocketInstance() != null) {
                ServerSocket socket = serverTracker.getServerSocketInstance();
                socket.close();
            }
        }
        // Catch Exception.
        catch(Exception e) {
            //System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
            e.printStackTrace();
            System.out.println("An Error has occured. Exception: " + e);
        }
        
        System.out.println("Server Method End: " + Thread.currentThread().getStackTrace()[1].getLineNumber());
    }
    
    public ArrayList<StudentListReferenceObject> getClientList() {
        return clientList;
    }
    
    /*
    *	Method that removes a client from the active
    *	client list.
    *
    *	@param id 	The id number of the client to
    *				be removed.
    */
    synchronized void removeClient(int id) {
        String clientUsername = "";
        // Loop through the client list until we find
        // the matching id.
        
        ArrayList<StudentListReferenceObject> newList = new ArrayList<StudentListReferenceObject>();
        newList = serverTracker.getStudentListReferenceObjectList();
                    
        for(int i = 0; i < clientList.size(); i++) {
            
            ServerThread serverThread = clientList.get(i).getServerThreadObject();
            
            if(serverThread.id == id) {
                    
                    clientUsername = serverThread.getUsername();
                    System.out.println("Kayla: " + clientUsername);
                    
                    /*for (int j = 0; j < newList.size(); j++) {
                        if (clientUsername.equalsIgnoreCase(newList.get(j).getStudentInformationExchangeIdentifier())) {
                            newList.remove(j);
                        }
                    }*/
                    
                    clientList.remove(i);
                    
                    break;
            }
        }
        
        for (int j = 0; j < serverTracker.getStudentListReferenceObjectList().size(); j++) {
            
            if (serverTracker.getStudentListReferenceObjectList().get(j).getStudentInformationExchangeIdentifier().equalsIgnoreCase(clientUsername)) {
                //newList.remove(j);
                serverTracker.retrieveOrModifyStudentListReferenceObjectList(1, null, j);
                //serverTracker.setStudentListReferenceObjectList(newList);
                //serverTracker.incrementListAlterationCounter();
                //serverTracker.setListAlterationStatus(true);
                break;
            }
        }
        
        //serverTracker.incrementListAlterationCounter();
        //serverTracker.setSendJTableListStatus(true);
        
        //System.out.println("ClientList Size: " + clientList.size() + " newList Size: " + newList.size());
        //System.out.println("Counter: " + serverTracker.getListAlterationCounter());
        // Print to the Server that a specific Client has disconnected.
        System.out.println(format.format(new Date()) + " " + clientUsername + " has disconnected with a Bye Message.");
    }

    /*
    *	Method that initializes and starts the 
    *	Server using the specific arguments.
    *
    *	@param args 	The port number
    */
    public static void main(String[] args) {
        Server server = serverTracker.getCurrentServerInstance();
        server.startServer();
    }

    /*
    *	An inner class that creates a 
    *	seperate thread instance for each client to run on.
    *	This class insures that the Server will 
    *	not run into any errors while hosting
    *	multiple clients.
    */
    class ServerThread extends Thread {
        
        ArrayList<StudentListReferenceObject> rowObjectLink = new ArrayList<>();
        
        SendTableInformation sendTableInformation;
        
        StudentInformationExchange reciever;
        StudentListReferenceObject referenceObject;
        // Initializing out Output Stream.
        ObjectOutputStream dos;
        // Initializing out Input Stream.
        ObjectInputStream dis;
        // Initializing an Instance of 
        // the Broadcast Class.
        Broadcast broadcast;
        // Clients Username.
        String username = "";
        
        String status = "Verified";
        // Variable used to store all
        // outgoing messages.
        String message = "";
        // Specific Socket for each.
        // instance of a Client.
        Socket socket;
        // Creates a Date for timestamps.
        String date = "";
        // Unique Id placeholder.
        int id = 0;

        /*
        *	Constructor that initializes a new thread
        *	assigning it a unique id and creating its socket.
        *
        *	@param	socket 	The socket passed in from the main
        *					Server Class.
        */
        ServerThread(Socket socket) {
            // nextID is unique ID for the Thread.
            id = nextID++;
            // Assigns the passed in parameter details 
            // to this instance of socket.
            this.socket = socket;
            

            try {
                // Establishing the Output Stream.
                dos = new ObjectOutputStream(this.socket.getOutputStream());
                // Establishing the Input Stream.
                dis = new ObjectInputStream(this.socket.getInputStream());

                // Retrieves the username passed in from
                // the client class.
                //username = (String) dis.readObject();
                
                //System.out.println(dis.readObject().toString());
                
                TCPObjectPacket objectPacket = (TCPObjectPacket) dis.readObject();
                reciever = objectPacket.getStudentInformationExchange();
                
                //reciever = (StudentInformationExchange) dis.readObject();   
                
                //System.out.println("Reciever: " + reciever.getStudentObject().getStudentDisplayName());
                
                
                
                
                /*if (dis.readObject() instanceof String) {
                    System.out.println("String Instance");
                }
                else if (dis.readObject() instanceof StudentInformationExchange) {
                    System.out.println("StudentInformationExchange Instance");
                }
                else if (dis.readObject() instanceof ClientInformationPacket) {
                    System.out.println("ClientInformationPacket Instance");
                }*/
                
                System.out.println("Server Password: " + serverTracker.getPassword());
                
                System.out.println("Reciever IP Address: " + reciever.getDestinationIPAddress());
                System.out.println("Reciever Port Number: " + reciever.getPortNumber());
                System.out.println("Reciever Password: " + reciever.getServerPassword());
                
                //System.out.println("ClientList Size + 1: " + (clientList.size() + 1) + " Participants Size: " + participants);
                
                if ((clientList.size() + 1) > participants) {
                    
                    System.out.println("Server Line 431: Getting Here in the Server Full Condition Statement.");
                    
                    String serverFull = "Connection Refused: Server Full.";
                    //byte[] temp = wrongPassword.getBytes();
                    
                    ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/mothershipattendance/Image/ProfilePictures/XSChatIcons/serverIcon48x48.png"));
                    ChatPanel panel = new ChatPanel(icon, "Server", "", serverFull);
                    
                    objectPacket = new TCPObjectPacket(2, panel, null, null);
                    //byte[] conversion = toByteArray(objectPacket);
                    
                    //dos.write(conversion);
                    boolean result = writeToStream(dos, objectPacket);
                    status = "Refused";
                }
                else if (reciever.getServerPassword().equalsIgnoreCase(serverTracker.getPassword()) == false) {
                    String wrongPassword = "Connection Refused: Wrong Password.";
                    //byte[] temp = wrongPassword.getBytes();
                    
                    ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/mothershipattendance/Image/ProfilePictures/XSChatIcons/serverIcon48x48.png"));
                    ChatPanel panel = new ChatPanel(icon, "Server", "", wrongPassword);
                    
                    objectPacket = new TCPObjectPacket(2, panel, null, null);
                    //byte[] conversion = toByteArray(objectPacket);
                    
                    //dos.write(conversion);
                    boolean result = writeToStream(dos, objectPacket);
                    //dos.writeObject(objectPacket);
                    //dos.flush();
                    
                    //dos.writeObject(wrongPassword);
                    
                    status = "Refused";
                }
                else {
                    
                    setUsername(reciever.getStudentObject().getStudentDisplayName());
                    //System.out.println("Server: This is the recievier identifier: " + reciever.getStudentObject().getStudentFirstName());

                    System.out.println("THIS IS THE ID OF THE SERVERTHREAD: " + id);
                    System.out.println("CLIENTLIST SIZE: " + clientList.size());
                    System.out.println("CLIENTLIST ENTRIES: " + clientList.toString());

                    username = reciever.getStudentObject().getStudentFirstName() + " " + reciever.getStudentObject().getStudentLastName();

                    StudentListReferenceObject studentObject = new StudentListReferenceObject(this, reciever, username);
                    serverTracker.retrieveOrModifyStudentListReferenceObjectList(0, studentObject, -1);
                    
                    clientList.add(studentObject);
                    //serverTracker.addToStudentListReferenceObjectStack(new StudentListReferenceObject(this, reciever, username));
                    //serverTracker.setStudentListReferenceObjectList(clientList);
                    //serverTracker.setListAlterationStatus(true);
                    //serverTracker.incrementListAlterationCounter();
                    
                    for (int i = 0; i < clientList.size(); i++) {
                        System.out.println("THIS IS THE USERNAME OF THE NEW CLIENT: " + clientList.get(i).getStudentInformationExchangeIdentifier());
                    }

                    // Create Welcome Message with new Client's username.
                    message = "Welcome " + username;
                    
                    ChatPanel panel = new ChatPanel(new javax.swing.ImageIcon(getClass().getResource("/mothershipattendance/Image/ProfilePictures/XSChatIcons/serverIcon48x48.png")), "Server", "", message);
                    // Send this message to all Connected Clients.
                    sendToAllClients(panel);
                    
                    //mutex.release();
                    
                    //Thread thread = new Thread("SendTableInformation");
                    //thread.start();
                }
            }
            catch (IOException ioe) {
                System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                // Catch the IOException.
                System.out.println("Error: Input/Output Exception: " + ioe);
                ioe.printStackTrace();
                
                return;
            }
            catch (ClassNotFoundException cnfe) {
                System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                // Catch the ClassNotFoundException.
                System.out.println("Error: Class Not Found Exception: " + cnfe);
                cnfe.printStackTrace();
            }
            // Create Date for timestamp purposes.
            date = new Date().toString() + "\n";
        }
        
        public String getStatus() {
            return status;
        }
        
        public SendTableInformation getSendTableInformationThreadInstance() {
            return sendTableInformation;
        }
        
        public synchronized boolean writeToStream(ObjectOutputStream value, TCPObjectPacket packet) {
            ObjectOutputStream dos = value;
            
            try {
                dos.writeObject(packet);
                return true;
            } 
            catch (IOException ex) {
                // Client Has Already Exited or
                // been removed. Thus, do nothing should
                // an Exception occur.
            }
            
            return false;
        }

        /*
        *	Method that handles all the input / output 
        *	from the Client Class. This method analyzes the
        *	types of input that are recieved and responds 
        *	according to key phrases. It is also responsible
        * 	for outputing the desired messages to all connected
        *	clients.
        */
        public void run() {
            
            // Tells us that the server is running.
            boolean serverStatus = true;

            String bye = "Bye";
            String allUsers = "AllUsers";
            //String obj = "";

            //Server.ServerThread s1 = clientList.;
            
            StudentListReferenceObject slro = new StudentListReferenceObject(this, reciever, username);
            
            //System.out.println("ServerThread threadObject: " + s1);
            //System.out.println("ServerThread studentReciever: " + reciever);
            //System.out.println("ServerThread username: " + username);
            
            //serverTracker.addToServerThreadSLROTrackerList(slro);
            //serverTracker.addToRowObjectLinkArrayList(slro);

            String previousBroadcastMessage;
            
            sendTableInformation = new SendTableInformation(dos, username, this, id);
            sendTableInformation.start();
            
            ChatPanel panel = new ChatPanel(null, "", "", "");
            
            System.out.println("Server: 559");
            // While the Server continues to operate
            // decide what to do with any recieved input
            // outgoing Output.
            while (serverStatus = true && status.equalsIgnoreCase("Verified")) {
                try {
                    
                    System.out.println("Server: 566");
                    //obj = "";
                    /* 
                    *  Store any incoming messages in an instance
                    *  of a broadcast Object which is capable of 
                    *  holding an (int) type and (String) message.
                    */
                    TCPObjectPacket packet = (TCPObjectPacket) dis.readObject();
                    //byte[] temp = packet.getMessage();

                    System.out.println("Server: Type of Incoming Packet: Line 576: " + packet.getType());
                    panel = packet.getMessage();
                    
                    //String obj = new String(temp, StandardCharsets.UTF_8);
                    String obj = panel.getMessage();
                    
                    if (obj.equalsIgnoreCase("Bye")) {
                        broadcast = new Broadcast(Broadcast.BYE,"");
                    }
                    else if (obj.equalsIgnoreCase("AllUsers")) {
                        broadcast = new Broadcast(Broadcast.ALLUSERS,"");
                    }
                    else {
                        broadcast = new Broadcast(Broadcast.MESSAGE,obj);
                    }
               }
               catch (EOFException eof) {
                   System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                   //eof.printStackTrace();
                   System.out.println("EndOfFileException");
                   removeClient(id);
                   
                   break;
               }
               catch (SocketException se) {
                   System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                   System.out.println("SocketException");
                   //se.printStackTrace();
                   removeClient(id);

                   break;
               }
               catch (IOException ioe) {
                   System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                    //ioe.printStackTrace();
                    removeClient(id);
                    //System.out.println("Error: Input/Output Exception: " + ioe + " Server: line 318");
                    //System.out.println("Error: Input/Output Exception: " + ioe + " Server: line 318");
                    break;
               }
               catch(ClassNotFoundException cnfe) {
                   System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                    cnfe.printStackTrace();
                    removeClient(id);
                    // Catch the ClassNotFoundException
                    System.out.println("Error: Class Not Found Exception: " + cnfe);
                    break;
               }


               // Retrieve the message string from the Broadcast Object
               String message = broadcast.getMessage();

               // Respond according to what type of message is recieved.
               switch(broadcast.getType()) {
                    // If broadcast.getType() returns a 1,
                    // send the message to all clients.
                    case Broadcast.MESSAGE:
                            //String temporaryMessage = username + ": " + message;
                            // Call sendToAllClients to add a timestamp
                            // to the message and send it out to all active
                            // clients.
                        
                            //serverTracker.modifyMessagesList(0, panel);
                            panel.setMessage(message);
                            sendToAllClients(panel);
                            break;
                    // If broadcast.getType() returns a 2,
                    // the Client disconnects and the Server relays
                    // a message about them leaving before removing 
                    // the client from the active Clients List.
                    case Broadcast.BYE:
                            // Call sendToAllClients to add a timestamp
                            // to the message and send it out to all active
                            // clients.
                            String temporaryMessage2 = "Goodbye " + username;
                            
                            //serverTracker.modifyMessagesList(0, panel);
                            //serverTracker.addToRemoveFromTableList(thread);
                            panel.setMessage(temporaryMessage2);
                            panel.setUsername("Server");
                            panel.setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/mothershipattendance/Image/ProfilePictures/XSChatIcons/serverIcon48x48.png")));
                            
                            sendToAllClients(panel);
                            // Remove Client from list.
                            
                            removeClient(id);
                            break;
                    // If broadcast.getType() returns a 0,
                    // a list of all active clients is sent to 
                    // the client who made the request.
                    case Broadcast.ALLUSERS:
                            // Sends first part of the message to the client.
                            String temporaryMessage3 = "\nList of the users connected at " + format.format(new Date()) + "\n";
                            // Keeps Looping until all of the clients in the
                            // active clients list have been scanned through.
                            // Selects one client from the list each iteration
                            // and sends them requesting Client thier username
                            // and timestamp since first login.
                            for (int i = 0; i < clientList.size(); i++) {
                                ServerThread thread = clientList.get(i).getServerThreadObject();
                                temporaryMessage3 = temporaryMessage3 + "\n" + (i + 1) + ") " + thread.username + " since " + thread.date;
                                //String invertedMessage = "Online since " + thread.date + "Username: " + thread.username;
                                
                                ChatPanel panel2 = new ChatPanel(new javax.swing.ImageIcon(getClass().getResource("/mothershipattendance/Image/ProfilePictures/XSChatIcons/serverIcon48x48.png")), "Server", "", temporaryMessage3);
                                
                                writeMessage(panel2);
                            }
                            break;
                }
            }
            
            // Close the thread if the client has disconnected.
            // close();
            
            System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
            System.out.println("Server Method End: " + Thread.currentThread().getStackTrace()[1].getLineNumber());
        }

        /*
        *	Method that returns the username associated with this Thread.
        */
        public String getUsername() {
                return username;
        }

        /*
        *	Method that sets the username associated with this Thread.
        */
        public void setUsername(String username) {
                this.username = username;
        }
        
        /*
        *	A method that closes all open
        *	Input / Output Streams and sockets
        *	if the server is about to shut down.
        */
        private void close() {
            try {
                // Close Output Stream.
                if(dos != null) {
                     dos.close();
                }
            }
            // Catch Exception.
            catch (Exception e) {
                System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                e.printStackTrace();
                System.out.println("Exception Encoutered: " + e);
            }
            
            try {
                // Close Input Stream.
                if(dis != null) {
                        dis.close();
                }
            }
            // Catch Exception.
            catch (Exception e) {
                System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                e.printStackTrace();
                System.out.println("Exception Encoutered: " + e);
            }
            try {
                // Close Socket.
                if(socket != null) {
                        socket.close();
                }
            }
            // Catch Exception.
            catch (Exception e) {
                System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                e.printStackTrace();
                System.out.println("Exception Encoutered: " + e);
            }
        }

        /*
        *	A method that writes the client and server
        *	messages into the Output Stream.
        *
        *	@param 	message 	The String to be written into
        *						the Output Stream.
        */
        private boolean writeMessage(ChatPanel panel) {
            // If socket isnt connected, close
            // all avaliable Output and Input Streams.
            if (!socket.isConnected()) {
                try {
                    dos.close();
                    dis.close();
                    socket.close();
                } 
                catch (IOException ex) {
                    System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                    ex.printStackTrace();
                }
                    
                return false;
            }
            // Write the message into the Output Stream.
            //try {
                
                //byte[] temp = message.getBytes();
                
                
                TCPObjectPacket objectPacket = new TCPObjectPacket(2, panel, null, null);
                
                System.out.println("DOS Status: " + this.socket.isOutputShutdown() + " " + dos);
                System.out.println("DIS Status: " + this.socket.isInputShutdown() + " " + dis);
                System.out.println("Socket Connection Status: " + this.socket.isConnected());
                
                boolean result = writeToStream(dos, objectPacket);
                
                return result;
                //dos.writeObject(objectPacket);
                //dos.flush();

                //dos.writeObject(message);
            //}
            
            //return true;
        }
    }
    
    class SendTableInformation extends Thread {
        
        // Initializing out Output Stream.
        ObjectOutputStream dos;
        // Initializing out Input Stream.
        ObjectInputStream dis;
        
        Server.ServerThread obj;
        String username = "";
        int id;
        
        boolean sendNewPacket = false;
        
        /*
        *   Constructor that initializes a new thread
        *   assigning to continously update the synchronized
        *   clientList Array in the ServerInformationTrackerClass.
        */
        SendTableInformation(ObjectOutputStream d1, String name, Server.ServerThread value, int num) {
            this.dos = d1;
            this.username = name;
            this.obj = value;
            this.id = num;
        }
        
        public synchronized void setSendInformationStatus(boolean value) {
            System.out.println("setSendInformationStatus: " + this.id);
            sendNewPacket = value;
        }
        
        public void run() {
            
            try {
                // Give JTableUpdater Thread Time to
                // Populate RowDataTwo ArrayList.
                Thread.sleep(500);
            } 
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            while (serverTracker.getServerRunningStatus() == true) {
                try {
                    if (serverTracker.getCustomJTableRowDataObjectList() != null) {
                        break;
                    }
                }
                catch (NullPointerException npe) {
                    System.out.println("The ArrayList has not yet been created.");
                }
            }
            
            ClientInformationPacket packet = new ClientInformationPacket(tracker.getChoosenAttendanceClassType(), serverTracker.getCustomJTableRowDataObjectList());
            
            TCPObjectPacket objectPacket = new TCPObjectPacket(3, null, packet, null);
            boolean result = obj.writeToStream(dos, objectPacket);
            
            System.out.println(result);
                
            java.util.List<CustomJTableRowDataObject> list = new ArrayList<CustomJTableRowDataObject>();

            System.out.println("We Are Getting Here in SendTableInfo.");

            while (serverTracker.getServerRunningStatus() == true) {

                //if (serverTracker.getSendJTableListStatus() == true) {
                /*if (serverTracker.getTableCreationInProgress() == true) {
                    
                    while (true) {
                        if (serverTracker.getTableCreationInProgress() == false) {
                            break;
                        }
                    }
                    
                    System.out.println("setSendInformationStatus Loop: " + this.id);
                    System.out.println("1 We Are Getting Here in SendTableInfo. RowData:" + serverTracker.getCustomJTableRowDataObjectList().size());

                    packet = new ClientInformationPacket(tracker.getChoosenAttendanceClassType(), serverTracker.getCustomJTableRowDataObjectList());
                    System.out.println("2 We Are Getting Here in SendTableInfo.");

                    // dos.writeObject(packet);

                    objectPacket = new TCPObjectPacket(3, null, packet, null);
                    result = obj.writeToStream(dos, objectPacket);
                    //dos.writeObject(objectPacket);
                    //dos.flush();

                    setSendInformationStatus(false);
                    //serverTracker.setSendJTableListStatus(false);
                }*/
                
                if (serverTracker.getCustomJTableRowDataObjectList() != null || serverTracker.getCustomJTableRowDataObjectList().size() > 0) {
                    packet = new ClientInformationPacket(tracker.getChoosenAttendanceClassType(), serverTracker.getCustomJTableRowDataObjectList());
                    
                    objectPacket = new TCPObjectPacket(3, null, packet, null);
                    result = obj.writeToStream(dos, objectPacket);
                    
                    Thread thread = Thread.currentThread();
                    try {
                        thread.sleep(1500);
                    }
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            System.out.println("We Are Getting Here in SendTableInfo. 3");
            //} 
            /*catch (IOException ex) {
                System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                ex.printStackTrace();
                System.out.println("The Socket Has Been Closed.");
            }*/
            
            System.out.println("Server Method End: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " " + getUsername());
        }
        
        /*
        *	Method that returns the username associated with this Thread.
        */
        public String getUsername() {
                return username;
        }

        /*
        *	Method that sets the username associated with this Thread.
        */
        public void setUsername(String username) {
                this.username = username;
        }
    }
}
