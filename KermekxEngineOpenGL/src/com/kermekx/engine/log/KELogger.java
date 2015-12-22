package com.kermekx.engine.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KELogger {
	
	/**
	 * Instance du logger
	 */
	private final static KELogger INSTANCE = new KELogger();
	
	/**
	 * logger
	 */
	private final Logger logger;
	
	/**
	 * cr�er le logger
	 */
	private KELogger() {
		logger = Logger.getLogger(KELogger.class.getName());
		logger.setLevel(Level.ALL);
	}
	
	/**
	 * log une info
	 * @param info message � afficher
	 */
	public static void logInfo(String info) {
		INSTANCE.logger.log(Level.INFO, info);
	}

	/**
	 * log une exception
	 * @param e exception � affacher
	 */
	public static void logException(Exception e) {
		INSTANCE.logger.log(Level.SEVERE, e.getMessage(), e);
	}
}
