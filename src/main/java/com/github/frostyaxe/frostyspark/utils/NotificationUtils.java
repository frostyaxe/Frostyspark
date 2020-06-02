package com.github.frostyaxe.frostyspark.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <b>Description:</b> This utility class helps to display notifications on the
 * web page while testing the web application. User can display 4 kinds of
 * message that are shown below.
 * <ul>
 * <li>SUCCESS</li>
 * <li>ERROR</li>
 * <li>INFO</li>
 * <li>WARNING</li>
 * </ul>
 * 
 * 
 * User can use either default notification styles to display notification on
 * the web page or user can builder {@link NotificationBuilder} to generate
 * custom notification styles. Before starting the notification configuration,
 * user must have to inject the Jquery as well as notify js files in order to
 * use the notification in the web page. In order to inject JS files, user must
 * have to execute {@code #injectJquery()} method after the creation of the
 * {@code NotificationUtils} instance.
 * 
 * @since 1.0
 * @author Abhishek Prajapati
 *
 */
@Component
public class NotificationUtils 
{
	
	
	
	
	/*
	 *  Declaration/Initialization of class/instance variables.
	 */
	private JavascriptExecutor jsExecutor;
	
	
	
	
	
	/**
	 * <b>Description:</b> This constructor accepts the {@code WebDriver} type
	 * instance and then casts the object with JavascriptExecutor.
	 * 
	 * @param driver : Instance of the selenium webdriver
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	@Autowired
	public NotificationUtils(JavascriptExecutorUtils jsExecUtils)
	{
		
			this.jsExecutor = jsExecUtils.getJavascriptExecutor();
	
	}
	
	
	
	/**
	 * <b>Description:</b> This method injects the Jquery and Notify JS files in the
	 * current application DOM with the help of JavacriptExecutor. It is mandatory
	 * to execute this method before you call any method in order to display the
	 * notification on the web page.
	 * 
	 * @return Update instance of the current class with the JQuery inject into the
	 *         application
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public NotificationUtils injectJquery()
	{
		
		String injectionScript = "if (!window.jQuery) {"
				+ "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
				+ "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js';"
				+ "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}";

		this.jsExecutor.executeScript(injectionScript);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.jsExecutor.executeScript("$.getScript('https://cdnjs.cloudflare.com/ajax/libs/notify/0.4.2/notify.js')");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method displays the success notification on the web
	 * page with the desired text in it.
	 * 
	 * @param message : Message to be displayed in the notification box.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public void displaySuccess(String message)
	{
		this.jsExecutor.executeScript("$.notify(\"+ message + \", \"success\");");
	}
	
	
	
	/**
	 * <b>Description:</b> This method displays the info notification on the web
	 * page with the desired text in it.
	 * 
	 * @param message : Message to be displayed in the notification box.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public void displayInfo(String message)
	{
		this.jsExecutor.executeScript("$.notify(\"+ message + \", \"info\");");
	}
	
	
	
	
	
	/**
	 * <b>Description:</b> This method displays the warning notification on the web
	 * page with the desired text in it.
	 * 
	 * @param message : Message to be displayed in the notification box.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public void displayWarning(String message)
	{
		this.jsExecutor.executeScript("$.notify(\"+ message + \", \"warn\");");
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method displays the error notification on the web
	 * page with the desired text in it.
	 * 
	 * @param message : Message to be displayed in the notification box.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public void displayError(String message)
	{
		this.jsExecutor.executeScript("$.notify(\"+ message + \", \"error\");");
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method returns the {@link NotificationBuilder}
	 * instance. With the help of this instance, user can customize the
	 * configuration of notification box.
	 * 
	 * @return Instance of the NotificationBuilder.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public NotificationBuilder getBuilder()
	{
		return new NotificationBuilder(this.jsExecutor);
	}
	
	
	
	
	/**
	 * <b>Description:</b> This builder class helps the user to build custom
	 * notification box. This builder class can help the user to create 4 different
	 * notification boxes and set the notification box at the desired position.
	 * 
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 *
	 */
	public class NotificationBuilder
	{
		
		
		
		/*
		 *  Declaration/Initialization of class/instance variables
		 */
		private String position = "top right";
		private String type = "info";
		private StringBuilder jsBuilder = new StringBuilder();
		private String text = "";
		private JavascriptExecutor jsExecutor;
		private Integer delay = 2000;
		
		
		
		/*
		 *  Initialization block for the code execution before the instance is created.
		 */
		{
			jsBuilder.append("$.notify(");
		}
		
		
		
		/**
		 * <b>Description:</b> This constructor accepts the selenium JavascriptExecutor
		 * instance.
		 * 
		 * @param jsExecutor : Selenium JavascriptExecutor instance.
		 * 
		 * @since 1.0
		 * @author Abhishek Prajapati
		 */
		public NotificationBuilder(JavascriptExecutor jsExecutor)
		{
			this.jsExecutor = jsExecutor;
		}
		
		
		
		/**
		 * <b>Description:</b> This method sets the message text. This message text will
		 * get display in the notification box.
		 * 
		 * @param text : Message to be displayed in the notification box.
		 * 
		 * @return Instance of the current class updated with the message text.
		 * 
		 * @since 1.0
		 * @author Abhishek Prajapati
		 */
		public NotificationBuilder setText(String text)
		{
			this.text = text;
			return this;
		}
		
		
		
		
		
		
		/**
		 * <b>Description:</b> This method sets the position of a notification box on
		 * the web page.
		 * 
		 * @param position : Position of a notification box on the web page.
		 * 
		 * @return Updated instance of the current class with the desired position of
		 *         the notification box.
		 * 
		 * @since 1.0
		 * @author Abhishek Prajapati
		 */
		public NotificationBuilder setPosition(NotificationPosition position)
		{
			this.position = position.getPosition();
			return this;
		}
		
		
		
		/**
		 * <b>Description:</b> This method sets the level of the message.
		 * 
		 * @param level : Level of the messasge.
		 * @return Updated instance of the current class with the message level.
		 * 
		 * 
		 * @since 1.0
		 * @author Abhishek Prajapati
		 */
		public NotificationBuilder setLevel( NotificationType type)
		{
			this.type = type.getLevel();
			return this;
		}
		
		
		
		
		/**
		 * <b>Description:</b> This method builds the notification box with the desired
		 * message based on the configuration done by the user. This method must be
		 * called at the end of the chaining once all desired configuration is done.
		 * 
		 * @since 1.0
		 * @author Abhishek Prajapati
		 */
		public void build()
		{
			
			jsBuilder.append( "\"" + this.text + "\",{ className:'" + this.type + "', globalPosition:'" + this.position + "', delay:" + this.delay.intValue() + "});");
			this.jsExecutor.executeScript(jsBuilder.toString());
		}
		
		 
	}
	
	
	
	
	/**
	 * <b>Description:</b> This enum  is used with the
	 * {@link NotificationBuilder} in order to provide the position value while
	 * setting up the desired position of the Notification box.
	 * 
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 *
	 */
	public enum NotificationPosition
	{
		TOP_RIGHT("top right"), TOP_LEFT("top left"), TOP_CENTER("top center"), BOTTOM_RIGHT("bottom right"),BOTTOM_LEFT("bottom left"),BOTTOM_CENTER("bottom center");
		
		private String position;
		
		NotificationPosition(String position)
		{
			this.position = position;
		}
		
		public String getPosition()
		{
			return this.position;
		}
	}
	
	
	
	
	/**
	 * <b>Description:</b> This enum is used with the {@link NotificationBuilder}
	 * class in order to provide desired message level.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 *
	 */
	public enum NotificationType
	{
		ERROR("error"), SUCCESS("success"),WARNING("warn"),INFO("info");
		
		private String level;
		
		NotificationType(String level)
		{
			this.level = level;
		}
		
		public String getLevel()
		{
			return this.level;
		}
	}
	
	
}
