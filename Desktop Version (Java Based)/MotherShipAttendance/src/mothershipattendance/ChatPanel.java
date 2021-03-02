/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author tylerfontana
 */
public class ChatPanel implements Serializable {
    
    public ImageIcon icon;
    public String username;
    public String timestamp;
    public String message;
    
    public ChatPanel(ImageIcon image, String name, String time, String mess) {
        this.icon = image;
        this.username = name;
        this.timestamp = time;
        this.message = mess;
    }
    
    public ImageIcon getImageIcon() {
        return this.icon;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getTimestamp() {
        return this.timestamp;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setImageIcon(ImageIcon value) {
        icon = value;
    }
    
    public void setUsername(String value) {
        username = value;
    }
    
    public void setMessage(String value) {
        message = value;
    }
    
    public void setTimestamp(String value) {
        timestamp = value;
    }
}
