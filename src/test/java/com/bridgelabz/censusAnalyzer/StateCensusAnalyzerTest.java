package com.bridgelabz.censusAnalyzer;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.censusAnalyzer.exception.StateAnalyzerException;

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
	
	private StateCensusAnalyzer analyser;
	
	@Before
	public void init() {
		analyser = new StateCensusAnalyzer();
	}
	
	//Test Cases For State Census
	
	@Test
	public void givenCensusCSVFileReturnsCorrectNoOfEntries() throws StateAnalyzerException {
		int stateCount = analyser.readStateCensusCSVData(CENSUS_CSV_PATH);
		assertEquals(28, stateCount);
	}
	
	@Test
	public void givenIncorrectCSVFilePath_ThrowsCustomExceptionInvalidFilePath(){
		try {
			analyser.readStateCensusCSVData(INVALID_CENSUS_CSV_PATH);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}
	
	@Test
	public void givenIncorrectDelimiter_ThrowsCustomExceptionInvalidDelimiter(){
		try {
			analyser.readStateCensusCSVData(INVALID_CENSUS_CSV_DELIM);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_DELIM, e.type);
		}
	}
	
	@Test
	public void givenIncorrectHeader_ThrowsCustomExceptionInvalidHeader(){
		try {
			analyser.readStateCensusCSVData(INVALID_CENSUS_CSV_HEAD);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_HEAD, e.type);
		}
	}
	
	//Test Cases For State Code
	
	@Test
	public void givenCodeCSVFileReturnsCorrectNoOfEntries() throws StateAnalyzerException{
		int stateCount = analyser.readStateCodeCSVData(STATECODE_CSV_PATH);
		assertEquals(36, stateCount);
	}
	
	@Test
	public void givenIncorrectCSVFilePath_ThrowsCustomExceptionInvalidFilePath2(){
		try {
			analyser.readStateCodeCSVData(INVALID_STATECODE_CSV_PATH);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}
	
	@Test
	public void givenIncorrectDelimiter_ThrowsCustomExceptionInvalidDelimiter2(){
		try {
			analyser.readStateCodeCSVData(INVALID_STATECODE_CSV_DELIM);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_DELIM, e.type);
		}
	}
}