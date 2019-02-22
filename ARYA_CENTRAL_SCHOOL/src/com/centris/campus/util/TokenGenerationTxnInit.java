package com.centris.campus.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.runner.Request;

public class TokenGenerationTxnInit {
	
	public static void main(String[] args) throws Exception {
		// class initialisations
					IFAESUtil aesutil=new IFAESUtil();
					IFHMACUtil hmacutil=new IFHMACUtil();
					
					//variable initialisations
					String encryptionkey="DCC4F202B207AD247437087C2D8A8247";
					String encryptioniv="JXEqSJJUa8P7QA2K";
					String signkey="HBsORxEEiO6UPsmjvye8DMd2OgUgraiq";
					String merchantid="APIMER";
					String merchantsubid="ACSTRI";
					String tokenaction="GENTOK";
					String txninitaction="TXNINIT";
					String feetype="ALL FEES";
					String totalamt="1.0";
					String replyurl=""; // reply url of merchant where txn init response has to be posted
					String iobpayapiurl="https://www.iobnet.co.in:8080/iobpay/iobpayRESTService/apitokenservice/generatenewtoken/";
					String iobpaytxniniturl="https://www.iobnet.co.in:8080/iobpay/apitxninit.do";
					String merchanttxnid="";// UNIQUE ID from merchant end
					
					//generating a new token
					JSONObject tokenjsonobj=new JSONObject();
					tokenjsonobj.put("merchantid",merchantid);
					tokenjsonobj.put("merchantsubid",merchantsubid);
					tokenjsonobj.put("action",tokenaction);
					tokenjsonobj.put("feetype",feetype);
					tokenjsonobj.put("totalamt",totalamt);
					tokenjsonobj.put("replyurl",replyurl);
					
					System.out.println("Token Generation : Before Encryption : "+tokenjsonobj.toString());
					String encryptedtokendata=aesutil.encrypt(tokenjsonobj.toString(), encryptioniv, encryptionkey);
					String signedtokenhmac=hmacutil.HmacSHA256(encryptedtokendata, signkey);
					JSONObject tokenrequestjson=new JSONObject();
					tokenrequestjson.put("merchantid", merchantid);
					tokenrequestjson.put("merchantsubid", merchantsubid);
					tokenrequestjson.put("action", tokenaction);
					tokenrequestjson.put("data", encryptedtokendata);
					tokenrequestjson.put("hmac", signedtokenhmac);
					System.out.println("Token Generation : Request Message : "+tokenrequestjson.toString());
					
					URL obj = new URL(iobpayapiurl);
					URLConnection con = (URLConnection) obj.openConnection();
					con.setReadTimeout(300000);
					con.setConnectTimeout(300000);
					con.setDoOutput(true);
					con.setRequestProperty("Content-Type", "application/json");
					DataOutputStream wr = new DataOutputStream(con.getOutputStream());
					wr.writeBytes(tokenrequestjson.toString());
					wr.flush();
					wr.close();
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
					while ((inputLine = in.readLine()) != null) 
					{
						response.append(inputLine);
					}
					in.close();
					System.out.println("response from token service : "+response.toString());
					
					JSONObject tokenjsonobjresponse=new JSONObject(response.toString());
					String decryptedtokendata=aesutil.decrypt(tokenjsonobjresponse.getString("data"), encryptioniv, encryptionkey);
					System.out.println("decrypted data from token service : "+decryptedtokendata);
					
					//getting tokenJSON from decryptedtokendata
					JSONObject tokenresponsejson=new JSONObject(decryptedtokendata);
					
					//generating request for txninit service
					JSONObject txninitjson=new JSONObject();
					txninitjson.put("merchantid", merchantid);
					txninitjson.put("merchantsubid", merchantsubid);
					txninitjson.put("action", txninitaction);
					txninitjson.put("feetype", feetype);
					txninitjson.put("totalamt", totalamt);
					txninitjson.put("tokenid",tokenresponsejson.get("tokenid"));
					txninitjson.put("merchanttxnid", merchanttxnid);
					txninitjson.put("udf1", "udf1test");
					txninitjson.put("udf2", "udf2test");
					txninitjson.put("udf3", "udf3test");
					
					//generate txninitrequestdataexchangeformat
					System.out.println("Txn Init : Before Encryption : "+txninitjson.toString());
					String encryptedtxninitreqdata=aesutil.encrypt(txninitjson.toString(), encryptioniv, encryptionkey);
					String signedtxninitreqhmac=hmacutil.HmacSHA256(encryptedtxninitreqdata, signkey);
					JSONObject txninitequestjson=new JSONObject();
					txninitequestjson.put("merchantid", merchantid);
					txninitequestjson.put("merchantsubid", merchantsubid);
					txninitequestjson.put("action", txninitaction);
					txninitequestjson.put("data", encryptedtxninitreqdata);
					txninitequestjson.put("hmac", signedtxninitreqhmac);
					
					
					
					/*req.setAttribute("redirecturl", iobpaytxniniturl);
					req.setAttribute("requestjson", txninitequestjson.toString());*/
					
					//pass this requestjson as reqjson parameter from browser using POST method form submission to iobpaytxniniturl url
	}
	

}
