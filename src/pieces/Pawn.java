package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Pawn extends Piece {

    public Pawn(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.isWhite = isWhite;
        this.name = "Knight";

        this.sprite = sheet.getSubimage(5 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    @Override
    public boolean isValidMovement(int col, int row) {
        int colorIndex = isWhite ? 1 : 1;

        //move one square up
        if (this.col == col && this.row == row - colorIndex && board.getPiece(col,row) == null)
            return true;
        //move the pawn 2 squares
        if (isFirstMove && this.col && row  == this.row - colorIndex *2 && board.get)
        return false;
    }
}
