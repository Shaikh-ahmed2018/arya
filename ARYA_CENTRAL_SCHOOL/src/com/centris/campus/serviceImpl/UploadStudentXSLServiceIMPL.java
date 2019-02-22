package com.centris.campus.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

//import org.apache.catalina.startup.SetAllPropertiesRule;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.UploadStudentXLSDaoImpl;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.service.UploadStudentXSLservice;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.UploadStudentXlsVO;
import com.itextpdf.text.log.SysoLogger;


public class UploadStudentXSLServiceIMPL implements UploadStudentXSLservice {
	
	private static Logger logger = Logger.getLogger(UploadStudentXSLServiceIMPL.class);


	public Set<UploadStudentXlsVO> insertEmpXSL(List<UploadStudentXlsPOJO> list, String username, String duplicate,String currentLoc) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLServiceImpl : insertEmpXSL : Starting");
		
		Connection connection = null;
		int checkcategory=0;
		int checkClassCode=0;
		int checkSectionCode=0;
		List<UploadStudentXlsPOJO> successlist=new ArrayList<UploadStudentXlsPOJO>();
		UploadStudentXLSDaoImpl daoImpl=new UploadStudentXLSDaoImpl();
	
		int count = 0;
	
		Set<UploadStudentXlsVO> failurelist = new LinkedHashSet<UploadStudentXlsVO>();
	
		failurelist.clear();
		successlist.clear();

