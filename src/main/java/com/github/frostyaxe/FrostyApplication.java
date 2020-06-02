package com.github.frostyaxe;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@ComponentScan( basePackages = {"com.github.frostyaxe.frostyspark.utils","com.github.frostyaxe.frostyspark.utils.services","com.github.frostyaxe"}  )
public class FrostyApplication {

	static ConfigurableApplicationContext appContext;
	
    public static void main(String[] args)
    {
    	
    	ThreadLocal<SpringApplication> app = new ThreadLocal<SpringApplication>();
    	app.get();
		appContext = SpringApplication.run(FrostyApplication.class, args);
    	
    
    }
  
}
