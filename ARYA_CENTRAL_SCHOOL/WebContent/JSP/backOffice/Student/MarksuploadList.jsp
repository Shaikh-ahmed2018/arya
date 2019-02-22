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


<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="JS/backOffice/Student/MarksUpload.js"></script>


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
					id="pageHeading">Exam Marks Details</span>
			</p>
		</div>
		<div class="input-group col-md-4" style="margin: 20px 0px;">

			<input type="text" id="searchterm" class="form-control" Placeholder="Search......">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="searchbtn">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
		<center>
			<div class="successmessagediv" style="display: none;">
				<div class="message-item">
					Warning <a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>
		</center>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title"
						style="color: #767676; vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Student Marks
					</h3></a>
				<div class="navbar-right">

					<!-- <a href="adminMenu.html?method=addStudent"> <span
						class="glyphicon glyphicon-plus" data-toggle="tooltip"
						data-placement="bottom" title="Add"></span>
					</a> --> <span id="editStudent" class="buttons">Edit</span>

					<!-- <span id="trash" class="glyphicon glyphicon-trash"
						data-toggle="tooltip" data-placement="bottom" title="Delete"></span> -->
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
					<display:table class="table" id="allstudent"
						name="requestScope.MarksUploadList" pagesize="10"
						requestURI="/teachermenuaction.html?method=marksStatus"
						decorator="com.centris.campus.decorator.MarksUploadDecorator">



						<display:column property="check" style="text-align:center"
							sortable="true"
							title="<input type='checkbox' name='selectall' style='text-align:center' id='selectall' onClick='selectAll()' />"></display:column>


						<display:column property="examname" sortable="true"
							title="Exam Name	<img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column property="classname" sortable="true"
							title="CLass Name	<img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column property="sectionname" sortable="true"
							title="Section Name <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>



						<display:column property="subjectname" sortable="true"
							title="Subject Name<img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>



						<display:column property="examdate" sortable="true"
							title="Exam Date <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>


						<display:column property="examstarttime" sortable="true"
							title="Exam StartTime <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
							
						<display:column property="examendtime" sortable="true"
							title="Exam EndTime <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
							
							
									
						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>
							
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