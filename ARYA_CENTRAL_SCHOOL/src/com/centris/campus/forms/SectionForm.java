package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class SectionForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	private String sectionId;
	private String sectionName;
	private String secDetailsId;
	private String secDetailsName;
	private String sectionStrength;
	private String streamName;
	private String streamId;
	private String createUser;
	private String modifyUser;
	private String status;
	private String secId;
	private String campusClassSectionCheckBox;
	private String classId;
	private String locationId;
	private String locationName;
	
	private String className;
	
	private String strength;

	

	
	
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getSecDetailsId() {
		return secDetailsId;
	}
	public void setSecDetailsId(String secDetailsId) {
		this.secDetailsId = secDetailsId;
	}
	public String getSecDetailsName() {
		return secDetailsName;
	}
	public void setSecDetailsName(String secDetailsName) {
		this.secDetailsName = secDetailsName;
	}
	public String getSectionStrength() {
		return sectionStrength;
	}
	public void setSectionStrength(String sectionStrength) {
		this.sectionStrength = sectionStrength;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public String getStreamId() {
		return streamId;
	}
	public void setStreamId(String streamId) {
		this.streamId = streamId;
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
	public String getCampusClassSectionCheckBox() {
		return campusClassSectionCheckBox;
	}
	public void setCampusClassSectionCheckBox(String campusClassSectionCheckBox) {
		this.campusClassSectionCheckBox = campusClassSectionCheckBox;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSecId() {
		return secId;
	}
	public void setSecId(String secId) {
		this.secId = secId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	

}
