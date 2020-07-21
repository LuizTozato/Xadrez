package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;
	
	//Constructor
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		/*
		 * primeiro: pega a pe�a do tabuleiro. Fa�a downcasting pq o que vem do
		 * tabuleiro � Piece, superclasse, e n�o ChessPiece, subclasse.
		 */
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		/*
		 * segundo: ver se essa pe�a "p" n�o � nula e se � de cor diferente da cor do
		 * rei, ou seja, uma pe�a advers�ria.
		 */
		return p == null || p.getColor() != getColor();
	}

	private boolean testRookCastling(Position position) {
		// esse m�todo testa se a torre est� apta a fazer a jogada Roque (Castling em
		// ingl�s)
		// primeiro: pegar a torre pela posi��o passada por argumento
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && // celula n�o est� vazia
				p instanceof Rook && // � uma torre
				p.getColor() == getColor() && // da mesma cor do rei
				p.getMoveCount() == 0; // que nunca se mexeu
	}

	@Override
	public boolean[][] possibleMoves() {
		/*Criar uma matriz do tamanho do tabuleiro.
		Essa matriz inicia-se com todos os valores falsos,
		como se a pe�a estivesse presa*/
		boolean[][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()]; 
		
		Position p = new Position(0, 0);
		
		//acima
		//primeiro: pega a posi��o da matriz acima do rei.
		p.setValues(position.getRow() - 1 , position.getColumn());
		//segundo: se a posi��o existir e canMove() for positivo, o rei pode mover-se.
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

		
		// Aqui vou testar os requisitos para o rei se mover para realizar a jogada Roque
		// primeiro: se o rei nunca se mexeu e o rei n�o est� em cheque
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {
			//ROQUE PEQUENO (a direita)
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3); // posi��o torre da direita
			if(testRookCastling(posT1)) {
				//aqui j� est� tudo certo com a torre. Agora vou ver se o caminho est� livre para o rei poder caminhar
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			
			//ROQUE GRANDE (a esquerda)
			Position posT2 = new Position(position.getRow(), position.getColumn() - 4); // posi��o torre da esquerda
			if(testRookCastling(posT2)) {
				//aqui j� est� tudo certo com a torre. Agora vou ver se o caminho est� livre para o rei poder caminhar
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}			
			}
		}

		return mat;
	}












}
