package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i=0; i<board.getRows();i++) {
			for (int j=0; j<board.getColumns();j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			//aqui estou abastecendo a matriz de pe�as com as pe�as do boardgame 
			}
		}
		return mat;
	}
	
	//--------------------------------------------
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		/*primeiro: pegar a posi��o em coordenada de xadrez e converter
		  em coordenada de matriz. */
		Position position = sourcePosition.toPosition();
		//segundo:validar a posi��o inicial.
		validateSourcePosition(position);
		//terceiro: retornar a matriz de possibilidades para a pe�a.
		return board.piece(position).possibleMoves();
		
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		//primeiro: transformar as posi��es recebidas por argumento em posi��o de matriz.
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		/*segundo: validar se nessa posi��o de origem j� h� uma pe�a.
		 * validar a posi��o de destino.*/
		validateSourcePosition(source);
		validateTargetPosition(source,target);
		Piece capturedPiece = makeMove(source,target);
		
		//terceiro: retornar a pe�a capturada (Piece) em formato ChessPiece (usar downcasting).
		return (ChessPiece) capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		//primeiro: retirar a pe�a na posi��o de origem.
		Piece p = board.removePiece(source);
		
		//segundo: remover a pe�a que possa estar na posi��o de destino, ou seja, captur�-la.
		Piece capturedPiece = board.removePiece(target);
		
		//terceiro: tira a pe�a da posi��o "source" e coloca na posi��o "target".
		board.placePiece(p, target);
		
		//quarto: retornar a pe�a capturada.
		return capturedPiece;
	}
	
	private void validateSourcePosition(Position position) {
		//se n�o tiver uma pe�a na posi��o recebida por argumento, lan�o exce��o
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position.");
		}
		//se n�o tiver movimento poss�vel, lan�o exce��o 
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece.");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		/*acessa o tabuleiro, a pe�a e a matriz de true e false que diz se 
		o movimento � ou n�o poss�vel. Se n�o for, lan�a exce��o.*/
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position.");
		}
	}
	//--------------------------------------------
	
	
	/*fun��o que recebe: 1 pe�a em coordenadas de batalha naval.
	faz: transforma em coordenadas de matriz e instancia a pe�a no board.*/
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
