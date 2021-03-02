/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import javax.swing.ImageIcon;

/**
 *
 * @author tylerfontana
 */
public class CustomJTableObjectManageStudentsPanel implements java.io.Serializable {
    
    public ImageIcon image;
    public String name;
    public int id;
    public String username;
    public String email;
    public String phone;
    public String address;

    public CustomJTableObjectManageStudentsPanel(ImageIcon icon, String displayName, int studentID, String user, String mail, String phoneNum, String add) {
        image = icon;
        name = displayName;
        id = studentID;
        username = user;
        email = mail;
        phone = phoneNum;
        address = add;
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
    public String getDisplayName() {
        // Return the Synchronized String Variable.
        return name;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public int getStudentID() {
        // Return the Synchronized String Variable.
        return id;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public String getUsername() {
        // Return the Synchronized String Variable.
        return username;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public String getAddress() {
        // Return the Synchronized String Variable.
        return address;
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
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public String getPhoneNumber() {
        // Return the Synchronized String Variable.
        return phone;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public void setDisplayName(String value) {
        // Set the Synchronized Boolean Variable.
        name = value;
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
        username = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public void setStudentID(int value) {
        // Set the Synchronized Boolean Variable.
        id = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public void setAddress(String value) {
        // Set the Synchronized Boolean Variable.
        address = value;
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
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public void setPhoneNumber(String value) {
        // Set the Synchronized Boolean Variable.
        phone = value;
    }
}
