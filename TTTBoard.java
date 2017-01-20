import java.util.Arrays;
import java.lang.Character;
import java.util.Scanner;
import util.Terminal;

public class TTTBoard {
    // 2D char array representing board
    private char[][] board = new char[3][3];


    public TTTBoard() {
        for (char[] row:board) Arrays.fill(row,' ');
    }

    //Create a board filled with one player so it's easy to see a board has been won
    public TTTBoard(char player) {
        for (char[] row:board) Arrays.fill(row,player);
    }

    public String[] getBoard() {
        String[] outboard = new String[3];
        for (int i=0;i<9;) {
            char[] boardline = board[i/3];
            outboard[i/3] = boardline[i++%3] + "|" + boardline[i++%3] + "|" + boardline[i++%3];
        }
        return outboard;
    }

    public void move(char player,int location) throws LocationTakenException {
        player = Character.toUpperCase(player);
        if (player != 'X' && player != 'O') throw new IllegalArgumentException("Player must be X or O, got " + player);
        if (!(location >= 0 && location <= 8)) throw new IllegalArgumentException("Location must be 0-8, got " + location);
        if (board[location/3][location%3] != ' ') throw new LocationTakenException(location);
        board[location/3][location%3] = player;
    }

    public String toString() {
        String out = "";
        for (String line : getBoard()) {
            out += line+"\n";
        }
        return out;
    }
    private boolean isWin(int loc1, int loc2, int loc3) {
        return board[loc1/3][loc1%3] == board[loc2/3][loc2%3] && board[loc2/3][loc2%3] == board[loc3/3][loc3%3] && board[loc1/3][loc1%3] != ' ' && board[loc1/3][loc1%3] != '!' ;
    }

    /* Check to see if somebody has won, and return who. Possible wins are:
     * 0 1 2
     * 3 4 5
     * 6 7 8
     * 0 3 6
     * 1 4 7
     * 2 5 8
     * 0 4 8
     * 2 4 6
     */
    public char getWinner() {
        int[][] wins = { {0,1,2},
                                 {3,4,5},
                                 {6,7,8},
                                 {0,3,6},
                                 {1,4,7},
                                 {2,5,8},
                                 {0,4,8},
                                 {2,4,6} };
        for (int[] locations : wins) {
            if (isWin(locations[0],locations[1],locations[2])) {
                return board[locations[0]/3][locations[0]%3];
            }
        }
        return '!';
    }

    public boolean gameOver() {
        boolean over = true;
        for (char[] r:board) {
            for (char c:r){
                if (c==' ') {
                    over = false;
                    break;
                }
            }
        }
        if(over) return true;
        else return getWinner() != '!';
    }

    public static void main(String[] args) {
        Terminal.clear();
        TTTBoard board = new TTTBoard();
        Scanner s = new Scanner(System.in);
        char currentPlayer = 'X';
        while (!board.gameOver()) {
            System.out.println(board);
            System.out.println("It's "+currentPlayer+"'s turn! Where do you want to go (1-9)?");
            try {
                board.move(currentPlayer,s.nextInt()-1);
                if (currentPlayer == 'X') currentPlayer = 'O';
                else currentPlayer = 'X';
                Terminal.clear();
            } catch (LocationTakenException e) {
                Terminal.clear();
                System.out.print("Location "+e.getLocation()+" is taken. Choose another location.");
            } catch (IllegalArgumentException e) {
                Terminal.clear();
                System.out.println(e);
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
