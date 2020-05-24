
package com.org.telenor.easypaisa.assignment.logger;

/**
 * @author abdul
 * @version 1.0 ILogAdapter interface for logging adapter (will work as adapter)
 */
public interface ILogAdapter {

	public abstract void debug(String msg);

	public abstract void info(String msg);

	public abstract void warn(String msg);

	public abstract void error(String msg);

	public abstract void fatal(String msg);

	public abstract void audit(String msg);

}
