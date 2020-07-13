package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces; //matriz de peças
	
	//Constructors
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	//Getters and setters
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	//Methods
	public Piece piece(int row, int column) {
		return pieces[row][column]; 
		//esse metodo retorna a matriz de peças
	}
	
		//sobrecarga - metodo com mesmo nome do de cima
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}	
	
	
	
	
}
