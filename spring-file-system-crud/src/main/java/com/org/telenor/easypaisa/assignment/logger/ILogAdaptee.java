
package com.org.telenor.easypaisa.assignment.logger;

/**
 * @author abdul
 * @version 1.0
 * ILogAdaptee interface for log-adaptee. All adaptees will implement this interface.
 * This interface is plugIn for any Logger API as ADAPTEE Any client who uses
 * logger API will Implement this interface to PlugIn any custom logger API.
 */
public interface ILogAdaptee {
	/**
	 * Method to log audit messages
	 * 
	 * @param msg String
	 */
	public abstract void audit(String msg);

	/**
	 * Method to log message at debug level
	 * 
	 * @param msg String
	 */
	public abstract void debug(String msg);

	/**
	 * Method to log message at warn level
	 * 
	 * @param msg String
	 */
	public abstract void warn(String msg);

	/**
	 * Method to log message at error level
	 * 
	 * @param msg String
	 */
	public abstract void error(String msg);

	/**
	 * Method to log message at fatal level
	 * 
	 * @param msg String
	 */
	public abstract void fatal(String msg);
	/**
	 * Method to log message at fatal level
	 * 
	 * @param msg String
	 */
	public abstract void info(String msg);
}
