package boardgame;

public class Piece {
	
	protected Position position; 
	private Board board;
	
	//Constructor
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	//Getters and setters
	protected Board getBoard() { //deixar o tabuleiro somente visivel ao pacote boardgame
		return board;
	}
	
	
	
}
