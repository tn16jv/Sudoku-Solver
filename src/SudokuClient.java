/*
Author's Name: ThaiBinh Nguyen

Main driver class
 */
import javax.swing.*;

public class SudokuClient {
    public SudokuClient() {
        JFrame frame = new ClientFrame(9);
    }

    public static void main(String[] args) {
        new SudokuClient();
    }
}
