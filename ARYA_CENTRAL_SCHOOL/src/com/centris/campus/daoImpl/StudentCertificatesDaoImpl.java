package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StudentCertificatesDao;
import com.centris.campus.pojo.StudentCertificatesPOJO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.StudentCertificatesSqlConstants;
import com.centris.campus.vo.StudentCertificatesVo;


public class StudentCertificatesDaoImpl implements StudentCertificatesDao {
	
	private static final Logger logger = Logger.getLogger(StudentCertificatesDaoImpl.class);

	@Override
	public ArrayList<StudentCertificatesVo> getStudentsDetails(String loc,String acyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesDaoImpl: getStudentsDetails : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<StudentCertificatesVo> list = new ArrayList<StudentCertificatesVo>();
		int slno =0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(StudentCertificatesSqlConstants.GET_STUDENTS_CERTIFICATE);
			pstmt.setString(1,loc);
			pstmt.setString(2,acyear);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno++;
				StudentCertificatesVo obj = new StudentCertificatesVo();
				obj.setSlno(slno);
				obj.setStuid(rs.getString("student_id_int"));
				obj.setStuname(rs.getString("studentName"));
				obj.setRollno(rs.getString("student_rollno"));
				obj.setClassid(rs.getString("classdetail_id_int"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionid(rs.getString("classsection_id_int"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setAdmissionno(rs.getString("student_admissionno_var"));
				obj.setLocid(rs.getString("locationId"));
				list.add(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesDaoImpl : getStudentsDetails : Ending");
		return list;
	}

	@Override
	public StudentCertificatesVo getStudentCertificate(StudentCertificatesPOJO pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesDaoImpl: getStudentsDetails : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentCertificatesVo obj = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(StudentCertificatesSqlConstants.GET_STUDENTS_DETAILS);
			pstmt.setString(1,pojo.getStuid());
			pstmt.setString(2,pojo.getAccyear());
			pstmt.setString(3,pojo.getLocid());
			rs = pstmt.executeQuery();
			while(rs.next()){
				obj = new StudentCertificatesVo();
				obj.setClassid(rs.getString("classdetail_id_int"));
				obj.setSectionid(rs.getString("classsection_id_int"));
				obj.setLocid(rs.getString("locationId"));
				obj.setAccyear(rs.getString("fms_acadamicyear_id_int"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setLocname(rs.getString("Location_Name"));
				obj.setAccyearid(rs.getString("acadamic_year"));
				obj.setAddress(rs.getString("address"));
				obj.setStuname(rs.getString("studentName"));
				obj.setAdmissionno(rs.getString("student_admissionno_var"));
				obj.setDob(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				obj.setFathername(rs.getString("FatherName"));
				obj.setMotherName(rs.getString("student_mothername_var"));
				if(rs.getString("student_gender_var").equalsIgnoreCase("Male")){
					obj.setGender("Male");
				}else if(rs.getString("student_gender_var").equalsIgnoreCase("Female")){
					obj.setGender("Female");
				}
				obj.setImgurl(rs.getString("student_imgurl_var"));
				PreparedStatement status = conn.prepareStatement("select student_status from campus_student_classdetails where student_id_int = ? and fms_acadamicyear_id_int= ? and locationId = ?");
				status.setString(1,pojo.getStuid());
				status.setString(2,pojo.getAccyear());
				status.setString(3,pojo.getLocid());
				ResultSet rsatatus = status.executeQuery();
				while(rsatatus.next()){
					obj.setStudentstatus(rsatatus.getString("student_status"));
				}
				status.close();
				rsatatus.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesDaoImpl : getStudentsDetails : Ending");
		return obj;
	}

	@Override
	public String saveAgeCertificateData(StudentCertificatesPOJO pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesDaoImpl: saveAgeCertificateData : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count =0;
		IDGenerator id = new IDGenerator();
		String result = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(StudentCertificatesSqlConstants.ADD_AGE_CERTIFICATE);
			pstmt.setString(1,id.getPrimaryKeyID("campus_student_age_certificate"));
			pstmt.setString(2,pojo.getAccyear());
			pstmt.setString(3,pojo.getLocid());
			pstmt.setString(4,pojo.getStuname());
			pstmt.setString(5,pojo.getClassid());
			pstmt.setString(6,pojo.getSectionid());
			pstmt.setString(7,pojo.getPurpose());
			pstmt.setString(8,pojo.getOtherinfo());
			pstmt.setString(9,pojo.getStudentstatus());
			count = pstmt.executeUpdate();
			
			if(count >0){
				result = "true";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesDaoImpl : getStudentsDetails : Ending");
		return result;
	}

	@Override
	public ArrayList<StudentCertificatesVo> getissueddetails(StudentCertificatesPOJO pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesDaoImpl: saveAgeCertificateData : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		ArrayList<StudentCertificatesVo> obj =new ArrayList<StudentCertificatesVo>();
		int slno = 0;
		 try{
			 conn = JDBCConnection.getSeparateConnection();
			 pstmt = conn.prepareStatement("select substr(entryDate,1,10) as date,purpose,AgeCertificate_id from campus_student_age_certificate where stuId = ? and Accyear_id = ? and loc_id like ? order by entryDate desc");
			 pstmt.setString(1,pojo.getStuid());
			 pstmt.setString(2,pojo.getAccyear());
			 pstmt.setString(3,pojo.getLocid());
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				 slno++;
				 StudentCertificatesVo list = new StudentCertificatesVo();
				 list.setPurpose(rs.getString("purpose"));
				 list.setSlno(slno);
				 list.setEntryDate(HelperClass.convertDatabaseToUI(rs.getString("date")));
				 list.setAgecertid(rs.getString("AgeCertificate_id"));
				 obj.add(list);
			 }
		 }catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return obj;
	}

	@Override
	public String saveBonafiedCertificateData(StudentCertificatesPOJO pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesDaoImpl: saveAgeCertificateData : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count =0;
		IDGenerator id = new IDGenerator();
		String result = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(StudentCertificatesSqlConstants.ADD_BONAFIED_CERTIFICATE);
			pstmt.setString(1,id.getPrimaryKeyID("campus_student_bonafied_certificate"));
			pstmt.setString(2,pojo.getStuname());
			pstmt.setString(3,pojo.getClassid());
			pstmt.setString(4,pojo.getSectionid());
			pstmt.setString(5,pojo.getAccyear());
			pstmt.setString(6,pojo.getLocid());
			pstmt.setString(7,pojo.getPurpose());
			pstmt.setString(8,pojo.getOtherinfo());
			pstmt.setString(9,pojo.getStudentstatus());
			count = pstmt.executeUpdate();
			
			if(count >0){
				result = "true";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesDaoImpl : getStudentsDetails : Ending");
		return result;
	}

	@Override
	public ArrayList<StudentCertificatesVo> getbonafiedissueddetails(StudentCertificatesPOJO pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesDaoImpl: saveAgeCertificateData : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		ArrayList<StudentCertificatesVo> obj =new ArrayList<StudentCertificatesVo>();
		int slno = 0;
		 try{
			 conn = JDBCConnection.getSeparateConnection();
			 pstmt = conn.prepareStatement("select substr(entrydate,1,10) as date,purpose,bonafiedcertiid from campus_student_bonafied_certificate where stuid = ? and accyearid = ? and locid like ? order by entrydate desc");
			 pstmt.setString(1,pojo.getStuid());
			 pstmt.setString(2,pojo.getAccyear());
			 pstmt.setString(3,pojo.getLocid());
			 System.out.println(pstmt);
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				 slno++;
				 StudentCertificatesVo list = new StudentCertificatesVo();
				 list.setPurpose(rs.getString("purpose"));
				 list.setSlno(slno);
				 list.setEntryDate(HelperClass.convertDatabaseToUI(rs.getString("date")));
				 list.setAgecertid(rs.getString("bonafiedcertiid"));
				 obj.add(list);
			 }
		 }catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return obj;
	}

	@Override
	public String saveCounductedCertificateData(StudentCertificatesPOJO pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesDaoImpl: saveCounductedCertificateData : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count =0;
		IDGenerator id = new IDGenerator();
		String result = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(StudentCertificatesSqlConstants.ADD_CONDUCTED_CERTIFICATE);
			pstmt.setString(1,id.getPrimaryKeyID("campus_student_conduct_certificate"));
			pstmt.setString(2,pojo.getAccyear());
			pstmt.setString(3,pojo.getLocid());
			pstmt.setString(4,pojo.getStuname());
			pstmt.setString(5,pojo.getClassid());
			pstmt.setString(6,pojo.getSectionid());
			pstmt.setString(7,pojo.getPurpose());
			pstmt.setString(8,pojo.getOtherinfo());
			pstmt.setString(9,pojo.getConductno());
			pstmt.setString(10,pojo.getConduct());
			pstmt.setString(11,pojo.getStudentstatus());
			count = pstmt.executeUpdate();
			
			if(count >0){
				result = "true";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesDaoImpl : saveCounductedCertificateData : Ending");
		return result;
	}
public ArrayList<StudentCertificatesVo> getconductissueddetails(StudentCertificatesPOJO pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesDaoImpl: saveAgeCertificateData : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		ArrayList<StudentCertificatesVo> obj =new ArrayList<StudentCertificatesVo>();
		int slno = 0;
		 try{
			 conn = JDBCConnection.getSeparateConnection();
			 pstmt = conn.prepareStatement("select substr(entrydate,1,10) as date,purpose,conductedcetificate_id from campus_student_conduct_certificate where stu_id = ? and accyear_id = ? and loc_id like ? order by entrydate desc");
			 pstmt.setString(1,pojo.getStuid());
			 pstmt.setString(2,pojo.getAccyear());
			 pstmt.setString(3,pojo.getLocid());
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				 slno++;
				 StudentCertificatesVo list = new StudentCertificatesVo();
				 list.setPurpose(rs.getString("purpose"));
				 list.setSlno(slno);
				 list.setEntryDate(HelperClass.convertDatabaseToUI(rs.getString("date")));
				 list.setAgecertid(rs.getString("conductedcetificate_id"));
				 obj.add(list);
			 }
		 }catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return obj;
	}

@Override
public String saveCourseCounductedCertificateData(StudentCertificatesPOJO pojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesDaoImpl: saveCounductedCertificateData : Starting");
	Connection conn = null;
	PreparedStatement pstmt = null;
	int count =0;
	IDGenerator id = new IDGenerator();
	String result = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		pstmt = conn.prepareStatement(StudentCertificatesSqlConstants.ADD_COURSE_CONDUCTED_CERTIFICATE);
		pstmt.setString(1,id.getPrimaryKeyID("campus_student_course_conduct_cetificate"));
		pstmt.setString(2,pojo.getAccyear());
		pstmt.setString(3,pojo.getLocid());
		pstmt.setString(4,pojo.getStuname());
		pstmt.setString(5,pojo.getClassid());
		pstmt.setString(6,pojo.getSectionid());
		pstmt.setString(7,pojo.getPurpose());
		pstmt.setString(8,pojo.getOtherinfo());
		pstmt.setString(9,pojo.getConductno());
		pstmt.setString(10,pojo.getConduct());
		pstmt.setString(11,pojo.getStudentstatus());
		count = pstmt.executeUpdate();
		
		if(count >0){
			result = "true";
		}
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesDaoImpl : saveCounductedCertificateData : Ending");
	return result;
}
public ArrayList<StudentCertificatesVo> getcourseconductissueddetails(StudentCertificatesPOJO pojo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesDaoImpl: getcourseconductissueddetails : Starting");
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	ArrayList<StudentCertificatesVo> obj =new ArrayList<StudentCertificatesVo>();
	int slno = 0;
	 try{
		 conn = JDBCConnection.getSeparateConnection();
		 pstmt = conn.prepareStatement("select substr(entrydate,1,10) as date,purpose,course_conduct_certifi_id from campus_student_course_conduct_cetificate where stu_id = ? and accyear_id = ? and loc_id like ? order by entrydate desc");
		 pstmt.setString(1,pojo.getStuid());
		 pstmt.setString(2,pojo.getAccyear());
		 pstmt.setString(3,pojo.getLocid());
		 rs = pstmt.executeQuery();
		 while(rs.next()){
			 slno++;
			 StudentCertificatesVo list = new StudentCertificatesVo();
			 list.setPurpose(rs.getString("purpose"));
			 list.setSlno(slno);
			 list.setEntryDate(HelperClass.convertDatabaseToUI(rs.getString("date")));
			 list.setAgecertid(rs.getString("course_conduct_certifi_id"));
			 obj.add(list);
		 }
	 }catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	return obj;
}

@Override
public String saveStudentVisaCertificateData(StudentCertificatesPOJO pojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesDaoImpl: saveCounductedCertificateData : Starting");
	Connection conn = null;
	PreparedStatement pstmt = null;
	int count =0;
	IDGenerator id = new IDGenerator();
	String result = null;
	String purpose = null;
	try {
		
		conn = JDBCConnection.getSeparateConnection();
		pstmt = conn.prepareStatement(StudentCertificatesSqlConstants.ADD_STUDENT_VISA_CERTIFICATE);
		pstmt.setString(1,id.getPrimaryKeyID("campus_student_visa_certificate"));
		pstmt.setString(2,pojo.getAccyear());
		pstmt.setString(3,pojo.getLocid());
		pstmt.setString(4,pojo.getStuname());
		pstmt.setString(5,pojo.getClassid());
		pstmt.setString(6,pojo.getSectionid());
		pstmt.setString(7,pojo.getPurpose());
		pstmt.setString(8,pojo.getPassportno());
		pstmt.setString(9,pojo.getStudentstatus());
		count = pstmt.executeUpdate();
		
		if(count >0){
			result = "true";
		}
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesDaoImpl : saveCounductedCertificateData : Ending");
	return result;
}
public ArrayList<StudentCertificatesVo> getstudentvisaissueddetails(StudentCertificatesPOJO pojo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesDaoImpl: getstudentvisaissueddetails : Starting");
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	String purpose = null;
	ArrayList<StudentCertificatesVo> obj =new ArrayList<StudentCertificatesVo>();
	int slno = 0;
	 try{
		 conn = JDBCConnection.getSeparateConnection();
		 pstmt = conn.prepareStatement("select substr(entrydate,1,10) as date,purpose,studentvisa_id from campus_student_visa_certificate where stu_id = ? and accyear_id = ? and loc_id like ? order by entrydate desc");
		 pstmt.setString(1,pojo.getStuid());
		 pstmt.setString(2,pojo.getAccyear());
		 pstmt.setString(3,pojo.getLocid());
		 rs = pstmt.executeQuery();
		 while(rs.next()){
			 slno++;
			 StudentCertificatesVo list = new StudentCertificatesVo();
			 purpose = (rs.getString("purpose"));

				if(purpose.equalsIgnoreCase("applypassport")){
					purpose = "Applying For Passport";
				}else if(purpose.equalsIgnoreCase("renewal")){
					purpose = "Passport Renewal";
				}else if(purpose.equalsIgnoreCase("applyvisa")){
					purpose = "Applying For VISA";
				}
			 list.setPurpose(purpose);
			 list.setSlno(slno);
			 list.setEntryDate(HelperClass.convertDatabaseToUI(rs.getString("date")));
			 list.setAgecertid(rs.getString("studentvisa_id"));
			 obj.add(list);
		 }
	 }catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	return obj;
}

@Override
public ArrayList<StudentCertificatesVo> displayageissueddetails(String stuid, String agecetiid,String selection) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesDaoImpl: displayageissueddetails : Starting");
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ArrayList<StudentCertificatesVo> list = new ArrayList<StudentCertificatesVo>();
	int slno =0;
	try {
		conn = JDBCConnection.getSeparateConnection();
		if(selection.equalsIgnoreCase("age")){
			pstmt = conn.prepareStatement(StudentCertificatesSqlConstants.GET_DISPLAY_STUDENTS_CERTIFICATE);
		}else if(selection.equalsIgnoreCase("bonafied")){
			pstmt = conn.prepareStatement(StudentCertificatesSqlConstants.GET_DISPLAY_BONAFIED_CERTIFICATE);
		}
		
		pstmt.setString(1,stuid);
		pstmt.setString(2,agecetiid);
		System.out.println(pstmt);
		rs = pstmt.executeQuery();
		while(rs.next()){
			slno++;
			StudentCertificatesVo obj = new StudentCertificatesVo();
			obj.setSlno(slno);
			obj.setAccyear(rs.getString("acadamic_year"));
			obj.setLocname(rs.getString("Location_Name"));
			obj.setClassname(rs.getString("classdetails_name_var"));
			obj.setSectionname(rs.getString("classsection_name_var"));
			obj.setStudentstatus(rs.getString("studentstatus"));
			obj.setAddress(rs.getString("address"));
			obj.setStuname(rs.getString("studentName"));
			obj.setAdmissionno(rs.getString("student_admissionno_var"));
			obj.setDob(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
			obj.setFathername(rs.getString("FatherName"));
			obj.setMotherName(rs.getString("student_mothername_var"));
			if(rs.getString("student_gender_var").equalsIgnoreCase("Male")){
				obj.setGender("Male");
			}else if(rs.getString("student_gender_var").equalsIgnoreCase("Female")){
				obj.setGender("Female");
			}
			obj.setPurpose(rs.getString("purpose"));
			obj.setOtherinfo(rs.getString("otherinfo"));
			obj.setImgurl(rs.getString("student_imgurl_var"));
			list.add(obj);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesDaoImpl : displayageissueddetails : Ending");
	return list;
}

@Override
public ArrayList<StudentCertificatesVo> displayconductdetails(String stuid, String agecetiid, String selection) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesDaoImpl: displayageissueddetails : Starting");
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ArrayList<StudentCertificatesVo> list = new ArrayList<StudentCertificatesVo>();
	int slno =0;
	try {
		conn = JDBCConnection.getSeparateConnection();
		if(selection.equalsIgnoreCase("conduct")){
			pstmt = conn.prepareStatement(StudentCertificatesSqlConstants.GET_DISPLAY_CONDUCT_CERTIFICATE);
		}else if(selection.equalsIgnoreCase("courseconduct")){
			pstmt = conn.prepareStatement(StudentCertificatesSqlConstants.GET_DISPLAY_COURSECONDUCT_CERTIFICATE);
		}
		
		pstmt.setString(1,stuid);
		pstmt.setString(2,agecetiid);
		rs = pstmt.executeQuery();
		while(rs.next()){
			slno++;
			StudentCertificatesVo obj = new StudentCertificatesVo();
			obj.setSlno(slno);
			obj.setAccyear(rs.getString("acadamic_year"));
			obj.setLocname(rs.getString("Location_Name"));
			obj.setClassname(rs.getString("classdetails_name_var"));
			obj.setSectionname(rs.getString("classsection_name_var"));
			obj.setStudentstatus(rs.getString("studentstatus"));
			obj.setAddress(rs.getString("address"));
			obj.setStuname(rs.getString("studentName"));
			obj.setAdmissionno(rs.getString("student_admissionno_var"));
			obj.setDob(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
			obj.setFathername(rs.getString("FatherName"));
			obj.setMotherName(rs.getString("student_mothername_var"));
			if(rs.getString("student_gender_var").equalsIgnoreCase("Male")){
				obj.setGender("Male");
			}else if(rs.getString("student_gender_var").equalsIgnoreCase("Female")){
				obj.setGender("Female");
			}
			obj.setConduct(rs.getString("conduct"));
			obj.setConductno(rs.getString("conductno"));
			
			obj.setPurpose(rs.getString("purpose"));
			obj.setOtherinfo(rs.getString("otherinfo"));
			list.add(obj);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesDaoImpl : displayageissueddetails : Ending");
	return list;
}

@Override
public ArrayList<StudentCertificatesVo> displaystudentvisadetails(String stuid, String agecetiid) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesDaoImpl: displayageissueddetails : Starting");
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ArrayList<StudentCertificatesVo> list = new ArrayList<StudentCertificatesVo>();
	int slno =0;
	try {
		conn = JDBCConnection.getSeparateConnection();
		pstmt = conn.prepareStatement(StudentCertificatesSqlConstants.GET_DISPLAY_STUDENTVISA_CERTIFICATE);
		pstmt.setString(1,stuid);
		pstmt.setString(2,agecetiid);
		rs = pstmt.executeQuery();
		while(rs.next()){
			slno++;
			StudentCertificatesVo obj = new StudentCertificatesVo();
			obj.setSlno(slno);
			obj.setAccyear(rs.getString("acadamic_year"));
			obj.setLocname(rs.getString("Location_Name"));
			obj.setClassname(rs.getString("classdetails_name_var"));
			obj.setSectionname(rs.getString("classsection_name_var"));
			obj.setStudentstatus(rs.getString("studentstatus"));
			obj.setAddress(rs.getString("address"));
			obj.setStuname(rs.getString("studentName"));
			obj.setAdmissionno(rs.getString("student_admissionno_var"));
			obj.setDob(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
			obj.setFathername(rs.getString("FatherName"));
			obj.setMotherName(rs.getString("student_mothername_var"));
			if(rs.getString("student_gender_var").equalsIgnoreCase("Male")){
				obj.setGender("Male");
			}else if(rs.getString("student_gender_var").equalsIgnoreCase("Female")){
				obj.setGender("Female");
			}
			obj.setPassportno(rs.getString("passportNo"));
			
			obj.setPurpose(rs.getString("purpose"));
			
			list.add(obj);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesDaoImpl : displayageissueddetails : Ending");
	return list;
}

}
