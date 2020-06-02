package com.github.frostyaxe.frostyspark.pagefactory;

import java.lang.reflect.Field;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;


public class FrostyElementLocatorFactory implements ElementLocatorFactory {
	  private final SearchContext searchContext;

	  public FrostyElementLocatorFactory(SearchContext searchContext) {
	    this.searchContext = searchContext;
	  }

	  public ElementLocator createLocator(Field field) {
	    return new FrostyElementLocator(searchContext, field);
	  }
}