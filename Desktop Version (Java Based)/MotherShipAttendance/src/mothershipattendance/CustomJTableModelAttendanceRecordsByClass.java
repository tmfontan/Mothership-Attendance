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
public class CustomJTableModelAttendanceRecordsByClass extends DefaultTableModel {
    
    private final String[] columnNames = {"Student Name", "Student ID", "Status", "Comments"};
    private ArrayList<ByClassJTableObject> attendanceListCommentsAbreviations = new ArrayList<ByClassJTableObject>();
    
    public CustomJTableModelAttendanceRecordsByClass(ArrayList<ByClassJTableObject> list) {
        for (int i = 0; i < list.size(); i++) {
            
            String abbreviation = "";
            
            if (!list.get(i).getComments().equalsIgnoreCase("")) {
                abbreviation = "Available";
            }
            else {
                abbreviation = "N/A";
            }
            
            ByClassJTableObject value = list.get(i);
            value.setComments(abbreviation);
            
            attendanceListCommentsAbreviations.add(value);
        }
        
        //studentList = list;
    }
    
    public ArrayList<ByClassJTableObject> getList() {
        return attendanceListCommentsAbreviations;
    }
    
    public void setCommentAbbreviation(int row, String value) {
        ByClassJTableObject object = attendanceListCommentsAbreviations.get(row);
        object.setComments(value);
        
        attendanceListCommentsAbreviations.set(row, object);
    }
    
    public void setAttendanceStatus(int row, String value) {
        ByClassJTableObject object = attendanceListCommentsAbreviations.get(row);
        object.setStudentStatus(value);
        
        attendanceListCommentsAbreviations.set(row, object);
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        int size;
   
        if (attendanceListCommentsAbreviations == null) {
            size = 0;
        }
        else {
            size = attendanceListCommentsAbreviations.size();
        }
        
        return size;
    }

    public Object getValueAt(int row, int col) {
        Object temp = null;
        
        if (col == 0) {
            temp = attendanceListCommentsAbreviations.get(row).getStudentName();
        }
        else if (col == 1) {
            temp = attendanceListCommentsAbreviations.get(row).getStudentID();
        }
        else if (col == 2) {
            temp = attendanceListCommentsAbreviations.get(row).getStudentStatus();
        }
        else if (col == 3) {
            temp = attendanceListCommentsAbreviations.get(row).getComments();
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
            default:
                return String.class;
        }
    }
    
    public boolean isCellEditable(int rowIndex, int columnIndex) { 
        return false;
    }
}

