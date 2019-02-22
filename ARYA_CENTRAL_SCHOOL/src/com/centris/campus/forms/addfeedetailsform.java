package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class addfeedetailsform extends ActionForm 

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String feename;
	private String feedescription;
	public String getFeename() {
		return feename;
	}
	public void setFeename(String feename) {
		this.feename = feename;
	}
	public String getFeedescription() {
		return feedescription;
	}
	public void setFeedescription(String feedescription) {
		this.feedescription = feedescription;
	}
	
	
	
}
