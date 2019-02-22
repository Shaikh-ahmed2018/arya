package com.centris.campus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.upload.FormFile;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.StudentExcelUploadPojo;

public class SettingsFileUploadUtil {
	FileOutputStream outstream;
    String errorId=null;
	private static Logger logger = Logger.getLogger(SettingsFileUploadUtil.class);
	
	public Map<String, Object> StudentExamExcelUpload(File fileURL, FormFile file) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsFileUploadUtil : StudentExamExcelUpload : Starting");
		
		ArrayList<StudentExcelUploadPojo> uploaddata = new ArrayList<StudentExcelUploadPojo>();
		ArrayList<Object> sheetcountlist = new ArrayList<Object>();
        Map<String,Object> map=new HashMap<String,Object>();
       try{
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
			Iterator<Row> rowIterator = sheet.iterator();

			XSSFRow row;
			XSSFCell cell;
			Iterator rows = sheet.rowIterator();

			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				/*	if(row.getRowNum()==0 || row.getRowNum()==1){
					continue;
				}*/

				for (int i = 0; i < 20; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
			
			
			System.out.println("size of sheetcountlist "+sheetcountlist.size());
			for (int i =40 ; i < sheetcountlist.size();) {
				
				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
				StudentExcelUploadPojo pojo = new StudentExcelUploadPojo();
				
				pojo.setStudentName(sheetcountlist.get(i).toString());
				String stuName = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setStudentIdNo(sheetcountlist.get(i).toString());
				String studentIdNo = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setSchoolName(sheetcountlist.get(i).toString());
				String school = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setAcademicYear(sheetcountlist.get(i).toString());
				String accyear = sheetcountlist.get(i).toString();
				i++;
				System.out.println("&&&&&&&&&&&&&&"+pojo.getAcademicYear());
				pojo.setStreamName(sheetcountlist.get(i).toString());
				i++;

				pojo.setClassName(sheetcountlist.get(i).toString());
				String classname = sheetcountlist.get(i).toString();
				i++;
			
				pojo.setSectionName(sheetcountlist.get(i).toString());
				String section = sheetcountlist.get(i).toString();
				i++;
				
				
				
				pojo.setExamType(sheetcountlist.get(i).toString());
				String examType = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setExamName(sheetcountlist.get(i).toString());
				String examname = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setExamCode(sheetcountlist.get(i).toString());
				String examcode = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setStartdate(sheetcountlist.get(i).toString());
				String startdate = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setEnddate(sheetcountlist.get(i).toString());
				String enddate = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setSubjectName(sheetcountlist.get(i).toString());
				String subname = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setAttandance(sheetcountlist.get(i).toString());
				String attendance = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setTestMaximumMarks(sheetcountlist.get(i).toString());
				String testmaxmarks = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setTestScoredMarks(sheetcountlist.get(i).toString());
				String testscoremarks = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setNotebookMaximumMarks(sheetcountlist.get(i).toString());
				String notebookmarks = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setNotebookScoredMarks(sheetcountlist.get(i).toString());
				String notebookscoremarks = sheetcountlist.get(i).toString();
				i++;
				
				
				
				pojo.setSubjectMaximumMarks(sheetcountlist.get(i).toString());
				String subjectMaxmarks = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setSubjectScoredMarks(sheetcountlist.get(i).toString());
				String subjectscoremarks = sheetcountlist.get(i).toString();
				i++;
				
				/*pojo.setWorkEducation(sheetcountlist.get(i).toString());
				String workedu = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setArtEducation(sheetcountlist.get(i).toString());
				String artedu = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setHealthEducation(sheetcountlist.get(i).toString());
				String healthedu = sheetcountlist.get(i).toString();
				i++;
				
				pojo.setDescipline(sheetcountlist.get(i).toString());
				String descipline = sheetcountlist.get(i).toString();
				i++;
				pojo.setRemarks(sheetcountlist.get(i).toString());
				String remarks = sheetcountlist.get(i).toString();
				i++;*/
				if(stuName.trim().equalsIgnoreCase("") && studentIdNo.trim().equalsIgnoreCase("") && school.trim().equalsIgnoreCase("")
						&& accyear.trim().equalsIgnoreCase("") && classname.trim().equalsIgnoreCase("") && section.trim().equalsIgnoreCase("") && attendance.trim().equalsIgnoreCase("")
						&& examType.trim().equalsIgnoreCase("")	&& examname.trim().equalsIgnoreCase("") && examcode.trim().equalsIgnoreCase("") && startdate.trim().equalsIgnoreCase("") 
						&& enddate.trim().equalsIgnoreCase("") && subname.trim().equalsIgnoreCase("") 
						&& testmaxmarks.trim().equalsIgnoreCase("") && testscoremarks.trim().equalsIgnoreCase("") && notebookmarks.trim().equalsIgnoreCase("") && notebookscoremarks.trim().equalsIgnoreCase("")
						&& subjectMaxmarks.trim().equalsIgnoreCase("") && subjectscoremarks.trim().equalsIgnoreCase("")
						/*&& workedu.trim().equalsIgnoreCase("") && artedu.trim().equalsIgnoreCase("")
						&& healthedu.trim().equalsIgnoreCase("") && descipline.trim().equalsIgnoreCase("")
						&& remarks.trim().equalsIgnoreCase("")*/
						){
				}else{
					uploaddata.add(pojo);
				}
				map.put("List",uploaddata);
			}
    	   
       }catch(Exception e){
    	   e.printStackTrace();
       }
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsFileUploadUtil : StudentExamExcelUpload : Ending");
		return map;
		
		
	}
	
	
	
	

