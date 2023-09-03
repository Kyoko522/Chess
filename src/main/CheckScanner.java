package main;

import pieces.King;
import pieces.Piece;

public class CheckScanner {

    Board board;

    public CheckScanner (Board baord){
        this.board = baord;
    }

    public boolean kingIsInCheck (Move move) {
        Piece king = board.finalKing(move.piece.isWhite);
        assert king != null;

        int KingCol = king.col;
        int KingRow = king.row;

        if (board.selectedPiece != null && board.selectedPiece.name.equals("King")) {
            KingCol = move.newCol;
            KingRow = move.newRow;
        }
        return hitByRook(move.newCol, move.newRow, king, KingCol, KingRow,0,1)||  //up
                hitByRook(move.newCol, move.newRow, king, KingCol, KingRow,1,0)|| //right
                hitByRook(move.newCol, move.newRow, king, KingCol, KingRow,0,-1)||//down
                hitByRook(move.newCol, move.newRow, king, KingCol, KingRow,-1,0)||//left

                hitByBishop(move.newCol, move.newRow, king, KingCol, KingRow,-1,-1)||//up,left
                hitByBishop(move.newCol, move.newRow, king, KingCol, KingRow,1,-1)||//up right
                hitByBishop(move.newCol, move.newRow, king, KingCol, KingRow,1,1)||//down right
                hitByBishop(move.newCol, move.newRow, king, KingCol, KingRow,-1,1)||//down left

            hitByKnight(move.newCol, move.newRow, king, KingCol, KingRow) ||
                    hitByPawn(move.newCol, move.newRow, king, KingCol, KingRow)||
                    hitByKing(king, KingCol, KingRow);

    }

        private boolean hitByRook(int col, int row, Piece king, int KingCol, int KingRow, int colVal, int rowVal){
            for (int i = 1; i < 8; i++) {
                if (KingCol + (i * colVal) == col && KingRow + (i * rowVal) == row) break;

                Piece piece = board.getPiece(KingCol + (i * colVal), KingRow + (i* rowVal));
                if (piece != null && piece != board.selectedPiece){
                    if (board.sameTeam(piece, king) && (piece.name.equals("Rook")|| piece.name.equals("Queen"))){return true;}
                    break;
            }
        }
            return false;
    }

    private boolean hitByBishop(int col, int row, Piece king, int KingCol, int KingRow, int colVal, int rowVal){
        for (int i = 1; i < 8; i++) {
            if (KingCol - (i * colVal) == col && KingRow - (i * rowVal) == row) break;

            Piece piece = board.getPiece(KingCol - (i * colVal), KingRow - (i* rowVal));
            if (piece != null && piece != board.selectedPiece){
                if (board.sameTeam(piece, king) && (piece.name.equals("Bishop")|| piece.name.equals("Queen"))){return true;}
                break;
            }
        }
        return false;
    }


    private boolean hitByKnight (int col, int row, Piece King, int KingCol, int KingRow){
    return checkKnight(board.getPiece(KingCol -1, KingRow -2), King, col, row) ||
            checkKnight(board.getPiece(KingCol +1, KingRow -2), King, col, row) ||
            checkKnight(board.getPiece(KingCol +2, KingRow -1), King, col, row) ||
            checkKnight(board.getPiece(KingCol +2, KingRow +1), King, col, row) ||
            checkKnight(board.getPiece(KingCol +1, KingRow +2), King, col, row) ||
            checkKnight(board.getPiece(KingCol -1, KingRow +2), King, col, row) ||
            checkKnight(board.getPiece(KingCol -2, KingRow +1), King, col, row) ||
            checkKnight(board.getPiece(KingCol -2, KingRow -1), King, col, row);
    }

    private boolean checkKnight (Piece p, Piece k, int col, int row){
        return (p != null && !board.sameTeam(k, p) && p.name.equals("Knight") && !(p.col == col && p.row == row));
    }

    private boolean hitByKing (Piece king, int kingCol, int kingRow){
        return checkKing(board.getPiece(kingCol - 1, kingRow -1), king) ||
                checkKing(board.getPiece(kingCol + 1, kingRow -1), king) ||
                checkKing(board.getPiece(kingCol, kingRow -1), king) ||
                checkKing(board.getPiece(kingCol - 1, kingRow), king) ||
                checkKing(board.getPiece(kingCol + 1, kingRow), king) ||
                checkKing(board.getPiece(kingCol - 1, kingRow +1), king) ||
                checkKing(board.getPiece(kingCol + 1, kingRow +1), king) ||
                checkKing(board.getPiece(kingCol, kingRow +1), king);
    }

    private boolean checkKing(Piece p, Piece king){
        return (p != null && board.sameTeam(p, king) && p.name.equals("King"));
    }

    private boolean hitByPawn (int col, int row, Piece King, int kingCol, int kingRow){
        int colorVal = King.isWhite ? -1 : 1;
        return checkPawn(board.getPiece(kingCol +1, kingRow +colorVal), King, col, row) ||
                checkPawn(board.getPiece(kingCol -1, kingRow +colorVal), King, col, row);
    }

    private boolean checkPawn (Piece p, Piece King, int col, int row){
        return (p != null && board.sameTeam(p, King) && p.name.equals("Pawn") && !(p.col == col && p.row == row));

    }
}
