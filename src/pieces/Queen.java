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
        this.name = "Knight";

        this.sprite = sheet.getSubimage(1 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int col, int row) {
        return Math.abs(this.col - col) == Math.abs(this.row - row) || this.col == col || this.row == row;
    }

    public boolean moveCollidesWithPiece(int col, int row) {//try to just call the  other methods from the Bishop  and rook class  so that your not just copying the same code again and again.

        if (this.col > col) { // left
            for (int c = this.col - 1; c > col; c--)
                if (board.getPiece(c, this.row) != null)
                    return true;
        }

        if (this.col < col) { // right
            for (int c = this.col + 1; c < col; c++)
                if (board.getPiece(c, this.row) != null)
                    return true;
        }

        if (this.row > row) {  // up
            for (int r = this.row - 1; r > row; r--) {
                if (board.getPiece(this.col, r) != null)
                    return true;
            }
        }

        if (this.row < row) { // down
            for (int r = this.row + 1; r < row; r++) {
                if (board.getPiece(this.col, r) != null)
                    return true;
            }
        }

        //up left
        if (this.col > col && this.row > row) {
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (board.getPiece(this.col - i, this.row - i) != null)
                    return true;
            }
        }

//        up right
        if (this.col < col && this.row > row) {
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (board.getPiece(this.col + i, this.row - i) != null)
                    return true;
            }
        }

//            down left
    if (this.col < -col && this.row < row){
        for (int i = 1; i < Math.abs(this.col - col); i++){
            if (board.getPiece(this.col - i, this.row +1)!=null)
                return true;
        }
    }

    //down right
        if (this.col < col && this.row < row) {
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (board.getPiece(this.col + i, this.row + i) != null)
                    return true;
            }
        }

        return false;
    }
}
