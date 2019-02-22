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
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
<link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
	
 <script type="text/javascript" src="JS/backOffice/Reports/TeacherLeaveReport.js"></script>
	
	
	
<style>
.save:hover {
	cursor: pointer;
}


 .table,th.heading1 {
	/* font-family: Roboto Bold; */
	font-size: 14px;
	color: #211f1f;
} 

.heading1{
	background:#d5d4d4;
}
</style>

<style>
#list:hover {
	cursor: pointer;
}
</style>
<style>
.buttons{

vertical-align: -28px;

}
.allstudent th:nth-child(1) {
    text-align: left;
    width:180px;
}
</style>

</head>

<body>
<form id="staffLeavereport" action="teacherLeaveDetailsReport.html?method=getLeaveDetailsReport" method="post" >
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span style="font-size: 16pt;">Staff Leave Report
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
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion2"
							href="#collapseOne" style="color: #767676;vertical-align: text-top"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Staff Leave Report
							</h4> 
						</a>
						
						

						<div class="navbar-right">
						<span class="buttons" id="print">Print</span>
							


						</div>
						
						
				
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
								<span></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	<span id="excelDownload"><img src="images/xl.png"
										class="xl"></span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--  <span
										id="pdfDownload"><img src="images/pdf.png" class="pdf"></span> -->
								</div>

							</div>
						</div>
					</div>
				
				
				<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body clearfix" id="tabletxt" style="margin-top: 19px;">
						<div class="col-md-6" id="txtstyle">
							
							
							<div class="form-group clearfix">
								<label for="inputforSchool" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">School</label>		
									<div class="col-xs-7">
										<select id="location" name="location" class="form-control">
											<option value="">--------Select--------</option>
											 <logic:present name="locationList" scope="request"><logic:iterate id="locationList" name="locationList"><option value="<bean:write name="locationList" property="locationId"/>"><bean:write name="locationList" property="locationName" /></option></logic:iterate></logic:present>
										</select>
									</div>
							</div>
							
							
							
						
						
							
							
							<div class="form-group clearfix">
						
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Staff Type
									
								</label>
								
								<div class="col-xs-7">
									<select name="teachingtype" id="teachertype" class="form-control" onchange="getTeacherName()" >
										<option value="all">ALL</option>
										<option value="TEACHING">TEACHING</option>
											<option value="NON TEACHING">NON TEACHING</option>
											<option value="OFFICE STAFF">OFFICE STAFF</option>
											<option value="SUBJECT TEACHER">SUBJECT TEACHER</option>
											<option value="MISC">MISC</option>
											<option value="PROBATION">PROBATION</option>
											<option value="GENERAL">GENERAL</option>
											<option value="TEMPORARY">TEMPORARY</option>
											<option value="PEON">PEON</option>
											<option value="HOUSEKEEPING">HOUSEKEEPING</option>
									</select>
								</div>
								
									
							</div>
			
						</div>
						<div class="col-md-6" id="txtstyle">
						
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Academic Year</label>		
									<div class="col-xs-7">
										<select id="accyear" name="accyear" class="form-control">
											 <logic:present name="AccYearList"><logic:iterate id="AccYear" name="AccYearList"><option value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option></logic:iterate></logic:present>
										</select>
									</div>
							</div>

					
              			
                       
                        

                       	 <div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"style="text-align: right; line-height: 35px;">Teacher Name</label>		
									<div class="col-xs-7">
										<select id="teachername" name="teachername" class="form-control">
											<option value=""></option>
										</select>
									</div>
								</div>	
								<div class="col-xs-4"></div>
								<div class="col-xs-8">
									<button type="submit" class="btn btn-info " id="search" >Search</button>
								</div>
							<br />	<br />
						
						</div>
						
					
								
			
	<div class="col-md-12" id="printarea"  style="padding:7px;text-align: center;">
	 
		<logic:present name="detailsList" scope="request">
		<input type="hidden" id="hideenId" value="studentlist" />
		<logic:iterate id="LeaveReport" name="detailsList" scope="request">		
			<table class="table allstudent" id="teacherdetail" style="overflow:scroll">
				<thead>
				<tr>
					<th  class="heading1">Employee Name :</th><th><bean:write name="LeaveReport" property="teachername"/></th>
					<th class="heading1">Employee Type :</th><th><bean:write name="LeaveReport" property="teachertype"/></th>
				</tr>
				<tr>
					<th  class="heading1">Designation :</th><th><bean:write name="LeaveReport" property="designationName"/></th>
					<th class="heading1">Date of Joining :</th><th><bean:write name="LeaveReport" property="doj"/></th>
				</tr>
				</thead>
				</table>
				<table class="table allstudent" id="leavebalacedetail" style="overflow:scroll">
				<thead>	
				<tr>
				<th>Leave Type</th>
				<logic:iterate id="leaveType" name="LeaveReport"  property="leaveArrayList">
					<th><bean:write name="leaveType" property="leaveName"/></th>
				</logic:iterate>
				</tr>
				<tr>
				<th>Total Leave</th>
				<logic:iterate id="totalbal" name="LeaveReport"  property="leaveArrayList">
					<th><bean:write name="totalbal" property="leaveValue"/></th>
				</logic:iterate>
				</tr>
				<tr>
				<th>Consumed Leave</th>
				<logic:iterate id="leaveConsume" name="LeaveReport"  property="leaveArrayList">
					<th><bean:write name="leaveConsume" property="leaveConsumed"/></th>
				</logic:iterate>
				</tr>
				<tr>
				<th>Available Leave</th>
				<logic:iterate id="leaveAvail" name="LeaveReport"  property="leaveArrayList">
					<th><bean:write name="leaveAvail" property="leaveAvailable"/></th>
				</logic:iterate>
				</tr>
			</thead>
			</table>
			<table class="table allstudent" id="leavedetail" style="overflow:scroll">
			<thead>
			<tr>
			<th>Start Date</th><th>End Date</th><th>No of Leaves</th><th>Leave Type</th><th>Approved By</th>
			
			</tr>
			</thead>
			<tbody>
			<logic:iterate id="leavedetails" name="LeaveReport"  property="leaveList">
			<tr>
			<td><bean:write name="leavedetails" property="startDate"/></td>
			<td><bean:write name="leavedetails" property="endDate"/></td>
			<td><bean:write name="leavedetails" property="noOfleave"/></td>
			<td><bean:write name="leavedetails" property="leaveName"/></td>
			<td><bean:write name="leavedetails" property="approvedBy"/></td>
			</tr>
			</logic:iterate>
			</tbody>
			
		</table>
		<div class="clearfix">
		<div class="col-md-6"><span><b>LWA </b></span><span> : </span><span><bean:write name="LeaveReport" property="lwa"/></span></div>
		<div class="col-md-6"><span><b>Half Day </b></span><span> : </span><span><bean:write name="LeaveReport" property="haflDay"/></span></div>
		</div>
		
			</logic:iterate>
	</logic:present>
		</div>
						
		</div> 
		</div>
					
			
	</div>

			</div>
			</div>
	</form>
	<textarea id="printing-css" style="display:none; ">
	.allstudent th:nth-child(1) {
    text-align: left;
    width:180px !important;
}
	
	</textarea>
</body>

</html>
