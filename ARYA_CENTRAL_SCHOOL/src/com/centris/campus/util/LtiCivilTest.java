package com.centris.campus.util;
import com.itextpdf.text.log.SysoLogger;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.lti.civil.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LtiCivilTest  {
    public static void main(String[] args) throws Exception {	
    	
    	
    	
    	URL obj = new URL("http://192.168.1.103:9090/ID_CARD_WEBSERVICES/v1/get_kids?token=PAR1234&userName=parent@localhost");
	
	
	
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	con.setRequestMethod("GET");
	con.setRequestProperty("Content-Type", "application/json");
	int responseCode = con.getResponseCode();
	System.out.println("GET Response Code :: " + responseCode);
	if (responseCode == HttpURLConnection.HTTP_OK) { // success
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());
		JSONObject tokenjsonobjresponse=new JSONObject(response.toString());
		System.out.println(tokenjsonobjresponse.get("jsonResponse"));
		JSONObject abc=new JSONObject(tokenjsonobjresponse.get("jsonResponse").toString());
		System.out.println(abc.get("kid"));
	} else {
		System.out.println("GET request not worked");
	}
	}
    
    
   

    //-------------------------------------------------------------------------

    private static class CaptureHandler  implements CaptureObserver {
        
        public int numImages;
        
        public void onNewImage(CaptureStream captureStream, Image image) {
            numImages++;
        }

        public void onError(CaptureStream captureStream, CaptureException e) {
            System.out.println("Error during capture: " + e);
            e.printStackTrace();
        }
    }

}

