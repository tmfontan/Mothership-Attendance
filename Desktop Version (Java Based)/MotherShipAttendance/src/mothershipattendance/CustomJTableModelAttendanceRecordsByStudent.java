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
public class CustomJTableModelAttendanceRecordsByStudent extends DefaultTableModel {
    
    private final String[] columnNames = {"Date", "Status", "Comments"};
    private ArrayList<ByStudentJTableObject> attendanceList = new ArrayList<ByStudentJTableObject>();
    
    public CustomJTableModelAttendanceRecordsByStudent(ArrayList<ByStudentJTableObject> list) {
        for (int i = 0; i < list.size(); i++) {
            
            String abbreviation = "";
            
            if (!list.get(i).getComments().equalsIgnoreCase("")) {
                abbreviation = "Available";
            }
            else {
                abbreviation = "N/A";
            }
            
            ByStudentJTableObject value = list.get(i);
            value.setComments(abbreviation);
            
            attendanceList.add(value);
        }
        
        //studentList = list;
    }
    
    public ArrayList<ByStudentJTableObject> getList() {
        return attendanceList;
    }
    
    public void setCommentAbbreviation(int row, String value) {
        ByStudentJTableObject object = attendanceList.get(row);
        object.setComments(value);
        
        attendanceList.set(row, object);
    }
    
    public void setAttendanceStatus(int row, String value) {
        ByStudentJTableObject object = attendanceList.get(row);
        object.setStudentStatus(value);
        
        attendanceList.set(row, object);
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        int size;
   
        if (attendanceList == null) {
            size = 0;
        }
        else {
            size = attendanceList.size();
        }
        
        return size;
    }

    public Object getValueAt(int row, int col) {
        Object temp = null;
        
        if (col == 0) {
            temp = attendanceList.get(row).getDate();
        }
        else if (col == 1) {
            temp = attendanceList.get(row).getStudentStatus();
        }
        else if (col == 2) {
            temp = attendanceList.get(row).getComments();
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

