package chess;

import boardgame.Position;

public class ChessPosition {

	private char column;
	private int row;
	
	//Constructor
	public ChessPosition(char column, int row) {
		if(column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Erro instantiating chess position. Valid values are from a1 to h8");
		}
		this.column = column;
		this.row = row;
	}

	//Getters and setters
	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	//Methods
		//returna a posição de matriz
	protected Position toPosition() {
		return new Position(8-row , column - 'a');
	}
		//retorna a posição de batalha naval
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}
	
}
