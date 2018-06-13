/*
Class that limits what is entered into a JTextField through its Document
 */
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextFieldLimiter extends PlainDocument {
    private int max;
    public TextFieldLimiter(int limit) {
        super();
        max = limit;
    }

    /*
    Anytime a string is inserted into the JTextField, it uses this method.
     */
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;

        if ((getLength() + str.length()) <= max) {
            try {
                Integer.parseInt(str);
                super.insertString(offset, str, attr);  // Only entered into the textfield if the string is an integer
            } catch (NumberFormatException e) {                     // Throws error if the string is not an integer
                JOptionPane.showMessageDialog(null,
                        "Invalid Input. Enter a number (0-9, with 0 for empty)", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
