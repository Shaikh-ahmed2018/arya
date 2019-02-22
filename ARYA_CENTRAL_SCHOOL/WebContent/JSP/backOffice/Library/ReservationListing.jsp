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

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- <script type="text/javascript" src="JS/backOffice/Election/NominationApproval.js"></script> -->
<script type="text/javascript" src="JS/backOffice/Library/BookReservationlist.js"></script>
<script type="text/javascript" src="JS/common.js"></script>

<style>
#editStudent:hover {
	cursor: pointer;
}

#trash:hover {
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

.class1 {
	width: 41.66666667%;
	margin-left: -65px;
	margin-top: 12px;
}

.fsetRight {
	width: 610px;
	margin-bottom: 15px;
	border: 0.5px solid #ccc;
	padding: 5px;
}

.fieldset {
	display: block;
	padding-bottom: 0.625em;
	padding-left: 55px;;
	padding-right: 0px;
	border: 0.5px solid #ccc;
	min-height: 99px;
}

.fieldset1 {
	display: block;
	padding-bottom: 0.625em;
	padding-left: 55px;;
	padding-right: 0px;
	border: 0.5px solid #ccc;
	min-height: 98px;
}

.fieldset2 {
	display: block;
	padding-bottom: 0.625em;
	padding-left: 55px;;
	padding-right: 0px;
	border: 0.5px solid #ccc;
	min-height: 97px;
}

legend {
	display: inline-block;
	width: auto;
	padding: 0;
	margin-bottom: 0px;
	font-size: 16px;
	line-height: inherit;
	color: #333;
	border: 0;
}

.stand {
	margin-left: -16px;
}

.with {
	margin-left: 60px;
}

.page {
	margin-top: 25px;
}

.search1 {
	width: 186px;
	left: -15px;
	color: #6f6f6f;
	font-size: 14px;
	font-family: Open Sans Light;
}

.navbar-right {
	right: 7px;
	top: 0;
	float: none !important;
	display: block;
	bottom: 0;
	vertical-align: middle;
}

.staffstand {
	display: none;
}

.stafftable {
	display: none;
}

.otherstable {
	display: none;
}

.setsearch {
	padding-left: 45px;
}

#others th:nth-child(6) {
	text-align: left;
	width: 28%;
}

#others th:nth-child(5) {
	text-align: left;
	width: 20%;
}

#others th:nth-child(2) {
	text-align: left;
	width: 10%;
}

#others td:nth-child(2) {
	text-align: center;
}

#others td:nth-child (6{
	text-align: left;
}

#others td:nth-child(7), th:nth-child(7) {
	text-align: center;
}

#allstudent  th:nth-child(2), th:nth-child(4), th:nth-child(5), th:nth-child(6),
	th:nth-child(7), th:nth-child(8) {
	text-align: center;
}

#allstudent  td:nth-child(2), td:nth-child(4), td:nth-child(5), td:nth-child(6),
	td:nth-child(7), td:nth-child(8) {
	text-align: center;
}

#allstaff td:nth-child(2), th:nth-child(2) {
	text-align: center;
}
</style>

