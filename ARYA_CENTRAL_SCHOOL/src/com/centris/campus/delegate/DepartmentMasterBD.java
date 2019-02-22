package com.centris.campus.delegate;

import java.util.ArrayList;
import com.centris.campus.forms.DepartmentMasterForm;
import com.centris.campus.service.DepartmentMasterService;
import com.centris.campus.serviceImpl.DepartmentMasterServiceImpl;
import com.centris.campus.vo.DepartmentMasterVO;

public class DepartmentMasterBD {

	public ArrayList<DepartmentMasterVO> getDepartmentDetails() {

		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.getDepartmentDetails();

	}

	public String insertDepartmentDetails(DepartmentMasterForm deptform,
			String update_dept) {
		// TODO Auto-generated method stub

		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.insertDepartmentDetails(deptform,
				update_dept);
	}

	public boolean validateDeptName(DepartmentMasterForm deptform) {
		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.validateDeptName(deptform);
	}

	public String deleteDepartmentDetails(String deptid) {
		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.deleteDepartmentDetails(deptid);
	}

	public DepartmentMasterVO getEditDepartmentDetails(String edit) {
		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.getEditDepartmentDetails(edit);
	}

	public ArrayList<DepartmentMasterVO> searchDepartment(
			String string) {
		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.searchDepartment(string);

	}

	public boolean validateDeptNameUpdate(DepartmentMasterVO validateUpdate) {
		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.validateDeptNameUpdate(validateUpdate);
	}


}
