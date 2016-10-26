
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

        // Doctor
        Doctor doc = new Doctor(1, 9);
        
        
        // Daleks
        Dalek dalek1 = new Dalek(2, 2);
        Dalek dalek2 = new Dalek(9, 9);
        Dalek dalek3 = new Dalek(2, 9);

        // Daleks initial position
        board.putPeg(Color.BLACK, dalek1.getRow(), dalek1.getCol());
        board.putPeg(Color.BLACK, dalek2.getRow(), dalek2.getCol());
        board.putPeg(Color.BLACK, dalek3.getRow(), dalek3.getCol());
        // Doctor inital position
        board.putPeg(Color.WHITE, doc.getRow(), doc.getCol());

        // message at the bottom
        board.displayMessage("PLAY DALEKS, THE GAME");

        while (true) {
            // get a click on the board
            Coordinate c = board.getClick();
            
            // get row & columns
            int row = c.getRow();
            int col = c.getCol();

            // remove peg and place in new spot
            board.removePeg(doc.getRow(), doc.getCol());
            
            // remove dalek before placing again
            board.removePeg(dalek1.getRow(), dalek1.getCol());
            board.removePeg(dalek2.getRow(), dalek2.getCol());
            board.removePeg(dalek3.getRow(), dalek3.getCol());
            
            
            // move Doctor
            doc.move(row, col);
            
            // daleks crash
            // 1 & 2
            if (dalek1.getCol() == dalek2.getCol() && dalek1.getRow() == dalek2.getRow()){
                dalek1.crash();
                dalek2.crash();
                board.putPeg(Color.RED, dalek1.getRow(), dalek1.getCol());
            }
            // 1 & 3
            if (dalek1.getCol() == dalek3.getCol() && dalek1.getRow() == dalek3.getRow() ){
                dalek1.crash();
                dalek3.crash();
                board.putPeg(Color.RED, dalek1.getRow(), dalek1.getCol());
            }
            // 3 & 2
            if (dalek3.getCol() == dalek2.getCol() && dalek3.getRow() == dalek2.getRow() ){
                dalek3.crash();
                dalek2.crash();
                board.putPeg(Color.RED, dalek2.getRow(), dalek2.getCol());
            }
            
            // doctor into dalek - END GAME
            if (dalek1.getCol() == doc.getCol() && dalek1.getRow() == doc.getRow() ||
                    dalek2.getCol() == doc.getCol() && dalek2.getRow() == doc.getRow() || 
                        dalek3.getCol() == doc.getCol() && dalek3.getRow() == doc.getRow()){
                board.displayMessage("YOU WERE CAPTURED. GAME OVER");
                break;
            }
            
            // Win Game - all Daleks have crashed
            if (dalek1.hasCrashed() && dalek2.hasCrashed() && dalek3.hasCrashed()){
                board.displayMessage("YOU DEFEATED THE DALEKS.");
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

            // if hasCrashed, then place a red peg
            if (dalek1.hasCrashed()) {
                board.putPeg(Color.RED, dalek1.getRow(), dalek1.getCol());
            } else if (dalek2.hasCrashed()) {
                board.putPeg(Color.RED, dalek2.getRow(), dalek2.getCol());
            } else if (dalek3.hasCrashed()) {
                board.putPeg(Color.RED, dalek3.getRow(), dalek3.getCol());
            }
            
            // put peg at the click
            board.putPeg(Color.WHITE, doc.getRow(), doc.getCol());
            // Daleks
            board.putPeg(Color.BLACK, dalek1.getRow(), dalek1.getCol());
            board.putPeg(Color.BLACK, dalek2.getRow(), dalek2.getCol());
            board.putPeg(Color.BLACK, dalek3.getRow(), dalek3.getCol());
        }
    }
}