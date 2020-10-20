package com.bridgelabz.censusAnalyzer.csvbuilder;

import java.io.Reader;

import com.bridgelabz.censusAnalyzer.csvbuilder.exception.CSVBuilderException;

public interface ICSVBuilder<T> {
	public int getCount(Reader reader,Class className)
			throws CSVBuilderException;
}