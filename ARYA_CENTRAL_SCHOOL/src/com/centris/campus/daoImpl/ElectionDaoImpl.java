package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hsqldb.jdbc.jdbcBlob;
import org.json.JSONArray;

import com.centris.campus.dao.ElectionDao;
import com.centris.campus.pojo.ElectionPojo;
import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.pojo.ReligionCasteCasteCategoryPojo;
import com.centris.campus.util.ElectionSQLUtils;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReligionCasteCasteCategoryUtils;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.vo.ElectionVo;
import com.centris.campus.vo.LibrarySubscribVO;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.itextpdf.text.log.SysoLogger;

public class ElectionDaoImpl implements ElectionDao {
	private static final Logger logger = Logger.getLogger(IDGenerator.class);
	
	
	public List<PageFilterVo> getAccyear( String academicYear) {
	
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in studentIDDaoImpl: getstaffSearch : Starting");
			
			Connection conn = null;
			PreparedStatement psmt2=null;
			ResultSet rs=null;
			List<PageFilterVo> list=new ArrayList<PageFilterVo>();
			int sno=0;
			String academicYearId=academicYear;
			try{
				
					conn=JDBCConnection.getSeparateConnection();
					String accyear=null;
			/*inserted orderby in query*/
					PreparedStatement acd=conn.prepareStatement("SELECT * FROM campus_acadamicyear where acadamic_id like  ? ORDER BY `acadamic_year` ASC");
					acd.setString(1, academicYearId);
					ResultSet rsc=acd.executeQuery();
					while(rsc.next()){
						accyear=rsc.getString("acadamic_year");
						
						PageFilterVo filterVo = new PageFilterVo();
						
						filterVo.setAcademicYear(accyear);
						
						list.add(filterVo);
						list.size();
					}
			rsc.close();
			acd.close();
					System.out.println(list.size());

			}catch(Exception e){
				e.printStackTrace();
			}finally {
				try {
					if (rs != null && (!rs.isClosed())) {
						rs.close();
					}
					if (psmt2 != null && (!psmt2.isClosed())) {
						psmt2.close();
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
					+ " Control in studentIDDaoImpl: getstaffSearch : Ending");
			
			return list;
		}


	@Override
	public String saveGroupdetails(String accyear, String locid,String groupname) {
		Connection conn = null;
		PreparedStatement psmt1=null;
		PreparedStatement psmt2=null;
		PreparedStatement psmt3=null;
		String result = null;
		IDGenerator id = new IDGenerator();
		int count = 0 ;
		int count1=0;
		int count3 =0;
		ResultSet rsn=null;
		String key = null;
		String loc[] = locid.split(",");
	
		
		try{/*{cheking groupname exists or not*/
			conn=JDBCConnection.getSeparateConnection();
			psmt3=conn.prepareStatement("SELECT COUNT(*) FROM campus_election_group_settings WHERE groupname = ? AND accyear_id = ?");
			psmt3.setString(1, groupname);
			psmt3.setString(2, accyear);
			
			rsn=psmt3.executeQuery();
			while(rsn.next()){
				count3=rsn.getInt(1);
			}
			if(count3==0){
				key = id.getPrimaryKeyID("campus_election_group_settings");
			
				psmt1=conn.prepareStatement("INSERT INTO `campus_election_group_settings`(`election_group_id`,`accyear_id`,`groupname`,`createdby`) VALUES(?,?,?,?)");
				psmt1.setString(1,key);
				psmt1.setString(2,accyear);
				psmt1.setString(3,groupname);
				psmt1.setString(4,"");
				System.out.println(psmt1);
				count =psmt1.executeUpdate();
		
			for(int i=0;i<loc.length;i++){
			psmt2 = conn.prepareStatement("INSERT INTO `campus_election_school_setting`(`group_sett_school_id`,`election_group_id`,`Loc_Id`) VALUES(?,?,?)");
			psmt2.setString(1,id.getPrimaryKeyID("campus_election_school_setting"));
			psmt2.setString(2,key);
			psmt2.setString(3,loc[i]);
			System.out.println(psmt2);
			count1 = psmt2.executeUpdate();
			}
			if(count>0){
				result = "true";
			}else{
				result ="false";
			}
			}
			else{
				result = "exists"; // checking groupname exists or not
			}
		}catch(Exception e){
			e.printStackTrace();
		}try {
			if (rsn != null && (!rsn.isClosed())) {
				rsn.close();
			}
			if (psmt2 != null && (!psmt2.isClosed())) {
				psmt2.close();
			}
			if (psmt3 != null && (!psmt3.isClosed())) {
				psmt3.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}

	
	
@Override
	public List<ElectionVo> getGroupdetails(){
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<ElectionVo> list=new ArrayList<ElectionVo>() ;
		int slno = 0;
		try{
				conn=JDBCConnection.getSeparateConnection();
		
				pstmt=conn.prepareStatement("SELECT ce.`election_group_id`,ce.accyear_id,ca.acadamic_year,ce.groupname FROM campus_election_group_settings ce LEFT JOIN campus_acadamicyear ca ON ce.accyear_id=ca.acadamic_id ORDER BY ca.acadamic_year ASC");
				rs = pstmt.executeQuery();
				while(rs.next()){
					slno++;
					ElectionVo obj = new ElectionVo();
					obj.setSno(slno);
					obj.setGroupName(rs.getString("groupname"));
					obj.setAccyear(rs.getString("acadamic_year"));
					obj.setGroupid(rs.getString("election_group_id"));
					obj.setAccid(rs.getString("accyear_id"));
					list.add(obj);
				}
		}			
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
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
		return list;
	}



@Override
public List<ElectionVo> getAccGrpName(ElectionPojo detailspojo) {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	List<ElectionVo> list=new ArrayList<ElectionVo>() ;
	int slno = 0;
	try{
			conn=JDBCConnection.getSeparateConnection();
		
			pstmt = conn.prepareStatement("SELECT cs.group_sett_school_id,cl.Location_Name,ce.`election_group_id`,ce.accyear_id,ca.acadamic_year,ce.groupname,cs.Loc_Id FROM campus_election_group_settings ce LEFT JOIN campus_acadamicyear ca ON ce.accyear_id=ca.acadamic_id LEFT JOIN campus_election_school_setting cs ON ce.election_group_id=cs.`election_group_id` LEFT JOIN campus_location cl ON cs.Loc_Id =cl.Location_Id WHERE ce.election_group_id LIKE ? AND ce.accyear_id LIKE ? ORDER BY ca.acadamic_year ASC");
			pstmt.setString(1, detailspojo.getGroupId());
			pstmt.setString(2, detailspojo.getAccid());
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno++;
				ElectionVo obj = new ElectionVo();
				obj.setSno(slno);
				obj.setGroupName(rs.getString("groupname"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setGroupid(rs.getString("election_group_id"));
				obj.setAccid(rs.getString("accyear_id"));
				obj.setSchoolId(rs.getString("Loc_Id"));
				obj.setSchoolName(rs.getString("Location_Name"));
				obj.setGrp(rs.getString("group_sett_school_id"));
				list.add(obj);
			}
	}			
	catch(Exception e){
		e.printStackTrace();
	}
	finally {
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
	return list;
}



public List<ElectionVo> getGroupdetailsByJS(String accyear) {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	List<ElectionVo> list=new ArrayList<ElectionVo>() ;
	int slno = 0;
	try{
			conn=JDBCConnection.getSeparateConnection();
	
			pstmt=conn.prepareStatement("SELECT ce.`election_group_id`,ce.accyear_id,ca.acadamic_year,ce.groupname FROM campus_election_group_settings ce LEFT JOIN campus_acadamicyear ca ON ce.accyear_id=ca.acadamic_id where ce.accyear_id LIKE ? ORDER BY ca.acadamic_year ASC ");
			pstmt.setString(1, accyear);
			System.out.println("checking data*****************************");
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno++;
				ElectionVo obj = new ElectionVo();
				obj.setSno(slno);
				obj.setGroupName(rs.getString("groupname"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setGroupid(rs.getString("election_group_id"));
				obj.setAccid(rs.getString("accyear_id"));
				list.add(obj);
			}
	}			
	catch(Exception e){
		e.printStackTrace();
	}
	finally {
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
	return list;
}

public String updateGroupdetails( ElectionPojo pojo,String[] schoolHiddenval){
	Connection conn = null;
	PreparedStatement psmt1=null;
	PreparedStatement psmt2=null;
	PreparedStatement psmt3=null;
	String result = null;
	IDGenerator id = new IDGenerator();
	int count = 0 ;
	int count1=0;
	int count2=0;
	
	int count3 =0;
	ResultSet rsn=null;
	String key = null;

	String keyvalue="";
	
	try{
			conn=JDBCConnection.getSeparateConnection();
			if(pojo.getGroupName().equalsIgnoreCase(pojo.getHiddengroupname())){
				
				psmt1=conn.prepareStatement("UPDATE `campus_election_group_settings` SET `groupname` = ? WHERE `accyear_id` = ? AND `election_group_id` = ?");
				
				psmt1.setString(1,pojo.getGroupName());
				psmt1.setString(2,pojo.getAcchidden());
				psmt1.setString(3,pojo.getGrphidden());
				System.out.println(psmt1);
				count =psmt1.executeUpdate();
				System.out.println("update 1 is succcessfull&&&&&&&&&&&&&&&&&&&");
				
				
				psmt3=conn.prepareStatement("DELETE FROM campus_election_school_setting WHERE election_group_id=?");
				psmt3.setString(1, pojo.getGrphidden());
				count2=psmt3.executeUpdate();
				
				for(int i=0;i<schoolHiddenval.length;i++){
					
					psmt2 = conn.prepareStatement("INSERT INTO `campus_election_school_setting`(`group_sett_school_id`,`election_group_id`,`Loc_Id`) VALUES(?,?,?)");
					psmt2.setString(1,id.getPrimaryKeyID("campus_election_school_setting"));
					psmt2.setString(2,pojo.getGrphidden());
					psmt2.setString(3,schoolHiddenval[i]);
					System.out.println(psmt2);
					count1 = psmt2.executeUpdate();
			}
			if(count>0){
				result = "true";
			}else{
				result ="false";
				}
				
			}
			else{
			psmt3=conn.prepareStatement("SELECT COUNT(*) FROM campus_election_group_settings WHERE groupname = ? AND accyear_id = ?");
			//psmt3.setString(1,pojo.getGroupName());

			psmt3.setString(1,pojo.getGroupName());
			psmt3.setString(2,pojo.getAcchidden());
			rsn=psmt3.executeQuery();
			
			while(rsn.next()){
				count3=rsn.getInt(1);
			}
			if(count3==0){ //checking the repeated data while updating
			
			psmt1=conn.prepareStatement("UPDATE `campus_election_group_settings` SET `groupname` = ? WHERE `accyear_id` = ? AND `election_group_id` = ?");
		
			psmt1.setString(1,pojo.getGroupName());
			psmt1.setString(2,pojo.getAcchidden());
			psmt1.setString(3,pojo.getGrphidden());
			System.out.println(psmt1);
			count =psmt1.executeUpdate();
			System.out.println("update 1 is succcessfull&&&&&&&&&&&&&&&&&&&");
			
			psmt3=conn.prepareStatement("DELETE FROM campus_election_school_setting WHERE election_group_id=?");
			psmt3.setString(1, pojo.getGrphidden());
			count2=psmt3.executeUpdate();
			
			for(int i=0;i<schoolHiddenval.length;i++){
				
				psmt2 = conn.prepareStatement("INSERT INTO `campus_election_school_setting`(`group_sett_school_id`,`election_group_id`,`Loc_Id`) VALUES(?,?,?)");
				psmt2.setString(1,id.getPrimaryKeyID("campus_election_school_setting"));
				psmt2.setString(2,pojo.getGrphidden());
				psmt2.setString(3,schoolHiddenval[i]);
				System.out.println(psmt2);
				count1 = psmt2.executeUpdate();
		}
		if(count>0){
			result = "true";
		}else{
			result ="false";
			}
			}
			else{
			result = "exists"; // checking groupname exists or not
		}
			}try {
				if (rsn != null && (!rsn.isClosed())) {
					rsn.close();
				}
				if (psmt2 != null && (!psmt2.isClosed())) {
					psmt2.close();
				}
				if (psmt1 != null && (!psmt1.isClosed())) {
					psmt1.close();
				}
				if (psmt3 != null && (!psmt3.isClosed())) {
					psmt3.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return result;
}


public ArrayList<ElectionVo> getGroupNamebyAcademicYear(ElectionPojo pojo) {

		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getGroupName: getGroupNamebyAcademicYear : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		//ArrayList<ReportMenuVo> accYearList=new ArrayList<ReportMenuVo>();
		ArrayList<ElectionVo> GroupName = new ArrayList<ElectionVo>();
	 	
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = conn.prepareStatement(ElectionSQLUtils.GET_GROUPNAME_BY_ACCYEAR);
			pstmt.setString(1, pojo.getAccyear());
			System.out.println("checkibng 8888888888888888555555555555555555555555555555555555555 ==========="+pstmt);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ElectionVo grpName = new ElectionVo();
				grpName.setGroupName(rs.getString("groupname"));
				grpName.setGroupid(rs.getString("election_group_id"));
				
				GroupName.add(grpName);
				
			}
		}catch(Exception e){
				e.printStackTrace();
			}
			finally {
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
				+ " Control in ReportsMenuDaoImpl :getGroupNamebyAcademicYear : Ending");
		return GroupName;
	}


/*------group setting-------------------*/

public ArrayList<ElectionVo> getAccYearByGroup() {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl: getAccYearByGroup : Starting");
	
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	Connection conn = null;
	ArrayList<ElectionVo> accYearList=new ArrayList<ElectionVo>();
 	
	try {
		
		conn = JDBCConnection.getSeparateConnection();
		
		pstmt = conn.prepareStatement(ElectionSQLUtils.getAccYearByGroup);
		rs=pstmt.executeQuery();
		
		while(rs.next()){
		
			ElectionVo vo = new ElectionVo();
			vo.setAccid(rs.getString("accyear_id"));
			vo.setAccyear(rs.getString("acadamic_year").trim());
			accYearList.add(vo);
		}
	
	}catch(Exception e){
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
			+ " Control in ElectionDaoImpl : getAccYearByGroup : Ending");
	
	return accYearList;

}



public String saveElectionDetails(ElectionPojo pojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl: saveElectionDetails : Starting");
	
	Connection conn = null;
	PreparedStatement psmt = null;
	PreparedStatement psmt2 = null;
	PreparedStatement psmt3 = null;
	
	String key = null;
	String result = null;
	int count = 0;
	int count1 = 0;
	ResultSet rsn = null;
	IDGenerator id = new IDGenerator();

	try{
		conn=JDBCConnection.getSeparateConnection();

		psmt3=conn.prepareStatement("SELECT COUNT(*) FROM campus_election_election_setting WHERE electionTitle =? AND `groupID` = ?");
		psmt3.setString(1, pojo.getElectionTitle());
		psmt3.setString(2, pojo.getGroupName());
		
		System.out.println("checking condition one while insertiong******************"+psmt3);
		
		rsn=psmt3.executeQuery();
		while(rsn.next()){
			count1=rsn.getInt(1);
		}
		System.out.println("Counting the result++++++"+count1);
	if(count1==0){
		key = id.getPrimaryKeyID("campus_election_election_setting");
		psmt=conn.prepareStatement(ElectionSQLUtils.InsertElectionData);
		psmt.setString(1, key);
		psmt.setString(2, pojo.getAccyear());
		psmt.setString(3, pojo.getGroupName());
		psmt.setString(4, pojo.getElectionTitle());
		psmt.setString(5, HelperClass.convertUIToDatabase(pojo.getfromDate()));
		psmt.setString(6, HelperClass.convertUIToDatabase(pojo.gettoDate()));
		psmt.setString(7, pojo.getStartTime());
		psmt.setString(8, pojo.getEndTime());
		
		System.out.println(psmt);
		count=psmt.executeUpdate();

		if(count>0){
			result = "true";
		}else{
			result ="false";
		}
		}
			else{
				result = "exists"; // checking groupname exists or not
				}
	}
	
		catch(Exception e){
		e.printStackTrace();
	}try {
		if (rsn != null && (!rsn.isClosed())) {
			rsn.close();
		}
		if (psmt != null && (!psmt.isClosed())) {
			psmt.close();
		}
		if (psmt2 != null && (!psmt2.isClosed())) {
			psmt2.close();
		}
		if (psmt3 != null && (!psmt3.isClosed())) {
			psmt3.close();
		}
		
		if (conn != null && (!conn.isClosed())) {
			conn.close();
		}

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : getAccYearByGroup : Ending");
	return result;
}


public ArrayList<ElectionVo> getElectionDetails(String academicYearID, String groupNameID) {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	ResultSet rs1 = null;
	PreparedStatement pstmt1 = null;
	ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
	int slno = 0;
	int count = 0;
	try{
			conn=JDBCConnection.getSeparateConnection();
	
			pstmt=conn.prepareStatement(ElectionSQLUtils.getElectionDetails);
			pstmt.setString(1, academicYearID);
			pstmt.setString(2, groupNameID);
			System.out.println(")))))))))))))))))))))))))))))))))))))))))))))))))))))))"+pstmt);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				slno++;
				ElectionVo obj = new ElectionVo();
				obj.setElectionTitle(rs.getString("electionTitle"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setGroupName(rs.getString("groupname"));
				obj.setSno(slno);
				obj.setAccid(rs.getString("accyearID"));
				obj.setGroupid(rs.getString("groupID"));
				obj.setElectionTitleId(rs.getString("electionSettingID"));
				
				
				
				//setting status column in listing page
				pstmt1=conn.prepareStatement("SELECT COUNT(*) FROM `campus_election_category_setting` WHERE `electionSettingID` =?");
				pstmt1.setString(1, rs.getString("electionSettingID"));
				System.out.println("8888888888888888888888888888"+pstmt1);
				rs1=pstmt1.executeQuery();
				while(rs1.next()){
					count=rs1.getInt(1);
				}
				if(count>0){
					obj.setStatus("Set");
				}
				else{
					obj.setStatus("Not set");
				}
				list.add(obj);
			}
	}			
	catch(Exception e){
		e.printStackTrace();
	}
	finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (rs1 != null && (!rs1.isClosed())) {
				rs1.close();
			}
			
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (pstmt1 != null && (!pstmt1.isClosed())) {
				pstmt1.close();
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

//for carry data from listing page to update page
public List<ElectionVo> getElectionDetailsFUpdate(ElectionPojo pojo) {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	List<ElectionVo> list=new ArrayList<ElectionVo>() ;
	int slno = 0;
	try{
			conn=JDBCConnection.getSeparateConnection();
	
			pstmt = conn.prepareStatement(ElectionSQLUtils.getElectionDetailsFUpdatePage);
			pstmt.setString(1, pojo.getAccid());
			pstmt.setString(2, pojo.getGroupId());
			pstmt.setString(3, pojo.getElectionTitleId());
			System.out.println("getting election details for listing page:+++++++++++++"+pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno++;
				ElectionVo obj = new ElectionVo();
				obj.setSno(slno);
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setAccid(rs.getString("accyearID"));
				
				obj.setGroupName(rs.getString("groupname"));
				obj.setGroupid(rs.getString("groupID"));
				
				obj.setElectionTitle(rs.getString("electionTitle"));
				obj.setElectionTitleId(rs.getString("electionSettingID"));
				
				obj.setFromDate(HelperClass.convertDatabaseToUI(rs.getString("startDate")));
				obj.setToDate(HelperClass.convertDatabaseToUI(rs.getString("endDate")));
				obj.setStartTime(rs.getString("startTime"));
				obj.setEndTime(rs.getString("endTime"));
			
				list.add(obj);
			}
	}			
	catch(Exception e){
		e.printStackTrace();
	}
	finally {
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
	return list;
}


public String UpdateElectionDetails(ElectionPojo pojo) {
	Connection conn = null;
	PreparedStatement psmt0=null;
	PreparedStatement psmt2=null;
	PreparedStatement psmt3=null;
	String result = null;
	IDGenerator id = new IDGenerator();
	ResultSet rs0=null;
	int count0=0;
	int count1=0;
	int count3 =0;
	ResultSet rsn=null;
	try{
				conn=JDBCConnection.getSeparateConnection();
				psmt0=conn.prepareStatement("SELECT COUNT(*) FROM campus_election_election_setting WHERE CONCAT(startDate,' ',startTime)<=NOW() AND accyearID=? AND groupID=? AND electionSettingID=?");
				psmt0.setString(1, pojo.getAcchidden());
				psmt0.setString(2, pojo.getGrphidden());
				psmt0.setString(3, pojo.getElectionTitleh());
				rs0=psmt0.executeQuery();
				while(rs0.next()){
					count0=rs0.getInt(1);
				}
				if(count0==0)
				{
				
				if(pojo.getElectionTitle().equalsIgnoreCase(pojo.getElectionTitleHName())){
					
					psmt2 = conn.prepareStatement(ElectionSQLUtils.InsertUpdatedRecord);
					
					psmt2.setString(1,pojo.getElectionTitle());
					psmt2.setString(2,HelperClass.convertUIToDatabase(pojo.getfromDate()));
					psmt2.setString(3,HelperClass.convertUIToDatabase(pojo.gettoDate()));
					psmt2.setString(4,pojo.getStartTime());
					psmt2.setString(5,pojo.getEndTime());
					psmt2.setString(6,pojo.getElectionTitleh());
					psmt2.setString(7,pojo.getAcchidden());
					psmt2.setString(8,pojo.getGrphidden());
					System.out.println("update the final statement++++++++++"+psmt2);
					count1 = psmt2.executeUpdate();
					
					if(count1>0){
						result = "true";
					}else{
						result ="false";
						}
				
				}
				else{

				psmt3=conn.prepareStatement("SELECT COUNT(*) FROM `campus_election_election_setting` WHERE `groupID`=? AND `electionTitle`=?");

				psmt3.setString(1,pojo.getGrphidden());
				psmt3.setString(2,pojo.getElectionTitle());
				rsn=psmt3.executeQuery();
				System.out.println("Checking the count of election title"+psmt3);
				
				while(rsn.next()){
					count3=rsn.getInt(1);
				}
				System.out.println("count is=="+count3);
				
				if(count3==0){ //checking the repeated data while updating
		
					
				psmt2 = conn.prepareStatement(ElectionSQLUtils.InsertUpdatedRecord);
				
				psmt2.setString(1,pojo.getElectionTitle());
				psmt2.setString(2,HelperClass.convertUIToDatabase(pojo.getfromDate()));
				psmt2.setString(3,HelperClass.convertUIToDatabase(pojo.gettoDate()));
				psmt2.setString(4,pojo.getStartTime());
				psmt2.setString(5,pojo.getEndTime());
				psmt2.setString(6,pojo.getElectionTitleh());
				psmt2.setString(7,pojo.getAcchidden());
				psmt2.setString(8,pojo.getGrphidden());
				
				System.out.println("update the final statement++++++++++"+psmt2);
				count1 = psmt2.executeUpdate();
				
				
		
		
			if(count1>0){
				result = "true";
			}else{
				result ="false";
				}
			
			//if repeated election title
				}else{
						result = "exists"; // checking groupname exists or not
					}
				}
				
				
				}
				else{
					result="started";
				}
					try {
					if (rsn != null && (!rsn.isClosed())) {
						rsn.close();
					}
					if (rs0 != null && (!rs0.isClosed())) {
						rs0.close();
					}
					if (psmt0 != null && (!psmt0.isClosed())) {
						psmt0.close();
					}
					if (psmt2 != null && (!psmt2.isClosed())) {
						psmt2.close();
					}
					if (psmt3 != null && (!psmt3.isClosed())) {
						psmt3.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}

				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
				
	}catch(Exception e){
		e.printStackTrace();
	}
	return result;

}


public String checkDateOverLap(String date, ElectionPojo pojo) {
	Connection conn = null;
	PreparedStatement psmt3=null;
	String result = null;
	ResultSet rs =null;
	int count1=0;
	try{
			conn=JDBCConnection.getSeparateConnection();
			psmt3=conn.prepareStatement("SELECT COUNT(*) FROM `campus_election_election_setting` WHERE `startDate`<=? AND " +
					"`endDate`>=? AND groupID=?");
			psmt3.setString(1,HelperClass.convertUIToDatabase(date));
			psmt3.setString(2,HelperClass.convertUIToDatabase(date));
			psmt3.setString(3, pojo.getGrphidden());
			
			
			rs = psmt3.executeQuery();
			System.out.println("Checking the count of election title"+psmt3);
			while(rs.next()){
				count1=rs.getInt(1);
				System.out.println("counting the matches************************************"+count1);
			}	
			if(count1>0){
				result = "true";
			}else{
				result ="false";
				}try {
					if (rs != null && (!rs.isClosed())) {
						rs.close();
					}
					if (psmt3 != null && (!psmt3.isClosed())) {
						psmt3.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}

				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
	}catch(Exception e){
		e.printStackTrace();
	}
	return result;
}

/*--------------------election Category setting------------------*/	
public ArrayList<ElectionVo> getElectionCategoryList(String academicYearID,String groupNameID) {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	ResultSet rs1 = null;
	PreparedStatement pstmt1 = null;
	ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
	int slno = 0;
	int count =0;
	try{
			conn=JDBCConnection.getSeparateConnection();
	
			pstmt=conn.prepareStatement(ElectionSQLUtils.getElectionDetails);
			pstmt.setString(1, academicYearID);
			pstmt.setString(2, groupNameID);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				slno++;
				ElectionVo obj = new ElectionVo();
				obj.setElectionTitle(rs.getString("electionTitle"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setGroupName(rs.getString("groupname"));
				obj.setSno(slno);
				obj.setAccid(rs.getString("accyearID"));
				obj.setGroupid(rs.getString("groupID"));
				obj.setElectionTitleId(rs.getString("electionSettingID"));
				
				
				list.add(obj);
				
				
				
			}
	}			
	catch(Exception e){
		e.printStackTrace();
	}
	finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (rs1 != null && (!rs1.isClosed())) {
				rs1.close();
			}
			
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (pstmt1 != null && (!pstmt1.isClosed())) {
				pstmt1.close();
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



/*Category setting--model-3*/
public String electionCategoryAddPopUp(ElectionPojo pojo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl: electionCategoryAddPopUp : Starting");
	
	Connection conn = null;
	PreparedStatement psmt = null;
	PreparedStatement psmt0 = null;
	PreparedStatement psmt3 = null;
	PreparedStatement psmt4 = null;
	ResultSet rs0=null;
	String key = null;
	String result = null;
	int count0 = 0;
	int count = 0;
	int count1 = 0;
	int count2 = 0;
	ResultSet rsn = null;
	IDGenerator id = new IDGenerator();
	

	try{
		
	//	ElectionVo result = new ElectionVo();
		
		conn=JDBCConnection.getSeparateConnection();
		psmt0=conn.prepareStatement("SELECT COUNT(*) FROM campus_election_election_setting WHERE CONCAT(startDate,' ',startTime)<=NOW() AND accyearID=? AND groupID=? AND electionSettingID=?");
		psmt0.setString(1, pojo.getAcchidden());
		psmt0.setString(2, pojo.getGrphidden());
		psmt0.setString(3, pojo.getElectionTitleh());
		
		rs0=psmt0.executeQuery();
		while(rs0.next()){
			count0=rs0.getInt(1);
		}
		
		if(count0 == 0){
		// For inserting if condition, For updating else condition
			if(pojo.getElectionCategoryId().equalsIgnoreCase("") || pojo.getElectionCategoryId()==null){
				
	
				
				psmt4=conn.prepareStatement(" SELECT COUNT(`priority`) FROM campus_election_category_setting WHERE priority=? and electionSettingID=? AND accyearID=? AND groupID=?");
				psmt4.setString(1, pojo.getPriority());
				psmt4.setString(2, pojo.getElectionTitleh());
				psmt4.setString(3, pojo.getAcchidden());
				psmt4.setString(4, pojo.getGrphidden());
				System.out.println("checking condition one while PRIOIRITYYYYYYYYYYYYYY******************"+psmt4);
				rsn=psmt4.executeQuery();
				while(rsn.next()){
					count2=rsn.getInt(1);
				}
		System.out.println("Counting the PRIORITY COUNT+++++++"+count2);
		psmt4 =conn.prepareStatement("");
		
		if(count2==0){
		
		psmt3=conn.prepareStatement(ElectionSQLUtils.chkDuplicateCategoryName);
		psmt3.setString(1, pojo.getCategoryName());
		psmt3.setString(2, pojo.getElectionTitleh());
		psmt3.setString(3, pojo.getAcchidden());
		psmt3.setString(4, pojo.getGrphidden());
		System.out.println("checking condition one while insertiong******************"+psmt3);
		
		rsn=psmt3.executeQuery();
		while(rsn.next()){
			count1=rsn.getInt(1);
		}
		System.out.println("Counting the result+++++++"+count1);
		
		if(count1==0){
		key = id.getPrimaryKeyID("campus_election_category_setting");
		psmt=conn.prepareStatement(ElectionSQLUtils.savePopUpData);
		psmt.setString(1, key);
		psmt.setString(2, pojo.getCategoryName());
		psmt.setString(3, pojo.getPriority());
		psmt.setString(4, pojo.getParticipateClass());
		psmt.setString(5, pojo.getGenderWise());
		psmt.setString(6, pojo.getHouseWise());
		psmt.setString(7, pojo.getClassWiseName());
		psmt.setString(8, pojo.getNominFor());
		psmt.setString(9, pojo.getNominLevel());
		
		psmt.setString(10, pojo.getElectionTitleh());
		psmt.setString(11, pojo.getAcchidden());
		psmt.setString(12, pojo.getGrphidden());
		psmt.setString(13, pojo.getHouseId());
		
	
		System.out.println(psmt);
		count=psmt.executeUpdate();

		if(count>0){
			result = "true";
		}else{
			result ="false";
		}
	}
		
		else{
			result = "exists"; // checking groupname exists or not
			}
	}
		else{
			result = "dupPri"; // checking groupname exists or not
			}
			}
			
			
			else{
				psmt=conn.prepareStatement(ElectionSQLUtils.updatePopUpData);
				psmt.setString(1, pojo.getCategoryName());
				psmt.setString(2, pojo.getPriority());
				psmt.setString(3, pojo.getParticipateClass());
				psmt.setString(4, pojo.getGenderWise());
				psmt.setString(5, pojo.getHouseWise());
				psmt.setString(6, pojo.getClassWiseName());
				psmt.setString(7, pojo.getNominFor());
				psmt.setString(8, pojo.getNominLevel());
				psmt.setString(9, pojo.getElectionTitleh());
				psmt.setString(10, pojo.getAcchidden());
				psmt.setString(11, pojo.getGrphidden());
				psmt.setString(12, pojo.getHouseId());
				psmt.setString(13, pojo.getElectionCategoryId());
			
				System.out.println(psmt);
				count=psmt.executeUpdate();

				if(count>0){
					result = "updatetrue";
				}else{
					result ="false";
				}
			
	}
			
		}
		else{
			result ="started";
		}
			
			
			
			
			
			
	}
		catch(Exception e){
		e.printStackTrace();
	}
	try {
		if (rsn != null && (!rsn.isClosed())) {
			rsn.close();
		}
		if (rs0 != null && (!rs0.isClosed())) {
			rs0.close();
		}
		if (psmt3!= null && (!psmt3.isClosed())) {
			psmt3.close();
		}
		if (psmt4!= null && (!psmt4.isClosed())) {
			psmt4.close();
		}
		if (psmt0!= null && (!psmt0.isClosed())) {
			psmt0.close();
		}
		if (psmt!= null && (!psmt.isClosed())) {
			psmt.close();
		}
		if (conn != null && (!conn.isClosed())) {
			conn.close();
		}

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : electionCategoryAddPopUp : Ending");
	
	return result;

	
}


public ArrayList<ElectionVo> getElectionCategoryList(String accyear, String groupnameHidden,String titleHidden) {
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	ResultSet rs1 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs2 = null;
	ArrayList<ElectionVo> list=new ArrayList<ElectionVo>();
	int slno = 0;
	
	try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ElectionSQLUtils.getElectionCategoryList);
			
			pstmt.setString(1, accyear);
			pstmt.setString(2, groupnameHidden);
			pstmt.setString(3, titleHidden);
			
			System.out.println("execute quserty*********"+pstmt);
			
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				String className=null;
				String classNameList[]=new String[] {};
				List<String> classNameListVal=Arrays.asList(classNameList);
				List<String> tempList = new ArrayList<String>(classNameListVal);
				
				
				String nomanationLevelList[]=new String[] {};
				List<String> nomanationLevelListVal=Arrays.asList(nomanationLevelList);
				List<String> nomanationtempList = new ArrayList<String>(nomanationLevelListVal);
				
				
				slno++;
				ElectionVo obj = new ElectionVo();
				obj.setSno(slno);
				obj.setCategoryName(rs.getString("electionCategory"));
				obj.setElectionCategoryId(rs.getString("electionCategoryId"));
				obj.setPriority(rs.getString("priority"));
				
				String[] classId=(rs.getString("classId")).split(",");
				String[] nomineeLevel=(rs.getString("nominationLevel")).split(",");
				
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
				
				obj.setParticipateClass(classNameList);
				
				obj.setGenderWise(rs.getString("genderWise"));
				obj.setHouseWise(rs.getString("houseWise"));
				obj.setClassWiseName(rs.getString("classWise"));
				obj.setNominFor(rs.getString("nominationFor"));
			
				for(int k=0;k<nomineeLevel.length;k++){
				pstmt2=conn.prepareStatement("SELECT DISTINCT classdetails_name_var from campus_classdetail where classdetail_id_int=?");
				pstmt2.setString(1, nomineeLevel[k]);
				rs2=pstmt2.executeQuery();
				while(rs2.next()){
					String classNameL=rs2.getString("classdetails_name_var");
					nomanationtempList.add(classNameL);
				}
				}
				
				String[] nomaSize=new String[nomanationtempList.size()];
				nomanationLevelList=nomanationtempList.toArray(nomaSize);
				obj.setNominLevel(nomanationLevelList);
				
				
				list.add(obj);

		}
	}			
	catch(Exception e){
		e.printStackTrace();
	}try {
		if (rs != null && (!rs.isClosed())) {
			rs.close();
		}
		if (rs2 != null && (!rs2.isClosed())) {
			rs2.close();
		}
		if (rs1 != null && (!rs1.isClosed())) {
			rs1.close();
		}
		if (pstmt!= null && (!pstmt.isClosed())) {
			pstmt.close();
		}
		if (pstmt1!= null && (!pstmt1.isClosed())) {
			pstmt1.close();
		}
		if (pstmt2!= null && (!pstmt2.isClosed())) {
			pstmt2.close();
		}
		if (conn != null && (!conn.isClosed())) {
			conn.close();
		}

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
return list;
}


public String DeleteElectionCategoryList(String id) {
	Connection conn = null;
	int rs = 0;
	PreparedStatement pstmt = null;
	String status =null;
	
	ArrayList<ElectionVo> list=new ArrayList<ElectionVo>();
	int slno = 0;
	
	try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ElectionSQLUtils.DeleteElectionCategoryList);
			
			pstmt.setString(1, id);
			System.out.println("execute quserty*********"+pstmt);
			
			rs = pstmt.executeUpdate();
			 if(rs>0){
				 status="true";
			 }else{
				 
				 status="false";
			 }
			
	}			
	catch(Exception e){
		e.printStackTrace();
	}finally {
	try{
		
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}

	}catch(Exception e){
		e.printStackTrace();
	}
	}
return status;
}


/*------------------Nomination Registration :::: Module - 4---------------------------------------*/

public ArrayList<ElectionVo> getElectionTitleByGroupName(ElectionPojo pojo) {
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	Connection conn = null;
	ArrayList<ElectionVo> electionTitle = new ArrayList<ElectionVo>();
 	
	try {
		
		conn = JDBCConnection.getSeparateConnection();
		
		pstmt = conn.prepareStatement(ElectionSQLUtils.getElectionTitleByGroupName);
		pstmt.setString(1, pojo.getAccyear());
		pstmt.setString(2, pojo.getGroupName());
		System.out.println("checkibng 88888888888888885555555555555555getElectionTitleByGroupName00000000000000000000000000000000000000 ==========="+pstmt);
		rs=pstmt.executeQuery();
		
		while(rs.next()){
			ElectionVo title = new ElectionVo();
			title.setElectionTitle(rs.getString("electionTitle"));
			title.setElectionTitleId(rs.getString("electionSettingID"));
			
			electionTitle.add(title);
			
		}
	}catch(Exception e){
			e.printStackTrace();
		}
		finally {
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
			+ " Control in ReportsMenuDaoImpl : getAccYears : Ending");
	return electionTitle;
}


public ArrayList<ElectionVo> getNominationRegistrationList(String academicYear,String groupName, String electionTitle) {
	PreparedStatement psmt = null;
	ResultSet rs = null;
	Connection conn = null;
	int sno=0;
	ArrayList<ElectionVo> list =  new ArrayList<ElectionVo>();
	try{
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement("SELECT * FROM `campus_election_category_setting` WHERE `electionSettingID` like ? AND `accyearID` like ? AND `groupID` like ? ORDER BY priority");
		psmt.setString(1, electionTitle);
		psmt.setString(2, academicYear);
		psmt.setString(3, groupName);
		
		System.out.println("get the nomintation registration data ====thissssssssssssssssss============"+psmt);
		rs = psmt.executeQuery();
		while(rs.next()){
			sno++;
			ElectionVo vo = new ElectionVo();
			vo.setSno(sno);
			vo.setElectionCategory(rs.getString("electionCategory"));
			vo.setElectionCategoryId(rs.getString("electionCategoryId"));
			vo.setGroupid(rs.getString("groupID"));
			vo.setElectionTitleId(rs.getString("electionSettingID"));
			list.add(vo);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}try {
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
	return list;
}


public ArrayList<ElectionVo> getElectionCategoryByTitle(ElectionPojo pojo) {
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	Connection conn = null;
	ArrayList<ElectionVo> electionTitle = new ArrayList<ElectionVo>();
 	
	try {
		
		conn = JDBCConnection.getSeparateConnection();
		
		pstmt = conn.prepareStatement(ElectionSQLUtils.getElectionTitleByGroupName);
		pstmt.setString(1, pojo.getAccyear());
		pstmt.setString(2, pojo.getGroupName());
		System.out.println("checkibng 8888888888888888555555555555555555555555555555555555555 ==========="+pstmt);
		rs=pstmt.executeQuery();
		
		while(rs.next()){
			ElectionVo title = new ElectionVo();
			title.setElectionTitle(rs.getString("electionTitle"));
			title.setElectionTitleId(rs.getString("electionSettingID"));
			
			electionTitle.add(title);
			
		}
	}catch(Exception e){
			e.printStackTrace();
		}
		finally {
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
			+ " Control in ReportsMenuDaoImpl : getElectionCategoryByTitle : Ending");
	return electionTitle;

}


public ArrayList<ElectionVo> getSingleNomineeDetails(ElectionPojo pojo) {
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	PreparedStatement pstmt1 = null;
	ResultSet rs1=null;
	Connection conn = null;
	ArrayList<ElectionVo> electionTitle = new ArrayList<ElectionVo>();
 	
	try {
		
		conn = JDBCConnection.getSeparateConnection();
		
		pstmt = conn.prepareStatement("SELECT CONCAT(cs.student_fname_var, ' ',student_lname_var) AS studentName,cc.classdetails_name_var,cc.classdetail_id_int,ccs.classsection_name_var,ccs.classsection_id_int,csc.student_imgurl_var,csc.`student_id_int`,csc.`student_rollno`,chs.housename,csc.student_house FROM `campus_student` cs LEFT JOIN `campus_student_classdetails` csc ON cs.student_id_int = csc.student_id_int  LEFT JOIN campus_classdetail cc ON (csc.`classdetail_id_int`=cc.classdetail_id_int AND csc.locationId=cc.locationId) LEFT JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings chs ON chs.house_id=csc.student_house WHERE cs.student_admissionno_var=? AND csc.fms_acadamicyear_id_int=?");
		
		pstmt.setString(1, pojo.getAdmissionNo());
		pstmt.setString(2, pojo.getAccyear());
		System.out.println("checkibng 8888888888888888555555555555555666666666666666666^^^^^^555555555555555555555555 ==========="+pstmt);
		rs=pstmt.executeQuery();
		
		while(rs.next()){
			ElectionVo title = new ElectionVo();
			title.setStudentName(rs.getString("studentName"));
			title.setStudentId(rs.getString("student_id_int"));
			title.setClassName(rs.getString("classdetails_name_var"));
			title.setClassId(rs.getString("classdetail_id_int"));
			title.setSectionName(rs.getString("classsection_name_var"));
			title.setSectionId(rs.getString("classsection_id_int"));
			title.setImgUrl(rs.getString("student_imgurl_var"));
			title.setHouseName(rs.getString("housename"));
			title.setHouseId(rs.getString("student_house"));			
			
			electionTitle.add(title);
			
		}
	}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
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
			+ " Control in ReportsMenuDaoImpl : getElectionCategoryByTitle : Ending");
	return electionTitle;
}


public String saveNewNomineeDetails(ElectionPojo pojo) {
	Connection conn = null;
	PreparedStatement psmt0=null;
	PreparedStatement psmt1=null;
	PreparedStatement psmt3=null;
	ResultSet rs0=null;
	String result = null;
	ResultSet rsn =null;
	int count0 = 0 ;
	int count = 0 ;
	int count3 = 0;

	try{
		//cheking duplicate admissionNumber `electionCategoryId`=? AND
		conn=JDBCConnection.getSeparateConnection();
		psmt0=conn.prepareStatement("SELECT COUNT(*) FROM campus_election_election_setting WHERE CONCAT(startDate,' ',startTime)<=NOW() AND accyearID=? AND groupID=? AND electionSettingID=?");
		psmt0.setString(1, pojo.getAccid());
		psmt0.setString(2, pojo.getGroupId());
		psmt0.setString(3, pojo.getElectionTitleId());
		
		rs0=psmt0.executeQuery();
		while(rs0.next()){
			count0=rs0.getInt(1);
		}
		
		if(count0==0){
		
		psmt3=conn.prepareStatement("SELECT COUNT(*) FROM `campus_election_nomiation_registration` WHERE  `electionSettingID`=? AND `accyearID`=? AND `groupID`=? AND `admissionNo`=? ");
		//psmt3.setString(1, pojo.getElectionCategoryId());
		psmt3.setString(1,pojo.getElectionTitleId());
		psmt3.setString(2,pojo.getAccid());
		psmt3.setString(3,pojo.getGroupId());
		psmt3.setString(4, pojo.getAdmissionNo());
		System.out.println("save Regostration details&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&coutttttttttttttttttttttt"+psmt3);
		rsn=psmt3.executeQuery();
		while(rsn.next()){
			count3=rsn.getInt(1);
		}
		if(count3==0){
/*			//key = id.getPrimaryKeyID("campus_election_group_settings");
		
			
			try{
			*/
			conn=JDBCConnection.getSeparateConnection();
			psmt1=conn.prepareStatement("INSERT INTO `campus_election_nomiation_registration`(`electionCategoryId`,`admissionNo`,`studentId`,`classdetail_id_int`,`classsection_id_int`,`electionSettingID`,`accyearID`,`groupID`) values(?,?,?,?,?,?,?,?)");
			psmt1.setString(1,pojo.getElectionCategoryId());
			psmt1.setString(2,pojo.getAdmissionNo());
			psmt1.setString(3,pojo.getStudentName());
			psmt1.setString(4,pojo.getClassWiseName());
			psmt1.setString(5,pojo.getSectionWiseName());
			psmt1.setString(6,pojo.getElectionTitleId());
			psmt1.setString(7,pojo.getAccid());
			psmt1.setString(8,pojo.getGroupId());
			
			System.out.println("save Regostration details&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+psmt1);
			count =psmt1.executeUpdate();
			
			if(count>0){
						result = "true";
					}else{
						result ="false";
			}
			}//IF
	   
	        else{
				result ="exist";
				}
		
		}
		else{
			result="started";
		}
		
		
		
		}catch(Exception e){
		e.printStackTrace();
	}try {
		if (rsn != null && (!rsn.isClosed())) {
			rsn.close();
		}
		if (rs0 != null && (!rs0.isClosed())) {
			rs0.close();
		}
		if (psmt1 != null && (!psmt1.isClosed())) {
			psmt1.close();
		}
		if (psmt3 != null && (!psmt3.isClosed())) {
			psmt3.close();
		}
		if (psmt0 != null && (!psmt0.isClosed())) {
			psmt0.close();
		}
		if (conn != null && (!conn.isClosed())) {
			conn.close();
		}

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	return result;
}

/*--------------------Nomination approval module-5------------------*/

public ArrayList<ElectionVo> getNominationApprovalList(String academicYear,String groupName, String electionTitle, String electionCategory) {
	PreparedStatement psmt = null;
	PreparedStatement psmt1 = null;
	ResultSet rs,rs1 = null;
	Connection conn = null;
	int sno = 0,count=0;
	ArrayList<ElectionVo> list =  new ArrayList<ElectionVo>();
	try{
		conn = JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement(ElectionSQLUtils.getNominationApprovalList);
		psmt.setString(1, academicYear);
		psmt.setString(2, groupName);
		psmt.setString(3, electionCategory);
		psmt.setString(4, electionTitle);
		
		
		System.out.println("get the nomintation registration data ====thissssssssssssssssss=Nomination Approvallll==========="+psmt);
		rs = psmt.executeQuery();
		while(rs.next()){
			sno++;
			ElectionVo vo = new ElectionVo();
			vo.setSno(sno);

			vo.setStudentName(rs.getString("studentName"));
			vo.setAdmissionNo(rs.getString("admissionNo"));
			vo.setClassName(rs.getString("classdetails_name_var"));
			vo.setSectionName(rs.getString("classsection_name_var"));
			vo.setStatus(rs.getString("isApproved"));

			list.add(vo);
	
		
		}
		rs.close();
	}catch(Exception e){
		e.printStackTrace();
	}try {
		
	
		if (psmt != null && (!psmt.isClosed())) {
			psmt.close();
		}
		if (psmt1 != null && (!psmt1.isClosed())) {
			psmt1.close();
		}
		if (conn != null && (!conn.isClosed())) {
			conn.close();
		}

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	return list;

}


public String saveApproval(ElectionPojo pojo) {
	String result = null;

	int count = 0;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	Connection conn = null;

	try {
		for(int i=0;i<pojo.getAdmissionNoArray().length;i++){
			conn = JDBCConnection.getSeparateConnection();
			psmt = conn.prepareStatement(" UPDATE `campus_election_nomiation_registration` SET `isApproved`=?  WHERE `admissionNo`=?");
			psmt.setString(1, "APPROVED");
			psmt.setString(2, pojo.getAdmissionNoArray()[i].trim());
			count = psmt.executeUpdate();
			System.out.println("query check for approval*************666666666666666666666*****************: "+psmt);
			if (count > 0) {

				result = "true";
			} else {
				result = "false";
			}
		}

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}try {
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
	return result;
}


public ArrayList<ElectionVo> getUpdateElectionCategory(String categoryId) {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;

	ArrayList<ElectionVo> list=new ArrayList<ElectionVo>();
	int slno = 0;
	
	try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement("SELECT `electionCategory`,`priority`,`classId`,`genderWise`,`houseWise`,houseId,`classWise`,`nominationFor`,`nominationLevel` FROM `campus_election_category_setting` WHERE `electionCategoryId`=?");
			
			pstmt.setString(1, categoryId);

			
			System.out.println("execute quserty===========================================*********"+pstmt);
			
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				slno++;
				ElectionVo obj = new ElectionVo();
				obj.setSno(slno);
				obj.setCategoryName(rs.getString("electionCategory"));
			
				obj.setPriority(rs.getString("priority"));
				
				String[] classId=(rs.getString("classId")).split(",");
				obj.setParticipateClass(classId);
				
				obj.setGenderWise(rs.getString("genderWise"));
				obj.setHouseWise(rs.getString("houseWise"));
				obj.setHouseId(rs.getString("houseId"));
				obj.setClassWiseName(rs.getString("classWise"));
				obj.setNominFor(rs.getString("nominationFor"));
				
				String[] nominLevel=rs.getString("nominationLevel").split(",");
				obj.setNominLevel(nominLevel);
				
				
				list.add(obj);
			}
	}			
	catch(Exception e){
		e.printStackTrace();
	}try {
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
	
return list;	
}


public String rejectApproval(ElectionPojo pojo) {
	String result = null;

	int count = 0;
	PreparedStatement psmt = null;
	Connection conn = null;

	try {
		for(int i=0;i<pojo.getAdmissionNoArray().length;i++){
			conn = JDBCConnection.getSeparateConnection();
			psmt = conn.prepareStatement(" UPDATE `campus_election_nomiation_registration` SET `isApproved`=?  WHERE `admissionNo`=?");
			psmt.setString(1, "REJECTED");
			psmt.setString(2, pojo.getAdmissionNoArray()[i].trim());
			count = psmt.executeUpdate();
			System.out.println("query check for approval*************666666666666666666666*****************: "+psmt);
			if (count > 0) {

				result = "true";
			} else {
				result = "false";
			}
		}

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}try {
	
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
	return result;
}

/*--------------------Booth setting  module-6------------------*/


public ArrayList<ElectionVo> getStaffNameList(){
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement psmt = null;
	ArrayList<ElectionVo> list=new ArrayList<ElectionVo>();
	try{
			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement("SELECT `TeacherID`,CONCAT(`FirstName`,'',`LastName`)AS TeacherName FROM `campus_teachers` WHERE `isActive`='y'");
	
			System.out.println("queryyyyy"+psmt);
			rs = psmt.executeQuery();
			while(rs.next()){
				ElectionVo obj = new ElectionVo();
				obj.setStaffId(rs.getString("TeacherID"));
				obj.setStaffName(rs.getString("TeacherName"));
				list.add(obj);
			}
	}catch(Exception e){
		e.printStackTrace();

	}
	try {
		if(rs != null && (!rs.isClosed())){
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
return list;	
}


public String saveBoothDetails(ElectionPojo pojo) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;
		PreparedStatement psmt0 = null;
		PreparedStatement psmt4 = null;
		PreparedStatement psmt5 = null;
		
		String key = null;
		String result = null;
		int count0 = 0;
		int count = 0;
		int count3=0;
		int count2 = 0;
		int count1=0;
		int count4=0;
		int count5=0;
		ResultSet rsn =null;
		ResultSet rs,rs1,rs2 = null;
		ResultSet rs0=null;
		IDGenerator id = new IDGenerator();
		

		try{
			
			conn=JDBCConnection.getSeparateConnection();
			
			
			psmt0=conn.prepareStatement("SELECT COUNT(*) FROM campus_election_election_setting WHERE CONCAT(startDate,' ',startTime)<=NOW() AND accyearID=? AND groupID=? AND electionSettingID=?");
			psmt0.setString(1, pojo.getAccyear());
			psmt0.setString(2, pojo.getGroupName());
			psmt0.setString(3, pojo.getElectionTitle());
			
			rs0=psmt0.executeQuery();
			while(rs0.next()){
				count0=rs0.getInt(1);
			}
			
			
			if(pojo.getBoothNameHidden().equalsIgnoreCase("") || pojo.getBoothNameHidden()==null)
			{//if for insert, else for update
			psmt4=conn.prepareStatement(ElectionSQLUtils.checkDuplicateBoothName);
			psmt4.setString(1, pojo.getBoothName());
			psmt4.setString(2, pojo.getAccyear());
			psmt4.setString(3, pojo.getGroupName());
			psmt4.setString(4, pojo.getElectionTitle());
			
			System.out.println("checking condition one while duplicate booth namme******************"+psmt4);
			rsn=psmt4.executeQuery();
			while(rsn.next()){
				count2=rsn.getInt(1);
				System.out.println("checking condition one while duplicate staff name --------------------namme******************"+count2);
			}
			if(count2==0){// if not duplicate booth
				psmt5=conn.prepareStatement(ElectionSQLUtils.checkDuplicateStaff);
				psmt5.setString(1, pojo.getBoothName());
				psmt5.setString(2, pojo.getStaffIncharge());
				psmt5.setString(3, pojo.getAccyear());
				psmt5.setString(4, pojo.getGroupName());
				psmt5.setString(5, pojo.getElectionTitle());
				
				System.out.println("checking condition one while duplicate staff name --------------------namme******************"+psmt5);
				rsn=psmt5.executeQuery();
				while(rsn.next()){
				count3 = rsn.getInt(1);
				}
				System.out.println("Counting the PRIORITY COUNT000000000000000000000000000000000000000000000000000+++++++"+count3);
			
				
				if(count3==0){
					
					psmt1=conn.prepareStatement("SELECT COUNT(*) FROM campus_election_booth_setting WHERE centralSystemIp=? AND accyearID=? AND groupID=? AND electionSettingID=?");
					psmt1.setString(1, pojo.getCentralSystemIp());
					psmt1.setString(2, pojo.getAccyear());
					psmt1.setString(3, pojo.getGroupName());
					psmt1.setString(4, pojo.getElectionTitle());
					rs1=psmt1.executeQuery();
					while(rs1.next()){
						count1=rs1.getInt(1);
					}
					if(count1==0){
						
						psmt2=conn.prepareStatement("SELECT COUNT(*) FROM campus_election_polling_machine_setting WHERE systemIp_var=? AND accyearID=? AND groupID=? AND electionSettingID=?");
						psmt2.setString(1, pojo.getCentralSystemIp());
						psmt2.setString(2, pojo.getAccyear());
						psmt2.setString(3, pojo.getGroupName());
						psmt2.setString(4, pojo.getElectionTitle());
						rs2=psmt2.executeQuery();
						while(rs2.next()){
							count5=rs2.getInt(1);
						}
						if(count5==0){
						key = id.getPrimaryKeyID("campus_election_booth_setting");
						psmt=conn.prepareStatement(ElectionSQLUtils.insertBoothDetails);
						psmt.setString(1, key);
						psmt.setString(2, pojo.getBoothName());
						psmt.setString(3, pojo.getStaffIncharge());
						psmt.setString(4, pojo.getCentralSystem());
						psmt.setString(5, pojo.getCentralSystemIp());
						psmt.setString(6, pojo.getVoterClass());
						psmt.setString(7, pojo.getAccyear());
						psmt.setString(8, pojo.getGroupName());
						psmt.setString(9, pojo.getElectionTitle());

					System.out.println("booth setting-----f8888888888888888888ffffffffffffffffffffffffffffffffffffffff---------->"+psmt);
					count=psmt.executeUpdate();

					if(count>0){
						result = key;
						}else{
						result ="false";
						}
					}
					else{
						result="polingIp";
					}
					}
					else{
						result="Ipexist";
					}
			
			}else{
				result = "dupStaff"; // checking groupname exists or not
				}
				
			}	
			else{
			result = "dupPri"; // checking dupPri exists or not
				}
	
		}//if end
			
		else{ //update else part
				
				psmt=conn.prepareStatement(ElectionSQLUtils.updateBoothDetails);
		
				psmt.setString(1, pojo.getBoothName());
				psmt.setString(2, pojo.getStaffIncharge());
				psmt.setString(3, pojo.getCentralSystem());
				psmt.setString(4, pojo.getCentralSystemIp());
				psmt.setString(5, pojo.getVoterClass());
				psmt.setString(6, pojo.getAccyear());
				psmt.setString(7, pojo.getGroupName());
				psmt.setString(8, pojo.getElectionTitle());
				psmt.setString(9, pojo.getBoothNameHidden());

				System.out.println("booth setting-----f8888888888888888888ffffffffffffffffffffffffffffffffffffffff---------->"+psmt);
				count=psmt.executeUpdate();

				if(count>0){
					result = "updatetrue";
				}else{
					result ="false";
				}
			
			}
			
			
			
		}
		
		//try
			catch(Exception e){
			e.printStackTrace();
		}
		try {
			if (rsn != null && (!rsn.isClosed())) {
				rsn.close();
			}
			if (rs0 != null && (!rs0.isClosed())) {
				rs0.close();
			}
			if (psmt0 != null && (!psmt0.isClosed())) {
				psmt0.close();
			}
			if (psmt4 != null && (!psmt4.isClosed())) {
				psmt4.close();
			}
			if (psmt!= null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (psmt1!= null && (!psmt1.isClosed())) {
				psmt1.close();
			}
			if (psmt2!= null && (!psmt2.isClosed())) {
				psmt2.close();
			}
			if (psmt5!= null && (!psmt5.isClosed())) {
				psmt5.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionDaoImpl : getAccYearByGroup : Ending");
		
		return result;

}


public ArrayList<ElectionVo> getBoothDetailsList(ElectionPojo pojo) {
	
	Connection conn = null;
	PreparedStatement psmt = null;
	PreparedStatement psmt1 = null;
	int sno =0;
	ResultSet rs =null;
	ResultSet rs1 =null;
	ArrayList<ElectionVo> list =new ArrayList<ElectionVo>();
 	try{
			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement(ElectionSQLUtils.getBoothDeat);
			psmt.setString(1, pojo.getAccyear());
			psmt.setString(2, pojo.getGroupName());
			psmt.setString(3, pojo.getElectionTitle());
			System.out.println("list page queru:"+psmt);
			rs = psmt.executeQuery();
			while(rs.next()){
				sno++;
				ElectionVo obj = new ElectionVo();
				obj.setSno(sno);
				obj.setBoothNameId(rs.getString("boothNameId_int"));
				obj.setBoothName(rs.getString("boothName_var"));
				String classArray[]=rs.getString("voterClass_id").split(",");
			if(rs.getString("voterClass_id").equalsIgnoreCase("all")){
				obj.setVoterClass(classArray);
			}
			else{
				
				String className=null;
				String classNameList[]=new String[] {};
				List<String> classNameListVal=Arrays.asList(classNameList);
				List<String> tempList = new ArrayList<String>(classNameListVal);
				
				for(int k=0;k<classArray.length;k++){
				psmt1=conn.prepareStatement("SELECT DISTINCT classdetails_name_var from campus_classdetail where classdetail_id_int=?");
				psmt1.setString(1, classArray[k]);
				rs1=psmt1.executeQuery();
				while(rs1.next()){
				 className=rs1.getString("classdetails_name_var");
				 tempList.add(className);
				}
				}
				String[] totalClassSize=new String[tempList.size()];
				classNameList=tempList.toArray(totalClassSize);
				
				obj.setVoterClass(classNameList);
				
			}	
			
				
				obj.setStaffIncharge(rs.getString("staffIncharge_id_int"));
				obj.setStaffName(rs.getString("StaffName"));
				obj.setCentralSystem(rs.getString("centralSystem"));
				obj.setCentralSystemIp(rs.getString("centralSystemIp"));
			
				list.add(obj);
			}
 	}
	catch(Exception e){
	}
	finally {
	try{
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}

	}catch(Exception e){
		e.printStackTrace();
	}
}
	return list;
}


public String deleteBoothSelectedRow(String id) {
	Connection conn = null;
	int rs = 0;
	PreparedStatement pstmt = null;
	String status =null;
	
	try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ElectionSQLUtils.deleteBoothSelectedRow);
			
			pstmt.setString(1, id);
			System.out.println("execute quserty*********"+pstmt);
			rs = pstmt.executeUpdate();
			 if(rs>0){
				 status="true";
			 }else{
				 
				 status="false";
			 }
	}			
	catch(Exception e){
		e.printStackTrace();
	}finally {
	try{
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}

	}catch(Exception e){
		e.printStackTrace();
	}
	}
return status;
}


public ArrayList<ElectionVo> getUpdateBoothSetting(String boothNameId) {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;

	ArrayList<ElectionVo> list=new ArrayList<ElectionVo>();
	int slno = 0;
	
	try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ElectionSQLUtils.getUpdateBoothSetting);
			pstmt.setString(1, boothNameId);
			System.out.println("execute quserty===========================================*********"+pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno++;
				ElectionVo obj = new ElectionVo();
				obj.setSno(slno);
				obj.setBoothName(rs.getString("boothName_var"));
				obj.setStaffIncharge(rs.getString("staffIncharge_id_int"));
				obj.setCentralSystem(rs.getString("centralSystem"));
				obj.setCentralSystemIp(rs.getString("centralSystemIp"));
				
				String[] voterClass=rs.getString("voterClass_id").split(",");
				obj.setVoterClass(voterClass);
				
				list.add(obj);
			}
	}			
	catch(Exception e){
		e.printStackTrace();
	}try {
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
	
return list;	
}


public ArrayList<ElectionVo> getBoothNameDropdown(ElectionPojo pojo) {
	Connection conn = null;
	PreparedStatement psmt = null;
	PreparedStatement psmt1 = null;
	int sno =0;
	ResultSet rs =null;
	ResultSet rs1 =null;
	ArrayList<ElectionVo> list =new ArrayList<ElectionVo>();
 	try{
			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement(ElectionSQLUtils.getBoothNameDropdown);
			psmt.setString(1, pojo.getAccyear());
			psmt.setString(2, pojo.getGroupName());
			psmt.setString(3, pojo.getElectionTitle());
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:"+psmt);
			rs = psmt.executeQuery();
			while(rs.next()){
				sno++;
				ElectionVo obj = new ElectionVo();
				obj.setSno(sno);
				obj.setBoothNameId(rs.getString("boothNameId_int"));
				obj.setBoothName(rs.getString("boothName_var"));
				list.add(obj);
			} 
			}
	catch(Exception e){
		e.printStackTrace();
	}
	finally {
	try{
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}

	}catch(Exception e){
		e.printStackTrace();
	}
}
	return list;
}


public String savePollingMachineDetails(ElectionPojo pojo) {
	Connection conn = null;
	PreparedStatement psmt0 = null;
	PreparedStatement psmt = null;
	PreparedStatement psmt1 = null;
	PreparedStatement psmt2 = null;
	
	String result = null;
	int count2 = 0;
	int count = 0;
	int count0 = 0;
	int count1 = 0;
	ResultSet rsn = null;
	ResultSet rsn1=null;
	ResultSet rs0=null;
	IDGenerator id = new IDGenerator();
	
	try{
		conn=JDBCConnection.getSeparateConnection();
			psmt2=conn.prepareStatement("SELECT COUNT(*) FROM campus_election_polling_machine_setting WHERE systemIp_var=?");
			psmt2.setString(1, pojo.getPollingSystemIp());
			System.out.println("bootId::"+psmt2);
			rsn=psmt2.executeQuery();
			while(rsn.next()){
				count1=rsn.getInt(1);
			}
		if(count1>0)
		{
		psmt=conn.prepareStatement(ElectionSQLUtils.update);
		psmt.setString(1, pojo.getPollingMachineName());
		psmt.setString(2, pojo.getPollingSystemName());
		psmt.setString(3, pojo.getPollingSystemIp());
		psmt.setString(4, pojo.getAccyear());
		psmt.setString(5, pojo.getGroupName());
		psmt.setString(6, pojo.getElectionTitle());
		psmt.setString(7, pojo.getBoothName());
		System.out.println("7777777777777777777777777777777777777777777777777777777777777777777(UPDATE)-- Update Polling Machine  with ID------------------->"+psmt);
		
		count=psmt.executeUpdate();

		if(count>0){
			result = "updatetrue";
		}else{
			result ="false";
		}
			
	}
	//else part for insert
		else 
		{
			psmt1=conn.prepareStatement("SELECT COUNT(*) FROM `campus_election_booth_setting` WHERE centralSystemIp=? AND accyearID=? AND groupID=? AND electionSettingID=?");
			psmt1.setString(1, pojo.getPollingSystemIp());
			psmt1.setString(2, pojo.getAccyear());
			psmt1.setString(3, pojo.getGroupName());
			psmt1.setString(4, pojo.getElectionTitle());
			rsn1=psmt1.executeQuery();
			while(rsn1.next()){
				count0=rsn1.getInt(1);
			}
			if(count0==0){
			String key1 = id.getPrimaryKeyID("campus_election_polling_machine_setting");
			psmt=conn.prepareStatement(ElectionSQLUtils.insertPollingMachineDetails);
			psmt.setString(1, key1);
			psmt.setString(2, pojo.getBoothName());
			psmt.setString(3, pojo.getPollingMachineName());
			psmt.setString(4, pojo.getPollingSystemName());
			psmt.setString(5, pojo.getPollingSystemIp());
			psmt.setString(6, pojo.getAccyear());
			psmt.setString(7, pojo.getGroupName());
			psmt.setString(8, pojo.getElectionTitle());
			
			System.out.println("booth setting-polling machinee------44444444444444444444444444444444444444444444444444444444444-(iNSERT)------->"+psmt);
			count=psmt.executeUpdate();

			if(count>0){
				result = "true";
			}else{
				result ="false";
			}
			}
			else{
				result ="Ipexist";
			}
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
		try{
			
			if (rs0 != null && (!rs0.isClosed())) {
				rs0.close();
			}
			if (rsn != null && (!rsn.isClosed())) {
				rsn.close();
			}
			if (rsn1 != null && (!rsn1.isClosed())) {
				rsn1.close();
			}
			if (rs0 != null && (!rs0.isClosed())) {
				rs0.close();
			}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (psmt1 != null && (!psmt1.isClosed())) {
					psmt1.close();
				}
				if (psmt2 != null && (!psmt2.isClosed())) {
					psmt2.close();
				}
				if (psmt0 != null && (!psmt0.isClosed())) {
					psmt0.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		return result;
}


public ArrayList<ElectionVo> getPollingMachineList(ElectionPojo pojo) {
	Connection conn = null;
	PreparedStatement psmt = null;
	int sno =0;
	ResultSet rs =null;
	ArrayList<ElectionVo> list =new ArrayList<ElectionVo>();
	IDGenerator id = new IDGenerator();
 	try{
			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement(ElectionSQLUtils.getPollingMachineList);
			psmt.setString(1, pojo.getAccyear());
			psmt.setString(2, pojo.getGroupName());
			psmt.setString(3, pojo.getElectionTitle());
			psmt.setString(4, pojo.getBoothName());
			
			System.out.println("list page queru000000000000000000000000000-----------------------------pollign pamchiodfsdfsdf000000000:"+psmt);
			rs = psmt.executeQuery();
			while(rs.next()){
				sno++;
				ElectionVo obj = new ElectionVo();
				obj.setSno(sno);
				obj.setPollingMachineId(rs.getString("pollingMachineId_int_var"));
				obj.setPollingMachineName(rs.getString("machineName_var"));
				obj.setPollingSystemName(rs.getString("systemName_var"));
				obj.setPollingSystemIp(rs.getString("systemIp_var"));
		
				list.add(obj);
			}
 	}
	catch(Exception e){
		e.printStackTrace();
	}
	finally {
	try{
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}

	}catch(Exception e){
		e.printStackTrace();
	}
}
	return list;
}


public String deletePoleSelectedRow(String id) {
		Connection conn = null;
		int rs = 0;
		PreparedStatement pstmt = null;
		String status =null;
		
		try{
				conn=JDBCConnection.getSeparateConnection();
				pstmt=conn.prepareStatement(ElectionSQLUtils.deletePoleSelectedRow);
				
				pstmt.setString(1, id);
				System.out.println("execute quserty*********"+pstmt);
				rs = pstmt.executeUpdate();
				 if(rs>0){
					 status="true";
				 }else{
					 
					 status="false";
				 }
		}			
		catch(Exception e){
			e.printStackTrace();
		}finally {
		try{
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

		}catch(Exception e){
			e.printStackTrace();
		}
		}
	return status;
	}

//only for fetching data 
public ArrayList<ElectionVo> getUpdatePollingDetails(String boothNameId, String status) {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;

	ArrayList<ElectionVo> list=new ArrayList<ElectionVo>();
	int slno = 0;
	
	try{
			conn=JDBCConnection.getSeparateConnection();
			if(status.equalsIgnoreCase("EBS")){
			pstmt=conn.prepareStatement("SELECT * FROM `campus_election_polling_machine_setting` WHERE `boothNameId_int`=?");
			}
			else{
				pstmt=conn.prepareStatement(ElectionSQLUtils.getUpdatePollingDetails);
			}
			pstmt.setString(1, boothNameId);
		
			System.out.println("execute quserty===========================================*********"+pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno++;
				ElectionVo obj = new ElectionVo();
				obj.setSno(slno);
				obj.setPollingMachineName(rs.getString("machineName_var"));
				obj.setPollingSystemName(rs.getString("systemName_var"));
				obj.setPollingSystemIp(rs.getString("systemIp_var"));
				
				list.add(obj);
			}
	}			
	catch(Exception e){
		e.printStackTrace();
	}try {
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
	
return list;	
}


public String updatePollingData(ElectionPojo pojo) throws SQLException {
	Connection conn = null;
	int rs = 0;
	int count=0;
	PreparedStatement psmt = null;
	String status =null;
	try{
	conn=JDBCConnection.getSeparateConnection();
	
	psmt=conn.prepareStatement(ElectionSQLUtils.update);
	
	psmt.setString(1, pojo.getBoothName());
	psmt.setString(2, pojo.getPollingMachineName());
	psmt.setString(3, pojo.getPollingSystemName());
	psmt.setString(4, pojo.getPollingSystemIp());
	psmt.setString(5, pojo.getAccyear());
	psmt.setString(6, pojo.getGroupName());
	psmt.setString(7, pojo.getElectionTitle());
	psmt.setString(8, pojo.getPollingMachineId());
	
	
	System.out.println("booth setting-polling machinee--0000000000000000000000000000000------------>"+psmt);
	count=psmt.executeUpdate();

	if(count>0){
		status = "true";
	}else{
		status ="false";
	}

}catch(Exception e){
		e.printStackTrace();
	}
finally {

	if (psmt != null && (!psmt.isClosed())) {
		psmt.close();
	}
	if (conn != null && (!conn.isClosed())) {
		conn.close();
	}

}
return status;
}


public String checkDuplicateStaff(ElectionPojo pojo) {
	Connection conn =null;
	PreparedStatement psmt5=null;
	ResultSet rsn =null;
	int count3=0;
	String result=null;
	
	
	try{
	conn=JDBCConnection.getSeparateConnection();
	psmt5=conn.prepareStatement(ElectionSQLUtils.checkDuplicateStaff);
	psmt5.setString(1, pojo.getBoothName());
	psmt5.setString(2, pojo.getStaffIncharge());
	psmt5.setString(3, pojo.getAccyear());
	psmt5.setString(4, pojo.getGroupName());
	psmt5.setString(5, pojo.getElectionTitle());
	
	System.out.println("checking condition one while duplicate staff name --------------------namme******************"+psmt5);
	rsn=psmt5.executeQuery();
	while(rsn.next()){
	count3 = rsn.getInt(1);
	System.out.println("Counting the PRIORITY COUNT000000000000000000000000000000000000000000000000000+++++++"+count3);
	}
	if(count3>0){
		result = "true";
	}else{
		result ="false";
	}
	}
catch(Exception e){
	e.printStackTrace();
}finally{
	try {
		conn.close();
		rsn.close();
		psmt5.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
return result;	
}

//----------------------voter search list:::module-7-----------------------------

public ArrayList<ElectionVo> getElectionTitleByAccYear(ElectionPojo pojo) {
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	Connection conn = null;

	ArrayList<ElectionVo> title = new ArrayList<ElectionVo>();
 	
	try {
		
		conn = JDBCConnection.getSeparateConnection();
		
		pstmt = conn.prepareStatement(ElectionSQLUtils.getTitleByAccyear);
		pstmt.setString(1, pojo.getAccyear());
		System.out.println("checkibng 8888888888888888555555555555555555555555555555555555555 ==========="+pstmt);
		rs=pstmt.executeQuery();
		
		while(rs.next()){
			ElectionVo t = new ElectionVo();
			t.setElectionTitleId(rs.getString("electionSettingID"));
			t.setElectionTitle(rs.getString("electionTitle"));
			
			title.add(t);
			
		}
	}catch(Exception e){
			e.printStackTrace();
		}
		finally {
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
			+ " Control in ReportsMenuDaoImpl : getAccYears : Ending");
	return title;
}


public ArrayList<ElectionVo> getClassByAccyearTitle(ElectionPojo pojo) {
	
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	Connection conn = null;
	ArrayList<ElectionVo> className = new ArrayList<ElectionVo>();
 	
	try {
		
		conn = JDBCConnection.getSeparateConnection();
		
		pstmt = conn.prepareStatement(ElectionSQLUtils.getClassByAccyearTitle);
		pstmt.setString(1, pojo.getAccyear());
		pstmt.setString(2, pojo.getElectionTitle());
		System.out.println("checkibng 8888888888888888555555555555555555555555555555555555555 ----------------------------------------------------------==========="+pstmt);
		rs=pstmt.executeQuery();
		
		while(rs.next()){
			ElectionVo title = new ElectionVo();
			title.setClassName(rs.getString("classdetails_name_var"));
			title.setClassId(rs.getString("classdetail_id_int"));
			//title.setElectionTitleId(rs.getString("electionSettingID"));
			
			className.add(title);
			
		}
	}catch(Exception e){
			e.printStackTrace();
		}
		finally {
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

	return className;
}


public ArrayList<ElectionVo> getSectionByAccyearTitle(ElectionPojo pojo) {
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	Connection conn = null;
	ArrayList<ElectionVo> sectionName = new ArrayList<ElectionVo>();
 	
	try {
		
		conn = JDBCConnection.getSeparateConnection();
		
		pstmt = conn.prepareStatement(ElectionSQLUtils.getSectionByAccyearTitle);
		pstmt.setString(1, pojo.getAccyear());
		pstmt.setString(2, pojo.getElectionTitle());
		System.out.println("checkibng 8888888888888888555555555555555555555555555555555555555 ----------------------------------------------------------==========="+pstmt);
		rs=pstmt.executeQuery();
		
		while(rs.next()){
			ElectionVo title = new ElectionVo();
			title.setSectionId(rs.getString("classsection_id_int"));
			title.setSectionName(rs.getString("classsection_name_var"));
			
			sectionName.add(title);
			
		}
	}catch(Exception e){
			e.printStackTrace();
		}
		finally {
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

	return sectionName;
}


public ArrayList<ElectionVo> getVoterSearchList(String academicYear,String className, String electionTitle, String sectionName) {
	PreparedStatement psmt = null;
	PreparedStatement psmt1 = null;
	ResultSet rs = null;
	ResultSet rs1 = null;
	Connection conn = null;
	int sno = 0,count=0;
	ArrayList<ElectionVo> list =  new ArrayList<ElectionVo>();
	try{
		conn = JDBCConnection.getSeparateConnection();
		psmt = conn.prepareStatement(ElectionSQLUtils.getVoterSearchList);
		psmt.setString(1, academicYear);
		psmt.setString(2, electionTitle);
		psmt.setString(3, className);
		psmt.setString(4, sectionName);
		
		System.out.println("get the nomintation registration data ====thissssssssssssssssss=Nomination Approvallll==========="+psmt);
		rs = psmt.executeQuery();
		while(rs.next()){
			sno++;
			ElectionVo vo = new ElectionVo();
			vo.setSno(sno);
			vo.setAdmissionNo(rs.getString("admissionNo"));
			vo.setStudentId(rs.getString("studentId"));
			vo.setStudentName(rs.getString("studentName"));
			vo.setRollNumber(rs.getString("student_rollno"));
			vo.setAdmissionNo(rs.getString("admissionNo"));
			vo.setClassName(rs.getString("classdetails_name_var"));
			vo.setSectionName(rs.getString("classsection_name_var"));
			
			PreparedStatement pstmt1 = conn.prepareStatement(ElectionSQLUtils.GET_ACADEMICYEAR_NAME);
			pstmt1.setString(1, academicYear);
			System.out.println("ACADEMIC YEAR "+pstmt1);
			rs1 =pstmt1.executeQuery();
			while(rs1.next())
			{
				vo.setAccyear(rs1.getString("acadamic_year"));
			}

			list.add(vo);
	
		
		}
		rs.close();
	}catch(Exception e){
		e.printStackTrace();
	}try {
		
		if (rs != null && (!rs.isClosed())) {
			rs.close();
		}
		if (rs1 != null && (!rs1.isClosed())) {
			rs1.close();
		}
		if (psmt1 != null && (!psmt1.isClosed())) {
			psmt1.close();
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
	return list;
}


public List<ElectionVo> getgetvoterDetailsViewUpdate(ElectionPojo pojo) {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	List<ElectionVo> list=new ArrayList<ElectionVo>() ;
	int slno = 0;
	try{
			conn=JDBCConnection.getSeparateConnection();
	
			pstmt = conn.prepareStatement(ElectionSQLUtils.getgetvoterDetailsViewUpdate);
			pstmt.setString(1, pojo.getAdmissionNo());
			pstmt.setString(2, pojo.getStudentId());
			//pstmt.setString(3, pojo.getAccid());
			System.out.println("getting voter details for listing page:+++++++++++++"+pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno++;
				ElectionVo obj = new ElectionVo();
				obj.setSno(slno);
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setAccid(rs.getString("acadamic_id"));
				
				obj.setAdmissionNo(rs.getString("admissionNo"));
				obj.setLocationName(rs.getString("Location_Name"));
				
				obj.setStudentName(rs.getString("studentName"));
				obj.setStudentId(rs.getString("studentId"));
				
				obj.setSectionName(rs.getString("classsection_name_var"));
				obj.setSectionId(rs.getString("classsection_id_int"));
				
				obj.setClassName(rs.getString("classdetails_name_var"));
				obj.setClassId(rs.getString("classdetail_id_int"));
				
				obj.setRollNumber(rs.getString("student_rollno"));
				
				obj.setStatus(rs.getString("student_status"));
				
				obj.setImgUrl(rs.getString("student_imgurl_var"));
				
				obj.setHouseWise(rs.getString("housename"));
				
				
			
				list.add(obj);
			}
	}			
	catch(Exception e){
		e.printStackTrace();
	}
	finally {
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
	return list;
}


public ArrayList<ElectionVo> getVotingMachine(String accid) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : getVotingMachine Starting");
	
	Connection conn =null;
	PreparedStatement psmt =null;
	ResultSet rs =null;
	ArrayList<ElectionVo> list1 = new ArrayList<ElectionVo>();
 	try{
		conn = JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT `machineName_var`,`pollingMachineId_int_var` FROM `campus_election_polling_machine_setting` WHERE `accyearID` LIKE ? ");
		psmt.setString(1, accid);
		System.out.println("99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999"+psmt);
		rs=psmt.executeQuery();
		while(rs.next()){
			ElectionVo vo = new ElectionVo();
			vo.setPollingMachineId(rs.getString("pollingMachineId_int_var"));
			vo.setPollingMachineName(rs.getString("machineName_var"));
			list1.add(vo);
		}
		
	}catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
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
 	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : getVotingMachine Ending");
	return list1;
}


public String checkIp(String systemIp) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : checkIp Starting");
	Connection conn =null;
	PreparedStatement psmt =null;
	ResultSet rs =null;
	String status =null;
	int count=0;
 	try{
		conn = JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT COUNT(*) FROM campus_election_booth_setting WHERE centralSystemIp=?");
		psmt.setString(1, systemIp);
		System.out.println("voterMachineActivate::"+psmt);
		rs=psmt.executeQuery();
		while(rs.next()){
			count=rs.getInt(1);
		}
		if(count>0){
			status="true";
		}
		else{
			status="false";
		}
	}catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
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
 	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : checkIp Ending");
	return status;
}


public String checkMachicneActivation(String localIp) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : checkMachicneActivation Starting");
	Connection conn =null;
	PreparedStatement psmt =null;
	ResultSet rs =null;
	String status ="false";
	int count=0;
 	try{
		conn = JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT COUNT(*),activationFor FROM campus_election_polling_machine_setting WHERE systemIp_var=? AND status='Y'");
		psmt.setString(1, localIp);
		System.out.println("psmt"+psmt);
		rs=psmt.executeQuery();
		while(rs.next()){
			count=rs.getInt(1);
			status=rs.getString(2);
		}
		if(count==0){
			status="false";
		}
		
		
	}catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
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
 	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : checkMachicneActivation Ending");
	return status;
}


public String getMachicneActivation(ElectionPojo object) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : getMachicneActivation Starting");
	Connection conn =null;
	PreparedStatement psmt0=null;
	PreparedStatement psmt =null;
	ResultSet rs0=null;
	ResultSet rs =null;
	PreparedStatement psmt1 =null;
	PreparedStatement psmt2 =null;
	PreparedStatement psmt3 =null;
	ResultSet rs2 =null;
	String status ="false";
	String voterstatus="";
	
	int count0=0;
	int count=0; 
	int count1=0; 
	int count2=0;
 	try{
		conn = JDBCConnection.getSeparateConnection();
		conn.setAutoCommit(false);
		psmt0=conn.prepareStatement("SELECT COUNT(*) FROM campus_election_election_setting WHERE CONCAT(startDate,' ',startTime)<=NOW() AND CONCAT(endDate,' ',endTime)>=NOW() AND accyearID=? AND groupID=? AND electionSettingID=?");
		psmt0.setString(1, object.getAccid());
		psmt0.setString(2, object.getGroupId());
		psmt0.setString(3, object.getElectionTitleId());
		
		rs0=psmt0.executeQuery();
		while(rs0.next()){
			count0=rs0.getInt(1);
		}
		
		if(count0 > 0){
				psmt2 = conn.prepareStatement("SELECT COUNT(*),status FROM polling_verification_voter_list WHERE voterId=? AND academicYearId=? AND electionGroupId=? AND electionTitleId=?");
				psmt2.setString(1, object.getStudentId());
				psmt2.setString(2, object.getAccid());
				psmt2.setString(3, object.getGroupId());
				psmt2.setString(4, object.getElectionTitleId());
				System.out.println("psmt2psmt2psmt2psmt2:::" + psmt2);
				rs2 = psmt2.executeQuery();
				while (rs2.next()) {
					count1 = rs2.getInt(1);
					voterstatus=rs2.getString(2);
				}
				if (count1 == 0) {
					psmt1 = conn.prepareStatement("UPDATE campus_election_polling_machine_setting SET status='Y',activationFor=? WHERE systemIp_var=? AND status='N'");
					psmt1.setString(1,object.getStudentId() + "," + object.getClassId()+ "," + object.getHouseId());
					psmt1.setString(2, object.getPollingSystemIp());
					System.out.println("psmt1psmt1psm1tpsmt1:::" + psmt1);
					count = psmt1.executeUpdate();
					if (count > 0) {
						psmt3 = conn.prepareStatement("INSERT INTO polling_verification_voter_list (voterId,academicYearId,electionGroupId,electionTitleId) VALUES(?,?,?,?)");
						psmt3.setString(1, object.getStudentId());
						psmt3.setString(2, object.getAccid());
						psmt3.setString(3, object.getGroupId());
						psmt3.setString(4, object.getElectionTitleId());
						System.out.println("psmt3psmt3psmt3psmt3:::" + psmt3);
						count2 = psmt3.executeUpdate();
						if (count2 > 0) {
							status = "true";
							conn.commit();
						} else {
							status = "false";
						}

					} else {
						System.out.println("psmt1");
						status="polling";
					}
				} else {
					if(voterstatus.equalsIgnoreCase("progress")) {
						
						psmt1 = conn.prepareStatement("UPDATE campus_election_polling_machine_setting SET status='Y',activationFor=? WHERE systemIp_var=? AND status IN ('P','N')");
						psmt1.setString(1,object.getStudentId() + "," + object.getClassId()+ "," + object.getHouseId());
						psmt1.setString(2, object.getPollingSystemIp());
						System.out.println("psmt1psmt1psm1tpsmt1:::" + psmt1);
						count = psmt1.executeUpdate();
						if (count > 0) {
							status="true";
							conn.commit();
						} else {
							System.out.println("psmt1");
							status="already";
						}
					
					}
					else {
						status = "casted";
					}
					
				}
				
				
				
				
				

			
		}
		else{
			status = "notStart";
		}
			
			
			
		
 	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		try {
			conn.rollback();
			status = "false";
		} catch (SQLException e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if (rs0 != null && (!rs0.isClosed())) {
				rs0.close();
			}
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (rs2 != null && (!rs2.isClosed())) {
				rs2.close();
			}
			if (psmt0 != null && (!psmt0.isClosed())) {
				psmt0.close();
			}
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (psmt1 != null && (!psmt1.isClosed())) {
				psmt1.close();
			}
			if (psmt2 != null && (!psmt2.isClosed())) {
				psmt2.close();
			}
			if (psmt3 != null && (!psmt3.isClosed())) {
				psmt3.close();
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
			+ " Control in ElectionDaoImpl : getMachicneActivation Ending");
	return status;
}


public List<StudentRegistrationVo> studentSearchbyadmissionNo(
		StudentRegistrationVo registrationVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : studentSearchbyadmissionNo Starting");
	String searchTerm = registrationVo.getSearchTerm() + "%";
	List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pstmObj = null;
	ResultSet rs = null;
	Connection conn = null;
	try {

		conn = JDBCConnection.getSeparateConnection();
		pstmObj = conn
				.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SEARCH_BY_ADMISSION);

		pstmObj.setString(1, searchTerm);
		pstmObj.setString(2, registrationVo.getElectionCategory());

		System.out.println("admission No query is "+pstmObj);

		rs = pstmObj.executeQuery();

		while (rs.next()) {
			StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
			studentRegistrationVo.setAdmissionNo(rs.getString("student_admissionno_var"));
			registrationList.add(studentRegistrationVo);

		}
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
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
			+ " Control in ElectionDaoImpl : studentSearchbyadmissionNo Ending");

	return registrationList;
}


public ArrayList<ElectionVo> getCandidateList(String accyear, String groupId, String electionId, String activationFor) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl: getCandidateList : Starting");
	
	
	PreparedStatement ps_categoryList=null;
	ResultSet rs_categoryList=null;
	PreparedStatement ps_cadidateList = null;
	ResultSet rs_cadidateList=null;
	Connection conn = null;
	int count=0;
		
	ArrayList<ElectionVo> collectionVo=new ArrayList<ElectionVo>();
	
	try {
		
		
		conn = JDBCConnection.getSeparateConnection();
	
		ps_categoryList = conn.prepareStatement("SELECT * FROM campus_election_category_setting WHERE classId REGEXP CONCAT('(^|,)(', REPLACE(?, ',', '|'), ')(,|$)') AND accyearID=? AND groupID=? AND electionSettingID=? ORDER BY priority");
		ps_categoryList.setString(1, activationFor.split(",")[1]);
		ps_categoryList.setString(2, accyear);
		ps_categoryList.setString(3, groupId);
		ps_categoryList.setString(4, electionId);
		System.out.println("ps_categoryList "+ps_categoryList);
		rs_categoryList=ps_categoryList.executeQuery();
		while(rs_categoryList.next()){
			ArrayList<StudentRegistrationVo> cadidateList=new ArrayList<StudentRegistrationVo>();
			ElectionVo	collection=new ElectionVo();
			collection.setElectionCategoryId(rs_categoryList.getString("electionCategoryId"));
			collection.setElectionCategory(rs_categoryList.getString("electionCategory"));
			
			if(rs_categoryList.getString("houseWise").equalsIgnoreCase("NO"))
			{
				if(rs_categoryList.getString("genderWise").equalsIgnoreCase("BOTH") || rs_categoryList.getString("genderWise").equalsIgnoreCase("NO")){
					ps_cadidateList=conn.prepareStatement("SELECT  CONCAT(cs.student_fname_var, ' ',student_lname_var) AS studentName,cenr.*, cc.classdetails_name_var,cc.classdetail_id_int,ccs.classsection_name_var, ccs.classsection_id_int,csc.student_imgurl_var,csc.`student_id_int`,CASE WHEN chs.housename IS NULL THEN 'No House' ELSE chs.housename END housename FROM campus_election_nomiation_registration cenr LEFT JOIN campus_student cs ON cs.student_admissionno_var = cenr.admissionNo LEFT JOIN campus_student_classdetails csc ON cs.student_id_int = csc.student_id_int LEFT JOIN campus_classdetail cc ON (csc.classdetail_id_int=cc.classdetail_id_int AND csc.locationId=cc.locationId) LEFT JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings chs ON csc.student_house WHERE electionCategoryId=? AND csc.fms_acadamicyear_id_int=? AND isApproved='APPROVED' ORDER BY studentName");
					ps_cadidateList.setString(1, rs_categoryList.getString("electionCategoryId"));
					ps_cadidateList.setString(2, accyear);
					
				}
				else{
					ps_cadidateList=conn.prepareStatement("SELECT  CONCAT(cs.student_fname_var, ' ',student_lname_var) AS studentName,cenr.*, cc.classdetails_name_var,cc.classdetail_id_int,ccs.classsection_name_var, ccs.classsection_id_int,csc.student_imgurl_var,csc.`student_id_int`,CASE WHEN chs.housename IS NULL THEN 'No House' ELSE chs.housename END housename FROM campus_election_nomiation_registration cenr LEFT JOIN campus_student cs ON cs.student_admissionno_var = cenr.admissionNo LEFT JOIN campus_student_classdetails csc ON cs.student_id_int = csc.student_id_int LEFT JOIN campus_classdetail cc ON (csc.classdetail_id_int=cc.classdetail_id_int AND csc.locationId=cc.locationId) LEFT JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings chs ON csc.student_house WHERE electionCategoryId=?  AND isApproved='APPROVED' AND cs.student_gender_var IN(SELECT student_gender_var FROM campus_student WHERE student_id_int=?) AND csc.fms_acadamicyear_id_int=? ORDER BY studentName");
					ps_cadidateList.setString(1, rs_categoryList.getString("electionCategoryId"));
					ps_cadidateList.setString(2, activationFor.split(",")[0]);
					ps_cadidateList.setString(3, accyear);
				}
			
			}
			else{
				if(rs_categoryList.getString("genderWise").equalsIgnoreCase("BOTH") || rs_categoryList.getString("genderWise").equalsIgnoreCase("NO")){
				ps_cadidateList=conn.prepareStatement("SELECT  CONCAT(cs.student_fname_var, ' ',student_lname_var) AS studentName,cenr.*, cc.classdetails_name_var,cc.classdetail_id_int,ccs.classsection_name_var, ccs.classsection_id_int,csc.student_imgurl_var,csc.`student_id_int`,CASE WHEN chs.housename IS NULL THEN 'No House' ELSE chs.housename END housename FROM campus_election_nomiation_registration cenr LEFT JOIN campus_student cs ON cs.student_admissionno_var = cenr.admissionNo LEFT JOIN campus_student_classdetails csc ON cs.student_id_int = csc.student_id_int LEFT JOIN campus_classdetail cc ON (csc.classdetail_id_int=cc.classdetail_id_int AND csc.locationId=cc.locationId) LEFT JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings chs ON csc.student_house WHERE electionCategoryId=? AND isApproved='APPROVED' AND csc.student_house=?  AND csc.fms_acadamicyear_id_int=? ORDER BY studentName");
				ps_cadidateList.setString(1, rs_categoryList.getString("electionCategoryId"));
				ps_cadidateList.setString(2, activationFor.split(",")[2]);
				ps_cadidateList.setString(3, accyear);
				}
				else{
					ps_cadidateList=conn.prepareStatement("SELECT  CONCAT(cs.student_fname_var, ' ',student_lname_var) AS studentName,cenr.*, cc.classdetails_name_var,cc.classdetail_id_int,ccs.classsection_name_var, ccs.classsection_id_int,csc.student_imgurl_var,csc.`student_id_int`,CASE WHEN chs.housename IS NULL THEN 'No House' ELSE chs.housename END housename FROM campus_election_nomiation_registration cenr LEFT JOIN campus_student cs ON cs.student_admissionno_var = cenr.admissionNo LEFT JOIN campus_student_classdetails csc ON cs.student_id_int = csc.student_id_int LEFT JOIN campus_classdetail cc ON (csc.classdetail_id_int=cc.classdetail_id_int AND csc.locationId=cc.locationId) LEFT JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings chs ON csc.student_house WHERE electionCategoryId=?  AND isApproved='APPROVED' AND csc.student_house=? AND cs.student_gender_var IN(SELECT student_gender_var FROM campus_student WHERE student_id_int=?) AND csc.fms_acadamicyear_id_int=? ORDER BY studentName");
					ps_cadidateList.setString(1, rs_categoryList.getString("electionCategoryId"));
					ps_cadidateList.setString(2, activationFor.split(",")[2]);
					ps_cadidateList.setString(3, activationFor.split(",")[0]);
					ps_cadidateList.setString(4, accyear);
				}
			}
			System.out.println("ps_cadidateList "+ps_cadidateList);
			rs_cadidateList=ps_cadidateList.executeQuery();
			while(rs_cadidateList.next()){
				StudentRegistrationVo vo=new StudentRegistrationVo();
				vo.setAdmissionNo(rs_cadidateList.getString("admissionNo"));
				vo.setStudentnamelabel(rs_cadidateList.getString("studentName"));
				vo.setClassname(rs_cadidateList.getString("classdetails_name_var"));
				vo.setSectionnaem(rs_cadidateList.getString("classsection_name_var"));
				vo.setHouseName(rs_cadidateList.getString("classsection_name_var"));
				vo.setImage(rs_cadidateList.getString("student_imgurl_var"));
				cadidateList.add(vo);
			}
		
			collection.setCandidateList(cadidateList);
			
			collectionVo.add(collection);
			
		}
		
	
		
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e1) {
		logger.error(e1.getMessage(), e1);
		e1.printStackTrace();
	} finally {
		try {
			if (rs_categoryList != null && (!rs_categoryList.isClosed()) && rs_cadidateList !=null && (!rs_cadidateList.isClosed())) {
				rs_categoryList.close();
				rs_cadidateList.close();
			}
			if (ps_categoryList != null && (!ps_categoryList.isClosed())) {
				ps_categoryList.close();
				ps_cadidateList.close();
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
			+ " Control in ElectionDaoImpl: getCandidateList: Ending");
	
	return collectionVo;
}


public String voteCount(String localIp, String admissionNo, String count2, String voter) {
	Connection conn =null;

	PreparedStatement psmt =null;
	PreparedStatement psmt3 =null;
	ResultSet rs =null;
	PreparedStatement psmt1 =null;
	PreparedStatement psmt2=null;
	String status ="false";
	int voteCount=0;
	int count=0;
	int count1=0;
 	try{
		conn = JDBCConnection.getSeparateConnection();
		
		if(Integer.parseInt(count2)>1){
			psmt1=conn.prepareStatement("UPDATE campus_election_polling_machine_setting SET status='P' WHERE systemIp_var=?");
			
		}
		else{
			psmt1=conn.prepareStatement("UPDATE campus_election_polling_machine_setting SET status='N' WHERE systemIp_var=? AND status='P'");
		
			
			psmt3=conn.prepareStatement("UPDATE polling_verification_voter_list SET status='casted' WHERE voterId=? AND status='progress'");
			psmt3.setString(1,voter);
			psmt3.executeUpdate();
			psmt3.close();
		}	
		psmt1.setString(1, localIp);
			count=psmt1.executeUpdate();
			if(count> 0){
				psmt=conn.prepareStatement("SELECT voteCount FROM campus_election_nomiation_registration WHERE admissionNo=?");
				psmt.setString(1, admissionNo);
				
				System.out.println("psmtggsgd="+psmt);
				rs=psmt.executeQuery();
				while(rs.next()){
					voteCount=rs.getInt("voteCount");
					psmt2=conn.prepareStatement("UPDATE campus_election_nomiation_registration SET voteCount=? WHERE admissionNo=?");
					psmt2.setInt(1, (voteCount+1));
					psmt2.setString(2, admissionNo);
					count1=psmt2.executeUpdate();
					if(count1>0){
						status="true";
					}
					else{
						status="illegal";	
					}
				}
				
			}
			else{
				status="false";
			}
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
 	finally {
 		try{
 				if (rs != null && (!rs.isClosed())) {
 					rs.close();
 				}
 				
 				if (psmt != null && (!psmt.isClosed())) {
 					psmt.close();
 				}
 				if (psmt1 != null && (!psmt1.isClosed())) {
 					psmt1.close();
 				}
 				
 				if (psmt2 != null && (!psmt2.isClosed())) {
 					psmt2.close();
 				}
 				if (conn != null && (!conn.isClosed())) {
 					conn.close();
 				}

 		}catch(Exception e){
 			e.printStackTrace();
 		}
 	}
	return status;
}


public ArrayList<ElectionVo> getBoothDetails(String systemIp, String accyear, String group, String titleID) {

	
	Connection conn = null;
	PreparedStatement psmt = null;
	PreparedStatement psmt1 = null;
	int sno =0;
	ResultSet rs =null;
	ResultSet rs1 =null;
	ArrayList<ElectionVo> list =new ArrayList<ElectionVo>();
 	try{
			conn=JDBCConnection.getSeparateConnection();
			psmt=conn.prepareStatement(ElectionSQLUtils.getBoothDetails);
			psmt.setString(1, systemIp);
			psmt.setString(2, accyear);
			psmt.setString(3, group);
			psmt.setString(4, titleID);
			
			System.out.println("boothDetails:"+psmt);
			rs = psmt.executeQuery();
			while(rs.next()){
				sno++;
				ElectionVo obj = new ElectionVo();
				obj.setSno(sno);
				obj.setBoothNameId(rs.getString("boothNameId_int"));
				obj.setBoothName(rs.getString("boothName_var"));
				String classArray[]=rs.getString("voterClass_id").split(",");
			if(rs.getString("voterClass_id").equalsIgnoreCase("all")){
				obj.setVoterClass(classArray);
			}
			else{
				
				String className=null;
				String classNameList[]=new String[] {};
				List<String> classNameListVal=Arrays.asList(classNameList);
				List<String> tempList = new ArrayList<String>(classNameListVal);
				
				for(int k=0;k<classArray.length;k++){
				psmt1=conn.prepareStatement("SELECT DISTINCT classdetails_name_var from campus_classdetail where classdetail_id_int=?");
				psmt1.setString(1, classArray[k]);
				rs1=psmt1.executeQuery();
				while(rs1.next()){
				 className=rs1.getString("classdetails_name_var");
				 tempList.add(className);
				}
				}
				String[] totalClassSize=new String[tempList.size()];
				classNameList=tempList.toArray(totalClassSize);
				
				obj.setVoterClass(classNameList);
				
			}	
				obj.setStaffIncharge(rs.getString("staffIncharge_id_int"));
				obj.setStaffName(rs.getString("StaffName"));
				obj.setCentralSystem(rs.getString("centralSystem"));
				obj.setCentralSystemIp(rs.getString("centralSystemIp"));
			
				list.add(obj);
			}
 	}
	catch(Exception e){
	}
	finally {
	try{
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (psmt != null && (!psmt.isClosed())) {
				psmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}

	}catch(Exception e){
		e.printStackTrace();
	}
}
	return list;

}


public ArrayList<ElectionVo> getCategoryList(String classId, String accyear, String group, String titleID, String studentId, String houseId) {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	int sno =0;
	ResultSet rs =null;
	ArrayList<ElectionVo> list =new ArrayList<ElectionVo>();
 	try{
			conn=JDBCConnection.getSeparateConnection();
			
			
			
			
			pstmt = conn.prepareStatement("SELECT * FROM campus_election_category_setting WHERE classId REGEXP CONCAT('(^|,)(', REPLACE(?, ',', '|'), ')(,|$)') AND accyearID=? AND groupID=? AND electionSettingID=? ORDER BY priority");
			pstmt.setString(1, classId);
			pstmt.setString(2, accyear);
			pstmt.setString(3, group);
			pstmt.setString(4, titleID);
			
			System.out.println("Get Category List= "+pstmt);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
			
				ElectionVo vo=new ElectionVo();
				String houseID=rs.getString("houseId");
				if(houseID==null || houseID.equalsIgnoreCase("All")) {
					sno++;
					vo.setSno(sno);
					vo.setElectionCategoryId(rs.getString("electionCategoryId"));
					vo.setElectionCategory(rs.getString("electionCategory"));
					list.add(vo);
				}
				else {
					
					if(houseID.equalsIgnoreCase(houseId)) {
						sno++;
						vo.setSno(sno);
						vo.setElectionCategoryId(rs.getString("electionCategoryId"));
						vo.setElectionCategory(rs.getString("electionCategory"));
						list.add(vo);
					}
					
				}
				
			}
 	}
	catch(Exception e){
	}
	finally {
	try{
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}

	}catch(Exception e){
		e.printStackTrace();
	}
}
	return list;
}


public ElectionPojo getFilterationForCandidateList(String systemIp) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl: getFilterationForCandidateList : Starting");
	
	Connection conn =null;
	PreparedStatement psmt =null;
	ResultSet rs =null;
	ElectionPojo pojo =null;
	int count=0;
 	try{
		conn = JDBCConnection.getSeparateConnection();
		psmt=conn.prepareStatement("SELECT * FROM campus_election_polling_machine_setting WHERE systemIp_var=? AND status='Y'");
		psmt.setString(1, systemIp);
		System.out.println("psmt"+psmt);
		rs=psmt.executeQuery();
		while(rs.next()){
			pojo=new ElectionPojo();
			pojo.setAccid(rs.getString("accyearID"));
			pojo.setGroupId(rs.getString("groupID"));
			pojo.setElectionTitleId(rs.getString("electionSettingID"));
			pojo.setActivationFor(rs.getString("activationFor"));
		}

		
	}catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
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
 	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : getFilterationForCandidateList Ending");
	return pojo;
}


public ArrayList<ElectionVo> getUpdatePollingDetailsForVerification(
		String boothNameId, String status, String classId) {
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1=null;
	ResultSet rs1=null;
	ArrayList<ElectionVo> list=new ArrayList<ElectionVo>();
	int slno = 0;
	
	try{
			conn=JDBCConnection.getSeparateConnection();
			if(status.equalsIgnoreCase("EBS")){
				String voterClassId=null;
					pstmt1=conn.prepareStatement("SELECT * FROM campus_election_booth_setting WHERE boothNameId_int=?");
					pstmt1.setString(1, boothNameId);
					rs1=pstmt1.executeQuery();
					while(rs1.next()){
						voterClassId=rs1.getString("voterClass_id");
					}
					if(voterClassId.equalsIgnoreCase("all")){
						pstmt=conn.prepareStatement("SELECT * FROM campus_election_polling_machine_setting cepms LEFT JOIN campus_election_booth_setting cebs ON cebs.boothNameId_int=cepms.boothNameId_int WHERE cepms.boothNameId_int=?");
						pstmt.setString(1, boothNameId);
					}
					else{
						pstmt=conn.prepareStatement("SELECT * FROM campus_election_polling_machine_setting cepms LEFT JOIN campus_election_booth_setting cebs ON cebs.boothNameId_int=cepms.boothNameId_int WHERE cepms.boothNameId_int=? AND cebs.voterClass_id REGEXP CONCAT('(^|,)(', REPLACE(?, ',', '|'), ')(,|$)')");
						pstmt.setString(1, boothNameId);
						pstmt.setString(2, classId);
					}
					
					
				
			}
			
			
		
			System.out.println("execute quserty===========================================*********"+pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno++;
				ElectionVo obj = new ElectionVo();
				obj.setSno(slno);
				obj.setPollingMachineName(rs.getString("machineName_var"));
				obj.setPollingSystemName(rs.getString("systemName_var"));
				obj.setPollingSystemIp(rs.getString("systemIp_var"));
				
				list.add(obj);
			}
	}			
	catch(Exception e){
		e.printStackTrace();
	}try {
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
	
return list;
}


public String getMachicneDeActivation(String systemIp) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : getMachicneActivation Starting");
	Connection conn =null;
	PreparedStatement psmt =null;
	ResultSet rs =null;
	int count=0;
	String status ="false";
	String boothId=null;
	
 	try{
		conn = JDBCConnection.getSeparateConnection();
		conn.setAutoCommit(false);
		
		psmt=conn.prepareStatement("UPDATE campus_election_polling_machine_setting SET status='N',activationFor='-' WHERE systemIp_var=? ");
		psmt.setString(1, systemIp);
	
					
						count = psmt.executeUpdate();
						if (count > 0) {
							status = "true";
							conn.commit();
						} else {
							status = "false";
						}

					
			
		
 	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		try {
			conn.rollback();
			status = "false";
		} catch (SQLException e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
		logger.error(e.getMessage(), e);
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
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : getMachicneActivation Ending");
	return status;
}


public ArrayList<ElectionVo> getElectionDetailsForReport(ElectionPojo pojo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : getElectionDetailsForReport Starting");
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
	int slno = 0;
	int count = 0;
	try{
			conn=JDBCConnection.getSeparateConnection();
	
			pstmt=conn.prepareStatement("SELECT cee.electionSettingID,cee.accyearID,cee.groupID,cee.`electionTitle`,ca.`acadamic_year`,ceg.`groupname`, CASE WHEN NOW() BETWEEN CONCAT(cee.startDate,' ',cee.startTime) AND CONCAT(cee.endDate,' ',cee.endTime) THEN 'Inprogress' WHEN CONCAT(cee.startDate,' ',cee.startTime)>NOW() THEN 'Not Started' WHEN CONCAT(cee.endDate,' ',cee.endTime) < NOW() THEN 'Completed' END AS 'status'  FROM `campus_election_election_setting` cee LEFT JOIN campus_election_group_settings ceg ON cee.groupID=ceg.election_group_id LEFT JOIN `campus_acadamicyear` ca ON cee.accyearID=ca.acadamic_id WHERE cee.accyearID LIKE  ? AND cee.groupID LIKE ? AND cee.electionSettingID LIKE ?");
			pstmt.setString(1, pojo.getAccyear());
			pstmt.setString(2, pojo.getGroupName());
			pstmt.setString(3, pojo.getElectionTitleId());
			System.out.println("))"+pstmt);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				slno++;
				ElectionVo obj = new ElectionVo();
				obj.setElectionTitle(rs.getString("electionTitle"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setGroupName(rs.getString("groupname"));
				obj.setSno(slno);
				obj.setAccid(rs.getString("accyearID"));
				obj.setGroupid(rs.getString("groupID"));
				obj.setElectionTitleId(rs.getString("electionSettingID"));
				obj.setStatus(rs.getString("status"));
				
				
				
				list.add(obj);
				
				
			
				
			}
	}			
	catch(Exception e){
		e.printStackTrace();
	}
	finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (pstmt1 != null && (!pstmt1.isClosed())) {
				pstmt1.close();
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
			+ " Control in ElectionDaoImpl : getElectionDetailsForReport Ending");
	
	return list;
}


public ArrayList<ElectionVo> getWinnerList(String accyear, String group,
		String titleID) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : getWinnerList Starting");
	
	Connection conn = null;
	ResultSet rs = null;
	ResultSet rs1 = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
	int slno = 0;
	int count = 0;
	try{
			conn=JDBCConnection.getSeparateConnection();
			pstmt1=conn.prepareStatement("SELECT electionCategoryId FROM campus_election_category_setting WHERE accyearID =? AND groupID =? AND electionSettingID=? ORDER BY priority");
			pstmt1.setString(1, accyear);
			pstmt1.setString(2, group);
			pstmt1.setString(3, titleID);
			rs1=pstmt1.executeQuery();
			
			while(rs1.next()){

			pstmt=conn.prepareStatement("SELECT cenr.voteCount voteCount,cat.electionCategory,CONCAT(cs.student_fname_var,' ',cs.student_lname_var) AS winnerName,csc.student_imgurl_var,cs.student_admissionno_var,cc.classdetails_name_var,ccl.classsection_name_var,cee.electionSettingID,cee.accyearID,cee.groupID,cee.`electionTitle`,ca.`acadamic_year`,ceg.`groupname`, CASE WHEN NOW() BETWEEN CONCAT(cee.startDate,' ',cee.startTime) AND CONCAT(cee.endDate,' ',cee.endTime) THEN 'Leading' WHEN CONCAT(cee.startDate,' ',cee.startTime)>NOW() THEN 'Not Started' WHEN CONCAT(cee.endDate,' ',cee.endTime) < NOW() THEN 'Winner' END AS 'status'  FROM `campus_election_nomiation_registration` cenr  LEFT JOIN `campus_election_election_setting` cee ON (cee.electionSettingID=cenr.electionSettingID AND cee.accyearID=cenr.accyearID AND cee.groupID=cenr.groupID) LEFT JOIN campus_election_group_settings ceg ON cee.groupID=ceg.election_group_id LEFT JOIN `campus_acadamicyear` ca ON cee.accyearID=ca.acadamic_id LEFT JOIN campus_student_classdetails csc ON (csc.student_id_int=cenr.studentId AND csc.fms_acadamicyear_id_int=cenr.accyearID) LEFT JOIN campus_student cs ON cs.student_id_int=csc.student_id_int LEFT JOIN campus_classdetail cc ON (csc.classdetail_id_int=cc.classdetail_id_int AND csc.locationId=cc.locationId) LEFT JOIN campus_classsection ccl ON csc.classsection_id_int=ccl.classsection_id_int LEFT JOIN campus_election_category_setting cat ON cenr.electionCategoryId=cat.electionCategoryId WHERE cee.accyearID =? AND cee.groupID =? AND cee.electionSettingID=? AND cenr.electionCategoryId=? ORDER BY cenr.voteCount DESC LIMIT 1");
			pstmt.setString(1, accyear);
			pstmt.setString(2, group);
			pstmt.setString(3, titleID);
			pstmt.setString(4, rs1.getString("electionCategoryId"));
			System.out.println("))"+pstmt);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				slno++;
				ElectionVo obj = new ElectionVo();
				obj.setElectionTitle(rs.getString("electionTitle"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setGroupName(rs.getString("groupname"));
				obj.setSno(slno);
				obj.setAccid(rs.getString("accyearID"));
				obj.setGroupid(rs.getString("groupID"));
				obj.setElectionTitleId(rs.getString("electionSettingID"));
				obj.setStatus(rs.getString("status"));
				obj.setStudentName(rs.getString("winnerName"));
				obj.setClassName(rs.getString("classdetails_name_var"));
				obj.setSectionName(rs.getString("classsection_name_var"));
				obj.setVoteCount(rs.getString("voteCount"));
				obj.setImgUrl(rs.getString("student_imgurl_var"));
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setCategoryName(rs.getString("electionCategory"));
				
				list.add(obj);
				
				
			
				
			}
			
			}
	}			
	catch(Exception e){
		e.printStackTrace();
	}
	finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (rs1 != null && (!rs1.isClosed())) {
				rs1.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (pstmt1 != null && (!pstmt1.isClosed())) {
				pstmt1.close();
			}
			if (pstmt1 != null && (!pstmt1.isClosed())) {
				pstmt1.close();
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
			+ " Control in ElectionDaoImpl : getWinnerList Ending");
	
	return list;
}


public String getMachicneDeActivation(ElectionPojo pojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : getMachicneDeActivation Starting");
	Connection conn =null;
	PreparedStatement psmt =null;
	ResultSet rs =null;
	int count=0;
	String status ="false";
	
 	try{
		conn = JDBCConnection.getSeparateConnection();
		conn.setAutoCommit(false);
		
		psmt=conn.prepareStatement("UPDATE campus_election_polling_machine_setting SET status='N',activationFor='-' WHERE pollingMachineId_int_var=? ");
		psmt.setString(1, pojo.getPollingMachineId());
	
					
						count = psmt.executeUpdate();
						if (count > 0) {
							conn.commit();
							status = "true";
							
						} else {
							status = "false";
						}

					
			
		
 	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		try {
			conn.rollback();
			status = "false";
		} catch (SQLException e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
		logger.error(e.getMessage(), e);
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
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : getMachicneDeActivation Ending");
	return status;
}

public List<StudentRegistrationVo> studentSearchbyadmissionNoForApproval(
		StudentRegistrationVo registrationVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : studentSearchbyadmissionNo Starting");
	String searchTerm = registrationVo.getSearchTerm() + "%";
	List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pstmObj = null;
	ResultSet rs = null;
	Connection conn = null;
	try {

		conn = JDBCConnection.getSeparateConnection();
		PreparedStatement pstmt=conn.prepareStatement("SELECT `voterClass_id` FROM `campus_election_booth_setting` WHERE  `centralSystemIp` =? AND `accyearID`=?");
		pstmt.setString(1,registrationVo.getTempregid());
		pstmt.setString(2,registrationVo.getAccyear());
		
		System.out.println(pstmt);
		ResultSet rs1=pstmt.executeQuery();
		
		
		String voterClass_id="";
		if(rs1.next()) {
			for(int i=0;i<rs1.getString("voterClass_id").split(",").length;i++) {
				voterClass_id=voterClass_id+"'"+rs1.getString("voterClass_id").split(",")[i]+"',";
			}
			voterClass_id=voterClass_id.substring(0, voterClass_id.length()-1);
			
			pstmObj = conn.prepareStatement("SELECT cs.student_admissionno_var FROM campus_student cs JOIN campus_student_classdetails csc ON cs.student_id_int=csc.student_id_int WHERE cs.student_admissionno_var LIKE ? AND csc.classdetail_id_int IN("+voterClass_id+") AND csc.fms_acadamicyear_id_int=?");

			pstmObj.setString(1, searchTerm);
			pstmObj.setString(2, registrationVo.getAccyear());

			System.out.println("admission No query is "+pstmObj);

			rs = pstmObj.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setAdmissionNo(rs.getString("student_admissionno_var"));
				registrationList.add(studentRegistrationVo);

			}

		}
		try {
			if (rs1 != null && (!rs1.isClosed())) {
				rs1.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
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
			+ " Control in ElectionDaoImpl : studentSearchbyadmissionNo Ending");

	return registrationList;
}


public ArrayList<ElectionVo> getCandidateList(String accyear, String group, String titleID) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : getWinnerList Starting");
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
	int slno = 0;
	int count = 0;
	try{
			conn=JDBCConnection.getSeparateConnection();
		

			pstmt=conn.prepareStatement("SELECT cenr.voteCount voteCount,cat.electionCategory,CONCAT(cs.student_fname_var,' ',cs.student_lname_var) AS candidateName,csc.student_imgurl_var,cs.student_admissionno_var,cc.classdetails_name_var,ccl.classsection_name_var,cee.electionSettingID,cee.accyearID,cee.groupID,cee.`electionTitle`,ca.`acadamic_year`,ceg.`groupname`  FROM `campus_election_nomiation_registration` cenr  LEFT JOIN `campus_election_election_setting` cee ON (cee.electionSettingID=cenr.electionSettingID AND cee.accyearID=cenr.accyearID AND cee.groupID=cenr.groupID) LEFT JOIN campus_election_group_settings ceg ON cee.groupID=ceg.election_group_id LEFT JOIN `campus_acadamicyear` ca ON cee.accyearID=ca.acadamic_id LEFT JOIN campus_student_classdetails csc ON (csc.student_id_int=cenr.studentId AND csc.fms_acadamicyear_id_int=cenr.accyearID) LEFT JOIN campus_student cs ON cs.student_id_int=csc.student_id_int LEFT JOIN campus_classdetail cc ON (csc.classdetail_id_int=cc.classdetail_id_int AND csc.locationId=cc.locationId) LEFT JOIN campus_classsection ccl ON csc.classsection_id_int=ccl.classsection_id_int LEFT JOIN campus_election_category_setting cat ON cenr.electionCategoryId=cat.electionCategoryId  WHERE cee.accyearID =? AND cee.groupID =? AND cee.electionSettingID=?  ORDER BY cat.priority,cenr.voteCount DESC");
			pstmt.setString(1, accyear);
			pstmt.setString(2, group);
			pstmt.setString(3, titleID);
		
			System.out.println("))"+pstmt);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				slno++;
				ElectionVo obj = new ElectionVo();
				obj.setElectionTitle(rs.getString("electionTitle"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setGroupName(rs.getString("groupname"));
				obj.setSno(slno);
				obj.setAccid(rs.getString("accyearID"));
				obj.setGroupid(rs.getString("groupID"));
				obj.setElectionTitleId(rs.getString("electionSettingID"));
			
				obj.setStudentName(rs.getString("candidateName"));
				obj.setClassName(rs.getString("classdetails_name_var"));
				obj.setSectionName(rs.getString("classsection_name_var"));
				obj.setVoteCount(rs.getString("voteCount"));
				obj.setImgUrl(rs.getString("student_imgurl_var"));
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setCategoryName(rs.getString("electionCategory"));
				
				list.add(obj);
				
				
			
				
			}
			
			
	}			
	catch(Exception e){
		e.printStackTrace();
	}
	finally {
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
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : getWinnerList Ending");
	
	return list;
}


public List<StudentRegistrationVo> studentSearchbyadmissionNoForNomination(StudentRegistrationVo registrationVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : studentSearchbyadmissionNoForNomination Starting");
	String searchTerm = registrationVo.getSearchTerm() + "%";
	List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pstmObj = null;
	ResultSet rs = null;
	Connection conn = null;
	try {

		conn = JDBCConnection.getSeparateConnection();
		
		
		PreparedStatement pstmt=conn.prepareStatement("SELECT nominationLevel,accyearID,houseWise,houseId FROM `campus_election_category_setting` WHERE  `electionCategoryId`=?");
		pstmt.setString(1,registrationVo.getElectionCategory());
		ResultSet rs1=pstmt.executeQuery();
		String nominationLevel="";
		if(rs1.next()) {
			for(int i=0;i<rs1.getString("nominationLevel").split(",").length;i++) {
				nominationLevel=nominationLevel+"'"+rs1.getString("nominationLevel").split(",")[i]+"',";
			}
			nominationLevel=nominationLevel.substring(0, nominationLevel.length()-1);
			if(rs1.getString("houseWise").equalsIgnoreCase("YES")) {
				pstmObj = conn.prepareStatement("SELECT cs.student_admissionno_var FROM campus_student cs JOIN campus_student_classdetails csc ON cs.student_id_int=csc.student_id_int WHERE cs.student_admissionno_var LIKE ? AND  cs.student_gender_var IN(SELECT nominationFor FROM campus_election_category_setting WHERE electionCategoryId=?) AND csc.classdetail_id_int IN("+nominationLevel+") AND csc.fms_acadamicyear_id_int=? AND csc.student_house=?");
				pstmObj.setString(1, searchTerm);
				pstmObj.setString(2, registrationVo.getElectionCategory());
				pstmObj.setString(3, rs1.getString("accyearID"));
				if(rs1.getString("houseId").equalsIgnoreCase("All")) {
					pstmObj.setString(4, "%%");
				}
				else {
					pstmObj.setString(4, rs1.getString("houseId"));
				}
				
			}
			else {
				pstmObj = conn.prepareStatement("SELECT cs.student_admissionno_var FROM campus_student cs JOIN campus_student_classdetails csc ON cs.student_id_int=csc.student_id_int WHERE cs.student_admissionno_var LIKE ? AND  cs.student_gender_var IN(SELECT nominationFor FROM campus_election_category_setting WHERE electionCategoryId=?) AND csc.classdetail_id_int IN("+nominationLevel+") AND csc.fms_acadamicyear_id_int=?");

				pstmObj.setString(1, searchTerm);
				pstmObj.setString(2, registrationVo.getElectionCategory());
				pstmObj.setString(3, rs1.getString("accyearID"));
			}
			

			System.out.println("admission No query is "+pstmObj);

			rs = pstmObj.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setAdmissionNo(rs.getString("student_admissionno_var"));
				registrationList.add(studentRegistrationVo);

			}

		}
		try {
			if (rs1 != null && (!rs1.isClosed())) {
				rs1.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
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
			+ " Control in ElectionDaoImpl : studentSearchbyadmissionNoForNomination Ending");

	return registrationList;
}


public List<StudentRegistrationVo> refrshCheck(StudentRegistrationVo registrationVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionDaoImpl : refrshCheck Starting");
	
	List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pstmObj = null;
	ResultSet rs = null;
	Connection conn = null;
	try {

		conn = JDBCConnection.getSeparateConnection();
		PreparedStatement pstmt=conn.prepareStatement("SELECT `activationFor` FROM `campus_election_polling_machine_setting` WHERE `accyearID`=? AND `electionSettingID`=? AND systemIp_var=? AND `status`='Y'");
		pstmt.setString(1,registrationVo.getAccyear());
		pstmt.setString(2,registrationVo.getElectionCategory());
		pstmt.setString(3, registrationVo.getTempregid());
		ResultSet rs1=pstmt.executeQuery();
	
		if(rs1.next()) {
			
			
			String activationFor=rs1.getString("activationFor").split(",")[0];
			pstmObj = conn.prepareStatement("SELECT cs.student_admissionno_var,csc.student_imgurl_var,cs.student_fname_var FROM campus_student cs JOIN campus_student_classdetails csc ON cs.student_id_int=csc.student_id_int WHERE csc.student_id_int=? AND csc.fms_acadamicyear_id_int=?");

			pstmObj.setString(1, activationFor);
			pstmObj.setString(2, registrationVo.getAccyear());


			rs = pstmObj.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegistrationVo.setStudentFirstName(rs.getString("student_fname_var"));
				studentRegistrationVo.setStudentimage(rs.getString("student_imgurl_var"));
				registrationList.add(studentRegistrationVo);

			}

		}
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
			}
			if (rs1 != null && (!rs1.isClosed())) {
				rs1.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmObj != null && (!pstmObj.isClosed())) {
				pstmObj.close();
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
			+ " Control in ElectionDaoImpl : refrshCheck Ending");

	return registrationList;
}

}
