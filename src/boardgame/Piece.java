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
		//O metodo abstrato "possibleMoves" ainda ser� definido de acordo com cada pe�a
		//por enquanto, s� fazemos esse gancho com ele mas ele ainda ser� sobrescrito mais para frente
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	//--------------------------
	
	public boolean isThereAnyPossibleMove() {
		/*essa fun��o vai ler toda a matriz de possibilidades de movimento. 
		Assim que encontrar pelo menos 1 valor de movimento poss�vel, vai retornar verdadeiro.
		*/
		boolean[][] mat = possibleMoves();
		
		for (int i=0; i < mat.length; i++) {
			for (int j=0; j < mat.length; j++) {
				//testar se a c�lula da matriz na posi��o i,j � verdadeira
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
}
