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

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
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
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
<link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/backOffice/Events/ProgramSetting.js"></script> 
 <!-- <link href="JSP/backOffice/Events/DocCSS/programSettingDoc.css" rel="stylesheet" type="text/css"> --> -->
<script type="text/javascript">
function valueChanged()
{
	 if($('#isHouse').is(":checked"))   
	        $(".data").show();
	    else
	        $(".data").hide();
}
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
select#judgeName{
width: 150%;
height: 259px !important;
}

#allstudent tbody tr td,#allstudent thead tr th{
position: relative;
min-width: 30px;
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
.successmessagediv{
margin:18px;
margin-left:200px;
}
.multiple{
height: auto !important;
}
.glyphicon-plus:before {
	content: "\2b";
	margin-right: -3px;
}
.add-on{
position: absolute;
top:1px;
right: 8px;
}
.errormessagediv{
margin-top:13px;
}
table#allstudent thead tr {
    background-color: #f5f5f5 !important;
}
</style>
</head>
<body>
	<div class="col-md-10" id="div1">
		<div id="dialog"></div>
		<div id="deleteDialog"></div>
		<div class="searchWrap">
			<div id="div2"><p><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Program Setting</span></p>
			</div>
		</div>
		
<!-- Pop up starts -->
<div id="progCreation" style="display: none">
<div class="col-md-12">
	<div class="row">
	 </div>
<div class="col-md-7" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
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
						<input type="hidden" id="eventIdHidden" value=""/>
					
				</div>
			</div>
			
	
<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4" 
					style="text-align: right;">Category Name</label>	
			<div class="col-xs-8">
					<select id="categoryName" name="categoryName" class="form-control"> 
					<option value="">----Select----</option>
					</select>
					<input type="hidden" id="categoryIdHidden" value=""/>
			</div>
		</div>

<div class="form-group clearfix ">
<label for="inputPassword" class="control-label col-xs-4"
	style="text-align: right;">Program Type </label>
	<div class="col-xs-8">
		<select name="progType" id="progType" class="form-control">
		<option value="">----Select---</option>
		<option value="group">Group</option>
		<option value="individual">Individual</option>	
		</select>
	</div>
</div>
		<div class="form-group clearfix">
			<label for="inputEmail" class="control-label col-xs-4" 
				style="text-align: right;">Program Name</label>	
		<div class="col-xs-8">
				<input type="text" id="progName" name="progName" class="form-control"> 
				<input type="hidden" id="progIdHidden" value="" />
				<input type="hidden" id="programNameHidden" value=""/>
		</div><!-- <span><input type="checkbox" id="isHouse" value=""  onchange="valueChanged()"/><b>HouseWise</b></span> -->
		</div>
		
<div class="form-group clearfix ">
<label for="inputPassword" class="control-label col-xs-4"
	style="text-align: right;">Program Date</label>
<div class="col-xs-4">
	<input type="text" name="progDate" id="progDate" class="form-control" value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="fromdate"/></logic:present>"></input>
	</div><span><input type="checkbox" id="isHouse" value=""  onchange="valueChanged()"/><b>HouseWise</b></span>
</div>

<div class="form-group clearfix data">
<label for="inputPassword" class="control-label col-xs-4"
	style="text-align: right;">No. of Childerns</label>
<div class="col-xs-4" style="align:right;">
	<input type="text" name="NoOfChildHouse" id="NoOfChildHouse" class="form-control" />
</div>
</div>


<div class="form-group clearfix dataTeam">
	<label for="inputEmail" class="control-label col-xs-4" style="text-align: right;">Teams Allowed</label>
	<div class="col-xs-4">
		<input type="text" name="termsAllowed" id="termsAllowed" class="form-control" />
	</div>
</div>

<div class="form-group clearfix ">
	<label for="inputEmail" class="control-label col-xs-4" style="text-align: right;">No. of Participants </label>
	<div class="col-xs-4">
		<input type="text" name="PartiNo" id="PartiNo" class="form-control" />
	</div>
