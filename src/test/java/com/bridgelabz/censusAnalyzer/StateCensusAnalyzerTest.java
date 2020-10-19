package com.bridgelabz.censusAnalyzer;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.censusAnalyzer.exception.StateAnalyzerException;
import com.bridgelabz.censusAnalyzer.model.CSVStateCensus;
import com.bridgelabz.censusAnalyzer.model.CSVStates;

public class StateCensusAnalyzerTest {
	
	//Paths for State Census Files
	private final String CENSUS_CSV_PATH="./src/Resources/IndiaStateCensusData.csv";
	private final String INVALID_CENSUS_CSV_PATH="IndiaStateCensusData.csv";
	private final String INVALID_CENSUS_CSV_DELIM="./src/Resources/IndiaStateCensusDataInvalidDelim.csv";
	private final String INVALID_CENSUS_CSV_HEAD="./src/Resources/IndiaStateCensusDataInvalidHead.csv";
	
	//Paths for State Code Files
	private final String STATECODE_CSV_PATH="./src/Resources/IndiaStateCode.csv";
	private final String INVALID_STATECODE_CSV_PATH="IndiaStateCode.csv";
	private final String INVALID_STATECODE_CSV_DELIM="./src/Resources/IndiaStateCodeInvalidDelim.csv";
	private final String INVALID_STATECODE_CSV_HEAD="./src/Resources/IndiaStateCodeInvalidHead.csv";
	
	private StateCensusAnalyzer analyser;
	
	@Before
	public void init() {
		analyser = new StateCensusAnalyzer();
	}
	
	//Test Cases For State Census
	
	@Test
	public void givenCensusCSVFileReturnsCorrectNoOfEntries() throws StateAnalyzerException {
		int stateCount = analyser.readCSVData(CENSUS_CSV_PATH,CSVStateCensus.class);
		assertEquals(29, stateCount);
	}
	
	@Test
	public void givenIncorrectCSVFilePath_ThrowsCustomExceptionInvalidFilePath(){
		try {
			analyser.readCSVData(INVALID_CENSUS_CSV_PATH,CSVStateCensus.class);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}
	
	@Test
	public void givenIncorrectDelimiter_ThrowsCustomExceptionInvalidDelimiter(){
		try {
			analyser.readCSVData(INVALID_CENSUS_CSV_DELIM,CSVStateCensus.class);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_DELIM_OR_HEAD, e.type);
		}
	}
	
	@Test
	public void givenIncorrectHeader_ThrowsCustomExceptionInvalidHeader(){
		try {
			analyser.readCSVData(INVALID_CENSUS_CSV_HEAD,CSVStateCensus.class);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_DELIM_OR_HEAD, e.type);
		}
	}
	
	//Test Cases For State Code
	
	@Test
	public void givenCodeCSVFileReturnsCorrectNoOfEntries() throws StateAnalyzerException{
		int stateCount = analyser.readCSVData(STATECODE_CSV_PATH,CSVStates.class);
		assertEquals(36, stateCount);
	}
	
	@Test
	public void givenIncorrectCSVFilePath_ThrowsCustomExceptionInvalidFilePath2(){
		try {
			analyser.readCSVData(INVALID_STATECODE_CSV_PATH,CSVStates.class);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}
	
	@Test
	public void givenIncorrectDelimiter_ThrowsCustomExceptionInvalidDelimiter2(){
		try {
			analyser.readCSVData(INVALID_STATECODE_CSV_DELIM,CSVStates.class);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_DELIM_OR_HEAD, e.type);
		}
	}
	
	@Test
	public void givenIncorrectHeader_ThrowsCustomExceptionInvalidHeader2(){
		try {
			analyser.readCSVData(INVALID_STATECODE_CSV_HEAD,CSVStates.class);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_DELIM_OR_HEAD, e.type);
		}
	}
}