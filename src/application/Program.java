package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();

		while (true) {
			try {
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces());

				System.out.println();

				// O usuário diz qual é a origem da peça
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);

				System.out.println();

				// O usuário diz qual é o destino da peça
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			} 
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine(); //aguardar o usuário digitar Enter
			} 
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine(); //aguardar o usuário digitar Enter
			}

		}
	}

}
