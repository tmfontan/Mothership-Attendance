/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.util.ArrayList;

/**
 *
 * @author tylerfontana
 */
public class JTableModelStudentObjectLink {
    
    private static int rowIndex;
    private static StudentListReferenceObject link;

    public JTableModelStudentObjectLink(int num, StudentListReferenceObject value) {
        rowIndex = num;
        link = value;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public StudentListReferenceObject getStudentListReferenceObject() {
        // Return the Synchronized String Variable.
        return link;
    }
    
    /*
    *   A Getter Method that retrieves the ConnectionRefused 
    *   boolean variable. This variable is used to verify whether
    *   or not the Client has beeen accepted by the server.
    */
    public int getRowIndex() {
        // Return the Synchronized String Variable.
        return rowIndex;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public void setStudentListReferenceObject(StudentListReferenceObject value) {
        // Set the Synchronized Boolean Variable.
        link = value;
    }
    
    /*
    *   A Getter Method that retrieves the StopCharacterSelectionRepetition 
    *   boolean variable. This variable is used to stop the Character
    *   Selection Screen from being spawned multiple times by a single
    *   Client.
    */
    public void setRowIndex(int value) {
        // Set the Synchronized Boolean Variable.
        rowIndex = value;
    }
}
