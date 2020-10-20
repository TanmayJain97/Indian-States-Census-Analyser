package com.bridgelabz.censusAnalyzer.csvbuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.bridgelabz.censusAnalyzer.csvbuilder.exception.CSVBuilderException;
import com.bridgelabz.censusAnalyzer.csvbuilder.exception.CSVBuilderException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVBuilder<T> implements ICSVBuilder<T> {
	
	public Iterator<T> getCSVIterator(Reader reader,Class className) throws CSVBuilderException{
			return getCSVBean(reader, className).iterator();
	}

	@Override
	public List<T> getCSVList(Reader reader, Class className) throws CSVBuilderException {
		return getCSVBean(reader, className).parse();
	}
	
	private CsvToBean<T> getCSVBean(Reader reader,Class className) throws CSVBuilderException{
		try {
			CsvToBean<T> csvToBean =
					new CsvToBeanBuilder<T>(reader)
					.withIgnoreLeadingWhiteSpace(true)
					.withType(className)
					.build();
			
			return csvToBean;
		}catch(IllegalStateException exception){
			throw new CSVBuilderException("Invalid Class Type.",
					ExceptionType.INVALID_CLASS_TYPE);
		}
	}
}