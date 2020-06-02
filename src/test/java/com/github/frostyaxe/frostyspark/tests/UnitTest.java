package com.github.frostyaxe.frostyspark.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.github.frostyaxe.FrostyApplication;
import com.github.frostyaxe.frostyspark.annotations.FrostyBow;
import com.github.frostyaxe.frostyspark.annotations.SystemProperties;
import com.github.frostyaxe.frostyspark.controllers.FrostyController;
import com.github.frostyaxe.frostyspark.support.SystemPropertiesLoader;



@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FrostyApplication.class)
@SystemProperties( path = "dumps/system.props")
//@FrostyBow(host = "localhost", port = 8089)
public class UnitTest extends FrostyController
{
	private Page googlePage ;
	
	@Before
	public void loadConfigs()
	{
		SystemPropertiesLoader.load(this);
	//	googlePage = new Page(getWebDriverUtils().getWebDriver());
	}
	
	
/*	
	@Test
	public void test() throws InterruptedException
	{
		getWebDriverUtils().getThreadLocalWebDriver().get().get("https://www.google.com");
		getActionUtils().getTextFieldUtils().highlightAndSendKeys(googlePage.getSearchBox(), "Frostyaxe", getElementHighlighterUtils(), true);
		Thread.sleep(10000);
		getWebDriverUtils().getThreadLocalWebDriver().get().close();
		
	}*/

		
		
		@Test
		public void test1() throws InterruptedException
		{
			getWebDriverUtils().getThreadLocalWebDriver().get().get("https://codepen.io/DaniRC/pen/PowwwjZ");
			List<String> data = new ArrayList<String>();
			
			data.add("5");
			data.add("4");
			data.add("3");
			data.add("5");
			int counter = 0;
			
			getWebDriverUtils().getThreadLocalWebDriver().get().switchTo().frame("result");
			
			List<WebElement> elements = getWebDriverUtils().getThreadLocalWebDriver().get().findElement(By.id("form")).findElements(By.xpath("input[@type='text']"));
			
			for(WebElement element : elements)
			{
				element.sendKeys(data.get(counter));
				counter++;
			}
			Thread.sleep(10000);
			getWebDriverUtils().getThreadLocalWebDriver().get().close();
			
		}
}
