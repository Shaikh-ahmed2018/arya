package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.TransportTypeDao;
import com.centris.campus.pojo.TransportTypePOJO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.TransportTypeSqlutil;
import com.centris.campus.vo.TransportTypeVO;

public class TransportTypeDaoImpl implements TransportTypeDao{

	private static Logger logger = Logger.getLogger(TransportTypeDaoImpl.class);

	public ArrayList<TransportTypeVO> getAllTransportypeDetails() {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeDaoImpl : getAllTransportypeDetails Starting");
		
		Connection conn = null;
		PreparedStatement ps_getTranpType = null;
		ResultSet rs_getTranpType = null;
		int count=0;
		ArrayList<TransportTypeVO> typelist = new ArrayList<TransportTypeVO>();
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			ps_getTranpType = conn.prepareStatement(TransportTypeSqlutil.GET_TRANSPORT_TYPE_DETAILS);
			
			rs_getTranpType = ps_getTranpType.executeQuery();

			while (rs_getTranpType.next()) {
				count++;
				TransportTypeVO transporttypeVO = new TransportTypeVO();

				transporttypeVO.setSno(count);
				transporttypeVO.setTransptyId(rs_getTranpType.getString("type_id"));
				transporttypeVO.setTransptyname(rs_getTranpType.getString("type_name"));
				transporttypeVO.setTransptydes(rs_getTranpType.getString("type_description"));
				transporttypeVO.setType(rs_getTranpType.getString("type_collectFee"));
			
				typelist.add(transporttypeVO);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				
				if (rs_getTranpType != null && (!rs_getTranpType.isClosed())) {
					rs_getTranpType.close();
				}


				if (ps_getTranpType != null && (!ps_getTranpType.isClosed())) {
					ps_getTranpType.close();
				}
				

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeDaoImpl : getAllTransportypeDetails  Ending");
	
		
		return typelist;
	}

	
	
	public ArrayList<TransportTypeVO> getSearchDetails(TransportTypePOJO transportTypePOJO) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeDaoImpl : getSearchDetails Starting");
		
		Connection conn = null;
		PreparedStatement ps_getTranpType = null;
		ResultSet rs_getTranpType = null;
		
		ArrayList<TransportTypeVO> typelist = new ArrayList<TransportTypeVO>();
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			ps_getTranpType = conn.prepareStatement(TransportTypeSqlutil.GET_SEARCH_DETAILS);
			ps_getTranpType.setString(1, "%"+transportTypePOJO.getSearchtext()+"%");
			ps_getTranpType.setString(2, "%"+transportTypePOJO.getSearchtext()+"%");
			ps_getTranpType.setString(3, "%"+transportTypePOJO.getSearchtext()+"%");
			
			rs_getTranpType = ps_getTranpType.executeQuery();

			while (rs_getTranpType.next()) {
				
				TransportTypeVO transporttypeVO = new TransportTypeVO();

				transporttypeVO.setTransptyId(rs_getTranpType.getString("type_id"));
				transporttypeVO.setTransptyname(rs_getTranpType.getString("type_name"));
				transporttypeVO.setTransptydes(rs_getTranpType.getString("type_description"));
				transporttypeVO.setType(rs_getTranpType.getString("type_collectFee"));
			
				typelist.add(transporttypeVO);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				
				if (rs_getTranpType != null && (!rs_getTranpType.isClosed())) {
					rs_getTranpType.close();
				}


				if (ps_getTranpType != null && (!ps_getTranpType.isClosed())) {
					ps_getTranpType.close();
				}
				

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeDaoImpl : getSearchDetails  Ending");
	
		
		return typelist;
	}




	public String validateTypeName(TransportTypePOJO transportTypePOJO) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeDaoImpl : validateTypeName Starting");
		
		Connection conn = null;
		PreparedStatement ps_validateType = null;
		ResultSet rs_validateType = null;
		
		int count=0;
		String result = MessageConstants.FALSE;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			if(transportTypePOJO.getTransptyId()!=null){
				
				ps_validateType = conn.prepareStatement(TransportTypeSqlutil.VALIDATE_TYPE_NAME_UPDATE);
				ps_validateType.setString(1, transportTypePOJO.getTransptyname());
				ps_validateType.setString(2, transportTypePOJO.getTransptyId());
			}else{
				
				ps_validateType = conn.prepareStatement(TransportTypeSqlutil.VALIDATE_TYPE_NAME);
				ps_validateType.setString(1, transportTypePOJO.getTransptyname());
				
			}
		
			
			rs_validateType = ps_validateType.executeQuery();
			
			while (rs_validateType.next()) {
				
				count = rs_validateType.getInt(1);

			}
			
			if(count>0){
				
				result = MessageConstants.TRUE;
			}
			
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				
				if (rs_validateType != null && (!rs_validateType.isClosed())) {
					rs_validateType.close();
				}


				if (ps_validateType != null && (!ps_validateType.isClosed())) {
					ps_validateType.close();
				}
				

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeDaoImpl : validateTypeName  Ending");
	
		
		return result;
	}



	
	public String AddTransportType(TransportTypePOJO transportTypePOJO) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeDaoImpl : AddTransportType Starting");
		
