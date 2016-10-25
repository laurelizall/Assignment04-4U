
import java.awt.Color;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author halll7908
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create the game board
        Board board = new Board(12, 12);

        int random = (int) (Math.random() * 12);

        Doctor doc = new Doctor(random, random);
        Dalek dalek1 = new Dalek(1, 1);
        Dalek dalek2 = new Dalek(10, 10);
        Dalek dalek3 = new Dalek(1, 10);

        // Doctor inital position
        board.putPeg(Color.GREEN, doc.getRow(), doc.getCol());

        // Daleks initial position
        board.putPeg(Color.YELLOW, dalek1.getRow(), dalek1.getCol());
        board.putPeg(Color.YELLOW, dalek2.getRow(), dalek2.getCol());
        board.putPeg(Color.YELLOW, dalek3.getRow(), dalek3.getCol());

        // message at the bottom
        board.displayMessage("PLAY DALEKS, THE GAME");

        while (true) {
            // get a click on the board
            Coordinate c = board.getClick();
            int row = c.getRow();
            int col = c.getCol();

            // remove peg and place in new spot
            board.removePeg(doc.getRow(), doc.getCol());
            board.removePeg(dalek1.getRow(), dalek1.getCol());
            board.removePeg(dalek2.getRow(), dalek2.getCol());
            board.removePeg(dalek3.getRow(), dalek3.getCol());

            doc.move(row, col);
            
            // daleks crash
            // 1 & 2
            if (dalek1.getCol() == dalek2.getCol() && dalek1.getRow() == dalek2.getRow()){
                dalek1.crash();
                dalek2.crash();
            }
            // 1 & 3
            if (dalek1.getCol() == dalek3.getCol() && dalek1.getRow() == dalek3.getRow() ){
                dalek1.crash();
                dalek3.crash();
            }
            // 3 & 2
            if (dalek3.getCol() == dalek2.getCol() && dalek3.getRow() == dalek2.getRow() ){
                dalek3.crash();
                dalek2.crash();
            }
            
            // doctor into dalek - END GAME
            if (dalek1.getCol() == doc.getCol() && dalek1.getRow() == doc.getRow() ||
                    dalek2.getCol() == doc.getCol() && dalek2.getRow() == doc.getRow() || 
                        dalek3.getCol() == doc.getCol() && dalek3.getRow() == doc.getRow()){
                board.displayMessage("YOUR MOTHER WOULD BE ASHAMED.");
                break;
            }
            
            // Win Game - all Daleks have crashed
            if (dalek1.hasCrashed() && dalek2.hasCrashed() && dalek3.hasCrashed()){
                board.displayMessage("GOOD JOB, MOMMA'S BOY");
                break;
            }

            // move only if not crashed
            if (dalek1.hasCrashed() == false) {
                dalek1.advanceTowards(doc);
            }
            if (dalek2.hasCrashed() == false) {
                dalek2.advanceTowards(doc);
            }
            if (dalek3.hasCrashed() == false) {
                dalek3.advanceTowards(doc);
            }

            // if hasCrashed, then put a red peg
            if (dalek1.hasCrashed()) {
                board.putPeg(Color.RED, dalek1.getRow(), dalek1.getCol());
            } else if (dalek2.hasCrashed()) {
                board.putPeg(Color.RED, dalek2.getRow(), dalek2.getCol());
            } else if (dalek3.hasCrashed()) {
                board.putPeg(Color.RED, dalek3.getRow(), dalek3.getCol());
            }

            // put peg at the click
            board.putPeg(Color.GREEN, doc.getRow(), doc.getCol());

            // Daleks
            board.putPeg(Color.YELLOW, dalek1.getRow(), dalek1.getCol());
            board.putPeg(Color.YELLOW, dalek2.getRow(), dalek2.getCol());
            board.putPeg(Color.YELLOW, dalek3.getRow(), dalek3.getCol());
        }
    }
}