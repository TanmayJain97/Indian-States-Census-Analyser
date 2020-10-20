package com.bridgelabz.censusAnalyzer;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.censusAnalyzer.csvbuilder.exception.CSVBuilderException;
import com.bridgelabz.censusAnalyzer.model.CSVStateCensus;
import com.bridgelabz.censusAnalyzer.model.CSVStates;
import com.bridgelabz.censusAnalyzer.model.Invalid;
import com.google.gson.Gson;

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
	public void givenCensusCSVFileReturnsCorrectNoOfEntries() throws CSVBuilderException {
		int stateCount = analyser.readCSVData(CENSUS_CSV_PATH,CSVStateCensus.class);
		assertEquals(29, stateCount);
	}
	
	@Test
	public void givenIncorrectCSVFilePath_ThrowsCustomExceptionInvalidFilePath(){
		try {
			analyser.readCSVData(INVALID_CENSUS_CSV_PATH,CSVStateCensus.class);
		} catch (CSVBuilderException e) {
			e.printStackTrace();
			assertEquals(CSVBuilderException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}
	
	@Test
	public void givenIncorrectClass_ThrowsCustomExceptionInvalidClassType(){
		try {
			analyser.readCSVData(CENSUS_CSV_PATH,Invalid.class);
		} catch (CSVBuilderException e) {
			e.printStackTrace();
			assertEquals(CSVBuilderException.ExceptionType.INVALID_CLASS_TYPE, e.type);
		}
	}
	
	@Test
	public void givenIncorrectDelimiter_ThrowsCustomExceptionInvalidDelimiter(){
		try {
			analyser.readCSVData(INVALID_CENSUS_CSV_DELIM,CSVStateCensus.class);
		} catch (CSVBuilderException e) {
			e.printStackTrace();
			assertEquals(CSVBuilderException.ExceptionType.INVALID_DELIM_OR_HEAD, e.type);
		}
	}
	
	@Test
	public void givenIncorrectHeader_ThrowsCustomExceptionInvalidHeader(){
		try {
			analyser.readCSVData(INVALID_CENSUS_CSV_HEAD,CSVStateCensus.class);
		} catch (CSVBuilderException e) {
			e.printStackTrace();
			assertEquals(CSVBuilderException.ExceptionType.INVALID_DELIM_OR_HEAD, e.type);
		}
	}
	
	//Test Cases For State Code
	
	@Test
	public void givenCodeCSVFileReturnsCorrectNoOfEntries() throws CSVBuilderException{
		int stateCount = analyser.readCSVData(STATECODE_CSV_PATH,CSVStates.class);
		assertEquals(36, stateCount);
	}
	
	@Test
	public void givenIncorrectClass_ThrowsCustomExceptionInvalidClassType2(){
		try {
			analyser.readCSVData(STATECODE_CSV_PATH,Invalid.class);
		} catch (CSVBuilderException e) {
			e.printStackTrace();
			assertEquals(CSVBuilderException.ExceptionType.INVALID_CLASS_TYPE, e.type);
		}
	}
	
	@Test
	public void givenIncorrectCSVFilePath_ThrowsCustomExceptionInvalidFilePath2(){
		try {
			analyser.readCSVData(INVALID_STATECODE_CSV_PATH,CSVStates.class);
		} catch (CSVBuilderException e) {
			e.printStackTrace();
			assertEquals(CSVBuilderException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}
	
	@Test
	public void givenIncorrectDelimiter_ThrowsCustomExceptionInvalidDelimiter2(){
		try {
			analyser.readCSVData(INVALID_STATECODE_CSV_DELIM,CSVStates.class);
		} catch (CSVBuilderException e) {
			e.printStackTrace();
			assertEquals(CSVBuilderException.ExceptionType.INVALID_DELIM_OR_HEAD, e.type);
		}
	}
	
	@Test
	public void givenIncorrectHeader_ThrowsCustomExceptionInvalidHeader2(){
		try {
			analyser.readCSVData(INVALID_STATECODE_CSV_HEAD,CSVStates.class);
		} catch (CSVBuilderException e) {
			e.printStackTrace();
			assertEquals(CSVBuilderException.ExceptionType.INVALID_DELIM_OR_HEAD, e.type);
		}
	}
	
	//Test for create JSON file in sorted Format
	
	//Sort By State
	@Test
	public void givenCensusData_WhenSorted_GivesFirstStateAndhraPradesh() throws CSVBuilderException{
		analyser.readCSVData(CENSUS_CSV_PATH,CSVStateCensus.class);
		String sortedData=analyser.makeCensusDataSorted();
		CSVStateCensus[] censusData = new Gson().fromJson(sortedData, CSVStateCensus[].class);
		assertEquals("Andhra Pradesh", censusData[0].getState());
	}
}