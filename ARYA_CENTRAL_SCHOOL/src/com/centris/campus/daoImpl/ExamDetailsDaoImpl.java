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
import org.apache.struts.action.ActionForward;

import com.centris.campus.dao.ExamDetailsDao;
import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.util.ExamDetailsSQLUtil;
import com.centris.campus.util.ExamSqlUtils;
import com.centris.campus.util.ExamTimeTableSqlUtils;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReligionCasteCasteCategoryUtils;
import com.centris.campus.vo.ExaminationDetailsVo;

public class ExamDetailsDaoImpl implements ExamDetailsDao {
	private static final Logger logger = Logger
			.getLogger(StreamDetailsDaoImpl.class);

	@Override
	public List<ExamDetailsPojo> getExamDetailsDao() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getExamDetailsDao Starting");
		
		List<ExamDetailsPojo> examPojoList = new ArrayList<ExamDetailsPojo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ExamSqlUtils.GET_EXAM_DETAILS);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				ExamDetailsPojo examPojo = new ExamDetailsPojo();
				
				examPojo.setExamid(rs.getString("examid"));
				examPojo.setExamName(rs.getString("examname"));
				examPojo.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				examPojo.setEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				examPojo.setAccyear(rs.getString("acadamic_year"));
				examPojo.setDescription(rs.getString("description"));
				examPojoList.add(examPojo);
				
			}
			
		} catch (Exception e) {
			
		}
		finally{
			try{
				if(rs !=null && !rs.isClosed()){
					rs.close();
				}
				if(pstmt !=null && !pstmt.isClosed()){
					pstmt.close();
				}
				
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getExamDetailsDao Endinging");
		return examPojoList;
	}

    //get Academic year Name
	public String getaccyName(String accyear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getaccyName Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String accyName = null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select acadamic_year from campus_acadamicyear where acadamic_id = ?");
			pstmt.setString(1,accyear);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				accyName = rs.getString("acadamic_year");
			}
		
		}
		catch (Exception e) {
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
		}logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getaccyName Endinging");
		return accyName;
      }
