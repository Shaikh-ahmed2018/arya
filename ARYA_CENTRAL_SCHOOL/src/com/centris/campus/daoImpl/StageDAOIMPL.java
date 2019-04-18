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
import com.centris.campus.dao.StageDAO;
import com.centris.campus.forms.AddStageForm;
import com.centris.campus.pojo.AddStagePojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.AddStageVO;

public class StageDAOIMPL implements StageDAO
{
	private static final Logger logger = Logger.getLogger(StageDAOIMPL.class);

	
	
	public synchronized ArrayList<AddStageVO> StageDetails(String accyear)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL: StageDetails : Starting");
		PreparedStatement stage_pstmt = null;
		
		ResultSet stage_rs = null;

		ArrayList<AddStageVO> list = new ArrayList<AddStageVO>();

		Connection conn = null;
		
		
		try {
			
			/*System.out.println("if "+vo.getStageName());*/
			
			conn = JDBCConnection.getSeparateConnection();
			stage_pstmt = conn.prepareStatement(SQLUtilConstants.GET_STAGEMASTER_DETAILS);
			stage_pstmt.setString(1, accyear);
			
			
			/*System.out.println("if stage_pstmt "+stage_pstmt);*/
			
			
			stage_rs = stage_pstmt.executeQuery();
			
				while (stage_rs.next()) {
					AddStageVO addstageVO = new AddStageVO();
				
					addstageVO.setStageCode(stage_rs.getString("stage_id").trim());
					addstageVO.setStageName(stage_rs.getString("stage_name").trim());
					
					addstageVO.setAmount(stage_rs.getString("amount").trim());
					
					addstageVO.setDescription(stage_rs.getString("description").trim());
					
				/*	System.out.println("if stage_name "+stage_rs.getString("stage_name"));*/
					
				list.add(addstageVO);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (stage_rs != null && (!stage_rs.isClosed())) {
					stage_rs.close();
				}
				if (stage_pstmt != null
						&& (!stage_pstmt.isClosed())) {
					stage_pstmt.close();
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
				+ " Control in StageDAOIMPL: StageDetails : Ending");
		return list;
		
		}

	public synchronized String insertstage(AddStagePojo apojo) 
		
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL:insertstage:Starting");

		PreparedStatement stage_pstmt = null;

		String status = null;

		Connection conn = null;
		
		int result1=0;
		
		try 
		{
		
			conn = JDBCConnection.getSeparateConnection();
			stage_pstmt = conn.prepareStatement(SQLUtilConstants.INSERT_STAGE_DETAILS);
			stage_pstmt.setString(1, apojo.getStagecode());
			stage_pstmt.setString(2, apojo.getStage_name());
			stage_pstmt.setString(3, apojo.getAmount());
			stage_pstmt.setString(4, apojo.getStage_description());
			stage_pstmt.setString(5, apojo.getCreateuser());
			stage_pstmt.setString(6, apojo.getAccyear());
		
					result1 = stage_pstmt.executeUpdate();
					
					if (result1 > 0) {

						status = MessageConstants.ADD_STAGE_SUCCESS; 

					} else {
						
						status = MessageConstants.ADD_STAGE_FAIL; 
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
					if (stage_pstmt != null
							&& (!stage_pstmt.isClosed())) {
						stage_pstmt.close();
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
				+ " Control in StageDAOIMPL:insertstage: Ending");

		
	
		return status;
	}
	
	public synchronized String updatestage(AddStagePojo apojo) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL:updatestage:Starting");

		PreparedStatement stage_pstmt = null;

		
		
		String status = null;

		Connection conn = null;
		
		int result1=0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			stage_pstmt = conn.prepareStatement(SQLUtilConstants.UPDATE_STAGE_DETAILS);
			
			stage_pstmt.setString(1, apojo.getStage_name().trim());
			stage_pstmt.setString(2, apojo.getAmount().trim());
			stage_pstmt.setString(3, apojo.getStage_description().trim());
			
			stage_pstmt.setString(4, apojo.getCreateuser().trim());
			stage_pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
			stage_pstmt.setString(6, apojo.getStageid().trim());
			/* HelperClass.getCurrentTimestamp()*/
			
		
			
			result1 = stage_pstmt.executeUpdate();

		
			if (result1 > 0) {

				status = MessageConstants.UPDATE_STAGE_SUCCESS;
				
			} else {
				status = MessageConstants.UPDATE_STAGE_FAIL;
				
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {
			try {

				if (stage_pstmt != null && !stage_pstmt.isClosed()) {
					stage_pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
			
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL:updatestage: Ending");

			return status;
		}

	public synchronized AddStageForm EditStageDetails(AddStageForm aform)
	{
		{

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StageDAOIMPL:EditStageDetails:Starting");
			
				
			PreparedStatement stage_pstmt = null;
			
			ResultSet stage_rs = null;
			

			Connection conn = null;
			
			AddStageForm addDesignationVO =null;
			
			
			try
			{
				conn = JDBCConnection.getSeparateConnection();
				stage_pstmt = conn
						.prepareStatement(SQLUtilConstants.EDIT_STAGE);

				stage_pstmt.setString(1,aform.getStageid());

				stage_rs = stage_pstmt.executeQuery();

				while (stage_rs.next())
				{
					addDesignationVO = new AddStageForm();

					addDesignationVO.setStageid(stage_rs.getString("stage_id"));
					addDesignationVO.setStage_name(stage_rs.getString("stage_name"));
					addDesignationVO.setAmount(stage_rs.getString("amount"));
					addDesignationVO.setStage_description(stage_rs.getString("description"));
					addDesignationVO.setCreateddate(stage_rs.getString("createdtime"));
					addDesignationVO.setCreatedby(stage_rs.getString("createdby"));
					
					
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
					if (stage_rs != null && (!stage_rs.isClosed())) {
						stage_rs.close();
					}
					if (stage_pstmt != null
							&& (!stage_pstmt.isClosed())) {
						stage_pstmt.close();
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
					+ " Control in StageDAOIMPL:EditStageDetails: Ending");
			return addDesignationVO;
			
			}
		}

	public synchronized String deleteStage(String[] stageid) 
	
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL:deleteStage : Starting");

		ResultSet stage_rs = null;
		ResultSet rs_deletestage = null;
		PreparedStatement deletestage_pstmt = null;
		PreparedStatement ps_deletestage = null;
		int no = 0;
		String status = null;

		Connection conn = null;
		try
		{
			
			for(int i=0;i<stageid.length;i++){
			conn = JDBCConnection.getSeparateConnection();
			deletestage_pstmt = conn.prepareStatement(SQLUtilConstants.CHECK_STAGE_MAP);
		
			deletestage_pstmt.setString(1, stageid[i]);
			
			System.out.println("CHECK_STAGE_MAP::"+deletestage_pstmt);
			
			stage_rs=deletestage_pstmt.executeQuery();
			
			while(stage_rs.next())
			{
				
				no=stage_rs.getInt(1);
				System.out.println("stage count ::"+no);
			}
			
			if(no == 0)
			{
				
				ps_deletestage = conn.prepareStatement(SQLUtilConstants.DELETE_STAGEMASTER);
				System.out.println("system delete");
				
				ps_deletestage.setString(1, stageid[i]);
				
				System.out.println("DELETE_STAGEMASTER:: "+ps_deletestage);
				
				no = ps_deletestage.executeUpdate();
			
				
				if(no>0)
				{
					
					status=MessageConstants.STAGE_SUCCESS;
					
				}
				
				else
				{
					status=MessageConstants.STAGE_ERROR;
				}
			}
			
			else
				
			{
				status=MessageConstants.STAGE_WARNING;
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

				if (rs_deletestage != null
						&& (!rs_deletestage.isClosed())) {
					rs_deletestage.close();
				}
				if (rs_deletestage != null && (!rs_deletestage.isClosed())) {

					rs_deletestage.close();
				}
				if (ps_deletestage != null
						&& (!ps_deletestage.isClosed())) {

					ps_deletestage.close();
				}
				if (deletestage_pstmt != null
						&& (!deletestage_pstmt.isClosed())) {
					deletestage_pstmt.close();
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
				+ " Control in StageDAOIMPL: deleteStage : Ending");
		
		return status;
	
	}

	
	

	public boolean getstagecount(AddStageVO vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL :  getstagecount Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;
		if (vo.getStageCode().equalsIgnoreCase(""))
			
		{
			try {

				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(SQLUtilConstants.ADD_STAGE_COUNT);
				pstmt.setString(1, vo.getStageName().trim());
				
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

		if (!vo.getStageCode().equalsIgnoreCase(""))

		{

			

			try {

				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_STAGE_COUNT);
				pstmt.setString(1, vo.getStageCode().trim());
				pstmt.setString(2, vo.getStageName().trim());
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
				+ " Control in StageDAOIMPL :  getstagecount Ending");
		return result;

	
	}

	public ArrayList<AddStageVO> SelectAllSatges(AddStageVO vo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL: SelectAllSatges : Starting");
		PreparedStatement stage_pstmt = null;
		
		ResultSet stage_rs = null;

		ArrayList<AddStageVO> list = new ArrayList<AddStageVO>();

		Connection conn = null;

		try {
			
			/*System.out.println("if "+vo.getStageName());*/
			
			conn = JDBCConnection.getSeparateConnection();
			stage_pstmt = conn.prepareStatement(SQLUtilConstants.GET_ALL_STAGE_DETAILS);
			stage_pstmt.setString(1, vo.getAccyearCode());
			
			
			/*System.out.println("if stage_pstmt "+stage_pstmt);*/
			
			
			stage_rs = stage_pstmt.executeQuery();
			
				while (stage_rs.next()) {
					AddStageVO addstageVO = new AddStageVO();
				
					addstageVO.setStageCode(stage_rs.getString("stage_id").trim());
					addstageVO.setStageName(stage_rs.getString("stage_name").trim());
				
		
				list.add(addstageVO);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (stage_rs != null && (!stage_rs.isClosed())) {
					stage_rs.close();
				}
				if (stage_pstmt != null
						&& (!stage_pstmt.isClosed())) {
					stage_pstmt.close();
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
				+ " Control in StageDAOIMPL: SelectAllSatges : Ending");
		return list;
		
		
	}

	public List<AddStageVO> searchStage(String searchName, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL : searchStage Starting");

		ArrayList<AddStageVO> list = new ArrayList<AddStageVO>();
		PreparedStatement pstmt = null;
		ResultSet stage_rs = null;
		Connection conn = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			
			
			pstmt = conn.prepareStatement(SQLUtilConstants.SEARCH_STAGE_DETAILS);

			pstmt.setString(1, "%" + searchName + "%");
			pstmt.setString(2, "%" + searchName + "%");
			pstmt.setString(3, "%" + searchName + "%");
			pstmt.setString(4,accyear);
			stage_rs = pstmt.executeQuery();

			while (stage_rs.next()) {
				sno++;
				
				AddStageVO searchvo = new AddStageVO();
				searchvo.setSno(String.valueOf(sno));
				searchvo.setStageCode(stage_rs.getString("stage_id"));
				searchvo.setStageName(stage_rs.getString("stage_name"));
				searchvo.setAmount(stage_rs.getString("amount"));
				searchvo.setDescription(stage_rs.getString("description"));
				list.add(searchvo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL : searchStage  Ending");

		return list;
	
	}

	@Override
	public ArrayList<AddStageVO> StageDetails(AddStageVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	} 
		
	
		


	

	


