/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halll7908
 */
public class Dalek {

    private int row;
    private int col;
    private boolean hasCrashed;
    
    
    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }    
    
    public boolean hasCrashed() {
        return hasCrashed;
    }

    // constructor
    public Dalek(int row, int col) {
        this.hasCrashed = false;
        this.row = row;
        this.col = col;
    }

    public void move(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
    }

    public void advanceTowards(Doctor doc) {
        if (this.hasCrashed == false) {
            if (doc.getCol() > this.col) {
                this.col = this.col + 1;
            } else if (doc.getCol() < this.col) {
                this.col = this.col - 1;
            }

            if (doc.getRow() > this.row) {
                this.row = this.row + 1;
            } else if (doc.getRow() < this.row) {
                this.row = this.row - 1;
            }
        }
    }

    // stop advanceTowards
    // call method if two Daleks have crashed
    public void crash() {
        this.hasCrashed = true;
    }
}