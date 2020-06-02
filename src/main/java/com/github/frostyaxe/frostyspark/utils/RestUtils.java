package com.github.frostyaxe.frostyspark.utils;

import javax.ws.rs.ServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestUtils 
{
	public String getResponse(String host, String port,String path)
	{
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://" +host +":" + port + "/" + path);
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.get();
		
		if ( response.getStatus() != 200 )
		{
			throw new ServerErrorException(response.getStatus());
		}
		 
		return response.readEntity(String.class);
		     
		
	}
	
	public static void main(String args[])
	{
		
	}
	
	
}
