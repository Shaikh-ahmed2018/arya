package com.centris.campus.actions;

public class TheMainActionClass {
	
	public static void main(String[] args)
	{
		
	
		
		try {
			
			System.out.println("TheMainActionClass ---------- Main Method Started");
			SendBirthdayWishesSMS action = new SendBirthdayWishesSMS();
			action.SendBday();
			
			
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
