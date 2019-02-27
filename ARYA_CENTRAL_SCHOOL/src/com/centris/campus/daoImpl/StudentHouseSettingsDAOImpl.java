package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StudentHouseSettingsDAO;
import com.centris.campus.pojo.StudentHouseSettingsPOJO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.StudentHouseSettingsVO;
import com.itextpdf.text.log.SysoLogger;

public class StudentHouseSettingsDAOImpl implements StudentHouseSettingsDAO {

	private static final Logger logger = Logger.getLogger(StudentHouseSettingsDAOImpl.class);
	
	@SuppressWarnings("static-access")
	@Override
	public String savehouseSettings(StudentHouseSettingsPOJO pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl: savehouseSettings : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int count = 0;
		String status = null;
		IDGenerator id = new IDGenerator();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("insert into campus_house_settings(house_id,accyear_id,loc_id,housename,created_by) values(?,?,?,?,?)");
			pstmt.setString(1,id.getPrimaryKeyID("campus_house_settings"));
			pstmt.setString(2,pojo.getAccid());
			pstmt.setString(3,pojo.getLocid());
			pstmt.setString(4,pojo.getHousename());
			pstmt.setString(5,pojo.getUserid());
			count = pstmt.executeUpdate();
			if(count > 0){
				status = "true";
			}else{
				status = "false";
			}
			
		}catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} 
		finally {
			try {

				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl : savehouseSettings : Ending");
		return status;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> gethouseSettings(String loc,String accyear) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl: gethouseSettings : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int slno=0;
		ArrayList<StudentHouseSettingsVO> list = new ArrayList<StudentHouseSettingsVO>();
		try {
		
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_HOUSE_SETTINGS);
			pstmt.setString(1,accyear);
			pstmt.setString(2,loc);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno++;
				StudentHouseSettingsVO obj= new StudentHouseSettingsVO();
				obj.setHouseid(rs.getString("house_id"));
				obj.setHousename(rs.getString("housename"));
				obj.setSlno(slno);
				list.add(obj);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {

				if (rs != null && !rs.isClosed()) {
					rs.close();
				}

				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl : gethouseSettings : Ending");
		return list;
	}

	@Override
	public String edithouseSettings(StudentHouseSettingsPOJO pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl: edithouseSettings : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int count = 0;
		String status = null;
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.UPDATE_HOUSE_SETTINGS);
			pstmt.setString(1,pojo.getHousename());
			pstmt.setString(2,pojo.getUserid());
			pstmt.setString(3,pojo.getHouseid());
			pstmt.setString(4,pojo.getAccid());
			pstmt.setString(5,pojo.getLocid());
			count = pstmt.executeUpdate();
			if(count > 0){
				status = "true";
			}else{
				status = "false";
			}
			
		}catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} 
		finally {
			try {
				

				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl : edithouseSettings : Ending");
		return status;
	}

	@Override
	public String deletehouseSettings(StudentHouseSettingsPOJO pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl: deletehouseSettings : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		
		int count = 0;
		int count1 = 0;
		String status = null;
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_COUNT_FROM_campus_student_house);
			pstmt1.setString(1,pojo.getHouseid());
			pstmt1.setString(2,pojo.getAccid());
			pstmt1.setString(3,pojo.getLocid());
			count = pstmt1.executeUpdate();
			if(count == 0){
				pstmt = conn.prepareStatement(SQLUtilConstants.DELETE_HOUSE_SETTING_DETAILS);
				pstmt.setString(1,pojo.getHouseid());
				pstmt.setString(2,pojo.getAccid());
				pstmt.setString(3,pojo.getLocid());
				count1 = pstmt.executeUpdate();
			}
			if(count1 == 0){
				status = "true";
			}else{
				status = "false";
			}
			
		}catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} 
		finally {
			try {
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}

				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl : deletehouseSettings : Ending");
		return status;
	}

	@Override
	public String checkduplicateHouse(StudentHouseSettingsPOJO pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl: checkduplicateHouse : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int count = 0;
		String status = null;
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_COUNT_FROM_campus_house_settings);
			pstmt.setString(1,pojo.getHousename());
			pstmt.setString(2,pojo.getAccid());
			pstmt.setString(3,pojo.getLocid());
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
			if(count > 0){
				status = "true";
			}else{
				status = "false";
			}
			
		}catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} 
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl : checkduplicateHouse : Ending");
		return status;
	}

	@Override
	public int gettotalstudentcount(String accyear,String locid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl: gettotalstudentcount : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int count = 0;
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_TOTAL_STUDENT_COUNT);
			pstmt.setString(1,accyear);
			pstmt.setString(2,locid);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt("total");
			}
		}catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} 
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl : gettotalstudentcount : Ending");
		return count;
	}

	@Override
	public int gettotalallostudent(String accyear,String locid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl: gettotalallostudent : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int count = 0;
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_ALLOCATED_STRENGTH);
			pstmt.setString(1,accyear);
			pstmt.setString(2,locid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt("total");
			}
		}catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} 
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl : gettotalallostudent : Ending");
		return count;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> getclassdetails(String locid,String accid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl: getclassdetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<StudentHouseSettingsVO> list = new ArrayList<StudentHouseSettingsVO>();
		int count = 0;
		int slno = 0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_CLASS_DETAILS_BY_LOCATION);
			pstmt.setString(1,locid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++;
				StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
				obj.setSlno(slno);
				obj.setClassid(rs.getString("classdetail_id_int"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				PreparedStatement classpstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_COUNT);
				classpstmt.setString(1,rs.getString("classdetail_id_int"));
				classpstmt.setString(2,accid);
				classpstmt.setString(3,locid);
				ResultSet classrs = classpstmt.executeQuery();
				while(classrs.next()){
					obj.setTotalclassStrength(classrs.getInt("total"));
				}
				classrs.close();
				classpstmt.close();
				PreparedStatement allocatedpstmt = conn.prepareStatement(SQLUtilConstants.GET_TOTAL_STRENGTH);
				allocatedpstmt.setString(1,rs.getString("classdetail_id_int"));
				allocatedpstmt.setString(2,accid);
				allocatedpstmt.setString(3,locid);
				ResultSet allocatedrs = allocatedpstmt.executeQuery();
				while(allocatedrs.next()){
					obj.setAllocatedstudents(allocatedrs.getInt("total"));
				}
				allocatedrs.close();
				allocatedpstmt.close();
				PreparedStatement statuspstmt = conn.prepareStatement(SQLUtilConstants.GET_TOTAL_COUNT_FROM_HOUSE_SETTING);
				statuspstmt.setString(1,rs.getString("classdetail_id_int"));
				statuspstmt.setString(2,accid);
				statuspstmt.setString(3,locid);
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				if(count > 0){
					obj.setStatus("Set");
					obj.setStatusid("Set");
					PreparedStatement houseidpstmt = conn.prepareStatement(SQLUtilConstants.GET_HOUSE_ID);
					houseidpstmt.setString(1,rs.getString("classdetail_id_int"));
					houseidpstmt.setString(2,accid);
					houseidpstmt.setString(3,locid);
					ResultSet houseidrs = houseidpstmt.executeQuery();
					String housid = null;
					while(houseidrs.next()){
						housid = houseidrs.getString("generate_houseid");
					}
					obj.setGenhouid(housid);
				}else{
					obj.setStatus("Not Set");
					obj.setStatusid("Not");
				}
				statusrs.close();
				statuspstmt.close();
				list.add(obj);
			}
		}catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} 
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl : getclassdetails : Ending");
		return list;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> getHouseSettingStudentWise(String locid, String classid,String classname,String accid,String filter1) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: getHouseSettingStudentWise : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<StudentHouseSettingsVO> list = new ArrayList<StudentHouseSettingsVO>();
		
		int slno = 0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			if(filter1 == "namewise"){
				pstmt = conn.prepareStatement(SQLUtilConstants.GET_HOUSE_SETTING_STUDENT_NAME_WISE_DETAILS);	
			}else if(filter1 == "namewisedesc"){
				pstmt = conn.prepareStatement(SQLUtilConstants.GET_HOUSE_SETTING_STUDENT_NAME_WISE_DESC_DETAILS);
			}
			pstmt.setString(1,accid);
			pstmt.setString(2,locid);
			pstmt.setString(3,classid);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++;
				StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
				obj.setSlno(slno);
				obj.setSectionid(rs.getString("classsection_id_int"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setStuname(rs.getString("student")+"-"+rs.getString("student_gender_var"));
				obj.setStuid(rs.getString("student_id_int"));
				obj.setClassid(classid);
				obj.setClassname(classname);
				obj.setAdmissionno(rs.getString("student_admissionno_var"));
				list.add(obj);
			}
		   
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl : getHouseSettingStudentWise : Ending");
		
		return list;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> generateHousing(StudentHouseSettingsPOJO pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: generateHousing : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<StudentHouseSettingsVO> list = new ArrayList<StudentHouseSettingsVO>();
		int seccount = 0;
		int count = 0;
		int slno = 0;
		int remcount =0;
		int classgrem=0;
		int classgremcount = 0;
		int classbremcount = 0;
		int classbrem=0;
		int grem = 0;
		int brem =0;
		int hcount =0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select count(*) from campus_house_settings where accyear_id=? and loc_id =?");
			pstmt.setString(1,pojo.getAccid());
			pstmt.setString(2,pojo.getLocid());
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
			//house count
			
			PreparedStatement hcountpstmt = conn.prepareStatement("select count(generate_houseid) from campus_generate_house where accyearid=? and locid =?");
			hcountpstmt.setString(1,pojo.getAccid());
			hcountpstmt.setString(2,pojo.getLocid());
			System.out.println(hcountpstmt);
			ResultSet hcountrs = hcountpstmt.executeQuery();
			while(hcountrs.next()){
				hcount = hcountrs.getInt(1);
			}
			
			// for getting the remainder
			PreparedStatement repstmt = conn.prepareStatement("select count(*) from campus_generate_house where accyearid = ? and locid = ?");
			repstmt.setString(1,pojo.getAccid());
			repstmt.setString(2,pojo.getLocid());
			
			ResultSet rspstmt = repstmt.executeQuery();
			while(rspstmt.next()){
				remcount = rspstmt.getInt(1);
			}		
			if(remcount !=0){
			PreparedStatement rempstmt = conn.prepareStatement("select sum(grem) as total1,sum(brem) as total2 from campus_generate_house where accyearid = ? and locid = ?");
			rempstmt.setString(1,pojo.getAccid());
			rempstmt.setString(2,pojo.getLocid());
			
			ResultSet remrs = rempstmt.executeQuery();
				while(remrs.next()){
					grem = remrs.getInt("total1");
					brem = remrs.getInt("total2");
				}
				classgrem = grem%count;
				classbrem = brem%count;
			}
			
			System.out.println("classgrem"+classgrem);
			System.out.println("classbrem"+classbrem);
			
			PreparedStatement rempstmt = conn.prepareStatement("select count(*) from campus_house_settings where accyear_id=? and loc_id =?");
			rempstmt.setString(1,pojo.getAccid());
			rempstmt.setString(2,pojo.getLocid());
			ResultSet remrs = rempstmt.executeQuery();
			while(remrs.next()){
				count = remrs.getInt(1);
			}
			ArrayList<String> ele = new ArrayList<String>();
			ArrayList<String> houseid = new ArrayList<String>();
			PreparedStatement housepstmt = conn.prepareStatement("select house_id,housename from campus_house_settings where accyear_id = ? and loc_id = ? order by housename");
			housepstmt.setString(1,pojo.getAccid());
			housepstmt.setString(2,pojo.getLocid());
			ResultSet housers = housepstmt.executeQuery();
			while(housers.next()){
				ele.add(housers.getString("housename"));
				houseid.add(housers.getString("house_id"));
			}
			System.out.println(ele.size());
			housers.close();
			housepstmt.close();
			
			if(hcount == 0){
				System.out.println("hcount" +hcount);
			int gcount = 0;
			int bcount = 0;
			PreparedStatement stusecpstmt = conn.prepareStatement("select count(*)as total,sc.classsection_id_int,cs.classsection_name_var from campus_student_classdetails sc join campus_classsection cs on cs.classsection_id_int = sc.classsection_id_int  where sc.classdetail_id_int = ? and sc.fms_acadamicyear_id_int = ? and sc.locationId = ? group by classsection_id_int order by classsection_name_var");
			stusecpstmt.setString(1,pojo.getClassid());
			stusecpstmt.setString(2,pojo.getAccid());
			stusecpstmt.setString(3,pojo.getLocid());
			System.out.println(stusecpstmt);
			ResultSet stusecrs = stusecpstmt.executeQuery();
			int rem=0;
			int rem1=0;
			
			while(stusecrs.next()){
					int i = 0;
					int j=0;
					
					seccount = stusecrs.getInt(1);
					PreparedStatement secstupstmt = conn.prepareStatement("select concat(st.student_fname_var,' ',st.student_lname_var) as student,st.student_gender_var,st.student_id_int from campus_student st join campus_student_classdetails sc on st.student_id_int = sc.student_id_int and sc.classsection_id_int = ? and sc.classdetail_id_int = ? and sc.locationId = ? and sc.fms_acadamicyear_id_int=? order by student_gender_var,student");
					secstupstmt.setString(1,stusecrs.getString("classsection_id_int"));
					secstupstmt.setString(2,pojo.getClassid());
					secstupstmt.setString(3,pojo.getLocid());
					secstupstmt.setString(4,pojo.getAccid());
					System.out.println("secstupstmt"+secstupstmt);
					ResultSet secstupstmtrs = secstupstmt.executeQuery();
					
					while(secstupstmtrs.next()){
						
						StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
						if(secstupstmtrs.getString("student_gender_var").equalsIgnoreCase("Female")){
						gcount ++;
			
						if(rem == ele.size()-1){
							obj.setHousename(ele.get(rem));
							obj.setHouseid(houseid.get(rem));
							i=0;
						}
						else if(rem != 0){
							obj.setHousename(ele.get(rem));
							obj.setHouseid(houseid.get(rem));
						
						}else{
						obj.setHousename(ele.get(i));
						obj.setHouseid(houseid.get(i));
						}
						if(rem == ele.size()-1){
							i=0;
							rem=0;
						}else{
							i++;
						}
						if(i>ele.size()-1){
							i=0;
						}
						if(rem !=0 && rem < ele.size()-1){
							rem++;
						}
						classgrem =0;
						list.add(obj);
					}else{
						bcount ++;
						if(rem1 == ele.size()-1){
							obj.setHousename(ele.get(rem1));
							obj.setHouseid(houseid.get(rem1));
							j=0;
						}
						else if(rem1 != 0){
							obj.setHousename(ele.get(rem1));
							obj.setHouseid(houseid.get(rem1));
						}else{
						obj.setHousename(ele.get(j));
						obj.setHouseid(houseid.get(j));
						}
						if(rem1 == ele.size()-1){
							j=0;
							rem1=0;
						}else{
							j++;
						}
						
						if(j>ele.size()-1){
							j=0;
						}
						if(rem1 !=0 && rem1 < ele.size()-1){
							rem1++;
						}
						classbrem =0;
						list.add(obj);
						}
					}
					rem = gcount%count;
					rem1 = bcount%count;
				}
			}
			else{
				int gcount = 0;
				int bcount = 0;
				PreparedStatement stusecpstmt = conn.prepareStatement("select count(*)as total,sc.classsection_id_int,cs.classsection_name_var from campus_student_classdetails sc join campus_classsection cs on cs.classsection_id_int = sc.classsection_id_int  where sc.classdetail_id_int = ? and sc.fms_acadamicyear_id_int = ? and sc.locationId = ? group by classsection_id_int order by classsection_name_var");
				stusecpstmt.setString(1,pojo.getClassid());
				stusecpstmt.setString(2,pojo.getAccid());
				stusecpstmt.setString(3,pojo.getLocid());
				ResultSet stusecrs = stusecpstmt.executeQuery();
				int i = 0;
				int j=0;
				while(stusecrs.next()){
						
						seccount = stusecrs.getInt(1);
						PreparedStatement secstupstmt = conn.prepareStatement("select concat(st.student_fname_var,' ',st.student_lname_var) as student,st.student_gender_var,st.student_id_int from campus_student st join campus_student_classdetails sc on st.student_id_int = sc.student_id_int and sc.classsection_id_int = ? and sc.classdetail_id_int = ? and sc.locationId = ? and sc.fms_acadamicyear_id_int=? order by student_gender_var,student");
						secstupstmt.setString(1,stusecrs.getString("classsection_id_int"));
						secstupstmt.setString(2,pojo.getClassid());
						secstupstmt.setString(3,pojo.getLocid());
						secstupstmt.setString(4,pojo.getAccid());
						System.out.println("secstupstmt"+secstupstmt);
						ResultSet secstupstmtrs = secstupstmt.executeQuery();
						
						while(secstupstmtrs.next()){
							
							StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
							if(secstupstmtrs.getString("student_gender_var").equalsIgnoreCase("Female")){
							gcount ++;
							if(classgrem == ele.size()-1){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
							}
							else if(classgrem !=0){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
								i=classgrem;
							}
							else{
							obj.setHousename(ele.get(i));
							obj.setHouseid(houseid.get(i));
							}
							i++;
							if(i > ele.size()-1){
								i=0;
							}
							if(classgrem == ele.size()-1){
								i=0;
							}
							classgremcount=0;
							classgrem =0;
							list.add(obj);
						}else{
							
							bcount ++;
							if(classbrem == ele.size()-1){
								obj.setHousename(ele.get(classbrem));
								obj.setHouseid(houseid.get(classbrem));
							}
							else if(classbrem!=0){
								obj.setHousename(ele.get(classbrem));
								obj.setHouseid(houseid.get(classbrem));
								j=classbrem;
							}else{
								obj.setHousename(ele.get(j));
								obj.setHouseid(houseid.get(j));
							}
							j++;
							if(j>ele.size()-1){
								j=0;
							}
							if(classbrem == ele.size()-1){
								j=0;
							}
							classbremcount=0;
							classbrem =0;
							list.add(obj);
							}
						}
					}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				} 
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public String savegenerateHouseDetails(StudentHouseSettingsPOJO pojo, String[] houseid,
			String[] sectionid, String[] studid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: savegenerateHouseDetails : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null,pstmt2=null;
		int count = 0;
		int count1 = 0;
		int gencount =0;
		int bcount =0;
		IDGenerator id = new IDGenerator();
		String status = null;
		int remcount =0;
		int count2=0;
		ResultSet rsb=null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			
			PreparedStatement repstmt = conn.prepareStatement("select count(*) from campus_student st  join campus_student_classdetails sc on st.student_id_int = sc.student_id_int where sc.fms_acadamicyear_id_int = ? and sc.locationId = ? and sc.classdetail_id_int =? and st.student_gender_var = 'Female'");
			repstmt.setString(1,pojo.getAccid());
			repstmt.setString(2,pojo.getLocid());
			repstmt.setString(3,pojo.getClassid());
			ResultSet rspstmt = repstmt.executeQuery();
			while(rspstmt.next()){
				gencount=(rspstmt.getInt(1));
			}
			PreparedStatement bpstmt = conn.prepareStatement("select count(*) from campus_student st  join campus_student_classdetails sc on st.student_id_int = sc.student_id_int where sc.fms_acadamicyear_id_int = ? and sc.locationId = ? and sc.classdetail_id_int =? and st.student_gender_var = 'Male'");
			bpstmt.setString(1,pojo.getAccid());
			bpstmt.setString(2,pojo.getLocid());
			bpstmt.setString(3,pojo.getClassid());
		    rsb = bpstmt.executeQuery();
			while(rsb.next()){
				bcount=(rsb.getInt(1));
			}
			pstmt = conn.prepareStatement("insert into campus_generate_house(generate_houseid,accyearid,locid,classid,class_strength,allocated_strength,grem,brem,selection)values(?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1,id.getPrimaryKeyID("campus_generate_house"));
			pstmt.setString(2,pojo.getAccid());
			pstmt.setString(3,pojo.getLocid());
			pstmt.setString(4,pojo.getClassid());
			pstmt.setInt(5,Integer.parseInt(pojo.getTotclsstre()));
			pstmt.setInt(6,studid.length);
			pstmt.setInt(7,gencount);
			pstmt.setInt(8,bcount);
			pstmt.setString(9,pojo.getFilter1());
			count = pstmt.executeUpdate();
			for(int i=0;i<studid.length;i++){
				
				pstmt1 = conn.prepareStatement("insert into campus_student_house(student_id,class_id,section_id,house_id,academic_year,loc_id) values(?,?,?,?,?,?)");
				//pstmt1 = conn.prepareStatement("update campus_student_classdetails set student_house = ? where student_id_int = ? and fms_acadamicyear_id_int = ? and locationId=? and classdetail_id_int = ? and classsection_id_int = ?");
				pstmt1.setString(1,studid[i]);
				pstmt1.setString(2,pojo.getClassid());
				pstmt1.setString(3,sectionid[i]);
				pstmt1.setString(4,houseid[i]);
				pstmt1.setString(5,pojo.getAccid());
				pstmt1.setString(6,pojo.getLocid());
				count1 = pstmt1.executeUpdate();
			}
			for(int i=0;i<studid.length;i++){
	            
				 pstmt2 = conn.prepareStatement("update campus_student_classdetails set student_house = ? where student_id_int = ? and classdetail_id_int = ? and  classsection_id_int = ? and fms_acadamicyear_id_int = ? and locationId=? ");
				pstmt2.setString(1,houseid[i]);
				pstmt2.setString(2,studid[i]);
				pstmt2.setString(3,pojo.getClassid());
				pstmt2.setString(4,sectionid[i]);
				pstmt2.setString(5,pojo.getAccid());
				pstmt2.setString(6,pojo.getLocid());
				count2 = pstmt2.executeUpdate();
				pstmt2.close();
			}
			
			if(count > 0){
				status = "success";
			}
			
			}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rsb != null&& (!rsb.isClosed())) {
					rsb.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public String checkHousing(String accyear, String locid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: checkHousing : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int count = 0;
		String status = null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select count(*) from campus_house_settings where accyear_id=? and loc_id =?");
			pstmt.setString(1,accyear);
			pstmt.setString(2,locid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
			if(count == 0){
				status = "true";
			}else{
				status = "false";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> displayHouseSettingStudentWise(StudentHouseSettingsPOJO pojo,
			String classname,String filter1) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: displayHouseSettingStudentWise : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<StudentHouseSettingsVO> list = new ArrayList<StudentHouseSettingsVO>();
		int slno = 0;
		String houseid = null;
		try{

			conn = JDBCConnection.getSeparateConnection();
			if(filter1 == "namewise"){
				pstmt = conn.prepareStatement(SQLUtilConstants.GET_HOUSE_SETTING_STUDENT_NAME_WISE_DETAILS);
			}else if(filter1 == "namewisedesc"){
				pstmt = conn.prepareStatement(SQLUtilConstants.GET_HOUSE_SETTING_STUDENT_NAME_WISE_DESC_DETAILS);
			}
			pstmt.setString(1,pojo.getAccid());
			pstmt.setString(2,pojo.getLocid());
			pstmt.setString(3,pojo.getClassid());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++;
				StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
				obj.setSlno(slno);
				obj.setSectionid(rs.getString("classsection_id_int"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setStuname(rs.getString("student")+"-"+rs.getString("student_gender_var"));
				obj.setStuid(rs.getString("student_id_int"));
				obj.setClassid(pojo.getClassid());
				obj.setClassname(classname);
				obj.setAdmissionno(rs.getString("student_admissionno_var"));
				
				PreparedStatement housepstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_HOUSE_ID);
				housepstmt.setString(1,rs.getString("student_id_int"));
				housepstmt.setString(2,pojo.getClassid());
				housepstmt.setString(3,pojo.getAccid());
				housepstmt.setString(4,pojo.getLocid());
				ResultSet housers = housepstmt.executeQuery();
				while(housers.next()){
					houseid = housers.getString("house_id");
				}
				
				PreparedStatement idpstmt = conn.prepareStatement(SQLUtilConstants.GET_HOUSE_NAME);
				idpstmt.setString(1,houseid);
				idpstmt.setString(2,pojo.getAccid());
				idpstmt.setString(3,pojo.getLocid());
				ResultSet idrs = idpstmt.executeQuery();
				while(idrs.next()){
					obj.setHousename(idrs.getString("housename"));
					obj.setHouseid(houseid);
				}
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> houseadmiWise(StudentHouseSettingsPOJO pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> generateaddmiHousing(StudentHouseSettingsPOJO pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: houseadmiWise : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null,hcountpstmt=null;
		ResultSet rs =null;
		ArrayList<StudentHouseSettingsVO> list = new ArrayList<StudentHouseSettingsVO>();
		int seccount = 0;
		int count = 0;
		int slno = 0;
		int remcount =0;
		int remcount1=0;
		int classgrem=0;
		int classgremcount = 0;
		int classbremcount = 0;
		int classbrem=0;
		int grem = 0;
		int brem =0;
		int hcount =0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_COUNT_FROM_HOUSE_SETTING);
			pstmt.setString(1,pojo.getAccid());
			pstmt.setString(2,pojo.getLocid());
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
			//house count
			
			hcountpstmt = conn.prepareStatement(SQLUtilConstants.GET_GENERATE_HOUSEID_COUNT);
			hcountpstmt.setString(1,pojo.getAccid());
			hcountpstmt.setString(2,pojo.getLocid());
			ResultSet hcountrs = hcountpstmt.executeQuery();
			while(hcountrs.next()){
				hcount = hcountrs.getInt(1);
			}
			hcountrs.close();
			hcountpstmt.close();
			
			// for getting the remainder
			PreparedStatement repstmt = conn.prepareStatement(SQLUtilConstants.GET_GENERATE_HOUSE_COUNT);
			repstmt.setString(1,pojo.getAccid());
			repstmt.setString(2,pojo.getLocid());
			
			ResultSet rspstmt = repstmt.executeQuery();
			while(rspstmt.next()){
				remcount = rspstmt.getInt(1);
			}	
			rspstmt.close();
			repstmt.close();
			if(remcount !=0){
			PreparedStatement rempstmt = conn.prepareStatement(SQLUtilConstants.GET_GENERATE_HOUSE_TOTAL_COUNT);
			rempstmt.setString(1,pojo.getAccid());
			rempstmt.setString(2,pojo.getLocid());
			
			ResultSet remrs = rempstmt.executeQuery();
				while(remrs.next()){
					grem = remrs.getInt("total1");
					brem = remrs.getInt("total2");
				}
				classgrem = grem%count;
				classbrem = brem%count;
				remrs.close();
				rempstmt.close();
			}
			
	
			
			PreparedStatement rempstmt = conn.prepareStatement(SQLUtilConstants.GET_COUNT_FROM_HOUSE_SETTING);
			rempstmt.setString(1,pojo.getAccid());
			rempstmt.setString(2,pojo.getLocid());
			ResultSet remrs = rempstmt.executeQuery();
			while(remrs.next()){
				count = remrs.getInt(1);
			}
			remrs.close();
			rempstmt.close();
			ArrayList<String> ele = new ArrayList<String>();
			ArrayList<String> houseid = new ArrayList<String>();
			PreparedStatement housepstmt = conn.prepareStatement(SQLUtilConstants.GET_HOUSENAME_AND_HOUSEID);
			housepstmt.setString(1,pojo.getAccid());
			housepstmt.setString(2,pojo.getLocid());
			ResultSet housers = housepstmt.executeQuery();
			while(housers.next()){
				ele.add(housers.getString("housename"));
				houseid.add(housers.getString("house_id"));
			}

			housers.close();
			housepstmt.close();
			if(hcount == 0){
			PreparedStatement stusecpstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_SECTIONNAME_AND_SECTIONID);
			stusecpstmt.setString(1,pojo.getClassid());
			stusecpstmt.setString(2,pojo.getAccid());
			stusecpstmt.setString(3,pojo.getLocid());
			ResultSet stusecrs = stusecpstmt.executeQuery();
			int rem=0;
			int rem1=0;
			while(stusecrs.next()){
					int i = 0;
					int j=0;
					int gcount = 0;
					int bcount = 0;
					seccount = stusecrs.getInt(1);
					PreparedStatement secstupstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DETAILS_ORDERBY_STUDENT_GENDER);
					secstupstmt.setString(1,stusecrs.getString("classsection_id_int"));
					secstupstmt.setString(2,pojo.getClassid());
					secstupstmt.setString(3,pojo.getLocid());
					secstupstmt.setString(4,pojo.getAccid());
		
					ResultSet secstupstmtrs = secstupstmt.executeQuery();
					
					while(secstupstmtrs.next()){
						
						StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
						if(secstupstmtrs.getString("student_gender_var").equalsIgnoreCase("Female")){
						gcount ++;
						
			
						if(rem == ele.size()-1){
							obj.setHousename(ele.get(rem));
							obj.setHouseid(houseid.get(rem));
							i=0;
						}
						else if(rem != 0){
							obj.setHousename(ele.get(rem));
							obj.setHouseid(houseid.get(rem));
						
						}else{
						obj.setHousename(ele.get(i));
						obj.setHouseid(houseid.get(i));
						}
						if(rem == ele.size()-1){
							i=0;
							rem=0;
						}else{
							i++;
						}
						if(i>ele.size()-1){
							i=0;
						}
						if(rem !=0 && rem < ele.size()-1){
							rem++;
						}
						classgrem =0;
						list.add(obj);
					}else{
						bcount ++;
					
						
						if(rem1 == ele.size()-1){
							obj.setHousename(ele.get(rem1));
							obj.setHouseid(houseid.get(rem1));
							j=0;
						}
						else if(rem1 != 0){
							obj.setHousename(ele.get(rem1));
							obj.setHouseid(houseid.get(rem1));
						}else{
						obj.setHousename(ele.get(j));
						obj.setHouseid(houseid.get(j));
						}
						if(rem1 == ele.size()-1){
							j=0;
							rem1=0;
						}else{
							j++;
						}
						
						if(j>ele.size()-1){
							j=0;
						}
						if(rem1 !=0 && rem1 < ele.size()-1){
							rem1++;
						}
						classbrem =0;
						list.add(obj);
						}
					}
					secstupstmtrs.close();
					secstupstmt.close();
					rem = gcount%count;
					rem1 = bcount%count;
				}
			stusecrs.close();
			stusecpstmt.close();
			}
			else{
				
				PreparedStatement stusecpstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_SECTIONNAME_AND_SECTIONID);
				stusecpstmt.setString(1,pojo.getClassid());
				stusecpstmt.setString(2,pojo.getAccid());
				stusecpstmt.setString(3,pojo.getLocid());
				ResultSet stusecrs = stusecpstmt.executeQuery();
				int rem=0;
				int rem1=0;
				int i = 0;
				int j=0;
				while(stusecrs.next()){
						int gcount = 0; 
						int bcount = 0;
						seccount = stusecrs.getInt(1);
						PreparedStatement secstupstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DETAILS_ORDERBY_STUDENT_GENDER);
						secstupstmt.setString(1,stusecrs.getString("classsection_id_int"));
						secstupstmt.setString(2,pojo.getClassid());
						secstupstmt.setString(3,pojo.getLocid());
						secstupstmt.setString(4,pojo.getAccid());
				
						ResultSet secstupstmtrs = secstupstmt.executeQuery();
						
						while(secstupstmtrs.next()){
							
							StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
							if(secstupstmtrs.getString("student_gender_var").equalsIgnoreCase("Female")){
							gcount ++;
							
				
							if(classgrem == ele.size()-1){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
							}
							else if(classgrem !=0){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
								i=classgrem;
							}
							else{
							obj.setHousename(ele.get(i));
							obj.setHouseid(houseid.get(i));
							}
							i++;
							if(i > ele.size()-1){
								i=0;
							}
							if(classgrem == ele.size()-1){
								i=0;
							}
							classgremcount=0;
							classgrem =0;
							list.add(obj);
						}else{
							bcount ++;
						
							
							if(classgrem == ele.size()-1){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
							}
							else if(classbrem!=0){
								obj.setHousename(ele.get(classbrem));
								obj.setHouseid(houseid.get(classbrem));
								j=classbrem;
							}else{
								obj.setHousename(ele.get(j));
								obj.setHouseid(houseid.get(j));
							}
							j++;
							if(j>ele.size()-1){
								j=0;
							}
							if(classbrem == ele.size()-1){
								j=0;
							}
							classbremcount=0;
							classbrem =0;
							list.add(obj);
							}
						}
						secstupstmtrs.close();
						secstupstmt.close();
					}
				stusecrs.close();
				stusecpstmt.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (hcountpstmt != null&& (!hcountpstmt.isClosed())) {
					hcountpstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> getHouseSettingAdminoWise(String locid, String classid, String classname,
			String accid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: getHouseSettingAdminoWise : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<StudentHouseSettingsVO> list = new ArrayList<StudentHouseSettingsVO>();
		
		int slno = 0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DETAILS_ORDERBY_STUDENT_SECTION_AND_GENDER);//
			pstmt.setString(1,accid);
			pstmt.setString(2,locid);
			pstmt.setString(3,classid);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++;
				StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
				obj.setSlno(slno);
				obj.setSectionid(rs.getString("classsection_id_int"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setStuname(rs.getString("student")+"-"+rs.getString("student_gender_var"));
				obj.setStuid(rs.getString("student_id_int"));
				obj.setClassid(classid);
				obj.setClassname(classname);
				obj.setAdmissionno(rs.getString("student_admissionno_var"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl : getHouseSettingAdminoWise : Ending");
		return list;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> byadminodescHousing(StudentHouseSettingsPOJO pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: byadminodescHousing : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<StudentHouseSettingsVO> list = new ArrayList<StudentHouseSettingsVO>();
		int seccount = 0;
		int count = 0;
		int slno = 0;
		int remcount =0;
		int remcount1=0;
		int classgrem=0;
		int classgremcount = 0;
		int classbremcount = 0;
		int classbrem=0;
		int grem = 0;
		int brem =0;
		int hcount =0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_COUNT_FROM_HOUSE_SETTING);
			pstmt.setString(1,pojo.getAccid());
			pstmt.setString(2,pojo.getLocid());
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
			//house count
			
			PreparedStatement hcountpstmt = conn.prepareStatement(SQLUtilConstants.GET_GENERATE_HOUSEID_COUNT);
			hcountpstmt.setString(1,pojo.getAccid());
			hcountpstmt.setString(2,pojo.getLocid());
			ResultSet hcountrs = hcountpstmt.executeQuery();
			while(hcountrs.next()){
				hcount = hcountrs.getInt(1);
			}
			hcountrs.close();
			hcountpstmt.close();
			// for getting the remainder   
			PreparedStatement repstmt = conn.prepareStatement(SQLUtilConstants.GET_GENERATE_HOUSE_COUNT);
			repstmt.setString(1,pojo.getAccid());
			repstmt.setString(2,pojo.getLocid());
			
			ResultSet rspstmt = repstmt.executeQuery();
			while(rspstmt.next()){
				remcount = rspstmt.getInt(1);
			}
			rspstmt.close();
			repstmt.close();
			if(remcount !=0){
			PreparedStatement rempstmt = conn.prepareStatement(SQLUtilConstants.GET_GENERATE_HOUSE_TOTAL_COUNT);
			rempstmt.setString(1,pojo.getAccid());
			rempstmt.setString(2,pojo.getLocid());
			
			ResultSet remrs = rempstmt.executeQuery();
				while(remrs.next()){
					grem = remrs.getInt("total1");
					brem = remrs.getInt("total2");
				}
				classgrem = grem%count;
				classbrem = brem%count;
				remrs.close();
				rempstmt.close();
			}
			
			System.out.println("classgrem"+classgrem);
			System.out.println("classbrem"+classbrem);
			
			PreparedStatement rempstmt = conn.prepareStatement(SQLUtilConstants.GET_COUNT_FROM_HOUSE_SETTING);
			rempstmt.setString(1,pojo.getAccid());
			rempstmt.setString(2,pojo.getLocid());
			ResultSet remrs = rempstmt.executeQuery();
			while(remrs.next()){
				count = remrs.getInt(1);
			}
			remrs.close();
			rempstmt.close();
			ArrayList<String> ele = new ArrayList<String>();
			ArrayList<String> houseid = new ArrayList<String>();  
			PreparedStatement housepstmt = conn.prepareStatement(SQLUtilConstants.GET_HOUSENAME_AND_HOUSEID);
			housepstmt.setString(1,pojo.getAccid());
			housepstmt.setString(2,pojo.getLocid());
			ResultSet housers = housepstmt.executeQuery();
			while(housers.next()){
				ele.add(housers.getString("housename"));
				houseid.add(housers.getString("house_id"));
			}
			System.out.println(ele.size());
			housers.close();
			housepstmt.close();
			if(hcount == 0){
			PreparedStatement stusecpstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_SECTIONNAME_AND_SECTIONID);
			stusecpstmt.setString(1,pojo.getClassid()); 
			stusecpstmt.setString(2,pojo.getAccid());
			stusecpstmt.setString(3,pojo.getLocid());
			ResultSet stusecrs = stusecpstmt.executeQuery();
			int rem=0;
			int rem1=0;
			while(stusecrs.next()){
					int i = 0;
					int j=0;
					int gcount = 0;
					int bcount = 0;
					seccount = stusecrs.getInt(1);
					PreparedStatement secstupstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DETAILS_ORDERBY_STUDENT_GENDER);
					secstupstmt.setString(1,stusecrs.getString("classsection_id_int")); 
					secstupstmt.setString(2,pojo.getClassid());
					secstupstmt.setString(3,pojo.getLocid());
					secstupstmt.setString(4,pojo.getAccid());
					System.out.println("secstupstmt"+secstupstmt);
					ResultSet secstupstmtrs = secstupstmt.executeQuery();
					
					while(secstupstmtrs.next()){
						
						StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
						if(secstupstmtrs.getString("student_gender_var").equalsIgnoreCase("Female")){
						gcount ++;
			
						if(rem == ele.size()-1){
							obj.setHousename(ele.get(rem));
							obj.setHouseid(houseid.get(rem));
							i=0;
						}
						else if(rem != 0){
							obj.setHousename(ele.get(rem));
							obj.setHouseid(houseid.get(rem));
						
						}else{
						obj.setHousename(ele.get(i));
						obj.setHouseid(houseid.get(i));
						}
						if(rem == ele.size()-1){
							i=0;
							rem=0;
						}else{
							i++;
						}
						if(i>ele.size()-1){
							i=0;
						}
						if(rem !=0 && rem < ele.size()-1){
							rem++;
						}
						classgrem =0;
						list.add(obj);
					}else{
						bcount ++;
						
						if(rem1 == ele.size()-1){
							obj.setHousename(ele.get(rem1));
							obj.setHouseid(houseid.get(rem1));
							j=0;
						}
						else if(rem1 != 0){
							obj.setHousename(ele.get(rem1));
							obj.setHouseid(houseid.get(rem1));
						}else{
						obj.setHousename(ele.get(j));
						obj.setHouseid(houseid.get(j));
						}
						if(rem1 == ele.size()-1){
							j=0;
							rem1=0;
						}else{
							j++;
						}
						
						if(j>ele.size()-1){
							j=0;
						}
						if(rem1 !=0 && rem1 < ele.size()-1){
							rem1++;
						}
						classbrem =0;
						list.add(obj);
						}
					}
					secstupstmtrs.close();
					secstupstmt.close();
					rem = gcount%count;
					rem1 = bcount%count;
				}
			stusecrs.close();
			stusecrs.close();
			}
			else{
				
				PreparedStatement stusecpstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_SECTIONNAME_AND_SECTIONID);
				stusecpstmt.setString(1,pojo.getClassid()); 
				stusecpstmt.setString(2,pojo.getAccid());
				stusecpstmt.setString(3,pojo.getLocid());
				ResultSet stusecrs = stusecpstmt.executeQuery();
				int rem=0;
				int rem1=0;
				int i = 0;
				int j=0;
				while(stusecrs.next()){
						int gcount = 0;
						int bcount = 0;
						seccount = stusecrs.getInt(1);
						PreparedStatement secstupstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DETAILS_DESC_ORDERBY_STUDENT_GENDER);
						secstupstmt.setString(1,stusecrs.getString("classsection_id_int")); 
						secstupstmt.setString(2,pojo.getClassid());
						secstupstmt.setString(3,pojo.getLocid());
						secstupstmt.setString(4,pojo.getAccid());
						
						ResultSet secstupstmtrs = secstupstmt.executeQuery();
						
						while(secstupstmtrs.next()){
							
							StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
							if(secstupstmtrs.getString("student_gender_var").equalsIgnoreCase("Female")){
							gcount ++;
				
							if(classgrem == ele.size()-1){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
							}
							else if(classgrem !=0){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
								i=classgrem;
							}
							else{
							obj.setHousename(ele.get(i));
							obj.setHouseid(houseid.get(i));
							}
							i++;
							if(i > ele.size()-1){
								i=0;
							}
							if(classgrem == ele.size()-1){
								i=0;
							}
							classgremcount=0;
							classgrem =0;
							list.add(obj);
						}else{
							bcount ++;
							
							if(classgrem == ele.size()-1){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
							}
							else if(classbrem!=0){
								obj.setHousename(ele.get(classbrem));
								obj.setHouseid(houseid.get(classbrem));
								j=classbrem;
							}else{
								obj.setHousename(ele.get(j));
								obj.setHouseid(houseid.get(j));
							}
							j++;
							if(j>ele.size()-1){
								j=0;
							}
							if(classbrem == ele.size()-1){
								j=0;
							}
							classbremcount=0;
							classbrem =0;
							list.add(obj);
							}
						}
						secstupstmtrs.close();
						secstupstmt.close();
						System.out.println("gcount"+ gcount);
						System.out.println("bcount"+ bcount);
					}
				stusecrs.close();
				stusecpstmt.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> getHouseSettingAdminodescWise(String locid, String classid,
			String classname, String accid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: getHouseSettingStudentWise : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<StudentHouseSettingsVO> list = new ArrayList<StudentHouseSettingsVO>();
		
		int slno = 0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DETAILS_BY_ADMINNO_WISE);
			pstmt.setString(1,accid);
			pstmt.setString(2,locid);
			pstmt.setString(3,classid);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++;
				StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
				obj.setSlno(slno);
				obj.setSectionid(rs.getString("classsection_id_int"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setStuname(rs.getString("student")+"-"+rs.getString("student_gender_var"));
				obj.setStuid(rs.getString("student_id_int"));
				obj.setClassid(classid);
				obj.setClassname(classname);
				obj.setAdmissionno(rs.getString("student_admissionno_var"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl : getHouseSettingAdminodescWise : Ending");
		return list;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> byadminoevenHousing(StudentHouseSettingsPOJO pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: byadminoevenHousing : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<StudentHouseSettingsVO> list = new ArrayList<StudentHouseSettingsVO>();
		int seccount = 0;
		int count = 0;
		int slno = 0;
		int remcount =0;
		int remcount1=0;
		int classgrem=0;
		int classgremcount = 0;
		int classbremcount = 0;
		int classbrem=0;
		int grem = 0;
		int brem =0;
		int hcount =0;  
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_COUNT_FROM_HOUSE_SETTING);
			pstmt.setString(1,pojo.getAccid());
			pstmt.setString(2,pojo.getLocid());
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
			//house count
			
			PreparedStatement hcountpstmt = conn.prepareStatement(SQLUtilConstants.GET_GENERATE_HOUSEID_COUNT);
			hcountpstmt.setString(1,pojo.getAccid());
			hcountpstmt.setString(2,pojo.getLocid());
			ResultSet hcountrs = hcountpstmt.executeQuery();
			while(hcountrs.next()){
				hcount = hcountrs.getInt(1);
			}
			hcountrs.close();
			hcountpstmt.close();
			// for getting the remainder
			PreparedStatement repstmt = conn.prepareStatement(SQLUtilConstants.GET_GENERATE_HOUSE_COUNT);
			repstmt.setString(1,pojo.getAccid());
			repstmt.setString(2,pojo.getLocid()); 
			
			ResultSet rspstmt = repstmt.executeQuery();
			while(rspstmt.next()){
				remcount = rspstmt.getInt(1);
			}
			rspstmt.close();
			repstmt.close();
			if(remcount !=0){
			PreparedStatement rempstmt = conn.prepareStatement(SQLUtilConstants.GET_GENERATE_HOUSE_TOTAL_COUNT);
			rempstmt.setString(1,pojo.getAccid());
			rempstmt.setString(2,pojo.getLocid());  
			
			ResultSet remrs = rempstmt.executeQuery();
				while(remrs.next()){
					grem = remrs.getInt("total1");
					brem = remrs.getInt("total2");
				}
				remrs.close();
				rempstmt.close();
				classgrem = grem%count;
				classbrem = brem%count;
			}
			
			System.out.println("classgrem"+classgrem);
			System.out.println("classbrem"+classbrem);
			
			PreparedStatement rempstmt = conn.prepareStatement(SQLUtilConstants.GET_COUNT_FROM_HOUSE_SETTING);
			rempstmt.setString(1,pojo.getAccid());
			rempstmt.setString(2,pojo.getLocid());
			ResultSet remrs = rempstmt.executeQuery(); 
			while(remrs.next()){
				count = remrs.getInt(1);
			}
			remrs.close();
			rempstmt.close();
			ArrayList<String> ele = new ArrayList<String>();
			ArrayList<String> houseid = new ArrayList<String>();
			PreparedStatement housepstmt = conn.prepareStatement(SQLUtilConstants.GET_HOUSENAME_AND_HOUSEID);
			housepstmt.setString(1,pojo.getAccid());
			housepstmt.setString(2,pojo.getLocid());
			ResultSet housers = housepstmt.executeQuery();
			while(housers.next()){
				ele.add(housers.getString("housename"));
				houseid.add(housers.getString("house_id"));
			}
			System.out.println(ele.size());
			housers.close();
			housepstmt.close();
			if(hcount == 0){
			PreparedStatement stusecpstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_SECTIONNAME_AND_SECTIONID);
			stusecpstmt.setString(1,pojo.getClassid());
			stusecpstmt.setString(2,pojo.getAccid());
			stusecpstmt.setString(3,pojo.getLocid());
			ResultSet stusecrs = stusecpstmt.executeQuery();
			int rem=0;
			int rem1=0;
			while(stusecrs.next()){
					int i = 0;
					int j=0;
					int gcount = 0;
					int bcount = 0;
					seccount = stusecrs.getInt(1);
					PreparedStatement secstupstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DETAIL_ORDERBY_EVEN_ADMISSIONNO);
					secstupstmt.setString(1,stusecrs.getString("classsection_id_int"));
					secstupstmt.setString(2,pojo.getClassid());
					secstupstmt.setString(3,pojo.getLocid());
					secstupstmt.setString(4,pojo.getAccid());
					System.out.println("secstupstmt"+secstupstmt);
					ResultSet secstupstmtrs = secstupstmt.executeQuery();
					
					while(secstupstmtrs.next()){
						
						StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
						if(secstupstmtrs.getString("student_gender_var").equalsIgnoreCase("Female")){
						gcount ++;
			
						if(rem == ele.size()-1){
							obj.setHousename(ele.get(rem));
							obj.setHouseid(houseid.get(rem));
							i=0;
						}
						else if(rem != 0){
							obj.setHousename(ele.get(rem));
							obj.setHouseid(houseid.get(rem));
						
						}else{
						obj.setHousename(ele.get(i));
						obj.setHouseid(houseid.get(i));
						}
						if(rem == ele.size()-1){
							i=0;
							rem=0;
						}else{
							i++;
						}
						if(i>ele.size()-1){
							i=0;
						}
						if(rem !=0 && rem < ele.size()-1){
							rem++;
						}
						classgrem =0;
						list.add(obj);
					}else{
						bcount ++;
						
						if(rem1 == ele.size()-1){
							obj.setHousename(ele.get(rem1));
							obj.setHouseid(houseid.get(rem1));
							j=0;
						}
						else if(rem1 != 0){
							obj.setHousename(ele.get(rem1));
							obj.setHouseid(houseid.get(rem1));
						}else{
						obj.setHousename(ele.get(j));
						obj.setHouseid(houseid.get(j));
						}
						if(rem1 == ele.size()-1){
							j=0;
							rem1=0;
						}else{
							j++;
						}
						
						if(j>ele.size()-1){
							j=0;
						}
						if(rem1 !=0 && rem1 < ele.size()-1){
							rem1++;
						}
						classbrem =0;
						list.add(obj);
						}
					}
					secstupstmtrs.close();
					secstupstmt.close();
					rem = gcount%count;
					rem1 = bcount%count;
				}
			stusecrs.close();
			stusecpstmt.close();
			}
			else{
				
				PreparedStatement stusecpstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_SECTIONNAME_AND_SECTIONID);
				stusecpstmt.setString(1,pojo.getClassid());
				stusecpstmt.setString(2,pojo.getAccid());
				stusecpstmt.setString(3,pojo.getLocid());
				ResultSet stusecrs = stusecpstmt.executeQuery();
				int rem=0;
				int rem1=0;
				int i = 0;
				int j=0;
				while(stusecrs.next()){
						int gcount = 0;
						int bcount = 0;
						seccount = stusecrs.getInt(1);
						PreparedStatement secstupstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DETAIL_ORDERBY_EVEN_ADMISSIONNO);
						secstupstmt.setString(1,stusecrs.getString("classsection_id_int"));
						secstupstmt.setString(2,pojo.getClassid());
						secstupstmt.setString(3,pojo.getLocid());
						secstupstmt.setString(4,pojo.getAccid());
						
						ResultSet secstupstmtrs = secstupstmt.executeQuery();
						
						while(secstupstmtrs.next()){
							
							StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
							if(secstupstmtrs.getString("student_gender_var").equalsIgnoreCase("Female")){
							gcount ++;
				
							if(classgrem == ele.size()-1){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
							}
							else if(classgrem !=0){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
								i=classgrem;
							}
							else{
							obj.setHousename(ele.get(i));
							obj.setHouseid(houseid.get(i));
							}
							i++;
							if(i > ele.size()-1){
								i=0;
							}
							if(classgrem == ele.size()-1){
								i=0;
							}
							classgremcount=0;
							classgrem =0;
							list.add(obj);
						}else{
							bcount ++;
							
							if(classgrem == ele.size()-1){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
							}
							else if(classbrem!=0){
								obj.setHousename(ele.get(classbrem));
								obj.setHouseid(houseid.get(classbrem));
								j=classbrem;
							}else{
								obj.setHousename(ele.get(j));
								obj.setHouseid(houseid.get(j));
							}
							j++;
							if(j>ele.size()-1){
								j=0;
							}
							if(classbrem == ele.size()-1){
								j=0;
							}
							classbremcount=0;
							classbrem =0;
							list.add(obj);
							}
						}
						secstupstmtrs.close();
						secstupstmt.close();
						System.out.println("gcount"+ gcount);
						System.out.println("bcount"+ bcount);
					}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> getHouseSettingAdminoevenWise(String locid, String classid,
			String classname, String accid,String filter1) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: getHouseSettingAdminoevenWise : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<StudentHouseSettingsVO> list = new ArrayList<StudentHouseSettingsVO>();
		
		int slno = 0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			if(filter1.equalsIgnoreCase("byadminoeven")){
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DETAIL_ORDERBY_EVEN_ADMISSIONNO);
			}else if(filter1.equalsIgnoreCase("byadminoodd")){
				pstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DETAIL_ORDERBY_ODD_ADMISSIONNO);
			}
			pstmt.setString(1,accid);
			pstmt.setString(2,locid);
			pstmt.setString(3,classid);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++;
				StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
				obj.setSlno(slno);
				obj.setSectionid(rs.getString("classsection_id_int"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setStuname(rs.getString("student")+"-"+rs.getString("student_gender_var"));
				obj.setStuid(rs.getString("student_id_int"));
				obj.setClassid(classid);
				obj.setClassname(classname);
				obj.setAdmissionno(rs.getString("student_admissionno_var"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl : getHouseSettingAdminoevenWise : Ending");
		return list;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> byadminooddHousing(StudentHouseSettingsPOJO pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: generateHousing : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<StudentHouseSettingsVO> list = new ArrayList<StudentHouseSettingsVO>();
		int seccount = 0;
		int count = 0;
		int slno = 0;
		int remcount =0;
		int remcount1=0;
		int classgrem=0;
		int classgremcount = 0;
		int classbremcount = 0;
		int classbrem=0;
		int grem = 0;
		int brem =0;
		int hcount =0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select count(*) from campus_house_settings where accyear_id=? and loc_id =?");
			pstmt.setString(1,pojo.getAccid());
			pstmt.setString(2,pojo.getLocid());
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
			//house count
			
			PreparedStatement hcountpstmt = conn.prepareStatement("select count(generate_houseid) from campus_generate_house where accyearid=? and locid =?");
			hcountpstmt.setString(1,pojo.getAccid());
			hcountpstmt.setString(2,pojo.getLocid());
			ResultSet hcountrs = hcountpstmt.executeQuery();
			while(hcountrs.next()){
				hcount = hcountrs.getInt(1);
			}
			
			// for getting the remainder
			PreparedStatement repstmt = conn.prepareStatement("select count(*) from campus_generate_house where accyearid = ? and locid = ?");
			repstmt.setString(1,pojo.getAccid());
			repstmt.setString(2,pojo.getLocid());
			
			ResultSet rspstmt = repstmt.executeQuery();
			while(rspstmt.next()){
				remcount = rspstmt.getInt(1);
			}		
			if(remcount !=0){
			PreparedStatement rempstmt = conn.prepareStatement("select sum(grem) as total1,sum(brem) as total2 from campus_generate_house where accyearid = ? and locid = ?");
			rempstmt.setString(1,pojo.getAccid());
			rempstmt.setString(2,pojo.getLocid());
			
			ResultSet remrs = rempstmt.executeQuery();
				while(remrs.next()){
					grem = remrs.getInt("total1");
					brem = remrs.getInt("total2");
				}
				classgrem = grem%count;
				classbrem = brem%count;
			}
			
			System.out.println("classgrem"+classgrem);
			System.out.println("classbrem"+classbrem);
			
			PreparedStatement rempstmt = conn.prepareStatement("select count(*) from campus_house_settings where accyear_id=? and loc_id =?");
			rempstmt.setString(1,pojo.getAccid());
			rempstmt.setString(2,pojo.getLocid());
			ResultSet remrs = rempstmt.executeQuery();
			while(remrs.next()){
				count = remrs.getInt(1);
			}
			ArrayList<String> ele = new ArrayList<String>();
			ArrayList<String> houseid = new ArrayList<String>();
			PreparedStatement housepstmt = conn.prepareStatement("select house_id,housename from campus_house_settings where accyear_id = ? and loc_id = ? order by housename");
			housepstmt.setString(1,pojo.getAccid());
			housepstmt.setString(2,pojo.getLocid());
			ResultSet housers = housepstmt.executeQuery();
			while(housers.next()){
				ele.add(housers.getString("housename"));
				houseid.add(housers.getString("house_id"));
			}
			System.out.println(ele.size());
			housers.close();
			housepstmt.close();
			if(hcount == 0){
			PreparedStatement stusecpstmt = conn.prepareStatement("select count(*)as total,sc.classsection_id_int,cs.classsection_name_var from campus_student_classdetails sc join campus_classsection cs on cs.classsection_id_int = sc.classsection_id_int  where sc.classdetail_id_int = ? and sc.fms_acadamicyear_id_int = ? and sc.locationId = ? group by classsection_id_int order by classsection_name_var");
			stusecpstmt.setString(1,pojo.getClassid());
			stusecpstmt.setString(2,pojo.getAccid());
			stusecpstmt.setString(3,pojo.getLocid());
			ResultSet stusecrs = stusecpstmt.executeQuery();
			int rem=0;
			int rem1=0;
			while(stusecrs.next()){
					int i = 0;
					int j=0;
					int gcount = 0;
					int bcount = 0;
					seccount = stusecrs.getInt(1);
					PreparedStatement secstupstmt = conn.prepareStatement("select concat(st.student_fname_var,' ',st.student_lname_var) as student,st.student_gender_var,st.student_id_int from campus_student st join campus_student_classdetails sc on st.student_id_int = sc.student_id_int and sc.classsection_id_int = ? and sc.classdetail_id_int = ? and sc.locationId = ? and sc.fms_acadamicyear_id_int=? order by student_gender_var,(CAST(st.student_admissionno_var AS UNSIGNED))%2 desc");
					secstupstmt.setString(1,stusecrs.getString("classsection_id_int"));
					secstupstmt.setString(2,pojo.getClassid());
					secstupstmt.setString(3,pojo.getLocid());
					secstupstmt.setString(4,pojo.getAccid());
					System.out.println("secstupstmt"+secstupstmt);
					ResultSet secstupstmtrs = secstupstmt.executeQuery();
					
					while(secstupstmtrs.next()){
						
						StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
						if(secstupstmtrs.getString("student_gender_var").equalsIgnoreCase("Female")){
						gcount ++;
			
						if(rem == ele.size()-1){
							obj.setHousename(ele.get(rem));
							obj.setHouseid(houseid.get(rem));
							i=0;
						}
						else if(rem != 0){
							obj.setHousename(ele.get(rem));
							obj.setHouseid(houseid.get(rem));
						
						}else{
						obj.setHousename(ele.get(i));
						obj.setHouseid(houseid.get(i));
						}
						if(rem == ele.size()-1){
							i=0;
							rem=0;
						}else{
							i++;
						}
						if(i>ele.size()-1){
							i=0;
						}
						if(rem !=0 && rem < ele.size()-1){
							rem++;
						}
						classgrem =0;
						list.add(obj);
					}else{
						bcount ++;
						
						if(rem1 == ele.size()-1){
							obj.setHousename(ele.get(rem1));
							obj.setHouseid(houseid.get(rem1));
							j=0;
						}
						else if(rem1 != 0){
							obj.setHousename(ele.get(rem1));
							obj.setHouseid(houseid.get(rem1));
						}else{
						obj.setHousename(ele.get(j));
						obj.setHouseid(houseid.get(j));
						}
						if(rem1 == ele.size()-1){
							j=0;
							rem1=0;
						}else{
							j++;
						}
						
						if(j>ele.size()-1){
							j=0;
						}
						if(rem1 !=0 && rem1 < ele.size()-1){
							rem1++;
						}
						classbrem =0;
						list.add(obj);
						}
					}
					rem = gcount%count;
					rem1 = bcount%count;
				}
			}
			else{
				
				PreparedStatement stusecpstmt = conn.prepareStatement("select count(*)as total,sc.classsection_id_int,cs.classsection_name_var from campus_student_classdetails sc join campus_classsection cs on cs.classsection_id_int = sc.classsection_id_int  where sc.classdetail_id_int = ? and sc.fms_acadamicyear_id_int = ? and sc.locationId = ? group by classsection_id_int order by classsection_name_var");
				stusecpstmt.setString(1,pojo.getClassid());
				stusecpstmt.setString(2,pojo.getAccid());
				stusecpstmt.setString(3,pojo.getLocid());
				ResultSet stusecrs = stusecpstmt.executeQuery();
				int rem=0;
				int rem1=0;
				int i = 0;
				int j=0;
				while(stusecrs.next()){
						int gcount = 0;
						int bcount = 0;
						seccount = stusecrs.getInt(1);
						PreparedStatement secstupstmt = conn.prepareStatement("select concat(st.student_fname_var,' ',st.student_lname_var) as student,st.student_gender_var,st.student_id_int from campus_student st join campus_student_classdetails sc on st.student_id_int = sc.student_id_int and sc.classsection_id_int = ? and sc.classdetail_id_int = ? and sc.locationId = ? and sc.fms_acadamicyear_id_int=? order by student_gender_var,(CAST(st.student_admissionno_var AS UNSIGNED))%2 desc");
						secstupstmt.setString(1,stusecrs.getString("classsection_id_int"));
						secstupstmt.setString(2,pojo.getClassid());
						secstupstmt.setString(3,pojo.getLocid());
						secstupstmt.setString(4,pojo.getAccid());
						
						ResultSet secstupstmtrs = secstupstmt.executeQuery();
						
						while(secstupstmtrs.next()){
							
							StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
							if(secstupstmtrs.getString("student_gender_var").equalsIgnoreCase("Female")){
							gcount ++;
				
							if(classgrem == ele.size()-1){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
							}
							else if(classgrem !=0){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
								i=classgrem;
							}
							else{
							obj.setHousename(ele.get(i));
							obj.setHouseid(houseid.get(i));
							}
							i++;
							if(i > ele.size()-1){
								i=0;
							}
							if(classgrem == ele.size()-1){
								i=0;
							}
							classgremcount=0;
							classgrem =0;
							list.add(obj);
						}else{
							bcount ++;
							
							if(classgrem == ele.size()-1){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
							}
							else if(classbrem!=0){
								obj.setHousename(ele.get(classbrem));
								obj.setHouseid(houseid.get(classbrem));
								j=classbrem;
							}else{
								obj.setHousename(ele.get(j));
								obj.setHouseid(houseid.get(j));
							}
							j++;
							if(j>ele.size()-1){
								j=0;
							}
							if(classbrem == ele.size()-1){
								j=0;
							}
							classbremcount=0;
							classbrem =0;
							list.add(obj);
							}
						}
						System.out.println("gcount"+ gcount);
						System.out.println("bcount"+ bcount);
					}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> bystudentdescHousing(StudentHouseSettingsPOJO pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: generateHousing : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<StudentHouseSettingsVO> list = new ArrayList<StudentHouseSettingsVO>();
		int seccount = 0;
		int count = 0;
		int slno = 0;
		int remcount =0;
		int remcount1=0;
		int classgrem=0;
		int classgremcount = 0;
		int classbremcount = 0;
		int classbrem=0;
		int grem = 0;
		int brem =0;
		int hcount =0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select count(*) from campus_house_settings where accyear_id=? and loc_id =?");
			pstmt.setString(1,pojo.getAccid());
			pstmt.setString(2,pojo.getLocid());
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
			//house count
			
			PreparedStatement hcountpstmt = conn.prepareStatement("select count(generate_houseid) from campus_generate_house where accyearid=? and locid =?");
			hcountpstmt.setString(1,pojo.getAccid());
			hcountpstmt.setString(2,pojo.getLocid());
			System.out.println(hcountpstmt);
			ResultSet hcountrs = hcountpstmt.executeQuery();
			while(hcountrs.next()){
				hcount = hcountrs.getInt(1);
			}
			
			// for getting the remainder
			PreparedStatement repstmt = conn.prepareStatement("select count(*) from campus_generate_house where accyearid = ? and locid = ?");
			repstmt.setString(1,pojo.getAccid());
			repstmt.setString(2,pojo.getLocid());
			
			ResultSet rspstmt = repstmt.executeQuery();
			while(rspstmt.next()){
				remcount = rspstmt.getInt(1);
			}		
			if(remcount !=0){
			PreparedStatement rempstmt = conn.prepareStatement("select sum(grem) as total1,sum(brem) as total2 from campus_generate_house where accyearid = ? and locid = ?");
			rempstmt.setString(1,pojo.getAccid());
			rempstmt.setString(2,pojo.getLocid());
			
			ResultSet remrs = rempstmt.executeQuery();
				while(remrs.next()){
					grem = remrs.getInt("total1");
					brem = remrs.getInt("total2");
				}
				classgrem = grem%count;
				classbrem = brem%count;
			}
			
			System.out.println("classgrem"+classgrem);
			System.out.println("classbrem"+classbrem);
			
			PreparedStatement rempstmt = conn.prepareStatement("select count(*) from campus_house_settings where accyear_id=? and loc_id =?");
			rempstmt.setString(1,pojo.getAccid());
			rempstmt.setString(2,pojo.getLocid());
			ResultSet remrs = rempstmt.executeQuery();
			while(remrs.next()){
				count = remrs.getInt(1);
			}
			ArrayList<String> ele = new ArrayList<String>();
			ArrayList<String> houseid = new ArrayList<String>();
			PreparedStatement housepstmt = conn.prepareStatement("select house_id,housename from campus_house_settings where accyear_id = ? and loc_id = ? order by housename");
			housepstmt.setString(1,pojo.getAccid());
			housepstmt.setString(2,pojo.getLocid());
			ResultSet housers = housepstmt.executeQuery();
			while(housers.next()){
				ele.add(housers.getString("housename"));
				houseid.add(housers.getString("house_id"));
			}
			System.out.println(ele.size());
			housers.close();
			housepstmt.close();
			
			if(hcount == 0){
				System.out.println("hcount" +hcount);
			int gcount = 0;
			int bcount = 0;
			PreparedStatement stusecpstmt = conn.prepareStatement("select count(*)as total,sc.classsection_id_int,cs.classsection_name_var from campus_student_classdetails sc join campus_classsection cs on cs.classsection_id_int = sc.classsection_id_int  where sc.classdetail_id_int = ? and sc.fms_acadamicyear_id_int = ? and sc.locationId = ? group by classsection_id_int order by classsection_name_var");
			stusecpstmt.setString(1,pojo.getClassid());
			stusecpstmt.setString(2,pojo.getAccid());
			stusecpstmt.setString(3,pojo.getLocid());
			System.out.println(stusecpstmt);
			ResultSet stusecrs = stusecpstmt.executeQuery();
			int rem=0;
			int rem1=0;
			
			while(stusecrs.next()){
					int i = 0;
					int j=0;
					
					seccount = stusecrs.getInt(1);
					PreparedStatement secstupstmt = conn.prepareStatement("select concat(st.student_fname_var,' ',st.student_lname_var) as student,st.student_gender_var,st.student_id_int from campus_student st join campus_student_classdetails sc on st.student_id_int = sc.student_id_int and sc.classsection_id_int = ? and sc.classdetail_id_int = ? and sc.locationId = ? and sc.fms_acadamicyear_id_int=? order by student_gender_var,student desc");
					secstupstmt.setString(1,stusecrs.getString("classsection_id_int"));
					secstupstmt.setString(2,pojo.getClassid());
					secstupstmt.setString(3,pojo.getLocid());
					secstupstmt.setString(4,pojo.getAccid());
					System.out.println("secstupstmt"+secstupstmt);
					ResultSet secstupstmtrs = secstupstmt.executeQuery();
					
					while(secstupstmtrs.next()){
						
						StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
						if(secstupstmtrs.getString("student_gender_var").equalsIgnoreCase("Female")){
						gcount ++;
			
						if(rem == ele.size()-1){
							obj.setHousename(ele.get(rem));
							obj.setHouseid(houseid.get(rem));
							i=0;
						}
						else if(rem != 0){
							obj.setHousename(ele.get(rem));
							obj.setHouseid(houseid.get(rem));
						
						}else{
						obj.setHousename(ele.get(i));
						obj.setHouseid(houseid.get(i));
						}
						if(rem == ele.size()-1){
							i=0;
							rem=0;
						}else{
							i++;
						}
						if(i>ele.size()-1){
							i=0;
						}
						if(rem !=0 && rem < ele.size()-1){
							rem++;
						}
						classgrem =0;
						list.add(obj);
					}else{
						bcount ++;
						if(rem1 == ele.size()-1){
							obj.setHousename(ele.get(rem1));
							obj.setHouseid(houseid.get(rem1));
							j=0;
						}
						else if(rem1 != 0){
							obj.setHousename(ele.get(rem1));
							obj.setHouseid(houseid.get(rem1));
						}else{
						obj.setHousename(ele.get(j));
						obj.setHouseid(houseid.get(j));
						}
						if(rem1 == ele.size()-1){
							j=0;
							rem1=0;
						}else{
							j++;
						}
						
						if(j>ele.size()-1){
							j=0;
						}
						if(rem1 !=0 && rem1 < ele.size()-1){
							rem1++;
						}
						classbrem =0;
						list.add(obj);
						}
					}
					rem = gcount%count;
					rem1 = bcount%count;
				}
			}
			else{
				int gcount = 0;
				int bcount = 0;
				PreparedStatement stusecpstmt = conn.prepareStatement("select count(*)as total,sc.classsection_id_int,cs.classsection_name_var from campus_student_classdetails sc join campus_classsection cs on cs.classsection_id_int = sc.classsection_id_int  where sc.classdetail_id_int = ? and sc.fms_acadamicyear_id_int = ? and sc.locationId = ? group by classsection_id_int order by classsection_name_var");
				stusecpstmt.setString(1,pojo.getClassid());
				stusecpstmt.setString(2,pojo.getAccid());
				stusecpstmt.setString(3,pojo.getLocid());
				ResultSet stusecrs = stusecpstmt.executeQuery();
				int rem=0;
				int rem1=0;
				int i = 0;
				int j=0;
				while(stusecrs.next()){
						
						seccount = stusecrs.getInt(1);
						PreparedStatement secstupstmt = conn.prepareStatement("select concat(st.student_fname_var,' ',st.student_lname_var) as student,st.student_gender_var,st.student_id_int from campus_student st join campus_student_classdetails sc on st.student_id_int = sc.student_id_int and sc.classsection_id_int = ? and sc.classdetail_id_int = ? and sc.locationId = ? and sc.fms_acadamicyear_id_int=? order by student_gender_var,student desc");
						secstupstmt.setString(1,stusecrs.getString("classsection_id_int"));
						secstupstmt.setString(2,pojo.getClassid());
						secstupstmt.setString(3,pojo.getLocid());
						secstupstmt.setString(4,pojo.getAccid());
						System.out.println("secstupstmt"+secstupstmt);
						ResultSet secstupstmtrs = secstupstmt.executeQuery();
						
						while(secstupstmtrs.next()){
							
							StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
							if(secstupstmtrs.getString("student_gender_var").equalsIgnoreCase("Female")){
							gcount ++;
							if(classgrem == ele.size()-1){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
							}
							else if(classgrem !=0){
								obj.setHousename(ele.get(classgrem));
								obj.setHouseid(houseid.get(classgrem));
								i=classgrem;
							}
							else{
							obj.setHousename(ele.get(i));
							obj.setHouseid(houseid.get(i));
							}
							i++;
							if(i > ele.size()-1){
								i=0;
							}
							if(classgrem == ele.size()-1){
								i=0;
							}
							classgremcount=0;
							classgrem =0;
							list.add(obj);
						}else{
							
							bcount ++;
							if(classbrem == ele.size()-1){
								obj.setHousename(ele.get(classbrem));
								obj.setHouseid(houseid.get(classbrem));
							}
							else if(classbrem!=0){
								obj.setHousename(ele.get(classbrem));
								obj.setHouseid(houseid.get(classbrem));
								j=classbrem;
							}else{
								obj.setHousename(ele.get(j));
								obj.setHouseid(houseid.get(j));
							}
							j++;
							if(j>ele.size()-1){
								j=0;
							}
							if(classbrem == ele.size()-1){
								j=0;
							}
							classbremcount=0;
							classbrem =0;
							list.add(obj);
							}
						}
					}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public String checkselection(String accyear, String locid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: checkselection : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String result = null;
		int count =0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select count(selection) from campus_generate_house where accyearid=? and locid=?");
			pstmt.setString(1,accyear);
			pstmt.setString(2,locid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
			if(count!=0){
				PreparedStatement selpstmt = conn.prepareStatement("select distinct selection from campus_generate_house where accyearid=? and locid=?");
				selpstmt.setString(1,accyear);
				selpstmt.setString(2,locid);
				ResultSet selrs = selpstmt.executeQuery();
				while(selrs.next()){
					result = selrs.getString("selection");
				}
			}else{
				result = "false";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {

					conn.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> displayHouseSettingAdminoWise(StudentHouseSettingsPOJO pojo,
			String classname, String filter1) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: displayHouseSettingStudentWise : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<StudentHouseSettingsVO> list = new ArrayList<StudentHouseSettingsVO>();
		int slno = 0;
		String houseid = null;
		try{

			conn = JDBCConnection.getSeparateConnection();
			if(filter1 == "admissionwiseasc"){
				pstmt = conn.prepareStatement("select distinct sc.fms_acadamicyear_id_int,concat(st.student_fname_var,' ',st.student_lname_var) as student,st.student_gender_var,st.student_admissionno_var,st.student_id_int,cs.classsection_name_var,cs.classsection_id_int from campus_student st join campus_student_classdetails sc on st.student_id_int = sc.student_id_int join campus_classsection cs on cs.classsection_id_int = sc.classsection_id_int join campus_acadamicyear acy on sc.fms_acadamicyear_id_int=acy.acadamic_id  and sc.fms_acadamicyear_id_int = ? and sc.locationId=? and sc.classdetail_id_int = ? order by classsection_name_var,student_gender_var,CAST(st.student_admissionno_var AS UNSIGNED)");
			}else if(filter1 == "admissionwisedesc"){
				pstmt = conn.prepareStatement("select distinct sc.fms_acadamicyear_id_int,concat(st.student_fname_var,' ',st.student_lname_var) as student,st.student_gender_var,st.student_admissionno_var,st.student_id_int,cs.classsection_name_var,cs.classsection_id_int from campus_student st join campus_student_classdetails sc on st.student_id_int = sc.student_id_int join campus_classsection cs on cs.classsection_id_int = sc.classsection_id_int join campus_acadamicyear acy on sc.fms_acadamicyear_id_int=acy.acadamic_id  and sc.fms_acadamicyear_id_int = ? and sc.locationId=? and sc.classdetail_id_int = ? order by classsection_name_var,student_gender_var,CAST(st.student_admissionno_var AS UNSIGNED) desc");
			}
			pstmt.setString(1,pojo.getAccid());
			pstmt.setString(2,pojo.getLocid());
			pstmt.setString(3,pojo.getClassid());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++;
				StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
				obj.setSlno(slno);
				obj.setSectionid(rs.getString("classsection_id_int"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setStuname(rs.getString("student")+"-"+rs.getString("student_gender_var"));
				obj.setStuid(rs.getString("student_id_int"));
				obj.setClassid(pojo.getClassid());
				obj.setClassname(classname);
				obj.setAdmissionno(rs.getString("student_admissionno_var"));
				
				PreparedStatement housepstmt = conn.prepareStatement("select house_id from campus_student_house where  student_id = ? and class_id =? and academic_year = ? and loc_id =?");
				housepstmt.setString(1,rs.getString("student_id_int"));
				housepstmt.setString(2,pojo.getClassid());
				housepstmt.setString(3,pojo.getAccid());
				housepstmt.setString(4,pojo.getLocid());
				ResultSet housers = housepstmt.executeQuery();
				while(housers.next()){
					houseid = housers.getString("house_id");
				}
				
				PreparedStatement idpstmt = conn.prepareStatement("select housename from campus_house_settings where house_id = ? and accyear_id = ? and loc_id = ?");
				idpstmt.setString(1,houseid);
				idpstmt.setString(2,pojo.getAccid());
				idpstmt.setString(3,pojo.getLocid());
				ResultSet idrs = idpstmt.executeQuery();
				while(idrs.next()){
					obj.setHousename(idrs.getString("housename"));
					obj.setHouseid(houseid);
				}
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> displayHouseSettingAdminoEven(StudentHouseSettingsPOJO pojo,
			String classname, String filter1) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: displayHouseSettingStudentWise : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<StudentHouseSettingsVO> list = new ArrayList<StudentHouseSettingsVO>();
		int slno = 0;
		String houseid = null;
		try{

			conn = JDBCConnection.getSeparateConnection();
			if(filter1 == "admissionwiseeven"){
				pstmt = conn.prepareStatement("select distinct sc.fms_acadamicyear_id_int,concat(st.student_fname_var,' ',st.student_lname_var) as student,st.student_gender_var,st.student_admissionno_var,st.student_id_int,cs.classsection_name_var,cs.classsection_id_int from campus_student st join campus_student_classdetails sc on st.student_id_int = sc.student_id_int join campus_classsection cs on cs.classsection_id_int = sc.classsection_id_int join campus_acadamicyear acy on sc.fms_acadamicyear_id_int=acy.acadamic_id  and sc.fms_acadamicyear_id_int = ? and sc.locationId=? and sc.classdetail_id_int = ? order by classsection_name_var,student_gender_var,(CAST(st.student_admissionno_var AS UNSIGNED))%2");
			}else if(filter1 == "admissionwiseodd"){
				pstmt = conn.prepareStatement("select distinct sc.fms_acadamicyear_id_int,concat(st.student_fname_var,' ',st.student_lname_var) as student,st.student_gender_var,st.student_admissionno_var,st.student_id_int,cs.classsection_name_var,cs.classsection_id_int from campus_student st join campus_student_classdetails sc on st.student_id_int = sc.student_id_int join campus_classsection cs on cs.classsection_id_int = sc.classsection_id_int join campus_acadamicyear acy on sc.fms_acadamicyear_id_int=acy.acadamic_id  and sc.fms_acadamicyear_id_int = ? and sc.locationId=? and sc.classdetail_id_int = ? order by classsection_name_var,student_gender_var,(CAST(st.student_admissionno_var AS UNSIGNED))%2 desc");
			}
			pstmt.setString(1,pojo.getAccid());
			pstmt.setString(2,pojo.getLocid());
			pstmt.setString(3,pojo.getClassid());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++;
				StudentHouseSettingsVO obj = new StudentHouseSettingsVO();
				obj.setSlno(slno);
				obj.setSectionid(rs.getString("classsection_id_int"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setStuname(rs.getString("student")+"-"+rs.getString("student_gender_var"));
				obj.setStuid(rs.getString("student_id_int"));
				obj.setClassid(pojo.getClassid());
				obj.setClassname(classname);
				obj.setAdmissionno(rs.getString("student_admissionno_var"));
				
				PreparedStatement housepstmt = conn.prepareStatement("select house_id from campus_student_house where  student_id = ? and class_id =? and academic_year = ? and loc_id =?");
				housepstmt.setString(1,rs.getString("student_id_int"));
				housepstmt.setString(2,pojo.getClassid());
				housepstmt.setString(3,pojo.getAccid());
				housepstmt.setString(4,pojo.getLocid());
				ResultSet housers = housepstmt.executeQuery();
				while(housers.next()){
					houseid = housers.getString("house_id");
				}
				
				PreparedStatement idpstmt = conn.prepareStatement("select housename from campus_house_settings where house_id = ? and accyear_id = ? and loc_id = ?");
				idpstmt.setString(1,houseid);
				idpstmt.setString(2,pojo.getAccid());
				idpstmt.setString(3,pojo.getLocid());
				ResultSet idrs = idpstmt.executeQuery();
				while(idrs.next()){
					obj.setHousename(idrs.getString("housename"));
					obj.setHouseid(houseid);
				}
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public String regenerateHousedetails(String accyear, String locid, String genhouid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentHouseSettingsDAOImpl: regenerateHousedetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String result = null;
		int count =0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.DELETE_GENERATE_HOUSE_DETAILS);
			pstmt.setString(1,accyear);
			pstmt.setString(2,locid);
			System.out.println(pstmt);
			count = pstmt.executeUpdate();
			
			if(count>0){
				PreparedStatement selpstmt = conn.prepareStatement(SQLUtilConstants.DELETE_STUDENT_HOUSE_DETAILS);
				selpstmt.setString(1,accyear);
				selpstmt.setString(2,locid);
				System.out.println(selpstmt);
				count = selpstmt.executeUpdate();
				selpstmt.close();
			}
			if(count > 0){
				result="true";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return result;
	}

}
