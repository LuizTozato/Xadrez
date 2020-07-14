package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{

	private Color color;

	//Constructor
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	//Getters and setters
	public Color getColor() {
		return color;
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		//pegar a peça e ver se ela é adversária ou não.
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		/*quando se lê a celula da matriz, ela não pode ser null ou a cor da 
		 * peça encontrada tem que ser diferente da cor da minha peça*/
		return (p != null && p.getColor() != color); 
	}
	
	
}
