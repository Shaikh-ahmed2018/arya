package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.LocationDao;
import com.centris.campus.dao.SpecializationDao;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.LocationDAOImpl;
import com.centris.campus.daoImpl.SpecializationDaoImpl;
import com.centris.campus.forms.SpecializationForm;
import com.centris.campus.pojo.SpecializationPojo;
import com.centris.campus.service.SpecializationService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.SpecializationVo;



public class SpecializationServiceImpl implements SpecializationService {

	private static Logger logger = Logger
			.getLogger(DepartmentMasterServiceImpl.class);
		SpecializationDao dao;
	@Override
	public ArrayList<SpecializationVo> getspecializationList(String schoolLocation) {
		dao= new SpecializationDaoImpl();
		return dao.getspecializationList(schoolLocation);
	}
	@Override
	public String insertSpecialization(SpecializationForm spec, String specId) {
		
		SpecializationDao specializationDao = new SpecializationDaoImpl();
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterServiceImpl: insertDepartmentDetails : Starting");
		String result =null;
		try{
			
			SpecializationPojo pojo =new SpecializationPojo();
			IDGenerator id = new IDGenerator();

			if(specId.equalsIgnoreCase("")){
				
				
			}else{
				
				
			}
			String s1 = id.getPrimaryKeyID("campus_class_specialization");

			pojo.setSpec_Id(spec.getSpec_Id());

			pojo.setSpec_Name(spec.getSpec_Name());
			pojo.setStream_Id(spec.getStream_Id());
			pojo.setCreate_User(spec.getCreate_User());
			pojo.setClass_Id(spec.getClass_Id());
			pojo.setSpecializationid(s1);
			pojo.setLocationId(spec.getLocationId());

			result = specializationDao.insertSpecialization(pojo,specId);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public SpecializationVo editSpecialization(String edit) {
		dao= new SpecializationDaoImpl();
		return dao.editSpecialization(edit);
	}
	@Override
	public String deleteSpec(String[] specId) {
		dao= new SpecializationDaoImpl();
		return dao.deleteSpec(specId);
	}
	
	public List<SpecializationVo> getSpecializationOnClassBased(String classId) {
		dao= new SpecializationDaoImpl();
		return dao.getSpecializationOnClassBased(classId);
	}
	@Override
	public String validateSpecialization(SpecializationForm form1) {
		dao= new SpecializationDaoImpl();
		return dao.getSpecializationOnClassBased(form1);
	}
	@Override
	public ArrayList<SpecializationVo> getSearchSpecializationList(String searchterm,String school) {
		dao= new SpecializationDaoImpl();
		return dao.getSearchSpecializationList(searchterm,school);
	}
	public List<SpecializationVo> getstreamdetailsforOnchange(String locationid, String classname, String streamId) {
	return new SpecializationDaoImpl().getstreamdetailsforOnchange( locationid,  classname,  streamId);
	}
	
	public List<SpecializationVo> getSpecializationOnClassWithoutLocId(String classId) {
		dao= new SpecializationDaoImpl();
		return dao.getSpecializationOnClassWithoutLocId(classId);
	}

}
