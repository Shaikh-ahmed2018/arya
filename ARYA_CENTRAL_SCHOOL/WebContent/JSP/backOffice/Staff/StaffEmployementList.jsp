<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>

<title>eCampus Pro</title>  

<link href="CSS/newUI/custome.css" rel="stylesheet">
<script type="text/javascript" src="JS/backOffice/Staff/StaffEmployement.js"></script>
<script type="text/javascript" src="JS/common.js"></script>


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
<style>
.buttons{

vertical-align:5px;

}
</style>

<script type="text/javascript">
	function handle(e) {
		var searhname = $("#searchname").val();
		if (e.keyCode === 13) {
			e.preventDefault(); // Ensure it is only this code that rusn

			window.location.href = "adminMenu.html?method=staffEmployementList&searhname="
				+ searhname;
		}
	}
</script>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Staff Renumeration Details</span>
			</p>
		</div>
		<div class="input-group col-md-4" style="margin: 20px 0px;">

			<input type="text" class="form-control" id="searchname" Placeholder="Search......" onkeypress="handle(event)">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="searchbtn" style="padding-top: 3px;">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
		
		<logic:present name="successmessagediv" scope="request">
						<div class="successmessagediv" align="center">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"><span><bean:write
											name="successmessagediv" scope="request" /></span></a>
							</div>
						</div>
		</logic:present>
		
		
						<logic:present name="errorMessage" scope="request">

							<div class="successmessagediv" align="center">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-warning bg-msg-warning"><span>
											<bean:write name="errorMessage" scope="request" />
									</span></a>
								</div>

							</div>

						</logic:present>
		
			<div class="errormessagediv"  align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;vertical-align: middle;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Staff
						Renumeration
					</h3></a>
					
				<div class="navbar-right" >
				
					
<!-- 					<img src="images/rightline.png" class="rightimg">
 -->					
					 	<span id="editTeacher" class="buttons">Edit</span>
					  
                       <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download </span>


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
			
				<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<!-- <table class="table" id="allstudent"> -->


					<%-- <logic:present name="allTeacherDetailsList" scope="request">
						<display:table class="table" id="allstudent"
							name="requestScope.allTeacherDetailsList"
							requestURI="/adminMenu.html?method=staffEmployementList" export="false" 
							decorator="com.centris.campus.decorator.AllTeacherDetailsDecorator">
							<display:column property="check" sortable="true"
								title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" />


							<display:column property="registartionNo" sortable="true"
								title="Teacher Id <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="teacherName" sortable="true"
								title="Teacher Name <img src='images/sort1.png' style='float: right'/>" />
							
							<display:column property="department" sortable="true"
								title="Department <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="bankaccountNo" sortable="true"
								title="Account No <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="pfnumber" sortable="true"
								title="Employee Pf No <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="ctc" sortable="true"
								title="CTC <img src='images/sort1.png' style='float: right'/>" />
					
							

						</display:table> --%>
						
						
						<logic:present name="allTeacherDetailsList" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' name='selectall' id='selectall' onClick='selectAll()'/></th>
							<th>Teacher Id</th>
							<th>Teacher Name </th>
							<th>Department</th>
							<th>Account No</th>
							<th>Employee Pf No</th>
							<th>CTC</th>
							
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="allTeacherDetailsList" name="allTeacherDetailsList">
								<tr>
								<td><input type='checkbox' name='select' class='select' id='<bean:write name="allTeacherDetailsList" property='teacherId'/>' /></td>
								<td><bean:write name="allTeacherDetailsList" property='registartionNo'/></td>
								<td><bean:write name="allTeacherDetailsList" property='teacherName'/></td>
								<td><bean:write name="allTeacherDetailsList" property='department'/></td>
								<td><bean:write name="allTeacherDetailsList" property='bankaccountNo'/></td>
								<td><bean:write name="allTeacherDetailsList" property='pfnumber'/></td>
									<td><bean:write name="allTeacherDetailsList" property='ctc'/></td>
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
	
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>