</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="searchWrap">
			<div id="div2">

				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Book Reservation </span>
				</p>
			</div>

		</div>

		<input type="hidden" id="succmsg"
			value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />

		<div id="successmessages" class="successmessagediv"
			style="display: none;">
			<div class="message-item">
				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessage"></span></a>
			</div>
		</div>

		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne">
					<h3 class="panel-title"
						style="color: #767676; vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>
						&nbsp;&nbsp;Book Reservation Details
					</h3>
				</a>

				<div class="navbar-right go">

					<div>
						<div class="form-group clearfix" style="padding-top: 3px;">

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<!-- <button type="button" class="btn btn-info"
												style="text-align: left; margin-left: -13px; margin-top: -26px;"
												id="gobutton">Go</button> -->
												
												<a href="LibraryMenu.html?method=reservations"> <span
							class="buttons" id="add" style="text-align: left; margin-left: -13px; margin-top: -26px;">Add</span></a> 

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<%-- <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<select class="col-xs-6 search1" id="goto">
												<option>----GoTo----</option>
												<option value="subdetail">Subscriber Details</option>
												<option value="issuestate">Issue Statement</option>
												<option value="issuereturn">Issue Return</option>
											</select>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
						</div>
					</div>
				</div>
			</div>

			<!-- pop up -->

			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt" style="padding: 15px;">

					<div class="row">
					
							<fieldset class="fieldset" style="margin-right: 29px;margin-left: 29px;">
								<legend
									style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 16px; color: #767676; margin-left: 56px;">
									Subscriber Type:</legend>
								<div class="form-group clearfix"
									style="padding-top: 17px; padding-left: 53px;">

									<label style="width: 100px; margin-left: 2px;"> <input
										type="radio" style="top: -2px;" class="radio-inline"
										name="requested_by" id="student" class="cencession"
										value="Student" checked="checked" />&nbsp;Student
									</label> <label style="width: 81px;"><input type="radio"
										style="top: -2px;" class="radio-inline" name="requested_by"
										class="cencession" id="staff" value="Teacher" />&nbsp;staff&nbsp;&nbsp;&nbsp;
									</label> <label style="width: 86px; margin-left: 8px;"> <input
										type="radio" style="top: -2px;" class="radio-inline"
										name="requested_by" id="others" class="cencession"
										value="other" />&nbsp;Others
									</label>

								</div>
								<div class="col-md-12">
								<div class="col-md-6">
								<div class="form-group clearfix with stusubno"
											style="margin-left: 53px;">
										<label for="inputPassword"
												class="control-label col-xs-4 standard"
												style="text-align: right; line-height: 35px;">Subscriber No</label>
											<div class="col-xs-8">
												<select id="studsubName" name="" style="margin-left: -18px;width:232px;" >
												<option>All</option>
												<%-- <logic:present name="eventList">
													<logic:iterate id="name" name="eventList">
														<option value='<bean:write name="name" property="eventId"/>'><bean:write name="name" property="eventName"/></option>
													</logic:iterate>
												</logic:present> --%>
												</select>
											</div>
										</div>
										
										<div class="form-group clearfix with teachsubno"
											style="margin-left: 53px;display: none;">
										<label for="inputPassword"
												class="control-label col-xs-4 standard"
												style="text-align: right; line-height: 35px;">Subscriber No</label>
											<div class="col-xs-8">
												<select id="teachsubName" name="" style="margin-left: -18px;width:232px;" >
												<option>All</option>
												<%-- <logic:present name="eventList">
													<logic:iterate id="name" name="eventList">
														<option value='<bean:write name="name" property="eventId"/>'><bean:write name="name" property="eventName"/></option>
													</logic:iterate>
												</logic:present> --%>
												</select>
											</div>
										</div>
										
										<div class="form-group clearfix with othersubno"
											style="margin-left: 53px;display: none;">
										<label for="inputPassword"
												class="control-label col-xs-4 standard"
												style="text-align: right; line-height: 35px;">Subscriber No</label>
											<div class="col-xs-8">
												<select id="othersubName" name="" style="margin-left: -18px;width:232px;" >
												<option>All</option>
												<%-- <logic:present name="eventList">
													<logic:iterate id="name" name="eventList">
														<option value='<bean:write name="name" property="eventId"/>'><bean:write name="name" property="eventName"/></option>
													</logic:iterate>
												</logic:present> --%>
												</select>
											</div>
										</div>
										
										</div>
										<div class="col-md-6">
										<div class="form-group clearfix with stusubname"
											style="margin-left: 53px;">
										<label for="inputPassword"
												class="control-label col-xs-4 standard "
												style="text-align: right; line-height: 35px;">Subscriber Name</label>
											<div class="col-xs-8">
												<select style="margin-left: -18px;width:232px;" id="teaSublist" >
												<option>All</option>
												<%-- <logic:present name="subscriberNameList">
													<logic:iterate id="name" name="subscriberNameList">
														<option value='<bean:write name="name" property="subscriberId"/>'><bean:write name="name" property="subscriberName"/></option>
													</logic:iterate>
												</logic:present> --%>
												</select>
											</div>
										</div>
										
										<div class="form-group clearfix with teachsubname"
											style="margin-left: 53px;display: none;">
										<label for="inputPassword"
												class="control-label col-xs-4 standard"
												style="text-align: right; line-height: 35px;">Subscriber Name</label>
											<div class="col-xs-8">
												<select style="margin-left: -18px;width:232px;" id="stuSublist" >
												<option>All</option>
												<%-- <logic:present name="teachsubscriberNameList">
													<logic:iterate id="name" name="teachsubscriberNameList">
														<option value='<bean:write name="name" property="subscriberId"/>'><bean:write name="name" property="subscriberName"/></option>
													</logic:iterate>
												</logic:present> --%>
												</select>
											</div>
										</div>
										
										<div class="form-group clearfix with othersubname"
											style="margin-left: 53px;display: none;">
										<label for="inputPassword"
												class="control-label col-xs-4 standard"
												style="text-align: right; line-height: 35px;">Subscriber Name</label>
											<div class="col-xs-8">
												<select style="margin-left: -18px;width:232px;" id="otherSublist" >
												<option>All</option>
												<%-- <logic:present name="teachsubscriberNameList">
													<logic:iterate id="name" name="teachsubscriberNameList">
														<option value='<bean:write name="name" property="subscriberId"/>'><bean:write name="name" property="subscriberName"/></option>
													</logic:iterate>
												</logic:present> --%>
												</select>
											</div>
										</div>
										
										
										
										</div>
								</div>
							</fieldset>
						</div>



				
						<div class="col-md-12"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; padding-top: 6px;">
							<fieldset class="fieldset2" style="margin-bottom: -20px;">
								<div class="row" style="margin-top: 10px;">
									<div class="col-md-6 clearfix ">
										<div class="form-group clearfix with studAcc"
											style="margin-left: 53px;">
										<label for="inputPassword"
												class="control-label col-xs-4 standard"
												style="text-align: right; line-height: 35px;">Accession No</label>
											<div class="col-xs-8" >
												
												<select style="width:232px" id="studAccNo">
												<option value="">All</option>
