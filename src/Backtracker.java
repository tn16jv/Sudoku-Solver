/*
Class that implements the backtracking algorithm. More specifically, it solves it on a NxN Sudoku board.
 */
public class Backtracker {
    private int[][] newBoard;
    private int size;
    public Backtracker(int N) {
        size = N;
    }; // constructor

    public int[][] getBoard() {
        return newBoard;
    }

/*  Method for a backtracking algorithm specific to Sudoku boards
    Returns true or false based on whether it has a solution or not*/
    public boolean backtrackSudoku(int[][] board) {
        for (int i =0; i<9; i++) {
            for (int j=0; j<9; j++) {
                if (board[i][j] == 0) {
                    for (int n=1; n<=9; n++) {
                        if (validRow(board, i, n) && validCol(board, j, n) &&
                                valid3x3(board, i-i%3, j-j%3, n)) { // Check the 3 Sudoku conditions
                            board[i][j] = n;
                            if (backtrackSudoku(board)) {
                                newBoard = board;
                                return true;
                            } else
                                board[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

/*  Determines if starting state of a Sudoku board is valid. Returns true if yes, false if no.
    board: NxN array representing Soduko board*/
    public boolean validateBoard(int[][] board) {
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (board[i][j] != 0) {
                    int[][] tmpBoard = new int[size][size];       // tmpBoard will be the cloned board for testing
                    for (int n=0; n<size; n++)
                        tmpBoard[n] = board[n].clone();
                    tmpBoard[i][j] = 0;     // Set this position on test board to empty to test all 3 Sudoku conditions
                    if (!validRow(tmpBoard, i, board[i][j]) || !validCol(tmpBoard, j, board[i][j])
                            || !valid3x3(tmpBoard, i-i%3, j-j%3, board[i][j]))
                        return false;   // Invalid if any one of the 3 Sudoku conditions fail
                }
            }
        }
        return true;
    }

/*  Determines whether the candidate number can be placed in a row by traversing it; must not already be present.
    board: NxN array representing Soduko board
    i: number representing selected row
    candidate: number to test if valid in this row*/
    private boolean validRow(int[][] board, int i, int candidate) {
        for (int j=0; j<size; j++) {
            if (board[i][j] == candidate)
                return false;
        }
        return true;
    }

/*  Determines whether the candidate number can be placed in a column by traversing it; must not already be present.
    board: NxN array representing Soduko board
    j: integer representing selected column
    candidate: number to test if valid in this column*/
    private boolean validCol(int[][] board, int j, int candidate) {
        for (int i=0; i<size; i++) {
            if (board[i][j] == candidate)
                return false;
        }
        return true;
    }

/*  Determines whether the candidate number can be placed in a 3x3 Sudoku square area; must not already be present.
    board: NxN array representing Soduko board
    topI: integer representing the top row of this Sudoku square
    leftJ: integer representing the left most column of this Sudoku square*/
    private boolean valid3x3(int[][] board, int topI, int leftJ, int candidate) {
        for (int m=0; m<3; m++) {
            for (int n=0; n<3; n++) {
                if (board[topI+m][leftJ+n]==candidate)
                    return false;
            }
        }
        return true;
    }
}
