import java.util.Scanner;

public class TicTacToe {
    final static int ROWS = 3;
    final static int COLS = 3;
    static String board[][];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String player = "X";
        int row = 0;
        int col = 0;
        board = new String[ROWS][COLS];
        boolean done = false;
        boolean playing = true;
        int moveCount = 0;

        clearBoard();
        showBoard();
        do {
            do {
                do {

                    //get move for X need a row and col
                    row = SafeInput.getRangedInt(in, "Player " + player + " Enter your row", 1, 3);
                    col = SafeInput.getRangedInt(in, "Player " + player + " Enter your col", 1, 3);
                    row--;
                    col--;
                    if (!isValidMove(row, col)) {
                        System.out.println("Invalid move! Please select another coordinate.");
                    }
                } while (!isValidMove(row, col));
                recordMove(player, row, col);

                showBoard();
                moveCount++;
                if (moveCount >= 5) {
                    if (isWin(player)) {
                        System.out.println("Player " + player + " has won!");
                        done = true;
                    }
                }
                if (moveCount == 9 && (!isWin(player))) {
                    System.out.println("It's a tie!");
                    done = true;
                }

                if (player.equals("X") && !done)
                    player = "O";
                else if (!done) {
                    player = "X";
                }
            } while (!done);

            clearBoard();
            player = "X";
            moveCount = 0;
            playing = (SafeInput.getYNConfirm(in, "Would you like to play again?"));
            done = !playing;
        }while(playing);
    }

    private static void clearBoard() {
        for(int r=0; r<ROWS; r++)
            for(int c=0; c<COLS; c++)
                board[r][c] = " ";
    }
    private static void showBoard() {
        for(int r=0; r<ROWS; r++) {
            System.out.print("|");
            for (int c = 0; c < COLS; c++)
                System.out.print(board[r][c] + " |");
            System.out.println();
        }
    }
    private static boolean isValidMove(int r, int c) {
        boolean retVal = false;
        if (board[r][c].equals(" "))
            retVal = true;
        return retVal;
    }
    private static void recordMove(String player, int r, int c) {
        board[r][c] = player;
    }
    private static boolean isWin(String player) {
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player)){
            return true;
        }
        return false;
    }
    private static boolean isRowWin(String player) {
        for(int row=0; row < ROWS; row++) {
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)){
                return true;
            }
        }
        return false;
    }
    private static boolean isColWin(String player){
        for(int col=0; col < COLS; col++) {
            if(board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)){
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagonalWin(String player){
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)){
            return true;
        } else if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;

        }
        return false;
    }
}