		Connection conn = null;
		PreparedStatement ps_addTranpType = null;
		String status= MessageConstants.FALSE;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			ps_addTranpType = conn.prepareStatement(TransportTypeSqlutil.ADD_TRANSPORT_TYPE);
			
			ps_addTranpType.setString(1, transportTypePOJO.getTransptyId());
			ps_addTranpType.setString(2, transportTypePOJO.getTransptyname());
			ps_addTranpType.setString(3, transportTypePOJO.getType());
			ps_addTranpType.setString(4, transportTypePOJO.getTransptydes());
			ps_addTranpType.setString(5, transportTypePOJO.getCreatedby());
			ps_addTranpType.setTimestamp(6, HelperClass.getCurrentTimestamp());
			
			int count = ps_addTranpType.executeUpdate();
			
			if(count>0){
				
				status = MessageConstants.TRUE;
				
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				
			
				if (ps_addTranpType != null && (!ps_addTranpType.isClosed())) {
					ps_addTranpType.close();
				}
				

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeDaoImpl : AddTransportType  Ending");
	
		
		return status;
	}



	public String UpdateTransportType(TransportTypePOJO transportTypePOJO) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeDaoImpl : UpdateTransportType Starting");
		
		Connection conn = null;
		PreparedStatement ps_updateTranpType = null;
		String status= MessageConstants.FALSE;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			ps_updateTranpType = conn.prepareStatement(TransportTypeSqlutil.UPDATE_TRANSPORT_TYPE);
			
			
			ps_updateTranpType.setString(1, transportTypePOJO.getTransptyname());
			ps_updateTranpType.setString(2, transportTypePOJO.getType());
			ps_updateTranpType.setString(3, transportTypePOJO.getTransptydes());
			ps_updateTranpType.setString(4, transportTypePOJO.getCreatedby());
			ps_updateTranpType.setTimestamp(5, HelperClass.getCurrentTimestamp());
			ps_updateTranpType.setString(6, transportTypePOJO.getTransptyId());
			
			int count = ps_updateTranpType.executeUpdate();
			
			if(count>0){
				
				status = MessageConstants.TRUE;
				
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				
			
				if (ps_updateTranpType != null && (!ps_updateTranpType.isClosed())) {
					ps_updateTranpType.close();
				}
				

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeDaoImpl : UpdateTransportType  Ending");
	
		
		return status;
	}



	
	public TransportTypeVO editType(TransportTypePOJO transportTypePOJO) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeDaoImpl : editType Starting");
		
		Connection conn = null;
		PreparedStatement ps_getTranpType = null;
		ResultSet rs_getTranpType = null;
		
		TransportTypeVO transporttypeVO = new TransportTypeVO();
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			ps_getTranpType = conn.prepareStatement(TransportTypeSqlutil.GET_SINGLE_TRANSPORT_TYPE);
			ps_getTranpType.setString(1, transportTypePOJO.getTransptyId());
			
			rs_getTranpType = ps_getTranpType.executeQuery();

			while (rs_getTranpType.next()) {
				
				transporttypeVO.setTransptyId(rs_getTranpType.getString("type_id"));
				transporttypeVO.setTransptyname(rs_getTranpType.getString("type_name"));
				transporttypeVO.setTransptydes(rs_getTranpType.getString("type_description"));
				transporttypeVO.setType(rs_getTranpType.getString("type_collectFee"));
			

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				
				if (rs_getTranpType != null && (!rs_getTranpType.isClosed())) {
					rs_getTranpType.close();
				}


				if (ps_getTranpType != null && (!ps_getTranpType.isClosed())) {
					ps_getTranpType.close();
				}
				

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeDaoImpl : editType  Ending");
		
		return transporttypeVO;
	}


	public String deleteType(TransportTypePOJO transportTypePOJO) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeDaoImpl : deleteType Starting");
		
		Connection conn = null;
		int count=(Integer) null;
		PreparedStatement ps_deleteTranpType = null;
		String status= MessageConstants.FALSE;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
		
			for(int i=0;i<transportTypePOJO.getGetDataArray().length;i++){//-------6
				
		
			ps_deleteTranpType = conn.prepareStatement(TransportTypeSqlutil.DELETE_TRANSPORT_TYPE);

			ps_deleteTranpType.setString(1, transportTypePOJO.getGetDataArray()[i]);//---------7
			
			//ps_deleteTranpType.setString(1, transportTypePOJO.getTransptyId());
			
			 count = ps_deleteTranpType.executeUpdate();
			}
			
			if(count>0){
				
				status = MessageConstants.TRUE;
				
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				
			
				if (ps_deleteTranpType != null && (!ps_deleteTranpType.isClosed())) {
					ps_deleteTranpType.close();
				}
				

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportTypeDaoImpl : deleteType  Ending");
	
		
		return status;
	
	}

}
