package com.centris.campus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
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
import org.json.JSONArray;

import com.centris.campus.pojo.StockEntryPOJO;
import com.centris.campus.pojo.UploadLibraryXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;

public class UploadLibraryCategoryXSLUtility {

	FileOutputStream outstream;
    String errorId=null;
	private static Logger logger = Logger.getLogger(UploadLibraryCategoryXSLUtility.class);

	public Map<String, Object> getCategoryExcelData(File fileURL, FormFile file) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadLibraryCategoryXSLUtility : getExcelData : Starting");

		ArrayList<UploadLibraryXlsPOJO> categorylist = new ArrayList<UploadLibraryXlsPOJO>();
	
		ArrayList<Object> sheetcountlist = new ArrayList<Object>();
         Map<String,Object> map=new HashMap<String,Object>();
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
			Iterator<Row> rowIterator = sheet.iterator();

			XSSFRow row;
			XSSFCell cell;

			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();

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
						String listofString = cell.getStringCellValue()
								.toString().trim();
						System.out.println("string===>>>"
								+ cell.getStringCellValue() + "\t");
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
			
			System.out.println("size of sheetcountlist "+sheetcountlist.size());
			for (int i = 20; i <= sheetcountlist.size() - 20;) {

				UploadLibraryXlsPOJO uploadStuXSLPOJO = new UploadLibraryXlsPOJO();
				
				//String studentid=sheetcountlist.get(i).toString();
				String studentname=sheetcountlist.get(i).toString();
				System.out.println("studentname***************"+studentname);
				uploadStuXSLPOJO.setCategorytypecode(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setCategorytypename(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setCategorystatus(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setCategorydescription(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategorytypecode(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategorytypename(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategorystatus(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategorydescription(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategorytype1code(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategorytype1name(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategory1status(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategory1description(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategorytype2code(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategorytype2name(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategory2status(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategory2description(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategorytype3code(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategorytype3name(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategory3status(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubcategory3description(sheetcountlist.get(i).toString());
				i++;
				
				
				categorylist.add(uploadStuXSLPOJO);
				map.put("List",categorylist);
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

	public Map<String, Object> getSubscriberDetailsExcelData(File fileURL, FormFile file) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadLibraryCategoryXSLUtility : getExcelData : Starting");

		ArrayList<UploadLibraryXlsPOJO> categorylist = new ArrayList<UploadLibraryXlsPOJO>();
	
		ArrayList<Object> sheetcountlist = new ArrayList<Object>();
         Map<String,Object> map=new HashMap<String,Object>();
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
			Iterator<Row> rowIterator = sheet.iterator();

			XSSFRow row;
			XSSFCell cell;

			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();

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
						String listofString = cell.getStringCellValue()
								.toString().trim();
						System.out.println("string===>>>"
								+ cell.getStringCellValue() + "\t");
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
			
			System.out.println("size of sheetcountlist "+sheetcountlist.size());
			for (int i = 20; i <= sheetcountlist.size() - 20;) {

				UploadLibraryXlsPOJO uploadStuXSLPOJO = new UploadLibraryXlsPOJO();
				
				String studentname=sheetcountlist.get(i).toString();
				System.out.println("studentname***************"+studentname);
				uploadStuXSLPOJO.setLocation(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubscriberType(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setSubscriberNumber(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setAdmissionNumber(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setName(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setContactNumber(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setEmailID(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setDepositType(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setAmount(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setCradNo(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setStatus(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setDate(sheetcountlist.get(i).toString());
				i++;
				uploadStuXSLPOJO.setAddress(sheetcountlist.get(i).toString());
				i++;
				categorylist.add(uploadStuXSLPOJO);
				map.put("List",categorylist);
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

	public Map<String, Object> getStockEntryExcelData(File fileURL,
			FormFile file) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadLibraryCategoryXSLUtility : getExcelData : Starting");

		ArrayList<StockEntryPOJO> stockEntry = new ArrayList<StockEntryPOJO>();
	
		ArrayList<Object> sheetcountlist = new ArrayList<Object>();
         Map<String,Object> map=new HashMap<String,Object>();
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
			Iterator<Row> rowIterator = sheet.iterator();

			XSSFRow row;
			XSSFCell cell;

			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();

				for (int i = 0; i < 28; i++) {

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
						String listofString = cell.getStringCellValue()
								.toString().trim();
						System.out.println("string===>>>"
								+ cell.getStringCellValue() + "\t");
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
			
			System.out.println("size of sheetcountlist "+sheetcountlist.size());
			for (int i = 28; i <= sheetcountlist.size() - 28;) {

				StockEntryPOJO StockentryXSLPOJO = new StockEntryPOJO();
				
				StockentryXSLPOJO.setAccessionno(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setItemtype(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setRegisterdDate(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setBooktitle(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setAuthor(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setCategory(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setSubCtegory(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setSubcategorytype1(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setSubcategorytype2(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setLanguage(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setPublisher(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setNoofcopies(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setCostpercopy(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setTotalcost(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setNoofpages(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setBillno(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setBilldate(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setSize(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setSuppliedby(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setGeneralinfo(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setPublicationyear(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setEdition(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setEditor(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setIsbnno(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setContentsearch(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setShelfno(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setLibrarylocation(sheetcountlist.get(i).toString());
				i++;
				StockentryXSLPOJO.setCurrentstatus(sheetcountlist.get(i).toString());
				i++;
				stockEntry.add(StockentryXSLPOJO);
				map.put("List",stockEntry);
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
	
/*	public boolean checkDuplicate(List<UploadStudentXlsPOJO> li,String studentid){
		boolean flag=false;
		
		System.out.println("li.size():::"+li.size());
		for(int i=0;i<li.size();i++){
			if(li.get(i).getApplicationNo().equals(studentid)){
				errorId="Duplicate Entry of Student Id Please Check It";
				flag=true;
				break;
			}
		}
		return flag;
	}*/
}