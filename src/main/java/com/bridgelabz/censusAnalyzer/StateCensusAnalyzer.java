package com.bridgelabz.censusAnalyzer;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.bridgelabz.censusAnalyzer.csvbuilder.CSVBuilderFactory;
import com.bridgelabz.censusAnalyzer.csvbuilder.ICSVBuilder;
import com.bridgelabz.censusAnalyzer.csvbuilder.exception.CSVBuilderException;
import com.bridgelabz.censusAnalyzer.csvbuilder.exception.CSVBuilderException.ExceptionType;
import com.bridgelabz.censusAnalyzer.model.CSVStateCensus;
import com.google.gson.Gson;

public class StateCensusAnalyzer {
	
	List<CSVStateCensus> censusList=null;

	public <T> int readCSVData(String FilePath,Class<T> className) throws CSVBuilderException {
		try {
			Files.newBufferedReader(Paths.get(FilePath));
			Reader reader=Files.newBufferedReader(Paths.get(FilePath));
			
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			return getCountByList(csvBuilder.getCSVList(reader, className));

		}catch(IOException exception) {
			throw new CSVBuilderException("Invalid Path Name",
					ExceptionType.INVALID_FILE_PATH);
		}catch(RuntimeException exception){
			throw new CSVBuilderException("Invalid Delimitor/Head Name",
					ExceptionType.INVALID_DELIM_OR_HEAD);
		}
	}
	
	private int getCountByList(List list) {
		return list.size();
	}

	private <T> int getCountByItr(Iterator<T> csvIterator) throws CSVBuilderException {
		Iterable<T> csvItrable= () -> csvIterator;
		int count=(int) StreamSupport.stream(csvItrable.spliterator(),false)
				.count();
		return count;
	}
	
	public String makeCensusDataSorted() throws CSVBuilderException {
		if(censusList==null||censusList.size()==0)
			throw new CSVBuilderException("No Census Data", ExceptionType.NO_DATA);
		sortByState();
		String sortedCensusDataJson = new Gson().toJson(sortByState());
		return sortedCensusDataJson;
	}
	
	private List<CSVStateCensus> sortByState() {
		return censusList.stream()
		.sorted(Comparator.comparing(CSVStateCensus::getState))
		.collect(Collectors.toList());
	}
}