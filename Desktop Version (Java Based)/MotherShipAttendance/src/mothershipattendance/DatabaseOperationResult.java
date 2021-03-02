/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

/**
 *  A Class That is Used to Create A Custom Object which will be used in the Retrieval
 *  of Database Method Execution Results. This Object Holds Three Separate Variables 
 *  which will show the Program how to Respond should Certain Incidents Occur.
 * 
 *  @author     Tyler Fontana
 *  @date       October 15 2020
 *  @version    1.0.3
 */
public class DatabaseOperationResult {
    
    /**
     *  Variable Meant to Hold the Overall Result of the Methods 
     *  Execution When Accessing or Modifying the Database. 
     */
    public boolean result;
    /**
     *  Variable Meant to Hold The Type of Error That May End Up
     *  Occurring During Execution. (Examples: "SQL Exception",
     *  "Communications Exception", or "Class Not Found Exception."
     *  Additionally, this Variable can also be used to Tell the
     *  User if their Specified Account Information Couldn't be
     *  Located, or the Result Set Information they are Looking
     *  For Contains no Entries. All of these Conditions will be
     *  Explained via the Type Variable and Translated to the
     *  Calling GUI Class Instance.
     */
    public String type;
    /**
     *  Variable Meant to Hold the Amount of Progress Made while 
     *  Executing a Database Modification Process. This Variable 
     *  is needed seeing as some Methods within the Database Manager 
     *  MySQL Class Query or Create / Update Multiple Tables at a time.
     * 
     *  (Note: Variable is Currently Not Used in Current Version 1.0.2
     *  of the Mothership Attendance Application.)
     */
    public int progress;
    
    /**
     *  This method is a Basic Constructor Method Which is Used to Create
     *  a New Instance of the Custom Database Operation Result Object.
     * 
     *  @param   value              Variable Meant to Hold
     *                              the Overall Result of the
     *                              Methods Execution When Accessing
     *                              or Modifying the Database. 
     * 
     *  @param   error              Variable Meant to Hold
     *                              The Type of Error That May End Up
     *                              Occurring During Execution. 
     *                              (Examples: "SQL Exception" or 
     *                              "Communications Exception")
     * 
     *  @param   progression        Variable Meant to Hold
     *                              the Amount of Progress Made
     *                              while Executing a Database Modification
     *                              Process. This Variable is needed
     *                              seeing as some Methods within
     *                              the DatabaseManagerMySQL Class
     *                              query or create / update multiple
     *                              tables at a time.
     *  
     */
    public DatabaseOperationResult(boolean value, String error, int progression) {
        // Set Overall Result Value.
        this.result = value;
        // Set Error Type Value.
        this.type = error;
        // Set Progression Status Value.
        this.progress = progression;
    }
    
    /**
     *  A Getter Method that is Used to Retrieve the
     *  Overall Result of the Database Method Execution.
     * 
     *  @return result          The Global Variable Holding
     *                          the Overall Result of the Database
     *                          Method's Execution.
     */
    public boolean getResult() {
        /**
         *  Return the Current Instance 
         *  of this Global Variable.
         */
        return this.result;
    }
    
    /**
     *  A Getter Method that is Used to Retrieve the
     *  Type of Error That May Possibly Occur During
     *  a DatabaseManagerMySQL Method Operation.
     * 
     *  @return type            The Global Variable Holding
     *                          the Name of the Type of Error
     *                          Caught.
     */
    public String getType() {
        /**
         *  Return the Current Instance 
         *  of this Global Variable.
         */
        return this.type;
    }
    
    /**
     *  A Getter Method that is Used to Retrieve the
     *  Progression State of the Database Method Execution.
     *  This Variable is used to Give the Program an Accurate
     *  Response of Where a Methods Execution has Stopped.
     * 
     *  @return progress        The Global Variable Holding
     *                          the Method's Progression Status.
     */
    public int getProgressionStatus() {
        /**
         *  Return the Current Instance 
         *  of this Global Variable.
         */
        return this.progress;
    }
    
    /**
     *  A Setter Method that is Used to Set the
     *  Overall Result of the Database Method Execution.
     * 
     *  @param value            The Boolean Value We will
     *                          Use to set the Overall Result
     *                          of the Database Method Execution.
     */
    public void setResult(boolean value) {
        /**
         *  Return the Current Instance 
         *  of this Global Variable.
         */
        this.result = value;
    }
    
    /**
     *  A Setter Method that is Used to Set the
     *  Message Type should an Error Occur During
     *  Database Method Execution.
     * 
     *  @param value            The Name of the Exception
     *                          Message Should one be caught.
     */
    public void setType(String value) {
        /**
         *  Return the Current Instance 
         *  of this Global Variable.
         */
        this.type = value;
    }
    
    /**
     *  A Setter Method that is Used to Set the
     *  Progression Level of a Database Method's
     *  Execution. 
     * 
     *  @param value            The Progression Step Level
     *                          Where A Database Execution
     *                          Method Has Reached.
     */
    public void setProgressionStatus(int value) {
        /**
         *  Return the Current Instance 
         *  of this Global Variable.
         */
        this.progress = value;
    }
}
