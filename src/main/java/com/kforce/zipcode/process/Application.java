package com.kforce.zipcode.process;
import java.util.List;
import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
	    Scanner scan = new Scanner(System.in);
	    String zipcodeRanges = scan.nextLine();
	    ZipCodeProcessor zipcodeProcessor = new ZipCodeProcessor(zipcodeRanges);
	    List<ZipCode> zipcodeList = zipcodeProcessor.stripZipcode();
	    ZipCodeCombiner zipcode_merger = new ZipCodeCombiner();
	    List<ZipCode> sortedZipCodeList = zipcode_merger.sortByLowerBounds(zipcodeList);
	    List<ZipCode> mergedZipcodeList = zipcode_merger.mergeZipcodes(sortedZipCodeList);
	    for (ZipCode zipcode : mergedZipcodeList) {
	      System.out.println("[" + zipcode.getLowerBound() + "," + zipcode.getUpperBound() + "]");
	    }
	    scan.close();

	  }
}