		try {
			
			connection=JDBCConnection.getSeparateConnection();
			
			String int_regex="^[0-9]*$";
			String datePattern = "\\d{1,2}\\-\\d{1,2}\\-\\d{4}";
			 
			for (Iterator<UploadStudentXlsPOJO> iterator = list.iterator(); iterator.hasNext();) {

				UploadStudentXlsPOJO uploadStudentXSLPOJO = (UploadStudentXlsPOJO) iterator.next();
				UploadStudentXlsVO uploadStudentXSLVo = new UploadStudentXlsVO();
				uploadStudentXSLPOJO.setCreateUser(username);
				uploadStudentXSLPOJO.setCurrentLocation(currentLoc);
				uploadStudentXSLVo.setCreateUser(username);
				uploadStudentXSLVo.setCurrentLocation(currentLoc);
				uploadStudentXSLVo.setStudentFirstName(uploadStudentXSLPOJO.getStudentFirstName()+" "+uploadStudentXSLPOJO.getStudentLastName());
				uploadStudentXSLVo.setStudentLastName(uploadStudentXSLPOJO.getStudentLastName());
				uploadStudentXSLVo.setStudentAdmissionNo(uploadStudentXSLPOJO.getStudentAdmissionNo());
				uploadStudentXSLVo.setStudentRegNo(uploadStudentXSLPOJO.getApplicationNo());
				uploadStudentXSLVo.setDateofJoin(uploadStudentXSLPOJO.getDateofJoin());
				
				uploadStudentXSLVo.setAcademicYear(uploadStudentXSLPOJO.getAcademicYear());
				uploadStudentXSLVo.setCategory(uploadStudentXSLPOJO.getCategory());
				uploadStudentXSLVo.setClassname(uploadStudentXSLPOJO.getClassname());
				
				uploadStudentXSLVo.setRollno(uploadStudentXSLPOJO.getRollno());
				uploadStudentXSLVo.setHousename(uploadStudentXSLPOJO.getHousename());
				
				uploadStudentXSLVo.setSectionname(uploadStudentXSLPOJO.getSectionname());
				uploadStudentXSLVo.setStudentquotaname(uploadStudentXSLPOJO.getStudentquotaname());
				uploadStudentXSLVo.setGrade(uploadStudentXSLPOJO.getGrade());
				uploadStudentXSLVo.setRte(uploadStudentXSLPOJO.getRte());
				uploadStudentXSLVo.setEmisNo(uploadStudentXSLPOJO.getEmisNo());
				uploadStudentXSLVo.setHostel(uploadStudentXSLPOJO.getHostel());
				uploadStudentXSLVo.setConcession_applicable(uploadStudentXSLPOJO.getConcession_applicable());
				uploadStudentXSLVo.setConcession_type(uploadStudentXSLPOJO.getConcession_type());
				uploadStudentXSLVo.setTransport(uploadStudentXSLPOJO.getTransport());
				uploadStudentXSLVo.setTranscategory(uploadStudentXSLPOJO.getTranscategory());
				uploadStudentXSLVo.setTranslocation(uploadStudentXSLPOJO.getTranslocation());
				uploadStudentXSLVo.setDateofBirth(uploadStudentXSLPOJO.getDateofBirth());
				uploadStudentXSLVo.setAge(uploadStudentXSLPOJO.getAge());
				uploadStudentXSLVo.setGender(uploadStudentXSLPOJO.getGender());
				uploadStudentXSLVo.setIdentificationMarks(uploadStudentXSLPOJO.getIdentificationMarks());
				uploadStudentXSLVo.setBloodGroup(uploadStudentXSLPOJO.getBloodGroup());
				uploadStudentXSLVo.setReligion(uploadStudentXSLPOJO.getReligion());
				uploadStudentXSLVo.setCaste(uploadStudentXSLPOJO.getCaste());
				uploadStudentXSLVo.setMedicalhistory(uploadStudentXSLPOJO.getMedicalhistory());
				uploadStudentXSLVo.setPhysicallyChallenged(uploadStudentXSLPOJO.getPhysicallyChallenged());
				uploadStudentXSLVo.setNationality(uploadStudentXSLPOJO.getNationality());
				uploadStudentXSLVo.setPhysicalchalreason(uploadStudentXSLPOJO.getPhysicalchalreason());
				uploadStudentXSLVo.setRemarks(uploadStudentXSLPOJO.getRemarks());
				uploadStudentXSLVo.setStudentstatus(uploadStudentXSLPOJO.getStudentstatus());
				uploadStudentXSLVo.setSibilingadminno(uploadStudentXSLPOJO.getSibilingadminno());
				uploadStudentXSLVo.setSibilingClass(uploadStudentXSLPOJO.getSibilingClass());
				uploadStudentXSLVo.setSibilingName(uploadStudentXSLPOJO.getSibilingName());
				uploadStudentXSLVo.setPrimaryPerson(uploadStudentXSLPOJO.getPrimaryPerson());
				uploadStudentXSLVo.setFatherName(uploadStudentXSLPOJO.getFatherName());
				uploadStudentXSLVo.setFatherMobileNo(uploadStudentXSLPOJO.getFatherMobileNo());
				uploadStudentXSLVo.setFatherQualification(uploadStudentXSLPOJO.getFatherQualification());
				uploadStudentXSLVo.setFatheroccupation(uploadStudentXSLPOJO.getFatheroccupation());
				uploadStudentXSLVo.setFatheremailId(uploadStudentXSLPOJO.getFatheremailId());
				uploadStudentXSLVo.setMotherName(uploadStudentXSLPOJO.getMotherName());
				uploadStudentXSLVo.setMotherMobileNo(uploadStudentXSLPOJO.getMotherMobileNo());
				uploadStudentXSLVo.setMotherQualification(uploadStudentXSLPOJO.getMotherQualification());
				uploadStudentXSLVo.setMotheroccupation(uploadStudentXSLPOJO.getMotheroccupation());
				uploadStudentXSLVo.setMotheremailId(uploadStudentXSLPOJO.getMotheremailId());
				uploadStudentXSLVo.setGuardianName(uploadStudentXSLPOJO.getGuardianName());
				uploadStudentXSLVo.setGuardianemailId(uploadStudentXSLPOJO.getGuardianemailId());
				uploadStudentXSLVo.setGuardianMobileNo(uploadStudentXSLPOJO.getGuardianMobileNo());
				uploadStudentXSLVo.setAddress(uploadStudentXSLPOJO.getAddress());
				uploadStudentXSLVo.setDob_in_words(uploadStudentXSLPOJO.getDob_in_words());
				uploadStudentXSLVo.setFatherDesignation(uploadStudentXSLPOJO.getFatherDesignation());
				uploadStudentXSLVo.setFatherOffAddress(uploadStudentXSLPOJO.getFatherOffAddress());
				uploadStudentXSLVo.setMotherDesignation(uploadStudentXSLPOJO.getMotherDesignation());
				uploadStudentXSLVo.setMotherOffAddress(uploadStudentXSLPOJO.getMotherOffAddress());
				uploadStudentXSLVo.setGuardianDesignation(uploadStudentXSLPOJO.getGuardianDesignation());
				uploadStudentXSLVo.setGuardianOffAddress(uploadStudentXSLPOJO.getGuardianOffAddress());
				uploadStudentXSLVo.setEmegencyNo(uploadStudentXSLPOJO.getEmegencyNo());
				uploadStudentXSLVo.setSmsNo(uploadStudentXSLPOJO.getSmsNo());
				uploadStudentXSLVo.setSecondLanguage(uploadStudentXSLPOJO.getSecondLanguage());
				uploadStudentXSLVo.setThirdLanguage(uploadStudentXSLPOJO.getThirdLanguage());
				
				
				String siblingId="";
				String routeId="";
				String sectionId="";
				String specilizationId="";
				String houseid = "";
				int checkClass=0;
				int checkSection=0;
				int checkSpecilization = 0;
				String classId = "";
				String secondLanguage="";
				String thirdLangauge="";
				String stuimgurl = "FIles/STUDENTIMAGES/"+uploadStudentXSLPOJO.getAcademicYear()+"/"+uploadStudentXSLPOJO.getStudentAdmissionNo()+".jpg";
				String schoolLocationId=daoImpl.getSchoolLocationId(uploadStudentXSLPOJO.getSchoolName(),connection);
				String stream=daoImpl.getStreamId(uploadStudentXSLPOJO.getCategory(),schoolLocationId,connection);
				
				if(!uploadStudentXSLPOJO.getClassname().equalsIgnoreCase("") || uploadStudentXSLPOJO.getClassname()== null)
				classId=daoImpl.getClassId(uploadStudentXSLPOJO.getClassname(),connection);
			
				String religionId=daoImpl.getReligionId(uploadStudentXSLPOJO.getReligion(),connection);
				
				String casteId=daoImpl.getCasteId(uploadStudentXSLPOJO.getCaste(),religionId,connection);
				
				String casteCategoryId=daoImpl.getCasteCategoryId(uploadStudentXSLPOJO.getCasteCategory(),casteId,religionId,connection);
				
				
				String academicYearId=daoImpl.getAcademicYearId(uploadStudentXSLPOJO.getAcademicYear(),connection);
				
				
				String fatherOccupationId = null;
				String motherOccupationId = null;
				String guardianOccupation = null;
				
				if(!uploadStudentXSLPOJO.getFatheroccupation().equalsIgnoreCase("") && uploadStudentXSLPOJO.getFatheroccupation() != null){
					fatherOccupationId=daoImpl.getOccupationId(uploadStudentXSLPOJO.getFatheroccupation(), connection);
				}
				
				if(!uploadStudentXSLPOJO.getGuardianOccupation().equalsIgnoreCase("") && uploadStudentXSLPOJO.getGuardianOccupation() != null){
					guardianOccupation= daoImpl.getOccupationId(uploadStudentXSLPOJO.getGuardianOccupation(), connection);
				}
				if(!uploadStudentXSLPOJO.getMotheroccupation().equalsIgnoreCase("") && uploadStudentXSLPOJO.getMotheroccupation() != null){
					motherOccupationId = daoImpl.getOccupationId(uploadStudentXSLPOJO.getMotheroccupation(), connection);
				}
				
				if(!uploadStudentXSLPOJO.getSecondLanguage().equalsIgnoreCase("") && uploadStudentXSLPOJO.getSecondLanguage() !=null){
					secondLanguage=daoImpl.getSubjectId(classId, uploadStudentXSLPOJO.getSecondLanguage().trim(), connection);
				}
				if(!uploadStudentXSLPOJO.getThirdLanguage().equalsIgnoreCase("") && uploadStudentXSLPOJO.getThirdLanguage() !=null){
					thirdLangauge=daoImpl.getSubjectId(classId, uploadStudentXSLPOJO.getThirdLanguage().trim(), connection);
				}
				String dob=uploadStudentXSLPOJO.getDateofBirth();
				
				String admissionDate=uploadStudentXSLPOJO.getDateofJoin();
				
				int ageOnDays=0;
				if(dob != null && !dob.equalsIgnoreCase("") && admissionDate !=null && !admissionDate.equalsIgnoreCase("")){
					List<String> dayList=HelperClass.getDateListBetweenDates(HelperClass.convertUIToDatabase(dob), HelperClass.convertUIToDatabase(admissionDate));
					ageOnDays=dayList.size();
				}
				
				
				if(stream !=null && stream!="" && classId!=""){
					checkClass=daoImpl.checkClassByStream(stream,classId, connection);
				}
				
				if(classId != null && !classId.equalsIgnoreCase("")  && !uploadStudentXSLPOJO.getSectionname().equalsIgnoreCase("")){
					sectionId=daoImpl.getSectionId(uploadStudentXSLPOJO.getSectionname(), classId,connection);
				}
				
				if(classId !=null && !classId.equalsIgnoreCase("") && sectionId!=""){
					checkSection=daoImpl.checkSectionByClass(classId, sectionId, connection);
				}
				if(!uploadStudentXSLPOJO.getHousename().equalsIgnoreCase("") && uploadStudentXSLPOJO.getHousename()!=null && schoolLocationId !=null && !schoolLocationId.equalsIgnoreCase("") && academicYearId!=null && !academicYearId.equalsIgnoreCase("")){
					houseid = daoImpl.getHouseId(uploadStudentXSLPOJO.getHousename(),schoolLocationId,academicYearId,connection);
				}
				if(!uploadStudentXSLPOJO.getSpecilization().equalsIgnoreCase("") && uploadStudentXSLPOJO.getSpecilization()!=null && !stream.equalsIgnoreCase("") && !classId.equalsIgnoreCase("") ){
					specilizationId=daoImpl.getSpecilizationId(uploadStudentXSLPOJO.getSpecilization(),stream,classId,connection);
					checkSpecilization=daoImpl.checkSpecilizationByStream(stream, classId, specilizationId, connection);
				}
				if(uploadStudentXSLPOJO.getSibilingadminno() !=null ){
					siblingId=daoImpl.getSiblingId(uploadStudentXSLPOJO.getSibilingadminno(),connection);
				}
				
				String transportTypeId="";
				if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") || !uploadStudentXSLPOJO.getTranscategory().equalsIgnoreCase("")){
					transportTypeId=daoImpl.getTransportTypeId(uploadStudentXSLPOJO.getTranscategory(),connection);
				}
				String transportLocationId="";
				if(transportTypeId !="" && !uploadStudentXSLPOJO.getTranslocation().equalsIgnoreCase("")){
					transportLocationId=daoImpl.getTransportLocationId(uploadStudentXSLPOJO.getTranslocation(),connection);
				}
				
				if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") && !uploadStudentXSLPOJO.getRoute().equalsIgnoreCase("")){
					routeId=daoImpl.getRouteId(uploadStudentXSLPOJO.getRoute(),connection);	
				}
				