<%-- 												<logic:present name="acessionList">
													<logic:iterate id="name" name="acessionList">
														<option value='<bean:write name="name" property="accessionNoId"/>'><bean:write name="name" property="accessionNo"/></option>
													</logic:iterate>
												</logic:present>
 --%>												</select>
											</div>
										</div>
										
										<div class="form-group clearfix with teachAcc"
											style="margin-left: 53px;display: none;">
										<label for="inputPassword"
												class="control-label col-xs-4 standard"
												style="text-align: right; line-height: 35px;">Accession No</label>
											<div class="col-xs-8" >
												
												<select style="width:232px" id="teachAccNo">
												<option value="">All</option>
<%-- 												<logic:present name="acessionList">
													<logic:iterate id="name" name="acessionList">
														<option value='<bean:write name="name" property="accessionNoId"/>'><bean:write name="name" property="accessionNo"/></option>
													</logic:iterate>
												</logic:present>
 --%>												</select>
											</div>
										</div>
										
										
										<div class="form-group clearfix with otherAcc"
											style="margin-left: 53px;display: none;">
										<label for="inputPassword"
												class="control-label col-xs-4 standard"
												style="text-align: right; line-height: 35px;">Accession No</label>
											<div class="col-xs-8" >
												
												<select style="width:232px" id="otherAccNo">
												<option value="">All</option>
<%-- 												<logic:present name="acessionList">
													<logic:iterate id="name" name="acessionList">
														<option value='<bean:write name="name" property="accessionNoId"/>'><bean:write name="name" property="accessionNo"/></option>
													</logic:iterate>
												</logic:present>
 --%>												</select>
											</div>
										</div>
										
										
										<div class="form-group clearfix with"
											style="margin-left: 53px;">
										<label for="inputPassword"
												class="control-label col-xs-4 standard"
												style="text-align: right; line-height: 35px;">Book Title</label>
											<div class="col-xs-8">
												<input type="text" class="form-control" style="width:232px">
											</div>
										</div>
										
										</div>
										
										<div class="col-md-6 clearfix">
									<div class="form-group clearfix with"
											style="margin-left: 53px;">
										<label for="inputPassword"
												class="control-label col-xs-4 standard"
												style="margin-left:-16px;text-align: right; line-height: 35px;"> Author</label>
											<div class="col-xs-8">
												<input type="text" class="form-control" style="width: 235px; margin-left: -10px;">
											</div>
										</div>
								</div>
									
									
