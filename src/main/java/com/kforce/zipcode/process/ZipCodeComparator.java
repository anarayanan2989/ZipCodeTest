package com.kforce.zipcode.process;

import java.util.Comparator;

public class ZipCodeComparator implements Comparator<ZipCode> {
	public int compare(ZipCode zipCode1, ZipCode zipCode2) {
	    if (zipCode1.getLowerBound() > zipCode2.getLowerBound())
	      return 1;
	    else
	      return -1;
	  }
}
