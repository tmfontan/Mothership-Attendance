/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author tylerfontana
 */
public class CustomJTableRowDataObjectStudentView implements java.io.Serializable {
    
    public ImageIcon image;
    public String name;
    public String designation;
    public String email;

    public CustomJTableRowDataObjectStudentView(ImageIcon icon, String username, String desig, String mail) {
        System.out.println("We ARE GETTING HERE 3");
        image = icon;
        name = username;
        designation = desig;
        email = mail;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public ImageIcon getImageIconValue() {
        // Return the Synchronized String Variable.
        return image;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public String getUsername() {
        // Return the Synchronized String Variable.
        return name;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public String getDesignation() {
        // Return the Synchronized String Variable.
        return designation;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public String getEmailAddress() {
        // Return the Synchronized String Variable.
        return email;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public void setImageIcon(ImageIcon value) {
        // Set the Synchronized Boolean Variable.
        image = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public void setUsername(String value) {
        // Set the Synchronized Boolean Variable.
        name = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public void setDesignation(String value) {
        // Set the Synchronized Boolean Variable.
        designation = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public void setEmailAddress(String value) {
        // Set the Synchronized Boolean Variable.
        email = value;
    }
}
