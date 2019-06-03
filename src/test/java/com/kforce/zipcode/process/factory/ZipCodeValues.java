package com.kforce.zipcode.process.factory;

import java.util.Random;

public class ZipCodeValues {
	Random rand = new Random();
	public String generateRandomZipCode(int noOfDataSets) {
	    String dataSet = "";
	    
	    for (int i = 0; i < noOfDataSets; i++) {
	    	int lowerBound = rand.nextInt(90000)+10000;
	    	int upperBound = rand.nextInt(99999-lowerBound) + lowerBound;
	    	dataSet += "[" + lowerBound + "," + upperBound + "] ";
	    }
	    return dataSet;
	  }

	  public String generateOverlappingZipCode(int noOfDataSets) {
	    String dataSet = "";
	    for (int i = 0; i < noOfDataSets; i++) {
	    	int lowerBound = rand.nextInt(90000)+10000;
	    	int upperBound = rand.nextInt(99999-lowerBound) + lowerBound;
	    	dataSet += "[" + (lowerBound-4) + "," + upperBound + "] ";
	    }
	    return dataSet;
	  }
}
