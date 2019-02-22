package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class ClassForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String streamId;
	private String streamName;
	private String secDetailId;
	private String secDetailName;
	private String campusStreamCheckBox;
	private String createUser;
	private String modifyUser;
	public String getStreamId() {
		return streamId;
	}
	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public String getSecDetailId() {
		return secDetailId;
	}
	public void setSecDetailId(String secDetailId) {
		this.secDetailId = secDetailId;
	}
	public String getSecDetailName() {
		return secDetailName;
	}
	public void setSecDetailName(String secDetailName) {
		this.secDetailName = secDetailName;
	}
	public String getCampusStreamCheckBox() {
		return campusStreamCheckBox;
	}
	public void setCampusStreamCheckBox(String campusStreamCheckBox) {
		this.campusStreamCheckBox = campusStreamCheckBox;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
