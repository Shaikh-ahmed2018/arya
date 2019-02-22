package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.centris.campus.actions.FeeCollectionAction;
import com.centris.campus.dao.FeeCollectionDao;
import com.centris.campus.daoImpl.FeeCollectionDaoImpl;
import com.centris.campus.delegate.FeeCollectionBD;
import com.centris.campus.forms.ClassFeeSetupForm;
import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.service.FeeCollectionService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.ParentVO;

public class FeeCollectionServiceImpl implements FeeCollectionService{
	
	private static Logger logger = Logger.getLogger(FeeCollectionServiceImpl.class);
	
	@Override
	public ArrayList<FeeNameVo> getFeeCollectionDetails(String currentYear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getFeeCollectionDetails Starting");
		
		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		ArrayList<FeeNameVo> feeCollectionList=new ArrayList<FeeNameVo>();
		
		try {
			
			feeCollectionList=dao.getFeeCollectionDetails(currentYear);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getFeeCollectionDetails Ending");

		return feeCollectionList;
	}

	@Override
	public FeeCollectionVo getFeeCollectionAmount(String FeeCodeDetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getFeeCollectionAmount Starting");
		
		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		FeeCollectionVo feeCollection=new FeeCollectionVo();
		
		try {
			
			feeCollection=dao.getFeeCollectionAmount(FeeCodeDetails);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getFeeCollectionAmount Ending");

		return feeCollection;
	}

	@Override
	public String saveFeeCollection(FeeCollectionVo collectionVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : saveFeeCollection Starting");
		
		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		String status=null;
		
		try {
			
			status=dao.saveFeeCollection(collectionVo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : saveFeeCollection Ending");

		return status;
	}

	@Override
	public ArrayList<FeeNameVo> getSearchFeeCollectionDetails(String currentYear,String classid,
			String sectionId,String termId,String stuId)
 {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getSearchFeeCollectionDetails Starting");
		
		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		ArrayList<FeeNameVo> feeCollectionList=new ArrayList<FeeNameVo>();
		
		try {
			
			feeCollectionList=dao.getSearchFeeCollectionDetails(currentYear,classid,sectionId,termId,stuId);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getSearchFeeCollectionDetails Ending");

		return feeCollectionList;
	}
	
public List<ParentVO> getAllStudentNamesReportService(String sectionid,String classname,String accyear) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getAllStudentNamesReportService Starting");	
		
		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		List<ParentVO> feeCollectionList=new ArrayList<ParentVO>();
		
		try {
			
			
			feeCollectionList=dao.getAllStudentNamesReportDao(sectionid,classname,accyear);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getAllStudentNamesReportService Ending");

		
		return feeCollectionList;
	}

public ArrayList<ClassFeeSetupVo> getAllFeesService(
		ClassFeeSetupPojo feeSetupPojo) {
	
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeCollectionServiceImpl : getAllFeesService Starting");	
	
	FeeCollectionDao dao=new FeeCollectionDaoImpl();
	ArrayList<ClassFeeSetupVo> feeCollectionList=new ArrayList<ClassFeeSetupVo>();
	
	try {
		
		
		feeCollectionList=dao.getAllFeesDao(feeSetupPojo);
		
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeCollectionServiceImpl : getAllFeesService Ending");
	
	return feeCollectionList;
}


public ClassFeeSetupVo getStudentValService(ClassFeeSetupPojo feeSetupPojo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeCollectionServiceImpl : getStudentValService Starting");	
	
	FeeCollectionDao dao=new FeeCollectionDaoImpl();
	ClassFeeSetupVo studentval=null;
	
	
	try {
		
		 studentval = dao.getStudentValDao(feeSetupPojo);
		
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeCollectionServiceImpl : getStudentValService Ending");
	
	return studentval;
}


public ArrayList<ClassFeeSetupVo> getPaymentTypeService(ClassFeeSetupPojo feeSetupPojo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeCollectionServiceImpl : getPaymentTypeService Starting");	
	
	FeeCollectionDao dao=new FeeCollectionDaoImpl();
	ArrayList<ClassFeeSetupVo> studentval=null;
	
	try {
		
		
		
		 studentval = dao.getPaymentTypeDAO(feeSetupPojo);
		
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeCollectionServiceImpl : getPaymentTypeService Ending");
	
	return studentval;
}

@Override
public boolean inserfeecollection(ClassFeeSetupForm form1) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeCollectionServiceImpl : inserfeecollection Starting");	
	
	FeeCollectionDao dao=new FeeCollectionDaoImpl();
boolean inserfeecollection=false;


	
	try
	{
		System.out.println("Service IMPL");
		
		String accyear =form1.getAccyear().trim();
		String fmsname =form1.getHfmsname().trim();
		String studentid =form1.getStudentid().trim();
		String admissionNum =form1.getAdmissionNum().trim();
		String payment_mode =form1.getPayment_mode().trim();
		String paymentType =form1.getPaymentType().trim();
		double paidamount =form1.getPayingamount();
		double totalfeeamt =	form1.getTotalfeeamt();
		double dueamount =	form1.getDueamount();
		String payment_date_id =form1.getPayment_date_id().trim();
		String hclassname =form1.getHclassname().trim();
		String paymentMonth =form1.getPaymentMonth().trim();
		String monthlist =form1.getMonthlist().trim();
		String cheque_no=form1.getCheque_no();

		
		ClassFeeSetupVo vo = new ClassFeeSetupVo();
		
	
		
		int count = dao.getstudentcount(studentid);
		
		if(count==0)
		{
			System.out.println("Insert Service Impl");
			
			vo.setAcadamicyear(accyear);
			vo.setFeecode(fmsname);
			vo.setStudentid(studentid);
			vo.setStdadmissionNo(admissionNum);
			vo.setPaymentmode(payment_mode);
			vo.setPaymenttype(paymentType);
			vo.setPaidamount(paidamount);
			vo.setTotalfeeamount(totalfeeamt);
			vo.setDueamount(dueamount);
			vo.setPaymentdate(payment_date_id);
			vo.setClassname(hclassname);
			vo.setPaymentmonth(paymentMonth);
			vo.setMonthlist(monthlist);
			vo.setCurrentuser(form1.getCurrentuserid());
			vo.setCheque_no(cheque_no);
			vo.setStudentname(fmsname);
			
			inserfeecollection=dao.inserfeecollection(vo);
			
		}
		else
		{
			System.out.println("Update Operation");
			vo.setAcadamicyear(accyear);
			vo.setFeecode(fmsname);
			vo.setStudentid(studentid);
			vo.setStdadmissionNo(admissionNum);
			vo.setPaymentmode(payment_mode);
			vo.setPaymenttype(paymentType);
			vo.setPaidamount(paidamount);
			vo.setTotalfeeamount(totalfeeamt);
			vo.setDueamount(dueamount);
			vo.setPaymentdate(payment_date_id);
			vo.setClassname(hclassname);
			vo.setPaymentmonth(paymentMonth);
			vo.setMonthlist(monthlist);
			vo.setCurrentuser(form1.getCurrentuserid());
			vo.setCheque_no(cheque_no);


			inserfeecollection=dao.updatefeecollection(vo);

		}
		
	} 
	
	catch (Exception e) 
	{
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeCollectionServiceImpl : inserfeecollection Ending");
	

	return inserfeecollection;
}

@Override
public FeeCollectionVo getTranportFeeCollectionAmount(String feeCodeDetails) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeCollectionServiceImpl : getTranportFeeCollectionAmount Starting");
	
	FeeCollectionDao dao=new FeeCollectionDaoImpl();
	FeeCollectionVo feeCollection=new FeeCollectionVo();
	
	try {
		
		feeCollection=dao.getTranportFeeCollectionAmount(feeCodeDetails);
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeCollectionServiceImpl : getTranportFeeCollectionAmount Ending");

	return feeCollection;
}

@Override
public String saveTransportFeeCollection(FeeCollectionVo collectionVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeCollectionServiceImpl : saveTransportFeeCollection Starting");
	
	FeeCollectionDao dao=new FeeCollectionDaoImpl();
	String status=null;
	
	try {
		
		status=dao.saveTransportFeeCollection(collectionVo);
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeCollectionServiceImpl : saveTransportFeeCollection Ending");

	return status;
}

@Override
public ArrayList<FeeNameVo> getFeePaidDetails(String feeCodeDetails) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeCollectionServiceImpl : getFeePaidDetails Starting");
	
	FeeCollectionDao dao=new FeeCollectionDaoImpl();
	ArrayList<FeeNameVo> feeCollectionList=new ArrayList<FeeNameVo>();
	
	try {
		
		feeCollectionList=dao.getFeePaidDetails(feeCodeDetails);
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in FeeCollectionServiceImpl : getFeePaidDetails Ending");

	return feeCollectionList;
}

public ArrayList<AddFeeVO> getDefaulterFeeList(String locId, String classId,String divId, String termId, String accId) {
	return new FeeCollectionDaoImpl().getDefaulterFeeList(locId,classId,divId,termId,accId);
}



}
