package com.github.frostyaxe;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.stereotype.Component;
import com.github.frostyaxe.frostyspark.utils.YamlUtils;


@Component
public class FrostysparkData 
{
	private YamlUtils yamlUtils = null;
	private String frostysparkData = null;
	
	
	{
		init();
	}
	
	private FrostysparkData init() 
	{
		try 
		{
			yamlUtils = new YamlUtils("frostyspark.yml");
			
			try 
			{
				frostysparkData = yamlUtils.convertYamlToJsonObj().toJSONString();
				return this; 
			}
			catch (IOException e) 
			{
				System.err.println(e.getMessage());
			}
			
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Loading defaults" );
		}
		
		return null;
		
	}
	
	
	public String getFrostyData()
	{
		return this.frostysparkData;
	}
	
}
