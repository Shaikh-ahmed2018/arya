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
<script type="text/javascript" src="JS/backOffice/Events/EventStudReg.js"></script> 
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

#individualPart thead tr{
border:1px solid #dedede;
}
#individualPart thead,#individualPart thead tr th:nth-child(9){
border:none;
}

#allstudent tbody tr td,#allstudent thead tr th{
border: 1px solid #dedede !important;
}

#allstudent tr:nth-child(6n) td:last-child,#allstudents tr:nth-child(6n) td:last-child {
   border: 1px solid #dedede !important;
   text-align: left;
}
#allstudent tr:nth-child(2n) td:last-child, .allstudent tr:nth-child(2n) td:last-child, #allstudents tr:nth-child(2n) td:last-child {
    border: 1px solid #dedede !important;
}

.deleteRow{
height:20px;
line-height: 16px;
position: absolute;
right: 0px;
top: 0;
bottom: 0;
margin: auto;
}
.deleteRowInd{
height:20px;
line-height: 16px;
position: absolute;
right: 0px;
top: 0;
bottom: 0;
margin: auto;
}

.pagebanner {
    margin-top: 13px;
}
.pagination {
    margin-right: 10px;
    float: right;
    margin-top: 10px;
}
.form-control multiple{
height: auto !important;
}
.glyphicon-trash{
    padding: 2px 6px;
}
.glyphicon-plus:before {
	content: "\2b";
	margin-right: -3px;
}
div[class^=col-md]{
font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;
}
table[id^=participants] {
    width: 100%;
    border: 1px solid #dedede;
}
a#individualTab, a#groupTab{      
    font-size: 20px;
    padding: 5px 3px;
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
#allstudent tbody  tr table tr.captainHighlight td{
 background-color: #07aab9;
    color: #fff;
}
 #addgroup {
    height: 29px;
    line-height: 16px;
    position: absolute;
    right: 0px;
    top: 0;
    bottom: 2px;
    margin: auto;
    margin-bottom: 0px;
}
.successmessagediv{
margin:17px;
margin-left:150px;
}
#allstudent thead tr:last-child{
background:#f9f9f9 !important;
}
#individualPart tbody tr td:last-child(){
border:none !important;
}
</style>
</head>
<body>
 	<div class="col-md-10 col-md-offset-2" id="div1">
	<div id="dialog"></div>
	<div id="deleteDialog"></div>
		<div class="searchWrap">
			<div id="div2"><p><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Student Registration</span></p>
			</div>
		</div>
		
		
		
<!-- Pop up starts -->
<div id="studentReg" style="display: none">
<div class="col-md-12">
	<div class="row">
	
<div class="col-md-6">

<div class="form-group clearfix Option">
	<label for="inputEmail" class="control-label col-xs-4"style="text-align: right;">Registration Type</label>
	<div class="col-xs-8" id="radiostyle" style="margin-top: -2px;    margin-bottom: 5px;">
		<div3> <label id="TypeIndividual"><input type="radio" class="radio-inline" name="requested_by" id="individual"  value="individual"/>&nbsp;Individual&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<label id="TypeGroup"> <input type="radio" class="radio-inline" checked name="requested_by" id="group"  value="group" />&nbsp;Group</label> 
				&nbsp&nbsp&nbsp	<span><!-- <input type="checkbox" id="isHouse" class="radio-inline" value=""  onchange="valueChanged()"/><b>HouseWise</b></span> -->
		</div3>

	</div>
