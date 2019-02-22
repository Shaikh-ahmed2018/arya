package com.centris.campus.actions;

import java.net.HttpRetryException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.daoImpl.ElectionDaoImpl;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.StudentRegistrationDaoImpl;
import com.centris.campus.delegate.ElectionBD;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.ReligionCasteCasteCategoryBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.StudentTransferCertifivateReportBD;
import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.ElectionPojo;
import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.pojo.ReligionCasteCasteCategoryPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ElectionVo;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.itextpdf.text.log.SysoLogger;

public class ElectionAction extends DispatchAction{
	
	static ResourceBundle res=ResourceBundle.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName=res.getString("ImageName");
	private static String IdTemplate=res.getString("IdTemplate1");
	private static final Logger logger =Logger.getLogger(IDCreationAction.class);
	
	public ActionForward getAccYear(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAccYear Starting");
		try {
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			
			JSONObject obj=new JSONObject();
			obj.put("accyar", accYearList);
			response.getWriter().print(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAccYear Ending");

		return null;
	}
	public ActionForward getAccYearByGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAccYearByGroup Starting");
		try {
		ArrayList<ElectionVo> accyarListByGroup = new ElectionBD().getAccYearByGroup();
			
			
			JSONObject obj=new JSONObject();
			obj.put("accyarByGroup", accyarListByGroup);
			response.getWriter().print(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAccYearByGroup Ending");

		return null;
	}
	

		
	public ActionForward getGroupNamebyAcademicYear(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getGroupNamebyAcademicYear Starting");
		try {
			
			ElectionPojo pojo = new ElectionPojo();
			String accyear = request.getParameter("accyear");
			System.out.println("checking accyear&&&&&&&&&&&&&&&&&&&&&&&"+accyear);
			pojo.setAccyear(accyear);
			ArrayList<ElectionVo> GroupName = new ElectionBD().getGroupNamebyAcademicYear(pojo);
	
			JSONObject obj=new JSONObject();
			obj.put("groupName", GroupName);
			response.getWriter().print(obj);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getGroupNamebyAcademicYear Ending");

		return null;
	}	
	
	
	
	
	public ActionForward getLocation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getLocation Starting");
		
		
		try {

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			
			JSONObject obj=new JSONObject();
			obj.put("locList", locationList);
			response.getWriter().print(obj);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getLocation Ending");

		return null;
	}
	
	public ActionForward checkDateOverLap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionMenuAction : checkDateOverLap Starting");
	
		
	
		try{
			
			
			
			
			ElectionPojo pojo = new ElectionPojo();
		
			String accyearh = request.getParameter("acyearh");
			String electionTitleh= request.getParameter("electionTitleh");
			String groupnameh= request.getParameter("groupnameh");
			
		
			pojo.setAcchidden(accyearh);
			pojo.setElectionTitleh(electionTitleh);
			pojo.setGrphidden(groupnameh);
			
			String date = request.getParameter("date");
			System.out.println("from date::::::::::::::::"+date);
			//String result = new ElectionBD().checkDateOverLap(date);
		String result = new ElectionBD().checkDateOverLap(date,pojo);
			
			JSONObject obj=new JSONObject();
			obj.put("status",result);
			response.getWriter().print(obj);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionMenuAction : checkDateOverLap Ending");

		return null;
	}
	
	
	public ActionForward addNewGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : addNewGroup Starting");
	
		List<ElectionVo> list=new ArrayList<ElectionVo>();
	
		try{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_ELECTION_GROUPSETTING);
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
			
			String accyear = request.getParameter("accyear");
			String accyname = request.getParameter("name");
			
			request.setAttribute("accyearid",accyear);
			request.setAttribute("accyear",accyname);
			
		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : addNewGroup Ending");

		return mapping.findForward(MessageConstants.addNewGroup);
	}
	public ActionForward saveGroupdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		try{
			
			String accyear = request.getParameter("accyearid");
			String locid= request.getParameter("locid");
			String groupname = request.getParameter("groupname");
			
			String result = new ElectionBD().saveGroupdetails(accyear,locid,groupname);

			JSONObject obj=new JSONObject();
			obj.put("status",result);
			response.getWriter().print(obj);
			
			}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return null;
	}
	
	public ActionForward saveElectionDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : saveElectionDetails Starting");
		try{
			
			ElectionPojo pojo = new ElectionPojo();

			String accyear = request.getParameter("acyear");
			String electionTitle= request.getParameter("electionTitle");
			String groupname = request.getParameter("groupname");
			String fromdate = request.getParameter("Fromdate");
			String todate = request.getParameter("todate");
			String startTime = request.getParameter("starttime");
			String endTime = request.getParameter("endtime");
			
			pojo.setAccyear(accyear);
			pojo.setElectionTitle(electionTitle);
			pojo.setGroupName(groupname);
			pojo.setfromDate(fromdate);
			pojo.settoDate(todate);
			pojo.setStartTime(startTime);
			pojo.setEndTime(endTime);
		
			String result = new ElectionBD().saveElectionDetails(pojo);

			JSONObject obj=new JSONObject();
			obj.put("status",result);
			response.getWriter().print(obj);

			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : saveElectionDetails Ending");

		return null;
	}
	
	public ActionForward UpdateElectionDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : UpdateElectionDetails Starting");
		try{
			
			ElectionPojo pojo = new ElectionPojo();
			
			
			String accyearh = request.getParameter("acyearh");
			String electionTitleh= request.getParameter("electionTitleh");
			String electionTitleHName= request.getParameter("electionTitleHName");
			String electionTitle= request.getParameter("electionTitle");
			String groupnameh = request.getParameter("groupnameh");
			String fromdate = request.getParameter("Fromdate");
			String todate = request.getParameter("todate");
			String startTime = request.getParameter("starttime");
			String endTime = request.getParameter("endtime");
			
			System.out.println("accyearh"+accyearh);
			System.out.println("electionTitleh"+electionTitleh);
			System.out.println("electionTitle"+electionTitle);
			System.out.println("groupnameh"+groupnameh);
			System.out.println("fromdate"+fromdate);
			System.out.println("todate"+todate);
			System.out.println("startTime"+startTime);
			System.out.println("endTime"+endTime);
			
			pojo.setAcchidden(accyearh);
			pojo.setElectionTitleh(electionTitleh);
			pojo.setElectionTitleHName(electionTitleHName);
			pojo.setElectionTitle(electionTitle);
			pojo.setGrphidden(groupnameh);
			pojo.setfromDate(fromdate);
			pojo.settoDate(todate);
			pojo.setStartTime(startTime);
			pojo.setEndTime(endTime);
			
			String result = new ElectionBD().UpdateElectionDetails(pojo);

			JSONObject obj=new JSONObject();
			obj.put("status",result);
			response.getWriter().print(obj);
		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : UpdateElectionDetails Ending");

		return null;
	}
	
	
	
	public ActionForward UpdateGroupDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : addNewGroup Starting");
	
		List<ElectionVo> list=new ArrayList<ElectionVo>();
		try{
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();//for dropdown of school names
			request.setAttribute("locationList", locationList);
			request.setAttribute("locationHidden",locationList.get(0).getLocationId());//for setting hidden values
			
			ElectionPojo detailspojo = new ElectionPojo();//for geting values from list page to show in inputboxes
			String accyear = request.getParameter("accyear");
			String groupid = request.getParameter("groupid");
			System.out.println("groupid==============="+groupid);
			System.out.println("accid==============="+accyear);
			request.setAttribute("accyearid",accyear);
			request.setAttribute("groupid",groupid);
			
			detailspojo.setAccid(accyear);
			detailspojo.setGroupId(groupid);
			
			list= new ElectionBD().getAccGrpName(detailspojo);
			
			request.setAttribute("accGrpDetails",list);
			
			request.setAttribute("yearId", list.get(0).getAccyear());
			request.setAttribute("yearIdHidden", list.get(0).getAccid()); // for setting hidden values
			request.setAttribute("GroupName", list.get(0).getGroupName());
			request.setAttribute("GroupIdHidden", list.get(0).getGroupid());// for setting hidden values
			request.setAttribute("size",list.size());
			System.out.println("list.size()=-----="+list.size());
		
		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : addNewGroup Ending");

		return mapping.findForward(MessageConstants.addNewGroupSchool);
	}
	
	
	public ActionForward daterange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : addNewGroup Starting");
		
		
		try{
			
			String accyear = request.getParameter("accyear");
			//String startDate = request.getParameter("");


			 String startDate=Integer.toString(HelperClass.getPastDateofAcademicYear(accyear)+1);
			 String enddate=Integer.toString(HelperClass.getForDateofAcademicYear(accyear));

			JSONObject obj=new JSONObject();
						
			obj.put("startDate", startDate+","+enddate);
						
			response.getWriter().print(obj);

			
		}catch(Exception e){
			}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : addNewGroup Ending");

		return null;
	}
	
	
	
	public ActionForward updateElectionGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : updateElectionGroup Starting");
	
		try{
			//getting hidden values for update
			System.out.println("checking data");
			String acch=request.getParameter("acchidden");
			String grph=request.getParameter("grouphidden");
			String grpname=request.getParameter("groupname");
			String hiddengroupname=request.getParameter("hiddengroupname");
			String schoolHiddenval[]=request.getParameter("schoolIdArray").split(",");
			System.out.println("acchhhh====="+acch);
			System.out.println("grphidden====="+grph);
			System.out.println("school hidden==========="+schoolHiddenval.length);
			
			ElectionPojo pojo = new ElectionPojo();
			pojo.setAcchidden(acch);
			pojo.setGrphidden(grph);
			pojo.setGroupName(grpname);
			pojo.setHiddengroupname(hiddengroupname);
			
			//pojo.setGroupName(groupName)
		
		
			String result = new ElectionBD().updateGroupdetails(pojo,schoolHiddenval);
			

			JSONObject obj=new JSONObject();
			obj.put("status",result);
			response.getWriter().print(obj);
			
		}catch(Exception e){
			
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in IDCreationAction : updateElectionGroup Ending");

		return null;
	}
	

public ActionForward ElectionUpdate(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : ElectionUpdate Starting");

	List<ElectionVo> list=new ArrayList<ElectionVo>();
	try{
		
		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
		
		String accyearId = request.getParameter("accyear");
		String groupId = request.getParameter("group");
		String TitleId = request.getParameter("titleID");
		

		request.setAttribute(accyearId, request.getParameter("accyear"));
		request.setAttribute(groupId, request.getParameter("group"));
		request.setAttribute(TitleId, request.getParameter("titleID"));

		ElectionPojo pojo = new ElectionPojo();
		pojo.setAccid(accyearId);
		pojo.setGroupId(groupId);
		pojo.setElectionTitleId(TitleId);
		
		list = new ElectionBD().getElectionDetailsFUpdate(pojo);
		
		request.setAttribute("accGrpDetails",list);
		
		request.setAttribute("yearId", list.get(0).getAccyear());
		request.setAttribute("yearIdHidden", list.get(0).getAccid()); // for setting hidden values
		
		request.setAttribute("GroupName", list.get(0).getGroupName());
		request.setAttribute("GroupIdHidden", list.get(0).getGroupid());// for setting hidden value
		
		request.setAttribute("electionTitle", list.get(0).getElectionTitle());
		request.setAttribute("electionTitleIdHidden", list.get(0).getElectionTitleId());

		request.setAttribute("fromDate", list.get(0).getFromDate());
		request.setAttribute("toDate", list.get(0).getToDate());
		request.setAttribute("startTime", list.get(0).getStartTime());
		request.setAttribute("endTime", list.get(0).getEndTime());
		
		request.setAttribute("size",list.size());
		System.out.println("list.size()=-----="+list.size());
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : ElectionUpdate Ending");

	return mapping.findForward(MessageConstants.electionUpdate);
}

//---election category setting MODULE-3------------------------------

public ActionForward electionCategoryAdd(ActionMapping mapping, ActionForm form,

		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction  : electionCategoryAdd Starting");


	List<ElectionVo> list=new ArrayList<ElectionVo>();
	try{
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_ELECTION_CATEGORYSETTING);
		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
		
		String accyearId = request.getParameter("accyear");
		String groupId = request.getParameter("group");
		String TitleId = request.getParameter("titleID");
		

		request.setAttribute(accyearId, request.getParameter("accyear"));
		request.setAttribute(groupId, request.getParameter("group"));
		request.setAttribute(TitleId, request.getParameter("titleID"));

		ElectionPojo pojo = new ElectionPojo();
		pojo.setAccid(accyearId);
		pojo.setGroupId(groupId);
		pojo.setElectionTitleId(TitleId);
		
		list = new ElectionBD().getElectionDetailsFUpdate(pojo);
		
		
		request.setAttribute("accGrpDetails",list);
		request.setAttribute("houseList", HelperClass.getHouse("LOC2", accyearId));
		request.setAttribute("yearId", list.get(0).getAccyear());
		request.setAttribute("yearIdHidden", list.get(0).getAccid()); // for setting hidden values
		
		request.setAttribute("GroupName", list.get(0).getGroupName());
		request.setAttribute("GroupIdHidden", list.get(0).getGroupid());// for setting hidden value
		
		request.setAttribute("electionTitle", list.get(0).getElectionTitle());
		request.setAttribute("electionTitleIdHidden", list.get(0).getElectionTitleId());

		request.setAttribute("fromDate", list.get(0).getFromDate());
		request.setAttribute("toDate", list.get(0).getToDate());
		request.setAttribute("startTime", list.get(0).getStartTime());
		request.setAttribute("endTime", list.get(0).getEndTime());
		
		request.setAttribute("size",list.size());
		System.out.println("list.size()=-----="+list.size());
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction  : electionCategoryAdd Ending");


	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in IDCreationAction : studentIDPDFReport Ending");

	return mapping.findForward(MessageConstants.electionCategoryAdd);
}

public ActionForward electionCategoryAddPopUp(ActionMapping mapping, ActionForm form,

		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : electionCategoryAddPopUp Starting");
	try{
		String categoryName = request.getParameter("categoryName");
		String participateClass = request.getParameter("participateClass");
		String genderWise = request.getParameter("genderWise");
		String houseWise = request.getParameter("houseWise");
		String priority = request.getParameter("priority");
		String nominLevel = request.getParameter("nominLevel");
		String nominFor = request.getParameter("nominFor");
		String classWise = request.getParameter("classWise");
		String houseId = request.getParameter("houseId");
		String acchidden = request.getParameter("accyear");
		String grphidden = request.getParameter("groupnameHidden");
		String electionTitleh = request.getParameter("TitleHidden");
		String electionCategoryId = request.getParameter("categoryNameIdHidden");
		
		System.out.println("electionCategoryId"+electionCategoryId);
		System.out.println("categoryName"+categoryName);
		
	  	ElectionPojo pojo = new ElectionPojo();
		pojo.setCategoryName(categoryName);
		pojo.setParticipateClass(participateClass);
		pojo.setGenderWise(genderWise);
		pojo.setHouseWise(houseWise);
		pojo.setPriority(priority);
		pojo.setNominLevel(nominLevel);
		pojo.setNominFor(nominFor);
		pojo.setClassWiseName(classWise);
		pojo.setHouseId(houseId);
		pojo.setAcchidden(acchidden);
		pojo.setGrphidden(grphidden);
		pojo.setElectionTitleh(electionTitleh);
		pojo.setElectionCategoryId(electionCategoryId);
		
		
		String result = new ElectionBD().electionCategoryAddPopUp(pojo);
		
		JSONObject obj=new JSONObject();
		obj.put("status",result);
		response.getWriter().print(obj);

		}catch(Exception e){
			e.printStackTrace();
		}
		


	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in IDCreationAction : electionCategoryAddPopUp Ending");

	return null;
}

public ActionForward getElectionCategoryList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
	try{
		
		ArrayList< ElectionVo> list = new ArrayList<ElectionVo>();
 		String accyear = request.getParameter("accyear");
		String groupnameHidden= request.getParameter("groupnameHidden");
		String TitleHidden = request.getParameter("TitleHidden");
		
		 list = new ElectionBD().getElectionCategoryList(accyear,groupnameHidden,TitleHidden);

		 JSONObject obj=new JSONObject();
		obj.put("DataList",list);
		response.getWriter().print(obj);
		
		}catch(Exception e){
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in IDCreationAction : studentIDPDFReport Ending");

	return null;
}


public ActionForward DeleteElectionCategoryList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : DeleteElectionCategoryList Starting");
	try{
		
		String id = request.getParameter("id");
		String	 list = new ElectionBD().DeleteElectionCategoryList(id);
		

		JSONObject obj=new JSONObject();
		obj.put("DataList",list);
		response.getWriter().print(obj);
		
		}catch(Exception e){
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in IDCreationAction : DeleteElectionCategoryList Ending");

	return null;
}


/*--------------------Nomination Registration :::: Module - 4----------------------------*/

public ActionForward getElectionTitleByGroupName(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getGroupNamebyAcademicYear Starting");
	try {
		
		ElectionPojo pojo = new ElectionPojo();
		String groupName = request.getParameter("groupName");
		String accyear = request.getParameter("accyear");
		
		if(accyear.equalsIgnoreCase("all") || accyear.equalsIgnoreCase("") || accyear==null ){
			accyear="%%";
		}
		
	
		if(groupName.equalsIgnoreCase("all") || groupName.equalsIgnoreCase("") || groupName==null ){
			groupName="%%";
		}
		System.out.println("checking accyear&&&&&&&&&&&&&&&&&&&&&&&"+groupName);
		pojo.setGroupName(groupName);
		pojo.setAccyear(accyear);
		ArrayList<ElectionVo> electionTitle = new ElectionBD().getElectionTitleByGroupName(pojo);

		JSONObject obj=new JSONObject();
		obj.put("electionTitle", electionTitle);
		response.getWriter().print(obj);


	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getGroupNamebyAcademicYear Ending");

	return null;
}

public ActionForward getNominationRegistrationList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getNominationRegistrationList Starting");
try{
	
	String academicYear = request.getParameter("academicYear");
	if(academicYear.equalsIgnoreCase("all")){
		academicYear="%%";
	}
	
	String groupName = request.getParameter("groupName");
	if(groupName.equalsIgnoreCase("all")){
		groupName="%%";
	}
	
	String electionTitle = request.getParameter("electionTitle");
	if(electionTitle.equalsIgnoreCase("all")){
		electionTitle="%%";
	}
	
	System.out.println("accheckkkkkkkkkkkkkkkGtrouppppppppppppp"+academicYear);
	System.out.println("accheckkkkkkkkkkkkkkk"+groupName);
	System.out.println("accheckkkkkkkkkkkkkkkGtrouppppppppppppp"+electionTitle);

	ArrayList<ElectionVo> list = new ElectionBD().getNominationRegistrationList(academicYear,groupName,electionTitle);
	
	JSONObject obj = new JSONObject();
	obj.put("DataList", list);
	response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
		+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
		+ " Control in ElectionAction : getNominationRegistrationList Ending");
	return null;
	}

	public ActionForward buttonForNomination(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : buttonForNomination Starting");
		try{
			
			String id = request.getParameter("id");
			String	 list = new ElectionBD().DeleteElectionCategoryList(id);
			JSONObject obj=new JSONObject();
			obj.put("DataList",list);
			response.getWriter().print(obj);
			}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : buttonForNomination Ending");
			return null;
			}
	
	
public ActionForward getElectionCategoryByTitle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getElectionCategoryByTitle Starting");
		try {
			
			ElectionPojo pojo = new ElectionPojo();
			String groupName = request.getParameter("groupName");
			String accyear = request.getParameter("accyear");
			String electionTitle = request.getParameter("electionTitle");
			System.out.println("checking accyear&&&&&&&&&&&&&&&&&&&&&&&"+groupName);
			pojo.setGroupName(groupName);
			pojo.setAccyear(accyear);
			pojo.setElectionTitle(electionTitle);
			
			ArrayList<ElectionVo> electionTitle1 = new ElectionBD().getElectionCategoryByTitle(pojo);

			JSONObject obj=new JSONObject();
			obj.put("electionTitle", electionTitle1);
			response.getWriter().print(obj);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
	}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getElectionCategoryByTitle Ending");

		return null;
	}
	
public ActionForward getSingleNomineeDetails(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getSingleNomineeDetails Starting");
	try {
		
		ElectionPojo pojo = new ElectionPojo();
		String academicYear = request.getParameter("academicYear");
		String admissionNo = request.getParameter("admissionNo");

		
		pojo.setAccyear(academicYear);
		pojo.setAdmissionNo(admissionNo);
		ArrayList<ElectionVo> electionCategory = new ElectionBD().getSingleNomineeDetails(pojo);

		JSONObject obj=new JSONObject();
	obj.put("nominee", electionCategory);
		response.getWriter().print(obj);


	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getSingleNomineeDetails Ending");

	return null;
}
	
	
public ActionForward saveNewNomineeDetails(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : saveNewNomineeDetails Starting");
	try {		
		ElectionPojo pojo = new ElectionPojo();
	
		String admissionNo = request.getParameter("admissionNo");
		String studentName = request.getParameter("studentName");
		String classNameId = request.getParameter("classNameId");
		String sectionNameId = request.getParameter("sectionNameId");
		String categoryId = request.getParameter("categoryId");
		String groupId = request.getParameter("groupId");
		String titleId = request.getParameter("titleId");
		String accyearId = request.getParameter("accyearId");

		
		pojo.setAdmissionNo(admissionNo);
		pojo.setStudentName(studentName);
		pojo.setClassWiseName(classNameId);
		pojo.setSectionWiseName(sectionNameId);
		pojo.setElectionCategoryId(categoryId);
		pojo.setGroupId(groupId);
		pojo.setAccid(accyearId);
		pojo.setElectionTitleId(titleId);
	
		pojo.setAdmissionNo(admissionNo);
		String result = new ElectionBD().saveNewNomineeDetails(pojo);

		JSONObject obj=new JSONObject();
		obj.put("status", result);
		response.getWriter().print(obj);

	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : saveNewNomineeDetails Ending");

	return null;
}

//-----------------------Nomination Approval :: Model 4---------------------------------

public ActionForward getNominationApprovalList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getNominationApprovalList Starting");
try{
	
	String academicYear = request.getParameter("academicYear");
	if(academicYear.equalsIgnoreCase("all") || academicYear.equalsIgnoreCase("") || academicYear==null ){
		academicYear="%%";
	}
	
	String groupName = request.getParameter("groupName");
	if(groupName.equalsIgnoreCase("all") || groupName.equalsIgnoreCase("") || groupName==null ){
		groupName="%%";
	}
	
	String electionTitle = request.getParameter("electionTitle");
	if(electionTitle.equalsIgnoreCase("all") || electionTitle.equalsIgnoreCase("") || electionTitle==null){
		electionTitle="%%";
	}
	String electionCategory = request.getParameter("electionCategory");
	if(electionCategory.equalsIgnoreCase("all") ){
		electionCategory="%%";
	}
	System.out.println("academic year----------------------->"+academicYear);
	System.out.println("groupName----------------------->"+groupName);
	System.out.println("electionTitle---------------------->"+electionTitle);
	System.out.println("electionCategory----------------------->"+electionCategory);
	
	ArrayList<ElectionVo> list = new ElectionBD().getNominationApprovalList(academicYear,groupName,electionTitle,electionCategory);
	
	JSONObject obj = new JSONObject();
	obj.put("DataList", list);
	response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
		+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
		+ " Control in ElectionAction : getNominationApprovalList Ending");
	return null;
	}

public ActionForward saveApproval(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StreamDetailsAction : saveApproval  Starting");

	try {
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);

		String admissionId[] = request.getParameter("admissionId").split(",");
		
		System.out.println("admissionId "+admissionId.length);
		
		ElectionPojo pojo = new ElectionPojo();

		pojo.setAdmissionNoArray(admissionId);

		System.out.println("pojolength "+pojo.getAdmissionNoArray().length);
		String check = new ElectionBD().saveApproval(pojo);

	

		JSONObject json = new JSONObject();

		json.put("status", check);

		response.getWriter().print(json);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StreamDetailsAction : saveApproval   Ending");

	return null;

}
public ActionForward rejectApproval(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StreamDetailsAction : rejectApproval  Starting");

	try {
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);

		String admissionId[] = request.getParameter("admissionId").split(",");
		System.out.println("admissionId "+admissionId.length);
		ElectionPojo pojo = new ElectionPojo();
		pojo.setAdmissionNoArray(admissionId);
		System.out.println("pojolength "+pojo.getAdmissionNoArray().length);
		
		String check = new ElectionBD().rejectApproval(pojo);

		JSONObject json = new JSONObject();
		json.put("status", check);
		response.getWriter().print(json);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StreamDetailsAction : rejectApproval   Ending");

	return null;

}
public ActionForward getUpdateElectionCategory(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getUpdateElectionCategory Starting");
	try{
		
		ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
 		String categoryId = request.getParameter("categoryId");
 		list = new ElectionBD().getUpdateElectionCategory(categoryId);
		
 		
 		JSONObject obj=new JSONObject();
		obj.put("DataList",list);
		response.getWriter().print(obj);
		
		}catch(Exception e){
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in IDCreationAction : getUpdateElectionCategory Ending");

	return null;
}
/*---------------------booth setting-----------------------*/
public ActionForward getStaffNameList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getStaffNameList Starting");
	try {
		ArrayList<ElectionVo> list = new ElectionBD().getStaffNameList();
		//request.setAttribute("AccYearList", accYearList);
		
		JSONObject obj=new JSONObject();
		obj.put("teacherData", list);
		response.getWriter().print(obj);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getAccYear Ending");

	return null;
}

public ActionForward saveBoothDetails(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : saveBoothDetails Starting");
	try{

		ElectionPojo pojo = new ElectionPojo();

		String boothName = request.getParameter("boothName");
		String boothNameHidden = request.getParameter("boothNameHidden");//used for update purpose
		String staffIncharge= request.getParameter("staffIncharge");
		String centralSystem = request.getParameter("centralSystem");
		String centralSystemIp = request.getParameter("centralSystemIp");
		String voterClass = request.getParameter("voterClass");

		String academicYear = request.getParameter("academicYear");
		String groupName = request.getParameter("groupName");
		String electionTitle = request.getParameter("electionTitle");
		
		if(academicYear.equalsIgnoreCase("all")){
			academicYear = "%%";
		}
		if(groupName.equalsIgnoreCase("all")){
			groupName = "%%";
		}
		if(electionTitle.equalsIgnoreCase("all")){
			electionTitle = "%%";
		}
		
		
		pojo.setBoothName(boothName);
		pojo.setStaffIncharge(staffIncharge);
		pojo.setCentralSystem(centralSystem);
		pojo.setCentralSystemIp(centralSystemIp);
		pojo.setVoterClass(voterClass);
		pojo.setAccyear(academicYear);
		pojo.setGroupName(groupName);
		pojo.setElectionTitle(electionTitle);
		pojo.setBoothNameHidden(boothNameHidden);
	
		String result = new ElectionBD().saveBoothDetails(pojo);

		JSONObject obj=new JSONObject();
		obj.put("status",result);
		response.getWriter().print(obj);

		
		
		
	}catch(Exception e){
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : saveBoothDetails Ending");

	return null;
}
public ActionForward getBoothDetailsList(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response )throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getBoothDetailsList Starting");
	
	try{
		ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
		
		ElectionPojo pojo =new ElectionPojo();
		String academicYear = request.getParameter("academicYear");
		String groupName = request.getParameter("groupName");
		String electionTitle = request.getParameter("electionTitle");
		
		pojo.setAccyear(academicYear);
		pojo.setGroupName(groupName);
		pojo.setElectionTitle(electionTitle);
		
		list = new ElectionBD().getBoothDetailsList(pojo);
		JSONObject obj = new JSONObject();
		obj.put("DataList",list);
		response.getWriter().print(obj);
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getBoothDetailsList Ending");
	
	return null;
}
public ActionForward deleteBoothSelectedRow(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : deleteBoothSelectedRow Starting");
	try{
		
		String id = request.getParameter("id");
		String list = new ElectionBD().deleteBoothSelectedRow(id);
		

		JSONObject obj=new JSONObject();
		obj.put("status",list);
		response.getWriter().print(obj);
		
		}catch(Exception e){
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in IDCreationAction : deleteBoothSelectedRow Ending");
	return null;
}

public ActionForward getUpdateBoothSetting(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getUpdateBoothSetting Starting");
	try{
		
		ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
 		String boothNameId = request.getParameter("boothNameId");
 		list = new ElectionBD().getUpdateBoothSetting(boothNameId);
		
 		
 		JSONObject obj=new JSONObject();
		obj.put("DataList",list);
		response.getWriter().print(obj);
		
		}catch(Exception e){
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in IDCreationAction : getUpdateBoothSetting Ending");

	return null;
}
public ActionForward getBoothNameDropdown(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getBoothNameDropdown Starting");
	try{
		ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
		
		ElectionPojo pojo =new ElectionPojo();
		String academicYear = request.getParameter("academicYear");
		String groupName = request.getParameter("groupName");
		String electionTitle = request.getParameter("electionTitle");
		
		if(academicYear.equalsIgnoreCase("all")){
			academicYear = "%%";
		}
		if(groupName.equalsIgnoreCase("all")){
			groupName = "%%";
		}
		if(electionTitle.equalsIgnoreCase("all")){
			electionTitle = "%%";
		}
		
		pojo.setAccyear(academicYear);
		pojo.setGroupName(groupName);
		pojo.setElectionTitle(electionTitle);
		
		list = new ElectionBD().getBoothNameDropdown(pojo);
		System.out.println("size of list"+list.size());
		
		JSONObject obj = new JSONObject();
		obj.put("DataList",list);
		response.getWriter().print(obj);
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getBoothNameDropdown Ending");

	return null;
}

public ActionForward savePollingMachineDetails(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : savePollingMachineDetails Starting");
	try{

		ElectionPojo pojo = new ElectionPojo();
		

		String boothNameforPolling = request.getParameter("boothNameforPolling");
		String pollingMachineName = request.getParameter("pollingMachineName");
		String pollingSystemName = request.getParameter("pollingSystemName");
		String pollingSystemIp = request.getParameter("pollingSystemIp");
		String pollingHiddenId = request.getParameter("pollingHiddenId");
	

		String academicYear = request.getParameter("academicYear");
		String groupName = request.getParameter("groupName");
		String electionTitle = request.getParameter("electionTitle");
		
		if(pollingHiddenId.equalsIgnoreCase("") || pollingHiddenId.equalsIgnoreCase(null)){
			pollingHiddenId="%%";
		}
		
		pojo.setBoothName(boothNameforPolling);
		pojo.setPollingMachineName(pollingMachineName);
		pojo.setPollingSystemName(pollingSystemName);
		pojo.setPollingSystemIp(pollingSystemIp);
		pojo.setAccyear(academicYear);
		pojo.setGroupName(groupName);
		pojo.setElectionTitle(electionTitle);
		pojo.setPollingMachineId(pollingHiddenId);
	
	
		String result = new ElectionBD().savePollingMachineDetails(pojo);

		JSONObject obj=new JSONObject();
		obj.put("status",result);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : savePollingMachineDetails Ending");

	return null;
}

public ActionForward getPollingMachineList(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response )throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getPollingMachineList Starting");
	
	try{
		ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
		
		ElectionPojo pojo =new ElectionPojo();
		String academicYear = request.getParameter("academicYear");
		String groupName = request.getParameter("groupName");
		String electionTitle = request.getParameter("electionTitle");
		String boothNameforPolling2 = request.getParameter("boothNameforPolling2");
		
		if(academicYear.equalsIgnoreCase("all")){
			academicYear ="%%";
		}
		if(groupName.equalsIgnoreCase("all")){
			groupName="%%";
		}
		if(electionTitle.equalsIgnoreCase("all")){
			electionTitle = "%%";
		}
		if(boothNameforPolling2.equalsIgnoreCase("all")){
			boothNameforPolling2 = "%%";
		}
		
		pojo.setAccyear(academicYear);
		pojo.setGroupName(groupName);
		pojo.setElectionTitle(electionTitle);
		pojo.setBoothName(boothNameforPolling2);	
		
		list = new ElectionBD().getPollingMachineList(pojo);
		
		
		JSONObject obj = new JSONObject();
		obj.put("DataList",list);
		response.getWriter().print(obj);
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getPollingMachineList Ending");
	
	return null;
}
public ActionForward deletePoleSelectedRow(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : deletePoleSelectedRow Starting");
	try{
		
		String id = request.getParameter("id");
		String list = new ElectionBD().deletePoleSelectedRow(id);
		

		JSONObject obj=new JSONObject();
		obj.put("DataList",list);
		response.getWriter().print(obj);
		
		}catch(Exception e){
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in IDCreationAction : deletePoleSelectedRow Ending");
	return null;
}

public ActionForward getUpdatePollingDetails(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuAction : getUpdatePollingDetails Starting");
	try{
		
		ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
 		String boothNameId = request.getParameter("boothNameId");
 		String status = request.getParameter("status");
 		String classId = request.getParameter("classId");
 		if(classId==null || classId.equalsIgnoreCase("") || classId.equalsIgnoreCase("null")){
 		list = new ElectionBD().getUpdatePollingDetails(boothNameId,status);
 		}
 		else{
 			list = new ElectionDaoImpl().getUpdatePollingDetailsForVerification(boothNameId,status,classId);	
 		}
		
 		
 		JSONObject obj=new JSONObject();
		obj.put("DataList",list);
		response.getWriter().print(obj);
		
		}catch(Exception e){
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in IDCreationAction : getUpdatePollingDetails Ending");

	return null;
}
//temporary
public ActionForward updatePollingData(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : updatePollingData Starting");
	try{

		ElectionPojo pojo = new ElectionPojo();
		

		String boothNameforPolling = request.getParameter("boothNameforPolling");
		String pollingMachineName = request.getParameter("pollingMachineName");
		String pollingSystemName = request.getParameter("pollingSystemName");
		String pollingSystemIp = request.getParameter("pollingSystemIp");
		String id = request.getParameter("id");
		
	

		String academicYear = request.getParameter("academicYear");
		String groupName = request.getParameter("groupName");
		String electionTitle = request.getParameter("electionTitle");
		
		pojo.setBoothName(boothNameforPolling);
		pojo.setPollingMachineName(pollingMachineName);
		pojo.setPollingSystemName(pollingSystemName);
		pojo.setPollingSystemIp(pollingSystemIp);
		pojo.setAccyear(academicYear);
		pojo.setGroupName(groupName);
		pojo.setElectionTitle(electionTitle);
		pojo.setPollingMachineId(id);
	
	
		String result = new ElectionBD().updatePollingData(pojo);

		JSONObject obj=new JSONObject();
		obj.put("status",result);
		response.getWriter().print(obj);
		
	}catch(Exception e){
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : updatePollingData Ending");

	return null;
}

public ActionForward checkDuplicateStaff(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : checkDuplicateStaff Starting");
	try{

		ElectionPojo pojo = new ElectionPojo();

		String boothName = request.getParameter("boothName");
		
		String staffIncharge= request.getParameter("staffIncharge");
;

		String academicYear = request.getParameter("academicYear");
		String groupName = request.getParameter("groupName");
		String electionTitle = request.getParameter("electionTitle");
		
		if(academicYear.equalsIgnoreCase("all")){
			academicYear = "%%";
		}
		if(groupName.equalsIgnoreCase("all")){
			groupName = "%%";
		}
		if(electionTitle.equalsIgnoreCase("all")){
			electionTitle = "%%";
		}
		
		
		pojo.setBoothName(boothName);
		pojo.setStaffIncharge(staffIncharge);

		pojo.setAccyear(academicYear);
		pojo.setGroupName(groupName);
		pojo.setElectionTitle(electionTitle);

	
		String result = new ElectionBD().checkDuplicateStaff(pojo);

		JSONObject obj=new JSONObject();
		obj.put("status",result);
		response.getWriter().print(obj);

		
		
		
	}catch(Exception e){
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : checkDuplicateStaff Ending");

	return null;
}
//-----------------------------voter search module:::7--------------------------

public ActionForward getElectionTitleByAccYear(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getGroupNamebyAcademicYear Starting");
	try {
		
		ElectionPojo pojo = new ElectionPojo();
		String accyear = request.getParameter("accyear");
		System.out.println("checking accyear&&&&&&&&&&&&&&&&&&&&&&&"+accyear);
		pojo.setAccyear(accyear);
		ArrayList<ElectionVo> title = new ElectionBD().getElectionTitleByAccYear(pojo);

		JSONObject obj=new JSONObject();
		obj.put("title", title);
		response.getWriter().print(obj);


	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);

	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getGroupNamebyAcademicYear Ending");

	return null;
}	


public ActionForward getClassByAccyearTitle(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getGroupNamebyAcademicYear Starting");
	try {
		
		ElectionPojo pojo = new ElectionPojo();
		String electionTitle = request.getParameter("electionTitle");
		String accyear = request.getParameter("accyear");
		
		pojo.setElectionTitle(electionTitle);
		pojo.setAccyear(accyear);
		ArrayList<ElectionVo> className = new ElectionBD().getClassByAccyearTitle(pojo);

		JSONObject obj=new JSONObject();
		obj.put("className", className);
		response.getWriter().print(obj);


	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getGroupNamebyAcademicYear Ending");

	return null;
}

public ActionForward getSectionByAccyearTitle(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getGroupNamebyAcademicYear Starting");
	try {
		
		ElectionPojo pojo = new ElectionPojo();
		String electionTitle = request.getParameter("electionTitle");
		String accyear = request.getParameter("accyear");
		
		pojo.setElectionTitle(electionTitle);
		pojo.setAccyear(accyear);
		ArrayList<ElectionVo> sectionName = new ElectionBD().getSectionByAccyearTitle(pojo);

		JSONObject obj=new JSONObject();
		obj.put("sectionName", sectionName);
		response.getWriter().print(obj);


	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getGroupNamebyAcademicYear Ending");

	return null;
}
public ActionForward getVoterSearchList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ElectionAction : getNominationApprovalList Starting");
try{
	
	String academicYear = request.getParameter("academicYear");
	if(academicYear.equalsIgnoreCase("all") || academicYear.equalsIgnoreCase("") || academicYear==null ){
		academicYear="%%";
	}
	
	String className = request.getParameter("className");
	if(className.equalsIgnoreCase("all") || className.equalsIgnoreCase("") || className==null ){
		className="%%";
	}
	
	String electionTitle = request.getParameter("electionTitle");
	if(electionTitle.equalsIgnoreCase("all") || electionTitle.equalsIgnoreCase("") || electionTitle==null){
		electionTitle="%%";
	}
	String sectionName = request.getParameter("sectionName");
	if(sectionName.equalsIgnoreCase("all")  || sectionName.equalsIgnoreCase("") || sectionName==null){
		sectionName="%%";
	}
	System.out.println("academic year----------------------->"+academicYear);
	System.out.println("className----------------------->"+className);
	System.out.println("electionTitle---------------------->"+electionTitle);
	System.out.println("sectionName----------------------->"+sectionName);
	
	ArrayList<ElectionVo> list = new ElectionBD().getVoterSearchList(academicYear,className,electionTitle,sectionName);
	
	JSONObject obj = new JSONObject();
	obj.put("DataList", list);
	response.getWriter().print(obj);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
		+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
		+ " Control in ElectionAction : getNominationApprovalList Ending");
	return null;
}



	
	public ActionForward voterDetailsView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : voterDetailsView Starting");

			List<ElectionVo> list=new ArrayList<ElectionVo>();
			
			try{
				String admissionNo = request.getParameter("admissionNo");
				String studentId = request.getParameter("studentId");
				

				request.setAttribute(admissionNo, request.getParameter("admissionNo"));
				request.setAttribute(studentId, request.getParameter("studentId"));

				ElectionPojo pojo = new ElectionPojo();
				pojo.setAdmissionNo(admissionNo);
				pojo.setStudentId(studentId);
				
				list = new ElectionBD().getvoterDetailsViewUpdate(pojo);
				
				request.setAttribute("voterDetails", list);
			
				request.setAttribute("yearName", list.get(0).getAccyear());
				request.setAttribute("yearIdHidden", list.get(0).getAccid()); //for hidden values
				
				request.setAttribute("studentName", list.get(0).getStudentName());
				request.setAttribute("studentId", list.get(0).getStudentId());
				
				request.setAttribute("className", list.get(0).getClassName());
				request.setAttribute("classId", list.get(0).getClassId());
				
				request.setAttribute("sectionName", list.get(0).getSectionName());
				request.setAttribute("sectionId", list.get(0).getSectionId());
				
				request.setAttribute("status", list.get(0).getStatus());
				request.setAttribute("imageUrl", list.get(0).getImgUrl());
				request.setAttribute("rollNo", list.get(0).getRollNumber());
				request.setAttribute("location", list.get(0).getLocationName());
				request.setAttribute("admissionNo", list.get(0).getAdmissionNo());
				request.setAttribute("house", list.get(0).getHouseWise());
				
				request.setAttribute("size", list.size());
				System.out.println("list size"+list.size());
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ElectionAction : voterDetailsView ending");

			return mapping.findForward(MessageConstants.voterDetailsView);
		}
	
	
	public ActionForward getVotingMachine(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getVotingMachine Starting");

		try{
			String accid = request.getParameter("accid");
			ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
				list = new ElectionBD().getVotingMachine(accid);
			
				JSONObject obj = new JSONObject();
				obj.put("machine", list);
				response.getWriter().print(obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ElectionAction : getVotingMachine ending");

			return null;
	}
	public ActionForward MachicneActivation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getVotingMachine Starting");

		try{
			String localIp = request.getParameter("localIp");
			String classId = request.getParameter("classId");
			String houseId = request.getParameter("houseId");
			String accyear = request.getParameter("accyear");
			String group = request.getParameter("group");
			String titleID = request.getParameter("titleID");
			String studentId = request.getParameter("studentId");
			ElectionPojo object=new ElectionPojo();
			object.setClassId(classId);
			object.setPollingSystemIp(localIp);
			object.setHouseId(houseId);
			object.setAccid(accyear);
			object.setGroupId(group);
			object.setElectionTitleId(titleID);
			object.setStudentId(studentId);
			
			String status=new ElectionDaoImpl().getMachicneActivation(object);
			System.out.println("getMachicneActivation:::"+status);
				JSONObject obj = new JSONObject();
				obj.put("status", status);
				response.getWriter().print(obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ElectionAction : getVotingMachine ending");

			return null;
	}
	
	public ActionForward CheckMachineStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getVotingMachine Starting");

		try{
			String localIp = request.getParameter("localIp");
			String status=new ElectionDaoImpl().checkMachicneActivation(localIp);
			
				JSONObject obj = new JSONObject();
				obj.put("status", status);
				response.getWriter().print(obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ElectionAction : getVotingMachine ending");

			return null;
	}
	
	
	public ActionForward studentSearchbyadmissionNoForNomination(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : studentSearchbyadmissionNo Starting");

		
			List<StudentRegistrationVo> searchStudentList = new ArrayList<StudentRegistrationVo>();
			try {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				String searchterm = request.getParameter("searchTerm");
				String electionCategory = request.getParameter("electionCategory");

				registrationVo.setSearchTerm(searchterm);
				registrationVo.setElectionCategory(electionCategory);
				ElectionDaoImpl daoImpl = new ElectionDaoImpl();
				searchStudentList = daoImpl.studentSearchbyadmissionNoForNomination(registrationVo);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put(MessageConstants.JSON_RESPONSE, searchStudentList);

				response.getWriter().print(jsonObject);
		}catch(Exception e){
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ElectionAction : studentSearchbyadmissionNo ending");

			return null;
	}
	public ActionForward studentSearchbyadmissionNo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : studentSearchbyadmissionNo Starting");

		
			List<StudentRegistrationVo> searchStudentList = new ArrayList<StudentRegistrationVo>();
			try {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				String searchterm = request.getParameter("searchTerm");
				String electionCategory = request.getParameter("electionCategory");

				registrationVo.setSearchTerm(searchterm);
				registrationVo.setElectionCategory(electionCategory);
				ElectionDaoImpl daoImpl = new ElectionDaoImpl();
				searchStudentList = daoImpl.studentSearchbyadmissionNo(registrationVo);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put(MessageConstants.JSON_RESPONSE, searchStudentList);

				response.getWriter().print(jsonObject);
		}catch(Exception e){
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ElectionAction : studentSearchbyadmissionNo ending");

			return null;
	}
	
	public ActionForward voteCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : voteCount Starting");

		try{
			String localIp =request.getParameter("localIp");
			String admissionNo=request.getParameter("admissionNo");
			String count=request.getParameter("count");
			String voter=request.getParameter("voter");
			
			String status=new ElectionDaoImpl().voteCount(localIp,admissionNo,count,voter);
			
				JSONObject obj = new JSONObject();
				obj.put("status", status);
				response.getWriter().print(obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ElectionAction : voteCount ending");

			return null;
	}
	
	
	public ActionForward getCategoryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getVotingMachine Starting");

		try{
			ArrayList<ElectionVo> categoryList=new ArrayList<ElectionVo>();
			String classId =request.getParameter("classId");
			String accyear =request.getParameter("accyear");
			String group =request.getParameter("group");
			String titleID =request.getParameter("titleID");
			String studentId=request.getParameter("studentId");
			String houseId=request.getParameter("houseId");
			categoryList=new ElectionDaoImpl().getCategoryList(classId,accyear,group,titleID,studentId,houseId);
			
				JSONObject obj = new JSONObject();
				obj.put("categoryList", categoryList);
				response.getWriter().print(obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ElectionAction : getVotingMachine ending");

			return null;
	}
	public ActionForward getElectionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getElectionList Starting");

		try{
			String academicYearID = request.getParameter("academicYear");
			if(academicYearID.equalsIgnoreCase("all")){
				academicYearID="%%";
			}
			
			String groupNameID = request.getParameter("groupName");
		
			if(groupNameID.equalsIgnoreCase("all")){
				groupNameID="%%";
			}
			
			
			String electionId = request.getParameter("electionId");
			
			if(electionId.equalsIgnoreCase("all")){
				electionId="%%";
			}
			ElectionPojo pojo = new ElectionPojo();
			
			pojo.setAccyear(academicYearID);
			pojo.setGroupName(groupNameID);
			pojo.setElectionTitleId(electionId);
			

			ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
			list = new ElectionDaoImpl().getElectionDetailsForReport(pojo);
			request.setAttribute("DataList", list);	
			JSONObject obj= new JSONObject();
			obj.put("DataList", list);
			response.getWriter().print(obj);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getElectionList Ending");

		return null;
	}
	public ActionForward getMachineDeActivation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getMachineDeActivation Starting");

		try{
			
			
			String pollingMachineId = request.getParameter("pollingMachineId");
			
			
			ElectionPojo pojo = new ElectionPojo();
			
			
			pojo.setPollingMachineId(pollingMachineId);
			

			String  status = new ElectionDaoImpl().getMachicneDeActivation(pojo);

			JSONObject obj= new JSONObject();
			obj.put("DataList", status);
			response.getWriter().print(obj);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : getMachineDeActivation Ending");

		return null;
	}
	
	public ActionForward studentSearchbyadmissionNoForApproval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : studentSearchbyadmissionNo Starting");

		
			List<StudentRegistrationVo> searchStudentList = new ArrayList<StudentRegistrationVo>();
			try {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				String searchterm = request.getParameter("searchTerm");
				String localIp=request.getParameter("localIp");
				String accyear=request.getParameter("accyear");

				registrationVo.setSearchTerm(searchterm);
				registrationVo.setTempregid(localIp);
				registrationVo.setAccyear(accyear);
				ElectionDaoImpl daoImpl = new ElectionDaoImpl();
				searchStudentList = daoImpl.studentSearchbyadmissionNoForApproval(registrationVo);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put(MessageConstants.JSON_RESPONSE, searchStudentList);

				response.getWriter().print(jsonObject);
		}catch(Exception e){
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ElectionAction : studentSearchbyadmissionNo ending");

			return null;
	}
	
	
	
	public ActionForward refreshCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ElectionAction : refreshCheck Starting");

		
			List<StudentRegistrationVo> searchStudentList = new ArrayList<StudentRegistrationVo>();
			try {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				String localIp=request.getParameter("localIp");
				
				String electionaccyear=request.getParameter("electionaccyear");
				String electiongroup=request.getParameter("electiongroup");
				String electiontitleID=request.getParameter("electiontitleID");
				
				registrationVo.setTempregid(localIp);
				registrationVo.setAccyear(electionaccyear);
				registrationVo.setElectionCategory(electiontitleID);
				ElectionDaoImpl daoImpl = new ElectionDaoImpl();
				searchStudentList = daoImpl.refrshCheck(registrationVo);
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put(MessageConstants.JSON_RESPONSE, searchStudentList);

				response.getWriter().print(jsonObject);
		}catch(Exception e){
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ElectionAction : refreshCheck ending");

			return null;
	}
	
}

