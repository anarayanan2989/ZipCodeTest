package com.kforce.zipcode.process;

import java.util.LinkedList;
import java.util.List;

public class ZipCodeProcessor {
	private String zipRange;

	  public ZipCodeProcessor(String zipRange) {
	    this.zipRange = zipRange;
	  }

	  public List<ZipCode> stripZipcode() {
	    String[] zipcodeIntervals = zipRange.split(" ");
	    return loadZipcode(zipcodeIntervals);
	  }

	  public int stringToInt(String zipcode) {
	    return Integer.parseInt(zipcode);
	  }

	  public boolean checkZipLength(int zipcode) {
	    if ((int) (Math.log10(zipcode) + 1) != 5)
	      return false;
	    return true;
	  }

	  public boolean compareZipcodeRange(int lowerBound, int upperBound) {
	    if (lowerBound > upperBound)
	      return false;
	    return true;
	  }

	  public boolean validateZipcodeRange(int lowerBound, int upperBound) {
	    if (!checkZipLength(lowerBound) && !checkZipLength(upperBound))
	      throw new IllegalArgumentException(lowerBound + " " + upperBound + ": " + "Zipcode should have 5 digits");
	    if (compareZipcodeRange(lowerBound, upperBound) == false)
	      throw new IllegalArgumentException(
	          lowerBound + " " + upperBound + ":  " + "Zipcode lower bound should be less than upper bound");
	    return true;
	  }

	  public ZipCode validateAndAdd(String[] zipRange) {
	    if (zipRange.length != 2)
	      throw new IllegalArgumentException(zipRange[0] + "Zipcode should have lower and upper bounds");
	    int lowerBound = stringToInt(zipRange[0]);
	    int upperBound = stringToInt(zipRange[1]);
	    ZipCode zipcode = null;
	    if (validateZipcodeRange(lowerBound, upperBound) == true)
	      zipcode = new ZipCode(lowerBound, upperBound);
	    return zipcode;
	  }

	  public ZipCode getZipcodeRange(String zipcodeRange) {
	    return validateAndAdd(zipcodeRange.replaceAll("\\[|\\]", "").split(","));
	  }

	  public List<ZipCode> loadZipcode(String[] zipcodeRange) {
	    List<ZipCode> zipcodesList = new LinkedList<ZipCode>();
	    for (int i = 0; i < zipcodeRange.length; i++) {
	      zipcodesList.add(getZipcodeRange(zipcodeRange[i]));
	    }
	    return zipcodesList;
	  }
}
