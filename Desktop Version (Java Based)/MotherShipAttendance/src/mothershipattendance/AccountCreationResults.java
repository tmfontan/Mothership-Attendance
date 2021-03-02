/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

/**
 *  This Class is Responsible for Creating
 *  a Custom Return Value Object which is Used
 *  During Either the "Instructor" or "Student"
 *  Account Creation Process. This Value is
 *  Used in Order to Retrieve Both the Overall
 *  Result of the Account Creation Process which
 *  is given in the form of a Boolean Variable
 *  in Addition to the Entry Number of newly
 *  created Account in the Remote Database
 *  (Providing the Operation is Successful).
 * 
 *  @date           November 10 2020
 *  @author         Tyler Fontana
 *  @version        1.0.2
 */
public class AccountCreationResults {
    
    /**
     *  The Overall Result of the Account
     *  Creation Process. If it has Failed
     *  return False. It it has Succeeded, 
     *  return True.
     */
    public boolean result;
    /**
     *  The Integer Value Containing the
     *  Entry Number of the New Account
     *  in Either the "Instructor" or
     *  "Student" Table Present Within
     *  the Remote Database.
     */
    public int id;
    
    
    
    /**
     *  This Method is Used to Create a New
     *  Instance of the Account Creation
     *  Result Object.
     * 
     *  @param res          The Boolean Value
     *                      Containing the Overall
     *                      Result of the Account
     *                      Creation Operation.
     * 
     *  @param value        The Integer Number Containing
     *                      the Row Entry Number Within
     *                      the new Accounts Associated
     *                      Table.
     */
    public AccountCreationResults(boolean res, int value) {
        // Set the Overall Result.
        this.result = res;
        // Set the Entry Number
        this.id = value;
    }
    
    /**
     *  This Method is Used to Retrieve the
     *  Overall Result of the Account Creation
     *  Process.
     * 
     *  @return             The Boolean Value
     *                      Telling us Whether or
     *                      Not the Process Was
     *                      Successful.
     */
    public boolean getResult() {
        // Return Overall Result.
        return this.result;
    }
    
    /**
     *  This Method is Used to Retrieve the
     *  Table Entry Number of the newly Created
     *  Account From Within the Database.
     * 
     *  @return             The Integer Value which
     *                      Contains the Table Entry
     *                      Number Given By the
     *                      Database.
     */
    public int getID() {
        // Return Entry Number
        return this.id;
    }
    
    /**
     *  This Method is Used in Order to Set
     *  the Overall Result for the Account
     *  Creation Process.
     * 
     *  @param value        The Boolean Value
     *                      Telling us Whether
     *                      or Not the Account
     *                      Creation Process
     *                      was Successful.
     */
    public void setResult(boolean value) {
        // Set Overall Result.
        this.result = value;
    }
    
    /**
     *  This Method is Used in Order to Set
     *  the Table Entry Number Given By the
     *  Database Upon the Creation of a New
     *  Account.
     * 
     *  @param value        The Integer Value
     *                      Containing the Table
     *                      Entry Number Associated
     *                      With the Newly Created
     *                      Account.
     */
    public void setID(int value) {
        // Set Table Entry Number.
        this.id = value;
    }
    
}
