package com.github.frostyaxe.frostyspark.support;

import java.lang.annotation.Annotation;

import org.apache.commons.configuration2.Configuration;
import com.github.frostyaxe.frostyspark.annotations.SystemProperties;
import com.github.frostyaxe.frostyspark.utils.PropertiesUtils;



/**
 * <b>Description:</b> This class loads the properties file data into the system
 * properties. It retrieves the file path from the {@link SystemProperties}
 * annotation's path variable.
 * 
 * @author Abhishek Prajapati
 * @since 1.0
 *
 */
public class SystemPropertiesLoader
{

	
	
	/**
	 * <b>Description:</b> This method loads the properties from the properties file.
	 * into the System.
	 * 
	 * @param classObj : Object of the Class.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public static void load(Object classObj)
	{
		Annotation annotation = classObj.getClass().getAnnotation(SystemProperties.class);
		PropertiesUtils propertiesUtils = new PropertiesUtils(((SystemProperties)annotation).path());
		Configuration propsConfig = propertiesUtils.initConfig().getPropertiesConfiguration();
		propsConfig.getKeys().forEachRemaining( key -> {
			System.setProperty(key, String.valueOf( propsConfig.getProperty(key) ));
		} );
	}
	
	
}
