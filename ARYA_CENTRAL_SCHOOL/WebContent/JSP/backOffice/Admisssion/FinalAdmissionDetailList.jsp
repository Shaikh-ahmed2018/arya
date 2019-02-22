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
<script type="text/javascript"
	src="JS/backOffice/Admission/FinalAdmissionDetailList.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">



<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />

<style>
.glyphicon:hover {
	cursor: pointer;
}
/* .modal-body {
	text-align: center;
} */
</style>

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
.buttons{

vertical-align: 6px;

}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Final Admisssion Details</span>
			</p>
		</div>




		<div class="input-group col-md-4" style="margin: 20px 0px;">
			<input type="text" class="form-control" id="searchvalue"
				Placeholder="Search......"> <span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>



		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />

													</logic:present>'></input>


		<logic:present name="successMessage" scope="request">

			<div class="successmessagediv">
				<div class="message-item">

					<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
								name="successMessage" scope="request" />
					</span></a>
				</div>
			</div>

		</logic:present>

		<logic:present name="errorMessage" scope="request">

			<div class="successmessagediv">
				<div class="message-item">

					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="errorMessage" scope="request" />
					</span></a>
				</div>

			</div>

		</logic:present>



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


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Final
						Admisssion Details
					</h3></a>



				<div class="navbar-right">

					<span class="buttons"
						id="editconfirmedadmission" data-toggle="tooltip"
						data-placement="bottom" title="Edit">Edit</span>&nbsp;


					<!-- <span class="glyphicon glyphicon-trash" id="delete"
						data-toggle="tooltip" data-placement="bottom" title="Delete"></span> -->
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

					<logic:present name="TemporaryAdmissionDetailsList" scope="request">
						<display:table class="table" id="allstudent"
							name="TemporaryAdmissionDetailsList" pagesize="10"
							requestURI="/adminMenu.html?method=FinalAdmisssionList"
							decorator="com.centris.campus.decorator.TemporaryAdmissionDetailsDecorator">

							<display:column property="temporary_details_checkbox"
								media="html" sortable="true"
								title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />"></display:column>

							<display:column property="accyear" sortable="true"
								title="Academic Year<img src='images/sort1.png' style='float: right'/>" />

							<display:column property="studentfirstName" sortable="true"
								title="Student Name<img src='images/sort1.png' style='float: right'/>" />

							<display:column property="classname" sortable="true"
								title="Class <img src='images/sort1.png' style='float: right'/>" />

							<display:column property="application_recieved_through" sortable="true"
								title="Mode Of Application <img src='images/sort1.png' style='float: right'/>" />

							<display:column property="evaluation_date" sortable="true"
								title="Evaluation Date <img src='images/sort1.png' style='float: right'/>" />

							<display:column property="evaluated_by" sortable="true"
								title="Evaluated By <img src='images/sort1.png' style='float: right'/>" />

							<display:column property="max_marks" sortable="true"
								title="Max. Marks <img src='images/sort1.png' style='float: right'/>" />

							<display:column property="marks_secured" sortable="true"
								title="Secured Marks <img src='images/sort1.png' style='float: right'/>" />

							<display:column property="remarks" sortable="true"
								title="Status<img src='images/sort1.png' style='float: right'/>" />

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

	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>