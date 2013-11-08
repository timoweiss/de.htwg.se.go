package de.htwg.go.util;

public class PrintErrors {
	
	private PrintErrors() {}
	
	public static void printErrorMessage(int errorcode) {
		switch (errorcode) {
		case 0:
			System.err.println("ErrorCode " + errorcode + ": Missing Cell Error"); 
			break;
		}
	}
}
