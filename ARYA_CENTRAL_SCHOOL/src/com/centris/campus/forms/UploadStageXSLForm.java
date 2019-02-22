package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class UploadStageXSLForm extends ActionForm  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FormFile formFile;

	public FormFile getFormFile() {
		return formFile;
	}

	public void setFormFile(FormFile formFile) {
		this.formFile = formFile;
	}
	

}
