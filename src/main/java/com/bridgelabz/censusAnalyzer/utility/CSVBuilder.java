package com.bridgelabz.censusAnalyzer.utility;

import java.io.Reader;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.bridgelabz.censusAnalyzer.exception.StateAnalyzerException;
import com.bridgelabz.censusAnalyzer.exception.StateAnalyzerException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVBuilder {
	
	public <T> int getCount(Reader reader,Class<T> className) throws StateAnalyzerException {
		Iterator<T> csvIterator=this.getCSVIterator(reader, className);
		Iterable<T> csvItrable= () -> csvIterator;
		int count=(int) StreamSupport.stream(csvItrable.spliterator(),false)
				.count();
		return count;
	}
	
	private <T> Iterator<T> getCSVIterator(Reader reader,Class<T> className) throws StateAnalyzerException {
		try {
			CsvToBean<T> csvToBean =
					new CsvToBeanBuilder<T>(reader)
					.withIgnoreLeadingWhiteSpace(true)
					.withType(className)
					.build();
			
			return csvToBean.iterator();
		}catch(IllegalStateException exception){
			throw new StateAnalyzerException("Invalid Class Type.",
					ExceptionType.INVALID_CLASS_TYPE);
		}
	}
}
