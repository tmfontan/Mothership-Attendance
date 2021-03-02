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
public class ArrayListSynchronizer {
    
    public static ArrayList<StudentListReferenceObject> studentList = new ArrayList<StudentListReferenceObject>();
    
    public void performOperationOnStudentList(String reason, StudentListReferenceObject value) {
        // Only one thread is permitted 
        // to change geek's name at a time. 
        synchronized(this) 
        { 
            if (reason.equalsIgnoreCase("ADD")) {
                studentList.add(value);
            }
            else if (reason.equalsIgnoreCase("REMOVE")) {
                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).equals(value)) {
                        studentList.remove(i);
                    }
                }
            }
            else if (reason.equalsIgnoreCase("RETRIEVE")) {
                
            }
        } 
    }
}
