/**
 * 
 */
package com.centris.campus.util;

/**
 * @author ratnaraju
 *
 */
public class ErrorMessage {

	/**
	 * ratna
	 * 
	 */
	
	private String message;
	private static ErrorMessage errorMsg=null;
	
	private ErrorMessage() {
	
	}
	
    public static ErrorMessage getInstance(){
    	if(errorMsg == null){
    		errorMsg=new ErrorMessage();
    	}
    	return errorMsg;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setContent(String msg) {
		if (message == null) {
			setMessage(msg);
		}
	}
}
