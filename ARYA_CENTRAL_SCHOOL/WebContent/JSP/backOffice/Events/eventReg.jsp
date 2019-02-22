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

<link rel="stylesheet"href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
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
<script  type="text/javascript" src="JS/backOffice/Events/EventReg.js"></script>
<style>
.pagebanner {
    margin-top: 11px;
    }
#editStudent:hover {
	cursor: pointer;
}
.glyphicon-trash {
    margin-top: -2px;
    height: 20px;
}
#trash:hover {    
	cursor: pointer;
}
.download:hover{
cursor: pointer;
}
span.CLOSED{
background-color:#FF0000;
min-width:80px;
display:inline-block;
 color:#fff;
 font-weight: 900;
}
span.OPEN{
background-color:rgba(0, 158, 0, 0.66);
min-width:80px;
display:inline-block;
 color:#fff;
 font-weight: 900;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all{
overflow: scroll;
max-height: 300px;
}
.filteration{
padding: 15px;
}
textarea{
width:100%;
}
#allstudent tbody td{
border:1px solid #dedede
}
#deleteDialog{
display: none;
}
.pagination {
    margin-top: 10px;
}
#allstudent thead tr, #allstudent thead tr th:last-child {
    border: 1px solid #dedede;
}
.successmessagediv{
margin-top:35px;
}
.errormessagediv{
margin-top:30px;
}
#allstudent thead tr:last-child{
background:#f9f9f9 !important;
}
#allstudent tbody tr{
border:1px solid #dedede !imporatnt;
}
#allstudent tbody tr,#allstudent tbody tr td:nth-child(7){
border:none !important;
}
#allstudent tr:nth-child(n) td:last-child, .allstudent tr:nth-child(n) td:last-child, #allstudents tr:nth-child(n) td:last-child {
    background-color: transparent;
    border: 1px solid #dedede;
}
</style>
<script>
function ShowTable(){
	$("#collapseOne").toggleClass("in");
}
</script>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
	<div id="dialog"></div>
	<div id="deleteDialog"></div>
	
	<div class="eventRegistration">
			<p><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Event Registration</span></p>
			
<!-- Pop up starts -->
			<div id="StudentNomineeDialog" style="display: none">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
						</div>
					</div>
				</div>
				
				
				
				<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
					
					<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right;">Event Name</label>
							<div class="col-xs-7">
							<input type="text" name="eventNameSave"  id="eventNameSave" class="form-control" />
							<input type="hidden" id="hiddenEventId" class="form-control"/>
							<input type="hidden" id="eventNameHidden" class="form-control"/>
							<input type="hidden" id="locationIdHidden" class="form-control"/>
							</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Event Start On</label>
								<div class="col-xs-7">
									<input type="text" name="startsOn" id="startsOn" readonly="readonly" class="form-control"  />
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Event Ends On</label>
								<div class="col-xs-7">
									<input type="text" name="endsOn" id="endsOn" readonly="readonly" class="form-control" />
								</div>
							</div>
							
			
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;"> Event Type</label>
						<div class="col-xs-7">
							<input type="text" name="eventType" id="eventType"class="form-control" />
						</div>
					</div>
					
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Start Registration</label>
								<div class="col-xs-7">
									<input type="text" name="strtReg" id="strtReg" readonly="readonly" class="form-control"  />
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">End Registration</label>
								<div class="col-xs-7">
									<input type="text" name="endReg" id="endReg" readonly="readonly" class="form-control" />
								</div>
							</div>
					
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-10">Approval for participants Registration</label>
						<div class="col-xs-2">
						<input type="checkbox" name="checkValue" id="isAprovPps" value="yes" />
						</div>
					</div>	
					
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-10">House Wise</label>
						<div class="col-xs-2">
							<input type="checkbox" name="checkValue" id="isHouseWise" value="yes" />
						</div>
					</div>	
					
					<div class="form-group clearfix importSett">
						<label for="inputPassword" class="control-label col-xs-5">Import All settings</label>
						<div class="col-xs-7">
							<select  name="importSettings" id="importSettings" >
								<option value="noImport">----------SELECT----------</option>
							</select>
						</div>
					</div>	
					
					
				</div>


			<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
						
						<div class="form-group clearfix ">
						<div class="col-xs-5">
						</div>
						<div class="col-xs-7">
						<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width:150px; height: 150px;">
												<img id="imagePreview" class="setImage" alt="image" src="images/girl.png" style="height:100%; width:100%;">
							</div>
						</div>
					</div>
					
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5" style="text-align: right;">Enter Description</label>
						<div class="col-xs-7">
						<textarea rows=8 cols=20 id="description"></textarea>
						</div>
					</div>	
						
						
			</div> 
		</div>
		
		
		
