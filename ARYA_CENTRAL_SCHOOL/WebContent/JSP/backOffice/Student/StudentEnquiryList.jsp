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
	src="JS/backOffice/Student/EnquiryList.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Student/StudentEnquiry.js"></script>
<style>
#editDesignationId:hover {
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
					id="pageHeading">Admission Enquiry </span>
			</p>
		</div>
		<div class="input-group col-md-4" style="margin: 20px 0px;">

			<input type="text" class="form-control" Placeholder="Search......"
				id="search"> <span class="input-group-btn">
				<button class="btn btn-default" type="button" id="searchid">
					<i class="fa fa-search"></i>
				</button>
			</span>
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
		</center>


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;
					</h3></a>
				<div class="navbar-right">
					<a href="adminMenu.html?method=enquiryCreateScreen" id="plus"><span
						class="buttons" data-toggle="tooltip" data-placement="bottom" title="Add">Save</span></a>
						 <span
						id="editDesignationId" class="buttons" data-toggle="tooltip" data-placement="bottom" title="Edit">Edit</span>
					<span id="trash" class="buttons" data-toggle="tooltip" data-placement="bottom" title="Delete">Delete</span> 
					
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
						name="requestScope.EnquiryDetails" pagesize="10"
						decorator="com.centris.campus.decorator.StudentEnquiryDecorator"
						requestURI="/adminMenu.html?method=studentEnquiryList">
						<display:column property="check" sortable="true"
							title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" />
						<display:column property="enq_fullname" title="Student Name" />
						<display:column property="enq_age" title="Age" />
						<display:column property="enq_conname" title="Contact Name" />
						<display:column property="enq_conPhno" title="Contact Number" />
						<display:column property="enq_classname" title="Joining Class" />
						<display:column property="enq_int_status"
							title="Interaction Status" />
						<display:column property="enq_reg_status"
							title="Admission Status " />
						<display:column property="enq_join_status" title="Joining Status" />
						<display:column property="enq_address" title="Address" />
						
						
						
						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>
						
					</display:table>

					
				</div>
				<br />
			</div>
		</div>
	</div>
</body>
</html>