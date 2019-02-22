package com.centris.campus.service;

import java.util.ArrayList;
import com.centris.campus.forms.DepartmentMasterForm;
import com.centris.campus.vo.DepartmentMasterVO;

public interface DepartmentMasterService {

	ArrayList<DepartmentMasterVO> getDepartmentDetails();

	String insertDepartmentDetails(DepartmentMasterForm deptform,
			String update_dept);

	String deleteDepartmentDetails(String deptid);

	boolean validateDeptName(DepartmentMasterForm deptform);

	DepartmentMasterVO getEditDepartmentDetails(String edit);

	ArrayList<DepartmentMasterVO> searchDepartment(String string);

	boolean validateDeptNameUpdate(DepartmentMasterVO validateUpdate);

}
