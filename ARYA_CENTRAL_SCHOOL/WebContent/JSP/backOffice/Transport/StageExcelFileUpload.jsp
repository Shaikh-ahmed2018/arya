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
	src="JS/backOffice/Transport/StageExcelFileUpload.js"></script>

<style>
.form-control { 
	width: 70%;
}
#allstudent td:nth-child(1){
width:auto;
}
#allstudent td{
	min-width:200px;
}
#inputnames{
    text-align: right;
    padding: 7px 0px;
}

#allstudent tr td {
    font-weight: 20;
}




</style>
<style>
.buttons{

vertical-align: 0;

}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Upload Stage Excel Data File</span>
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
			<div class="errormessagediv" align="center" style="width: 1200px;">
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


		<form action="uploadStageXSL.html?method=insertStageXSL"
			id="excelfileupload" name="UploadStudentXSLForm" method="post"
			enctype="multipart/form-data">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676;vertical-align: text-top;"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Upload Stage Excel Data File
							</h4></a>
						
						<div class="navbar-right">
							<span id="saveid" class="buttons"  data-toggle="tooltip"
								data-placement="bottom">Upload
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
											<input type="file" name="formFile" id="stageFile"
												class="form-control" style="height:auto;"></input>
										</div>
										<br />
									</div>
								</div>

								<div class="col-md-3" id="txtstyle" style="padding: 10px 0px;">
									<font size="3"><u><a href="uploadStageXSL.html?method=downloadStageFileFormat">Click
										here for Download File Format</a></u></font>
								</div>


								
								<logic:present name="failedStageList" scope="request">
									<div class="col-md-12"
										style="padding: 0px; height: 250px; overflow: scroll;">
										
										<p class="theading">
											<span class="displayno"></span>
										</p>
										<p>
											<font size="4">Following Records are not Uploaded!</font>
										</p>
									
									
										<display:table id="allstudent"
											name="requestScope.failedStageList" class="table"
											requestURI="/uploadStageXSL.html?method=insertStageXSL">

											<display:column property="stage_name"
												title="Stage" headerClass="heading1" style="font-weiht normal;" />
											<display:column property="stage_description" title="Description"
												headerClass="heading1" />
											<display:column property="amount"
												title="Amount" headerClass="heading1" />
											<display:column property="reason"
												title="Reason" headerClass="heading1" />
										

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
