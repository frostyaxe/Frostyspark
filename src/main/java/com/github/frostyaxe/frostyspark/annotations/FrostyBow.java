package com.github.frostyaxe.frostyspark.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>Description:</b> This annotation helps the user to enable the FrostyBow in
 * order to retrieve the data related to the WebElement. With the help of this
 * annotation, user will be able to provide host details as well port on which
 * the application will get started.
 * 
 * @since 1.0
 * @author Abhishek Prajapati
 *
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FrostyBow 
{
	String host() default "localhost";
	int port() default 8080;
}
