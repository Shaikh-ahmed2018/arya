<!DOCTYPE html>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="JS/newUI/jquery.js"></script>
<script src="JS/newUI/bootstrap.min.js"></script>

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>

<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>

<script type="text/javascript"
	src="JS/backOffice/Settings/addremainderdetails.js"></script>
<style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Add
				Remainder Details</span>
		</p>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<logic:present name="errormessagediv" scope="request">
			<div class="errormessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><bean:write name="errormessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="errormessagediv1" align="center"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 50%; font-size: 11pt; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>




		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Remainder Details
							</h4></a>
						
						<div class="navbar-right">
							<span id="save" class="buttons"
								data-toggle="tooltip" data-placement="bottom" title="Save">Save
							</span>
							&nbsp;
				
							<span id="back" class="buttons">Back</span>
						</div>
					</div>



					
					</br> <input type="hidden" id="remainderid"
						value='<logic:present name="editremainderDetails"><bean:write name="editremainderDetails" property="id" /></logic:present>'></input>
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Remainder
										Tittle</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="remaindername" onkeypress="HideError()"
											value='<logic:present name="editremainderDetails"><bean:write name="editremainderDetails" property="name" /></logic:present>'></input>
									</div>
								</div>
								<br /> <br />



								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Remainder
										To</label>

									<div class="col-xs-7">
										<input type="hidden" id="radio"
											value='<logic:present name="editremainderDetails"><bean:write name="editremainderDetails" property="remtype" /></logic:present>'></input>
										<input type="radio" class="remainto" name="remain"
											id="remainIdAll" value="All" /><label for="Remainder">All</label>
										<br /> <input type="radio" class="remainto" name="remain"
											id="remainIdBack" value="Backoffice" /><label
											for="Remainder">Back Office</label> <br /> <input
											type="radio" class="remainto" name="remain" id="remainIdTea"
											value="Teachers" /><label for="Remainder">Teachers</label> <br />
										<input type="radio" class="remainto" name="remain"
											id="remainIdPar" value="Parents" /><label for="Remainder">Parents/Students</label>
									</div>
								</div>
								<br /> <br /> <br /> <br /> <br />
							</div>

							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="remdescription"
											value='<logic:present name="editremainderDetails"><bean:write name="editremainderDetails" property="description" /></logic:present>'></input>
									</div>
								</div>
								<br /> <br /> <br /> <br /> <br />
							</div>
						</div>
					</div>
				</div>
		</form>
	</div>
	</div>
</body>

</html>
