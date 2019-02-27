package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;





import com.centris.campus.dao.HolidayMasterSDAO;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.forms.HolidayMasterForm;
import com.centris.campus.pojo.HolidayMasterPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.HolidayMasterSqlUtil;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.HolidayMasterVo;
import com.centris.campus.vo.LocationVO;


public class HolidayMasterDAOImpl implements HolidayMasterSDAO{
	
	private static 	Logger logger = Logger.getLogger(HolidayMasterDAOImpl.class);
	public String addMultipleHoliday(HolidayMasterForm formObj, String usercode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterDAOImpl :addSingleHolidayDetailDaoImpl: Starting");

		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;

		List<LocationVO> locationList = new ArrayList<LocationVO>();
		int count=0;
		String success=null;
		IDGenerator id = new IDGenerator();
		try {
			System.out.println("Inside the more");
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(HolidayMasterSqlUtil.INSERT_HOLIDAY);
			
			System.out.println(formObj.getLocation());
			//System.out.println(formObj.getDate().length);
			if(formObj.getLocation().equalsIgnoreCase("%%")){
				locationList = new  LocationBD().getLocationDetails();
				System.out.println(locationList.size());
			}
			
			if(locationList.size()!=0){
				for(int i=0;i<formObj.getHoliday_type().length;i++){
				for(int j=0;j<locationList.size();j++){
					
						
						pstmt.setString(1, id.getPrimaryKeyID("campus_holidaymaster"));
						pstmt.setString(2,HelperClass.convertUIToDatabase(formObj.getDate()[i]));
						pstmt.setString(3,formObj.getWeekday()[i]);
						pstmt.setString(4, formObj.getHoliday()[i]);
						pstmt.setString(5, formObj.getHoliday_type()[i]);
						pstmt.setString(6, formObj.getYear().trim());
						pstmt.setString(7, usercode);
						pstmt.setString(8, locationList.get(j).getLocation_id());
						count=pstmt.executeUpdate();
						
					}
				}
			}
			else{
				for(int i=0;i<formObj.getHoliday_type().length;i++){
					
					pstmt.setString(1, id.getPrimaryKeyID("campus_holidaymaster"));
					pstmt.setString(2,HelperClass.convertUIToDatabase(formObj.getDate()[i]));
					pstmt.setString(3,formObj.getWeekday()[i]);
					pstmt.setString(4, formObj.getHoliday()[i]);
					pstmt.setString(5, formObj.getHoliday_type()[i]);
					pstmt.setString(6, formObj.getYear());
					pstmt.setString(7, usercode);
					pstmt.setString(8, formObj.getLocation());
					count=pstmt.executeUpdate();
				}
			}
			
			
			if (count > 0) {

				success = MessageConstants.HOLIDAY_ADD_SUCCESS;

			} 
			else{
				success=MessageConstants.HOLIDAY_ADD_FAILURE;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1);
		}finally{
			try{

				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null
						&& (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1);
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterDAOImpl: addSingleHolidayDetailDaoImpl : Ending");

		return success;
	}
	@Override
	public ArrayList<HolidayMasterVo> getHolidayDetails(String academic_year,String schoolLocation) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterDAOImpl :addSingleHolidayDetailDaoImpl: Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HolidayMasterVo> list = new ArrayList<HolidayMasterVo>();
		String loc = null;
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(HolidayMasterSqlUtil.GET_ALL_HOLIDAY);
			pstmt.setString(1, academic_year);
			if(!schoolLocation.equalsIgnoreCase("all"))
			pstmt.setString(2, schoolLocation);
			else
			pstmt.setString(2, "%%");
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				HolidayMasterVo vo = new HolidayMasterVo();
				
				vo.setHolidaysName(rs.getString("HOLIDAY_NAME"));
				vo.setDate(HelperClass.convertDatabaseToUI(rs.getString("HOLIDAY_DATE")));
				vo.setHolidayType(rs.getString("HOLIDAY_TYPE"));
				vo.setLocId(rs.getString("LOC_ID"));
				vo.setHolId(rs.getString("HOL_ID"));
				 PreparedStatement pstmt1 = conn.prepareStatement("select Location_Name from campus_location where Location_Id = ?");
				 pstmt1.setString(1, rs.getString("LOC_ID"));
				 
				 ResultSet rs1=pstmt1.executeQuery();
				 while(rs1.next()){
					 loc = rs1.getString("Location_Name");
					 
				 }
				vo.setLocName(loc);
				list.add(vo);
				
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		finally{
			
			try{
				
				if(rs != null && (!rs.isClosed())){
					rs.close();
				}
				
				if (pstmt != null
						&& (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

				
				
				
			}catch(Exception e){
				
				e.printStackTrace();
			}
			
		}
		
		
		return list;
	}
	@Override
	public HolidayMasterVo editHolidayDetail(String deptId, String date) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterDAOImpl :editHolidayDetailDaoImpl: Starting");
		
		
		Connection conn = null;
		ResultSet rs=null;
		PreparedStatement pstmt= null;
		String locname= null;
		String accyname=null;
		HolidayMasterVo holidayMasterVO = new HolidayMasterVo();
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt =  conn.prepareStatement(HolidayMasterSqlUtil.GET_EDIT_HOLIDAY); 
			 pstmt.setString(1,HelperClass.convertUIToDatabase(date));
			 pstmt.setString(2,deptId);
			
			 System.out.println(pstmt);
			rs =pstmt.executeQuery();
		
			 while(rs.next()){
				 
				 holidayMasterVO.setLocId(rs.getString("LOC_ID"));
				 PreparedStatement pstmt1 = conn.prepareStatement("select Location_Name from campus_location where Location_Id = ?");
				 pstmt1.setString(1, deptId);
				 
				 ResultSet rs1=pstmt1.executeQuery();
				 while(rs1.next()){
					 locname = rs1.getString("Location_Name");
					 
				 }
				 System.out.println(locname);
				 holidayMasterVO.setLocName(locname);
				 holidayMasterVO.setHolidaysName(rs.getString("HOLIDAY_NAME"));
				 holidayMasterVO.setDate(HelperClass.convertDatabaseToUI(rs.getString("HOLIDAY_DATE")));
				 holidayMasterVO.setWeekDay(rs.getString("WEEKDAY"));
				 holidayMasterVO.setYear(rs.getString("CURRENT_YEAR"));
				 holidayMasterVO.setHolId(rs.getString("HOL_ID"));
				 
				 PreparedStatement pstmt2 = conn.prepareStatement("select acadamic_year from campus_acadamicyear where acadamic_id =?");
				 pstmt2.setString(1, rs.getString("CURRENT_YEAR").trim());
				 
				 System.out.println(pstmt2);
				 ResultSet rs2 =pstmt2.executeQuery();
				 while(rs2.next()){
					 accyname = rs2.getString("acadamic_year");
					 
				 }
				 holidayMasterVO.setAccyname(accyname);
				 holidayMasterVO.setHolidayType(rs.getString("HOLIDAY_TYPE"));
				
			 }
			 
			 
			 
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1);
		}finally{
				try{
					
					if(rs != null && (!rs.isClosed())){
						rs.close();
					}
					
					if (pstmt != null
							&& (!pstmt.isClosed())) {

						pstmt.close();
					}
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
						
					}
					
				catch (SQLException sqle) {
					sqle.printStackTrace();
					logger.error(sqle);
				} catch (Exception e1) {
					e1.printStackTrace();
					logger.error(e1);
				}
			}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterDAOImpl: editHolidayDetailDaoImpl : Ending");
		
	return holidayMasterVO;
		
}
	/*public String addSingleHolidayDetailDaoImpl(HolidayMasterPojo hpojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterDAOImpl :addSingleHolidayDetailDaoImpl: Starting");
		    
		   Connection conn = null;
			PreparedStatement pstmt= null;
			
			
		  List<LocationVO> departmentList = new ArrayList<LocationVO>();
		  int count=0;
		  String success=MessageConstants.HOLIDAY_ADD_FAILURE;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(HolidayMasterSqlUtil.INSERT_HOLIDAY); 
             
  			if(hpojo.getLocation().equalsIgnoreCase("%%")){
  				departmentList = new  LocationBD().getLocationDetails();
  			}
			
  		    if(departmentList.size()!=0){
  		    	
  	          for(int j=0;j<departmentList.size();j++){
  	        	  		
  	        	  		pstmt.setString(1, HelperClass.convertUIToDatabase(hpojo.getDate()));
  	        	  		pstmt.setString(2, hpojo.getWeekday());
  	        	  		pstmt.setString(3, hpojo.getHoliday());
  	        	  		pstmt.setString(4, hpojo.getHolidayType());
  	        	  		pstmt.setString(5, hpojo.getYear());
  	        	  		pstmt.setString(6, hpojo.getCreatedby());
  	        	  		pstmt.setString(7, departmentList.get(j).getLocation_id());
						
						count=pstmt.executeUpdate();
  	          }
  		    }
  		    else{
  		    	
  		    	
  		    	pstmt.setString(1,HelperClass.convertUIToDatabase(hpojo.getDate()));
  		    	pstmt.setString(2, hpojo.getWeekday());
  		    	pstmt.setString(3, hpojo.getHoliday());
  		    	pstmt.setString(4, hpojo.getHolidayType());
  		    	pstmt.setString(5, hpojo.getYear());
  		    	pstmt.setString(6, hpojo.getCreatedby());
  		    	pstmt.setString(7, hpojo.getLocation());
				
				count=pstmt.executeUpdate();
  		    }

			if (count > 0) {

				success = MessageConstants.HOLIDAY_ADD_SUCCESS;

			} 
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1);
		}finally{
			try{
				
				if (pstmt != null
						&& (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1);
			}
		}
	
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HolidayMasterDAOImpl: addSingleHolidayDetailDaoImpl : Ending");
		
		return success;
	}*/

public  String addSingleHolidayDetailDaoImpl(HolidayMasterPojo hpojo){
	

	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in HolidayMasterDAOImpl :updateHolidayDaoImpl: Starting");
	Connection conn =null;
	PreparedStatement pstmt= null;
	int no=0;
	String Status=MessageConstants.HOLIDAY_UPDATE_FAILURE; 
	
	try {
		conn = JDBCConnection.getSeparateConnection();
			pstmt =  conn.prepareStatement(HolidayMasterSqlUtil.UPDATE_HOLIDAY); 
				
					pstmt.setString(1, hpojo.getHoliday());
					pstmt.setString(2, hpojo.getHolidayType());
					pstmt.setString(3, hpojo.getCreatedby());
					pstmt.setTimestamp(4, hpojo.getCreateddate());
					pstmt.setString(5,HelperClass.convertUIToDatabase(hpojo.getDate()));
					pstmt.setString(6,hpojo.getLocation());
					pstmt.setString(7, hpojo.getHolidayid());
					System.out.println(pstmt);
					no=pstmt.executeUpdate();
					
					if(no>0){
						
						Status=MessageConstants.HOLIDAY_UPDATE_SUCCESS;
					}
					
			
	} catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle);
	} catch (Exception e1) {
		e1.printStackTrace();
		logger.error(e1);
	}finally{
		try{
			if (pstmt != null
					&& (!pstmt.isClosed())) {

				pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1);
		}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in HolidayMasterDAOImpl: updateHolidayDaoImpl : Ending");
	
	return Status;

	
	
}
public Boolean deleteSingleHoliday(String holidaydate, String deptid) {

	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in HolidayMasterDAOImpl :deleteSingleHoliday: Starting");
	
	Connection conn = null;
	PreparedStatement pstmt= null;
	boolean status=false;

	try {
		
		conn = JDBCConnection.getSeparateConnection();
		pstmt= conn.prepareStatement(HolidayMasterSqlUtil.DELETE_SINGLE_HOLIDAY);
		pstmt.setString(1,HelperClass.convertUIToDatabase(holidaydate));
		pstmt.setString(2, deptid.trim());
		System.out.println(pstmt);
	    if(pstmt.executeUpdate()>0)
	    	
	    	status= true;
	    else
	    	status=false;
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle);
	} catch (Exception e1) {
		e1.printStackTrace();
		logger.error(e1);
	}finally{
			try{
				
				if (pstmt != null
						&& (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1);
			}
		}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in HolidayMasterDAOImpl: deleteSingleHoliday : Ending");
	
	return status;
	
}
public String dateValidate(String dateval, String location, String accYear) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in HolidayMasterDAOImpl :dateValidate: Starting");
	Connection conn = null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int datevalexit = 0;
	String status=null;


	try {
		conn = JDBCConnection.getSeparateConnection();
		pstmt = conn.prepareStatement(HolidayMasterSqlUtil.DATE_VALIDATE);
		pstmt.setString(1,HelperClass.convertUIToDatabase(dateval));
		
		pstmt.setString(2, location);
		pstmt.setString(3, accYear);
		System.out.println(pstmt);
		 rs = pstmt.executeQuery();
		
		while(rs.next()) {
			datevalexit = rs.getInt(1);
		}
		
		if(datevalexit > 0){
			System.out.println(datevalexit);
			status="exit";
		}else{
			status="not exit";
			
		}
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle);
	} catch (Exception e1) {
		e1.printStackTrace();
		logger.error(e1);
	}finally{
			try{
				
				
				
				if (rs != null
						&& (!rs.isClosed())) {

					rs.close();
				}
				
				
				if (pstmt != null
						&& (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1);
			}
		}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in HolidayMasterDAOImpl: dateValidate : Ending");
	
	return status;
}
public ArrayList<HolidayMasterVo> searchLocationDetails(String searchterm, String academic_year) {
	

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in HolidayMasterDAOImpl :addSingleHolidayDetailDaoImpl: Starting");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ArrayList<HolidayMasterVo> list = new ArrayList<HolidayMasterVo>();
	String loc = null;
	try{
		conn = JDBCConnection.getSeparateConnection();
		pstmt = conn.prepareStatement(HolidayMasterSqlUtil.GET_SEARCH_HOLIDAY);
		pstmt.setString(1, searchterm);
		pstmt.setString(2, academic_year);
		
		System.out.println(pstmt);
		rs=pstmt.executeQuery();
		while(rs.next()){
			HolidayMasterVo vo = new HolidayMasterVo();
			
			vo.setHolidaysName(rs.getString("HOLIDAY_NAME"));
			vo.setDate(HelperClass.convertDatabaseToUI(rs.getString("HOLIDAY_DATE")));
			vo.setHolidayType(rs.getString("HOLIDAY_TYPE"));
			vo.setLocId(rs.getString("LOC_ID"));
			vo.setHolId(rs.getString("HOL_ID"));
			
			PreparedStatement pstmt1 = conn.prepareStatement("select Location_Name from campus_location where Location_Id = ?");
			 pstmt1.setString(1, rs.getString("LOC_ID"));
			 
			 ResultSet rs1=pstmt1.executeQuery();
			 while(rs1.next()){
				 loc = rs1.getString("Location_Name");
				 
			 }
			vo.setLocName(loc);
			
			
			list.add(vo);
			
		}
		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	finally{
		
		try{
			
			if(rs != null && (!rs.isClosed())){
				rs.close();
			}
			
			if (pstmt != null
					&& (!pstmt.isClosed())) {

				pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}

			
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	
	
	return list;

	
	
	
	
}
public Boolean deleteSingleHoliday(String[] list) {


	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in HolidayMasterDAOImpl :deleteSingleHoliday: Starting");
	
	Connection conn = null;
	PreparedStatement pstmt= null;
	boolean status=false;

	try {
		
		conn = JDBCConnection.getSeparateConnection();
		for(int i=0 ;i<list.length;i++){
		pstmt= conn.prepareStatement(HolidayMasterSqlUtil.DELETE_SINGLE_HOLIDAY);
		/*pstmt.setString(1,HelperClass.convertUIToDatabase(holidaydate));*/
		pstmt.setString(1, list[i].trim());
		System.out.println(pstmt);
	    if(pstmt.executeUpdate()>0)
	    	
	    	status= true;
	    else
	    	status=false;
		}
	} catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle);
	} catch (Exception e1) {
		e1.printStackTrace();
		logger.error(e1);
	}finally{
			try{
				
				if (pstmt != null
						&& (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1);
			}
		}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in HolidayMasterDAOImpl: deleteSingleHoliday : Ending");
	
	return status;
	

}
public HolidayMasterVo editHolidayDetail(String deptId) {

	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in HolidayMasterDAOImpl :editHolidayDetailDaoImpl: Starting");
	
	
	Connection conn = null;
	ResultSet rs=null;
	PreparedStatement pstmt= null;
	String locname= null;
	String accyname=null;
	HolidayMasterVo holidayMasterVO = new HolidayMasterVo();
	try {
		
		conn = JDBCConnection.getSeparateConnection();
		pstmt =  conn.prepareStatement(HolidayMasterSqlUtil.GET_EDIT_HOLIDAY); 
		 
		 pstmt.setString(1,deptId);
		
		 System.out.println(pstmt);
		rs =pstmt.executeQuery();
	
		 while(rs.next()){
			 
			 holidayMasterVO.setLocId(rs.getString("LOC_ID"));
			 PreparedStatement pstmt1 = conn.prepareStatement("select Location_Name from campus_location where Location_Id = ?");
			 pstmt1.setString(1, deptId);
			 
			 ResultSet rs1=pstmt1.executeQuery();
			 while(rs1.next()){
				 locname = rs1.getString("Location_Name");
				 
			 }
			 System.out.println(locname);
			 holidayMasterVO.setLocName(locname);
			 holidayMasterVO.setHolidaysName(rs.getString("HOLIDAY_NAME"));
			 holidayMasterVO.setDate(HelperClass.convertDatabaseToUI(rs.getString("HOLIDAY_DATE")));
			 holidayMasterVO.setWeekDay(rs.getString("WEEKDAY"));
			 holidayMasterVO.setYear(rs.getString("CURRENT_YEAR"));
			 holidayMasterVO.setHolId(rs.getString("HOL_ID"));
			 PreparedStatement pstmt2 = conn.prepareStatement("select acadamic_year from campus_acadamicyear where acadamic_id =?");
			 pstmt2.setString(1, rs.getString("CURRENT_YEAR").trim());
			 
			 System.out.println(pstmt2);
			 ResultSet rs2 =pstmt2.executeQuery();
			 while(rs2.next()){
				 accyname = rs2.getString("acadamic_year");
				 
			 }
			 holidayMasterVO.setAccyname(accyname);
			 holidayMasterVO.setHolidayType(rs.getString("HOLIDAY_TYPE"));
			
		 }
		 
		 
		 
	} catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle);
	} catch (Exception e1) {
		e1.printStackTrace();
		logger.error(e1);
	}finally{
			try{
				
				if(rs != null && (!rs.isClosed())){
					rs.close();
				}
				
				if (pstmt != null
						&& (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
					
				}
				
			catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1);
			}
		}
	
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in HolidayMasterDAOImpl: editHolidayDetailDaoImpl : Ending");
	
return holidayMasterVO;
	

}
public String HolidayNameValidate(String holiday, String location,
		String accyear) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in HolidayMasterDAOImpl :dateValidate: Starting");
	Connection conn = null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int datevalexit = 0;
	String status=null;


	try {
		conn = JDBCConnection.getSeparateConnection();
		pstmt = conn.prepareStatement(HolidayMasterSqlUtil.HOLIDAYNAME_VALIDATE);
		pstmt.setString(1,holiday);
		pstmt.setString(2, location);
		pstmt.setString(3, accyear.trim());
		System.out.println(pstmt);
		 rs = pstmt.executeQuery();
		
		while(rs.next()) {
			datevalexit = rs.getInt(1);
		}
		
		if(datevalexit > 0){
			System.out.println(datevalexit);
			status="exit";
		}else{
			status="not exit";
			
		}
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle);
	} catch (Exception e1) {
		e1.printStackTrace();
		logger.error(e1);
	}finally{
			try{
				
				
				
				if (rs != null
						&& (!rs.isClosed())) {

					rs.close();
				}
				
				
				if (pstmt != null
						&& (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1);
			}
		}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in HolidayMasterDAOImpl: dateValidate : Ending");
	
	return status;
}
	
	
	
	
}
