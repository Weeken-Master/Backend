package com.api.link.handel;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import com.datastax.oss.driver.shaded.guava.common.hash.Hashing;


public class CreateURL{
    public static final String ALPHABET = "234bcdfghj5kmn6789pqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_";
	// public static final String ALPHABET = "9bcdfg";
	public static final int BASE = ALPHABET.length();

  
    
   
    public static  String encode(int countnum) {   
		
		StringBuilder str = new StringBuilder();
		while (countnum > 0) {
			str.insert(0, ALPHABET.charAt(countnum % BASE));
			countnum = countnum / BASE;
		}
		return str.toString();
	}


}
