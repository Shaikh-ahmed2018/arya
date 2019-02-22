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

 <script type="text/javascript"
	src="JS/backOffice/Reports/OnlineFeeReceipt.js"></script>
	
	
	
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

<!-- action="onlinefeereceipt.html?method=getOnlineFeeReceiptListAction" -->

<body>
<form id="stuattnid" action="onlinefeereceipt.html?method=getOnlineFeeReceiptListAction" method="post" enctype="multipart/form-data">
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Generate Fee Receipts
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


<center><logic:present name="Nothingfoundmsg" scope="request"><span style="color: black;font-family: Segoe UI;font-size:12px;"><bean:write name="Nothingfoundmsg"/></span></logic:present></center>

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion2"
							href="#collapseOne" style="color: #767676;vertical-align: text-top"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Generate Fee Receipts
						</h4>	 
						</a>
						
						

						<div class="navbar-right">
						
							<img src="images/rightline.png" style="margin-top: -2px;margin-right: 11px;">
							<img src="images/download.png" class="download" id="iconsimg" style="margin-top:4px"
								data-toggle="modal" data-target="#myModal" data-toggle="tooltip"
								data-placement="bottom"  style="top:-8px;">

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
						
						
							
							
							
							<div class="form-group clearfix">
									
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
							
							
							
							
						
			
						</div>
						<div class="col-md-6" id="txtstyle">




								<div class="form-group clearfix">
								
										
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
														<bean:write name="AccYear" property="className" />
													</option>

												</logic:iterate>

											</logic:present>
										</select>
									</div>
								</div>
							
						



						 <div class="form-group clearfix">
								
										
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


							</div>
							<br />
							<br />
							<br />
							<br />
							<br />
							<br />
							
							
							
							
							<!-- <button type="submit" class="btn btn-info col-md-offset-5"
								id="search" onclick="return validate()">Generate Report</button> -->
							<!-- data-toggle="modal" data-target="#myModal" -->
							<br />
						
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
							
						
				<input type="hidden" id="huserid" 
							value='<logic:present name="userid"><bean:write name="userid" />
													</logic:present>'></input>				
					
	<!-- 	<button type="submit" class="btn btn-info col-md-offset-5"
								id="search" onclick="return validate()">Search</button> -->
							
							<br />			
						
						
						  <%-- <div class="col-md-12 selecteditems">
								<br /> 
								
								<input type="hidden" id="haccyear"
									value="<logic:present name="accyearitem"><bean:write name="accyearitem" property="accyearId"/></logic:present>" />
									
								<input type="hidden" id="hstream"
									value="<logic:present name="streamitem"><bean:write name="streamitem" property="streamId"/></logic:present>" />
									
								<input type="hidden" id="hclass"
									value="<logic:present name="classitem"><bean:write name="classitem" property="classId"/></logic:present>" />
									
								<input type="hidden" id="hsection"
									value="<logic:present name="sectionitem"><bean:write name="sectionitem" property="sectionId"/></logic:present>" />


								<input type="hidden" id="hfromdate"
									value="<logic:present name="selecteditems"><bean:write name="selecteditems" property="fromdate"/></logic:present>" />
								
								<input type="hidden" id="htodate"
									value="<logic:present name="selecteditems"><bean:write name="selecteditems" property="todate"/></logic:present>" />




								<input type="hidden" id="hstudent"
									value="<logic:present name="studentitem"><bean:write name="studentitem" property="studentid"/></logic:present>" />
									
									
										<input type="hidden" id="hallstudent"
								    	value="<logic:present name="Stu"><bean:write name="Stu"/></logic:present>" />

						
							 <span><b>AcademicYear :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="accyearitem">
										<bean:write name="accyearitem" property="accyearname" />
										
									</logic:present></span> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>StreamName
										 :</b></span>&nbsp;&nbsp;&nbsp;<span>
										 
										 <logic:present
										name="streamitem">
										<bean:write name="streamitem" property="streamName" />
										
									</logic:present>
									
									</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>ClassName
										 :</b></span>&nbsp;&nbsp;&nbsp;<span>
										 
										<logic:present
										name="classitem">
										<bean:write name="classitem" property="className" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>SectionName
										 :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="sectionitem">
										<bean:write name="sectionitem" property="sectionName" />
										
									</logic:present></span>
									
									
									
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>StudentName:</b></span>&nbsp;&nbsp;&nbsp;<span>
								   <logic:present
									name="studentitem">
									<bean:write name="studentitem" property="studentFnameVar" />
									</logic:present></span>
									
									
								 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><b>FromDate
										 :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="selecteditems">
										<bean:write name="selecteditems" property="fromdate" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									
									    <span><b>ToDate
										 :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="selecteditems">
										<bean:write name="selecteditems" property="todate" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />	
								
							</div>   --%>  
							<br />	
						<br />	
						
					<%-- <div id="allstudent">
								<logic:present name="feeReceiptlist">
                      				<display:table class="table" id="allstudent"
									name="requestScope.feeReceiptlist"
									requestURI="/onlinefeereceipt.html?method=getOnlineFeeReceiptListAction?">

								    	<display:column property="count" sortable="true"
										title="S.No	<img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="addmissionnum" sortable="true"
										title="Admission Number	<img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="classname" sortable="true"
										title="Class Name<img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="sectionname" sortable="true"
										title="Section Name <img src='images/sort1.png' style='float: right'/>"
										
										></display:column>
										
										<display:column property="studentname" sortable="true"
										title="Student Name <img src='images/sort1.png' style='float: right'/>"
										
										></display:column>
										
										<display:column property="termname" sortable="true"
										title="Term Name<img src='images/sort1.png' style='float: right'/>"
										
										></display:column>
										
										<display:column property="totalamount" sortable="true"
										title="Total Amount<img src='images/sort1.png' style='float: right'/>"
										
										></display:column>
										
										

									
								</display:table>

							</logic:present>
						</div>  --%>
					</div>
					
			</div>
				</div>

			</div>
		</div>
	
	</form>
</body>

</html>
