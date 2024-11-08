package main;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    // Size of each tile in pixels
    public int tileSize = 85;

    // Number of rows and columns on the board
    final int row = 8;
    final int col = 8;

    // List to hold all chess pieces on the board
    ArrayList<Piece> pieceList = new ArrayList<>();

    // Reference to the currently selected piece
    public Piece selectedPiece;

    // Input handler for mouse events
    Input input = new Input(this);

    // Check scanner for determining check/checkmate states
    CheckScanner checkScanner = new CheckScanner(this);

    // Constructor to initialize board properties and pieces
    public Board() {
        this.setPreferredSize(new Dimension(col * tileSize, tileSize * row));

        // Adding mouse listeners for interaction
        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        // Method to populate the board with pieces
        addPieces();
    }

    // Retrieve a piece at a specific column and row
    public Piece getPiece(int col, int row) {
        for (Piece piece : pieceList) {
            if (piece.col == col && piece.row == row)
                return piece;
        }
        return null;
    }

    // Determine if a move is valid based on game rules
    public boolean isValidMove(Move move) {
        if (sameTeam(move.piece, move.capture)) // Cannot capture a piece on the same team
            return false;
        if (!move.piece.isValidMovement(move.newCol, move.newRow)) // Check if the piece can move to the destination
            return false;
        if (move.piece.moveCollidesWithPiece(move.newCol, move.newRow)) // Ensure the move path is clear
            return false;
        return true;
    }

    // Check if two pieces are on the same team
    public boolean sameTeam(Piece one, Piece two) {
        if (one == null || two == null)
            return false;
        return one.isWhite == two.isWhite;
    }

    // Perform a move and update piece position
    public void makeMove(Move move) {
        move.piece.col = move.newCol;
        move.piece.row = move.newRow;

        move.piece.xPos = move.newCol * tileSize;
        move.piece.yPos = move.newRow * tileSize;

        move.piece.isFirstMove = false; // Update first move status for special moves
        capture(move); // Capture any piece on the target square
    }

    // Capture a piece in the move and remove it from the board
    public void capture(Move move) {
        pieceList.remove(move.capture);
    }

    // Find the king piece of a specific team
    Piece findKing(boolean isWhite) {
        for (Piece piece : pieceList) {
            if (isWhite == piece.isWhite && piece.name.equalsIgnoreCase("King"))
                return piece;
        }
        return null;
    }

    // Initialize and place pieces on the board
    public void addPieces() {
        // Adding black and white pieces in standard starting positions
        pieceList.add(new Rook(this, 0, 0, false));
        pieceList.add(new Knight(this, 1, 0, false));
        pieceList.add(new Bishop(this, 2, 0, false));
        pieceList.add(new Queen(this, 3, 0, false));
        pieceList.add(new King(this, 4, 0, false));
        pieceList.add(new Bishop(this, 5, 0, false));
        pieceList.add(new Knight(this, 6, 0, false));
        pieceList.add(new Rook(this, 7, 0, false));

        for (int i = 0; i < 8; i++) {
            pieceList.add(new Pawn(this, i, 1, false)); // Black pawns
            pieceList.add(new Pawn(this, i, 6, true)); // White pawns
        }

        pieceList.add(new Rook(this, 0, 7, true));
        pieceList.add(new Knight(this, 1, 7, true));
        pieceList.add(new Bishop(this, 2, 7, true));
        pieceList.add(new Queen(this, 3, 7, true));
        pieceList.add(new King(this, 4, 7, true));
        pieceList.add(new Bishop(this, 5, 7, true));
        pieceList.add(new Knight(this, 6, 7, true));
        pieceList.add(new Rook(this, 7, 7, true));
    }

    // Custom painting method to render the board and pieces
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for smoother rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the chessboard with alternating colors and borders
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                // Set tile color based on position for a checkerboard pattern
                g2d.setColor((r + c) % 2 == 0 ? new Color(240, 217, 181) : new Color(181, 136, 99));
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);

                // Add border for each tile for clarity
                g2d.setColor(new Color(60, 60, 60));
                g2d.drawRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }

        // Highlight valid moves for the selected piece
        if (selectedPiece != null) {
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (isValidMove(new Move(this, selectedPiece, c, r))) {
                        g2d.setColor(new Color(68, 180, 190, 128)); // Semi-transparent highlight
                        g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);

                        // Add outline to the highlighted tile
                        g2d.setColor(new Color(50, 150, 160));
                        g2d.drawRect(c * tileSize, r * tileSize, tileSize, tileSize);
                    }
                }
            }
        }

        // Draw all pieces in their current positions
        for (Piece piece : pieceList) {
            piece.paint(g2d);
        }

        // Highlight the selected piece (if any) with a yellow overlay
        if (selectedPiece != null) {
            g2d.setColor(new Color(255, 223, 0, 150)); // Semi-transparent yellow for selected piece
            g2d.fillRect(selectedPiece.col * tileSize, selectedPiece.row * tileSize, tileSize, tileSize);

            // Outline the selected piece's tile for better visibility
            g2d.setColor(Color.ORANGE);
            g2d.drawRect(selectedPiece.col * tileSize, selectedPiece.row * tileSize, tileSize, tileSize);
        }
    }
}