</div>


	<div class="form-group clearfix">
		<label for="inputEmail" class="control-label col-xs-4" 
			style="text-align: right;">Event Name</label>	
		<div class="col-xs-8">
				<select id="eventName" name="eventName" class="form-control"> 
				<option value="">----Select----</option>
					<logic:present name="eventList">
						<logic:iterate id="name" name="eventList">
							<option value='<bean:write name="name" property="eventId"/>'><bean:write name="name" property="eventName"/></option>
						</logic:iterate>
					</logic:present>
				</select>
				<input type="hidden" value="" id="eventIdHidden"> 
				<input type="hidden" id="categoryIdHidden" value="">
				<input type="hidden" id="progIdHidden" value="">
				<input type="hidden" id="progCaptainHidden" value="">
				<input type="hidden" id="registrationId" value="">
		</div>
	</div>
	
		<div class="form-group clearfix">
		<label for="inputEmail" class="control-label col-xs-4" 
			style="text-align: right;">Category Name</label>
		<div class="col-xs-8">
				<select id="categoryName" class="form-control"> 
					<option value="">----Select----</option>
				</select>
		</div>
	</div>

	<div class="form-group clearfix">
		<label for="inputEmail" class="control-label col-xs-4" 
			style="text-align: right;">Program Name</label>	
		<div class="col-xs-8">
				<select id="programName" class="form-control" multiple="multiple" style="height: 150px !important;">
					<option value="">----Select----</option>
				</select>
		</div>
	</div>


	<div class="form-group clearfix data">
		<label for="inputEmail" class="control-label col-xs-4" 
			style="text-align: right;">House Name</label>	
		<div class="col-xs-8">
				<select id="houseName" name="houseName" class="form-control"> 
				<option value="">--------Select-------</option>
				</select>
		</div>
	</div>
	
	<div class="form-group clearfix GroupIndividual">
		<label for="inputEmail" class="control-label col-xs-4" 
			style="text-align: right;">Student Name</label>	
		<div class="col-xs-8">
				<input type="text" id="studentName"  class="form-control">
		</div> 
	</div>
	
	
	
	
		<div class="form-group clearfix Group">
		<label for="inputEmail" class="control-label col-xs-4" 
			style="text-align: right;">Program Captain</label>	
		<div class="col-xs-8"> 
				<input type="text" id="programCaptain"  class="form-control">
				<input type="hidden" id="programCaptainHidden" value=""  class="form-control">
				<input type="hidden"  id="studentIdHidden" value=""/>
		</div> 
	</div>
	
	<div class="form-group clearfix GroupParti">
		<label for="inputEmail" class="control-label col-xs-4" 
			style="text-align: right;">Participants Name</label>	
			<div class="col-xs-7">
					<select id="participantsList" multiple>
					</select>
			</div>
		</div>
	</div>	
		
		
		
<div class="col-md-6">
			<div class="form-group clearfix GroupStaffInc ">
			<label for="inputEmail" class="control-label col-xs-3" 
				style="text-align: right;">Staff Incharge</label>	
		<div class="col-xs-9">
				<textarea rows="6" cols="35" id="info_staffG"></textarea>
		</div>
	</div>

	<div class="form-group clearfix GroupSyn">
		<label for="inputEmail" class="control-label col-xs-3" 
			style="text-align: right;">SYNOPSYS</label>	
		<div class="col-xs-9">
				<textarea rows="6" cols="35" id="info_synopsisG"></textarea> 
		</div>
	</div>

	<div class="form-group clearfix GroupStageReq">
		<label for="inputEmail" class="control-label col-xs-3" 
			style="text-align: right;">Stage Requirements if any</label>	
							<div class="col-xs-9">
									<textarea rows="6" cols="35" id="info_reqG"></textarea>
							</div>
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
			<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Student Registration </h3></a>
			
				<div class="navbar-right">
					<span class="buttons"  id="addgroup"> Register</span>
				</div>
			
			</div>

