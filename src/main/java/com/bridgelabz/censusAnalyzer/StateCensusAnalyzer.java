package com.bridgelabz.censusAnalyzer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.bridgelabz.censusAnalyzer.model.CSVStateCensus;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyzer {
	
	private final String CENSUS_CSV_PATH="./src/Resources/IndiaStateCensusData.csv";

	public void readCSVData() throws IOException {
		try {
			Reader reader=Files.newBufferedReader(Paths.get(CENSUS_CSV_PATH));
			CsvToBean<CSVStateCensus> csvToBean =
					new CsvToBeanBuilder<CSVStateCensus>(reader)
					.withIgnoreLeadingWhiteSpace(true)
					.withSkipLines(1)
					.withType(CSVStateCensus.class).build();

			Iterator<CSVStateCensus> csvIterator = csvToBean.iterator();

            while (csvIterator.hasNext()) {
            	CSVStateCensus csvData=csvIterator.next();
            	System.out.println(csvData);
            }
			
		}catch(IOException exception){
			exception.printStackTrace();
		}
	}
}