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


<script type="text/javascript"
	src="JS/backOffice/Student/StudentPromotion.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

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
		<div class="col-md-5" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Promotion Details</span>
			</p>
		</div>

		<div class="input-group col-md-7">
		
			<label style="margin: 20px 0px; width: 8%; border: none;"
				class="form-control">Stream</label> <select name="category"
				id="category0" class="form-control" 
				style="margin: 20px 0px; margin-left: 0%; width: 20%;">
				<option value=""></option>
				
			</select> 
		
			<label style="margin: 20px 0px; width: 8%; border: none;"
				class="form-control">Class</label>
				 <select name="classname"
				id="classname0" class="form-control" 
				style="margin: 20px 0px; margin-left: 0%; width: 20%;">
				<option value=""></option>
				</select>
				<label style="margin: 20px 0px; width: 8%; border: none;"
				class="form-control">Section</label> <select name="section"
				id="section" class="form-control" 
				style="margin: 20px 0px; margin-left: 0%;width: 20%">
				<option value=""></option>
				<!-- <option value="">A</option>
				<option value="">B</option>
				<option value="">C</option> -->
			</select>

			<button class="btn btn-default" type="button" id="searchButtonId"
				style="margin: 20px 0px;">
				<i class="fa fa-search"></i>
			</button>

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


					<a href="adminMenu.html?method=studentPromotionscreen" id="plus">
						<span class="buttons">Add</span>
					</a> 
					
					<!-- <a href="adminMenu.html?method=studentPromotionscreenedit" id="editID">
					
						<span class="buttons">Edit</span>
					</a>
					
					 <a href="adminMenu.html?method=studentPromotionList">
					 	 <span id="trash" class="buttons">Delete</span> -->
					 </a> 
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
						name="requestScope.StudentPramotionlist" pagesize="10"
						requestURI="/adminMenu.html?method=studentPromotionList">
						<%-- <display:column property="check" sortable="true"
							title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" /> --%>
						<display:column property="admissionno" title="Admission No" />
						<display:column property="stname" title="Student Name" />
						<display:column property="classname" title="Class Name" />
						<display:column property="sectionname" title="Section Name" />
						<display:column property="acadamicyear" title="Academic year" />
						
						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

					    	<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>
						
					</display:table>

					<!-- <ul class="pagination" style="float: right; margin: 0;">
						<li><a href="#">&laquo;</a></li>
						<li class="active"><a href="#">1</a></li>
						<li class=""><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul> -->
				</div>
				<br />
			</div>
		</div>
	</div>


</body>
</html>