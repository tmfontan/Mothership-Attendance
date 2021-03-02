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
public class AttendanceRecord {
    
    int id;
    int classID;
    int instructorID;
    int studentID;
    String status;
    String date;
    String comment;
    
    public AttendanceRecord(int valueOne, int valueTwo, int valueThree, int valueFour, String attendance, String timestamp, String details) {
        
        this.id = valueOne;
        this.classID = valueTwo;
        this.instructorID = valueThree;
        this.studentID = valueFour;
        this.status = attendance;
        this.date = timestamp;
        this.comment = details;
        
    }
    
    public int getAttendanceRecordID() {
        return this.id;
    }
    
    public int getAttendanceRecordClassID() {
        return this.classID;
    }
    
    public int getAttendanceRecordStudentID() {
        return this.studentID;
    }
    
    public int getAttendanceRecordInstructorID() {
        return this.instructorID;
    }
    
    public String getAttendanceRecordStatus() {
        return this.status;
    }
    
    public String getAttendanceRecordDate() {
        return this.date;
    }
    
    public String getAttendanceRecordComments() {
        return this.comment;
    }
    
    public void setAttendanceRecordID(int value) {
        this.id = value;
    }
    
    public void setAttendanceRecordClassID(int value) {
        this.classID = value;
    }
    
    public void setAttendanceRecordStudentID(int value) {
        this.studentID = value;
    }
    
    public void setAttendanceRecordInstructorID(int value) {
        this.instructorID = value;
    }
    
    public void setAttendanceRecordStatus(String value) {
        this.status = value;
    }
    
    public void setAttendanceRecordDate(String value) {
        this.date = value;
    }
    
    public void setAttendanceRecordComments(String value) {
        this.comment = value;
    }
    
}
