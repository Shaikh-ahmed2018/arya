package com.centris.campus.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.actions.LeaveBankAction;
import com.centris.campus.daoImpl.AddDesignationDaoImpl;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.LeaveBankDAOIMPL;
import com.centris.campus.forms.AddDesignation;
import com.centris.campus.forms.LeaveBankForm;
import com.centris.campus.pojo.AddDesignationPojo;
import com.centris.campus.service.LeaveBankService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LeaveBankVO;
import com.centris.campus.vo.LeaveCalculation;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.LeaveViewDetailsVo;

public class LeaveBankServiceImpl implements LeaveBankService {
	
	private static Logger logger = Logger
			.getLogger(LeaveBankServiceImpl.class);
	

	public ArrayList<LeaveBankVO> leavebanklist(LeaveBankVO vo) {

		return new LeaveBankDAOIMPL().leavebanklist(vo);
	}
	
	public String insertLeaveBankservice(LeaveBankForm aform){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationServiceImpl:insertDesignationDetails: Starting");

		LeaveBankDAOIMPL dao = new LeaveBankDAOIMPL();

		String check = "";

		if(aform.getSno()==null||aform.getSno()=="")
		{
			IDGenerator id = new IDGenerator();

			LeaveBankVO vo = new LeaveBankVO();

			vo.setAcademicyear(aform.getAcademicyear());
			vo.setsl(aform.getSickleave());
			vo.setPl(aform.getPaidleave());
			vo.setCl(aform.getCasualleave());
			vo.setTotalleaves(aform.getTotalleaves());
			vo.setPermonth(aform.getPermonth());
			vo.setCreateuser(aform.getCreatedby());
			check = dao.insertLeaveBanksDAO(vo);
		}

		else if (!(aform.getSno() == null))

		{
			LeaveBankVO vo = new LeaveBankVO();
			System.out.println("Updateleavebank  serviceImpl is Working");

			vo.setAcademicyear(aform.getAcademicyear());

			vo.setsl(aform.getSickleave());

			vo.setCl(aform.getCasualleave());

			vo.setTotalleaves(aform.getTotalleaves());

			vo.setPermonth(aform.getPermonth());

			vo.setAcademicyear(aform.getAcademicyear());

			vo.setSno(aform.getSno());

			vo.setCreateuser(aform.getCreatedby());

			check = dao.updateLeaveBanksDAO(vo);

		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationServiceImpl:insertDesignationDetails: Ending");
		return check;
	}

	public LeaveBankForm editleavebank(LeaveBankForm aform)
	{
		return new LeaveBankDAOIMPL().editleavebank(aform);
	}

	@Override
	public ArrayList<LeaveBankVO> getSearchDetails(String searchTextVal) {
		return new LeaveBankDAOIMPL().getSearchDetails(searchTextVal);

	}

	@Override
	public Boolean deleteLeave(String[] deletelist)
	{
		// TODO Auto-generated method stub
		return new LeaveBankDAOIMPL().deleteLeave(deletelist);
	}

	@Override
	public ArrayList<LeaveRequestVo> getLeaveAprrovedDetails(
			LeaveRequestVo leavevo)
			{
		return new LeaveBankDAOIMPL().getLeaveAprrovedDetails(leavevo);
	}

	public Boolean validAddLeave(String year) {
		return new LeaveBankDAOIMPL().validAddLeave(year);
	}

	public ArrayList<LeaveViewDetailsVo> getviewLeaveDetails(String userId) {
		return new LeaveBankDAOIMPL().getviewLeaveDetails(userId);
	}

	public String addLeavesCategory(String[] categorynames, String[] shortnames, String[] noofleaves, String[] catId, String accyear, String location) {
		return new LeaveBankDAOIMPL().addLeavesCategory(categorynames,shortnames,noofleaves,catId,accyear,location);
	}

	public ArrayList<LeaveBankVO> getleaveCatList() {
		return new LeaveBankDAOIMPL().getleaveCatList();
	}

	public ArrayList<LeaveBankVO> getleavetypeDetails(String academic_year) {
		return new LeaveBankDAOIMPL().getleavetypeDetails(academic_year);
	}

	public ArrayList<LeaveBankVO> editleavetypes(String accyear, String loc) {
		return new LeaveBankDAOIMPL().editleavetypes(accyear,loc);
	}

	public String checkLeaveType(String accyear, String loc, String category) {
		return new LeaveBankDAOIMPL().editleavetypes(accyear,loc,category);
	}

	public ArrayList<LeaveBankVO> getSearchleavetypeDetails(String searchTearm, String academic_year) {
		return new LeaveBankDAOIMPL().getSearchleavetypeDetails(searchTearm,academic_year);
	}

	public String updateLeavesCategory(String[] hiddenLEaveIdArray, String[] categorynames, String[] shortnames,
			String[] noofleaves, String[] catId, String accyear, String location) {
		return new LeaveBankDAOIMPL().updateLeavesCategory(hiddenLEaveIdArray,categorynames,shortnames,noofleaves,catId,accyear,location);
	}

	public ArrayList<LeaveBankVO> getaccLocCatList(String accyear, String loc) {
		// TODO Auto-generated method stub
		return new LeaveBankDAOIMPL().getaccLocCatList(accyear,loc);
	}

	public String checkDuplicacy(String accyear, String loc) {
		return new LeaveBankDAOIMPL().checkDuplicacy(accyear,loc);
	}

	public ArrayList<LeaveBankVO> getleavenamesList(String academic_year) {
		return new LeaveBankDAOIMPL().getleavenamesList(academic_year);
	}

	public ArrayList<LeaveBankVO> getleaveusertype(String parentid, String academic_year) {
		return new LeaveBankDAOIMPL().getleaveusertype(parentid,academic_year);
	}

	public ArrayList<LeaveBankVO> getleaveBalance(String parentid, String academic_year) {
		return new LeaveBankDAOIMPL().getleaveBalance(parentid,academic_year);
	}

	public LeaveCalculation getLeaveCalculation(String parentid, String academic_year) {
		return new LeaveBankDAOIMPL().getLeaveCalculation(parentid,academic_year);
	}

	public LeaveCalculation getNewLeaveCalculation(String parentid, String academic_year) {
		return new LeaveBankDAOIMPL().getNewLeaveCalculation(parentid,academic_year);
	}

	public ArrayList<LeaveCalculation> checkLeaveCount(LeaveCalculation obj) {
		return new LeaveBankDAOIMPL().checkLeaveCount(obj);
	}

	public String checkDateDuplicacy(String startDate, String toDate, String leavetype, String parentid) {
		return new LeaveBankDAOIMPL().checkDateDuplicacy(startDate,toDate,leavetype,parentid);
	}

	public ArrayList<LeaveViewDetailsVo> getviewNewLeaveDetails(String trim, String academic_year) {
		return new LeaveBankDAOIMPL().getviewNewLeaveDetails(trim,academic_year);
	}


	public ArrayList<LeaveBankVO> getLeaveTypes(String academicYear, String location) {
		return new LeaveBankDAOIMPL().getLeaveTypes(academicYear,location);
	}



	public String checkMonthleave(String academic_year, String parentid, String fromDate, String leavetype) {
		return new LeaveBankDAOIMPL().checkMonthleave(academic_year,parentid,fromDate,leavetype);
	}

	public List<LeaveBankVO> getNoOfLeave(String academicYear, String location,String leaveType) {
		return new LeaveBankDAOIMPL().getNoOfLeave(academicYear, location, leaveType);
	}


	
}
