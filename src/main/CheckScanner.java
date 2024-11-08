package main;

import pieces.King;
import pieces.Piece;

public class CheckScanner {

    Board board; // Reference to the game board for accessing pieces and their positions

    // Constructor initializes the CheckScanner with the game board
    public CheckScanner(Board board) {
        this.board = board;
    }

    // Checks if the king is in check based on a potential move
    public boolean kingIsInCheck(Move move) {
        Piece king = board.findKing(move.piece.isWhite); // Find the king of the same color as the piece making the move
        assert king != null;

        int kingCol = king.col;
        int kingRow = king.row;

        // If the selected piece is the king, update its position to the move's destination
        if (board.selectedPiece != null && board.selectedPiece.name.equalsIgnoreCase("King")) {
            kingCol = move.newCol;
            kingRow = move.newRow;
        }

        // Check all possible directions and positions to see if any piece threatens the king
        return hitByRook(move.newCol, move.newRow, king, kingCol, kingRow, 0, 1) ||  // up
                hitByRook(move.newCol, move.newRow, king, kingCol, kingRow, 1, 0) || // right
                hitByRook(move.newCol, move.newRow, king, kingCol, kingRow, 0, -1) ||// down
                hitByRook(move.newCol, move.newRow, king, kingCol, kingRow, -1, 0) ||// left

                hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow, -1, -1) ||// up left
                hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow, 1, -1) || // up right
                hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow, 1, 1) ||  // down right
                hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow, -1, 1) || // down left

                hitByKnight(move.newCol, move.newRow, king, kingCol, kingRow) ||       // check knight threat
                hitByPawn(move.newCol, move.newRow, king, kingCol, kingRow) ||         // check pawn threat
                hitByKing(king, kingCol, kingRow);                                    // check king threat
    }

    // Check if the king is in check from a rook or queen in a straight line
    private boolean hitByRook(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) { // Loop up to the board's edge
            if (kingCol + (i * colVal) == col && kingRow + (i * rowVal) == row) {
                break; // Stop if we reach the intended move position
            }

            Piece piece = board.getPiece(kingCol + (i * colVal), kingRow + (i * rowVal));
            if (piece != null && piece != board.selectedPiece) {
                // Check if this piece is an enemy rook or queen threatening the king
                if (board.sameTeam(piece, king) && (piece.name.equalsIgnoreCase("Rook") || piece.name.equals("Queen"))) {
                    return true; // King is in check by rook or queen
                }
                break;
            }
        }
        return false; // No rook or queen threatens the king in this direction
    }

    // Check if the king is in check from a bishop or queen diagonally
    private boolean hitByBishop(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) {
            if (kingCol - (i * colVal) == col && kingRow - (i * rowVal) == row) break;

            Piece piece = board.getPiece(kingCol - (i * colVal), kingRow - (i * rowVal));
            if (piece != null && piece != board.selectedPiece) {
                // Check if this piece is an enemy bishop or queen
                if (board.sameTeam(piece, king) && (piece.name.equals("Bishop") || piece.name.equals("Queen"))) {
                    return true; // King is in check by bishop or queen
                }
                break;
            }
        }
        return false;
    }

    // Check if the king is in check from a knight
    private boolean hitByKnight(int col, int row, Piece king, int kingCol, int kingRow) {
        // Check all knight positions around the king
        return checkKnight(board.getPiece(kingCol - 1, kingRow - 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 1, kingRow - 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 2, kingRow - 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 2, kingRow + 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 1, kingRow + 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 1, kingRow + 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 2, kingRow + 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 2, kingRow - 1), king, col, row);
    }

    // Helper to check if a specific knight threatens the king
    private boolean checkKnight(Piece p, Piece king, int col, int row) {
        return (p != null && !board.sameTeam(king, p) && p.name.equals("Knight") && !(p.col == col && p.row == row));
    }

    // Check if the king is in check by another king in adjacent tiles
    private boolean hitByKing(Piece king, int kingCol, int kingRow) {
        // Check all adjacent positions for an enemy king
        return checkKing(board.getPiece(kingCol - 1, kingRow - 1), king) ||
                checkKing(board.getPiece(kingCol + 1, kingRow - 1), king) ||
                checkKing(board.getPiece(kingCol, kingRow - 1), king) ||
                checkKing(board.getPiece(kingCol - 1, kingRow), king) ||
                checkKing(board.getPiece(kingCol + 1, kingRow), king) ||
                checkKing(board.getPiece(kingCol - 1, kingRow + 1), king) ||
                checkKing(board.getPiece(kingCol + 1, kingRow + 1), king) ||
                checkKing(board.getPiece(kingCol, kingRow + 1), king);
    }

    // Helper to check if a specific king is in the surrounding positions and threatens the king
    private boolean checkKing(Piece p, Piece king) {
        return (p != null && board.sameTeam(p, king) && p.name.equals("King"));
    }

    // Check if the king is in check from a pawn in diagonal attack positions
    private boolean hitByPawn(int col, int row, Piece king, int kingCol, int kingRow) {
        int colorVal = king.isWhite ? -1 : 1; // Direction depends on pawn color
        // Check the two possible diagonal positions where a pawn could threaten the king
        return checkPawn(board.getPiece(kingCol + 1, kingRow + colorVal), king, col, row) ||
                checkPawn(board.getPiece(kingCol - 1, kingRow + colorVal), king, col, row);
    }

    // Helper to check if a pawn threatens the king
    private boolean checkPawn(Piece p, Piece king, int col, int row) {
        return (p != null && board.sameTeam(p, king) && p.name.equals("Pawn") && !(p.col == col && p.row == row));
    }
}