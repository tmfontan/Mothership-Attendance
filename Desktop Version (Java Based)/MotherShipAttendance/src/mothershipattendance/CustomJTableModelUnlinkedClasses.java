/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tylerfontana
 */
public class CustomJTableModelUnlinkedClasses extends DefaultTableModel {
    
    private final String[] columnNames = {"Field", "Class Number", "Title", "Type", "Section", "Semester", "Year"};
    private ArrayList<ClassType> classList = new ArrayList<ClassType>();
    
    public CustomJTableModelUnlinkedClasses(ArrayList<ClassType> list) {
        classList = list;
    }
    
    public ArrayList<ClassType> getList() {
        return classList;
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        int size;
   
        if (classList == null) {
            size = 0;
        }
        else {
            size = classList.size();
        }
        
        return size;
    }

    public Object getValueAt(int row, int col) {
        
        Object temp = null;
        
        if (col == 0) {
            temp = classList.get(row).getClassField();
        }
        else if (col == 1) {
            temp = classList.get(row).getClassNumber();
        }
        else if (col == 2) {
            temp = classList.get(row).getClassTitle();
        }
        else if (col == 3) {
            temp = classList.get(row).getClassStructureType();
        }
        else if (col == 4) {
            temp = classList.get(row).getClassSection();
        }
        else if (col == 5) {
            temp = classList.get(row).getClassSemester();
        }
        else if (col == 6) {
            temp = classList.get(row).getClassYear();
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
                return String.class;
            case 1:
                return Integer.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            default:
                return Integer.class;
        }
    }
    
    public boolean isCellEditable(int rowIndex, int columnIndex) { 
        return false;
    }
}

