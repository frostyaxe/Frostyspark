package com.github.frostyaxe.frostyspark.tests;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.assertj.core.util.Arrays;

public class Demo {
	
	public static void main(String[] args)
	{
		int[] arr = new int[]{3, 4, 5, 5, 5, 6, 2, 2};
		
		IntStream.of(arr).filter( data -> Collections.frequency(Arrays.asList(arr), data) >1
				).distinct().forEach( data -> { System.out.println(data );} );
	}
	

}
