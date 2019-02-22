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

<script type="text/javascript" src="JS/backOffice/Election/countingListForVoting.js"></script>



<style>
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
.glyphicon-trash:before {
    content: "\e020";
    margin-left: 15px;
}
.multiple{
height: auto !important;
}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
	<div class="searchWrap">
		<div  id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Election Report</span>
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
			
		
		</div>

	</div>
		
		<input type="hidden" id="succmsg" value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />
		
		
		<div class="panel panel-primary">
		
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" >
					<h3 class="panel-title" style="color: #767676; vertical-align: middle;"> <span class="glyphicon glyphicon-menu-hamburger"></span>
					&nbsp;&nbsp;Find Election Details
					</h3></a>
					
				
			
			</div>
			
			<!-- pop up -->


		
						
					
	<div class="panel-body" id="tabletxt" style="padding: 15px;">
							<div class="col-md-6" id="txtstyle">
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Academic Year</label>
									<div class="col-xs-7">
										<select  name="accyear" tabindex="1" id="academicYear" class="form-control">
										<option value="">----------Select----------</option>
										</select>
									
									</div>
								</div>
								
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Group Name</label>
									<div class="col-xs-7">
										<select  name="electionGroup"  id="electionGroup" class="form-control">
											<option value="">----------Select----------</option>
										</select>	
									
									</div>
								</div>
											
																			
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Election Title</label>
									<div class="col-xs-7">
										<select  name="electionTitle"  id="electionTitle" class="form-control">
											<option value="">----------Select----------</option>
										</select>
									
									</div>
								</div>
								</div>
							

	</div>

				<div id="collapseOne" class="panel-collapse collapse in ">
					<table class='table allstudent' id='allstudent' width='100%'>
						<thead>
							<tr>
								<th>Sl.No.</th>
								<th>Academic Year</th>
								<th>Group Name</th>
								<th>Election Name</th>
								<th>Election Status</th>
								
							</tr>
						</thead>

						<tbody>
						</tbody>

					</table>
				</div>
			</div>
					
			
		</div>


</body>
</html>

