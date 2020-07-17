package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		//instanciar a partida
		ChessMatch chessMatch = new ChessMatch();
		//instanciar a lista de pe�as que ser�o capturadas
		List<ChessPiece> captured = new ArrayList<>();

		while (chessMatch.getCheckMate() == false) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);

				System.out.println();

				// O usu�rio diz qual � a origem da pe�a
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				//receber a matriz de possibilidades e gerar sobrecarga no printBoard
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				
				System.out.println();

				// O usu�rio diz qual � o destino da pe�a
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
			} 
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine(); //aguardar o usu�rio digitar Enter
			} 
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine(); //aguardar o usu�rio digitar Enter
			}

		}
		//A partir daqui j� houve a constata��o do checkMate
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
		
		
		
		
	}

}