</div>

<div class="form-group clearfix ">
	<label for="inputEmail" class="control-label col-xs-4" style="text-align: right;">Duration </label>
	<div class="col-xs-4">
		<input type="text" name="duration" id="duration" class="form-control" />
	</div>
	<span class="min">Minutes</span>
</div>



	<div class="form-group clearfix">
		<label for="inputEmail" class="control-label col-xs-4" 
			style="text-align: right;">Participants Type</label>	
		<div class="col-xs-8">
				<input type="text" id="partiType" name="partiType" class="form-control"> 
		</div>
	</div>

	
<div class="form-group clearfix ">
	<label for="inputEmail" class="control-label col-xs-4" style="text-align: right;">Staff Info</label>
		<div class="col-xs-8">
			<textarea id="infoStaff"  rows="4" cols="69"></textarea>
		</div>
</div>	
<div class="form-group clearfix ">
	<label for="inputEmail" class="control-label col-xs-4" style="text-align: right;">General Info</label>
					<div class="col-xs-8">
						<textarea id="infoGeneral"  rows="4" cols="69"></textarea>
					</div>
			</div>		
		</div>	


<div class="col-md-5" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
<div class="form-group clearfix ">
		<label for="inputEmail" class="control-label col-xs-5" style="text-align: left;">Assign Judges</label>
	<div class="col-xs-8">
		<select id="judgeName" name="judgeName" multiple="">
		<option value="Judge1">Judge1</option>
<option value="Judge2">Judge2</option>
<option value="Judge3">Judge3</option>
<option value="Judge4">Judge4</option>
<option value="Judge5">Judge5</option>
<option value="Judge6">Judge6</option>
<option value="Judge7">Judge7</option>
<option value="Judge8">Judge8</option>
<option value="Judge9">Judge9</option>
<option value="Judge10">Judge10</option>
	
									</select>
								</div>
							</div>
					</div>
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
		<a data-toggle="collapse" data-parent="#accordion2" href="#collapseOne"
			style="color: #fff;"><h3 class="panel-title class"
style="color: #767676;vertical-align: text-top;">
			<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Program Setting </h3></a></div>


<div id="classOne" class="accordion-body collapse in">
	<div class="panel-body">
		<div class="col-md-6" id="txtstyle">
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right" id="inputnames">Event Name</label>
				<div class="col-xs-7">
				<select id="eventNameList" name="eventNameList" class="form-control"> 
				<option value="">----------Select---------</option>
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
		align="right" id="inputnames">Category<span style="color: red;"></span></label>
<div class="col-xs-7">
		<select name="categoryNameList" id="categoryNameList" class="form-control">
		<option value="">----------Select---------</option>
		</select>
</div>
</div>

<div class="form-group clearfix">
<label for="inputPassword" class="control-label col-xs-5" align="right" id="inputnames">Program Name<span style="color: red;"></span></label>
					<div class="col-xs-7">
							<select name="programNameList" id="programNameList" class="form-control">
								<option value="">----------Select---------</option>
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
		<th>Category Name</th>
		<th>Program Name</th>
		<th>Program Type</th>
	<!-- 	//<th>Participants type</th>
		<th>Teams Allowed</th>
		<th>Total Participants</th> -->
		<th>Duration</th>
		<th>Program Date</th>
<!-- 	//	<th>Housewise</th>
		<th>Total Childrens</th> -->
		
		<th><span id="addgroup" class="glyphicon glyphicon-plus"></span></th>
	</tr>
</thead>
<tbody>

</tbody>
</table>
<div style="margin-top: 12px;">
<div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select>
							<span class='numberOfItems'></span>
							
					</div >
					<div class='pagination pagelinks'></div>
					</div>
				</div>
			</div>

		</div>
</body>
</html>