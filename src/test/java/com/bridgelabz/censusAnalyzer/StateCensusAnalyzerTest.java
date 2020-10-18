package com.bridgelabz.censusAnalyzer;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class StateCensusAnalyzerTest {
	
	private StateCensusAnalyzer analyser;
	
	@Before
	public void init() {
		analyser = new StateCensusAnalyzer();
	}
	
	@Test
	public void givenCensusCSVFile_ReturnsCorrectNoOfEntries() throws IOException {
		int stateCount = analyser.readCSVData();
		assertEquals(29, stateCount);
	}
}