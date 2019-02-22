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
<script type="text/javascript" src="JS/backOffice/Student/individualStudentSearch.js"></script>
<script type="text/javascript" src="JS/backOffice/Student/StudentSearch.js"></script>

<script type="text/javascript">

function handle(e){ 
	
	
	
    if(e.keyCode === 13){
        e.preventDefault(); // Ensure it is only this code that rusn

        searchList();
    }
}

</script>
<style type="text/css">
.table-striped tbody td,.table-striped thead th{
width:250px;
}
#pageLoader{
position: absolute;
width: 900px;
height: 600px;
margin: auto;
top: 0;
left: 0;
right: 0;
bottom: 0;
z-index: 999;
overflow-y:auto; 
}
</style>

</head>


<body>
<div id="pageLoader" style="display: none;">
<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Student Search
							</h4></a>
						
						
						<div class="navbar-right">
							<span class="search"  style="top:0px">
							<label style="text-align: right;color:#767676;font-family:inherit;font-weight: 500;font-size: 16px;padding: 4px;">Select Action</label>	 
							 				<select class="nil" style="color:#767676;font-family:inherit;font-size: 14px;padding: 2px">
							 					<option value="">-----Select-----</option>
							 					<option value="adm_Form">Registration Form</option>
							 					<option value="conf_Report">Disciplinary Action</option>
							 					<option value="id_Card">Single ID Card</option>
							 					<option value="mis_Report">MIS Report</option>
							 					<!-- <option value="tc">Transfer Certificate</option> -->
											 </select>
							</span>
							<span class="buttons" id="goPage" class="save" style="top:0px">Go</span>
							<span id="close" class="buttons" style="top:0px">Close</span>
						</div>
					</div>
					<div id="collapseOnePop" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
							
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="academicYear" tabindex="1"	onkeypress="HideError()" id="academicYear"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<input type="text" name="schoolName" tabindex="1"	onkeypress="HideError()" id="schoolName"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" 
										style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentFullName" tabindex="1"	onkeypress="HideError()" id="studentFullName"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission	No</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="admissionNo" tabindex="4" onkeypress="HideError()" id="admissionNo"
											onchange="" maxlength="25" readonly="readonly" 
											value='' />
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Roll No</label>

									<div class="col-xs-7">
										<input type="text" name="studentRollNo" tabindex="1"	onkeypress="HideError()" id="studentRollNo"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" name="classId" tabindex="1"	onkeypress="HideError()" id="classId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<input type="text" name="sectionId" tabindex="1"	onkeypress="HideError()" id="sectionId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								
								 <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Bus Route </label>
									<div class="col-xs-7">
										<input type="text" name="routeId" tabindex="1"	onkeypress="HideError()" id="routeId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Bus Boarding Point</label>
									<div class="col-xs-7">
										<input type="text" name="stageId" tabindex="1"	onkeypress="HideError()" id="stageId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
							</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
												<img id="imagePreview" class="setImage" alt="image" src="#" style="height: 45mm; width: 45mm;">
												<div style="position: absolute;top: 0;height: 160px;">
												<input type="file" id="studentImageId1" name="studentImage" class="form-control" style=" height: 170px !important;width:170px; opacity: 0; z-index: 99999999;">
												</div>
										</div>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student Status</label>
									<div class="col-xs-7">
										<input type="text" name="studentStatusId" tabindex="1"	onkeypress="HideError()" id="studentStatusId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Disciplinary Action</label>
									<div class="col-xs-7">
										<input type="text" name="confidentialStatusId" tabindex="1"	onkeypress="HideError()" id="confidentialStatusId" 
											maxlength="25" class="form-control" readonly="readonly" 
											value='' />
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">House</label>
									<div class="col-xs-7">
										<input type="text" name="houseId" tabindex="1"	onkeypress="HideError()" id="houseId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Second Language</label>
									<div class="col-xs-7">
										<input type="text" name="secondLanguageId" tabindex="1"	onkeypress="HideError()" id="secondLanguageId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Third Language</label>
									<div class="col-xs-7">
										<input type="text" name="thirdLanguageId" tabindex="1"	onkeypress="HideError()" id="thirdLanguageId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='' />
									</div>
								</div>
								
								<input type="hidden" id="hstudentid" name="studentId"
									value=""/>
									
								<input type="hidden" id="hacademicYearId" name="academicYearId"
									value=""/>
									
								<input type="hidden" id="hschoolNameId" name="schoolNameId"
									value=""/>
								
								<input type="hidden" id="photohiddenid" name="previousImage"
									value="">
							</div>
						</div>
						<hr style="height:1px;border:none;color:#333;background-color:#ddd;"/>
					
					
						<div>
						<div class="slideTab clearfix">
						<div class="tab">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#contacts"  id="contacts">Contacts</a></li>
								<li><a data-toggle="tab" href="#classHistory" id="classHistory">Class History</a></li>
							<li><a data-toggle="tab" href="#ContactAddr" id="ContactAddr">Address</a></li>
							</ul>
						
							<div id="contacts" class="tab-pane">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap">
									<div class="col-md-8" id=div2></div>
									<div id="studenttable"></div>
									<div id="individualstudenttable"></div>	
									<div id="Addressstudenttable"></div>	
								</div>
								</div>
							</div>
							
						</div>
					</div>
					</div>
					
				
				
			</div>

</div>
</div>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		
		<p><img src="images/addstu.png" /><span id="pageHeading">Student Search</span></p>
				<div class="panel-body clearfix"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
				</div>

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title" style="vertical-align: super;"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Student Search</h4></a>
						
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											<option value="all">ALL</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
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
									style="text-align: right; line-height: 35px;">Sort by</label>
								<div class="col-xs-7">

									<select class="form-control" onkeypress="HideError()"
										name="sortingby" id="sortingby">
										<option value="Alphabetical">Alphabetical Order</option>
										<option value="Gender">Gender Wise</option>
										<option value="Admission">Admission No.</option>
									</select>

								</div>
							</div>
							
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search By</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" onkeypress="handle(event)"
										value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
									</div>
								</div>

							

						</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
						
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
							
								<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"></label>
								<div class="col-xs-7" id="orderby">
									<input type="radio" value="ASC" name="sorting" class="sorting" id="ASC" checked="checked"><p1>Ascending</p1>
									<input type="radio" value="DESC" style="margin-left:30px" name="sorting" class="sorting" id="DESC" title="Male"><p2>Descending</p2>
									
									<input type="radio" value="ASC" name="sorting1" class="sorting" id="Female" checked="checked" hidden><p3 hidden>Female</p3>
									<input type="radio" value="DESC" style="margin-left:30px" name="sorting1" class="sorting" id="Male" hidden><p4 hidden>Male</p4>
								</div>
							</div>
							
								<p align="center"style="margin-left:17%">
								<button type="button" class="btn btn-info" id="search" >Search</button>
								<button type="reset" class="btn btn-info" id="resetbtn" style="height:28px;" >Reset</button></p>
							
							
							
							
							</div>
						<input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
							
				<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">	
					
					
						<table class="table table-striped" id="allstudent">
							<thead>
							<tr>
							<th>S.No</th>
							<th>Admission No</th>
							<th>Student Name</th>
							<th>Roll No</th>
							<th>Class and Division</th>
							</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
				
					
					
					</div>
        <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
	    <span  class='numberOfItem'></span>	
	    </div><div class='pagination pagelinks' style='top:-9px'></div>
					</div>
					
						</div>
					</div>
				</div>
			</div>
	</div>
</html>
