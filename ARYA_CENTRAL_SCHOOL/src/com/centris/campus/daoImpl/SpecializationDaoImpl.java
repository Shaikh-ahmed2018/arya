package com.centris.campus.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.SpecializationDao;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.forms.SpecializationForm;

import com.centris.campus.pojo.SpecializationPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.SpecializationVo;

import java.sql.Connection;

public class SpecializationDaoImpl implements SpecializationDao {

	private static final Logger logger = Logger
			.getLogger(SpecializationDaoImpl.class);
	
	@Override
	public ArrayList<SpecializationVo> getspecializationList(String schoolLocation) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationDaoImpl : getspecializationList Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		
		ArrayList<SpecializationVo> specList=new ArrayList<SpecializationVo>();
		try{
			
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(SQLUtilConstants.GET_SPECIALIZATION_DETAILS);
			if(!schoolLocation.equalsIgnoreCase("all"))
			pstmt.setString(1, schoolLocation);
			else
			pstmt.setString(1, "%%");
			rs=pstmt.executeQuery();
			while(rs.next()){
				
				SpecializationVo vo=new SpecializationVo();
				vo.setSpec_Name(rs.getString("Specialization_name"));
				vo.setClass_Name(rs.getString("classdetails_name_var"));
				vo.setStream_Name(rs.getString("classstream_name_var"));
				vo.setSpec_Id(rs.getString("Specialization_Id"));
				vo.setLocationName(rs.getString("Location_Name"));
				vo.setLocationId(rs.getString("locationId"));
				specList.add(vo);
			}
			
		}catch(Exception e){
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
		
		return specList;
	}

