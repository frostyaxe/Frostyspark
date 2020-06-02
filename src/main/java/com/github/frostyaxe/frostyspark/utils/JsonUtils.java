package com.github.frostyaxe.frostyspark.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.github.frostyaxe.frostyspark.exceptions.FrostyJsonException;
import com.github.frostyaxe.frostyspark.exceptions.NotJsonObjectException;


/**
 * <b>Description:</b> This class handles the operations performed on the JSON
 * content.
 * 
 * @author Abhishek Prajapati
 *
 */
@Component
public class JsonUtils
{
	
	/*
	 *  Declaration/Initialization of class/instance variables
	 */
	private JSONParser jsonParser = null;
	
	
	/*
	 *  Initialization Block
	 */
	{
		jsonParser = new JSONParser();
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method converts the specified string into
	 * JSONObject.
	 * 
	 * @param jsonString : Json Content in string.
	 * @return Object of the Json content which is passed as a string.
	 * @throws NotJsonObjectException : If the type of passed object does not match
	 *                                to JSONObject then this exception is thrown.
	 * @since 1.0                               
	 * @author Abhishek Prajapati
	 */
	public JSONObject convertStringIntoJsonObject(String jsonString) throws NotJsonObjectException 
	{
		Object contentObj = null;
		try 
		{
			contentObj = jsonParser.parse(jsonString);
		} catch (ParseException e) 
		{
			throw new RuntimeException(e);
		}
		
		try 
		{
			if( !isJsonArray(contentObj) )
				return (JSONObject)contentObj;
			else
				throw new NotJsonObjectException("Passed object is not the type of JSONObject");
			
		} catch (FrostyJsonException e) 
		{
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	/**
	 * <b>Description:</b> This method converts the specified string into
	 * JSONArray.
	 * 
	 * @param jsonString : Json Content in string.
	 * @return Array of the Json content which is passed as a string.
	 * @throws NotJsonObjectException : If the type of passed object does not match
	 *                                to JSONArray then this exception is thrown.
	 * @since 1.0                               
	 * @author Abhishek Prajapati
	 */
	public JSONArray convertStringIntoJsonArray(String jsonString) throws NotJsonObjectException 
	{
		Object contentObj = null;
		try 
		{
			contentObj = jsonParser.parse(jsonString);
		} catch (ParseException e) 
		{
			throw new RuntimeException(e);
		}
		
		try 
		{
			if( isJsonArray(contentObj) )
				return (JSONArray)contentObj;
			else
				throw new NotJsonObjectException("Passed object is not the type of JSONArray");
			
		} catch (FrostyJsonException e) 
		{
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	/**
	 * <b>Description:</b> This method verifies whether the specified object is of
	 * type JSONArray or JSONObject.
	 * 
	 * @param content : Content of JSON in Object type.
	 * @return true, if the type of the specified object is JSONArray else it
	 *         returns false.
	 * @throws FrostyJsonException : This exception is thrown when the object is
	 *                             neither JSONArray type nor JSONObject type.
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public boolean isJsonArray(Object content) throws FrostyJsonException
	{
		if( content instanceof org.json.simple.JSONArray)
		{
			return true;
		}
		else if( content instanceof org.json.simple.JSONObject )
		{
			return false;
		}
		else
		{
			throw new FrostyJsonException("The given object is neither instance of JSONObject nor JSONArray.");
		}
	}
	
	
}
