<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Teacher/LeaveApproval.js"></script>

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

<style>
.buttons{

vertical-align: 0px;

}
.form-group{
margin-bottom: 10px;
}



</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id ="div-main"
		style="font-family: Roboto Medium; font-size: 20px; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="">
			<img src="images/addstu.png" />&nbsp;<span>Approve Leave</span>
		</p>

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessage"></span></a>
			</div>
		</div>

		<div class="errormessagediv1" align="center" style="display: none;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1" style="width: 30%;"><span
					class="validateTips1"></span></a>
			</div>
		</div>
		<input type="hidden" name="attnhidden" id="snoid"
			value="<logic:present name="attnhidden" ><bean:write name="attnhidden"/></logic:present>" />
		<input type="hidden" name="leavetype" id="leavetype"
			value="<logic:present name="leaveapprovelist" ><bean:write name="leaveapprovelist" property="leavetype"/></logic:present>" />


		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#" style="color: #767676"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Leave
							Details
						</h4></a>
					

					<div class="navbar-right">
						<span id="submit" class="buttons" style="top: 9px;margin-right: 1px;">Save</span>
						<span id="back" class="buttons" style="top: 9px;">Back</span>
					</div>
				</div>
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">

						<div class="col-md-6" id="txtstyle" style = "padding-top:10px;padding-bottom:7px;">

							<div class="form-group clearfix">

								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">
									RequestedBy </label>
								<div class="col-xs-7">
									<input type="text" name="requested" id="requested"
										readonly="readonly" class="form-control"
										value="<logic:present name="leaveapprovelist" ><bean:write name="leaveapprovelist" property="studentid"/></logic:present>"></input>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">From Date
								</label>
								<div class="col-xs-7">
									<input type="text" name="fromdate" id="fromdate"
										readonly="readonly" placeholder="Click Here"
										class="form-control"
										value='<logic:present name="leaveapprovelist" ><bean:write name="leaveapprovelist" property="fromdate" /></logic:present>'
										onchange="setEndDate()"></input>
								</div>
							</div>
						

							<div class="form-group clearfix">

								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Session(Start)

								</label>
								<div class="col-xs-7">
									<select name="startsessionDay" id="startsessionDay"
										class="form-control">
										<option value="">-----------Select-----------</option>
										<option value="FH">First Half</option>
										<option value="SH">Second Half</option>
									</select>
								</div>
							</div>
					
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;"> Total
									Leave </label>
								<div class="col-xs-7">
									<input type="text" name="totalleaves" readonly="readonly" 
										id="totalleaves" class="form-control"
										value="<logic:present name="leaveapprovelist" ><bean:write name="leaveapprovelist" property="totalleave"/></logic:present>"></input>
								</div>
							</div>
						
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Start
									Date</label>
								<div class="col-xs-7">

									<input type="text" name="approvedstartdate" onkeypress="HideError()"
										id="approvedstartdate" placeholder="Click Here"
										class="form-control" value='<logic:present name="leaveapprovelist" ><bean:write name="leaveapprovelist" property="fromdate" /></logic:present>'></input>
								</div>
							</div>


							
							<div class="form-group clearfix">

								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Session(Start)

								</label>

								<div class="col-xs-7">
									<select name="approvedstartsessionDay" onkeypress="HideError()"
										id="approvedstartsessionDay" class="form-control">
										<option value="">-----------Select-----------</option>
										<option value="FH">First Half</option>
										<option value="SH">Second Half</option>


									</select>
								</div>
							</div>
						
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;"> Total Approve
									Leaves </label>
								<div class="col-xs-7">
									<input type="text" name="leavesapproved" id="leavesapproved" onkeypress="HideError()"
										class="form-control"></input>
								</div>
							</div>
							 
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Leave
									Status </label>
								<div class="col-xs-7">
									<select name="approvedleavestatus" id="approvedleavestatus" onkeypress="HideError()"
										class="form-control">
										<option value="">-----------Select-----------</option>
										<option value="Approved">APPROVE</option>
										<option value="Rejected">REJECT</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6" id="txtstyle" style = "padding-top:10px;">
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Leave
									Type
								</label>
								<div class="col-xs-7">
									<logic:present name="LeaveList" scope="request">
								         <select class="form-control" name="leavetype" id="leavetype" onkeypress="HideError()" disabled="disabled">
								           <option value="">----------Select----------</option>
									       <logic:iterate id="LeaveList" name="LeaveList" scope="request">
											<option value='<bean:write name='LeaveList' property='leaveID'/>'>
											<bean:write name='LeaveList' property='leaveName'/></option>
										    </logic:iterate>
							              </select>
									</logic:present>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">To Date</label>
								<div class="col-xs-7">
									<input type="text" name="todate" id="todate"
										readonly="readonly" placeholder="Click Here"
										class="form-control"
										value='<logic:present name="leaveapprovelist" ><bean:write name="leaveapprovelist" property="todate" /></logic:present>'></input>
								</div>
							</div>
							
							<div class="form-group clearfix">

								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Session(End)
								</label>

								<div class="col-xs-7">
									<select name="endsessionDay" id="endsessionDay"
										class="form-control">
										<option value="">-----------Select-----------</option>
										<option value="FH">First Half</option>
										<option value="SH">Second Half</option>
									</select>
								</div>
							</div>
						
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Reason </label>
								<div class="col-xs-7">
									<textarea style="font-size: 15px;" rows="2" name="reason" class="form-control" readonly="readonly" id="reason"><logic:present name="leaveapprovelist"><bean:write name="leaveapprovelist" property="reason" /></logic:present></textarea>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">End Date</label>
								<div class="col-xs-7">
									<input type="text" name="approvedenddate" id="approvedenddate" onkeypress="HideError()"
										placeholder="Click Here" class="form-control" value='<logic:present name="leaveapprovelist" ><bean:write name="leaveapprovelist" property="todate" /></logic:present>'></input>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">
									Session(End) </label>
								<div class="col-xs-7">
									<select name="approvedendsessionDay" id="approvedendsessionDay" onkeypress="HideError()"
										class="form-control">
										<option value="">-----------Select-----------</option>
										<option value="FH">First Half</option>
										<option value="SH">Second Half</option>
									</select>
								</div>
							</div>
							
						<!-- 	<div class="form-group" style="visibility: hidden">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">DG</label>
								<div class="col-xs-7">
									<input type="text" placeholder="Click Here"
										class="form-control"></input>

								</div>
							</div>
							<br /> -->
							
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Comments
								</label>
								<div class="col-xs-7">
									<textarea style="font-size: 15px;" name="comments" class="form-control" id="comments">
									</textarea>
								</div>
							</div>
						</div>
						<input type="hidden" id="duplicateid"
							value='<logic:present name="status" scope="request"><bean:write name="status" scope="request" /></logic:present>'>
						<input type="hidden" id="hiddenleavetype"
							value='<logic:present name="leaveapprovelist" scope="request"><bean:write name="leaveapprovelist" scope="request" property="leavetype"/></logic:present>'>
						<input type="hidden" id="hiddenstartsession"
							value='<logic:present name="leaveapprovelist" scope="request"><bean:write name="leaveapprovelist" scope="request" property="startsessionDay"/></logic:present>'>
						<input type="hidden" id="hiddenendsession"
							value='<logic:present name="leaveapprovelist" scope="request"><bean:write name="leaveapprovelist" scope="request" property="endsessionDay"/></logic:present>'>

					</div>
				</div>

			</div>
		</div>
	</div>
</body>

</html>
