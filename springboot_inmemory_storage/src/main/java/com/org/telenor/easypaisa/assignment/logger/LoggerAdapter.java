
package com.org.telenor.easypaisa.assignment.logger;

/**
 * This class provides logging facility.This class acts as ADAPTER
 * in LogManager component
 */
public class LoggerAdapter implements ILogAdapter{
	private ILogAdaptee loggerAdaptee = null;

	/**
	 * This constructor registers ADAPTEE class which holds the methods wrapping
	 * LOGGER API
	 * 
	 * @param loggerAdapte
	 *            : Implementation of ILoggerAPI_ADAPTEE
	 * @param linePrefix
	 *            : Line prefix for logging message
	 */
	public LoggerAdapter(ILogAdaptee loggerAdapte) {
		if (loggerAdapte != null) {
			this.loggerAdaptee = loggerAdapte;
		}
	}


	public void debug( String msg) {
		if (this.loggerAdaptee != null) {
			this.loggerAdaptee.debug(msg);
		}
	}

	public void info(String msg) {
		if (this.loggerAdaptee != null) {
			this.loggerAdaptee.info(msg);
		}
	}

	public void warn( String msg) {
		if (this.loggerAdaptee != null) {
			this.loggerAdaptee.warn( msg);
		}
	}

	public void error(String msg) {
		if (this.loggerAdaptee != null) {
			this.loggerAdaptee.error(msg);
		}
	}

	public void fatal( String msg) {
		if (this.loggerAdaptee!= null) {
			this.loggerAdaptee.fatal(msg);
		}
	}

	@Override
	public void audit(String msg) {
		// TODO Auto-generated method stub
		
	}


}