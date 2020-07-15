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
			//aqui estou abastecendo a matriz de peças com as peças do boardgame 
			}
		}
		return mat;
	}
	
	//--------------------------------------------
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		/*primeiro: pegar a posição em coordenada de xadrez e converter
		  em coordenada de matriz. */
		Position position = sourcePosition.toPosition();
		//segundo:validar a posição inicial.
		validateSourcePosition(position);
		//terceiro: retornar a matriz de possibilidades para a peça.
		return board.piece(position).possibleMoves();
		
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		//primeiro: transformar as posições recebidas por argumento em posição de matriz.
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		/*segundo: validar se nessa posição de origem já há uma peça.
		 * validar a posição de destino.*/
		validateSourcePosition(source);
		validateTargetPosition(source,target);
		Piece capturedPiece = makeMove(source,target);
		
		//terceiro: retornar a peça capturada (Piece) em formato ChessPiece (usar downcasting).
		return (ChessPiece) capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		//primeiro: retirar a peça na posição de origem.
		Piece p = board.removePiece(source);
		
		//segundo: remover a peça que possa estar na posição de destino, ou seja, capturá-la.
		Piece capturedPiece = board.removePiece(target);
		
		//terceiro: tira a peça da posição "source" e coloca na posição "target".
		board.placePiece(p, target);
		
		//quarto: retornar a peça capturada.
		return capturedPiece;
	}
	
	private void validateSourcePosition(Position position) {
		//se não tiver uma peça na posição recebida por argumento, lanço exceção
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position.");
		}
		//se não tiver movimento possível, lanço exceção 
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece.");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		/*acessa o tabuleiro, a peça e a matriz de true e false que diz se 
		o movimento é ou não possível. Se não for, lança exceção.*/
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position.");
		}
	}
	//--------------------------------------------
	
	
	/*função que recebe: 1 peça em coordenadas de batalha naval.
	faz: transforma em coordenadas de matriz e instancia a peça no board.*/
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
