<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<html>
<head>
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
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Reports/ITFeeCollection.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="add" class="tab-pane">
		<div class="col-md-10 col-md-offset-2" id="div-main"
			style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
			<div id="dialog3"></div>
			<p>
				<img src="images/addstu.png" />&nbsp;<span id="pageHeading">
					IT Fee Collection</span>
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

			


				<input type="hidden" id="updatelocationname"
					value='<logic:present name="edit_issuedlist"><bean:write name="edit_issuedlist"/></logic:present>' />
				<input type="hidden" id="updateenquiryid"
					value='<logic:present name="edit_issuedlist"><bean:write name="edit_issuedlist" 
property="enquiryid" />
		</logic:present>'></input>

				<div class="panel-group" id="accordion" role="tablist"
					aria-multiselectable="true">
					<div class="panel panel-primary">
						<div class="panel-heading clearfix" role="tab" id="headingOne">
							
								<a data-toggle="collapse" data-parent="#accordion" href="#"
									style="color: #767676"><h4 class="panel-title"><i
									class="glyphicon glyphicon-menu-hamburger"></i>
									&nbsp;&nbsp;IT Fee Collection
								</h4></a>
							<div class="navbar-right" style="top: 0px; right: 9px;">
							<span class="buttons" id="print">Print</span>
							</div>

						</div>
						<div id="collapseOne" class="panel-collapse collapse in "
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 20px;">

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School
										Name</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid"
											class="form-control" required>
											<option value="all">----------Select----------</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option
														value="<bean:write name="Location" property="locationId"/>"><bean:write
															name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">

										<select class="form-control" onkeypress="HideError()"
											name="classname" id="classname">
											<option value="all">ALL</option>
										</select>

										<%-- <select id="" name="" class="form-control" required>
											<option value="%%">All</option>

											<logic:present name="">
												<logic:iterate id="a" name="">
													<option	value="<bean:write name="" property=""/>">
														<bean:write name="" property=""/>
													</option>
												</logic:iterate>
											</logic:present>
										</select> --%>
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Search
										By</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" value="" />
									</div>
								</div>

							</div>

							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic
										Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control"
											required>

											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option
														value="<bean:write name="AccYear" property="accyearId"/>"><bean:write
															name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control"
											required>
											<option value="all">ALL</option>
										</select>
									</div>
								</div>

								<p align="center" style="margin-left: 17%">
									<button type="button" class="btn btn-info" id="search">Search</button>
									<button type="reset" class="btn btn-info" id="resetbtn">Reset</button>
								</p>
							</div>
							<input type="hidden" name="Acyearid" id="Acyearid"
								value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
							<%-- <input type="hidden" name="clasdetailids" id="classdetailid" value='<logic:present name="studentdetailslist" scope="request"><logic:iterate id="studentdetailslist" name="studentdetailslist"><bean:write name="studentdetailslist" property="classDetailId"/></logic:iterate></logic:present>'></input> --%>



							<div id="collapseOne" class="accordion-body collapse in">
								<div class="panel-body"
									style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


									<table class="table" id="allstudent" width="100%">
										<thead>
											<tr>
												<th>Select</th>
												<th class="sortable">Academic Year</th>
												<th class="sortable">Admission No</th>
												<th class="sortable">Student Name</th>
												<th class="sortable">Class and Section</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
									<div class="pagebanner">
										<select id="ShowPerPage"><option value="50">50</option>
											<option value="100" selected>100</option>
											<option value="200">200</option>
											<option value="300">300</option>
											<option value="400">400</option>
											<option value="500">500</option>
										</select>
											<span  class='numberOfItem'></span>	
									</div>
									<div class="pagination pagelinks"></div>

								</div>
								<br />
							</div>
						</div>
					</div>
					</div>
		</div>
	</div>
</body>
</head>
</html>
