package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class AcadamicYearPlanForm extends ActionForm{
	
	
	private String title;
	private String date;
	private String dayOfWeekId;
	private String starttime;
	private String endtime;
	private FormFile file;
	private String description;
	private String heventId;
	private String hFile;
	private String venue;
	private String locationId;
	private String locationName;
	private String teachertype;
	
	
	
	
	public String getTeachertype() {
		return teachertype;
	}
	public void setTeachertype(String teachertype) {
		this.teachertype = teachertype;
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
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String gethFile() {
		return hFile;
	}
	public void sethFile(String hFile) {
		this.hFile = hFile;
	}
	public String getHeventId() {
		return heventId;
	}
	public void setHeventId(String heventId) {
		this.heventId = heventId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDayOfWeekId() {
		return dayOfWeekId;
	}
	public void setDayOfWeekId(String dayOfWeekId) {
		this.dayOfWeekId = dayOfWeekId;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	
	
	

}
