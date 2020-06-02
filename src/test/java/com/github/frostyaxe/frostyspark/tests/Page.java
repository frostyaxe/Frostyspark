package com.github.frostyaxe.frostyspark.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.github.frostyaxe.frostyspark.annotations.FrostyConfig;
import com.github.frostyaxe.frostyspark.annotations.SearchWith;
import com.github.frostyaxe.frostyspark.pagefactory.FrostyFactory;

@FrostyConfig(host = "localhost", port = 8080)
public class Page {
	
	private WebDriver driver;
	
	@SearchWith(application = "google", name = "searchBox", page = "googleSearch" )
	private WebElement searchBox;
	
	public Page(WebDriver driver)
	{
		this.driver = driver;
		FrostyFactory.initElements(this.driver, this);
		
	}
	
	public WebElement getSearchBox()
	{
		return searchBox;
	}
	
	
}
