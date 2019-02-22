package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.DepartmentMasterDao;
import com.centris.campus.daoImpl.DepartmentMasterDAOImpl;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.forms.DepartmentMasterForm;
import com.centris.campus.pojo.DepartmentMasterPojo;
import com.centris.campus.service.DepartmentMasterService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.DepartmentMasterVO;

public class DepartmentMasterServiceImpl implements DepartmentMasterService {

	private static Logger logger = Logger
			.getLogger(DepartmentMasterServiceImpl.class);

	public ArrayList<DepartmentMasterVO> getDepartmentDetails() {

		DepartmentMasterDao departmentMasterDao = new DepartmentMasterDAOImpl();
		return departmentMasterDao.getDepartmentDetails();
	}

	@Override
	public String insertDepartmentDetails(DepartmentMasterForm deptform,
			String update_dept) {

		DepartmentMasterDao DepartmentMasterDao = new DepartmentMasterDAOImpl();
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterServiceImpl: insertDepartmentDetails : Starting");

		String result = null;

		try {
			DepartmentMasterPojo deptpojo = new DepartmentMasterPojo();

			IDGenerator id = new IDGenerator();

			String s1 = id.getPrimaryKeyID("campus_department");

			deptpojo.setDeptid(id.getPrimaryKeyID("campus_department"));

			deptpojo.setDeptname(deptform.getDept_name());
			deptpojo.setDeptdescription(deptform.getDepartment_description());
			deptpojo.setCreatedby(deptform.getUsername());

			deptpojo.setDepartmentid(s1);

			result = DepartmentMasterDao.insertDepartmentDetails(deptpojo,
					update_dept);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterServiceImpl : insertDepartmentDetails: Ending");
		return result;
	}

	public String deleteDepartmentDetails(String deptid) {
		DepartmentMasterDao DepartmentMasterDao = new DepartmentMasterDAOImpl();
		return DepartmentMasterDao.deleteDepartmentDetails(deptid);
	}

	public boolean validateDeptName(DepartmentMasterForm deptfForm) {
		DepartmentMasterDao DepartmentMasterDao = new DepartmentMasterDAOImpl();
		return DepartmentMasterDao.validateDeptName(deptfForm);
	}

	public DepartmentMasterVO getEditDepartmentDetails(String edit) {
		DepartmentMasterDao DepartmentMasterDao = new DepartmentMasterDAOImpl();
		return DepartmentMasterDao.getEditDepartmentDetails(edit);
	}

	public ArrayList<DepartmentMasterVO> searchDepartment(
			String searchvo) {
		DepartmentMasterDao DepartmentMasterDao = new DepartmentMasterDAOImpl();
		return DepartmentMasterDao.searchDepartment(searchvo);

	}

	public boolean validateDeptNameUpdate(DepartmentMasterVO validateUpdate) {
		DepartmentMasterDao DepartmentMasterDao = new DepartmentMasterDAOImpl();
		return DepartmentMasterDao.validateDeptNameUpdate(validateUpdate);
	}

}
