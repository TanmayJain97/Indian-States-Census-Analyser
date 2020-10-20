package com.bridgelabz.censusAnalyzer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.bridgelabz.censusAnalyzer.csvbuilder.CSVBuilderFactory;
import com.bridgelabz.censusAnalyzer.csvbuilder.ICSVBuilder;
import com.bridgelabz.censusAnalyzer.csvbuilder.exception.CSVBuilderException;
import com.bridgelabz.censusAnalyzer.csvbuilder.exception.CSVBuilderException.ExceptionType;

public class StateCensusAnalyzer {

	public <T> int readCSVData(String FilePath,Class<T> className) throws CSVBuilderException {
		try {
			Files.newBufferedReader(Paths.get(FilePath));
			Reader reader=Files.newBufferedReader(Paths.get(FilePath));
			
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			return csvBuilder.getCount(reader, className);

		}catch(IOException exception) {
			throw new CSVBuilderException("Invalid Path Name",
					ExceptionType.INVALID_FILE_PATH);
		}catch(RuntimeException exception){
			throw new CSVBuilderException("Invalid Delimitor/Head Name",
					ExceptionType.INVALID_DELIM_OR_HEAD);
		}
	}
}