/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.io.*;

/*
*	@Author Tyler Fontana
*	@Date 	February 25 2019
*
*	Broadcast.java
*	
*	CSCI 4311 - Computer Networks & Telecommunications
*
*	This class is used to categorize the different types of
*	Client input messages, then store the String part of the
*	message into an Object for later use.
*
*/

public class Broadcast implements Serializable {

	// These are the three different types of messages
	// a Client can send:

	/*
	* If a user types in 'ALLUSERS,' The Server will
	* return a list of all active connections to that
	* particular Client.
	*/
	static final int ALLUSERS = 0;
	
	/*
	* If a user types in anything else aside from ALLUSERS
	* or BYE, the server will forward thier message to all
	* connected clients.
	*/
	static final int MESSAGE = 1;
	
	/*
	* If a user types in 'BYE,' the server will disconnect
	* from the client and send out a farewell message.
	*/
	static final int BYE = 2;

	// The type of message to be recieved, Either a 0,1, or 2.
	private int type;
	// The message to temporarily passed into the Broadcast Object.
	private String message;
	
	/*
	* 	Basic constructor that allows us to create a
	*	Broadcast Object using a (int) type and (String)
	*	message.
	*/
	Broadcast(int type, String message) {
		this.type = type;
		this.message = message;
	}

	/*
	*   Returns the type integer form our Broadcast 
	*	Objectthat describes what type of message 
	*	the Server has recieved.
	*/
	int getType() {
		return type;
	}

	/*
	*   Returns the message sent to us by the Client.
	*/
	String getMessage() {
		return message;
	}
}