/*
Class that has all the GUI elements needed for using the Sudoku solver.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

public class ClientPanel extends JPanel implements ActionListener {
    private JButton solveButton, clearButton;
    private JTextField[][] sudokuFields;
    private JTextField notificationField;
    private Backtracker solver;
    private int gridSize;

    public ClientPanel(int size) {
        gridSize = size;

        sudokuFields = new JTextField[gridSize][gridSize];
        for (int i=0; i<gridSize; i++) {                // Initializing all the text fields that represent the board
            for (int j=0; j<gridSize; j++) {
                sudokuFields[i][j] = new JTextField(2);
                sudokuFields[i][j].setDocument(new TextFieldLimiter(1));
                sudokuFields[i][j].setHorizontalAlignment(JTextField.CENTER);
                sudokuFields[i][j].setFont(new Font("SansSerif", Font.BOLD, 20));
                add(sudokuFields[i][j]);
            }
        }

        notificationField = new JTextField(25);
        notificationField.setEditable(false);
        notificationField.setHorizontalAlignment(JTextField.CENTER);
        notificationField.setFont(new Font("Arial", Font.PLAIN, 20));
        add(notificationField);
        solveButton = new JButton("Solve");
        solveButton.addActionListener(this);
        add(solveButton);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        add(clearButton);

        solver = new Backtracker(gridSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.draw(new Line2D.Double(0,106,425,106));    // Horizontal separation of the grid
        g2.draw(new Line2D.Double(0,211,425,211));
        g2.draw(new Line2D.Double(143,0,143,314));    // Vertical separation of the grid
        g2.draw(new Line2D.Double(272,0,272,314));
    }

    /*
    Takes the input of a board of sudoku numbers and then determines if there is a solution, and whether the board state
    is valid. If there is a solution, the textfields will turn into the solution, otherwise nothing happens.
     */
    private void evaluateBoard(int[][] board) {
        if(!solver.validateBoard(board))
            notificationField.setText("Invalid board state for Sudoku");
        else if (!solver.backtrackSudoku(board))
            notificationField.setText("No possible solution for this board");
        else {
            notificationField.setText("Solved!");
            for (int i=0; i<gridSize; i++) {
                for (int j=0; j<gridSize; j++) {
                    sudokuFields[i][j].setText(Integer.toString(board[i][j]));
                }
            }
        }
    }

    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (source == solveButton) {
            notificationField.setText(null);
            int[][] tmpGrid = new int[gridSize][gridSize];
            for (int i=0; i<gridSize; i++) {
                for (int j=0; j<gridSize; j++) {
                    if (sudokuFields[i][j].getText().compareTo("")!=0) {
                        try {
                            tmpGrid[i][j] = Integer.parseInt(sudokuFields[i][j].getText());
                        } catch(NumberFormatException e) {
                            tmpGrid[i][j] = 0;
                        }
                    }

                    else
                        tmpGrid[i][j] = 0;
                }
            }
            evaluateBoard(tmpGrid);
        } else if (source == clearButton) {     // Clears all the text fields when user hits the clear button
            notificationField.setText(null);
            for (int i=0; i<gridSize; i++) {
                for (int j=0; j<gridSize; j++) {
                    sudokuFields[i][j].setText(null);
                }
            }
        }
    }
}
