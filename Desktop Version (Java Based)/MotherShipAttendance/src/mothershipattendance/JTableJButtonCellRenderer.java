/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author tylerfontana
 */
public class JTableJButtonCellRenderer extends JButton implements TableCellRenderer {
    
    //CONSTRUCTOR
    public JTableJButtonCellRenderer(StudentListReferenceObject stud) {
        //SET BUTTON PROPERTIES
        setOpaque(true);
    }
  
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText("View Profile");
        return this;
    } 
}
