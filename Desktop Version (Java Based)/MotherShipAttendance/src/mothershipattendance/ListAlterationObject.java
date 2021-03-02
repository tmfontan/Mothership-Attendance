/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

/**
 *
 * @author tylerfontana
 */
public class ListAlterationObject {
    
    boolean status;
    int number;
    
    public ListAlterationObject(boolean value, int num) {
        status = value;
        number = num;
    }
    
    public boolean getStatus() {
        return status;
    }
    
    public int getCounter() {
        return number;
    }
    
    public void setStatus(boolean value) {
        status = value;
    }
    
    public void setCounter(int num) {
        number = num;
    }
    
    public void incrementCounter() {
        number++;
    }
    
    public void decrementCounter() {
        number--;
    }
}
