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
	src="JS/backOffice/Reports/PhoneDirectoryReport.js"></script>
	
	
	
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

</head>



<body>
<form id="stuattnid" action="phonedirectory.html?method=getPhoneDirectoryList" method="post" enctype="multipart/form-data">
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span style="font-size: 16pt;">Phone Directory
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
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Phone Directory
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
					
					<div class="panel-body" id="tabletxt" style="margin-top: 25px;">

						<div class="col-md-6" id="txtstyle">
							
							
							
							<div class="form-group">
									
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Category
								</label>		
										
										
										
									<div class="col-xs-7">
										<select id="category" name="category" class="form-control"
											required>
										<option value=""></option>
										<option value="all">All</option>
										<option value="admin">Admin</option>
										<option value="students">Students</option>
										<option value="teachers">Teachers</option>
										</select>
									</div>
								</div>
						
						</div>
						<div class="col-md-6" id="txtstyle">



                         <%--   <div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;"> </label>
									<div class="col-xs-7">
									<input type="text" name="departmentname" class="form-control" id="departmentid" 
									value='<logic:present name="editlist"><bean:write name="editlist" property="depName" /></logic:present>'></input>
									</div>
								</div> --%>



						<div class="form-group">
								
										
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Name
								</label>		
										
										
										
									<div class="col-xs-7">
										<select id="selectname" name="name" class="form-control"
											required>
											<option value=""></option>

											<logic:present name="streamlist">

												<logic:iterate id="AccYear" name="streamlist">

													<option
														value="<bean:write name="AccYear" property="streamId"/>">
														<bean:write name="AccYear" property="streamName" />
													</option>

												</logic:iterate>

											</logic:present>
										</select>
									</div>
								</div>
                                      </br>
                                    <div class="col-xs-4"></div>	
									<div class="col-xs-8">	
		 								<button type="submit" class="btn btn-info"id="search" onclick="return validate()" style="margin-top: 11px;margin-bottom: 25px;">Search</button>
									</div>








								
							</div>
							<br />
	             
						
						
													
				<input type="hidden" id="successid" 
							value='<logic:present name="success"><bean:write name="success" />
													</logic:present>'></input>		
							
						
						
			
							<!-- data-toggle="modal" data-target="#myModal" -->
						
							<br />	
							
						<div class="col-md-12 selecteditems">
								<br /> 
								
							<input type="hidden" id="hcategory" name="hcategory"
								value='<logic:present name="selectedvalue" scope="request"><bean:write name="selectedvalue" scope="request" property="category" /></logic:present>'>		
							
					       	<input type="hidden" id="hname" name="hname"
								value='<logic:present name="selectedvalue" scope="request"><bean:write name="selectedvalue" scope="request" property="name" /></logic:present>'>		
							
								
							 <span><b>Category :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="selectedvalue">
										<bean:write name="selectedvalue" property="category" />
										
									</logic:present></span> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Name
										 :</b></span>&nbsp;&nbsp;&nbsp;<span>
										 
										 <logic:present
										name="selectedlist">
										<bean:write name="selectedlist" property="name" />
										
									</logic:present>
									
									</span>
								
							</div>	
					
					
					
					<br />	
					
					
					
					
					
					
						<%--  <div id="allstudent">
								<logic:present name="phonedirectorylist">
                      				<display:table class="table" id="allstudent"  pagesize="10"
									name="requestScope.phonedirectorylist"
									requestURI="/phonedirectory.html?method=phonedirectoryAction?">

								    	<display:column property="count" sortable="true"
										title="S.No	<img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="name" sortable="true"
										title="Name<img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="category" sortable="true"
										title="Category<img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="phone" sortable="true"
										title="Contact Number <img src='images/sort1.png' style='float: right'/>"
										
										></display:column>
										
										<display:column property="email" sortable="true"
										title="Email Id<img src='images/sort1.png' style='float: right'/>"
										
										></display:column>
										
										<display:column property="address" sortable="true"
										title="Address<img src='images/sort1.png' style='float: right'/>"
										
										></display:column>
						 --%>				
										<%-- <display:column property="attdancedate" sortable="true"
										title="Attendance Date<img src='images/sort1.png' style='float: right'/>"
										
										></display:column>
										
										<display:column property="attendancestatus" sortable="true"
										title="Attendance Status <img src='images/sort1.png' style='float: right'/>"
										
										></display:column> --%>

									
					<%-- 			</display:table>

							</logic:present>
							
						</div> 
						 --%>
						<logic:present name="phonedirectorylist" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>S.No</th>
							<th>Name</th>
							<th>Category</th>
							<th>Contact Number</th>
							<th>Email Id</th>
							<th>Address</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="phonedirectorylist" name="phonedirectorylist">
								<tr>
								<td><bean:write name='phonedirectorylist' property="count"/></td>
								<td><bean:write name="phonedirectorylist" property='name'/></td>
								<td><bean:write name="phonedirectorylist" property='category'/></td>
								<td><bean:write name="phonedirectorylist" property='phone'/></td>
								<td><bean:write name="phonedirectorylist" property='email'/></td>
								<td><bean:write name="phonedirectorylist" property='address'/></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
					  <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
	                  <span  class='numberOfItem'></span>	
	                  </div><div class='pagination pagelinks' style='top:-9px'></div>	
						
					</div>
					
			
				</div>
</div>
			</div>
		</div>
	
	</form>
</body>

</html>
