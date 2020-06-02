package com.github.frostyaxe.frostyspark.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <b>Description:</b> This utility class handles the configuration of
 * JavascriptExecutor. Internally it uses the WebDriverUtils to retrieve the
 * WebDriver and then it casts the webdriver instance with JavascriptExecutor.
 * 
 * @author Abhishek Prajapati
 * @since 1.0
 */
@Component
public class JavascriptExecutorUtils 
{
	
	

	/*
	 * Declaration/Initialization of class/instance variables.
	 */
	private WebDriverUtils webDriverUtils;
	private JavascriptExecutor jsExecutor;
	
	
	

	/**
	 * <b>Description:</b>: This constructor accepts the {@link WebDriverUtils}
	 * instance with the help of spring autowiring.
	 * 
	 * @param webDriverUtils : Instanace of the WebDriverUtils class.
	 * @since 1.0
	 * @author Abhishek Prajapati
	 * 
	 */
	@Autowired
	public JavascriptExecutorUtils(WebDriverUtils webDriverUtils) {
		this.webDriverUtils = webDriverUtils;
		jsExecutor = (JavascriptExecutor) this.webDriverUtils.getThreadLocalWebDriver().get();
	}
	
	
	

	/**
	 * <b>Description:</b> This getter method returns the instance of webdriver
	 * casted with the JavascriptExecutor.
	 * 
	 * @return Instance of the webdriver casted with the JavascriptExecutor.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public JavascriptExecutor getJavascriptExecutor() {
		return jsExecutor;
	}

}
