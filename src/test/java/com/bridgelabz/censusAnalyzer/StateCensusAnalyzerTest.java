package com.bridgelabz.censusAnalyzer;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.censusAnalyzer.exception.StateAnalyzerException;

public class StateCensusAnalyzerTest {
	
	private final String CENSUS_CSV_PATH="./src/Resources/IndiaStateCensusData.csv";
	private final String INAVLID_CENSUS_CSV_PATH="IndiaStateCensusData.csv";
	private StateCensusAnalyzer analyser;
	
	@Before
	public void init() {
		analyser = new StateCensusAnalyzer();
	}
	
	@Test
	public void givenCensusCSVFileReturnsCorrectNoOfEntries() throws StateAnalyzerException {
		int stateCount = analyser.readCSVData(CENSUS_CSV_PATH);
		assertEquals(29, stateCount);
	}
	
	@Test
	public void givenIncorrectCSVFilePath_ThrowsCustomExceptionInvalidFilePath(){
		try {
			analyser.readCSVData(INAVLID_CENSUS_CSV_PATH);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}
}