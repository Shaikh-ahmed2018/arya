package com.centris.campus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.upload.FormFile;

import com.centris.campus.pojo.UploadStudentXlsPOJO;

public class UploadStudentXSLUtility {

	FileOutputStream outstream;
    String errorId=null;
	private static Logger logger = Logger.getLogger(UploadStudentXSLUtility.class);

	public Map<String, Object> getExcelData(File fileURL, FormFile file) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLUtility : getExcelData : Starting");

		ArrayList<UploadStudentXlsPOJO> employeelist = new ArrayList<UploadStudentXlsPOJO>();
	
		
         Map<String,Object> map=new HashMap<String,Object>();
     	ArrayList<ArrayList<Object>> rowcountlist = new ArrayList<ArrayList<Object>>();
         
		try {
			outstream = new FileOutputStream(fileURL);
			outstream.write(file.getFileData());

			if (!fileURL.exists()) {
				FileOutputStream fileOutStream = new FileOutputStream(fileURL);
				fileURL.mkdir();
				fileOutStream.write(file.getFileData());
				fileOutStream.flush();
				fileOutStream.close();
			}

			FileInputStream inputstream = new FileInputStream(fileURL);
			XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			//Iterator<Row> rowIterator = sheet.iterator();

			XSSFRow row;
			XSSFCell cell;
			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext()) {
				ArrayList<Object> sheetcountlist = new ArrayList<Object>();
				row = (XSSFRow) rows.next();

				for (int i = 0; i < 74; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);

					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_BLANK:
						String listofString1 = cell.getStringCellValue().toString().trim();
						sheetcountlist.add(listofString1);
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString().trim();
							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);
							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell.getNumericCellValue()) + "";
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						sheetcountlist.add(listofString);
						break;
					}	

				}
				rowcountlist.add(sheetcountlist); 
				sheetcountlist=null;
			}
		
			outstream.close();
			inputstream.close();
		
			
			
			for (int j =1; j < rowcountlist.size();j++) {
				 Object[] sheetarray = rowcountlist.get(j).toArray();
				 ArrayList<Object> sheetcountlist = new ArrayList<Object>(Arrays.asList(sheetarray));
				for(int i=0;i<sheetcountlist.size();){
				
				boolean empltyflag=true;
 
				UploadStudentXlsPOJO uploadStuXSLPOJO = new UploadStudentXlsPOJO();
				
				uploadStuXSLPOJO.setStudentFirstName(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				
				uploadStuXSLPOJO.setStudentLastName(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				
				i++;
				uploadStuXSLPOJO.setStudentAdmissionNo(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setApplicationNo(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				
				i++;
				uploadStuXSLPOJO.setDateofJoin(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setAcademicYear(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setCategory(sheetcountlist.get(i).toString()); //stream
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setClassname(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setSectionname(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setSpecilization(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setRollno(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setHousename(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setSchoolName(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setIsParentsGuardianWorking(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setWorkingParentsGuardianName(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setTransferCertificateNo(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				//up to TC NO
				
				uploadStuXSLPOJO.setTransport(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setTranscategory(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setTranslocation(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setRoute(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setMedium(sheetcountlist.get(i).toString());
				
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				
				i++;
				uploadStuXSLPOJO.setDateofBirth(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setDob_in_words(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				
				uploadStuXSLPOJO.setGender(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				
				uploadStuXSLPOJO.setIdentificationMarks(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setBloodGroup(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setReligion(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setCaste(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				//up to caste
				uploadStuXSLPOJO.setCasteCategory(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setNationality(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setMedicalhistory(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setRemarks(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setPhysicallyChallenged(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setPhysicalchalreason(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				//up to setPhysicalchalreason
				uploadStuXSLPOJO.setAadharNo(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setStudentstatus(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setMotherTounge(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setStudentsiblingname(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setStudentsiblingadmission(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setStudentsiblingclass(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				
				uploadStuXSLPOJO.setFatherName(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setFatherMobileNo(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setFatheroccupation(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setFatherDesignation(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setFatherPanNO(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setFatherAnnualIncome(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setFatheremailId(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setFatherQualification(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setFatherOffAddress(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setMotherName(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setMotherMobileNo(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setMotheroccupation(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setMotherDesignation(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				
				uploadStuXSLPOJO.setMotherPanNo(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setMotherAnnualIncome(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setMotheremailId(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setMotherQualification(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setMotherOffAddress(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				
				//up to mother Qualification
				uploadStuXSLPOJO.setGuardianName(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setGuardianMobileNo(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setGuardianOccupation(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setGuardianDesignation(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setGuardianPanNo(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setGuardianAnnualIncome(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setGuardianemailId(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setGuardianQualification(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				//upto GUARDIAN QUALIFICATION
				uploadStuXSLPOJO.setGuardianOffAddress(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				
				uploadStuXSLPOJO.setPrimaryPerson(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				
				uploadStuXSLPOJO.setPermanentAddress(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setPresentAddress(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setEmegencyNo(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setSmsNo(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setSecondLanguage(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				uploadStuXSLPOJO.setThirdLanguage(sheetcountlist.get(i).toString());
				if(empltyflag){
					if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
						empltyflag=true;
					}
					else{
						empltyflag=false;
					}
				}
				i++;
				if(!empltyflag){
				employeelist.add(uploadStuXSLPOJO);
				map.put("List",employeelist);
				}
				
				uploadStuXSLPOJO=null;
				}
				sheetarray=null;
				sheetcountlist=null;
			
		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		}

		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLDaoImpl : getExcelData : Ending");
		return map;
	}
	
	public boolean checkDuplicate(List<UploadStudentXlsPOJO> li,String studentid){
		boolean flag=false;
		
		for(int i=0;i<li.size();i++){
			if(li.get(i).getApplicationNo().equals(studentid)){
				errorId="Duplicate Entry of Student Id Please Check It";
				flag=true;
				break;
			}
		}
	
		return flag;
		
	}
	
	

}
