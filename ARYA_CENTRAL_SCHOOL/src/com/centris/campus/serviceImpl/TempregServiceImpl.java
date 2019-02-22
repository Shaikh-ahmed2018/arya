package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.daoImpl.FormsManagementdaoImpl;
import com.centris.campus.daoImpl.SpecializationDaoImpl;
import com.centris.campus.vo.Issuedmenuvo;
import com.centris.campus.vo.ViewallSubjectsVo;

public class TempregServiceImpl {

	public ArrayList<Issuedmenuvo> getissuedforms() {
		// TODO Auto-generated method stub
		FormsManagementdaoImpl	dao= new FormsManagementdaoImpl();

		return dao.getissuedforms();
	}

	public String insertapprovedlist(String idList, String reason, String othereason, String mobile_number) {
		// TODO Auto-generated method stub
		FormsManagementdaoImpl	dao= new FormsManagementdaoImpl();
		return  dao.insertapprovedlist(idList,reason,othereason,mobile_number);

	}

	public ArrayList<Issuedmenuvo> getApprovedForms() {
		FormsManagementdaoImpl fdao= new FormsManagementdaoImpl();
		// TODO Auto-generated method stub
		return fdao.getApprovedForms(); 
	}

	public String insertrejectedList(String idList, String rejectreason, String otherrsn) {
		FormsManagementdaoImpl rlist=new FormsManagementdaoImpl();
		return rlist.insertrejectedList(idList,rejectreason,otherrsn);
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Issuedmenuvo> getRejectedlist() {
		// TODO Auto-generated method stub
		FormsManagementdaoImpl rejectlist=new FormsManagementdaoImpl();

		return rejectlist.getRejectedlist();
	}

	public ArrayList<Issuedmenuvo> sendmailtoparents() {
		// TODO Auto-generated method stub
		FormsManagementdaoImpl sendmail=new FormsManagementdaoImpl();
                return sendmail.sendmailtoparents();
	}


	public synchronized Issuedmenuvo getIssueddetails(Issuedmenuvo obj1) {
		// TODO Auto-generated method stub
		FormsManagementdaoImpl issuedForm=new FormsManagementdaoImpl();
		return issuedForm.getIssueddetails(obj1);
		}

	public Issuedmenuvo editIssuedForm(String edit) {
		// TODO Auto-generated method stub
		FormsManagementdaoImpl editissuedform=new FormsManagementdaoImpl();

		return editissuedform.editIssuedForm(edit);
	}

	public List<Issuedmenuvo> searchadmformDetails(String searchName) {
		// TODO Auto-generated method stub
		FormsManagementdaoImpl searchissuedform=new FormsManagementdaoImpl();

		return searchissuedform.searchadmformDetails(searchName);
	}

	public List<Issuedmenuvo> searchapproveformDetails(String searchName) {
		// TODO Auto-generated method stub
		FormsManagementdaoImpl searchapproveform=new FormsManagementdaoImpl();

		return searchapproveform.searchapproveformDetails(searchName);
	}

	public List<Issuedmenuvo> searchrejectformDetails(String searchName) {
		// TODO Auto-generated method stub
		FormsManagementdaoImpl rejectapproveform=new FormsManagementdaoImpl();

		return rejectapproveform.searchrejectformDetails(searchName);
	}

	public Issuedmenuvo apprIssuedForm(String edit) {
		FormsManagementdaoImpl detailsaprform=new FormsManagementdaoImpl();

		return detailsaprform.apprIssuedForm(edit);
	}

	public Issuedmenuvo rejectFormdetails(String edit) {
		FormsManagementdaoImpl detailsrejectform=new FormsManagementdaoImpl();

		return detailsrejectform.rejectFormdetails(edit);
	}

	public String insertcancelledlist(String idList, String reason, String canreason) {
		FormsManagementdaoImpl insertcancelledlist=new FormsManagementdaoImpl();

		return insertcancelledlist.insertcancelledlist(idList,reason,canreason);
	}

	public ArrayList<Issuedmenuvo> getCancelledForms() {
		FormsManagementdaoImpl cancelledlist=new FormsManagementdaoImpl();

		return cancelledlist.getCancelledForms();
	}

	public Issuedmenuvo cancelFormdetails(String edit) {
		FormsManagementdaoImpl canceldetails=new FormsManagementdaoImpl();

		return canceldetails.cancelFormdetails(edit);
	}

	public ArrayList<Issuedmenuvo> getSubmittedForms() {
		FormsManagementdaoImpl submittedform=new FormsManagementdaoImpl();

		return submittedform.getSubmittedForms();
	}

	public ArrayList<Issuedmenuvo> getProcessedForms() {
		FormsManagementdaoImpl processed=new FormsManagementdaoImpl();

		return processed.getProcessedForms();
	}

	public Issuedmenuvo submitFormdetails(String edit) {
		FormsManagementdaoImpl submitted=new FormsManagementdaoImpl();

		return submitted.submitFormdetails(edit);
	}

	public Issuedmenuvo processFormdetails(String edit) {
		FormsManagementdaoImpl processed=new FormsManagementdaoImpl();

		return processed.processFormdetails(edit);
	}

	public List<Issuedmenuvo> searchcancelformDetails(String searchName) {
		FormsManagementdaoImpl searchcancel=new FormsManagementdaoImpl();

		return searchcancel.searchcancelformDetails(searchName);
	}

	public List<Issuedmenuvo> searchsubmitformDetails(String searchName) {
		FormsManagementdaoImpl searchsubmit=new FormsManagementdaoImpl();

		return searchsubmit.searchsubmitformDetails(searchName);
	}

	public List<Issuedmenuvo> searchprocessformDetails(String searchName) {
		FormsManagementdaoImpl searchprocess=new FormsManagementdaoImpl();

		return searchprocess.searchprocessformDetails(searchName);
	}

	

}
