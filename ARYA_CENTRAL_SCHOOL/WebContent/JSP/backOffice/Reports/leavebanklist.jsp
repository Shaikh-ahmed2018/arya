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

<script type="text/javascript" src="JS/backOffice/Reports/leavebank.js"></script>
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


<title>eCampus Pro</title>
<style>
#editleavebank:hover {
	cursor: pointer;
}

#deleteleavebank:hover {
	cursor: pointer;
}
#addleavebank:hover {
	cursor: pointer;
}

#xlss:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
.download:hover {
	cursor: pointer;
}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-7" id="div2">
			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Leave Bank Details</span>
			</p>
		</div>


		<div class="input-group col-md-5" style="margin: 20px 0px;">

			<input type="text" class="form-control" id="searchvalue"
				Placeholder="Search......"> <span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>





		<logic:present name="successmessagediv"  scope="request">
			<div class="successmessagediv"  align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>



		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">

				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessagediv"></span></a>
			</div>
		</div>


		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">

				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="errormessagediv1" align="center" style="display: none;">
			<div class="message-item1"></div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Leave
						Bank Details
					</h3></a>
				<div class="navbar-right">




					<span id="addleavebank" class="buttons"
						data-toggle="tooltip" data-placement="bottom" title="Add">Add</span>

					</a> <span id="editleavebank" class="buttons"
						data-toggle="tooltip" data-placement="bottom" title="Edit">Edit</span>



					<span  id="deleteleavebank" onclick="deleteLeave()"  class="buttons"
						data-toggle="tooltip" data-placement="bottom" title="Delete">Delete</span>

<!-- onclick="deleteLeave()" -->

						<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom"  style="top:-8px;">Download </span>
						 
						 
						 
				</div>

				<script>
					$(document).ready(function() {
						$('[data-toggle="tooltip"]').tooltip();
					});
				</script>

			</div>

			<input type="hidden" name="attnhidden" id="snoid" value="" />

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
							<span id="xlss"><img src="images/xl.png" class="xl"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
								id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div>






			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


					<logic:present name="leaveBank" scope="request">


						<display:table id="allstudent" class="table"
							name="requestScope.leaveBank"
							requestURI="/adminMenu.html?method=LeaveBankList" pagesize="10"
							decorator="com.centris.campus.decorator.LeaveBankDecorator">

							<display:column title="Select" headerClass="heading1">
								<input type="checkbox" name="selectBox" 
									onClick='getvaldetails(this)' value="${allstudent.sno}"
									id="${allstudent.sno}"> </>
							</display:column>

							<display:column property="academicyear" sortable="true"
								title=" Academic Year<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="totalleaves" sortable="true"
								title="SL<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="totalleaves" sortable="true"
								title="PL<img src='images/sort1.png' style='float: right'/>" />
								<display:column property="totalleaves" sortable="true"
								title="CL<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="totalleaves" sortable="true"
								title="Total Leaves<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="permonth" sortable="true"
								title="Allowed Per Month<img src='images/sort1.png' style='float: right'/>" />


	<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>
						</display:table>


					</logic:present>

					



				</div>
				<br />
			</div>
		</div>
	</div>

	<!-- <script src="JS/newUI/jquery.js"></script>
	<script src="JS/newUI/bootstrap.min.js"></script>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script> -->
</body>
</html>