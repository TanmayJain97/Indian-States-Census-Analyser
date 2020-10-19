package com.bridgelabz.censusAnalyzer.utility;

import java.io.Reader;

import com.bridgelabz.censusAnalyzer.exception.StateAnalyzerException;

public interface ICSVBuilder<T> {
	public int getCount(Reader reader,Class className)
			throws StateAnalyzerException;
}