package com.github.frostyaxe.frostyspark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import com.github.frostyaxe.FrostyApplication;
import com.github.frostyaxe.FrostysparkData;
import com.github.frostyaxe.frostyspark.utils.ActionUtils;
import com.github.frostyaxe.frostyspark.utils.BrowserCapabilities;
import com.github.frostyaxe.frostyspark.utils.ElementHighlighterUtils;
import com.github.frostyaxe.frostyspark.utils.JavascriptExecutorUtils;
import com.github.frostyaxe.frostyspark.utils.JsonUtils;
import com.github.frostyaxe.frostyspark.utils.NotificationUtils;
import com.github.frostyaxe.frostyspark.utils.NpmUtils;
import com.github.frostyaxe.frostyspark.utils.WebDriverUtils;
import com.github.frostyaxe.frostyspark.utils.services.NpmService;

/**
 * <b>Description:</b> This controller file acts as a base controller file that
 * user must use to use the frosty framework
 * 
 * @author Abhishek Prajapati
 *
 */
@ContextConfiguration( classes = FrostyApplication.class )

public class FrostyController 
{
	
	
	/*
	 *  Declaration/Initialization of class/instance variables.
	 */
	private NpmService npmService;
	private FrostysparkData frostyData;
	private BrowserCapabilities browserCaps;
	private JavascriptExecutorUtils jsExecUtils;
	private WebDriverUtils webDriverUtils;
	private ElementHighlighterUtils elementHighlighterUtils;
	private JsonUtils jsonUtils;
	private NotificationUtils notificationUtils;
	private ActionUtils actionUtils;
	


	@Autowired
	public void setActionUtils(ActionUtils actionUtils) {
		this.actionUtils = actionUtils;
	}


	@Autowired
	public void setNotificationUtils(NotificationUtils notificationUtils) {
		this.notificationUtils = notificationUtils;
	}


	@Autowired
	public void setJsonUtils(JsonUtils jsonUtils) {
		this.jsonUtils = jsonUtils;
	}


	@Autowired
	public void setElementHighlighterUtils(ElementHighlighterUtils elementHighlighterUtils) 
	{
		this.elementHighlighterUtils = elementHighlighterUtils;
	}


	@Autowired
	public void setFrostyData( FrostysparkData frostyData )
	{
		this.frostyData = frostyData;
	}
	
	
	@Autowired
	public void setWebDriverUtils( WebDriverUtils webDriverUtils )
	{
		this.webDriverUtils = webDriverUtils;
	}
	
	
	@Autowired
	public void setBrowserCaps( BrowserCapabilities browserCaps )
	{
		this.browserCaps = browserCaps;
	}

	
	
	@Autowired
	public void setNpmService(NpmUtils npmService)
	{
		this.npmService = npmService;
	}
	
	@Autowired
	public void setJsExecUtils(JavascriptExecutorUtils jsExecUtils) {
		this.jsExecUtils = jsExecUtils;
	}


	
	/**
	 * <b>Description:</b> 
	 * @return
	 */
	public FrostysparkData getFrostyData() {
		return frostyData;
	}


	public BrowserCapabilities getBrowserCaps() {
	
		return browserCaps;
	}

	
	public ActionUtils getActionUtils() {
		return actionUtils;
	}

	public WebDriverUtils getWebDriverUtils() {
		return webDriverUtils;
	}

	

	
	public JavascriptExecutorUtils getJsExecUtils() {
		return jsExecUtils;
	}


	
	public NpmService getNpmService()
	{
		return this.npmService;
	}
	
	
	public ElementHighlighterUtils getElementHighlighterUtils() {
		return elementHighlighterUtils;
	}

	public JsonUtils getJsonUtils() {
		return jsonUtils;
	}


	public NotificationUtils getNotificationUtils() {
		return notificationUtils;
	}
	
	
	
}
