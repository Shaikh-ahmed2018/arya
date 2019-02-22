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

<script type="text/javascript"src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>

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
	src="JS/backOffice/Teacher/LeaveRequestEntry.js"></script>
	
	
	
<style>
.save:hover {
	cursor: pointer;
}

#allstudent th:nth-child(1){

width : 150px;
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
<form id="leaverequestid" action="teachermenuaction.html?method=leaveRequestEntry" method="post" enctype="multipart/form-data">
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20px; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="">
			<img src="images/addstu.png" />&nbsp;<span>Leave Request
			</span>
		</p>
      <div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Leave
							Request Details
						</h4></a>
						
						

						<div class="navbar-right" style = "top:2px;">
								<span class="buttons" id="save" style ="margin-right:1px;">Save</span>
						
								<span id="back" class="buttons">Back</span>
							
							
						</div>
				
				</div>
				
				
		<input type="hidden" name="userid"	id="userid" value="<logic:present name="parentid" ><bean:write name="parentid"  /></logic:present>"  />			
		
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body"  id="tabletxt">

						<div class="col-md-6" id="txtstyle" style="padding-top: 10px;">
				
						 	<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Requested
									To
								</label>
								<div class="col-xs-7">
								         <select class="form-control" name="requestto" id="requesttoid" onkeypress="HideError()" tabindex="1">
								          <logic:present name="userlist" scope="request">
								           <option value="">----------Select----------</option>
									       <logic:iterate id="stream" name="userlist" scope="request"><option value='<bean:write name='stream' property='eployeecode'/>'><bean:write name='stream' property='teachername'/></option></logic:iterate></logic:present>
							              </select>
								</div>
							</div> 
							
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">From Date
								</label>
								<div class="col-xs-7">
									<input type="text" name="fromdate" id="Fromdate" tabindex="3"
										readonly="readonly" onkeypress="HideError()" 
										class="form-control" value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="fromdate"/></logic:present>"></input>
								</div>
							</div>
					
						
						<div class="form-group clearfix">
						
						<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Session(Start)
									
								</label>
								<div class="col-xs-7">
									<select name="starttime" id="startsessionDay" onkeypress="HideError()" class="form-control" tabindex="5">
										<option value="">----------Select----------</option>
										<option value="FH">First Half</option>
										<option value="SH">Second Half</option>
										
							
									</select>
								</div>
							</div>		
						
								
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Reason
								</label>
								<div class="col-xs-7" style = "padding-bottom: 10px;">
									<textarea style="font-size: 15px;" name="reason" rows="4" class="form-control" id="reason" onkeypress="HideError()" tabindex="7" ><logic:present name="leavedatails"><bean:write name="leavedatails" property="reason" /></logic:present></textarea>
								</div>
							</div>
							
						</div>
						<div class="col-md-6" id="txtstyle" style="padding-top: 10px;">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Leave
									Type
								</label>
								<div class="col-xs-7">
									
								         <select class="form-control" name="leavetype" id="leavetype" onkeypress="HideError()" tabindex="2">
								           <logic:present name="LeaveList" scope="request">
								           <option value="">----------Select----------</option>
								          
									       <logic:iterate id="LeaveList" name="LeaveList" scope="request">
											<option value='<bean:write name='LeaveList' property='leaveID'/>'><bean:write name='LeaveList' property='leaveName'/></option></logic:iterate>
										    </logic:present>
										     <option value="LWA">LWA</option>
							              </select>
									
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">To Date</label>
								<div class="col-xs-7">
									<input type="text" name="todate" id="todate" tabindex="4"
										readonly="readonly" 
										class="form-control" onkeypress="HideError()"
										 value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="todate"/></logic:present>" />
									
								</div>
							</div>
						
							
							<div class="form-group clearfix">
						
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Session(End)
								</label>
								
								<div class="col-xs-7">
									<select name="endtime" id="endsessionDay" class="form-control" onkeypress="HideError()" tabindex="6">
										<option value="">----------Select----------</option>
										<option value="FH">First Half</option>
										<option value="SH">Second Half</option>
									</select>
								</div>
									
							</div>	
					
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;"> Total
									Leave
								</label>
								<div class="col-xs-7">
									<input type="text" name="totalleave" id="totalleaves" readonly tabindex="8"
										class="form-control" value="<logic:present name="leavedatails"><bean:write name="leavedatails" property="totalleave"/></logic:present>"></input>
								</div>
								<!-- <span class="submitbutton" onclick="view()" data-toggle="modal" data-target="#myModal" style="cursor:pointer;float: right;    margin: 0px 56px -12px 0px;text-decoration:underline;">Leave Balance</span> -->
							</div>
						
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">File
									Upload
								</label>
								<div class="col-xs-7">
									<input type="file" name="fileupload" id="fileupload" tabindex="9"
										class="form-control">
										<br /></input>
										<input type="button" id="document2btn" name="idproof" class="downloadDoc" value="Download" />
										<!-- <span id="downloadIdTitle"> download File</span> -->
										<span style="font-size:20px;color:red;cursor:pointer;" id="downloadIdTitle">x</span>
								</div>
							</div>
							
							
						<input type="hidden" name="leavecount" id="hiddenleavecount"  value='<logic:present name="leaveCalculation"><bean:write name ="leaveCalculation" property ="leaveCount"/></logic:present>'/>	
						
						<input type="hidden" name="hiddenleavesumcount" id="hiddenleavesumcount"  value='<logic:present name="leaveCalculation"><bean:write name ="leaveCalculation" property ="leaveCountSum"/></logic:present>'/>	
							
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
							 value='<logic:present name="parentid" scope="request"><bean:write name="parentid" scope="request"  />
							</logic:present>'></input>		
							
							<input type="hidden" id="successid" 
							value='<logic:present name="success"><bean:write name="success" />
							</logic:present>'></input>			
						</div>
						<table class="table table-bordered" id="allstudent" >
				              <tr>
				              	 <th class="headth" style="text-align:center;">Leave Type</th>
                                 <th  class="headth" style="text-align:center;">Total Leave</th>
                                 <th class="headth"style="text-align:center;">Leaves Taken</th>
                               	 <!--  <th class="headth"style="text-align:center;">Pending</th> -->
                                 <!-- <th class="headth"style="text-align:center;">Approved</th> -->
                                 <th class="headth" style = "text-align:center;">Balance</th>
				              </tr>
				              <logic:present name="BalanceList" scope="request">
							  <logic:iterate id="BalanceList" name="BalanceList" scope="request">
				              <tr>
				              <td><bean:write name="BalanceList" property="leavetypeName" /></td>
				              <td><bean:write name="BalanceList"  property="totalleaves" /></td>
				              <!-- <td></td>
				              <td></td> -->
				              <td><bean:write name="BalanceList" property="consumedLeave" /></td>
				              <td class='td<bean:write name="BalanceList" property="leavetypeName" />'><bean:write name="BalanceList"  property="available_leave" /></td>
				              </tr>
				              </logic:iterate>
							</logic:present>
						</table>	
							
			
					</div>
			
		
				</div>

			</div>
		</div>
	</div>
	</form>
</body>

</html>
