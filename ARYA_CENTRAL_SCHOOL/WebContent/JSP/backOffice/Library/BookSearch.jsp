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
	
 <script type="text/javascript"  src="JS/backOffice/Library/BookSearch.js"></script>
 
 <script type="text/javascript" src="JS/common.js"></script>

<style>
 #individualstudenttable th:nth-child(1),th:nth-child(2),th:nth-child(3),th:nth-child(4),th:nth-child(5),th:nth-child(6){
  text-align: center;
  }
  
 #individualstudenttable td:nth-child(1),td:nth-child(2),td:nth-child(3),td:nth-child(4),td:nth-child(5),td:nth-child(6){
  text-align: center;
  }
  
#allstudent tbody tr{
  position: relative;
}

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
	min-height:46px;
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
	min-height: 55px;
	margin-bottom:7px;
	margin-top: 18px;
    margin-left: 0px;
    width: 100%;
}
.fieldset3 {
	display: block;
	padding-bottom: 0.625em;
	padding-left: 55px;;
	padding-right: 0px;
	border: 0.5px solid #ccc;
	min-height:60px;
	margin-top: 10px;
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

</style>

</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="searchWrap">
			<div id="div2">

				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Book Search</span>
				</p>
			</div>

		</div>

		<input type="hidden" id="succmsg"
			value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage' /></logic:present>" />

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
						&nbsp;&nbsp;Book Search
					</h3>
				</a>

				<div class="navbar-right go">

						<div class="form-group clearfix" style="padding-top: 3px;">

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<button type="button" class="btn btn-info"
												style="text-align: left; margin-left: -13px; margin-top: -26px;"
												id="gobutton">Go</button>
												
										<!-- 		<span class="buttons" id="iconsimg" data-toggle="modal"
											data-target="#myModal" data-toggle="tooltip"
											data-placement="bottom" title="Download" style="top: 4px;">Download </span>
												&nbsp;
											<span class="buttons" id="print" style="top: 4px;">Print </span> -->

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
			<!-- 				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
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
				</div> -->

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<select class="col-xs-6 search1" id="goto">
												<option>----GoTo----</option>
												<option value="stockdetail">Stock Details</option>
												<option value="issuestate">Issued Statement</option>
												<option value="issuereturn">Issue Return</option>
											</select>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
						</div>
				</div>
			</div>

		

			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt" style="padding: 15px;">

					<div class="row">

						<div class="col-md-6"
							style="font-family: Open Sans Light;font-size:11pt;color:#5d5d5d;width:100%;">
							<fieldset class="fieldset">
							 <!-- <legend style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 16px; color: #767676; margin-left: 46px;">Start With</legend> -->
								<div class="form-group clearfix"
									style="padding-top:13px;padding-left:26%;">

									<label style="width:8%; margin-left:-261px;"> <input
										type="radio" style="top: -2px;" class="radio-inline"
										name="requested_by" id="ALL" class="cencession"
										value="all" checked="checked" />&nbsp;ALL
									</label> 
									<label style="margin-left: 70px;width: 81px;"><input type="radio"
										style="top: -2px;" class="radio-inline" name="requested_by"
										class="cencession" id="issued" value="issued" />&nbsp;Issued&nbsp;&nbsp;&nbsp;
									</label>
									 <label style="width: 80px;margin-left: 53px;"> <input
										type="radio" style="top: -2px;" class="radio-inline"
										name="requested_by" id="available" name="available" class="cencession"
										value="available" />&nbsp;Available
									</label>
									<label style="margin-left: 38px;"> <input
										type="radio" style="top: -2px;" class="radio-inline"
										name="requested_by" id="notavailable" name="notavailable" class="cencession"
										value="notavailable" />&nbsp;NotAvailable
									</label>

								</div>
							</fieldset>
						</div>
						
						<!-- conditions starts -->
						
						<div class="col-md-12"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
							<fieldset class="fieldset3">
							<legend style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 16px; color: #767676; margin-left: 46px;">Order By</legend>
								<div class="row" style="margin-top: 10px;">
									<div class="col-md-12 clearfix">
									
										<div class="form-group clearfix with">

											<label style="margin-left: 4px;"> <input type="radio"
												style="top: -3px;" class="radio-inline" name="order_by" checked="checked"
												id="accessionNo" class="cencession" value="accessionNo" />&nbsp;AccessionNo
											</label> 
											<label style="margin-left: 31px;"><input type="radio"
												style="top: -3px;" class="radio-inline" name="order_by"
												class="cencession" id="title" value="title" />&nbsp;Title&nbsp;&nbsp;&nbsp; 
											</label>
											<label style="margin-left: 78px;">
												<input type="radio" class="radio-inline" name="order_by"
												style="top: -3px;" id="author" class="cencession"
												value="author" />&nbsp;Author
											</label>
                                            <label style="margin-left: 57px;">
												<input type="radio" class="radio-inline" name="order_by"
												style="top: -3px;" id="publisher" class="cencession"
												value="publisher" />&nbsp;Publisher
											</label>
											<label style="margin-left: 58px;">
												<input type="radio" class="radio-inline" name="order_by"
												style="top: -3px;" id="supplier" class="cencession"
												value="supplier" />&nbsp;Supplier
											</label>
											<label style="margin-left: 46px;">
												<input type="radio" class="radio-inline" name="order_by"
												style="top: -3px;" id="itemtype" class="cencession"
												value="itemtype" />&nbsp;ItemType
											</label>
											
											<label style="margin-left: 45px;">
												<input type="radio" class="radio-inline" name="order_by"
												style="top: -3px;" id="ddc" class="cencession"
												value="ddc" />&nbsp;DDC
											</label>
											<label style="margin-left: 45px;">
												<input type="radio" class="radio-inline" name="order_by"
												style="top: -3px;" id="content" class="cencession"
												value="content"  />&nbsp;Content
											</label>
											<label style="margin-left:5px;">
												<input type="radio" class="radio-inline" name="order_by"
												style="top: -3px;" id="location" class="cencession"
												value="location" />&nbsp;Location
											</label>
											<label style="margin-left: 58px;">
												<input type="radio" class="radio-inline" name="order_by"
												style="top: -3px;" id="category" class="cencession"
												value="category"/>&nbsp;Category
											</label>
											<label style="margin-left: 58px;">
												<input type="radio" class="radio-inline" name="order_by"
												style="top: -3px;" id="language" class="cencession"
												value="language" />&nbsp;Language
											</label>
										 </div>
									    </div>
								      </div>
									</div>
								
						
						<!-- conditions ends -->
						
						<div class="col-md-12"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top:-8px;">
							<fieldset class="fieldset2">
								<legend style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 16px; color: #767676; margin-left: 46px;">Search</legend>
								<div class="row" style="margin-top: 10px;">
									<div class="col-md-6 clearfix ">
										<div class="form-group clearfix with"
											style="margin-left: 52px;">

											<label style="margin-left: -49px;width: 100px;"> <input type="radio"
												style="top: -2px;" class="radio-inline" name="started_by"
												id="student" class="cencession" value="startwith" />&nbsp;Start
												with
											</label> <label style="margin-left: 31px;width: 96px;"><input type="radio"
												style="top: -2px;" class="radio-inline" name="started_by"
												class="cencession" id="staff" value="endswith" />&nbsp;Ends
												with&nbsp;&nbsp;&nbsp; </label><label style="margin-left: 44px;width: 89px;">
												<input type="radio" class="radio-inline" name="started_by"
												style="top: -2px;" id="student" class="cencession"
												value="anywhere" checked="checked" />&nbsp;Any where
											</label>

										</div>
									</div>

									<div class="col-md-6 stand">
										<div class="form-group clearfix">
											<label for="inputPassword" class="control-label col-xs-5"
												style="text-align: left; line-height: 35px; width: 67px;">Search</label>
											<div class="col-md-7">
												<input type="text" class="form-control" id="searchValue"
													Placeholder="Search......"
													value="<logic:present name='searchnamelistValue' scope='request'><bean:write name='searchnamelistValue'/></logic:present>">
											</div>
										</div>

									</div>
								</div>
							</fieldset>
							
						</div>
						</div>
							</fieldset>
					
					<input type="hidden" id="hidenaccyear"
						value="<logic:present name='accademic_year' scope='request'><bean:write name='accademic_year'/></logic:present>" />

		<div id='individualstudenttable'></div>
			<div class='pagebanner'>
				<select id='show_per_page' style="margin-top:-3px;">
					<option value='50'>50</option>
					<option value='100'>100</option>
					<option value='200'>200</option>
					<option value='300'>300</option>
					<option value='400'>400</option>
					<option value='500'>500</option>
				</select>
	         </div>
				<span class='numberOfItem'style="margin-left: 5%;font-size:13px;color: #040404;;"></span>
				<div class='pagination pagelinks' style="margin-top:-3px;"></div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>