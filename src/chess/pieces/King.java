package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
	}

	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		/*primeiro: pega a peça do tabuleiro. Faça downcasting pq o que vem do 
		  tabuleiro é Piece, superclasse, e não ChessPiece, subclasse.*/
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		/*segundo: ver se essa peça "p" não é nula e se é de cor diferente da 
		  cor do rei, ou seja, uma peça adversária.*/
		return p == null || p.getColor() != getColor();
	}
	
	@Override
	public boolean[][] possibleMoves() {
		/*Criar uma matriz do tamanho do tabuleiro.
		Essa matriz inicia-se com todos os valores falsos,
		como se a peça estivesse presa*/
		boolean[][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()]; 
		
		Position p = new Position(0, 0);
		
		//acima
		//primeiro: pega a posição da matriz acima do rei.
		p.setValues(position.getRow() - 1 , position.getColumn());
		//segundo: se a posição existir e canMove() for positivo, o rei pode mover-se.
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//abaixo
		p.setValues(position.getRow() + 1 , position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//esquerda
		p.setValues(position.getRow(), position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}				
		//direita
		p.setValues(position.getRow(), position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}			
		//noroeste
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}			
		//nordeste
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}		
		//sudeste
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}	
		//sudoeste
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}			
		
		
		
		return mat;
	}
}
