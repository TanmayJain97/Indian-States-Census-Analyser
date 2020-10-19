package com.bridgelabz.censusAnalyzer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.bridgelabz.censusAnalyzer.exception.StateAnalyzerException;
import com.bridgelabz.censusAnalyzer.exception.StateAnalyzerException.ExceptionType;
import com.bridgelabz.censusAnalyzer.utility.CSVBuilder;

public class StateCensusAnalyzer {

	public <T> int readCSVData(String FilePath,Class<T> className) throws StateAnalyzerException {
		try {
			Files.newBufferedReader(Paths.get(FilePath));
			Reader reader=Files.newBufferedReader(Paths.get(FilePath));
			
			return new CSVBuilder().getCount(reader, className);

		}catch(IOException exception) {
			throw new StateAnalyzerException("Invalid Path Name",
					ExceptionType.INVALID_FILE_PATH);
		}catch(RuntimeException exception){
			throw new StateAnalyzerException("Invalid Delimitor/Head Name",
					ExceptionType.INVALID_DELIM_OR_HEAD);
		}
	}
}