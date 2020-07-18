package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{

	private Board<ChessPiece> board;
	private Color color;

	//Constructor
	public ChessPiece(Board<ChessPiece> board, Color color) {
		super();
		this.board = board;
		this.color = color;
	}

	//Getters and setters
	public Board<ChessPiece> getBoard() {
		return board;
	}

	public Color getColor() {
		return color;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	//Methods
 	protected boolean isThereOpponentPiece(Position position) {
		//pegar a peça e ver se ela é adversária ou não.
		ChessPiece p = board.piece(position);
		/*quando se lê a celula da matriz, ela não pode ser null ou a cor da 
		 * peça encontrada tem que ser diferente da cor da minha peça*/
		return (p != null && p.getColor() != color); 
	}

}
