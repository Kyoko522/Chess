package main;
import pieces.Piece;

public class Move {

    int oldCol, oldRow; // Stores the original column and row of the piece
    int newCol, newRow; // Stores the target column and row for the move

    Piece piece;        // The piece being moved
    Piece capture;      // The piece that will be captured, if any

    // Constructor initializes a Move object with the board, piece, and destination coordinates
    public Move(Board board, Piece piece, int newCol, int newRow) {
        // Store the original position of the piece
        this.oldCol = piece.col;
        this.oldRow = piece.row;

        // Store the target position for the move
        this.newCol = newCol;
        this.newRow = newRow;

        // Reference to the piece being moved
        this.piece = piece;

        // Check if there is a piece at the destination to capture
        this.capture = board.getPiece(this.newCol, this.newRow);
    }
}