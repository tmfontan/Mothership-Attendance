/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.io.Serializable;

/**
 *
 * @author tylerfontana
 */
public class StudentInformationExchange implements Serializable {
    
    public Student student;
    public int port;
    public String destinationIPAddress;
    public String password;
    

    public StudentInformationExchange(Student stud, int portNumber, String address, String pass) {
        student = stud;
        port = portNumber;
        destinationIPAddress = address;
        password = pass;
    }
    
    public Student getStudentObject() {
        return student;
    }
    
    public int getPortNumber() {
        return port;
    }
    
    public String getDestinationIPAddress() {
        return destinationIPAddress;
    }
    
    public String getServerPassword() {
        return password;
    }
    
    public void setStudentObject(Student stud) {
        student = stud;
    }
    
    public void setPortNumber(int portNumber) {
        port = portNumber;
    }
    
    public void setDestinationIPAddress(String destination) {
        destinationIPAddress = destination;
    }
    
    public void setPassword(String pass) {
        password = pass;
    }
}
    

