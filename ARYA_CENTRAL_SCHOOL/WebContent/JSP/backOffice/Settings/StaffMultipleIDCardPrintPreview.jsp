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

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="CSS/newUI/custome.css" rel="stylesheet">

<script type="text/javascript" src="JS/backOffice/Staff/StaffIDPrint.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<link href="CSS/IdCard/StaffId/StaffIdCard.css" rel="stylesheet" type="text/css" />
<link href='CSS/IdCard/StaffId/<logic:present name="template" scope="request" ><bean:write name="template" /></logic:present>.css' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/qrcode.min.js"></script>

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

	<div class="col-md-10 col-md-offset-2" id="div-main">
	<div class="searchWrap">
		<div class="clearfix">
		<div class="col-md-7">
		<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Print Preview Staff ID Card - Multiple</span>
			</p>
		</div>
		<div class="col-md-5">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-9"><input type="radio" name="print" value="card"/><span> Card Printing </span><input type="radio" name="print" value="A3"/><span> A3 Extra </span><input type="radio" name="print" value="A4"/><span> A4 </span></div>
		</div>
		</div>
		</div>
			
		
	</div>
	<input type="hidden" id="templatename" value='<logic:present name="template" scope="request" ><bean:write name="template" /></logic:present>' />
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
		
		<div class="panel panel-primary clearfix">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" >
					<h3 class="panel-title" style="color: #767676; vertical-align: middle;"> <span class="glyphicon glyphicon-menu-hamburger"></span>
					&nbsp;&nbsp;Student Details
					</h3></a>
				<div class="navbar-right">
				<span id="print" class="buttons">Print</span>
				
					<span id="back" class="buttons">Back</span>
		
				</div>
			</div>
			


	

 		<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
 		<div class="clearfix" id="staffpreview">
 		<logic:present name="staffList" scope="request">
 			<logic:iterate id="staffList" name="staffList">
 		
				
				
				<section class="col-md-4 section">
			<div class="main-div" id="main-div" >
			 <img src='images/IdCard/<logic:present name="template" scope="request" ><bean:write name="template" /></logic:present>.jpg' width="100%" height="100%" id="layoutImage" />
			  <header>
				<div class="schoolName" id="schoolName"><span id="schoolNameChange"><bean:write name="staffList" property="locationName" /></span></div>
				<div class="branch" id="branch"><span id="branchName"><bean:write name="staffList" property="location_address" /></span></div>
				<div class="phone" id="phone"><span id="teleName">Tel.<bean:write name="staffList" property="location_phone" /></span></div>
			</header>
				<div class="namediv" id="namediv">
					<span id="nametext"><bean:write name="staffList" property="teacherName"/></span>
				</div>


				<div class="photo" id="photo">
					<img src='<bean:write name="staffList" property="images"/>' alt="image" width="100%" height="100%" />
				</div>






				
					
							<div class="abbravative" id="abbravative">
							<span id="abbrativeText"><bean:write name="staffList" property="abbrivateId"/></span>
						</div>
						<div class="registrationId" id="registrationId">
							<span id="regNotext">Dept. Of <bean:write name="staffList" property="departmentName"/></span>
						</div>
						<div class="designation" id="designation">
							<span id="degIdtext">Designation <bean:write name="staffList" property="designationName"/></span>
						</div>
						<div class="valid" id="valid">
							<span class="label" id="validUpto">Valid upto : </span><span id="validUptotext" class="validUptotext"><logic:present name="validUpto" scope="request" ><bean:write name="validUpto"/></logic:present></span>
						</div> 
					



					<div class="addressdetails" id="address">
						<span id="addresstext"><bean:write name="staffList" property="address"/></span>
					</div>


					<div class="phonedetails" id="phonedetails">
						<span class="phones" id="phonelebel">Mob : </span><span id="phonetext"><bean:write name="staffList" property="mobileNo"/></span>
					</div>
					 <div class="qrdetails" id="qrdetails">
						<i class='qrcodes' id='<bean:write name="staffList" property="teacherID"/>'></i>
					</div> 
					
					</div>

				
		
			</section>
				
			</logic:iterate>
 		</logic:present>
 		</div>
			</div>
		

</div>
</div>
<textarea id="printing-css-a4" style="display:none;">

.col-md-4 {width:30.5%;}
.col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9 {
    float: left;
}
.col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
    position: relative;
    min-height: 1px;
    padding-right: 10px;
    padding-left: 10px;
}

#logodiv{
width: 30px;
height: 30px;
position: absolute;
z-index: 99999;
 background: transparent;
 top:5px;
}
#main-div div#parentDetail,#emergencyNo{
    position: relative !important;
    top: 0px;
}
.col-md-4.section{
margin-bottom:20px;
}
body {-webkit-print-color-adjust: exact;margin:0;padding:0;}


</textarea>

<textarea id="printing-css-a3" style="display:none;">
.col-md-4 {
     width: 19.6%;
	margin-right:2px;


   


}
#logodiv{
width: 30px;
height: 30px;
position: absolute;
z-index: 99999;
 background: transparent;
 top:5px;
}
#main-div div#parentDetail,#emergencyNo{
    position: relative !important;
    width: 100%;
    top: 0px;
}
.col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9 {
    float: left;
}
.col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
    position: relative;
    min-height: 1px;
   
}


body {-webkit-print-color-adjust: exact;margin:0 auto;padding:0;}


</textarea>

<textarea id="printing-css-card" style="display:none;">
.col-md-4 {width:100%;}
.col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9 {
    float: left;
}
.col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
    position: relative;
    min-height: 1px;
    padding-right: 10px;
    padding-left: 10px;
}

#logodiv{
width: 30px;
height: 30px;
position: absolute;
z-index: 99999;
 background: transparent;
 top:5px;
}
#main-div div#parentDetail,#emergencyNo{
    position: relative !important;
    top: 0px;
}
body {-webkit-print-color-adjust: exact;margin:0;padding:0;}
.col-md-4.section{
margin:0 0 6px 0;
padding-left:0px;
}
@media print {
    .section {page-break-after: always;}
}
</textarea>






</body>
</html>