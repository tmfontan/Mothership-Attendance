/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/*
*   @Author Tyler Fontana
*   @Date February 25 2019
*
*   CSCI 4311 - Computer Networks & Telecommunications
*
*   A TCP connection based Client class that implements Thread in
*   order to cater to multiple clients. Gives users the ability to
*   securely chat with fellow clients as well as see an consistent
*   real time list of users connected through the server.
*
*/

// Start of the Client Class
public class Client {

    // Initializing the Input Stream.
    private ObjectInputStream dis;
    // Initializing the Output Stream.
    private ObjectOutputStream dos;
    // Creating variables for the server
    // name and the client's username.
    public static String server, username, password;
    // Creating a variable for our socket.
    private Socket socket;
    // Creating a variable for the
    // Client's Destination port number.
    private static int port;
    public static Student student;
    
    public boolean resendTableInformation = false;
    
    public boolean terminateConnection = false;
    
    public static boolean connectionRefusedWrongPassword = false;

    /*
    *   A Contructor that retrieves the username of this
    *   specifc client.
    */
    public static String getUsername() {
        return username;
    }

    /*
    *   A Contructor that retrieves the username of this
    *   specifc client.
    */
    public static boolean getConnectionRefusedWrongPassword() {
        return connectionRefusedWrongPassword;
    }
    
    /*
    *   A Contructor that retrieves the username of this
    *   specifc client.
    */
    public boolean getTerminateConnectionStatus() {
        return terminateConnection;
    }
    
    /*
    *   A Contructor that retrieves the username of this
    *   specifc client.
    */
    public static String getPassword() {
        return password;
    }
    
    /*
    *   A Contructor that retrieves the username of this
    *   specifc client.
    */
    public static int getPortNumber() {
        return port;
    }
    
    /*
    *   A Contructor that retrieves the username of this
    *   specifc client.
    */
    public static String getServerIPAddress() {
        return server;
    }
    
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /*
    *   A Contructor that retrieves the username of this
    *   specifc client.
    */
    public static void setConnectionRefusedWrongPassword(boolean value) {
        connectionRefusedWrongPassword = value;
    }
    
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public void setTerminateConnectionStatus(boolean value) {
        this.terminateConnection = value;
    }
    
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public void setPassword(String value) {
        this.password = value;
    }
    
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public void setResendTableInformationBoolean(boolean value) {
        this.resendTableInformation = value;
    }
    
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public boolean getResendTableInformationBoolean() {
        return this.resendTableInformation;
    }
    
    /*
    *   A Contructor that sets the username of this specfic
    *   client to the arguments of the parameters.
    *
    *   @param username     The client's chosen username
    *   for this session.
    */
    public void setServerIPAddress(String value) {
        this.server = value;
    }

    /*
    *   A Contructor that creates a Client Object
    *   using the arguments of the parameters.
    *
    *   @param server The server object that
    *   client will connect to.
    *
    *   @param port The port number the Client
    *   will use to connect with
    *   the Server.
    *
    *   @param username The username of this specific
    *   instance of client.
    */
    Client(String server, int port, String username, String password) {
        this.username = username;
        this.server = server;
        this.port = port;
        this.password = password;
    }

