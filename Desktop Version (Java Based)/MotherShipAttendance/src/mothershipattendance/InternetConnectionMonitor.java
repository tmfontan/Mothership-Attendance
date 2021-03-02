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
public class InternetConnectionMonitor {
    
    
    public TrackerObject tracker = new TrackerObject();
    
    public InternetConnectionMonitor() {
        Thread thread = new Thread();
        thread.start();
    }
    
    public void run() {
        
        while (tracker.getApplicationClosingStatus() == false) {
            
        }
    }
}
