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
<script src="JS/backOffice/Reports/StudentInformation.js"></script>

<style>
.modal-body {
	text-align: center;
}

</style><style>
.buttons{

vertical-align: -28px;

}
</style>
</head>



<body>


	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<!-- <p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>All Student</span>
		</p> -->

		
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span style="font-size: 16pt;">All Student
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
						class="successmessage"></span></a>
				</div>
			</div>
		

		<form action="reportaction.html?method=getStudentDetailsReport"
			method="post">
			<p id="parent1" style="display: none;">
				<a href="">Expand all</a>
			</p>
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary" style="margin-top: -20px;">

					<div class="panel-heading" role="tab" id="headingOne">

						
							<a data-toggle="collapse" data-parent="#accordion2"
								href="#collapseOne" style="color: #767676;vertical-align: text-top"><h4 class="panel-title" id="beforeparent"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Student
							</h4></a>
						

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
							<div class="col-md-6" id="txtstyle">
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										id="inputnames" style="text-align: right;">Location</label>
									<div class="col-xs-7">
										<select id="location" name="location" class="form-control"
											required>
											<option value=""></option>

											<logic:present name="locationList">

												<logic:iterate id="Location" name="locationList">

													<option
														value=" <bean:write name="Location" property="locationId"/>">
														<bean:write name="Location" property="locationName" />
													</option>

												</logic:iterate>

											</logic:present>
										</select>
									</div>
								</div>
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										id="inputnames" style="text-align: right;">Stream</label>
									<div class="col-xs-7">
										<select id="stream" name="stream" class="form-control"
											required>
											<option value=""></option>
											<logic:present name="StreamList">

												<logic:iterate id="StreamRec" name="StreamList">

													<option
														value="<bean:write name="StreamRec" property="streamId"/>">
														<bean:write name="StreamRec" property="streamname" />
													</option>

												</logic:iterate>

											</logic:present>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										id="inputnames" style="text-align: right;">Section</label>
									<div class="col-xs-7">
										<select id="section" name="section" class="form-control"
											required>
											<option value=""></option>

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
											<option value=""></option>

											<logic:present name="AccYearList">

												<logic:iterate id="AccYear" name="AccYearList">

													<option
														value=" <bean:write name="AccYear" property="accyearId"/>">
														<bean:write name="AccYear" property="accyearname" />
													</option>

												</logic:iterate>

											</logic:present>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right;">Class</label>
									<div class="col-xs-7">
										<select id="class" name="classname" class="form-control"
											required>
											<option value=""></option>

										</select>
									</div>
								</div>
								<div class="col-xs-4"></div>
							<div class="col-xs-8">
							<button type="submit" class="btn btn-info "
								id="search" onclick="return validate()" >Search</button>
								
								</div>
							</div>
							
							<!-- data-toggle="modal" data-target="#myModal" -->

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
									
									<span><b>Location :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="CurrentForm">
										<bean:write name="CurrentForm" property="locationName" />
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span><b>Academic Year :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="CurrentForm">
										<bean:write name="CurrentForm" property="accyearname" />
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>
										Stream :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="CurrentForm">
										<bean:write name="CurrentForm" property="streamname" />
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Class
										 :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="CurrentForm">
										<bean:write name="CurrentForm" property="classname" />
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>
										Section :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="CurrentForm">
										<bean:write name="CurrentForm" property="sectionname" />
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
							
							</div>

							<logic:present name="StudentInfoList">

								<input type="hidden" id="hideenId" value="studentlist" />

								<display:table class="table" id="allstudent"
									name="requestScope.StudentInfoList"
									requestURI="/adminMenu.html?method=studentList?"
									decorator="com.centris.campus.decorator.StudentRegistrationDecorator">

									<display:column property="sno" sortable="true"
										title="S.No	<img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>


									<display:column property="admissionno" sortable="true"
										title="Admission No	<img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>


									<display:column property="name" sortable="true"
										title="Student Name <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>



									<display:column property="age" sortable="true"
										title="Age <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>



									<display:column property="doj" sortable="true"
										title="DOB <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>


									<display:column property="fathername" sortable="true"
										title="FatherName <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>

									<display:column property="fathermobno" sortable="true"
										title="Father Mobile No <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>

									<display:column property="mothername" sortable="true"
										title="Mother Name <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>

									<display:column property="monthermobno" sortable="true"
										title="Mother Mobile No <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>
								</display:table>

							</logic:present>

						</div>
					</div>
				</div>
			</div>
			<!-- Button trigger modal -->

		</form>
	</div>

	<span>&nbsp;</span>


	<!-- jQuery -->
	<script src="js/jquery.js"></script>


	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>


	<script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>

</body>

</html>
