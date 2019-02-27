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

import com.centris.campus.dao.StreamDetailsDao;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.StreamSqlUtils;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.StreamDetailsVO;

public class StreamDetailsDaoImpl implements StreamDetailsDao {
	private static final Logger logger = Logger
			.getLogger(StreamDetailsDaoImpl.class);

	public List<StreamDetailsPojo> getStreamDetailsDao(String schoolLocation) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : getStreamDetailsDao Starting");
		List<StreamDetailsPojo> streamList = new ArrayList<StreamDetailsPojo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			if(schoolLocation.equalsIgnoreCase("all")){
				schoolLocation="%%";
			}

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(StreamSqlUtils.GET_STREAM_DETAILS);
			pstmt.setString(1, schoolLocation);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StreamDetailsPojo streamPojo = new StreamDetailsPojo();
				streamPojo.setStreamId(rs.getString("classstream_id_int"));
				streamPojo.setStreamName(rs.getString("classstream_name_var"));
				streamPojo.setDescription(rs.getString("description"));
				streamPojo.setLocationName(rs.getString("Location_Name"));
				streamList.add(streamPojo);

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
				+ " Control in StreamDetailsDaoImpl : getStreamDetailsDao  Ending");
		return streamList;
	}

	
	
	
	public int insertStreamDetailsDao(StreamDetailsPojo detailsPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : insertStreamDetailsDao  Starting");

		String streamName = detailsPojo.getStreamName();
		String desc = detailsPojo.getDescription();
		List<LocationVO> locationList = new ArrayList<LocationVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int rs=0;
		
		try {
			
			String properDescription = desc
					.replaceAll("[^\\x00-\\x7F]", "");
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(StreamSqlUtils.INSERT_STREAM_DETAILS);
			
			if(detailsPojo.getLocationId().equalsIgnoreCase("all")){
				locationList = new  LocationBD().getLocationDetails();
				System.out.println(locationList.size());
			}
			if(locationList.size()!=0){
				for(int j=0;j<locationList.size();j++){
					
			pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_classstream"));

			pstmt.setString(2, streamName.trim());
			pstmt.setString(3, properDescription.trim());
			pstmt.setString(4, detailsPojo.getCreateUser());
			pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
			pstmt.setString(6, null);
			pstmt.setString(7, null);
			pstmt.setString(8, locationList.get(j).getLocation_id());
			rs = pstmt.executeUpdate();
				}
			}
			else{
				pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_classstream"));

				pstmt.setString(2, streamName.trim());
				pstmt.setString(3, properDescription.trim());
				pstmt.setString(4, detailsPojo.getCreateUser());
				pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
				pstmt.setString(6, null);
				pstmt.setString(7, null);
				pstmt.setString(8, detailsPojo.getLocationId());
				rs = pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		/*if (!detailsPojo.getStreamId().equalsIgnoreCase("")) {

			{

				try {

					conn = JDBCConnection.getSeparateConnection();
					pstmt = conn
							.prepareStatement(StreamSqlUtils.UPDATE_STREAM_DETAILS);

					pstmt.setString(1, detailsPojo.getStreamName().trim());
					pstmt.setString(2, detailsPojo.getDescription().trim());
					pstmt.setString(3, detailsPojo.getCreateUser().trim());

					pstmt.setTimestamp(4, HelperClass.getCurrentTimestamp());
					pstmt.setString(5, null);
					pstmt.setString(6, null);
					pstmt.setString(7, detailsPojo.getStreamId().trim());

					result = pstmt.executeUpdate();

					if (result == 1) {
						results = true;
					} else {
						results = false;
					}

				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}

		} else if (detailsPojo.getStreamId().equalsIgnoreCase("")) {

			try {

				String properDescription = desc
						.replaceAll("[^\\x00-\\x7F]", "");
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn
						.prepareStatement(StreamSqlUtils.INSERT_STREAM_DETAILS);

				pstmt.setString(1,
						IDGenerator.getPrimaryKeyID("campus_classstream"));

				pstmt.setString(2, streamName.trim());
				pstmt.setString(3, properDescription.trim());
				pstmt.setString(4, detailsPojo.getCreateUser());
				pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
				pstmt.setString(6, null);
				pstmt.setString(7, null);

				result = pstmt.executeUpdate();

				if (result == 1) {
					results = true;
				} else {
					results = false;
				}

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			finally {
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
		}*/
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StreamDetailsDaoImpl : insertStreamDetailsDao  Ending");

		
		return rs;

	}

	
	
	
	public int updateStreamDetailsDao(StreamDetailsPojo detailsPojo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : updateStreamDetailsDao  Starting");
		
		
		String streamName = detailsPojo.getStreamName();
		String desc = detailsPojo.getDescription();

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int rs=0;
		
		
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(StreamSqlUtils.UPDATE_STREAM_DETAILS);

			pstmt.setString(1, detailsPojo.getStreamName().trim());
			pstmt.setString(2, detailsPojo.getDescription().trim());
			pstmt.setString(3, detailsPojo.getCreateUser().trim());
			pstmt.setTimestamp(4,  HelperClass.getCurrentTimestamp());
			pstmt.setString(5, detailsPojo.getLocationId());
			pstmt.setString(6, detailsPojo.getStreamId().trim());

			result = pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {

			try {
				
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : updateStreamDetailsDao  Ending");
		
		
		
		return result;
	}
	

	
	public StreamDetailsVO editStreamDetailsDao(StreamDetailsVO detailsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : editStreamDetailsDao Starting");

		StreamDetailsVO stream = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(StreamSqlUtils.GET_STREAM_ID);

			pstmt.setString(1, detailsVo.getStreamId());

			rs = pstmt.executeQuery();
			while (rs.next()) {

				stream = new StreamDetailsVO();

				stream.setStreamId(rs.getString("classstream_id_int"));
				stream.setStreamName(rs.getString("classstream_name_var"));
				stream.setDescription(rs.getString("description"));
				stream.setLocationId(rs.getString("locationId"));
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
				+ " Control in StreamDetailsDaoImpl : editStreamDetailsDao  Ending");

		return stream;
	}

	public String deleteStreamDetailsDao(String[] streamid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : deleteStreamDetailsDao Starting");

		PreparedStatement pstmt = null;
		String check = "";
		String check1 ="";
		int streamcount = 0;
	/*	String streamid = detailsVo.getStreamId();*/
		int count = 0;
		Connection conn = null;
		ResultSet rs = null;
		int stream = 0;
		try {
			for(int i=0;i<streamid.length;i++){
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(StreamSqlUtils.DELETE_STREAM_MAPPING);
				pstmt.setString(1, streamid[i]);
				rs = pstmt.executeQuery();

				while(rs.next()){
					streamcount=rs.getInt(1);
					if(streamcount > 0){
						stream ++;
					}
				}
				pstmt.close();
				if (streamcount == 0) {
					pstmt = conn
							.prepareStatement(StreamSqlUtils.DELETE_STREAM_DETAILS);
					pstmt.setString(1, streamid[i]);
					count = pstmt.executeUpdate();

					if (count > 0) {

						check = MessageConstants.DELETE_SUCCESS;
					} else {
						check = MessageConstants.DELETE_FAIL;
					}
					
				}
				
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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
				+ " Control in StreamDetailsDaoImpl : deleteStreamDetailsDao  Ending");

		return check;
		
	}

	public ArrayList<StreamDetailsVO> searchStreamDetailsDao(String searchTerm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : searchStreamDetailsDao Starting");

		ArrayList<StreamDetailsVO> getstreamlist = new ArrayList<StreamDetailsVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;
		String searchName=searchTerm.split(",")[0];
		String school=searchTerm.split(",")[1];

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(StreamSqlUtils.SEARCH_STREAM_DETAILS);

			pstmt.setString(1, "%" + searchName + "%");
			pstmt.setString(2, "%" + searchName + "%");
			pstmt.setString(3, "%" + searchName + "%");
			if(!school.equalsIgnoreCase("all"))
			pstmt.setString(4, school);
			else
			pstmt.setString(4, "%%");	

			
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sno++;

				StreamDetailsVO streamvo = new StreamDetailsVO();

				streamvo.setSno(String.valueOf(sno));
				streamvo.setStreamId(rs.getString("classstream_id_int"));
				streamvo.setStreamName(rs.getString("classstream_name_var"));
				streamvo.setDescription(rs.getString("description"));
				streamvo.setLocationName(rs.getString("Location_Name"));

				getstreamlist.add(streamvo);
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
				+ " Control in StreamDetailsDaoImpl : searchStreamDetailsDao  Ending");

		return getstreamlist;
	}

	public boolean validateStreamNameDao(StreamDetailsVO detailsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in validateStreamDetailsDaoImpl : validateStreamNameDetailsDao Starting");

		boolean streamname_validate = false;

		int count = 0;
		PreparedStatement pscheckStreamName = null;
		ResultSet rsCheckStreamName = null;
		Connection conn = null;

		if (detailsVo.getStreamId().equalsIgnoreCase("")) {

			try {
				conn = JDBCConnection.getSeparateConnection();
				pscheckStreamName = conn.prepareStatement(StreamSqlUtils.VALIDATE_STREAM_NAME_UPDATE);

				pscheckStreamName.setString(1, detailsVo.getStreamname().trim());
				pscheckStreamName.setString(2, detailsVo.getLocationId().trim());

				rsCheckStreamName = pscheckStreamName.executeQuery();
				while (rsCheckStreamName.next()) {

					count = rsCheckStreamName.getInt(1);

				}
				if (count > 0) {

					streamname_validate = true;

				} else {

					streamname_validate = false;
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		else if (!detailsVo.getStreamId().equalsIgnoreCase(""))

		{
			try {
				conn = JDBCConnection.getSeparateConnection();
				pscheckStreamName = conn
						.prepareStatement(StreamSqlUtils.VALIDATE_STREAM_NAME_EDIT);

				pscheckStreamName.setString(1, detailsVo.getStreamId().trim());
				pscheckStreamName
						.setString(2, detailsVo.getStreamname().trim());

				rsCheckStreamName = pscheckStreamName.executeQuery();

				while (rsCheckStreamName.next()) {

					count = rsCheckStreamName.getInt(1);

				}
				if (count > 0) {

					streamname_validate = true;

				} else {

					streamname_validate = false;
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				try {
					if (rsCheckStreamName != null
							&& (!rsCheckStreamName.isClosed())) {
						rsCheckStreamName.close();
					}

					if (pscheckStreamName != null
							&& (!pscheckStreamName.isClosed())) {
						pscheckStreamName.close();
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

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in validateDetailsDaoImpl : searchStreamNameDetailsDao  Ending");

		return streamname_validate;
	}
	
	public ArrayList<StreamDetailsVO> searchByLocationOnly(String locationId,String accYear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchByLocationOnly Starting");
		
		ArrayList<StreamDetailsVO> streamList = new ArrayList<StreamDetailsVO>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String locationid = locationId;
		
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_SEARCH_BY_LOCATION_ONLY);
			pst.setString(1,locationid);
	
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StreamDetailsVO streamPojo = new StreamDetailsVO();
				streamPojo.setStreamId(rs.getString("classstream_id_int"));
				streamPojo.setStreamName(rs.getString("classstream_name_var"));
				streamPojo.setDescription(rs.getString("description"));
				streamPojo.setLocationName(rs.getString("Location_Name"));
				streamList.add(streamPojo);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchByLocationOnly Ending");
		
		return streamList;
	}


	
}