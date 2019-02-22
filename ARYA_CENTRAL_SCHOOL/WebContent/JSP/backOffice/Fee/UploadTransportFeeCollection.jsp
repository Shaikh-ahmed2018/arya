<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<link href="CSS/newUI/custome.css" rel="stylesheet">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Fee/UploadTransportFeeCollection.js"></script>

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
  #allstudent th:nth-child(1),th:nth-child(4){
 	width:100px;	
  }
  #allstudent th:nth-child(2),th:nth-child(6),th:nth-child(7){
  	width:125px;	
  }
 #allstudent{
 width: 1200px;
 }
 #allstudent th:nth-child(8){
 width: 80px;
 }
 .clearfix{
 margin-top: 10px;
 }
</style>




</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Upload Transport Fee Collection Details</span>
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

		<form action="feeupload.html?method=insertTransportFeeXSL" id="excelfileupload" name="feecollectionform" method="post" enctype="multipart/form-data">
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Upload Transport Fee Details
							</h4></a>
						
						<div class="navbar-right">
							<span id="saveid" class="buttons">Upload</Span>
						</div>
					</div>
					<script>
						$(document).ready(function() {
							$('[data-toggle="tooltip"]').tooltip();
						});
					</script>


					<div class="feebody panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne" align="center">
							<div class="panel-body">
								<div class="col-md-6" id="txtstyle" style="padding: 0;">
									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											id="inputnames">Choose File</label>
										<div class="col-xs-8">
											<input type="file" name="formfile" id="studentfile" class="form-control" style="height: auto;"></input>
										</div>
										<br />
									</div>
								</div>

								<div class="col-md-3" id="txtstyle" style="padding: 10px 0px;">
									<u><a href="feeupload.html?method=downloadtransportfileformat">Click here for Download File Format</a>
									</u>
								</div>

								<logic:present name="FailFeeList" scope="request">
									<div class="row">
									<div class="col-md-12">
										
										<p class="theading">
											<span class="displayno"></span>
										</p>
										<p>
											<font size="4">Following Records are not Uploaded!</font>
										</p>
										<div style="overflow-x: scroll;margin-bottom: 15px;">
										<display:table id="allstudent" name="requestScope.FailFeeList" class="table" requestURI="/feeupload.html?method=insertFeeXSL">
											<display:column property="admissionNo" title="AdmissionNo" headerClass="heading1" />
											<display:column property="acadamicyear" title="Academic Year" headerClass="heading1" />
											<display:column property="termid" title="Term" headerClass="heading1" />
											<display:column property="feeamount" title="Paid" headerClass="heading1" />
											<display:column property="noofmnths" title="No.Of Months" headerClass="heading1" />
											<display:column property="stmnth" title="Start Month" headerClass="heading1" />
											<display:column property="endmnth" title="End Month" headerClass="heading1" />
											<display:column property="paymentmode" title="Mode" headerClass="heading1" />
											<display:column property="reason" title="Reason" headerClass="heading1" />
										</display:table>
										</div>
									</div>
									<div class="col-md-12">
										<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
											<span  class='numberOfItem'></span>	
										</div>
										<div class='pagination pagelinks'></div>
									</div>
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
