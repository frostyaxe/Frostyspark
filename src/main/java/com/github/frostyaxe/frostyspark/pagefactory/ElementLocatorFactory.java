package com.github.frostyaxe.frostyspark.pagefactory;

import java.lang.reflect.Field;

import org.openqa.selenium.support.pagefactory.ElementLocator;

public interface ElementLocatorFactory 
{
	ElementLocator createLocator(Field field);
}
