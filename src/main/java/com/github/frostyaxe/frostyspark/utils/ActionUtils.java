package com.github.frostyaxe.frostyspark.utils;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.frostyaxe.seldnd.DragAndDropAction;



/**
 * <b>Description:</b> This utility class handles the action to be performed on
 * the Web Pages with the help of selenium {@link Actions} and some other custom
 * dependencies.
 * 
 * @since 1.0
 * @author Abhishek Prajapati
 *
 */
@Component
public class ActionUtils 
{
	
	
	
	/*
	 *  Declaration/Initialization of class/instance variables.
	 */
	private Actions actions;
	private WebDriverUtils webDriverUtils;
	
	
	
	
	/**
	 * <b>Description:</b> This constructor accepts the {@link WebDriverUtils}
	 * instance and initializes the {@link Actions} class.
	 * 
	 * @param webDriverUtils : Instance of WebDriverUtils.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	@Autowired
	public ActionUtils(WebDriverUtils webDriverUtils)
	{
		this.webDriverUtils = webDriverUtils;
		this.actions = new Actions(this.webDriverUtils.getWebDriver());
	}
	
	/**
	 * <b>Description:</b> This method perform the drag and drop operation with the
	 * help of Jquery. Sometime dragAndDrop() method of Selenium Actions class does
	 * not work. Hence in order to overcome this issue, custom class has been
	 * created. This helper class allows the user to simulate the drag and drop
	 * action with the help of Jquery.
	 * 
	 * @param source : Source element locator
	 * @param target : Target Element Locator
	 * 
	 * @see <a href="https://github.com/frostyaxe/Frosty-Selenium-Drag-Drop">Selenium Drag Drop Simulator</a>
	 * @since
	 * @author Abhishek Prajapati
	 */
	public void dragAndDrop(String source, String target)
	{
		new DragAndDropAction().perform(this.webDriverUtils.getWebDriver(), source , target);
	}
	
	
	
	
	
	/**
	 * <b>Description:</b> This method returns the instance of the Selenium Action
	 * class that has been created by the ActionUtils.
	 * 
	 * @return Instance of the Selenium Actions class
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public Actions getActions()
	{
		return actions;
	}
	
	
	
	/**
	 * <b>Description:</b> This getter method returns the {@code Slider} slider
	 * instance based on the requirement.
	 * 
	 * @return Slider instance.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public SliderUtils getSliderUtils()
	{
		return new SliderUtils(this.webDriverUtils.getWebDriver());
	}
	
	
	
	public TextFieldUtils getTextFieldUtils()
	{
		return new TextFieldUtils();
	}
	
	
	
	
	/**
	 * <b>Description:</b> This class helps the user to perform the operations on
	 * the range sliders. User can perform the operations on range sliders by using
	 * attribute, attribute value and offset. Internally it uses
	 * {@link JavascriptExecutor} to change the attribute of the range slider
	 * component. User must retrieve the object of this {@link Slider} class with
	 * the help of {@link ActionUtils} instance.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 *
	 */
	public class SliderUtils
	{
		
		
		
		/*
		 *  Declaration/Initialization of class/instance variables
		 */
		private WebDriver driver;
		
		
		
		/**
		 * <b>Description:</b> This constructor of the Slider class accepts the selenium
		 * webdriver instance.
		 * 
		 * @param driver : WebDriver instance.
		 * 
		 * @since 1.0
		 * @author Abhishek Prajapati
		 */
		private SliderUtils(WebDriver driver)
		{
			this.driver = driver;
		}
		
		
		
		/**
		 * <b>Description:</b> This method performs the operation on the range slider
		 * component by changing the value of style attribute. </br>
		 * </br>
		 * <p>
		 * <i>Example of Range Slider DOM</i>
		 * </p>
		 * </br>
		 * {@code <div id="slider" class=
		 * "ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content">
		 * <span tabindex="0" class=
		 *"ui-slider-handle ui-corner-all ui-state-default" style="left: 0%;">
		 * </span></div>} </br>
		 * </br>
		 * In the above code {@code style="left: 0%";} attribute handles the operations
		 * on the range slider component.
		 * </br>
		 * </br>
		 * 
		 * @param element      : Range slider html component.
		 * @param attributeVal : Updated value of style attribute.
		 * 
		 * @since 1.0
		 * @author Abhishek Prajapati
		 */
		public void slideByAttributeValue(WebElement element, String attributeVal)
		{
			((JavascriptExecutor)driver).executeScript("arguments[0].value = \"" + attributeVal + "\";", element);
		}
		
		
		
		
		
