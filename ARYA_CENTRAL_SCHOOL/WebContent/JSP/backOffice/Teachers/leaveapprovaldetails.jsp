<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>

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


<!-- <script type="text/javascript"
	src="JS/backOffice/Teacher/LeaveRequest.js"></script> -->


<script type="text/javascript"
	src="JS/backOffice/Teacher/LeaveRequest.js"></script>



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
	<form id="leaverequestid"
		action="teachermenuaction.html?method=leaveRequestEntry" method="post"
		enctype="multipart/form-data">
		<div class="col-md-10 col-md-offset-2"
			style="font-family: Roboto Medium; font-size: 20px; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
			<p style="">
				<img src="images/addstu.png" />&nbsp;<span>Leave Approval Details </span>
			</p>
			

			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Leave Approval Details
							</h4></a>

						

						<div class="navbar-right" >
						 <span id="back" class="buttons" style ="top: 9px;margin-right: 7px;">Back</span>
							</a>
						</div>
					</div>

					<input type="hidden" name="userid" id="userid"
						value="<logic:present name="parentid" ><bean:write name="parentid"  /></logic:present>" />

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" id="tabletxt">

							<div class="col-md-6" id="txtstyle" style = "padding-top:15px;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Approved By 
										 </label>
										<div class="col-xs-7">
										<input type="text" name="approvedby" id="approvedby" 
											class="form-control" readonly="readonly"
											value="<logic:present name="leaveapprovaldetails" ><bean:write name="leaveapprovaldetails" property="userid"/></logic:present>"></input>
									</div>
									</div>
									<br />
									
									<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Approved Start Date 
										 </label>
										<div class="col-xs-7">
										<input type="text" name="approvedstartdate" id="approvedstartdate" 
											class="form-control" readonly="readonly"
											value="<logic:present name="leaveapprovaldetails" ><bean:write name="leaveapprovaldetails" property="approvedstartdate"/></logic:present>"></input>
									</div>
									</div>
									<br />
								
									<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Approved Session(S) 
										 </label>
										<div class="col-xs-7">
										<select   name="approvedstartsession" id="approvedstartsession" 
											class="form-control" readonly="readonly" disabled="true">
											 <option value="">----Select----</option>
												         <option value="FH">First Half</option>
														<option value="SH">Second Half</option>
									</select>
									</div>
									</div>
								<br />
								
									<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Approved Leaves 
										 </label>
										<div class="col-xs-7">
										<input type="text" name="approvedleaves" id="approvedleaves" 
											class="form-control" readonly="readonly"
											value="<logic:present name="leaveapprovaldetails" ><bean:write name="leaveapprovaldetails" property="leavesapproved"/></logic:present>"></input>
									</div>
									</div>
								
									<input type="hidden" name="approvedstartsession" id="happrovedstartsession" value="<logic:present name="leaveapprovaldetails" ><bean:write name="leaveapprovaldetails" property="approvedstartsessionDay"/></logic:present>"></input>
									
								</div>

							<div class="col-md-6" id="txtstyle" style = "padding-top:15px;">

                                <div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Approved Date 
										 </label>
										<div class="col-xs-7">
										<input type="text" name="approveddate" id="approveddate" 
											class="form-control" readonly="readonly"
											value="<logic:present name="leaveapprovaldetails" ><bean:write name="leaveapprovaldetails" property="approveddate"/></logic:present>"></input>
									</div>
									</div>
								
								<br />

								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Approved End Date 
										 </label>
										<div class="col-xs-7">
										<input type="text" name="approveendddate" id="approveendddate" 
											class="form-control" readonly="readonly"
											value="<logic:present name="leaveapprovaldetails" ><bean:write name="leaveapprovaldetails" property="approvedenddate"/></logic:present>"></input>
									</div>
									</div>
									
									<br />

									<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Approved Session(E) 
										 </label>
										<div class="col-xs-7">
										<select name="approveendsession" id="approveendsession" 
											class="form-control" readonly="readonly" disabled="true">
											<option value="">----Select----</option>
												         <option value="FH">First Half</option>
														<option value="SH">Second Half</option>
									</select>
									</div>
									</div>
									<br /> 
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Comments 
										 </label>
										<div class="col-xs-7">
									<textarea style="font-size: 12px;"  name="comments" readonly="readonly"
										class="form-control" id="comments"> <logic:present name="leaveapprovaldetails" ><bean:write name="leaveapprovaldetails" property="comments"/></logic:present>
										</textarea>

									</div>
									</div>
								<br /> <br />
								
									
								
								
							   <input type="hidden" name="happroveendsession" id="happroveendsession" 
                      	value="<logic:present name="leaveapprovaldetails" ><bean:write name="leaveapprovaldetails" property="approvedendsessionDay"/></logic:present>"></input>
							
								

							</div>
							
                   

						</div>


					</div>

				</div>
			</div>
		</div>
	</form>
</body>

</html>
