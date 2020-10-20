package com.bridgelabz.censusAnalyzer.csvbuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.bridgelabz.censusAnalyzer.csvbuilder.exception.CSVBuilderException;

public interface ICSVBuilder<T> {
	public Iterator<T> getCSVIterator(Reader reader,Class className)
			throws CSVBuilderException;
	
	public List<T> getCSVList(Reader reader,Class className)
			throws CSVBuilderException;
}