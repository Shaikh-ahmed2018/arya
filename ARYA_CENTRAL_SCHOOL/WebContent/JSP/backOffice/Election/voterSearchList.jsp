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
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Election/individualVoterSearch.js"></script>

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

.tableheading{
  text-align: center;
  }

</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
	<div class="searchWrap">
		<div  id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Voter Search</span>
			</p>
		</div>
		
	</div>
		
		<input type="hidden" id="succmsg" value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />
		
			<div id="successmessages" class="successmessagediv" style="display: none;">
				<div class="message-item">
					 <a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"><logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present></span></a>
				</div>
			</div>
		
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><logic:present name='errorMessage'  scope='request' ><bean:write name='errorMessage'  /></logic:present></span></a>
				</div>
			</div>
		
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" >
					<h3 class="panel-title" style="color: #767676; vertical-align: middle;"> <span class="glyphicon glyphicon-menu-hamburger"></span>
					&nbsp;&nbsp;Student Details
					</h3></a>

			</div>
			<!-- pop up -->


<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" id="tabletxt" style="padding: 15px;">

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
								
									
								<div class="form-group clearfix" >
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Class</label>
								<div class="col-xs-7">
											<select id="className" name="className" class="form-control">
											<option value="all">----Select----</option>
										</select>
									</div>
								</div>
								 	
							</div>
							<div class="col-md-6" id="txtstyle">
			
								 <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Election Title</label>
										<div class="col-xs-7">
											<select id="electionTitle" name="electionTitle" class="form-control">
											<option value="all">----Select----</option>
										</select>
									</div>
									</div>
								
												<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Section</label>
										<div class="col-xs-7">
										<select id="sectionName" name="sectionName" class="form-control">
											<option value="all">----Select----</option>
										</select>
										</div>
								</div>
								
			
								
							</div>

	
<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
 <table class='table' style="margin-right:250px;" id='allstudent' width='100%' style="margin-top:50px">
 					
 					<thead>
 					<tr>
 						
 						<th>Sl.No.</th>
 						<th>Admission Number</th>
						<th>Student Name</th>
						<th>Roll Number</th>
						<th>Class</th>
						<th>Section</th>
						<th>Status</th>
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
		</div>
	</div>

	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>