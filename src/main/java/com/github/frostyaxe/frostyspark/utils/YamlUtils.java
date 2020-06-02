package com.github.frostyaxe.frostyspark.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


/**
 * <b>Description:</b> This utility class allows the user to manipulate the yaml
 * file content. This class helps to perform desired operation on the yaml file
 * content such as conversion of yaml into json and many more.
 * 
 * @author Abhishek Prajapati
 *
 */
public class YamlUtils 
{

	/*
	 *  Declaration/initialization of class/instance variables.
	 */
	private FileReader yamlFileReader = null;
	private ObjectMapper yamlObjMapper = null;
	
	
	
	/*
	 *  Initialization Block
	 */
	{
		yamlObjMapper = new ObjectMapper(new YAMLFactory());
	}
	
	
	
	/**
	 * <b>Description:</b> This constructor accepts the Yaml File path.
	 * 
	 * @param yamlFilePath : Path of the Yaml file.
	 * 
	 * @author Abhishek Prajapati
	 * @throws FileNotFoundException 
	 */
	public YamlUtils(String yamlFilePath) throws FileNotFoundException
	{
		this(new File(yamlFilePath));
	}
	
	
	/**
	 * <b>Description:</b> This constructor accepts the YAML file object.
	 * 
	 * @param yamlFile : Object of the YAML file.
	 * @author Abhishek Prajapati
	 * @throws FileNotFoundException 
	 */
	public YamlUtils(File yamlFile) throws FileNotFoundException
	{
		
			yamlFileReader = new FileReader(yamlFile);
	
	}

	
	
	/**
	 * <b>Description:</b> This method converts the YAML file content into JSON
	 * object.
	 * 
	 * @author Abhishek Prajapati
	 * @return Content of the Yaml file in JSON Object.
	 * @throws JsonParseException   : Exception type for parsing problems, used when
	 *                              non-well-formed content (content that does not
	 *                              conform to JSON syntax as per specification) is
	 *                              encountered.
	 * 
	 * @throws JsonMappingException : Checked exception used to signal fatal
	 *                              problems with mapping of content, distinct from
	 *                              low-level I/O problems (signaled using simple
	 *                              java.io.IOExceptions) or data encoding/decoding
	 *                              problems (signaled with
	 *                              com.fasterxml.jackson.core.JsonParseException,
	 *                              com.fasterxml.jackson.core.JsonGenerationException).
	 * 
	 *                              One additional feature is the ability to denote
	 *                              relevant path of references (during
	 *                              serialization/deserialization) to help in
	 *                              troubleshooting.
	 * 
	 * @throws IOException          :
	 * 
	 *                              Signals that an I/O exception of some sort has
	 *                              occurred. This class is the general class of
	 *                              exceptions produced by failed or interrupted I/O
	 *                              operations.
	 * 
	 */
	public JSONObject convertYamlToJsonObj() throws JsonParseException, JsonMappingException, IOException
	{
		return yamlObjMapper.readValue(yamlFileReader, JSONObject.class);
	}
	
	
	
	/**
	 * <b>Description:</b> This getter method returns the file reader object of the
	 * YAML file.
	 * 
	 * @return FileReader object of the specified YAML file.
	 * @author Abhishek Prajapati
	 */
	public FileReader getFileReader()
	{
		return yamlFileReader;
	}
	
	
	
	public static void main(String[] args)
	{
		
		YamlUtils yamlUtils = null;
		try
		{
			yamlUtils  =  new YamlUtils("frostyspark.yml");
			System.out.println( yamlUtils.convertYamlToJsonObj() );
			yamlUtils.getFileReader().close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
}
