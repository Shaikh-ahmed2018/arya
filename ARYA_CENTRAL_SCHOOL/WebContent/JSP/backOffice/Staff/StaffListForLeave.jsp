
	<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>eCampus Pro</title>

<script type="text/javascript" src="JS/common.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Staff/StaffListForLeave.js"></script>
<style>
.download:hover {
	cursor: pointer; 
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}
</style>
<style>
.buttons {
	vertical-align: 5px;
}

.navbar-right span {
	margin-right: 3px;
}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div id="dialog"></div>
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Staff Registration</span>
			</p>
		</div>

		<form id="searchForm"
			action="teacherregistration.html?method=searchStaffDetails"
			method="post">
			<div class="input-group col-md-4" style="margin: 20px 0px;">

			<input type="text" class="form-control"  id="myInput" onkeyup="myFunction()" placeholder="Search for name" title="Type in name">
				

			</div>
		</form>
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>

			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write
									name="successmessagediv" scope="request" /></span></a>
					</div>
				</div>
			</logic:present>
			
			<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>
			

		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Staff
						List
					</h3></a>
				
				
			</div>
			<!-- pop up -->

			
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<!-- <table class="table" id="allstudent"> -->


				
						
						<logic:present name="allTeacherDetailsList" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Registration Id</th>
							<th>Abbreviative Id </th>
							<th>Staff Name</th>
							<th>Mobile No </th>
							
							<th>Designation</th>
							<th>Email</th>
							
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="allTeacherDetailsList" name="allTeacherDetailsList">
								<tr id='<bean:write name="allTeacherDetailsList" property='teacherId'/>'>
								<td><bean:write name="allTeacherDetailsList" property='registartionNo'/></td>
								<td><bean:write name="allTeacherDetailsList" property='abbreviative_Id'/></td>
								<td><bean:write name="allTeacherDetailsList" property='teacherName'/></td>
								<td><bean:write name="allTeacherDetailsList" property='mobileNo'/></td>
								
								<td><bean:write name="allTeacherDetailsList" property='designation'/></td>
								<td><bean:write name="allTeacherDetailsList" property='email'/></td>
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
