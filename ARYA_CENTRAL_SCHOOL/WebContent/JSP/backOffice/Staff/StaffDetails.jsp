
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
<link href="CSS\Tearcher\inActive.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/backOffice/Staff/StaffDetails.js"></script>
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

				<input type="text" name="searchname" id="searchname"
					class="form-control" Placeholder="Search......" value="<logic:present name="searchTerm"><bean:write name="searchTerm" /></logic:present>"> <span
					class="input-group-btn">
					<button class="btn btn-default" type="button" id="search">
						<i class="fa fa-search"></i>
					</button>
				</span>

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
						Registration
					</h3></a>
				<div class="navbar-right">



					<a href="teacherregistration.html?method=getTeachers"> <span
						class="buttons">Add </span>
					</a> <span class="buttons" id="editTeacher" style="cursor: pointer">Edit
					</span> <span class="buttons" id="deleteTeacher" style="cursor: pointer">Delete
					</span>
					<span class="buttons"  id="activateTeacher" data-toggle="modal" data-target="#activateModal"  style="cursor: pointer">Activate
					</span>
					<!-- activate modal -->
						
					
					<span class="buttons"  id="deactivateTeacher" style="cursor: pointer">Deactivate
					</span>

					<!-- <img src="images/download.png" class="download" id="iconsimg"
						data-toggle="modal" data-target="#myModal" data-toggle="tooltip"
						data-placement="bottom" title="Download"> -->

					<span class="buttons" id="iconsimg" data-toggle="modal"
						data-target="#myModal">Download </span>

				</div>
				
			</div>
			<div class="modal fade" id="activateModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									Inactive Teachers
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								
								<span aria-hidden="true">&times;</span>
							</button>
								</div>
								<div class="modal-body">
								
							          <div id="teachertable"></div>    
						</div>
						<div class="modal-footer">
        <button type="button" id="close" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
							</div>
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
					<!-- <table class="table" id="allstudent"> -->


				<%-- 	<logic:present name="allTeacherDetailsList" scope="request">
						<display:table class="table" id="allstudent"
							name="requestScope.allTeacherDetailsList"
							requestURI="/adminMenu.html" export="false" 
							decorator="com.centris.campus.decorator.AllTeacherDetailsDecorator">
							teacherId
							<display:column title="Select">
								<input type="radio" name="selectBox" id="selectBox"
									value="${allstudent.teacherId }" />
							</display:column>


							<display:column style="text-align:center"
								title="<input type='checkbox' name='selectall' style='text-align:center' id='selectall'  />">
								<input type='checkbox' name='select' class='select'
									style='text-align: center' id='${allstudent.teacherId }' />
							</display:column>



							<display:column property="check" sortable="true"
								title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" />

							<display:column property="registartionNo" sortable="true"
								title="Registration Id <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="abbreviative_Id" sortable="true"
								title="Abbreviative Id <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="teacherName" sortable="true"
								title="Staff Name <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="mobileNo" sortable="true"
								title="Mobile No <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="qualification" sortable="true"
								title="Qualification <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="designation" sortable="true"
								title="Designation <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="email" sortable="true"
								title="Email <img src='images/sort1.png' style='float: right'/>" />



							<display:setProperty name="export.pdf.filename"
								value="AllEmployee.pdf" />
							<display:setProperty name="export.pdf" value="true" />
							<display:setProperty name="export.excel.filename"
								value="AllEmployee.xls" />
							<display:setProperty name="export.excel" value="true" />
							<display:setProperty name="export.csv.filename"
								value="AllEmployee.csv" />
							<display:setProperty name="export.csv" value="true" />
							<display:setProperty name="export.xml.filename"
								value="AllEmployee.xml" />
							<display:setProperty name="export.xml" value="true" />
								<display:column sortable="true"
								title="Salary Details <img src='images/sort1.png' style='float: right'/>">
								<input type="button" name="getsalary" onClick='salaryDeatails()'
									value="Get Salary Details"
									id="${defaulttable.empid},${defaulttable.empname},${defaulttable.emplyee_role}"> </>
						</display:column>

							
						</display:table> --%>
						
						<logic:present name="allTeacherDetailsList" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' name='selectall' style='text-align:center' id='selectall' /></th>
							<th>Registration Id</th>
							<th>Abbreviative Id </th>
							<th>Staff Name</th>
							<th>Mobile No </th>
							<th>Qualification</th>
							<th>Designation</th>
							<th>Email</th>
							
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="allTeacherDetailsList" name="allTeacherDetailsList">
								<tr>
								<td><input type='checkbox' name='select' class='select'style='text-align: center' id='<bean:write name="allTeacherDetailsList" property='teacherId'/>' /></td>
								<td><bean:write name="allTeacherDetailsList" property='registartionNo'/></td>
								<td><bean:write name="allTeacherDetailsList" property='abbreviative_Id'/></td>
								<td><bean:write name="allTeacherDetailsList" property='teacherName'/></td>
								<td><bean:write name="allTeacherDetailsList" property='mobileNo'/></td>
								<td><bean:write name="allTeacherDetailsList" property='qualification'/></td>
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