    /*
    *   This method triggers the start
    *   of the Client Server message exchange. It
    *   begins by trying to establish a connection
    *   with the Server using a Socket that takes
    *   the name of the server (String) and the
    *   desired port number (int) as arguments.
    *
    */
    public boolean startChat() {
        
        ClientInformationTracker clientTracker = new ClientInformationTracker();
        String ipAddress = "";
        
        try {
            System.out.println("Client Class: " + server + " " + port);
            // Attempt to Establish a Connection to the Server.
            socket = new Socket(server, port);
        }
        catch (Exception e) {
            clientTracker.setConnectionRefusedStatus(1);
            System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
            
            // Catch Exception.
            System.out.println("Exception Encoutered: " + e);
            return false;
        }
        
        // Let the MainScreenStudent GUI Screen Know That We
        // Have Succesfully found the Desired Running Server Instance.
        clientTracker.setConnectionRefusedStatus(2);
        
        // If connection with Server is established, print message.
        // socket.getInetAddress() retrieves the IP address of the Server.
        // socket.getPort() retrieves the port number of the Server.
        String message = "\nConnection accepted " + socket.getInetAddress() + ":" + socket.getPort() + "\n";
        
        // Display the message to this
        // instance of client's screen only.
        System.out.println(message);
        //display(message);

        try {
            // Establishing the Input Stream.
            dis = new ObjectInputStream(socket.getInputStream());
            // Establishing the Output Stream.
            dos = new ObjectOutputStream(socket.getOutputStream());
            // Establishing the Input Stream.
            //disTableInformation = new ObjectInputStream(socket.getInputStream());
            // Establishing the Output Stream.
            //dosTableInformation = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException ioe) {
            System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
            // Catch Exception.
            System.out.println("Error: Input/Output Exception: " + ioe);
            //display("Exception creating new Input/output Streams: " + ioe);
            return false;
        }

        clientTracker.setConnectedStatus(true);
        
        // Creates a thread meant to listen for input
        // from the server.
        new ListenFromServer().start();

        FindNetworkInterfaces findInterfaces = new FindNetworkInterfaces();
        String ipaddress = findInterfaces.determineIPAddress();
        
        Scanner src = new Scanner(System.in);

        TrackerObject tracker = new TrackerObject();
        
        /*System.out.println("Name: ");
        String name = src.nextLine();
        
        System.out.println("Image: ");
        int num = src.nextInt();
        
        Student student = new Student(2552444, name, name, "temp@uno.edu", "temp", "temp", name, "", "", "", "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture" + num + ".png", "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture" + num + "Scaled.png", "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture" + num + "Small.png");
        StudentInformationExchange sender = new StudentInformationExchange(student, port, ipAddress, getPassword());
        */
        
        System.out.println("Client Password: " + clientTracker.getPassword());
        StudentInformationExchange sender = new StudentInformationExchange(tracker.getCurrentStudentInformation(), port, ipaddress, clientTracker.getPassword());
        TCPObjectPacket objectPacket = new TCPObjectPacket(1, null, null, sender);
        
        // Try to write the Client username into the
        // output stream.
        /*try {
            // Send the username to Server.
            dos.writeObject(objectPacket);
            dos.flush();
        }
        // Catch Exception.
        catch (IOException ioe) {
            System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
            // Close DataStreams and
            // Socket to disconnect if error occurs.
            System.out.println("Error: Input/Output Exception: " + ioe + " Disconnecting From Server...");
            disconnect();
            return false;
        }*/
        
        if (writeToStream(objectPacket) == false) {
            return false;
        }
        
        //Thread thread = new Thread();
        //thread.start();
        
        // Return True to let the Server know
        // to be expecting a message.
        return true;
    }

    /*
    * If an error occurs, try to close the
    * DataStreams and socket to disconnect
    * from the Server.
    */
    private void disconnect() {
        try {
            //Try to close the Input Stream.
            if(dis != null) {
                dis.close();
            }
        }
        // Catch Exception.
        catch(Exception e) {
            System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
            System.out.println("Exception Encoutered: " + e);
        }
        
        try {
            //Try to close the Output Stream.
            if(dos != null) {
                dos.close();
            }
        }
        // Catch Exception.
        catch(Exception e) {
            System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
            //if (e.toString().equalsIgnoreCase("java.net.ConnectException: Connection refused")) {
                //System.out.println("No Current Server Instance Running");
            //}
            System.out.println("Exception Encoutered: " + e);
        }
        
        try {
            //Try to close the Socket and Disconnect.
            if(socket != null) {
                socket.close();
            }
        }
        // Catch Exception.
        catch(Exception e) {
            System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
            System.out.println("Exception Encoutered: " + e);
        }
    }

