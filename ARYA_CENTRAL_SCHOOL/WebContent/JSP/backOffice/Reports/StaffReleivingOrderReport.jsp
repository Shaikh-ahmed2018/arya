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
	src="JS/backOffice/Reports/ReleivingOrder.js"></script>
	
	
	
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

vertical-align: -28px;

}
</style>
</style>

</head>

<body>
<form id="staffattendanceid" action="staffattendancereport.html?method=getStaffAttendanceAction" method="post" enctype="multipart/form-data">
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span style="font-size: 16pt;">Releiving Order
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
			<div class="panel panel-primary" style="margin-top: -20px;">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion2"
							href="#collapseOne" style="color: #767676;vertical-align: text-top"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Releiving Order
						</h4>	 
						</a>
						
						

						<div class="navbar-right">
						<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom"  style="top:-8px;">Download </span>

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
				
				
				
				
				
				 
				
				
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					
					<div class="panel-body" id="tabletxt" style="margin-top: 19px;">

						<div class="col-md-6" id="txtstyle">
							
							
							
							 <div class="form-group clearfix">
									
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">User Type
								</label>		
										
										
										
									<div class="col-xs-7">
										<select id="usertype" name="usertype" class="form-control"
											required>
											<option value=""></option>

											<logic:present name="userList">

												<logic:iterate id="AccYear" name="userList">

													<option
														value="<bean:write name="AccYear" property="userId"/>">
														<bean:write name="AccYear" property="userName" />
													</option>

												</logic:iterate>

											</logic:present>
										</select>
									</div>
								</div> 
						
						
							
							
							
							
							 <div class="form-group clearfix">
								
										
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Teacher Name
								</label>		
										
										
										
									<div class="col-xs-7">
										<select id="teachername" name="teachername" class="form-control"
											required>
											<option value=""></option>

										
										</select>
									</div>
								</div>	
							
							
							
							
							
							<%-- <div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">From Date
								</label>
								<div class="col-xs-7">
									<input type="text" name="fromdate" id="Fromdate"
										readonly="readonly"
										class="form-control" value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="fromdate"/></logic:present>"></input>
								</div>
							</div> --%>
							
			
						</div>
						<div class="col-md-6" id="txtstyle">

                    
                    
                    
                    <div class="form-group clearfix">
						
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
								
									
							</div>
                    
                    
                   
                    
                    
                    
                           	<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Resignation Date</label>
								<div class="col-xs-7">
									<input type="text" name="resignationdate" id="dateofRelievingId"
										readonly="readonly" 
										class="form-control"
										
										 value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="todate"/></logic:present>">
										</input>

								</div>
							</div>	
                       
                             <br />
 
 

                         	
						</div>
						
					
		         <!-- 	<button type="submit" class="btn btn-info col-md-offset-5"
								id="search" onclick="return validate()">Search</button> -->
						
							<br />			
						
						
							<%-- <div class="col-md-12 selecteditems">
								<br /> 
								
								
									
								<input type="hidden" id="hteachertype"
									value="<logic:present name="selecteddetails"><bean:write name="selecteddetails" property="teacherName"/></logic:present>" />
									
								<input type="hidden" id="hfromdate"
									value="<logic:present name="selecteddetails"><bean:write name="selecteddetails" property="todate"/></logic:present>" />
									
								<input type="hidden" id="htodate"
									value="<logic:present name="selecteddetails"><bean:write name="selecteddetails" property="description"/></logic:present>" />

								<span><b>First item :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="accyearname">
										<bean:write name="accyearname" property="accyearname" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Second
										item :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="selecteddetails">
										<bean:write name="selecteddetails" property="teachertype" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Third
										item :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="selecteddetails">
										<bean:write name="selecteddetails" property="fromdate" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Fourth
										item :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="selecteddetails">
										<bean:write name="selecteddetails" property="todate" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
							</div> --%>
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
					</div>
					
			
				</div>

			</div>
		</div>
	</div>
	</form>
</body>

</html>
