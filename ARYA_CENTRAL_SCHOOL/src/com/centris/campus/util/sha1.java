package com.centris.campus.util;

import org.apache.commons.codec.binary.Base64;
public class sha1 {
	
	
public static String FDBSHAHASING(String data) {

	java.security.MessageDigest digest = null;
	String myString =data;
	  try {
		  
	 
	  digest = java.security.MessageDigest.getInstance("SHA-1");

	  digest.reset();
	  
	  digest.update(myString.getBytes("UTF-8"));
	  
	  } catch (Exception e) {
		System.out.println("Exception:"+e);
	  }
	  System.out.println( new String(Base64.encodeBase64(digest.digest())));
	  
	  return myString;

}

}

