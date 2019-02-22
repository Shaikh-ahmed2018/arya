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

<script  type="text/javascript" src="JS/backOffice/Election/nominationRegister.js"></script>



<style>
.pagebanner {
    margin-top: 20px;
    }
#editStudent:hover {
	cursor: pointer;
}

#trash:hover {    
	cursor: pointer;
}
</style>
<style>
.download:hover{

cursor: pointer;
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
</style>
<script>
function ShowTable(){

	$("#collapseOne").toggleClass("in");

}
</script>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
	

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Nomination Registration</span>
			</p>
			
			
			
<!-- Pop up starts -->
			<div id="StudentNomineeDialog" style="display: none">
			<div class="col-md-12">
				<input type="hidden" id="hiddenGroupId" value="" />
				<input type="hidden" id="hiddenElectionId" value="" />
				<input type="hidden" id="hiddenElectionCategoryId" value="" />
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
									style="text-align: right;">Admission No</label>
							<div class="col-xs-7">
							<input type="text" name="admissionNo" onkeypress="HideError()" id="admissionNo" class="form-control" />
							</div>
							</div>
							
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">Student Name</label>
						<div class="col-xs-7">
							<input type="text" name="studentName" id="studentName" class="form-control" value="" />
							<input type="hidden" name="hStudentId" id="hStudentId" class="form-control" value="" />
						</div>
					</div>
					
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">Class</label>
						<div class="col-xs-7">
							<input type="text" name="className"  id="className" class="form-control" />
							<input type="hidden" name="hclassId" id="hclassId" class="form-control" />
						</div>
					</div>
			
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">Division</label>
						<div class="col-xs-7">
							<input type="text" name="division" id="division"class="form-control" />
							<input type="hidden" name="hdivisionId" id="hdivisionId" class="form-control" />
						</div>
					</div>
					
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">House</label>
						<div class="col-xs-7">
							<input type="text" name="house" id="house"class="form-control" />
						</div>
					</div>
				
				</div>


			<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
						<div class="col-xs-7">
							<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 150px;">
												<img id="imagePreview" class="setImage" alt="image" src="images/girl.png" style="height:100%; width:100%;">
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
					<h3 class="panel-title" style="color: #767676; vertical-align: middle;"> <span class="glyphicon glyphicon-menu-hamburger"></span>
					&nbsp;&nbsp;Register New Nominee
					</h3></a>
			
			</div>
			<!-- pop up -->


<div id="filteration" class="filteration clearfix">
	<div class="panel-body" id="tabletxt">

		<div class="col-md-6" id="txtstyle">
									
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-4" align="right"
					id="inputnames">Academic Year</label>
					<div class="col-xs-7">
						<select id="academicYear" name="accyear" class="form-control">
							<option value="all">All</option>
						</select>
					</div>
			</div>
									
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-4" align="right"
					id="inputnames">Group Name</label>
					<div class="col-xs-7">
					   <select id="groupName" name="grpname" class="form-control">
							<option value="">----Select----</option>
						</select>
						</div>
				</div>
									
								
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4" align="right"
						id="inputnames">Election Title</label>
						<div class="col-xs-7">
							<select id="electionTitle" name="electionTitle" class="form-control">
								<option value="">----Select----</option>
							</select>
						</div>
				</div>
		</div>
</div>
	
</div>
 <div id="collapseOne" class="panel-collapse collapse in">
 	<table class='table'  id='allstudent' style="width:100%" >
 	<thead>
 		<tr>
 			<th>Sl.No.</th>
			<th>Election Category</th>
			<th>Nomination</th>
		</tr>
	</thead>
	<tbody>	
	</tbody>
</table>
		<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
			<span  class='numberOfItem'></span>	
			</div><div class='pagination pagelinks'></div>
</div>
					
</div>
				
</div>
</body>
</html>					
				
				
				
