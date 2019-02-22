<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript"
	src="JS/backOffice/Library/ReservationList.js"></script>
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

#others

 

td


:nth-child

 

(6{
text-align


:

 

left


;
}
#others td:nth-child(7),th:nth-child(7) {
	text-align: center;
}

#allstudent  th:nth-child(2),th:nth-child(4),th:nth-child(5),th:nth-child(6),th:nth-child(7),th:nth-child(8)
	{
	text-align: center;
}

#allstudent  td:nth-child(2),td:nth-child(4),td:nth-child(5),td:nth-child(6),td:nth-child(7),td:nth-child(8)
	{
	text-align: center;
}

#allstaff td:nth-child(2),th:nth-child(2) {
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
						id="pageHeading"> Reservation List</span>
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
						&nbsp;&nbsp; Reservation List
					</h3>
				</a>

				<div class="navbar-right go">


			
					

					

							
						<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRPDWD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<span class="buttons" id="iconsimg" data-toggle="modal"
												data-target="#myModal" data-toggle="tooltip"
												data-placement="bottom" title="Download">Download </span>&nbsp;&nbsp;
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

								<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<button type="button" class="btn btn-info"
												style="text-align: left; margin-left: -13px; margin-top: -19px;"
												id="print">Print</button>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
				

				</div>
			</div>

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


			<!-- pop up -->

			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt" style="padding: 15px;">

					<div class="row">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">School
									Name</label>
								<div class="col-xs-7">
									<select id="locationname" name="locationnid"
										class="form-control" required>
										<option value="">-----------Select-----------</option>
										<logic:present name="locationList">
											<logic:iterate id="Location" name="locationList">
												<option
													value="<bean:write name="Location" property="locationId"/>">
													<bean:write name="Location" property="locationName" />
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>

						</div>

						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Academic
									Year</label>
								<div class="col-xs-7">
									<select id="Acyearid" name="accyear" class="form-control"
										required>
										<!-- <option value="all"></option> -->
										<logic:present name="AccYearList">
											<logic:iterate id="AccYear" name="AccYearList">
												<option
													value="<bean:write name="AccYear" property="accyearId"/>">
													<bean:write name="AccYear" property="accyearname" />
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>

						</div>

						<div class="col-md-12"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
							<fieldset class="fieldset">
								<legend
									style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 16px; color: #767676; margin-left: 260px;">
									Subscriber Type:</legend>
								<div class="col-md-6 stand">
									<div class="form-group clearfix" id="Subscriber"
										style="padding-top: 17px;  padding-left: 53px;">

										<label style="width: 100px; margin-left: 2px;"> <input
											type="radio" style="top: -2px;" class="radio-inline"
											name="requested_by" id="All" class="cencession" value="ALL"
											checked="checked" />&nbsp;ALL
										</label> <label style="width: 100px; margin-left: 2px;"> <input
											type="radio" style="top: -2px;" class="radio-inline"
											name="requested_by" id="student" class="cencession"
											value="Student" />&nbsp;Student
										</label> <label style="width: 81px;"><input type="radio"
											style="top: -2px; margin-left: 16px;" class="radio-inline"
											name="requested_by" class="cencession" id="staff"
											value="Teacher" />&nbsp;Staff &nbsp;&nbsp;&nbsp; </label> <label
											style="width: 86px; margin-left: 2px;"> <input
											type="radio" style="top: -2px; margin-left: 20px;"
											class="radio-inline" name="requested_by" id="others"
											class="cencession" value="others" />&nbsp;Others
										</label>

									</div>
								</div>

								<div class="col-md-6 stand">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Accession
											No</label>
										<div class="col-xs-7">
											<select name="accessionNo" id="AccNo"
												style="width: 282px; font-size: 14px;">
												<option value="ALL">ALL</option>
												
											</select>
										</div>

									</div>
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Book
											Title</label>
										<div class="col-xs-7">
											<select name="bookTitle" id="booktitle" style="width: 282px; font-size: 14px;">
												<option value="ALL">ALL</option>
											</select>
										</div>

									</div>

								</div>




							</fieldset>
						</div>


						<div class="col-md-12"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; padding-top: 6px;">
							<fieldset class="fieldset2">
								<div class="row" style="margin-top: 10px;">
									<div class="col-md-6 clearfix ">
										<div class="form-group clearfix with"
											style="margin-left: 53px;">

											<label style="width: 100px;"> <input type="radio"
												style="top: -2px;" class="radio-inline" name="started_by"
												id="allfordate" class="cencession" value="allfordate" checked="checked" />&nbsp;ALL
											</label> <label style="width: 163px;"><input type="radio"
												style="top: -2px;" class="radio-inline" name="started_by"
												class="cencession" id="Selecteddate" value="all" />&nbsp;Selected
												Date with&nbsp;&nbsp;&nbsp; </label>

										</div>



									</div>

									<div class="col-md-6 dateshow">
										<div class="form-group clearfix dateshow" Style="display:none;">
											<label for="inputPassword" class="control-label col-xs-4"
												style="text-align: right; line-height: 35px;">From
												Date</label>
											<div class="col-xs-8">
												<input type="text" name=" " class="form-control date"
													readonly="readonly" id="Fromdate"></input>
											</div>

										</div>
										<div class="form-group clearfix dateshow " Style="display:none;">
											<label for="inputPassword" class="control-label col-xs-4"
												style="text-align: right; line-height: 35px; ">To
												Date</label>
											<div class="col-xs-8">
												<input type="text" name=" " class="form-control date"
													readonly="readonly" id="Todate"></input>
											</div>

										</div>

									</div>


								</div>
							</fieldset>
						</div>
					</div>
					<input type="hidden" id="hidenaccyear"
						value="<logic:present name='accademic_year' scope='request'><bean:write name='accademic_year'/></logic:present>" />
					<!-------------- Student table -------------->
                        	<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="" style="padding: 15px;">
				
					<div class="row">
			<div id="collapseOne" class="panel-collapse collapse in ">
					<div class="panel-body own-panel">
						<div class="row"></div>
                        
						<table class='table' id='allstudent' style="width:100%;display:none">
							<thead>
								<tr>
									<th>Sl.No</th>
									<th>Accession No</th>
									<th>Book Title</th>
									<th>Author</th>
									<th>Library location</th>
								    <th>Subscriber Name</th>
									<th>From date</th>
									<th>To date</th>
									<th>User Type</th>
									
								</tr>
							</thead>
							<tbody>

							</tbody>

						</table>
			     </div>
     	 </div>
					<div class='pagebanner' class="panel panel-primary clearfix" style="display:none">
				
					<select id='show_per_page' >
					<option value='50'>50</option>
					<option value='100'>100</option>
					<option value='200'>200</option>
					<option value='300'>300</option>
					<option value='400'>400</option>
					<option value='500'>500</option></select>
						<span  class='numberOfItem' style="display:none"></span>	
				</div>
				<div class='pagination pagelinks' style="display:none"></div>
			</div>
		</div>
	</div>
                        
                        
                        
				</div>
			</div>
		</div>
	</div>

</body>
</html>