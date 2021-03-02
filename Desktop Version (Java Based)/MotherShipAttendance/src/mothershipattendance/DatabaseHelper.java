/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tylerfontana
 */
public class DatabaseHelper {
    private static Connection connection;
    private static final String INSERT_STUDENT = "INSERT INTO Student(ID, FirstName, LastName, Username, Password, Email) VALUES (?, ?, ?, ?, ?, ?)";
    Statement statement;
    
    public DatabaseHelper() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/tylerfontana/NetBeansProjects/MotherShipAttendance/AutomattedAttendance.db");
            System.out.println("Connection Succesful");
            
            TrackerObject tracker = new TrackerObject();
            tracker.setDatabaseConnectionStatus(true);
            
            connection.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error " + e);
        }
    }
    
    public void openConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/tylerfontana/NetBeansProjects/MotherShipAttendance/AutomattedAttendance.db");
            System.out.println("Connection Succesful");
            /*
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/tylerfontana/NetBeansProjects/MotherShipAttendance/AutomattedAttendance.db");
            System.out.println("Connection Succesful");
            */
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error " + e);
        }
    }
    
    public String checkStudentLoginCredintials(String user, String pass) {
        
        openConnection();
        
        System.out.println(user + " " + pass + " Student");
        
        try {
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT Username, Password FROM Student");
            
            while(rs.next()) {
                
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                
                if (username.equalsIgnoreCase(user) && password.equals(pass)) {
                    closeConnection();
                    return "Login Successful";
                }
                else if (username.equalsIgnoreCase(user) && !password.equals(pass)) {
                    closeConnection();
                    return "Incorrect Password";
                }
            }
            
        }
        catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        closeConnection();
        return "Username Does Not Exist";
    }
    
    public String checkInstructorLoginCredintials(String user, String pass) {
        
        System.out.println(user + " " + pass + " Instructor");
        openConnection();
        
        try {
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT Username, Password FROM Instructor");
            
            while(rs.next()) {
                
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                
                if (username.equalsIgnoreCase(user) && password.equals(pass)) {
                    closeConnection();
                    return "Login Successful";
                }
                else if (username.equalsIgnoreCase(user) && !password.equals(pass)) {
                    closeConnection();
                    return "Incorrect Password";
                }
            }
            
        }
        catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        closeConnection();
        return "Username Does Not Exist";
    }
    
    public boolean CheckUsernameAvalability(String value) {
        openConnection();
        
        try {
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT Username FROM Instructor");
            
            while(rs.next()) {
                
                String username = rs.getString("Username");
                
                if (username.equalsIgnoreCase(value)) {
                    closeConnection();
                    return true;
                }
            }
            
            this.statement = (Statement) connection.createStatement();
            ResultSet rs2 = statement.executeQuery("SELECT Username FROM Student");
            
            while(rs2.next()) {
                
                String username = rs2.getString("Username");
                
                if (username.equalsIgnoreCase(value)) {
                    closeConnection();
                    return true;
                }
            }            
        }
        catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        closeConnection();
        return false;
    }
    
    public boolean CheckStudentIDAvalability(int id) {
        openConnection();
        
        try {
            this.statement = (Statement) connection.createStatement();
            ResultSet rs2 = statement.executeQuery("SELECT ID FROM Student");
            
            while(rs2.next()) {
                
                int identity = rs2.getInt("ID");
                
                if (identity == id) {
                    closeConnection();
                    return true;
                }
            }            
        }
        catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        closeConnection();
        return false;
    }
    
    public boolean insertStudent(int id, String first, String last, String email, String username, String password) {
        openConnection();
        
        int numRowsInserted = 0;
        PreparedStatement ps = null;
        
        try {
            ps = this.connection.prepareStatement(INSERT_STUDENT);
            ps.setInt(1, id);
            ps.setString(2, first);
            ps.setString(3, last);
            ps.setString(4, username);
            ps.setString(5, password);  // You'll have to update this each and every year. BirthDate would be better.
            ps.setString(6, email);
            numRowsInserted = ps.executeUpdate();

        } 
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return true;
        /*try (Connection conn = this.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        try {
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeUpdate("INSERT INTO Student (ID, FirstName, LastName, Username, Password, Email) VALUES (" + id + ", '" + first +  "', '" + last +   "', '" + username +   "', '" + password +   "', '" + email +   "');");
            return true;
        }
        catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }*/
    }
    
    public void listStudents() {
        try {
            this.statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Student");
            
            while(rs.next()) {
                int id = rs.getInt("ID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                
                System.out.println("Student ID: (" + id + ") Name: " + firstName + " " + lastName + " Username: " + username + " Password: " + password + " Email: " + email + "\n");
            }
        }
        catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
    public void closeConnection() {
        try {
            connection.close();
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
    public void executeQuery(String query) {
        try {
            this.statement = (Statement) connection.createStatement();
            statement.executeQuery(query);
        }
        catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        DatabaseHelper databaseHelper = new DatabaseHelper();
    }
}
