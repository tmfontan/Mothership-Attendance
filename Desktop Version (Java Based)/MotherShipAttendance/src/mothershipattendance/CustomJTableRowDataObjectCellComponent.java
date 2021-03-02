/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

public class CustomJTableRowDataObjectCellComponent extends JPanel {
    CustomJTableRowDataObject dataObject;
    ServerInformationTracker tracker = new ServerInformationTracker();

    public CustomJTableRowDataObjectCellComponent() {
        // initialize components (labels, buttons, etc.)
        // add action listeners
        
        JPanel jPanel1 = new JPanel();
        JButton jButton1 = new JButton();
        
        jPanel1.setOpaque(false);
        jPanel1.setSize(179, 83);
        jPanel1.setPreferredSize(new Dimension(179, 83));
        jPanel1.setMaximumSize(new Dimension(179, 83));
        jPanel1.setMinimumSize(new Dimension(179, 83));
        
        jButton1.setText("");
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mothershipattendance/Image/ViewProfile.png")));
        
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
        jPanel1.add(jButton1);
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        System.out.println("Hello.");
    }

    public void updateData(CustomJTableRowDataObject value, boolean isSelected, JTable table) {
        this.dataObject = value;
        // update buttons, labels etc. accordingly
    }
}
