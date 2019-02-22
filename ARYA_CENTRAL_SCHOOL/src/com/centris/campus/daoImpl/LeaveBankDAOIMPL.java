package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.LeaveBankDAO;
import com.centris.campus.delegate.LocationBD;

import com.centris.campus.forms.LeaveBankForm;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.TeacherUtilsConstants;

import com.centris.campus.vo.LeaveBankVO;
import com.centris.campus.vo.LeaveCalculation;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.LeaveViewDetailsVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.util.HelperClass;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class LeaveBankDAOIMPL implements LeaveBankDAO {
	private static Logger logger = Logger.getLogger(LeaveBankDAOIMPL.class);

	@Override
	public ArrayList<LeaveBankVO> leavebanklist(LeaveBankVO vo)

	{

		System.out.println("Leave BAnk DAOIMPL is Working");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL: leavebanklist : Starting");
		PreparedStatement leavebank_pstmt = null;

		ResultSet leavebank_rs = null;

		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();

		Connection conn = null;
		if (vo.getAccyearcode() == null
				|| vo.getAccyearcode().equalsIgnoreCase(""))

		{
			try {

				conn = JDBCConnection.getSeparateConnection();
				leavebank_pstmt = conn.prepareStatement(SQLUtilConstants.LEAVEBANK_DETAILS);

				leavebank_rs = leavebank_pstmt.executeQuery();

				while (leavebank_rs.next()) {
					LeaveBankVO vo1 = new LeaveBankVO();
					vo1.setSno(leavebank_rs.getString("sno"));
					vo1.setAccyear(leavebank_rs.getString("acadamic_id"));
					vo1.setAcademicyear(leavebank_rs.getString("acadamic_year"));
					
					vo1.setTotalleaves(leavebank_rs.getString("total_leaves"));
					
					vo1.setPermonth(leavebank_rs.getString("allowed_per_month"));
					
					vo1.setsl(leavebank_rs.getDouble("SL"));
					vo1.setPl(leavebank_rs.getDouble("PL"));
					vo1.setCl(leavebank_rs.getDouble("CL"));
					list.add(vo1);
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
					if (leavebank_rs != null && (!leavebank_rs.isClosed())) {
						leavebank_rs.close();
					}
					if (leavebank_pstmt != null
							&& (!leavebank_pstmt.isClosed())) {
						leavebank_pstmt.close();
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
		} else if (!vo.getAccyearcode().equalsIgnoreCase(""))

			try {
				conn = JDBCConnection.getSeparateConnection();

				leavebank_pstmt = conn.prepareStatement(SQLUtilConstants.GET_SEARCH_LEAVEBANKDETAILS);

				leavebank_pstmt.setString(1, "%" + vo.getAccyearcode() + "%");
				leavebank_pstmt.setString(2, "%" + vo.getAccyearcode() + "%");
				leavebank_pstmt.setString(3, "%" + vo.getAccyearcode() + "%");

				leavebank_rs = leavebank_pstmt.executeQuery();
				
				System.out.println("leavebank_pstmt"+leavebank_pstmt);

				while (leavebank_rs.next())

				{
					LeaveBankVO bat = new LeaveBankVO();

					bat.setAcademicyear(leavebank_rs.getString("acadamic_year"));
					
					bat.setTotalleaves(leavebank_rs.getString("total_leaves"));

					bat.setPermonth(leavebank_rs.getString("allowed_per_month"));

					list.add(bat);

				}

			} catch (SQLException e)

			{
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			finally {
				try {
					if (leavebank_rs != null && (!leavebank_rs.isClosed())) {
						leavebank_rs.close();
					}
					if (leavebank_pstmt != null && (!leavebank_pstmt.isClosed())) {
						leavebank_pstmt.close();
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
				+ " Control in LeaveBankDAOIMPL : leavebanklist Ending");
		return list;

	}

	
	@Override
	public String insertLeaveBanksDAO(LeaveBankVO vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:addLeave Starting");

		Connection con = null;
		PreparedStatement pstmt = null,psmtcheckjoiningdate = null,pstmt_hrms = null;
		ResultSet rs=null,rscheckjoiningdate=null;
		String dateofjoin = "",empid = "";
		String addstatus = "";
		if (!(validAddLeave(vo.getAcademicyear()))) {

			try {
				con = JDBCConnection.getSeparateConnection();
				java.sql.CallableStatement stmt= con.prepareCall("{call getAccYear()}");   
				 rs = stmt.executeQuery();  
				
				while(rs.next())
					
				{
				}
				
				psmtcheckjoiningdate = con.prepareStatement("select distinct TeacherID,FirstName,dateofJoining from campus_teachers where isActive='Y' ");
				
				rscheckjoiningdate = psmtcheckjoiningdate.executeQuery();
				
				while(rscheckjoiningdate.next())
				
				{
					
					empid = rscheckjoiningdate.getString("TeacherID");
					
					dateofjoin = rscheckjoiningdate.getString("dateofJoining");
					
					String [] doj = dateofjoin.split("-");
					
					String [] currdate = HelperClass.getCurrentSqlDate().toString().split("-");

					int noofmonths=(Integer.parseInt(currdate[0])-Integer.parseInt(doj[0]))*12;
					
					noofmonths=noofmonths+((12-(Integer.parseInt(doj[1])-1)+(Integer.parseInt(currdate[1])-12)));
					
						pstmt_hrms  = con.prepareStatement(SQLUtilConstants.ADD_LEAVE_TECHAER_LEAVE_BANK);
						
						
						pstmt_hrms.setString(1, vo.getAcademicyear());
						pstmt_hrms.setString(2, empid);
						pstmt_hrms.setDouble(3, vo.getsl());
						pstmt_hrms.setDouble(4, vo.getPl());
						pstmt_hrms.setDouble(5, vo.getCl());
						pstmt_hrms.setDouble(6, 0);
						pstmt_hrms.setString(7, vo.getTotalleaves());
						pstmt_hrms.setTimestamp(8, HelperClass.getCurrentTimestamp());
						pstmt_hrms.setString(9, vo.getCreateuser());
						
						int res = pstmt_hrms.executeUpdate();
						
						if (res > 0) {
							addstatus = "Leave Details Added successfully";
						}
				}		
				
				pstmt = con.prepareStatement(SQLUtilConstants.ADD_LEAVE);
				pstmt.setString(1, vo.getAcademicyear());
				pstmt.setString(2, vo.getTotalleaves());
				pstmt.setString(3, vo.getPermonth());
				pstmt.setString(4, vo.getCreateuser());
				pstmt.setDouble(5, vo.getsl());
				pstmt.setDouble(6, vo.getPl());
				pstmt.setDouble(7, vo.getCl());
				
				int res = pstmt.executeUpdate();
				if (res > 0) {
					addstatus = "Added successfully";
				}

			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1.getMessage(), e1);
			} finally {
				try {

					if (pstmt != null && !pstmt.isClosed()) {
						pstmt.close();
					}

					if (con != null && (!con.isClosed())) {
						con.close();

					}

				} catch (SQLException sqle) {
					sqle.printStackTrace();
					logger.error(sqle.getMessage(), sqle);
				} catch (Exception e1) {
					e1.printStackTrace();
					logger.error(e1.getMessage(), e1);
				}
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:addLeave Ending");
		return addstatus;
}

	public String updateLeaveBanksDAO(LeaveBankVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:updateLeaveBanksDAO:Starting");

		PreparedStatement pstmt = null;

		String status = null;

		Connection conn = null;

		int result1 = 0;

		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(SQLUtilConstants.UPDATE_LEAVEBANK_DETAILS);
			pstmt.setString(1, vo.getTotalleaves());
			pstmt.setString(2, vo.getPermonth());
			pstmt.setDouble(3, vo.getsl());
			pstmt.setDouble(4, vo.getPl());
			pstmt.setDouble(5, vo.getCl());
			pstmt.setString(6, vo.getAcademicyear());
			pstmt.setString(7, vo.getSno());

			result1 = pstmt.executeUpdate();

			System.out.println("pstmtpstmt" + pstmt);
			System.out.println("result1" + result1);

			if (result1 > 0) {

				status = MessageConstants.UPDATE_LEAVEBANK_SUCCESS;

			} else {
				status = MessageConstants.UPDATE_LEAVEBANK_FAIL;

			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {
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
				+ " Control in LeaveBankDAOIMPL : updateLeaveBanksDAO Ending");
		return status;
	}

	public LeaveBankForm editleavebank(LeaveBankForm aform)

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:editleavebank:Starting");

		PreparedStatement leavebank_pstmt = null;

		ResultSet leavebankrs = null;

		Connection conn = null;
		LeaveBankForm leavebankVO = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			leavebank_pstmt = conn
					.prepareStatement(SQLUtilConstants.EDIT_LEAVEBANK_DETAILS);

			leavebank_pstmt.setString(1, aform.getSno());

			leavebankrs = leavebank_pstmt.executeQuery();

			while (leavebankrs.next()) {
				leavebankVO = new LeaveBankForm();

				leavebankVO.setAcademicyear(leavebankrs
						.getString("AccyearCode"));
				leavebankVO.setTotalleaves(leavebankrs
						.getString("total_leaves"));
				leavebankVO.setPermonth(leavebankrs
						.getString("allowed_per_month"));
				leavebankVO.setCreatedby(leavebankrs.getString("CreatedBy"));
				leavebankVO.setSickleave(leavebankrs.getDouble("SL"));
				leavebankVO.setPaidleave(leavebankrs.getDouble("PL"));
				leavebankVO.setCasualleave(leavebankrs.getDouble("CL"));

			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (leavebankrs != null && (!leavebankrs.isClosed())) {
					leavebankrs.close();
				}
				if (leavebank_pstmt != null && (!leavebank_pstmt.isClosed())) {
					leavebank_pstmt.close();
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :editleavebank Ending");
		return leavebankVO;

	}

	@Override
	public ArrayList<LeaveBankVO> getSearchDetails(String searchTextVal) {

		logger.setLevel(Level.DEBUG);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : getSearchDetails Starting");

		ArrayList<LeaveBankVO> bat_Details = new ArrayList<LeaveBankVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_SEARCH_LEAVEBANKDETAILS);

			pstmt.setString(1, "%" + searchTextVal + "%");
			pstmt.setString(2, "%" + searchTextVal + "%");
			pstmt.setString(3, "%" + searchTextVal + "%");

			rs = pstmt.executeQuery();

			while (rs.next())

			{
				LeaveBankVO bat = new LeaveBankVO();

				bat.setAcademicyear(rs.getString("AccyearCode"));

				bat.setTotalleaves(rs.getString("total_leaves"));

				bat.setPermonth(rs.getString("allowed_per_month"));

				bat_Details.add(bat);

			}

		} catch (SQLException e)

		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		catch (Exception e) {
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
				+ " Control in LeaveBankDAOIMPL : getSearchDetails Ending");

		return bat_Details;

	}

	@Override
	public Boolean deleteLeave(String[] deletelist) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:delete Leave Starting");

		Connection con = null;
		PreparedStatement pstmt = null;

		Boolean deletestatus = false;

		try {
			con = JDBCConnection.getSeparateConnection();
			
			for (int i = 0; i < deletelist.length; i++) {
				pstmt = con.prepareStatement(SQLUtilConstants.DELETE_LEAVEBANK_DETAILS);
				pstmt.setString(1, deletelist[i]);
				int count = pstmt.executeUpdate();
				
				System.out.println("pstmt"+pstmt);
				
				if (count > 0) {
					deletestatus = true;
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (con != null && (!con.isClosed())) {
					con.close();

				}
			} catch (SQLException sql) {
				logger.error(sql.getMessage(), sql);
				sql.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:deleteLeave  Ending");
		return deletestatus;
	
	}

	@Override
	public ArrayList<LeaveRequestVo> getLeaveAprrovedDetails(
			LeaveRequestVo leavevo) {
		System.out.println("DAOIMPL Is Working");


		System.out.println("Leave BAnk DAOIMPL is Working");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL: getLeaveAprrovedDetails : Starting");
		
		PreparedStatement leaveapproved_pstmt = null;

		ResultSet leaveapproved_rs = null;
		
		PreparedStatement leaveapproved_pstmt1 = null;

		ResultSet leaveapproved_rs1 = null;

		ArrayList<LeaveRequestVo> list = new ArrayList<LeaveRequestVo>();

		Connection conn = null;
		
	
		
			try {

				conn = JDBCConnection.getSeparateConnection();
				leaveapproved_pstmt = conn
						.prepareStatement(SQLUtilConstants.LEAVEAPPROVED_DETAILS_TEACHERS);
				
				leaveapproved_pstmt.setString(1, leavevo.getSno1());
				
				
				System.out.println("leaveapproved_pstmt::::"+leaveapproved_pstmt);


				leaveapproved_rs = leaveapproved_pstmt.executeQuery();

				while (leaveapproved_rs.next()) {
					
					LeaveRequestVo vo1 = new LeaveRequestVo();
					
					vo1.setUserid(leaveapproved_rs.getString("name"));
					vo1.setApproveddate(HelperClass.convertDatabaseToUI(leaveapproved_rs.getString("AprovedDate")));
					vo1.setLeavesapproved(leaveapproved_rs.getString("TotalDaysAproved"));
					vo1.setComments(leaveapproved_rs.getString("commennts"));
					vo1.setApprovedstartdate(HelperClass.convertDatabaseToUI(leaveapproved_rs.getString("ApprovedStartDate")));
					vo1.setApprovedenddate(HelperClass.convertDatabaseToUI(leaveapproved_rs.getString("ApprovedEndDate")));
					vo1.setApprovedstartsessionDay(leaveapproved_rs.getString("ApproveSessionStart"));
					vo1.setApprovedendsessionDay(leaveapproved_rs.getString("ApproveSessionEnd"));
					
					list.add(vo1);
				
				}
			

				leaveapproved_pstmt1 = conn
						.prepareStatement(SQLUtilConstants.LEAVEAPPROVED_DETAILS_STUDENTS);
				
				leaveapproved_pstmt1.setString(1, leavevo.getSno1());
				
				
				System.out.println("leaveapproved_pstmt::::"+leaveapproved_pstmt1);


				leaveapproved_rs1 = leaveapproved_pstmt1.executeQuery();

				while (leaveapproved_rs1.next()) {
					
					LeaveRequestVo vo1 = new LeaveRequestVo();
					
					vo1.setUserid(leaveapproved_rs1.getString("name"));
					vo1.setApproveddate(HelperClass.convertDatabaseToUI(leaveapproved_rs1.getString("AprovedDate")));
					vo1.setLeavesapproved(leaveapproved_rs1.getString("TotalDaysAproved"));
					vo1.setComments(leaveapproved_rs1.getString("commennts"));
					vo1.setApprovedstartdate(HelperClass.convertDatabaseToUI(leaveapproved_rs1.getString("ApprovedStartDate")));
					vo1.setApprovedenddate(HelperClass.convertDatabaseToUI(leaveapproved_rs1.getString("ApprovedEndDate")));
					vo1.setApprovedstartsessionDay(leaveapproved_rs1.getString("ApproveSessionStart"));
					vo1.setApprovedendsessionDay(leaveapproved_rs1.getString("ApproveSessionEnd"));
					
					list.add(vo1);
				
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
					if (leaveapproved_rs != null && (!leaveapproved_rs.isClosed())) {
						leaveapproved_rs.close();
					}
					if (leaveapproved_pstmt != null
							&& (!leaveapproved_pstmt.isClosed())) {
						leaveapproved_pstmt.close();
					}
					if (leaveapproved_rs1 != null && (!leaveapproved_rs1.isClosed())) {
						leaveapproved_rs1.close();
					}
					if (leaveapproved_pstmt1 != null
							&& (!leaveapproved_pstmt1.isClosed())) {
						leaveapproved_pstmt1.close();
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
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
			return list;
	}

	public Boolean validAddLeave(String year) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:validAddLeave Starting");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		Boolean status = false;
		try {
			con = JDBCConnection.getSeparateConnection();

			pstmt = con.prepareStatement(SQLUtilConstants.COUNT_LEAVE_YEAR);
			pstmt.setString(1, year);

			res = pstmt.executeQuery();
			int yearCount = 0;
			while (res.next()) {
				yearCount = res.getInt(1);

			}
			if (yearCount > 0) {
				status = true;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (con != null && (!con.isClosed())) {
					con.close();

				}
			} catch (SQLException sql) {
				logger.error(sql.getMessage(), sql);
				sql.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:validAddLeave  Ending");
		return status;
	}

	public ArrayList<LeaveViewDetailsVo> getviewLeaveDetails(String userId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Starting");
		
		PreparedStatement ps_leavebank = null,ps_attendance = null,ps_getemp = null,ps_getcl = null;
		ResultSet rs_leavebank = null,rs_attendance= null,rs_getemp= null,rs_getcl= null;
		
		Connection connection=null;
		
		ArrayList<LeaveViewDetailsVo> leaveviewlist = new ArrayList<LeaveViewDetailsVo>();
		
		try{

			boolean flag=true;
			connection=JDBCConnection.getSeparateConnection();

			ps_getemp=connection.prepareStatement(SQLUtilConstants.GET_EMP_DOJ_YEAR);
			ps_getemp.setString(1,userId);
			System.out.println("ps_getemp is "+ps_getemp);
			/*int currentYear=HelperClass.getCurrentYear();*/

			/*ArrayList<String> yearCode=new ArrayList<String>();*/

			rs_getemp = ps_getemp.executeQuery();
			if (rs_getemp.next()) {
				String 	empyear= rs_getemp.getString("acadamic_year");
				String  empyearcode = rs_getemp.getString("acadamic_id");
				String month=rs_getemp.getString("month");

				ps_leavebank=connection.prepareStatement(SQLUtilConstants.GET_SL_LEAVES);
				ps_leavebank.setString(1, empyearcode);
				ps_leavebank.setString(2, userId);
				System.out.println("ps_leavebank"+ps_leavebank);
				rs_leavebank=ps_leavebank.executeQuery();

				while (rs_leavebank.next()) {

					LeaveViewDetailsVo leaveViewDetailsVo=new LeaveViewDetailsVo();

					leaveViewDetailsVo.setAccyear(empyear);
					leaveViewDetailsVo.setLeavetype(MessageConstants.LEAVE_TYPE_SL);

					double totopenings=rs_leavebank.getDouble("slopenbal");
					
					double monthopeningd=totopenings/12;

					if(flag){
						double afetrdojopenings=monthopeningd *(12-Integer.parseInt(month));
						leaveViewDetailsVo.setOpeningbal(afetrdojopenings);
						leaveViewDetailsVo.setClosingbal(afetrdojopenings-rs_leavebank.getDouble("slconsumebal"));

					}else{

						leaveViewDetailsVo.setOpeningbal(rs_leavebank.getDouble("slopenbal"));
						leaveViewDetailsVo.setClosingbal(rs_leavebank.getDouble("slopenbal")-rs_leavebank.getDouble("slconsumebal"));
					}

					leaveViewDetailsVo.setConsumebal(rs_leavebank.getDouble("slconsumebal"));


					leaveviewlist.add(leaveViewDetailsVo);

				}

				ps_attendance=connection.prepareStatement(SQLUtilConstants.GET_PL_LEAVES);
				ps_attendance.setString(1,empyearcode);
				ps_attendance.setString(2,userId);
				System.out.println("ps_attendance"+ps_attendance);
				rs_attendance=ps_attendance.executeQuery();

				while (rs_attendance.next()) {

					LeaveViewDetailsVo leaveViewDetailsVo=new LeaveViewDetailsVo();

					leaveViewDetailsVo.setAccyear("");
					leaveViewDetailsVo.setLeavetype(MessageConstants.LEAVE_TYPE_PL);

					double totopenings=rs_attendance.getDouble("plopenbal");
					double monthopenings=totopenings/12;

					if(flag){

						double afetrdojopenings=monthopenings *(12-Integer.parseInt(month));
						leaveViewDetailsVo.setOpeningbal(afetrdojopenings);
						leaveViewDetailsVo.setClosingbal(afetrdojopenings-rs_attendance.getDouble("plconsumebal"));

					}else{

						leaveViewDetailsVo.setOpeningbal(rs_attendance.getDouble("plopenbal"));
						leaveViewDetailsVo.setClosingbal(rs_attendance.getDouble("plopenbal")-rs_attendance.getDouble("plconsumebal"));
					}

					leaveViewDetailsVo.setConsumebal(rs_attendance.getDouble("plconsumebal"));

					leaveviewlist.add(leaveViewDetailsVo);

				}


				ps_getcl=connection.prepareStatement(SQLUtilConstants.GET_CL_LEAVES);
				ps_getcl.setString(1,empyearcode);
				ps_getcl.setString(2,userId);
				System.out.println("ps_getcl"+ps_getcl);
				rs_getcl=ps_getcl.executeQuery();

				while (rs_getcl.next()) {

					LeaveViewDetailsVo leaveViewDetailsVo=new LeaveViewDetailsVo();

					leaveViewDetailsVo.setAccyear("");
					leaveViewDetailsVo.setLeavetype(MessageConstants.LEAVE_TYPE_CL);

					double totopenings=rs_getcl.getDouble("clopenbal");
					double monthopenings=totopenings/12;

					if(flag){

						double afetrdojopenings=monthopenings *(12-Integer.parseInt(month));
						leaveViewDetailsVo.setOpeningbal(afetrdojopenings);
						leaveViewDetailsVo.setClosingbal(afetrdojopenings-rs_getcl.getDouble("clconsumebal"));

					}else{

						leaveViewDetailsVo.setOpeningbal(rs_getcl.getDouble("clopenbal"));
						leaveViewDetailsVo.setClosingbal(rs_getcl.getDouble("clopenbal")-rs_getcl.getDouble("clconsumebal"));
					}

					leaveViewDetailsVo.setConsumebal(rs_getcl.getDouble("clconsumebal"));

					leaveviewlist.add(leaveViewDetailsVo);

				}

				flag=false;
			}
			else{
				
			}
		} catch (SQLException sqle) {
		       sqle.printStackTrace();
		        logger.error(sqle.getMessage(),sqle);
	           } catch (Exception e1) {
		        e1.printStackTrace();
		         logger.error(e1.getMessage(),e1);
	           } finally {
		    try {
	 
		    	
		    	
		    	
		    if (rs_attendance != null && (!rs_attendance.isClosed())) {

					rs_attendance.close();
			}
			if (rs_leavebank != null && (!rs_leavebank.isClosed())) {

				rs_leavebank.close();
			}
			if (rs_getemp != null && (!rs_getemp.isClosed())) {

				rs_getemp.close();
			}
		
			if (ps_attendance != null && (!ps_attendance.isClosed())) {

				ps_attendance.close();
			}
			if (ps_leavebank != null && (!ps_leavebank.isClosed())) {

				ps_leavebank.close();
			}
			if (ps_getemp != null && (!ps_getemp.isClosed())) {

				ps_getemp.close();
			}
			if (connection != null && (!connection.isClosed())) {

				connection.close();
			}
			
			
		    } catch (SQLException sqle) {
			         sqle.printStackTrace();
		        	logger.error(sqle.getMessage(),sqle);
	            	} catch (Exception e1) {
		           	e1.printStackTrace();
			        logger.error(e1.getMessage(),e1);
		       }
	         }
		

		
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Ending");

		return leaveviewlist;
}


	public String addLeavesCategory(String[] categorynames, String[] shortnames, String[] noofleaves, String[] catId, String accyear, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: addLeavesCategory : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
	
		String status = null;
		String teaId = null;
		String doj = null;
		String accyearStart = null;
		int count =0;
		String accEndDate=null;
		int noofmonthsInt = 0;
		String teacherType= null;
		int month = 0;
		int day = 0;
		String yearFace="";
		
		List<String> LeaveName=new ArrayList<String>();
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			
			PreparedStatement accyearpstmt = conn.prepareStatement("select startDate,endDate,acadamic_year from campus_acadamicyear where acadamic_id = ?");
			accyearpstmt.setString(1,accyear);
			ResultSet accyearrs = accyearpstmt.executeQuery();
			while(accyearrs.next()){
				accyearStart = accyearrs.getString("startDate");
				accEndDate = accyearrs.getString("endDate");
				yearFace = accyearrs.getString("acadamic_year").split("-")[0];
			}
			
			pstmt = conn.prepareStatement("insert into campus_new_leave_bank(Leave_ID,Leave_name,No_Of_Leaves,ShortName,Cat_Id,Accy_Id,Loc_ID,year)values(?,?,?,?,?,?,?,?)");
				
				for(int i=0;i<categorynames.length;i++){
					
					String categoryNames = StringUtils.capitalize(categorynames[i]);
					LeaveName.add(categoryNames);
					pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_new_leave_bank"));
					pstmt.setString(2, categoryNames);
					pstmt.setString(3, noofleaves[i]);
					pstmt.setString(4, shortnames[i]);
					pstmt.setString(5, catId[i]);
					pstmt.setString(6, accyear);
					pstmt.setString(7, location);
					pstmt.setString(8, yearFace);
					count = pstmt.executeUpdate();
				}
			
				if(count > 0){
					
					status = "Added Successfully";
				}
				
			PreparedStatement teacherpstmt = conn.prepareStatement("select TeacherID,teachingType,dateofJoining,Loc_ID from campus_teachers where Loc_ID = ?");
			teacherpstmt.setString(1, location);
			
			ResultSet teacherrs = teacherpstmt.executeQuery();
			while(teacherrs.next()){
				
				leaveBankInsert(teacherrs.getString("TeacherID"), teacherrs.getString("dateofJoining"), location, teacherrs.getString("teachingType"), accyear, categorynames, noofleaves, conn);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :addLeavesCategory Ending");
		return status;
	}

	public ArrayList<LeaveBankVO> getleaveCatList() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getleaveCatList : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select * from campus_category_master order by length(Cat_Id),Cat_Id asc");
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO obj = new LeaveBankVO();
				obj.setCat_id(rs.getString("Cat_Id"));
				obj.setCat_name(rs.getString("Category_Name"));
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getleaveCatList Ending");
		return list;
	}


	public ArrayList<LeaveBankVO> getleavetypeDetails(String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getleavetypeDetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		int count =0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_LEAVE_NAME_DETAILS);
			pstmt.setString(1,academic_year);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count ++;
				LeaveBankVO obj = new LeaveBankVO();
				obj.setCount(count);
				obj.setAcademicyear(rs.getString("acadamic_year"));
				obj.setLocationName(rs.getString("Location_Name"));
				obj.setAccyearcode(rs.getString("Accy_Id"));
				obj.setLocId(rs.getString("Loc_ID"));
				list.add(obj);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getleavetypeDetails Ending");
		
		return list;
	}


	public ArrayList<LeaveBankVO> editleavetypes(String accyear, String loc) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: editleavetypes : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select Leave_ID,Cat_Id,Leave_name,No_Of_Leaves,ShortName,Accy_Id,Loc_ID from campus_new_leave_bank where Accy_Id =? and Loc_ID=?");
			pstmt.setString(1, accyear);
			pstmt.setString(2, loc);
			
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO obj = new LeaveBankVO();
				obj.setLeaveName(rs.getString("Leave_name"));
				obj.setShortName(rs.getString("ShortName"));
				obj.setNoofleaves(rs.getString("No_Of_Leaves"));
				obj.setLeaveID(rs.getString("Leave_ID"));
				obj.setAccyear(accyear);
				obj.setLocId(loc);
				
				pstmt1 = conn.prepareStatement("select Cat_Id,Category_Name from campus_category_master where Cat_Id =?");
				pstmt1.setString(1, rs.getString("Cat_Id"));
				rs1= pstmt1.executeQuery();
				while(rs1.next()){
					obj.setCat_id(rs1.getString("Cat_Id"));
					obj.setCat_name(rs1.getString("Category_Name"));
					
				}
				pstmt1.close();
				rs1.close();
				list.add(obj);
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
			finally{
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
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :editleavetypes Ending");
		return list;
	}


	public String editleavetypes(String accyear, String loc, String category) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String status = null;
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select count(*) Leave_name from campus_new_leave_bank where Leave_name =? and Accy_Id=? and Loc_ID =?");
			pstmt.setString(1, category);
			pstmt.setString(2,accyear);
			pstmt.setString(3,loc);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				count = rs.getInt(1);
			}
			
			if(count > 0){
				status = "true";
			}
			else {
				status = "false";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return status;
	}


	public ArrayList<LeaveBankVO> getSearchleavetypeDetails(String searchTearm, String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getSearchleavetypeDetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int count = 0;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			
			System.out.println("DAOIMPL");
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_LEAVENAME_SEARCH);
			
			pstmt.setString(1,academic_year);
			pstmt.setString(2, "%"+searchTearm+"%");
			pstmt.setString(3, "%"+searchTearm+"%");
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				count++;
				LeaveBankVO obj = new LeaveBankVO();
				obj.setCount(count);
				obj.setAcademicyear(rs.getString("acadamic_year"));
				obj.setLocationName(rs.getString("Location_Name"));
				obj.setAccyearcode(rs.getString("Accy_Id"));
				obj.setLocId(rs.getString("Loc_ID"));
				list.add(obj);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getSearchleavetypeDetails Ending");
		return list;
	}


	public String updateLeavesCategory(String[] hiddenLEaveIdArray, String[] categorynames, String[] shortnames,
			String[] noofleaves, String[] catId, String accyear, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getSearchleavetypeDetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String status = null;
		List<LocationVO> locationList = new ArrayList<LocationVO>();
		int count =0;
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("update campus_new_leave_bank set Leave_name = ?,No_Of_Leaves = ?,ShortName = ?,Cat_Id = ?,Accy_Id= ?,Loc_ID = ? where Leave_ID = ?");
			
			if(location.equalsIgnoreCase("%%")){
				locationList = new  LocationBD().getLocationDetails();
				System.out.println(locationList.size());
			}

			if(locationList.size()!=0){
			
			for(int i=0;i<categorynames.length;i++){
				
			for(int j=0;j<locationList.size();j++){
				
				pstmt.setString(1, categorynames[i]);
				pstmt.setString(2, noofleaves[i]);
				pstmt.setString(3, shortnames[i]);
				pstmt.setString(4, catId[i]);
				pstmt.setString(5, accyear);
				pstmt.setString(6, locationList.get(j).getLocation_id());
				pstmt.setString(7,hiddenLEaveIdArray[i]);
				count = pstmt.executeUpdate();
				}
			}	
		}
			else{
				
				for(int i=0;i<hiddenLEaveIdArray.length;i++){
					
					pstmt.setString(1, categorynames[i]);
					pstmt.setString(2, noofleaves[i]);
					pstmt.setString(3, shortnames[i]);
					pstmt.setString(4, catId[i]);
					pstmt.setString(5, accyear);
					pstmt.setString(6, location);
					pstmt.setString(7,hiddenLEaveIdArray[i]);
					count = pstmt.executeUpdate();
				}
			}
			
			if(count > 0){
				
				status = "Updated Successfully";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return status;
	}


	public ArrayList<LeaveBankVO> getaccLocCatList(String accyear, String loc) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select acadamic_year,Location_Name from campus_acadamicyear,campus_location where acadamic_id = ? and Location_Id = ?");
			pstmt.setString(1, accyear);
			pstmt.setString(2,loc);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				LeaveBankVO obj = new LeaveBankVO();
				obj.setAcademicyear(rs.getString("acadamic_year"));
				obj.setLocationName(rs.getString("Location_Name"));
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return list;
	}


	public String checkDuplicacy(String accyear, String loc) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String status = null;
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select count(*) Leave_name from campus_new_leave_bank where Accy_Id=? and Loc_ID =?");
			pstmt.setString(1,accyear);
			pstmt.setString(2,loc);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				count = rs.getInt(1);
			}
			
			if(count > 0){
				status = "true";
			}
			else {
				status = "false";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return status;
	}


	public ArrayList<LeaveBankVO> getleavenamesList(String academic_year) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getleavenamesList : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select Leave_ID,Leave_name from campus_new_leave_bank where Accy_Id=?");
			pstmt.setString(1, academic_year);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO obj = new LeaveBankVO();
				obj.setLeaveID(rs.getString("Leave_ID"));
				obj.setLeaveName(rs.getString("Leave_name"));
				list.add(obj);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getleavenamesList Ending");
		return list;
	}


	public ArrayList<LeaveBankVO> getleaveusertype(String parentid, String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select tnlb.Leave_Type,nlb.Leave_name from campus_new_leave_bank nlb,campus_teacher_new_leave_bank_details tnlb where nlb.Leave_ID = tnlb.Leave_Type and EmpId=? and AccYearCode = ?");
			pstmt.setString(1,parentid);
			pstmt.setString(2, academic_year);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO obj = new LeaveBankVO();
				obj.setLeaveID(rs.getString("Leave_Type"));
				obj.setLeaveName(rs.getString("Leave_name"));
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return list;
	}


	public ArrayList<LeaveBankVO> getleaveBalance(String parentid, String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getleaveBalance : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select EmpId,Leave_Name,Leave_Type,total_available,total_consumed,total_avaliable_leaves as tot_year,total_consumed,total_avaliable_leaves from campus_teacher_new_leave_bank_details where AccYearCode = ? and EmpId = ?");
			pstmt.setString(1, academic_year);
			pstmt.setString(2,parentid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO obj = new LeaveBankVO();
				obj.setLeavetypeName(rs.getString("Leave_Name"));
				obj.setLeaveID(rs.getString("Leave_Type"));
				obj.setTotalleaves(rs.getString("total_available"));
				obj.setConsumedLeave(rs.getString("total_consumed"));
				double tot_leaves = Double.parseDouble(rs.getString("total_available"));
				double tot_available =tot_leaves- rs.getDouble("total_consumed");
				obj.setAvailable_leave(tot_available);
				obj.setUserID(rs.getString("EmpId"));
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getleaveBalance Ending");
		return list;
	}


	public LeaveCalculation getLeaveCalculation(String parentid, String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getLeaveCalculation : Starting");
	
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
	
		String accyyearStart = null;
		String leavename = null;
		Double total_available = 0.0;
		double tot_leave = 0.0;
		double leave_applicable = 0.0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select startDate,endDate from campus_acadamicyear where acadamic_id = ?");
			pstmt.setString(1, academic_year);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				accyyearStart = rs.getString("startDate");
			}
			
			
			String [] doj = accyyearStart.split("-");
			
			String [] currdate = HelperClass.getCurrentSqlDate().toString().split("-");

			int noofmonths=(Integer.parseInt(currdate[0])-Integer.parseInt(doj[0]))*12;
			
			noofmonths=noofmonths+((12-(Integer.parseInt(doj[1])-1)+(Integer.parseInt(currdate[1])-12)));
			
			double leaveDivisor = 12/noofmonths;
			
			PreparedStatement casualpstmt = conn.prepareStatement("select nlb.Leave_name,ntlb.Leave_Type,ntlb.total_consumed,ntlb.total_available from campus_teacher_new_leave_bank_details ntlb,campus_new_leave_bank nlb where nlb.Leave_ID = ntlb.Leave_Type and  EmpId = ?");
			casualpstmt.setString(1,parentid);
			System.out.println(casualpstmt);
			ResultSet leavecal = casualpstmt.executeQuery();
			while(leavecal.next()){
				
				leavename = leavecal.getString("Leave_name");
				total_available = Double.parseDouble(leavecal.getString("total_available"));
				if(leavename.startsWith("Casual")){
					
					tot_leave = total_available/leaveDivisor;
					
					if(tot_leave % 0.5 == 0){
						leave_applicable = tot_leave;
					}else{
						leave_applicable = tot_leave-0.25;
					}
					
				}
				System.out.println(leave_applicable);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return null;
	}


	public LeaveCalculation getNewLeaveCalculation(String parentid, String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getNewLeaveCalculation : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int month = 0;
		String monthName = null;
		LeaveCalculation leaveValue = new LeaveCalculation();
		double leaveCount =0;
		double leavecountSum =0.0;
		try{
			
			String [] currdate = HelperClass.getCurrentSqlDate().toString().split("-");
			month = Integer.parseInt(currdate[1]);
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select nlb.Leave_name,January,February,March,April,May,June,July,August,September,October,November,December,total_available,Leave_Type from campus_new_leave_bank nlb,campus_teacher_new_leave_bank_details where nlb.Leave_ID = Leave_Type and AccYearCode =? and EmpId =?");
			pstmt.setString(1,academic_year);
			pstmt.setString(2,parentid);
			rs = pstmt.executeQuery();
			while(rs.next()){

				double month1 = Double.parseDouble(rs.getString("January"));
				double month2 = Double.parseDouble(rs.getString("February"));
				double month3 = Double.parseDouble(rs.getString("March"));
				double month4 = Double.parseDouble(rs.getString("April"));
				double month5 = Double.parseDouble(rs.getString("May"));
				double month6 = Double.parseDouble(rs.getString("June"));
				double month7 = Double.parseDouble(rs.getString("July"));
				double month8 = Double.parseDouble(rs.getString("August"));
				double month9 = Double.parseDouble(rs.getString("September"));
				double month10 = Double.parseDouble(rs.getString("October"));
				double month11 = Double.parseDouble(rs.getString("November"));
				double month12 = Double.parseDouble(rs.getString("December"));
				String leaveType = rs.getString("Leave_name");
				leaveValue.setLeaveName(rs.getString("Leave_name"));
				leaveValue.setLeaveId(rs.getString("Leave_Type"));
				if(leaveType.startsWith("Casual")){
					 if(month == 04){
						monthName = "April";
						leaveCount =month4;
						System.out.println("leaveCount"+leaveCount);
						leavecountSum = leavecountSum+month4;
						leaveValue.setLeaveCount(leaveCount);
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setCurrentMonCount(month);
						leaveValue.setMonthName(monthName);
					}else if(month == 05){
						monthName = "May";
						leaveCount =month5;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month5+month4;
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setCurrentMonCount(month);
						leaveValue.setMonthName(monthName);
					}else if(month == 06){
						monthName = "June";
						leaveCount = month6;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month6+month5+month4;
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setCurrentMonCount(month);
						leaveValue.setMonthName(monthName);
					}else if(month == 07){
						monthName = "July";
						leaveCount = month7;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month7+month6+month5+month4;
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setCurrentMonCount(month);
						leaveValue.setMonthName(monthName);
					}else if(month == 8){
						monthName = "August";
						leaveCount = month8;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month8+month7+month6+month5+month4;
						leaveValue.setCurrentMonCount(month);
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setMonthName(monthName);
					}else if(month == 9){
						monthName = "September";
						leaveCount = month9;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month9+month8+month7+month6+month5+month4;
						leaveValue.setCurrentMonCount(month);
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setMonthName(monthName);
					}else if(month == 10){
						monthName = "October";
						leaveCount = month10;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month10+month9+month8+month7+month6+month5+month4;
						leaveValue.setCurrentMonCount(month);
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setMonthName(monthName);
					}else if(month == 11){
						monthName = "November";
						leaveCount = month11;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month11+month10+month9+month8+month7+month6+month5+month4;
						leaveValue.setCurrentMonCount(month);
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setMonthName(monthName);
					}else if(month == 12){
						monthName = "December";
						leaveCount = month12;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month12+month11+month10+month9+month8+month7+month6+month5+month4;
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setCurrentMonCount(month);
						leaveValue.setMonthName(monthName);
					}
					else if(month == 01){
							monthName = "January";
							leaveCount = month1;
							leaveValue.setLeaveCount(leaveCount);
							leavecountSum = leavecountSum+month1+month12+month11+month10+month9+month8+month7+month6+month5+month4;
							leaveValue.setCurrentMonCount(month);
							leaveValue.setLeaveCountSum(leavecountSum);
							leaveValue.setMonthName(monthName);
				   }else if(month == 02){
							monthName = "February";
							leaveCount = month2;
							leaveValue.setLeaveCount(leaveCount);
							leavecountSum = leavecountSum+month2+month1+month12+month11+month10+month9+month8+month7+month6+month5+month4;
							leaveValue.setCurrentMonCount(month);
							leaveValue.setLeaveCountSum(leavecountSum);
							leaveValue.setMonthName(monthName);
					}else if(month == 03){
							monthName = "March";
							leaveCount = month3;
							leaveValue.setLeaveCount(leaveCount);
							leavecountSum = leavecountSum+month3+month2+month1+month12+month11+month10+month9+month8+month7+month6+month5+month4;
							leaveValue.setLeaveCountSum(leavecountSum);
							leaveValue.setCurrentMonCount(month);
							leaveValue.setMonthName(monthName);
						}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getNewLeaveCalculation Ending");
		return leaveValue;
	}


	public ArrayList<LeaveCalculation> checkLeaveCount(LeaveCalculation obj) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: checkLeaveCount : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int month = 0;
		String monthName = null;
		double leaveCount =0;
		double leavecountSum =0.0;
		double tot_leave = 0.0;
		ArrayList<LeaveCalculation> leavelist = new ArrayList<LeaveCalculation>();
		try{
			
			String[] stDate = (obj.getLeaveStdate()).split("-");
			/*int stdate =Integer.parseInt(stDate[0]);*/
			month = Integer.parseInt(stDate[1]);
			
			conn = JDBCConnection.getSeparateConnection();
			
			PreparedStatement leaveConsumed = conn.prepareStatement("select total_consumed from campus_teacher_new_leave_bank_details where AccYearCode = ? and EmpId=? and  Leave_Type = ?");
			leaveConsumed.setString(1,obj.getAccyear());
			leaveConsumed.setString(2,obj.getUserid());
			leaveConsumed.setString(3,obj.getLeavetype());
			System.out.println(leaveConsumed);
			ResultSet rsleave = leaveConsumed.executeQuery();
			while(rsleave.next()){
				tot_leave = rsleave.getDouble("total_consumed");
			}
			System.out.println("tot_leave :"+tot_leave);
			pstmt = conn.prepareStatement("select nlb.Leave_name,January,February,March,April,May,June,July,August,September,October,November,December,total_available,Leave_Type from campus_new_leave_bank nlb,campus_teacher_new_leave_bank_details where nlb.Leave_ID = Leave_Type and AccYearCode =? and EmpId =? and Leave_Type=?");
			pstmt.setString(1,obj.getAccyear());
			pstmt.setString(2,obj.getUserid());
			pstmt.setString(3, obj.getLeavetype());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveCalculation list = new LeaveCalculation();
				double month1 = Double.parseDouble(rs.getString("January"));
				double month2 = Double.parseDouble(rs.getString("February"));
				double month3 = Double.parseDouble(rs.getString("March"));
				double month4 = Double.parseDouble(rs.getString("April"));
				double month5 = Double.parseDouble(rs.getString("May"));
				double month6 = Double.parseDouble(rs.getString("June"));
				double month7 = Double.parseDouble(rs.getString("July"));
				double month8 = Double.parseDouble(rs.getString("August"));
				double month9 = Double.parseDouble(rs.getString("September"));
				double month10 = Double.parseDouble(rs.getString("October"));
				double month11 = Double.parseDouble(rs.getString("November"));
				double month12 = Double.parseDouble(rs.getString("December"));
				String leaveType = rs.getString("Leave_name");
				
			 if(month == 01){
					monthName = "January";
					leaveCount = month1;
					leavecountSum = leavecountSum+month1;
					list.setLeaveCount(leaveCount);
					list.setLeaveCountSum(leavecountSum - tot_leave);
		   }else if(month == 02){
					monthName = "February";
					leaveCount = month2;
					leavecountSum = leavecountSum+month2+month1;
					list.setLeaveCount(leaveCount);
					list.setLeaveCountSum(leavecountSum - tot_leave);
			}else if(month == 03){
					monthName = "March";
					leaveCount = month3;
					leavecountSum = leavecountSum+month3+month2+month1;
					list.setLeaveCount(leaveCount);
					list.setLeaveCountSum(leavecountSum - tot_leave);
				}
				else if(month == 04){
						monthName = "April";
						leaveCount =month4;
						leavecountSum = leavecountSum+month3+month2+month1+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
						
					}else if(month == 05){
						monthName = "May";
						leaveCount =month5;
						leavecountSum = leavecountSum+month3+month2+month1+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
						System.out.println("leavecountSum"+(leavecountSum - tot_leave));
						
					}else if(month == 06){
						monthName = "June";
						leaveCount = month6;
						leavecountSum = leavecountSum+month3+month2+month1+month6+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
						
					}else if(month == 07){
						monthName = "July";
						leaveCount = month7;
						leavecountSum = leavecountSum+month3+month2+month1+month7+month6+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
						
					}else if(month == 8){
						monthName = "August";
						leaveCount = month8;
						leavecountSum = leavecountSum+month3+month2+month1+month8+month7+month6+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
						
					}else if(month == 9){
						monthName = "September";
						leaveCount = month9;
						leavecountSum = leavecountSum+month3+month2+month1+month9+month8+month7+month6+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
						
					}else if(month == 10){
						monthName = "October";
						leaveCount = month10;
						leavecountSum = leavecountSum+month3+month2+month1+month10+month9+month8+month7+month6+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
						
					}else if(month == 11){
						monthName = "November";
						leaveCount = month11;
						leavecountSum = leavecountSum+month3+month2+month1+month11+month10+month9+month8+month7+month6+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
					}else if(month == 12){
						monthName = "December";
						leaveCount = month12;
						leavecountSum = leavecountSum+month3+month2+month1+month12+month11+month10+month9+month8+month7+month6+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
					}
					
					leavelist.add(list);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		System.out.println(leavecountSum);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :checkLeaveCount Ending");
		return leavelist;
	}

	public String checkDateDuplicacy(String startDate, String toDate, String leavetype, String parentid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int status = 0;
		String result = null;
		int leaveStatus = 0;
		try{
			 conn = JDBCConnection.getSeparateConnection();
			 pstmt = conn.prepareStatement("select count(*) from campus_teachers_leave_request where RequestedBy = ? and LeaveType = ? and StartDate = ? and EndDate = ?");
			 pstmt.setString(1,parentid);
			 pstmt.setString(2,leavetype);
			 pstmt.setString(3,HelperClass.convertUIToDatabase(startDate));
			 pstmt.setString(4,HelperClass.convertUIToDatabase(toDate));
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				 status = rs.getInt(1);
			 }
			 if(status > 0){
				
				  PreparedStatement leavestatus = conn.prepareStatement("select count(*) from campus_teachers_leave_request where RequestedBy = ? and LeaveType = ? and StartDate = ? and EndDate = ? and LeaveStatus = 'Pending'");
				 leavestatus.setString(1,parentid);
				 leavestatus.setString(2,leavetype);
				 leavestatus.setString(3,HelperClass.convertUIToDatabase(startDate));
				 leavestatus.setString(4,HelperClass.convertUIToDatabase(toDate));
				 ResultSet leavers = leavestatus.executeQuery();
				 while(leavers.next()){
					 leaveStatus = leavers.getInt(1);
				 }
				 if(leaveStatus > 0){
					 result = "Leave applied pending for approve";
				 }
				 else{
					 int leavestatuss =0;
					 PreparedStatement leavestatus1 = conn.prepareStatement("select count(*) from campus_teachers_leave_request where RequestedBy = ? and LeaveType = ? and StartDate = ? and EndDate = ? and LeaveStatus = 'Approved'");
					 leavestatus1.setString(1,parentid);
					 leavestatus1.setString(2,leavetype);
					 leavestatus1.setString(3,HelperClass.convertUIToDatabase(startDate));
					 leavestatus1.setString(4,HelperClass.convertUIToDatabase(toDate));
					 ResultSet leavers1 = leavestatus1.executeQuery();
					 while(leavers1.next()){
						 leavestatuss = leavers1.getInt(1);
					 }
					 if(leavestatuss > 0){
					 result = "You already applied leave";
				 }
			 }
			 }
			 else{
				 result = "false";
			 }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return result;
	}


	public ArrayList<LeaveViewDetailsVo> getviewNewLeaveDetails(String trim, String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewNewLeaveDetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		ArrayList<LeaveViewDetailsVo> list = new ArrayList<LeaveViewDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select total_available,total_consumed,total_avaliable_leaves,Leave_Type,Leave_Name,acadamic_year from campus_teacher_new_leave_bank_details,campus_acadamicyear where acadamic_id = AccYearCode and AccYearCode = ? and EmpId = ? "); 
			pstmt.setString(1,academic_year);
			pstmt.setString(2,trim);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveViewDetailsVo obj = new LeaveViewDetailsVo();
				obj.setAccyear(rs.getString("acadamic_year"));
				
				obj.setConsumebal(rs.getDouble("total_consumed"));
				obj.setTotal_leave_year(rs.getString("total_available"));
				
				double tot_leaves = Double.parseDouble(rs.getString("total_available"));
				double tot_available =tot_leaves- rs.getDouble("total_consumed");
				obj.setTotalleaves(tot_available);
				
				obj.setLeavetype(rs.getString("Leave_Name"));
				obj.setLeaveID(rs.getString("Leave_Type"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getviewNewLeaveDetails Ending");
		return list;
	}



	public ArrayList<LeaveBankVO> getLeaveTypes(String academicYear, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getLeaveTypes : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_LEAVE_TYPES);
			pstmt.setString(1, academicYear);
			pstmt.setString(2,location);
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO obj = new LeaveBankVO();
				obj.setLeaveID(rs.getString("Leave_ID"));
				obj.setLeaveName(rs.getString("Leave_name"));
				obj.setNoofleaves(rs.getString("No_Of_Leaves"));
				obj.setShortName(rs.getString("ShortName"));
				obj.setCat_id(rs.getString("Cat_Id"));
				obj.setAcademicyear(rs.getString("Accy_Id"));
				obj.setLocId(rs.getString("Loc_ID"));
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return list;
	}



	public String checkMonthleave(String academic_year, String parentid, String fromDate, String leavetype) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Starting");
		
	/*	Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;*/
		
		try{
			
		/*	conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("");
			*/
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return null;
	}


	public List<LeaveBankVO> getNoOfLeave(String academicYear, String location,String leaveType) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getNoOfLeave : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_NO_OF_LEAVES);
			pstmt.setString(1, academicYear);
			pstmt.setString(2,location);
			pstmt.setString(3, leaveType);
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO leaveBankVo = new LeaveBankVO();
				leaveBankVo.setNoofleaves(rs.getString("No_Of_Leaves"));
				list.add(leaveBankVo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return list;
	}
	
	
int leaveBankInsert(String teaId,String doj,String location,String teacherType,String accyear,String categorynames[],String noofleaves[],Connection conn) {
int leaveExcecte=0;
try {	
int fullleavestartmonth=0;
		int halfpaidleavestartmonth=0;
		
		int leaveyear=0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateofJoining = sdf.parse(doj);
        
        
      
        PreparedStatement leavidpstmt = conn.prepareStatement("select year from campus_new_leave_bank where  Loc_ID=? and Accy_Id =?");
    	
    	leavidpstmt.setString(1,location);
    	leavidpstmt.setString(2,accyear);
    	ResultSet leaveidrs = leavidpstmt.executeQuery();
    	while(leaveidrs.next()){
    		leaveyear = leaveidrs.getInt("year");
    	}
    	leavidpstmt.close();
    	leaveidrs.close();
    	Date startingyear=sdf.parse(leaveyear+"-01-01");
        int diffyear=HelperClass.getDiffYears(dateofJoining, startingyear);
        
        String [] tdoj = doj.split("-");
        int dyear=Integer.parseInt(tdoj[0])	;
        int dmonth = Integer.parseInt(tdoj[1])-1;
        int	dday = Integer.parseInt(tdoj[2]);
        if(diffyear<2) {
        	 Calendar c1 = Calendar.getInstance();
		 	    c1.set(dyear,dmonth ,dday); // Setting Year
		 	    c1.add(Calendar.YEAR,2);   // adding years 
		 	   fullleavestartmonth=Integer.parseInt(sdf.format(c1.getTime()).split("-")[1]);
        }
        if(diffyear<1) {
        	 Calendar c2 = Calendar.getInstance();
		 	    c2.set(dyear,dmonth ,dday); // Setting Year
		 	    c2.add(Calendar.YEAR,1);   // adding years 
		 	   halfpaidleavestartmonth=Integer.parseInt(sdf.format(c2.getTime()).split("-")[1]);
        }
      
        
        if(teacherType.equalsIgnoreCase("TEACHING") || teacherType.equalsIgnoreCase("NON TEACHING") || teacherType.equalsIgnoreCase("MISC")){
        	
        	PreparedStatement insteacherLeave = conn.prepareStatement("insert into campus_teacher_new_leave_bank_details(AccYearCode,EmpId,Leave_Type,total_available,January,February,March,April,May,June,July,August,September,October,November,December,Date_Of_Join,LOC_Id,Leave_Name)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        	for(int i=0;i<categorynames.length;i++){
        	
        	String categoryNames = StringUtils.upperCase(categorynames[i]);
        	double no_of_leaves = Double.parseDouble(noofleaves[i]);
        	System.out.println("categorynames :: "+ categorynames[i]);
        	
        
        	if(!(categoryNames.contains("EARN"))){
        		insteacherLeave.setString(1,accyear);
        		insteacherLeave.setString(2,teaId);
        		String leavetypes = null;
        		 leavidpstmt = conn.prepareStatement("select Leave_ID from campus_new_leave_bank where Leave_name = ? and Loc_ID=? and Accy_Id =?");
        		leavidpstmt.setString(1,categoryNames);
        		leavidpstmt.setString(2,location);
        		leavidpstmt.setString(3,accyear);
        		
        		
        		System.out.println("sjkdfhjksdfhjksdf="+leavidpstmt);
        		leaveidrs = leavidpstmt.executeQuery();
        		while(leaveidrs.next()){
        		leavetypes = leaveidrs.getString("Leave_ID");
        		}
        		leavidpstmt.close();
        		leaveidrs.close();
        	
        		insteacherLeave.setString(3,leavetypes);
        		 DecimalFormat value = new DecimalFormat("#.#");
        		if(categoryNames.startsWith("CASUAL")){
        			if(diffyear<2 && diffyear>1) {
        				double no_of_leave_first=(10/12)*fullleavestartmonth;
        				double no_of_leave_second=(no_of_leaves/12)*(12-fullleavestartmonth);
        				double no_of_leave_second_perMonth=(no_of_leaves/12);
        				double no_of_leave_first_per_month=no_of_leave_first/fullleavestartmonth;
        				insteacherLeave.setString(4,Double.toString((Math.round(no_of_leave_first+no_of_leave_second))));
        				for(int c=5;c<fullleavestartmonth+5;c++) {
        					double montlhyLeave=no_of_leave_first_per_month;
        					double flag=(montlhyLeave % 0.5);
        					if(flag!=0) {
        						no_of_leave_first_per_month=.833;
        						montlhyLeave=montlhyLeave-flag;
        						no_of_leave_first_per_month=no_of_leave_first_per_month+flag;
        						no_of_leave_first_per_month=Double.parseDouble(value.format(no_of_leave_first_per_month));
        					}
        					else {
        						montlhyLeave=no_of_leave_first_per_month;
        						no_of_leave_first_per_month=.833;
        					}
        					insteacherLeave.setString(c, Double.toString(montlhyLeave));
        				}
        				for(int c=fullleavestartmonth+5;c<17;c++) {
        					double montlhyLeave=no_of_leave_second_perMonth;
        					double flag=(montlhyLeave % 0.5);
        					if(flag!=0) {
        						no_of_leave_second_perMonth=(no_of_leaves/12);
        						montlhyLeave=montlhyLeave-flag;
        						no_of_leave_second_perMonth=no_of_leave_second_perMonth+flag;
        						no_of_leave_second_perMonth=Double.parseDouble(value.format(no_of_leave_second_perMonth));
        					}
        					else {
        						montlhyLeave=no_of_leave_second_perMonth;
        						no_of_leave_second_perMonth=(no_of_leaves/12);
        					}
        					insteacherLeave.setString(c, Double.toString(montlhyLeave));
        				}
        				insteacherLeave.setString(17,doj);
						insteacherLeave.setString(18,location);
						insteacherLeave.setString(19,categoryNames);
						leaveExcecte=insteacherLeave.executeUpdate();
        			}
        			else {
        				
        				double no_of_leave_perMonth=(no_of_leaves/12);
        				insteacherLeave.setString(4,value.format(no_of_leaves));
        				for(int c=5;c<17;c++) {
        					double montlhyLeave=no_of_leave_perMonth;
        					double flag=(montlhyLeave % 0.5);
        					if(flag!=0) {
        						no_of_leave_perMonth=(no_of_leaves/12);
        						montlhyLeave=montlhyLeave-flag;
        						no_of_leave_perMonth=no_of_leave_perMonth+flag;
        						no_of_leave_perMonth=Double.parseDouble(value.format(no_of_leave_perMonth));
        					}
        					else {
        						montlhyLeave=no_of_leave_perMonth;
        						no_of_leave_perMonth=(no_of_leaves/12);
        					}
        					
        					insteacherLeave.setString(c, Double.toString(montlhyLeave));
        				}
        				
        				insteacherLeave.setString(17,doj);
						insteacherLeave.setString(18,location);
						insteacherLeave.setString(19,categoryNames);
        				insteacherLeave.executeUpdate();
        			}
        			
        		}
        		
        		else if(categoryNames.startsWith("HALF")) {
        			if(diffyear<1 && diffyear>0) {
        				double no_of_leave_second=(no_of_leaves/12)*(12-halfpaidleavestartmonth);
        				insteacherLeave.setString(4,value.format(no_of_leave_second));
        				for(int c=5;c<halfpaidleavestartmonth+5;c++) {
        					insteacherLeave.setString(c, "0.0");
        				}
        				for(int c=halfpaidleavestartmonth+5;c<halfpaidleavestartmonth+6;c++) {
        					insteacherLeave.setString(c, value.format(no_of_leave_second));
        				}
        				for(int c=halfpaidleavestartmonth+6;c<17;c++) {
        					insteacherLeave.setString(c, "0.0");
        				}
        				insteacherLeave.setString(17,doj);
						insteacherLeave.setString(18,location);
						insteacherLeave.setString(19,categoryNames);
        				insteacherLeave.executeUpdate();
        			}
        			else {
        				insteacherLeave.setString(4,value.format(no_of_leaves));
        				for(int c=5;c<6;c++) {
        					insteacherLeave.setString(c, value.format(no_of_leaves));
        				}
        				for(int c=6;c<17;c++) {
        					insteacherLeave.setString(c, "0.0");
        				}
        				insteacherLeave.setString(17,doj);
						insteacherLeave.setString(18,location);
						insteacherLeave.setString(19,categoryNames);
						leaveExcecte=insteacherLeave.executeUpdate();
        			}
        		}
        		}
     
        	
        	
        	}
   }else if(teacherType.equalsIgnoreCase("OFFICE STAFF") || teacherType.equalsIgnoreCase("GENERAL")){

        PreparedStatement insteacherLeave = conn.prepareStatement("insert into campus_teacher_new_leave_bank_details(AccYearCode,EmpId,Leave_Type,total_available,January,February,March,April,May,June,July,August,September,October,November,December,Date_Of_Join,LOC_Id,Leave_Name)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        for(int i=0;i<categorynames.length;i++){
        
        	String leavetypes = null;
        	String categoryNames = StringUtils.capitalize(categorynames[i]);
        	double no_of_leaves = Double.parseDouble(noofleaves[i]);
        	 leavidpstmt = conn.prepareStatement("select Leave_ID from campus_new_leave_bank where Leave_name = ? and Loc_ID=? and Accy_Id =?");
        	leavidpstmt.setString(1,categoryNames);
        	leavidpstmt.setString(2,location);
        	leavidpstmt.setString(3,accyear);
        	 leaveidrs = leavidpstmt.executeQuery();
        	while(leaveidrs.next()){
        		leavetypes = leaveidrs.getString("Leave_ID");
        	}
        	leavidpstmt.close();
        	leaveidrs.close();
        	
        	insteacherLeave.setString(1,accyear);
        	insteacherLeave.setString(2,teaId);
        	insteacherLeave.setString(3,leavetypes);
        	 DecimalFormat value = new DecimalFormat("#.#");
        		if(categoryNames.startsWith("CASUAL")){
        				double no_of_leave_perMonth=(no_of_leaves/12);
        				insteacherLeave.setString(4,value.format(no_of_leaves));
        				for(int c=5;c<17;c++) {
        					double montlhyLeave=no_of_leave_perMonth;
        					double flag=(montlhyLeave % 0.5);
        					if(flag!=0) {
        						no_of_leave_perMonth=(no_of_leaves/12);
        						montlhyLeave=montlhyLeave-flag;
        						no_of_leave_perMonth=no_of_leave_perMonth+flag;
        						no_of_leave_perMonth=Double.parseDouble(value.format(no_of_leave_perMonth));
        					}
        					else {
        						montlhyLeave=no_of_leave_perMonth;
        						no_of_leave_perMonth=(no_of_leaves/12);
        					}
        					insteacherLeave.setString(c, Double.toString(montlhyLeave));
        				}
        				insteacherLeave.setString(17,doj);
						insteacherLeave.setString(18,location);
						insteacherLeave.setString(19,categoryNames);
        				insteacherLeave.executeUpdate();
        		}
        		else if(categoryNames.startsWith("HALF")) {
        				insteacherLeave.setString(4,value.format(no_of_leaves));
        				for(int c=5;c<6;c++) {
        					insteacherLeave.setString(c, value.format(no_of_leaves));
        				}
        				for(int c=6;c<17;c++){
        					insteacherLeave.setString(c, "0.0");
        				}
        				insteacherLeave.setString(17,doj);
						insteacherLeave.setString(18,location);
						insteacherLeave.setString(19,categoryNames);
						leaveExcecte=insteacherLeave.executeUpdate();
        	
        		}
			else if(categoryNames.contains("EARN")){
				double no_of_leave_perMonth=(no_of_leaves/12);
				insteacherLeave.setString(4,value.format(no_of_leaves));
				for(int c=5;c<17;c++) {
					double montlhyLeave=no_of_leave_perMonth;
					double flag=(montlhyLeave % 0.5);
					if(flag!=0) {
						no_of_leave_perMonth=(no_of_leaves/12);
						montlhyLeave=montlhyLeave-flag;
						no_of_leave_perMonth=no_of_leave_perMonth+flag;
						no_of_leave_perMonth=Double.parseDouble(value.format(no_of_leave_perMonth));
					}
					else {
						montlhyLeave=no_of_leave_perMonth;
						no_of_leave_perMonth=(no_of_leaves/12);
					}
					insteacherLeave.setString(c, Double.toString(montlhyLeave));
				}
				insteacherLeave.setString(17,doj);
				insteacherLeave.setString(18,location);
				insteacherLeave.setString(19,categoryNames);
				leaveExcecte=insteacherLeave.executeUpdate();	
		}
						
        }
   }
   else if(teacherType.equalsIgnoreCase("SUBJECT TEACHER") || teacherType.equalsIgnoreCase("PROBATION")){

        PreparedStatement insteacherLeave = conn.prepareStatement("insert into campus_teacher_new_leave_bank_details(AccYearCode,EmpId,Leave_Type,total_available,January,February,March,April,May,June,July,August,September,October,November,December,Date_Of_Join,LOC_Id,Leave_Name)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        for(int i=0;i<categorynames.length;i++){
        
        	String leavetypes = null;
        	String categoryNames = StringUtils.capitalize(categorynames[i]);
        	 leavidpstmt = conn.prepareStatement("select Leave_ID from campus_new_leave_bank where Leave_name = ? and Loc_ID=? and Accy_Id =?");
        	leavidpstmt.setString(1,categoryNames);
        	leavidpstmt.setString(2,location);
        	leavidpstmt.setString(3,accyear);
        	 leaveidrs = leavidpstmt.executeQuery();
        	while(leaveidrs.next()){
        		leavetypes = leaveidrs.getString("Leave_ID");
        	}
        	leavidpstmt.close();
        	leaveidrs.close();
        	
        	insteacherLeave.setString(1,accyear);
        	insteacherLeave.setString(2,teaId);
        	insteacherLeave.setString(3,leavetypes);
        	 DecimalFormat value = new DecimalFormat("#.#");
        		if(categoryNames.startsWith("CASUAL")){
        				double no_of_leave_perMonth=(12/12);
        				insteacherLeave.setString(4,"12.0");
        				for(int c=5;c<17;c++) {
        					double montlhyLeave=no_of_leave_perMonth;
        					double flag=(montlhyLeave % 0.5);
        					if(flag!=0) {
        						no_of_leave_perMonth=(1);
        						montlhyLeave=montlhyLeave-flag;
        						no_of_leave_perMonth=no_of_leave_perMonth+flag;
        						no_of_leave_perMonth=Double.parseDouble(value.format(no_of_leave_perMonth));
        					}
        					else {
        						montlhyLeave=no_of_leave_perMonth;
        						no_of_leave_perMonth=(1);
        					}
        					insteacherLeave.setString(c, Double.toString(montlhyLeave));
        				}
        				insteacherLeave.setString(17,doj);
						insteacherLeave.setString(18,location);
						insteacherLeave.setString(19,categoryNames);
						leaveExcecte=insteacherLeave.executeUpdate();
        		}
        	
						
        }
   }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
return leaveExcecte;
	}

public ArrayList<LeaveCalculation> checkLeaveCountForTemporary(LeaveCalculation obj) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: checkLeaveCountForTemporary : Starting");
	Connection conn = null;
	PreparedStatement leaveConsumed = null;
	ResultSet rsleave = null;
	
	double tot_leave = 0.0;
	ArrayList<LeaveCalculation> leavelist = new ArrayList<LeaveCalculation>();
	try{
		
		String startdate=obj.getAccStartDate().split("-")[2]+"-"+obj.getAccStartDate().split("-")[1]+"-%";
		String enddate=obj.getAccEndDate().split("-")[2]+"-"+obj.getAccEndDate().split("-")[1]+"-%";
		conn = JDBCConnection.getSeparateConnection();
		
		leaveConsumed = conn.prepareStatement("SELECT COUNT(*) FROM `campus_teachers_leave_request` WHERE RequestedBy='TEA38' AND (`ApprovedStartDate` LIKE ? OR `ApprovedEndDate` LIKE ?)");
		leaveConsumed.setString(1,obj.getUserid());
		leaveConsumed.setString(2,startdate);
		leaveConsumed.setString(3,enddate);
		 rsleave = leaveConsumed.executeQuery();
		while(rsleave.next()){
			LeaveCalculation list = new LeaveCalculation();
			tot_leave =2- rsleave.getInt(1);
			list.setLeaveCount(tot_leave);
			leavelist.add(list);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if (rsleave != null && (!rsleave.isClosed())) {
				rsleave.close();
			}
			if (leaveConsumed != null && (!leaveConsumed.isClosed())) {
				leaveConsumed.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBankDAOIMPL :checkLeaveCountForTemporary Ending");
	return leavelist;
}


public String getHpl(String academicYear, String teacherID) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getHpl : Starting");
	Connection conn = null;
	PreparedStatement leaveConsumed = null;
	ResultSet rsleave = null;
	
	String hpl="0";
	try{
		
		conn = JDBCConnection.getSeparateConnection();
		
		leaveConsumed = conn.prepareStatement("SELECT total_available FROM campus_teacher_new_leave_bank_details where EmpId=? and AccYearCode=? and Leave_Type=leaveType('HPL')");
		leaveConsumed.setString(1,teacherID);
		leaveConsumed.setString(2,academicYear);
		 rsleave = leaveConsumed.executeQuery();
		while(rsleave.next()){
			hpl=rsleave.getString("total_available");
			
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if (rsleave != null && (!rsleave.isClosed())) {
				rsleave.close();
			}
			if (leaveConsumed != null && (!leaveConsumed.isClosed())) {
				leaveConsumed.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBankDAOIMPL :getHpl Ending");
	return hpl;
}

	
}
