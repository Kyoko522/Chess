package main;

import pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input extends MouseAdapter {

    Board board; // Reference to the game board for handling interactions

    // Constructor initializes Input with a reference to the board
    public Input(Board board) {
        this.board = board;
    }

    // Event triggered when the mouse button is pressed
    @Override
    public void mousePressed(MouseEvent e) {
        // Calculate the column and row based on mouse position
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        // Check if there is a piece at the clicked position
        Piece pieceXY = board.getPiece(col, row);
        if (pieceXY != null) {
            // Set the selected piece to the clicked piece
            board.selectedPiece = pieceXY;
        }
    }

    // Event triggered when the mouse is dragged (used to drag selected pieces)
    @Override
    public void mouseDragged(MouseEvent e) {
        if (board.selectedPiece != null) {
            // Update the position of the selected piece to follow the mouse
            board.selectedPiece.xPos = e.getX() - board.tileSize / 2;
            board.selectedPiece.yPos = e.getY() - board.tileSize / 2;

            // Repaint the board to update the piece's position visually
            board.repaint();
        }
    }

    // Event triggered when the mouse button is released
    @Override
    public void mouseReleased(MouseEvent e) {
        // Calculate the column and row based on mouse release position
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        if (board.selectedPiece != null) {
            // Create a move object to represent the new position of the selected piece
            Move move = new Move(board, board.selectedPiece, col, row);

            // Check if the move is valid based on the game rules
            if (board.isValidMove(move)) {
                board.makeMove(move); // Execute the move if valid
            } else {
                // If invalid, reset the piece to its original position
                board.selectedPiece.xPos = board.selectedPiece.col * board.tileSize;
                board.selectedPiece.yPos = board.selectedPiece.row * board.tileSize;
            }
        }

        // Clear the selected piece and repaint the board
        board.selectedPiece = null;
        board.repaint();
    }
}