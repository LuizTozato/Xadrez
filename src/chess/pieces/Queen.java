package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece{

	public Queen(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Q";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		/*Criar uma matriz do tamanho do tabuleiro.
		Essa matriz inicia-se com todos os valores falsos,
		como se a peça estivesse presa*/
		boolean[][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()]; 
		
		/*Como estamos tratando da rainha, vamos verificar as celulas da matriz:
		 * noroeste, nordeste, sudeste, sudoeste, acima, abaixo, leste e oeste. 
		 * Criar posição auxiliar*/
		Position p = new Position(0,0); 
		
		
		//Noroeste
		p.setValues(position.getRow()-1, position.getColumn()-1); //colocando em p a noroeste do bispo.
		
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//enquanto a posição existir e não tiver nada lá, vou marca-la como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1 );
		}
		/*o while só vai parar quando encontrar uma peça logo a frente ou cair fora do tabuleiro.
		 * Então, quando ele terminar, vamos analisar se a peça logo a frente
		 * é inimigo ou não. Se for, marcar também true pois podemos capturar o inimigo.*/
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Nordeste
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}		
		//Sudeste
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}	
		//Sudoeste
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {

			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Acima
		p.setValues(position.getRow() - 1, position.getColumn());
		
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {

			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn());
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Abaixo
		p.setValues(position.getRow() + 1, position.getColumn());
		
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {

			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn());
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Leste
		p.setValues(position.getRow(), position.getColumn() + 1);
		
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {

			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow(), p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Oeste
		p.setValues(position.getRow(), position.getColumn() - 1);
		
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {

			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow(), p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		

		return mat;
	}
	
}
