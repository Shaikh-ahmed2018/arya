package com.centris.campus.util;
import java.util.logging.FileHandler;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;



public class JLogger
{
	private static Logger objLogger = null;

	static 
	{
		try    
		{
			objLogger = Logger.getLogger("");
			Handler[] h = objLogger.getHandlers();
			for(int i=0;i<h.length;i++)
				objLogger.removeHandler(h[i]);

			
			String propery_path= JPropertyReader.getProperty("LSTMS_LOG_PATH".trim());
			
			Handler fh = new FileHandler(propery_path);
			fh.setFormatter(new SimpleFormatter());
			objLogger.addHandler(fh);
		}
		catch(Exception ex)
		{
		}
	} 
		

	public static String getlog4JPropertyPath() throws Exception
	{
		String propery_path= JPropertyReader.readlog4JProperty("LOG4J_PATH".trim());
	    
		
		return propery_path;
	}
	
	
	
	public static synchronized void log(int level,String msg)
	{
		
		StackTraceElement ste[] = new Throwable().getStackTrace();
		String sClass = ste[1].getClassName();
		String sMethod = ste[1].getMethodName();

		if(level == 0)
			objLogger.logp(Level.INFO,sClass,sMethod,msg); // SEVERITY LEVEL FOR DEVELOPMENT COSNTRCUTION PHASE 
		// OTHER SEVERITY LEVELS NEEDS TO BE HANDLED ACCORDING TO PRIORTIY		
		
		else if(level == 1)
			objLogger.logp(Level.WARNING,sClass,sMethod,msg);
	}

	public static synchronized void log(int level,String msg,Throwable t)
	{
		StackTraceElement ste[] = new Throwable().getStackTrace();
		String sClass = ste[1].getClassName();
		String sMethod = ste[1].getMethodName();

		if(level == -1)
			objLogger.logp(Level.SEVERE,sClass,sMethod,msg,t);
		else if(level == 1)
			objLogger.logp(Level.WARNING,sClass,sMethod,msg,t);
	}
}