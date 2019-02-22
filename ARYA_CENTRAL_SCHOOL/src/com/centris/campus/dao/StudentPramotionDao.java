package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;
import com.centris.campus.pojo.StudentPromotionPOJO;
import com.centris.campus.vo.AcadamicYearVO2;
import com.centris.campus.vo.StudentPramotionVO;

public interface StudentPramotionDao {
	public List<AcadamicYearVO2> getAcadamicYear();

	public List<StudentPramotionVO> getStudentData(String acadamicYear,
			String section);

	public StudentPramotionVO insertStudentPromotion(
			StudentPromotionPOJO studentPramotionPOJO);

	public ArrayList<StudentPramotionVO> getpromotionslist();

}