<div id="classOne" class="accordion-body collapse in">
	<div class="panel-body">

		<div class="col-md-6 groupData" id="txtstyle">
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right" id="inputnames">Event Name</label>
				<div class="col-xs-7">
				<select id="eventNameListGroup" name="eventNameListGroup" class="form-control"> 
				<option value="">----Select----</option>
				</select>
				</div>
			</div>

			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5"
					align="right" id="inputnames">Program Name<span style="color: red;"></span></label>
				<div class="col-xs-7">
						<select name="progNameListGroup" id="progNameListGroup" class="form-control">
						<option value="">----------Select----------</option>
						</select>
				</div>
				</div>
			</div>
			
			
			<div class="col-md-6 groupData" id="txtstyle">
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right" id="inputnames">Category Name</label>
				<div class="col-xs-7">
				<select id="CategoryNameListGroup" name="CategoryNameListGroup" class="form-control"> 
				<option value="">----Select----</option>
				</select>
				</div>
			</div>
				
				<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5"
					align="right" id="inputnames">House Name<span style="color: red;"></span></label>
					<div class="col-xs-7">
							<select name="houseNameListGroup" id="houseNameListGroup" class="form-control">
							<option value="">----Select----</option>
							</select>
					</div>
					</div>

				</div>
				
				
				
			<div class="col-md-6 IndividualData" id="txtstyle">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5" align="right" id="inputnames">Event Name</label>
					<div class="col-xs-7">
					<select id="eventNameListIndiv" name="eventNameListIndiv" class="form-control"> 
					<option value="">----Select----</option>
					</select>
					</div>
				</div>
				
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5" align="right" id="inputnames">Program Name</label>
					<div class="col-xs-7">
					<select id="progNameListIndiv" name="progNameListIndiv" class="form-control"> 
					<option value="">----Select----</option>
					</select>
					</div>
				</div>
				</div>
				
				
			
			<div class="col-md-6 IndividualData" id="txtstyle">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5" align="right" id="inputnames">Category Name</label>
					<div class="col-xs-7">
					<select id="catNameListIndiv" name="catNameListIndiv" class="form-control"> 
					<option value="">----select----</option>
					</select>
					</div>
				</div>
				
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5" align="right" id="inputnames">House Name</label>
					<div class="col-xs-7">
					<select id="houseNameListIndiv" name="houseNameListIndiv" class="form-control"> 
					<option value="">----select----</option>
					</select>
					</div>
				</div>
			</div>	
		</div>
</div>


				<div>
				<div class="slideTab clearfix">
				<div class="tab">
					<ul class="nav nav-tabs">
						<li  class="active"><a data-toggle="tab" href="#classHistory" id="groupTab">Group</a></li>
						<li><a data-toggle="tab" href="#contacts"  id="individualTab">Individual</a></li>
						
					</ul>
				
					<div id="contacts" class="tab-pane">
					<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
							<div class="col-md-8" id=div2></div>
							<div id="individualTable"></div>
							<div id="groupTable"></div>	
						</div>
					</div>
					
				</div>
			</div>
			</div>
	

<div id="collapseOne" class="panel-collapse collapse in ">
				<table style="width: 100%;" class='table' id='allstudent'>
			<thead>	<tr>
					<th>Sl.No.</th>
					<th>Registration No</th>
					<th>Participants Details</th>
					
					<th>House Name</th>
					<!-- <th><span id="addgroup" class="glyphicon glyphicon-plus"></span></th> -->
				</tr>
			</thead>
			<tbody>
			
			</tbody>
			</table>
<!-- 						<div style="margin-top: 12px;">
<div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select>
							<span class='noOfRecords'></span>
					</div >
					<div class='pagination pagelinks'></div>
					</div> -->
	</div>
	
	
	
	
	
	
			<div id="collapseOne" class="panel-collapse collapse in ">
			<table style="width: 100%;" class='allstudent' id='individualPart'>
			<thead>	<tr>
					<th>Sl.No.</th>
					<th>Registration No</th>
					<th>Admission No</th>
					<th>Student Name</th>
					<th>Class</th>
					<th>Section</th>
					<th>Program</th>
					<th>Image</th>
					<th>House Name</th>
				<!-- 	<th><span id="addgroupInd" class="glyphicon glyphicon-plus"></span></th> -->
							</tr>
						</thead>
						<tbody>
						
						</tbody>
						</table>


				</div>
				
				
				
				
				
			</div>

		</div>
</body>
</html>