package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{

	public Pawn(Board board, Color color) {
		super(board, color);
		
	}

	@Override
	public boolean[][] possibleMoves() {
		//Primeiro: criar matriz auxiliar e vari�rvel de posi��o auxiliar
		boolean[][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()]; 
		Position p = new Position(0, 0);
		
		if(getColor() == Color.WHITE) {
			//pegar a posi��o logo acima olhando para o tabuleiro.
			p.setValues(position.getRow()-1, position.getColumn());
			//se a posi��o existe e n�o tem ningu�m l�, podemos mover para l�.
			if( getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//agora testarei para a primeira jogada em que pode-se andar 2 casas a frente
			//obs.: para andar 2 para frente, n�o pode ter ngm na casa logo a frente
			Position p2 = new Position(position.getRow()-2, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) &&
			   getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && 
			   getMoveCount()==0){
				mat[p2.getRow()][p2.getColumn()] = true;
			}
			
			p.setValues(position.getRow()-1, position.getColumn()-1);
			//Move de ataque esquerda: se a posi��o existe e tem inimigo l�, podemos mover para l�.
			if( getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}			

			p.setValues(position.getRow()-1, position.getColumn()+1);
			//Move de ataque direita: se a posi��o existe e tem inimigo l�, podemos mover para l�.
			if( getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}				
		}
		else {//se a pe�a n�o � branca, � pq � preta.
			//pegar a posi��o logo abaixo, olhando para o tabuleiro.
			p.setValues(position.getRow()+1, position.getColumn());
			//se a posi��o existe e n�o tem ningu�m l�, podemos mover para l�.
			if( getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//agora testarei para a primeira jogada em que pode-se andar 2 casas a frente
			//obs.: para andar 2 para frente, n�o pode ter ngm na casa logo a frente
			Position p2 = new Position(position.getRow()+2, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) &&
			   getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && 
			   getMoveCount()==0){
				mat[p2.getRow()][p2.getColumn()] = true;
			}
			
			p.setValues(position.getRow()+1, position.getColumn()-1);
			//Move de ataque esquerda: se a posi��o existe e tem inimigo l�, podemos mover para l�.
			if( getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}			

			p.setValues(position.getRow()+1, position.getColumn()+1);
			//Move de ataque direita: se a posi��o existe e tem inimigo l�, podemos mover para l�.
			if( getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}	
		
		
		}
		
		return mat;
	}

	@Override
	public String toString(){
		return "P";
	}
	
	
}
