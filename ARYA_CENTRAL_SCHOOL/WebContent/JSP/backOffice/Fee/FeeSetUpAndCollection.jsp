<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script> 

<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>

<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>

<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
<link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
  
<!--  <script type="text/javascript"
	src="JS/backOffice/Reports/StudentBonafiedCertificateReport.js"></script> -->
	
<script type="text/javascript" src="JS/backOffice/Fee/FeeSetupAndCollection.js"></script>
	
<style>
.save:hover {
	cursor: pointer;
}
</style>

<style>
#list:hover {
	cursor: pointer;
}
</style>


</head>



<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Fee Collection
			</span>
		</p>
     <logic:present name="successMessage" scope="request">

				<div class="successmessagediv" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
									name="successMessage" scope="request" />
						</span></a>
					</div>
				</div>

			</logic:present>

			<logic:present name="errorMessage" scope="request">

				<div class="errormessagediv1" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
									name="errorMessage" scope="request" />
						</span></a>
					</div>

				</div>

			</logic:present>
			
		<div class="errormessagediv" align="center" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips"></span></a>
							</div>
						</div>
				
			
					
						<div class="successmessagediv" align="center"  style="display: none;">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>
			
			
			

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Fee Collection 
							 
						</h4></a>
						
						

						<div class="navbar-right">
						
							<!-- <img src="images/rightline.png" style="margin-top: -2px;margin-right: 11px;"> -->
							<!-- <img src="images/download.png" class="download" id="iconsimg" style="margin-top:4px"
								data-toggle="modal" data-target="#myModal" data-toggle="tooltip"
								data-placement="bottom" title="Download"> -->

						</div>
						
						<script>
							$(document).ready(function() {
								$('[data-toggle="tooltip"]').tooltip();
							});
						</script>
				
				</div>
				
				
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Download</h4>
								</div>
								<div class="modal-body">
									<!-- <span id="excelDownload"><img src="images/xl.png"
										class="xl"></span> -->
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
										id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
								</div>

							</div>
						</div>
					</div>
				
				
		<input type="hidden" name="userid"	id="userid" value="<logic:present name="parentid" ><bean:write name="parentid"  /></logic:present>"  />			
				
				
				
				
				
				
	<form id="stuattnid" action="feecollection.html?method=getFeeCollectionAndSetup" method="post" enctype="multipart/form-data">
				
				
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					
					<div class="panel-body" id="tabletxt">

						<div class="col-md-6" id="txtstyle">
							
							
							
							<div class="form-group">
									
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Academic Year
								</label>		
								
									<div class="col-xs-7">
										<select id="accyear" name="accyear" class="form-control"
											>
											<option value=""></option>

											<logic:present name="AccYearList">

												<logic:iterate id="AccYear" name="AccYearList">

													<option
														value="<bean:write name="AccYear" property="accyearId"/>">
														<bean:write name="AccYear" property="accyearname" />
													</option>

												</logic:iterate>

											</logic:present>
										</select>
									</div>
								</div>
						
							<br />
							
							
							
							<div class="form-group">
									
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Section
								</label>		
										
										
										
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control"
											required>
											<option value=""></option>

											
										</select>
									</div>
								</div>
							
							<br />
							
							
							
							
							
							<%-- <div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description
									</label>
									<div class="col-xs-7">
										<textarea name="address" id="description" class="form-control"><logic:present name="driverlist"><bean:write name="driverlist" property="address"/></logic:present></textarea>
									</div>
								</div> --%>
							<br />
					
			
						</div>
						<div class="col-md-6" id="txtstyle">




								<div class="form-group">
								
										
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Class
								</label>		
										
										
										
									<div class="col-xs-7">
										<select id="classname" name="classname" class="form-control"
											required>
											<option value=""></option>

											<logic:present name="classlist">

												<logic:iterate id="AccYear" name="classlist">

													<option
														value="<bean:write name="AccYear" property="classId"/>">
														<bean:write name="AccYear" property="classname" />
													</option>

												</logic:iterate>

											</logic:present>
										</select>
									</div>
								</div>
							
							<br />



						 <div class="form-group">
								
										
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Student Name
								</label>		
										
										
										
									<div class="col-xs-7">
										<select id="studentname" name="studentname" class="form-control"
											required>
											<option value=""></option>

										
										</select>
									</div>
								</div>
							
							<br />

<br />
							</div>
							
							
							
							
							
							<button type="submit" class="btn btn-info col-md-offset-5"
								id="search" onclick="return validate();">Search</button>
							<!-- data-toggle="modal" data-target="#myModal" -->
							
							
						
	               <input type="hidden" name="defaultprofile"	id="hiddenprofile" value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="fileupload" /></logic:present>"  />							
							
							
							<input type="hidden" id="hiddenrequestto"
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="requesttoid" /></logic:present>'>
								
								<input type="hidden" id="hiddenleavetype"
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="leavetype"/></logic:present>'>
								
							<input type="hidden" id="hiddensno" name="sno"
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="sno" /></logic:present>'>	
								
								
							<input type="hidden" id="hiddenstartsession"
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="starttime" /></logic:present>'>		
								
								
							<input type="hidden" id="hiddenendsession" 
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="endtime" /></logic:present>'>		
						
							<input type="hidden" id="hiddenstudent" name="studentFname"
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="studentFname" /></logic:present>'>		
							
							
							
							
					<input type="hidden" name="userhidden" class="userhiddenclass" id="userhiddenid" 
							value='<logic:present name="parentid"><bean:write name="parentid" />
													</logic:present>'></input>
													
					<input type="hidden" id="successid" 
							value='<logic:present name="success"><bean:write name="success" />
													</logic:present>'></input>		
							
					<input type="hidden" id="countId" 
							value='<logic:present name="feeCount"><bean:write name="feeCount" />
													</logic:present>'></input>		
						</div>
						
					
		
					</div>
					</form>
			
				</div>

			</div>
		</div>


</body>

</html>
