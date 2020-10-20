package com.bridgelabz.censusAnalyzer.csvbuilder;

public class CSVBuilderFactory {

	public static ICSVBuilder createCSVBuilder() {
		return new CSVBuilder();
	}
}