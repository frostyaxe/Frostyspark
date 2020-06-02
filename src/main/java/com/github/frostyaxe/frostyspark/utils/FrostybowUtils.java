package com.github.frostyaxe.frostyspark.utils;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.github.frostyaxe.frostyspark.annotations.FrostyBow;


/**
 * <b>Description:</b> This class helps to read the details present in the
 * {@link FrostyBow} annotation. With the help of those details, FrostyBow
 * Application will get started.
 * 
 * @since 1.0
 * @author Abhishek Prajapati
 *
 */
@FrostyBow(host = "localhost", port = 8080)
public class FrostybowUtils
{
	public static void init(Object classObj) throws InterruptedException
	{
		
		Annotation frostybowAnnotation = classObj.getClass().getAnnotation(FrostyBow.class);
		String host = ((FrostyBow) frostybowAnnotation).host();
		int port = ((FrostyBow) frostybowAnnotation).port();

		List<String> executeCommand = new ArrayList<>();
		executeCommand.add("cmd.exe");
		executeCommand.add("/K");
		executeCommand.add("start");
		
		executeCommand.add("java");
		executeCommand.add("-jar");
		executeCommand.add(System.getProperty("user.dir") + "/src/main/resources/frostybow/frostybow-1.0-SNAPSHOT.jar");
		executeCommand.add("--server.host=" + host);
		executeCommand.add("--server.port=" + port);
		
		Thread frostybowDaemon = new Thread(new Runnable() 
		{
			
			@Override
			public void run() 
			{
				System.out.println("Inside run");
				try 
				{
					ProcessBuilder processBuilder = new ProcessBuilder(executeCommand);
				
					processBuilder.redirectError(Redirect.INHERIT);
					processBuilder.redirectInput(Redirect.INHERIT);
					processBuilder.redirectOutput(Redirect.INHERIT);
					
					Process process = processBuilder.start();
					
					System.out.println("After start");
					Thread.sleep(10000);
					try {
						process.waitFor();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				catch (IOException | InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		//frostybowDaemon.setDaemon(true);
		frostybowDaemon.start();
	
	}
	
	public static void main(String[] args)
	{
		try {
			init(new FrostybowUtils());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
