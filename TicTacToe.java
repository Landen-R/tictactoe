import java.util.Scanner;

public class TicTacToe {

    public static final char EMPTY = ' ';
    public static final char CROSS = 'X';
    public static final char NOUGHT = 'O';
    private char[][] board;
    private boolean player1Turn = true; // Player 1 uses 'X', Player 2 uses 'O'

    public TicTacToe() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (!isGameFinished()) {
            printBoard();
            System.out.println("Player " + (player1Turn ? "1 (X)" : "2 (O)") + "'s turn:");
            int row = -1;
            int col = -1;
            while (row < 0 || col < 0 || row >= 3 || col >= 3 || board[row][col] != EMPTY) {
                System.out.println("Enter row and column: ");
                row = scanner.nextInt();
                col = scanner.nextInt();
            }
            board[row][col] = player1Turn ? CROSS : NOUGHT;
            player1Turn = !player1Turn;
        }
        printBoard();
        scanner.close();
        printResult();
    }

    private void printResult() {
        if (hasWon(CROSS)) {
            System.out.println("Player 1 (X) wins!");
        } else if (hasWon(NOUGHT)) {
            System.out.println("Player 2 (O) wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    private boolean isGameFinished() {
        if (hasWon(CROSS) || hasWon(NOUGHT)) {
            return true;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hasWon(char symbol) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                return true;
            }
        }
        return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
               (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    private void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new TicTacToe().playGame();
    }
}
