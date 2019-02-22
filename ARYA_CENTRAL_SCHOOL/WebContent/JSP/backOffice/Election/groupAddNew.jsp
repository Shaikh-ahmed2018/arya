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
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript"src="JS/backOffice/Election/GroupInsertUpdate.js"></script>
<script type="text/javascript">
	
</script>

<style>
.glyphicon:hover {
	cursor: pointer;
}
/* .modal-body {
	text-align: center;
} */
.buttons {
	top: 10px !important;
}
</style>

<style>
.download:hover {
	cursor: pointer;
}

.controls.style {
	margin-top: -32px;
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}

.searchWrap {
	padding: 6px;
}

.glyphicon-plus {
	font-size: 20px;
	line-height: 34px;
	color: #989898;
	padding: 2px 12px;
	margin-top: -6px;
	height: 0px;
	position: relative;
}

#allstudent  tr td,.allstudent  tr td {
	padding: 0;
	margin: 0;
}

.glyphicon-trash {
	margin-top: -2px;
	height: 20px;
}

.location {
	border: none;
	background: transparent;
}

.glyphicon-plus:before {
	content: "\2b";
	margin-right: -6px;
}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div id="dialog"></div>
		<div class="searchWrap">
			<div id="div2">

				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Add New Group</span>

				</p>
			</div>


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


		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2" href="#"
					style="color: #fff;"><h3 class="panel-title class"
						style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Group
						Details
					</h3></a>



				<div class="navbar-right" style="top: -3px; right: 10px;">

					<a> <span class="buttons" id="save">Save</span>
					</a> <span class="buttons" id="back">Back</span></a>

				</div>


			</div>
			<!-- pop up -->

			<div id="classOne" class="accordion-body collapse in">
				<div class="panel-body">

					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								align="right" id="inputnames">Academic Year <span
								style="color: red;">*</span></label>
							<div class="col-xs-7">
								<select id="academicYear" name="accyear" class="form-control">
									<option value="">----Select----</option>
								</select>
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								align="right" id="inputnames">Group Name <span
								style="color: red;">*</span></label>

							<div class="col-xs-7">
								<input type="text" name="groupname" id="groupname"
									class="form-control" />
							</div>
						</div>

					</div>
				</div>

				<div style="margin-top: 10px;" id="collapseOne"
					class="panel-collapse collapse in " role="tabpanel"
					aria-labelledby="headingOne">
					<div>
						<table style="width: 100%;" class='table' id='allstudent'>
							<tr>
								<th align="center">Sl.No.</th>
								<th style="text-align: center;">School Name</th>
								<th style="text-align: right; padding-right: 18px;"><span
									id="addgroup" onclick="addMoreHoliday()"
									class="glyphicon glyphicon-plus"
									style="width: 200px !important; text-align: right;"></span></th>
							</tr>

						</table>

					</div>
				</div>
			</div>

		</div>
</body>
</html>