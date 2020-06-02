package com.github.frostyaxe.frostyspark.pagefactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;

import com.github.frostyaxe.frostyspark.annotations.SearchWith;



public class FrostyFieldDecorator implements FieldDecorator{

	 protected ElementLocatorFactory factory;

	  public FrostyFieldDecorator(ElementLocatorFactory factoryRef) {
	    this.factory = factoryRef;
	  }

	  public Object decorate(ClassLoader loader, Field field) {
	    if (!(WebElement.class.isAssignableFrom(field.getType())
	          || isDecoratableList(field))) {
	      return null;
	    }

	    ElementLocator locator = factory.createLocator(field);
	    if (locator == null) {
	      return null;
	    }

	    if (WebElement.class.isAssignableFrom(field.getType())) {
	      return proxyForLocator(loader, locator);
	    } else if (List.class.isAssignableFrom(field.getType())) {
	      return proxyForListLocator(loader, locator);
	    } else {
	      return null;
	    }
	  }

	  protected boolean isDecoratableList(Field field) {
	    if (!List.class.isAssignableFrom(field.getType())) {
	      return false;
	    }

	    // Type erasure in Java isn't complete. Attempt to discover the generic
	    // type of the list.
	    Type genericType = field.getGenericType();
	    if (!(genericType instanceof ParameterizedType)) {
	      return false;
	    }

	    Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];

	    if (!WebElement.class.equals(listType)) {
	      return false;
	    }

	    if (field.getAnnotation(SearchWith.class) == null) {
	      return false;
	    }

	    return true;
	  }

	  protected WebElement proxyForLocator(ClassLoader loader, ElementLocator locator) {
	    InvocationHandler handler = new LocatingElementHandler(locator);

	    WebElement proxy;
	    proxy = (WebElement) Proxy.newProxyInstance(
	        loader, new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);
	    return proxy;
	  }

	  @SuppressWarnings("unchecked")
	  protected List<WebElement> proxyForListLocator(ClassLoader loader, ElementLocator locator) {
	    InvocationHandler handler = new LocatingElementListHandler(locator);

	    List<WebElement> proxy;
	    proxy = (List<WebElement>) Proxy.newProxyInstance(
	        loader, new Class[]{List.class}, handler);
	    return proxy;
	  }


}
