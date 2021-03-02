/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import javax.swing.JFrame;

/**
 *
 * @author tylerfontana
 */
public class TesterCreatePanel {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 1200);
        
        GifImageTester panel = new GifImageTester();
        
        frame.add(panel);
        
        frame.pack();
        frame.setVisible(true);
    }
}
