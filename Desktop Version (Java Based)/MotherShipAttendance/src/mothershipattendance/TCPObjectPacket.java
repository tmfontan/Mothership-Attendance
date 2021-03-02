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
public class TCPObjectPacket implements Serializable {
    
    public int type;
    public ChatPanel message;
    public ClientInformationPacket packet;
    public StudentInformationExchange exchange;

    
    public TCPObjectPacket(int tvalue, ChatPanel mvalue, ClientInformationPacket pvalue, StudentInformationExchange evalue) {
        type = tvalue;
        message = mvalue;
        packet = pvalue;
        exchange = evalue;
    }
    
    public void setType(int value) {
        type = value;
    }
    
    public void setMessage(ChatPanel value) {
        message = value;
    }
    
    public void setClientInformationPacket(ClientInformationPacket value) {
        packet = value;
    }
    
    public void setStudentInformationExchange(StudentInformationExchange value) {
        exchange = value;
    }
    
    public int getType() {
        return type;
    }
    
    public ChatPanel getMessage() {
        return message;
    }
    
    public ClientInformationPacket getClientInformationPacket() {
        return packet;
    }
    
    public StudentInformationExchange getStudentInformationExchange() {
        return exchange;
    }
}
