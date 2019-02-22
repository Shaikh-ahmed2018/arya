package com.centris.campus.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.UploadStaffXLSDaoImpl;
import com.centris.campus.daoImpl.UploadStageXLSDaoImpl;
import com.centris.campus.daoImpl.UploadStudentXLSDaoImpl;
import com.centris.campus.pojo.UploadStaffXlsPOJO;
import com.centris.campus.pojo.UploadStaffXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.service.UploadStaffXSLservice;
import com.centris.campus.service.UploadStageXSLservice;
import com.centris.campus.service.UploadStudentXSLservice;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.UploadStaffXlsVO;
import com.centris.campus.vo.UploadStaffXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;
import com.itextpdf.text.log.SysoLogger;


public class UploadStaffXSLServiceIMPL implements UploadStaffXSLservice {
	
	private static Logger logger = Logger.getLogger(UploadStaffXSLServiceIMPL.class);


	public Set<UploadStaffXlsVO> insertStaffXSL(
			List<UploadStaffXlsPOJO> list, String username, String duplicate) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLServiceImpl : insertStaffXSL : Starting");
		
		Connection connection = null;
		
		List<UploadStaffXlsPOJO> successlist=new ArrayList<UploadStaffXlsPOJO>();
		UploadStaffXLSDaoImpl daoImpl=new UploadStaffXLSDaoImpl();
	
		int count = 0;
	
		Set<UploadStaffXlsVO> failurelist = new LinkedHashSet<UploadStaffXlsVO>();
	
		failurelist.clear();
		successlist.clear();

