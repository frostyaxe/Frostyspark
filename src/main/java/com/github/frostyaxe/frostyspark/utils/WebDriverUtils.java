package com.github.frostyaxe.frostyspark.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <b>Description:</b> This utils class initializes the selenium web driver based on the
 * requirement of the user.
 * 
 * @author Abhishek Prajapati
 *
 */
@Component
public class WebDriverUtils implements DisposableBean
{
	
	/*
	 *  Declaration/Initialization of class/instance variables
	 */
	private BrowserCapabilities browserCapabilities;
	private ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
	
	
	 
	/*
	 *  Initialization Block
	 */
	@Autowired
	public WebDriverUtils( BrowserCapabilities browserCapabilities)
	{
		this.browserCapabilities = browserCapabilities;
		initDriver();
	}
	
	/**
	 * <b>Description:</b> This method sets the FrostySpark
	 * {@link BrowserCapabilities} object.
	 * 
	 * @param browserCapabilities : Object of the BrowserCapabilities.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	
	public void setBrowserCapabilities(BrowserCapabilities browserCapabilities)
	{
		this.browserCapabilities = browserCapabilities;
	}
	
	
	
	/**
	 * <b>Description:</b> This method initializes the selenium web driver instance.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public void initDriver()
	{
		if( browserCapabilities.getBrowser().equals("chrome") )
		{
			webDriver.set(new ChromeDriver((ChromeOptions)this.browserCapabilities.getCapabilities()));
		}
		else if( browserCapabilities.getBrowser().equals("ie") )
		{
			webDriver.set( new InternetExplorerDriver((InternetExplorerOptions)this.browserCapabilities.getCapabilities()) ); 
		}
		else if( browserCapabilities.getBrowser().equals("firefox") )
		{
			webDriver.set( new FirefoxDriver((FirefoxOptions)this.browserCapabilities.getCapabilities()) );
		}
		else
		{
			throw new RuntimeException("This browser [ " + this.browserCapabilities.getBrowser() + " ] is not supported!");
		}
	}
	
	
	
	
	/**
	 * <b>Description:</b> This getter method returns the current selenium WebDriver
	 * instance.
	 * 
	 * @author Abhishek Prajapati
	 * @since 1.0
	 * @return Current WebDriver instance.
	 */
	public WebDriver getWebDriver()
	{
		return webDriver.get();
	}
	
	
	public ThreadLocal<WebDriver> getThreadLocalWebDriver()
	{
		return webDriver;
	}
	
	public void display()
	{
		System.out.println("In WebDriver Utils");
	}

	@Override
	public void destroy() throws Exception {
		
		System.out.println("Removing thread local instance of the WebDriver");
		this.webDriver.remove();
		System.out.println("Thread local instance is removed from the WebDriverUtils");
		
	}

}
