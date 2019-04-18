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

<title>eCampus Pro</title>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
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
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<script type="text/javascript" src="JS/common.js"></script>
<script src="JS/backOffice/Transport/SettransportDetails.js"></script>
<script>
</script>
</head>
<style>
.glyphicon:hover {
	cursor: pointer;
}

.download:hover {
	cursor: pointer;
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}

.Pending {
	background-color: #FF0000;
	min-width: 80px;
	display: inline-block;
	text-align: center;
	color: #fff;
}

.Completed {
	background-color: rgba(0, 158, 0, 0.66);
	min-width: 80px;
	display: inline-block;
	text-align: center;
	color: #fff;
}
.table {
    width: 80%;
    max-width: 100%;
    margin-bottom: 20px;
    margin: 0 auto;
}
#allstudent th:nth-child(1) {
	text-align: center;
	width:200px;
}
#allstudent th:nth-child(2) {
	width: 250px;
}
#allstudent th:nth-child(3) {
	width: 200px;
}
#allstudent th:nth-child(4) {
	width: 250px;
}
#allstudent td{
	text-align: center;
}
.form-group {
	margin-bottom: 10px;
}
.scrollbar {
	overflow-y: scroll;
	height: 250px;
}
.form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control {
    background-color: white;
    /* opacity: 1; */
}
.month{
width:100%;
}
#routeDetails table tr td{
position:relative;
}
.glyphicon-trash{
line-height: 1;
}
</style>