</div>
									

									
								</div>
								
								
									<div class="col-md-12"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; padding-top: 6px;">
							<fieldset class="fieldset2" style=" margin-bottom: -15px;margin-top: 13px;">
								<div class="row" style="margin-top: 37px;margin-left: -59px;">
									<div class="col-md-4 clearfix ">
										<div class="form-group clearfix "
											style="">
										<label for="inputPassword"
												class=" col-xs-4 "
												style="">From Date</label>
											<div class="col-xs-8" >
												
												<select style="width:180px" id="fromdate">
												<option value="">All</option>
												</select>
											</div>
										</div>
										
									
										
										</div>
										
										<div class="col-md-4 clearfix">
									<div class="form-group clearfix with"
											style="margin-left: 53px;">
										<label for="inputPassword"
												class="control-label col-xs-4 standard"
												style="margin-left:-16px;text-align: left; line-height: 35px;">To Date</label>
											<div class="col-xs-8">
												<select style="width: 180px; margin-left: -10px;" id="todate">
												<option value="">All</option>
												</select>
											</div>
										</div>
								</div>
									
										<div class="col-md-4 clearfix">
									<div class="form-group clearfix with"
											style="margin-left: 53px;">
										<label for="inputPassword"
												class="control-label col-xs-4 standard"
												style="margin-left:-16px;text-align: left; line-height: 35px;">Priority</label>
											<div class="col-xs-8">
												<input type="text" class="form-control" style="width: 180px; margin-left: -10px;">
											</div>
										</div>
								</div>
									
									
</div>
</fieldset>
									

									
								</div>
								
								
								
								
								
								
								
						
						</div>
					</div>
					<input type="hidden" id="hidenaccyear"
						value="<logic:present name='accademic_year' scope='request'><bean:write name='accademic_year'/></logic:present>" />
					<!-------------- Student table -------------->
					<div class="allstudenttable" style="margin-top: 6px;">
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<table class='table allstudent' id='allstudent'>
								<thead>
									<tr>
										<th><input type="checkbox" class="selectedall" id="radio" /></th>
										<th>Book Title</th>
										<th>Location</th>
										<th>Status</th>
										<th>Issued To</th>
										<th>From Date </th>
										<th>To Date</th>
									<!-- 	<th>Status</th> -->
									</tr>
								</thead>

								<tbody>

								</tbody>

							</table>


						</div>
					</div>

					<!------------- Staff table ----------->
					<div class="stafftable" style="margin-top: 6px;">
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<table class='table allstudent' id="allstaff">

								<thead>
									<tr>
										<th><input type="checkbox" class="selectedall" id="radio" /></th>
										<th>Book Title</th>
										<th>Location</th>
										<th>Status</th>
										<th>Issued To</th>
										<th>From Date </th>
										<th>To Date</th>
									</tr>
								</thead>
								<tbody>

								</tbody>

							</table>
						</div>
					</div>

					<!---------------------------- others table------------------------------ -->
					<div class="otherstable" style="margin-top: 6px;">
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<table class='table allstudent' id="others">

								<thead>
									<tr>
										<th><input type="checkbox" class="selectedall" id="radio" /></th>
										<th>Book Title</th>
										<th>Location</th>
										<th>Status</th>
										<th>Issued To</th>
										<th>From Date </th>
										<th>To Date</th>
									</tr>
								</thead>
								<tbody>

								</tbody>

							</table>
						</div>
					</div>
					<!-- <div class='pagebanner page'> -->
					<div class='pagebanner' style="margin-top: 2px; margin-left: 15px;">
						<select id='show_per_page'>
							<option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option>
						</select>
					</div>
					<div class='pagination pagelinks' style="font-size: 10px;"></div>
					<div
						style="padding-top: 8px; margin-left: 55px; ">
						<span class='numberOfItem' style="font-size: 13px;"></span>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>