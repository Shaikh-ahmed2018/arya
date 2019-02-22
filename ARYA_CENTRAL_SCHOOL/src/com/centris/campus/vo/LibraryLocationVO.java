package com.centris.campus.vo;

public class LibraryLocationVO {
	
	 private String schoolName;
	 private String libraryLocations;
	 private String description;
	 private String currentuserid;
	 private String locationid;
	 private String librarylocid;
	 
	public String getCurrentuserid() {
		return currentuserid;
	}
	public void setCurrentuserid(String currentuserid) {
		this.currentuserid = currentuserid;
	}
	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public String getLibrarylocid() {
		return librarylocid;
	}
	public void setLibrarylocid(String librarylocid) {
		this.librarylocid = librarylocid;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getLibraryLocations() {
		return libraryLocations;
	}
	public void setLibraryLocations(String libraryLocations) {
		this.libraryLocations = libraryLocations;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
