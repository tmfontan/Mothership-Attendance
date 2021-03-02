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
public class RecordsSearchObject {
    
    // The Type of Search, Either A By Class,
    // or By Student Search Type in the Records Tab
    // 
    // 0 - "By Class" Search
    // 1 - "By Student" Search
    public int searchType;
    public int classID;
    public String studentName;
    public String date;
    
    public RecordsSearchObject(int type, int c1, String sname, String timestamp) {
        this.searchType = type;
        this.classID = c1;
        this.studentName = sname;
        this.date = timestamp;
    }
    
    public int getSearchType() {
        return this.searchType;
    }
    
    public int getClassID() {
        return this.classID;
    }
    
    public String getStudentName() {
        return this.studentName;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public void setSearchType(int value) {
        this.searchType = value;
    }
    
    public void setClassID(int value) {
        this.classID = value;
    }
    
    public void setStudentName(String value) {
        this.studentName = value;
    }
    
    public void setDate(String value) {
        this.date = value;
    }
    
}