		/**
		 * <b>Description:</b> This method performs the operation on slider component by
		 * updating the value of desired attribute.
		 * 
		 * @param element      : WebElement instance of slider component.
		 * @param attribute    : Attribute present in the slider component.
		 * @param attributeVal : Value of the attribute.
		 * 
		 * @since 1.0
		 * @author Abhishek Prajapati
		 */
		public void slideByAttribute(WebElement element, String attribute, String attributeVal)
		{
			String scriptJs = "arguments[0]."+attribute +" = \"" + attributeVal + "\";";
			((JavascriptExecutor)driver).executeScript(scriptJs, element);
		}
		
		
		
		
		/**
		 * <b>Description:</b> This method performs the operation on the specified
		 * slider component by using X and Y offset.
		 * 
		 * @param element : Desired slider WebElement.
		 * @param xOffset : X offset.
		 * @param yOffset : Y offset.
		 * 
		 * @since 1.0
		 * @author Abhishek Prajapati
		 */
		public void slideByOffset(WebElement element, int xOffset, int yOffset)
		{
			actions.clickAndHold(element).moveByOffset(xOffset, yOffset).release().build().perform();
		}
	}
	
	
	
	/**
	 * <b>Description:</b> This class handles the operation to be performed on the
	 * text fields.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 *
	 */
	public class TextFieldUtils
	{
		
		
		private TextFieldUtils()
		{
			
		}
		
		
		
		/**
		 * <b>Description:</b> This method sends the desired text to the text field.
		 * First, it clicks on the text field and then it sends the desired text into it
		 * in order to prevent the unwanted behavior of the text field.
		 * 
		 * @param element : Element in which desired text to be sent.
		 * @param text    : Text to be sent in the desired text field.
		 * 
		 * @since 1.0
		 * @author Abhishek Prajapati
		 */
		public void sendKeys(WebElement element, String text)
		{
			actions.click(element).sendKeys(text).build().perform();
		}
		
		
		
		/**
		 * <b>Description:</b> This method sends the desired text in to the specified
		 * text field with random delays while sending the characters of text. This
		 * method is mostly used when user wants to send the text to the auto suggestive
		 * drop down list.
		 * 
		 * @param element : Element in which desired text to be sent.
		 * @param text    : Text to be sent in the specified element.
		 * 
		 * @since 1.0
		 * @author Abhishek Prajapati
		 */
		public void sendKeysWithDelay(WebElement element, String text)
		{
			actions.click(element).perform();
			ScheduledExecutorService execService = Executors.newSingleThreadScheduledExecutor();
			text.chars().sequential().forEach( charVal ->
			{
				execService.schedule(new Runnable()
				{
					
					@Override
					public void run() 
					{
						element.sendKeys(String.valueOf((char)charVal));
					}
					
				}, 4, TimeUnit.SECONDS);
				try 
				{
					execService.awaitTermination(new Random().nextInt(3), TimeUnit.SECONDS);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			});
		}
		
		
		
		/**
		 * <b>Description:</b> This method highlights the element initially and then it
		 * sends the desired text into the specified web element. If user wants to send
		 * the characters with some delays in between then user can pass true in the
		 * withDelay parameter.
		 * 
		 * @param element          : Desired field in which text to be sent.
		 * @param text             : Text to be sent in the desired field.
		 * @param highlighterUtils : Instance of the {@code ElementHighlighterUtils}.
		 * @param withDelay        : Pass true if user wants to enable the delay else
		 *                         false.
		 * 
		 * @since 1.0
		 * @author Abhishek Prajapati
		 */
		public void highlightAndSendKeys(WebElement element, String text, ElementHighlighterUtils highlighterUtils, boolean withDelay)
		{
			WebElement highlightedElement = highlighterUtils.getElementHighlighter().getElement(element);

			if( withDelay )
			{
				sendKeysWithDelay(highlightedElement, text);
			}
			else
			{
				sendKeys(highlightedElement, text);
			}
			
		}
		
	}
	
}



