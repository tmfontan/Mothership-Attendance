/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author tylerfontana
 */
public class JTextFieldCharLimit extends PlainDocument {
    private int limitation;
    
    public JTextFieldCharLimit(int limit) {
        this.limitation = limit;
    }
    
    public void insertString(int offset, String value, AttributeSet attribute) throws BadLocationException {
        if (value == null) {
            return;
        }
        else if ((getLength() + value.length() <= limitation)) {
            super.insertString(offset, value, attribute);
        }
    }
}
