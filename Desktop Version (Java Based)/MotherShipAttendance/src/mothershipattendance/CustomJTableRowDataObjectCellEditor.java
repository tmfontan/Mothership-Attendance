/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author tylerfontana
 */
public class CustomJTableRowDataObjectCellEditor extends AbstractCellEditor implements TableCellEditor {
    CustomJTableRowDataObjectCellComponent panel;
    
    public CustomJTableRowDataObjectCellEditor() {
        panel = new CustomJTableRowDataObjectCellComponent();
    }
    
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        CustomJTableRowDataObject obj = (CustomJTableRowDataObject) value;
        panel.updateData(obj, true, table);
        return panel;
    }
    
    public Object getCellEditorValue() {
        return null;
    }
}
