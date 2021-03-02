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
public class ByStudentJTableObject {
    
    public String date;
    public String status;
    public String comment;
    
    public ByStudentJTableObject(String timeStamp, String stat, String com) {
        this.date = timeStamp;
        this.status = stat;
        this.comment = com;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public String getStudentStatus() {
        return this.status;
    }
    
    public String getComments() {
        return this.comment;
    }
    
    public void setDate(String value) {
        this.date = value;
    }
    
    public void setStudentStatus(String value) {
        this.status = value;
    }
    
    public void setComments(String value) {
        this.comment = value;
    }
}
