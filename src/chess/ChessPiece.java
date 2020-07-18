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
		//pegar a pe�a e ver se ela � advers�ria ou n�o.
		ChessPiece p = board.piece(position);
		/*quando se l� a celula da matriz, ela n�o pode ser null ou a cor da 
		 * pe�a encontrada tem que ser diferente da cor da minha pe�a*/
		return (p != null && p.getColor() != color); 
	}

}
