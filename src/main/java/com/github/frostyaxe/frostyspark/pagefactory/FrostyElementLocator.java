package com.github.frostyaxe.frostyspark.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import java.lang.reflect.Field;
import java.util.List;


public class FrostyElementLocator implements ElementLocator {
private final SearchContext searchContext;

private final By by;


/**
* Creates a new element locator.
*
* @param searchContext The context to use when finding the element
* @param field The field on the Page Object that will hold the located value
*/
public FrostyElementLocator(SearchContext searchContext, Field field) {
 this(searchContext, new FrostyAnnotations(field));
}

/**
* Use this constructor in order to process custom annotaions.
*
* @param searchContext The context to use when finding the element
* @param annotations AbstractAnnotations class implementation
*/
public FrostyElementLocator(SearchContext searchContext, FrostyAnnotations annotations) {
 this.searchContext = searchContext;
this.by = annotations.buildBy();
}

/**
* Find the element.
*/
public WebElement findElement() {

 WebElement element = searchContext.findElement(by);


 return element;
}

/**
* Find the element list.
*/
public List<WebElement> findElements() {

 List<WebElement> elements = searchContext.findElements(by);


 return elements;
}


@Override
public String toString() {
 return this.getClass().getSimpleName() + " '" + by + "'";
}
}
