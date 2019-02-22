<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script src="JS/newUI/jquery.js"></script>
<script src="JS/newUI/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


<script type="text/javascript"
	src="JS/backOffice/Teacher/LeaveRequest.js"></script>

<!-- <script type="text/javascript"
	src="JS/backOffice/Teacher/LeaveRequestTeacher.js"></script>  -->

<title>eCampus Pro</title>

<style>
#plus:hover {
	cursor: pointer;
}

#editID:hover {
	cursor: pointer;
}

#delete:hover {
	cursor: pointer;
}

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
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
	
		<div class="" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">LEAVE REQUEST DETAILS</span>
			</p>
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
					class="validateTips"></span></a>
			</div>
		</div>



		<input type="hidden" name="userhidden" class="userhiddenclass"
			id="userhiddenid"
			value='<logic:present name="parentid"><bean:write name="parentid" />
													</logic:present>'></input>



		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchterms"
			value='<logic:present name="searchterm"><bean:write name="searchterm" />

													</logic:present>'></input>




		 <input type="hidden" name="hleavelist" id="leavestatusid" value='<logic:present name="hidden" property="status"><bean:write name="hidden" property="status"></bean:write></logic:present>'>	



		<input type="hidden" name="attnhidden" id="snoid" value="" /> <input
			type="hidden" name="attnhidden1" id="requesttoid" value="" />



		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3 class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Leave
						Request Details
					</h3></a>
				<div class="navbar-right">
					<a onClick='sendrequest()' id="plus"> <span
						class="buttons">Add</span>
					</a>


					<a onClick='editequest()' id="editID"> <span
						class="buttons">Edit</span>
					</a>
				
					<span id="delete" class="buttons">Delete</span>
					<!--  </a> -->

					 <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						>Download </span>


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

					<logic:present name="leavelist" scope="request">
						<display:table class="meeting table" pagesize="10"
							name="requestScope.leavelist"
							requestURI="parentMenu.html?method=leaveRequest"
							id="allstudent">

							<display:column title="Select" headerClass="heading1">
								<input type="checkbox" name="getempid"
									onClick='getvaldetails(this)' value="Get Salary Details"
									id="${allstudent.sno},${allstudent.status}"> </>
							</display:column>


								
							<display:column property="totalleave" sortable="true"
								title="No Of Leaves<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="requestto" sortable="true"
								title="Teacher Name<img src='images/sort1.png' style='float: right'/>"></display:column>


							<display:column property="fromdate" sortable="true"
								title="Satrt Date<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="todate" sortable="true"
								title="End Date<img src='images/sort1.png' style='float: right'/>"></display:column>
							
							<display:column property="status" sortable="true"
								title="Leave Status<img src='images/sort1.png' style='float: right'/>"></display:column>


							<display:column property="leavetype" sortable="true"
								title="Leave Type<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

							<display:setProperty name="paging.banner.placement"
								value="bottom"></display:setProperty>




						</display:table>

					</logic:present>


					
				</div>
				<br />
			</div>
		</div>
	</div>


</body>
</html>