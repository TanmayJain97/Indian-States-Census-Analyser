package com.bridgelabz.censusAnalyzer.csvbuilder.exception;

public class CSVBuilderException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3369988279518561290L;

	public enum ExceptionType{
		INVALID_FILE_PATH, INVALID_CLASS_TYPE, INVALID_DELIM_OR_HEAD, NO_DATA}
	
	public ExceptionType type;
	
	public CSVBuilderException(String message,ExceptionType type) {
		super(message);
		this.type = type;
	}
}