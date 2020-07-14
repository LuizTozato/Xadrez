package boardgame;

public abstract class Piece {
	
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
	
	//--------------------------
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		//O metodo abstrato "possibleMoves" ainda será definido de acordo com cada peça
		//por enquanto, só fazemos esse gancho com ele mas ele ainda será sobrescrito mais para frente
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	//--------------------------
	
	public boolean isThereAnyPossibleMove() {
		/*essa função vai ler toda a matriz de possibilidades de movimento. 
		Assim que encontrar pelo menos 1 valor de movimento possível, vai retornar verdadeiro.
		*/
		boolean[][] mat = possibleMoves();
		
		for (int i=0; i < mat.length; i++) {
			for (int j=0; j < mat.length; j++) {
				//testar se a célula da matriz na posição i,j é verdadeira
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
}
