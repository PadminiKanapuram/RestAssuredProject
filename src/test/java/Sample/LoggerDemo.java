package Sample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerDemo {
	
	private static Logger logger =LogManager.getLogger(LoggerDemo.class);
	
	public static void main(String args[])
	{
		logger.info("This is information message");
		logger.debug("Debug");
		logger.fatal("fatal");
		logger.error("Error");
		logger.warn("Warn");
		
	}
	

}
