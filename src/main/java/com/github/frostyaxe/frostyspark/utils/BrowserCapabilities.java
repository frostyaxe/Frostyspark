package com.github.frostyaxe.frostyspark.utils;

import org.json.simple.JSONObject;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.frostyaxe.FrostysparkData;
import com.github.frostyaxe.frostyspark.exceptions.NotJsonObjectException;




/**
 * <b>Description:</b> This class sets the browser capabilities in Selenium
 * based on the selection of the browser. Currently this framework supports
 * three web browsers: chrome, ie and firefox. If browser name is invalid or
 * user does not specify desired browser in the frostyspark.yml then it will by
 * default choose the chrome browser for the execution of test scripts.
 * 
 * @since 1.0
 * @author Abhishek Prajapati
 */
@Component
public class BrowserCapabilities 
{
	
	
	/*
	 *  Declaration/Initialization of class/instance variables
	 */
	private String browser = "chrome";
	private MutableCapabilities capabilities;

	
	@Autowired
	public BrowserCapabilities(FrostysparkData frostydata, JsonUtils jsonUtils)
	{
		init(frostydata,  jsonUtils);
	}
	
	
	/**
	 * <b>Description:</b> This method configures the browser and the capabilities
	 * based on the requirement of the user. By default it configures chrome browser
	 * for the execution of test scripts.
	 * 
	 * @param frostydata : FrostySpark data retrieved from the YAML file.
	 * @param jsonUtils  : FrostySpark Json Utils object.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	private void init(FrostysparkData frostydata, JsonUtils jsonUtils)
	{
		String frostysparkData = frostydata.getFrostyData();
		
		if( null != frostysparkData | !frostysparkData.isEmpty()  )
		{
			try
			{
				JSONObject jsonObject = jsonUtils.convertStringIntoJsonObject(frostysparkData);
				setBrowser( String.valueOf( ((JSONObject)((JSONObject)jsonObject.get("frostyspark")).get("webdriver")).get("browser")));
				if( !((JSONObject)((JSONObject)jsonObject.get("frostyspark")).get("webdriver")).containsKey("useRemoteDriver") )
				{
					String path = String.valueOf( ((JSONObject)((JSONObject)jsonObject.get("frostyspark")).get("webdriver")).get("path"));
					if( this.browser.equalsIgnoreCase("ie") )
					{
						System.setProperty("webdriver.ie.driver", path);
					}
					else if( this.browser.equalsIgnoreCase("firefox") )
					{
						System.setProperty("webdriver.gecko.driver", path);
					}
					else
					{
						System.setProperty("webdriver.chrome.driver", path);
					}
					
				}
			} 
			catch (NotJsonObjectException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Loading default configuration...");
		}
	}
	
	
	
	/**
	 * <b>Description:</b> This method helps the user to set the desired browser.
	 * Valid values are chrome, ie and firefox. Once the user uses this method to
	 * set the desired browser automatically default capabilities will get loaded.
	 * If user wants to set the desired capabilities then user needs to call the
	 * {@link #setCapabilities(Capabilities)}.
	 * 
	 * @param browser : Name of the browser for execution test scripts.
	 * @return 
	 */
	public void setBrowser(String browser)
	{
		this.browser = browser;
		setDefaultCapabilities();
	}
	
	
	
	public String getBrowser()
	{
		return this.browser;
	}
	
	
	
	/**
	 * <b>Description:</b> This method sets the default capabilities based on the
	 * selection of web browser.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	private void setDefaultCapabilities()
	{
		if( this.browser.equalsIgnoreCase("firefox"))
		{
			capabilities = new FirefoxOptions(DesiredCapabilities.firefox());
		}
		else if( this.browser.equalsIgnoreCase("ie"))
		{
			capabilities = new InternetExplorerOptions(DesiredCapabilities.internetExplorer());
		}
		else
		{
			capabilities = new ChromeOptions();
		}
		
	}
	
	
	
	/**
	 * <b>Description:</b> This getter method returns the capabilities.
	 * 
	 * @return Capabilities
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public MutableCapabilities getCapabilities()
	{
		return capabilities;
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method sets the capabilities that need to be set in
	 * the web browser before the execution of test scripts.
	 * 
	 * @param capabilities : Desired Capabilities.
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public void setCapabilities(MutableCapabilities capabilities)
	{
		this.capabilities.merge(capabilities);
	}
	
	
	
	/**
	 * 
	 */
	public void display()
	{
		System.out.println("BrowserCaps: It is working! [ Browser ] " + this.browser );
	}
	
	
}
