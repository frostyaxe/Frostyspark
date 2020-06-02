package com.github.frostyaxe.frostyspark.utils;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


/**
 * <b>Description:</b> This class logs the user message.
 * 
 * @author Abhishek Prajapati
 *
 */
public class OutputUtils 
{

	/**
	 *  Declaration/Initialization of class/instance variables.
	 */
	private ThreadLocal<Logger> logger = null;
	private boolean isDefaultPattern = true;
	
	
	
	/**
	 * <b>Description:</b> This constructor initializes the logger.
	 * 
	 * @param clazz : Name of the class.
	 * 
	 * @author Abhishek Prajapati
	 */
	
	public OutputUtils(final Class<?> clazz)
	{
		logger = new ThreadLocal<Logger>() { 
			
			 @Override public Logger initialValue() {
	            return Logger.getLogger(clazz);
	        }
		};
		
		if( isDefaultPattern )
			logger.get().addAppender(new ConsoleAppender(new PatternLayout("%d{yyyy-MM-dd}-%t-%x-%-5p-%-10c:%m%n")));
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method display the info message.
	 * 
	 * @param message : Message to be displayed in string.
	 * 
	 * @author Abhishek Prajapati
	 */
	public void displayInfoMessage(String message)
	{
		logger.get().info(message);
	}
	
	
	
	/**
	 * <b>Description:</b> This method display the error message.
	 * 
	 * @param message : Message to be displayed in string.
	 * 
	 * @author Abhishek Prajapati
	 */
	public void displayErrorMessage(String message)
	{
		logger.get().error(message);
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method display the fatal message.
	 * 
	 * @param message : Message to be displayed in string.
	 * 
	 * @author Abhishek Prajapati
	 */
	public void displayFatalMessage(String message)
	{
		logger.get().fatal(message);
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method display the debug message.
	 * 
	 * @param message : Message to be displayed in string.
	 * 
	 * @author Abhishek Prajapati
	 */
	public void displayDebugMessage(String message)
	{
		logger.get().debug(message);
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method display the trace message.
	 * 
	 * @param message : Message to be displayed in string.
	 * 
	 * @author Abhishek Prajapati
	 */
	public void displayTraceMessage(String message)
	{
		logger.get().trace(message);
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method returns the instance of logger.
	 * 
	 * @return log4j logger's instance
	 * 
	 * @author Abhishek Prajapati
	 */
	public Logger getLogger()
	{
		return logger.get();
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method sets the instance of logger.
	 * 
	 * @author Abhishek Prajapati
	 */
	public void setLogger( Logger logger )
	{
		this.logger.set(logger);
	}
}
