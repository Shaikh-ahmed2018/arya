package com.centris.campus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.upload.FormFile;

import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.pojo.ClassFeeSetupPojo;


public class FeeExcelUploadUtil {
	
	private static Logger logger = Logger.getLogger(FeeExcelUploadUtil.class);
	FileOutputStream outstream;
	public Map<String, Object> getExcelData(File fileURL, FormFile file) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeExcelUploadUtil : getExcelData : Starting");
		
		ArrayList<ClassFeeSetupPojo> stulist = new ArrayList<ClassFeeSetupPojo>();
		
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

			XSSFRow row;
			XSSFCell cell;

			Iterator<Row> rows = sheet.rowIterator();
			
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();

				for (int i = 0; i < 13; i++) {

					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);

					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>" + cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue().toString().trim();
						sheetcountlist.add(listofString1);

						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>" + cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();
							System.out.println("formatedDate---" + dateStr);
							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = (cal.get(Calendar.MONTH)+ 1) + "/"
									+ cal.get(Calendar.DATE)  + "/"
									+ cal.get(Calendar.YEAR);
							System.out.println("date" + formatedDate);
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
			for (int i = 13; i <= sheetcountlist.size() - 13;) {
				
				ClassFeeSetupPojo classFeeSetupPojo = new ClassFeeSetupPojo();
				
				classFeeSetupPojo.setStuFname(sheetcountlist.get(i).toString());
				System.out.println("hfhfjasfh=="+sheetcountlist.get(i).toString()+"       "+i);
				i++;
				classFeeSetupPojo.setStuLname(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setAdmissionNo(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setAcadamicYear(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setLocation_id(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setClassId(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setTermId(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setTotalfee(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setPaidDate(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setPaymentmode(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setParticularNo(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setBankName(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setDdDate(sheetcountlist.get(i).toString());
				i++;
				
				stulist.add(classFeeSetupPojo);
				
			}
			map.put("List",stulist);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeExcelUploadUtil : getExcelData : Ending");
		return map;
	}
	public String checkStudent(String stufname, String stulname, String addmissionNo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in FeeExcelUploadUtil : checkStudent : Starting");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String stuid = null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("SELECT student_id_int FROM campus_student WHERE student_admissionno_var = ? AND student_fname_var = ? AND  student_lname_var = ?");
			pstmt.setString(1,addmissionNo);
			pstmt.setString(2,stufname);
			pstmt.setString(3,stulname);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			if(rs.next()){
				stuid = rs.getString("student_id_int");
			}else{
				stuid = "notfound";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(rs.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeExcelUploadUtil : checkStudent : Ending");
		return stuid;
	}
	public String getTermId(String termId, Connection connection, String accyearId, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in FeeExcelUploadUtil : getTermId : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String termid = null;
		Connection conn =null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("SELECT termid FROM campus_fee_termdetails WHERE termname = ? AND accyear = ? AND locationId = ?");
			pstmt.setString(1,termId);
			pstmt.setString(2,accyearId);
			pstmt.setString(3,locationid.split(",")[0]);
	
			rs = pstmt.executeQuery();
			if(rs.next()){
				termid = rs.getString("termid");
			}else{
				termid = "notfound";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeExcelUploadUtil : getTermId : Ending");
		return termid;
	}
	public static String getfeesettingCode(String termid,String accyear,String studentails){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in FeeExcelUploadUtil : getfeesettingCode : Starting");
	String feesetingCode =null;
	PreparedStatement getfeesettingcode = null;
	ResultSet getfeesettingrs = null;
	Connection conn = null;
	try{
		conn = JDBCConnection.getSeparateConnection();
		getfeesettingcode = conn.prepareStatement("SELECT feeSettingcode FROM campus_fee_setup WHERE ClassCode =? AND AccyearCode =? AND Termcode =? AND locationId = ? AND specialization =?");
		getfeesettingcode.setString(1,studentails.split(",")[1]);
		getfeesettingcode.setString(2,accyear);
		getfeesettingcode.setString(3,termid);
		getfeesettingcode.setString(4,studentails.split(",")[0]);
		getfeesettingcode.setString(5,studentails.split(",")[3]);
		
		getfeesettingrs = getfeesettingcode.executeQuery();
		if(getfeesettingrs.next()){
			feesetingCode = getfeesettingrs.getString("feeSettingcode");
		}else{
			feesetingCode = "NotSet";
		}
		
	}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(getfeesettingrs!=null && !(getfeesettingrs.isClosed())){
					getfeesettingrs.close();
				}
				if(getfeesettingcode!=null && !(getfeesettingcode.isClosed())){
					getfeesettingcode.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeExcelUploadUtil : getTermId : Ending");
		
		return feesetingCode;
	}
	public Map<String, Object> getTransportExcelData(File fileURL, FormFile file) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeExcelUploadUtil : getTransportExcelData : Starting");
		
		ArrayList<ClassFeeSetupPojo> stulist = new ArrayList<ClassFeeSetupPojo>();
		
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

				for (int i = 0; i < 12; i++) {

					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);

					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>" + cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue().toString().trim();
						sheetcountlist.add(listofString1);

						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>" + cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();
							System.out.println("formatedDate---" + dateStr);
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
			for (int i = 12; i <= sheetcountlist.size() - 12;) {
				
				ClassFeeSetupPojo classFeeSetupPojo = new ClassFeeSetupPojo();
				
				classFeeSetupPojo.setAdmissionNo(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setAcadamicYear(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setTermId(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setTotalfee(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setNoofmnths(sheetcountlist.get(i).toString());
				i++;
				System.out.println("Start Month::::::::"+sheetcountlist.get(i).toString());
				classFeeSetupPojo.setStmnth(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setEndmnth(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setPaidDate(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setPaymentmode(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setParticularNo(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setBankName(sheetcountlist.get(i).toString());
				i++;
				classFeeSetupPojo.setDdDate(sheetcountlist.get(i).toString());
				i++;
				
				stulist.add(classFeeSetupPojo);
				map.put("List",stulist);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeExcelUploadUtil : getExcelData : Ending");
		return map;
	
	}
	public String getTransportTermId(String termId, Connection connection, String accyearId, String locationid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in FeeExcelUploadUtil : getTermId : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String termid = null;
		Connection conn =null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("SELECT termid FROM campus_fee_transport_termdetails WHERE termname = ? AND locationId = ? AND accyear = ?");
			pstmt.setString(1,termId);
			pstmt.setString(3,accyearId);
			pstmt.setString(2,locationid);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			if(rs.next()){
				termid = rs.getString("termid");
			}else{
				termid = "notfound";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeExcelUploadUtil : getTermId : Ending");
		return termid;
	
	}
	public String checkStudentAdmin(String admissionno) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in FeeExcelUploadUtil : checkStudentAdmin : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String student_id = null;
		Connection conn =null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("SELECT student_id_int FROM campus_student WHERE student_admissionno_var = ?");
			pstmt.setString(1,admissionno);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			if(rs.next()){
				student_id = rs.getString("student_id_int");
			}else{
				student_id = "notfound";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeExcelUploadUtil : checkStudentAdmin : Ending");
		return student_id;
	
	}

}
