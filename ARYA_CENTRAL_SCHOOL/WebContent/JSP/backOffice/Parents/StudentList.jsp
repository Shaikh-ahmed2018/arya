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

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="JS/backOffice/Student/StudentRegistration.js"></script>
<script type="text/javascript" src="JS/common.js"></script>


<style>
#editStudent:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
</style>
<style>
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
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Student Details</span>
			</p>
		</div>
		<div class="input-group col-md-4" style="margin: 20px 0px;">

			<input type="text" class="form-control" id="searchvalue" Placeholder="Search......"  >
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
		
		<center>
		<input type="hidden" id="succmsg" value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />
			<div id="successmessages" class="successmessagediv">
				<div class="message-item">
					 <a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"><logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present></span></a>
				</div>
			</div>
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><logic:present name='errorMessage'  scope='request' ><bean:write name='errorMessage'  /></logic:present></span></a>
				</div>
			</div>
		</center>
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title"
						style="color: #767676; vertical-align: middle;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Student
						Details
					</h3></a>
				<div class="navbar-right">

					
					<span id="editStudent" class="buttons"
						data-toggle="tooltip" data-placement="bottom" title="Edit">View</span>

					
				<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span>

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
					
					
					<display:table class="table" id="allstudent"
						name="requestScope.studentdetailslist" requestURI="/adminMenu.html?method=studentList?"
						decorator="com.centris.campus.decorator.StudentRegistrationDecorator">
						<display:column title="Select">
    						  <input type="radio" name="selectBox"         
   									id="selectBox"   value="${allstudent.studentId }" />      
						</display:column>
						

<%-- 
						<display:column property="check" style="text-align:center"
							sortable="true"
							title="<input type='checkbox' name='selectall' style='text-align:center' id='selectall' onClick='selectAll()' />"></display:column> --%>


							<display:column property="academicYear" sortable="true"
							title="Academic Year<img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column property="studentAdmissionNo" sortable="true"
							title="Admission No	<img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>


						<display:column property="studentnamelabel" sortable="true"
							title="Student Name <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>



						<display:column property="classSectionId" sortable="true"
							title="Class and Division <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>



						<display:column property="dateofBirth" sortable="true"
							title="DOB <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>


						<display:column property="studentStatus" sortable="true"
							title="Status <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
							
							
							
						<%-- <display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty> --%>
							
					</display:table>
		

				
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