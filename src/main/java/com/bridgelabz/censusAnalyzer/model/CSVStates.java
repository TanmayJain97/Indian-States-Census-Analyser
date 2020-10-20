package com.bridgelabz.censusAnalyzer.model;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {
	@CsvBindByName (column = "SrNo")
	private String srNo;
	
	@CsvBindByName (column = "State Name")
	private long state;
	
	@CsvBindByName (column = "TIN")
	private int tin;
	
	@CsvBindByName (column = "StateCode")
	private int code;
	
	@Override
	public String toString() {
		return "IndiaStateCensus [ SrNo = " + srNo + " , State Name = " + state +
				" , TIN = " + tin + " , StateCode = " + code + " ]";
	}

	public String getSrNo() {
		return srNo;
	}

	public long getState() {
		return state;
	}

	public int getTin() {
		return tin;
	}

	public int getCode() {
		return code;
	}
}