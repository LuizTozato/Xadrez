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
		//pegar a pe�a e ver se ela � advers�ria ou n�o.
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		/*quando se l� a celula da matriz, ela n�o pode ser null ou a cor da 
		 * pe�a encontrada tem que ser diferente da cor da minha pe�a*/
		return (p != null && p.getColor() != color); 
	}
	
	
}