	public Map<String, Object> SubjectDetailsSetting(File fileURL, FormFile file) {
			
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SettingsFileUploadUtil : SubjectDetailsSetting : Starting");
			
			ArrayList<ClassPojo> subjectList = new ArrayList<ClassPojo>();
			ArrayList<Object> sheetcountlist = new ArrayList<Object>();
		    Map<String,Object> map=new HashMap<String,Object>();
		    
		   try{
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
				Iterator<Row> rowIterator = sheet.iterator();

				XSSFRow row;
				XSSFCell cell;
				Iterator rows = sheet.rowIterator();

				while (rows.hasNext()) {
					row = (XSSFRow) rows.next();
					for (int i = 0; i < 9; i++) {
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BLANK:
							System.out.println("blank===>>>"
									+ cell.getStringCellValue() + "\t");
							String listofString1 = cell.getStringCellValue()
									.toString().trim();
							sheetcountlist.add(listofString1);

							System.out.println("count" + sheetcountlist.size());
							break;

						case Cell.CELL_TYPE_BOOLEAN:
							System.out.println("boolean===>>>"
									+ cell.getBooleanCellValue() + "\t");
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								String dateStr = cell.getDateCellValue().toString()
										.trim();

								DateFormat formatter = new SimpleDateFormat(
										"E MMM dd HH:mm:ss Z yyyy");
								Date date1 = (Date) formatter.parse(dateStr);

								Calendar cal = Calendar.getInstance();
								cal.setTime(date1);
								String formatedDate = cal.get(Calendar.DATE) + "-"
										+ (cal.get(Calendar.MONTH) + 1) + "-"
										+ cal.get(Calendar.YEAR);
								System.out.println("formatedDate" + formatedDate);
								sheetcountlist.add(formatedDate);

							} else {
								String sheetNumber = ((long) cell
										.getNumericCellValue()) + "";
								System.out.println("cell numeric value===>>"
										+ sheetNumber);
								sheetcountlist.add(sheetNumber.trim());
							}
							break;
						case Cell.CELL_TYPE_STRING:
							String listofString = cell.getStringCellValue().toString().trim();
							System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
							System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
							sheetcountlist.add(listofString);
							break;
						}
					}
				}
				
