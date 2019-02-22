package com.centris.campus.admin;

public class SMSThread implements Runnable{

	private String code;
	private String date;
	private String category;
	
	public SMSThread(String code, String date,String category) {
	     this.code=code;
	     this.date=date;
	     this.category=category;
	   }
	
	
	
	
	@Override
	public void run() {
		
		System.out.println("Inside SMS THREAD--------------->");
		System.out.println("code" +code + "date " +date + "category" +category) ;
		
		if("Holiday".equals(category)){
			new SMSDetails().getHolidayDetails(code,date);
		}
		else if("Meeting".equals(category)){
			new SMSDetails().getMeetingDetails(code,date);
		}
		else if("Event".equals(category)){
			new SMSDetails().getEventDetails(code,date);
		}
		else if("Late".equals(category)){
			new SMSDetails().getLateComingStudentDetails(code,date);
		}
		else if("Uniform".equals(category)){
			new SMSDetails().getUniformDetails(code,date);
		}
		else if("Absent".equals(category)){
			new SMSDetails().getAbsentDetails(code,date);
		}
		else if("Birthday".equals(category)){
			new SMSDetails().getBirthdayWishesDetails(code,date);
		}
		else if("Fee".equals(category)){
			new SMSDetails().getFeeDetails(code,date);
		}
	}
	
}
