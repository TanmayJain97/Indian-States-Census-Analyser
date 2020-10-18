package com.bridgelabz.censusAnalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;
import com.bridgelabz.censusAnalyzer.exception.StateAnalyzerException;
import com.bridgelabz.censusAnalyzer.exception.StateAnalyzerException.ExceptionType;
import com.bridgelabz.censusAnalyzer.model.CSVStateCensus;
import com.bridgelabz.censusAnalyzer.model.CSVStates;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyzer {

	public int readStateCensusCSVData(String FilePath) throws StateAnalyzerException {
		try {
			Files.newBufferedReader(Paths.get(FilePath));
			Reader reader=Files.newBufferedReader(Paths.get(FilePath));
			CsvToBean<CSVStateCensus> csvToBean =
					new CsvToBeanBuilder<CSVStateCensus>(reader)
					.withIgnoreLeadingWhiteSpace(true)
					.withSkipLines(1)
					.withType(CSVStateCensus.class).build();
			
			Iterator<CSVStateCensus> csvIterator = csvToBean.iterator();

			Iterable<CSVStateCensus> csvItrable= () -> csvIterator;
			int count=(int) StreamSupport.stream(csvItrable.spliterator(),false)
					.count();

			BufferedReader br = new BufferedReader(reader);
			
			//Check Header
			while (br.readLine()!=null) {
				String[] head=br.readLine().split(",");
				boolean flagCorrectHead=
						head[0]=="State" &&
						head[1]=="Population" &&
						head[2]=="AreaInSqKm" &&
						head[3]=="DensityPerSqKm";
				if (!flagCorrectHead) {
					throw new StateAnalyzerException("Invalid Headers",
							ExceptionType.INVALID_HEAD);
				}
				break;
			}
			
			
			//Check delimitor
			boolean flagCorrectDelim=true;
			while (br.readLine()!=null) {
				if (!br.readLine().contains(",")) {
					flagCorrectDelim=false;
				}
			}
			if (!flagCorrectDelim) {
				throw new StateAnalyzerException("Invalid Delimitor",
						ExceptionType.INVALID_DELIM);
			}
			
			return count;

		}catch(IOException exception) {
			throw new StateAnalyzerException("Invalid Path Name",
					ExceptionType.INVALID_FILE_PATH);
		}catch(IllegalStateException exception){
			throw new StateAnalyzerException("Invalid Class Type.",
					ExceptionType.INVALID_CLASS_TYPE);
		}
	}
	
	public int readStateCodeCSVData(String FilePath) {
		
		try {
			Files.newBufferedReader(Paths.get(FilePath));
			Reader reader=Files.newBufferedReader(Paths.get(FilePath));
			CsvToBean<CSVStates> csvToBean =
					new CsvToBeanBuilder<CSVStates>(reader)
					.withIgnoreLeadingWhiteSpace(true)
					.withSkipLines(1)
					.withType(CSVStates.class).build();
			
			Iterator<CSVStates> csvIterator = csvToBean.iterator();

			Iterable<CSVStates> csvItrable= () -> csvIterator;
			int count=(int) StreamSupport.stream(csvItrable.spliterator(),false)
					.count();
			return count;
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
}