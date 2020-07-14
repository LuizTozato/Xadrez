package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece{

	public Rook(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		/*Criar uma matriz do tamanho do tabuleiro.
		Essa matriz inicia-se com todos os valores falsos,
		como se a peça estivesse presa*/
		boolean[][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()]; 
		
		/*Como estamos tratando da Torre, vamos verificar as celulas da matriz:
		 * acima, abaixo, direita e esquerda*/
		Position p = new Position(0,0);
		
		
		//Acima
		p.setValues(position.getRow()-1, position.getColumn()); //colocando em p a posição da torre.
		
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//enquanto a posição existir e não tiver nada lá, vou marca-la como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow()-1);
		}
		/*o while só vai para quando encontrar uma peça logo a frente ou cair fora do tabuleiro.
		 * Então, quando ele terminar, vamos analisar se a peça logo a frente
		 * é inimigo ou não. Se for, marcar também true pois podemos capturar o inimigo.*/
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//Esquerda
		p.setValues(position.getRow(), position.getColumn()-1);
		
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn()-1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}		
		//Direita
		p.setValues(position.getRow(), position.getColumn()+1);
		
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn()+1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}	
		//Para baixo
		p.setValues(position.getRow()+1, position.getColumn());
		
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {

			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow()+1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}
	
}
