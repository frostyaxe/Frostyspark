package com.github.frostyaxe.frostyspark.pagefactory;

import java.lang.reflect.Field;
import java.util.Map;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import com.github.frostyaxe.frostyspark.annotations.SearchWith;
import com.github.frostyaxe.frostyspark.exceptions.NotJsonObjectException;
import com.github.frostyaxe.frostyspark.utils.JsonUtils;
import com.github.frostyaxe.frostyspark.utils.RestUtils;


public class FrostyAnnotations 
{
	private Field field;

	  /**
	   * @param field expected to be an element in a Page Object
	   */
	  public FrostyAnnotations(Field field) {
	    this.field = field;
	  }


	  /**
	   * {@inheritDoc}
	   *
	   * Looks for one of {@link org.openqa.selenium.support.FindBy},
	   * {@link org.openqa.selenium.support.FindBys} or
	   * {@link org.openqa.selenium.support.FindAll} field annotations. In case
	   * no annotations provided for field, uses field name as 'id' or 'name'.
	   * @throws IllegalArgumentException when more than one annotation on a field provided
	   */
	  public By buildBy() {
		  	
		 SearchWith[] request =  field.getAnnotationsByType(SearchWith.class);
		 
		 String path =  "/get/" + request[request.length-1].application() + "/" +  request[request.length-1].page() + "/" + request[request.length-1].name() + "/getLocator" ;
		 
		  
		  JSONObject jsonContent = null;
		  
		  Map<String,Object> configMap =  FrostyFactory.getConfigMap();
		  	
		  try 
		  {
			  jsonContent = new JsonUtils().convertStringIntoJsonObject( new RestUtils().getResponse(String.valueOf(configMap.get("host")),String.valueOf(configMap.get("port")), path) );
		  } 
		  catch ( NotJsonObjectException e) 
		  {
			e.printStackTrace();
		  }
		  
		  String usingString = String.valueOf( jsonContent.get("using") );
		  String locatorString = String.valueOf( jsonContent.get("locator"));
		  try 
		  {
			validateJsonResponse(usingString,locatorString);
		  } 
		  catch (Exception e)
		  {
			e.printStackTrace();
		  }
	    return new ByBuilder( usingString, locatorString ).buildBy();
	  }

	  protected Field getField() {
	    return field;
	  }

	  
	  private void validateJsonResponse(String usingString, String locatorString) throws Exception
	  {
		  if( usingString.equals("") && usingString.equals("null") && usingString.equals(null) && usingString.equals("") && usingString.equals("null") && usingString.equals(null)  )
		  {
			  throw new Exception("Response from the server is invalid");
		  }
	  }
	
}
