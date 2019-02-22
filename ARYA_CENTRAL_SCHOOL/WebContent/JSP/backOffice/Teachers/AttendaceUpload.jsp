<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Teacher/AttendaceUpload.js"></script>

<title>eCampus Pro</title>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
.hiddenclass{

	display: none;
}
</style>

<style>
.buttons{

vertical-align: -5px;

}

.navbar-right span {
    margin-right: -4px;
    position: relative;
    top: 4px;
    vertical-align: text-top;
}

.slno{
max-width : 20px;
}

.style{
	    line-height: 15px;
}
.saperator{
background-color :#ffffff !important;
}




/* #allstudent th{
min-width : 150px;

} */
 #allstudent th:nth-child(2){
min-width : 65px;
} 
 #allstudent th:nth-child(3),#allstudent th:nth-child(4){
min-width : 150px;
} 
 #allstudent th:nth-child(5){
min-width : 140px;
} 
 #allstudent th:nth-child(7),#allstudent th:nth-child(8),#allstudent th:nth-child(9),#allstudent th:nth-child(11),#allstudent th:nth-child(12),#allstudent th:nth-child(14),#allstudent th:nth-child(15),#allstudent th:nth-child(17),#allstudent th:nth-child(18){
min-width : 120px;
} 
 #allstudent th:nth-child(10){
 
 min-width : 50px;
 }


</style>

</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Student Attendance
				</span>
		</p>
	
			<logic:present name="successMessage" scope="request">

				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
									name="successMessage" scope="request" />
						</span></a>
					</div>
				</div>

			</logic:present>

			<logic:present name="errorMessage" scope="request">

				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
									name="errorMessage" scope="request" />
						</span></a>
					</div>

				</div>

			</logic:present>

			
				<div class="successmessagediv" align="center" style="display: none;">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>

	

		
			<div class="errormessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>

		
		<form action=""
			enctype="multipart/form-data" id="formstudent" method="post" >
			
			
			<!-- chiru -->

			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"
								style="color: #767676; vertical-align: middle;"><h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Student Details</h4></a>
						

						<div class="navbar-right">
							<span id="saveAttendance" class="buttons style" style ="top: 7px;">Save</span>&nbsp;
							<span id="back" class="buttons style" style ="top: 7px;" >Back</span>
						</div>

					</div>

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">School Name</label>
											<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											<option value="">-------------Select------------</option>
											<option value="all" selected>ALL</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
							</div>
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;"	id="inputnames">Class</label>
									<div class="col-xs-7">
										<select id="classId" name="classname" class="form-control" onChange="HideError()" required>
											<option value="all">-------------Select------------</option>
										</select>
									</div>
							</div>
							
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="section" name="section" class="form-control" onChange="HideError()">
											<option value="all">-------------Select------------</option>
										</select>
									</div>
								</div>
							
							
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Attendance Date</label>
									<div class="col-xs-7">
										<input type="text" name="date" readonly  placeholder="Select Date"
											 id="date"
											maxlength="25" class="form-control"
											value=''/>
										<!-- <img src="./images/calendar-icon.png" width="30" height="30"  style="margin-left: 88%; margin-top: -12%;"> -->
									</div>
								</div>
							</div>
							<div class="col-md-6" id="txtstyle">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
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
										id="inputnames" style="text-align: right; line-height: 35px;">Specialization</label>
									<div class="col-xs-7">
										<select id="specialization" name="specialization" class="form-control" onChange="HideError()">
											<option value="">-------------Select------------</option>
										</select>
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Teacher</label>
									<div class="col-xs-7">
										<input type="text" name="teacher"  
											 id="teacher" readonly onChange="HideError()"
											maxlength="25" class="form-control"
											value=''/>
									</div>
									<input type="hidden" id="teacherid"></input>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<select id="studentname" name="studentname" class="form-control" onChange="HideError()">
											<option value="">-------------Select------------</option>
										</select>
									</div>
								</div>
								<!-- <button type="button" class="col-md-offset-5 buttons style" id="search">Filter</button>
							<button type="reset" class="col-md-offset-1 buttons style" id="reset" style="margin-left :0">Reset</button> -->	
							</div>
							
					<div id="collapseOne" class="accordion-body collapse in" >
					<div class="col-md-12" style="padding: 0; overflow-x: scroll;display: none;height: 240px;" id="divIdList" >			
						<table id="allstudent" >
						<thead></thead>
						<tbody ></tbody>
						</table>
				
					</div>
						</div>
					</div>
					
				</div>
			</div>
			</div>
		</form>
	</div>
</html>









