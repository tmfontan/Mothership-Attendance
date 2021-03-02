/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.DocumentFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author tylerfontana
 */
public class IntegerDocumentFilter extends DocumentFilter {

    final int maxCharacters = 10;
    
    @Override
    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException {

        String text = fb.getDocument().getText(0, fb.getDocument().getLength());
        text += str;
        if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters && text.matches("^[0-9]+[.]?[0-9]{0,1}$")) {
            super.replace(fb, offs, length, str, a);
        } 
        else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
    
    @Override
    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {

        String text = fb.getDocument().getText(0, fb.getDocument().getLength());
        text += str;
        if ((fb.getDocument().getLength() + str.length()) <= maxCharacters && text.matches("^[0-9]+[.]?[0-9]{0,1}$")) {
            super.insertString(fb, offs, str, a);
        } 
        else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}
