/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author tylerfontana
 */
public class CreateSQLInsertStatements {
    
    public static void main(String[] args) {

        ArrayList<String> entries = new ArrayList<>();
        ArrayList<String> origin = new ArrayList<>();
        
        Scanner srcInt = new Scanner(System.in);
        Scanner srcString = new Scanner(System.in);

        String insertStatementOne = "INSERT INTO `Class`(`ID`, `Field`, `ClassNumber`, `Title`, `Type`, `Description`, `Credits`, `Section`, `Semester`, `Year`, `Participants`) VALUES (NULL, ";
        String insertStatementTwo = "INSERT INTO `Class`(`ID`, `Field`, `ClassNumber`, `Title`, `Type`, `Description`, `Credits`, `Section`, `Semester`, `Year`, `Participants`) VALUES (NULL, ";
        String insertStatementThree = "INSERT INTO `Class`(`ID`, `Field`, `ClassNumber`, `Title`, `Type`, `Description`, `Credits`, `Section`, `Semester`, `Year`, `Participants`) VALUES (NULL, ";
        String insertStatementFour = "INSERT INTO `Class`(`ID`, `Field`, `ClassNumber`, `Title`, `Type`, `Description`, `Credits`, `Section`, `Semester`, `Year`, `Participants`) VALUES (NULL, ";
        String insertStatementFive = "INSERT INTO `Class`(`ID`, `Field`, `ClassNumber`, `Title`, `Type`, `Description`, `Credits`, `Section`, `Semester`, `Year`, `Participants`) VALUES (NULL, ";
        
        origin.add(insertStatementOne);
        origin.add(insertStatementTwo);
        origin.add(insertStatementThree);
        origin.add(insertStatementFour);
        origin.add(insertStatementFive);
        
        int entrys = 0;

        String field = "";
        int classNum = 0;
        String title = "";
        String type = "";
        String description = "";
        String credits = "";
        String section = "";
        String semester = "";
        int year = 0;
        int participants;

        System.out.print("Please enter the number of Insert Statements you wish to create: ");
        entrys = srcInt.nextInt();

        for (int i = 0; i < entrys; i++) {
            
            insertStatementOne = origin.get(0);
                    
            System.out.print("\nPlease Enter the Class's Field Abbreviation: ");
            field = srcString.nextLine();

            insertStatementOne = insertStatementOne + "\"" + field + "\", ";
            
            System.out.print("Please Enter the Class's Number: ");
            classNum = srcInt.nextInt();

            insertStatementOne = insertStatementOne + classNum + ", ";
            
            System.out.print("Please Enter the Class's Title: ");
            title = srcString.nextLine();
            
            insertStatementOne = insertStatementOne + "\"" + title + "\", ";

            System.out.print("Please Enter the Class's Type: ");
            type = srcString.nextLine();
            
            insertStatementOne = insertStatementOne + "\"" + type + "\", ";

            System.out.print("Please Enter the Class's Decription: ");
            description = srcString.nextLine();
            
            insertStatementOne = insertStatementOne + "\"" + description + "\", ";

            System.out.print("Please Enter the Class's Number of Credit Hours: ");
            credits = srcString.nextLine();
            
            insertStatementOne = insertStatementOne + "\"" + credits + ".00\", ";
            
            insertStatementOne = insertStatementOne + "\"" + getSection() + "\", ";
            
            insertStatementOne = insertStatementOne + "\"" + getSemester() + "\", ";
            
            insertStatementOne = insertStatementOne + "" + getYear() + ", ";
            
            insertStatementOne = insertStatementOne + "" + getParticipants() + ");\n";
            
            entries.add(insertStatementOne);
        }
        
        System.out.println("");
        
        for (int i = 0; i < entries.size(); i++) {
            System.out.println(entries.get(i));
        }
    }
    
    public static int getParticipants() {
        Random rand = new Random();
        //int value = (int) Math.random() * (60 - 15 + 1) + 15;
        int value = rand.nextInt(45) + 15;
        
        return value;
    }

    public static String getSection() {
        Random rand = new Random();
        //int value = (int) Math.random() * (5 - 1 + 1) + 1;
        int value = rand.nextInt(4) + 1;
        
        String format = "00" + value;
        return format;
    }

    public static String getSemester() {
        String temp = "";
        Random rand = new Random();
        //int value = (int) Math.random() * (4 - 1 + 1) + 1;

        int value = rand.nextInt(3);
        
        if (value == 0) {
            temp = "FALL";
        }
        else if (value == 1) {
            temp = "SPRING";
        }
        else if (value == 2) {
            temp = "SUMMER";
        }
        else if (value == 3) {
            temp = "WINTER";
        }

        return temp;
    }
    
    public static int getYear() {
        int temp = 0;
        Random rand = new Random();
        //int value = (int) Math.random() * (3 - 1 + 1) + 1;
        int value = rand.nextInt(2) + 1;
        
        if (value == 0) {
            temp = 2019;
        }
        else if (value == 1) {
            temp = 2020;
        }
        else if (value == 2) {
            temp = 2021;
        }

        return temp;
    }
}
