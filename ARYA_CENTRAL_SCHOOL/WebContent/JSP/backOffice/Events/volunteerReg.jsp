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

<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Events/VolunteerReg.js"></script>
<script type="text/javascript">
	
</script>

<style>
.glyphicon:hover {
	cursor: pointer;
}
table#allstudent thead tr {
    background-color: #f5f5f5 !important;
}
.buttons {
	top: 10px !important;
}
</style>

<style>
.controls.style {
	margin-top: -32px;
}

.searchWrap {
	padding: 6px;
}

#allstudent tbody tr td, #allstudent thead tr th {
	position: relative;
	min-width: 20px;
	border: 1px solid #dedede;
	padding: 5px;
}

#allstudent tbody td {
	border: 1px solid #dedede
}

#allstudent tbody th:nth-child(1) {
	text-align: center;
}
#allstudent thead tr {
	border: 1px solid #dedede
}



.deleteGreenRoom, #addgroup {
	height: 20px;
	line-height: 16px;
	position: absolute;
	right: -5px;
	top: 0;
	bottom: 0;
	margin: auto;
}
/*  .glyphicon-plus {
	font-size: 20px;
	line-height: 34px;
	color: #989898;
	padding: 2px 12px;
	margin-top: -6px;
	height: 0px;
	position: relative;
} 
 */
.glyphicon-plus:before {
	content: "\2b";
	margin-right:46px;
}
#popschoolandevent {
	font-family: Open Sans Light;
	font-size: 11pt;
	color: #5d5d5d;
	margin-top: 10px;
	margin-left: -42px;
}

#startt {
	margin-left: 0px;
}

#endtimeimg {
	margin-top: -53px;
	margin-left: 154px;
}
.dropdown-menu {
    display: none;
    top: 353.266px;
    left:543.984px;
    z-index: 1002;
    position: absolute;
}
.add-on {
    position: absolute;
    top: 1px;
    right: 8px;
}
.deleteRow, #addgroup {
    height: 20px;
    line-height: 16px;
    position: absolute;
    right: 0px;
    top: 0;
    bottom: 0;
    margin: auto;
    left:-3px;
}
.text{
    text-align: right;}
