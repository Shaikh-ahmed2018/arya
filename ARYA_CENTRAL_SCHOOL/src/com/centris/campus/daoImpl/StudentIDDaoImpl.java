package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StudentIDDao;
import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.StudentIDVo;
import com.centris.campus.vo.StudentPramotionVO;



public class StudentIDDaoImpl implements StudentIDDao {
 
	private static final Logger logger= Logger.getLogger(StudentIDDaoImpl.class);
	@Override
	public List<StudentIDVo> getstudentIDPDFReport(String accyear, String section, String className, String student) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStdMarksDetails : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<StudentIDVo> list=new ArrayList<StudentIDVo>() ;
		
		try{
			
			String studentid[]=student.split(",");
			conn=JDBCConnection.getSeparateGodaddyConnection();
			for(int i=0;i<studentid.length;i++){
			psmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_ID_DETAILS);
			psmt.setString(1, studentid[i].trim());
			psmt.setString(2, accyear.trim());
			psmt.setString(3, className.trim());
			psmt.setString(4, section.trim());
			rs=psmt.executeQuery();
			while(rs.next()){
				
				StudentIDVo studentlist = new StudentIDVo();
				studentlist.setStuName(rs.getString("studentName"));
				studentlist.setAdress(rs.getString("address"));
				studentlist.setClassName(rs.getString("classdetails_name_var"));
				studentlist.setSection(rs.getString("classsection_name_var"));
				studentlist.setPhone(rs.getString("mobileno"));
				studentlist.setValidity(HelperClass.convertDatabaseToUI(rs.getString("endDate")));
				studentlist.setAdmissionno(rs.getString("student_admissionno_var"));
				
				String image=rs.getString("student_imgurl_var");
		
				if(image!=null){
				String[] x=image.split("/");
				studentlist.setImage(x[2]);
				}
				list.add(studentlist);
				
				/*String images=GetStudentImages.imageName(x[2]);
				System.out.println("images----------------------->"+images);*/
			}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	public List<StudentIDVo> getStudentData(String acadamicYear,
			String section, String classVal) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStdMarksDetails : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<StudentIDVo> list=new ArrayList<StudentIDVo>() ;
		
		try{
			
		
			conn=JDBCConnection.getSeparateGodaddyConnection();
		
			psmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_ID_SEARCH);
	
			psmt.setString(1, acadamicYear.trim());
			psmt.setString(2, classVal.trim());
			psmt.setString(3, section.trim());
			rs=psmt.executeQuery();
			while(rs.next()){
				
				StudentIDVo studentlist = new StudentIDVo();
				studentlist.setStuId(rs.getString("student_id_int"));
				studentlist.setStuName(rs.getString("studentName"));
				studentlist.setStreamName(rs.getString("classstream_name_var"));
				
				studentlist.setClassName(rs.getString("classdetails_name_var"));
				studentlist.setSection(rs.getString("classsection_name_var"));
			
				studentlist.setValidity(HelperClass.convertDatabaseToUI(rs.getString("endDate")));
			
			
				studentlist.setAdmissionno(rs.getString("student_admissionno_var"));
				String image=rs.getString("student_imgurl_var");
		
				
			
				list.add(studentlist);
				
				/*String images=GetStudentImages.imageName(x[2]);
				System.out.println("images----------------------->"+images);*/
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	@Override
	public List<StudentIDVo> getstudentBusIDPDFReport(String accyear[],
			String locationId[], String streamId[], String studentid[]) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStdMarksDetails : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<StudentIDVo> list=new ArrayList<StudentIDVo>() ;
		
		try{
			
			
			conn=JDBCConnection.getSeparateGodaddyConnection();
			for(int i=0;i<studentid.length;i++){
			psmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_BUS_STUDENT_ID_DETAILS);
			psmt.setString(1, locationId[i].trim());
			psmt.setString(2, accyear[i].trim());
			psmt.setString(3, streamId[i].trim());
			psmt.setString(4, studentid[i].trim());
			rs=psmt.executeQuery();
			while(rs.next()){
				
				StudentIDVo studentlist = new StudentIDVo();
				studentlist.setStuName(rs.getString("studentName"));
				studentlist.setClassName(rs.getString("classdetails_name_var"));
				studentlist.setSection(rs.getString("classsection_name_var"));
				studentlist.setValidity(HelperClass.convertDatabaseToUI(rs.getString("endDate")));
				studentlist.setAdmissionno(rs.getString("student_admissionno_var"));
				studentlist.setRoute_no(rs.getString("Route_No"));
				studentlist.setPoint_name(rs.getString("stage_name"));
				studentlist.setDroppoint(rs.getString("drop_point"));
				studentlist.setSchoolName(rs.getString("Location_Name"));
				String image=rs.getString("student_imgurl_var");
				studentlist.setImage(image);
				list.add(studentlist);
				
			}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return list;
	}
	public List<PageFilterVo> getNewstudentIdCardDesignList(
			PageFilterpojo filterpojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstudentIdCardDesignList : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		int sno=0;
		
		try{
			
			
			conn=JDBCConnection.getSeparateGodaddyConnection();/*here*/
		
			psmt=conn.prepareStatement("SELECT ca.acadamic_id,ca.acadamic_year,cl.Location_Id,cl.Location_Name,cl.Location_Address,cl.Location_Phone,cc.classstream_id_int,cc.classstream_name_var,SUBSTR(cl.Location_Id,4) FROM campus_acadamicyear ca,campus_classstream cc JOIN campus_location cl ON cc.locationId=cl.Location_Id WHERE cc.locationId LIKE ? AND ca.acadamic_id LIKE ? AND cc.classstream_id_int LIKE ? ORDER BY CAST(SUBSTR(cl.Location_Id,4)AS UNSIGNED),CAST(SUBSTR(cc.classstream_id_int,4)AS UNSIGNED)");
			psmt.setString(1, filterpojo.getLocationId());
			psmt.setString(2, filterpojo.getAcademicYear());
			
			psmt.setString(3, filterpojo.getStreamId());
		
			rs=psmt.executeQuery();
			while(rs.next()){
				sno++;
				PageFilterVo filterVo = new PageFilterVo();
				filterVo.setSno(sno);
				filterVo.setAcademicYear(rs.getString("acadamic_year"));
				filterVo.setAcademicYearCode(rs.getString("acadamic_id"));
				filterVo.setLocationName(rs.getString("Location_Name"));
				filterVo.setLocationId(rs.getString("Location_Id"));
				filterVo.setStreamId(rs.getString("classstream_id_int"));
				filterVo.setStreamName(rs.getString("classstream_name_var"));
				filterVo.setLocation_address(rs.getString("Location_Address"));
				filterVo.setLocation_phone(rs.getString("Location_Phone"));
				filterVo.setTemplateName(rs.getString("classstream_name_var")+" "+rs.getString("Location_Name")+" "+rs.getString("acadamic_year")+" ");
				filterVo.setTemplateId(rs.getString("acadamic_id")+rs.getString("Location_Id")+rs.getString("classstream_id_int"));
			
				list.add(filterVo);
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
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
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstudentIdCardDesignList : Ending");
		
		return list;
	}
	public List<StudentIDVo> getstudentIDPDFReport(String[] accyear,
			String[] locationId, String[] studentId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStdMarksDetails : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<StudentIDVo> list=new ArrayList<StudentIDVo>() ;
		
		try{
			
			
			conn=JDBCConnection.getSeparateGodaddyConnection();
			for(int i=0;i<studentId.length;i++){
			psmt=conn.prepareStatement("SELECT DISTINCT cont.emergencyNo,cl.Location_Name,cl.Location_Address,cl.Location_Phone,acc.endDate,st.fms_acadamicyear_id_int,cl.Location_Id,cpcr.parentid,par.FatherName,par.mobileno,par.student_mothername_var,par.student_mothermobileno_var,CASE WHEN par.PresentAddress='' THEN par.address ELSE par.PresentAddress END address,par.student_gaurdianname_var,par.student_gardian_mobileno,st.student_id_int,cstd.student_rollno,st.student_admissionno_var,CONCAT(st.student_fname_var,'  ',st.student_lname_var) AS studentName,cstd.student_imgurl_var,acc.acadamic_year,cd.classdetails_name_var,sec.classsection_name_var,cstd.student_status,cl.Location_Name,cstd.secondlanguage,cstd.thirdlanguage,chs.housename,cstd.fms_classstream_id_int,st.student_dob_var,st.adharNo,st.student_bloodgroup_var FROM campus_student st JOIN campus_student_classdetails cstd ON st.student_id_int=cstd.student_id_int JOIN campus_student_transportdetails stt ON st.student_id_int=stt.student_id_int JOIN campus_parentchildrelation cpcr ON cpcr.stu_addmissionNo=st.student_id_int JOIN campus_parents par ON cpcr.parentid=par.ParentID JOIN campus_classdetail cd ON cd.classdetail_id_int=cstd.classdetail_id_int JOIN campus_classsection sec ON sec.classsection_id_int=cstd.classsection_id_int JOIN campus_acadamicyear acc ON acc.acadamic_id=cstd.fms_acadamicyear_id_int JOIN campus_location cl ON cstd.locationId=cl.Location_Id LEFT JOIN campus_student_house cgh ON cgh.student_id=st.student_id_int LEFT JOIN campus_house_settings chs ON chs.house_id=cstd.student_house LEFT JOIN `campus_students_contacts` cont ON st.student_id_int=cont.studentId where cstd.student_id_int=? and cstd.fms_acadamicyear_id_int=? and cstd.locationId=?");
			psmt.setString(1, studentId[i].trim());
			psmt.setString(2, accyear[i].trim());
			psmt.setString(3, locationId[i].trim());
			rs=psmt.executeQuery();
			while(rs.next()){
				
				StudentIDVo studentlist = new StudentIDVo();
				studentlist.setStuName(rs.getString("studentName"));
				studentlist.setAdress(rs.getString("address"));
				studentlist.setClassName(rs.getString("classdetails_name_var"));
				studentlist.setSection(rs.getString("classsection_name_var"));
				studentlist.setPhone(rs.getString("mobileno"));
				studentlist.setValidity(HelperClass.convertDatabaseToUI(rs.getString("endDate")));
				studentlist.setAdmissionno(rs.getString("student_admissionno_var"));
				studentlist.setSecodaryPhone(rs.getString("student_mothermobileno_var"));
				studentlist.setEmergencyNo(rs.getString("emergencyNo"));
				studentlist.setFatherName(rs.getString("FatherName"));
				studentlist.setMotherName(rs.getString("student_mothername_var"));
				studentlist.setHouseName(rs.getString("housename"));
				studentlist.setSchoolName(rs.getString("Location_Name"));
				studentlist.setLocation_address(rs.getString("Location_Address"));
				studentlist.setLocation_phone(rs.getString("Location_phone"));
				studentlist.setDob(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				studentlist.setAdharNo(rs.getString("adharNo"));
				studentlist.setBgroup(rs.getString("student_bloodgroup_var"));
				String image=rs.getString("student_imgurl_var");
				
				if(!image.equalsIgnoreCase("noImage")){
					
					studentlist.setImage(image);
					}
					list.add(studentlist);
				
				/*String images=GetStudentImages.imageName(x[2]);
				System.out.println("images----------------------->"+images);*/
			}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	
	
	public List<PageFilterVo> getstaffIDPDFReport(String[] locationId, String[] teacherID) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStdMarksDetails : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		

		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		
		try{
			conn=JDBCConnection.getSeparateGodaddyConnection();
			for(int i=0;i<teacherID.length;i++){
			psmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_STAFF_ID_MULTIPLE_DETAILS);
				psmt.setString(1, teacherID[i].trim());
				psmt.setString(2, locationId[i].trim());
				
				System.out.println(psmt);
				rs=psmt.executeQuery();
				
				while(rs.next()){
					
					PageFilterVo filterVo = new PageFilterVo();
				
					filterVo.setTeacherName(rs.getString("teacherName"));
					filterVo.setTeacherID(rs.getString("registerId"));
					filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
					filterVo.setDesignationName(rs.getString("designationName"));
					filterVo.setMobileNo(rs.getString("mobileNo"));
					filterVo.setAddress(rs.getString("presentAddress"));
					filterVo.setAbbrivateId(rs.getString("Abbreviative_Id"));
					
					
					filterVo.setLocationName(rs.getString("Location_Name"));
					filterVo.setLocation_address(rs.getString("Location_Address"));
					filterVo.setLocation_phone(rs.getString("Location_Phone"));
					filterVo.setLocationId(locationId[i].trim());
					filterVo.setDesignationId(rs.getString("DesignationCode"));
					filterVo.setDesignationName(rs.getString("designationName"));
					filterVo.setDepartmentId(rs.getString("DEPT_ID"));
					
					String image=rs.getString("imagePath");
					System.out.println("image   ::"+image);
							
							if(!(image.trim().equalsIgnoreCase("noImage"))){
								filterVo.setImage(image);
								}else
								{
									filterVo.setImage("noImage");	
								}
								
					list.add(filterVo);
			}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	public List<PageFilterVo> getNewstafftIdCardDesignList(
			PageFilterpojo filterpojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstafftIdCardDesignList : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		int sno=0;
		
		try{
			
			
			conn=JDBCConnection.getSeparateGodaddyConnection();/*here*/
		
			psmt=conn.prepareStatement("SELECT ca.acadamic_id,ca.acadamic_year,cl.Location_Id,cl.Location_Name,cl.Location_Address,cl.Location_Phone,cc.DEPT_ID,cc.DEPT_NAME,SUBSTR(cl.Location_Id,4) FROM campus_acadamicyear ca,campus_department cc ,campus_location cl WHERE cl.location_Id LIKE ? AND ca.acadamic_id LIKE ? AND cc.DEPT_ID LIKE ? ORDER BY CAST(SUBSTR(cl.Location_Id,4)AS UNSIGNED),CAST(SUBSTR(cc.DEPT_ID,4)AS UNSIGNED)");
			psmt.setString(1, filterpojo.getLocationId());
			psmt.setString(2, filterpojo.getAcademicYear());
			
			psmt.setString(3, filterpojo.getDepartmentId());
		
			rs=psmt.executeQuery();
			while(rs.next()){
				sno++;
				PageFilterVo filterVo = new PageFilterVo();
				filterVo.setSno(sno);
				filterVo.setAcademicYear(rs.getString("acadamic_year"));
				filterVo.setAcademicYearCode(rs.getString("acadamic_id"));
				filterVo.setLocationName(rs.getString("Location_Name"));
				filterVo.setLocationId(rs.getString("Location_Id"));
				filterVo.setDepartmentId(rs.getString("DEPT_ID"));
				filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
				filterVo.setLocation_address(rs.getString("Location_Address"));
				filterVo.setLocation_phone(rs.getString("Location_Phone"));
				filterVo.setTemplateName(rs.getString("DEPT_NAME")+" "+rs.getString("Location_Name")+" "+rs.getString("acadamic_year")+" ");
				filterVo.setTemplateId(rs.getString("acadamic_id")+rs.getString("Location_Id")+rs.getString("DEPT_ID"));
			
				list.add(filterVo);
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
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
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstafftIdCardDesignList : Ending");
		
		return list;
	}


}
