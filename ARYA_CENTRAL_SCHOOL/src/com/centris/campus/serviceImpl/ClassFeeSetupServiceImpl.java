package com.centris.campus.serviceImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.ClassFeeSetupDao;
import com.centris.campus.daoImpl.ClassFeeSetupDaoImpl;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.UploadStudentXLSDaoImpl;
import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.service.ClassFeeSetupService;
import com.centris.campus.util.FeeCollectionSqlUtils;
import com.centris.campus.util.FeeExcelUploadUtil;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.UploadStudentXlsVO;


public class ClassFeeSetupServiceImpl implements ClassFeeSetupService {

	private static final Logger logger = Logger.getLogger(ClassFeeSetupServiceImpl.class);

	static ClassFeeSetupDao dao = null;
	static{
		dao = new ClassFeeSetupDaoImpl();
	}
	 
	public ArrayList<ClassFeeSetupVo> getFeeSetupDetails(String currentaccyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getFeeSetupDetails Starting");
		ArrayList<ClassFeeSetupVo> feelist = null;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		try {
			feelist = dao.getFeeSetupDetails(currentaccyear);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getFeeSetupDetails Ending");
		return feelist;
	}



	@Override
	public ArrayList<ClassFeeSetupVo> getSearchFeeSetupDetails(String searchTerm,String currentaccyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getSearchFeeSetupDetails Starting");
		
		ArrayList<ClassFeeSetupVo> feelist = null;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		
		try {
			
			feelist = dao.getSearchFeeSetupDetails(searchTerm,currentaccyear);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getSearchFeeSetupDetails Ending");
		return feelist;
	}

	public ArrayList<ClassFeeSetupVo> getApprovedFees(ClassFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getSearchFeeSetupDetails Starting");
		
		ArrayList<ClassFeeSetupVo> feelist = null;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		
		try {
			
			feelist = dao.getApprovedFees(feeSetupPojo);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getSearchFeeSetupDetails Ending");
	
		return feelist;
	}



	@Override
	public ArrayList<ClassFeeSetupVo> getAllFees(ClassFeeSetupPojo feeSetupPojo,String location) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getAllFees Starting");
		
		ArrayList<ClassFeeSetupVo> feelist = null;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		
		try {
			
			feelist = dao.getAllFees(feeSetupPojo,location);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getAllFees Ending");
	
		return feelist;
	}
	
	public int insertApproveFees(ArrayList<ClassFeeSetupPojo> approvefeelist) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertApproveFees Starting");
		
		int count=0;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		
		try {
			
			count = dao.insertApproveFees(approvefeelist);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertApproveFees Ending");
	
		return count;
	}


	@Override
	public boolean deleteFees(ClassFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : deleteFees Starting");
		
		boolean flag=false;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		
		try {
			
			flag = dao.deleteFees(feeSetupPojo);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : deleteFees Ending");
	
		return flag;
	}



	@Override
	public int insertFeeAmount(ArrayList<ClassFeeSetupPojo> feeSetupList) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertFeeAmount Starting");
		
		int count=0;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		
		try {
			
			count = dao.insertFeeAmount(feeSetupList);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertFeeAmount Ending");
	
		return count;
	}



	@Override
	public ArrayList<ClassFeeSetupVo> getHeading(ClassFeeSetupPojo feeSetupPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getAllFees Starting");
		
		ArrayList<ClassFeeSetupVo> feelist = null;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		
		try {
			
			feelist = dao.getHeading(feeSetupPojo);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getAllFees Ending");
	
		return feelist;
	}

	@Override
	public String getSpecialization(String classId, String accYear) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ClassFeeSetupVo> insertStudentXSL(List<ClassFeeSetupPojo> list, String username, String demo){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertStudentXSL Starting");
		
		List<ClassFeeSetupPojo> successlist=new ArrayList<ClassFeeSetupPojo>();
		ArrayList<ClassFeeSetupVo> getstudentList = new ArrayList<ClassFeeSetupVo>();
		Connection connection = null;
		
		FeeExcelUploadUtil sqlutil= new FeeExcelUploadUtil();
		Set<ClassFeeSetupVo> failurelist = new LinkedHashSet<ClassFeeSetupVo>();
		UploadStudentXLSDaoImpl daoImpl=new UploadStudentXLSDaoImpl();
		String student =null;
		String accyearId = null;
		String classid =null;
		String termid =null;
		String studentdetails =null;
		String checkfeesettingcode = null;
		failurelist.clear();
		successlist.clear();
		String dbl="^[0-9]*(\\.[0-9]{1,4})?$";
		String datePattern = "\\d{1,2}\\-\\d{1,2}\\-\\d{4}";
		String datePattern1 = "\\d{1,2}\\/\\d{1,2}\\/\\d{4}";
		String string_regx="([a-zA-Z.,-]+\\s+)*[a-zA-Z.,-]+";
		try{
			connection = JDBCConnection.getSeparateConnection();
			for (Iterator<ClassFeeSetupPojo> iterator = list.iterator(); iterator.hasNext();) {
			
				
				ClassFeeSetupPojo uploadFeespojo = (ClassFeeSetupPojo) iterator.next();
				
				
				String ddDate = null;
				String ddFlag = "false";
				if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("cheque")) && !uploadFeespojo.getDdDate().equalsIgnoreCase(" ")){
					ddDate=HelperClass.convertExceltoUIformat(uploadFeespojo.getDdDate());
					ddFlag=HelperClass.validateDate(ddDate);
				}
				String paymentDate = null;
				String paymentFlag = "false";
				if(!(uploadFeespojo.getPaidDate().equalsIgnoreCase("")) || !(uploadFeespojo.getPaidDate().equalsIgnoreCase(" "))){
					System.out.println("uploadFeespojo.getPaidDate() "+uploadFeespojo.getPaidDate()); 
					paymentDate=HelperClass.convertExceltoUIformat(uploadFeespojo.getPaidDate());
					 paymentFlag=HelperClass.validateDate(paymentDate);
				}
				
				uploadFeespojo.setCreatedBy(username);
				ClassFeeSetupVo classFeeSetupVo = new ClassFeeSetupVo();
				classFeeSetupVo.setStuFname(uploadFeespojo.getStuFname());
				classFeeSetupVo.setStuLname(uploadFeespojo.getStuLname());
				classFeeSetupVo.setAdmissionNo(uploadFeespojo.getAdmissionNo());
				classFeeSetupVo.setAcadamicyear(uploadFeespojo.getAcadamicYear());
				classFeeSetupVo.setLocationName(uploadFeespojo.getLocation_id());
				classFeeSetupVo.setClassname(uploadFeespojo.getClassId());
				classFeeSetupVo.setTermid(uploadFeespojo.getTermId());
				classFeeSetupVo.setFeeamount(uploadFeespojo.getTotalfee());
				classFeeSetupVo.setPaymentdate(paymentDate);
				classFeeSetupVo.setPaymentmode(uploadFeespojo.getPaymentmode());
				classFeeSetupVo.setParticularNo(uploadFeespojo.getParticularNo());
				classFeeSetupVo.setBankname(uploadFeespojo.getBankName());
				classFeeSetupVo.setDddate(ddDate);
				getstudentList.add(classFeeSetupVo);
				if(!uploadFeespojo.getAdmissionNo().equalsIgnoreCase("")){
					student = sqlutil.checkStudentAdmin(uploadFeespojo.getAdmissionNo());
				}
				if(student!=null && !student.equalsIgnoreCase("notfound")){
					int count=0;
					if(!uploadFeespojo.getAcadamicYear().equalsIgnoreCase("")){
						accyearId = daoImpl.getAcademicYearId(uploadFeespojo.getAcadamicYear(),connection);
						
					}
				/*	if(accyearId !=null && !accyearId.equalsIgnoreCase("notfound") && !locationid.equalsIgnoreCase("notfound")){
						count = daoImpl.validateStudent(student,accyearId,locationid);
					}*/
					if(accyearId !=null && !accyearId.equalsIgnoreCase("notfound")){
						studentdetails = daoImpl.getstudetails(student,accyearId);
					}
					/*if(!uploadFeespojo.getLocation_id().equalsIgnoreCase("")){
						locationid = daoImpl.getSchoolLocationId(uploadFeespojo.getLocation_id(),connection);
					}*/
					
					/*if(!uploadFeespojo.getClassId().equalsIgnoreCase("") && locationid!=null && !locationid.equalsIgnoreCase("notfound")){
						classid = daoImpl.getClassIdByLoc(uploadFeespojo.getClassId(),connection,locationid);
						
					}*/
					/*if(!uploadFeespojo.getTermId().equalsIgnoreCase("") && accyearId !=null && studentdetails !=null && !accyearId.equalsIgnoreCase("notfound") && !studentdetails.equalsIgnoreCase("notfound")){
						termid = sqlutil.getTermId(uploadFeespojo.getTermId(),connection,accyearId,studentdetails);
						checkfeesettingcode = FeeExcelUploadUtil.getfeesettingCode(termid,accyearId,studentdetails);
					}*/
					
				}
			
					/*if(classFeeSetupVo.getStuFname().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Student First Name Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if(!(classFeeSetupVo.getStuFname().matches(string_regx))){
						classFeeSetupVo.setReason("Invalid Student First Name");
						failurelist.add(classFeeSetupVo);
					}else if(!classFeeSetupVo.getStuLname().equalsIgnoreCase("") && !(classFeeSetupVo.getStuLname().matches(string_regx))){
						classFeeSetupVo.setStuFname(classFeeSetupVo.getStuFname()+" "+classFeeSetupVo.getStuLname());
						classFeeSetupVo.setReason("Invalid Student Last Name");
						failurelist.add(classFeeSetupVo);
					}
					else */
					if(classFeeSetupVo.getAdmissionNo().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Student Admission No Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(student.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("Student Admission No doesn't Exists");
						failurelist.add(classFeeSetupVo);
					}else if(uploadFeespojo.getAcadamicYear().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Academic Year Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if(accyearId.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("Academic Year Not Found");
						failurelist.add(classFeeSetupVo);
					}/*else if(uploadFeespojo.getLocation_id().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("School Name Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if(locationid.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("School Name Not Found");
						failurelist.add(classFeeSetupVo);
					}else if(uploadFeespojo.getClassId().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Class Name Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if(classid.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("Class Name Not Found");
						failurelist.add(classFeeSetupVo);
					}*/
					else if(uploadFeespojo.getClassId().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Class Name Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(studentdetails!=null && studentdetails.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("Student Details not Found");
						failurelist.add(classFeeSetupVo);
					}
					/*else if(uploadFeespojo.getTermId().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Term Name Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if(termid.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("Term Not Found");
						failurelist.add(classFeeSetupVo);
					}*//*else if(checkfeesettingcode.equalsIgnoreCase("NotSet")){
						classFeeSetupVo.setReason("Fees is Not Configured for this Class");
						failurelist.add(classFeeSetupVo);
					}*/
					else if(classFeeSetupVo.getFeeamount().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Paid Amount Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if(!(classFeeSetupVo.getFeeamount().matches(dbl))){
						classFeeSetupVo.setReason("Invalid Paid Amount");
						failurelist.add(classFeeSetupVo);
					}else if(classFeeSetupVo.getPaymentdate().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Paid Date Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(paymentFlag=="false"){
						classFeeSetupVo.setReason("Invalid Payment Date");
						classFeeSetupVo.setPaymentdate(classFeeSetupVo.getPaymentdate());
						failurelist.add(classFeeSetupVo);
					}
					else if(uploadFeespojo.getPaymentmode().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Payment Mode Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && uploadFeespojo.getParticularNo().equalsIgnoreCase("") ){
						classFeeSetupVo.setReason("DD/Cheque No. Should Not Empty When Payment Mode is DD/Cheque");
						failurelist.add(classFeeSetupVo);
					}else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && uploadFeespojo.getBankName().equalsIgnoreCase("") ){
						classFeeSetupVo.setReason("Bank Name Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && uploadFeespojo.getDdDate().equalsIgnoreCase("") ){
						classFeeSetupVo.setReason("DD/Cheque Date Should Not Empty");
						classFeeSetupVo.setPaymentdate(classFeeSetupVo.getDddate());
						failurelist.add(classFeeSetupVo);
					}
					else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && ddFlag=="false"){
						classFeeSetupVo.setReason("Invalid DD/Cheque Date");
						failurelist.add(classFeeSetupVo);
					}
					else{
						uploadFeespojo.setFeeSettingCode(checkfeesettingcode);
						uploadFeespojo.setStudentId(student);
						uploadFeespojo.setAcadamicYear(accyearId);
						uploadFeespojo.setLocation_id(studentdetails);
						uploadFeespojo.setClassId(studentdetails.split(",")[1]);
						uploadFeespojo.setTermId(termid);
						uploadFeespojo.setTotFeeAmt(Double.parseDouble(classFeeSetupVo.getFeeamount()));
						uploadFeespojo.setTermName(classFeeSetupVo.getTermid());
						uploadFeespojo.setStuFname(classFeeSetupVo.getStuFname());
 						uploadFeespojo.setAdmissionNo(classFeeSetupVo.getAdmissionNo());
						uploadFeespojo.setClassName(classFeeSetupVo.getClassname());
						uploadFeespojo.setAccYear(classFeeSetupVo.getAcadamicyear());
						uploadFeespojo.setLocationName(classFeeSetupVo.getLocationName());
						uploadFeespojo.setPaidDate(paymentDate);
						uploadFeespojo.setDdDate(ddDate);
						successlist.add(uploadFeespojo);
					}
			}
			Set<ClassFeeSetupVo> failureListFromDiompl=new LinkedHashSet<ClassFeeSetupVo>();
			if(successlist.size()>0){
				failureListFromDiompl=dao.insertStudentXSL(successlist,connection);
			}
			
			for (Iterator<ClassFeeSetupVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				ClassFeeSetupVo failureDiomplVo = (ClassFeeSetupVo) it.next();
				ClassFeeSetupVo classFeeSetupVo = new ClassFeeSetupVo();
				if(!failureDiomplVo.getStuLname().equalsIgnoreCase("")){
					classFeeSetupVo.setStuFname(failureDiomplVo.getStuFname()+" "+failureDiomplVo.getStuLname());
				}else{
					classFeeSetupVo.setStuFname(failureDiomplVo.getStuFname());
				}
				classFeeSetupVo.setAdmissionNo(failureDiomplVo.getAdmissionNo());
				classFeeSetupVo.setAcadamicyear(failureDiomplVo.getAcadamicyear());
				classFeeSetupVo.setClassname(failureDiomplVo.getClassname());
				classFeeSetupVo.setTermid(failureDiomplVo.getTermid());
				classFeeSetupVo.setFeeamount(failureDiomplVo.getFeeamount()+"");
				classFeeSetupVo.setFineAmount(failureDiomplVo.getFineAmount()+"");
				classFeeSetupVo.setPaymentmode(failureDiomplVo.getPaymentmode());
				classFeeSetupVo.setPaymentdate(failureDiomplVo.getPaymentdate());
				classFeeSetupVo.setReason(failureDiomplVo.getReason());
				failurelist.add(classFeeSetupVo);
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertStudentXSL Ending");
	
		return failurelist;
	}



	@Override
	public Set<ClassFeeSetupVo> insertTransportStudentXSL(List<ClassFeeSetupPojo> list, String username, String demo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertStudentXSL Starting");
		
		List<ClassFeeSetupPojo> successlist=new ArrayList<ClassFeeSetupPojo>();
		ArrayList<ClassFeeSetupVo> getstudentList = new ArrayList<ClassFeeSetupVo>();
		Connection connection = null;
		
		FeeExcelUploadUtil sqlutil= new FeeExcelUploadUtil();
		Set<ClassFeeSetupVo> failurelist = new LinkedHashSet<ClassFeeSetupVo>();
		UploadStudentXLSDaoImpl daoImpl=new UploadStudentXLSDaoImpl();
		String student =null;
		String accyearId = null;
		String classid =null;
		String termid =null;
		String locationid =null;
		//String checkfeesettingcode = null;
		failurelist.clear();
		successlist.clear();
	String dbl="^[0-9]*(\\.[0-9]{1,4})?$";
	String datePattern = "\\d{1,2}\\-\\d{1,2}\\-\\d{4}";
	String datePattern1 = "\\d{1,2}\\/\\d{1,2}\\/\\d{4}";
	String string_regx="([a-zA-Z.,-]+\\s+)*[a-zA-Z.,-]+";
	String regex = "\\d+";
	int count = 0;
		try{
			connection = JDBCConnection.getSeparateConnection();
			for (Iterator<ClassFeeSetupPojo> iterator = list.iterator(); iterator.hasNext();) {
				ClassFeeSetupPojo uploadFeespojo = (ClassFeeSetupPojo) iterator.next();
				uploadFeespojo.setCreatedBy(username);
				ClassFeeSetupVo classFeeSetupVo = new ClassFeeSetupVo();
				
				classFeeSetupVo.setAdmissionNo(uploadFeespojo.getAdmissionNo());
				classFeeSetupVo.setAcadamicyear(uploadFeespojo.getAcadamicYear());
				classFeeSetupVo.setTermid(uploadFeespojo.getTermId());
				classFeeSetupVo.setFeeamount(uploadFeespojo.getTotalfee());
				classFeeSetupVo.setPaymentdate(uploadFeespojo.getPaidDate());
				classFeeSetupVo.setPaymentmode(uploadFeespojo.getPaymentmode());
				classFeeSetupVo.setParticularNo(uploadFeespojo.getParticularNo());
				classFeeSetupVo.setBankname(uploadFeespojo.getBankName());
				classFeeSetupVo.setDddate(uploadFeespojo.getDdDate());
				classFeeSetupVo.setNoofmnths(uploadFeespojo.getNoofmnths());
				classFeeSetupVo.setStmnth(uploadFeespojo.getStmnth());
				classFeeSetupVo.setEndmnth(uploadFeespojo.getEndmnth());
				getstudentList.add(classFeeSetupVo);
				if( !uploadFeespojo.getAdmissionNo().trim().equalsIgnoreCase("")){
					student = sqlutil.checkStudentAdmin(uploadFeespojo.getAdmissionNo().trim());
				}
				
				if(student!=null && !student.equalsIgnoreCase("notfound")){
					
					if(!uploadFeespojo.getAcadamicYear().equalsIgnoreCase("")){
						accyearId = daoImpl.getAcademicYearId(uploadFeespojo.getAcadamicYear(),connection);
					}
					if(accyearId !=null && !accyearId.equalsIgnoreCase("notfound")){
						locationid = daoImpl.getstuloc(student,accyearId);
					}
					if(accyearId !=null && locationid !=null && !accyearId.equalsIgnoreCase("notfound") && !locationid.equalsIgnoreCase("notfound")){
						count = daoImpl.validateStudent(student,accyearId,locationid);
					}
					
					
					
					
				}
					System.out.println(classFeeSetupVo.getStmnth());
					
					if(classFeeSetupVo.getAdmissionNo().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Student Admission No Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(student.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("Admission No. doesn't Exists");
						failurelist.add(classFeeSetupVo);
					}else if(uploadFeespojo.getAcadamicYear().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Academic Year Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if(accyearId.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("Academic Year Not Found");
						failurelist.add(classFeeSetupVo);
					}else if(count == 0){
						classFeeSetupVo.setReason("Student Details Not Found");
						failurelist.add(classFeeSetupVo);
					}
					/*else if(uploadFeespojo.getTermId().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Term Name Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(termid.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("Transport Fee Setup Not Found");
						failurelist.add(classFeeSetupVo);
					}/*else if(checkfeesettingcode.equalsIgnoreCase("NotSet")){
						classFeeSetupVo.setReason("Fees is Not Configured for this Class");
						failurelist.add(classFeeSetupVo);
					}*/
					else if(classFeeSetupVo.getFeeamount().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Paid Amount Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if(!(classFeeSetupVo.getFeeamount().matches(dbl))){
						classFeeSetupVo.setReason("Invalid Paid Amount");
						failurelist.add(classFeeSetupVo);
					}
					else if(classFeeSetupVo.getNoofmnths().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("No Of Months Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(!(classFeeSetupVo.getNoofmnths().matches(regex))){
						classFeeSetupVo.setReason("Invalid No Of Months");
						failurelist.add(classFeeSetupVo);
					}
					else if(classFeeSetupVo.getStmnth().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Start Month Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(!classFeeSetupVo.getStmnth().matches(string_regx)){
						classFeeSetupVo.setReason("Invalid Start Month Name");
						failurelist.add(classFeeSetupVo);
					}
					else if(classFeeSetupVo.getEndmnth().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("End Month Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(!classFeeSetupVo.getEndmnth().matches(string_regx)){
						classFeeSetupVo.setReason("Invalid End Month Name");
						failurelist.add(classFeeSetupVo);
					}
					else if(classFeeSetupVo.getPaymentdate().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Paid Date Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(!(classFeeSetupVo.getPaymentdate().matches(datePattern))){
						classFeeSetupVo.setReason("Invalid Payment Date");
						failurelist.add(classFeeSetupVo);
					}
					else if(uploadFeespojo.getPaymentmode().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Payment Mode Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && uploadFeespojo.getParticularNo().equalsIgnoreCase("") ){
						classFeeSetupVo.setReason("DD/Cheque No. Should Not Empty When Payment Mode is DD/Cheque");
						failurelist.add(classFeeSetupVo);
					}else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && uploadFeespojo.getBankName().equalsIgnoreCase("") ){
						classFeeSetupVo.setReason("Bank Name Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && uploadFeespojo.getDdDate().equalsIgnoreCase("") ){
						classFeeSetupVo.setReason("DD/Cheque Date Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && (!(uploadFeespojo.getDdDate().matches(datePattern)))){
						classFeeSetupVo.setReason("Invalid DD/Cheque Date");
						failurelist.add(classFeeSetupVo);
					}
					else{
						//uploadFeespojo.setFeeSettingCode(checkfeesettingcode);
						uploadFeespojo.setStudentId(student);
						uploadFeespojo.setAcadamicYear(accyearId);
						uploadFeespojo.setLocation_id(locationid);
						uploadFeespojo.setClassId(classid);
						uploadFeespojo.setTermId(termid);
						uploadFeespojo.setTotFeeAmt(Double.parseDouble(classFeeSetupVo.getFeeamount()));
						uploadFeespojo.setTermName(classFeeSetupVo.getTermid());
						uploadFeespojo.setStuFname(classFeeSetupVo.getStuFname());
 						uploadFeespojo.setAdmissionNo(classFeeSetupVo.getAdmissionNo());
						uploadFeespojo.setClassName(classFeeSetupVo.getClassname());
						uploadFeespojo.setAccYear(classFeeSetupVo.getAcadamicyear());
						uploadFeespojo.setLocationName(classFeeSetupVo.getLocationName());
						uploadFeespojo.setCreatedby(username);
						successlist.add(uploadFeespojo);
					}
			}
			Set<ClassFeeSetupVo> failureListFromDiompl=new LinkedHashSet<ClassFeeSetupVo>();
			if(successlist.size()>0){
				failureListFromDiompl=dao.insertTransportStudentXSL(successlist,connection);
			}
			
			for (Iterator<ClassFeeSetupVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				ClassFeeSetupVo failureDiomplVo = (ClassFeeSetupVo) it.next();
				ClassFeeSetupVo classFeeSetupVo = new ClassFeeSetupVo();
				classFeeSetupVo.setAdmissionNo(failureDiomplVo.getAdmissionNo());
				classFeeSetupVo.setAcadamicyear(failureDiomplVo.getAcadamicyear());
				classFeeSetupVo.setTermid(failureDiomplVo.getTermid());
				classFeeSetupVo.setFeeamount(failureDiomplVo.getFeeamount()+"");
				classFeeSetupVo.setNoofmnths(failureDiomplVo.getNoofmnths());
				classFeeSetupVo.setStmnth(failureDiomplVo.getStmnth());
				classFeeSetupVo.setEndmnth(failureDiomplVo.getEndmnth());
				classFeeSetupVo.setPaymentmode(failureDiomplVo.getPaymentmode());
				classFeeSetupVo.setReason(failureDiomplVo.getReason());
				failurelist.add(classFeeSetupVo);
		    }
		
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertStudentXSL Ending");
	
		return failurelist;
	}
	
}
