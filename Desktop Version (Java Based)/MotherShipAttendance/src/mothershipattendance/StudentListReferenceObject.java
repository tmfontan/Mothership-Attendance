/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author tylerfontana
 */
public class StudentListReferenceObject implements Serializable {
    
    public StudentInformationExchange exchange;
    public Server.ServerThread thread;
    public String identifier;

    public StudentListReferenceObject(Server.ServerThread t1, StudentInformationExchange value, String name) {
        thread = t1;
        exchange = value;
        identifier = name;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public Server.ServerThread getServerThreadObject() {
        // Return the Synchronized String Variable.
        return thread;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public StudentInformationExchange getStudentInformationExchangeObject() {
        // Return the Synchronized String Variable.
        return exchange;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public String getStudentInformationExchangeIdentifier() {
        // Return the Synchronized String Variable.
        return identifier;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public void setServerThreadObject(Server.ServerThread value) {
        // Set the Synchronized Boolean Variable.
        //thread = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public void setStudentInformationExchangeObject(StudentInformationExchange value) {
        // Set the Synchronized Boolean Variable.
        exchange = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public void setStudentInformationExchangeIdentifier(String value) {
        // Set the Synchronized Boolean Variable.
        identifier = value;
    }
}
