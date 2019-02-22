
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
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
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
<script type="text/javascript" src="JS/backOffice/Events/stageSetting.js"></script> 
<script type="text/javascript">
	
</script>

<script>
function ShowTable(){
	$("#collapseOne").toggleClass("in");
}
</script>

<style>
.glyphicon:hover {
	cursor: pointer;
}
.buttons {
	top: 10px !important;
}
</style>

<style>

.controls.style {
	margin-top: -32px;
}
.pagebanner {
    margin-top: 13px;
}
.pagination {
    margin-right: 10px;
    float: right;
    margin-top: 10px;
}
.searchWrap {
	padding: 6px;
}
#allstudent tbody tr td,#allstudent thead tr th{
position: relative;
min-width: 20px;
border: 1px solid #dedede;
padding: 5px;
}
#allstudent tbody td{
border:1px solid #dedede
}
#allstudent tbody,#allstudent tbody td:nth-child(7){
border:none;
}

#allstudent thead tr{
border:1px solid #dedede
}
#allstudent thead,#allstudent thead tr th:nth-child(7){
border:none;
}
#allstudent tbody td,#allstudent thead tr{
border:none;
}
.deleteRow,#addgroup{
height:20px;
line-height: 16px;
position: absolute;
right: -6px;
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
.multiple{
height: auto !important;
}
.glyphicon-plus:before {
	content: "\2b";
	margin-right: -3px;
}
#allstudent tbody tr{
border:1px solid #dedede !imporatnt;
}
#allstudent tbody tr,#allstudent tbody tr td:nth-child(7){
border:none !important;
}
#allstudent tr:nth-child(n) td:last-child, .allstudent tr:nth-child(n) td:last-child, #allstudents tr:nth-child(n) td:last-child {
    background-color: transparent;
    border: 1px solid #dedede;
}
.successmessagediv{
margin:17px;
margin-left:250px;
}
.errormessagediv{
margin:14px;
margin-left:250px;
}
table#allstudent thead tr {
    background-color: #f5f5f5 !important;
}
</style>

</head>
<body>
	<div class="col-md-10 col-md-offset-2" id="div1">
		<div id="dialog"></div>
		<div id="deleteDialog"></div>
		<div class="searchWrap">
			<div id="div2"><p><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Stage Setting</span></p>
			</div>
		</div>
		
		
		
<!-- Pop up starts -->
			<div id="stageSetting" style="display: none">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
						</div>
					</div>
				 </div>
						
				
				<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
							<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right;">Event Name</label>	
									<div class="col-xs-7">
											<select id="eventName" name="eventName" class="form-control"> 
											<option value="">----Select----</option>
												<logic:present name="eventList">
													<logic:iterate id="name" name="eventList">
														<option value='<bean:write name="name" property="eventId"/>'><bean:write name="name" property="eventName"/></option>
													</logic:iterate>
												</logic:present>
											</select>
											<input type="hidden" id="hiddenStageId" value=""/>
									</div>
								</div>
							
							<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-5" style="text-align: right;">Stage Name</label>
								<div class="col-xs-7">
									<input type="text" name="stageName" id="stageName" class="form-control" />
									<input type="hidden" id="stageNameHidden" />
 								</div>
							</div>

							<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-5" style="text-align: right;">Floor Name </label>
								<div class="col-xs-7">
									<input type="text" name="floorName" id="floorName" class="form-control" />
								</div>
							</div>
							
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">Building </label>
							<div class="col-xs-7">
								<input type="text" name="building" id="building" class="form-control" />
							</div>
					</div>
					
						<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">Room Number</label>
							<div class="col-xs-7">
								<input type="text" name="roomNumber" id="roomNumber" class="form-control" />
							</div>
					</div>
					</div>


			<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
							<div class="form-group clearfix ">
									<!-- <label for="inputPassword" class="control-label col-xs-5" style="text-align: right;">Other Information</label> -->
							<div class="col-xs-7">
							<textarea id="info"  rows="9" cols="37" id="info" placeholder="-------------- Other Information ----------------"></textarea>
							</div>
					</div>

			</div>
			
			
			
			
			
			
			
		</div>
</div>
<!--Pop Up Ends  -->		
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
				<a data-toggle="collapse" data-parent="#accordion2" href="#collapseOne"
					style="color: #fff;"><h3 class="panel-title class" style="color: #767676;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Stage Setting </h3></a></div>

			<div id="classOne" class="accordion-body collapse in">
				<div class="panel-body">

					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5" align="right" id="inputnames" style="text-align: right; line-height: 35px;">Event Name</label>
							<div class="col-xs-7">
							<select id="eventNameList" name="eventNameList" class="form-control"> 
							<option value="">----------Select----------</option>
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
								align="right" id="inputnames" style="text-align: right; line-height: 35px;">Stage Name</label>
							<div class="col-xs-7">
									<select name="stageNameList" id="stageNameList" class="form-control">
									<option value="">----------Select----------</option>
									</select>
							</div>
							</div>
							
						</div>

					</div>
				</div>

		<div id="collapseOne" class="panel-collapse collapse in ">
						<table style="width: 100%;" class='table' id='allstudent'>
						<thead>	<tr>
								<th>Sl.No.</th>
								<th>Event Name</th>
								<th>Stage Name</th>
								<th>Building</th>
								<th>Floor</th>
								<th>Room Number</th>
								<th><span id="addgroup" class="glyphicon glyphicon-plus"></span></th>
							</tr>
						</thead>
						<tbody>
						
						</tbody>
						</table>
<div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select>
							<span  class='numberOfItem'></span>	
					</div>
					<div class='pagination pagelinks'></div>
					</div>
				</div>
			</div>

		</div>
</body>
</html>