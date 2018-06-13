/*
A custom (extended) JMenuBar class that is to be used for the top menu
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientMenu extends JMenuBar implements ActionListener{
    private JMenu help;
    private JMenuItem guide, about;
    private JFrame masterFrame;

    public ClientMenu(JFrame aframe) {
        super();            // Calls the parent (JMenuBar) constructor

        help = new JMenu("Help");
        guide = new JMenuItem("Guide");
        about = new JMenuItem("About");
        guide.addActionListener(this);
        about.addActionListener(this);
        help.add(guide);
        help.add(about);
        this.add(help);

        masterFrame = aframe;       // The JFrame that will be containing this was passed in as an argument
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (source == guide) {
            JOptionPane.showMessageDialog(masterFrame,      // JOptionPane is a simple popup box
                    "Fill in any of the textfields, representing the Sudoku board, with 1-9.\n" +
                            "0 and empty spaces will represent the empty spaces on the board.\n\n" +
                            "Press the Solve button to get the solution, Clear to clear the board.",
                    "Guide",
                    JOptionPane.PLAIN_MESSAGE);
        } else if (source == about) {
            JOptionPane.showMessageDialog(masterFrame,
                    "<html><body><div width='200px' align='center'>Author: ThaiBinh Nguyen<br/>" +
                            "Created: 2018</div></body></html>",        // Used HTML injection to center this message
                    "About",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }
}
