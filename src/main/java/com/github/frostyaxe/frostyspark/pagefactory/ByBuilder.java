package com.github.frostyaxe.frostyspark.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.support.How;

public class ByBuilder 
{
	
	private String using = "";
	private String locator = "";
	
	public ByBuilder(String using, String locator)
	{
		this.using = using;
		this.locator = locator;
	}
	
	public By buildBy()
	{
		switch(using.toLowerCase())
		{
			case "id":
				return How.ID.buildBy(locator);
			case "className":
				return How.CLASS_NAME.buildBy(locator);
			case "css":
				return How.CSS.buildBy(locator);
			case "tagName":
				return How.TAG_NAME.buildBy(locator);
			case "linkText":
				return How.LINK_TEXT.buildBy(locator);
			case "partialLinkText":
				return How.PARTIAL_LINK_TEXT.buildBy(locator);
			case "xpath":
				return How.XPATH.buildBy(locator);
			case "idOrName":
				return How.ID_OR_NAME.buildBy(locator);
			case "name":
				return How.NAME.buildBy(locator);
			default:
				 throw new IllegalArgumentException(String.format("You must specify at most one location strategy. Found: %s",using ));
						 
			
		}
			
		
	}
	
	
}
