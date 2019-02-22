package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.forms.DepartmentMasterForm;
import com.centris.campus.pojo.DepartmentMasterPojo;
import com.centris.campus.vo.DepartmentMasterVO;

public interface DepartmentMasterDao {

	public ArrayList<DepartmentMasterVO> getDepartmentDetails();

	public String insertDepartmentDetails(DepartmentMasterPojo deptpojo,
			String update_dept);

	public String deleteDepartmentDetails(String deptid);

	public boolean validateDeptName(DepartmentMasterForm deptForm);

	public DepartmentMasterVO getEditDepartmentDetails(String edit);

	public ArrayList<DepartmentMasterVO> searchDepartment(
			String searchvo);

	public boolean validateDeptNameUpdate(DepartmentMasterVO validateUpdate);

}
