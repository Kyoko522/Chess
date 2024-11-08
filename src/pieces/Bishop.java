package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Bishop extends Piece {
    public Bishop(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.isWhite = isWhite;
        this.name = "Bishop";

        this.sprite = sheet.getSubimage(2 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int col, int row) {
        return Math.abs(this.col - col) == Math.abs(this.row - row);
    }

    public boolean moveCollidesWithPiece(int col, int row) {
        int colDiff = col - this.col;
        int rowDiff = row - this.row;

        if (Math.abs(colDiff) != Math.abs(rowDiff)) {
            // If not moving diagonally, return false (bishop movement not valid)
            return true;
        }

        int colStep = colDiff > 0 ? 1 : -1;
        int rowStep = rowDiff > 0 ? 1 : -1;

        int currentCol = this.col + colStep;
        int currentRow = this.row + rowStep;

        // Check each tile along the path to the destination
        while (currentCol != col && currentRow != row) {
            if (board.getPiece(currentCol, currentRow) != null) {
                return true; // Collision detected
            }
            currentCol += colStep;
            currentRow += rowStep;
        }

        return false; // No collision along the path
    }
}
