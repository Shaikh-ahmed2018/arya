package com.centris.campus.util;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class IFAESUtil {
	private byte[] getSHAHashedKey(String myKey) throws Exception {
		byte[] key = myKey.getBytes("UTF-8");
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		key = sha.digest(key);
		return key;
	}

	public String encrypt(String text,String myiv, String mykey) throws Exception
	{
		byte[] key=getSHAHashedKey(mykey);
		byte[] iv=myiv.getBytes("UTF-8");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		//Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");

		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(iv);

		cipher.init(1, keySpec, ivSpec);
		byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(results);
	}

	public String decrypt(String text,String myiv, String mykey) throws Exception 
	{
		byte[] key=getSHAHashedKey(mykey);
		byte[] iv=myiv.getBytes("UTF-8");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		cipher.init(2, keySpec, ivSpec);

		BASE64Decoder decoder = new BASE64Decoder();
		byte[] results = cipher.doFinal(decoder.decodeBuffer(text));
		return new String(results);
	}

	public static void main(String[] args) throws Exception 
	{
		String key = "A73AA83F2F93CF0C156161E2E4DE113F";
		String iv = "wm6zmr75BsCLBcp1";// should always be 16 digits
		String data = "test1234test1234";
		
		IFAESUtil util = new IFAESUtil();
		//System.out.println(new String(util.getSHAHashedKey(key)));
	   String encData = util.encrypt(data, iv,key);
		System.out.println("encData:" + encData);
		//encData="3YUibZaz09dwJUGRGBVwmg==";
		String decData = util.decrypt(encData.toString(), iv,key);
		System.out.println("decData:" + decData);
	}
}