		try {
			System.out.println("Service IMPL is working for Excel file student");
			connection=JDBCConnection.getSeparateConnection();
			
			
			
			String int_regex="^[0-9]*$";
			String numbers_regx = "[0-9//]{10}";
			
			String string_regx="([a-zA-Z.]+\\s+)*[a-zA-Z.]+";
			String datePattern = "\\d{1,2}\\-\\d{1,2}\\-\\d{4}";
			String regexpforEmailId="/[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]*$/i";
			 
			for (Iterator<UploadStaffXlsPOJO> iterator = list.iterator(); iterator.hasNext();) {

				UploadStaffXlsPOJO uploadStaffXSLPOJO = (UploadStaffXlsPOJO) iterator.next();
									
				UploadStaffXlsVO uploadStaffXSLVo = new UploadStaffXlsVO();
				uploadStaffXSLPOJO.setCreatedby(username);
				
				System.out.println("Date formate: "+uploadStaffXSLPOJO.getDateOfJoining());
				System.out.println("Date formate: "+uploadStaffXSLPOJO.getDob());
				
				uploadStaffXSLVo.setFirstName(uploadStaffXSLPOJO.getFirstName());
				String firstName=uploadStaffXSLPOJO.getFirstName();
				String lastName=uploadStaffXSLPOJO.getLastName();
				String fullName=firstName+" "+lastName;
				
				String departmentId=null;
				if(uploadStaffXSLPOJO.getDepartment() !=null || !uploadStaffXSLPOJO.getDepartment().equalsIgnoreCase("")){
					System.out.println("department id bolck");
					departmentId=daoImpl.getDepartmentId(uploadStaffXSLPOJO.getDepartment());
				}
			
				String designationId=null;
				if(uploadStaffXSLPOJO.getDesignation() !=null || !uploadStaffXSLPOJO.getDesignation().equalsIgnoreCase("")){
					designationId=daoImpl.getDesignationId(uploadStaffXSLPOJO.getDesignation());
				}
				String locationId=null;
				if(uploadStaffXSLPOJO.getLocation() !=null || !uploadStaffXSLPOJO.getLocation().equalsIgnoreCase("")){
					locationId=new UploadStudentXLSDaoImpl().getSchoolLocationId(uploadStaffXSLPOJO.getLocation(), connection);
				}
				 
				String reportingToId=null;
				if(uploadStaffXSLPOJO.getReportingTo() !=null || !uploadStaffXSLPOJO.getReportingTo().equalsIgnoreCase("")){
					reportingToId=daoImpl.getReportingToId(uploadStaffXSLPOJO.getReportingTo());
				}
				String roleId=null;
				if(uploadStaffXSLPOJO.getRole() !=null || !uploadStaffXSLPOJO.getRole().equalsIgnoreCase("")){
					roleId=daoImpl.getRoleId(uploadStaffXSLPOJO.getRole());
				}
				 
				
				uploadStaffXSLVo.setRegistrationId(uploadStaffXSLPOJO.getRegistrationId());
				uploadStaffXSLVo.setDepartment(uploadStaffXSLPOJO.getDepartment());
				uploadStaffXSLVo.setDateOfJoining(uploadStaffXSLPOJO.getDateOfJoining());
				uploadStaffXSLVo.setDesignation(uploadStaffXSLPOJO.getDesignation());
				uploadStaffXSLVo.setTeachingType(uploadStaffXSLPOJO.getTeachingType());
				//uploadStaffXSLVo.setPrimarySubject(uploadStaffXSLPOJO.getPrimarySubject());
				uploadStaffXSLVo.setUserName(uploadStaffXSLPOJO.getUserName());
				uploadStaffXSLVo.setUserType(uploadStaffXSLPOJO.getUserType());
				uploadStaffXSLVo.setRole(uploadStaffXSLPOJO.getRole());
				uploadStaffXSLVo.setIsStudentStudying(uploadStaffXSLPOJO.getIsStudentStudying());
				uploadStaffXSLVo.setGender(uploadStaffXSLPOJO.getGender());
				uploadStaffXSLVo.setDob(uploadStaffXSLPOJO.getDob());
				uploadStaffXSLVo.setQualification(uploadStaffXSLPOJO.getQualification());
				uploadStaffXSLVo.setMobileNo(uploadStaffXSLPOJO.getMobileNo());
				uploadStaffXSLVo.setFatherName(uploadStaffXSLPOJO.getFatherName());
				uploadStaffXSLVo.setMotherName(uploadStaffXSLPOJO.getMotherName());
				uploadStaffXSLVo.setPresentAddress(uploadStaffXSLPOJO.getPresentAddress());
				uploadStaffXSLVo.setPermanentAddress(uploadStaffXSLPOJO.getPermanentAddress());
				
				if(uploadStaffXSLPOJO.getRegistrationId()==null || uploadStaffXSLPOJO.getRegistrationId().equalsIgnoreCase("")){
					uploadStaffXSLVo.setFirstName(fullName);
					failurelist.add(uploadStaffXSLVo);
					uploadStaffXSLVo.setReason("Teacher Id Should Not be Empty.");
				/*}else if(uploadStaffXSLPOJO.getAbbreviation()==null || uploadStaffXSLPOJO.getAbbreviation().equalsIgnoreCase("")){
					uploadStaffXSLVo.setFirstName(fullName);
					failurelist.add(uploadStaffXSLVo);
					uploadStaffXSLVo.setReason("Abbreviation Should Not be Empty.");*/
				}
				else if(uploadStaffXSLPOJO.getFirstName()=="" || uploadStaffXSLPOJO.getFirstName().equals("-") || uploadStaffXSLPOJO.getFirstName()==null){
					
					uploadStaffXSLVo.setReason("First Name Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				}else if(!(uploadStaffXSLPOJO.getFirstName().matches(string_regx))){
				   uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("First Name Should be Alphabet");
					failurelist.add(uploadStaffXSLVo);
				}
				
				else if(daoImpl.checkRegistrainId(uploadStaffXSLPOJO.getRegistrationId(),connection) !=0){
				
					uploadStaffXSLVo.setFirstName(fullName);
					failurelist.add(uploadStaffXSLVo);
					uploadStaffXSLVo.setReason("Teacher Id Already Exist in Database.");
				}else if(uploadStaffXSLPOJO.getDuplicateInExcel()==1){
					uploadStaffXSLVo.setFirstName(fullName);
					System.out.println("Duplicate Record in Excel! ");
					uploadStaffXSLVo.setReason("Duplicate in Excel");
					failurelist.add(uploadStaffXSLVo);
		/*		}	else if(uploadStaffXSLPOJO.getLocation()=="" || uploadStaffXSLPOJO.getLocation().equals("-") || uploadStaffXSLPOJO.getLocation()==null){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("School Name Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);*/
				}else if(!uploadStaffXSLPOJO.getLocation().equalsIgnoreCase("") && locationId==null){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Invalid School Name.");
					failurelist.add(uploadStaffXSLVo);
				/*}
				else if(uploadStaffXSLPOJO.getDepartment()=="" || uploadStaffXSLPOJO.getDepartment().equals("-") || uploadStaffXSLPOJO.getDepartment()==null){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Department Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);*/
				}else if(!uploadStaffXSLPOJO.getDepartment().equalsIgnoreCase("") && departmentId==null){
					System.out.println("Validation for department code. ");
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Invalid Department.");
					failurelist.add(uploadStaffXSLVo);
				}else if(!uploadStaffXSLPOJO.getDesignation().equalsIgnoreCase("") && designationId==null){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Invalid Designation.");
					failurelist.add(uploadStaffXSLVo);
			
				}else if(!uploadStaffXSLPOJO.getRole().equalsIgnoreCase("") && roleId==null){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Invalid Role.");
					failurelist.add(uploadStaffXSLVo);
					
				}else if(!uploadStaffXSLPOJO.getReportingTo().equalsIgnoreCase("") && reportingToId == null){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Invalid Reporting Person.");
					failurelist.add(uploadStaffXSLVo);		
				}else if(uploadStaffXSLPOJO.getIsStudentStudying().equalsIgnoreCase("yes") && (uploadStaffXSLPOJO.getAdmissionNo()==null || uploadStaffXSLPOJO.getAdmissionNo().equalsIgnoreCase(""))){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Enter Student Admission No.");
					failurelist.add(uploadStaffXSLVo);
			/*	}else if(uploadStaffXSLPOJO.getGender()=="" || uploadStaffXSLPOJO.getGender().equals("-") || uploadStaffXSLPOJO.getGender()==null){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Gender Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);*/
			/*	}else if(uploadStaffXSLPOJO.getDob()=="" || uploadStaffXSLPOJO.getDob().equals("-") || uploadStaffXSLPOJO.getDob()==null){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Date Of Birth Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				*/
				/*}else if(uploadStaffXSLPOJO.getMobileNo()=="" || uploadStaffXSLPOJO.getMobileNo().equals("-") || uploadStaffXSLPOJO.getMobileNo()==null){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Mobile No Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				
				}else if(!(uploadStaffXSLPOJO.getMobileNo().matches(int_regex))){
					uploadStaffXSLVo.setReason("Enter Valid Mobile Number");
					failurelist.add(uploadStaffXSLVo);
				}else if(uploadStaffXSLPOJO.getMobileNo().equals(numbers_regx)){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Mobile No should be in Numbers.");
					failurelist.add(uploadStaffXSLVo);
					
				}else if(uploadStaffXSLPOJO.getMobileNo().length() > 10 || uploadStaffXSLPOJO.getMobileNo().length() < 10){
					uploadStaffXSLVo.setReason("Mobile Number No should be 10 digit");
					failurelist.add(uploadStaffXSLVo);*/
					/*}				
				
				else if(uploadStaffXSLPOJO.getFatherName()=="" || uploadStaffXSLPOJO.getFatherName().equals("-") || uploadStaffXSLPOJO.getFatherName()==null){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Father Name Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				}else if(uploadStaffXSLPOJO.getMotherName()=="" || uploadStaffXSLPOJO.getMotherName().equals("-") || uploadStaffXSLPOJO.getMotherName()==null){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Mother Name Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
					*/
				/*}else if(uploadStaffXSLPOJO.getPresentAddress()=="" || uploadStaffXSLPOJO.getPresentAddress().equals("-") || uploadStaffXSLPOJO.getPresentAddress()==null){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Present Address Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				}else if(uploadStaffXSLPOJO.getPermanentAddress()=="" || uploadStaffXSLPOJO.getPermanentAddress().equals("-") || uploadStaffXSLPOJO.getPermanentAddress()==null){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Permanent Address Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);*/
				
				}else{
					uploadStaffXSLPOJO.setDepartment(departmentId);
					uploadStaffXSLPOJO.setDesignation(designationId);
					uploadStaffXSLPOJO.setRole(roleId);
					uploadStaffXSLPOJO.setReportingTo(reportingToId);
					uploadStaffXSLPOJO.setLocation(locationId);
					System.out.println("Inside Else Block");
					successlist.add(uploadStaffXSLPOJO);
					System.out.println("successlist.size()"+successlist.size());
					JSONArray j=new JSONArray(successlist);
				  	System.out.println("j:::"+j);
			   }
				
		}
			
			
			
			
		String success=daoImpl.insertStaffXSL(successlist,connection);
		
		System.out.println("in serviceImpl: inserted Record  are:::::= "+success);
		System.out.println("in service IMPL: faulurelist list size::::= "+failurelist.size());
		
		
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
				+ " Control in UploadStageXSLServiceImpl : insertStageXSL : Ending");

		return failurelist;
	}

}
