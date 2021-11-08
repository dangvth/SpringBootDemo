/**
 * 
 */
package com.tma.SpringBootDemo.utils;

import org.apache.log4j.Logger;

/**
 * @author dangv
 *
 */
public class LogUtil {

	/**
	 * Log debug
	 * 
	 * @param message the message to log
	 */
	public static void logDebug(Logger logger, String message) {
		logger.debug(message);
	}

	/**
	 * Log error
	 * 
	 * @param message the message to log
	 */
	public static void logError(Logger logger, String message) {
		logger.error(message);
	}
}
