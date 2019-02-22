<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
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
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JS/backOffice/Teacher/AttendaceStatus.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<title>eCampus Pro</title>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}

.download:hover{

cursor: pointer;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}

.navbar-right span {
    margin-right: -4px;
    position: relative;
    top: 6px;
    vertical-align: text-top;
}

.anchor a:hover {
    text-decoration: underline;
}
.tooltip {
    position: relative;
    display: inline-block;
    border-bottom: 1px dotted black;
}

.tooltip .tooltiptext {
    visibility: hidden;
    width: 120px;
    background-color: black;
    color: #fff;
    text-align: center;
    border-radius: 6px;
    padding: 5px 0;

    /* Position the tooltip */
    position: absolute;
    z-index: 1;
}
.buttonstyle{
border:none;
background: transparent;

}

.tooltip:hover .tooltiptext {
    visibility: visible;
}
#allstudent th:nth-child(1){
max-width : 50%;
}



</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-6" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Student Attendance</span>
			</p>
		</div>

		
		
		<div class="input-group col-md-6">
			
			<label  class="hedderstyle form-control" style="margin: 20px 0px; width: 20% !important; border: none;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;    background: transparent;
    				color: #767676 !important;"></label> 
		
			<%-- <input type="text" name="year" style="width: 30%; margin: 25px 0px ; margin-left: 0%;height:35px;" 
							id="startdate" maxlength="25" class="form-control"  readonly="readonly" value="<logic:present name='StartDate'><bean:write name='StartDate'/></logic:present>" /> --%>
			
			<label style="margin: 20px 0px; width: 21%; border: none;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;    background: transparent;
    		color: #767676 !important;"	class="form-control"></label>
				
			<%-- <input type="text" name="year" style="width: 30%; margin: 25px 0px; margin-left: -1%;height:35px;" 
							id="enddate" maxlength="25" class="form-control"  readonly="readonly" value="<logic:present name='EndDate'><bean:write name='EndDate'/></logic:present>" /> --%>
							
			<!-- <span class="input-group-btn">
					<button class="btn btn-default" type="button" id="searchAttendanceList">
						<i class="fa fa-search"></i>
					</button>
				</span> -->


		</div>



			<div class="successmessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>

			<div class="errormessagediv1" style="display: none;">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 30%;"><span class="validateTips1"></span></a>
				</div>
			</div>



		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Student Attendance
					</h3></a>
				<div class="navbar-right">
				
					<a href="StudentAttendanceActionPath.html?method=studentattendaceUploadEntry" style="margin: 0px 12px 0px 0px;"
						id="plus"><span class="buttons" style="margin-right: -14px;">Add</span> </a>
						
					 <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download </span>


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
											<option value="all">ALL</option>
										</select>
									</div>
							</div>
							
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="section" name="section" class="form-control" onChange="HideError()">
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
							
							
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Start Date</label>
									<div class="col-xs-7">
										<input type="text" name="year" id="startdate" maxlength="25" class="form-control"  readonly="readonly" value="<logic:present name='StartDate'><bean:write name='StartDate'/></logic:present>" />
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
										<!-- <input type="text" name="teacher"  
											 id="teacher" readonly onChange="HideError()"
											maxlength="25" class="form-control"
											value=''/> -->
										<select id="teacher" name="teacher" class="form-control" onChange="HideError()">
											<option value="">-------------Select------------</option>
										</select>
											
									</div>
									<input type="hidden" id="teacherid"></input>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">End Date</label>
									<div class="col-xs-7">
										<input type="text" name="year" id="enddate" maxlength="25" class="form-control"  readonly="readonly" value="<logic:present name='EndDate'><bean:write name='EndDate'/></logic:present>" />
									</div>
								</div>
								<!-- <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<select id="studentname" name="studentname" class="form-control" onChange="HideError()">
											<option value="">-------------Select------------</option>
										</select>
									</div>
									<input type="hidden" id="teacherid"></input>
								</div> -->
								<!-- <button type="button" class="col-md-offset-5 buttons style" id="search">Filter</button>
							<button type="reset" class="col-md-offset-1 buttons style" id="reset" style="margin-left :0">Reset</button> -->	
							</div>
			
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"	style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
			<%-- 		<display:table name="requestScope.attendancelist"
						requestURI="teachermenuaction.html?method=attendaceStatus"
						export="false" class="table" id="allstudent">


						<display:column property="count" sortable="true"
							title="Sl.No<img src='images/sort1.png' style='float: right'/>" />
						
						<display:column  sortable="true" 
							title="Attendance Date<img src='images/sort1.png' style='float: right'/>"><a class="anchor"><input type="button" class="buttonstyle" id="${allstudent.classId},${allstudent.sectionId},${allstudent.date},${allstudent.specId},${allstudent.teacherID},${allstudent.locationId}"  value="${allstudent.date}"/></a></display:column>
				
							<display:column property="classsection" sortable="true" class = "slno"
							title="Class & Division<img src='images/sort1.png' style='float: right'/>" />
							
							<display:column property="specName" sortable="true"
							title="Specialization<img src='images/sort1.png' style='float: right'/>" />
							
							<display:column property="teacherName" sortable="true"
							title="Teacher<img src='images/sort1.png' style='float: right'/>" />
							
						<display:column property="tot_count" sortable="true"
							title="Total Strength<img src='images/sort1.png' style='float: right'/>" />
						<display:column property="present_count" sortable="true"
							title="Total Present<img src='images/sort1.png' style='float: right'/>" />
						<display:column property="absent_count" sortable="true"
							title="Total Absent<img src='images/sort1.png' style='float: right'/>" />
						<display:column property="holiday_count" sortable="true"
							title="Total Holiday<img src='images/sort1.png' style='float: right'/>" />
						<display:column property="leave_count" sortable="true"
							title="Total Leave<img src='images/sort1.png' style='float: right'/>" />

						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>

					</display:table> --%>
					
					<logic:present name="attendancelist" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Sl.No</th>
							<th>Attendance Date</th>
							<th>Class & Division</th>
							<th>Specialization</th>
							<th>Teacher</th>
							<th>Total Strength</th>
							<th>Total Present</th>
							<th>Total Absent</th>
							<th>Total Holiday</th>
							<th>Total Leave</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="attendancelist" name="attendancelist">
								<tr>
								<td><bean:write name='attendancelist' property="count"/></td>
								<td><a class="anchor"><input type="button" class="buttonstyle" id="<bean:write name="attendancelist" property='classId'/>,<bean:write name="attendancelist" property='sectionId'/>,<bean:write name="attendancelist" property='date'/>,<bean:write name="attendancelist" property='specId'/>,<bean:write name="attendancelist" property='teacherID'/>,<bean:write name="attendancelist" property='locationId'/>"  value="<bean:write name="attendancelist" property='date'/>"/></a></td>
								<td><bean:write name="attendancelist" property='classsection'/></td>
								<td><bean:write name="attendancelist" property='specName'/></td>
								<td><bean:write name="attendancelist" property='teacherName'/></td>
								<td><bean:write name="attendancelist" property='tot_count'/></td>
							   <td><bean:write name="attendancelist" property='present_count'/></td>
							    <td><bean:write name="attendancelist" property='absent_count'/></td>
							     <td><bean:write name="attendancelist" property='holiday_count'/></td>
							      <td><bean:write name="attendancelist" property='leave_count'/></td>
							      
								
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
					
					
					
					
					<div class='pagebanner' style="margin-top: 34px;" ><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
						</div><div class='pagination pagelinks' style="margin-top: 34px;"></div>
				</div>
				<br />
			</div>
		</div>
	</div>


</body>
</html>