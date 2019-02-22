	package com.centris.campus.daoImpl;
	
	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
	


	import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
	





import com.centris.campus.delegate.eventBD;
import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.pojo.eventRegPojo;
import com.centris.campus.util.EventsSqlUtils;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.LibrarySqlUtils;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.ElectionVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.eventRegVo;
import com.mysql.jdbc.CallableStatement;

import sun.net.www.content.text.plain;

import com.itextpdf.text.log.SysoLogger;
	
	public class EventsDaoImpl<ParticipantsAdmisNos> {
		private static final Logger logger = Logger
				.getLogger(StudentAttendanceDaoImpl.class);
	
		public String SaveEventRegistration(eventRegPojo pojo) throws Exception{
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in EventsDaoImpl: SaveEventRegistration Starting");
	PreparedStatement psmt = null;
	Connection conn =null;
	int count= 0;
	String result=null;
	ResultSet rs =null;
	String key =null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			key=IDGenerator.getPrimaryKeyID("campus_event_registration");
			
			if( pojo.getHiddenEventId()==null || pojo.getHiddenEventId().equalsIgnoreCase("")){
			psmt=conn.prepareStatement("SELECT COUNT(`eventName`) FROM `campus_event_registration` WHERE eventName=? and `accyer_ID`=? AND `location_ID`=?");
			psmt.setString(1, pojo.getEventName());
			psmt.setString(2, pojo.getAccyear());
			psmt.setString(3, pojo.getLocation());
			rs=psmt.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
			if(count==0){
			psmt=conn.prepareStatement(EventsSqlUtils.SaveEventRegistration);
			psmt.setString(1, key);
			psmt.setString(2, pojo.getEventName());
			psmt.setString(3, pojo.getEventType());
			psmt.setString(4, pojo.getStartsOn());
			psmt.setString(5, pojo.getEndsOn());
			psmt.setString(6, pojo.getStartsRegis());
			psmt.setString(7, pojo.getEndsRegis());
			psmt.setString(8, pojo.getStatus());
			psmt.setString(9, pojo.getDescription());
			psmt.setString(10, pojo.getIshouseWise());
			psmt.setString(11, pojo.getIsAprovPps());
			psmt.setString(12, pojo.getAccyear());
			psmt.setString(13, pojo.getLocation());
			count=psmt.executeUpdate();
			if(count>0){
			
				if(!pojo.getImportSettings().equalsIgnoreCase("noImport")) {
					PreparedStatement catget=conn.prepareStatement("SELECT * FROM `campus_event_category` WHERE `eventId`=?");
					catget.setString(1, pojo.getImportSettings());
					ResultSet catrs=catget.executeQuery();
					while(catrs.next()) {
						eventRegPojo cat = new eventRegPojo();
						cat.setEventId(key);
						cat.setCategoryName(catrs.getString("categoryName"));
						cat.setClassList(catrs.getString("classList"));
						saveCategory(cat);
				}
					if (catrs != null && (!catrs.isClosed())) {
						catrs.close();
					}
					if (catget != null && (!catget.isClosed())) {
						catget.close();
					}
					

					 catget=conn.prepareStatement("SELECT * FROM `campus_event_stage` WHERE eventId=?");
					 catget.setString(1, pojo.getImportSettings());
					 catrs=catget.executeQuery();
					while(catrs.next()) {
						eventRegPojo cat = new eventRegPojo();
					
						cat.setEventId(key);
						cat.setStageName(catrs.getString("stageName"));
						cat.setBuilding(catrs.getString("building"));
						cat.setFloorName(catrs.getString("floorName"));
						cat.setRoomNumber(catrs.getString("roomNumber"));
						cat.setInfo(catrs.getString("description"));
						saveStageSetting(cat);
				}
					if (catrs != null && (!catrs.isClosed())) {
						catrs.close();
					}
					if (catget != null && (!catget.isClosed())) {
						catget.close();
					}
					
					 catget=conn.prepareStatement("SELECT * FROM `campus_event_programcreation` WHERE event_id=?");
					 catget.setString(1, pojo.getImportSettings());
					 catrs=catget.executeQuery();
					while(catrs.next()) {
						eventRegPojo cat = new eventRegPojo();
					
						cat.setEventId(key);
						
						cat.setCategoryId(catrs.getString("category_id"));
						cat.setPartiType(catrs.getString("participantsType"));
						cat.setProgName(catrs.getString("program_name"));
						
						cat.setTermsAllowed(catrs.getString("teamsAllowed"));
						cat.setPartiNo(catrs.getString("noOfparticipants"));
						cat.setDuration(catrs.getString("duration"));
						
						cat.setProgType(catrs.getString("programType"));
						cat.setNoOfChildHouse(catrs.getString("noOfHouseChildren"));
						cat.setProgDate(catrs.getString("programDate"));
						
						cat.setInfoStaff(catrs.getString("info_staff"));
						cat.setInfoGeneral(catrs.getString("info_general"));
						cat.setIsHouse(catrs.getString("isHouseWise"));
						cat.setJudgeList(catrs.getString("noOfJudge"));
						
						saveProgram(cat,catrs.getString("noOfJudge"));
				}
					if (catrs != null && (!catrs.isClosed())) {
						catrs.close();
					}
					if (catget != null && (!catget.isClosed())) {
						catget.close();
					}
					
					
					
					catget=conn.prepareStatement("SELECT * FROM `campus_event_greenroom` WHERE event_id=?");
					 catget.setString(1, pojo.getImportSettings());
					 catrs=catget.executeQuery();
					while(catrs.next()) {
						eventRegPojo cat = new eventRegPojo();
					
						cat.setEventId(key);
						cat.setGreenRoomName(catrs.getString("greenroom_name"));
						cat.setGreenRoomType(catrs.getString("greenroom_type"));
						cat.setBuilding(catrs.getString("building"));
						cat.setFloorName(catrs.getString("floor"));
						cat.setRoomNumber(catrs.getString("roomno"));
						
						
						saveGreenRoom(cat);
				}
					if (catrs != null && (!catrs.isClosed())) {
						catrs.close();
					}
					if (catget != null && (!catget.isClosed())) {
						catget.close();
					}
					
					
					
					catget=conn.prepareStatement("SELECT * FROM `campus_event_prizelevel` WHERE event_name=?");
					 catget.setString(1, pojo.getImportSettings());
					 catrs=catget.executeQuery();
					while(catrs.next()) {
						eventRegPojo cat = new eventRegPojo();
					
						cat.setEventName(key);
						
						cat.setProgrammeName(catrs.getString("program_name"));
						cat.setCategory(catrs.getString("category"));
						cat.setPrizeLevel(catrs.getString("prize_level"));
						cat.setPoints(catrs.getString("points"));
						cat.setSeqNo(catrs.getString("seq_no"));
						cat.setDescription(catrs.getString("description"));
						
						savePrizelevelSetting(cat);
				}
					if (catrs != null && (!catrs.isClosed())) {
						catrs.close();
					}
					if (catget != null && (!catget.isClosed())) {
						catget.close();
					}
					
					
					
					
					catget=conn.prepareStatement("SELECT * FROM `campus_event_criteria` WHERE `Event`=?");
					 catget.setString(1, pojo.getImportSettings());
					 catrs=catget.executeQuery();
					while(catrs.next()) {
						eventRegPojo cat = new eventRegPojo();
					
						
						cat.setEventName(key);
						cat.setProgrammeName(catrs.getString("Programme_Name"));
						cat.setCategory(catrs.getString("Category"));
					
						cat.setCriteria(catrs.getString("Criteria"));
						cat.setMaxMarks(catrs.getString("Max_marks"));
						
						saveCriteriaSetting(cat);
				}
					if (catrs != null && (!catrs.isClosed())) {
						catrs.close();
					}
					if (catget != null && (!catget.isClosed())) {
						catget.close();
					}
					
					
					
					
				}
				
				result ="true";
			}else{
				result="false";
			}
			}else{
				result="insertDuplicate";
			}
		}else{
			
			if(pojo.getEventNameHidden().equalsIgnoreCase(pojo.getEventName())){
				
			psmt=conn.prepareStatement(EventsSqlUtils.updateEventRegistarion);
			psmt.setString(1, pojo.getEventType());
			psmt.setString(2, pojo.getStartsOn());
			psmt.setString(3, pojo.getEndsOn());
			psmt.setString(4, pojo.getStartsRegis());
			psmt.setString(5, pojo.getEndsRegis());
			psmt.setString(6, pojo.getStatus());
			psmt.setString(7, pojo.getDescription());
			psmt.setString(8, pojo.getIshouseWise());
			psmt.setString(9, pojo.getIsAprovPps());
			psmt.setString(10, pojo.getEventName());
			psmt.setString(11, pojo.getHiddenEventId());
		
			System.out.println(psmt);
			count = psmt.executeUpdate();
			if(count>0){
				result ="UpdateTrue";
			}else{
				result="false";
				}
		}else{
			psmt=conn.prepareStatement("SELECT COUNT(`eventName`) FROM `campus_event_registration` WHERE eventName=? and `accyer_ID`=? AND `location_ID`=?");
			psmt.setString(1, pojo.getEventName());
			psmt.setString(2, pojo.getAccyear());
			psmt.setString(3, pojo.getLocationIdHidden());
			System.out.println("^^^^^^^^^^^^^^^^^^^  UPDATE ^^^^^^^^"+psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
				count=Integer.parseInt(rs.getString(1));
				System.out.println("rs.getInt(1)   ========= " + rs.getString(1));
			}
			if(count==0){
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				psmt=conn.prepareStatement(EventsSqlUtils.updateEventRegistarion);
				psmt.setString(1, pojo.getEventType());
				psmt.setString(2, pojo.getStartsOn());
				psmt.setString(3, pojo.getEndsOn());
				psmt.setString(4, pojo.getStartsRegis());
				psmt.setString(5, pojo.getEndsRegis());
				psmt.setString(6, pojo.getStatus());
				psmt.setString(7, pojo.getDescription());
				psmt.setString(8, pojo.getIshouseWise());
				psmt.setString(9, pojo.getIsAprovPps());
				psmt.setString(10, pojo.getEventName());
				psmt.setString(11, pojo.getHiddenEventId());
			
				System.out.println(psmt);
				count = psmt.executeUpdate();
				if(count>0){
					result ="UpdateTrue";
				}else{
					result="false";
					}
			
		}else{
			result="duplicateUpdate";
		}
		}
		}
		}
			
			catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : SaveEventRegistration Ending");
		return result;
	}
	
	public ArrayList<eventRegVo> getEventRegistrationList(eventRegPojo pojo)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getEventRegistrationList Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	 SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	 SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
	
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn
				.prepareStatement(EventsSqlUtils.getEventRegistrationList);
		psmt.setString(1, pojo.getAccyear());
		psmt.setString(2, pojo.getLocation());
		psmt.setString(3, "%" + pojo.getSaerchTerm() + "%");
	psmt.setString(4, "%" + pojo.getSaerchTerm() + "%");
	psmt.setString(5, "%" + pojo.getSaerchTerm() + "%");
	psmt.setString(6, "%" + pojo.getSaerchTerm() + "%");
	psmt.setString(7, "%" + pojo.getSaerchTerm() + "%");
	psmt.setString(8, "%" + pojo.getSaerchTerm() + "%");
	System.out.println("listing page++++++" + psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setAccId(rs.getString("accyer_ID"));
		vo.setLocId(rs.getString("location_ID"));
		vo.setEventId(rs.getString("event_Id"));
		vo.setLocation(rs.getString("Location_Name"));
		vo.setAccyear(rs.getString("acadamic_year"));
		vo.setEventName(rs.getString("eventName"));
		
		Date start = format1.parse(rs.getString("startsOn")); 
		vo.setStartsOn(format2.format(start));
		Date end = format1.parse(rs.getString("endsOn")); 
		vo.setEndsOn(format2.format(end));
		
		vo.setStatus(rs.getString("regStatus"));
			list.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventRegistrationList Ending");
		return list;
	}
	
	public String deleteEventRegistration(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: deleteEventRegistration Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	String result = null;
	ResultSet rs = null;
	String status=null;
	int no=0;
	int count = 0;
	try {
		conn = JDBCConnection.getSeparateConnection();
				psmt=conn.prepareStatement("SELECT COUNT(eventId) eventId FROM `campus_event_category` WHERE `eventId`=?");
				psmt.setString(1, id);
				System.out.println("deleteEventRegistration***************`campus_event_category`************"+ psmt);
				ResultSet event=psmt.executeQuery();
				while(event.next()){
					no=event.getInt("eventId");
				}
				if (no > 0) {
					status ="true";
				}
				else if(no==0){
					event.close();
					psmt.close();
					psmt=conn.prepareStatement("SELECT COUNT(eventId) eventId FROM `campus_event_stage` WHERE `eventId`=?");
					psmt.setString(1, id);
					System.out.println("deleteEventRegistration***************campus_event_stage************"+ psmt);
					ResultSet stage=psmt.executeQuery();
					while(stage.next()){
						no=stage.getInt("eventId");
					}
					if (no > 0) {
						status="true";
					}
					else if(no==0)
					{		
						stage.close();
						psmt.close();
						psmt=conn.prepareStatement("SELECT COUNT(event_id) event_id FROM `campus_event_programcreation` WHERE `event_id`=?");
						psmt.setString(1, id);
						System.out.println("deleteEventRegistration***************`campus_event_programcreation`************"+ psmt);
						ResultSet prog=psmt.executeQuery();
						while(prog.next()){
							no=prog.getInt("event_id");
						}
						if (no > 0) {
							status="true";
						}
					else if(no==0){
						prog.close();
						psmt.close();
					psmt=conn.prepareStatement("SELECT COUNT(event_id) event_id FROM `campus_event_greenroom` WHERE `event_id`=?");
					psmt.setString(1, id);
					System.out.println("deleteEventRegistration***************campus_event_greenroom************"+ psmt);
					ResultSet green=psmt.executeQuery();
					while(green.next()){
						no=green.getInt("event_id");
					}
					if (no > 0) {
						status="true";
					}
					else if(no==0){
					green.close();
					psmt.close();
					psmt=conn.prepareStatement("SELECT COUNT(Event) Event FROM `campus_event_criteria` WHERE `Event`=?");
					psmt.setString(1, id);
					System.out.println("deleteEventRegistration***************campus_event_greenroom************"+ psmt);
					ResultSet crit =psmt.executeQuery();
					while(crit.next()){
						no=crit.getInt("Event");
					}
					if (no > 0) {
						status="true";
					}
					else if(no==0){
					crit.close();
					psmt.close();
					psmt=conn.prepareStatement("SELECT COUNT(event_id) event_id FROM campus_event_studentregistration WHERE `event_id`=?");
					psmt.setString(1, id);
					System.out.println("deleteEventRegistration***************campus_event_greenroom************"+ psmt);
					ResultSet stude=psmt.executeQuery();
					while(stude.next()){
						no=stude.getInt("event_id");
					}
					if (no > 0) {
						status="true";
					}	
					else if(no==0){
						stude.close();
						psmt.close();
					psmt=conn.prepareStatement("SELECT COUNT(event_id) event_id FROM `campus_event_volunteerregistation` WHERE `event_id`=?");
					psmt.setString(1, id);
					System.out.println("deleteEventRegistration***************campus_event_greenroom************"+ psmt);
					ResultSet volun =psmt.executeQuery();
					while(volun.next()){
						no=volun.getInt("event_id");
					}
					if (no > 0) {
						status="true";
					}	
				else if(no==0){
					volun.close();
					psmt.close();
					psmt=conn.prepareStatement("DELETE FROM `campus_event_registration` WHERE `event_Id`=?");
					psmt.setString(1, id);
					no=psmt.executeUpdate();
					System.out.println("^^^^^^^^^^^ Delete ^^^^^^^^^^ event"+psmt);
					if(no >0){
						status = "false";
					}
				}
			}	
		}
	}
}
}
}
	}
catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : deleteEventRegistration Ending");
		return status;
	}
	
	public ArrayList<eventRegVo> getDataForUpdate(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getDataForUpdate Starting");
	
	Connection conn =null;
	PreparedStatement psmt= null;
	String result=null;
	ResultSet rs =null;
	int count=0;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.getDataForUpdate);
		psmt.setString(1, id);
		rs=psmt.executeQuery();
		while(rs.next()){
			eventRegVo vo = new eventRegVo();
			vo.setEventId(rs.getString("event_Id"));
		vo.setEventName(rs.getString("eventName"));
		vo.setEventType(rs.getString("eventType"));
		vo.setStartsOn(rs.getString("startsOn"));
		vo.setEndsOn(rs.getString("endsOn"));
		vo.setStartsRegis(rs.getString("startRegistation"));
		vo.setEndsRegis(rs.getString("endRegstation"));
		vo.setStatus(rs.getString("regStatus"));
		vo.setDescription(rs.getString("description"));
		vo.setIshouseWise(rs.getString("isHouseWise"));
		vo.setIsAprovPps(rs.getString("isAprvPartReg"));
		vo.setLocationIdHidden(rs.getString("location_ID"));
			list.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && !(psmt.isClosed())){psmt.close();}if(conn!=null && (conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getDataForUpdate Ending");
		return list;
	}
	
	public ArrayList<eventRegVo> getClassList() throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getClassList Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getclassList);
		System.out.println("^^^^^^^^^^^ CLASS DETAILS LIST ^^^^^"+psmt);
		rs = psmt.executeQuery();
		System.out.println(psmt);
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setClassId(rs.getString("classdetail_id_int"));
		vo.setClassName(rs.getString("classdetails_name_var"));
			list.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getClassList Ending");
		return list;
	}
	
	
	public ArrayList<eventRegVo> getClassListForCategory(String eventName) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getClassListforCategory Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement("SELECT DISTINCT `classdetail_id_int`,`classdetails_name_var` FROM `campus_classdetail` WHERE `locationId` LIKE(SELECT `location_ID` FROM `campus_event_registration` WHERE `event_Id`=?) order by LENGTH(classdetail_id_int),classdetail_id_int");
				if(eventName.equalsIgnoreCase(" ")){
					psmt.setString(1, "%%");
				}psmt.setString(1, eventName);
		System.out.println("^^^^^^^^^^^ CLASS DETAILS LIST ^^^^^"+psmt);
		rs = psmt.executeQuery();
			while (rs.next()) {
				eventRegVo vo = new eventRegVo();
				vo.setClassId(rs.getString("classdetail_id_int"));
				vo.setClassName(rs.getString("classdetails_name_var"));
				list.add(vo);
			}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && !(psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && !(conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getClassListforCategory Ending");
		return list;
	}
	
	
	
	public ArrayList<eventRegVo> getEventName() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getEventName Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> name = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getEventName);
		System.out.println(psmt);
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setEventId(rs.getString("event_Id"));
		vo.setEventName(rs.getString("eventName"));
			name.add(vo);
		}
	
	}catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventName Ending");
		return name;
	}
	
	public String saveCategory(eventRegPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: saveCategory Starting");
	Connection conn=null;
	PreparedStatement psmt =null;
	PreparedStatement psmt1 =null;
	String result =null;
	ResultSet rs1=null;
	IDGenerator key= new IDGenerator();
	String id=null;
	int count=0;
	try{
	
		id=key.getPrimaryKeyID("campus_event_category");
		conn=JDBCConnection.getSeparateConnection();
	
		if(pojo.getEventIdHidden() == null || pojo.getEventIdHidden().equalsIgnoreCase("")){
	
		psmt1=conn.prepareStatement("SELECT COUNT(`categoryName`) FROM `campus_event_category` WHERE `categoryName`=? AND eventId=?" );	
		psmt1.setString(1, pojo.getCategoryName());	
		psmt1.setString(2, pojo.getEventId());	
		rs1=psmt1.executeQuery();
		while(rs1.next()){
			count=rs1.getInt(1);
		}
		if(count==0){
			psmt=conn.prepareStatement(EventsSqlUtils.saveCategory);
			psmt.setString(1, id);
			psmt.setString(2, pojo.getCategoryName());
			psmt.setString(3, pojo.getEventId());
			psmt.setString(4, pojo.getClassList());
	
			count=psmt.executeUpdate();
			if(count>0){
				result="true";
			}else{
				result="false";
			}
	
		}
		else{
			result="dupCategoryName";
		}
	}else{
		if(pojo.getCategoryNameHidden().equalsIgnoreCase(pojo.getCategoryName())){
			psmt=conn.prepareStatement(EventsSqlUtils.updateCategory);
			psmt.setString(1, pojo.getCategoryName());
			psmt.setString(2, pojo.getClassList());
			psmt.setString(3, pojo.getCategoryHidden());
			System.out.println("update Record))))))))))))))))))))))))))))))))))))))))))))+"+psmt);
			count = psmt.executeUpdate();
	
			if(count>0){
				result ="UpdateTrue";
			}else{
				result="false";
			}
		}else{
			psmt1=conn.prepareStatement("SELECT COUNT(`categoryName`) FROM `campus_event_category` WHERE `categoryName`=?");	
			psmt1.setString(1, pojo.getCategoryName());	
			rs1=psmt1.executeQuery();
			System.out.println("Count dataa88888888888888888880"+psmt1);
			while(rs1.next()){
				count=rs1.getInt(1);
			}
			if(count==0){
				psmt=conn.prepareStatement(EventsSqlUtils.updateCategory);
				psmt.setString(1, pojo.getCategoryName());
				psmt.setString(2, pojo.getClassList());
				psmt.setString(3, pojo.getCategoryHidden());
				System.out.println("update Record))))))))))))))))))))))))))))))))))))))))))))+"+psmt);
				count = psmt.executeUpdate();
		
				if(count>0){
					result ="UpdateTrue";
				}else{
					result="false";
					}
				}
				else{
			result="dupCategoryName";
				}
			}
		}//update end
	}//try
	catch(Exception e){
		e.printStackTrace();
	}finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : saveCategory Ending");
		return result;
	}
	
			public ArrayList<eventRegVo> getPrizeCategoryName(String id) {
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in EventsDaoImpl: getPrizeCategoryName Starting");
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ArrayList<eventRegVo> categoryName = new ArrayList<eventRegVo>();
		try {
			conn = JDBCConnection.getSeparateConnection();
	
			psmt = conn.prepareStatement(EventsSqlUtils.getCategoryName);
	
			if (id.equalsIgnoreCase("")) {
				id = "%%";
			}
			psmt.setString(1, id);
			System.out.println(psmt);
			rs = psmt.executeQuery();
	
			System.out.println("category name in prize>>" + psmt);
			while (rs.next()) {
				eventRegVo vo = new eventRegVo();
				vo.setCategoryId(rs.getString("categoryId"));
				vo.setCategoryName(rs.getString("categoryName"));
				categoryName.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getPrizeCategoryName Ending");
		return categoryName;
	}
	
	public ArrayList<eventRegVo> getCategorySettingList(String eventId,String catId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getCategorySettingList Starting");
		Connection conn = null;
		PreparedStatement psmt =null;
		PreparedStatement pstmt1 = null;
		ResultSet rs,rs1 = null;
		ArrayList<eventRegVo> list=new ArrayList<eventRegVo>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement(EventsSqlUtils.getCategorySettingList);
			psmt.setString(1, eventId);
			psmt.setString(2, catId);
			System.out.println(psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
				eventRegVo vo = new eventRegVo();
				String className=null;
				String classNameList[]=new String[] {};
				List<String> classNameListVal=Arrays.asList(classNameList);
				List<String> tempList = new ArrayList<String>(classNameListVal);
	
				String[] classId=(rs.getString("classList")).split(",");
	
				for(int k=0;k<classId.length;k++){
					pstmt1=conn.prepareStatement("SELECT DISTINCT classdetails_name_var from campus_classdetail where classdetail_id_int=?");
					pstmt1.setString(1, classId[k]);
					rs1=pstmt1.executeQuery();
					while(rs1.next()){
						className=rs1.getString("classdetails_name_var");
						tempList.add(className);
					}
				}
				String[] totalClassSize=new String[tempList.size()];
				classNameList=tempList.toArray(totalClassSize);
	
				vo.setClassList(classNameList);
	
				vo.setEventId(rs.getString("event_Id"));
				vo.setEventName(rs.getString("eventName"));
				vo.setCategoryId(rs.getString("categoryId"));
				vo.setCategoryName(rs.getString("categoryName"));
	
	
				list.add(vo);
			}
	
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(psmt!=null && (!psmt.isClosed())){psmt.close();}if(conn!=null && (!conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
				}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getCategorySettingList Ending");
				return list;
			}
			
	
	public String deleteCategory(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: deleteCategory Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	String result = null;
	int no=0;
	String status=null;
	ResultSet rs=null;
	int count = 0;
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT COUNT(*) test FROM `campus_event_programcreation` WHERE `category_id`=?");
		psmt.setString(1, id);
		System.out.println("deleteEventRegistration***************`campus_event_programcreation`************"+ psmt);
		ResultSet prog=psmt.executeQuery();
		while(prog.next()){
			no=prog.getInt("test");
		}
		if (no > 0) {
			status ="false";
		}
		else if(no==0){
			psmt.close();
			
			psmt=conn.prepareStatement("SELECT COUNT(*) test FROM `campus_event_prizelevel` WHERE  `category`=? ");
			psmt.setString(1, id);
			System.out.println("deleteEventRegistration***************`campus_event_prizelevel`************"+ psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
				no=rs.getInt("test");
			}
			if (no > 0) {
				status ="false";
			}
			else if(no==0){
				psmt=conn.prepareStatement("SELECT COUNT(*) test FROM `campus_event_criteria` WHERE  `Category`=?");
				psmt.setString(1, id);
				System.out.println("deleteEventRegistration***************`campus_event_criteria`************"+ psmt);
				rs=psmt.executeQuery();
				while(rs.next()){
					no=rs.getInt("test");
				}
				if (no > 0) {
					status ="false";
				}
				else if(no==0){
					psmt=conn.prepareStatement("SELECT COUNT(*) test FROM `campus_event_studentregistration` WHERE  `category_id`=?");
					psmt.setString(1, id);
					System.out.println("deleteEventRegistration***************`campus_event_studentregistration`************"+ psmt);
					rs=psmt.executeQuery();
					while(rs.next()){
						no=rs.getInt("test");
					}
					if (no > 0) {
						status ="false";
					}
					else if(no==0){
						psmt = conn.prepareStatement("DELETE FROM `campus_event_category` WHERE categoryId =?");
						psmt.setString(1, id);
						System.out.println(psmt);
						no = psmt.executeUpdate();
						if(no >0){
							status = "true";
						}
					}
				}
			}
		}
	}
	catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : deleteCategory Ending");
		return status;
	}
	
	
	@SuppressWarnings("resource")
	public String saveCriteriaSetting(eventRegPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: saveStageSetting Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	String result = null;
	int count = 0;
	IDGenerator key = new IDGenerator();
	String id = null;
	try {
		System.out.println("inside saveCriteriaSetting daoimpl");
	id = key.getPrimaryKeyID("campus_event_criteria");
	conn = JDBCConnection.getSeparateConnection();
	System.out.println("value of hidden criteria id>>>"
			+ pojo.getHiddenCriteriaId());
	if (pojo.getHiddenCriteriaId().equalsIgnoreCase("")
			|| pojo.getHiddenCriteriaId() == null) {
		psmt = conn.prepareStatement(EventsSqlUtils.criteriaSetting);
	
		psmt.setString(1, id);
		psmt.setString(2, pojo.getEventId());
	
		psmt.setString(3, pojo.getEventName());
		psmt.setString(4, pojo.getProgrammeName());
		psmt.setString(5, pojo.getCategory());
		psmt.setString(6, pojo.getCriteria());
		psmt.setString(7, pojo.getMaxMarks());
		psmt.setString(8, pojo.getSeqNo());
	
		count = psmt.executeUpdate();
		System.out.println("insert in criteria>>>>" + psmt);
		if (count > 0) {
			result = "true";
		} else {
			result = "false";
		}
	} else {
		psmt = conn
				.prepareStatement(EventsSqlUtils.updateCriteriaSetting);
	id=key.getPrimaryKeyID("campus_event_criteria");
	conn=JDBCConnection.getSeparateConnection();
	System.out.println("value of hidden criteria id>>>"+pojo.getHiddenCriteriaId());
	if(pojo.getHiddenCriteriaId()==null || pojo.getHiddenCriteriaId().trim().equalsIgnoreCase("")){
	psmt=conn.prepareStatement(EventsSqlUtils.criteriaSetting);
	
	psmt.setString(1, id);
	psmt.setString(2, pojo.getEventName());
	psmt.setString(3, pojo.getProgrammeName());
	psmt.setString(4, pojo.getCategory());
	
	
	psmt.setString(5, pojo.getCriteria());
	psmt.setString(6, pojo.getMaxMarks());
	
			
	count=psmt.executeUpdate();
	System.out.println("insert in criteria>>>>"+psmt);
	if(count>0){
		result="true";
	}else{
		result="false";
	}
	}else{
		psmt=conn.prepareStatement(EventsSqlUtils.updateCriteriaSetting);
		psmt.setString(1, pojo.getEventName());
		psmt.setString(2, pojo.getProgrammeName());
		psmt.setString(3, pojo.getCategory());
		
	
		
		psmt.setString(4, pojo.getCriteria());
		psmt.setString(5, pojo.getMaxMarks());
		psmt.setString(6, pojo.getHiddenCriteriaId());
	
		count = psmt.executeUpdate();
		System.out.println("update query in daoImpl>>>>>>" + psmt);
		if (count > 0) {
			result = "updateTrue";
		} else {
			result = "false";
			}
	
		}}}
	 catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : saveStageSetting Ending");
		return result;
	}
	
	public ArrayList<eventRegVo> getDataForUpdateCategorySetting(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getDataForUpdateCategorySetting Starting");
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getDataForUpdateCategory);
		psmt.setString(1, id);
		System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu"
					+ psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setCategoryName(rs.getString("categoryName"));
		vo.setCategoryId(rs.getString("categoryId"));
		vo.setEventName(rs.getString("eventName"));
		vo.setEventId(rs.getString("event_Id"));
			String[] classList = (rs.getString("classList")).split(",");
		vo.setClassList(classList);
			list.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getDataForUpdateCategorySetting Ending");
		return list;
	}
	
	public String saveStageSetting(eventRegPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: saveStageSetting Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	String result = null;
	int count,count1 = 0;
	ResultSet rs = null;
	IDGenerator key = new IDGenerator();
	String id = null;
	try {
		id = key.getPrimaryKeyID("campus_event_stage");
		conn = JDBCConnection.getSeparateConnection();
		if (pojo.getHiddenStageId() == null || pojo.getHiddenStageId().equalsIgnoreCase("")) {
			
		psmt=conn.prepareStatement("SELECT COUNT(`stageName`)FROM `campus_event_stage` WHERE `eventId`=? AND `stageName`=?");
		psmt.setString(1, pojo.getEventId());
		psmt.setString(2, pojo.getStageName());
		System.out.println("66666666666^^^^^^^ count"+psmt);
		rs=psmt.executeQuery();
		while(rs.next()){
			count1=rs.getInt(1);
		}	
			
		if(count1==0){	
		psmt.close();
		psmt = conn.prepareStatement(EventsSqlUtils.stageSetting);
		psmt.setString(1, id);
		psmt.setString(2, pojo.getEventId());
		psmt.setString(3, pojo.getStageName());
		psmt.setString(4, pojo.getBuilding());
		psmt.setString(5, pojo.getFloorName());
		psmt.setString(6, pojo.getRoomNumber());
		psmt.setString(7, pojo.getInfo());
	
		count = psmt.executeUpdate();
		if (count > 0) {
			result = "true";
		} else {
			result = "false";
		}
		}else{
			result = "Duplicate";
		}
	} else {
		
		if(pojo.getStageNameHidden().equalsIgnoreCase(pojo.getStageName())){
		
		psmt = conn.prepareStatement(EventsSqlUtils.updateStageSetting);
		psmt.setString(1, pojo.getStageName());
		psmt.setString(2, pojo.getBuilding());
		psmt.setString(3, pojo.getInfo());
		psmt.setString(4, pojo.getFloorName());
		psmt.setString(5, pojo.getRoomNumber());
		psmt.setString(6, pojo.getHiddenStageId());
		System.out.println();
		count = psmt.executeUpdate(); 
		if (count > 0) {
			result = "updateTrue";
		} else {
			result = "false";
			}
		}else{
			psmt=conn.prepareStatement("SELECT COUNT(`stageName`)FROM `campus_event_stage` WHERE `eventId`=? AND `stageName`=?");
			psmt.setString(1, pojo.getEventId());
			psmt.setString(2, pojo.getStageName());
			System.out.println("66666666666^^^^^^^ count"+psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
				count1=rs.getInt(1);
			}
			if(count1==0){
				psmt = conn.prepareStatement(EventsSqlUtils.updateStageSetting);
				psmt.setString(1, pojo.getStageName());
				psmt.setString(2, pojo.getBuilding());
				psmt.setString(3, pojo.getInfo());
				psmt.setString(4, pojo.getFloorName());
				psmt.setString(5, pojo.getRoomNumber());
				psmt.setString(6, pojo.getHiddenStageId());
				System.out.println();
				count = psmt.executeUpdate();
				if (count > 0) {
					result = "updateTrue";
				} else {
					result = "false";
					}
			}else{
				result = "Duplicate";
			}
		}
		}//update end
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null || !(psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null || !(conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : saveStageSetting Ending");
		return result;
	}
	
	public ArrayList<eventRegVo> getstageList(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getstageList Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getstageList);
		psmt.setString(1, id);
		System.out.println(psmt);
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setStageId(rs.getString("stageId"));
		vo.setStageName(rs.getString("stageName"));
			list.add(vo);
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getstageList Ending");
		return list;
	}
	
	public ArrayList<eventRegVo> getstageSettingList(String eventId,
			String stageId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getstageSettingList Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	PreparedStatement pstmt1 = null;
	ResultSet rs, rs1 = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getStageSettingList);
		psmt.setString(1, eventId);
		psmt.setString(2, stageId);
		System.out.println(psmt);
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setStageId(rs.getString("stageId"));
		vo.setStageName(rs.getString("stageName"));
		vo.setEventId(rs.getString("eventId"));
		vo.setEventName(rs.getString("eventName"));
		vo.setRoomNo(rs.getString("roomNumber"));
		vo.setBuilding(rs.getString("building"));
		vo.setFloorName(rs.getString("floorName"));
			list.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getstageSettingList Ending");
	
		return list;
	}
	
	public ActionForward EventstudentRegistration(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : studentRegistration Starting");
	try{
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EVENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EVENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_EVENTS_STUDENTREGISTRATION);
			
		request.setAttribute("AccYearList", HelperClass.getAllAcademicYear());
		request.setAttribute("locationList", HelperClass.getAllLocation());
		
		ArrayList<eventRegVo> Classlist = new eventBD().getClassList();
		ArrayList<eventRegVo> name = new eventBD().getEventNameStudentReg();
		ArrayList<eventRegVo> IndivEventName = new eventBD().getEventNameStudentReg();
		
		ArrayList<eventRegVo> house = new eventBD().getHouse();
		request.setAttribute("houseList", house);
		
		request.setAttribute("classList", Classlist);
		request.setAttribute("eventList", name);
		
	}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : studentRegistration Ending");

		return mapping.findForward(MessageConstants.EventStudentReg);
	}
	
	
	
	
	
	
	
	public String deleteStage(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: deleteStage Starting");
	Connection conn =null;
	PreparedStatement psmt= null;
	String result=null;
	int count=0;
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.deleteStage);
		psmt.setString(1, id);
		count=psmt.executeUpdate();
		System.out.println("deleting stage query>>>>"+psmt);
	if(count>0){
		result="true";
	}else {
		result="false";
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && (!psmt.isClosed())){psmt.close();}if(conn!=null && (!conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : deleteStage Ending");
		return result;
	}
	
	
	public ArrayList<eventRegVo> getDataForUpdateStage(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getDataForUpdateStage Starting");
	
	Connection conn =null;
	PreparedStatement psmt= null;
	ResultSet rs =null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.getDataForUpdateStage);
		psmt.setString(1, id);
		System.out.println("upadfssssssssssssss"+psmt);
	rs=psmt.executeQuery();
	while(rs.next()){
		eventRegVo vo = new eventRegVo();
		vo.setEventId(rs.getString("eventId"));
		vo.setEventName(rs.getString("eventName"));
		vo.setStageId(rs.getString("stageId"));
		vo.setStageName(rs.getString("stageName"));
		vo.setBuilding(rs.getString("building"));
		vo.setFloorName(rs.getString("floorName"));
		vo.setRoomNo(rs.getString("roomNumber"));
		vo.setDescription(rs.getString("description"));
			list.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && (!psmt.isClosed())){psmt.close();}if(conn!=null && (!conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getDataForUpdateStage Ending");
		return list;
	}
	
	
	public String saveGreenRoom(eventRegPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: saveGreenRoom Starting");
	Connection conn=null;
	PreparedStatement psmt =null;
	PreparedStatement psmt1 = null;
	String result =null;
	ResultSet rs =null;
	int count =0;
	IDGenerator key= new IDGenerator();
	String id=null;
	try{
		id=key.getPrimaryKeyID("campus_event_greenroom");
		conn=JDBCConnection.getSeparateConnection();
		if(pojo.getGreenRoomIdHidden()==null || pojo.getGreenRoomIdHidden().equalsIgnoreCase("")){
		
		psmt1=conn.prepareStatement("SELECT COUNT(`greenroom_name`) FROM `campus_event_greenroom` WHERE event_id=? AND `greenroom_name`=?");
		psmt1.setString(1, pojo.getEventId());	
		psmt1.setString(2, pojo.getGreenRoomName());
		System.out.println("^^^^^^^^^^^^^^^^^^ coutn insert"+psmt1);
		rs=psmt1.executeQuery();
		while(rs.next()){
			count=rs.getInt(1);
			}
		if(count==0){	
		psmt=conn.prepareStatement(EventsSqlUtils.saveGreenRoom);
		psmt.setString(1, id);
		psmt.setString(2, pojo.getEventId());
		psmt.setString(3, pojo.getGreenRoomName());
		psmt.setString(4, pojo.getGreenRoomType());
		psmt.setString(5, pojo.getBuilding());
		psmt.setString(6, pojo.getFloorName());
		psmt.setString(7, pojo.getRoomNumber());
	
		count=psmt.executeUpdate();
		if(count>0){
			result="true";
		}else{
			result="false";
		}
		}else{
			result="duplicateInsert";
		}
	}else {
		if(pojo.getGreenRoomNameHidden().equalsIgnoreCase(pojo.getGreenRoomName())){
		psmt=conn.prepareStatement(EventsSqlUtils.updateGreenRoom);
		psmt.setString(1, pojo.getGreenRoomName());
		psmt.setString(2, pojo.getGreenRoomType());
		psmt.setString(3, pojo.getBuilding());
		psmt.setString(4, pojo.getFloorName());
		psmt.setString(5, pojo.getRoomNumber());
		psmt.setString(6, pojo.getGreenRoomIdHidden());
	
		count =psmt.executeUpdate();
		if(count>0){
			result="updateTrue";
		}else{
			result="false";
			}
		}else{
			psmt=conn.prepareStatement("SELECT COUNT(`greenroom_name`) FROM `campus_event_greenroom` WHERE event_id=? AND `greenroom_name`=?");
			psmt.setString(1, pojo.getEventId());	
			psmt.setString(2, pojo.getGreenRoomName());
			System.out.println("^^^^^^^^^^^^^^^^^^ coutn update"+psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
				}
			if(count==0){	
			psmt=conn.prepareStatement(EventsSqlUtils.saveGreenRoom);
			psmt.setString(1, id);
			psmt.setString(2, pojo.getEventId());
			psmt.setString(3, pojo.getGreenRoomName());
			psmt.setString(4, pojo.getGreenRoomType());
			psmt.setString(5, pojo.getBuilding());
			psmt.setString(6, pojo.getFloorName());
			psmt.setString(7, pojo.getRoomNumber());
		
			count=psmt.executeUpdate();
			if(count>0){
				result="true";
			}else{
				result="false";
			}
		}else{
			result="duplicateUpdate";
		}
		
	}
	}
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : saveGreenRoom Ending");
		return result;
	}
	
	
	
	public ArrayList<eventRegVo> getGreenRoom(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getGreenRoom Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getGreenRoom);
		psmt.setString(1, id);
		System.out.println(psmt);
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setGreenRoomName(rs.getString("greenroom_name"));
		vo.setGreenRoomId(rs.getString("greenroom_id"));
			list.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getGreenRoom Ending");
		return list;
	}
	
	public ArrayList<eventRegVo> getGreenRoomList(String eventId,
			String greenRoomId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getGreenRoomList Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	PreparedStatement pstmt1 = null;
	ResultSet rs, rs1 = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getGreenRoomList);
		psmt.setString(1, eventId);
		psmt.setString(2, greenRoomId);
		System.out.println(psmt);
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setEventName(rs.getString("eventName"));
		vo.setGreenRoomId(rs.getString("greenroom_id"));
		vo.setGreenRoomName(rs.getString("greenroom_name"));
		vo.setGreenRoomType(rs.getString("greenroom_type"));
		vo.setRoomNo(rs.getString("roomno"));
		vo.setBuilding(rs.getString("building"));
		vo.setFloorName(rs.getString("floor"));
			list.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getGreenRoomList Ending");
	
		return list;
	}
	
	public String deleteGreenRoom(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: deleteGreenRoom Starting");
	Connection conn =null;
	PreparedStatement psmt= null;
	String result=null;
	int count=0;
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.deleteGreenRoom);
		psmt.setString(1, id);
		count=psmt.executeUpdate();
		if(count>0){
			result="true";
	}else {
		result="false";
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && (!psmt.isClosed())){psmt.close();}if(conn!=null && (!conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : deleteGreenRoom Ending");
		return result;
	}
	public ArrayList<eventRegVo> getDataForUpdateGreenRoom(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getDataForUpdateGreenRoom Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	String result = null;
	ResultSet rs = null;
	int count = 0;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn
				.prepareStatement(EventsSqlUtils.getDataForUpdateGreenRoom);
		psmt.setString(1, id);
		System.out.println(psmt);
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setEventId(rs.getString("event_id"));
		vo.setEventName(rs.getString("eventName"));
		vo.setGreenRoomId(rs.getString("greenroom_id"));
		vo.setGreenRoomName(rs.getString("greenroom_name"));
		vo.setGreenRoomType(rs.getString("greenroom_type"));
		vo.setRoomNo(rs.getString("roomno"));
		vo.setBuilding(rs.getString("building"));
		vo.setFloorName(rs.getString("floor"));
			list.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getDataForUpdateGreenRoom Ending");
		return list;
	}
	
	public String saveProgram(eventRegPojo pojo, String judgeList) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: saveProgram Starting");
	Connection conn =null;
	PreparedStatement psmt= null;
	String result=null;
	ResultSet rs,rsn =null;
	int count=0;
	String id=null;
	try{
		conn=JDBCConnection.getSeparateConnection();
		
		id= IDGenerator.getPrimaryKeyID("campus_event_programcreation");
	if(pojo.getProgIdHidden()==null || pojo.getProgIdHidden().equalsIgnoreCase("")){
	
		psmt= conn.prepareStatement(EventsSqlUtils.checkDupProg);
		psmt.setString(1, pojo.getEventId());
		psmt.setString(2, pojo.getCategoryId());
		psmt.setString(3, pojo.getProgName());
		System.out.println("check duplicate program"+psmt);
		rsn=psmt.executeQuery();
		while(rsn.next()){
			count =rsn.getInt(1);
		}
		if(count==0){
			psmt.close();
			psmt=conn.prepareStatement(EventsSqlUtils.saveProgram);
			psmt.setString(1, id);
			psmt.setString(2, pojo.getEventId());
			psmt.setString(3, pojo.getCategoryId());
			psmt.setString(4, pojo.getProgName());
			psmt.setString(5, judgeList);
			psmt.setString(6, pojo.getProgType());
			psmt.setString(7, pojo.getPartiType());
			psmt.setString(8, pojo.getTermsAllowed());
			psmt.setString(9, pojo.getPartiNo());
			psmt.setString(10, pojo.getDuration());
			psmt.setString(11, pojo.getIsHouse());
			psmt.setString(12, pojo.getNoOfChildHouse());
			psmt.setString(13, pojo.getProgDate());
			psmt.setString(14, pojo.getInfoStaff());
			psmt.setString(15, pojo.getInfoGeneral());
			count=psmt.executeUpdate();
			if(count>0){
				result="true";
			}else {
				result="false";
			}
		}else{
			result="Duplicate";
		}
	}
	else{
		//here
		if(pojo.getProgramNameHidden().equalsIgnoreCase(pojo.getProgName())){
			psmt=conn.prepareStatement(EventsSqlUtils.updateProgram);
			psmt.setString(1, pojo.getProgType());
			psmt.setString(2, pojo.getPartiType());
			psmt.setString(3, pojo.getTermsAllowed());
			psmt.setString(4, pojo.getPartiNo());
			psmt.setString(5, pojo.getDuration());
			psmt.setString(6, pojo.getIsHouse());
			psmt.setString(7, pojo.getNoOfChildHouse());
			psmt.setString(8, pojo.getProgDate());
			psmt.setString(9, pojo.getInfoStaff());
			psmt.setString(10, pojo.getInfoGeneral());
			psmt.setString(11, pojo.getProgName());
			psmt.setString(13, pojo.getProgIdHidden());
			psmt.setString(12,	pojo.getJudgeList());
			System.out.println("update program00000000000000000"+psmt);
			count=psmt.executeUpdate();
			if(count>0){
				result="updateTrue";
			}	else{
				result="false";
			}
		}else{
			
		psmt= conn.prepareStatement(EventsSqlUtils.checkDupProg);
		psmt.setString(1, pojo.getEventIdHidden());
		psmt.setString(2, pojo.getCategoryIdHidden());
		psmt.setString(3, pojo.getProgName());
		System.out.println("check duplicate UPDATE program updateDuplicate ^^^^^^^^^^^^^^^^^^" +psmt);
		rsn=psmt.executeQuery();
		while(rsn.next()){
			count =rsn.getInt(1);
		}
		if(count==0){
			psmt=conn.prepareStatement(EventsSqlUtils.updateProgram);
			psmt.setString(1, pojo.getProgType());
			psmt.setString(2, pojo.getPartiType());
			psmt.setString(3, pojo.getTermsAllowed());
			psmt.setString(4, pojo.getPartiNo());
			psmt.setString(5, pojo.getDuration());
			psmt.setString(6, pojo.getIsHouse());
			psmt.setString(7, pojo.getNoOfChildHouse());
			psmt.setString(8, pojo.getProgDate());
			psmt.setString(9, pojo.getInfoStaff());
			psmt.setString(10, pojo.getInfoGeneral());
			psmt.setString(11, pojo.getProgName());
			psmt.setString(13, pojo.getProgIdHidden());
			psmt.setString(12,	pojo.getJudgeList());
			System.out.println("update program00000000000000000"+psmt);
			count=psmt.executeUpdate();
			if(count>0){
				result="updateTrue";
			}	else{
				result="false";
			}
			}else{
				result="updateDuplicate";
			}
		}
	}//end if-else
	}//end Try
	catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && (!psmt.isClosed())){psmt.close();}if(conn!=null && (!conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : saveProgram Ending");
			return result;
		}
	
	public ArrayList<eventRegVo> getProgramName() {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in EventsDaoImpl: getProgramName Starting");
	Connection conn=null;
	PreparedStatement psmt=null;
	ResultSet rs =null;
	ArrayList<eventRegVo> name = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.getProgramName);
	
		rs=psmt.executeQuery();
		System.out.println("getting program list>>>>"+psmt);
	while(rs.next()){
		eventRegVo vo = new eventRegVo();
		vo.setProgrammeId(rs.getString("program_id"));
		vo.setProgrammeName(rs.getString("program_name"));
			name.add(vo);
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && (!psmt.isClosed())){psmt.close();}if(conn!=null && (!conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getProgramName Ending");
		return name;
	}
	
	public ArrayList<eventRegVo> getprogramNameReg(String evId, String catId, String group, String proType) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getprogramName Starting");
	Connection conn =null;
	PreparedStatement psmt= null;
	ResultSet rs =null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		if(group.equalsIgnoreCase("GroupS")){
		psmt=conn.prepareStatement(EventsSqlUtils.getProgNameForStdReg_Group);
	}
	else if(group.equalsIgnoreCase("Indiv")){
		psmt=conn.prepareStatement(EventsSqlUtils.getProgNameForStdReg_Indiv);
	}
	else if(proType!=null){
		psmt=conn.prepareStatement("SELECT `program_name`,`program_id`,`isHouseWise` FROM `campus_event_programcreation` WHERE `event_id`like ? AND category_id like ? AND programType=?");
		psmt.setString(3, proType);
	}
	else{
		psmt=conn.prepareStatement(EventsSqlUtils.getprogramName);
	}
	if(evId.equalsIgnoreCase("")){
		evId="%%";
	}psmt.setString(1, evId);
	if(catId.equalsIgnoreCase("")){
		catId="%%";
	}psmt.setString(2, catId);
	System.out.println("getprogramName** :::::::::::: ::::::"+psmt);
	rs=psmt.executeQuery();
	while(rs.next()){
		eventRegVo vo = new eventRegVo();
		vo.setProgId(rs.getString("program_id"));
		vo.setProgName(rs.getString("program_name"));
		vo.setIshouseWise(rs.getString("isHouseWise"));
			list.add(vo);
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && (!psmt.isClosed())){psmt.close();}if(conn!=null && (!conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getprogramName Ending");
			return list;
		}
	
	
	public ArrayList<eventRegVo> getCategoryList(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getstageList Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getCategoryList);
		psmt.setString(1, id);
		System.out.println("get category list>>>>" + psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
	
		vo.setCategory(rs.getString("Category"));
	System.out
			.println("getting category value in category list>>>>"
					+ rs.getString("Category"));
			list.add(vo);
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getstageList Ending");
		return list;
	}
	public ArrayList<eventRegVo> getprogramName(String evId, String catId,
			String group) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getprogramName Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		if (group.equalsIgnoreCase("GroupS")) {
		psmt = conn.prepareStatement(EventsSqlUtils.getProgNameForStdReg_Group);
	} else if (group.equalsIgnoreCase("Indiv")) {
		psmt = conn.prepareStatement(EventsSqlUtils.getProgNameForStdReg_Indiv);
	} else {
		psmt = conn.prepareStatement(EventsSqlUtils.getprogramName);
	}
	if (evId.equalsIgnoreCase("")) {
	evId = "%%";
	}
	psmt.setString(1, evId);
	if (catId.equalsIgnoreCase("")) {
	catId = "%%";
	}
	psmt.setString(2, catId);
	System.out.println("getprogramName** :::::getProgNameForStdReg_Group::::^^^^^^^^^^^^^^^^^^^^^^^::: ::::::" + psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setProgId(rs.getString("program_id"));
		vo.setProgName(rs.getString("program_name"));
		vo.setIshouseWise(rs.getString("isHouseWise"));
			list.add(vo);
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getprogramName Ending");
		return list;
	}
	
	
public ArrayList<eventRegVo> getProgramSettingList(String evId,String catId, String progId) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl: getProgramSettingList Starting");
	Connection conn =null;
	PreparedStatement psmt= null;
	String result=null;
	ResultSet rs =null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.getProgramSettingList);
		psmt.setString(1, progId);
		psmt.setString(2, evId);
		psmt.setString(3, catId);
		System.out.println(psmt);
		rs=psmt.executeQuery();
		while(rs.next()){
			eventRegVo vo = new eventRegVo();
			vo.setEventName(rs.getString("eventName"));
			vo.setCategoryName(rs.getString("categoryName"));
			vo.setProgrammeName(rs.getString("program_name"));
			vo.setProgId(rs.getString("program_id"));
			vo.setProgType(rs.getString("programType"));
			vo.setPartiType(rs.getString("participantsType"));
			vo.setTermsAllowed(rs.getString("teamsAllowed"));
			vo.setNoOfChildHouse(rs.getString("noOfHouseChildren"));
			vo.setDuration(rs.getString("duration"));
			vo.setPartiNo(rs.getString("noOfparticipants"));
			vo.setProgDate(rs.getString("programDate"));
			vo.setIsHouse(rs.getString("isHouseWise"));
			list.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	}

	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getProgramSettingList Ending");
				return list;
			}
	
	
	public String deleteProgram(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: deleteProgram Starting");
	Connection conn =null;
	PreparedStatement psmt= null;
	String result=null;
	int count=0;
	ResultSet rs=null;
	String status=null;
	int no=0;
	try{
		conn = JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT COUNT(*) test FROM `campus_event_prizelevel` WHERE `program_name`=?");
		psmt.setString(1, id);
		System.out.println("deleteEventRegistration***************`campus_event_prizelevel`************"+ psmt);
		ResultSet prog=psmt.executeQuery();
		while(prog.next()){
			no=prog.getInt("test");
		}
		if (no > 0) {
			status ="false";
		}
		else if(no==0){
			psmt=conn.prepareStatement("SELECT COUNT(*) test FROM `campus_event_criteria` WHERE `Programme_Name`=?");
			psmt.setString(1, id);
			System.out.println("deleteEventRegistration***************`campus_event_criteria`************"+ psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
				no=rs.getInt("test");
			}
			if (no > 0) {
				status ="false";
			}
			else if(no==0){
				psmt=conn.prepareStatement("SELECT COUNT(*) test FROM `campus_event_studentregistration` WHERE `program_id`=?");
				psmt.setString(1, id);
				System.out.println("deleteEventRegistration***************`campus_event_studentregistration`************"+ psmt);
				rs=psmt.executeQuery();
				while(rs.next()){
					no=rs.getInt("test");
				}
				if (no > 0) {
					status ="false";
				}
				else if(no==0){
					psmt=conn.prepareStatement(EventsSqlUtils.deleteProgram);
					psmt.setString(1, id);
					count=psmt.executeUpdate();
					if(count>0){
						status="true";
				}else {
					status="false";
					}
					
				}
			}
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || !(psmt.isClosed())){psmt.close();}if(conn!=null || !(conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : deleteProgram Ending");
		return status;
	}
	
public ArrayList<eventRegVo> getDataforUpdateProgram(String id) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl: getDataforUpdateProgram Starting");
	
	Connection conn =null;
	PreparedStatement psmt= null;
	ResultSet rs =null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.getDataforUpdateProgram);
		psmt.setString(1, id);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+psmt);
		rs=psmt.executeQuery();
		while(rs.next()){
			eventRegVo vo = new eventRegVo();
			vo.setEventId(rs.getString("event_Id"));
			vo.setProgId(rs.getString("program_id"));
			vo.setCategoryId(rs.getString("category_id"));
			vo.setProgName(rs.getString("program_name"));
			vo.setProgType(rs.getString("programType"));
			vo.setPartiType(rs.getString("participantsType"));
			vo.setTermsAllowed(rs.getString("teamsAllowed"));
			vo.setNoOfChildHouse(rs.getString("noOfHouseChildren"));
			vo.setPartiNo(rs.getString("noOfparticipants"));
			vo.setDuration(rs.getString("duration"));
			vo.setIsHouse(rs.getString("isHouseWise"));
			vo.setProgDate(rs.getString("programDate"));
			vo.setInfoGeneral(rs.getString("info_general"));
			vo.setInfoStaff(rs.getString("info_staff"));
			vo.setJudgeList(rs.getString("noOfJudge").split(","));
			list.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && (!psmt.isClosed())){psmt.close();}if(conn!=null && (!conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getDataforUpdateProgram Ending");
		return list;
	}
	
			public ArrayList<eventRegVo> getCriteriaSettingList(String eventId,
					String programmeList, String categoryList) {
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in EventsDaoImpl: getstageSettingList Starting");
			Connection conn = null;
			PreparedStatement psmt = null;
			PreparedStatement pstmt1 = null;
			ResultSet rs, rs1 = null;
			ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
			
			try {
				conn = JDBCConnection.getSeparateConnection();
				psmt = conn.prepareStatement("SELECT cat.`categoryId`,cat.`categoryName`,reg.eventName,reg.event_Id,pgrm.program_id,pgrm.program_name,pgrm.programDate FROM campus_event_registration reg  JOIN campus_event_programcreation pgrm ON pgrm.event_id=reg.event_Id JOIN `campus_event_category` cat ON cat.`categoryId`=pgrm.`category_id`  WHERE reg.event_Id LIKE ? AND pgrm.`category_id` LIKE ? AND pgrm.program_id LIKE ?  ORDER BY pgrm.programDate,reg.eventName,pgrm.program_name");
				psmt.setString(1, eventId);
				psmt.setString(2,categoryList );
				psmt.setString(3, programmeList);
			
				rs = psmt.executeQuery();
				System.out.println("already list for criteria.."+psmt);
				while (rs.next()) {
					eventRegVo vo = new eventRegVo();
					
					vo.setEventName(rs.getString("eventName"));
					vo.setEventId(rs.getString("event_Id"));
					vo.setProgrammeName(rs.getString("program_name"));
					vo.setProgId(rs.getString("program_id"));
					vo.setProg_date(rs.getString("programDate"));
					vo.setCategoryId(rs.getString("categoryId"));
					vo.setCategory(rs.getString("categoryName"));
					
			
					PreparedStatement reportpstmt = conn.prepareStatement("SELECT Criteria_Id,`Criteria`,Max_marks  FROM `campus_event_criteria` WHERE Event =?  AND `Category`=?  AND Programme_Name=? ");
			
					reportpstmt.setString(1, rs.getString("event_Id"));
					reportpstmt.setString(2, rs.getString("categoryId"));
					
					reportpstmt.setString(3, rs.getString("program_id"));
					
					ResultSet reportrs = reportpstmt.executeQuery();
					
					if (reportrs.next()) {
						vo.setCriteria(reportrs.getString("Criteria"));
						vo.setCriteriaId(reportrs.getString("Criteria_Id"));
			
						vo.setMaxMarks(reportrs.getString("Max_marks"));
						
						String criterialist[]=reportrs.getString("Criteria").split(",");
						vo.setCriterialength(criterialist.length);
			
			
			
					} else {
						vo.setCriteria("-");
						vo.setCriteriaId(" ");
						vo.setMaxMarks("-");
					}
			
					list.add(vo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (psmt != null && (!psmt.isClosed())) {
						psmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in EventsDaoImpl : getstageSettingList Ending");
			
				return list;
			}
	

	public ArrayList<eventRegVo> getCategory() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getEventName Starting");
	Connection conn=null;
	PreparedStatement psmt=null;
	ResultSet rs =null;
	ArrayList<eventRegVo> name = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.getCategory);
		System.out.println("getCategory query>>>>>"+psmt);
		rs=psmt.executeQuery();
		while(rs.next()){
			eventRegVo vo = new eventRegVo();
			vo.setCategoryId(rs.getString("categoryId"));
			vo.setCategoryName(rs.getString("categoryName"));
			name.add(vo);
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && (!psmt.isClosed())){psmt.close();}if(conn!=null && (!conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventName Ending");
			return name;
		}
	
	public String deleteCriteria(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: deleteStage Starting");
	Connection conn =null;
	PreparedStatement psmt= null;
	String result=null;
	int count=0;
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.deleteCriteria);
		psmt.setString(1, id);
		count=psmt.executeUpdate();
		System.out.println("delete criteria query>>>>"+psmt);
	if(count>0){
		result="true";
	}else {
		result="false";
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && (!psmt.isClosed())){psmt.close();}if(conn!=null && (!conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : deleteStage Ending");
		return result;
	}
	
	public ArrayList<eventRegVo> getDataForUpdateCriteria(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getDataForUpdateCriteria Starting");
	
	Connection conn =null;
	PreparedStatement psmt= null;
	ResultSet rs =null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try{
	
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.getDataForUpdateCriteria);
		psmt.setString(1, id);
		System.out.println("update criteria query>>>>"+psmt);
	rs=psmt.executeQuery();
	while(rs.next()){
		eventRegVo vo = new eventRegVo();
		vo.setEventId(rs.getString("Event_id"));
		vo.setEventName(rs.getString("Event_name"));
		vo.setProgrammeName(rs.getString("Programme_Name"));
		System.out.println("programmename >>>>"+rs.getString("Programme_Name"));
		vo.setCategoryName(rs.getString("Category"));
		vo.setCriteria(rs.getString("Criteria"));
		vo.setMaxMarks(rs.getString("Max_marks"));
		vo.setSeqNo(rs.getString("Seq_No"));
	
			list.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && (!psmt.isClosed())){psmt.close();}if(conn!=null && (!conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getDataForUpdateCriteria Ending");
		return list;
	}
	
	
	
	public ArrayList<eventRegVo> getProgrammeName(String id, String event_id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getProgrammeName Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> categoryName = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getProgrammeName);
		psmt.setString(1, id);
		psmt.setString(2, event_id);
		System.out.println(psmt);
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setProgrammeId(rs.getString("program_id"));
		vo.setProgrammeName(rs.getString("program_name"));
			categoryName.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getProgrammeName Ending");
		return categoryName;
	}
	
	
	public ArrayList<eventRegVo> getCriteriaList(String id,String event_id,String cat_id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getCriteriaList Starting");
	Connection conn= null;
	PreparedStatement psmt =null;
	ResultSet rs =null;
	ArrayList<eventRegVo> categoryName = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.getCriteriaList);
		psmt.setString(1, id);
		psmt.setString(2, event_id);
		psmt.setString(3, cat_id);
		System.out.println(psmt);
		rs=psmt.executeQuery();
		while(rs.next()){
			eventRegVo vo = new eventRegVo();
			vo.setCriteriaId(rs.getString("Criteria_Id"));
		vo.setCriteria(rs.getString("Criteria"));
			categoryName.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || !(psmt.isClosed())){
				psmt.close();
			}
			if(conn!=null || !(conn.isClosed()))
			{conn.close();
			}
		}catch(Exception e){e.printStackTrace();
	
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getCriteriaList Ending");
		return categoryName;
	}
	
	
	public String validateCriteria(String eventName,
			String programmeName, String category, String criteria) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationMasterDAOImpl : validateLocName : Starting");
	
	PreparedStatement sub_pstmt = null;
	ResultSet sub_rs = null;
	
	String list =null;
	int count = 0;
	
	Connection conn = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
	
		sub_pstmt = conn
				.prepareStatement(SQLUtilConstants.VALIDATE_Criteria);
	
		//select count(*) subject from laboratory_details where Class_Name =? and Lab_Name =? and School_Name=? and Specialization=?
	
	sub_pstmt.setString(1, eventName);
	sub_pstmt.setString(2, programmeName );
	sub_pstmt.setString(3, category );
	sub_pstmt.setString(4, criteria);
	System.out.println("Criteria  name validating query....:" +sub_pstmt);
	sub_rs = sub_pstmt.executeQuery();
	
	while (sub_rs.next()) {
	
		count = sub_rs.getInt(1);
	
	}
	
	if (count > 0) {
	
		list ="true";
	
	} else {
	
		list ="false";
		}
	
	} catch (SQLException sqle) {
		logger.error(sqle.getMessage(), sqle);
		logger.error(sqle);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
	
		try {
			if (sub_rs != null && (!sub_rs.isClosed())) {
	
				sub_rs.close();
			}
			if (sub_pstmt != null && (!sub_pstmt.isClosed())) {
	
				sub_pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
	
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
	
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in DepartmentMasterDAOImpl : validateDeptName : Ending");
	
		return list;
	}
	
	public ArrayList<eventRegVo> getHouse() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getHouse Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> name = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getHouse);
		System.out.println(psmt);
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setHouseId(rs.getString("house_id"));
		vo.setHouseName(rs.getString("housename"));
			name.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getHouse Ending");
		return name;
	}
	
public String saveEventStudentReg(eventRegPojo pojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl: saveStudentReg Starting");
	Connection conn=null;
	PreparedStatement psmt,psmt5= null;
	PreparedStatement psmt2,psmt6,psmt3,psmt4= null;
	String result =null;
	ResultSet rs1;
	ResultSet rs3;
	int rs2 = 0;
	String key=null;
	int count =0;
	int count2 =0;
	int number = 0;
	IDGenerator id = new IDGenerator();
	try{
		conn=JDBCConnection.getSeparateConnection();
	if(pojo.getProgramCaptainHidden()==null || pojo.getProgramCaptainHidden().equalsIgnoreCase("")){
		//conn.setAutoCommit(false);
		psmt5=conn.prepareStatement("SELECT `reg_no`  FROM `campus_event_regno`");
		rs1=psmt5.executeQuery();
		while(rs1.next()){
			number = rs1.getInt(1);
		}
		number++;
		String regNo = pojo.getGrpSubstr()+number;
		System.out.println("pojo.getGrpSubstr()"+pojo.getGrpSubstr());
		System.out.println("REG No"+regNo);
		
	//checking duplicate data				
	psmt3=conn.prepareStatement("SELECT COUNT(`captain_admissionNo`) FROM `campus_event_studentregistration` WHERE `event_id`=? AND `category_id`=? AND `program_id`=? AND `captain_admissionNo`=?");
	psmt3.setString(1, pojo.getEventId());
	psmt3.setString(2, pojo.getCategoryId());
	psmt3.setString(3, pojo.getProgId());
	psmt3.setString(4, pojo.getProgramCaptain());
	System.out.println("counting psmt3 6666666666666^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+psmt3);
	rs3 = psmt3.executeQuery();
	while(rs3.next()){
		count2=rs3.getInt(1);
	}
	if(count2==0){//no duplicate data
		key=id.getPrimaryKeyID("campus_event_studentregistration");
		psmt=conn.prepareStatement(EventsSqlUtils.saveEventStudentReg);
		psmt.setString(1, key);
		psmt.setString(2, pojo.getEventId());
		psmt.setString(3, pojo.getCategoryId());
		psmt.setString(4, pojo.getProgId());
		psmt.setString(5, pojo.getHouseId());
		psmt.setString(6, pojo.getProgramCaptain());
		psmt.setString(7, pojo.getParticipantsName());
		psmt.setString(8, pojo.getInfo_staff());
		psmt.setString(9, pojo.getInfo_synopsis());
		psmt.setString(10, pojo.getInfo_req());
		psmt.setString(11, pojo.getParticipationType());
		psmt.setString(12, regNo);
		psmt.setString(13, pojo.getAccId());
		System.out.println("saveEventStudentReg"+psmt);
		count =psmt.executeUpdate();
		System.out.println("saveEventStudentReg ::::::::: "+count);
			if(count>0){
				result ="true";
				psmt6=conn.prepareStatement("UPDATE  `campus_event_regno` SET `reg_no`=?");
				psmt6.setInt(1, number);
				System.out.println("insert into =="+psmt6);
				count2 =psmt6.executeUpdate();
				if(count2>0){
					result ="true";
					//conn.commit();
				}
				result ="true";
		}else{
			result="false";
					}
			}else{
					result="falseif";
				}
		}
/*	else{//duplicate data
	result="duplicateRecord";
	}
	
			}*/
	else{
				
				if(pojo.getProgCaptainHidden().equalsIgnoreCase(pojo.getCaptainCompare())){

				psmt2=conn.prepareStatement("UPDATE `campus_event_studentregistration` SET `captain_admissionNo`=?,`participants_admissionNo`=?,`info_staff`=?,`info_synopsys`=?,`info_req`=? WHERE `registration_id`=?");
				System.out.println("dfgdsfdsfdsfdsssssssssss-------------------s"+pojo.getProgramCaptain());
			
				psmt2.setString(1, pojo.getProgramCaptain());
				psmt2.setString(2, pojo.getParticipantsName());
				psmt2.setString(3, pojo.getInfo_staff());
				psmt2.setString(4, pojo.getInfo_synopsis());
				psmt2.setString(5, pojo.getInfo_req());
				psmt2.setString(6, pojo.getRegistrationId());
				count2 =psmt2.executeUpdate();
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^count2"+count2);
				System.out.println("update updateEventStudentReg in daoImpl>>>>>>"+psmt2);
				if(count2>0){
					result="updateTrue";
				}else{
					result="falseupdate";
				}
				//}
					/*	else{
							result="alreadyC";
						}*/
						}else{
							psmt3=conn.prepareStatement("SELECT COUNT(`captain_admissionNo`) FROM `campus_event_studentregistration` WHERE `event_id`=? AND `category_id`=? AND `program_id`=? AND `captain_admissionNo`=?");
							psmt3.setString(1, pojo.getEventIdHidden());
							psmt3.setString(2, pojo.getCategoryHidden());
							psmt3.setString(3, pojo.getProgramIdHidden());
							psmt3.setString(4, pojo.getProgramCaptain());
							System.out.println("counting psmt3 6666666666666^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+psmt3);
							rs3 = psmt3.executeQuery();
							while(rs3.next()){
								count2=rs3.getInt(1);
							}
							if(count2==0){
								psmt2=conn.prepareStatement(EventsSqlUtils.updateEventStudentReg);
								System.out.println("dfgdsfdsfdsfdsssssssssss-------------------s"+pojo.getProgramCaptain());
								psmt2.setString(1, pojo.getHouseId());
								psmt2.setString(2, pojo.getProgramCaptain());
								psmt2.setString(3, pojo.getParticipantsName());
								psmt2.setString(4, pojo.getInfo_staff());
								psmt2.setString(5, pojo.getInfo_synopsis());
								psmt2.setString(6, pojo.getInfo_req());
								psmt2.setString(7, pojo.getRegistrationId());
								count2 =psmt2.executeUpdate();
										
								System.out.println("update updateEventStudentReg in daoImpl>>>>>>"+psmt2);
								if(count2>0){
									result="updateTrue";
								}else{
									result="false";
								}
							
						}
							else{
								result="duplicateUpdate";
							}
					}
		}
		// }
	}
		/*	else{
				 result="duplicateRecord"; 
			  }*/
			
			
				//}//end of try
				catch(Exception e){
					e.printStackTrace();
				}
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.END_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in EventsDaoImpl : saveStudentReg Ending");
				return result;
			
			}
			
			public ArrayList<eventRegVo> getStudentRegList(eventRegPojo pojo) {
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in EventsDaoImpl: getStudentRegList Starting");
		Connection conn=null;
		PreparedStatement psmt= null;
		String result =null;
		String key=null;
		try{
			conn=JDBCConnection.getSeparateConnection();
			//psmt=conn.prepareStatement(EventsSqlUtils.getStudentRegList);
	
		}catch(Exception e){
			e.printStackTrace();
		}
		/*finally{
			try{
				if(psmt!=null || psmt.isClosed()){psmt.close();}if(conn!=null || conn.isClosed()){conn.close();}}catch(Exception e){e.printStackTrace();
			}
		}*/
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getStudentRegList Ending");
			return null;
		}
	
	public String savePrizelevelSetting(eventRegPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: saveStageSetting Starting");
	Connection conn=null;
	PreparedStatement psmt =null;
	String result =null;
	int count =0;
	IDGenerator key= new IDGenerator();
	String id=null;
	try{
		//Criteria_Id,`Event_id`,`Event_name`,`Programme_Name`,`Category`,`Criteria`,`Max_marks`,`Seq_No`
		System.out.println("inside savePrizelevelSetting daoimpl");
		id=key.getPrimaryKeyID("campus_event_prizelevel");
		conn=JDBCConnection.getSeparateConnection();
		System.out.println("value of hidden criteria id>>>"+pojo.getHiddenCriteriaId());
		if(pojo.getHiddenCriteriaId().equalsIgnoreCase("") ||pojo.getHiddenCriteriaId()==null){
			psmt=conn.prepareStatement(EventsSqlUtils.prizeSetting);
	
			psmt.setString(1, id);
	
	
			psmt.setString(2, pojo.getEventName());
			System.out.println("getting Eventname>>>>>>"+ pojo.getEventName());
			psmt.setString(3, pojo.getCategory());
			psmt.setString(4, pojo.getProgrammeName());
			psmt.setString(5, pojo.getSeqNo());
			psmt.setString(6, pojo.getPrizeLevel());
			psmt.setString(7, pojo.getPoints());
			psmt.setString(8, pojo.getDescription());
	
			System.out.println("value of points<<<<"+pojo.getPoints());
	
			count=psmt.executeUpdate();
			System.out.println("insert in prize>>>>"+psmt);
			if(count>0){
				result="true";
			}else{
				result="false";
			}
		}else{
			psmt=conn.prepareStatement(EventsSqlUtils.updatePrizeSetting);
	
			psmt.setString(1, pojo.getEventName());
			psmt.setString(2, pojo.getProgrammeName());
			psmt.setString(3, pojo.getCategory());
			psmt.setString(4, pojo.getSeqNo());
			psmt.setString(5, pojo.getPrizeLevel());
			psmt.setString(6, pojo.getPoints());
			psmt.setString(7, pojo.getDescription());
			psmt.setString(8, pojo.getHiddenCriteriaId());
	
			count =psmt.executeUpdate();
			System.out.println("update query in daoImpl>>>>>>"+psmt);
			if(count>0){
				result="updateTrue";
			}else{
				result="false";
			}
	
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && (!psmt.isClosed())){psmt.close();}if(conn!=null && (!conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : saveStageSetting Ending");
		return result;
	}
	
	public ArrayList<eventRegVo> getPrizeSettingList(String eventId,
			String programmeList, String categoryList) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getPrizeSettingList Starting");
	Connection conn = null;
	PreparedStatement psmt =null;
	PreparedStatement pstmt1 = null;
	ResultSet rs,rs1 = null;
	ArrayList<eventRegVo> list=new ArrayList<eventRegVo>();
	System.out.println("inside getPrizeSettingList DaoImpl>>>>>");
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.getPrizeSettingList);
		psmt.setString(1, eventId);
		psmt.setString(2,categoryList );
		psmt.setString(3, programmeList);
		
	
		rs=psmt.executeQuery();
		System.out.println("get single data for update in prize setting>>>>"+psmt);
		while(rs.next()){
			eventRegVo vo = new eventRegVo();
			vo.setPrize_id(rs.getString("prize_id"));
			vo.setEventName(rs.getString("eventName"));
			vo.setProgrammeName(rs.getString("program_name"));
			vo.setCategory(rs.getString("categoryName"));
			vo.setPrizeLevel(rs.getString("prize_level"));
			vo.setPoints(rs.getString("points"));
			System.out.println("value of points>>>"+rs.getString("points"));
			vo.setDescription(rs.getString("description"));
			vo.setSeqNo(rs.getString("seq_no"));
			list.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && (!psmt.isClosed())){psmt.close();}if(conn!=null && (!conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT); 
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getstageSettingList Ending");
	
		return list;
	}
	
	public ArrayList<eventRegVo> getDataForUpdatePrize(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getDataForUpdatePrize Starting");
	
	Connection conn =null;
	PreparedStatement psmt= null;
	ResultSet rs =null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try{
	
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.getDataForUpdatePrize);
		psmt.setString(1, id);
		System.out.println("update prize query>>>>"+psmt);
		rs=psmt.executeQuery();
		while(rs.next()){
			eventRegVo vo = new eventRegVo();
	
			vo.setEventName(rs.getString("event_name"));
			vo.setProgrammeName(rs.getString("program_name"));
			vo.setCategoryName(rs.getString("category"));
			vo.setPrizeLevel(rs.getString("prize_level"));
			vo.setPoints(rs.getString("points"));
			vo.setSeqNo(rs.getString("seq_no"));
			vo.setDescription(rs.getString("description"));
			list.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && (!psmt.isClosed())){psmt.close();}if(conn!=null && (!conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getDataForUpdatePrize Ending");
			return list;
		}
	
	
	public String deletePrize(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: deletePrize Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	String result = null;
	int count = 0;
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.deletePrize);
		psmt.setString(1, id);
		count = psmt.executeUpdate();
		System.out.println(" deletePrize query>>>>" + psmt);
	if (count > 0) {
		result = "true";
	} else {
		result = "false";
		}
	
	}catch(Exception e){
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : deletePrize Ending");
		return result;
	}
	
	public ArrayList<eventRegVo> getPrizeList(String id, String event_id,
			String cat_id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getPrizeList Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> prize = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getPrizeList);
		psmt.setString(1, id);
		psmt.setString(2, event_id);
		psmt.setString(3, cat_id);
		System.out.println(psmt);
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setPrize_id(rs.getString("prize_id"));
		vo.setPrizeLevel(rs.getString("prize_level"));
			prize.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getPrizeList Ending");
		return prize;
	}
	
	public ArrayList<eventRegVo> getSeqList(String id, String event_id,
			String cat_id, String prog_id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getPrizeList Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> prize = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getSeqList);
		psmt.setString(1, id);
		psmt.setString(2, prog_id);
		psmt.setString(3, event_id);
		psmt.setString(4, cat_id);
	
		System.out.println(psmt);
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setPrize_id(rs.getString("prize_id"));
		vo.setSeqNo(rs.getString("seq_no"));
			prize.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getPrizeList Ending");
		return prize;
	}
	
	public ArrayList<eventRegVo> getPointsList(String id, String event_id,
			String cat_id, String prog_id, String prize_id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getPointsList Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> prize = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getPointsList);
		psmt.setString(1, id);
		psmt.setString(2, prize_id);
		psmt.setString(3, prog_id);
		psmt.setString(4, event_id);
		psmt.setString(5, cat_id);
	
		System.out.println(psmt);
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
	
			vo.setPoints(rs.getString("points"));
			prize.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getPointsList Ending");
		return prize;
	}
	
	public String validateSeq(String eventName, String programmeName,
			String category, String seqNo, String prizelevel, String points) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : validateSeq : Starting");
	
	PreparedStatement sub_pstmt = null;
	ResultSet sub_rs = null;
	
	String list =null;
	int count = 0;
	
	Connection conn = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
	
		sub_pstmt = conn
				.prepareStatement(SQLUtilConstants.VALIDATE_Seq);
	
		//select count(*) subject from laboratory_details where Class_Name =? and Lab_Name =? and School_Name=? and Specialization=?
	
	sub_pstmt.setString(1, eventName);
	sub_pstmt.setString(2, programmeName );
	sub_pstmt.setString(3, category );
	sub_pstmt.setString(4, seqNo);
	sub_pstmt.setString(5, prizelevel);
	sub_pstmt.setString(6, points);
	
	System.out.println("seqNo  name validating query....:" +sub_pstmt);
	sub_rs = sub_pstmt.executeQuery();
	
	while (sub_rs.next()) {
	
		count = sub_rs.getInt(1);
	
	}
	
	if (count > 0) {
	
		list ="true";
	
	} else if(count==0){
	
		list ="false";
		}
	
	} catch (SQLException sqle) {
		logger.error(sqle.getMessage(), sqle);
		logger.error(sqle);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
	
		try {
			if (sub_rs != null && (!sub_rs.isClosed())) {
	
				sub_rs.close();
			}
			if (sub_pstmt != null && (!sub_pstmt.isClosed())) {
	
				sub_pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
	
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
	
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : validateSeq : Ending");
	
		return list;
	}
	
	public String validatePrize(String eventName, String programmeName,
			String category, String seqNo, String prizelevel, String points) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : validatePrize : Starting");
	
	PreparedStatement sub_pstmt = null;
	ResultSet sub_rs = null;
	
	String list =null;
	int count = 0;
	
	Connection conn = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
	
		sub_pstmt = conn
				.prepareStatement(SQLUtilConstants.VALIDATE_Prize);
	
		//select count(*) subject from laboratory_details where Class_Name =? and Lab_Name =? and School_Name=? and Specialization=?
	
	sub_pstmt.setString(1, eventName);
	sub_pstmt.setString(2, programmeName );
	sub_pstmt.setString(3, category );
	sub_pstmt.setString(4, seqNo);
	sub_pstmt.setString(5, prizelevel);
	sub_pstmt.setString(6, points);
	
	System.out.println("seqNo  name validating query....:" +sub_pstmt);
	sub_rs = sub_pstmt.executeQuery();
	
	while (sub_rs.next()) {
	
		count = sub_rs.getInt(1);
	
	}
	
	if (count > 0) {
	
		list ="true";
	
	} else {
	
		list ="false";
		}
	
	} catch (SQLException sqle) {
		logger.error(sqle.getMessage(), sqle);
		logger.error(sqle);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
	
		try {
			if (sub_rs != null && (!sub_rs.isClosed())) {
	
				sub_rs.close();
			}
			if (sub_pstmt != null && (!sub_pstmt.isClosed())) {
	
				sub_pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
	
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
	
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : validatePrize : Ending");
	
		return list;
	}
	public ArrayList<eventRegVo> getprogramlist(String cat_id, String evId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getcategorylist Starting");
	Connection conn =null;
	PreparedStatement psmt =null;
	ResultSet rs =null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.getprogramList);
		psmt.setString(1,evId);
		psmt.setString(2,cat_id);
		System.out.println("********************* :::::::::::: *******************"+psmt);
	rs=psmt.executeQuery();
	while(rs.next()){
		eventRegVo vo = new eventRegVo();
		vo.setProgrammeId(rs.getString("program_id"));
		vo.setProgrammeName(rs.getString("program_name"));
			list.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	
	}		JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getcategorylist Ending");
		return list;
	}
	
	public ArrayList<eventRegVo> getProgramList(String id) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getProgrammeName Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> categoryName = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getProgramList);
		psmt.setString(1, id);
	
		System.out.println(psmt);
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setProgrammeId(rs.getString("program_id"));
		vo.setProgrammeName(rs.getString("program_name"));
			categoryName.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null || psmt.isClosed()) {
				psmt.close();
			}
			if (conn != null || conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getProgrammeName Ending");
		return categoryName;
	}
	
	public String saveProgramScheduling(eventRegPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: saveStageSetting Starting");
	Connection conn=null;
	PreparedStatement psmt =null;
	String result =null;
	int count =0;
	
	String id=null;
	try{
		//Criteria_Id,`Event_id`,`Event_name`,`Programme_Name`,`Category`,`Criteria`,`Max_marks`,`Seq_No`
	System.out.println("inside saveCriteriaSetting daoimpl");
	id=IDGenerator.getPrimaryKeyID("campus_event_program_scheduling");
	conn=JDBCConnection.getSeparateConnection();
	System.out.println("value of hidden criteria id>>>"+pojo.getHiddenCriteriaId());
	if(pojo.getHiddenCriteriaId().equalsIgnoreCase("") ||pojo.getHiddenCriteriaId()==null||pojo.getHiddenCriteriaId()=="undefined"){
		psmt=conn.prepareStatement(EventsSqlUtils.saveProgramscheduling);
	
		psmt.setString(1, id);
		psmt.setString(2, pojo.getEventName());
		psmt.setString(3, pojo.getProgrammeName());
		psmt.setString(4, HelperClass.convertUIToDatabase(pojo.getProgramDate()));
		psmt.setString(5, pojo.getStageName());
		psmt.setString(6, pojo.getGreenRoomName());
		psmt.setString(7, pojo.getMakeup());
		psmt.setString(8, pojo.getBackStage());
		psmt.setString(9,pojo.getProgramTime());
	
		count=psmt.executeUpdate();
		System.out.println("insert in program scheduling>>>>"+psmt);
		if(count>0){
			result="true";
		}else{
			result="false";
		}
	}else{
		psmt=conn.prepareStatement(EventsSqlUtils.updateProgramscheduling);
	
		psmt.setString(1, pojo.getEventName());
		psmt.setString(2, pojo.getProgrammeName());
		psmt.setString(3, HelperClass.convertUIToDatabase(pojo.getProgramDate()));
		psmt.setString(4, pojo.getStageName());
		psmt.setString(5, pojo.getMakeup());
		psmt.setString(6, pojo.getGreenRoomName());
		psmt.setString(7, pojo.getBackStage());
		psmt.setString(8, pojo.getProgramTime());
		psmt.setString(9, pojo.getHiddenCriteriaId());
	
	
		count =psmt.executeUpdate();
		System.out.println("update query in daoImpl>>>>>>"+psmt);
		if(count>0){
			result="updateTrue";
		}else{
			result="false";
			}
	
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : saveStageSetting Ending");
		return result;
	}
	

		
			public ArrayList<eventRegVo> getProgramSchedulingList(String eventId,
					String prog_date) {
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in EventsDaoImpl: getstageSettingList Starting");
				Connection conn = null;
				PreparedStatement psmt = null;

				ResultSet rs = null;
				ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();

				try {
					conn = JDBCConnection.getSeparateConnection();
					if (prog_date.equalsIgnoreCase("") || (prog_date == null)) {
						psmt = conn
								.prepareStatement("SELECT reg.eventName,reg.event_Id,pgrm.program_id,pgrm.program_name,pgrm.programDate,stg.stageId,stg.stageName,grn.greenroom_id,grn.greenroom_name FROM campus_event_registration reg    JOIN campus_event_programcreation pgrm ON pgrm.event_id=reg.event_Id   JOIN `campus_event_stage_allocation` cesa ON pgrm.program_id=cesa.ProgramId   JOIN campus_event_stage stg ON stg.stageId=cesa.StageName JOIN campus_event_greenroom grn ON grn.event_id=reg.event_Id where reg.event_Id like ? ORDER BY pgrm.programDate,reg.eventName,pgrm.program_name");
						psmt.setString(1, eventId);
						
					} else {
						psmt = conn
								.prepareStatement("SELECT reg.eventName,reg.event_Id,pgrm.program_id,pgrm.program_name,pgrm.programDate,stg.stageId,stg.stageName,grn.greenroom_id,grn.greenroom_name FROM campus_event_registration reg    JOIN campus_event_programcreation pgrm ON pgrm.event_id=reg.event_Id   JOIN `campus_event_stage_allocation` cesa ON pgrm.program_id=cesa.ProgramId   JOIN campus_event_stage stg ON stg.stageId=cesa.StageName JOIN campus_event_greenroom grn ON grn.event_id=reg.event_Id where reg.event_Id like ? and programDate like ? ORDER BY pgrm.programDate,reg.eventName,pgrm.program_name");
						psmt.setString(1, eventId);
						psmt.setString(2, prog_date);
					}

					rs = psmt.executeQuery();
					
					System.out.println("programscheduling query>>>"+psmt);

					while (rs.next()) {
						eventRegVo vo = new eventRegVo();

						vo.setEventName(rs.getString("eventName"));
						vo.setEventId(rs.getString("event_Id"));
						vo.setProgrammeName(rs.getString("program_name"));
						vo.setProgId(rs.getString("program_id"));
						vo.setProg_date(rs.getString("programDate"));

						vo.setStageName(rs.getString("stageName"));
						vo.setStageId(rs.getString("stageId"));
						vo.setGreenRoomName(rs.getString("greenroom_name"));
						vo.setGreenRoomId(rs.getString("greenroom_id"));

						PreparedStatement reportpstmt = conn
								.prepareStatement("select Program_time,Scheduling_id,Makeup_report,BackStage_report from campus_event_program_scheduling where Event_name =? and Program_name =?  and Stage = ? and Green_room = ?");
						reportpstmt.setString(1, rs.getString("event_Id"));
						reportpstmt.setString(2, rs.getString("program_id"));
						reportpstmt.setString(3, rs.getString("stageId"));
						reportpstmt.setString(4, rs.getString("greenroom_id"));
						ResultSet reportrs = reportpstmt.executeQuery();
						if (reportrs.next()) {
							vo.setMakeup(reportrs.getString("Makeup_report"));
							vo.setBackStage(reportrs.getString("BackStage_report"));
							vo.setScheduling_id(reportrs.getString("Scheduling_id"));
							vo.setProg_time(reportrs.getString("Program_time"));
						} else {
							vo.setMakeup("-");
							vo.setBackStage("-");
							vo.setProg_time("-");
							vo.setScheduling_id("");
						}
						int count = 0;
						PreparedStatement getstatus = conn
								.prepareStatement("select count(*) from campus_event_program_scheduling where Event_name = ? and Program_name = ? and Stage = ? and Green_room = ?");
						getstatus.setString(1, rs.getString("event_Id"));
						getstatus.setString(2, rs.getString("program_id"));
						getstatus.setString(3, rs.getString("stageId"));
						getstatus.setString(4, rs.getString("greenroom_id"));
						ResultSet getstausrs = getstatus.executeQuery();
						while (getstausrs.next()) {
							count = getstausrs.getInt(1);
						}
						if (count == 0) {
							vo.setStatus("Not Scheduled");
							vo.setStatus1("Not");

						} else {
							vo.setStatus("Scheduled");
							vo.setStatus1("Scheduled");
						}
						list.add(vo);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (psmt != null || psmt.isClosed()) {
							psmt.close();
						}
						if (conn != null || conn.isClosed()) {
							conn.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.END_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in EventsDaoImpl : getProgramSchedulingList Ending");

				return list;
			}
	
	public ArrayList<eventRegVo> getAdmissionNo(String searchterm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getAdmissionNo Starting");
	String searchTerm = searchterm + "%";
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	PreparedStatement psmt = null;
	ResultSet rs = null;
	Connection conn = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getAdmissionNo);
		psmt.setString(1, searchTerm);
	
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setAdmissionNo(rs.getString("student_admissionno_var") + "_"
				+ rs.getString("studentName"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null || psmt.isClosed()) {
					psmt.close();
				}
				if (conn != null || conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				}
		}
		return list;
	}
	
	
	public ArrayList<eventRegVo> studentAdmnName(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : studentAdmnName Starting");
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	PreparedStatement psmt = null;
	ResultSet rs = null;
	Connection conn = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.studentAdmnName);
		psmt.setString(1, id);
		System.out.println("studentAdmnName00000000000000000000000000000000 No query is "+psmt);
	rs = psmt.executeQuery();
	
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setStudentId(rs.getString("student_id_int"));
		vo.setAdmissionNo(rs.getString("student_admissionno_var"));
		vo.setStudentName(rs.getString("student_admissionno_var") +"_"+rs.getString("studentName"));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(psmt!=null || psmt.isClosed()){psmt.close();}if(conn!=null || conn.isClosed()){conn.close();}}catch(Exception e){e.printStackTrace();
				}
		}
		return list;
	}
	
	
	
	public ArrayList<eventRegVo> getEventStudentRegListIndividual(String evId,String catId, String progId, String houseId, String accId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
	PreparedStatement psmt,psmt1,psmt2 = null;
	Connection conn= null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	ArrayList<eventRegVo> mainList=new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT DISTINCT cep.program_name,s.`participateType`,s.registrationNo,CONCAT(cs.`student_admissionno_var`,'_',cs.`student_fname_var`,' ',cs.`student_lname_var`) studentName, csc.`student_rollno`,cc.classdetails_name_var,l.`Location_Name`,csc.`student_imgurl_var`,  CASE WHEN  h.`housename`!='' THEN  h.`housename` ELSE  '-' END houseName,`registration_id`,s.`captain_admissionNo`,s.`registration_id`,s.created_time,csec.classsection_name_var FROM `campus_event_studentregistration` s LEFT JOIN `campus_student` cs ON cs.`student_admissionno_var`=s.`captain_admissionNo` LEFT JOIN campus_student_classdetails csc ON csc.student_id_int=cs.student_id_int LEFT JOIN campus_classdetail cc ON (cc.classdetail_id_int=csc.classdetail_id_int AND cc.locationId=csc.locationId) JOIN  campus_classsection csec ON csec.classsection_id_int=csc.classsection_id_int LEFT JOIN `campus_house_settings` h ON h.`house_id`=s.`house_id` LEFT JOIN `campus_location` l ON l.`Location_Id`=cs.`locationId` JOIN `campus_event_programcreation` cep ON cep.program_id=s.`program_id` WHERE s.`participateType`='individual' AND s.`event_id` like ? AND s.`category_id` LIKE ? AND s.`program_id` LIKE ? AND s.`house_id` LIKE ? and s.accId like ? AND csc.fms_acadamicyear_id_int=? ORDER BY `created_time` DESC");
	
		psmt.setString(1, evId);
		psmt.setString(2, catId);
		psmt.setString(3, progId);
		psmt.setString(4, houseId);
		psmt.setString(5, accId);
		psmt.setString(6, accId);
	
		System.out.println("studentData 0--------getEventStudentRegListIndividual88888888 ********---------- -individual"+psmt);
	rs=psmt.executeQuery();
	
	while(rs.next()){
		eventRegVo vo=new eventRegVo();
		vo.setRegistrationNo(rs.getString("registrationNo"));
		vo.setStudentName(rs.getString("studentName"));
		vo.setRollNumber(rs.getString("student_rollno"));
		vo.setClassName(rs.getString("classdetails_name_var"));
		vo.setLocation(rs.getString("Location_Name"));
		vo.setImageUrl(rs.getString("student_imgurl_var"));
		vo.setHouseName(rs.getString("housename"));
		vo.setRegistrationId(rs.getString("registration_id"));
		vo.setCaptainAdmnNo(rs.getString("captain_admissionNo"));
		vo.setParticipateType(rs.getString("participateType"));
		vo.setProgName(rs.getString("program_name"));
		vo.setClassSec(rs.getString("classsection_name_var"));
			mainList.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
		return mainList;
	}
	
	public ArrayList<eventRegVo> getHouseNameListGroup(String evId, String catId, String progId, String flag) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getRegNumberList_Group Starting");
	Connection conn= null;
	PreparedStatement psmt =null;
	ResultSet rs =null;
	ArrayList<eventRegVo> regNo = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		if(flag.equalsIgnoreCase("Indiv")){
		psmt=conn.prepareStatement(EventsSqlUtils.getHouseNameList_Indiv);
	}else
		psmt=conn.prepareStatement(EventsSqlUtils.getHouseNameList_Group);
	
	if(evId.equalsIgnoreCase("")){
		evId="%%";
	}psmt.setString(1, evId);
	
	if(catId.equalsIgnoreCase("")){
		catId="%%";
	}psmt.setString(2, catId);
	
	if(progId.equalsIgnoreCase("")){
		progId="%%";
	}psmt.setString(3, progId);
	
	System.out.println("#################################################"+psmt);
	rs=psmt.executeQuery();
	while(rs.next()){
		eventRegVo vo = new eventRegVo();
		vo.setHouseId(rs.getString("house_id"));
		vo.setHouseName(rs.getString("housename"));
			regNo.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || psmt.isClosed()){psmt.close();}if(conn!=null || conn.isClosed()){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getRegNumberList_Group Ending");
		return regNo;
	}
	
	public ArrayList<eventRegVo> getDataForUpdateEventStdRegis(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getDataForUpdateEventStdRegis Starting");
	
	Connection conn =null;
	PreparedStatement psmt = null;
	PreparedStatement psmt2 =null;
	ResultSet rs =null;
	ResultSet rs1 =null;
	ArrayList<eventRegVo> mainList=new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.getDataForUpdateEventStdRegis);
		psmt.setString(1, id);
		System.out.println("upadf"+psmt);
	rs=psmt.executeQuery();
	while(rs.next()){
		ArrayList<eventRegVo> sublist=new ArrayList<eventRegVo>();
		eventRegVo vo = new eventRegVo();
	
		vo.setRegistrationId(rs.getString("registration_id"));
		vo.setEventId(rs.getString("event_id"));
		vo.setCategoryId(rs.getString("category_id"));
		vo.setProgId(rs.getString("program_id"));
		vo.setHouseName(rs.getString("house_id"));
		vo.setInfoStaff(rs.getString("info_staff"));
		vo.setInfoSynopsis(rs.getString("info_synopsys"));
		vo.setInfoReq(rs.getString("info_req"));
		vo.setCaptainFullName(rs.getString("cName"));
		vo.setParticipateType(rs.getString("participateType"));
	
		vo.setCaptainAdmnNo(rs.getString("captain_admissionNo"));
		String[] ParticipantsAdmisNos = rs.getString("participants_admissionNo").split(",");
		ParticipantsAdmisNos=HelperClass.removeElements(ParticipantsAdmisNos, rs.getString("captain_admissionNo"));
			vo.setParticipantsAdmisNos(ParticipantsAdmisNos);
	
			mainList.add(vo);
	
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(rs!=null || rs.isClosed())
			{rs.close();}
			if(psmt!=null || psmt.isClosed())
			{psmt.close();}
			if(conn!=null || conn.isClosed())
			{conn.close();}
	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getDataForUpdateEventStdRegis Ending");
		return mainList;
	
	}
	
	
	
	public String deleteEventStudReg(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: deleteEventStudReg Starting");
	Connection conn =null;
	PreparedStatement psmt= null;
	String result=null;
	int count=0;
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.deleteEventStudReg);
		psmt.setString(1, id);
		count=psmt.executeUpdate();
		System.out.println("deleting stage query deleteEventStudReg>>>>"+psmt);
	if(count>0){
		result="true";
	}else {
		result="false";
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || psmt.isClosed()){psmt.close();}if(conn!=null || conn.isClosed()){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : deleteEventStudReg Ending");
		return result;
	}
	
	public ArrayList<eventRegVo> getDataForUpdateScheduling(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getDataForUpdateScheduling Starting");
	
	Connection conn =null;
	PreparedStatement psmt= null;
	ResultSet rs =null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try{
	
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.getDataForUpdateScheduling);
		psmt.setString(1, id);
		System.out.println("update scheduling query>>>>"+psmt);
	rs=psmt.executeQuery();
	while(rs.next()){
		eventRegVo vo = new eventRegVo();
		vo.setScheduling_id(rs.getString("Scheduling_id"));
		vo.setEventName(rs.getString("Event_name"));
		vo.setProgrammeName(rs.getString("Program_name"));
		vo.setProg_date(HelperClass.convertDatabaseToUI(rs.getString("Program_date")));
		vo.setStageName(rs.getString("Stage"));
		vo.setGreenRoomName(rs.getString("Green_room"));
		vo.setMakeup(rs.getString("Makeup_report"));
		vo.setBackStage(rs.getString("BackStage_report"));
	
			list.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || psmt.isClosed()){psmt.close();}if(conn!=null || conn.isClosed()){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getDataForUpdateScheduling Ending");
		return list;
	}
	
	
	public String deleteProgramScheduling(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: deleteProgramScheduling Starting");
	Connection conn =null;
	PreparedStatement psmt= null;
	String result=null;
	int count=0;
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.deleteScheduling);
		psmt.setString(1, id);
		count=psmt.executeUpdate();
		System.out.println("delete criteria query>>>>"+psmt);
	if(count>0){
		result="true";
	}else {
		result="false";
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || psmt.isClosed()){psmt.close();}if(conn!=null || conn.isClosed()){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : deleteProgramScheduling Ending");
		return result;
	}
	
	
	public String validateScheduling(String eventName, String programmeList,
			String fromDate, String stageNameList, String greenRoomList) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : validatePrize : Starting");
	
	PreparedStatement sub_pstmt = null;
	ResultSet sub_rs = null;
	
	String list = null;
	int count = 0;
	
	Connection conn = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
	
		sub_pstmt = conn
				.prepareStatement(SQLUtilConstants.validate_scheduling);
		sub_pstmt.setString(1, eventName);
		sub_pstmt.setString(2, programmeList);
		sub_pstmt.setString(3, HelperClass.convertUIToDatabase(fromDate));
		sub_pstmt.setString(4, stageNameList);
		sub_pstmt.setString(5, greenRoomList);
	
		sub_rs = sub_pstmt.executeQuery();
	
		while (sub_rs.next()) {
	
			count = sub_rs.getInt(1);
	
		}
	
		if (count > 0) {
	
			list = "true";
	
	} else {
	
		list = "false";
		}
	
	} catch (SQLException sqle) {
		logger.error(sqle.getMessage(), sqle);
		logger.error(sqle);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
	
		try {
			if (sub_rs != null && (!sub_rs.isClosed())) {
	
				sub_rs.close();
			}
			if (sub_pstmt != null && (!sub_pstmt.isClosed())) {
	
				sub_pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
	
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
	
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : validatePrize : Ending");
	
		return list;
	}
	
	
	
	public ArrayList<eventRegVo> geteventList() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getProgramName Starting");
	Connection conn=null;
	PreparedStatement psmt=null;
	ResultSet rs =null;
	ArrayList<eventRegVo> name = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.geteventname);
	
		rs=psmt.executeQuery();
	
	
		while(rs.next()){
			eventRegVo vo = new eventRegVo();
			vo.setEventId(rs.getString("event_Id"));
		vo.setEventName(rs.getString("eventName"));
	
			name.add(vo);
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || psmt.isClosed()){psmt.close();}if(conn!=null || conn.isClosed()){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getProgramName Ending");
		return name;
	}
	
	public ArrayList<eventRegVo> getStage(String event_id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getStage Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> name = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getStage);
		psmt.setString(1, event_id);
		System.out.println(psmt);
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setStageId(rs.getString("stageId"));
		vo.setStageName(rs.getString("stageName"));
			name.add(vo);
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null || psmt.isClosed()) {
				psmt.close();
			}
			if (conn != null || conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getStage Ending");
		return name;
	}
	
	public ArrayList<eventRegVo> eventCategory(String eid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getProgramName Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> name = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.geteventcategory);
		psmt.setString(1, eid);
		rs=psmt.executeQuery();
		System.out.println(psmt);
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setCategoryId(rs.getString("categoryId"));
		vo.setCategoryName(rs.getString("categoryName"));
			name.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null || psmt.isClosed()) {
				psmt.close();
			}
			if (conn != null || conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getProgramName Ending");
		return name;
	}
	
	public ArrayList<eventRegVo> eventProgramName(String cid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getProgramName Starting");
	Connection conn=null;
	PreparedStatement psmt=null;
	ResultSet rs =null;
	ArrayList<eventRegVo> name = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.getprogramname);
	
		psmt.setString(1, cid);
	
		rs=psmt.executeQuery();
	
	
		while(rs.next()){
			eventRegVo vo = new eventRegVo();
			vo.setProgrammeId(rs.getString("program_id"));
		vo.setProgrammeName(rs.getString("program_name"));
			name.add(vo);
	
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || psmt.isClosed()){psmt.close();}if(conn!=null || conn.isClosed()){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getProgramName Ending");
		return name;
	}
	
	public ArrayList<eventRegVo> getEventIdList(eventRegPojo filterpojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getEventIdList : Starting");
	
	Connection conn = null;
	PreparedStatement psmt=null;
	ResultSet rs=null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	int sno = 0;
	
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT eventName,event_Id FROM  `campus_event_registration`  WHERE event_id LIKE ?");
	psmt.setString(1, filterpojo.getEventId());
	
	rs=psmt.executeQuery();
	
	while(rs.next()){
		sno++;
		eventRegVo filterVo = new eventRegVo();
		filterVo.setSno(sno);
		filterVo.setEventId(rs.getString("event_Id"));
		filterVo.setEventName(rs.getString("eventName"));
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
			+ " Control in EventsDaoImpl: getEventIdList : Ending");
	
		return list;
	}
	public ArrayList<eventRegVo> getChestNoRegistrationList(String evId,
			String catId, String progId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getProgramSettingList Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	String result = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.GET_CHESTNO_REGISTRATION_LIST);
		psmt.setString(1, evId);
		psmt.setString(2, catId);
		psmt.setString(3, progId);
		System.out.println(psmt);
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setChestNoId(rs.getString("chestnumber_id"));
		vo.setEventId(rs.getString("event_id"));
		vo.setCategoryId(rs.getString("category_id"));
		vo.setProgId(rs.getString("program_id"));
		vo.setEventName(rs.getString("eventName"));
		vo.setCategoryName(rs.getString("categoryName"));
		vo.setProgrammeName(rs.getString("program_name"));
		vo.setStageName(rs.getString("stageName"));
		vo.setProgDate(rs.getString("programDate"));
			list.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null || psmt.isClosed()) {
				psmt.close();
			}
			if (conn != null || conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getProgramSettingList Ending");
		return list;
	}
	
	public String deletechestNo(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: deletechestNo Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	String result = null;
	int count = 0;
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn
				.prepareStatement(EventsSqlUtils.DELETE_CHESTNO_GENERATION);
		psmt.setString(1, id);
		count = psmt.executeUpdate();
		if (count > 0) {
			result = "true";
	} else {
		result = "false";
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null || !psmt.isClosed()) {
				psmt.close();
			}
			if (conn != null || !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : deletechestNo Ending");
		return result;
	}
	
	public ArrayList<eventRegVo> getDataforUpdateChestNoGeneration(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getDataforUpdateProgram Starting");
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn
				.prepareStatement(EventsSqlUtils.GET_DATA_FOR_UPDATE_CHESTNO_GENERATION);
		psmt.setString(1, id);
		System.out.println("upadfssssssssssssss" + psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setEventId(rs.getString("event_id"));
		vo.setCategoryId(rs.getString("category_id"));
		vo.setProgId(rs.getString("program_id"));
			list.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null || psmt.isClosed()) {
				psmt.close();
			}
			if (conn != null || conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getDataforUpdateProgram Ending");
		return list;
	}
	
	public ArrayList<eventRegVo> getProgramNumberingDetails(String event, String stage) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getProgramNumberingDetails Starting");
	Connection conn = null;
	PreparedStatement psmt =null;
	PreparedStatement pstmt1 = null;
	PreparedStatement getstu=null;
	ResultSet rs,rs1 = null;
	String studentList[]=null;
	ArrayList<eventRegVo> list=new ArrayList<eventRegVo>();
	System.out.println("inside getProgramNumberingDetails DaoImpl>>>>>");
	try{
		conn=JDBCConnection.getSeparateConnection();
		pstmt1=conn.prepareStatement("SELECT captain_admissionNo FROM `campus_event_studentregistration` WHERE program_id IN (select ProgramId from campus_event_stage_allocation where Event=? and StageName=?)");
		pstmt1.setString(1, event);
		pstmt1.setString(2, stage);
		rs1=pstmt1.executeQuery();
	
	
	while(rs1.next()){
	
		
	
	int count = 0;
	getstu = conn.prepareStatement("select count(*) from campus_event_program_numbering where participants =?");
		
		getstu.setString(1,rs1.getString("captain_admissionNo"));
		ResultSet getsturs =getstu.executeQuery();
		while(getsturs.next()){
			count = getsturs.getInt(1);
		}
	
		if(count == 0){
			psmt=conn.prepareStatement(EventsSqlUtils.getProgramNumberingDetails);
			psmt.setString(1,rs1.getString("captain_admissionNo"));
			rs=psmt.executeQuery();
			while(rs.next()){
	
				eventRegVo vo = new eventRegVo();
				vo.setStudentId(rs1.getString("captain_admissionNo"));
				vo.setCategoryName(rs.getString("categoryName"));
				vo.setCategoryId(rs.getString("category_id"));
				vo.setProgrammeName(rs.getString("program_name"));
				vo.setProgrammeId(rs.getString("program_id"));
				vo.setParticipantName(rs.getString("student"));
				vo.setRegistrationId(rs.getString("registration_id"));
	
					list.add(vo);
				}
			}
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && psmt.isClosed()){
				
				
				psmt.close();
			}
			if(  pstmt1!=null && pstmt1.isClosed()) {
				pstmt1.close();
			}
			if(getstu!=null && getstu.isClosed()) {
				getstu.close();
			}
			if(conn!=null && conn.isClosed()){
				conn.close();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT); 
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getProgramNumberingDetails Ending");
	
		return list;
	}
	
	public ArrayList<eventRegVo> getChestNoList(String evId, String catId, String progId){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
		PreparedStatement psmt=null,psmt2=null,psmt1=null;
		Connection conn= null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ArrayList<eventRegVo> mainList=new ArrayList<eventRegVo>();
	    ArrayList<String> stulist = new ArrayList<String>();
	    Set<String> hs = new HashSet<String>();
	    String[]ParticipantsAdmisNos = null;
	    int count=100;
	   String value="";
	int duplicate=0;
	int result=0;
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT captain_admissionNo FROM  campus_event_studentregistration WHERE event_id LIKE ? AND category_id LIKE ? AND program_id LIKE ?");
			psmt.setString(1, evId);
			psmt.setString(2, catId);
			psmt.setString(3, progId);
	rs=psmt.executeQuery();
	while(rs.next()){
		
		stulist.add(rs.getString("captain_admissionNo"));
	   
	}
		for(int i=0;i<stulist.size();i++){
			String a1=stulist.get(i);
			for(int j=i+1;j<stulist.size()-1;j++){
				String a2=stulist.get(j);
				if((a1.equals(a2))){
					stulist.remove(a2);
					hs.addAll(stulist);
					stulist.clear();
					stulist.addAll(hs);
				}
			}
		}
			for(int i=0;i<stulist.size();i++){
				
				psmt1=conn.prepareStatement("SELECT MAX(cast(chest_no as unsigned)) FROM campus_event_chestno_list");
				rs1=psmt1.executeQuery();
				while(rs1.next()){
					value=rs1.getString(1);
				}
				
					psmt2=conn.prepareStatement(EventsSqlUtils.GET_PARTICPANT_CHESTNO_FOR_GENERATING_NO_FROM_STUDENT_REGISTRATION);
			 		psmt2.setString(1,stulist.get(i));
					rs2=psmt2.executeQuery();
						while(rs2.next()){
							eventRegVo vo1 = new eventRegVo();
								vo1.setAdmissionNo(rs2.getString("student_admissionno_var"));
								vo1.setStudentName(rs2.getString("student_fname_var")+" "+rs2.getString("student_lname_var"));
								vo1.setLocation(rs2.getString("Location_Name"));
								vo1.setImageUrl(rs2.getString("imgurl_var"));
								
								if(value.equalsIgnoreCase("")|| value.equalsIgnoreCase("null") || value.equalsIgnoreCase("0"))
								{
								   count++;
								   
								  vo1.setChestNoId(""+(count));
								  
								  psmt=conn.prepareStatement("UPDATE campus_event_chestno_list SET chest_no=? WHERE admission_no=?  AND chest_no=''");
								  psmt.setInt(1,count);
								  psmt.setString(2,stulist.get(i));
								  psmt.executeUpdate();
								}
								else if(!value.equalsIgnoreCase("null") || !value.equalsIgnoreCase("") || !value.equalsIgnoreCase("0"))
								  {
									result=Integer.parseInt(value);
									result++;
								    
							        vo1.setChestNoId(""+(result));
							        
							        psmt=conn.prepareStatement("UPDATE campus_event_chestno_list SET chest_no=? WHERE admission_no=?  AND chest_no=''");
									  psmt.setInt(1,result);
									  psmt.setString(2,stulist.get(i));
									  psmt.executeUpdate();
								  }
								mainList.add(vo1);
					       }
						
						
			        }
	   }
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null && !rs.isClosed()){rs.close();}
				if(rs2!=null && !rs2.isClosed()){rs2.close();}
				if(psmt!=null && !psmt.isClosed()){psmt.close();}
				if(psmt2!=null && !psmt2.isClosed()){psmt2.close();}
				if(conn!=null && conn.isClosed()){conn.close();}
				}
			catch(Exception e){e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
		return mainList;
	}
	
	public ArrayList<eventRegVo> getParticipants(String event, String stage) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getParticipants Starting");
	Connection conn = null;
	java.sql.CallableStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> name = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareCall("{CALL getAllocatedList(?)}");
	psmt.setString(1, event);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setParticipantId(rs.getString("captain_admissionNo"));
		vo.setParticipantName(rs.getString("name"));
		name.add(vo);
	}
	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getParticipants Ending");
		return name;
	}
	
	public String saveStageParticipantMapping(eventRegPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: saveStageParticipantMapping Starting");
	Connection conn=null;
	PreparedStatement psmt =null;
	PreparedStatement psmt1 =null;
	PreparedStatement ps=null;
	String result =null;
	int count1=0;
	int count =0;
	int count2=0;
	String Students[]=null;
	String id=null;
	ResultSet rs=null;
	try{
		
		conn=JDBCConnection.getSeparateConnection();
		System.out.println("pojo.getParticipantsList() for stage allocation>>"+pojo.getParticipantsList());
	Students=pojo.getParticipantsList().split(",");
	ps=conn.prepareStatement("SELECT COUNT(*) FROM `campus_event_program_numbering` WHERE `participants`=? AND `stageId`=? AND `event`=?");
	
	for(int i=0;i<Students.length;i++)
	{
		ps.setString(1, Students[i]);
		ps.setString(2, pojo.getStageId());
		ps.setString(3, pojo.getEventId());
		rs=ps.executeQuery();
	}
	System.out.println("count query to check numbering>>"+ps);
	while(rs.next())
	{
		count2=rs.getInt(1);
		System.out.println("count2 value>>>"+count2);
	}
	if(count2>0)
	{
		result="failure";
		ps.close();
	}
	
	else
	{
		psmt1=conn.prepareStatement("DELETE FROM campus_event_stage_allocation WHERE StageName=?");
		psmt1.setString(1, pojo.getStageId());
		count1=psmt1.executeUpdate();
		
		psmt=conn.prepareStatement(EventsSqlUtils.saveStageAllocation);
		
		
		
		psmt.setString(2, pojo.getEventId());
		psmt.setString(3, pojo.getStageId());
		
		for(int i=0;i<pojo.getParticipantsList().split(",").length;i++)
		{
			psmt.setString(1, IDGenerator.getPrimaryKeyID("campus_event_stage_allocation"));
			psmt.setString(4, pojo.getParticipantsList().split(",")[i]);
			count=psmt.executeUpdate();
		}
		
		
		if(count>0 || count1 > 0){
			result="true";
		}else{
			result="false";
		}
		
		
	}      
		
	
	
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			
			if(psmt!=null && !psmt.isClosed())
			{
				psmt.close();
			}
			if(psmt1!=null && !psmt1.isClosed())
			{
				psmt1.close();
			}
			/*if(ps!=null ||!ps.isClosed())
			{
				ps.close();
			}*/
			if(conn!=null && !conn.isClosed())
			{
				conn.close();
			}
		}catch(Exception e)
		{e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : saveStageParticipantMapping Ending");
		return result;
	}
	
	
	public String saveGeneratedchestNo(eventRegPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: saveChestNumber Starting");
		Connection conn=null;
		PreparedStatement psmt=null,psmt1=null,psmt2=null;
		ResultSet rs=null;
		String result =null;
		int count=0,duplicat=0;
		String value="";
		String stulist[] = null,count1=null;
		String id=null;
		try{
			conn=JDBCConnection.getSeparateConnection();
			
			for(int i=0;i<pojo.getAdmissionNoArray().length;i++){
				psmt=conn.prepareStatement("SELECT  COUNT(admission_no),CASE WHEN chest_no IS NULL THEN '' ELSE chest_no END chest_no  FROM  campus_event_chestno_list WHERE admission_no=?");
				psmt.setString(1, pojo.getAdmissionNoArray()[i]);
				System.out.println(psmt);
				rs = psmt.executeQuery();
				while(rs.next()){
					count1=rs.getString(1);
					value=rs.getString(2);
					duplicat=rs.getInt(1);
				}
			
			System.out.println("--------------------Saving operations--------------------");
				System.out.println("count-->>>"+count1);
				System.out.println("value-->>>"+value);
				System.out.println("duplicat-->>>"+duplicat);
				
			/*if(value.equalsIgnoreCase("")|| value.equalsIgnoreCase("null") && count1.equalsIgnoreCase("1") )	
			{
				System.out.println("--------------------Inside update the value validation--------------------");
				System.out.println("Value validation :-"+value);
				System.out.println("-------------------- Inside update the value validation--------------------");
				
					System.out.println("-------------------- Update operations--------------------");
					System.out.println(count1);
					for(int j=0;j<pojo.getAdmissionNoArray().length;j++){
						psmt=conn.prepareStatement("UPDATE campus_event_chestno_list SET chest_no=? WHERE admission_no=?  AND chest_no=''");
						psmt.setString(1, pojo.getChestNoArray()[j]);
						psmt.setString(2, pojo.getAdmissionNoArray()[j]);
						System.out.println(psmt);
						count=psmt.executeUpdate();
		       }
			}
		  
			 else*/
				if(!value.equalsIgnoreCase("") || !value.equalsIgnoreCase("null"))
			 {
				 if(duplicat==0){
				System.out.println("--------------------Insert operations--------------------");
				System.out.println(count1);
				
			    
			   for(int k=0;k<pojo.getAdmissionNoArray().length;k++){
				   psmt=conn.prepareStatement("insert into campus_event_chestno_list(admission_no,chest_no)values(?,?)");
				  psmt.setString(1, pojo.getAdmissionNoArray()[k]);
				  psmt.setString(2, pojo.getChestNoArray()[k]);
				  System.out.println(psmt);
				  count=psmt.executeUpdate();
			    }
			 }
			}
			}
			System.out.println("count value is :-"+count);
			
			if(count>0){
				result="true";
			}else{
				result="false";
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null || !rs.isClosed())
				{
					rs.close();
				}
				if(psmt!=null ||!psmt.isClosed())
				{
					psmt.close();
				}
				if(conn!=null || !conn.isClosed())
				{conn.close();
				}
			}catch(Exception e)
			{e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : saveChestNumber Ending");
		return result;
	}
	
	
	public ArrayList<eventRegVo> newEventIdCardPrint(eventRegVo filterpojo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getEventIdList : Starting");
	
	Connection conn = null;
	PreparedStatement psmt=null;
	ResultSet rs=null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	int sno = 0;
	
	try{
		conn=JDBCConnection.getSeparateConnection();
	
		psmt=conn.prepareStatement("SELECT cer.eventName,cer.event_Id,cec.categoryName,cec.categoryId,cepr.`program_name`,cepr.`program_id` FROM `campus_event_programcreation` cepr LEFT JOIN `campus_event_category` cec ON cec.categoryId=cepr.category_id LEFT JOIN `campus_event_registration` cer ON cer.event_Id=cepr.event_id WHERE cepr.event_id LIKE ? AND cepr.category_id LIKE ? AND cepr.program_id LIKE ?");
	psmt.setString(1, filterpojo.getEventId());
	psmt.setString(2, filterpojo.getCategoryId());
	psmt.setString(3, filterpojo.getProgId());
	rs=psmt.executeQuery();
	System.out.println("----------------------------"+psmt);
	
	while(rs.next()){
		sno++;
		eventRegVo filterVo = new eventRegVo();
		filterVo.setSno(sno);
		filterVo.setEventId(rs.getString("event_Id"));
		filterVo.setEventName(rs.getString("eventName"));
		filterVo.setCategoryId(rs.getString("categoryId"));
		filterVo.setCategory(rs.getString("categoryName"));
		filterVo.setProgrammeId(rs.getString("program_id"));
		filterVo.setProgrammeName(rs.getString("program_name"));
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
			+ " Control in EventsDaoImpl: getEventIdList : Ending");
	
		return list;
	}
	
	public ArrayList<eventRegVo> getMappedParticipants(String stageVal) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getMappedParticipants Starting");
	String studentList[]=null;
	Connection conn =null;
	PreparedStatement psmt= null;
	PreparedStatement ps=null;
	ResultSet rs =null;
	ResultSet rs1 =null;
	String stage=null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try{
	
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT stg.program_id,stg.program_name,cec.categoryName FROM campus_event_stage_allocation al JOIN `campus_event_programcreation` stg ON stg.`program_id`=al.`ProgramId` JOIN campus_event_category cec ON stg.category_id=cec.categoryId WHERE al.StageName=?");
		psmt.setString(1, stageVal);
	
		rs=psmt.executeQuery();
		
		
	while(rs.next()){
		eventRegVo vo=new eventRegVo();
		vo.setProgId(rs.getString("program_id"));
		vo.setProgName(rs.getString("program_name")+" ("+rs.getString("categoryName")+")");
		list.add(vo);
	}
	

	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(rs!=null && !rs.isClosed())
			{
				rs.close();
				
			}
			if(psmt!=null && !psmt.isClosed())
			{
				psmt.close();
				
			}
			if(conn!=null && !conn.isClosed())
			{
				conn.close();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getMappedParticipants Ending");
		return list;
	}
	
	public ArrayList<eventRegVo> getUnAllocatedProgramList(String event) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getUnAllocatedProgramList Starting");
	String studentList[]=null;
	Connection conn =null;
	PreparedStatement psmt= null;
	ResultSet rs =null;
	String stage=null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try{
	
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT cep.*,cec.categoryName FROM `campus_event_programcreation` cep JOIN campus_event_category cec ON cep.category_id=cec.categoryId WHERE `program_id` NOT IN(SELECT programId FROM `campus_event_stage_allocation`) AND event_id=?");
		psmt.setString(1, event);
	
		rs=psmt.executeQuery();
		
	
	while(rs.next()){
		eventRegVo vo=new eventRegVo();
		vo.setProgId(rs.getString("program_id"));
		vo.setProgName(rs.getString("program_name")+" ("+rs.getString("categoryName")+")");
		list.add(vo);
		
	}
	
	
	
	
	
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(rs!=null && !rs.isClosed())
			{
				rs.close();
				
			}
			
			if(psmt!=null && !psmt.isClosed())
			{
				psmt.close();
				
			}
			if(conn!=null && !conn.isClosed())
			{
				conn.close();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getUnAllocatedProgramList Ending");
		return list;
	}
	public ArrayList<eventRegVo> getEventIdListToPrint(eventRegPojo filterpojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getEventIdList : Starting");
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	int sno = 0;
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement("SELECT cer.eventName,cer.event_Id,cec.categoryName,cec.categoryId,cepr.`program_name`,cepr.`program_id` FROM `campus_event_programcreation` cepr LEFT JOIN `campus_event_category` cec ON cec.categoryId=cepr.category_id LEFT JOIN `campus_event_registration` cer ON cer.event_Id=cepr.event_id WHERE cepr.event_id LIKE ? AND cepr.category_id LIKE ? AND cepr.program_id LIKE ?");
	psmt.setString(1, filterpojo.getEventId());
	psmt.setString(2, filterpojo.getCategoryId());
	psmt.setString(3, filterpojo.getProgId());
	rs=psmt.executeQuery();
	
	while (rs.next()) {
		sno++;
		eventRegVo filterVo = new eventRegVo();
		filterVo.setSno(sno);
		filterVo.setEventId(rs.getString("event_Id"));
		filterVo.setEventName(rs.getString("eventName"));
		filterVo.setCategoryId(rs.getString("categoryId"));
		filterVo.setCategory(rs.getString("categoryName"));
		filterVo.setProgrammeId(rs.getString("program_id"));
		filterVo.setProgrammeName(rs.getString("program_name"));
			list.add(filterVo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
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
			+ " Control in EventsDaoImpl: getEventIdList : Ending");
	
		return list;
	}
	
	public ArrayList<eventRegVo> getEventIdPrintList(String evId,String catId, String regNo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
	PreparedStatement psmt,psmt1,psmt2,psmt3 = null;
	Connection conn= null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	ResultSet rs3 = null;
	String stuid = null;
	ArrayList<eventRegVo> mainList=new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT ces.*,h.housename,cer.`eventName`,cer.`event_Id`,cec.`categoryName`,cec.`categoryId`,cep.`program_name`,cep.`program_id` FROM campus_event_studentregistration ces LEFT JOIN campus_house_settings h ON h.house_id=ces.house_id LEFT JOIN `campus_event_registration` cer ON cer.`event_Id`=ces.`event_id` LEFT JOIN `campus_event_category` cec ON cec.`categoryId`=ces.`category_id` AND cec.`eventId`=ces.`event_id` LEFT JOIN `campus_event_programcreation` cep ON cep.`program_id`=ces.`program_id` AND cep.`event_id`=ces.`event_id` AND cep.`category_id`=ces.`category_id` WHERE ces.event_id = ? AND ces.category_id = ? AND ces.program_id = ?");	
		psmt.setString(1, evId);
		psmt.setString(2, catId);
		psmt.setString(3, regNo);
	
	
	
	System.out.println("studentData111111111"+psmt);
	rs=psmt.executeQuery();
	
	while(rs.next()){
	
		ArrayList<eventRegVo> subList = new ArrayList<eventRegVo>();
		
	
		String[] ParticipantsAdmisNos = rs.getString("participants_admissionNo").split(",");
	
	
		for(int i=0;i<ParticipantsAdmisNos.length;i++){
			psmt3=conn.prepareStatement(EventsSqlUtils.GET_PARTICPANT_ROLLNO);
			psmt3.setString(1, ParticipantsAdmisNos[i]);
			rs3=psmt3.executeQuery();
			System.out.println("studentData222222222"+psmt3);
			while(rs3.next()){
				stuid = rs3.getString("student_id_int");
			}
			
			
	
			psmt2=conn.prepareStatement("SELECT l.`Location_Name`,cs.*,csc.*, csc.student_rollno rollNo,csc.student_imgurl_var imgurl_var,cc.classdetails_name_var,sec.classsection_name_var FROM campus_student cs  JOIN campus_student_classdetails csc ON csc.student_id_int=cs.student_id_int  JOIN campus_classdetail cc ON (cc.classdetail_id_int=csc.classdetail_id_int AND cc.locationId=csc.locationId) JOIN campus_classsection sec ON csc.classsection_id_int=sec.classsection_id_int  JOIN `campus_location` l ON l.`Location_Id`=cs.`locationId`  WHERE csc.fms_acadamicyear_id_int=? AND cs.student_id_int=? ");
			psmt2.setString(1, rs.getString("accId"));
			psmt2.setString(2, stuid);
			rs2=psmt2.executeQuery();
			System.out.println("studentData3333333333"+psmt2);
			while(rs2.next()){
				
				eventRegVo vo=new eventRegVo();
				vo.setRegistrationId(rs.getString("registration_id"));
				vo.setEventName(rs.getString("eventName"));
				vo.setEventId(rs.getString("event_id"));
				vo.setCategoryName(rs.getString("categoryName"));
				vo.setCategoryId(rs.getString("category_id"));
				vo.setProgrammeName(rs.getString("program_name"));
				vo.setProgrammeId(rs.getString("program_id"));
				vo.setAdmissionNo(rs2.getString("student_admissionno_var"));
				vo.setLocation(rs2.getString("Location_Name"));
				vo.setStudentName(rs2.getString("student_fname_var")+" "+rs2.getString("student_lname_var"));
				vo.setClassName(rs2.getString("classdetails_name_var")+"-"+rs2.getString("classsection_name_var"));
				vo.setRollNumber(rs2.getString("rollNo"));
				vo.setImageUrl(rs2.getString("imgurl_var"));
				
				PreparedStatement prpstmt=conn.prepareStatement("SELECT chest_no FROM campus_event_chestno_list WHERE admission_no=?");
				prpstmt.setString(1, ParticipantsAdmisNos[i]);
				ResultSet rss=prpstmt.executeQuery();
				if(rss.next()){
					vo.setChestNumber(rss.getString("chest_no"));	
				}
				else{
					vo.setChestNumber("");	
				}
				rss.close();
				prpstmt.close();
				
				prpstmt=conn.prepareStatement("SELECT ces.stageName,ceps.* FROM `campus_event_program_scheduling` ceps JOIN `campus_event_stage` ces ON ceps.Stage=ces.StageId WHERE ceps.Program_name=?");
				prpstmt.setString(1, rs.getString("program_id"));
				rss=prpstmt.executeQuery();
				if(rss.next()){
					vo.setProg_date(HelperClass.convertDatabaseToUI(rss.getString("Program_date")));
					vo.setStageName(rss.getString("stageName"));
					vo.setMakeup(rss.getString("Makeup_report"));
					vo.setBackStage(rss.getString("BackStage_report"));
					vo.setProg_time(rss.getString("Program_time"));
				}
				rss.close();
				prpstmt.close();
				
				mainList.add(vo);
				}
	
			
	
	
	
			}
			
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
		return mainList;
	}
	
	
	public String saveProgramNumberingDetails(eventRegPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: saveProgramNumberingDetails Starting");
	Connection conn=null;
	PreparedStatement psmt =null;
	PreparedStatement psmt1,psmt2 =null;
	String result =null;
	int rs=0;
	int count1=0;
	int count =0;
	
	String id=null;
	try{
		System.out.println("pojo>>>>"+pojo.getParticipant().length);
	conn=JDBCConnection.getSeparateConnection();
	
	psmt2=conn.prepareStatement(EventsSqlUtils.deleteAllProgramNumber);
	psmt2.setString(1,pojo.getEventId());
	psmt2.setString(2, pojo.getStageId());
	rs=psmt2.executeUpdate();
	
	psmt=conn.prepareStatement(EventsSqlUtils.saveProgramNumberingDetails);
	
	for(int i=0; i<pojo.getParticipant().length;i++)
	{
		psmt.setString(1, IDGenerator.getPrimaryKeyID("campus_event_program_numbering"));
		psmt.setString(2, pojo.getRegistrationIdArray()[i]);
		psmt.setString(3, pojo.getStageId());
		psmt.setString(4, pojo.getEventId());
		psmt.setString(5, pojo.getCategoryArray()[i]);
		psmt.setString(6, pojo.getProgramArray()[i]);
		psmt.setString(7, pojo.getParticipant()[i]);
		psmt.setString(8, pojo.getProgramNumberArray()[i]);
		count=psmt.executeUpdate();
	}
	if(count>0){
		result="true";
	}else{
		result="false";
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || psmt.isClosed())
			{
				psmt.close();
			}
			if(conn!=null || conn.isClosed())
			{conn.close();
			}
		}catch(Exception e)
		{e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : saveProgramNumberingDetails Ending");
		return result;
	}
	
	public ArrayList<eventRegVo> getAllocatedParticipants(String event,String stage) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getAllocatedParticipants Starting");
	Connection conn=null;
	java.sql.CallableStatement	psmt=null;
	ResultSet rs =null;
	ArrayList<eventRegVo> name = new ArrayList<eventRegVo>();
	try{
		
	conn=JDBCConnection.getSeparateConnection();
	psmt=conn.prepareCall("{CALL getAllocatedList(?)}");
	psmt.setString(1,event);
	
	rs=psmt.executeQuery();
	
	while(rs.next()){
		eventRegVo vo = new eventRegVo();
		vo.setParticipantId(rs.getString("captain_admissionNo"));
		vo.setParticipantName(rs.getString("name"));
		name.add(vo);
	}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null && (!psmt.isClosed())){psmt.close();}if(conn!=null || conn.isClosed()){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getAllocatedParticipants Ending");
		return name;
	}
	
	public ArrayList<eventRegVo> getMappedProgramNumberingDetails(String event,
			String stage) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getMappedProgramNumberingDetails Starting");
	Connection conn = null;
	PreparedStatement psmt =null;
	PreparedStatement pstmt1 = null;
	ResultSet rs,rs1 = null;
	String studentList[]=null;
	ArrayList<eventRegVo> list=new ArrayList<eventRegVo>();
	
	try{
		conn=JDBCConnection.getSeparateConnection();
		pstmt1=conn.prepareStatement(EventsSqlUtils.getMappedProgramNumberingDetails);
		pstmt1.setString(1, event);
		pstmt1.setString(2, stage);
		rs1=pstmt1.executeQuery();
	
		
	
	while(rs1.next()){
		eventRegVo vo = new eventRegVo();
		vo.setProgramNumberId(rs1.getString("program_number_id"));
		vo.setCategoryName(rs1.getString("categoryName"));
		vo.setCategoryId(rs1.getString("category"));
		vo.setProgrammeName(rs1.getString("program_name"));
		vo.setProgrammeId(rs1.getString("program"));
		vo.setParticipantName(rs1.getString("name"));
		vo.setParticipantId(rs1.getString("participants"));
		vo.setProgramNumber(rs1.getString("programnumber"));
		vo.setRegistrationId(rs1.getString("regId"));
			list.add(vo);
		}
	
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(pstmt1!=null || pstmt1.isClosed()){
				pstmt1.close();
			}
			if(conn!=null || conn.isClosed()){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT); 
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getMappedProgramNumberingDetails Ending");
	
		return list;
	}
	
	public ArrayList<eventRegVo> getcategorylist(String event_id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getcategorylist Starting");
	Connection conn =null;
	PreparedStatement psmt =null;
	ResultSet rs =null;
	
	
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
	
		psmt=conn.prepareStatement(EventsSqlUtils.getcatagorysList);
		psmt.setString(1, event_id);
		rs=psmt.executeQuery();
		while(rs.next()){
	
	
			eventRegVo vo = new eventRegVo();
			vo.setCategoryId(rs.getString("categoryId"));
		vo.setCategoryName(rs.getString("categoryName"));
			list.add(vo);
		}
	
	}catch(Exception e){
		e.printStackTrace();
	
	}		JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getcategorylist Ending");
		return list;
	}
	
	public String deleteProgramNumber(String[] id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: deleteProgramNumber Starting");
	Connection conn =null;
	PreparedStatement psmt= null;
	String result=null;
	int count=0;
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.deleteProgramNumber);
	
		for (int i=0;i<id.length;i++)
		{
			
		psmt.setString(1, id[i]);
		count=psmt.executeUpdate();
	}
	
	
	
	if(count>0){
		result="true";
	}else {
		result="false";
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || psmt.isClosed()){
				psmt.close();
			}
			if(conn!=null || conn.isClosed()){
				conn.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : deleteProgramNumber Ending");
		return result;
	}
	
	public String udpateIndividualSave(eventRegPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: saveStudentReg Starting");
	Connection conn=null;
	PreparedStatement psmt= null;
	PreparedStatement psmt2= null;
	String result =null;
	String key=null;
	int count =0;
	try{
		conn=JDBCConnection.getSeparateConnection();
	
		psmt2=conn.prepareStatement(EventsSqlUtils.udpateIndividualSave);
		psmt2.setString(1, pojo.getHouseId());
		psmt2.setString(2, pojo.getProgramCaptain());
		psmt2.setString(3, pojo.getParticipantsName());
		psmt2.setString(4, pojo.getInfo_staff());
		psmt2.setString(5, pojo.getInfo_synopsis());
		psmt2.setString(6, pojo.getInfo_req());
		psmt2.setString(7, pojo.getProgramCaptainHidden());
		count =psmt2.executeUpdate();
		
	count =psmt2.executeUpdate();
	
	if(count>0){
		result="updateTrue";
	}else{
		result="false";
		}
	
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : saveStudentReg Ending");
		return result;
	
	}
	
	
	public ArrayList<eventRegVo> getEventNameList_Group(String val) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getEventNameList_Group Starting");
	Connection conn= null;
	PreparedStatement psmt =null;
	ResultSet rs =null;
	ArrayList<eventRegVo> evName = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
	
		if(val.equalsIgnoreCase("Indiv")){
		psmt=conn.prepareStatement(EventsSqlUtils.getEventNameList_Indiv);
	}
	else
		psmt=conn.prepareStatement(EventsSqlUtils.getEventNameList_Group);
		System.out.println("^^^^^^^^^^^^"+psmt);
	
	rs=psmt.executeQuery();
	while(rs.next()){
		eventRegVo vo = new eventRegVo();
		vo.setEventId(rs.getString("event_id"));
		vo.setEventName(rs.getString("eventName"));
			evName.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || !(psmt.isClosed())){psmt.close();}if(conn!=null || !(conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventNameList_Group Ending");
		return evName;
	}
	
	
	public ArrayList<eventRegVo> getEventNameStudentReg() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getEventNameStudentReg Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> name = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getEventName);
		
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setEventId(rs.getString("event_Id"));
		vo.setEventName(rs.getString("eventName"));
			name.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null || !(psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null || !(conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventNameStudentReg Ending");
		return name;
	}
	
	
	public ArrayList<eventRegVo> getstagelist(String event_id, String flagVal) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getstagelist Starting");
	Connection conn =null;
	PreparedStatement psmt =null;
	ResultSet rs =null;
	
	
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
	
		if(flagVal.equalsIgnoreCase("stageSetting")){
		psmt=conn.prepareStatement(EventsSqlUtils.getstageList);
	}else{
		psmt=conn.prepareStatement(EventsSqlUtils.getstageList);
	}
	psmt.setString(1, event_id);
	rs=psmt.executeQuery();
	while(rs.next()){
		eventRegVo vo = new eventRegVo();
		vo.setStageId(rs.getString("stageId"));
		vo.setStageName(rs.getString("stageName"));
			list.add(vo);
		}
	
	}catch(Exception e){
		e.printStackTrace();
	
	}		JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getstagelist Ending");
		return list;
	}
	
	
	public ArrayList<eventRegVo> getparticipanteventList(String event_name) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getparticipantList Starting");
	Connection conn =null;
	PreparedStatement psmt =null;
	ResultSet rs =null;
	int count =0;
	
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.getEventParticipantseventList);
		psmt.setString(1, event_name);
	
	
		rs=psmt.executeQuery();
		while(rs.next()){
			count++;
	
			eventRegVo vo = new eventRegVo();
	
			vo.setSno(count);
			vo.setEventId(rs.getString("event_Id"));
		vo.setParticipantName(rs.getString("student"));
		vo.setEventName(rs.getString("eventName"));
		vo.setCategoryName(rs.getString("categoryName"));
		vo.setStageName(rs.getString("stageName"));
		vo.setProgName(rs.getString("program_name"));
			list.add(vo);
		}
	
	}catch(Exception e){
		e.printStackTrace();
	
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getparticipantList Ending");
		return list;
	}
	
	
	public ArrayList<eventRegVo> getParticipantList_Group(String evId,String catId, String progId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getParticipantList_Group Starting");
	PreparedStatement psmt=null;
	PreparedStatement psmt1=null;
	PreparedStatement psmt2=null;
	Connection conn= null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	ArrayList<eventRegVo> mainList=new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.GET_PARTICPANT_LIST_GROUP);
		psmt.setString(1, evId);
		psmt.setString(2, catId);
		psmt.setString(3, progId);
		
	rs=psmt.executeQuery();
	
	while(rs.next()){
	
		ArrayList<eventRegVo> subList = new ArrayList<eventRegVo>();
		eventRegVo vo=new eventRegVo();
		vo.setHouseName(rs.getString("housename"));
		vo.setRegistrationId(rs.getString("registration_id"));
		vo.setCaptainAdmnNo(rs.getString("captain_admissionNo"));
		String captain=rs.getString("captain_admissionNo");
		String[] ParticipantsAdmisNos = rs.getString("participants_admissionNo").split(",");
	
		for(int i=0;i<ParticipantsAdmisNos.length;i++){
			psmt2=conn.prepareStatement(EventsSqlUtils.GET_PARTICPANT_LIST_FROM_STUDENT_REGISTRATION);
			psmt2.setString(1, ParticipantsAdmisNos[i]);
			psmt2.setString(2, "ACY2");
	
			
			rs2=psmt2.executeQuery();
	
			while(rs2.next()){
				eventRegVo vo1 = new eventRegVo();
				String participant=rs2.getString("student_admissionno_var");
				vo1.setAdmissionNo(rs2.getString("student_admissionno_var"));
				vo1.setLocation(rs2.getString("Location_Name"));
	
				if(captain.equalsIgnoreCase(participant)){
					vo1.setStudentName(rs2.getString("student_fname_var")+" "+rs2.getString("student_lname_var")+"  (C)");
					vo1.setCaptainHighlight("captainHighlight");
				}
				else{
					vo1.setStudentName(rs2.getString("student_fname_var")+" "+rs2.getString("student_lname_var"));	
					vo1.setCaptainHighlight("participantHighlight");
				}
	
				vo1.setClassName(rs2.getString("classdetails_name_var"));
				vo1.setRollNumber(rs2.getString("rollNo"));
				vo1.setImageUrl(rs2.getString("imgurl_var"));
					subList.add(vo1);
				}
				vo.setParticipantsList(subList);
			}
			mainList.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || !(psmt.isClosed())){psmt.close();}
			if(psmt1!=null || !(psmt1.isClosed())){psmt1.close();}if(psmt2!=null || !(psmt2.isClosed())){psmt2.close();}if(conn!=null || !(conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
		return mainList;
	}
	
	
	public ArrayList<eventRegVo> getStageNameListGroup(String evId,
			String progId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getStageNameListGroup Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.getStageNameListGroup);
		if (evId.equalsIgnoreCase("")) {
		evId = "%%";
	}
	psmt.setString(1, evId);
	if (progId.equalsIgnoreCase("")) {
		progId = "%%";
	}
	psmt.setString(2, progId);
	System.out.println("getprogramName** : $$$$$$$$$$$$$$$$$$$$$$$$$$$$$::::::::::: ::::::"
					+ psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setStageId(rs.getString("stageId"));
		vo.setStageName(rs.getString("stageName"));
			list.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getStageNameListGroup Ending");
		return list;
	}
	
	public ArrayList<eventRegVo> getProgrammeDate(String event_id,
			String programId, String stageId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getStageNameListGroup Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
	
		if (event_id.equalsIgnoreCase("")) {
		event_id = "%%";
	}
	if (programId.equalsIgnoreCase("")) {
		programId = "%%";
	}
	if (stageId.equalsIgnoreCase("")) {
		stageId = "%%";
	}
	
	conn = JDBCConnection.getSeparateConnection();
	psmt = conn.prepareStatement(EventsSqlUtils.getProgrammeDate);
	psmt.setString(1, event_id);
	psmt.setString(2, programId);
	psmt.setString(3, stageId);
	System.out.println("Date " + psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setProgDate(HelperClass.convertDatabaseToUI(rs.getString("Program_date")));
			list.add(vo);
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
			try {if (rs != null || (!rs.isClosed())) {
				rs.close();
			}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getStageNameListGroup Ending");
		return list;
	}
	
	public ArrayList<eventRegVo> getTableByData(String event_id, String catId,
			String progId, String houseId, String accId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getStageNameListGroup Starting");
	Connection conn = null;
	PreparedStatement psmt = null, psmt1 = null, psmt2 = null, psmt3 = null, psmt4 = null, psmt5 = null;
	ResultSet rs = null, rs1 = null, rs2 = null, rs3 = null, rs4 = null, rs5 = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	int count=0;
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement("SELECT cep.program_name,ces.*,h.housename FROM campus_event_studentregistration ces LEFT JOIN campus_house_settings h ON h.house_id=ces.house_id LEFT JOIN campus_event_programcreation cep ON cep.program_id=ces.program_id WHERE ces.event_id=? and ces.program_id LIKE ? and ces.category_id=? and h.house_id LIKE ? ORDER BY h.housename,cep.program_name");
	psmt.setString(1, event_id);
	psmt.setString(2, progId);
	psmt.setString(3, catId);
	psmt.setString(4, houseId);
	System.out.println("GET_PARTICPANT_LIST  " + psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		String[] ParticipantsAdmisNos = rs.getString(
				"participants_admissionNo").split(",");
		for (int i = 0; i < ParticipantsAdmisNos.length; i++){
			String house=null;
			String StudentId = null;
			eventRegVo vo = new eventRegVo();
			System.out.println("STUDENT ID " + ParticipantsAdmisNos[i]);
			vo.setAdmissionno(ParticipantsAdmisNos[i]);
			count++;
			vo.setCount(count);
			psmt2 = conn.prepareStatement("SELECT l.`Location_Name`,cs.*,csc.*,cs.`student_id_int`,csc.student_rollno rollNo,csc.student_imgurl_var imgurl_var,cc.classdetails_name_var,css.`classsection_name_var`FROM campus_student cs LEFT JOIN campus_student_classdetails csc ON csc.student_id_int=cs.student_id_int LEFT JOIN campus_classdetail cc ON (cc.classdetail_id_int=csc.classdetail_id_int AND cc.locationId=csc.locationId) LEFT JOIN campus_classsection css ON (css.`classsection_id_int`=csc.`classsection_id_int` AND css.locationId=csc.locationId) LEFT JOIN `campus_location` l ON l.`Location_Id`=cs.`locationId`  WHERE cs.student_admissionno_var=? AND csc.fms_acadamicyear_id_int=?");
			psmt2.setString(1, ParticipantsAdmisNos[i]);
			psmt2.setString(2, accId);
			System.out.println("getStudentDetails" + psmt2);
			rs2 = psmt2.executeQuery();
			while (rs2.next()) {
	
				System.out.println("STUDENT ID "
						+ rs2.getString("student_id_int"));
				StudentId = rs2.getString("student_id_int");
				String participant = rs2
						.getString("student_admissionno_var");
				vo.setAdmissionNo(rs2
						.getString("student_admissionno_var"));
				vo.setLocation(rs2.getString("Location_Name"));
				vo.setStudentName(rs2.getString("student_fname_var")
						+ " " + rs2.getString("student_lname_var"));
				vo.setClassSec(rs2.getString("classdetails_name_var")
						+ "-" + rs2.getString("classsection_name_var"));
				vo.setRollNumber(rs2.getString("rollNo"));
				house=rs2.getString("student_house");
				vo.setProgName(rs.getString("program_name"));
				
			}
			psmt3 = conn.prepareStatement("SELECT `chest_no` FROM `campus_event_chestno_list` WHERE `admission_no`=?");
			psmt3.setString(1, ParticipantsAdmisNos[i]);
			rs3 = psmt3.executeQuery();
			System.out.println("ASSS " + psmt3);
			while (rs3.next()) {
				if(rs3.getString("chest_no")==null) {
					vo.setChestNoId("-");
				}
				else {
					vo.setChestNoId(rs3.getString("chest_no"));
				}
				
			}
	
			
				psmt5 = conn
						.prepareStatement("SELECT `housename` FROM `campus_house_settings` WHERE `house_id`=?");
				psmt5.setString(1, house);
				rs5 = psmt5.executeQuery();
				while (rs5.next()) {
					vo.setHouseName(rs5.getString("housename"));
					}
				
				list.add(vo);
			}
	
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || !(psmt.isClosed())){psmt.close();}if(conn!=null || !(conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getStageNameListGroup Ending");
		return list;
	}
	
	
	public ArrayList<eventRegVo> getChestNoForMarksEntry(String evId, String catId,String progId, String userId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getStageNameListGroup Starting");
	Connection conn =null;
	PreparedStatement psmt= null;
	ResultSet rs,rs2 =null;
	ArrayList<eventRegVo> mainList=new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT DISTINCT j.`noOfJudge`,cecl.chest_no,c.Max_marks,c.Programme_Name,p.captain_admissionNo FROM campus_event_studentregistration p LEFT JOIN campus_event_criteria c ON (c.Programme_Name= p.program_id)  join campus_event_chestno_list cecl on p.captain_admissionNo=cecl.admission_no LEFT JOIN `campus_event_programcreation` j ON (j.`event_id`=p.`event_Id` AND j.category_id=p.`category_id` AND j.`program_id`=p.`program_id`) WHERE p.event_id like ? AND p.category_id like ? AND p.program_id like ?");
		psmt.setString(1, evId);
		psmt.setString(2, catId);
		psmt.setString(3, progId);
	
		System.out.println("psmt "+psmt);
		rs=psmt.executeQuery();
		while(rs.next()){
			double totNumber=0;
			PreparedStatement totalnumber=conn.prepareStatement("SELECT CASE WHEN SUM(`marksForEachCriteria`) IS NULL THEN 0 ELSE SUM(`marksForEachCriteria`) END totNumber FROM `campus_event_marksentrycriteriawise` WHERE marksEntry_id IN(SELECT `marksEntry_id` FROM `campus_event_marksentry` WHERE `chestNo`=? AND `event_id`=? AND `category_id`=? AND `program_id`=?)");
			totalnumber.setString(1, rs.getString("chest_no"));
			totalnumber.setString(2, evId);
			totalnumber.setString(3, catId);
			totalnumber.setString(4, progId);
			System.out.println("totalnumber="+totalnumber);
			ResultSet rls=totalnumber.executeQuery();
			if(rls.next()){
				totNumber=rls.getDouble("totNumber");
			}
			rls.close();
			totalnumber.close();
			eventRegVo vo = new eventRegVo();//get the admission nos 
			vo.setMaxMarks(rs.getString("Max_marks"));
			vo.setChestNumber(rs.getString("chest_no"));
			vo.setCaptainAdmnNo(rs.getString("captain_admissionNo"));
			vo.setJudgeNos(rs.getString("noOfJudge").split(",").length);//string
			vo.setTotNumber(totNumber);
				mainList.add(vo);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
			try{
				if(psmt!=null || !(psmt.isClosed())){psmt.close();}if(conn!=null || !(conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
				}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getStageNameListGroup Ending");
			return mainList;
	}
	
	public ArrayList<eventRegVo> getHouseWise(String event_id, String catId,
			String progId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getStageNameListGroup Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
	
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.CHECK_HOUSE_WISE);
		psmt.setString(1, progId);
		psmt.setString(2, event_id);
		psmt.setString(3, catId);
		System.out.println("Date " + psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setHouseName(rs.getString("isHouseWise"));
			list.add(vo);
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null || (!rs.isClosed())) {
				rs.close();
			}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getStageNameListGroup Ending");
		return list;
	}
	
	public ArrayList<eventRegVo> getCriteriaForMarksEntry(String evId,String catId, String progId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getCriteriaForMarksEntry Starting");
	Connection conn =null;
	PreparedStatement psmt= null;
	ResultSet rs,rs2 =null;
	ArrayList<eventRegVo> list=new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT `Criteria`,`Criteria_Id` FROM `campus_event_criteria` WHERE `Event`=? AND `Category`=? AND `Programme_Name`=?");
	psmt.setString(1, evId);
	psmt.setString(2, catId);
	psmt.setString(3, progId);
	System.out.println("psmt "+psmt);
	rs=psmt.executeQuery();
	while(rs.next()){
		String[] criteriaArray =(rs.getString("Criteria")).split(",");
		for(int i=0;i<criteriaArray.length;i++){
			
			eventRegVo vo = new eventRegVo();//get the admission nos 
			vo.setCriteriaId((i+1)+rs.getString("Criteria_Id"));
				vo.setCriteria(criteriaArray[i]);
				list.add(vo);
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || !(psmt.isClosed())){psmt.close();}if(conn!=null || !(conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getCriteriaForMarksEntry Ending");
		return list;
	}
	
	public String saveMarksEntry(String accId, String catId, String progId,
			String evId, String[] marksObtained, String[] chestNo,String[] criteriaName, String[] criteriaMarks, String userId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: saveMarksEntry Starting");
	Connection conn =null;
	PreparedStatement psmt= null;
	PreparedStatement psmt1=null;
	PreparedStatement psmt2=null;
	ResultSet rs2=null;
	IDGenerator key = new IDGenerator();
	String result=null;
	String id=null;
	int count =0;
	int exist=0;
	int scoredMarks=0;
	
	try{
		conn=JDBCConnection.getSeparateConnection();
		for(int i=0;i<chestNo.length;i++){
			psmt2=conn.prepareStatement("SELECT COUNT(*),marksEntry_id,scoredMarks FROM campus_event_marksentry WHERE chestNo=? AND event_id=? AND category_id=? AND program_id=? AND AccYear=?");
		psmt2.setString(1, chestNo[i]);
		psmt2.setString(2, evId);
		psmt2.setString(3, catId);
		psmt2.setString(4, progId);
		psmt2.setString(5, accId);
		rs2=psmt2.executeQuery();
		if(rs2.next()){
			exist=rs2.getInt(1);
			id=rs2.getString(2);
			scoredMarks=rs2.getInt(3);
		}
		if(exist==0){
		psmt=conn.prepareStatement("INSERT INTO `campus_event_marksentry`(`marksEntry_id`,`chestNo`,`scoredMarks`,`event_id`,`category_id`,`program_id`,`AccYear`)VALUES(?,?,?,?,?,?,?);");
		id=key.getPrimaryKeyID("campus_event_marksentry");
		psmt.setString(1, id);
		psmt.setString(2, chestNo[i]);
		psmt.setString(3, marksObtained[i]);
		psmt.setString(4, evId);
		psmt.setString(5, catId);
		psmt.setString(6, progId);
		psmt.setString(7, accId);
		count = psmt.executeUpdate();
		
		
		psmt1=conn.prepareStatement("INSERT INTO `campus_event_marksentrycriteriawise`(marksEntry_id,`criteria`,`marksForEachCriteria`,judge_id)VALUES(?,?,?,?)");
		for(int j=0;j<criteriaMarks[i].split(":").length;j++){
		psmt1.setString(1, id);
		psmt1.setString(2, criteriaName[j]);
		
		
		
		for(int k=0;k<criteriaMarks[i].split(":")[j].split("-").length;k++) {
			psmt1.setString(3, criteriaMarks[i].split(":")[j].split("-")[k]);
			psmt1.setString(4, "Judge"+(k+1));
			count=psmt1.executeUpdate();
		}
		
		}
		
		}
		else{
			psmt=conn.prepareStatement("UPDATE  `campus_event_marksentry` SET `scoredMarks`=? WHERE marksEntry_id=?");
			psmt.setDouble(1, Double.parseDouble(marksObtained[i]));
			psmt.setString(2, id);
			
			count = psmt.executeUpdate();
			
			
			
			PreparedStatement dlpst=conn.prepareStatement("DELETE FROM campus_event_marksentrycriteriawise WHERE marksEntry_id=? AND criteria=?");
			for(int j=0;j<criteriaMarks[i].split(":").length;j++){
				dlpst.setString(1, id);
				dlpst.setString(2, criteriaName[j]);
				dlpst.executeUpdate();
				
			}
			dlpst.close();
			
			
			
			
			
			psmt1=conn.prepareStatement("INSERT INTO `campus_event_marksentrycriteriawise`(marksEntry_id,`criteria`,`marksForEachCriteria`,judge_id)VALUES(?,?,?,?)");
			for(int j=0;j<criteriaMarks[i].split(":").length;j++){
			psmt1.setString(1, id);
			psmt1.setString(2, criteriaName[j]);
			
			
			
			for(int k=0;k<criteriaMarks[i].split(":")[j].split("-").length;k++) {
				psmt1.setString(3, criteriaMarks[i].split(":")[j].split("-")[k]);
				psmt1.setString(4, "Judge"+(k+1));
				count=psmt1.executeUpdate();
			}
			
			}
			
		}
		
		
	}
	if(count>0){
		result="true";
	}else{
		result="false";
		}
		psmt1.close();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || !(psmt.isClosed())){psmt.close();}if(conn!=null || !(conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : saveMarksEntry Ending");
		return result;
	}
	
	
	public ArrayList<eventRegVo> getTableByHouse(String evId, String catId,
			String progId, String houseId, String accId, String stageId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
	PreparedStatement psmt, psmt1, psmt2 = null, psmt3 = null, psmt4 = null, psmt5 = null;
	Connection conn = null;
	ResultSet rs = null;
	ResultSet rs2 = null, rs3 = null, rs4 = null, rs5 = null;
	
	if (houseId == null || houseId == " ") {
	houseId = "%%";
	}
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	int count = 0;
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement("SELECT ces.*,h.housename FROM campus_event_studentregistration ces LEFT JOIN campus_house_settings h ON h.house_id=ces.house_id WHERE ces.event_id=? and ces.program_id LIKE ? and ces.category_id=?");
		psmt.setString(1, evId);
		psmt.setString(2, progId);
		psmt.setString(3, catId);
		
	System.out.println("GET_PARTICPANT_LIST  " + psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
	
		// vo.setHouseName(rs.getString("housename"));
		String[] ParticipantsAdmisNos = rs.getString(
				"participants_admissionNo").split(",");
		for (int i = 0; i < ParticipantsAdmisNos.length; i++) {
			String StudentId = null;
			eventRegVo vo = new eventRegVo();
			System.out.println("STUDENT ID " + ParticipantsAdmisNos[i]);
			vo.setAdmissionno(ParticipantsAdmisNos[i]);
			count++;
			vo.setCount(count);
			psmt2 = conn
					.prepareStatement("SELECT cep.program_name,l.`Location_Name`,cs.*,csc.*,cs.`student_id_int`,csc.student_rollno rollNo,csc.student_imgurl_var imgurl_var,cc.classdetails_name_var,css.`classsection_name_var`FROM campus_student cs LEFT JOIN campus_student_classdetails csc ON csc.student_id_int=cs.student_id_int LEFT JOIN campus_classdetail cc ON (cc.classdetail_id_int=csc.classdetail_id_int AND cc.locationId=csc.locationId) LEFT JOIN campus_classsection css ON (css.`classsection_id_int`=csc.`classsection_id_int` AND css.locationId=csc.locationId) LEFT JOIN `campus_location` l ON l.`Location_Id`=cs.`locationId` JOIN campus_event_studentregistration s ON cs.student_admissionno_var=s.participants_admissionNo JOIN `campus_event_programcreation` cep ON cep.program_id=s.`program_id`  WHERE cs.student_admissionno_var=? AND csc.fms_acadamicyear_id_int=? AND csc.student_house=?");
			psmt2.setString(1, ParticipantsAdmisNos[i]);
			psmt2.setString(2, accId);
			psmt2.setString(3, houseId);
			System.out.println("getStudentDetails" + psmt2);
			rs2 = psmt2.executeQuery();
			while (rs2.next()) {
	
				
				if(rs2.getString("student_house").equalsIgnoreCase(houseId)){
					
					
					System.out.println("House "+rs2.getString("student_house"));
					System.out.println("IN IF LOOP");
				StudentId = rs2.getString("student_id_int");
				String participant = rs2
						.getString("student_admissionno_var");
				vo.setAdmissionNo(rs2
						.getString("student_admissionno_var"));
				vo.setLocation(rs2.getString("Location_Name"));
				vo.setStudentName(rs2.getString("student_fname_var")
						+ " " + rs2.getString("student_lname_var"));
				vo.setClassSec(rs2.getString("classdetails_name_var")
						+ "-" + rs2.getString("classsection_name_var"));
				vo.setRollNumber(rs2.getString("rollNo"));
				vo.setProgName(rs2.getString("program_name"));
				psmt3 = conn.prepareStatement("SELECT `chest_no` FROM `campus_event_chestno_list` WHERE `admission_no`=?");
				psmt3.setString(1, ParticipantsAdmisNos[i]);
				rs3 = psmt3.executeQuery();
				System.out.println("ASSS " + psmt3);
				while (rs3.next()) {
					if(rs3.getString("chest_no")==null || rs3.getString("chest_no").equalsIgnoreCase("")) {
						vo.setChestNoId("-");
					}
					else {
						vo.setChestNoId(rs3.getString("chest_no"));
					}
					
				}
		
				if (!houseId.equalsIgnoreCase("")) {
					psmt5 = conn.prepareStatement("SELECT `housename` FROM `campus_house_settings` WHERE `house_id`=?");
					psmt5.setString(1, houseId);
					rs5 = psmt5.executeQuery();
					while (rs5.next()) {
						vo.setHouseName(rs5.getString("housename"));
					}
				} else {
		
					System.out.println("in else loop");
					psmt4 = conn
							.prepareStatement("SELECT `student_house` FROM `campus_student_classdetails` WHERE `student_id_int`=?");
					psmt4.setString(1, StudentId);
					rs4 = psmt4.executeQuery();
					while (rs4.next()) {
						psmt5 = conn
								.prepareStatement("SELECT `housename` FROM `campus_house_settings` WHERE `house_id`=?");
						psmt5.setString(1, rs4.getString("student_house"));
						rs5 = psmt5.executeQuery();
						while (rs5.next()) {
							vo.setHouseName(rs5.getString("housename"));
							}
						}
					}
				list.add(vo);
				}
				
			}
			
				
			}
	
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
		return list;
	}
	
				public eventRegVo getEventDetail(String event_id, String catId,
						String progId, String houseId) {
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
				PreparedStatement psmt, psmt1, psmt2 = null;
				Connection conn = null;
				ResultSet rs = null;
				ResultSet rs1 = null, rs2 = null;
				eventRegVo vo = new eventRegVo();
				try {
					conn = JDBCConnection.getSeparateConnection();
					if(!progId.equalsIgnoreCase("")){
					psmt = conn.prepareStatement("SELECT cer.`eventName`,cec.`categoryName`,cepc.`program_name`,cepc.`programDate` FROM `campus_event_registration` cer LEFT JOIN `campus_event_category` cec ON cer.`event_Id`=cec.`eventId` LEFT JOIN `campus_event_programcreation` cepc ON cepc.`category_id`=cec.`categoryId` WHERE cer.`event_Id`=? AND cec.`categoryId`=? AND cepc.`program_id`=?");
					psmt.setString(1, event_id);
					psmt.setString(2, catId);
					psmt.setString(3, progId);
				} else {
					psmt = conn.prepareStatement("SELECT cer.`eventName`,cec.`categoryName`,cepc.`program_name`,cepc.`programDate` FROM `campus_event_registration` cer LEFT JOIN `campus_event_category` cec ON cer.`event_Id`=cec.`eventId` LEFT JOIN `campus_event_programcreation` cepc ON cepc.`category_id`=cec.`categoryId` WHERE cer.`event_Id`=? AND cec.`categoryId`=?");
					psmt.setString(1, event_id);
					psmt.setString(2, catId);
				}
				
				
				
				System.out.println("PROGRAMME DETAILS "+psmt);
				rs = psmt.executeQuery();
				while (rs.next()) {
					vo.setEventName(rs.getString("eventName"));
					vo.setCategoryName(rs.getString("categoryName"));
					vo.setProgrammeName(rs.getString("program_name"));
					vo.setProg_date(rs.getString("programDate"));
				}
				psmt1 = conn
						.prepareStatement("SELECT `Stage` FROM `campus_event_program_scheduling` WHERE `Event_name`=? AND `Program_name`=?");
				psmt1.setString(1, event_id);
				psmt1.setString(2, progId);
				rs1 = psmt1.executeQuery();
				System.out.println("STAGE " + psmt1);
				while (rs1.next()) {
					psmt2 = conn
							.prepareStatement("SELECT `stageName` FROM `campus_event_stage` WHERE `stageId`=?");
					psmt2.setString(1, rs1.getString("Stage"));
					rs2 = psmt2.executeQuery();
					while (rs2.next()) {
						vo.setStageName(rs2.getString("stageName"));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.END_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
					return vo;
				}
				
				public ArrayList<eventRegVo> getStudents(String event_id, String catId,
						String progId, String AcyYear, String certificateon) {
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
				PreparedStatement psmt2 = null, psmt3 = null,psmt=null,psmt1=null;
				Connection conn = null;
				ResultSet rs2 = null, rs3 = null,rs=null,rs1=null;
				ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
				int i = 0;
				String[] plcae = { "First", "Second", "Third" };
				try {
					conn = JDBCConnection.getSeparateConnection();
				
					System.out.println("CERTIFICATE ON" + certificateon);
				if (!certificateon.equalsIgnoreCase("merit")) {
				
					System.out.println("IN IF");
					psmt2 = conn.prepareStatement(EventsSqlUtils.PARTICIPATORS_LIST);
					psmt2.setString(1, event_id);
					psmt2.setString(2, catId);
					psmt2.setString(3, progId);
					psmt2.setString(4, AcyYear);
					System.out.println("PARTICIPATION "+psmt2);
					rs2 = psmt2.executeQuery();
					while (rs2.next()) {
						eventRegVo vo = new eventRegVo();
						vo.setPlace("Participated");
						vo.setChestNoId(rs2.getString("chestNo"));
						psmt3 = conn
								.prepareStatement("SELECT l.`Location_Name`,cs.*,csc.*,cs.`student_id_int`,csc.student_rollno rollNo,cho.housename,csc.student_imgurl_var imgurl_var,cc.classdetails_name_var,css.`classsection_name_var`FROM campus_student cs LEFT JOIN campus_student_classdetails csc ON csc.student_id_int=cs.student_id_int LEFT JOIN campus_classdetail cc ON (cc.classdetail_id_int=csc.classdetail_id_int AND cc.locationId=csc.locationId) LEFT JOIN campus_classsection css ON (css.`classsection_id_int`=csc.`classsection_id_int` AND css.locationId=csc.locationId) LEFT JOIN `campus_location` l ON l.`Location_Id`=cs.`locationId` JOIN campus_house_settings cho ON cho.house_id=csc.student_house  WHERE cs.student_admissionno_var=(SELECT `admission_no` FROM `campus_event_chestno_list` WHERE `chest_no`=?) AND csc.fms_acadamicyear_id_int=?");
						
						psmt3.setString(1, rs2.getString("chestNo"));
						psmt3.setString(2, AcyYear);
						rs3 = psmt3.executeQuery();
						while (rs3.next()) {
							vo.setAdmissionNo(rs3
									.getString("student_admissionno_var"));
							vo.setStudentName(rs3.getString("student_fname_var")
									+ " " + rs3.getString("student_lname_var"));
							vo.setClassSec(rs3.getString("classdetails_name_var")
									+ "-" + rs3.getString("classsection_name_var"));
							vo.setHouseName(rs3.getString("housename"));	
						}
						list.add(vo);
					}
				} else {
					
					
					

					psmt = conn.prepareStatement("SELECT GROUP_CONCAT(chestNo) chestNo FROM `campus_event_marksentry` WHERE`event_id`=? AND `category_id`=? AND `program_id`=? AND `AccYear`=? GROUP BY  `scoredMarks` ORDER BY `scoredMarks` DESC LIMIT 3 ");
					psmt.setString(1, event_id);
					psmt.setString(2, catId);
					psmt.setString(3, progId);
					psmt.setString(4, AcyYear);
					
					
					int j=0;
					rs = psmt.executeQuery();
					System.out.println("MERIT LIST "+psmt);
					while (rs.next()) {
						String chestNoArray[]=rs.getString("chestNo").split(",");
						for(int m=0;m<chestNoArray.length;m++) {
							
							
							psmt1 = conn.prepareStatement("SELECT `admission_no` FROM `campus_event_chestno_list` WHERE `chest_no`=?");
							psmt1.setString(1, chestNoArray[m]);
							rs1=psmt1.executeQuery();
				              while(rs1.next()){
				            	 
				            	  PreparedStatement prt=conn.prepareStatement("SELECT `participants_admissionNo` FROM `campus_event_studentregistration` WHERE `captain_admissionNo`=? AND `event_id`=? AND participateType='group'");
				            	  prt.setString(1, rs1.getString("admission_no"));
				            	  prt.setString(2, event_id);
				            	  ResultSet prs=prt.executeQuery();
				            	 
				            	  if(prs.next()) {
				            		 String participantNo[]=prs.getString("participants_admissionNo").split(",");
				            	
				            	  for(int p=0;p<participantNo.length;p++) {
				            		  eventRegVo vo = new eventRegVo();
				            			psmt2 = conn.prepareStatement("SELECT l.`Location_Name`,cs.*,csc.*, csc.student_rollno rollNo,csc.student_imgurl_var imgurl_var,cc.classdetails_name_var,cho.housename,css.classsection_name_var FROM campus_student cs LEFT JOIN campus_student_classdetails csc ON csc.student_id_int=cs.student_id_int LEFT JOIN campus_classdetail cc ON (cc.classdetail_id_int=csc.classdetail_id_int AND cc.locationId=csc.locationId) LEFT JOIN `campus_location` l ON l.`Location_Id`=cs.`locationId` JOIN campus_house_settings cho ON cho.house_id=csc.student_house LEFT JOIN campus_classsection css ON (css.`classsection_id_int`=csc.`classsection_id_int` AND css.locationId=csc.locationId)  WHERE cs.student_admissionno_var=? AND csc.fms_acadamicyear_id_int=?");
										psmt2.setString(1,participantNo[p]);
										psmt2.setString(2, AcyYear);
										rs2 = psmt2.executeQuery();
										while (rs2.next()) {
											vo.setChestNoId(chestNoArray[m]);
											vo.setPlace(plcae[j]);
											String participant = rs2.getString("student_admissionno_var");
											vo.setStudentName(rs2.getString("student_fname_var")+" "+ rs2.getString("student_lname_var"));
											vo.setAdmissionNo(rs2.getString("student_admissionno_var"));
											vo.setLocation(rs2.getString("Location_Name"));
											vo.setClassSec(rs2.getString("classdetails_name_var")+"-"+rs2.getString("classsection_name_var"));
											vo.setRollNumber(rs2.getString("rollNo"));
											vo.setImageUrl(rs2.getString("imgurl_var"));
											vo.setHouseName(rs2.getString("housename"));	
											list.add(vo);
											}
				            	  }
							
				            	  }
				            	  else {

				            		  eventRegVo vo = new eventRegVo();
				            			psmt2 = conn.prepareStatement("SELECT l.`Location_Name`,cs.*,csc.*, csc.student_rollno rollNo,csc.student_imgurl_var imgurl_var,cc.classdetails_name_var,cho.housename,css.classsection_name_var FROM campus_student cs LEFT JOIN campus_student_classdetails csc ON csc.student_id_int=cs.student_id_int LEFT JOIN campus_classdetail cc ON (cc.classdetail_id_int=csc.classdetail_id_int AND cc.locationId=csc.locationId) LEFT JOIN `campus_location` l ON l.`Location_Id`=cs.`locationId` JOIN campus_house_settings cho ON cho.house_id=csc.student_house LEFT JOIN campus_classsection css ON (css.`classsection_id_int`=csc.`classsection_id_int` AND css.locationId=csc.locationId)  WHERE cs.student_admissionno_var=? AND csc.fms_acadamicyear_id_int=?");
										psmt2.setString(1,rs1.getString("admission_no"));
										psmt2.setString(2, AcyYear);
										
										rs2 = psmt2.executeQuery();
										while (rs2.next()) {
											vo.setChestNoId(chestNoArray[m]);
											vo.setPlace(plcae[j]);
											String participant = rs2.getString("student_admissionno_var");
											vo.setStudentName(rs2.getString("student_fname_var")+" "+ rs2.getString("student_lname_var"));
											vo.setAdmissionNo(rs2.getString("student_admissionno_var"));
											vo.setLocation(rs2.getString("Location_Name"));
											vo.setClassSec(rs2.getString("classdetails_name_var")+"-"+rs2.getString("classsection_name_var"));
											vo.setRollNumber(rs2.getString("rollNo"));
											vo.setImageUrl(rs2.getString("imgurl_var"));
											vo.setHouseName(rs2.getString("housename"));	
											list.add(vo);
											}
				            	  
				            		  
				            	  }
				            	  
				            	  prs.close();
				            	  prt.close();
				                  }
							
						}
						j++;
						}
					
				
					
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.END_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
					return list;
				}
	
	
	public ArrayList<eventRegVo> getEventStudentRegList(String evId,String progId,String catId, String houseId, String accId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
	PreparedStatement psmt,psmt1,psmt2 = null;
	Connection conn= null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	ArrayList<eventRegVo> mainList=new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(EventsSqlUtils.GET_PARTICPANT_LIST_FROM_EVENT_REGISTRATION);
	
		if(evId.equalsIgnoreCase("")){
		psmt.setString(1, "%%");
	}psmt.setString(1, evId);
	
	if(progId.equalsIgnoreCase("")){
		psmt.setString(2, "%%");
	}psmt.setString(2, progId);
	
	if(catId.equalsIgnoreCase("")){
		psmt.setString(3, "%%");
	}psmt.setString(3, catId);
	
	if(houseId.equalsIgnoreCase("")){
		psmt.setString(4,"%%");
	}psmt.setString(4, houseId);
	
	System.out.println("studentData  new"+psmt);
	rs=psmt.executeQuery();
	
	while(rs.next()){
	
		ArrayList<eventRegVo> subList = new ArrayList<eventRegVo>();
		eventRegVo vo=new eventRegVo();
		vo.setHouseName(rs.getString("housename"));
		vo.setRegistrationNo(rs.getString("registrationNo"));
		vo.setRegistrationId(rs.getString("registration_id"));
		vo.setCaptainAdmnNo(rs.getString("captain_admissionNo"));
		String captain=rs.getString("captain_admissionNo");
		String[] ParticipantsAdmisNos = rs.getString("participants_admissionNo").split(",");
	
		for(int i=0;i<ParticipantsAdmisNos.length;i++){
			psmt2=conn.prepareStatement(EventsSqlUtils.GET_PARTICPANT_LIST_FROM_STUDENT_REGISTRATION);
			psmt2.setString(1, ParticipantsAdmisNos[i]);
			psmt2.setString(2, accId);
	
			System.out.println("getStudentDetails"+psmt2);
			rs2=psmt2.executeQuery();
	
			while(rs2.next()){
				eventRegVo vo1 = new eventRegVo();
				String participant=rs2.getString("student_admissionno_var");
				vo1.setAdmissionNo(rs2.getString("student_admissionno_var"));
				vo1.setLocation(rs2.getString("Location_Name"));
	
				if(captain.equalsIgnoreCase(participant)){
					vo1.setStudentName(rs2.getString("student_fname_var")+" "+rs2.getString("student_lname_var")+"  (C)");
					vo1.setCaptainHighlight("captainHighlight");
				}
				else{
					vo1.setStudentName(rs2.getString("student_fname_var")+" "+rs2.getString("student_lname_var"));	
					vo1.setCaptainHighlight("participantHighlight");
				}
	
				vo1.setClassName(rs2.getString("classdetails_name_var"));
				vo1.setRollNumber(rs2.getString("rollNo"));
				vo1.setImageUrl(rs2.getString("imgurl_var"));
					subList.add(vo1);
				}
				vo.setParticipantsList(subList);
	
			}
			mainList.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
		return mainList;
	}
	
	public ArrayList<eventRegVo> getCertificateDetail(String event_id,
			String catId, String progId, String accId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
	PreparedStatement psmt, psmt1, psmt2 = null;
	Connection conn = null;
	ResultSet rs = null;
	ResultSet rs1 = null, rs2 = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn
				.prepareStatement("SELECT cer.`eventName`,cec.`categoryName`,cepc.`program_name`,cepc.`programDate` FROM `campus_event_registration` cer LEFT JOIN `campus_event_category` cec ON cer.`event_Id`=cec.`eventId` LEFT JOIN `campus_event_programcreation` cepc ON cepc.`category_id`=cec.`categoryId` WHERE cer.`event_Id`=? AND cec.`categoryId`=? AND cepc.`program_id`=? AND `accyer_ID`=?");
	psmt.setString(1, event_id);
	psmt.setString(2, catId);
	psmt.setString(3, progId);
	psmt.setString(4, accId);
	System.out.println(psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
	
		System.out.println(rs.getString("eventName"));
		System.out.println(rs.getString("categoryName"));
		System.out.println(rs.getString("program_name"));
		System.out.println(rs.getString("programDate"));
	
		vo.setEventName(rs.getString("eventName"));
		vo.setCategoryName(rs.getString("categoryName"));
		vo.setProgrammeName(rs.getString("program_name"));
		vo.setProg_date(rs.getString("programDate"));
		/*
		 * psmt1=conn.prepareStatement(ReportsMenuSqlConstants.GET_YEAR);
		 * psmt1.setString(1, accId); rs1=psmt1.executeQuery();
		 * while(rs1.next()){
		 * vo.setAccyear(rs1.getString("acadamic_year")); }
		 */
		list.add(vo);
	}
	
	System.out.println("SIZE " + list.size());
	} catch (Exception e) {
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
		return list;
	}
	
	public ArrayList<eventRegVo> getProgramScheduledList(String eventId,
			String prog_date) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getProgramScheduledList Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	int count = 0;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	
	try {
	
		conn = JDBCConnection.getSeparateConnection();
		if (prog_date.equalsIgnoreCase("") || (prog_date == null)) {
		psmt = conn
				.prepareStatement(EventsSqlUtils.getProgramScheduledList_eventId);
		psmt.setString(1, eventId);
	} else {
		psmt = conn
				.prepareStatement(EventsSqlUtils.getProgramScheduledList_progDate);
		psmt.setString(1, eventId);
		psmt.setString(2, prog_date);
	}
	
	rs = psmt.executeQuery();
	
	System.out.println("query in daoimpl pdf"+psmt);
	
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		count++;
		vo.setSno(count);
		System.out.println("sno>>>>>" + vo.getSno());
		vo.setEventName(rs.getString("eventName"));
		vo.setEventId(rs.getString("event_Id"));
		vo.setProgrammeName(rs.getString("program_name"));
		vo.setProgId(rs.getString("program_id"));
		vo.setProg_date(rs.getString("programDate"));
	
		vo.setStageName(rs.getString("stageName"));
		vo.setStageId(rs.getString("stageId"));
		vo.setGreenRoomName(rs.getString("greenroom_name"));
		vo.setGreenRoomId(rs.getString("greenroom_id"));
		vo.setProg_time(rs.getString("Program_time"));
		vo.setProgDate((rs.getString("programDate")+" & "+(rs.getString("Program_time"))));
		vo.setMakeup(rs.getString("Makeup_report"));
		vo.setBackStage(rs.getString("BackStage_report"));
	
			list.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null || psmt.isClosed()) {
				psmt.close();
			}
			if (conn != null || conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getProgramScheduledList Ending");
	
		return list;
	}
	
	public String saveChestNoRegister(eventRegPojo pojo){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: saveChestNumber Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	String result = null;
	int count = 0;
	
	String id = null;
	try {
		System.out.println("inside campus_event_chestnumber daoimpl");
	id = IDGenerator.getPrimaryKeyID("campus_event_chestnumber");
	conn = JDBCConnection.getSeparateConnection();
	if (pojo.getHiddenStageId().equalsIgnoreCase("")
			|| pojo.getHiddenStageId() == null) {
		psmt = conn
				.prepareStatement(EventsSqlUtils.SAVE_CHEST_NO_REGISTRATION);
		// saveProgramscheduling
		psmt.setString(1, id);
		psmt.setString(2, pojo.getEventId());
		psmt.setString(3, pojo.getCategoryId());
		psmt.setString(4, pojo.getProgName());
	
		System.out.println(psmt);
		count = psmt.executeUpdate();
		if (count > 0) {
			result = "true";
		} else {
			result = "false";
		}
	} else {
		psmt = conn
				.prepareStatement(EventsSqlUtils.UPDATE_CHESTNO_GENERATION);
	
		psmt.setString(1, pojo.getEventId());
		psmt.setString(2, pojo.getCategoryId());
		psmt.setString(3, pojo.getProgName());
		psmt.setString(4, pojo.getHiddenStageId());
	
		System.out.println(psmt);
		count = psmt.executeUpdate();
		if (count > 0) {
			result = "updateTrue";
		} else {
			result = "false";
			}
	
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
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
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : saveChestNumber Ending");
		return result;
	}
	
	public Set<eventRegVo> getEventStudentRegChestNoList(String evId,
			String catId, String progId, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
	PreparedStatement psmt = null, psmt2 = null, psmt3 = null;
	Connection conn = null;
	ResultSet rs = null, rs1 = null;
	ResultSet rs2 = null;
	int count = 0,count2=0;
	Set<eventRegVo> mainList = new HashSet<eventRegVo>();
	ArrayList<String> stulist = new ArrayList<String>();
	Set<String> hs = new HashSet<String>();
	String[] ParticipantsAdmisNos = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		
		psmt = conn.prepareStatement("SELECT captain_admissionNo FROM  campus_event_studentregistration WHERE event_id LIKE ? AND category_id LIKE ? AND program_id LIKE ?");
		psmt.setString(1, evId);
		psmt.setString(2, catId);
		psmt.setString(3, progId);
		System.out.println("studentData" + psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		
			stulist.add(rs.getString("captain_admissionNo"));
		
	}
	System.out.println(stulist.size());
	for (int i = 0; i < stulist.size(); i++) {
		String a1 = stulist.get(i);
		for (int j = i + 1; j < stulist.size() - 1; j++) {
			String a2 = stulist.get(j);
			if ((a1.equals(a2))) {
				stulist.remove(a2);
				hs.addAll(stulist);
				stulist.clear();
				stulist.addAll(hs);
			}
		}
	}
	System.out.println(stulist.size());
	for (int i = 0; i < stulist.size(); i++) {
		eventRegVo vo1 = new eventRegVo();
	
		psmt3 = conn.prepareStatement("SELECT COUNT(*) FROM campus_event_chestno_list WHERE admission_no=?");
		psmt3.setString(1, stulist.get(i));
		rs1 = psmt3.executeQuery();
		while (rs1.next()) {
			count = rs1.getInt(1);
		}
		System.out.println(count);
		if (count <= 0) {
			System.out.println("************************ Inside count ************************");
			psmt3 = conn.prepareStatement("INSERT INTO campus_event_chestno_list(admission_no,chest_no) VALUES(?,?)");
			psmt3.setString(1, stulist.get(i));
			psmt3.setString(2, "");
			//psmt3.setString(2, "");
			System.out.println("Inserting Pending Admission No"+psmt3);
			System.out.println("************************ Inside count ************************");
			psmt3.executeUpdate();
		}
	
		psmt2 = conn.prepareStatement(EventsSqlUtils.GET_PARTICPANT_CHESTNO_LIST_FROM_STUDENT_REGISTRATION);
		psmt2.setString(1, stulist.get(i));
		psmt2.setString(2, evId);
		psmt2.setString(3, catId);
		psmt2.setString(4, progId);
		System.out.println(psmt2);
		rs2 = psmt2.executeQuery();
		while (rs2.next()) {
			count2++;
			vo1.setSno(count2);
			vo1.setAdmissionNo(rs2.getString("student_admissionno_var"));
			vo1.setStudentName(rs2.getString("student_fname_var") + " "+ rs2.getString("student_lname_var"));
			vo1.setLocation(rs2.getString("Location_Name"));
			vo1.setImageUrl(rs2.getString("imgurl_var"));
			if (rs2.getString("chest_no")==null|| rs2.getString("chest_no").equalsIgnoreCase("")|| rs2.getString("chest_no").equalsIgnoreCase("--"))
			{
				vo1.setChestNoId("--");
			} else {
				vo1.setChestNoId(rs2.getString("chest_no"));
				}
	
				mainList.add(vo1);
			}
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs1 != null || !rs1.isClosed()) {
				rs1.close();
			}
			if (rs != null || !rs.isClosed()) {
				rs.close();
			}
			if (rs2 != null || !rs2.isClosed()) {
				rs2.close();
			}
			if (psmt != null || !psmt.isClosed()) {
				psmt.close();
			}
			if (psmt2 != null || !psmt2.isClosed()) {
				psmt2.close();
			}
			if (psmt3 != null || !psmt3.isClosed()) {
				psmt3.close();
			}
			if (conn != null || conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
		return mainList;
	}
	
	public ArrayList<eventRegVo> getEventNameList() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getGreenRoom Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.GET_EVENT_NAME);
		System.out.println("'&&&&&&&&&&&&&&&&||||" + psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setEventName(rs.getString("eventName"));
		vo.setEventId(rs.getString("event_Id"));
			list.add(vo);
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null || psmt.isClosed()) {
				psmt.close();
			}
			if (conn != null || conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getGreenRoom Ending");
		return list;
	}
	
	public ArrayList<eventRegVo> getVolunteerList(String eveId, String locId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getProgramSettingList Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	String result = null;
	ResultSet rs = null;
	int count = 0;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
	
		psmt = conn.prepareStatement(EventsSqlUtils.GET_VOLANTEER_LIST);
		psmt.setString(1, locId);
		psmt.setString(2, eveId);
		System.out.println("******" + psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		count++;
		eventRegVo vo = new eventRegVo();
		vo.setVolanteerId(rs.getString("volunteer_id"));
		vo.setVolanteerAdmissionNo(rs.getString("admission_no") + "-"
				+ rs.getString("student"));
		vo.setGreenRoomName(rs.getString("greenroom_name"));
		vo.setStageName(rs.getString("stageName"));
		vo.setStartTime(rs.getString("start_time"));
		vo.setEndTime(rs.getString("end_time"));
			list.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null || psmt.isClosed()) {
				psmt.close();
			}
			if (conn != null || conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getProgramSettingList Ending");
		return list;
	}
	
	public ArrayList<eventRegVo> getDataforUpdateVolanteer(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl:getDataforUpdateVolanteer Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.update);
		psmt.setString(1, id);
		System.out.println("update*****" + psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setVolanteerId(rs.getString("volunteer_id"));
		vo.setEventId(rs.getString("event_id"));
		vo.setLocId(rs.getString("loc_id"));
		vo.setVolanteerAdmissionNo(rs.getString("AdmissionNo"));
		vo.setGreenRoomId(rs.getString("greenroom_id"));
		vo.setStageId(rs.getString("stage_id"));
		vo.setStartTime(rs.getString("start_time"));
		vo.setEndTime(rs.getString("end_time"));
			list.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl :getDataforUpdateVolanteer Ending");
		return list;
	}
	
	public String savevolunteer(eventRegPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in VolanteerRegistrationDaoImpl :SaveVolanteer Starting");
	PreparedStatement pst = null;
	Connection conn = null;
	int result = 0;
	String studentData = null;
	try {
		conn = JDBCConnection.getSeparateConnection();
		if (pojo.getVolanteerhiddenid().equalsIgnoreCase("")
			|| pojo.getVolanteerhiddenid() == null) {
		pst = conn
				.prepareStatement(SQLUtilConstants.SAVE_VOLANTEER_DETAIL);
		pst.setString(1, IDGenerator
				.getPrimaryKeyID("campus_event_volunteerregistation"));
		pst.setString(2, pojo.getEventId());
		pst.setString(3, pojo.getLocation());
		pst.setString(4, pojo.getVolanteeradmissionno());
		pst.setString(5, pojo.getGreenRoomName());
		pst.setString(6, pojo.getStageName());
		pst.setString(7, pojo.getStarttime());
		pst.setString(8, pojo.getEndtime());
		pst.setString(9, pojo.getCreateUser());
	
		System.out.println("---------------------------" + pst);
		result = pst.executeUpdate();
		if (result > 0) {
			studentData = "success";
		} else {
			studentData = "fail";
		}
	} else {
		pst = conn
				.prepareStatement(SQLUtilConstants.UPDATE_VOLANTEER_DETAIL);
		pst.setString(1, pojo.getEventId());
		pst.setString(2, pojo.getLocation());
		pst.setString(3, pojo.getVolanteeradmissionno());
		pst.setString(4, pojo.getGreenRoomName());
		pst.setString(5, pojo.getStageName());
		pst.setString(6, pojo.getStarttime());
		pst.setString(7, pojo.getEndtime());
		pst.setString(8, pojo.getEditUser());
		pst.setTimestamp(9, HelperClass.getCurrentTimestamp());
		pst.setString(10, pojo.getVolanteerhiddenid());
	
		System.out.println(pst);
		result = pst.executeUpdate();
		if (result > 0) {
			studentData = "update";
		} else {
			studentData = "fail";
			}
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
	
			if (pst != null && (!pst.isClosed())) {
				pst.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in VolanteerRegistrationDaoImpl : SaveVolanteer Ending");
		return studentData;
	}
	
	public ArrayList<eventRegVo> getEventName(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getGreenRoom Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.GET_EVENT_NAME);
		System.out.println("'''''''''''''''''''''''''''''''''''''''''"
			+ psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setEventName(rs.getString("eventName"));
		vo.setEventId(rs.getString("event_Id"));
			list.add(vo);
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null || psmt.isClosed()) {
				psmt.close();
			}
			if (conn != null || conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getGreenRoom Ending");
		return list;
	}
	
	public String deletevolanteer(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: deleteProgram Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	String result = null;
	int count = 0;
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.DELETE_VOLNTEER);
		psmt.setString(1, id);
		count = psmt.executeUpdate();
		if (count > 0) {
			result = "true";
	} else {
		result = "false";
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null || psmt.isClosed()) {
				psmt.close();
			}
			if (conn != null || conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : deleteProgram Ending");
		return result;
	}
	
	public ArrayList<eventRegVo> getAdmissionNoByVolanteer(String searchterm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl:getAdmissionNoByVolanteer Starting");
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn
				.prepareStatement(EventsSqlUtils.GET_VOLANTEER_BY_ADMISSION_NO);
		psmt.setString(1, searchterm + "%");
	psmt.setString(2, searchterm + "%");
	System.out.println("admission No query is " + psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setVolanteerAdmissionNo(rs
				.getString("student_admissionno_var")
				+ "-"
				+ rs.getString("student"));
			list.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl :getAdmissionNoByVolanteer Ending");
		return list;
	}
	
	public ArrayList<eventRegVo> getProgramNumberingList(String event,
			String stage) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getProgramNumberingList Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement getstu = null;
	ResultSet rs, rs1 = null;
	String studentList[] = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	System.out.println("inside getProgramNumberingList DaoImpl>>>>>");
	try {
		conn = JDBCConnection.getSeparateConnection();
	
		psmt = conn
				.prepareStatement(EventsSqlUtils.getProgramNumberingList);
		psmt.setString(1, event);
		psmt.setString(2, stage);
		rs = psmt.executeQuery();
		System.out.println("get program numbering list for pdf download>>>"
			+ psmt);
	while (rs.next()) {
	
		eventRegVo vo = new eventRegVo();
		vo.setProgramNumber(rs.getString("programnumber"));
		vo.setCategoryName(rs.getString("categoryName"));
		vo.setCategoryId(rs.getString("category_id"));
		vo.setProgrammeName(rs.getString("program_name"));
		vo.setProgrammeId(rs.getString("program_id"));
		vo.setStageName(rs.getString("stageName"));
		vo.setParticipantName(rs.getString("student"));
		vo.setRegistrationId(rs.getString("registration_id"));
		vo.setEventName(rs.getString("eventName"));
	
			list.add(vo);
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null || psmt.isClosed()) {
	
				psmt.close();
			}
			if (conn != null || conn.isClosed()) {
				conn.close();
			}
			System.out.println("pdf list size>>"+list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getProgramNumberingList Ending");
	
		return list;
	}
	
	public ArrayList<eventRegVo> getStageAllocatedParticipants(String event,
			String stageVal) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getStageAllocatedParticipants Starting");
	String studentList[] = null;
	Connection conn = null;
	PreparedStatement psmt = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	ResultSet rs1 = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
	
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn
				.prepareStatement(EventsSqlUtils.getStageAllocatedParticipants);
		psmt.setString(1, event);
		psmt.setString(2, stageVal);
	
		rs = psmt.executeQuery();
		System.out.println("getMapped Participants>>>>" + psmt);
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setParticipantName(rs.getString("student"));
		vo.setEventName(rs.getString("eventName"));
		vo.setCategoryName(rs.getString("categoryName"));
		vo.setProgrammeName(rs.getString("program_name"));
		vo.setStageName(rs.getString("stageName"));
			list.add(vo);
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null || psmt.isClosed()) {
				psmt.close();
	
			}
			if (conn != null || conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getStageAllocatedParticipants Ending");
		return list;
	}
	
	public ArrayList<eventRegVo> getCategoryName(String id, String group) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getCategoryName Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> categoryName = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		if (group.equalsIgnoreCase("Group")) {
		psmt = conn.prepareStatement("SELECT DISTINCT c.`categoryId`,c.`categoryName`,s.`category_id`,s.`participateType`,s.`event_id` FROM `campus_event_studentregistration` s  LEFT JOIN `campus_event_category` c ON c.`categoryId`=s.`category_id` WHERE s.`participateType`='group' AND s.`event_id` like ?");
	} else if (group.equalsIgnoreCase("Indiv")) {
		psmt = conn.prepareStatement("SELECT DISTINCT c.`categoryId`,c.`categoryName`,s.`category_id`,s.`participateType`,s.`event_id` FROM `campus_event_studentregistration` s  LEFT JOIN `campus_event_category` c ON c.`categoryId`=s.`category_id` WHERE s.`participateType`='individual' AND s.`event_id` like ?");
	} else {
		psmt = conn.prepareStatement(EventsSqlUtils.getCategoryName);
	}
	
	if (id.equalsIgnoreCase("")) {
		id = "%%";
	}
	psmt.setString(1, id);
	System.out.println(psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setCategoryId(rs.getString("categoryId"));
		vo.setCategoryName(rs.getString("categoryName"));
			categoryName.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	/*
	 * finally{ try{ if(psmt!=null ||
	 * (!psmt.isClosed())){psmt.close();}if(conn!=null ||
	 * (!conn.isClosed())){conn.close();}}catch(Exception
	 * e){e.printStackTrace(); } }
	 */
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getCategoryName Ending");
		return categoryName;
	}
	
	public ArrayList<eventRegVo> getCriteriaForJudgementSheet(String event_id,
			String progId, String catId) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getCategoryName Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn
				.prepareStatement(EventsSqlUtils.GET_CRITERIA_FOR_JUDGEMENTSHEET);
		psmt.setString(1, event_id);
		psmt.setString(2, progId);
		psmt.setString(3, catId);
		System.out.println("JUDGE "+psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		
		String[] cri = rs.getString("Criteria").split(",");
			for (int i = 0; i < cri.length; i++) {
				eventRegVo vo = new eventRegVo();
				System.out.println(cri[i]);
				vo.setCriteria(cri[i]);
				list.add(vo);
			}
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getCategoryName Ending");
		return list;
	
	}
	public ArrayList<eventRegVo> getEventStudentMeritList(String evId,
			String progId, String catId, String houseId, String accId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
	PreparedStatement psmt=null, psmt1=null, psmt2 = null,psmt3 = null;
	Connection conn = null;
	ResultSet rs = null,rs1 = null,rs2 = null,rs3= null;
	ArrayList<eventRegVo> mainList = new ArrayList<eventRegVo>();
	ArrayList<eventRegVo> sub = new ArrayList<eventRegVo>();
	
	try {
			System.out.println("IN DAO IMPL");
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(EventsSqlUtils.GET_MERIT_PARTICPANT_LIST);
		psmt.setString(1, evId);
		psmt.setString(2, catId);
		psmt.setString(3, progId);
		psmt.setString(4, accId);
		String[] plcae = { "First", "Second", "Third" };
		int j=0;
		rs = psmt.executeQuery();
		System.out.println("MERIT LIST "+psmt);
		while (rs.next()) {
			psmt1 = conn.prepareStatement("SELECT `admission_no` FROM `campus_event_chestno_list` WHERE `chest_no`=?");
			psmt1.setString(1, rs.getString("chestNo"));
			System.out.println("ADMISSION NO "+psmt1);
			rs1=psmt1.executeQuery();
	          while(rs1.next()){
	        	System.out.println("ADMISSION NO "+rs1.getString("admission_no"));
	            ArrayList<eventRegVo> subList = new ArrayList<eventRegVo>();
				psmt2 = conn.prepareStatement(EventsSqlUtils.GET_PARTICPANT_LIST_FROM_STUDENT_REGISTRATION);
				psmt2.setString(1,rs1.getString("admission_no") );
				psmt2.setString(2, accId);
				System.out.println("getStudentDetails" + psmt2);
				rs2 = psmt2.executeQuery();
				while (rs2.next()) {
					eventRegVo vo = new eventRegVo();
					System.out.println("PLACE " + plcae[j]);
					vo.setPlace(plcae[j]);
					System.out.println("ADMIN IN WHILE "+rs2.getString("student_admissionno_var"));
					String participant = rs2.getString("student_admissionno_var");
					vo.setStudentName(rs2.getString("student_fname_var")+" "+ rs2.getString("student_lname_var"));
					vo.setAdmissionNo(rs2.getString("student_admissionno_var"));
					vo.setLocation(rs2.getString("Location_Name"));
					vo.setClassName(rs2.getString("classdetails_name_var"));
					vo.setRollNumber(rs2.getString("rollNo"));
					vo.setImageUrl(rs2.getString("imgurl_var"));
						mainList.add(vo);
						j++;
					}
				}
			}
			return mainList;
		} catch (Exception e) {
		e.printStackTrace();
	}finally {
		if (rs != null) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}if (rs1 != null) {
		try {
			rs1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}if (rs2 != null) {
		try {
			rs2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}if (rs3 != null) {
		try {
			rs3.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt1 != null) {
			try {
				psmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt2 != null) {
			try {
				psmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt3 != null) {
			try {
				psmt3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())+" Control in EventsDaoImpl : getEventStudentRegList : Ending");
		return null;
	}
	
	public ArrayList<eventRegVo> getEventStudentMeritListByOnlyEvent(String evId,
			String progId, String catId, String houseId, String accId, int place) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
	PreparedStatement psmt=null, psmt1=null, psmt2 = null,psmt3 = null;
	Connection conn = null;
	ResultSet rs = null,rs1 = null,rs2 = null,rs3= null;
	ArrayList<eventRegVo> sub = new ArrayList<eventRegVo>();
	int  count=1;
	try {
		    conn = JDBCConnection.getSeparateConnection();
			psmt3 = conn.prepareStatement("SELECT `program_id`,`program_name` FROM `campus_event_programcreation` WHERE `event_id`=? AND `category_id`=?");
		psmt3.setString(1, evId);
		psmt3.setString(2, catId);
		rs3=psmt3.executeQuery();
		System.out.println("PSMT 3"+psmt3);
		while(rs3.next()){
			ArrayList<eventRegVo> mainList = new ArrayList<eventRegVo>();
			eventRegVo vo1 = new eventRegVo();
			vo1.setCount(count++);
			vo1.setProgrammeName(rs3.getString("program_name"));
			System.out.println("IN DAO IMPL");
			conn = JDBCConnection.getSeparateConnection();
			psmt = conn.prepareStatement("SELECT * FROM `campus_event_marksentry` WHERE`event_id`=? AND `category_id`=? AND `program_id`=? AND `AccYear`=? ORDER BY `scoredMarks` DESC LIMIT ?");
			psmt.setString(1, evId);
			psmt.setString(2, catId);
			psmt.setString(3, rs3.getString("program_id"));
			psmt.setString(4, accId);
			psmt.setInt(5, place);
			String[] plcae = { "First", "Second", "Third","Fourth","Fifth","Sixth","Seventh","Eighth","Ninth","Tenth"};
			int j=0;
			rs = psmt.executeQuery();
			System.out.println("MERIT LIST "+psmt);
			while (rs.next()) {
				psmt1 = conn.prepareStatement("SELECT `admission_no` FROM `campus_event_chestno_list` WHERE `chest_no`=?");
				psmt1.setString(1, rs.getString("chestNo"));
				rs1=psmt1.executeQuery();
	              while(rs1.next()){
					psmt2 = conn.prepareStatement(EventsSqlUtils.GET_PARTICPANT_LIST_FROM_STUDENT_REGISTRATION);
					psmt2.setString(1,rs1.getString("admission_no") );
					psmt2.setString(2, accId);
					rs2 = psmt2.executeQuery();
					while (rs2.next()) {
						eventRegVo vo = new eventRegVo();
						vo.setPlace(plcae[j]);
						String participant = rs2.getString("student_admissionno_var");
						vo.setStudentName(rs2.getString("student_fname_var")+" "+ rs2.getString("student_lname_var"));
						vo.setAdmissionNo(rs2.getString("student_admissionno_var"));
						vo.setLocation(rs2.getString("Location_Name"));
						vo.setClassName(rs2.getString("classdetails_name_var"));
						vo.setRollNumber(rs2.getString("rollNo"));
						vo.setImageUrl(rs2.getString("imgurl_var"));
							j++;
							mainList.add(vo);
						}
						
						 }
				}
				vo1.setParticipantsList(mainList);
			    sub.add(vo1);
			}
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		if (rs != null) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}if (rs1 != null) {
		try {
			rs1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}if (rs2 != null) {
		try {
			rs2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}if (rs3 != null) {
		try {
			rs3.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt1 != null) {
			try {
				psmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt2 != null) {
			try {
				psmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt3 != null) {
			try {
				psmt3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
		return sub;
	}
	
	public ArrayList<eventRegVo> getEventStudentMeritListByProgramme(String evId,
			String progId, String catId, String houseId, String accId, String resultType) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
	PreparedStatement psmt=null, psmt1=null, psmt2 = null,psmt3 = null;
	Connection conn = null;
	ResultSet rs = null,rs1 = null,rs2 = null,rs3= null;
	ArrayList<eventRegVo> sub = new ArrayList<eventRegVo>();
	try {
		    conn = JDBCConnection.getSeparateConnection();
			psmt3 = conn.prepareStatement("SELECT `program_id`,`program_name` FROM `campus_event_programcreation` WHERE `event_id`=? AND `category_id`=? AND `program_id`=?");
		psmt3.setString(1, evId);
		psmt3.setString(2, catId);
		psmt3.setString(3, progId);
		rs3=psmt3.executeQuery();
		System.out.println("PSMT 3"+psmt3);
		while(rs3.next()){
			ArrayList<eventRegVo> mainList = new ArrayList<eventRegVo>();
			eventRegVo vo1 = new eventRegVo();
			vo1.setProgrammeName(rs3.getString("program_name"));
			conn = JDBCConnection.getSeparateConnection();
			
			if(resultType.equalsIgnoreCase("Postionwise")) {
				psmt = conn.prepareStatement("SELECT GROUP_CONCAT(chestNo) chestNo FROM `campus_event_marksentry` WHERE`event_id`=? AND `category_id`=? AND `program_id`=? AND `AccYear`=? GROUP BY  `scoredMarks` ORDER BY `scoredMarks` DESC LIMIT 3 ");
				psmt.setString(1, evId);
				psmt.setString(2, catId);
				psmt.setString(3, rs3.getString("program_id"));
				psmt.setString(4, accId);
				
				String[] plcae = { "First", "Second", "Third","Fourth","Fifth","Sixth","Seventh","Eighth","Ninth","Tenth"};
				int j=0;
				rs = psmt.executeQuery();
				System.out.println("MERIT LIST "+psmt);
				while (rs.next()) {
					String chestNoArray[]=rs.getString("chestNo").split(",");
					for(int m=0;m<chestNoArray.length;m++) {
						psmt1 = conn.prepareStatement("SELECT `admission_no` FROM `campus_event_chestno_list` WHERE `chest_no`=?");
						psmt1.setString(1, chestNoArray[m]);
						rs1=psmt1.executeQuery();
			              while(rs1.next()){
							psmt2 = conn.prepareStatement("SELECT l.`Location_Name`,cs.*,csc.*, csc.student_rollno rollNo,csc.student_imgurl_var imgurl_var,cc.classdetails_name_var,cho.housename FROM campus_student cs LEFT JOIN campus_student_classdetails csc ON csc.student_id_int=cs.student_id_int LEFT JOIN campus_classdetail cc ON (cc.classdetail_id_int=csc.classdetail_id_int AND cc.locationId=csc.locationId) LEFT JOIN `campus_location` l ON l.`Location_Id`=cs.`locationId` JOIN campus_house_settings cho ON cho.house_id=csc.student_house  WHERE cs.student_admissionno_var=? AND csc.fms_acadamicyear_id_int=?");
							psmt2.setString(1,rs1.getString("admission_no") );
							psmt2.setString(2, accId);
							rs2 = psmt2.executeQuery();
							while (rs2.next()) {
								eventRegVo vo = new eventRegVo();
								vo.setPlace(plcae[j]);
								String participant = rs2.getString("student_admissionno_var");
								vo.setStudentName(rs2.getString("student_fname_var")+" "+ rs2.getString("student_lname_var"));
								vo.setAdmissionNo(rs2.getString("student_admissionno_var"));
								vo.setLocation(rs2.getString("Location_Name"));
								vo.setClassName(rs2.getString("classdetails_name_var"));
								vo.setRollNumber(rs2.getString("rollNo"));
								vo.setImageUrl(rs2.getString("imgurl_var"));
								vo.setHouseName(rs2.getString("housename"));	
									mainList.add(vo);
								}
								vo1.setParticipantsList(mainList);
			                  }
						
					}
					j++;
					}
				
				sub.add(vo1);
			}
			else if(resultType.equalsIgnoreCase("Tabulation")) {
				psmt = conn.prepareStatement("SELECT * FROM `campus_event_marksentry` WHERE`event_id`=? AND `category_id`=? AND `program_id`=? AND `AccYear`=? ");
				psmt.setString(1, evId);
				psmt.setString(2, catId);
				psmt.setString(3, rs3.getString("program_id"));
				psmt.setString(4, accId);
				
				rs = psmt.executeQuery();
				while(rs.next()) {
					eventRegVo vo2 = new eventRegVo();
					List<eventRegVo> list=new ArrayList<eventRegVo>();
					psmt1 = conn.prepareStatement("SELECT SUM(`marksForEachCriteria`) marks FROM `campus_event_marksentrycriteriawise` WHERE `marksEntry_id`=? GROUP BY judge_id");
					psmt1.setString(1, rs.getString("marksEntry_id"));
					rs1=psmt1.executeQuery();
					while(rs1.next()) {
						eventRegVo vo = new eventRegVo();
						vo.setCriteriaMarks(rs1.getInt("marks"));
						list.add(vo);
					}
					
					vo2.setEventRgoList(list);
					vo2.setChestNoId(rs.getString("chestNo"));
					vo2.setScoredmarks(rs.getString("scoredMarks"));
					sub.add(vo2);
				}
				
				
			}
			else if(resultType.equalsIgnoreCase("Criteriawise")) {
				psmt = conn.prepareStatement("SELECT * FROM `campus_event_marksentry` WHERE`event_id`=? AND `category_id`=? AND `program_id`=? AND `AccYear`=? ");
				psmt.setString(1, evId);
				psmt.setString(2, catId);
				psmt.setString(3, rs3.getString("program_id"));
				psmt.setString(4, accId);
				
				rs = psmt.executeQuery();
				while(rs.next()) {
					eventRegVo vo2 = new eventRegVo();
					List<eventRegVo> list=new ArrayList<eventRegVo>();
					psmt1 = conn.prepareStatement("SELECT SUM(`marksForEachCriteria`) marks,criteria FROM `campus_event_marksentrycriteriawise` WHERE `marksEntry_id`=? GROUP BY criteria");
					psmt1.setString(1, rs.getString("marksEntry_id"));
					rs1=psmt1.executeQuery();
					while(rs1.next()) {
						eventRegVo vo = new eventRegVo();
						vo.setCriteriaMarks(rs1.getInt("marks"));
						vo.setCriteria(rs1.getString("criteria"));
						list.add(vo);
					}
					
					vo2.setEventRgoList(list);
					vo2.setChestNoId(rs.getString("chestNo"));
					vo2.setScoredmarks(rs.getString("scoredMarks"));
					sub.add(vo2);
				}
				
				
			}
			
			
			}
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		if (rs != null) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}if (rs1 != null) {
		try {
			rs1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}if (rs2 != null) {
		try {
			rs2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}if (rs3 != null) {
		try {
			rs3.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt1 != null) {
			try {
				psmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt2 != null) {
			try {
				psmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt3 != null) { 
			try {
				psmt3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
		return sub;
	}
	
	public ArrayList<eventRegVo> EventResultPrint(String evId, String progId,
			String catId, String houseId, String accId, int place) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
	PreparedStatement psmt=null, psmt1=null,psmt2=null,psmt3=null;
	Connection conn = null;
	ResultSet rs = null,rs1 = null,rs2 = null,rs3= null;
	ArrayList<eventRegVo> mainList = new ArrayList<eventRegVo>();
	int  count=0;
	try {
		    conn = JDBCConnection.getSeparateConnection();
			psmt3 = conn.prepareStatement("SELECT `program_id`,`program_name` FROM `campus_event_programcreation` WHERE `event_id`=? AND `category_id`=?");
		psmt3.setString(1, evId);
		psmt3.setString(2, catId);
		rs3=psmt3.executeQuery();
		System.out.println("PSMT 3"+psmt3);
		while(rs3.next()){
			count++;
			eventRegVo vo1 = new eventRegVo();
		    String prohrammename=rs3.getString("program_name");
			conn = JDBCConnection.getSeparateConnection();
			psmt = conn.prepareStatement("SELECT * FROM `campus_event_marksentry` WHERE`event_id`=? AND `category_id`=? AND `program_id`=? AND `AccYear`=? ORDER BY `scoredMarks` DESC LIMIT ?");
			psmt.setString(1, evId);
			psmt.setString(2, catId);
			psmt.setString(3, rs3.getString("program_id"));
			psmt.setString(4, accId);
			psmt.setInt(5, place);
			String[] plcae = { "First", "Second", "Third","Fourth","Fifth","Sixth","Seventh","Eighth","Ninth","Tenth"};
			int j=0;
			rs = psmt.executeQuery();
			System.out.println("MERIT LIST "+psmt);
			while (rs.next()) {
				psmt1 = conn.prepareStatement("SELECT `admission_no` FROM `campus_event_chestno_list` WHERE `chest_no`=?");
				psmt1.setString(1, rs.getString("chestNo"));
				rs1=psmt1.executeQuery();
	              while(rs1.next()){
					psmt2 = conn.prepareStatement(EventsSqlUtils.GET_PARTICPANT_LIST_FROM_STUDENT_REGISTRATION);
					psmt2.setString(1,rs1.getString("admission_no") );
					psmt2.setString(2, accId);
					rs2 = psmt2.executeQuery();
					while (rs2.next()) {
						eventRegVo vo = new eventRegVo();
						vo.setPlace(plcae[j]);
						vo.setCount(count);
						String participant = rs2.getString("student_admissionno_var");
						vo.setProgrammeName(prohrammename);
						vo.setStudentName(rs2.getString("student_fname_var")+" "+ rs2.getString("student_lname_var"));
						vo.setAdmissionNo(rs2.getString("student_admissionno_var"));
						vo.setLocation(rs2.getString("Location_Name"));
						vo.setClassName(rs2.getString("classdetails_name_var"));
						vo.setRollNumber(rs2.getString("rollNo"));
						vo.setImageUrl(rs2.getString("imgurl_var"));
							j++;
							mainList.add(vo);
					}
	
				}
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs1 != null) {
			try {
				rs1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs2 != null) {
			try {
				rs2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs3 != null) {
			try {
				rs3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt1 != null) {
			try {
				psmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt2 != null) {
			try {
				psmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt3 != null) {
			try {
				psmt3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
		return mainList;
	}
	
	public ArrayList<eventRegVo> getEventStudentOverallResult(String evId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
	PreparedStatement psmt=null, psmt1=null, psmt2 = null,psmt3 = null;
	Connection conn = null;
	ResultSet rs = null,rs1 = null,rs2 = null,rs3= null;
	ArrayList<eventRegVo> mainList = new ArrayList<eventRegVo>();
	int  count=0;
	try {
		    conn = JDBCConnection.getSeparateConnection();
			psmt3 = conn.prepareStatement("SELECT program_id,program_name,programType FROM `campus_event_programcreation` WHERE event_id=? group by program_name order by program_name");
		psmt3.setString(1, evId);
		
		rs3=psmt3.executeQuery();
		
		while(rs3.next()){
			
			    eventRegVo vo = new eventRegVo();
			    vo.setProgrammeName(rs3.getString("program_name"));
			    vo.setPartiType(rs3.getString("programType"));
			    mainList.add(vo);
			    
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
		if (rs != null && !rs.isClosed()) {
			
				rs.close();
	}
		if (rs1 != null && !rs1.isClosed()) {
	
			rs1.close();
		
	}
		if (rs2 != null &&!rs2.isClosed()) {
		
			rs2.close();
		
	}
		if (rs3 != null && !rs3.isClosed()) {
		
			rs3.close();
		
	}
		if (psmt != null && !psmt.isClosed()) {
		
				psmt.close();
			
		}
		if (psmt1 != null && !psmt1.isClosed()) {
			
				psmt1.close();
			
		}
		if (psmt2 != null && !psmt2.isClosed()) {
			
				psmt2.close();
			
		}
		if (psmt3 != null && !psmt3.isClosed()) {
		
				psmt3.close();
			
		}if (conn != null && !conn.isClosed()) {
			
				conn.close();
			
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
		return mainList;
	}
	
	public ArrayList<eventRegVo> getJudgeListforProgram() {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in EventsDaoImpl: getJudgeListforProgram Starting");
	
	CallableStatement proc = null;
	ResultSet rs = null;
	Connection conn = null;
	ArrayList<eventRegVo> teacherName = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		proc = (CallableStatement) conn.prepareCall("{CALL getJudgeList()}");
		proc.execute();
		rs = proc.getResultSet();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setTeacherId(rs.getString("TeacherID"));
			vo.setTeacherName(rs.getString("TeacherName"));
			teacherName.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getJudgeListforProgram Ending");
				return teacherName;
			}
	
	public eventRegVo getPartEventDetail(String event_id, String catId,
			String progId, String houseId, String stageId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
		PreparedStatement psmt=null,psmt3 = null;
		Connection conn = null;
		ResultSet rs = null,rs3 = null;
		eventRegVo vo = new eventRegVo();
		try {
			conn = JDBCConnection.getSeparateConnection();
			if(!progId.equalsIgnoreCase("")){
				psmt = conn.prepareStatement("SELECT cer.`eventName`,cec.`categoryName`,cepc.`program_name`,cepc.`programDate` FROM `campus_event_registration` cer LEFT JOIN `campus_event_category` cec ON cer.`event_Id`=cec.`eventId` LEFT JOIN `campus_event_programcreation` cepc ON cepc.`category_id`=cec.`categoryId` WHERE cer.`event_Id`=? AND cec.`categoryId`=? AND cepc.`program_id`=?");
				psmt.setString(1, event_id);
				psmt.setString(2, catId);
				psmt.setString(3, progId);
			} else {
				psmt = conn.prepareStatement("SELECT cer.`eventName`,cec.`categoryName`,cepc.`program_name`,cepc.`programDate` FROM `campus_event_registration` cer LEFT JOIN `campus_event_category` cec ON cer.`event_Id`=cec.`eventId` LEFT JOIN `campus_event_programcreation` cepc ON cepc.`category_id`=cec.`categoryId` WHERE cer.`event_Id`=? AND cec.`categoryId`=?");
				psmt.setString(1, event_id);
				psmt.setString(2, catId);
			}
			System.out.println("PROGRAMME DETAILS "+psmt);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo.setEventName(rs.getString("eventName"));
				vo.setCategoryName(rs.getString("categoryName"));
				vo.setProgrammeName(rs.getString("program_name"));
				vo.setProg_date(rs.getString("programDate"));
			}

			psmt3 = conn.prepareStatement("SELECT `stageName` FROM `campus_event_stage` WHERE `stageId`=?");
			psmt3.setString(1, stageId);
			rs3 = psmt3.executeQuery();
			System.out.println("STAGE3 " + psmt3);
			while (rs3.next()) {
				System.out.println("RS6 INSIDE");
				vo.setStageName(rs3.getString("stageName"));
			}

			/*psmt1 = conn.prepareStatement("SELECT `Stage` FROM `campus_event_program_scheduling` WHERE `Event_name`=? AND `Program_name`=?");
			psmt1.setString(1, event_id);
			psmt1.setString(2, progId);
			rs1 = psmt1.executeQuery();
			System.out.println("STAGE " + psmt1);
			while (rs1.next()) {
				psmt2 = conn.prepareStatement("SELECT `stageName` FROM `campus_event_stage` WHERE `stageId`=?");
				psmt2.setString(1, rs1.getString("Stage"));
				rs2 = psmt2.executeQuery();
				while (rs2.next()){
					vo.setStageName(rs2.getString("stageName"));
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (rs != null || !(rs.isClosed())) {
					rs.close();
				}
				if (psmt != null || !(psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null || !(conn.isClosed())) {
					conn.close();
				}
				}catch(Exception e){e.printStackTrace();
				}
		
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
		return vo;
	}
	public ArrayList<eventRegVo> getProgramNameByCategory(String eventid, String categoryid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getCategoryName Starting");
		Connection conn= null;
		PreparedStatement psmt =null;
		ResultSet rs =null;
		ArrayList<eventRegVo> categoryName = new ArrayList<eventRegVo>();
		try{
			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement(EventsSqlUtils.GET_PROGRAM_NAME);
			psmt.setString(1, eventid);
			psmt.setString(2, categoryid);
			
			rs=psmt.executeQuery();
			while(rs.next()){
				eventRegVo vo = new eventRegVo();
				vo.setProgId(rs.getString("program_id"));
				vo.setProgName(rs.getString("program_name"));
				categoryName.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(psmt!=null || psmt.isClosed()){psmt.close();}if(conn!=null || conn.isClosed()){conn.close();}}catch(Exception e){e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getCategoryName Ending");
		return categoryName;
	}

	public ArrayList<eventRegVo> getCategoryNameByEvent(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getCategoryName Starting");
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ArrayList<eventRegVo> categoryName = new ArrayList<eventRegVo>();
		try {
			conn = JDBCConnection.getSeparateConnection();
				psmt = conn.prepareStatement(EventsSqlUtils.getCategoryName);

			psmt.setString(1, id);
			System.out.println(psmt);
			rs = psmt.executeQuery();
			while (rs.next()) {
				eventRegVo vo = new eventRegVo();
				vo.setCategoryId(rs.getString("categoryId"));
				vo.setCategoryName(rs.getString("categoryName"));
				categoryName.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getCategoryName Ending");
		return categoryName;
	}

	public ArrayList<eventRegVo> getCriteriaForMarksEntrySaved(String evId,
			String catId, String progId, String userId, String chestno) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getCriteriaForMarksEntrySaved Starting");
	Connection conn =null;
	PreparedStatement psmt= null;
	ResultSet rs,rs2 =null;
	ArrayList<eventRegVo> list=new ArrayList<eventRegVo>();
	
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT `Criteria`,`Criteria_Id` FROM `campus_event_criteria` WHERE `Event`=? AND `Category`=? AND `Programme_Name`=?");
	psmt.setString(1, evId);
	psmt.setString(2, catId);
	psmt.setString(3, progId);
	
	rs=psmt.executeQuery();
	while(rs.next()){
		String[] criteriaArray =(rs.getString("Criteria")).split(",");
		for(int i=0;i<criteriaArray.length;i++){
			eventRegVo vo1 = new eventRegVo();
			ArrayList<eventRegVo> list1=new ArrayList<eventRegVo>();
			double criteriaMarks=0;
			int JudgeCount=0;
			PreparedStatement totalnumber=conn.prepareStatement("SELECT CASE WHEN `marksForEachCriteria` IS NULL THEN 0 ELSE `marksForEachCriteria` END criteriaMarks FROM `campus_event_marksentrycriteriawise` WHERE marksEntry_id IN(SELECT `marksEntry_id` FROM `campus_event_marksentry` WHERE `chestNo`=? AND `event_id`=? AND `category_id`=? AND `program_id`=?)  AND criteria=?");
			totalnumber.setString(1, chestno);
			totalnumber.setString(2, evId);
			totalnumber.setString(3, catId);
			totalnumber.setString(4, progId);
			totalnumber.setString(5, criteriaArray[i]);
			System.out.println("totalnumber="+totalnumber);
			ResultSet rls=totalnumber.executeQuery();
			while(rls.next()){
				JudgeCount++;
				eventRegVo vo = new eventRegVo();
				criteriaMarks=rls.getDouble("criteriaMarks");
				vo.setCriteriaMarks(criteriaMarks);
				vo.setCount(JudgeCount);
				list1.add(vo);
				
			}
			vo1.setEventRgoList(list1);
			list.add(vo1);
			rls.close();
			totalnumber.close();
			//get the admission nos 
				
				
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(psmt!=null || !(psmt.isClosed())){psmt.close();}if(conn!=null || !(conn.isClosed())){conn.close();}}catch(Exception e){e.printStackTrace();
			}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getCriteriaForMarksEntrySaved Ending");
		return list;
	}


	public ArrayList<eventRegVo> printChestNo(eventRegVo obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getCategoryName Starting");
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
		ArrayList<String> stu = new ArrayList<String>();
		HashSet copy = new HashSet();
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			psmt = conn.prepareStatement(EventsSqlUtils.PRINT_CHEST_NO);
			psmt.setString(1,obj.getAccId());
			psmt.setString(2,obj.getEventId());
			psmt.setString(3,obj.getCategoryId());
			psmt.setString(4,obj.getProgId());
			System.out.println(psmt);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String[] stulist = rs.getString("participants_admissionNo").split(",");
				for(int i=0;i<stulist.length;i++){
					stu.add(i,stulist[i]);
				}
			}
			rs.close();
			psmt.close();
			copy.addAll(stu);
			stu.clear();
			stu.addAll(copy);
			Collections.sort(stu);
			psmt = conn.prepareStatement(EventsSqlUtils.PRINT_CHEST_NOS);
			for(int i=0;i<stu.size();i++){
				psmt.setString(1,stu.get(i));
				rs = psmt.executeQuery();
				while(rs.next()){
					count++;
					eventRegVo obj1 = new eventRegVo();
					obj1.setSno(count);
					obj1.setAdmissionno(stu.get(i));
					obj1.setStudentName(rs.getString("student"));
					obj1.setLocation(rs.getString("Location_Name"));
					obj1.setChestNumber(rs.getString("chest_no"));
					list.add(obj1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null || rs.isClosed()) {
					rs.close();
				}
				if (psmt != null || psmt.isClosed()) {
					psmt.close();
				}
				if (conn != null || conn.isClosed()) {
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getCategoryName Ending");
		return list;
	}

	public ArrayList<eventRegVo> studentAdmnNameForEvent(String id,String eventName,String categoryName, String houseName) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : studentAdmnName Starting");
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	PreparedStatement psmt = null;
	ResultSet rs = null;
	PreparedStatement psmt1 = null;
	ResultSet rs1 = null;
	Connection conn = null;
	try {
		String classListArray[]=null;
		String classList="";
		String locId="";
		String accYearId="";
		conn = JDBCConnection.getSeparateConnection();
		psmt1=conn.prepareStatement("SELECT cec.classList,cer.accyer_ID,cer.location_ID FROM `campus_event_category` cec JOIN `campus_event_registration` cer ON cer.event_Id=cec.eventId WHERE categoryId=?");
		psmt1.setString(1, categoryName);
		rs1=psmt1.executeQuery();
		while(rs1.next()){
			classListArray=rs1.getString("classList").split(",");
			accYearId=rs1.getString("accyer_ID");
			locId=rs1.getString("location_ID");
		}
		for(int i=0;i<classListArray.length;i++){
			classList+="'"+classListArray[i]+"'"+",";
		}
		classList = classList.replaceAll(",$", "");
		System.out.println(classList);
		rs1.close();
		psmt1.close();
		
		
		psmt = conn.prepareStatement("SELECT cs.student_id_int,cs.`student_admissionno_var`,CONCAT(cs.`student_fname_var`,'',cs.`student_lname_var`) as studentName FROM `campus_student` cs JOIN campus_student_classdetails csc ON cs.student_id_int=csc.student_id_int WHERE cs.student_admissionno_var  NOT IN (?) AND csc.classdetail_id_int IN("+classList+") AND csc.fms_acadamicyear_id_int=? AND csc.locationId=? AND csc.student_house LIKE ? ");
		psmt.setString(1, id);
		psmt.setString(2, accYearId);
		psmt.setString(3, locId);
		psmt.setString(4, houseName);
		System.out.println(psmt);
		rs = psmt.executeQuery();
	
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setStudentId(rs.getString("student_id_int"));
		vo.setAdmissionNo(rs.getString("student_admissionno_var"));
		vo.setStudentName(rs.getString("student_admissionno_var") +"_"+rs.getString("studentName"));
				list.add(vo);
			}
	
	
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<eventRegVo> getProgramDateByEventID(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : studentAdmnName Starting");
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	PreparedStatement psmt = null;
	Connection conn;
	ResultSet rs = null;
	PreparedStatement psmt1 = null;
	String startsOn="";
	String endsOn="";
	String startDate="0";
	String enDatDate="0";
	int count=0;
	try{
		conn=JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT `startsOn`,`endsOn` FROM `campus_event_registration` WHERE `event_Id`=?");
		psmt.setString(1, id);
		rs=psmt.executeQuery();
		while(rs.next()){
			count=1;
			startsOn=rs.getString("startsOn");
			endsOn=rs.getString("endsOn");
			
		}
		if(count>0){
			String currentDate=HelperClass.getCurrentSqlDate().toString();
			List<String> firstDate=HelperClass.getDateListBetweenDates(currentDate, startsOn);
			startDate=Integer.toString(firstDate.size()-1);
			if(firstDate.size()==0){
				firstDate=HelperClass.getDateListBetweenDates(startsOn, currentDate);
				startDate="-"+Integer.toString(firstDate.size()-1);
			}
			System.out.println("startDate="+startDate);
			List<String> lastDate=HelperClass.getDateListBetweenDates(currentDate, endsOn);
			enDatDate=Integer.toString(lastDate.size()-1);
			if(lastDate.size()==0){
				lastDate=HelperClass.getDateListBetweenDates(endsOn, currentDate);
				enDatDate="-"+Integer.toString(lastDate.size()-1);
			}
			System.out.println("enDatDate="+enDatDate);
		}
		eventRegVo vo=new eventRegVo();
		vo.setStartFrom(startDate);
		vo.setEndTo(enDatDate);
		list.add(vo);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getCategoryName Ending");
	return list;
		
	}

	public ArrayList<eventRegVo> checkIsHouseWiseForEventReg(String evId,
			String catId, String progId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : studentAdmnName Starting");
		ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
		PreparedStatement psmt = null;
		Connection conn;
		ResultSet rs = null;
		try{
			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement("SELECT `program_name`,`program_id`,`event_id`,`category_id`,isHouseWise FROM`campus_event_programcreation` WHERE `event_id` like ? AND `category_id` like ? AND `program_id` like ?");
			psmt.setString(1, evId);
			psmt.setString(2, catId);
			psmt.setString(3, progId);
			System.out.println("############^^^^^^^^^^^^^^^^^^^^^^^^ is HOuse wise scheck"+psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
				eventRegVo vo = new eventRegVo();
				vo.setIshouseWise(rs.getString("isHouseWise"));
				list.add(vo);
			}
				
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getCategoryName Ending");
		
		return list;
	}

	public ArrayList<eventRegVo> getAdmissionNoForEventStdReg(
			String searchterm, String categoryName, String houseName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getAdmissionNoForEventStdReg Starting");
	String searchTerm = searchterm + "%";
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	PreparedStatement psmt = null;
	ResultSet rs = null;
	PreparedStatement psmt1 = null;
	ResultSet rs1 = null;
	Connection conn = null;
	try {
		String classListArray[]=null;
		String classList="";
		String locId="";
		String accYearId="";
		conn = JDBCConnection.getSeparateConnection();
		psmt1=conn.prepareStatement("SELECT cec.classList,cer.accyer_ID,cer.location_ID FROM `campus_event_category` cec JOIN `campus_event_registration` cer ON cer.event_Id=cec.eventId WHERE categoryId=?");
		psmt1.setString(1, categoryName);
		rs1=psmt1.executeQuery();
		while(rs1.next()){
			classListArray=rs1.getString("classList").split(",");
			accYearId=rs1.getString("accyer_ID");
			locId=rs1.getString("location_ID");
		}
		for(int i=0;i<classListArray.length;i++){
			classList+="'"+classListArray[i]+"'"+",";
		}
		classList = classList.replaceAll(",$", "");
		System.out.println(classList);
		rs1.close();
		psmt1.close();
		
		
		psmt = conn.prepareStatement("SELECT cs.student_id_int,cs.`student_admissionno_var`,CONCAT(cs.`student_fname_var`,'',cs.`student_lname_var`) as studentName FROM `campus_student` cs JOIN campus_student_classdetails csc ON cs.student_id_int=csc.student_id_int WHERE cs.student_admissionno_var  LIKE ? AND csc.classdetail_id_int IN("+classList+") AND csc.fms_acadamicyear_id_int=? AND csc.locationId=? AND csc.student_house LIKE ?");
		psmt.setString(1, searchTerm);
		psmt.setString(2, accYearId);
		psmt.setString(3, locId);
		psmt.setString(4, houseName);
		System.out.println(psmt);
		rs = psmt.executeQuery();
	
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setAdmissionNo(rs.getString("student_admissionno_var") + "_"
			+ rs.getString("studentName"));
			list.add(vo);
			}
		
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null || psmt.isClosed()) {
					psmt.close();
				}
				if (conn != null || conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				}
		}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getAdmissionNoForEventStdReg Ending");
	
		return list;
	}

	public eventRegVo getEventCertificateDetail(String event_id, String catId, String progId, String accId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventStudentRegList Starting");
	PreparedStatement psmt=null, psmt1= null;
	Connection conn = null;
	ResultSet rs = null;
	ResultSet rs1 = null;
	eventRegVo vo = new eventRegVo();
	try {
		conn = JDBCConnection.getSeparateConnection();
		
		psmt = conn.prepareStatement("SELECT cer.`eventName`,cec.`categoryName`,cepc.`program_name`,cepc.`programDate` FROM `campus_event_registration` cer LEFT JOIN `campus_event_category` cec ON cer.`event_Id`=cec.`eventId` LEFT JOIN `campus_event_programcreation` cepc ON cepc.`category_id`=cec.`categoryId` WHERE cer.`event_Id`=? AND cec.`categoryId`=? AND cepc.`program_id`=?");
		psmt.setString(1, event_id);
		psmt.setString(2, catId);
		psmt.setString(3, progId);
	    System.out.println("PROGRAMME DETAILS "+psmt);
	    rs = psmt.executeQuery();
	    while (rs.next()) {
		vo.setEventName(rs.getString("eventName"));
		vo.setCategoryName(rs.getString("categoryName"));
		vo.setProgrammeName(rs.getString("program_name"));
		vo.setProg_date(rs.getString("programDate"));
		psmt1=conn.prepareStatement("SELECT `acadamic_year` FROM `campus_acadamicyear` WHERE `acadamic_id`=?");
		psmt1.setString(1, accId);
		rs1 = psmt1.executeQuery();
		 while (rs1.next()) {
			 vo.setAccyear(rs1.getString("acadamic_year"));
		 }
	}
	
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventStudentRegList : Ending");
		return vo;
	}

	public ArrayList<eventRegVo> getHouseNamebyCategoryId(String catId, String evnId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getHouseNamebyCategoryId Starting");
		ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
		Connection conn;
		ResultSet rs = null;
		CallableStatement proc = null;
		String location="";
		String accyear="";
		try{
				conn = JDBCConnection.getSeparateConnection();
				
				PreparedStatement pstmt=conn.prepareStatement("SELECT * FROM `campus_event_registration` WHERE `event_Id`=?");
				pstmt.setString(1, evnId);
				
				System.out.println("dgdhhghsgd="+pstmt);
				ResultSet rst=pstmt.executeQuery();
				if(rst.next()) {
					location=rst.getString("location_ID");
					accyear=rst.getString("accyer_ID");
				}
				rst.close();
				pstmt.close();
				proc = (CallableStatement) conn.prepareCall("{call getHouseNameByCategory(?,?,?)}");
				proc.setString(1,catId);
				proc.setString(2,location);
				proc.setString(3,accyear);
				rs=proc.executeQuery();
				
				System.out.println("%%%%%%%%%%%%%   getHouseNameByCategory ^^^^^^^^^^^^^^^^^"+proc);
				
				while(rs.next()){
				eventRegVo vo = new eventRegVo();
				vo.setHouseId(rs.getString("student_house"));
				vo.setHouseName(rs.getString("housename"));
				list.add(vo);
			}
				
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getHouseNamebyCategoryId Ending");
		
		return list;

	}

	public ArrayList<eventRegVo> getHouseForParticipantList(String eventID) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getHouse Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> name = new ArrayList<eventRegVo>();
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement("SELECT `house_id`,`housename` FROM `campus_house_settings`  WHERE accyear_Id IN (select accyer_ID from campus_event_registration where event_Id=?)");
		psmt.setString(1, eventID);
		
		System.out.println("gsadgs="+psmt);
		rs = psmt.executeQuery();
		while (rs.next()) {
			eventRegVo vo = new eventRegVo();
			vo.setHouseId(rs.getString("house_id"));
		vo.setHouseName(rs.getString("housename"));
			name.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getHouse Ending");
		return name;
	
	}

	public List<eventRegVo> getOverAllReport(String evId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getOverAllReport Starting");
	PreparedStatement psmt=null, psmt1=null, psmt2 = null,psmt3 = null;
	Connection conn = null;
	ResultSet rs = null,rs1 = null,rs2 = null,rs3= null;
	List<eventRegVo> mainList = new ArrayList<eventRegVo>();
	int  count=0;
	try {
		    conn = JDBCConnection.getSeparateConnection();
			psmt3 = conn.prepareStatement("SELECT program_id,program_name,programType FROM `campus_event_programcreation` WHERE event_id=?  order by program_name");
			psmt3.setString(1, evId);
		
		rs3=psmt3.executeQuery();
		
		String[] plcae = { "first", "second","third"};
		while(rs3.next()){
			int j=0;
			psmt = conn.prepareStatement("SELECT GROUP_CONCAT(chestNo) chestNo,scoredMarks,category_id FROM `campus_event_marksentry` WHERE program_id=? GROUP BY scoredMarks  ORDER BY scoredMarks DESC LIMIT 3");
			psmt.setString(1,rs3.getString("program_id"));   
			rs=psmt.executeQuery();
			while(rs.next()) {
				String chestNo[]=rs.getString("chestNo").split(",");
				for(int i=0;i<chestNo.length;i++) {
					psmt1=conn.prepareStatement("SELECT housename FROM `campus_house_settings` WHERE house_id IN(SELECT ces.house_id FROM `campus_event_studentregistration` ces JOIN `campus_event_chestno_list` ch ON ch.admission_no=ces.captain_admissionNo  WHERE ces.program_id=? AND ch.chest_no=?)");
					psmt1.setString(1, rs3.getString("program_id"));
					psmt1.setString(2,chestNo[i]);
					
					rs1=psmt1.executeQuery();
					if(rs1.next()) {
					
						eventRegVo vo=new eventRegVo();
						if(plcae[j].equalsIgnoreCase("first")) {
							psmt2=conn.prepareStatement("SELECT points FROM `campus_event_prizelevel` WHERE prize_level='1' AND program_name=?");
							psmt2.setString(1, rs3.getString("program_id"));
							rs2=psmt2.executeQuery();
							if(rs2.next()) {
								vo.setCategoryId(rs1.getString("housename")+"_"+rs.getString("category_id")+"_"+plcae[j]+","+rs3.getString("program_name")+","+rs2.getDouble("points"));
							}
							else {
								vo.setCategoryId(rs1.getString("housename")+"_"+rs.getString("category_id")+"_"+plcae[j]+","+rs3.getString("program_name")+","+" ");
							}
							
						}
						else if(plcae[j].equalsIgnoreCase("second")) {
							psmt2=conn.prepareStatement("SELECT points FROM `campus_event_prizelevel` WHERE prize_level='2' AND program_name=?");
							psmt2.setString(1, rs3.getString("program_id"));
							rs2=psmt2.executeQuery();
							if(rs2.next()) {
								vo.setCategoryId(rs1.getString("housename")+"_"+rs.getString("category_id")+"_"+plcae[j]+","+rs3.getString("program_name")+","+rs2.getDouble("points"));
							}
							else {
								vo.setCategoryId(rs1.getString("housename")+"_"+rs.getString("category_id")+"_"+plcae[j]+","+rs3.getString("program_name")+","+" ");
							}
						}
						else if(plcae[j].equalsIgnoreCase("third")) {
							psmt2=conn.prepareStatement("SELECT points FROM `campus_event_prizelevel` WHERE prize_level='3' AND program_name=?");
							psmt2.setString(1, rs3.getString("program_id"));
							rs2=psmt2.executeQuery();
							if(rs2.next()) {
								vo.setCategoryId(rs1.getString("housename")+"_"+rs.getString("category_id")+"_"+plcae[j]+","+rs3.getString("program_name")+","+rs2.getDouble("points"));
							}
							else {
								vo.setCategoryId(rs1.getString("housename")+"_"+rs.getString("category_id")+"_"+plcae[j]+","+rs3.getString("program_name")+","+" ");
							}
						}
						else {
							vo.setCategoryId(rs1.getString("housename")+"_"+rs.getString("category_id")+"_"+plcae[j]+","+rs3.getString("program_name")+","+" ");
						}
						
						mainList.add(vo);
						
					}
				}
				j++;
			}
			
			
			
			    
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
		if (rs != null && !rs.isClosed()) {
			
				rs.close();
	}
		if (rs1 != null && !rs1.isClosed()) {
	
			rs1.close();
		
	}
		if (rs2 != null &&!rs2.isClosed()) {
		
			rs2.close();
		
	}
		if (rs3 != null && !rs3.isClosed()) {
		
			rs3.close();
		
	}
		if (psmt != null && !psmt.isClosed()) {
		
				psmt.close();
			
		}
		if (psmt1 != null && !psmt1.isClosed()) {
			
				psmt1.close();
			
		}
		if (psmt2 != null && !psmt2.isClosed()) {
			
				psmt2.close();
			
		}
		if (psmt3 != null && !psmt3.isClosed()) {
		
				psmt3.close();
			
		}if (conn != null && !conn.isClosed()) {
			
				conn.close();
			
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getOverAllReport : Ending");
		return mainList;
	}

	public List<eventRegVo> getOverAllReportMarksHouseAndCategory(String eventID) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getOverAllReportMarksHouseAndCategory Starting");
	PreparedStatement psmt=null, psmt1=null,psmt2=null;
	Connection conn = null;
	ResultSet rs = null,rs1 = null,rs2=null;
	List<eventRegVo> mainList = new ArrayList<eventRegVo>();
	List<eventRegVo> list = new ArrayList<eventRegVo>();
	int  count=0;
	try {
		    conn = JDBCConnection.getSeparateConnection();
		    
		    
			
				psmt1=conn.prepareStatement("SELECT DISTINCT(category_id),house FROM markwithHouse");
				rs1=psmt1.executeQuery();
				double point=0.0;
				while(rs1.next()) {
					
					
					
					PreparedStatement psmttt=conn.prepareStatement("SELECT housename FROM `campus_house_settings` WHERE house_id=?");
					psmttt.setString(1, rs1.getString("house"));
					ResultSet rstt=psmttt.executeQuery();
					if(rstt.next()) {
						
						eventRegVo vo=new eventRegVo();
						
						
					
						vo.setHouseName(rstt.getString("housename"));
						
						vo.setCategoryId(rstt.getString("housename")+"_"+rs1.getString("category_id"));
						
						rstt.close();
						psmttt.close();
						mainList.add(vo);
					}
				
				}
				
			
			
			
			
			    
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
		if (rs != null && !rs.isClosed()) {
			
				rs.close();
	}
		if (rs1 != null && !rs1.isClosed()) {
	
			rs1.close();
		
	}
		
		if (psmt != null && !psmt.isClosed()) {
		
				psmt.close();
			
		}
		if (psmt1 != null && !psmt1.isClosed()) {
			
				psmt1.close();
			
		}
		if (conn != null && !conn.isClosed()) {
			
				conn.close();
			
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getOverAllReportMarksHouseAndCategory : Ending");
		return mainList;
	}

	public List<eventRegVo> getOverAllReportMarksHouse(String eventID) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getOverAllReportMarksHouse Starting");
		PreparedStatement psmt=null, psmt1=null,psmt2=null;
		Connection conn = null;
		ResultSet rs = null,rs1 = null,rs2=null;
		List<eventRegVo> mainList = new ArrayList<eventRegVo>();
		List<eventRegVo> list = new ArrayList<eventRegVo>();
		int  count=0;
		try {
			    conn = JDBCConnection.getSeparateConnection();
			    
			    
				
					psmt2=conn.prepareStatement("SELECT * FROM `campus_event_programcreation` WHERE event_id=?");
					psmt2.setString(1, eventID);
					rs2=psmt2.executeQuery();
					while(rs2.next()) {
						
						
						psmt = conn.prepareStatement("SELECT GROUP_CONCAT(scoredMarks) scoredMarks,category_id,GROUP_CONCAT(house) house FROM markwithHouse WHERE event_id=?  AND program_id=? GROUP BY scoredMarks ORDER BY scoredMarks DESC LIMIT 3");
						psmt.setString(1, eventID);
						psmt.setString(2, rs2.getString("program_id"));
						rs=psmt.executeQuery();
					
					int j=1;
					while(rs.next()){
					
					String scoredMarkList[]=rs.getString("scoredMarks").split(",");
					String houseList[]=rs.getString("house").split(",");
						for(int k=0;k<scoredMarkList.length;k++) {
							eventRegVo vo=new eventRegVo();
							vo.setHouseName(houseList[k]);
							
							PreparedStatement psmtt=conn.prepareStatement("SELECT points FROM `campus_event_prizelevel` WHERE prize_level=? AND program_name=?");
							psmtt.setInt(1, j);
							psmtt.setString(2, rs2.getString("program_id"));
							
							ResultSet rst=psmtt.executeQuery();
							if(rst.next()) {
								vo.setScoredmarks(rst.getString("points"));
							}
							else {
								vo.setScoredmarks("0");
							}
							vo.setCategoryId(houseList[k]);
							list.add(vo);
							
							rst.close();
							psmtt.close();
							
						}		
						j++;		
						}
						
					}
					psmt1=conn.prepareStatement("SELECT DISTINCT house FROM markwithHouse");
					rs1=psmt1.executeQuery();
					double point=0.0;
					while(rs1.next()) {
						eventRegVo vo=new eventRegVo();
						
						
						
						PreparedStatement psmttt=conn.prepareStatement("SELECT housename FROM `campus_house_settings` WHERE house_id=?");
						psmttt.setString(1, rs1.getString("house"));
						ResultSet rstt=psmttt.executeQuery();
						if(rstt.next()) {
							for(int i=0;i<list.size();i++) {
								if(list.get(i).getCategoryId().equalsIgnoreCase(rs1.getString("house"))) {
									point=point+(Double.parseDouble(list.get(i).getScoredmarks()));
									
								}
								
							}
							vo.setHouseName(rstt.getString("housename"));
							vo.setScoredmarks(point+"");
							vo.setCategoryId(rstt.getString("housename"));
							
							rstt.close();
							psmttt.close();
							mainList.add(vo);
						}
						
						
					}
					
				
				
				
				
				    
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			if (rs != null && !rs.isClosed()) {
				
					rs.close();
		}
			if (rs1 != null && !rs1.isClosed()) {
		
				rs1.close();
			
		}
			
			if (psmt != null && !psmt.isClosed()) {
			
					psmt.close();
				
			}
			if (psmt1 != null && !psmt1.isClosed()) {
				
					psmt1.close();
				
			}
			if (conn != null && !conn.isClosed()) {
				
					conn.close();
				
			}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getOverAllReportMarksHouse : Ending");
		return mainList;
	}

	public List<eventRegVo> getEventIdCardDesignList(eventRegVo filterpojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl: getEventIdCardDesignList Starting");
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<eventRegVo> list = new ArrayList<eventRegVo>();
	
	try {
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement("SELECT * FROM `campus_event_registration` WHERE `event_Id`=?");
		psmt.setString(1, filterpojo.getEventId());
		System.out.println("psdsdt="+psmt);
	rs = psmt.executeQuery();
	while (rs.next()) {
		eventRegVo vo = new eventRegVo();
		vo.setEventName(rs.getString("eventName"));
		
			list.add(vo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventIdCardDesignList Ending");
		return list;
	}

	public ArrayList<eventRegVo> getEventVolanteerIdPrintList(String evenm, String evecat, String prognm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsDaoImpl : getEventVolanteerIdPrintList Starting");
	PreparedStatement psmt,psmt1,psmt2,psmt3 = null;
	Connection conn= null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	ResultSet rs3 = null;
	String stuid = null;
	ArrayList<eventRegVo> mainList=new ArrayList<eventRegVo>();
	try{
		conn=JDBCConnection.getSeparateConnection();

	
		ArrayList<eventRegVo> subList = new ArrayList<eventRegVo>();
		
	
		String[] ParticipantsAdmisNos = prognm.split(",");
	
	
		for(int i=0;i<ParticipantsAdmisNos.length;i++){
			psmt3=conn.prepareStatement("SELECT cs.`student_id_int`  FROM `campus_student` cs JOIN `campus_event_volunteerregistation` cev ON cev.admission_no=cs.`student_admissionno_var` WHERE cev.`volunteer_id`=?");
			psmt3.setString(1, ParticipantsAdmisNos[i]);
			rs3=psmt3.executeQuery();
			System.out.println("studentData222222222"+psmt3);
			while(rs3.next()){
				stuid = rs3.getString("student_id_int");
			}
			
			
			psmt=conn.prepareStatement("SELECT ces.*,cer.`eventName`,cer.`event_Id`,cer.accyer_Id,ceg.greenroom_name,stg.stageName FROM campus_event_volunteerregistation ces  LEFT JOIN `campus_event_registration` cer ON cer.`event_Id`=ces.`event_id` LEFT JOIN `campus_event_stage` stg ON ces.stage_id=stg.stageId LEFT JOIN `campus_event_greenroom` ceg ON ces.greenroom_id=ceg.greenroom_id WHERE ces.volunteer_id = ?");	
			psmt.setString(1, ParticipantsAdmisNos[i]);
			
		
		
		
		System.out.println("studentData111111111"+psmt);
		rs=psmt.executeQuery();
		
		while(rs.next()){
			psmt2=conn.prepareStatement("SELECT l.`Location_Name`,cs.*,csc.*, csc.student_rollno rollNo,csc.student_imgurl_var imgurl_var,cc.classdetails_name_var,sec.classsection_name_var FROM campus_student cs  JOIN campus_student_classdetails csc ON csc.student_id_int=cs.student_id_int  JOIN campus_classdetail cc ON (cc.classdetail_id_int=csc.classdetail_id_int AND cc.locationId=csc.locationId) JOIN campus_classsection sec ON csc.classsection_id_int=sec.classsection_id_int  JOIN `campus_location` l ON l.`Location_Id`=cs.`locationId`  WHERE csc.fms_acadamicyear_id_int=? AND cs.student_id_int=? ");
			psmt2.setString(1, rs.getString("accyer_Id"));
			psmt2.setString(2, stuid);
			rs2=psmt2.executeQuery();
			System.out.println("studentData3333333333"+psmt2);
			while(rs2.next()){
				
				eventRegVo vo=new eventRegVo();
			
				vo.setEventName(rs.getString("eventName"));
				
				
				
				vo.setAdmissionNo(rs2.getString("student_admissionno_var"));
				vo.setLocation(rs2.getString("Location_Name"));
				vo.setStudentName(rs2.getString("student_fname_var")+" "+rs2.getString("student_lname_var"));
				vo.setClassName(rs2.getString("classdetails_name_var")+"-"+rs2.getString("classsection_name_var"));
				vo.setRollNumber(rs2.getString("rollNo"));
				vo.setImageUrl(rs2.getString("imgurl_var"));
				vo.setStageName(rs.getString("stageName"));
				
				
				
				
				mainList.add(vo);
				}
	
		}
	
	
	
			}
			
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in EventsDaoImpl : getEventVolanteerIdPrintList : Ending");
		return mainList;
	}

	

}

