package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;

public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check = false;
	private boolean checkMate = false;	
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	//Constructor
	public ChessMatch() {
		board = new Board(8,8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	//Getters and setter
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
	
	//Methods
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
		
		//terceiro: se, ap�s o movimento da pe�a, o rei foi colocado em check, temos que desfazer a jogada.
		if(testCheck(currentPlayer) == true) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Voce nao pode se colocar em check");
		}
		
		//quarto: se o oponente ficou em check, marcar verdadeiro, sen�o, falso.
		check = (testCheck(opponent(currentPlayer)) == true)? true : false;
		
		/*quinto: se houver checkMate, finalizar a partida. Testar para o oponente 
		pois � quem recebe a jogada que acabou de acontecer.*/ 
		if(testCheckMate(opponent(currentPlayer)) == true) {
			checkMate = true;
		}
		else {
		//sexto: trocar o turno se n�o houve checkMate.
		nextTurn();		 
		}
		
		//s�timo: retornar a pe�a capturada (Piece) em formato ChessPiece (usar downcasting).
		return (ChessPiece) capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		//primeiro: retirar a pe�a na posi��o de origem. Usando downcasting.
		ChessPiece p = (ChessPiece) board.removePiece(source);
		p.increaseMoveCount();
		
		//segundo: remover a pe�a que possa estar na posi��o de destino, ou seja, captur�-la.
		Piece capturedPiece = board.removePiece(target);
		
		//terceiro: tira a pe�a da posi��o "source" e coloca na posi��o "target".
		board.placePiece(p, target);
		
		//quarto: tirar a pe�a capturada da lista das pe�as no tabuleiro e colocar na lista das pe�as capturadas.
		piecesOnTheBoard.remove(capturedPiece);
		capturedPieces.add(capturedPiece);		
		
		//quinto: retornar a pe�a capturada.
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		/*primeiro: l�gica de Check. Se o movimento for deixar o rei em check, precisamos desfaz�-lo.
		 *Aqui devolvemos a pe�a que teria sido movida ao local onde ela estava. */
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);
		//segundo: se uma pe�a fosse capturada, temos que devolv�-la � sua origem tambem
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			//terceiro: atualizar as listas de pe�as no tabuleiro e pe�as capturadas
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
			
		}
	}
	
	private void validateSourcePosition(Position position) {
		//primeiro: se n�o tiver uma pe�a na posi��o recebida por argumento, lan�o exce��o.
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position.");
		}
		/*segundo:se a cor da pe�a selecionada n�o for a do currentPlayer, lan�o exce��o.
		  Obs:fazer o downcasting pois a cor est� em ChessPiece e a pe�a pega � do tipo Piece */
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours.");
		}
		
		//terceiro: se n�o tiver movimento poss�vel, lan�o exce��o 
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
	
	private void nextTurn() {
		turn ++;
		//usaremos if else em formato de linha
		//"se o jogador for o branco, retornar o preto, sen�o, retornar o branco"
		currentPlayer = (currentPlayer == Color.WHITE)? Color.BLACK : Color.WHITE;
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE)? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		//primeiro: quero a lista de pe�as no tabuleiro que s�o da mesma cor da passada por argumento.
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			//para cada pe�a encontrada, vou verificar se � um rei. Se for, vou retorn�-lo.
			if(p instanceof King) {
				return (ChessPiece) p;
			}
		}
		//segundo: se o for each n�o encontrar nenhum rei, lan�o exce��o
		//Obs: essa exce��o n�o ser� tratada pois, sem rei, o jogo est� quebrado. Vou deixar estourar o erro mesmo.
		throw new IllegalStateException("There is no "+ color + " king on the board.");
	}
	
	private boolean testCheck(Color color) {
		/*primeiro: usa o m�todo king para pegar o rei da cor especificada por argumento.
		Da� pega a posi��o de xadrez dele e transforma em posi��o de matriz*/
		Position kingPosition = king(color).getChessPosition().toPosition();
		//segundo: gerar uma lista com as pe�as inimigas.
		List<Piece> opponentPieces = piecesOnTheBoard
									 .stream()
									 .filter(x -> ((ChessPiece)x).getColor() == opponent(color))
									 .collect(Collectors.toList());
		//terceiro: verificar pe�a por pe�a inimiga se o poss�vel movimento atinge o meu rei.
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()] == true) {
				return true;
			}
		}
		//quarto: se esgotar o for, ou seja, percorri todas as pe�as e o rei n�o est� em check, retorna falso.
		return false;
		
	}
	
	private boolean testCheckMate(Color color) {
		//primeiro: se n�o est� em check, tamb�m n�o est� em check mate.
		if (testCheck(color) == false) {
			return false;
		}
		//segundo: criar uma lista com todas as pe�as no tabuleiro que s�o da cor vinda em argumento
		List<Piece> list = piecesOnTheBoard
						   .stream()
						   .filter(x -> ((ChessPiece)x).getColor() == color)
						   .collect(Collectors.toList());
		
		//terceiro: analisar todos os movimentos poss�veis de cada pe�a.
		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for(int i = 0; i<board.getRows(); i++) {
				for(int j=0; j<board.getColumns(); j++) {
					if(mat[i][j] == true) {	
						/*Agora vamos simular o deslocamento de cada pe�a para todas as suas posi��es.
						 *Assim que simularmos esse movimento, veremos se saimos da situa��o de Check.
						 *Se sair, continua o jogo. Sen�o, � checkMate*/
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i,j);
						Piece capturedPiece = makeMove(source, target); //fiz o movimento
						boolean testCheck = testCheck(color); //testei
						undoMove(source,target,capturedPiece); //desfiz o movimento
						//vou ver como est� o testCheck. Se estiver falso, a jogada tira o rei de check
						if (testCheck == false) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/*fun��o que recebe: 1 pe�a em coordenadas de batalha naval.
	faz: transforma em coordenadas de matriz e instancia a pe�a no board.*/
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	private void initialSetup() {
		
		placeNewPiece('e', 1, new King(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));


        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));        
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));        
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));        
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
        
	}
}
