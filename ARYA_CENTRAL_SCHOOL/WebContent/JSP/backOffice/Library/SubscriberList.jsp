<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">
<head>

<title>eCampus Pro</title>

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/custome.css" rel="stylesheet">
<script src="JS/backOffice/Library/SubscriberList.js"></script>
<script type="text/javascript" src="JS/common.js"></script>


<style>
.modal-body {
	text-align: center;
}
</style>
<style>
.buttons {
	vertical-align: -28px;
}
.stafdetail{
  display:none;
  }
.other{
display:none;
}
#show_per_page{
    margin-top: 10px;
    margin-left: 10px;
}
.pagelinks{
    margin-top: 10px;
}


</style>
</head>
<body>
  <div class="col-md-10 col-md-offset-2"
 		style="font-family: Roboto Medium; font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;" >
 		<p>
			<img src="images/addstu.png" />&nbsp;<span>Subscriber List </span>
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
					class="successmessage"></span></a>
			</div>
		</div>
		
		
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
			<div class="panel-heading" role="tab" id="headingOne">
			
			
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" style="color: #767676;vertical-align: text-top;"> <h4 class="panel-title" id="beforeparent"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Subscriber List
						</h4>	</a>
				
					<div class="navbar-right" style="display: none;">
					   <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBRPDWD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
						<span class="buttons" id="iconsimg" data-toggle="modal"
							data-target="#myModal" data-toggle="tooltip"
							data-placement="bottom" title="Download">Download </span>
								&nbsp;
							<span class="buttons" id="print" >Print </span>
						
							
							
							</logic:equal>
							</logic:equal>
							</logic:iterate>
							</logic:present>
							
					</div>
				</div>
				
				<!-- pop up -->

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
				
				
				
				<!-- Filters -->
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body" id="tabletxt" style="padding: 15px;">
				    <div class="col-md-6" id="txtstyle" >
				    <div  class="form-group clearfix">
				    <label for="inputPassword" class="control-label col-xs-5" style="text-align:left; line-height: 35px;">School Name</label>
						<div class="col-xs-7">
						 <select id="locationname" name="location" class="form-control"required>
							<option value="">----------Select----------</option>
							<logic:present name="locationList">
											<logic:iterate id="Location" name="locationList">
												<option
													value="<bean:write name="Location" property="locationId"/>">
													<bean:write name="Location" property="locationName" />
												</option>
											</logic:iterate>
										</logic:present>
							
							 </select>
					   </div>
					</div>
					
					
					

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align:left; line-height: 35px;">Subscriber 
									List</label>
								<div class="col-xs-7" style="margin-top: -10px;">
							
									<div class="radio" id="selection">
										<label><input type="radio" name="requested_by" id="student"  value="studentwise" style="margin-top: 8px;" checked="checked">Student</label> &nbsp;&nbsp;
										<label><input type="radio" name="requested_by" id="staff" value="staffwise" style="margin-top: 8px;">Staff</label>&nbsp;&nbsp;
										<label><input type="radio" name="requested_by" id="other" value="other" style="margin-top: 8px;">Others</label>
									</div>
								</div>
							</div> 
							
							<div class="form-group clearfix division" 
								style="display:none">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: left; line-height: 35px;" id="inputnames">Division</label>
								<div class="col-xs-7">
									<select class="form-control"name="section" id="section">
										<option value="all">ALL</option>
									</select>
								</div>
							</div>
							 
							 
							 <div class="form-group clearfix stafdetail" 
								style="display:none">
								
								
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align:left; line-height: 35px;" >Department</label>
								<div class="col-xs-7">
									<select class="form-control" name="" id="department">
										<option value="ALL">ALL</option>
										<logic:present name="deplist">
															<logic:iterate id="deplist" name="deplist">
																<option
																	value="<bean:write name="deplist" property="depId"/>">
																	<bean:write name="deplist" property="depName" />
																</option>
															</logic:iterate>
											</logic:present>
									</select>
								</div>
							</div>
				  </div>
				  
				  
				  
				  <div class="col-md-6" id="txtstyle">
				    <div class="form-group clearfix">
				     <label for="inputPassword" class="control-label col-xs-5" style="text-align:left; line-height: 35px;" 
				     id="inputnames">Academic Year</label>
				     <div  class="col-xs-7">
				     <select id="accyear" name="accyear" class="form-control" required>
						  <logic:present name="AccYearList">
							<logic:iterate id="AccYear" name="AccYearList">
							<option value="<bean:write name="AccYear" property="accyearId"/>">
						  <bean:write name="AccYear" property="accyearname" /></option>
						 </logic:iterate>
						 </logic:present>
						</select>
				          </div>
				          </div>
				  
				     	<input type="hidden" id="hiddenaccyear" value='<logic:present name="accyear"><bean:write name="accyear" /></logic:present>' />
				  
							<div class="form-group clearfix class" id="class" style="display:none">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align:left; line-height: 35px;"> Class</label>
								<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()"
										name="classname" id="classname" >
										<option value="all">ALL</option>
									</select>
								</div>
							</div>
							
							<div class="form-group clearfix stafdetail">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: left; line-height: 35px;"> Desiganation</label>
								<div class="col-xs-7">
									<select class="form-control" id="designation"
										name="" id="">
										<option value="all">ALL</option>
										<logic:present name="DESIGNATIONLIST">
															<logic:iterate id="DESIGNATIONLIST"
																name="DESIGNATIONLIST">
																<option
																	value="<bean:write name="DESIGNATIONLIST" property="desgid"/>">
																	<bean:write name="DESIGNATIONLIST" property="desgname" />
																</option>
															</logic:iterate>
														</logic:present>
									</select>
								</div>
							</div>
							
							<!-- <div class="form-group clearfix other">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: left; line-height: 35px;"> Name</label>
								<div class="col-xs-7">
									<select class="form-control" id="otherName" name="" >
									<option value="">-----------ALL-------------</option>
									</select>
								</div>
							</div> -->
				 </div>
         
         	<div class="allstudenttable">
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<table class='allstudent'  id='allstudent' width='100%' >

						<thead>
				
							<tr>
								<th>Sl.No.</th>
								<th>Subscriber No</th>
								<th>Name</th>
								<th>Admission No</th>
								<th>Roll No</th>
								<th>Class</th>
								<th>Section</th>
								<th>Status</th>
							</tr>
						</thead>

						<tbody>
						</tbody>

					</table>
					
					
				</div>
			</div>




         	<div class="othersTable">
				<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					<table class='allstudent'  id='allother'  width='100%' >
				

						<thead>
				
							<tr>
								<th>Sl.No.</th>
								<th>Subscriber No</th>
								<th>Name</th>
								<th>Gender</th>
								<th>Contact Number</th>
								<th>Mail Id</th>
								<th>Address</th>
								<th>Status</th>
							</tr>
						</thead>

						<tbody>
						
						
						</tbody>

					</table>
					
				</div>
			</div>


			<div class="stafftable" style="display:none;">
				<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					<table class='allstudent'  id="allstaffs"  width='100%' >
				

						<thead>
							<tr>
								<th>Sl.No.</th>
								<th>Subscriber No</th>
								<th>Name</th>
								<th>Departmnt</th>
								<th>Designation</th>
								<th>Status</th>
							</tr>
						</thead>

						<tbody>
						
						</tbody>

					</table>

					
				</div>
			</div>
			<div id ='pagebanner'>
			
			<div class='pagebanner'>
						<select id='show_per_page'>
						<option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select>
								<span  class='numberOfItem'></span>	
					</div>
					<div class='pagination pagelinks'></div>
			</div>
			   </div>	
          </div>
				
      </div>
   </div>
</div>

</body>
</html>