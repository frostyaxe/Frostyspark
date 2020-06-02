package com.github.frostyaxe.frostyspark.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.assertj.core.util.Arrays;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;


/**
 * <b>Description:</b> This utils class reads the csv file and performs the
 * operations as per the requirement of the user. User can even change the
 * default separator based on the requirement.
 * 
 * @author Abhishek Prajapati
 * @since 1.0
 */
public class CsvUtils 
{
	
	
	
	/*
	 *  Declaration/Initialization of class/instance variables.
	 */
	private FileReader csvFileReader;
	private char separator = ',';
	private CSVReader csvReader;
	private CSVReaderBuilder readerBuilder;
	private int counter = 0;
	private File csvFile;
	
	
	
	/**
	 * <b>Description:</b> This constructor accepts the csvfile path and helps the
	 * user to access the methods present in the current class to perform some
	 * operations on the specified CSV file.
	 * 
	 * @param csvFilePath : Path of the CSV file.
	 * @throws FileNotFoundException : If file does not exist in the current system
	 *                               then this exception is thrown.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public CsvUtils( String csvFilePath) 
	{
		this.csvFile = new File(csvFilePath);
	}
	
	
	
	
	
	
	/**
	 * <b> Description:</b> This constructor accepts the object of the CSV file and
	 * internally it calls another constructor that creates FileReader object of the
	 * specified file. It throws {@link FileNotFoundException} exception if the
	 * specified file does not exist at the given location.
	 * 
	 * @param csvFileObj : File Object of CSV file.
	 * @throws FileNotFoundException : It throws {@link FileNotFoundException}
	 *                               exception when file does not exist at the given
	 *                               location.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public CsvUtils( File csvFileObj) 
	{
		this.csvFile = csvFileObj;
	}
	
	
	
	
	
	
	
	
	/**
	 * <b>Description:</b> This method overrides the default separator with the
	 * separator passed by the user as an argument to this method. By default, this
	 * utility class uses comma(,) as a separator.
	 * 
	 * @param separator : Desired separator.
	 * @return Object of the current class with the user defined separator.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public CsvUtils setSeparator(char separator)
	{
		this.separator = separator;
		return this;
	}
	
	
	
	
	
	/**
	 * <b>Description:</b> This method initializes the CSV reader with either
	 * default separator or user defined separator.
	 * 
	 * @return The state of the current class with the updated {@link CSVReader} Object.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 * @throws FileNotFoundException 
	 */
	public CsvUtils initCsvReader(Boolean skipHeader) throws FileNotFoundException
	{
		this.csvFileReader = new FileReader(csvFile);
		readerBuilder = new CSVReaderBuilder(this.csvFileReader);
		
		if( skipHeader )
		{
			csvReader = readerBuilder.withSkipLines(1).withCSVParser(new CSVParserBuilder().withSeparator(separator).build())
				     .build();
		}
		else
		{
			csvReader = readerBuilder.withCSVParser(new CSVParserBuilder().withSeparator(separator).build())
				     .build();
		}
		return this;
	}
	
	
	
	
	
	
	/**
	 * <b>Description:</b> This method allows the user to use custom
	 * {@link CSVReaderBuilder} instance in order to build a {@link CSVReader}
	 * object. If you want to use the default implementation to reader the CSV file
	 * then you can call {@link #initCsvReader(Optional)} method. If you want to
	 * skip the header then pass the argument value as true.
	 * 
	 * @param readerBuilder : Custom {@link CSVReaderBuilder} instance.
	 * @return Updated object of the {@link CsvUtils} class.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public CsvUtils setCustomBuilder( CSVReaderBuilder readerBuilder)
	{
		csvReader = readerBuilder.build();
		return this;
	}
	
	
	
	
	
	
	/**
	 * <b>Description:</b> This method reads the content present in the CSV file and
	 * returns all the data in a list.
	 * 
	 * </br>
	 * </br>
	 * <b>CSV Example:</b>
	 * 
	 * <p>Name, Age, State</p>
	 * <p>Abhishek, 24, Maharashtra</p>
	 * <p>Morris, 22, Canada</p>
	 * 
	 * 
	 * @return Data of CSV file in a list.
	 * @throws IOException : IOException.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public List<String[]> readAll() throws IOException 
	{
	    List<String[]> list = new ArrayList<>();
	    list = csvReader.readAll();
	    csvFileReader.close();
	    csvReader.close();
	    return list;
	}
	
	
	
	
	
	
	/**
	 * <b>Description:</b> This method returns the record based on the input of a
	 * user. If the CSV file content has a column containing unique data then user
	 * can pass the desired value as a key with the column index in order identify
	 * that record uniquely. </br>
	 * </br>
	 * <b>CSV Example:</b>
	 * 
	 * <p>
	 * EmployeeId, EmployeeName, Department
	 * </p>
	 * <p>
	 * emp001, Abhishek, IT
	 * </p>
	 * <p>
	 * emp002, unknown, IT
	 * </p>
	 * 
	 * </br>
	 * The column index starts from 0. So in the above example column present at the
	 * 0th index is EmployeeId after that Employee name at 1 and so on. As per the
	 * above example, user can pass the key as emp001 because it is unique in all
	 * the records and it can help the user to identify any record uniquely.
	 * 
	 * @param key         : Key to identify the record uniquely in the CSV file.
	 * @param columnIndex : Index of the column containing unique values.
	 * @return Map containing the data of identified record with the help of key and
	 *         column index passed by the user.
	 * @throws IOException : IOException.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public Map<String, Object> get( String key, int columnIndex ) throws IOException
	{
		List<String[]> list = new LinkedList<>();
		list = csvReader.readAll();
		String[] headers = list.get(0);
		Optional<Map<String, Object>> dataMap = Optional.of( new HashMap<>() );
		
		Optional<Map<String, Object>>  response = list.stream().parallel()

		.filter( data ->  data[columnIndex].equals(key) )
		.map(data -> 
		{
				counter = 0;
				Arrays.asList(data).stream().forEach(actualData -> 
				{
					dataMap.get().put(headers[counter], actualData);
					counter++;
				});
			return dataMap.get();
		}).findFirst();
		  
		
		if( response.isPresent() )
		{
			return dataMap.get();
		}
		else
		{
			throw new RuntimeException("No data found with the given key [ " +  key  + " ] and column index [ " + columnIndex + " ]");
		}
		
	}
	
	
	
	
	/**
	 * <b>Description:</b> This getter method returns the instance of CSV File
	 * Reader.
	 * 
	 * @return Instance of the FileReader holding the CSV file data.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public FileReader getCsvFileReader()
	{
		return csvFileReader;
	}
	
	
	
	
	
	/**
	 * <b>Description:</b> This getter method returns the instance of
	 * {@link CSVReader}.
	 * 
	 * @return Instance of CSVReader.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public CSVReader getCsvReader()
	{
		return csvReader;
	}
	
	
	
	/**
	 * <b>Description:</b> This method writes the set of records in the specified
	 * CSV file. User can create the data records as shown below.
	 * 
	 * <p>
	 * {@code List<String[]> records = new ArrayList<>();}
	 * </p>
	 * <p>
	 * {@code records.add( new String[]{"Abhishek","23","India"} ); }
	 * </p>
	 * 
	 * @param records : Records in List<String[]>
	 * @throws IOException : IoException
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public void writeRecordsInCsv(List<String[]> records) throws IOException
	{
		FileWriter csvwriter = new FileWriter(this.csvFile, true);
		Optional<CSVWriter> writer = Optional.of( new CSVWriter(csvwriter) );
		
		if( writer.isPresent() )
		{
			records.stream().forEach(record -> {
				writer.get().writeNext(record);
			});
			
			writer.get().flush();
			writer.get().close();
			csvwriter.close();
		}
		else
		{
			throw new RuntimeException("Error");
		}
		
	}
	
	
	
}
