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
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css">
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
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
<link href="CSS/Admin/StudentNew.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Fee/FeeScholorship.js"></script>


<title>eCampus Pro</title>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

.glyphicon-pencil:hover {
	cursor: pointer;
}

#edit:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
table tr[class^='STU'] td{
vertical-align: middle;
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
.paymentStatus span {
     display: inline-block;
    padding: 0 5px;
    margin: 0 2px;
    min-width: 80px;
    text-align: center;
    color: #fff;
    font-weight: 600;
    border-radius: 6px;
    box-shadow: 5px 5px 5px #ccc;
}
span.Not.Paid{
background-color: #f00;
}
span.Paid{
background-color: rgba(0, 158, 0, 0.66);
}
.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons {
    width: 89% !important;
    left: 0 !important;
    right: 0 !important;
    margin: auto;
}
.concessionType{
text-align: right;
}
input[type='text']{
width: 100%;
}
.equalconcession,.partialconcession{
display: none;
}
</style>
</head>

<body class="feeconcession">


	<div class="successmessagediv" style="display: none;">
		<div class="message-item">
			<!-- Warning -->
			<a href="#" class="msg-success bg-msg-succes"
				style="text-align: center;"><span
				class="sucessmessage"> </span></a>
		</div>
	</div>
	<div class="errormessagediv" style="display: none;">
		<div class="message-item">
			<!-- Warning -->
			<a href="#" class="msg-warning bg-msg-warning"
				style="text-align: center;"><span
				class="validateTips"></span></a>
		</div>
	</div>
	<div id="myDialog" style="display:none;">
	<div class='row'>
			<div class='col-md-6'>
				<div class='form-group clearfix'>
					<label for='labelforAdmissionNo' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Admission
						No.</label>
					<div class="col-xs-7">
						<input type="text" name='admissionNo' id='admissionNo' />
						<input type="hidden" name='hstudentId' id='hstudentId' />
					</div>
				</div>
				
				<div class='form-group clearfix'>
					<label for='labelforName' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Name</label>
					<div class="col-xs-7">
						<input type="text" name='student' id='student' />
					</div>
				</div>
				
				
				<div class='form-group clearfix'>
					<label for='labelforName' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Concession Type</label>
					<div class="col-xs-7">
						<div class="concessionType"><label for='labelforFullConcession' class='control-label' >Full Concession </label> <input type="radio" name='contype' value="full"  /></div>
						<div class="concessionType"><label for='labelforEqualConcession' class='control-label' >Equally Concession </label> <input type="radio" name='contype' value="equal" /></div>
						<div class="concessionType"><label for='labelforPartialConcession' class='control-label' >Concession Term wise</label> <input type="radio" name='contype' value="partial" /></div>
					</div>
				</div>
				
			</div>
			<div class='col-md-6'>
				<div class='form-group clearfix'>
					<label for='labelforclass' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Standard-Division</label>
					<div class="col-xs-7">
						<input type="text" name='class_section' id='class_section'  />
						<input type="hidden" name='hclassId' id='hclassId' />
						<input type="hidden" name='hspecialization' id='hspecialization' />
					</div>

				</div>
				
				
				<div class='form-group clearfix'>
					<label for='AcademicYear' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Academic
						Year</label>
					<div class="col-xs-7">
						<select id="AcademicYearFor" name="AcademicYearFor" class="form-control"
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
				
				<div class='form-group clearfix equalconcession'>
					<label for='labelforscholorshipAmount' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Concession
						Amount</label>
					<div class="col-xs-7">
						<input type="text" name='scholorshipAmount'
							id='scholorshipAmount'  />
					</div>

				</div>
				
				
				<div class='form-group clearfix partialconcession'>
					<label for='labelforscholorshipAmount' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Concession
						Amount</label>
					<div class="col-xs-7">
						<fieldset style="width: auto;padding-top: 10px;">
						<legend>Term wise</legend>
						<table id="termwiseconcession">
							<thead>
								<tr>
									<th>Term Name</th>
									<th>Fee Amount</th>
									<th style="width:100px;">Concession Amount</th>
								</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
						
						
						</fieldset>
					</div>

				</div>
				
				
			</div>
			<div class='col-md-12'>
				
			</div>
		</div>
	</div>
	<div id="dialog" style="display:none;"></div>
	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="searchWrap">
			<div class="" id="div2">
				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Fee Concession Details</span>
				</p>
			</div>
		</div>
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading clearfix" role="tab" id="headingOne">

						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne"
							style="color: #767676; vertical-align: text-top; ">
							<h4 class="panel-title">
								<i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Fee Concession Student List
							</h4>
						</a>
					<div class="navbar-right">
					 <span class="buttons" id="add">Add</span>
					 <span id="delete" class="buttons">Delete</span>
					</div>
					</div>
					
				</div>
					<div id="collapseOne" class="panel-collapse collapse in "
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 20px;">

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">School Name</label>
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

									
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Search
										By</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue"
											Placeholder="Search......"
											value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
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



							<div id="collapseOne" class="accordion-body collapse in">
								<div class="panel-body"
									style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


									<table class="table" id="allstudent" width="100%">
										<thead>
											<tr>
												<th><input type='checkbox' id='selectAll' /></th>
												<th class="sortable">Academic Year</th>
												<th class="sortable">Admission No</th>
												<th class="sortable">Student Name</th>
												<th class="sortable">Class and Section</th>
												<th class="sortable">Concession Type</th>
												<th class="sortable">Photo</th>

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
										</select> <span class='numberOfItem'></span>
									</div>
									<div class="pagination pagelinks"></div>

								</div>
								<br />
							</div>
						</div>
					</div>
					</div>
					</div>
					
</body>
</html>