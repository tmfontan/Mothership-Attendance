/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import javax.swing.*;  
import java.awt.event.*;  

public class OptionPaneWindow extends WindowAdapter {  
    
    public JFrame frame;
    public String message;
    
    public OptionPaneWindow(){  
        frame = new JFrame();   
        frame.addWindowListener(this);  
        frame.setSize(300, 300);  
        frame.setLayout(null);  
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
        frame.setVisible(true);  
    }
    
    public void setMessage(String value) {
        this.message = value;
    }
    
    public void windowClosing(WindowEvent e) {
        
        int a = JOptionPane.showConfirmDialog(frame, message);
        
        if(a == JOptionPane.YES_OPTION){  
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        }  
    }  
    
    public static void main(String[] args) {  
        new OptionPaneWindow();  
    }     
}  
