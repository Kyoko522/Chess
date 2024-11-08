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
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        Piece pieceXY = board.getPiece(col, row);

        // Only select a piece if it matches the turn color
        if (pieceXY != null && ((board.whiteTurn && pieceXY.isWhite) || (!board.whiteTurn && !pieceXY.isWhite))) {
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
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        if (board.selectedPiece != null) {
            // Check if the selected piece color matches the turn
            if ((board.whiteTurn && board.selectedPiece.isWhite) || (!board.whiteTurn && !board.selectedPiece.isWhite)) {
                Move move = new Move(board, board.selectedPiece, col, row);

                // Check if the move is valid and execute it if so
                if (board.isValidMove(move)) {
                    board.makeMove(move);
                } else {
                    // Reset piece position if the move is invalid
                    board.selectedPiece.xPos = board.selectedPiece.col * board.tileSize;
                    board.selectedPiece.yPos = board.selectedPiece.row * board.tileSize;
                }
            }
        }

        // Clear the selected piece and repaint the board
        board.selectedPiece = null;
        board.repaint();
    }
}