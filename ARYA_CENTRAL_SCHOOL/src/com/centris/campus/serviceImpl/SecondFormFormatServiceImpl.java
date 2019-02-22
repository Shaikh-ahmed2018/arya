package com.centris.campus.serviceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.centris.campus.dao.SecadmissionformatDao;
import com.centris.campus.daoImpl.SecadmissionFormDaoImpl;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.forms.Secondadmissionformformat;
import com.centris.campus.service.SecondFormFormatService;

public class SecondFormFormatServiceImpl implements SecondFormFormatService{

	SecadmissionformatDao dao =  new SecadmissionFormDaoImpl();
	
	public String InsertSecadmissionform(ParentRequiresAppointmentForm secform) {
		return  dao.InsertSecadmissionform(secform);
	}

	@Override
	public String InsertThirdadmissionform(ParentRequiresAppointmentForm secform) {
		return  dao.InsertThirdadmissionform(secform);
	}}
