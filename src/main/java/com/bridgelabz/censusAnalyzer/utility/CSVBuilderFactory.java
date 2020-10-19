package com.bridgelabz.censusAnalyzer.utility;

public class CSVBuilderFactory {

	public static ICSVBuilder createCSVBuilder() {
		return new CSVBuilder();
	}
}