package boardgame;

public class Board<Piece extends boardgame.Piece> {

	private int rows;
	private int columns;
	private Piece[][] pieces; //matriz de pe�as
	
	//Constructors
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column.");
		}
		this.rows = rows;
		this.columns = columns;
		//por algum motivo obscuro o Java n�o deixa criar matriz de tipo generico
		//noinspection unchecked
		pieces = (Piece[][]) new boardgame.Piece[rows][columns];
	}

	//Getters and setters
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	//Methods
	public Piece piece(int row, int column) {
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the board.");
		}
		return pieces[row][column]; 
		//esse metodo retorna a PE�A da linha row, coluna column da matriz de pe�as
	}
		//sobrecarga - metodo com mesmo nome do de cima
	public Piece piece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board.");
		}
		return pieces[position.getRow()][position.getColumn()];
	}	
	
	//---------------------------------------
	public void placePiece (Piece piece, Position position) {
		if(thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on the position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece; //coloca a pe�a na matriz de pe�as.
		piece.position = position; //atualiza a posi��o do objeto pe�a que veio por argumento.
	}
	
	public Piece removePiece(Position position){
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board.");
		}
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position); //o auxiliar aponta para o objeto instanciado na posi��o que veio por argumento. Agora tudo o que for alterado nele, ser� no objeto tamb�m.
		aux.position = null; //a posi��o do auxiliar (consequentemente do objeto apontado tamb�m) se torna nula.
		pieces[position.getRow()][position.getColumn()] = null; //na posi��o recebida, anula/retira o objeto pe�a que l� existia.
		return aux; //retorna o objeto que estava no tabuleiro. Ele existe mas possui Position nula.
	}
	
	//---------------------------------------
	public boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}	
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board.");
		}
		return piece(position) != null;
	}
	
	
	
	
}
