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
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="CSS/Admin/StudentNew.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Student/findStudent.js"></script>
</head>

<body> 
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		
		<p><img src="images/addstu.png" /><span id="pageHeading">Find Student</span></p>
				<div class="panel-body clearfix"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				</div>

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Find Student</h4></a>
						
						
						<div class="navbar-right">
							<span class="search"  style="top:5px">
							<label style="text-align: right;color:#767676;font-family:inherit;font-weight: 500;font-size: 16px;padding: 4px;margin-left: -165px">Certificate Type</label>	 
							 				<input type="text" name="" tabindex="1"	onkeypress="HideError()" style="margin-top: -37px;margin-left: -25px;" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
							</span>
									
							<!-- <span class="buttons" id="" class="save" style="line-height: 1.89;">Go</span> -->
						</div>
						
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
									
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search By</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"
											onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
								
							</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Section</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
								
								<p align="center"style="margin-left:17%">
								<button type="button" class="btn btn-info "
								id="search" >Search</button>
								<button type="button" class="btn btn-info "
								id="Reset" >Reset</button></p>
								
							</div>
					<table class='table' id='allstudent' width='100%'>
						<tr>
							<th>Sl.No.</th>
							<th style="text-align:center">Admission No</th>
							<th style="text-align:center">Student Name</th>
							<th style="text-align:center">Roll No</th>
							<th style="text-align:center">Class</th>
							<th style="text-align:center">Division</th>
						</tr>
						<tr>
							<td >1</td>
							<td>10225</td>
							<td>Abc</td>
							<td>001</td>
							<td>X</td>
							<td>A</td>
						</tr>
						<tr>
							<td>2</td>
							<td>10226</td>
							<td>Mno</td>
							<td>002</td>
							<td>X</td>
							<td>B</td>
						</tr>
						<tr>
							<td>3</td>
							<td>10227</td>
							<td>Pqr</td>
							<td>003</td>
							<td>X</td>
							<td>C</td>
						</tr>
						<tr>
							<td>4</td>
							<td>10228</td>
							<td>Xyz</td>
							<td>003</td>
							<td>X</td>
							<td>D</td>
						</tr>
						<tr>
							<td>5</td>
							<td>10229</td>
							<td>Nil</td>
							<td>003</td>
							<td>X</td>
							<td>E</td>
						</tr>
						</table>
						</div>
					</div>
				</div>
			</div>
	</div>
</html>
