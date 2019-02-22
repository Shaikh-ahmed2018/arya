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
<script type="text/javascript" src="JS/backOffice/Student/StudentIdCardSingle.js"></script>

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


		 	<div class="col-md-10 col-md-offset-2" id="div1">
			<div class="searchWrap">
			<div  id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Print Student ID Card - Single</span>
			</p>
		</div>

	</div>

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
				<div class="panel panel-primary">

					<div class="panel-heading" role="tab" id="headingOne" >

						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title" id="beforeparent" style="color: #767676;"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp; Find Student Details
								</h4></a>
					
					</div>
					<!-- pop up -->

					<!-- Filters -->
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" id="tabletxt" style="padding: 15px;">

							<div class="col-md-6" id="txtstyle">
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">School Name</label>
											<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											<option value="all">----------Select----------</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
									
									
									<div class="form-group clearfix ">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()" 
											name="classname" id="classname">
											<option value="">----------Select----------</option>
										</select>
									</div>
								</div>
						
																			
							<div class="form-group clearfix ">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Search By</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" 
										value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
									</div>
								</div>
					
							
							</div>
							<div class="col-md-6" id="txtstyle">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
											<option value="all">----------Select----------</option>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
								
									
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="">----------Select----------</option>
										</select>
									</div>
								</div>
									<div  style ="margin-left:185px; margin-top:5px">	
										<span class="buttons" id="search">Search</span>
										<span class="buttons" id="resetbtn">Reset</span>
									</div>
							</div>
						<input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
								
							</div>
							
							<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">	
					<display:table class="table" id="allstudent"
						name="requestScope.studentList"
						requestURI="/adminMenu.html?method=studentSearch?">
						<display:column property="sno" sortable="true"	title="S.No"/>
						<display:column property="studentAdmissionNo" sortable="true" title="Admission No <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="studentFullName" sortable="true" title="Student Name <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="studentrollno" sortable="true" title="Roll No <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="classname" sortable="true" title="Class <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="sectionnaem" sortable="true" title="Division <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						
					</display:table>
					<!-- <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select></div><div class='pagination pagelinks'></div> -->
					<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
						</div><div class='pagination pagelinks'></div> 
					
					</div>
					
					
					
					
					</div>

					</div>
			
			</div>
			<!-- Button trigger modal -->

		</form>
	</div>




	<span>&nbsp;</span>


</body>

</html>
