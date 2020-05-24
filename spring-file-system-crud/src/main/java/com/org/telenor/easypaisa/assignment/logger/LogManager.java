
package com.org.telenor.easypaisa.assignment.logger;

/**
 * The class manages logging for the collector. It uses a LoggerAdapter for the
 * actual logging. This class acts as TARGET for logManager component
 */
public class LogManager implements ILogManager {
	private LoggerAdapter handler = null;
	public boolean registerLoggerAPI(ILogAdaptee loggerAdapte) {

		if (loggerAdapte != null) {
			if (handler == null) {
				handler = new LoggerAdapter(loggerAdapte);
			}
			return true;
		} else
			return false;
	}
	
	
	public LogManager(String className){
		if(className == null || className.isEmpty()) {
			throw new NullPointerException("className is empty");
		}
//		if(this.handler == null) {
//			this.handler = new LoggerAdapter(new ConsoleAdaptee(className));
//		}
		if(this.handler == null) {
			this.handler = new LoggerAdapter(new SL4JAdaptee(className));
		}
	}
	public void debug(String msg) {
		if (handler != null)
			handler.debug( msg);
	}

	/**
	 * Output a error-level message
	 */
	public void audit(String msg) {
		if (handler != null)
			handler.audit(msg);
	}


	/**
	 * Output a error-level message
	 */
	public void error( String msg) {
		if (handler != null)
			handler.error(msg);
	}

	/**
	 * Output a fatal-level message.
	 * 
	 * @param className
	 *            The name of the class where the message is being output from.
	 * @param function
	 *            The function (method) that is being executed.
	 * @param The
	 *            specific message.
	 */
	public void fatal(String msg) {
		if (handler != null)
			handler.fatal(msg);
	}


	/**
	 * Output a info-level message.
	 * 
	 * @param className
	 *            The name of the class where the message is being output from.
	 * @param function
	 *            The function (method) that is being executed.
	 * @param The
	 *            specific message.
	 */
	public void info(String msg) {
		if (handler != null)
			handler.info( msg);
	}

	/**
	 * Print a line and flush
	 * 
	 * @param line
	 *            The line to print
	 */
	public void println(String msg) {
		System.out.println(msg);
	}

	/**
	 * For debugging when we don't have an interactive debugger; this can be called
	 * anywhere to give information about where we are.
	 * 
	 * @param msg
	 *            Message to output with the stack trace.
	 */
	public void stackTrace(String msg) {
		Exception e = new Exception(msg);
		System.err.println("Forcing stack trace: " + msg);
		stackTrace(e);
	}

	/**
	 * Print a stack trace on stderr.
	 * 
	 * @param e
	 *            The exceptions, whose stack trace is to be output.
	 */
	public void stackTrace(Throwable e) {
		e.printStackTrace(System.err);
		System.err.flush();
	}

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
	public void warn(String msg) {
		if (handler != null)
			handler.warn(msg);
	}


}