/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 *
 * @author tylerfontana
 */
public class FindNetworkInterfaces {
    
    public int i = 5;
    public int num = 0;

    public String address = "";
    public String octet = "";
        
    public FindNetworkInterfaces() {
        // Create A Reference
    }
    
    public boolean parseFirstOctet(String number) {
        int first = 0;
        
        try {
            first = Integer.parseInt(number);
            System.out.println("Getting Here 3");
            return true;
        }
        catch (Exception e) {
            System.out.println("Getting Here 4");
            return false;
        }
    }
    
    public String determineIPAddress() {
        
        try {
            
            Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
            
            for (; n.hasMoreElements();)
            {
                NetworkInterface e = n.nextElement();

                Enumeration<InetAddress> a = e.getInetAddresses();
                for (; a.hasMoreElements();)
                {
                    InetAddress addr = a.nextElement();
                    
                    System.out.println(addr.getAddress().toString());
                    //if (addr.getHostAddress().toString().startsWith("192", 0) || addr.getHostAddress().toString().startsWith("172", 0)) {
                        //address = addr.getHostAddress().toString();
                    //}
                    
                    octet = addr.getHostAddress().substring(0, i);
                    
                    while (true) {
                        if (octet.contains(".") || octet.contains("/") || octet.contains(":")) {
                           octet = octet.substring(0, (i - 1));
                           i--;
                        }
                        else {
                            break;
                        }
                    }
                    
                    System.out.println(octet);
                    
                    if (parseFirstOctet(octet) == true) {
                        System.out.println("Getting Here 1");
                        num = Integer.parseInt(octet);
                        
                        if (num > 0 && num < 256 && num != 127) {
                            address = addr.getHostAddress().toString();
                            System.out.println(address);

                            return address;
                        }
                    }
                    else {
                        System.out.println("Getting Here 2");
                        i = 5;
                    }
                    
                    
                    
                    ///stringSplitter = octet.toString().split(".");
                    //System.out.println("1 " + octet);
                    
                    //for (int i = 0; i <= stringSplitter) {
                        
                    //}
                    
                    
                    
                    // Look for the Personal INET4 IP Address Associated with this Machine on the Current
                    // Network. The Desired IP Address will be in the form "XXX.XXX.XXX.XXX" containing four
                    // octect numbers, each number being in the range of (0 - 255). The numbers 0 and 127 are
                    // reserved beggining octects which are used to reference the localhost and temporary
                    // addresses of the current machine. Thus, the desired address will not contain either of
                    // the previously mentioned begginng octect numbers.
                    //if (addr.getHostAddress().toString().startsWith("" + firstOctet, 0) && (firstOctet != 0 && firstOctet != 127) && firstOctet < 256) {
                        //address = addr.getHostAddress().toString();
                        //System.out.println(address);
                    //}
                    
                    //System.out.println("  " + addr.getHostAddress());
                }
            }

            return address;
            
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
}
