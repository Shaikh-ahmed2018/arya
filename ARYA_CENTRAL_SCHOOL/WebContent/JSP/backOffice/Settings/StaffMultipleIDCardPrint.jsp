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
 <script type="text/javascript" src="JS/backOffice/Student/staffMultiIDcardPrint.js"></script> 
<script type="text/javascript" src="JS/common.js"></script>


</script>

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
#allstudent tbody tr td:nth-child(1){
width:auto;

}
.info3{
border:2px solid #cccccc !important;
}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
	<div class="searchWrap">
		<div  id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Print Staff ID Card - Multiple</span>
			</p>
		</div>
	</div>
		
		<input type="hidden" id="succmsg" value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />
			<center>
			<div id="successmessages" class="successmessagediv" style="display: none;">
				<div class="message-item">
					 <a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"><logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present></span></a>
				</div>
			</div>
			</center>
			<center>
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><logic:present name='errorMessage'  scope='request' ><bean:write name='errorMessage'  /></logic:present></span></a>
				</div>
			</div>
			</center>
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" >
					<h3 class="panel-title" style="color: #767676; vertical-align: middle;"> <span class="glyphicon glyphicon-menu-hamburger"></span>
					&nbsp;&nbsp;Staff Details
					</h3></a>
				<div class="navbar-right">
					
					<span id="editStudent" class="buttons">Print</span>
					
				</div>
			</div>
			<!-- pop up -->


	

 		<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
			<table id="allstudent" class="table">
		
				<tr>
					<td class="info3" >
						<div class="info1" class="row">
							<div class="info col-md-6" >
								<div class="roll" style="width:150px; height:30px; margin-top:15px; float:left">Staff Name</div>
								
								<div class="roll" style="width:150px; height:30px">Designation</div>
							</div>
						<div class="info col-md-6">
							<div class="photo"><img alt="photp" src="#" style="width:150px; height:150px;"></div>
						</div>
					</div>
		</td>
		
		<td class="info3">
						<div class="info1" class="row">
							<div class="info col-md-6" >
								<div class="roll" style="width:150px; height:30px; margin-top:15px; float:left">Kavitha V</div>
								
								<div class="roll" style="width:150px; height:30px">Teacher</div>
							</div>
						<div class="info col-md-6">
							<div class="photo"><img alt="photp" src="#" style="width:150px; height:150px;"></div>
						</div>
					</div>
		</td>
		<td class="info3">
						<div class="info1" class="row">
							<div class="info col-md-6" >
								<div class="roll" style="width:150px; height:30px; margin-top:15px; float:left">Amrutha K</div>
								
								<div class="roll" style="width:150px; height:30px">Teacher</div>
							</div>
						<div class="info col-md-6">
							<div class="photo"><img alt="photp" src="#" style="width:150px; height:150px;"></div>
						</div>
					</div>
		</td>

		</tr>
		
		
		
		
						<tr>
					<td class="info3">
						<div class="info1" class="row">
							<div class="info col-md-6" >
								<div class="roll" style="width:150px; height:30px; margin-top:15px; float:left">2369</div>
								
								<div class="roll" style="width:150px; height:30px">Teacher</div>
							</div>
						<div class="info col-md-6">
							<div class="photo"><img alt="photp" src="#" style="width:150px; height:150px;"></div>
						</div>
					</div>
		</td>
		
		<td class="info3">
						<div class="info1" class="row">
							<div class="info col-md-6" >
								<div class="roll" style="width:150px; height:30px; margin-top:15px; float:left">98741</div>
								
								<div class="roll" style="width:150px; height:30px">Teacher</div>
							</div>
						<div class="info col-md-6">
							<div class="photo"><img alt="photp" src="#" style="width:150px; height:150px;"></div>
						</div>
					</div>
		</td>
		<td class="info3">
						<div class="info1" class="row">
							<div class="info col-md-6" >
								<div class="roll" style="width:150px; height:30px; margin-top:15px; float:left">7896</div>
								
								<div class="roll" style="width:150px; height:30px">Teacher</div>
							</div>
						<div class="info col-md-6">
							<div class="photo"><img alt="photp" src="#" style="width:150px; height:150px;"></div>
						</div>
					</div>
		</td>

		</tr>
		
						<tr>
					<td class="info3">
						<div class="info1" class="row">
							<div class="info col-md-6" >
								<div class="roll" style="width:150px; height:30px; margin-top:15px; float:left">Manasa</div>
								
								<div class="roll" style="width:150px; height:30px">Principal</div>
							</div>
						<div class="info col-md-6">
							<div class="photo"><img alt="photp" src="#" style="width:150px; height:150px;"></div>
						</div>
					</div>
		</td>
		
		<td class="info3">
						<div class="info1" class="row">
							<div class="info col-md-6" >
								<div class="roll" style="width:150px; height:30px; margin-top:15px; float:left">prathibha</div>
								
								<div class="roll" style="width:150px; height:30px">Care Taker</div>
							</div>
						<div class="info col-md-6">
							<div class="photo"><img alt="photp" src="#" style="width:150px; height:150px;"></div>
						</div>
					</div>
		</td>
		<td class="info3">
						<div class="info1" class="row">
							<div class="info col-md-6" >
								<div class="roll" style="width:150px; height:30px; margin-top:15px; float:left">Manasa</div>
								
								<div class="roll" style="width:150px; height:30px">Care Taker</div>
							</div>
						<div class="info col-md-6">
							<div class="photo"><img alt="photp" src="#" style="width:150px; height:150px;"></div>
						</div>
					</div>
		</td>

		</tr>
</table>
</div>

</div>
</div>





</body>
</html>