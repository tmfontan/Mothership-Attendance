/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

/**
 *
 * @author tylerfontana
 */
public class StudentRecordsTabObject {
    
    public int studentID;
    public String studentName;
    
    public StudentRecordsTabObject(int id, String name) {
        this.studentID = id;
        this.studentName = name;
    }
    
    public int getStudentIDNumber() {
        return this.studentID;
    }
    
    public String getStudentName() {
        return this.studentName;
    }
    
    public void setStudentIDNumber(int id) {
        this.studentID = id;
    }
    
    public void setStudentName(String name) {
        this.studentName = name;
    }
    
    @Override
    public String toString() {
        return this.studentName;
    }
}
