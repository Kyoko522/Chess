package pieces;

import main.Board;
import java.awt.image.BufferedImage;

public class Queen extends Piece {

    public Queen(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.isWhite = isWhite;
        this.name = "Queen";  // Fixed name

        this.sprite = sheet.getSubimage(1 * sheetScale,
                        isWhite ? 0 : sheetScale,
                        sheetScale,
                        sheetScale)
                .getScaledInstance(board.tileSize,
                        board.tileSize,
                        BufferedImage.SCALE_SMOOTH);
    }

    @Override
    public boolean isValidMovement(int col, int row) {
        // Diagonal or same row/column
        return Math.abs(this.col - col) == Math.abs(this.row - row)
                || this.col == col
                || this.row == row;
    }

    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        // Figure out how many steps we move in each direction
        int colDiff = col - this.col;
        int rowDiff = row - this.row;

        // Step of -1, 0, or 1
        int colStep = Integer.compare(colDiff, 0);
        int rowStep = Integer.compare(rowDiff, 0);

        // Move along the path until we reach the target
        int currentCol = this.col + colStep;
        int currentRow = this.row + rowStep;

        while (currentCol != col || currentRow != row) {
            if (board.getPiece(currentCol, currentRow) != null) {
                return true; // Collision
            }
            currentCol += colStep;
            currentRow += rowStep;
        }
        return false; // No collision
    }
}