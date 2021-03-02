/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author tylerfontana
 */
public class ClientInformationPacket implements Serializable {
    
    public ClassType classInfo;
    public java.util.List<CustomJTableRowDataObjectStudentView> list;
    
    public ClientInformationPacket(ClassType type, java.util.List<CustomJTableRowDataObjectStudentView> value) {
        classInfo = type;
        list = value;
    }
    
    public ClassType getClassType() {
        return classInfo;
    }
    
    public java.util.List<CustomJTableRowDataObjectStudentView> getArrayListOfRowEntries() {
        return list;
    }
    
    public void setClassType(ClassType value) {
        classInfo = value;
    }
    
    public void setArrayListOfRowEntries(java.util.List<CustomJTableRowDataObjectStudentView> value) {
        list = value;
    }
}
