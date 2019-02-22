<!DOCTYPE html>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>



<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="JS/newUI/jquery.js"></script>
<script src="JS/newUI/bootstrap.min.js"></script>

<script type="text/javascript" src="javascript.js"></script>

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>

<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>

<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>

<script type="text/javascript"
	src="JS/backOffice/Admin/AddDisplayDetails.js"></script>
</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span>Add Teacher Mapping Details</span>
		</p>
		
		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<logic:present name="errormessagediv" scope="request">
		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"><bean:write name="errormessagediv" scope="request" /></span></a>
			</div>
		</div>
		</logic:present>
		
		<div class="errormessagediv1"
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
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;<h4 class="panel-title">Class Teacher Mapping
								</h4></a>
					
							<div class="navbar-right">
							
							 <span id="save" class ="buttons"> Save</span>
							<span id="back" class="buttons">Back</span>
							</div>
						
					</div>
					
				
					<input type="hidden" id="classteacherid" value='<logic:present name="classteacherid"><bean:write name="classteacherid" property="classteacherid" /></logic:present>'></input>
					<input type="hidden" id="idclass" value="">
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6" 
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class
										Name</label>
									<div class="col-xs-7">
										<select class="form-control" id="classid" name="classname">
											
										</select>
									</div>
								</div>
								<br />

								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Teacher
										Name</label>
									<div class="col-xs-7">
										<select class="form-control" id="teacherid" name="teachername">
											
										</select>
									</div>
								</div>
								<br />
								
								
								
								<br />

								<br />
								
								<br />
								
								<br />
								
								<br />
							</div>
							
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Section
										Name</label>
									<div class="col-xs-7">
										<select class="form-control" id="sectionid" name="sectionname">
											
										</select>
									</div>
								</div>
								<br />
								
								<br />
								
								<br />
								
								<br />
								
								<br />
							</div>
						</div>
					</div>
				</div>
				
				
		</form>
	</div>
	</div>
</body>

</html>