				System.out.println("size of sheetcountlist "+sheetcountlist.size());
				for (int i = 9; i <= sheetcountlist.size() - 9;) {
					ClassPojo pojo = new ClassPojo();
					
					pojo.setLocationName(sheetcountlist.get(i).toString());
					String loc = sheetcountlist.get(i).toString();
					i++;
					
					pojo.setClassName(sheetcountlist.get(i).toString());
					String className = sheetcountlist.get(i).toString();
					i++;
					
					pojo.setSpecializationName(sheetcountlist.get(i).toString());
					String specName = sheetcountlist.get(i).toString();
					i++;
					
					pojo.setSubjectName(sheetcountlist.get(i).toString());
					String subjectName = sheetcountlist.get(i).toString();
					i++;
					
					pojo.setSubjectCode(sheetcountlist.get(i).toString());
					String subjectCode = sheetcountlist.get(i).toString();
					i++;
					
					pojo.setIsLanguage(sheetcountlist.get(i).toString());
					String isLang = sheetcountlist.get(i).toString();
					i++;
					
					pojo.setTotalMarks(sheetcountlist.get(i).toString());
					String totMarks = sheetcountlist.get(i).toString();
					i++;
					
					pojo.setPassmarks(sheetcountlist.get(i).toString());
					String pasmarks = sheetcountlist.get(i).toString();
					i++;
					
					pojo.setDescription(sheetcountlist.get(i).toString());
					String desc = sheetcountlist.get(i).toString();
					i++;
					
					if(loc.equalsIgnoreCase("") && className.equalsIgnoreCase("") && specName.equalsIgnoreCase("") && subjectName.equalsIgnoreCase("")
					&&	subjectCode.equalsIgnoreCase("") &&  isLang.equalsIgnoreCase("") &&	totMarks.equalsIgnoreCase("") &&  pasmarks.equalsIgnoreCase("")	
					&& desc.equalsIgnoreCase("")){
						
					}else{
						subjectList.add(pojo);
					}
					map.put("List",subjectList);
				}
		   }catch(Exception e){
			   e.printStackTrace();
		   }
			return map;
		}
	
	
	
	
//holiday 
	
	
	public Map<String, Object> holidaySetting(File fileURL, FormFile file) {
		ArrayList<ClassPojo> holidayList = new ArrayList<ClassPojo>();
		ArrayList<Object> sheetcountlist = new ArrayList<Object>();
	    Map<String,Object> map=new HashMap<String,Object>();
	    
	   try{
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
			Iterator<Row> rowIterator = sheet.iterator();

			XSSFRow row;
			XSSFCell cell;
			Iterator rows = sheet.rowIterator();

			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				for (int i = 0; i < 4; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);

						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);
							
							System.out.println(dateStr);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
			
			System.out.println("size of sheetcountlist "+sheetcountlist.size());
			for (int i = 4; i <= sheetcountlist.size() - 4;) {
				ClassPojo pojo = new ClassPojo();
				
				pojo.setLocationName(sheetcountlist.get(i).toString());
				String loc = sheetcountlist.get(i).toString();
				i++;
				pojo.setHolidayDate(sheetcountlist.get(i).toString());
				String date = sheetcountlist.get(i).toString();
				i++;
				pojo.setHolidayName(sheetcountlist.get(i).toString());
				String name = sheetcountlist.get(i).toString();
				i++;
				pojo.setHolidaytype(sheetcountlist.get(i).toString());
				String type = sheetcountlist.get(i).toString();
				i++;
				if(loc.equalsIgnoreCase("") && date.equalsIgnoreCase("") && name.equalsIgnoreCase("") && type.equalsIgnoreCase("")){
					
				}else{
				holidayList.add(pojo);
				}
				map.put("List",holidayList);
			}
	   }catch(Exception e){
		   e.printStackTrace();
	   }
		return map;
	}
	}




