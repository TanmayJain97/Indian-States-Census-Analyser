package com.bridgelabz.censusAnalyzer.csvbuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.bridgelabz.censusAnalyzer.csvbuilder.exception.CSVBuilderException;
import com.bridgelabz.censusAnalyzer.csvbuilder.exception.CSVBuilderException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVBuilder<T> implements ICSVBuilder<T> {
	
	public int getCount(Reader reader,Class className) throws CSVBuilderException {
		Iterator<T> csvIterator=this.getCSVIterator(reader, className);
		Iterable<T> csvItrable= () -> csvIterator;
		int count=(int) StreamSupport.stream(csvItrable.spliterator(),false)
				.count();
		return count;
	}
	
	private Iterator<T> getCSVIterator(Reader reader,Class<T> className) throws CSVBuilderException {
		try {
			CsvToBean<T> csvToBean =
					new CsvToBeanBuilder<T>(reader)
					.withIgnoreLeadingWhiteSpace(true)
					.withType(className)
					.build();
			
			return csvToBean.iterator();
		}catch(IllegalStateException exception){
			throw new CSVBuilderException("Invalid Class Type.",
					ExceptionType.INVALID_CLASS_TYPE);
		}
	}
}
