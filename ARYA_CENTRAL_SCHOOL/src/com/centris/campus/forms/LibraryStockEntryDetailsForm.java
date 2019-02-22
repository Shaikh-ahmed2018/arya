package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

@SuppressWarnings("serial")
public class LibraryStockEntryDetailsForm extends ActionForm {
	
	private String accessionNo;
	private String itemType;
	private String regdate;
	
	private String bookTitle;
	private String author;
	private String category;
	private String languge;
	private String classDescription;
	private String divisionDescription;
	private String sectionDescription;
	private String dDC;
	private String publisher;
	private String no_of_Copies;
	private String publicationYear;
	private String edition;
	private String editor;
	private String iSBNNo;
	private String cost;
	private String billNo;
	private String billDate;
	private String size;
	private String no_of_Pages;
	private String suppliedBy;
	private String generalInfo;
	private String contentSearch;
	private String shelfNo;
	private String location;
	private String currentStatus;
	private String totalCost;
	private String createdby;
	private String ddc;
	private int slno;
	private String enteryid;
	private String date;
	private String haccessionNo;
	private String userType;
	
	private String subscriberName;
	private String designation;
	private String department;
	private String division;
	private String standard;
	private String address;
	private String contactNo;
	private String otherName;
	private String admissionNo;
	private String subscriberNo;
	private String studentId;
	private String staffId;
	private String returnDate;
	private int count;
	private String test;
	private String previousImage;
	
	
	public String getPreviousImage() {
		return previousImage;
	}
	public void setPreviousImage(String previousImage) {
		this.previousImage = previousImage;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getSubscriberName() {
		return subscriberName;
	}
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getOtherName() {
		return otherName;
	}
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	private FormFile bookimage;
	private String academicyear;
	private String bookPhoto;
	
	private String reservationId;
	private String issued_to;
	private String from_date;
	private String to_date;
	
	private String subscriber_id;
	
	
	public String getSubscriber_id() {
		return subscriber_id;
	}
	public void setSubscriber_id(String subscriber_id) {
		this.subscriber_id = subscriber_id;
	}
	public String getIssued_to() {
		return issued_to;
	}
	public void setIssued_to(String issued_to) {
		this.issued_to = issued_to;
	}
	public String getReservationId() {
		return reservationId;
	}
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	public FormFile getBookimage() {
		return bookimage;
	}
	public void setBookimage(FormFile bookimage) {
		this.bookimage = bookimage;
	}
	public String getBookPhoto() {
		return bookPhoto;
	}
	public void setBookPhoto(String bookPhoto) {
		this.bookPhoto = bookPhoto;
	}
	public String getHaccessionNo() {
		return haccessionNo;
	}
	public void setHaccessionNo(String haccessionNo) {
		this.haccessionNo = haccessionNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	
	public String getEnteryid() {
		return enteryid;
	}
	public void setEnteryid(String enteryid) {
		this.enteryid = enteryid;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getSlno() {
		return slno;
	}
	public void setSlno(int slno) {
		this.slno = slno;
	}
	public String getDdc() {
		return ddc;
	}
	public void setDdc(String ddc) {
		this.ddc = ddc;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getAccessionNo() {
		return accessionNo;
	}
	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLanguge() {
		return languge;
	}
	public void setLanguge(String languge) {
		this.languge = languge;
	}
	public String getClassDescription() {
		return classDescription;
	}
	public void setClassDescription(String classDescription) {
		this.classDescription = classDescription;
	}
	public String getDivisionDescription() {
		return divisionDescription;
	}
	public void setDivisionDescription(String divisionDescription) {
		this.divisionDescription = divisionDescription;
	}
	public String getSectionDescription() {
		return sectionDescription;
	}
	public void setSectionDescription(String sectionDescription) {
		this.sectionDescription = sectionDescription;
	}
	public String getdDC() {
		return dDC;
	}
	public void setdDC(String dDC) {
		this.dDC = dDC;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getNo_of_Copies() {
		return no_of_Copies;
	}
	public void setNo_of_Copies(String no_of_Copies) {
		this.no_of_Copies = no_of_Copies;
	}
	public String getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getiSBNNo() {
		return iSBNNo;
	}
	public void setiSBNNo(String iSBNNo) {
		this.iSBNNo = iSBNNo;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getNo_of_Pages() {
		return no_of_Pages;
	}
	public void setNo_of_Pages(String no_of_Pages) {
		this.no_of_Pages = no_of_Pages;
	}
	public String getSuppliedBy() {
		return suppliedBy;
	}
	public void setSuppliedBy(String suppliedBy) {
		this.suppliedBy = suppliedBy;
	}
	public String getGeneralInfo() {
		return generalInfo;
	}
	public void setGeneralInfo(String generalInfo) {
		this.generalInfo = generalInfo;
	}
	public String getContentSearch() {
		return contentSearch;
	}
	public void setContentSearch(String contentSearch) {
		this.contentSearch = contentSearch;
	}
	public String getShelfNo() {
		return shelfNo;
	}
	public void setShelfNo(String shelfNo) {
		this.shelfNo = shelfNo;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getAcademicyear() {
		return academicyear;
	}
	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}
	public String getSubscriberNo() {
		return subscriberNo;
	}
	public void setSubscriberNo(String subscriberNo) {
		this.subscriberNo = subscriberNo;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
	
	
	
}
