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
	    ZipCode zipcode1 = new ZipCode(49679,52015);
	    ZipCode zipcode2 = new ZipCode(49800,50000);
	    ZipCode zipcode3 = new ZipCode(51500,53479);
	    ZipCode zipcode4 = new ZipCode(45012,46937);
	    ZipCode zipcode5 = new ZipCode(54012,59607);
	    ZipCode zipcode6 = new ZipCode(45500,45590);
	    ZipCode zipcode7 = new ZipCode(45999,47900);
	    ZipCode zipcode8 = new ZipCode(44000,45000);
	    ZipCode zipcode9 = new ZipCode(43012,45950);
	    List<ZipCode> zipcodeList = new LinkedList<ZipCode>();
	    zipcodeList.add(zipcode1);
	    zipcodeList.add(zipcode2);
	    zipcodeList.add(zipcode3);
	    zipcodeList.add(zipcode4);
	    zipcodeList.add(zipcode5);
	    zipcodeList.add(zipcode6);
	    zipcodeList.add(zipcode7);
	    zipcodeList.add(zipcode8);
	    zipcodeList.add(zipcode9);
	    ZipCodeCombiner zipcodeCombiner = new ZipCodeCombiner();
	    List<ZipCode> sortedZipCodeList = zipcodeCombiner.sortByLowerBounds(zipcodeList);
	    List<ZipCode> mergedZipcodeList = zipcodeCombiner.mergeZipcodes(sortedZipCodeList);
	    assertTrue(mergedZipcodeList.get(0).getUpperBound() == 47900);
	  }

	  public void testSortingBeforeMerging() {
		ZipCode zipcode1 = new ZipCode(49679,52015);
		ZipCode zipcode2 = new ZipCode(49800,50000);
		ZipCode zipcode3 = new ZipCode(51500,53479);
		ZipCode zipcode4 = new ZipCode(45012,46937);
		ZipCode zipcode5 = new ZipCode(54012,59607);
		ZipCode zipcode6 = new ZipCode(45500,45590);
		ZipCode zipcode7 = new ZipCode(45999,47900);
		ZipCode zipcode8 = new ZipCode(44000,45000);
		ZipCode zipcode9 = new ZipCode(43012,45950);
		List<ZipCode> zipcodeList = new LinkedList<ZipCode>();
		zipcodeList.add(zipcode1);
	    zipcodeList.add(zipcode2);
	    zipcodeList.add(zipcode3);
	    zipcodeList.add(zipcode4);
	    zipcodeList.add(zipcode5);
	    zipcodeList.add(zipcode6);
	    zipcodeList.add(zipcode7);
	    zipcodeList.add(zipcode8);
	    zipcodeList.add(zipcode9);
	    ZipCodeCombiner zipcodeCombiner = new ZipCodeCombiner();
	    List<ZipCode> sortedZipCodeList = zipcodeCombiner.sortByLowerBounds(zipcodeList);
	    assertTrue(sortedZipCodeList.get(0) == zipcode9);
	  }

	  public void testLoadAfterCallingMerge() {
		ZipCode zipcode1 = new ZipCode(49679, 52015);
		ZipCode zipcode2 = new ZipCode(49800, 50000);
		ZipCode zipcode3 = new ZipCode(51500, 53479);
		ZipCode zipcode4 = new ZipCode(45012, 46937);
		ZipCode zipcode5 = new ZipCode(54012, 59607);
		ZipCode zipcode6 = new ZipCode(45500, 45590);
		ZipCode zipcode7 = new ZipCode(45999, 47900);
		ZipCode zipcode8 = new ZipCode(44000, 45000);
		ZipCode zipcode9 = new ZipCode(43012, 45950);
	    List<ZipCode> zipcodeList = new LinkedList<ZipCode>();
	    zipcodeList.add(zipcode1);
	    zipcodeList.add(zipcode2);
	    zipcodeList.add(zipcode3);
	    zipcodeList.add(zipcode4);
	    zipcodeList.add(zipcode5);
	    zipcodeList.add(zipcode6);
	    zipcodeList.add(zipcode7);
	    zipcodeList.add(zipcode8);
	    zipcodeList.add(zipcode9);
	    ZipCodeCombiner zipcodeCombiner = new ZipCodeCombiner();
	    List<ZipCode> sortedZipCodeList = zipcodeCombiner.sortByLowerBounds(zipcodeList);
	    List<ZipCode> mergedZipcodeList = zipcodeCombiner.mergeZipcodes(sortedZipCodeList);
	    assertEquals(mergedZipcodeList, zipcodeList);
	  }
}