    /*
    *   This method sends a message to
    *   the Server.
    *
    *   @param message The Broadcast Object
    *   that contains the message
    *   to the server along with
    *   its type.
    */
    public void sendMessage(ChatPanel message) {
        TCPObjectPacket objectPacket = new TCPObjectPacket(2, message, null, null);
        writeToStream(objectPacket);
    }

    /*
    *   The main method is responsible for recieveing
    *   the Client specified Username, IP Address, and Port
    *   Number before sending the information to the Server.
    *   Seeing as this program is being run on a local
    *   machine, both the Port Number and IP Address
    *   have been specified a default value. The destination
    *   port number of the Server is 5000.
    */
    public static void main(String[] args) throws UnknownHostException {
        // These are the default values providing
        InetAddress inetAddress = InetAddress.getLocalHost();
        
        Scanner src = new Scanner(System.in);
        
        try {
            // Make client sleep for five seconds before
            // connecting.
            Thread thread = Thread.currentThread();
            thread.sleep(5000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Client: Args[0]: " + args[0] + " Args[1]: " + args[1] + " Args[2]: " + args[2] + " Args[3]: " + args[3]);
        
        System.out.println("IP Address:- " + inetAddress.getHostAddress());
        System.out.println("Host Name:- " + inetAddress.getHostName());
        
        // Create a new Client Object using the
        // three previous values we recieved.
        Client client = new Client(args[2], Integer.parseInt(args[4]), args[1], args[3]);
        
        ClientInformationTracker clientTracker = new ClientInformationTracker();
        
        clientTracker.setCurrentClientInstance(client);
        //clientTracker.setConnectedStatus(true);

        //System.out.println(getPassword());
        //System.out.println(getUsername());
        //System.out.println(getPortNumber());
        
        // Try connecting to the Server. If unable to,
        // disconect.
        if(!client.startChat())
            return;

        // Greeting Message for Client once Connected.
        System.out.print("Welcome " + getUsername());

        //Thread terminateConnectionThread = new Thread();
        //terminateConnectionThread.setName("Terminate Client Connection");
        //terminateConnectionThread.start();
        String message = "";
        
        ChatPanel panel = new ChatPanel(null, "", "", "");
        
        // Implement an endless loop that doesn't break
        // until the Client enters "Bye" and disconnects.
        while(clientTracker.getConnectionStatus() == true) {
            
            System.out.println("Client Loop 440");
            while (true) {
                
                if (clientTracker.getMessagesToServerList().size() > 0) {
                    
                    panel = clientTracker.modifyMessagesToServerList(1, null);
                    message = panel.getMessage();
                    
                    System.out.println("Client Loop 448 Message: " + message);
                    
                    break;
                }
                // Break From Inner Loop Should Server Connection Be Terminated.
                // This is done to Elimate all currently executing Client Threads
                // so that the User may start a new Client Instance from the GUI
                // Screen without encountering any errors.
                else if (clientTracker.getConnectionStatus() == false) {
                    break;
                }
            }
            
            // Break From Outer Loop Should Server Connection Be Terminated.
            // This is done to Elimate all currently executing Client Threads
            // so that the User may start a new Client Instance from the GUI
            // Screen without encountering any errors.
            if (clientTracker.getConnectionStatus() == false) {
                break;
            }
            
            System.out.println("Client Loop 461 Message: " + message);
            
            //System.out.println("");
            // Get the input from the Client.
            //String message = src.nextLine();
            // If the message is "Bye", Disconnect the Client.
            if(message.equalsIgnoreCase("Bye")) {
                client.sendMessage(panel);
                break;
            }
            // If the message is "AllUsers", Retrieve list of
            // all active Connections through the Server.
            else if(message.equalsIgnoreCase("AllUsers")) {
                client.sendMessage(panel);
            }
            // If the input is a regular message, Send it through
            // the server so it can be shared with all the clients.
            else {
                client.sendMessage(panel);
            }
        }
        
        System.out.println("Client Method End: " + Thread.currentThread().getStackTrace()[1].getLineNumber());
        // Close Scanner
        src.close();
        // Disconnect the Client
        client.disconnect();
    }
    
    public synchronized boolean writeToStream(TCPObjectPacket packet) {
        
        if (packet.getType() == 2) {
            System.out.println("Client WriteToStream: " + packet.getMessage().getMessage());
        }
        
        try {
            dos.writeObject(packet);
            return true;
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }
    
    /*
    *  A Listener Class that is responsible for recieving
    * incoming messages from the Server.
    */
    class TerminateConnection extends Thread {
        
        ObjectOutputStream dos;
        
        public TerminateConnection(ObjectOutputStream d1) {
            dos = d1;
        }
        
        public void run() {
        
            ClientInformationTracker clientTracker = new ClientInformationTracker();

            System.out.println("Getting Here in the TerminateConnection Class");

            while (true) {

                if (clientTracker.getTerminateConnectionStatus() == true) {

                    System.out.println("Terminate Connection Status: " + terminateConnection);
                    String message = "Bye";

                    //byte[] temp = message.getBytes();

                    TrackerObject tracker = new TrackerObject();     
                    ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("" + tracker.getCurrentStudentInformation().getStudentProfileImageXS()));
                    ChatPanel panel = new ChatPanel(icon, tracker.getCurrentStudentInformation().getStudentDisplayName(), "", message);
        
                    TCPObjectPacket objectPacket = new TCPObjectPacket(2, panel, null, null);
                    //dos.writeObject(objectPacket);
                    clientTracker.getCurrentClientInstance().writeToStream(objectPacket);
                    //dos.flush();
                    
                    disconnect();

                    terminateConnection = false;
                    break;
                }
                else if (clientTracker.getLostConnectionStatus() == true) {
                    break;
                }
                else if (clientTracker.getConnectionStatus() == false) {
                    break;
                }
            }

            clientTracker.setTerminateConnectionStatus(false);
            clientTracker.setConnectedStatus(false);
            
            System.out.println("Client Method End: " + Thread.currentThread().getStackTrace()[1].getLineNumber());
        }
        
        
    }
    
    /*
    *  A Listener Class that is responsible for recieving
    * incoming messages from the Server.
    */
    class ListenFromServer extends Thread {

        public void run() {
            
            TerminateConnection terminateConnectionThread = new TerminateConnection(dos);
            terminateConnectionThread.start();
            
            // These Boolean Values Are Used To Prevent
            // The Loop From Entering a Second If Statement Iteration
            // and Disconnecting From the Server Should one of the 
            // Clients Enter A Message Equalling the Status Messages
            // used to first determine server connectivity.
            boolean checkPassword = false;
            boolean checkServerCapacity = false;
        
            ClientInformationTracker clientTracker = new ClientInformationTracker();
            
            while(clientTracker.getConnectionStatus() == true) {
                
                try {
                    
                    TCPObjectPacket objectPacket = (TCPObjectPacket) dis.readObject();


                    if (objectPacket.getType() == 2) {
                    //if (dis.readObject() instanceof String) {
                        System.out.println("Getting here in ListenFromServer 1");

                        // Recieve the Message From the Server.
                        //String message = (String) dis.readObject();
                        // Print the Message to the Client Screen.

                        //byte[] temp = objectPacket.getMessage();
                        ChatPanel panel = objectPacket.getMessage();
                        String message = panel.getMessage();
                        
                        //String message = new String(temp, StandardCharsets.UTF_8);
                        System.out.println(message);

                        if (message.equalsIgnoreCase("Connection Refused: Server Full.") && checkServerCapacity == false) {
                            socket.close();
                            dis.close();
                            dos.close();
                            
                            //setConnectionRefusedWrongPassword(true);
                            clientTracker.setConnectionRefusedServerFull(1);
                            checkServerCapacity = true;
                            
                            break;
                        }
                        if (message.equalsIgnoreCase("Connection Refused: Wrong Password.") && checkPassword == false) {
                            socket.close();
                            dis.close();
                            dos.close();
                            
                            //setConnectionRefusedWrongPassword(true);
                            clientTracker.setConnectionRefusedStatusWrongPassword(1);
                            checkPassword = true;
                            
                            break;
                        }
                        else if (checkPassword == false && checkServerCapacity == false) {
                            
                            System.out.println("Getting Here in the Second Check Password Field.");
                            // Execute only once for the first incoming message
                            if ((clientTracker.getConnectionRefusedStatusWrongPassword() != 2) && (clientTracker.getConnectionRefusedServerFull() != 2)) {
                                
                                System.out.println("Getting Here in the Second Check Password Field. 3");
                                
                                // Let the GUI know that All Conditions have been Meet
                                // and the Client Has Succesfull Connected to the Server.
                                clientTracker.setConnectionRefusedStatusWrongPassword(2);
                                clientTracker.setConnectionRefusedServerFull(2);
                                clientTracker.setConnectedStatus(true);
                            }
                            
                            // If Everything checks out, add panel to 
                            clientTracker.modifyMessagesFromClientsList(0, panel);
                        }
                    }
                    else if (objectPacket.getType() == 3) {
                    //else if (dis.readObject() instanceof ClientInformationPacket) {
                        //System.out.println("We Are Getting A Arraylist Packet Line 514");


                        //ArrayList<CustomJTableRowDataObject> rowData = new ArrayList<>();
                        //ClientInformationPacket packet = (ClientInformationPacket) dis.readObject();
                        ClientInformationPacket packet = objectPacket.getClientInformationPacket();

                        
                        if (!clientTracker.getCustomJTableRowDataObjectList().equals(packet.getArrayListOfRowEntries())) {
                            clientTracker.setCurrentClassInformation(objectPacket.getClientInformationPacket().getClassType());
                            //System.out.println(objectPacket.getClientInformationPacket().getClassType().getClassTitle() + " " + objectPacket.getClientInformationPacket().getClassType().getClassField() + " " + objectPacket.getClientInformationPacket().getClassType().getClassSemester());
                            clientTracker.setCustomJTableRowDataObjectList(packet.getArrayListOfRowEntries());

                            //System.out.println(objectPacket.getClientInformationPacket().getClassType().getClassTitle());
                            clientTracker.setListAlterationStatus(true);
                        }
                        //System.out.println(packet.getArrayListOfRowEntries().get(0).getStudentID());

                        //System.out.println("We are here in the Client Section: " + packet.getClassType().getClassTitle());
                    }
                } 
                // Catch Exception
                catch(IOException ioe) {
                    System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                    clientTracker.setLostConnnectionStatus(true);
                    
                    System.out.println("The Server has Disconnected. Terminating Client Program...");
                    //System.exit(0);
                    break;
                }
                // Catch Exception
                catch(ClassNotFoundException cnfe) {
                    System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                    System.out.println("Error: Class Not Found Exception: " + cnfe);
                }
                catch (ClassCastException cce) {
                    System.out.println("Getting Here: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " "+ getUsername());
                    cce.printStackTrace();
                }
            }
            
            try {
                // Give GUI Thread Time To Reach If Statement
                // And Throw Lost Connection Warning Screen.
                Thread thread = Thread.currentThread();
                thread.sleep(1200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            //ioe.printStackTrace();
            clientTracker.setConnectedStatus(false);
            
            System.out.println("Client Method End: " + Thread.currentThread().getStackTrace()[1].getLineNumber());
        }
    }
}
