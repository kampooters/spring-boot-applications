
package com.org.telenor.easypaisa.assignment.logger;

/**
 * @author Abdul
 * @version 1.0 ILogManager interface exposing the methods for log manager
 */
public interface ILogManager {

	/**
	 * This Method Output a debug-level message Method ARGUMENTS:
	 * 
	 * @param o
	 *            :The object in whose context this is being output
	 * @param function
	 *            : being executed method
	 * @param msg
	 *            :pecific message.
	 */
	public abstract void debug(String msg);


	/**
	 * Output a error-level message
	 */
	public abstract void audit(String msg);

	/**
	 * Output a error-level message
	 */
	public abstract void error(String msg);


	/**
	 * Output a fatal-level message.
	 * 
	 * @param o
	 *            The object in whose context this is being output (may be null).
	 * @param function
	 *            The function (method) that is being executed.
	 * @param msg
	 *            The specific message.
	 */
	public abstract void fatal(String msg);

	/**
	 * Output a info-level message.
	 * 
	 * @param o
	 *            The object in whose context this is being output (may be null).
	 * @param function
	 *            The function (method) that is being executed.
	 * @param msg
	 *            The specific message.
	 */
	public abstract void info(String msg);


	public abstract boolean registerLoggerAPI(ILogAdaptee loggerAdapte);

	/**
	 * For debugging when we don't have an interactive debugger; this can be called
	 * anywhere to give information about where we are.
	 * 
	 * @param msg
	 *            Message to output with the stack trace.
	 */
	public abstract void stackTrace(String msg);

	/**
	 * Print a stack trace on stderr.
	 * 
	 * @param e
	 *            The exceptions, whose stack trace is to be output.
	 */
	public abstract void stackTrace(Throwable e);


	/**
	 * Output a warning-level message.
	 * 
	 * @param o
	 *            The object in whose context this is being output (may be null).
	 * @param function
	 *            The function (method) that is being executed.
	 * @param msg
	 *            The specific message.
	 */
	public abstract void warn(String msg);

}