/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author tylerfontana
 */
public class CustomJTableRowDataObjectCellRenderer extends JButton implements TableCellRenderer {
    
    CustomJTableRowDataObjectCellComponent panel;

    public CustomJTableRowDataObjectCellRenderer() {
        panel = new CustomJTableRowDataObjectCellComponent();
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        CustomJTableRowDataObject obj = (CustomJTableRowDataObject) value;
        panel.updateData(obj, isSelected, table);
        return panel;
    }
}
