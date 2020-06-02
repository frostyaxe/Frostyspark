package com.github.frostyaxe.frostyspark.utils;

import java.io.File;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;


/**
 * <b>Description:</b> This utility class handles the operations to be performed
 * on the Properties file. With the help of apache commons properties
 * configuration2, this utility class has been created.
 * 
 * @since 1.0
 * @author Abhishek Prajapati
 *
 */
public class PropertiesUtils 
{

	
	/*
	 *  Declaration/Initialization of class/instance variables.
	 */
	private String filePath;
	private Configurations configuration;
	private Configuration propertiesConfig;
	
	
	
	
	/**
	 * <b>Description:</b> This constructor accepts the path of the properties file
	 * and assigns the parameter value to the current class instance variable.
	 * 
	 * @param propertiesFilePath : Path of the properties file.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public PropertiesUtils( String propertiesFilePath )
	{
		this.filePath = propertiesFilePath;
	}
	
	
	
	/**
	 * <b>Description:</b> This method reads the properties from the properties file
	 * and loads the data into the PropertiesConfiguration. It returns the current
	 * class instance updated with the properties configuration that user can use
	 * with other methods to perform desired operation on the properties file.
	 * 
	 * @return Instance of the current class updated with the properties file
	 *         configuration.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public PropertiesUtils initConfig()
	{
		
		configuration = new Configurations();
		
		File props = new File(this.filePath);
		if( props.exists() )
		{
		  try 
		  {
			  propertiesConfig = configuration.properties(props);
		  } 
		  catch (ConfigurationException e)
		  {
			  e.printStackTrace();
		  }
		}
		else
		{
			throw new RuntimeException("File does not exist.");
		}
		  
		  return this;
		  
	}
	
	
	
	
	
	/**
	 * <b>Description:</b> This getter method returns the
	 * {@link Configuration} instance to the user. User can use this
	 * instance to retrieve the loaded data from the properties file.
	 * 
	 * @return Configuration instance.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public Configuration getPropertiesConfiguration()
	{
		return propertiesConfig;
	}
	
	
}
