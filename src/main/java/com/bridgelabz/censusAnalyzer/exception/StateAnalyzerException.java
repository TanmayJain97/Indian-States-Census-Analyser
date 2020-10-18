package com.bridgelabz.censusAnalyzer.exception;

public class StateAnalyzerException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3369988279518561290L;

	public enum ExceptionType{
		INVALID_FILE_PATH, INVALID_CLASS_TYPE, INVALID_DELIM}
	
	public ExceptionType type;
	
	public StateAnalyzerException(String message,ExceptionType type) {
		super(message);
		this.type = type;
	}
}