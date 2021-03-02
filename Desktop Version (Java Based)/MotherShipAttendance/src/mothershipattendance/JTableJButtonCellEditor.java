/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author tylerfontana
 */
public class JTableJButtonCellEditor extends JButton implements TableCellRenderer {
    
    protected JButton jButton1;
    private String inputText;
    private Boolean status;
   
    public JTableJButtonCellEditor(String value) {
        super(value);
        
        jButton1 = new JButton();
        jButton1.setOpaque(true);
        
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //fireEditingStopped();
            }
        });
    }
    
    /*public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
        jButton1 = (obj == null) ? "" : obj.toString();
        jButton1.setText(lbl);
        status = true;
        return jButton1;
    }
    
    //IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
    @Override
    public Object getCellEditorValue() {
        if(clicked)
        {
        //SHOW US SOME MESSAGE
          JOptionPane.showMessageDialog(btn, lbl+" Clicked");
        }
        //SET IT TO FALSE NOW THAT ITS CLICKED
        clicked=false;
        return new String(lbl);
    }

    @Override
    public boolean stopCellEditing() {
        //SET CLICKED TO FALSE FIRST
        clicked=false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        // TODO Auto-generated method stub
        super.fireEditingStopped();
    } 

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        jButton1 = (obj == null) ? "":obj.toString();
        jButton1.setText(lbl);
        status = true;
        return jButton1;
    }*/

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
