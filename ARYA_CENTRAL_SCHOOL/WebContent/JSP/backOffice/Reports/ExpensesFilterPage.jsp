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
	
	
<!-- <script type="text/javascript"
	src="JS/backOffice/Teacher/LeaveRequest.js"></script> -->
	
	
 <script type="text/javascript"
	src="JS/backOffice/Reports/ExpensesReport.js"></script>
	
	
	
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
<!-- <form id="expensesid"  method="post" enctype="multipart/form-data"> -->
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Expenses Details
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
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Expenses Details
							</h4> 
						</a>
						
						

						<div class="navbar-right">
						
							<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span>

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
									<span id="excelDownload"><img src="images/xl.png"
										class="xl"></span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
										id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
								</div>

							</div>
						</div>
					</div>
				
				
		<input type="hidden" name="userid"	id="userid" value="<logic:present name="parentid" ><bean:write name="parentid"  /></logic:present>"  />			
				
				
				
				
				
				
				
				
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					
					<div class="panel-body" id="tabletxt">

						<div class="col-md-6" id="txtstyle">
							
							
							
							<div class="form-group">
									<!-- <label for="inputPassword" class="control-label col-xs-4"
										id="inputnames">Academic Year</label> -->
										
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Academic Year
								</label>		
										
										
										
									<div class="col-xs-7">
										<select id="accyear" name="accyear" class="form-control"
											required>
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
									style="text-align: right; line-height: 35px;">From Date
								</label>
								<div class="col-xs-7">
									<input type="text" name="fromdate" id="Fromdate"
										readonly="readonly"
										class="form-control" value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="fromdate"/></logic:present>"></input>
								</div>
							</div>
							<br />
							
							
					<!-- <div class="form-group">
								
										
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Teacher Name
								</label>		
										
										
										
									<div class="col-xs-7">
										<select id="teachername" name="teachername" class="form-control"
											required>
											<option value=""></option>

										
										</select>
									</div>
								</div>	 -->	
							
							
							<br />
							
							
							
			
						</div>
						<div class="col-md-6" id="txtstyle">

                    <!-- <div class="form-group">
						
						<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Teacher Type
									
								</label>
								
								<div class="col-xs-7">
									<select name="teachertype" id="teachertype" class="form-control">
										<option value=""></option>
										<option value="teaching">Teaching</option>
										<option value="nonteaching">Non Teaching</option>
										
							
									</select>
								</div>
								
									
							</div>	 -->
                       
                             <br />
<br />
                           	<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">To Date</label>
								<div class="col-xs-7">
									<input type="text" name="todate" id="todate"
										readonly="readonly" 
										class="form-control"
										
										 value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="todate"/></logic:present>">
										</input>

								</div>
							</div>
							<br /><br /><br />
						
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
							
						</div>
						
					
			<button type="submit" class="btn btn-info col-md-offset-5"
								id="addsearch" onclick="return validate()">Search</button>
							<!-- data-toggle="modal" data-target="#myModal" -->
							<br />			
						
						
							<div class="col-md-12 selecteditems">
								<br /> 
								
								<input type="hidden" id="haccyear"
									value="<logic:present name="accyearname"><bean:write name="accyearname" property="accyearId"/></logic:present>" />
									
								<input type="hidden" id="hteachertype"
									value="<logic:present name="selecteddetails"><bean:write name="selecteddetails" property="teachertype"/></logic:present>" />
									
								<input type="hidden" id="hfromdate"
									value="<logic:present name="selecteddetails"><bean:write name="selecteddetails" property="fromdate"/></logic:present>" />
									
								<input type="hidden" id="htodate"
									value="<logic:present name="selecteddetails"><bean:write name="selecteddetails" property="todate"/></logic:present>" />


		
								<input type="hidden" id="hteachername"
									value="<logic:present name="selecteddetails"><bean:write name="selecteddetails" property="teachername"/></logic:present>" />



								<span><b>Academic Year  :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="accyearname">
										<bean:write name="accyearname" property="accyearname" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Teacher Type :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="selecteddetails">
										<bean:write name="selecteddetails" property="teachertype" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>From Date:</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="selecteddetails">
										<bean:write name="selecteddetails" property="fromdate" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>To Date:</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="selecteddetails">
										<bean:write name="selecteddetails" property="todate" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									
									
									
									 <span><b>Teacher Name:</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="selectedteacher">
										<bean:write name="selectedteacher" property="teacherName" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									
									
									
									
									<br />
							</div>
							<br />	
						<br />	
						
						<%-- <div id="allstudent">
								<logic:present name="staffattendanceList">
                      				<display:table class="table" id="allstudent"
									name="requestScope.staffattendanceList"
									requestURI="/staffattendancereport.html?method=getStaffAttendanceAction?">

									<display:column property="count" sortable="true"
										title="S.No	<img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="teacherName" sortable="true"
										title="Teacher Name	<img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="date" sortable="true"
										title="Attendance Date <img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="status" sortable="true"
										title="Attendance Status <img src='images/sort1.png' style='float: right'/>"
										
										></display:column>
									
								</display:table>

							</logic:present>
						</div> --%>
						
				<table id="allstudent" align="center" style="width: 100%">
				
				<tr>
								<th align="center" sortable="true"><center>
								<label>AcademicYear     </label>
								</center>
								</th>
								<th align="center"><center>
								<label>From Date</label></center>
								</th>
								<th align="center"><center><label>To Date</label></center></th>
								<th align="center"><center><label>Expense Type</label></center></th>
								<th align="center"><center><label>Expenses Amount</label></center></th>
								</tr>
				
				
				
				
				
				<tr>
								<td align="center">
								<label>2015-2016</label>
								
								</td>
								<td align="center">
								<label>1-8-2015</label>
								</td>
								<td align="center"><label>1-8-2016</label></td>
								<td align="center"><label>School Event</label></td>
								<td align="center"><label>1000</label></td>
								</tr>
								
								
								
								
								<tr>
								<td align="center">
								<label>2015-2016</label>
								
								</td>
								<td align="center">
								<label>14-7-2015</label>
								</td>
								<td align="center"><label>14-6-2016</label></td>
								<td align="center"><label>Fuel Maintanence</label></td>
								<td align="center"><label>1500</label></td>
								</tr>
								
								<tr>
								<td align="center">
								<label>2015-2016</label>
								
								</td>
								<td align="center">
								<label>14-7-2015</label>
								</td>
								<td align="center"><label>14-6-2016</label></td>
								<td align="center"><label>Vehicle Maintanence</label></td>
								<td align="center"><label>2000</label></td>
								</tr>
								
								<tr>
								<td align="center">
								<label>2015-2016</label>
								
								</td>
								<td align="center">
								<label>14-7-2015</label>
								</td>
								<td align="center"><label>14-6-2016</label></td>
								<td align="center"><label>Stationary Expenses</label></td>
								<td align="center"><label>2500</label></td>
								</tr>
								
								<tr>
								<td align="center">
								<label>2015-2016</label>
								
								</td>
								<td align="center">
								<label>14-7-2015</label>
								</td>
								<td align="center"><label>14-6-2016</label></td>
								<td align="center"><label>Food Expenses</label></td>
								<td align="center"><label>3000</label></td>
								</tr>
				
				
				</table>		
						
						
						
						
						
						
						
						
						
						
					</div>
					
			
				</div>

			</div>
		</div>
	</div>
	<!-- </form> -->
</body>

</html>
