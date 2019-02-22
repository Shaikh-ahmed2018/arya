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

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JS/backOffice/Admin/Latheef.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script src="JS/newUI/jquery.js"></script>
<script src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Student/StudentPromotion.js"></script>

<title>eCampus Pro</title>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
</style>
<style>
.buttons{

vertical-align: 7px;

}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-7" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">New Student Promotion</span>
			</p>
		</div>


		<br /> <br />

		<center>

			<logic:present name="success" scope="request">
				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
									name="success" scope="request" />
						</span></a>
					</div>
				</div>
			</logic:present>

			<logic:present name="error" scope="request">
				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
									name="error" scope="request" /></span></a>
					</div>
				</div>
			</logic:present>


		</center>


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Promotion
						Details
					</h3></a>
				<div class="navbar-right">
					
					<a href="adminMenu.html?method=studentPromotionList">
						<span class="buttons" id="save">Save</span>
					</a> 
						&nbsp;
					
						<span class="buttons"  id="back">Back</span>
					</a>
				<!-- 	
					<a href=""><img src="images/download.png" class="downloadright"></a>  -->
					
				</div>
			</div>
			<form name="studentPramotionForm" action="" enctype="">
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">

						<div class="col-md-6" id="txtstyle">
							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Academic
									Year </label>
								<div class="col-xs-7">
									<select name="acadamicyear" id="acadamicyear"
										class="form-control">
										<option value="">-----Select----------</option>
									</select>
								</div>
							</div>

							<br />

							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Class </label>
								<div class="col-xs-7">
									<select name="classname" id="classname0" class="form-control">
										<option value="">----Select----------</option>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6" id="txtstyle">

							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Stream</label>
								<div class="col-xs-7">
									<select name="category" id="category0" class="form-control">
										<option value="">----Select----------</option>

									</select>
								</div>
							</div>
							<br />
							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Section </label>
								<div class="col-xs-7">
									<select name="section" id="section" class="form-control">
										<option value="">----Select----------</option>
									</select>
								</div>
							</div>

						</div>
					</div>

					<center>
						<input type="button" id="search" value="Search"
							style="width: 71px; height: 27px; font-size: 15px;"></input>
					</center>
					
					<center>

						<div class="errormessagediv" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips"></span></a>
							</div>
						</div>
					</center>
					<br />

					<center>
						<table class="table" id="allstudent">
						</table>
					</center>
					<br />
					<center>
						<table id="forbutton">
						</table>
					</center>


					<center>
						<logic:present name="notptomotedStudentList" scope="request">
							<display:table id="feeDetailsId" name="notptomotedStudentList"
								class="view" export="false"
								requestURI="/adminMenu.html?method=studPromotion">


								<display:column property="admissionno" title="Admission Number" />
								<display:column property="stname" title="StudentName" />

								<display:column property="categoryname" title="Category" />
								<display:column property="classname" title="Class" />
								<display:column property="sectionname" title="Section" />
								<display:column property="acadamicyear" title="Accadamic Year" />




							</display:table>
						</logic:present>
					</center>
				</div>
		</div>
	</div>
</body>
</html>