package org;

import java.util.Date;

public class Test {
	 public static void main(String[] args) {
		 	double doubleTime = (Math.floor(System.currentTimeMillis() / 60000L));
	        //往下取整 1.9=> 1.0
	        long floorValue = new Double(doubleTime).longValue()*60;
	        System.out.println(new Date().getTime());
	    }
}