	@Override
	public String insertSpecialization(SpecializationPojo pojo, String specId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationDaoImpl : insertSpecialization Starting");
		List<LocationVO> locationList = new ArrayList<LocationVO>();
		Connection conn= null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count =0;
		String status = null;
		int validateCount=0;
		try{
			conn=JDBCConnection.getSeparateConnection();
		if(specId.equalsIgnoreCase("")){
			if(pojo.getLocationId().equalsIgnoreCase("all")){
				locationList = new  LocationBD().getLocationDetails();
			}
			if(locationList.size()!=0){
				for(int j=0;j<locationList.size();j++){
		
			
			PreparedStatement validate=conn.prepareStatement(SQLUtilConstants.VALIDATE);
			validate.setString(1, pojo.getClass_Id());
			validate.setString(2, pojo.getSpec_Name());
			validate.setString(3, locationList.get(j).getLocation_id());
			rs=validate.executeQuery();
			while(rs.next()){
			
				validateCount = rs.getInt(1);
			}
			if(validateCount == 0){
			pstmt = conn.prepareStatement(SQLUtilConstants.INSERT_SPEC);
			pstmt.setString(1,pojo.getSpecializationid());
			pstmt.setString(2, pojo.getStream_Id());
			pstmt.setString(3, pojo.getClass_Id());
			pstmt.setString(4, pojo.getSpec_Name());
			pstmt.setString(5, pojo.getCreate_User());
			pstmt.setString(6, locationList.get(j).getLocation_id());
			count=pstmt.executeUpdate();
			
			if(count>0){
				status = "true";
			}
			
			}
			else{
				status="false";
			}
		}
			}
			
			else{
				PreparedStatement validate=conn.prepareStatement(SQLUtilConstants.VALIDATE);
				validate.setString(1, pojo.getClass_Id());
				validate.setString(2, pojo.getSpec_Name());
				validate.setString(3, pojo.getLocationId());
				rs=validate.executeQuery();
				while(rs.next()){
				
					validateCount = rs.getInt(1);
				}
				if(validateCount == 0){
				pstmt = conn.prepareStatement(SQLUtilConstants.INSERT_SPEC);
				pstmt.setString(1,pojo.getSpecializationid());
				pstmt.setString(2, pojo.getStream_Id());
				pstmt.setString(3, pojo.getClass_Id());
				pstmt.setString(4, pojo.getSpec_Name());
				pstmt.setString(5, pojo.getCreate_User());
				pstmt.setString(6, pojo.getLocationId());
				count=pstmt.executeUpdate();
				
				if(count>0){
					status = "true";
				}
				
				}
				else{
					status="false";
				}
			
			}
		}
		
		else if(!(specId.equalsIgnoreCase(""))){
			
			try{
				
				conn = JDBCConnection.getSeparateConnection();
				pstmt=conn.prepareStatement(SQLUtilConstants.UPDATE_SPEC);
				
				
				pstmt.setString(1, pojo.getSpec_Name());
				pstmt.setString(2, pojo.getClass_Id());
				pstmt.setString(3, pojo.getStream_Id());
			
				pstmt.setString(4, pojo.getCreate_User());
				pstmt.setString(5, pojo.getLocationId());
				pstmt.setString(6, specId.trim());
				count=pstmt.executeUpdate();
				
				if (count > 0) {
					status = MessageConstants.SPEC_UPDATE_SUCCESS;
				} else {
					status = MessageConstants.SPEC_UPDATE_FAIL;
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		}catch(Exception e){
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
		
		
		return status;
	}

	@Override
	public SpecializationVo editSpecialization(String edit) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: editSpecialization : Starting");

		PreparedStatement spec_pstmt = null;
		ResultSet spec_rs = null;

		
		Connection conn = null;

		SpecializationVo addspecVO = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			spec_pstmt = conn
					.prepareStatement(SQLUtilConstants.EDIT_SPEC);

			spec_pstmt.setString(1, edit);

			spec_rs = spec_pstmt.executeQuery();

			while (spec_rs.next()) {

				addspecVO = new SpecializationVo();

				addspecVO.setSpec_Id(spec_rs.getString("Specialization_Id"));
				addspecVO
						.setSpec_Name(spec_rs.getString("Specialization_name"));
				addspecVO.setClass_Id(spec_rs.getString("ClassDetails_Id"));
				addspecVO.setStream_Id(spec_rs.getString("Stream_Id"));
				addspecVO.setLocationId(spec_rs.getString("locationId"));
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (spec_rs != null && (!spec_rs.isClosed())) {

					spec_rs.close();
				}
				if (spec_pstmt != null && (!spec_pstmt.isClosed())) {

					spec_pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return addspecVO;

	}

	@Override
	public String deleteSpec(String[] specId) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: deleteSpec : Starting");

		ResultSet location_rs = null;

		PreparedStatement location_pstmt = null;
		PreparedStatement ps_deletelocation = null;
		ResultSet rs_deletelocation = null;

		int no = 0;
		String status = null;

		Connection conn = null;
		try {
			
					for(int i=0;i<specId.length;i++){
					ps_deletelocation = (PreparedStatement) JDBCConnection
							.getStatement(SQLUtilConstants.DELETE_SPEC);
					
					ps_deletelocation.setString(1, specId[i]);
					
					no = ps_deletelocation.executeUpdate();
					}
					if (no > 0) {

						status = MessageConstants.SPEC_DELETE_SUCCESS;
					} else {

						status = MessageConstants.SPEC_DELETE_FAIL;
					}
			}

		catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		} finally {

			try {

				if (rs_deletelocation != null
						&& (!rs_deletelocation.isClosed())) {

					rs_deletelocation.close();
				}
				if (location_rs != null && (!location_rs.isClosed())) {

					location_rs.close();
				}
				if (ps_deletelocation != null
						&& (!ps_deletelocation.isClosed())) {

					ps_deletelocation.close();
				}
				if (location_pstmt != null
						&& (!location_pstmt.isClosed())) {

					location_pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl: deleteSpec : Ending");

		return status;
	
	}

	
	public List<SpecializationVo> getSpecializationOnClassBased(String parameter) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getSpecializationOnClassBased : Starting");

		PreparedStatement spec_pstmt = null;
		ResultSet spec_rs = null;
		
		String classId=parameter.split(",")[0];
		String locationId=parameter.split(",")[1];
		Connection conn = null;
		List<SpecializationVo> addspecList=new ArrayList<SpecializationVo>();

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			spec_pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_SPECILIZATION_BASED_ON_CLASS);

			spec_pstmt.setString(1, classId);
			spec_pstmt.setString(2, locationId);

			spec_rs = spec_pstmt.executeQuery();
			while (spec_rs.next()) {

				SpecializationVo addspecVO = new SpecializationVo();

				addspecVO.setSpec_Id(spec_rs.getString("Specialization_Id"));
				addspecVO.setSpec_Name(spec_rs.getString("Specialization_name"));
				addspecList.add(addspecVO);
				
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (spec_rs != null && (!spec_rs.isClosed())) {

					spec_rs.close();
				}
				if (spec_pstmt != null && (!spec_pstmt.isClosed())) {

					spec_pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl: getSpecializationOnClassBased  : Ending");

		return addspecList;
	}

	@Override
	public String getSpecializationOnClassBased(SpecializationForm form1) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationMasterDAOImpl : getSpecializationOnClassBased : Starting");

		PreparedStatement loc_pstmt = null;
		ResultSet loc_rs = null;

		String locame_available = "";
		int count = 0;

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			loc_pstmt = conn.prepareStatement("select count(*) Specialization_name from campus_class_specialization where Specialization_name=? and ClassDetails_Id=? and locationId=?");

			loc_pstmt.setString(1, form1.getSpec_Name());
			loc_pstmt.setString(2, form1.getClass_Id());
			loc_pstmt.setString(3, form1.getLocationId());
			loc_rs = loc_pstmt.executeQuery();

			while (loc_rs.next()) {

				count = loc_rs.getInt("Specialization_name");

			}

			if (count > 0) {

				locame_available = "true";

			} else {

				locame_available = "false";
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			logger.error(sqle);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (loc_rs != null && (!loc_rs.isClosed())) {

					loc_rs.close();
				}
				if (loc_pstmt != null && (!loc_pstmt.isClosed())) {

					loc_pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl : getSpecializationOnClassBased : Ending");
		return locame_available;
	}

	@Override
	public ArrayList<SpecializationVo> getSearchSpecializationList(String searchterm,String school) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationDaoImpl : getSearchSpecializationList Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		String School = school;
		String schoool = null;
		
		ArrayList<SpecializationVo> specList=new ArrayList<SpecializationVo>();
		try{
			if(!(School.equalsIgnoreCase("all"))){
				schoool=School;
			}
			else{
				schoool="%%";
			}
			
			conn=JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(SQLUtilConstants.GET_SEARCH_SPECIALIZATION_DETAILS);
			pstmt.setString(1,"%"+searchterm+"%");
			pstmt.setString(2,"%"+searchterm+"%");
			pstmt.setString(3,"%"+searchterm+"%");
			pstmt.setString(4,"%"+searchterm+"%");
			pstmt.setString(5,schoool);
		
			rs=pstmt.executeQuery();
			while(rs.next()){
				
				SpecializationVo vo=new SpecializationVo();
				vo.setSpec_Name(rs.getString("Specialization_name"));
				vo.setClass_Name(rs.getString("classdetails_name_var"));
				vo.setStream_Name(rs.getString("classstream_name_var"));
				vo.setSpec_Id(rs.getString("Specialization_Id"));
				vo.setLocationName(rs.getString("Location_Name"));
				specList.add(vo);
			}
			
		}catch(Exception e){
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
		
		return specList;
	}

	public List<SpecializationVo> getstreamdetailsforOnchange(String locationid, String classname, String streamId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getstreamdetailsforOnchange  Starting");
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		List<SpecializationVo> list = new ArrayList<SpecializationVo>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement("SELECT csp.`Specialization_Id`,cl.Location_Name,cstr.classstream_name_var,cstr.classstream_id_int,ccd.classdetails_name_var,ccd.classdetail_id_int,csp.`Specialization_name` FROM `campus_class_specialization` csp LEFT JOIN campus_classdetail ccd ON (csp.ClassDetails_Id=ccd.classdetail_id_int AND csp.`locationId` = ccd.`locationId`)   LEFT JOIN campus_classstream cstr  ON (ccd.classstream_id_int=cstr.classstream_id_int AND ccd.locationId=cstr.locationId) LEFT JOIN campus_location cl ON ccd.locationId=cl.Location_Id  WHERE ccd.locationId LIKE ? AND ccd.classstream_id_int LIKE ? AND ccd.classdetail_id_int LIKE ? ORDER BY CAST(SUBSTR(ccd.locationId,4) AS UNSIGNED),CAST(SUBSTR(ccd.classstream_id_int,4) AS UNSIGNED),CAST(SUBSTR(ccd.classdetail_id_int,4) AS UNSIGNED)");
			pst.setString(1, locationid);
			pst.setString(2, streamId);
			pst.setString(3, classname);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				
				SpecializationVo stdvo = new SpecializationVo();
		
				stdvo.setSpec_Id(rs.getString("Specialization_Id"));
				stdvo.setLocationName(rs.getString("Location_Name"));
				stdvo.setStream_Name(rs.getString("classstream_name_var"));
				stdvo.setClass_Name(rs.getString("classdetails_name_var"));
				stdvo.setSpec_Name(rs.getString("Specialization_name"));

				list.add(stdvo);
			list.size();
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getstreamdetailsforOnchange  Ending");

		return list;

	}
	
	public List<SpecializationVo> getSpecializationOnClassWithoutLocId(String parameter) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getSpecializationOnClassWithoutLocId : Starting");

		PreparedStatement spec_pstmt = null;
		ResultSet spec_rs = null;
		
		String classId=parameter.split(",")[0];
		Connection conn = null;
		List<SpecializationVo> addspecList=new ArrayList<SpecializationVo>();

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			spec_pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_SPECILIZATION_BY_CLASS_NOTBY_LOCATION);

			spec_pstmt.setString(1, classId);

			spec_rs = spec_pstmt.executeQuery();
			while (spec_rs.next()) {

				SpecializationVo addspecVO = new SpecializationVo();

				addspecVO.setSpec_Id(spec_rs.getString("Specialization_Id"));
				addspecVO.setSpec_Name(spec_rs.getString("Specialization_name"));
				addspecList.add(addspecVO);
				
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (spec_rs != null && (!spec_rs.isClosed())) {

					spec_rs.close();
				}
				if (spec_pstmt != null && (!spec_pstmt.isClosed())) {

					spec_pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl: getSpecializationOnClassWithoutLocId  : Ending");

		return addspecList;
	}
}
