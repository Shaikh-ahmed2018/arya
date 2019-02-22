package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.AbsentsSMSPojo;
import com.centris.campus.serviceImpl.AbsentSMSServiceImpl;

public class AbsentSMSBD {

	public ArrayList<AbsentsSMSPojo> absentlist() {
		
		return new AbsentSMSServiceImpl().absentlist();
	}

	public String storeAbsentSms(AbsentsSMSPojo absentpojo) {
		
		return new AbsentSMSServiceImpl().storeAbsentSms(absentpojo);
	}

	public boolean validateAbsentSms(String date, String smstext) {
		
		return new AbsentSMSServiceImpl().validateAbsentSms(date,smstext);
	}

}