</style>
</head>
<body>
	<div class="col-md-10 col-md-offset-2" id="div1">
		<div id="dialog"></div>
		<div class="searchWrap">
			<div id="div2">
				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Volanteer Registration</span>
				</p>
			</div>
		</div>
		<!--------------- Pop up starts --------------->
		<div id="volunteer" style="display: none">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
						</div>
					</div>
				</div>
				<div class="col-md-12" id="popschoolandevent" style="margin-left:-7px;">
					
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: right;">School Name</label>
						<div class="col-xs-7">
							<select name="schoolName" id="schoolName" class="form-control">
								<option value="">--------Select--------</option>
								<logic:present name="locationList">
									<logic:iterate id="name" name="locationList">
										<option
											value='<bean:write name="name" property="locationId"/>'><bean:write
												name="name" property="locationName" /></option>
									</logic:iterate>
								</logic:present>
							</select>
						</div>
					</div>
					<div class="form-group clearfix ">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: right;">Event Name</label>
						<div class="col-xs-7">
							<select id="eventName" name="eventName" class="form-control">
								<option value="">--------Select--------</option>
								<%-- <logic:present name="eventList">
									<logic:iterate id="name" name="eventList">
										<option value='<bean:write name="name" property="eventId"/>'><bean:write name="name" property="eventName"/></option>
									</logic:iterate>
								</logic:present> --%>
							</select>
						</div>
					</div>
                  <div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-4 text"
							style="text-align: right;">Stage Name</label>
						<div class="col-xs-7">
							<select name="stagername" id="stagername" class="form-control">
								<option value="">--------Select--------</option>
							</select>
						</div>
					</div>
					<div class="form-group clearfix ">
						<label for="inputEmail" class="control-label col-xs-4 text"
							id="greenroom">Green Room</label>
						<div class="col-xs-7">
							<select name="greenroomname" id="greenroomname"
								class="form-control">
								<option value="">--------Select--------</option>
								<logic:present name="greennamelist">
									<logic:iterate id="name" name="greennamelist">
										<option
											value='<bean:write name="name" property="greenRoomId"/>'><bean:write
												name="name" property="greenRoomName" /></option>
									</logic:iterate>
								</logic:present>
							</select>
						</div>
					</div>
					<div class="form-group clearfix" style="margin-top:-2px;">
						<label for="inputEmail" class="control-label col-xs-4 text">Admission No</label>
						<div class="col-xs-7">
							<input type="text" name="volanteeradmissionno"
								id="volanteeradmissionno"
								class="form-control ui-autocomplete-input" autocomplete="off"
								value="">
						</div>
					</div>
					 <input type="hidden" id="volunteerIdHidden" value="" />
					
						<div class="input-append clearfix" style="margin-top:-2px;margin-bottom:3px;">
						<label for="inputEmail" class="control-label col-xs-4 text" id="startt">Start
							Time</label>
						<div class="col-xs-7">
							<div id="datetimepicker3" class="input-append">
								<input type="text" name="starttime" id="starttime" size="8"
									class="form-control" data-format="hh:mm:ss"
									onkeypress="HideError()"
									value="<logic:present name="startTime"  scope="request"><bean:write name="startTime" ></bean:write></logic:present>"/><span
									class="add-on"> <img src="./images/time1.jpg" width="25"
									height="25" class="add-on"/>
								</span>
							</div>
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-4 text">End Time</label>
						<div class="col-xs-7">
							<div id="datetimepicker4" class="input-append">
								<input type="text" name="endtime" id="endtime" size="8"
									onkeypress="HideError()" class="form-control"
									data-format="hh:mm:ss"
									value="<logic:present name="endTime"  scope="request"><bean:write name="endTime" ></bean:write></logic:present>" />
								<span class="add-on"> <img src="./images/time1.jpg" 
									width="25" height="25" class="add-on" />
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--Pop Up Ends  -->
		<div class="errormessagediv" style="display: none;" align="center">
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
                 <div id="deleteDialog" style="display: none">
						<p>Are You Sure to Delete?</p>
					</div>
		<div class="panel panel-primary clearfix">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2" href="#"
					style="color: #fff;"><h3 class="panel-title class"
						style="color: #767676;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Volanteer
						Registration
					</h3></a>
			</div>

			<div id="classOne" class="accordion-body collapse in">
				<div class="panel-body">

					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" id="inputnames">School Name</label>
							<div class="col-xs-7">
								<select name="schoolNameList" id="schoolNameList"
									class="form-control">
									<option value="">------SELECT--------</option>
									<logic:present name="locationList">
										<logic:iterate id="name" name="locationList">
											<option
												value='<bean:write name="name" property="locationId"/>'><bean:write
													name="name" property="locationName" /></option>
										</logic:iterate>
									</logic:present>
								</select>
							</div>
						</div>
					
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" id="inputnames">Event Name</label>
							<div class="col-xs-7">
								<select id="eventNameList" name="eventNameList"
									class="form-control">
									<option value="">------SELECT--------</option>
								<logic:present name="eventList">
									<logic:iterate id="name" name="eventList">
										<option value='<bean:write name="name" property="eventId"/>'><bean:write name="name" property="eventName"/></option>
									</logic:iterate>
								</logic:present>
								</select>
							</div>
						</div>

					</div>
					<div class="col-md-6" id="txtstyle">
					<div class="form-group clearfix">
							<div class="col-xs-5">
							<span class="buttons" id="printIdCard">Print Id Card</span>
							</div>
							<div class="col-xs-7">
								
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
							<th>Volunteer Admission No</th>
							<th>GreenRoom Name</th>
							<th>Stage Name</th>
							<th>Event Time</th>
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
						<span class='numberOfItem'></span>
				</div>
				<div class='pagination pagelinks' style="margin-top: 2%;"></div>
			</div>
		</div>
	</div>

</body>
</html>