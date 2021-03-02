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
public class ByClassJTableObject {
    
    public String studentName;
    public int studentID;
    public String status;
    public String comment;
    
    public ByClassJTableObject(String name, int id, String stat, String com) {
        this.studentName = name;
        this.studentID = id;
        this.status = stat;
        this.comment = com;
    }
    
    public String getStudentName() {
        return this.studentName;
    }
    
    public int getStudentID() {
        return this.studentID;
    }
    
    public String getStudentStatus() {
        return this.status;
    }
    
    public String getComments() {
        return this.comment;
    }
    
    public void setStudentName(String value) {
        this.studentName = value;
    }
    
    public void setStudentID(int value) {
        this.studentID = value;
    }
    
    public void setStudentStatus(String value) {
        this.status = value;
    }
    
    public void setComments(String value) {
        this.comment = value;
    }
}
