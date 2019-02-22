package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.serviceImpl.TempregServiceImpl;
import com.centris.campus.vo.Issuedmenuvo;

public class TemporaryRegBD {
	
	public ArrayList<Issuedmenuvo> getissuedforms() {
		// TODO Auto-generated method stub
		TempregServiceImpl	service = new TempregServiceImpl();
		return service.getissuedforms();

	}

	public String insertapprovedlist(String idList, String reason, String othereason, String mobile_number) {
		// TODO Auto-generated method stub
		TempregServiceImpl insertapp=new TempregServiceImpl();
		return insertapp.insertapprovedlist(idList,reason,othereason,mobile_number);
	}

	public ArrayList<Issuedmenuvo> getApprovedForms() {
		// TODO Auto-generated method stub
		TempregServiceImpl apprapp=new TempregServiceImpl();

		return apprapp.getApprovedForms();
	}

	public String insertrejectedList(String idList, String rejectreason, String otherrsn) {
	TempregServiceImpl reject = new TempregServiceImpl();
	 return reject.insertrejectedList(idList,rejectreason,otherrsn);
		
	}

	public ArrayList<Issuedmenuvo> getRejectedlist() {
		// TODO Auto-generated method stub
		TempregServiceImpl rejectlist = new TempregServiceImpl();
		return rejectlist.getRejectedlist();
	}

	public ArrayList<Issuedmenuvo> sendmailtoparents() {
		// TODO Auto-generated method stub
		TempregServiceImpl rejectlist = new TempregServiceImpl();
		return rejectlist.sendmailtoparents();
	}

	

	public Issuedmenuvo getIssueddetails(Issuedmenuvo obj1) {
		// TODO Auto-generated method stub
		TempregServiceImpl issuedlist = new TempregServiceImpl();

		return issuedlist.getIssueddetails(obj1);
	}

	public Issuedmenuvo editIssuedForm(String edit) {
		// TODO Auto-generated method stub
		TempregServiceImpl editissued = new TempregServiceImpl();

		return editissued.editIssuedForm(edit);
	}

	public List<Issuedmenuvo> searchadmformDetails(String searchName) {
		// TODO Auto-generated method stub
		TempregServiceImpl searchissued = new TempregServiceImpl();

		return searchissued.searchadmformDetails(searchName);
	}

	public List<Issuedmenuvo> searchapproveformDetails(String obj1) {
		// TODO Auto-generated method stub
		TempregServiceImpl searchapproved = new TempregServiceImpl();

		return searchapproved.searchapproveformDetails(obj1);
	}

	public List<Issuedmenuvo> searchrejectformDetails(String searchName) {
		// TODO Auto-generated method stub
		TempregServiceImpl searchreject = new TempregServiceImpl();

		return searchreject.searchrejectformDetails(searchName);
	}

	public Issuedmenuvo apprIssuedForm(String edit) {
		TempregServiceImpl apprforms = new TempregServiceImpl();

		return apprforms.apprIssuedForm(edit);
	}

	public Issuedmenuvo rejectFormdetails(String edit) {
		TempregServiceImpl rejectforms = new TempregServiceImpl();

		return rejectforms.rejectFormdetails(edit);
		
	}

	public String insertcancelledlist(String idList, String reason, String canreason) {
		TempregServiceImpl insercancellist = new TempregServiceImpl();

		return insercancellist.insertcancelledlist(idList,reason,canreason);
	}

	public ArrayList<Issuedmenuvo> getCancelledForms() {
		TempregServiceImpl cancelform = new TempregServiceImpl();

		return cancelform.getCancelledForms();
	}

	public Issuedmenuvo cancelFormdetails(String edit) {
		TempregServiceImpl cancelformdetails = new TempregServiceImpl();

		return cancelformdetails.cancelFormdetails(edit);
	}

	public ArrayList<Issuedmenuvo> getSubmittedForms() {
		TempregServiceImpl submittedform = new TempregServiceImpl();

		return submittedform.getSubmittedForms();
	}

	public ArrayList<Issuedmenuvo> getProcessedForms() {
		TempregServiceImpl processedform = new TempregServiceImpl();

		return processedform.getProcessedForms();
	}

	public Issuedmenuvo submitFormdetails(String edit) {
		TempregServiceImpl submitform = new TempregServiceImpl();
         return submitform.submitFormdetails(edit);
	}

	public Issuedmenuvo processFormdetails(String edit) {
		TempregServiceImpl processform = new TempregServiceImpl();
        return processform.processFormdetails(edit);
	}

	public List<Issuedmenuvo> searchcancelformDetails(String searchName) {
		TempregServiceImpl searchcancel = new TempregServiceImpl();
        return searchcancel.searchcancelformDetails(searchName);
	}

	public List<Issuedmenuvo> searchsubmitformDetails(String searchName) {
		TempregServiceImpl searchsubmit = new TempregServiceImpl();
        return searchsubmit.searchsubmitformDetails(searchName);
	}

	public List<Issuedmenuvo> searchprocessformDetails(String searchName) {
		TempregServiceImpl searchprocess = new TempregServiceImpl();
        return searchprocess.searchprocessformDetails(searchName);
	}

}
