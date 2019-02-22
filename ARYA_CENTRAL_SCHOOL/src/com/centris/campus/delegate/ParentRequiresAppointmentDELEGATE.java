package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.service.ParentRequiresAppointmentService;
import com.centris.campus.serviceImpl.ParentRequiresAppointmentServiceImpl;
import com.centris.campus.vo.AryasmartschoolVo;
import com.centris.campus.vo.ParentRequiresAppointmentVO;
import com.centris.campus.vo.ThirdformformatVO;
import com.centris.campus.vo.secadmissionformformatVO;
import com.centris.campus.vo.SecondAdmissionformVo;
import com.centris.campus.vo.ThirdAddmissionApplicationVo;

public class ParentRequiresAppointmentDELEGATE {
	
	ParentRequiresAppointmentService service = new ParentRequiresAppointmentServiceImpl();

	public List<ParentRequiresAppointmentVO> getAdmisssionProcessingListDetails() {
		// TODO Auto-generated method stub
		return service.getAdmisssionProcessingListDetails();
	}

	public ParentRequiresAppointmentVO EditingForAdmissionApproval(ParentRequiresAppointmentVO detailsVo) {
		// TODO Auto-generated method stub
		return service.EditingForAdmissionApproval(detailsVo);
	}

	public String UpdatingFirstLevelAdmissionApproval(
			ParentRequiresAppointmentVO detailsVo) {
		// TODO Auto-generated method stub
		return service.UpdatingFirstLevelAdmissionApproval(detailsVo);
	}

	public String InsertTemporaryStudentRegistration(
			ParentRequiresAppointmentForm parentform) {
		// TODO Auto-generated method stub
		return service.InsertTemporaryStudentRegistration(parentform);
	}

	public List<ParentRequiresAppointmentVO> searchadmissionsList(
			String searchName) {
		// TODO Auto-generated method stub
		return service.searchadmissionsList(searchName);
	}

	public String DeleteParentRequiresAppointment(ParentRequiresAppointmentVO vo) {
		// TODO Auto-generated method stub
		return service.DeleteParentRequiresAppointment(vo);
	}

	public List<ParentRequiresAppointmentVO> CalledForEvaluationList() {
		// TODO Auto-generated method stub
		return service.CalledForEvaluationList();
	}

	public ParentRequiresAppointmentVO EditingForCalledAdmissionDetails(
			ParentRequiresAppointmentVO detailsVo) {
		// TODO Auto-generated method stub
		return service.EditingForCalledAdmissionDetails(detailsVo);
	}

	public String UpdatingCalledForEvaluationStatus(
			ParentRequiresAppointmentVO details) {
		// TODO Auto-generated method stub
		return service.UpdatingCalledForEvaluationStatus(details);
	}

	public List<ParentRequiresAppointmentVO> searchCalledForEvaluationList(
			String searchName) {
		// TODO Auto-generated method stub
		return service.searchCalledForEvaluationList(searchName);
	}

	public List<ParentRequiresAppointmentVO> FinalAdmisssionList() {
		// TODO Auto-generated method stub
		return service.FinalAdmisssionList();
	}

	public ParentRequiresAppointmentVO EditingForConfirmingAdmissionDetails(
			ParentRequiresAppointmentVO detailsVo) {
		// TODO Auto-generated method stub
		return service.EditingForConfirmingAdmissionDetails(detailsVo);
	}

	public String UpdatingFinalApprovalAdmissionStatus(
			ParentRequiresAppointmentVO details) {
		// TODO Auto-generated method stub
		return service.UpdatingFinalApprovalAdmissionStatus(details);
	}


	public ArrayList<AryasmartschoolVo> getimageName(String string) {
		return service.getimageName(string);
	}

	public ArrayList<SecondAdmissionformVo> downloadsecadmissionapplication(String parameter) {
		return service.downloadsecadmissionapplication(parameter);
	}



	public String Insertthirdadmissionformat(
			ParentRequiresAppointmentForm parentform) {
		// TODO Auto-generated method stub
	    return service.Insertthirdadmissionformat(parentform);
	}

	

	public List<secadmissionformformatVO> getsecformadmissiondetails() {
		// TODO Auto-generated method stub
		return service.getsecformadmissiondetails();
	}

	public List<ThirdformformatVO> getthirdadmissiondetailslist() {
		// TODO Auto-generated method stub
		return service.getthirdadmissiondetailslist();
	}

	public ArrayList<ThirdAddmissionApplicationVo> downloadthirdAppform(String parameter) {
		return service.downloadthirdAppform(parameter);
	}

	public String getsecform() {
		// TODO Auto-generated method stub
		return service.getsecform();
	}

	public List<ParentRequiresAppointmentVO> getAdmissionRegDetails(ParentRequiresAppointmentVO appointmentVo) {
		// TODO Auto-generated method stub
		return service.getAdmissionRegDetails(appointmentVo);
	}

	public String getValidateAdmissionNo(ParentRequiresAppointmentVO appointmentVo) {
		// TODO Auto-generated method stub
		return service.getValidateAdmissionNo(appointmentVo);
	}
	


}
