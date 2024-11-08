package pieces;

import main.Board;
import pieces.Piece;

public class Move {

    private int startCol, startRow; // Starting position
    private int endCol, endRow;     // Ending position

    private Piece piece;            // The piece being moved
    private Piece capturedPiece;    // The piece being captured, if any

    // Constructor to initialize a Move object
    public Move(Piece piece, int startCol, int startRow, int endCol, int endRow, Piece capturedPiece) {
        this.piece = piece;
        this.startCol = startCol;
        this.startRow = startRow;
        this.endCol = endCol;
        this.endRow = endRow;
        this.capturedPiece = capturedPiece;
    }

    // Getter for start column
    public int getStartCol() {
        return startCol;
    }

    // Getter for start row
    public int getStartRow() {
        return startRow;
    }

    // Getter for end column
    public int getEndCol() {
        return endCol;
    }

    // Getter for end row
    public int getEndRow() {
        return endRow;
    }

    // Getter for piece being moved
    public Piece getPiece() {
        return piece;
    }

    // Getter for captured piece, if any
    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    // For debugging or display purposes
    @Override
    public String toString() {
        return piece.getName() + " from (" + startCol + ", " + startRow + ") to (" + endCol + ", " + endRow + ")" +
                (capturedPiece != null ? ", capturing " + capturedPiece.getName() : "");
    }
}