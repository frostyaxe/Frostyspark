package com.github.frostyaxe.frostyspark.pagefactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import com.github.frostyaxe.frostyspark.annotations.FrostyConfig;


public class FrostyFactory
{
	
	private static Map<String,Object> frostyConfig = new HashMap<>();
	
	  public static void initElements(WebDriver driver, Object page) {
	    final WebDriver driverRef = driver;
	    initElements(new FrostyElementLocatorFactory(driverRef), page);
	  }

	  
	  
	  public static void initElements(ElementLocatorFactory factory, Object page) {
	    final ElementLocatorFactory factoryRef = factory;
	    initElements(new FrostyFieldDecorator(factoryRef), page);
	  }
	
	  

	  public static void initElements(FieldDecorator decorator, Object page) {
		createConfigMap(page);
	    Class<?> proxyIn = page.getClass();
	    while (proxyIn != Object.class) {
	      proxyFields(decorator, page, proxyIn);
	      proxyIn = proxyIn.getSuperclass();
	    }
	  }

	  private static void proxyFields(FieldDecorator decorator, Object page, Class<?> proxyIn) {
	    Field[] fields = proxyIn.getDeclaredFields();
	    for (Field field : fields) {
	      Object value = decorator.decorate(page.getClass().getClassLoader(), field);
	      if (value != null) {
	        try {
	          field.setAccessible(true);
	          field.set(page, value);
	        } catch (IllegalAccessException e) {
	          throw new RuntimeException(e);
	        }
	      }
	    }
	  }
	
	
	private static void createConfigMap(Object page)
	{
		FrostyConfig[] config = page.getClass().getDeclaredAnnotationsByType(FrostyConfig.class);
		if( config.length <= 0  )
		{
			System.err.println("FrostyConfig annotation is required!");
		}
		else
		{
			frostyConfig.put( "host", config[config.length-1].host() );
			frostyConfig.put( "port", config[config.length-1].port() );
		}
		
	}
	
	
	
	public static Map<String, Object> getConfigMap()
	{
		return frostyConfig;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	
		
	public static void initElements(Object page)
	{
		

		Field[] fields = page.getClass().getDeclaredFields();
		
		Arrays.asList(fields).forEach(field ->{
				
			field.setAccessible(true);
			
			    if (field.isAnnotationPresent(SearchWith.class)) 
			    {
			    	
			    	
			    	if( field.getType().equals(String.class) )
			    	{
				    	SearchWith fAnno = field.getAnnotation(SearchWith.class);
				    	
				        
				        	field.setAccessible(true);
				        	try 
				        	{
								field.set(page, "Abhishek");
							} 
				        	catch (IllegalArgumentException | IllegalAccessException e) 
				        	{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    	}
			    	else
			    	{
			    		System.err.println( field.getName() + " is not declared with the type as WebElement!");
			    	}
			    }
			
			
			
		});
		
	}*/
	
}
