package com.github.frostyaxe.frostyspark.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import com.github.frostyaxe.frostyspark.utils.services.NpmService;



/**
 * <b>Description:</b> This class helps the user to download the artifacts with
 * the help of npm. Normally this class is used to download drivers with the
 * help of NPM.
 * 
 * @author Abhishek Prajapati
 *
 */

@Component
public class NpmUtils implements NpmService
{
	
	
	
	Process process = null;
	
	/**
	 * <b>Description:</b> This method verifies whether the current system is
	 * windows based or Linux based.
	 * 
	 * @return If current os is windows based then it returns true else it will
	 *         return false.
	 * 
	 * @author Abhishek Prajapati
	 */
	private boolean isWindows()
	{
	    return System.getProperty("os.name").toLowerCase().contains("win");	
	}

	private String npm = isWindows() ? "npm.cmd" : "npm";

	
	
	/**
	 * <b>Description:</b> This method runs the npm command as specified by the
	 * user.
	 * 
	 * @param directoryPath : Process builder's working directory
	 * @param args          : Arguments containing npm command and arguments
	 * 
	 * @author Abhishek Prajapati
	 */
	@Override
	public Process run(String... args) 
	{

	
	    try 
	    {
	    	ProcessBuilder npmProcessBuilder =  new ProcessBuilder(convertedArgs(args));
	    	npmProcessBuilder.redirectErrorStream(true);
	    	process = npmProcessBuilder
			        .start();
	    	
		} 
	    catch (IOException e)
	    {
			e.printStackTrace();
		} 
	    
	 
	    
	    return process;
	}
	
	
	
	/**
	 * <b>Description:</b> This method converts the varargs into the list. It
	 * prepends the npm executable file as well in the given list.
	 * 
	 * @param args : Arguments passed by user with npm arguments and command.
	 *
	 * @return List of commands with npm.
	 */
	private List<String> convertedArgs(String... args)
	{
		List<String> argumentsList = new ArrayList<String>(Arrays.asList(args));
	
		argumentsList.add(0, npm);
		
		return argumentsList;
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method verifies whether npm is installed in the
	 * current system or not.
	 * 
	 * @return True if npm is installed in the current system else false.
	 * 
	 * @author Abhishek Prajapati
	 */
	@Override
	public boolean verifyNpm()
	{
		
		
		BufferedReader reader = new BufferedReader(new InputStreamReader( run("-v").getInputStream() ));
		
		try 
		{
			if(reader.readLine().matches("\\d{1,3}.\\d{1,3}.\\d{1,3}"))
			{
				return true;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				reader.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		
		return false;
	}
	
	
	
	/**
	 * <b>Description:</b> This method reads the npm output based on the input
	 * stream of the process provided by the user.
	 * 
	 * @param inputStream : Process's InputStream
	 * 
	 * @author Abhishek Prajapati
	 */
	@Override
	public void readNpmOutput(InputStream inputStream)
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		String line = null;
		try 
		{
			while ((line = reader.readLine()) != null) 
			{
			   System.out.println(line);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				reader.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * <b>Description:</b> This method returns the process created by the
	 * {@link #run()} method.
	 * 
	 * @return Process created by the run method.
	 * 
	 * @author Abhishek Prajapati
	 */
	@Override
	public Process getProcess()
	{
		return process;
	}
	
	@Override
	public void displayInfo()
	{
		System.out.println("NpmUtils is working properly.");
	}
	
}
