package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.ReleivingOrderDao;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.ReportMenuVo;

public class ReleivingOrderDaoImpl implements ReleivingOrderDao {
	
	
	private static final Logger logger = Logger
			.getLogger(ReleivingOrderDaoImpl.class);

	
	
	
	
	public List<ReportMenuVo> getTeacherListDao() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsDaoImpl : getTeacherListDao  Starting");
		
		List<ReportMenuVo> teachersList = new ArrayList<ReportMenuVo>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstm = conn.prepareStatement(SQLUtilConstants.GET_ALLTEACHERS);
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
				
				ReportMenuVo vo = new ReportMenuVo();
				
				vo.setTeacherName(rs.getString("teachername"));
				vo.setTeachertId(rs.getString("TeacherID"));
				
				teachersList.add(vo);
				
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsDaoImpl : getTeacherListDao  Ending");
		
		return teachersList;
	}
	
	

}
