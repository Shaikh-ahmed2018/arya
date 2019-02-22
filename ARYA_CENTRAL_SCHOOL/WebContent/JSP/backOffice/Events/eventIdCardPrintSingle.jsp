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


<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Events/eventIdCardPrintSingle.js"></script> 
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

#allstudent tbody, #allstudent tbody td:nth-child(4) {
    border: 1px solid #dedede;
}

.deleteRow,#addgroup{
height:20px;
line-height: 16px;
position: absolute;
right: 0px;
top: 0;
bottom: 0;
margin: auto;
}
#allstudent tbody td{
border: 1px solid #dedede;
    padding: 5px;
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

.form-control multiple{
height: auto !important;
}
.glyphicon-plus:before {
	content: "\2b";
	margin-right: -3px;
}
div[class^=col-md]{
font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;
}
select#participantsList{
width: 116%;
height: 190px !important;
}
.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons {
    width: 75% !important;
    left: 0 !important;
    right: 0 !important;
    margin: auto;
}


#allstudent thead{
border:1px solid #dedede
}


#allstudent tbody td{
border:1px solid #dedede
}
                                                        

</style>
</head>
<body>
	<div class="col-md-10 col-md-offset-2" id="div1">
	<div id="dialog"></div>
		<div class="searchWrap">
			<div id="div2"><p><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Print Event Id Card</span></p>
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

		<div class="panel panel-primary clearfix">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2" href="#"
					style="color: #fff;"><h3 class="panel-title class"
						style="color: #767676;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Event Id Card Print</h3></a></div>

			<div id="classOne" class="accordion-body collapse in">
				<div class="panel-body">

					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5" align="right" id="inputnames">Event Name</label>
							<div class="col-xs-7">
							<select id="eventNameList" name="eventNameList" class="form-control"> 
							<option value="">----select----</option>
							<logic:present name="eventList">
													<logic:iterate id="name" name="eventList">
														<option value='<bean:write name="name" property="eventId"/>'><bean:write name="name" property="eventName"/></option>
													</logic:iterate>
												</logic:present>
									</select>
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" id="eventprogramname">Programme Name<span style="color: red;"></span></label>
							<div class="col-xs-7">
									<select name="registrationNumberList" id="registrationNumberList" class="form-control">
									<option value="">----select----</option>
									</select>
							</div>
						</div>
						
						<div class="form-group clearfix">
						<div class="control-label col-xs-5"></div>
						<div class="col-xs-6">
							<button class="btn btn-info" type="button" id="printall">print</button>
						 	
		</div>
						</div>
						</div>
						
						
					<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" >Category Name<span style="color: red;"></span></label>
							<div class="col-xs-7">
									<select name="eventcategory" id="eventcategory" class="form-control">
									<option value="">----select----</option>
									</select>
							</div>
						</div>
						<br><br>

						<div class="form-group clearfix">
							<div class="control-label col-xs-5"></div>
							<div class="col-xs-6">
								
								
							</div>
						</div>

					</div>
				</div>
		</div>


			<div id="collapseOne" class="panel-collapse collapse in ">
				<table style="width: 100%;" class='table' id='allstudent'>
					<thead>
						<tr>

							<th>S.No</th>
							<th>Ad.No.</th>
							<th>Name</th>
							<th>Roll No.</th>
							<th>Class</th>
							<th>School Name</th>
							<th>Image</th>

						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
				<div style="margin-top: 12px;"></div>
			</div>
		</div>

		</div>
</body>
</html>