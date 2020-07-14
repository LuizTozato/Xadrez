package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
	}

	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		/*Criar uma matriz do tamanho do tabuleiro.
		Essa matriz inicia-se com todos os valores falsos,
		como se a peça estivesse presa*/
		boolean[][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()]; 
		return mat;
	}
}