				count = daoImpl.checkStudentID(uploadStudentXSLPOJO.getStudentAdmissionNo(),connection);
				checkcategory=daoImpl.checkCategorycode(uploadStudentXSLPOJO.getCategory(), connection);
				checkClassCode=daoImpl.checkClassCode(uploadStudentXSLPOJO.getClassname(), connection);
				checkSectionCode=daoImpl.checkSectionCode(uploadStudentXSLPOJO.getSectionname(),connection);
				
				
				
				if(count!=0){

					uploadStudentXSLVo.setReason("Student Admission No Already Exist");
					failurelist.add(uploadStudentXSLVo);
				
				}
				else if(uploadStudentXSLPOJO.getStudentFirstName()=="" || uploadStudentXSLPOJO.getStudentFirstName().equals("-")){
					uploadStudentXSLVo.setReason("Student First Name Should Not Empty");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getStudentAdmissionNo()=="" || uploadStudentXSLPOJO.getStudentAdmissionNo().equals("-")){
					uploadStudentXSLVo.setReason("Student Admission No Should Not Empty");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getDuplicateInExcel()==1){
					uploadStudentXSLVo.setReason("Duplicate Admission No in Excel file.");
					failurelist.add(uploadStudentXSLVo);
				
				
				}else if(uploadStudentXSLPOJO.getAcademicYear()=="" || uploadStudentXSLPOJO.getAcademicYear().equals("-")){

					uploadStudentXSLVo.setReason("Student Academic Year Should Not Empty");
					failurelist.add(uploadStudentXSLVo);
				}else if(academicYearId ==null || academicYearId.equalsIgnoreCase("")){
					uploadStudentXSLVo.setReason("Invalid Academic Year");
					failurelist.add(uploadStudentXSLVo);
				
			   }else if(uploadStudentXSLPOJO.getDateofJoin()=="" || uploadStudentXSLPOJO.getDateofJoin().equals("-")){
					uploadStudentXSLVo.setReason("Admision Date Should Not Empty");
					failurelist.add(uploadStudentXSLVo);
				}else if(uploadStudentXSLPOJO.getCategory()=="" || uploadStudentXSLPOJO.getCategory().equals("-")){
					uploadStudentXSLVo.setReason("Stream Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}else if(stream==null || stream.equalsIgnoreCase("")){
					uploadStudentXSLVo.setReason("Stream Doesn't Exist");
					failurelist.add(uploadStudentXSLVo);
				}else if(uploadStudentXSLPOJO.getClassname()=="" || uploadStudentXSLPOJO.getClassname().equals("-")){
					uploadStudentXSLVo.setReason("Class Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}else if(checkClass ==0){
					uploadStudentXSLVo.setReason("Class is not avilable for selected stream.");
					failurelist.add(uploadStudentXSLVo);
				}else if(classId==null || classId.equalsIgnoreCase("")){
					uploadStudentXSLVo.setReason("Class Doesn't Exist");
					failurelist.add(uploadStudentXSLVo);
				}else if(checkSection==0 && (uploadStudentXSLPOJO.getSectionname() != null || !uploadStudentXSLPOJO.getSectionname().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Section is not available for selected class.");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(checkSpecilization==0 && !(uploadStudentXSLPOJO.getSpecilization().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Specilization is not available for selected class.");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(!(uploadStudentXSLPOJO.getRollno().matches(int_regex))){
					uploadStudentXSLVo.setReason("Enter Valid Roll No.");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(!uploadStudentXSLPOJO.getHousename().equalsIgnoreCase("") && (houseid==null || houseid.equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("House Name Not Found for this Academic Year and School Location");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(schoolLocationId==null || schoolLocationId.equalsIgnoreCase("")){
						uploadStudentXSLVo.setReason("School Name(Location) Doesn't Exist");
						failurelist.add(uploadStudentXSLVo);
				}
				
				else if(uploadStudentXSLPOJO.getIsParentsGuardianWorking().equalsIgnoreCase("yes")){
					uploadStudentXSLVo.setReason("Working parents/guardian name is empty");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getTransport()=="" || uploadStudentXSLPOJO.getTransport().equals("-")){
					uploadStudentXSLVo.setReason("Transport Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}
				/*else if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") && (uploadStudentXSLPOJO.getTranscategory()==null || uploadStudentXSLPOJO.getTranscategory().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Transport Type is Empty.");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") && (transportTypeId==null || transportTypeId.equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Transport Type Doesn't Exist");
					failurelist.add(uploadStudentXSLVo);
				}*/
				else if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") && (uploadStudentXSLPOJO.getTranslocation()==null || uploadStudentXSLPOJO.getTranslocation().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Transport Location is Empty.");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") && (transportLocationId==null || transportLocationId.equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Transport Location(Stage) Doesn't Exist");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") && (uploadStudentXSLPOJO.getRoute()==null || uploadStudentXSLPOJO.getRoute().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Route is Empty.");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") && (routeId==null || routeId.equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Route Doesn't Exist");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getDateofBirth()=="" || uploadStudentXSLPOJO.getDateofBirth().equals("-")){
					uploadStudentXSLVo.setReason("Enter Date of Birth");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(!(uploadStudentXSLPOJO.getDateofBirth().matches(datePattern))){
					uploadStudentXSLVo.setReason("Enter Correct DOB (dd/mm/yyyy");
					failurelist.add(uploadStudentXSLVo);
				}
				
				
				else if(ageOnDays < 730){
					   uploadStudentXSLVo.setReason("Age of Student is less than 2 years.");
					   failurelist.add(uploadStudentXSLVo);
				}
				
				else if(uploadStudentXSLPOJO.getGender()==""  || uploadStudentXSLPOJO.getGender().equals("-")){
					uploadStudentXSLVo.setReason("Gender Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}
				/*else if(uploadStudentXSLPOJO.getReligion()==""  || uploadStudentXSLPOJO.getReligion().equals("-")){
					System.out.println("RELIGION");
					uploadStudentXSLVo.setReason("Religion Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}*/
				else if(!uploadStudentXSLPOJO.getReligion().equalsIgnoreCase("") && (religionId==null || religionId.equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Religion Doesn't Exist");
					failurelist.add(uploadStudentXSLVo);
				}
				/*else if(uploadStudentXSLPOJO.getCaste()==""  || uploadStudentXSLPOJO.getCaste().equals("-")){
					System.out.println("CASTE");
					uploadStudentXSLVo.setReason("Caste Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}*/
				else if(!uploadStudentXSLPOJO.getCaste().equalsIgnoreCase("") && (casteId==null || casteId.equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Caste Doesn't Exist");
					failurelist.add(uploadStudentXSLVo);
				}
				/*else if(uploadStudentXSLPOJO.getCasteCategory()==""  || uploadStudentXSLPOJO.getCasteCategory().equals("-")){
					System.out.println("CASTE");
					uploadStudentXSLVo.setReason("Caste Category Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}*/
				else if(!uploadStudentXSLPOJO.getCasteCategory().trim().equalsIgnoreCase("") && (casteCategoryId==null || casteCategoryId.equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Caste Category Doesn't Exist");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getNationality()==null || uploadStudentXSLPOJO.getNationality()==""  || uploadStudentXSLPOJO.getNationality().equals("-")){
					uploadStudentXSLVo.setReason("Nationality Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
			
				}
				else if(uploadStudentXSLPOJO.getPhysicallyChallenged().equalsIgnoreCase("yes") && (uploadStudentXSLPOJO.getPhysicalchalreason()==null || uploadStudentXSLPOJO.getPhysicalchalreason().equalsIgnoreCase(""))){
						uploadStudentXSLVo.setReason("Physicall Challenged Reason Should not be Empty");
						failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getFatherName()=="" || uploadStudentXSLPOJO.getFatherName().equals("-")){
					uploadStudentXSLVo.setReason("Father Name Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}else if(uploadStudentXSLPOJO.getFatherMobileNo().trim()=="" || uploadStudentXSLPOJO.getFatherMobileNo().trim().equals("-")){
				
					uploadStudentXSLVo.setReason("Father Mobile Number Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(!uploadStudentXSLPOJO.getFatherMobileNo().equalsIgnoreCase("") && !uploadStudentXSLPOJO.getFatherMobileNo().equalsIgnoreCase("0") && !(uploadStudentXSLPOJO.getFatherMobileNo().matches(int_regex))){
					uploadStudentXSLVo.setReason("Enter Valid Father Mobile Number");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getMotherName()=="" || uploadStudentXSLPOJO.getMotherName().equals("-")){
					uploadStudentXSLVo.setReason("Mother Name Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}else if(uploadStudentXSLPOJO.getMotherMobileNo().trim()=="" || uploadStudentXSLPOJO.getMotherMobileNo().trim().equals("-")){
					uploadStudentXSLVo.setReason("Mother Mobile Number Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(!uploadStudentXSLPOJO.getMotherMobileNo().equalsIgnoreCase("") && !uploadStudentXSLPOJO.getMotherMobileNo().equalsIgnoreCase("0") &&!(uploadStudentXSLPOJO.getMotherMobileNo().matches(int_regex))){
							uploadStudentXSLVo.setReason("Enter Valid Mother Mobile Number");
							failurelist.add(uploadStudentXSLVo);
				}
				
				else if(!uploadStudentXSLPOJO.getGuardianMobileNo().equalsIgnoreCase("") && !uploadStudentXSLPOJO.getGuardianMobileNo().equalsIgnoreCase("0") && !(uploadStudentXSLPOJO.getGuardianMobileNo().matches(int_regex))){
					uploadStudentXSLVo.setReason("Enter Valid Guardian Mobile Number");
					failurelist.add(uploadStudentXSLVo);
				}
				
				else if(uploadStudentXSLPOJO.getSibilingadminno() !=null){
					if(siblingId==null || siblingId.equalsIgnoreCase("")){
						uploadStudentXSLVo.setReason("Invalid Sibling Name or Admissio No.");
						failurelist.add(uploadStudentXSLVo);
				}
					else if(!uploadStudentXSLPOJO.getEmegencyNo().equalsIgnoreCase("") && !uploadStudentXSLPOJO.getEmegencyNo().equalsIgnoreCase("0") && !(uploadStudentXSLPOJO.getEmegencyNo().matches(int_regex))){
						uploadStudentXSLVo.setReason("Enter Valid Emergency Mobile Number");
						failurelist.add(uploadStudentXSLVo);
					}
					else if(!uploadStudentXSLPOJO.getSmsNo().equalsIgnoreCase("") && !uploadStudentXSLPOJO.getSmsNo().equalsIgnoreCase("0") && !(uploadStudentXSLPOJO.getSmsNo().matches(int_regex))){
						uploadStudentXSLVo.setReason("Enter Valid Sms Mobile Number");
						failurelist.add(uploadStudentXSLVo);
					}
					else if(secondLanguage.equalsIgnoreCase("notFount")){
						uploadStudentXSLVo.setReason("Language "+uploadStudentXSLPOJO.getSecondLanguage() +" Does Not Exit in "+uploadStudentXSLPOJO.getClassname());
						failurelist.add(uploadStudentXSLVo);
					}
					else if(thirdLangauge.equalsIgnoreCase("notFount")){
						uploadStudentXSLVo.setReason("Language "+uploadStudentXSLPOJO.getThirdLanguage() +" Does Not Exit in "+uploadStudentXSLPOJO.getClassname());
						failurelist.add(uploadStudentXSLVo);
					}
			}else{
					uploadStudentXSLPOJO.setStreamValue(uploadStudentXSLPOJO.getCategory());
					uploadStudentXSLPOJO.setCategory(stream);
					uploadStudentXSLPOJO.setCalssValue(uploadStudentXSLPOJO.getClassname());
					uploadStudentXSLPOJO.setClassname(classId);
					
					uploadStudentXSLPOJO.setSectionValue(uploadStudentXSLPOJO.getSectionname());
					uploadStudentXSLPOJO.setSectionname(sectionId);
					uploadStudentXSLPOJO.setReligion(religionId);
					uploadStudentXSLPOJO.setSibilingadminno(siblingId);
					uploadStudentXSLPOJO.setCaste(casteId);
					uploadStudentXSLPOJO.setTranscategory(transportTypeId);
					uploadStudentXSLPOJO.setTranslocation(transportLocationId);
					uploadStudentXSLPOJO.setRoute(routeId);
					uploadStudentXSLPOJO.setSpecilization(specilizationId);
					uploadStudentXSLPOJO.setCasteCategory(casteCategoryId);
					uploadStudentXSLPOJO.setSchoolName(schoolLocationId);
					uploadStudentXSLPOJO.setHousename(houseid);
					uploadStudentXSLPOJO.setFatheroccupation(fatherOccupationId);
					uploadStudentXSLPOJO.setMotheroccupation(motherOccupationId);
					uploadStudentXSLPOJO.setGuardianOccupation(guardianOccupation);
					uploadStudentXSLPOJO.setStuimgurl(stuimgurl);
					uploadStudentXSLPOJO.setSecondLanguage(secondLanguage);
					uploadStudentXSLPOJO.setThirdLanguage(thirdLangauge);
					successlist.add(uploadStudentXSLPOJO);
				}
			}
	
			Set<UploadStudentXlsVO> failureListFromDiompl=new LinkedHashSet<UploadStudentXlsVO>();
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertEmpXSL(successlist,connection);
				
			}
			
			for (Iterator<UploadStudentXlsVO> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				UploadStudentXlsVO failureDiomplVo = (UploadStudentXlsVO) it.next();
				UploadStudentXlsVO uploadStudentXSLVo = new UploadStudentXlsVO();
				uploadStudentXSLVo.setStudentLastName(failureDiomplVo.getStudentLastName());
				
				uploadStudentXSLVo.setStudentFirstName(failureDiomplVo.getStudentFirstName());
				uploadStudentXSLVo.setStudentAdmissionNo(failureDiomplVo.getStudentAdmissionNo());
				uploadStudentXSLVo.setStudentRegNo(failureDiomplVo.getApplicationNo());
				uploadStudentXSLVo.setDateofJoin(failureDiomplVo.getDateofJoin());
				
				uploadStudentXSLVo.setAcademicYear(failureDiomplVo.getAcademicYear());
				uploadStudentXSLVo.setCategory(failureDiomplVo.getCategory());
				uploadStudentXSLVo.setClassname(failureDiomplVo.getClassname());
				uploadStudentXSLVo.setSectionname(failureDiomplVo.getSectionname());
				uploadStudentXSLVo.setReason(failureDiomplVo.getReason());
				
				failurelist.add(uploadStudentXSLVo);
		    }
			
			
		}catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}finally{
			try {
				if(connection!=null && (!connection.isClosed())){
					connection.close();
				}
			} catch (SQLException sqle) {
				   sqle.printStackTrace();
					logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLServiceImpl : insertEmpXSL : Ending");

		return failurelist;
	}


}
