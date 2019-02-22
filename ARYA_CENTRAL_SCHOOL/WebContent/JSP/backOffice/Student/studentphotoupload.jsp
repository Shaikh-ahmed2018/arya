<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<link href="/CSS/newUI/custome.css" rel="stylesheet">

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Student/Studentexcelfileupload.js"></script>

<style>
.form-control {  
	width: 70%;
}
</style>
<style>
.buttons{

vertical-align: 0;

}
/* #allstudent td{
	min-width:150px;
}
 */

#inputnames{
    text-align: right;
    padding: 7px 0px;
}
</style>

<style>
  #allstudent td:nth-child(7),td:nth-child(8){
 		min-width:4px;
  }
  #allstudent td:nth-child(1){
 		min-width:150px;
  }
  
</style>




</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Upload Students Photo</span>
		</p>



		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<logic:present name="errorMessage" scope="request">
			<div class="errormessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
								name="errorMessage" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>
		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>


		<form action="studentRegistration.html?method=saveStudentPhotoUpload"
			id="excelfileupload" name="StudentRegistrationForm" method="post"
			enctype="multipart/form-data">
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Upload Students Photo
							</h4></a>
						
						
						<div class="navbar-right">
							<span id="" class="buttons"><input type="submit" value="Upload" style="background-color: #07aab9;border:  none;">
							</Span>
						</div>
					</div>
					<script>
						$(document).ready(function() {
							$('[data-toggle="tooltip"]').tooltip();
						});
					</script>


					<div class="feebody panel-group" id="accordion" role="tablist"
						aria-multiselectable="true">
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne" align="center">
							<div class="panel-body">
								<div class="col-md-6" id="txtstyle" style="padding: 0;">
									<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4"
											id="inputnames">Choose File</label>
										<div class="col-xs-8">
											<input type="file" name="photoUpload" id="studentfile" multiple="multiple"
												class="form-control" style="height: auto;"></input>
										</div>
										<br />
									</div>
								</div>
								

							</div>

						</div>

					</div>

				</div>

			</div>

		</form>
		</div>
		
</body>

</html>
