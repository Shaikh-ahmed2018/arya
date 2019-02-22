package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class SettingExcelUploadForm extends ActionForm{
	
private FormFile formFile;

public FormFile getFormFile() {
	return formFile;
}

public void setFormFile(FormFile formFile) {
	this.formFile = formFile;
}

}