<body>
	<div class="col-md-10 col-md-offset-2" id="div1">
		<div id="dialog" style="display:none;">
		<input type="checkbox" name="selectBoxD"  class="selectAll" value=""/> <span>Select All</span>
		<table class="table allstudent table-bordered month">
		<thead>
			<tr>
			<th>Select</th>
			<th>Month</th>
			</tr>
		</thead>
		<tbody>
		<logic:present name="monthList">
			<logic:iterate name="monthList" id="monthList">
			<tr>
				<td><input type="checkbox" name="selectList" class="select" id='<bean:write name="monthList" property="monthNo"/>' value='<bean:write name="monthList" property="monthNo"/>'/></td>
				<td class="monthname <bean:write name="monthList" property="months"/>"><bean:write name="monthList" property="months"/></td>
			</tr>
		</logic:iterate>
		</logic:present>
		</tbody>
		</table>
	</div>
	
	
		<p style="margin-top: 5px; margin-bottom: 5px;">
			<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
				id="pageHeading">Transport Request</span>
		</p>

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
			<div class="panel-heading clearfix" role="tab" id="headingOne">
				<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseOne" style="color: #fff;"><h3
							class="panel-title" style="color: #767676;vertical-align: text;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Route 
							Details
						</h3></a>
				<div class="navbar-right">
						<span class="buttons" id="waivedOff" style="display:none;">Waived Off</span> 
						<span class="buttons" id="save">Save</span> 
		 				<span class="buttons" id="back">Back</span>
				</div>
			</div>
			<div id="gradeOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
							<div class='clearfix'>
			<div class="col-md-12" style="margin-top: 10px;">
				<div class="col-md-6" id="txtstyle"
					style="font-size: 11pt; color: #5d5d5d;">
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Academic
							Year</label>
						<div class="col-xs-6">
							<input type="text" readonly="readonly" name="accyear"
								onkeypress="HideError()" id="accyear" class="form-control"
								value='<logic:present name="accyname" scope="request" ><bean:write name="accyname" ></bean:write></logic:present>' />
						</div>
						<input type="hidden" name="hiddenaccyid" id="hiddenaccyid"
							value='<logic:present name="accyearid" scope="request"><bean:write name="accyearid"></bean:write></logic:present>' />
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">School Name</label>
						<div class="col-xs-6">

							<input type="text" readonly="readonly" name="location"
								onkeypress="HideError()" id="location" class="form-control"
								value='<logic:present name="currentlocation" scope="request" ><bean:write name="currentlocation" ></bean:write></logic:present>' />
						</div>
						<input type="hidden" name="hiddenlocid" id="hiddenlocid" 
						value='<logic:present name="locid" scope="request"><bean:write name="locid"></bean:write></logic:present>'/>
					</div>

					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Student Name</label>
						<div class="col-xs-6">
								<input type="text" readonly="readonly" name="studentname"
									onkeypress="HideError()" id="nameofstudent" class="form-control"
									value='<logic:present name="studentDetailsList" scope="request" ><bean:write name="studentDetailsList" property="student_name"/></logic:present>' />
						</div>
						<input type="hidden" name="hiddenstudentid" id="hiddenstudentid"
							value='<logic:present name="hiddenstudentid" scope="request"><bean:write name="hiddenstudentid"></bean:write></logic:present>' />

					</div>
				</div>
			</div>

			<div class="col-md-12">
				
				<div class="col-md-6" id="txtstyle"
					style="font-size: 11pt; color: #5d5d5d;">
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Admission No</label>
						<div class="col-xs-6">

							<input type="text" readonly="readonly" name="admissionno"
								onkeypress="HideError()" id="subcode" class="form-control"
								value='<logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="admisssion_no"></bean:write></logic:present>' />
						</div>
						<input type="hidden" name="hiddenclassid" id="hiddenclassid"
							value='<logic:present name="subid" scope="request"><bean:write name="subid"></bean:write></logic:present>' />
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Class</label>
						<div class="col-xs-6">

							<input type="text" readonly="readonly" name="classid"
								onkeypress="HideError()" id="classid" class="form-control"
								value='<logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="classname"></bean:write></logic:present>'  />
						</div>
						<input type="hidden" name="classidhidden" id="classidhidden"
							value='<logic:present name="classId" scope="request"><bean:write name="classId"></bean:write></logic:present>' />
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Address</label>
						<div class="col-xs-6">
							<textarea name="identificationMarks" rows="3" id="identificationMarksId" readonly="true" class="form-control"><logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="address"></bean:write></logic:present></textarea>
						</div>
						</div>
					</div>

				<div class="col-md-6" id="txtstyle" style="font-size: 11pt; color: #5d5d5d;">

					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Student
							Status</label>
						<div class="col-xs-6">
							<input type="text" readonly="readonly" name="studentname" onkeypress="HideError()" id="studentname" class="form-control"
								value='<logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="student_status"></bean:write></logic:present>' />
						</div>
						<input type="hidden" name="hiddenstudentid" id="hiddenstudentid" value='<logic:present name="hiddenstudentid" scope="request"><bean:write name="hiddenstudentid"></bean:write></logic:present>' />
					</div>

					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Division</label>
						<div class="col-xs-6">
							<input type="text" readonly="readonly" name="sectionid"
								onkeypress="HideError()" id="sectionid" class="form-control"
								value='<logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="section_name"></bean:write></logic:present>'  />
						</div>
						<input type="hidden" name="hiddensectionid" id="hiddensectionid"
							value='<logic:present name="sectionId" scope="request"><bean:write name="sectionId"></bean:write></logic:present>' />
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Transport
							Status</label>
						<div class="col-xs-6">
							<input type="text" readonly="readonly" name="examName"
								onkeypress="HideError()" id="examName" class="form-control"
								value='<logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="transport_status"></bean:write></logic:present>'  />
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id = "hiddenstuid" value='<logic:present name="stuid"><bean:write name="stuid"/></logic:present>'/>
		<input type="hidden" id="istransport" value='<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="status"/></logic:present>'>
		<input type="hidden" id="stageid" value='<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="stage_id"/></logic:present>'>
		<input type="hidden" id="routeid" value='<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="routeCode"/></logic:present>'>
		<input type="hidden" id="hiddenmonth" value="<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="noofmonths"/></logic:present>"/>
 		<input type="hidden" id="startmonth" value="<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="stmonth"/></logic:present>"/>
		<input type="hidden" id="endmonth" value='<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="endmonth"/></logic:present>'/>
		<hr style="height:1px;border:none;color:#333;background-color:#ddd;"/>
			<div>
		<div class="slideTab clearfix">
						
						<ul class="nav nav-tabs transport-request">
						<li class="active"><a data-toggle="tab" href="#routeDetails"  style="font-weight: 700;background-color:#07aab9;color:#fff">Route Details</a></li>
								<li class=""><a data-toggle="tab" href="#contacts"  style="font-weight: 700;background-color:#07aab9;color:#fff">Request</a></li>
								</ul>
								
								<div class="tab">
								<div id="contacts" class="tab-pane fade">
									<div class="row">
										<div class="col-md-12">
											<div class="searchWrap">
												<div class="col-md-8" id=div2></div>
													<div id="studenttable">
														<table style="background: #fff;" class="table" id="allstudent">
															<tr>
																<th style="font-size: 15px; text-align: center;">Route</th>
																<th style="font-size: 15px; text-align: center;">Stop</th>
																<th style="font-size: 15px; text-align: center;">Drop Point</th>
																<th style="font-size: 15px; text-align: center;">Amount</th>
																<th style="font-size: 15px; text-align: center;">Month</th>
															</tr>
															<tr id='rowId'>
																<td><select class="routename"  id ="routename" name="route" style="width:100%;">
																	<option value="">---Select---</option>
							    									</select>
							    								</td>
																<td><select class="stopname" id="stopname" name="stop" style="width:100%;">
																	<option value="">----------Select----------</option>
							    									</select>
							    								</td>
							    								<td><select class="drop-point" id="droppoint" name="droppoint" style="width:100%;">
																	<option value="">----------Select----------</option>
							    									</select>
							    								</td>
																<td><input type="text" class="amount"  style="width:100%;" name="amount" readonly="readonly"></td>
																<td><input type="text" class="month"  style="width:100%;" id="month" readonly="readonly"></td>
															</tr>
														</table> 
													</div>
												</div>
											</div>
										</div>	
											
										</div>
								<div id="routeDetails" class="tab-pane fade active in">
									<table class="table" style="margin-bottom: 20px;">
										<thead>
											<tr>
												<th>Route</th>
												<th>Stage</th>
												<th>Drop Point</th>
												<th>Month</th>
												<th>Delete</th>
											</tr>
										
										</thead>
										<tbody>
											<logic:present name="mappingList" scope="request">
												<logic:iterate id="mappingList" name="mappingList">
													<tr id='<bean:write name="mappingList" property="sno"/>'>
														<td><bean:write name="mappingList" property="routeName"/></td>
														<td><bean:write name="mappingList" property="stageName"/></td>
														<td><bean:write name="mappingList" property="droppoint"/></td>
														<td><bean:write name="mappingList" property="months"/></td>
														<td><span class="glyphicon glyphicon-trash delete"  style="position: absolute;top:0;bottom:0;margin:auto;"></span></td>
													</tr>									
												</logic:iterate>
											</logic:present>
										</tbody>
									</table>	
								</div>
								
								
									
										
										
										
						</div>
						</div>
						</div>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>
