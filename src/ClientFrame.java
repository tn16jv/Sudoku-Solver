/*
Class that will be the program frame for the Sudoku solving client.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class ClientFrame extends JFrame {
    ClientFrame(int size)  {
        setTitle("Sudoku Solver");
        setSize(425, 450);
        setLocation(0,0);
        addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                }
            });
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        Container contentPane = getContentPane();

        this.setJMenuBar(new ClientMenu(this));

        //Thread panelThread = new Thread(panel);
        //panelThread.start();
        ClientPanel panel = new ClientPanel(size);
        contentPane.add(panel);
        this.setVisible(true);
    }
}