</div>
<!--Pop Up Ends  -->		


		
		<input type="hidden" id="succmsg" value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />
	
			<div id="successmessages" class="successmessagediv" style="display: none;">
				<div class="message-item">
					 <a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"><logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present></span></a>
				</div>
			</div>
	
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><logic:present name='errorMessage'  scope='request' ><bean:write name='errorMessage'  /></logic:present></span></a>
				</div>
			</div>

		
		<div class="panel panel-primary clearfix">
			<div class="panel-heading">
				<a 	href="javascript:ShowTable();" >
					<h3 class="panel-title" style="color: #767676;vertical-align: text-top;"> <span class="glyphicon glyphicon-menu-hamburger"></span>
					&nbsp;&nbsp;Event Registration
					</h3></a>
			<div class="navbar-right" style="margin-top:3px;">
					<a>
					<span id="Register" class="buttons">Register</span>
					</a>
				</div>
			</div>
			<!-- pop up -->



<!-- Filteration Tabs  -->
<div id="filteration" class="filteration clearfix">
	<div class="panel-body" id="tabletxt">

		<div class="col-md-6" id="txtstyle">
									
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">School Name</label>
					<div class="col-xs-7">
						<select id="location" name="location" class="form-control">
							<option value="">-----------Select-----------</option>
							 <logic:present name="locationList">
								<logic:iterate id="Location" name="locationList">
									<option  value='<bean:write name="Location" property="locationId"/>'>
									<bean:write name="Location" property="locationName" />
								</logic:iterate>
							</logic:present> 
					
						</select>
					</div>
			</div>
								
				<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Search</label>
						 	<div class="col-xs-7">
								<input type="text" name="searchname" id="searchname" class="form-control"
									Placeholder="Search......"
									value="<logic:present name="searchTerm"><bean:write name="searchTerm" /></logic:present>">
							</div> 
		
						</div>
		</div>
		
				<div class="col-md-6" id="txtstyle">
									
			<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Academic Year</label>
					<div class="col-xs-7">
						<select id="academicYear" name="accyear" class="form-control">
								<logic:present name="AccYearList">
												<logic:iterate id="accyear" name="AccYearList">
													<option value="<bean:write name="accyear" property="accId"/>"><bean:write name="accyear" property="accName" /></option>
												</logic:iterate>
											</logic:present> 
											
						</select>
					</div>
					</div>
					<div class="form-group clearfix">
						<div class="control-label col-xs-5"></div>
						<div class="col-xs-6">
							<button class="btn btn-info" type="button" id="search" >Search</button>
							<button type="reset" class="btn btn-info" id="resetbtn">Reset</button>
						</div>
					</div>
			
		</div>
</div>
</div><!-- Filteration OVER  -->

 <div id="collapseOne" class="panel-collapse collapse in table-responsive">
					<table class='table' id='allstudent' style="width: 100%">
						<thead>
							<tr>
								<th>Sl.No.</th>
								<th>School Name</th>
								<th>Academic year</th>
								<th>Event Name</th>
								<th>Event Date</th>
								<th>Registration Status</th> 
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
							<span  class='numberOfItem'></span>	

					</div>
					<div class='pagination pagelinks'></div>

</div>
</div>
</div>
</div>
</body>
</html>					
				
				
				
