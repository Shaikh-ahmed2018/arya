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

<script  type="text/javascript" src="JS/backOffice/Reports/defaulterList.js"></script>

<style>

.pagebanner {
    margin-top: 20px;
    }
#trash:hover {    
	cursor: pointer;
}

.download:hover{
cursor: pointer;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
.filteration{
padding: 15px;
}
textarea{
width:100%;
}
.navbar-right {
    top: 0px;
}
#deleteDialog{
display: none;
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
			<p><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Defaulter List</span></p>
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
					<h3 class="panel-title" style="color: #767676; vertical-align: middle;"> <span class="glyphicon glyphicon-menu-hamburger"></span>
					&nbsp;&nbsp;Defaulter Fee List
					</h3></a>
					
			<div class="navbar-right">
				<span class="buttons" id="print">Print</span>
				<span  class="buttons"  id="download"  >Download </span>
			</div>
		
			
		
			</div>
			
		
			<!-- pop up -->



<!-- Filteration Tabs  -->
<div id="filteration" class="filteration clearfix">
	<div class="panel-body" id="tabletxt">

		<div class="col-md-6" id="txtstyle">
									
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right"
					id="inputnames">School Name</label>
					<div class="col-xs-7">
						<select id="locationName" name="locationName" class="form-control">
							<option value="">----select----</option>
							 <logic:present name="locationList">
								<logic:iterate id="Location" name="locationList">
									<option  value='<bean:write name="Location" property="locationId"/>'><bean:write name="Location" property="locationName" /></option>
								</logic:iterate>
							</logic:present> 
						</select>
					</div>
			</div>
				
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right"
					id="inputnames">Class </label>
					<div class="col-xs-7">
						<select id="className" name="className" class="form-control">
							<option value="">----select----</option>
						</select>
					</div>
			</div>
			
				<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right"
					id="inputnames">Terms </label>
					<div class="col-xs-7">
						<select id="termName" class="form-control" 	onkeypress="HideError()">
							<option value="">----select----</option>
						</select>
					</div>
			</div>
		</div>
		
		
		<div class="col-md-6" id="txtstyle">
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right"
					id="inputnames">Academic Year</label>
					<div class="col-xs-7">
						<select id="academicYear" name="accyear" class="form-control">
								<logic:present name="AccYearList">
												<logic:iterate id="accyear" name="AccYearList">
													<option value="<bean:write name="accyear" property="accyearId"/>"><bean:write name="accyear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present> 
						</select>
					</div>
					</div>
					
				<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right"
					id="inputnames">Division</label>
					<div class="col-xs-7">
						<select id="divisionName" class="form-control">
								<option value="all">ALL</option>
						</select>
					</div>
					</div>
					
					
			
		</div>
</div>
</div><!-- Filteration OVER  -->

 <div id="collapseOne" class="panel-collapse collapse in table-responsive">
					<table class='table' id='allstudent' style="width: 100%">
						<thead>
							<tr>
								<th>Sl no</th>
								<th>Admission Number</th>
								<th>Student Name</th>
								<th>School Name</th>
								<th>Class</th>
								<th>Division</th> 
								<th>Term</th>
								<th>Due Amount</th> 
								<th>Contact No.</th> 
								<th>Sms No.</th> 
							</tr>
						</thead>
						<tbody>
						</tbody>
						
					</table>
		                

</div>
</div>
</div>
</div>
</body>
</html>					
				
				
				
