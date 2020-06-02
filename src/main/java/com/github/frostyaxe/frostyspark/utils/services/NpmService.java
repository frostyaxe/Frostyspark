package com.github.frostyaxe.frostyspark.utils.services;

import java.io.InputStream;

public interface NpmService 
{
	Process run(String... args);
	boolean verifyNpm();
	void readNpmOutput(InputStream inputStream);
	Process getProcess();
	void displayInfo();
}
