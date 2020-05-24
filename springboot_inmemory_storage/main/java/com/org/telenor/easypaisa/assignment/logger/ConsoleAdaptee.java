
package com.org.telenor.easypaisa.assignment.logger;

/**
 * @author abdul
 * @version 1.0
 * ConsoleAdaptee is default console adaptee for logger
 */
public class ConsoleAdaptee implements ILogAdaptee{
	private String className = "";
	public ConsoleAdaptee(String className) {
		this.className = className;
	}
	/* (non-Javadoc)
	 * @see com.org.service.logger.ILogAdaptee#audit(java.lang.String)
	 */
	@Override
	public void audit(String msg) {
		System.out.println(msg);
		
	}

	/* (non-Javadoc)
	 * @see com.org.service.logger.ILogAdaptee#debug(java.lang.String)
	 */
	@Override
	public void debug(String msg) {
		System.out.println(msg);
		
	}

	/* (non-Javadoc)
	 * @see com.org.service.logger.ILogAdaptee#warn(java.lang.String)
	 */
	@Override
	public void warn(String msg) {
		System.out.println(msg);
		
	}

	/* (non-Javadoc)
	 * @see com.org.service.logger.ILogAdaptee#error(java.lang.String)
	 */
	@Override
	public void error(String msg) {
		System.out.println(msg);
		
	}

	/* (non-Javadoc)
	 * @see com.org.service.logger.ILogAdaptee#fatal(java.lang.String)
	 */
	@Override
	public void fatal(String msg) {
		System.out.println(msg);
		
	}
	/* (non-Javadoc)
	 * @see com.org.service.logger.ILogAdaptee#info(java.lang.String)
	 */
	@Override
	public void info(String msg) {
		System.out.println(msg);
		
	}

}
