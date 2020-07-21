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
		 * primeiro: pega a peça do tabuleiro. Faça downcasting pq o que vem do
		 * tabuleiro é Piece, superclasse, e não ChessPiece, subclasse.
		 */
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		/*
		 * segundo: ver se essa peça "p" não é nula e se é de cor diferente da cor do
		 * rei, ou seja, uma peça adversária.
		 */
		return p == null || p.getColor() != getColor();
	}

	private boolean testRookCastling(Position position) {
		// esse método testa se a torre está apta a fazer a jogada Roque (Castling em
		// inglês)
		// primeiro: pegar a torre pela posição passada por argumento
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && // celula não está vazia
				p instanceof Rook && // é uma torre
				p.getColor() == getColor() && // da mesma cor do rei
				p.getMoveCount() == 0; // que nunca se mexeu
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

		
		// Aqui vou testar os requisitos para o rei se mover para realizar a jogada Roque
		// primeiro: se o rei nunca se mexeu e o rei não está em cheque
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {
			//ROQUE PEQUENO (a direita)
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3); // posição torre da direita
			if(testRookCastling(posT1)) {
				//aqui já está tudo certo com a torre. Agora vou ver se o caminho está livre para o rei poder caminhar
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			
			//ROQUE GRANDE (a esquerda)
			Position posT2 = new Position(position.getRow(), position.getColumn() - 4); // posição torre da esquerda
			if(testRookCastling(posT2)) {
				//aqui já está tudo certo com a torre. Agora vou ver se o caminho está livre para o rei poder caminhar
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
