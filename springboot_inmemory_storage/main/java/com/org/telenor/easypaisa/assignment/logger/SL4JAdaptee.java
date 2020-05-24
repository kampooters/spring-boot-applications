

package com.org.telenor.easypaisa.assignment.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author abdul
 * @version 1.0 ConsoleAdaptee is default console adaptee for logger
 */
public class SL4JAdaptee implements ILogAdaptee {
	private final Logger logger;

	public SL4JAdaptee(String className) {
		this.logger = LoggerFactory.getLogger(className);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.org.service.logger.ILogAdaptee#audit(java.lang.String)
	 */
	@Override
	public void audit(String msg) {
		logger.info(msg);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.org.service.logger.ILogAdaptee#debug(java.lang.String)
	 */
	@Override
	public void debug(String msg) {
		logger.debug(msg);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.org.service.logger.ILogAdaptee#warn(java.lang.String)
	 */
	@Override
	public void warn(String msg) {
		logger.warn(msg);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.org.service.logger.ILogAdaptee#error(java.lang.String)
	 */
	@Override
	public void error(String msg) {
		logger.error(msg);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.org.service.logger.ILogAdaptee#fatal(java.lang.String)
	 */
	@Override
	public void fatal(String msg) {
		logger.error(msg);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.org.service.logger.ILogAdaptee#info(java.lang.String)
	 */
	@Override
	public void info(String msg) {
		logger.info(msg);

	}

}
