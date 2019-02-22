package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.AddDesignationDao;
import com.centris.campus.delegate.AddDesignationBD;
import com.centris.campus.forms.AddDesignation;
import com.centris.campus.pojo.AddDesignationPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.AddDesignationVO;

public class AddDesignationDaoImpl implements AddDesignationDao {

	private static Logger logger = Logger
			.getLogger(AddDesignationDaoImpl.class);
	
	
	
	public synchronized ArrayList<AddDesignationVO> DesignationDetails(AddDesignationVO vo) 
	
	{
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl: DesignationDetails : Starting");
		PreparedStatement designation_pstmt = null;
		ResultSet designation_rs = null;

		ArrayList<AddDesignationVO> list = new ArrayList<AddDesignationVO>();

		Connection conn = null;
		
		if (vo.getDesgname()==null || vo.getDesgname().equalsIgnoreCase(""))
			
		{
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			designation_pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_DESIGNATION_DETAILS);

			designation_rs = designation_pstmt.executeQuery();
			
				while (designation_rs.next()) {
				AddDesignationVO addDesignationVO = new AddDesignationVO();
				
				addDesignationVO.setDesgid(designation_rs.getString("DesignationCode").trim());
				addDesignationVO.setDesgname(designation_rs.getString("designationName").trim());
				addDesignationVO.setDesgdes(designation_rs.getString("description").trim());
				addDesignationVO.setCreatedate(designation_rs.getString("createdate").trim());
				addDesignationVO.setCreateuser(designation_rs.getString("CreatedBy").trim());
				list.add(addDesignationVO);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (designation_rs != null && (!designation_rs.isClosed())) {
					designation_rs.close();
				}
				if (designation_pstmt != null
						&& (!designation_pstmt.isClosed())) {
					designation_pstmt.close();
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
		}
		
		
		else if (!vo.getDesgname().equalsIgnoreCase(""))
			
		{
			
			
			try {
				conn = JDBCConnection.getSeparateConnection();

				designation_pstmt = conn.prepareStatement(SQLUtilConstants.GET_SEARCH_DETAILS);

						
				designation_pstmt.setString(1,"%"+vo.getDesgname().trim()+"%");	
				
				designation_pstmt.setString(2,"%"+vo.getDesgname().trim()+"%");				



				designation_rs = designation_pstmt.executeQuery();
				

				while (designation_rs.next())

				{
					AddDesignationVO addDesignationVO = new AddDesignationVO();
					
					addDesignationVO.setDesgid(designation_rs.getString("DesignationCode"));
					addDesignationVO.setDesgname(designation_rs.getString("designationName"));
					addDesignationVO.setDesgdes(designation_rs.getString("description"));
					list.add(addDesignationVO);
					
				}

			}
			catch (SQLException e)

			{
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			catch (Exception e)
			{
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			finally {
				try {
					if (designation_rs != null && (!designation_rs.isClosed())) {
						designation_rs.close();
					}
					if (designation_pstmt != null && (!designation_pstmt.isClosed())) {
						designation_pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl: DesignationDetails : Ending");
		return list;
	
	}
	
	
public String updateDesignationDetails(AddDesignationPojo apojo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AddDesignationDaoImpl:insertDesignationDetails:Starting");

	PreparedStatement designation_pstmt = null;

	
	
	String status = null;

	Connection conn = null;
	
	int result1=0;
	
	try {
		
		conn = JDBCConnection.getSeparateConnection();
		
		designation_pstmt = conn.prepareStatement(SQLUtilConstants.UPDATE_DES_DETAILS);
		
		designation_pstmt.setString(1, apojo.getDesgname().trim());
		designation_pstmt.setString(2, apojo.getDesgdes().trim());
		designation_pstmt.setString(3, "Y");
		designation_pstmt.setString(4, apojo.getDesgid().trim());
		
		System.out.println("UPDATE_DES_DETAILS:;"+designation_pstmt);
		
		result1 = designation_pstmt.executeUpdate();

	
		if (result1 > 0) {

			status = "Designation Update Successfully";
			
		} else {
			status = MessageConstants.UPDATE_DESIGNATION_FAIL;
			
		}
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.printStackTrace();
	} finally {
		try {

			if (designation_pstmt != null && !designation_pstmt.isClosed()) {
				designation_pstmt.close();
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
	
	
public synchronized String insertDesignationDetails(AddDesignationPojo apojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl:insertDesignationDetails:Starting");

		PreparedStatement designation_pstmt = null;

		
		
		String status = null;

		Connection conn = null;
		
		int result1=0;
		
		try 
		{
			
		
			conn = JDBCConnection.getSeparateConnection();
				designation_pstmt = conn.prepareStatement(SQLUtilConstants.INSERT_DESIGNATION_DETAILS);
					designation_pstmt.setString(1, apojo.getDesignationcode());
					designation_pstmt.setString(2, apojo.getDesgname());
					designation_pstmt.setString(3, apojo.getDesgdes());
					designation_pstmt.setString(4, apojo.getCreateuser());
					designation_pstmt.setString(5, "Y");
					
					System.out.println("INSERT_DESIGNATION::"+designation_pstmt);
					
					result1 = designation_pstmt.executeUpdate();

					if (result1 > 0) {

						
						status = "Designation Added Successfully"; 

					} else {
						
						status = MessageConstants.ADD_DESIGNATION_FAIL; 
					}

				}
	
	

			 catch (SQLException sqle) {

				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			} finally {
				try {
					if (designation_pstmt != null
							&& (!designation_pstmt.isClosed())) {
						designation_pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
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
				+ " Control in AddDesignationDaoImpl:insertDesignationDetails: Ending");

		
	
		return status;
	}
	
	public synchronized AddDesignation  EditDesignationDetails(AddDesignation aform) 
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl:updateDesignationDetails:Starting");
		
			
		PreparedStatement designation_pstmt = null;
		
		ResultSet designation_rs = null;
		

		Connection conn = null;
		AddDesignation addDesignationVO =null;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			designation_pstmt = conn
					.prepareStatement(SQLUtilConstants.EDIT_DESIGNATION);

			designation_pstmt.setString(1,aform.getDesignationid());

			designation_rs = designation_pstmt.executeQuery();

			while (designation_rs.next())
			{
				addDesignationVO = new AddDesignation();

				addDesignationVO.setDesignationid(designation_rs.getString("DesignationCode"));
				addDesignationVO.setDesignation_name(designation_rs.getString("designationName"));
				addDesignationVO.setDesignation_description(designation_rs.getString("description"));
				addDesignationVO.setCreateddate(designation_rs.getString("createdate"));
				addDesignationVO.setCreatedby(designation_rs.getString("CreatedBy"));
				
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
				if (designation_rs != null && (!designation_rs.isClosed())) {
					designation_rs.close();
				}
				if (designation_pstmt != null
						&& (!designation_pstmt.isClosed())) {
					designation_pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
		return addDesignationVO;
		
		}
	
	public synchronized String deleteDesignationDetails(String[] designation_code)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl: deleteDesignationDetails : Starting");

		ResultSet designation_rs = null;
		ResultSet rs_deletedesignation = null;
		PreparedStatement deletedesignation_pstmt = null;
		PreparedStatement ps_deletedesignation = null;
		
		int no = 0;
		String status = null;

		Connection conn = null;
		try
		{
			
			for(int i=0;i<designation_code.length;i++){
			conn = JDBCConnection.getSeparateConnection();
			deletedesignation_pstmt = conn.prepareStatement(SQLUtilConstants.CHECK_DESIGNATION_MAP);
			
			deletedesignation_pstmt.setString(1, designation_code[i]);
			
			designation_rs=deletedesignation_pstmt.executeQuery();
			
			System.out.println(deletedesignation_pstmt);
			
			while(designation_rs.next())
			{
				
				no=designation_rs.getInt(1);
			}
			
			if(no==0)
			{
				
				ps_deletedesignation = conn.prepareStatement(SQLUtilConstants.DELETE_DESIGNATION);
				
				ps_deletedesignation.setString(1, "N");
				ps_deletedesignation.setString(2, designation_code[i]);
				
				no = ps_deletedesignation.executeUpdate();
				System.out.println(ps_deletedesignation);
				
				if(no>0)
				{
					
					status="UnMapped Designation Details Deleted Successfully";
					
				}
				
				else
				{
					status=MessageConstants.DESIGNATION_ERROR;
				}
			}
			
			else
				
			{
				
				status=MessageConstants.DESIGNATION_WARNING;
			}
			
			
		}
		}
			
			
			 catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {

			try {

				if (rs_deletedesignation != null
						&& (!rs_deletedesignation.isClosed())) {
					rs_deletedesignation.close();
				}
				if (designation_rs != null && (!designation_rs.isClosed())) {

					designation_rs.close();
				}
				if (ps_deletedesignation != null
						&& (!ps_deletedesignation.isClosed())) {

					ps_deletedesignation.close();
				}
				if (deletedesignation_pstmt != null
						&& (!deletedesignation_pstmt.isClosed())) {
					deletedesignation_pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in AddDesignationDaoImpl: deleteDesignationDetails : Ending");
		
		return status;
	
	}

	public synchronized ArrayList<AddDesignationVO> getSearchDetails(String searchTextVal)
	{

	
		
		logger.setLevel(Level.DEBUG);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl : getSearchDetails Starting");

		ArrayList<AddDesignationVO> bat_Details = new ArrayList<AddDesignationVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(SQLUtilConstants.GET_SEARCH_DETAILS);

					
			pstmt.setString(1,"%"+searchTextVal+"%");
			pstmt.setString(2,"%"+searchTextVal+"%");

			
			rs = pstmt.executeQuery();

			while (rs.next())

			{
				AddDesignationVO bat = new AddDesignationVO();

				bat.setDesgid(rs.getString("DesignationCode"));

				bat.setDesgname(rs.getString("designationName"));

				bat.setDesgdes(rs.getString("description"));
				
				bat_Details.add(bat);

			}

		}
		catch (SQLException e)

		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
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
				+ " Control in AddDesignationDaoImpl : getSearchDetails Ending");

		return bat_Details;
	
	}

	public boolean getnamecount(AddDesignationVO vo) {
		


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl : getnamecount Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;
		if (vo.getDesgid().equalsIgnoreCase(""))
			
		{
			try {

				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(SQLUtilConstants.ADD_DESG_COUNT);
				pstmt.setString(1, vo.getDesgname().trim());
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}

				if (count > 0)

				{
					result = true;
				}

			} catch (SQLException sqlExp) {
				logger.error(sqlExp.getMessage(), sqlExp);
				sqlExp.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			} finally {

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

		}

		if (!vo.getDesgid().equalsIgnoreCase(""))

		{

			

			try {

				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_DESG_COUNT);
				pstmt.setString(1, vo.getDesgid().trim());
				pstmt.setString(2, vo.getDesgname().trim());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}

				if (count > 0)

				{
					result = true;
				}

			} catch (SQLException sqlExp) {
				logger.error(sqlExp.getMessage(), sqlExp);
				sqlExp.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			} finally {

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

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl : getnamecount Ending");
		return result;

	
		
		
	}



	public ArrayList<AddDesignationVO> DesignationDetails() {
	
		return null;
	}


	
	
		}

	

