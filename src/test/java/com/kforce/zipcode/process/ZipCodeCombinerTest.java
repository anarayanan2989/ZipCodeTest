package com.kforce.zipcode.process;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.kforce.zipcode.process.factory.ZipCodeValues;

import junit.framework.TestCase;

public class ZipCodeCombinerTest extends TestCase{
	@Test
	  public void testMergeZipcodes() {
	    ZipCodeValues values = new ZipCodeValues();
	    String zipcodeRanges = values.generateRandomZipCode(100);
	    ZipCodeProcessor zipcodeProcessor = new ZipCodeProcessor(zipcodeRanges);
	    List<ZipCode> zipcodeList = zipcodeProcessor.stripZipcode();
	    ZipCodeCombiner zipcodeCombiner = new ZipCodeCombiner();
	    List<ZipCode> sortedZipCodeList = zipcodeCombiner.sortByLowerBounds(zipcodeList);
	    List<ZipCode> mergedZipcodeList = zipcodeCombiner.mergeZipcodes(sortedZipCodeList);
	    assertTrue(mergedZipcodeList.size() > 0);
	  }

	  public void testOverlappingRangeToReturnOneRange() {
	    ZipCode zipcode1 = new ZipCode(95000, 95005);
	    ZipCode zipcode2 = new ZipCode(95002, 95006);
	    List<ZipCode> zipcodeList = new LinkedList<ZipCode>();
	    zipcodeList.add(zipcode1);
	    zipcodeList.add(zipcode2);
	    ZipCodeCombiner zipcodeCombiner = new ZipCodeCombiner();
	    List<ZipCode> sortedZipCodeList = zipcodeCombiner.sortByLowerBounds(zipcodeList);
	    List<ZipCode> mergedZipcodeList = zipcodeCombiner.mergeZipcodes(sortedZipCodeList);
	    assertTrue(mergedZipcodeList.get(0).getUpperBound() == 95006);
	  }

	  public void testSortingBeforeMerging() {
	    ZipCode zipcode1 = new ZipCode(95000, 95005);
	    ZipCode zipcode2 = new ZipCode(95002, 95006);
	    List<ZipCode> zipcodeList = new LinkedList<ZipCode>();
	    zipcodeList.add(zipcode2);
	    zipcodeList.add(zipcode1);
	    ZipCodeCombiner zipcodeCombiner = new ZipCodeCombiner();
	    List<ZipCode> sortedZipCodeList = zipcodeCombiner.sortByLowerBounds(zipcodeList);
	    assertTrue(sortedZipCodeList.get(0) == zipcode1);
	  }

	  public void testLoadAfterCallingMerge() {
	    ZipCode zipcode1 = new ZipCode(95000, 95005);
	    ZipCode zipcode2 = new ZipCode(95007, 95008);
	    List<ZipCode> zipcodeList = new LinkedList<ZipCode>();
	    zipcodeList.add(zipcode1);
	    zipcodeList.add(zipcode2);
	    ZipCodeCombiner zipcodeCombiner = new ZipCodeCombiner();
	    List<ZipCode> sortedZipCodeList = zipcodeCombiner.sortByLowerBounds(zipcodeList);
	    List<ZipCode> mergedZipcodeList = zipcodeCombiner.mergeZipcodes(sortedZipCodeList);
	    assertEquals(mergedZipcodeList, zipcodeList);
	  }
}
