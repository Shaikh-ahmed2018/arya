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
	src="JS/backOffice/Library/subscriberdetailexcelfileupload.js"></script>

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
  #back1,#saveid{
  margin-right:5px;
  }
</style>
</head>
<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Upload Subscriber Detail Data From File</span>
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

		<form action="uploadLibraryXSL.html?method=insertSubscriberDetailsXSL"
			id="excelfileupload" name="UploadLibraryXSLForm" method="post"
			enctype="multipart/form-data">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Upload Subscriber Detail Data From File
							</h4></a>
						<div class="navbar-right">
							<span id="saveid" class="buttons">Upload
							</Span>
							
							<a href="LibraryMenu.html?method=subscribersDetails">
						<span id="back1" class="buttons">Back
							</Span> </a>
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
											<input type="file" name="formFile" id="studentfile"
												class="form-control" style="height: auto;"></input>
										</div>
										<br />
									</div>
								</div>

								<div class="col-md-3" id="txtstyle" style="padding: 10px 0px;">
									<u><a href="uploadLibraryXSL.html?method=downloadsubscriberdetailfileformat">Click
										here for Download File Format</a>
									</u>
								</div>
								
								<logic:present name="FailEmployeeList" scope="request">
								<div class="col-md-12"
										style="padding: 0px; height: 250px; overflow: scroll;">
										<p class="theading">
											<span class="displayno"></span>
										</p>
										<p>
											<font size="4">Following Records are not Uploaded!</font>
										</p>

										<%-- 			  					<display:table class="table" id="allstudent"
 --%>

										<%-- 		       <display:table id="tableid" name="requestScope.FailEmployeeList" class="table table-bordered"
 --%>
							<display:table id="allstudent" name="requestScope.FailEmployeeList" class="table"
											requestURI="/uploadLibraryXSL.html?method=insertCategoryTypeXSL">
							<display:column property="reasone" title="Reasone" headerClass="heading1" />
							<display:column property="location" title="Location" headerClass="heading1" />
							<display:column property="subscriberType" title="Subscriber Type" headerClass="heading1" />
							<display:column property="subscriberNumber" title="Subscriber Number" headerClass="heading1" />
							<display:column property="admissionNumber" title="Admis.No/StaffId" headerClass="heading1" />
							<display:column property="name" title="Others Name" headerClass="heading1" />
							<display:column property="depositType" title="Deposit Type" headerClass="heading1" />
							<display:column property="status" title="Status" headerClass="heading1" />
							<display:column property="date" title="Reg.Date" headerClass="heading1" />
							
							</display:table>
						</div>
									<div class="paginationbox">
										<p class="paginationp"></p>
									</div>
								</logic:present>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
</body>
</html>
