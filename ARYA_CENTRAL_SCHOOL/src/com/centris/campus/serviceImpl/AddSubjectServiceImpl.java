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
















import com.centris.campus.actions.AddLabForm;
import com.centris.campus.dao.AddtSubjectDao;
import com.centris.campus.dao.LocationDao;
import com.centris.campus.daoImpl.AddSubjectDaoImpl;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.LocationDAOImpl;
import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.pojo.LibraryLocationPojo;
import com.centris.campus.service.AddSubjectService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LibrarySqlUtils;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.VehicleDetailsVO;
import com.centris.campus.vo.ViewallSubjectsVo;

public class AddSubjectServiceImpl implements AddSubjectService {
	private static final Logger logger = Logger
			.getLogger(AddSubjectServiceImpl.class);
	AddtSubjectDao daoObj=new AddSubjectDaoImpl();
	
	public synchronized List<ViewallSubjectsVo> subjectdetails(String schoolLocation)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : subjectdetails Starting");
		ArrayList<ViewallSubjectsVo> subjectdetails = null;
		try {
			subjectdetails = daoObj.subjectdetails(schoolLocation);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : subjectdetails  Ending");
    return subjectdetails;
}

	public synchronized boolean DeleteSubject(String[] voObj,String[] locationList){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : DeleteSubject Starting");
		boolean status=false;
		try {
			status = daoObj.DeleteSubject(voObj,locationList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : DeleteSubject  Ending");
    
		return status;
	}
	
	public synchronized List<ViewallSubjectsVo> searchsubjectdetails(ViewallSubjectsVo voObj)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : searchsubjectdetails Starting");
		List<ViewallSubjectsVo> subjectdetails = null;
		try {
			subjectdetails = daoObj.searchsubjectdetails(voObj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : searchsubjectdetails  Ending");
    return subjectdetails;
	}
	
	public synchronized boolean addSubject(AddSubjectForm obj)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : addSubject Starting");
		boolean status=false;
		try {
			status = daoObj.addSubject(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : addSubject  Ending");
    
		return status;
	}
	
	public synchronized ViewallSubjectsVo getSubjectDetails(ViewallSubjectsVo obj)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : getSubjectDetails Starting");
	
		try {
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : getSubjectDetails  Ending");
		return daoObj.getSubjectDetails(obj);
	}
	
	
	
	public synchronized boolean updateSubjectDetails(AddSubjectForm obj)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : updateSubjectDetails Starting");
		boolean status=false;
		try {
			status = daoObj.updateSubjectDetails(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : updateSubjectDetails  Ending");
    
		return status;
	}
	
	public synchronized String getpath(String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ViewallSubjectsServiceImpl: getpath  Starting");
		String path=null;
		try{
		 path = new AddSubjectDaoImpl().getpath(classname);
		}
		catch(Exception exception){
			exception.printStackTrace();
			logger.error(exception);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ViewallSubjectsServiceImpl: getpath Ending");
		return path;
	}

	
	public String DdownloadsyllabuspathService(String subjectid) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ViewallSubjectsServiceImpl: DdownloadsyllabuspathService  Starting");
		
		
		String path = null;
		
		try {
			
			 path = new AddSubjectDaoImpl().DdownloadsyllabuspathDao(subjectid);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ViewallSubjectsServiceImpl: DdownloadsyllabuspathService Ending");
		return path;
	}

	@Override
	public String validateSubName(AddSubjectForm form1) {
		AddtSubjectDao subObj=new AddSubjectDaoImpl();
		return subObj.validateSubName(form1);
	}

	public List<ViewallSubjectsVo> subjectdetailsOnchangeListingPage(String locationid, String classname, String specialization) {
		return new AddSubjectDaoImpl().subjectdetailsOnchangeListingPage( locationid,  classname,  specialization);
	}

	@Override
	
		public synchronized List<ViewallSubjectsVo> labdetails(String schoolLocation)
		{
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AddSubjectServiceImpl : labdetails Starting");
			ArrayList<ViewallSubjectsVo> labdetails = null;
			try {
				labdetails = daoObj.labdetails(schoolLocation);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AddSubjectServiceImpl : labdetails  Ending");
	    return labdetails;
	}

	@Override
	public boolean addLab(AddSubjectForm obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : addLab Starting");
		boolean status=false;
		try {
			status = daoObj.addLab(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : addSubject  Ending");
    
		return status;
	}

	@Override
	public List<AddSubjectForm> getLaboratoryDetails() {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<AddSubjectForm> list = new ArrayList<AddSubjectForm>();
		try {
			con = JDBCConnection.getSeparateConnection();
			pstmt = con.prepareStatement(SQLUtilConstants.GET_LABORATORY_DETAILS);
			System.out.println("lab details query for table display..."+pstmt);
			rs = pstmt.executeQuery();
			//cl.Location_Name,ccd.classdetails_name_var,cs.subjectName,ccs.Specialization_name,Total_Marks,Pass_Marks,ld.Description,lab_id,Lab_Code,Syllabus
			while (rs.next()) {
				AddSubjectForm lablist = new AddSubjectForm();
				lablist.setLab_id(rs.getString("lab_id"));
				lablist.setLocationName(rs.getString("Location_Name"));
				lablist.setLocationId(rs.getString("Location_Id"));
				lablist.setClassname(rs.getString("classdetails_name_var"));
				lablist.setSubjtname(rs.getString("subjectName"));
				lablist.setLab_name(rs.getString("Lab_Name"));
				lablist.setTotalMarks(rs.getInt("Total_Marks"));
				lablist.setSpecialization(rs.getString("Specialization_name"));
				lablist.setPassMarks(rs.getInt("Pass_Marks"));
				lablist.setDescription(rs.getString("Description"));
				lablist.setSubjectCode(rs.getString("Lab_Code"));
				lablist.setSyllabus(rs.getString("Syllabus"));
				list.add(lablist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public boolean DeleteLab(String[] idList, String[] locationList) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : DeleteLab Starting");
		boolean status=false;
		try {
			status = daoObj.DeleteLab(idList,locationList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : DeleteSubject  Ending");
    
		return status;
	}

	@Override
	public AddSubjectForm getLabDetails(AddSubjectForm obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : getSubjectDetails Starting");
	
		try {
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : getSubjectDetails  Ending");
		return daoObj.getLabDetails(obj);
	}

	@Override
	public boolean updateLabDetails(AddSubjectForm addSubjectForm)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : updateSubjectDetails Starting");
		boolean status=false;
		try {
			status = daoObj.updateLabDetails(addSubjectForm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : updateSubjectDetails  Ending");
    
		return status;
	}

	@Override
	public List<AddSubjectForm> labdetailsOnchangeListingPage(
			String locationid, String classname, String specialization) {
	
		return new AddSubjectDaoImpl().labdetailsOnchangeListingPage( locationid,  classname,  specialization);
	}

	@Override
	public String validateLabName(AddSubjectForm form1) {
		AddtSubjectDao subObj=new AddSubjectDaoImpl();
		return subObj.validateLabName(form1);
	}

	@Override
	public String DdownloadLabsyllabuspathService(String subjectid) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ViewallSubjectsServiceImpl: DdownloadsyllabuspathService  Starting");
		
		
		String path = null;
		
		try {
			
			 path = new AddSubjectDaoImpl().DdownloadLabsyllabuspathDao(subjectid);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ViewallSubjectsServiceImpl: DdownloadsyllabuspathService Ending");
		return path;
	}
	

	@Override
	public String getSubjectName(String subjectid) {
		AddtSubjectDao subObj=new AddSubjectDaoImpl();
		return subObj.getSubjectName(subjectid);
	}

	

	
}
