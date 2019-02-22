package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.dao.ParentRequiresAppointmentDAO;
import com.centris.campus.daoImpl.ParentRequiresAppointmentDAOIMPL;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.service.ParentRequiresAppointmentService;
import com.centris.campus.vo.AryasmartschoolVo;
import com.centris.campus.vo.ParentRequiresAppointmentVO;
import com.centris.campus.vo.ThirdformformatVO;
import com.centris.campus.vo.secadmissionformformatVO;
import com.centris.campus.vo.SecondAdmissionformVo;
import com.centris.campus.vo.ThirdAddmissionApplicationVo;

public class ParentRequiresAppointmentServiceImpl implements
		ParentRequiresAppointmentService {
	
	ParentRequiresAppointmentDAO dao = new ParentRequiresAppointmentDAOIMPL();

	public List<ParentRequiresAppointmentVO> getAdmisssionProcessingListDetails() {
		
		return dao.getAdmisssionProcessingListDetails();
	}

	
	public ParentRequiresAppointmentVO EditingForAdmissionApproval(
			ParentRequiresAppointmentVO detailsVo) {
		
		return dao.EditingForAdmissionApproval(detailsVo);
	}

	
	public String UpdatingFirstLevelAdmissionApproval(
			ParentRequiresAppointmentVO detailsVo) {
		
		return dao.UpdatingFirstLevelAdmissionApproval(detailsVo);
	}

	
	public String InsertTemporaryStudentRegistration(
			ParentRequiresAppointmentForm parentform) {
		
		return dao.InsertTemporaryStudentRegistration(parentform);
	}

	
	public List<ParentRequiresAppointmentVO> searchadmissionsList(
			String searchName) {
		
		return dao.searchadmissionsList(searchName);
	}

	
	public String DeleteParentRequiresAppointment(ParentRequiresAppointmentVO vo) {
		
		return dao.DeleteParentRequiresAppointment(vo);
	}

	
	public List<ParentRequiresAppointmentVO> CalledForEvaluationList() {
		return dao.CalledForEvaluationList();
	}

	
	public ParentRequiresAppointmentVO EditingForCalledAdmissionDetails(
			ParentRequiresAppointmentVO detailsVo) {
		
		return dao.EditingForCalledAdmissionDetails(detailsVo);
	}

	
	public String UpdatingCalledForEvaluationStatus(
			ParentRequiresAppointmentVO details) {
		
		return dao.UpdatingCalledForEvaluationStatus(details);
	}

	
	public List<ParentRequiresAppointmentVO> searchCalledForEvaluationList(
			String searchName) {
		
		return dao.searchCalledForEvaluationList(searchName);
	}


	@Override
	public List<ParentRequiresAppointmentVO> FinalAdmisssionList() {
		// TODO Auto-generated method stub
		return dao.FinalAdmisssionList();
	}


	@Override
	public ParentRequiresAppointmentVO EditingForConfirmingAdmissionDetails(
			ParentRequiresAppointmentVO detailsVo) {
		// TODO Auto-generated method stub
		return dao.EditingForConfirmingAdmissionDetails(detailsVo);
	}


	@Override
	public String UpdatingFinalApprovalAdmissionStatus(
			ParentRequiresAppointmentVO details) {
		// TODO Auto-generated method stub
		return dao.UpdatingFinalApprovalAdmissionStatus(details);
	}



	@Override
	public ArrayList<AryasmartschoolVo> getimageName(String id) {
		// TODO Auto-generated method stub
		return dao.getimageName(id);
	}


	@Override
	public ArrayList<SecondAdmissionformVo> downloadsecadmissionapplication(String parameter) {
		return dao.downloadsecadmissionapplication(parameter);
	}




	@Override
	public String Insertthirdadmissionformat(
			ParentRequiresAppointmentForm parentform) {
		// TODO Auto-generated method stub
		return dao.Insertthirdadmissionformat(parentform);
	}


	@Override
	public List<secadmissionformformatVO> getsecformadmissiondetails() {
		// TODO Auto-generated method stub
		return dao.getsecformadmissiondetails();
	}


	@Override
	public List<ThirdformformatVO> getthirdadmissiondetailslist() {
		// TODO Auto-generated method stub
		return dao.getthirdadmissiondetailslist();
	}


	@Override
	public ArrayList<ThirdAddmissionApplicationVo> downloadthirdAppform(String param) {
		return dao.downloadthirdAppform(param);
	}


	@Override
	public String getsecform() {
		// TODO Auto-generated method stub
		return dao.getsecform();
	}


	@Override
	public List<ParentRequiresAppointmentVO> getAdmissionRegDetails(ParentRequiresAppointmentVO appointmentVo) {
		// TODO Auto-generated method stub
		return dao.getAdmissionRegDetails(appointmentVo);
	}


	@Override
	public String getValidateAdmissionNo(ParentRequiresAppointmentVO appointmentVo) {
		// TODO Auto-generated method stub
		return dao.getValidateAdmissionNo(appointmentVo);
	}



	


}
