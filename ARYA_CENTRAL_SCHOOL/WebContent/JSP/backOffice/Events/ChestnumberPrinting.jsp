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
<script type="text/javascript" src="JS/backOffice/Events/ChestNoGeneration.js"></script>
<script type="text/javascript">
</script>

<style>
.glyphicon:hover {
	cursor: pointer;
}

.buttons {
	top: 10px !important;
}

.controls.style {
	margin-top: -32px;
}

.searchWrap {
	padding: 6px;
}

 #allstudent tbody td,#allstudent tbody tr  {
    border: 1px solid #dedede;
}
#allstudent tbody td:last-child,#allstudent tbody th:last-child  {
    border: 1px solid #dedede;
    text-align: center !important;
        font-size: 14px;
    color: #000000;
}

#allstudent thead,#allstudent thead tr th:nth-child(6),#allstudent thead td th:nth-child(6)
{
    border: 1px solid #dedede;
    background: #f9f9f9 !important;
    text-align: center;
}
#allstudent thead tr {
	border: 1px solid #dedede;
}

.deleteRow, #addgroup {
	height: 20px;
	line-height: 16px;
	position: absolute;
	right: 8px;
	top: 0;
	bottom: 0;
	margin: auto;
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
.multiple {
	height: auto !important;
}

.glyphicon-plus:before {
	content: "\2b";
	margin-right: -3px;
}

.col-md-12 {
	width: 100%;
	margin-left: -15px;
}

#save {
	display: none;
}

#headbuttons {
	margin-top: -3px;
}
#accyear{
display: none;
}
#printArea{
display: none;
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
						id="pageHeading">Chest Number Generation</span>
				</p>
			</div>
		</div>
		
		<div id="deleteDialog" style="display: none"> 
			<p>Are You Sure to Delete?</p>
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

		<div class="panel panel-primary clearfix">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2" href="#"
					style="color: #fff;"><h3 class="panel-title class"
						style="color: #767676;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Chest Number Generation
					</h3></a>
				<div class="navbar-right" id="headbuttons">
					<span class="buttons" id="chestnogeneration" style="margin-right:3px;">Generate Chest No</span> 
						
						<span class="buttons" id="iconsimg" data-toggle="modal"
							data-target="#myModal" style="left:-8px;"
							data-placement="bottom">Download </span>
						
						<span class="buttons" id="print" >Print</span> 
						<!-- <span class="buttons" id="print" data-toggle="modal">Print</span> --> 
						
						
						<span class="buttons" style="display: none" id="back1"><a
						href="EventsMenu.html?method=ChestNoGeneration">Back</a></span>
				</div>
			</div>
			
			<!-- pop up Start -->
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
			<!-- pop up End -->

			<div id="classOne" class="accordion-body collapse in">
				<div class="panel-body">

					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix" style="margin-top:7px;">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" id="inputnames">Event Name</label>
							<div class="col-xs-7">
								<select id="eventNameList" name="eventNameList"
									class="form-control">
									<option value="all">ALL</option>
									<logic:present name="eventList">
										<logic:iterate id="name" name="eventList">
											<option value='<bean:write name="name" property="eventId"/>'><bean:write
													name="name" property="eventName" /></option>
										</logic:iterate>
									</logic:present>
								</select>
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" id="inputnames">Category<span
								style="color: red;"></span></label>
							<div class="col-xs-7">
								<select name="categoryNameList" id="categoryNameList"
									class="form-control">
									<option value="all">ALL</option>
								</select>
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" id="inputnames">Program Name<span
								style="color: red;"></span></label>
							<div class="col-xs-7">
								<select name="programNameList" id="programNameList"
									class="form-control">
									<option value="all">ALL</option>
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="collapseOne" class="panel-collapse collapse in ">
				<table style="width: 100%;" class='table' id='allstudent'>
					<thead>
						<tr>
							<th>Sl.No.</th>
							<th>Admission No</th>
							<th>Participant Name</th>
							<th>School Name</th>
							<th>Image</th>
							<th>ChestNo</th>
							<!-- <th><span id="addgroup" class="glyphicon glyphicon-plus"></span></th> -->
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
				<div style="margin-top: 12px;">
					<div class='pagebanner' style="margin-top:2px;">
						<select id='show_per_page'>
							<option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option>
							<option value='500'>500</option>
						</select> 
						<span class='numberOfItem'></span>
					</div>
					<div class='pagination pagelinks'></div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="printArea">
	
	</div>
</body>
</html>