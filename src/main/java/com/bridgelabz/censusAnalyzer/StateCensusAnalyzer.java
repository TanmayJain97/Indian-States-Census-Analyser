package com.bridgelabz.censusAnalyzer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;
import com.bridgelabz.censusAnalyzer.exception.StateAnalyzerException;
import com.bridgelabz.censusAnalyzer.exception.StateAnalyzerException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyzer {

	public <T> int readCSVData(String FilePath,Class<T> className) throws StateAnalyzerException {
		try {
			Files.newBufferedReader(Paths.get(FilePath));
			Reader reader=Files.newBufferedReader(Paths.get(FilePath));
			CsvToBean<T> csvToBean =
					new CsvToBeanBuilder<T>(reader)
					.withIgnoreLeadingWhiteSpace(true)
					.withType(className).build();
			
			Iterator<T> csvIterator = csvToBean.iterator();

			Iterable<T> csvItrable= () -> csvIterator;
			int count=(int) StreamSupport.stream(csvItrable.spliterator(),false)
					.count();
			
			return count;

		}catch(IOException exception) {
			throw new StateAnalyzerException("Invalid Path Name",
					ExceptionType.INVALID_FILE_PATH);
		}catch(IllegalStateException exception){
			throw new StateAnalyzerException("Invalid Class Type.",
					ExceptionType.INVALID_CLASS_TYPE);
		}catch(RuntimeException exception){
			throw new StateAnalyzerException("Invalid Delimitor/Head Name",
					ExceptionType.INVALID_DELIM_OR_HEAD);
		}
	}
}