public String insertGradeSettings(ExamDetailsPojo obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getaccyName Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String accyName = null;
		String msg =null;
		int count =0;
		IDGenerator id = new IDGenerator();
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("insert into campus_exam_gradesettings (grade_id,grade_name,comments,min_marks,max_marks,accyear,created_by,loc_id) values (?,?,?,?,?,?,?,?)");
			
			pstmt.setString(1,id.getPrimaryKeyID("campus_exam_gradesettings"));
			pstmt.setString(2,obj.getGradename());
			pstmt.setString(3,obj.getComments());
			pstmt.setString(4,obj.getMin_marks());
			pstmt.setString(5,obj.getMax_marks());
			pstmt.setString(6,obj.getAccyearId());
			pstmt.setString(7,obj.getCreatedBy());
			pstmt.setString(8,obj.getLocid());
			count = pstmt.executeUpdate();
			if(count > 0){
				
				msg="success";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(rs !=null && !rs.isClosed()){
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
		return msg;
	}

	@Override
	public ArrayList<ExamDetailsPojo> displayGradeSettings(String accyear,String location) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ExamDetailsPojo> list = new ArrayList<ExamDetailsPojo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select * from  campus_exam_gradesettings where accyear=? and loc_id=? order by substring(grade_name, 1, 1),case substring(grade_name,2)when '++' then 1 when '+' then 2 end ");
			pstmt.setString(1,accyear);
			pstmt.setString(2,location);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ExamDetailsPojo pojo = new ExamDetailsPojo();
				pojo.setGradename(rs.getString("grade_name"));
				pojo.setComments(rs.getString("comments"));
				pojo.setMin_marks(rs.getString("min_marks"));
				pojo.setMax_marks(rs.getString("max_marks"));
				pojo.setGradeid(rs.getString("grade_id"));
				list.add(pojo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(rs !=null && !rs.isClosed()){
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
	public String deleteGradeSettings(String gradeid,String locname,String accyear) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		String msg = null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("delete from campus_exam_gradesettings where grade_id = ? and loc_id=? and accyear=?");
			pstmt.setString(1,gradeid);
			pstmt.setString(2,locname);
			pstmt.setString(3,accyear);
			count = pstmt.executeUpdate();
			if(count > 0){
				msg = "success";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		return msg;
	}

	@Override
	public String editGradeSettings(ExaminationDetailsVo list) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		String msg = null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("update campus_exam_gradesettings set grade_name=?,comments=?,min_marks=?,max_marks=? where grade_id=? and loc_id=? and accyear=?");
			pstmt.setString(1,list.getGradename());
			pstmt.setString(2,list.getComments());
			pstmt.setString(3,list.getMin_marks());
			pstmt.setString(4,list.getMax_marks());	
			pstmt.setString(5,list.getGradeid());
			pstmt.setString(6,list.getLocationid());
			pstmt.setString(7,list.getAccyear());
			System.out.println("pstmt" + pstmt);
			count = pstmt.executeUpdate();
			
			if(count >0){
				msg = "success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		return msg;
	}

	@Override
	public String checkduplicateGrade(String accyear, String gradename,String loc) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String msg = null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select count(*) gradename from campus_exam_gradesettings where grade_name = ? and accyear = ? and loc_id=?");
			pstmt.setString(1,gradename);
			pstmt.setString(2,accyear);
			pstmt.setString(3,loc);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt("gradename");
			}
			if(count > 0){
				msg = "true";
			}else{
				msg = "false";
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
		return msg;
	}

	
	// SubjectWise Marks
	@Override
	public ArrayList<ExaminationDetailsVo> getSubjectmarksStatus(String acyear) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String msg = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select examid,examcode,examname,startdate,enddate from campus_examination where acadamicyear =?");
			pstmt.setString(1,acyear.trim());
			rs = pstmt.executeQuery();
			while(rs.next()){
				ExaminationDetailsVo obj =new ExaminationDetailsVo();
				obj.setExamid(rs.getString("examid"));
				obj.setExamCode(rs.getString("examcode"));
				obj.setExamName(rs.getString("examname"));
				obj.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				obj.setEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
			
				PreparedStatement pstmtstatus = conn.prepareStatement("select count(*) from campus_subject_marks_wise where examid = ? and Accyear_Id = ?");
				pstmtstatus.setString(1,rs.getString("examid"));
				pstmtstatus.setString(2,acyear.trim());
				ResultSet statusrs = pstmtstatus.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				statusrs.close();
				pstmtstatus.close();
				if(count > 0){
					obj.setStatus("Completed");
				}else{
					obj.setStatus("Pending");
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
	public ArrayList<ExaminationDetailsVo> getSubjectmarksList(String accyear,String schoolLocation) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0,classcount=0;
		String msg = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select examid,examcode,examname,startdate,enddate from campus_examination where acadamicyear =? and loc_id = ? order by startdate");
			pstmt.setString(1,accyear.trim());
			pstmt.setString(2,schoolLocation.trim());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ExaminationDetailsVo obj =new ExaminationDetailsVo();
				obj.setExamid(rs.getString("examid"));
				obj.setExamCode(rs.getString("examcode"));
				obj.setExamName(rs.getString("examname"));
				obj.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				obj.setEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
			
				PreparedStatement pstmtstatus = conn.prepareStatement("select count(classdetail_id_int) from campus_classsection where locationId=?");
				pstmtstatus.setString(1,schoolLocation);
				ResultSet statusrs = pstmtstatus.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				PreparedStatement pstmtcountstatus = conn.prepareStatement("select count(distinct (classId)) from campus_subject_marks_wise where loc_id=?");
				pstmtcountstatus.setString(1, schoolLocation);
				ResultSet rs2 = pstmtcountstatus.executeQuery();
				while(rs2.next())
				{
					classcount=rs2.getInt(1);
				}
				statusrs.close();
				pstmtstatus.close();
				if(count!=0 && classcount!=0 &&(count == classcount)){
					obj.setStatus("Completed");
				}else{
					obj.setStatus("Pending");
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
	public ArrayList<ExaminationDetailsVo> getSubjectClass(String accyear, String examid,String locid,String classid) {

		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0,totalstrength=0,noofsubjects=0;
		
		String msg = null;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("SELECT ccd.classdetail_id_int,ccd.classdetails_name_var,ccs.classsection_id_int,ccs.classsection_name_var FROM campus_classdetail ccd LEFT JOIN campus_classsection ccs ON ccs.`classdetail_id_int`=ccd.classdetail_id_int AND ccd.locationId=ccs.locationId WHERE ccd.locationId=? AND ccd.classdetail_id_int=? ORDER BY LENGTH(ccd.classdetail_id_int),ccd.classdetail_id_int,ccs.classsection_name_var");
			pstmt.setString(1, locid);
			pstmt.setString(2, classid);
		    rs = pstmt.executeQuery();
			
			while(rs.next()){
				ExaminationDetailsVo obj =new ExaminationDetailsVo();
				slno++;
				obj.setSno1(slno);
				obj.setClassId(rs.getString("classdetail_id_int"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSection(rs.getString("classsection_id_int"));
				obj.setSectionName(rs.getString("classsection_name_var"));
				
				PreparedStatement pstmtcount=conn.prepareStatement("select count(student_id_int) as studentcount from campus_student_classdetails where classdetail_id_int=? and classsection_id_int=? and locationId=? and fms_acadamicyear_id_int=?");
				pstmtcount.setString(1,obj.getClassId());
				pstmtcount.setString(2, obj.getSection());
				pstmtcount.setString(3,locid);
				pstmtcount.setString(4,accyear);
				ResultSet rs1 =pstmtcount.executeQuery(); 
				while(rs1.next())
				{
					obj.setTot_strength(rs1.getString("studentcount"));		
				}
				int checktotal = Integer.parseInt(obj.getTot_strength());
			    if(checktotal>0)
			    {
				PreparedStatement pstmtstatus = conn.prepareStatement("select count(cm.scored_marks) from campus_studentwise_mark_details cm where cm.classid=? and cm.sectionid=?");
				pstmtstatus.setString(1,rs.getString("classdetail_id_int"));
				pstmtstatus.setString(2,rs.getString("classsection_id_int"));
				ResultSet statusrs = pstmtstatus.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				PreparedStatement countstatus = conn.prepareStatement("select count(subjectName) as subject from campus_subject where classid=?");
				countstatus.setString(1,obj.getClassId());
				ResultSet counrs = countstatus.executeQuery();
				while(counrs.next())
				{
					noofsubjects=counrs.getInt(1);
					
				}
				countstatus.setString(1,obj.getClassId());
				String total=obj.getTot_strength();
				int foo = Integer.parseInt(total);
				if(noofsubjects!=0){
				if(count/noofsubjects==foo){
					obj.setStatus("Completed");
				}else{
					obj.setStatus("Pending");
				}
				}
				else
			    {
			    	obj.setStatus("Pending");
			    }
			    }
			    else
			    {
			    	obj.setStatus("Pending");
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

	
	public ArrayList<ExaminationDetailsVo> getsubjectstudent(String accyear, String examid,String locid) {

		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0,totalstrength=0,noofsubjects=0,subjectidcount=0;
		
		String msg = null;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select ccd.classdetail_id_int,ccd.classdetails_name_var,ccs.classsection_id_int,ccs.classsection_name_var from campus_classdetail ccd join campus_classsection ccs on ccd.classdetail_id_int=ccs.classdetail_id_int and ccd.locationId=? and ccs.locationId=? order by length(ccd.classdetail_id_int),ccd.classdetail_id_int,ccs.classsection_name_var");
			pstmt.setString(1, locid);
			pstmt.setString(2, locid);
		    rs = pstmt.executeQuery();
			
			while(rs.next()){
				ExaminationDetailsVo obj =new ExaminationDetailsVo();
				slno++;
				obj.setSno1(slno);
				obj.setClassId(rs.getString("classdetail_id_int"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSection(rs.getString("classsection_id_int"));
				obj.setSectionName(rs.getString("classsection_name_var"));
				
				PreparedStatement pstmtcount=conn.prepareStatement("select count(subjectName) as subjectcount from campus_subject where locationId=? and classid=?");
				pstmtcount.setString(1,locid);
				pstmtcount.setString(2,obj.getClassId());

			
				ResultSet rs1 =pstmtcount.executeQuery(); 
				while(rs1.next())
				{
					obj.setTot_strength(rs1.getString("subjectcount"));		
				}
				int checktotal = Integer.parseInt(obj.getTot_strength());
				PreparedStatement pstmtcount1=conn.prepareStatement("select count(distinct subject_id) as subjectid from campus_subject_marks_wise where classId=? and SectionId=? and loc_id=?");
				pstmtcount1.setString(1,obj.getClassId());
				pstmtcount1.setString(2,obj.getSection());
				pstmtcount1.setString(3,locid);
				
				ResultSet rs2 =pstmtcount1.executeQuery();
				while(rs2.next())
				{
					 subjectidcount =rs2.getInt(1);
				}
				
			    if(checktotal==subjectidcount)
			    {
			    	obj.setStatus("Completed");
			    }
			    else{
					obj.setStatus("Pending");
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
	public String getexamName(String examid,String accyear,String locid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String msg = null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select examname,examcode from campus_examination where examid = ? and acadamicyear =? and loc_id=?");
			pstmt.setString(1,examid.trim());
			pstmt.setString(2,accyear.trim());
			pstmt.setString(3,locid.trim());
			rs = pstmt.executeQuery();
			while(rs.next()){
				msg = rs.getString("examname") +","+rs.getString("examcode");
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
		return msg;
	}

	@Override
	
	

	

	public ArrayList<ExaminationDetailsVo> getexamclassDetails(ExaminationDetailsVo obj) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{

			conn = JDBCConnection.getSeparateConnection();
			//pstmt = conn.prepareStatement("select classdetails_name_var,classsection_name_var,ca.acadamic_year,ce.examcode,ce.examname,ce.startdate,ce.enddate from campus_acadamicyear ca,campus_classdetail cd,campus_classsection cs,campus_examination ce where cd.classdetail_id_int=? and classsection_id_int=? and ce.examid =? and ca.acadamic_id =?");
			pstmt = conn.prepareStatement("SELECT ce.examcode,ce.examname,ce.startdate,ce.enddate,cd.classdetails_name_var,cs.classsection_name_var,ca.acadamic_year,cscsa.`work_edu_grade`,cscsa.`art_edu_grade`,cscsa.`health_edu_grade`,cscsa.`discipline_grade`,cext.`exam_prefix` FROM campus_examination ce LEFT JOIN campus_classdetail cd ON cd.classdetail_id_int=ce.`classid` LEFT JOIN campus_classsection cs ON cs.classdetail_id_int=cd.classdetail_id_int LEFT JOIN campus_acadamicyear ca ON ca.acadamic_id=ce.`acadamicyear` LEFT JOIN `campus_student_co_scholastic_areas` cscsa ON cscsa.`exam_id`=ce.`examid` LEFT JOIN `campus_examtype` cext ON cext.`examtypeid`=ce.`examtype` WHERE ce.classid=? AND cs.classsection_id_int=? AND ce.examid =? AND ce.`acadamicyear`=? AND ce.`loc_id`=?");
			pstmt.setString(1,obj.getClassId());
			pstmt.setString(2,obj.getSection());
			pstmt.setString(3,obj.getExamid());
			pstmt.setString(4,obj.getAccyearid());
			pstmt.setString(5,obj.getLocationid());
			System.out.println("tesatr "+pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ExaminationDetailsVo obje = new ExaminationDetailsVo();
				obje.setAccyear(rs.getString("acadamic_year"));
				obje.setSectionName(rs.getString("classsection_name_var"));
				obje.setClassname(rs.getString("classdetails_name_var"));
				obje.setExamCode(rs.getString("examcode"));
				obje.setExamName(rs.getString("examname"));
				obje.setExamtypeprefix(rs.getString("exam_prefix"));
				obje.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				obje.setEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				obje.setWorkedu_grade(rs.getString("work_edu_grade"));
				obje.setArtedu_grade(rs.getString("art_edu_grade"));
				obje.setHealthedu_grade(rs.getString("health_edu_grade"));
				obje.setDisciplinedu_grade(rs.getString("discipline_grade"));
				list.add(obje);
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
	public ArrayList<ExaminationDetailsVo> classWiseSubject(ExaminationDetailsVo obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0,classcount=0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		ArrayList<ExaminationDetailsVo> list2 = new ArrayList<ExaminationDetailsVo>();
		
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select subjectID,subjectName,Sub_Code from campus_subject where classid = ? and locationId=? order by subjectName");
			pstmt.setString(1,obj.getClassId());
			pstmt.setString(2,obj.getLocationid());

			rs = pstmt.executeQuery();
			while(rs.next()){
				slno++;
				ExaminationDetailsVo objec = new ExaminationDetailsVo();
				objec.setSno1(slno);
				objec.setSubId(rs.getString("subjectID"));
				objec.setSubjectName(rs.getString("subjectName"));
				objec.setSubCode(rs.getString("Sub_Code"));
		
				PreparedStatement statuspstmt = conn.prepareStatement("select count(subject_id) from campus_subject_marks_wise where subject_id=? and classId = ? and SectionId = ? and Accyear_Id = ? and loc_id = ?");
				statuspstmt.setString(1,objec.getSubId());
				statuspstmt.setString(2,obj.getClassId());
				statuspstmt.setString(3,obj.getSection());
				statuspstmt.setString(4,obj.getAccyearid());
				statuspstmt.setString(5,obj.getLocationid());
				ResultSet statusrs = statuspstmt.executeQuery();
			
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				PreparedStatement anotherstpstmt= conn.prepareStatement("select count(classdetail_id_int) from campus_student_classdetails where classdetail_id_int=? and classsection_id_int=? and locationId=? and fms_acadamicyear_id_int = ?");
				anotherstpstmt.setString(1, obj.getClassId());
				anotherstpstmt.setString(2,obj.getSection());
				anotherstpstmt.setString(3,obj.getLocationid());
				anotherstpstmt.setString(4,obj.getAccyearid());
				ResultSet stsrs1 =anotherstpstmt.executeQuery();
		

				while(stsrs1.next()){
					classcount=stsrs1.getInt(1);
				}
				if(count!=0 && classcount!=0 && (count == classcount)){
					objec.setStatus("Completed");
				}else{
					objec.setStatus("Pending");
				}
				list.add(objec);
			}
			count = 0;classcount=0;
			PreparedStatement pstmtlab = conn.prepareStatement("SELECT lab_id,Lab_Name,Lab_Code,Total_Marks,Pass_Marks FROM laboratory_details WHERE Class_Name = ? AND School_Name = ? order by Lab_Name");
			pstmtlab.setString(1,obj.getClassId());
			pstmtlab.setString(2,obj.getLocationid());
		
			ResultSet labrs = pstmtlab.executeQuery();
			while(labrs.next()){
				slno++;
				ExaminationDetailsVo objec1 = new ExaminationDetailsVo();
				objec1.setSno1(slno);
				objec1.setSubId(labrs.getString("lab_id"));
				objec1.setSubjectName(labrs.getString("Lab_Name"));
				objec1.setSubCode(labrs.getString("Lab_Code"));
				PreparedStatement statuspstmtlab = conn.prepareStatement("select count(subject_id) from campus_subject_marks_wise where subject_id=? and classId = ? and SectionId = ? and Accyear_Id = ? and loc_id = ?");
				statuspstmtlab.setString(1,objec1.getSubId());
				statuspstmtlab.setString(2,obj.getClassId());
				statuspstmtlab.setString(3,obj.getSection());
				statuspstmtlab.setString(4,obj.getAccyearid());
				statuspstmtlab.setString(5,obj.getLocationid());
				ResultSet statusrslab = statuspstmtlab.executeQuery();
				while(statusrslab.next()){
					count = statusrslab.getInt(1);
								}
				PreparedStatement anotherstpstmtlab = conn.prepareStatement("select count(classdetail_id_int) from campus_student_classdetails where classdetail_id_int=? and classsection_id_int=? and locationId=? and fms_acadamicyear_id_int = ?");
				anotherstpstmtlab.setString(1, obj.getClassId());
				anotherstpstmtlab.setString(2,obj.getSection());
				anotherstpstmtlab.setString(3,obj.getLocationid());
				anotherstpstmtlab.setString(4,obj.getAccyearid());
				ResultSet stsrslab =anotherstpstmtlab.executeQuery();
			
				while(stsrslab.next()){
					classcount=stsrslab.getInt(1);
				
				}
				if(count!=0 && classcount!=0 && (count == classcount)){
					objec1.setStatus("Completed");
				}else{
					objec1.setStatus("Pending");
				}
				list2.add(objec1);
			}
			list.addAll(list2);
			
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
	public String getsubDetails(ExaminationDetailsVo obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getsubDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			if(obj.getSubId().startsWith("SUB")){
				pstmt = conn.prepareStatement("select subjectName,Sub_Code,totalMarks,passMarks from campus_subject where subjectID = ? and locationId = ?");
				pstmt.setString(1,obj.getSubId());
				pstmt.setString(2,obj.getLocationid());
				rs = pstmt.executeQuery();
				while(rs.next()){
					result = rs.getString("subjectName")+","+rs.getString("Sub_Code")+","+rs.getString("totalMarks")+","+rs.getString("passMarks");
				}
			}else if(obj.getSubId().startsWith("LAB")){
				pstmt = conn.prepareStatement("select Lab_Name,Lab_Code,Total_Marks,Pass_Marks from laboratory_details where lab_id = ? and School_Name = ?");
				pstmt.setString(1,obj.getSubId());
				pstmt.setString(2,obj.getLocationid());
				rs = pstmt.executeQuery();
				while(rs.next()){
					result = rs.getString("Lab_Name")+","+rs.getString("Lab_Code")+","+rs.getString("Total_Marks")+","+rs.getString("Pass_Marks");
				}
			}
			
		} catch (Exception e) {
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
		return result;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getstudentsList(ExaminationDetailsVo obj,String schoolLocation) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getstudentsList Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1=null;
		ResultSet rs = null,rs1=null;

		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select cs.student_id_int,csc.student_rollno,cs.student_admissionno_var,concat(cs.student_fname_var,' ',cs.student_lname_var) as student from campus_student cs join campus_student_classdetails csc on csc.student_id_int=cs.student_id_int where csc.classdetail_id_int=? and csc.classsection_id_int=? and csc.locationId=? and  csc.fms_acadamicyear_id_int =? order by length(csc.student_rollno),csc.student_rollno");
			pstmt.setString(1,obj.getClassId());
			pstmt.setString(2,obj.getSection());
			pstmt.setString(3,schoolLocation);
			pstmt.setString(4,obj.getAccyearid());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){

				ExaminationDetailsVo objec = new ExaminationDetailsVo();
				objec.setStudentid(rs.getString("student_id_int"));
				objec.setRollno(rs.getString("student_rollno"));
				objec.setAddmisiionno(rs.getString("student_admissionno_var"));
				objec.setStudentname(rs.getString("student"));
				pstmt1 = conn.prepareStatement("select statusvalues,Sub_marks_id,scoredmarks from campus_subject_marks_wise where StudentId=? and subject_id=?");
				pstmt1.setString(1,rs.getString("student_id_int"));
				pstmt1.setString(2, obj.getSubId());
				System.out.println(pstmt1);
				rs1 = pstmt1.executeQuery();
				while (rs1.next())
				{    objec.setPrimaryid(rs1.getString("Sub_marks_id"));
					 objec.setScoredmarks(rs1.getString("scoredmarks"));
					 objec.setAttendace(rs1.getString("statusvalues"));
				}
				list.add(objec);
			}
			pstmt.close();rs.close();
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
	public ArrayList<ExaminationDetailsVo> classWiseStudent(
			ExaminationDetailsVo obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String student_id=null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select cs.student_id_int,csc.student_rollno,cs.student_admissionno_var,concat(cs.student_fname_var,' ',cs.student_lname_var) as student from campus_student cs join campus_student_classdetails csc on csc.student_id_int=cs.student_id_int and csc.classdetail_id_int=? and classsection_id_int=? where csc.locationId=? and csc.fms_acadamicyear_id_int=? order by student ");

			pstmt.setString(1,obj.getClassId());
			pstmt.setString(2,obj.getSection());
			pstmt.setString(3,obj.getLocationid());
			pstmt.setString(4,obj.getAccyearid());
	
			rs = pstmt.executeQuery();
			while(rs.next()){
				ExaminationDetailsVo objec = new ExaminationDetailsVo();
				objec.setStudentid(rs.getString("student_id_int"));
				objec.setStudentrollno(rs.getString("student_rollno"));
				objec.setAdmissionno(rs.getString("student_admissionno_var"));
				objec.setStudentname(rs.getString("student"));
				
				PreparedStatement statuspstmt = conn.prepareStatement("select count(scored_marks) from campus_studentwise_mark_details where stu_id=? and exam_id = ?");
				statuspstmt.setString(1,objec.getStudentid());
				statuspstmt.setString(2,obj.getExamid());
				ResultSet statusrs = statuspstmt.executeQuery();
			
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				if(count > 0){
					objec.setStatus("Completed");
				}else{
					objec.setStatus("Pending");
				}
				list.add(objec);
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
	public ArrayList<ExaminationDetailsVo> getStudentDetails(ExaminationDetailsVo obj1) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getsubDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null,rsscored=null;
		
		int sno=0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		ArrayList<ExaminationDetailsVo> list2 = new ArrayList<ExaminationDetailsVo>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			int examcount=getExitExamCount(obj1);
			if(examcount > 0){
				pstmt = conn.prepareStatement("SELECT subjectID,Sub_Code,subjectName,csmd.`total_marks`,csmd.`max_notebook_marks`,csmd.`max_subjenrich_marks`,csmd.max_periodic_marks FROM campus_subject cs LEFT JOIN `campus_studentwise_mark_details` csmd ON csmd.`classid`=cs.classid LEFT JOIN `campus_examination` ce ON ce.`examid`=csmd.`exam_id` AND ce.`classid`=csmd.classid WHERE cs.classid=? AND cs.locationId=? AND csmd.`exam_id`=? AND cs.isExam='Y' GROUP BY subjectID");
				pstmt.setString(1,obj1.getClassid());
				pstmt.setString(2,obj1.getLocationid());
				pstmt.setString(3,obj1.getExamid());
				rs = pstmt.executeQuery();
				while(rs.next()){
					sno++;
					ExaminationDetailsVo objresult = new ExaminationDetailsVo();
					objresult.setSno1(sno);
					objresult.setSubId(rs.getString("subjectID"));
					objresult.setSubCode(rs.getString("Sub_Code"));
					objresult.setSubjectName(rs.getString("subjectName"));
					if(rs.getString("total_marks") != null){
						objresult.setTot_marks(rs.getString("total_marks"));
					}else{
						objresult.setTot_marks("0");
					}
					if(rs.getString("max_notebook_marks") != null){
						objresult.setMax_notebook_marks(rs.getString("max_notebook_marks"));
					}else{
						objresult.setMax_notebook_marks("0");
					}
					if(rs.getString("max_subjenrich_marks") != null){
						objresult.setMax_subenrich_marks(rs.getString("max_subjenrich_marks"));
					}else{
						objresult.setMax_subenrich_marks("0");
					}
					if(rs.getString("max_periodic_marks") != null){
						objresult.setMaxperiodicmark(rs.getString("max_periodic_marks"));
					}else{
						objresult.setMaxperiodicmark("0");
					}

					PreparedStatement pstscoredmarks = conn.prepareStatement("select attendance_status,Stu_mark_id,scored_marks,notebook_marks,subject_enrich_marks,periodic_test from campus_studentwise_mark_details where stu_id=? and sub_id=? and exam_id=?");
					pstscoredmarks.setString(1,obj1.getStudentid());
					pstscoredmarks.setString(2,objresult.getSubId());
					pstscoredmarks.setString(3,obj1.getExamid());
					rsscored=pstscoredmarks.executeQuery();
					while(rsscored.next())
					{
						if(rsscored.getString("scored_marks") != null ){
							objresult.setScoredmarks(rsscored.getString("scored_marks")); 
						}else{
							objresult.setScoredmarks("0");
						}
						if(rsscored.getString("Stu_mark_id") != null){
							objresult.setStudenprimid(rsscored.getString("Stu_mark_id"));
						}else{
							objresult.setStudenprimid("0");
						}

						objresult.setAttendace(rsscored.getString("attendance_status"));

						if(rsscored.getString("notebook_marks") !=null){
							objresult.setNotebooks(rsscored.getString("notebook_marks"));
						}else{
							objresult.setNotebooks("0");
						}
						if(rsscored.getString("subject_enrich_marks") !=null){
							objresult.setSubjectenrichmarks(rsscored.getString("subject_enrich_marks")); 
						}else{
							objresult.setSubjectenrichmarks("0");
						}
						if(rsscored.getString("periodic_test") !=null){
							objresult.setPertest(rsscored.getString("periodic_test")); 
						}else{
							objresult.setPertest("0");
						}
					}
					list.add(objresult);
				}
			}else{
				pstmt = conn.prepareStatement("SELECT subjectID,Sub_Code,subjectName FROM campus_subject cs WHERE cs.classid=? AND cs.locationId=? AND cs.isExam='Y'");
				pstmt.setString(1,obj1.getClassid());
				pstmt.setString(2,obj1.getLocationid());
				System.out.println(pstmt);
				rs = pstmt.executeQuery();
				while(rs.next()){
					sno++;
					ExaminationDetailsVo objresult = new ExaminationDetailsVo();
					objresult.setSno1(sno);
					objresult.setSubId(rs.getString("subjectID"));
					objresult.setSubCode(rs.getString("Sub_Code"));
					objresult.setSubjectName(rs.getString("subjectName"));
					objresult.setTot_marks("0");
					objresult.setMax_notebook_marks("0");
					objresult.setMax_subenrich_marks("0");
					objresult.setMaxperiodicmark("0");

					PreparedStatement pstscoredmarks = conn.prepareStatement("select attendance_status,Stu_mark_id,scored_marks,notebook_marks,subject_enrich_marks,periodic_test from campus_studentwise_mark_details where stu_id=? and sub_id=? and exam_id=?");
					pstscoredmarks.setString(1,obj1.getStudentid());
					pstscoredmarks.setString(2,objresult.getSubId());
					pstscoredmarks.setString(3,obj1.getExamid());
					System.out.println(pstscoredmarks);
					rsscored=pstscoredmarks.executeQuery();
					while(rsscored.next())
					{
						if(rsscored.getString("scored_marks") != null ){
							objresult.setScoredmarks(rsscored.getString("scored_marks")); 
						}else{
							objresult.setScoredmarks("0");
						}
						if(rsscored.getString("Stu_mark_id") != null){
							objresult.setStudenprimid(rsscored.getString("Stu_mark_id"));
						}else{
							objresult.setStudenprimid("0");
						}

						objresult.setAttendace(rsscored.getString("attendance_status"));

						if(rsscored.getString("notebook_marks") !=null){
							objresult.setNotebooks(rsscored.getString("notebook_marks"));
						}else{
							objresult.setNotebooks("0");
						}
						if(rsscored.getString("subject_enrich_marks") !=null){
							objresult.setSubjectenrichmarks(rsscored.getString("subject_enrich_marks")); 
						}else{
							objresult.setSubjectenrichmarks("0");
						}
						if(rsscored.getString("periodic_test") !=null){
							objresult.setPertest(rsscored.getString("periodic_test")); 
						}else{
							objresult.setPertest("0");
						}
					}
					list.add(objresult);
				}
			}
			String prefix=obj1.getExamtypeprefix().toString();
			if(prefix.equals("yrlym")){
				PreparedStatement labpstmt = conn.prepareStatement("SELECT Lab_Code,lab_id,Lab_Name,Total_Marks,Pass_Marks FROM laboratory_details lab WHERE lab.Subject = ? AND lab.Class_Name=? AND lab.School_Name=?");
				for(int i=0;i<list.size();i++){

					labpstmt.setString(1,list.get(i).getSubId());
					labpstmt.setString(2,obj1.getClassid());
					labpstmt.setString(3,obj1.getLocationid());
					ResultSet labrs = labpstmt.executeQuery();

					while(labrs.next()){

						sno++;
						ExaminationDetailsVo objresult1 = new ExaminationDetailsVo();
						objresult1.setSno1(sno);
						objresult1.setSubId(labrs.getString("lab_id"));
						objresult1.setSubjectName(labrs.getString("Lab_Name"));
						objresult1.setSubCode(labrs.getString("Lab_Code"));
						objresult1.setTot_marks(labrs.getString("Total_Marks"));
						objresult1.setPassmarks(labrs.getString("Pass_Marks"));


						PreparedStatement labpstscoredmarks = conn.prepareStatement("select attendance_status,Stu_mark_id,scored_marks from campus_studentwise_mark_details where stu_id=? and sub_id=? and exam_id=?");
						labpstscoredmarks.setString(1,obj1.getStudentid());
						labpstscoredmarks.setString(2,objresult1.getSubId());
						labpstscoredmarks.setString(3,obj1.getExamid());
						ResultSet labrsscored=labpstscoredmarks.executeQuery();
						while(labrsscored.next())
						{
							objresult1.setScoredmarks(labrsscored.getString("scored_marks"));
							objresult1.setStudenprimid(labrsscored.getString("Stu_mark_id"));
							objresult1.setAttendace(labrsscored.getString("attendance_status"));
						}
						list2.add(objresult1);
					}
				}
				list.addAll(list2);
			}
		} catch (Exception e) {
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


	private int getExitExamCount(ExaminationDetailsVo obj1) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			conn = JDBCConnection.getSeparateConnection();

			pstmt=conn.prepareStatement(ExamDetailsSQLUtil.GET_EXAM_COUNT);
			pstmt.setString(1, obj1.getExamid());
			pstmt.setString(2, obj1.getAccyearid());
			pstmt.setString(3, obj1.getLocationid());
			rs=pstmt.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public String insertmarkentrydetails(ExaminationDetailsVo obj) {
		
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1 = null,pstmt3=null,pstmt4=null;
		ResultSet rs = null;
		String primaryid=null;
		int count=0,count1=0,count3=0,count4=0;
		String msg=null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt=conn.prepareStatement(ExamDetailsSQLUtil.INSERT_MARK_ENTRY_STUDENTTWISE);
			pstmt1=conn.prepareStatement(ExamDetailsSQLUtil.UPDATE_MARK_ENTRY_STUDENTTWISE);
			pstmt3=conn.prepareStatement(ExamDetailsSQLUtil.INSERT_GRADING_SCALE);
			pstmt4=conn.prepareStatement(ExamDetailsSQLUtil.UPDATE_GRADING_SCALE);
			String[] studentlist=obj.getStuprimaryid();
			for(int i=0;i<studentlist.length;i++){
				if(studentlist[i].equals("")){
					primaryid = IDGenerator.getPrimaryKeyID("campus_studentwise_mark_details");
					pstmt.setString(1,primaryid);
					pstmt.setString(2,obj.getClassid());
					pstmt.setString(3,obj.getSectionid());
					pstmt.setString(4,obj.getExamid());
					pstmt.setString(5,obj.getAccyear());
					pstmt.setString(6,obj.getSubid()[i].trim());
					pstmt.setString(7,obj.getStatusvalues()[i].trim());
					pstmt.setString(8,obj.getScoremarks()[i].trim());
					pstmt.setString(9,obj.getStudentid().trim());
					pstmt.setString(10,obj.getLocationid().trim());
					pstmt.setString(11,obj.getNotebookmarks()[i].trim());
					pstmt.setString(12,obj.getSubjectenrichment()[i].trim());
					pstmt.setString(13,obj.getMax_scored_marks()[i].trim());
					pstmt.setString(14,obj.getMaxnotebookmarks()[i].trim());
					pstmt.setString(15,obj.getMaxsubenrichmentmarks()[i].trim());
					pstmt.setString(16,obj.getCreatedBy());
					//`max_periodic_marks`,`periodic_test`
					pstmt.setString(17,obj.getMaxperiodicmarks()[i].trim());
					pstmt.setString(18,obj.getPeriodicscoredmarks()[i].trim());
					count =pstmt.executeUpdate();
				}else{
					pstmt1.setString(1, obj.getStatusvalues()[i].trim());
					pstmt1.setString(2,obj.getScoremarks()[i].trim());
					pstmt1.setString(3,obj.getNotebookmarks()[i].trim());
					pstmt1.setString(4,obj.getSubjectenrichment()[i].trim());
					pstmt1.setString(5,obj.getMax_scored_marks()[i].trim());
					pstmt1.setString(6,obj.getMaxnotebookmarks()[i].trim());
					pstmt1.setString(7,obj.getMaxsubenrichmentmarks()[i].trim());
					pstmt1.setString(8,obj.getCreatedBy());
					pstmt1.setDate(9,HelperClass.getCurrentSqlDate());
					pstmt1.setString(10,obj.getMaxperiodicmarks()[i].trim());
					pstmt1.setString(11,obj.getPeriodicscoredmarks()[i].trim());
					pstmt1.setString(12,obj.getStuprimaryid()[i].trim());
					pstmt1.setString(13,obj.getExamid());

					count1 =pstmt1.executeUpdate();

				}
			}
			
			if(count > 0)
			{
				if(obj.getExamtypeprefix().equalsIgnoreCase("hlfym")){
					pstmt3.setString(1, obj.getStudentid().trim());
					pstmt3.setString(2,obj.getLocationid().trim());
					pstmt3.setString(3,obj.getAccyear());
					pstmt3.setString(4,obj.getClassid());
					pstmt3.setString(5,obj.getSectionid());
					pstmt3.setString(6,obj.getWorkedu_grade().trim());
					pstmt3.setString(7,obj.getArtedu_grade().trim());
					pstmt3.setString(8,obj.getHealthedu_grade().trim());
					pstmt3.setString(9,obj.getDisciplinedu_grade().trim());
					pstmt3.setString(10,obj.getCreatedBy());
					pstmt3.setString(11,obj.getExamid());
					count3=pstmt3.executeUpdate();
					if(count3 >0){
						msg="inserted";
					}
				}else{
					msg="inserted";
				}
				
			}else if(count1 > 0){
				if(obj.getExamtypeprefix().equalsIgnoreCase("hlfym")){
					pstmt4.setString(1,obj.getWorkedu_grade().trim());
					pstmt4.setString(2,obj.getArtedu_grade().trim());
					pstmt4.setString(3,obj.getHealthedu_grade().trim());
					pstmt4.setString(4,obj.getDisciplinedu_grade().trim());
					pstmt4.setString(5,obj.getCreatedBy());
					pstmt4.setDate(6, HelperClass.getCurrentSqlDate());
					pstmt4.setString(7, obj.getStudentid().trim());
					pstmt4.setString(8,obj.getLocationid().trim());
					pstmt4.setString(9,obj.getAccyear());
					pstmt4.setString(10,obj.getClassid());
					pstmt4.setString(11,obj.getExamid());
					count4=pstmt4.executeUpdate();
					if(count4 >0){
						msg="inserted";
					}
				}else{
					msg="inserted";
				}
				
			}
			else
			{
				msg="failed";

			}
		}
	
		catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt3 != null&& (!pstmt3.isClosed())) {
					pstmt3.close();
				}
				if (pstmt4 != null&& (!pstmt4.isClosed())) {
					pstmt4.close();
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
		
		return msg;
	

	
	

	}

	@Override
	public String getlocationname(String schoolLocation) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String locationname=null; 
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt=conn.prepareStatement(ExamDetailsSQLUtil.GET_LOCATION);
		    pstmt.setString(1, schoolLocation);
			rs =pstmt.executeQuery();
			while(rs.next())
			{
				locationname=rs.getString("Location_Name");
			}
				
           
			}
	
		catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		
		return locationname;
	

	
	

	}

	@Override
	public String insertmarkentrysubjectwise(ExaminationDetailsVo obj,String schoolLocation) {
		
		System.out.println("it is coming inside the daoimpl:");
		
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1 = null;
		ResultSet rs = null;
		String primaryid=null;
		int count=0,count1=0;
		String msg=null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt=conn.prepareStatement(ExamDetailsSQLUtil.INSERT_MARK_ENTRY_SUBJECTTWISE);
			pstmt1=conn.prepareStatement(ExamDetailsSQLUtil.UPDATE_MARK_ENTRY_SUBJECTTWISE);
			
			String[] subjectlist=obj.getSubmarksid();
			for(int i=0;i<subjectlist.length;i++){
				if(subjectlist[i].equals("")){
						primaryid = IDGenerator.getPrimaryKeyID("campus_subject_marks_wise");
						pstmt.setString(1,primaryid);
						pstmt.setString(2, obj.getAccyear());
						pstmt.setString(3, obj.getClassid());
						pstmt.setString(4, obj.getExamid());
						pstmt.setString(5, obj.getSectionid());
						pstmt.setString(6, obj.getSubId());
						pstmt.setString(7, schoolLocation);
						pstmt.setString(8, obj.getStudentids()[i].trim());
						pstmt.setString(9,obj.getScoremarks()[i].trim());
						pstmt.setString(10, obj.getStatusvalues()[i].trim());
						count =pstmt.executeUpdate();
					
				}else{
					pstmt1.setString(1,obj.getScoremarks()[i].trim());
					pstmt1.setString(2,obj.getStatusvalues()[i].trim());
					pstmt1.setString(3,obj.getSubmarksid()[i].trim());

					count1 =pstmt1.executeUpdate();
					//count1=updatemarkentrysubjectwiselist(obj,schoolLocation);
				}
			}
			
			
			System.out.println(count);
		  if(count>0 || count1>0)
            {
            	msg="inserted";
            }
            else
            {
            	msg="failed";
            	
            }
			}
	
		catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		
		return msg;
	}
	
	@Override
	public String getclassname(String classid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String classname=null; 
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt=conn.prepareStatement(ExamDetailsSQLUtil.GET_CLASS);
		    pstmt.setString(1,classid);
			rs =pstmt.executeQuery();
			while(rs.next())
			{
				classname=rs.getString("classdetails_name_var");
			}
           
		}
	
		catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		
		return classname;
}
	
	@Override
	public ArrayList<ExaminationDetailsVo> getlistofExamCodes(
			String schoolLocation) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String examcode=null; 
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();

		try {
			ExaminationDetailsVo examcodes = new ExaminationDetailsVo();
			conn = JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ExamDetailsSQLUtil.GET_LISTOFEXAM_CODES);
		    pstmt.setString(1, schoolLocation);
//		    pstmt.setString(2, acyear);
			rs =pstmt.executeQuery();
			while(rs.next())
			{
				examcode=rs.getString("examcode");
			}
			examcodes.setExamCode("examcode");
				
			 list.add(examcodes);
			}
	
		catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	public String updatemarkentrysubjectwise(ExaminationDetailsVo obj,
			String schoolLocation) {
		
		System.out.println("it is coming inside the daoimpl:");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;
		String msg=null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt=conn.prepareStatement(ExamDetailsSQLUtil.UPDATE_MARK_ENTRY_SUBJECTTWISE);
			
			for(int j=0;j<obj.getStudentids().length;j++){
			
			
			pstmt.setString(1,obj.getScoremarks()[j].trim());
			pstmt.setString(2, obj.getStatusvalues()[j].trim());
			pstmt.setString(3,obj.getSubmarksid()[j].trim());
			
			System.out.println("pstmt for run query:" +pstmt);
			
			}
			
			
			count =pstmt.executeUpdate();
		
            if(count>0)
            {
            	msg="inserted";
            }
            else
            {
            	msg="failed";
            	
            }
			}
	
		catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		
		return msg;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> examTimeTableListYear(String accyear,String loc) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl: examTimeTableListYear : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select acadamic_id,acadamic_year,Location_Id,Location_Name from campus_acadamicyear,campus_location where acadamic_id like ? and Location_Id like ? order by startDate");
			pstmt.setString(1,accyear);
			pstmt.setString(2,loc);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				String locid = rs.getString("Location_Id");
				String academicyear = rs.getString("acadamic_id");
				int totexamcount = 0;
				int seccount =0;
				int secexmcount = 0;
				int clscountexam = 0;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setAccyearid(academicyear);
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setLocationid(locid);
				obj.setLocname(rs.getString("Location_Name"));
				obj.setSno1(slno);
				
				PreparedStatement statuspstmt = conn.prepareStatement("select count(*) from campus_exam_timetable where accyear_id = ? and loc_id like ?");
				statuspstmt.setString(1,academicyear);
				statuspstmt.setString(2,locid);
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				if(count > 0){
					PreparedStatement examcount = conn.prepareStatement("SELECT COUNT(examid) FROM campus_examination WHERE acadamicyear = ? AND loc_id LIKE ?");
					examcount.setString(1,academicyear);
					examcount.setString(2,locid);
					ResultSet examrs = examcount.executeQuery();
					while(examrs.next()){
						totexamcount = examrs.getInt(1);
					}
					if(count == totexamcount){
						obj.setStatus("Set");
						obj.setStatus1("Set");
					}else{
						obj.setStatus("Not Set");
						obj.setStatus1("Not");
					}
				}
				else{
					obj.setStatus("Not Set");
					obj.setStatus1("Not");
				}
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
	public List<ExaminationDetailsVo> getExamClassByLocation(String loc,String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl: getExamClassByLocation : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select classdetail_id_int,classdetails_name_var from campus_classdetail where locationId = ? order by length(classdetail_id_int),classdetail_id_int");
			pstmt.setString(1, loc);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				String classid = rs.getString("classdetail_id_int");
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setClassId(classid);
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSno1(slno);
				
				//get the section count in each class
				int seccount =0;
				PreparedStatement secpstmt = conn.prepareStatement("SELECT COUNT(*)classsection_id_int FROM campus_classsection WHERE classdetail_id_int = ? AND locationId = ?");
				secpstmt.setString(1,classid);
				secpstmt.setString(2,accyear);
				ResultSet rssec =secpstmt.executeQuery();
				while(rssec.next()){
					seccount = rssec.getInt(1);
				}
				
				PreparedStatement statuspstmt = conn.prepareStatement("select count(*) from campus_exam_timetable where accyear_id = ? and loc_id = ? and class_id = ?");
				statuspstmt.setString(1,accyear);
				statuspstmt.setString(2,loc);
				statuspstmt.setString(3,classid);
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				
				if(seccount !=0 && count !=0){
					if(count == seccount){
						obj.setStatus("Set");
						obj.setStatus1("Set");
					}
					else{
						obj.setStatus("Not Set");
						obj.setStatus1("Not");
					}
				}else{
					obj.setStatus("Not Set");
					obj.setStatus1("Not");
				}
				
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
	public List<ExaminationDetailsVo> getexamlistbyclass(ExamTimetablePojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl: getExamClassByLocation : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		String timetable = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select examid,examcode,examname,startdate,enddate,cs.classsection_id_int,classsection_name_var from campus_examination join campus_classsection cs on cs.locationId = loc_id where cs.classdetail_id_int =? and acadamicyear = ? and loc_id like ? and cs.classsection_id_int like ? and examid like ? order by classsection_name_var,startdate");
			pstmt.setString(1,pojo.getClassId());
			pstmt.setString(2,pojo.getAccyearid());
			pstmt.setString(3,pojo.getLocid());
			pstmt.setString(4,pojo.getSectionid());
			pstmt.setString(5,pojo.getExamId());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setExamid(rs.getString("examid"));
				obj.setExamCode(rs.getString("examcode"));
				obj.setExamName(rs.getString("examname"));
				obj.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				obj.setEndTime(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				obj.setSectionName(rs.getString("classsection_name_var"));
				obj.setSection(rs.getString("classsection_id_int"));
				obj.setSno1(slno);
				PreparedStatement statuspstmt = conn.prepareStatement("select count(*) from campus_exam_timetable where accyear_id = ? and loc_id = ? and class_id = ? and section_id like ? and examcode = ?");
				statuspstmt.setString(1,pojo.getAccyearid());
				statuspstmt.setString(2,pojo.getLocid());
				statuspstmt.setString(3,pojo.getClassId());
				statuspstmt.setString(4,rs.getString("classsection_id_int"));
				statuspstmt.setString(5,rs.getString("examid"));
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				if(count > 0){
					PreparedStatement setstatus = conn.prepareStatement("select timetable_id from campus_exam_timetable where accyear_id=? and loc_id=? and class_id=? and section_id=? and examcode=?");
					setstatus.setString(1,pojo.getAccyearid());
					setstatus.setString(2,pojo.getLocid());
					setstatus.setString(3,pojo.getClassId());
					setstatus.setString(4,rs.getString("classsection_id_int"));
					setstatus.setString(5,rs.getString("examid"));
					ResultSet setrs = setstatus.executeQuery();
					while(setrs.next()){
						timetable = setrs.getString("timetable_id");
					}
					obj.setTimetableid(timetable);
					obj.setStatus("Set");
					obj.setStatus1("set");
				}
				else{
					obj.setStatus("Not Set");
					obj.setStatus1("not");
				}
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		System.out.println(slno);
		return list;
	}

	@Override
	public ExaminationDetailsVo getexamdetails(ExamTimetablePojo pojo) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ResultSet rs = null;
		ExaminationDetailsVo obj = null;
		try {
			obj = new ExaminationDetailsVo();
	
			conn = JDBCConnection.getSeparateConnection();
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement("select examcode,examname,startdate,enddate from campus_examination where examid = ? and acadamicyear = ? and loc_id = ?");
			pstmt.setString(1,pojo.getExamId());
			pstmt.setString(2,pojo.getAccyearid());
			pstmt.setString(3,pojo.getLocid());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				obj.setExamCode(rs.getString("examcode"));
				obj.setExamName(rs.getString("examname"));
				obj.setExamstartdate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				obj.setExamenddate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
			}
			pstmt1=conn.prepareStatement("select classdetails_name_var,classsection_name_var from campus_classdetail cd,campus_classsection cs where cd.classdetail_id_int = ? and cs.classsection_id_int = ?");
			pstmt1.setString(1,pojo.getClassId());
			pstmt1.setString(2,pojo.getSectionid());
			System.out.println(pstmt1);
			rs1 = pstmt1.executeQuery();
			while(rs1.next()){
				obj.setClassname(rs1.getString("classdetails_name_var"));
				obj.setSectionName(rs1.getString("classsection_name_var"));
			}
			conn.commit();
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				
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
		return obj;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getsubdetails(ExamTimetablePojo pojo) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ResultSet rs = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		String startdate = null;
		String enddate = null;
		int slno = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement("select startdate from campus_examination where examid = ? and loc_id = ?");
			pstmt.setString(1,pojo.getExamId());
			pstmt.setString(2,pojo.getLocid());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				startdate = (HelperClass.convertDatabaseToUI(rs.getString("startdate")));
			}
			pstmt1=conn.prepareStatement("select subjectID,subjectName,Sub_Code,isLanguage from campus_subject where locationId = ? and classid = ? and isExam='Y' order by subjectName");
			pstmt1.setString(1,pojo.getLocid());
			pstmt1.setString(2,pojo.getClassId());
			System.out.println(pstmt1);
			rs1 = pstmt1.executeQuery();
			while(rs1.next()){
				slno++;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setSno1(slno);
				obj.setSubId(rs1.getString("subjectID"));
				obj.setSubjectName(rs1.getString("subjectName"));
				obj.setSubCode(rs1.getString("Sub_Code"));
				obj.setExamstartdate(startdate);
				obj.setIslanguage(rs1.getString("isLanguage"));
				list.add(obj);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
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
		return list;
	}

	
	@Override
	public String savetimetabledetails(ExamTimetablePojo pojo, String[] subid1, String[] starttime1, String[] endtime1,
			String[] startdate) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String result = null;
		int count = 0;
		int count1= 0;
		
		IDGenerator id = new IDGenerator();
		String key = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			if(pojo.getSectionid().equalsIgnoreCase("%%")){
				pstmt2 = conn.prepareStatement("select classsection_id_int from campus_classsection where locationId = ? and classdetail_id_int = ? order by classsection_name_var");
				pstmt2.setString(1, pojo.getLocid());
				pstmt2.setString(2, pojo.getClassId());
				rs = pstmt2.executeQuery();
				while(rs.next()){
					key = id.getPrimaryKeyID("campus_exam_timetable");
					pstmt=conn.prepareStatement("insert into campus_exam_timetable (timetable_id,accyear_id,loc_id,class_id,section_id,examcode) values (?,?,?,?,?,?)");
					pstmt.setString(1,key);
					pstmt.setString(2,pojo.getAccyearid());
					pstmt.setString(3,pojo.getLocid());
					pstmt.setString(4,pojo.getClassId());
					pstmt.setString(5,rs.getString("classsection_id_int"));
					pstmt.setString(6,pojo.getExamId());
					
					System.out.println(pstmt);
					count = pstmt.executeUpdate();
					for(int i=0;i<subid1.length;i++){
						pstmt1 = conn.prepareStatement("insert into campus_detailed_timetable(examtimetablecode,sub_id,startdate,starttime,endtime) values(?,?,?,?,?)");
						pstmt1.setString(1,key);
						pstmt1.setString(2,subid1[i]);
						pstmt1.setString(3,HelperClass.convertUIToDatabase(startdate[i]));
						pstmt1.setString(4,starttime1[i]);
						pstmt1.setString(5,endtime1[i]);
						count1 = pstmt1.executeUpdate();
						}
						
						if(count1 > 0){
							result = "true";
						}
				}
			}
			else{
			key = id.getPrimaryKeyID("campus_exam_timetable");
			pstmt=conn.prepareStatement("insert into campus_exam_timetable (timetable_id,accyear_id,loc_id,class_id,section_id,examcode) values (?,?,?,?,?,?)");
			pstmt.setString(1,key);
			pstmt.setString(2,pojo.getAccyearid());
			pstmt.setString(3,pojo.getLocid());
			pstmt.setString(4,pojo.getClassId());
			pstmt.setString(5,pojo.getSectionid());
			pstmt.setString(6,pojo.getExamId());
			
			System.out.println(pstmt);
			count = pstmt.executeUpdate();
		
			for(int i=0;i<subid1.length;i++){
			pstmt1 = conn.prepareStatement("insert into campus_detailed_timetable(examtimetablecode,sub_id,startdate,starttime,endtime) values(?,?,?,?,?)");
			pstmt1.setString(1,key);
			pstmt1.setString(2,subid1[i]);
			pstmt1.setString(3,HelperClass.convertUIToDatabase(startdate[i]));
			pstmt1.setString(4,starttime1[i]);
			pstmt1.setString(5,endtime1[i]);
			count1 = pstmt1.executeUpdate();
			}
			
			if(count1 > 0){
				result = "true";
			}
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (Exception e) {
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
		return result;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getexamsbtselection(String accyear, String locid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		
		try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select examid,examcode,examname from campus_examination where acadamicyear = ? and loc_id like ?");
			pstmt.setString(1,accyear);
			pstmt.setString(2,locid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setExamid(rs.getString("examid"));
				obj.setExamName(rs.getString("examcode")+" - "+rs.getString("examname"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs !=null && !rs.isClosed()){
					rs.close();
				}
				if(pstmt !=null && !pstmt.isClosed()){
					pstmt.close();
				}
				
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<ExaminationDetailsVo> getExamClassByLocation(String accyear, String locid, String examid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl: getExamClassByLocation : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
	
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select classdetail_id_int,classdetails_name_var from campus_classdetail where locationId = ? order by length(classdetail_id_int),classdetail_id_int");
			pstmt.setString(1, accyear);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				String classid = rs.getString("classdetail_id_int");
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setClassId(classid);
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSno1(slno);
				
				//get the section count in each class
				int seccount =0;
				PreparedStatement secpstmt = conn.prepareStatement("SELECT COUNT(*)classsection_id_int FROM campus_classsection WHERE classdetail_id_int = ? AND locationId = ?");
				secpstmt.setString(1,classid);
				secpstmt.setString(2,accyear);
				ResultSet rssec =secpstmt.executeQuery();
				while(rssec.next()){
					seccount = rssec.getInt(1);
				}
								
				PreparedStatement statuspstmt = conn.prepareStatement("select count(*) from campus_exam_timetable where accyear_id = ? and loc_id = ? and class_id = ? and examcode = ?");
				statuspstmt.setString(1,locid);
				statuspstmt.setString(2,accyear);
				statuspstmt.setString(3,classid);
				statuspstmt.setString(4,examid);
			
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				
				obj.setTotseccount(seccount);
				obj.setSetseccount(count);
				
				if(count !=0 && seccount!=0){
					
					if(count == seccount){
						obj.setStatus1("Set");
						obj.setStatus("Set");
						obj.setSetmsg("Time Table is Set for All the Sections");
					}
					else{
						obj.setStatus1("Not");
						obj.setStatus("Not Set");
						obj.setSetmsg("Out of"+" "+seccount+" "+"Sections"+" "+"Set"+" "+count);
					}
				}
				else{
					obj.setStatus1("Not");
					obj.setStatus("Not Set");
					obj.setSetmsg("");
				}
				
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
	public ArrayList<ExaminationDetailsVo> getexamsettingslist(String accyear, String locid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmListYear Starting");
		ArrayList<ExaminationDetailsVo> examlistyear = new ArrayList<ExaminationDetailsVo>();

		PreparedStatement pstmt = null,pstmt1=null;
		Connection conn = null;
		ResultSet rs = null,rs1 = null;
		int yearcount=0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMYEAR1);
			pstmt.setString(1,locid);
			pstmt.setString(2,accyear);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			int count=0;
			while (rs.next()) {
				ExaminationDetailsVo exampojo = new ExaminationDetailsVo();
				count++;
				exampojo.setSno1(count);
				exampojo.setAccyearid(rs.getString("acadamic_id"));
				exampojo.setAccyear(rs.getString("acadamic_year"));
				exampojo.setStartDate(rs.getString("startDate"));
				exampojo.setEndDate(rs.getString("endDate"));
				exampojo.setLocationid(rs.getString("Location_Id"));
				exampojo.setLocname(rs.getString("Location_Name"));
				pstmt1 = conn.prepareStatement(ExamTimeTableSqlUtils.GET_ACCYEAR_COUNT);
				pstmt1.setString(1, rs.getString("acadamic_id"));
				pstmt1.setString(2, rs.getString("Location_Id"));
				rs1 = pstmt1.executeQuery();
				while(rs1.next()) {
					yearcount=rs1.getInt("accyearcount");
				}	
				exampojo.setAccyearcount(yearcount);
				if (yearcount > 0) {
					exampojo.setStatus("Set");
				} else {
					exampojo.setStatus("Not Set");
				}
				examlistyear.add(exampojo);
			}
			

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (rs1 != null && !rs1.isClosed()) {
					rs1.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
				}
				
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmListYear Ending");
		return examlistyear;

	}

	@Override
	public String checkduplicatedate(ExamTimetablePojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmListYear Starting");
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String result=null;
		int count=0;
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select count(*) from campus_examination where acadamicyear = ? and loc_id = ? and classid = ? and ((startdate between ? and ?) or (enddate between ? and ?))");
			pstmt.setString(1, pojo.getAccyearid());
			pstmt.setString(2, pojo.getLocid());
			pstmt.setString(3, pojo.getClassId());
			pstmt.setString(4, pojo.getExamStartdate());
			pstmt.setString(5, pojo.getExamEndDate());
			pstmt.setString(6, pojo.getExamStartdate());
			pstmt.setString(7, pojo.getExamEndDate());
		
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1) ;
			}
			if(count>0){
				System.out.println(count);
				result = "true";
			}else{
				result = "false";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
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
		
		return result;
		
	}

	@Override
	public ExaminationDetailsVo getexamdetailsbyset(ExamTimetablePojo pojo) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		//PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs = null;
		//ResultSet rs2 = null;
		ExaminationDetailsVo obj = null;
		try {
			obj = new ExaminationDetailsVo();
	
			conn = JDBCConnection.getSeparateConnection();
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement("select examcode,examname,startdate,enddate from campus_examination where examid = ? and acadamicyear = ? and loc_id = ?");
			pstmt.setString(1,pojo.getExamId());
			pstmt.setString(2,pojo.getAccyearid());
			pstmt.setString(3,pojo.getLocid());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				obj.setExamCode(rs.getString("examcode"));
				obj.setExamName(rs.getString("examname"));
				obj.setExamstartdate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				obj.setExamenddate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
			}
			pstmt1=conn.prepareStatement("select classdetails_name_var,classsection_name_var from campus_classdetail cd,campus_classsection cs where cd.classdetail_id_int = ? and cs.classsection_id_int = ?");
			pstmt1.setString(1,pojo.getClassId());
			pstmt1.setString(2,pojo.getSectionid());
			System.out.println(pstmt1);
			rs1 = pstmt1.executeQuery();
			while(rs1.next()){
				obj.setClassname(rs1.getString("classdetails_name_var"));
				obj.setSectionName(rs1.getString("classsection_name_var"));
			}
			
			conn.commit();
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				
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
		return obj;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getsubdetailsset(ExamTimetablePojo pojo) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ResultSet rs = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		String startdate = null;
		String enddate = null;
		int slno = 0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement("select sub_id,startdate,starttime,endtime from campus_detailed_timetable where examtimetablecode = ?");
			pstmt.setString(1,pojo.getTimetableid());
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				obj.setExamstarttime(rs.getString("starttime"));
				obj.setExamendtime(rs.getString("endtime"));
			pstmt1=conn.prepareStatement("select subjectID,subjectName,Sub_Code,isLanguage from campus_subject where locationId = ? and classid = ? and subjectID = ? order by subjectName");
			pstmt1.setString(1,pojo.getLocid());
			pstmt1.setString(2,pojo.getClassId());
			pstmt1.setString(3,rs.getString("sub_id"));
			System.out.println(pstmt1);
			rs1 = pstmt1.executeQuery();
			while(rs1.next()){
				slno++;
				
				obj.setSno1(slno);
				obj.setSubId(rs1.getString("subjectID"));
				obj.setSubjectName(rs1.getString("subjectName"));
				obj.setSubCode(rs1.getString("Sub_Code"));
				obj.setExamstartdate(startdate);
				obj.setIslanguage(rs1.getString("isLanguage"));
				list.add(obj);
			}
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (Exception e) {
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
	public String savetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String result = null;
		int count = 0;
		int count1= 0;
		
		IDGenerator id = new IDGenerator();
		String key = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			if(pojo.getSectionid().equalsIgnoreCase("%%")){
				pstmt2 = conn.prepareStatement("select classsection_id_int from campus_classsection where locationId = ? and classdetail_id_int = ? order by classsection_name_var");
				pstmt2.setString(1, pojo.getLocid());
				pstmt2.setString(2, pojo.getClassId());
				rs = pstmt2.executeQuery();
				while(rs.next()){
					String sectionid = rs.getString("classsection_id_int");
					PreparedStatement pstmttimeid = conn.prepareStatement("SELECT timetable_id FROM campus_exam_timetable WHERE section_id = ? AND accyear_id = ? AND loc_id = ?");
					pstmttimeid.setString(1,sectionid);
					pstmttimeid.setString(2,pojo.getAccyearid());
					pstmttimeid.setString(3,pojo.getLocid());
					ResultSet rstimeid = pstmttimeid.executeQuery();
					while(rstimeid.next()){
						key = rstimeid.getString("timetable_id");
					}
					pstmt=conn.prepareStatement("update campus_exam_timetable set accyear_id=?,loc_id=?,class_id=?,section_id=?,examcode=? where timetable_id=? ");
					pstmt.setString(1,pojo.getAccyearid());
					pstmt.setString(2,pojo.getLocid());
					pstmt.setString(3,pojo.getClassId());
					pstmt.setString(4,sectionid);
					pstmt.setString(5,pojo.getExamId());
					pstmt.setString(6,key);
					System.out.println(pstmt);
					count = pstmt.executeUpdate();
					for(int i=0;i<subid1.length;i++){
						pstmt1 = conn.prepareStatement("update  campus_detailed_timetable set startdate=?,starttime=?,endtime=? where examtimetablecode =? and sub_id=?");
						pstmt1.setString(5,subid1[i]);
						pstmt1.setString(1,HelperClass.convertUIToDatabase(startdate[i]));
						pstmt1.setString(2,starttime1[i]);
						pstmt1.setString(3,endtime1[i]);
						pstmt1.setString(4,key);
						System.out.println(pstmt1);
						count1 = pstmt1.executeUpdate();
						}
						if(count1 > 0){
							result = "true";
						}
				}
			}
			else{
			
			pstmt=conn.prepareStatement("update campus_exam_timetable set accyear_id=?,loc_id=?,class_id=?,section_id=?,examcode=? where timetable_id=?");
		
			pstmt.setString(1,pojo.getAccyearid());
			pstmt.setString(2,pojo.getLocid());
			pstmt.setString(3,pojo.getClassId());
			pstmt.setString(4,pojo.getSectionid());
			pstmt.setString(5,pojo.getExamId());
			pstmt.setString(6,pojo.getTimetableid());
			
			count = pstmt.executeUpdate();
		
			for(int i=0;i<subid1.length;i++){
			pstmt1 = conn.prepareStatement("update  campus_detailed_timetable set startdate=?,starttime=?,endtime=? where examtimetablecode =? and sub_id=?");
			
			pstmt1.setString(5,subid1[i]);
			pstmt1.setString(1,HelperClass.convertUIToDatabase(startdate[i]));
			pstmt1.setString(2,starttime1[i]);
			pstmt1.setString(3,endtime1[i]);
			pstmt1.setString(4,pojo.getTimetableid());
		
			count1 = pstmt1.executeUpdate();
			}
			
			if(count1 > 0){
				result = "true";
			}
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (Exception e) {
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
		return result;
	}

	@Override
	public String updatetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String result = null;
		int count = 0;
		int count1= 0;
		
		IDGenerator id = new IDGenerator();
		String key = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			if(pojo.getSectionid().equalsIgnoreCase("%%")){
				pstmt2 = conn.prepareStatement("select classsection_id_int from campus_classsection where locationId = ? and classdetail_id_int = ? order by classsection_name_var");
				pstmt2.setString(1, pojo.getLocid());
				pstmt2.setString(2, pojo.getClassId());
				rs = pstmt2.executeQuery();
				while(rs.next()){
				
					pstmt=conn.prepareStatement("update campus_exam_timetable set accyear_id=?,loc_id=?,class_id=?,section_id=?,examcode=? where examtimetablecode=? ");
					
					pstmt.setString(1,pojo.getAccyearid());
					pstmt.setString(2,pojo.getLocid());
					pstmt.setString(3,pojo.getClassId());
					pstmt.setString(4,rs.getString("classsection_id_int"));
					pstmt.setString(5,pojo.getExamId());
					pstmt.setString(6,pojo.getTimetableid());
					System.out.println(pstmt);
					count = pstmt.executeUpdate();
					for(int i=0;i<subid1.length;i++){
						pstmt1 = conn.prepareStatement("update  campus_detailed_timetable set sub_id=?,startdate=?,starttime=?,endtime=? where examtimetablecode =?");
						
						pstmt1.setString(1,subid1[i]);
						pstmt1.setString(2,HelperClass.convertUIToDatabase(startdate[i]));
						pstmt1.setString(3,starttime1[i]);
						pstmt1.setString(4,endtime1[i]);
						pstmt1.setString(5,pojo.getTimetableid());
						count1 = pstmt1.executeUpdate();
						}
						
						if(count1 > 0){
							result = "true";
						}
				}
			}
			else{
			
			pstmt=conn.prepareStatement("update campus_exam_timetable set accyear_id=?,loc_id=?,class_id=?,section_id=?,examcode=? where examtimetablecode=?");
		
			pstmt.setString(1,pojo.getAccyearid());
			pstmt.setString(2,pojo.getLocid());
			pstmt.setString(3,pojo.getClassId());
			pstmt.setString(4,pojo.getSectionid());
			pstmt.setString(5,pojo.getExamId());
			pstmt.setString(6,pojo.getTimetableid());
			System.out.println(pstmt);
			count = pstmt.executeUpdate();
		
			for(int i=0;i<subid1.length;i++){
			pstmt1 = conn.prepareStatement("update  campus_detailed_timetable set sub_id=?,startdate=?,starttime=?,endtime=? where examtimetablecode =?");
			
			pstmt1.setString(1,subid1[i]);
			pstmt1.setString(2,HelperClass.convertUIToDatabase(startdate[i]));
			pstmt1.setString(3,starttime1[i]);
			pstmt1.setString(4,endtime1[i]);
			pstmt1.setString(5,pojo.getTimetableid());
			count1 = pstmt1.executeUpdate();
			}
			
			if(count1 > 0){
				result = "true";
			}
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (Exception e) {
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
		return result;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getexamsettingslistfordep(String accyear, String locid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmListYear Starting");
		ArrayList<ExaminationDetailsVo> examlistyear = new ArrayList<ExaminationDetailsVo>();

		PreparedStatement pstmt = null,pstmt1=null,pstmt2=null;
		Connection conn = null;
		ResultSet rs = null,rs1 = null,rs2 = null;
		int yearcount=0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMYEAR1);
			pstmt.setString(1,locid);
			pstmt.setString(2,accyear);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			int count=0;
			while (rs.next()) {
				ExaminationDetailsVo exampojo = new ExaminationDetailsVo();
				count++;
				exampojo.setSno1(count);
				exampojo.setAccyearid(rs.getString("acadamic_id"));
				exampojo.setAccyear(rs.getString("acadamic_year"));
				exampojo.setStartDate(rs.getString("startDate"));
				exampojo.setEndDate(rs.getString("endDate"));
				exampojo.setLocationid(rs.getString("Location_Id"));
				exampojo.setLocname(rs.getString("Location_Name"));
				pstmt1 = conn.prepareStatement("SELECT `examid` FROM `campus_examination` WHERE acadamicyear=? AND loc_id=?");
				pstmt1.setString(1, rs.getString("acadamic_id"));
				pstmt1.setString(2, rs.getString("Location_Id"));
				rs1 = pstmt1.executeQuery();
				while(rs1.next()) {
					pstmt2 = conn.prepareStatement("SELECT COUNT(`Exam_code`) FROM `campus_exam_dependency` WHERE `Exam_code`=?");
					pstmt2.setString(1, rs1.getString("examid"));
					rs2=pstmt2.executeQuery();
                   while(rs2.next()){
                	   yearcount=rs2.getInt(1);
                	   }
				}	
				exampojo.setAccyearcount(yearcount);
				if (yearcount > 0) {
					exampojo.setStatus("Set");
				} else {
					exampojo.setStatus("Not Set");
				}
				examlistyear.add(exampojo);
			}
			

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}if (rs1 != null && !rs1.isClosed()) {
					rs1.close();
				}if (rs2 != null && !rs2.isClosed()) {
					rs2.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
				}if (pstmt2 != null && !pstmt2.isClosed()) {
					pstmt2.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmListYear Ending");
		return examlistyear;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getSubjectClassBySpec(String accyear, String examid, String locid,String classid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0,totalstrength=0,noofsubjects=0;
		
		String msg = null;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("SELECT ccd.classdetail_id_int,ccd.classdetails_name_var,ccs.classsection_id_int,ccs.classsection_name_var FROM campus_classdetail ccd LEFT JOIN campus_classsection ccs ON ccs.`classdetail_id_int`=ccd.classdetail_id_int AND ccd.locationId=ccs.locationId WHERE ccd.locationId=? AND ccd.classdetail_id_int=? ORDER BY LENGTH(ccd.classdetail_id_int),ccd.classdetail_id_int,ccs.classsection_name_var");
			pstmt.setString(1, locid);
			pstmt.setString(2, classid);
		    rs = pstmt.executeQuery();
			
			while(rs.next()){
				ExaminationDetailsVo obj =new ExaminationDetailsVo();
				slno++;
				obj.setSno1(slno);
				obj.setClassId(rs.getString("classdetail_id_int"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSection(rs.getString("classsection_id_int"));
				obj.setSectionName(rs.getString("classsection_name_var"));
				
				PreparedStatement pstmtcount=conn.prepareStatement("select count(student_id_int) as studentcount from campus_student_classdetails where classdetail_id_int=? and classsection_id_int=? and locationId=? and fms_acadamicyear_id_int=?");
				pstmtcount.setString(1,obj.getClassId());
				pstmtcount.setString(2, obj.getSection());
				pstmtcount.setString(3,locid);
				pstmtcount.setString(4,accyear);
				ResultSet rs1 =pstmtcount.executeQuery(); 
				while(rs1.next())
				{
					obj.setTot_strength(rs1.getString("studentcount"));		
				}
				int checktotal = Integer.parseInt(obj.getTot_strength());
			    if(checktotal>0)
			    {
				PreparedStatement pstmtstatus = conn.prepareStatement("select count(cm.scored_marks) from campus_studentwise_mark_details cm where cm.classid=? and cm.sectionid=?");
				pstmtstatus.setString(1,rs.getString("classdetail_id_int"));
				pstmtstatus.setString(2,rs.getString("classsection_id_int"));
				ResultSet statusrs = pstmtstatus.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				PreparedStatement countstatus = conn.prepareStatement("select count(subjectName) as subject from campus_subject where classid=?");
				countstatus.setString(1,obj.getClassId());
				ResultSet counrs = countstatus.executeQuery();
				while(counrs.next())
				{
					noofsubjects=counrs.getInt(1);
					
				}
				countstatus.setString(1,obj.getClassId());
				String total=obj.getTot_strength();
				int foo = Integer.parseInt(total);
				if(noofsubjects!=0){
				if(count/noofsubjects==foo){
					obj.setStatus("Completed");
				}else{
					obj.setStatus("Pending");
				}
				}
				else
			    {
			    	obj.setStatus("Pending");
			    }
			    }
			    else
			    {
			    	obj.setStatus("Pending");
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
}
