package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void clearScreen() {
		System.out.println("\033[H\033[02J");
		System.out.flush();
	}

	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 t h8.");
		}
	}

	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turn: " + chessMatch.getTurn());

		//testar se n�o h� checkmate. Se n�o houver, pode continuar o jogo.
		if (!chessMatch.getCheckMate()) {
			System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
			if(chessMatch.getCheck()) {
				System.out.println("CHECK!");
			}
		}
		else {
			System.out.println("CHECKMATE!!!");
			System.out.println("Winner " + chessMatch.getCurrentPlayer());
		}

	}

	public static void printBoard(ChessPiece[][] pieces) {
		// aqui vamos imprimir todas as pe�as
		for (int i = 0; i < pieces.length; i++) { // matriz quadrada
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		// aqui vamos imprimir todas as pe�as
		for (int i = 0; i < pieces.length; i++) { // matriz quadrada
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	// metodo para imprimir uma unica pe�a
	private static void printPiece(ChessPiece piece, boolean background) {
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}

		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if(piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			}
			else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}

		}
		System.out.print(" ");
	}

	private static void printCapturedPieces(List <ChessPiece> captured) {
		//primeiro: recebemos a lista de todos os capturados. Criamos duas listas filtradas por cor.
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		System.out.println("Captured pieces: ");
		System.out.print("White: ");
		//segundo: quero imprimir a lista toda em branco:
		System.out.print(ANSI_WHITE);
		//terceiro: para imprimir a lista, usamos um macete do java
		//Jeitinho padr�o para imprimir um array de valores
		System.out.println(Arrays.toString(white.toArray()));

		System.out.print(ANSI_RESET);

		//terceiro: resetar a cor de impress�o
		System.out.print(ANSI_YELLOW);
		System.out.print("Black: ");
		//quarto: fazer a mesma coisa para a lista preta
		System.out.println(Arrays.toString(black.toArray()));

		System.out.print(ANSI_RESET);
		
		
	}








}
