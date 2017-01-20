import java.util.Arrays;
import java.util.Scanner;
import util.Terminal;

public class UltimateTTTBoard {
    // 2D TTTBoard array representing the full board
    private TTTBoard[][] boardArray = new TTTBoard[3][3];
    //TTTBoard representing won boards (corresponds to boardArrayl.ft )
    private TTTBoard board = new TTTBoard();
    // int representation of current board, 0-8
    /*
     * 0|1|2
     * 3|4|5
     * 6|7|8
     */
    private int focus = 4;
    //Is the user using numpad mode?
    public static boolean numpad;

    public UltimateTTTBoard() {
        super();
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                boardArray[i][j] = new TTTBoard();
            }
        }
    }

    public void move(char player, int location) throws LocationTakenException, TargetBoardFullException {
        System.out.println(focus);
        TTTBoard smallBoard = boardArray[focus/3][focus%3];
        smallBoard.move(player,location);
        if (smallBoard.gameOver()) {
            boardArray[focus/3][focus%3] = new TTTBoard(smallBoard.getWinner());
            board.move(smallBoard.getWinner(),focus);
        }
        if (boardArray[location/3][location%3].gameOver()) {
            throw new TargetBoardFullException(location);
        }
        focus = location;
    }

    public char getWinner() {
        return board.getWinner();
    }

    public boolean gameOver() {
        return board.gameOver();
    }

    public String toString() {
        int line=0;
        String out = "";
        while (line<9) {
            int j = 0;
            out += boardArray[line/3][j++].getBoard()[line%3]+"#"+boardArray[line/3][j++].getBoard()[line%3]+"#"+boardArray[line/3][j++].getBoard()[line%3]+"\n";
            if (line==2||line==5) out += "#################\n";
            line++;
        }
        return out;
    }

    public static int processNumpad(int input) {
        switch (input) {
            case 7: input = 1;
                        break;
            case 8: input = 2;
                        break;
            case 9: input = 3;
                        break;
            case 1: input = 7;
                        break;
            case 2: input = 8;
                        break;
            case 3: input = 9;
                        break;
        }
        return input;
    }

    public TTTBoard[][] getBoardArray() {
        return boardArray;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        /*
        int players=-1;
        while (players==-1) {
            try {
                System.out.println("How many players?");
                players = s.nextInt();
                if (players<1 || players>2) throw new IllegalArgumentException("Players must be between 1 and 2, got "+players);
            } catch (IllegalArgumentException e) {
                System.out.println(e);
                players=-1;
            }
        }
        if (players != 2) {
                System.out.println("Not yet implemented");
            return;
        }
        */
        Terminal.clear();
        UltimateTTTBoard board = new UltimateTTTBoard();
        char currentPlayer = 'X';
        System.out.println("1)Numpad mode or 2)logical mode?");
        if (s.nextInt() == 1) numpad=true;
        Terminal.clear();
        if (numpad) System.out.println("Using numpad mode.");
        else System.out.println("Using logical mode.");
        while (!board.gameOver()) {
            System.out.println(board);
            System.out.println("It's "+currentPlayer+"'s turn! Where do you want to go? (1-9)");
            try {
                int input = s.nextInt();
                if (numpad) input = processNumpad(input);
                board.move(currentPlayer,input-1);
                if (currentPlayer == 'X') currentPlayer = 'O';
                else currentPlayer = 'X';
                Terminal.clear();
                System.out.println("Current board is "+(board.focus+1));
            } catch (TargetBoardFullException e) {
                while (true) {
                    if (currentPlayer == 'X') currentPlayer = 'O';
                    else currentPlayer = 'X';
                    Terminal.clear();
                    System.out.println("\n"+board);
                    System.out.println("Target board " + e.getBoard() + "is full. Which board would you like to move on, " + currentPlayer + "? (1-9)");
                    int location = s.nextInt();
                    if (numpad) location = processNumpad(location);
                    location--;
                    if (location != e.getBoard()) {
                        board.focus = location;
                        break;
                    }
                }
                Terminal.clear();
                System.out.println("Current board is "+(board.focus+1));
            } catch (LocationTakenException e) {
                Terminal.clear();
                System.out.println(e);
            } catch (IllegalArgumentException e) {
                Terminal.clear();
                System.out.println("Just what do you think you're doing, Dave?");
            } catch (java.util.InputMismatchException e) {
                s = new Scanner(System.in);
                Terminal.clear();
                System.out.println("Just what do you think you're doing, Dave?");
            }
        }
        System.out.println(board);
        if (board.getWinner() == '!') System.out.println("Draw");
        else System.out.println(board.getWinner() + " wins!");
    }
}
