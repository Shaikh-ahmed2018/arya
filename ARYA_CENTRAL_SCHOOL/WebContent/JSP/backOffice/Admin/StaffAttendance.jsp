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
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Admin/StaffAttendance.js"></script>

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
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
	<div class="searechWrap">	
		<div class="col-md-6" id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Staff Attendance</span>
			</p>
		</div>

		
		
		<div class="input-group col-md-6">
			
			<label  class="hedderstyle form-control" style="margin: 15px 0px; width: 20% !important; border: none;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;">Start Date</label> 
		
			<input type="text" name="year" style="width: 30%; margin: 20px 0px; margin-left: 0%;" 
							id="startdate" maxlength="25" class="form-control"  readonly="readonly" value="<logic:present name='StartDate'><bean:write name='StartDate'/></logic:present>" />
			
			<label style="margin: 15px 0px; width: 21%; border: none;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;"
				class="form-control">End Date</label>
				
			<input type="text" name="year" style="width: 30%; margin: 20px 0px; margin-left: -1%;" 
							id="enddate" maxlength="25" class="form-control"  readonly="readonly" value="<logic:present name='EndDate'><bean:write name='EndDate'/></logic:present>" />
							
			<span class="input-group-btn">
					<button class="btn btn-default" type="button" id="searchAttendanceList" style="padding-top: 3px;">
						<i class="fa fa-search" ></i>
					</button>
				</span>


		</div>
</div>

		<center>
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
		</center>


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;
					</h3></a>
				<div class="navbar-right">
				<span class="buttons" id="refresh">Refresh</span>
					<a href="staffattendancepath.html?method=staffattendaceUpload" id="plus" >
					<span class="buttons">Add</span> </a>
						
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

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

					
					<logic:present name="attendancelist" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Sno</th>
							<th>Attendance Date</th>
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
								<td><bean:write name='attendancelist' property='count'/></td>
								<td><bean:write name="attendancelist" property='date'/></td>
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
					
					
					
 <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
   	<span  class='numberOfItem'></span>	
   	</div><div class='pagination pagelinks'></div>
				</div>
				<br />
			</div>
		</div>
	</div>


</body>
</html>