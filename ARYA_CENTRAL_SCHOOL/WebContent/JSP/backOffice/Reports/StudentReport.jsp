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
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />

<link href="CSS/newUI/custome.css" rel="stylesheet">
<script src="JS/backOffice/Reports/StudentReport.js"></script>

<style>
.modal-body {
	text-align: center;
}
</style>
<style>
.buttons {
	vertical-align: -28px;
}
</style>
</head>
<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">

		<p>
			<img src="images/addstu.png" />&nbsp;<span style="font-size: 16pt;">All Student </span>
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
		
		<p id="parent1" style="display: none;">
			<a href="">Expand all</a>
		</p>
		<div class=" -group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">

					
						<a data-toggle="collapse" data-parent="#accordion2"
							href="#collapseOne" style="color: #767676"><h4 class="panel-title" id="beforeparent"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Student
						</h4></a>
					

					<div class="navbar-right">
						<span class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" style="top:-8px;">Download</span>

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
				<div class="tab-row" style="margin-bottom: 125px;">
					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="location" name="location" class="form-control" required>
											<option value="all">ALL</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
					
						
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;"> Class</label>
							<div class="col-xs-7">
							<select class="form-control" onkeypress="HideError()" 
									name="classname" id="class">
									<option value="all">ALL</option>
							</select>
							</div>
						</div>
						
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Report Type</label>
							<div class="col-xs-7">
								<select id="selection" name="selection" class="form-control"
									required>
									<option value="all">----------Select----------</option>
									<option value="studept">Department List</option>
									<option value="newadmlist">New Admission List</option>
									<option value="newtempadmlist">New Temp. Admission List</option>
									<option value="busroutewise">Report For Bus in Route wise</option>
									<option value="stuconde">Student Contact Details</option>
									<option value="stulist">Student List</option>
									<option value="stulistadmission">Student List - Admission No. Wise</option>
									<option value="stulistalpha">Student List - Alphabetic Wise</option>
									<option value="stulcatwise">Student List - Category Wise</option>
									<option value="stulistgenwise">Student List - Gender Wise</option>
									<option value="stulistrollwise">Student List - Roll No. Wise</option>
									<option value="stulisthousewise">Student List - House. Wise</option>
									<option value="stuhousewise">House Wise</option>
									<option value="stuoptsub">Student Optional Subject Details</option>
									<option value="stuparlist">Student Parent List</option>
									<option value="sturelig">Student List Religion Wise</option>
									<option value="stustanwise">Student Standard Wise</option>
									<option value="studob">Students With DOB</option>
									<option value="stufatheroccu">Students with Father's Occupation</option>
									<option value="stumotheroccu">Students with Mother's Occupation</option>
									<option value="stuphonnum">Students with Phone Numbers</option>
									<option value="stuwithheld">Withheld Students List</option>
									
									<!-- <option value="oldstulist">Old Students List</option> -->
									<!-- <option value="stustrngth">Students Strength</option> -->
									
									
									<option value="prmlist">Promotion List</option>
									
								</select>
							</div>
						</div>
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								id="house">House</label>
							<div class="col-xs-7">
								<select id="houselistId" name="house" class="form-control"
									required>
									<option value="all">----------Select---------</option>
									<option value="Akbar">Akbar</option>
									<option value="Ashoka">Ashoka</option>
									<option value="Kanishka">Kanishka</option>
									<option value="Shivaji">Shivaji</option>
									
								</select>
							</div>
						</div>
					</div>

					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Academic Year</label>
							<div class="col-xs-7">
								<select id="accyear" name="accyear" class="form-control"
									required>
									
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
								id="inputnames" style="text-align: right;">Division</label>
							<div class="col-xs-7">
								<select id="section" name="section" class="form-control"
									required>
									<option value="all">ALL</option>
								</select>
							</div>
						</div>
						
					<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="genderName">Gender Type</label>
							<div class="col-xs-7">
								<select id="gender" name="gender" class="form-control"
									required>
									<option value="all">----------Select---------</option>
									<option value="Male">Boys</option>
									<option value="Female">Girls</option>
								</select>
							</div>
						</div>
						<div class="col-xs-4"></div>
					<div class="col-xs-8">
						<button type="button" class="btn btn-info " id="search" >Search</button>
					</div>
					</div>
					
					</div>
					
					<div class="col-md-12 selecteditems">
						<br /> <input type="hidden" id="haccyear"
							value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="accyearId"/></logic:present>" />
						<input type="hidden" id="hstream"
							value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="streamId"/></logic:present>" />
						<input type="hidden" id="hclass"
							value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="classId"/></logic:present>" />
						<input type="hidden" id="hsection"
							value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="sectionId"/></logic:present>" />
						<input type="hidden" id="hlocation"
							value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="locationId"/></logic:present>" />

						<span><b>Location :</b></span>&nbsp;&nbsp;&nbsp;<span>
						<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="locationName" />
						</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						
						<span><b>Academic Year :</b></span>&nbsp;&nbsp;&nbsp;<span>
						<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="accyearname" />
						</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
											
						<span><b>Class :</b></span>&nbsp;&nbsp;&nbsp;<span>
						<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="classname" />
						</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						
						<span><b>Section :</b></span>&nbsp;&nbsp;&nbsp;<span>
						<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="sectionname" />
						</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>

					</div>
		
					<div id="studentlisttable"></div>
				</div>
			</div>
			</div>
		</div>
		</div>
	<!-- Button trigger modal -->
	<span>&nbsp;</span>
	
	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>

</html>
