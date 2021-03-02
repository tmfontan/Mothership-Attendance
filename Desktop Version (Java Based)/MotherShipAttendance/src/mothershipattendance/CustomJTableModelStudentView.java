/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author tylerfontana
 */
public class CustomJTableModelStudentView extends DefaultTableModel {
    
    private final String[] columnNames = {"", "Name", "Designation", "Email"};
    private ArrayList<CustomJTableRowDataObjectStudentView> studentList = new ArrayList<CustomJTableRowDataObjectStudentView>();
    
    public CustomJTableModelStudentView(java.util.List<CustomJTableRowDataObjectStudentView> list) {
        for (int i = 0; i < list.size(); i++) {
            studentList.add(list.get(i));
        }
        
        //studentList = list;
    }
    
    public ArrayList<CustomJTableRowDataObjectStudentView> getList() {
        return studentList;
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        int size;
   
        if (studentList == null) {
            size = 0;
        }
        else {
            size = studentList.size();
        }
        
        return size;
    }

    public Object getValueAt(int row, int col) {
        Object temp = null;
        
        if (col == 0) {
            temp = studentList.get(row).getImageIconValue();
        }
        else if (col == 1) {
            temp = studentList.get(row).getUsername();
        }
        else if (col == 2) {
            temp = studentList.get(row).getDesignation();
        }
        else if (col == 3) {
            temp = studentList.get(row).getEmailAddress();
        }
        
        return temp;
    }

    // needed to show column names in JTable
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int col) {
        switch (col) {
            case 0:
                return ImageIcon.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            default:
                return String.class;
        }
    }
    
    public boolean isCellEditable(int rowIndex, int columnIndex) { 
        return false;
    }
}

