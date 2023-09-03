package main;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    public int tileSize = 85;

    final int row = 8;
    final int col = 8;

    ArrayList<Piece> pieceList = new ArrayList<>();

    public Piece selectedPiece;

    Input input = new Input(this);

    CheckScanner checkScanner = new CheckScanner(this);

    public Board() {
        this.setPreferredSize(new Dimension(col * tileSize, tileSize * row));

        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        addPieces();
    }

    public Piece getPiece(int col, int row) {
        for (Piece piece : pieceList) {
            if (piece.col == col && piece.row == row)
                return piece;
        }
        return null;
    }

    public boolean isValidMove(Move move) {
        if (sameTeam(move.piece, move.capture))
            return false;
        if (!move.piece.isValidMovement(move.newCol, move.newRow))
            return false;
        //when not commented out the knight doesn't work the way it's supposed to fix it later
        if (move.piece.moveCollidesWithPiece(move.newCol,move.newRow))
            return false;

        return true;
    }

    public boolean sameTeam(Piece one, Piece two) {
        if (one == null || two == null)
            return false;
        return one.isWhite == two.isWhite;
    }

    public void makeMove(Move move) {
        move.piece.col = move.newCol;
        move.piece.row = move.newRow;

        move.piece.xPos = move.newCol * tileSize;
        move.piece.yPos = move.newRow * tileSize;

        capture(move);
    }

    public void capture(Move move) {
        pieceList.remove(move.capture);

    }

    Piece findKing(boolean isWhite) {
        for (Piece piece : pieceList) {
            if (isWhite == piece.isWhite && piece.name.equalsIgnoreCase("King"))
                return piece;
        }
        return null;
    }

    public void addPieces() {
        pieceList.add(new Rook(this, 0, 0, false));
        pieceList.add(new Knight(this, 1, 0, false));
        pieceList.add(new Bishop(this, 2, 0, false));
        pieceList.add(new Queen(this, 3, 0, false));
        pieceList.add(new King(this, 4, 0, false));
        pieceList.add(new Bishop(this, 5, 0, false));
        pieceList.add(new Knight(this, 6, 0, false));
        pieceList.add(new Rook(this, 7, 0, false));

        pieceList.add(new Pawn(this, 0, 1, false));
        pieceList.add(new Pawn(this, 1, 1, false));
        pieceList.add(new Pawn(this, 2, 1, false));
        pieceList.add(new Pawn(this, 3, 1, false));
        pieceList.add(new Pawn(this, 4, 1, false));
        pieceList.add(new Pawn(this, 5, 1, false));
        pieceList.add(new Pawn(this, 6, 1, false));
        pieceList.add(new Pawn(this, 7, 1, false));

        pieceList.add(new Rook(this, 0, 7, true));
        pieceList.add(new Knight(this, 1, 7, true));
        pieceList.add(new Bishop(this, 2, 7, true));
        pieceList.add(new Queen(this, 3, 7, true));
        pieceList.add(new King(this, 4, 7, true));
        pieceList.add(new Bishop(this, 5, 7, true));
        pieceList.add(new Knight(this, 6, 7, true));
        pieceList.add(new Rook(this, 7, 7, true));

        pieceList.add(new Pawn(this, 0, 6, true));
        pieceList.add(new Pawn(this, 1, 6, true));
        pieceList.add(new Pawn(this, 2, 6, true));
        pieceList.add(new Pawn(this, 3, 6, true));
        pieceList.add(new Pawn(this, 4, 6, true));
        pieceList.add(new Pawn(this, 5, 6, true));
        pieceList.add(new Pawn(this, 6, 6, true));
        pieceList.add(new Pawn(this, 7, 6, true));
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                g2d.setColor((r + c) % 2 == 0 ? new Color(252, 234, 168) : new Color(20, 94, 77));
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }
        if (selectedPiece != null)
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (isValidMove(new Move(this, selectedPiece, c, r))) {
                        g2d.setColor(new Color(68, 180, 190));
                        g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
                    }
                }
            }


        for (Piece piece : pieceList) {
            piece.paint(g2d);
        }

    }
}
