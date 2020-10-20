package com.bridgelabz.censusAnalyzer;

import java.io.IOException;
import java.io.Reader;
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
import com.bridgelabz.censusAnalyzer.model.CSVStates;
import com.google.gson.Gson;

public class StateCensusAnalyzer {
	
	List<CSVStateCensus> censusList=null;
	List<CSVStates> stateList=null;

	public <T> int readCSVData(String FilePath,Class<T> className) throws CSVBuilderException {
		try {
			Files.newBufferedReader(Paths.get(FilePath));
			Reader reader=Files.newBufferedReader(Paths.get(FilePath));
			
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List <T> list=csvBuilder.getCSVList(reader, className);
			censusList=(List<CSVStateCensus>) list;
			stateList=(List<CSVStates>) list;
			return getCountByList(list);

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
		String sortedCensusDataJson = new Gson().toJson(sortByDensity());
		return sortedCensusDataJson;
	}
	
	private List<CSVStateCensus> sortByState() {
		return censusList.stream()
		.sorted(Comparator.comparing(CSVStateCensus::getState))
		.collect(Collectors.toList());
	}
	
	private List<CSVStateCensus> sortByPop() {
		return censusList.stream()
		.sorted(Comparator.comparingLong(CSVStateCensus::getPop).reversed())
		.collect(Collectors.toList());
	}
	
	private List<CSVStateCensus> sortByDensity() {
		return censusList.stream()
		.sorted(Comparator.comparingLong(CSVStateCensus::getDensity).reversed())
		.collect(Collectors.toList());
	}

	public String makeStateDataSorted() throws CSVBuilderException {
		if(stateList==null||stateList.size()==0)
			throw new CSVBuilderException("No Census Data", ExceptionType.NO_DATA);
		String sortedStateDataJson = new Gson().toJson(sortByStateCode());
		return sortedStateDataJson;
	}

	private List<CSVStates> sortByStateCode() {
		return stateList.stream()
				.sorted(Comparator.comparing(CSVStates::getState))
				.collect(Collectors.toList());
	}
}