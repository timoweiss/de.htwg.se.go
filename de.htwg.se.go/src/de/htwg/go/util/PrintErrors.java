package de.htwg.go.util;

import java.util.logging.Logger;

public final class PrintErrors {
	
	private PrintErrors() {}
	private static Logger logger = Logger.getLogger("de.htwg.go");
	private static String newLine = System.getProperty("line.separator");
	
	
	public static void printErrorMessage(int errorcode) {
		switch (errorcode) {
		case 0:
			logger.info(newLine + "ErrorCode " + errorcode + ": Missing Cell Error");
			break;
		}
	}
}
