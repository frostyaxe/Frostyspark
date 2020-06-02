package com.github.frostyaxe.frostyspark.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.frostyaxe.frostylight.ElementHighlighter;

@Component
public class ElementHighlighterUtils 
{
	
	
	
	/*
	 *  Declaration/Initialization of class/instance variables.
	 */
	private ElementHighlighter elementHighLighter;
	
	
	
	/**
	 * <b>Description:</b> This constructor accepts the WebDriverUtils instance and
	 * then it initializes the object of ElementHighlighter.
	 * 
	 * @param webDriverUtils : Instance of the WebDriverUtils.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	@Autowired
	public ElementHighlighterUtils(WebDriverUtils webDriverUtils)
	{
		elementHighLighter = new ElementHighlighter(webDriverUtils.getThreadLocalWebDriver().get());
	}
	
	
	/**
	 * <b>Description:</b> This getter method returns the instance of
	 * {@link ElementHighlighter} based on the request.
	 * 
	 * @return Instance of the ElementHighlighter.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public ElementHighlighter getElementHighlighter()
	{
		return elementHighLighter;
	}
}
