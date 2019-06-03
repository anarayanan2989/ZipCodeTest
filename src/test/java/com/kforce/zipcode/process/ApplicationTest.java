package com.kforce.zipcode.process;

import java.util.List;

import com.kforce.zipcode.process.factory.ZipCodeValues;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ApplicationTest extends TestCase{
	public ApplicationTest(String testName) {
	    super(testName);
	  }

	  public static Test suite() {
	    TestSuite suite = new TestSuite(ApplicationTest.class);
	    return suite;
	  }

	  public void testLoadedList() {
	    ZipCodeValues values = new ZipCodeValues();
	    String inputDataSet = values.generateRandomZipCode(100);
	    ZipCodeProcessor zipcodeProcessor = new ZipCodeProcessor(inputDataSet);
	    List<ZipCode> zipcodeList = zipcodeProcessor.stripZipcode();
	    assertTrue(zipcodeList.size() > 0);
	  }

	  public void testfinalResultToMatch() {
		ZipCodeValues values = new ZipCodeValues();
	    String inputDataSet = values.generateOverlappingZipCode(5);
	    ZipCodeProcessor zipcodeProcessor = new ZipCodeProcessor(inputDataSet);
	    List<ZipCode> zipCodeList = zipcodeProcessor.stripZipcode();
	    ZipCodeCombiner zipcodeCombiner = new ZipCodeCombiner();
	    List<ZipCode> sortedZipCodeList = zipcodeCombiner.sortByLowerBounds(zipCodeList);
	    List<ZipCode> mergedZipCodeList = zipcodeCombiner.mergeZipcodes(sortedZipCodeList);
	    assertTrue(mergedZipCodeList.size() == 1);
	  }

	  public void testIllegalArgumentException() {
	    try {
	      String inputDataSet = "[92004,92002] [92003,92004]";
	      ZipCodeProcessor zipcodeProcessor = new ZipCodeProcessor(inputDataSet);
	      List<ZipCode> zipCodeList = zipcodeProcessor.stripZipcode();
	    } catch (IllegalArgumentException e) {
	      assertEquals("IllegalArgumentException", e.getClass().getSimpleName());
	    }
	  }

	  public void testExceptionWhenMoreRanges() {
	    try {
	      String inputValues = "[92004,92002,92003] [92003,92004]";
	      ZipCodeProcessor zipcodeProcessor = new ZipCodeProcessor(inputValues);
	      List<ZipCode> zipcodeList = zipcodeProcessor.stripZipcode();
	    } catch (IllegalArgumentException e) {
	      assertEquals("IllegalArgumentException", e.getClass().getSimpleName());
	    }
	  }

	  public void testExceptionMessageWhenLowerBoundGreater() {
	    try {
	      String inputValues = "[92004,92002] [92003,92004]";
	      ZipCodeProcessor zipcodeProcessor = new ZipCodeProcessor(inputValues);
	      List<ZipCode> zipcodeList = zipcodeProcessor.stripZipcode();
	    } catch (IllegalArgumentException e) {
	      String expectedMessage = "92004 92002:  Zipcode lower bound should be less" + " than upper bound";
	      assertEquals(expectedMessage, e.getMessage());
	    }
	  }

	  public void testExceptionMessageWhenMoreRangeGiven() {
	    try {
	      String inputDataSet = "[92004,92002,92003] [92003,92004]";
	      ZipCodeProcessor zipcodeProcessor = new ZipCodeProcessor(inputDataSet);
	      List<ZipCode> zipcodeList = zipcodeProcessor.stripZipcode();
	    } catch (IllegalArgumentException e) {
	      String expectedMessage = "92004Zipcode should have lower " + "and upper bounds";
	      assertEquals(expectedMessage, e.getMessage());
	    }
	  }


}
