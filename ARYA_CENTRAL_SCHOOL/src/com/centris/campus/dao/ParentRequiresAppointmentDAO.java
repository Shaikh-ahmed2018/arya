package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.vo.AryasmartschoolVo;
import com.centris.campus.vo.ParentRequiresAppointmentVO;
import com.centris.campus.vo.ThirdformformatVO;
import com.centris.campus.vo.secadmissionformformatVO;
import com.centris.campus.vo.SecondAdmissionformVo;
import com.centris.campus.vo.ThirdAddmissionApplicationVo;

public interface ParentRequiresAppointmentDAO {

	List<ParentRequiresAppointmentVO> getAdmisssionProcessingListDetails();

	ParentRequiresAppointmentVO EditingForAdmissionApproval(ParentRequiresAppointmentVO detailsVo);

	String UpdatingFirstLevelAdmissionApproval(ParentRequiresAppointmentVO detailsVo);

	String InsertTemporaryStudentRegistration(ParentRequiresAppointmentForm parentform);

	List<ParentRequiresAppointmentVO> searchadmissionsList(String searchName);

	String DeleteParentRequiresAppointment(ParentRequiresAppointmentVO vo);

	List<ParentRequiresAppointmentVO> CalledForEvaluationList();

	ParentRequiresAppointmentVO EditingForCalledAdmissionDetails(ParentRequiresAppointmentVO detailsVo);

	String UpdatingCalledForEvaluationStatus(ParentRequiresAppointmentVO details);

	List<ParentRequiresAppointmentVO> searchCalledForEvaluationList(String searchName);

	List<ParentRequiresAppointmentVO> FinalAdmisssionList();

	ParentRequiresAppointmentVO EditingForConfirmingAdmissionDetails(ParentRequiresAppointmentVO detailsVo);

	String UpdatingFinalApprovalAdmissionStatus(ParentRequiresAppointmentVO details);


	ArrayList<AryasmartschoolVo> getimageName(String id);

	ArrayList<SecondAdmissionformVo> downloadsecadmissionapplication(String parameter);



	String Insertthirdadmissionformat(ParentRequiresAppointmentForm parentform);

	List<secadmissionformformatVO> getsecformadmissiondetails();

	List<ThirdformformatVO> getthirdadmissiondetailslist();

	ArrayList<ThirdAddmissionApplicationVo> downloadthirdAppform(String param);

	String getsecform();

	List<ParentRequiresAppointmentVO> getAdmissionRegDetails(ParentRequiresAppointmentVO appointmentVo);

	String getValidateAdmissionNo(ParentRequiresAppointmentVO appointmentVo);
	


}
