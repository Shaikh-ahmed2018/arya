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
<script type="text/javascript" src="JS/backOffice/Student/studentIDPrint.js"></script>


<script type="text/javascript" src="JS/common.js"></script>
<link href="CSS/IdCard/IdCard.css" rel="stylesheet" type="text/css" />
<link href='CSS/IdCard/<logic:present name="template" scope="request" ><bean:write name="template" /></logic:present>.css' rel="stylesheet" type="text/css" />
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
#logodiv{
width: 30px;
height: 30px;
position: absolute;

top:5px;
 background: transparent;
}
#main-div div#parentDetail,#emergencyNo{
    position: relative !important;
   
    top: 0px;
}
.main-div.idBack{
display: none;
}
.frnt-bck{
position: relative;
top: -10px;
left: 10px;
}
#bprint{
display: none;}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div-main">
	<div class="searchWrap">
		<div class="clearfix">
		<div class="col-md-7">
		<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Print Student ID Card - Multiple</span>
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
					<h3 class="panel-title" style="color: #767676; vertical-align: text-top;"><span class="glyphicon glyphicon-menu-hamburger"></span>
					&nbsp;&nbsp;Student Details
					</h3></a>
					
					<span class="buttons frnt-bck" id="idfront" >Front</span>
		<span class="buttons frnt-bck" id="idback">Back</span>	
				<div class="navbar-right">
					
					<span id="print" class="buttons">Print</span>
					<span id="bprint" class="buttons">Print</span>
					
				</div>
			</div>
			


	

 		<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
 		<div class="clearfix" id="studentpreview">
 		<logic:present name="StudentList" scope="request">
 			<logic:iterate id="StudentList" name="StudentList">
 		
				
				
			<section class="col-md-4 section">
			<div class="main-div idFront" id="main-div" >
			 <img src='images/IdCard/<logic:present name="template" scope="request" ><bean:write name="template" /></logic:present>.jpg' width="100%" height="100%" id="layoutImage" />
			  <header>
			  <div class="logodiv" id="logodiv">
			 	 <img src="./images/school-logo.png" width="100%" height="100%"  />
				</div>
				<div class="schoolName" id="schoolName"><span id="schoolNameChange"><bean:write name="StudentList" property="schoolName"/></span></div>
				<div class="branch" id="branch"><span id="branchName"><bean:write name="StudentList" property="location_address"/></span></div>
				<div class="phone" id="phone"><span id="teleName">Tel.<bean:write name="StudentList" property="location_phone"/></span></div>
			</header>
				<div class="namediv" id="namediv">
					<span class="name label" id="name">Name : </span><span id="nametext"><bean:write name="StudentList" property="stuName"/></span>
				</div>


				<div class="photo" id="photo">
					<img src='<bean:write name="StudentList" property="images"/>' alt="image" width="100%" height="100%" />
				</div>


				
					<div class="studentdetails" id="studentdetails">
						<div class="classDivision" id="classDivision">
							<span class="label" id="classlebel">Class/Div<span class="column">:</span></span><span id="classtext"><bean:write name="StudentList" property="className"/></span>
						</div>
						<div class="admission" id="admission">
							<span class="label" id="admNo">Adm No.<span class="column">:</span></span><span id="admNotext"><bean:write name="StudentList" property="admissionno"/></span>
						</div>
						<div class="house" id="house">
							<span class="label" id="houNo">House<span class="column">:</span></span><span id="houNotext"><bean:write name="StudentList" property="houseName"/></span>
						</div>
						<div class="valid" id="valid">
							<span class="label" id="validUpto">Valid upto<span class="column">:</span></span><span id="validUptotext"><bean:write name="StudentList" property="validity"/></span>
						</div>
					</div>


				<div class="dob" id="dob"><span class="label" id="doblabel">D.O.B<span class="column">:</span></span><span id="dobtext"><bean:write name="StudentList" property="dob"/></span></div>
					<div class="bgroup" id="bgroup"><span class="label" id="bgrouplabel">B Group<span class="column">:</span></span><span id="bgrouptext"><bean:write name="StudentList" property="bgroup"/></span></div>

					<div class="addressdetails" id="address">
						<span class="addresss" id="addresslebel">Address :</span><div id="addresstext"><bean:write name="StudentList" property="adress"/></div>
					</div>


					<div class="phonedetails" id="phonedetails">
						<span class="phones" id="phonelebel">Ph:</span><span id="phonetext"><bean:write name="StudentList" property="phone"/>,<div style="padding-left: 20px;margin-top: -4px;"><bean:write name="StudentList" property="secodaryPhone"/></div></span>
					</div>
					<div class="qrdetails" id="qrdetails">
						<i class='qrcodes' id='<bean:write name="StudentList" property="admissionno"/>'></i>
					</div>
					<div class="parents" id="parents">
						<span class="label" id="patentlabel">Parents:</span><div class="parentDetail" id="parentDetail"><span  id="faterName" style="display:block;"><bean:write name="StudentList" property="fatherName"/></span><span style="display:block;"><span  id="motherName"> <bean:write name="StudentList" property="motherName"/></span></span></div>
						<div class="emerencyNodiv" id="emerencyNodiv"><span class="label" id="emergency">Emergency No:</span><span id="emergencyNo"><bean:write name="StudentList" property="emergencyNo"/></span></div>
					</div>
				
			</div>
			
			
			
			
			<div class="main-div idBack"  >
			 <img src='images/IdCard/<logic:present name="template" scope="request" ><bean:write name="template" /></logic:present>_bck.jpg' width="100%" height="100%" id="layoutImagebck" />
			
				


				
				
						<div class="classDivision-bck" id="classDivision-bck">
							<span class="label" id="classlebel-bck">Class/Div<span class="column">:</span></span><span id="classtext-bck"><bean:write name="StudentList" property="className"/></span>
						</div>
						<div class="admission-bck" id="admission-bck">
							<span class="label" id="admNo-bck">Adm No.<span class="column">:</span></span><span id="admNotext-bck"><bean:write name="StudentList" property="admissionno"/></span>
						</div>
						<div class="house-bck" id="house-bck">
							<span class="label" id="houNo-bck">House<span class="column">:</span></span><span id="houNotext-bck"><bean:write name="StudentList" property="houseName"/></span>
						</div>
						<div class="valid-bck" id="valid-bck">
							<span class="label" id="validUpto-bck">Valid upto<span class="column">:</span></span><span id="validUptotext-bck"><bean:write name="StudentList" property="validity"/></span>
						</div>
				



					<div class="addressdetails-bck" id="address-bck">
						<span class="addresss-bck" id="addresslebel-bck">Address :</span><div id="addresstext"><bean:write name="StudentList" property="adress"/></div>
					</div>


					<div class="phonedetails-bck" id="phonedetails-bck">
						<span class="phones-bck" id="phonelebel-bck">Ph:</span><span id="phonetext-bck"><bean:write name="StudentList" property="phone"/>,<div style="padding-left: 20px;margin-top: -4px;"><bean:write name="StudentList" property="secodaryPhone"/></div></span>
					</div>
					<div class="qrdetails-bck" id="qrdetails-bck">
						<i class='qrcodes-bck' id='<bean:write name="StudentList" property="admissionno"/>_bck'></i>
					</div>
					<div class="parents-bck" id="parents-bck">
						<span class="label" id="patentlabel-bck">Parents:</span><div class="parentDetail-bck" id="parentDetail-bck"><span  id="faterName-bck" style="display:block;"><bean:write name="StudentList" property="fatherName"/></span><span style="display:block;"><span  id="motherName-bck"> <bean:write name="StudentList" property="motherName"/></span></span></div>
					
					</div>
					<div class="emerencyNodiv-bck" id="emerencyNodiv-bck"><span class="label" id="emergency-bck">Emergency No:</span><span id="emergencyNo-bck"><bean:write name="StudentList" property="emergencyNo"/></span></div>
			</div>
			
			</section>
				
			</logic:iterate>
 		</logic:present>
 		</div>
			</div>
		

</div>
</div>
<textarea id="printing-css-a4" style="display:none;">

.col-md-4 {width:32.5%;}
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
.main-div.idBack{
display: none;
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
.main-div.idBack{
display: none;
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
.main-div.idBack{
display: none;
}
@media print {
    .section {page-break-after: always;}
}
</textarea>



<textarea id="bprinting-css-a4" style="display:none;">

.col-md-4 {width:32.5%;}
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
.main-div.idFront{
display: none;
}
body {-webkit-print-color-adjust: exact;margin:0;padding:0;}


</textarea>

<textarea id="bprinting-css-a3" style="display:none;">
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

.main-div.idFront{
display: none;
}
body {-webkit-print-color-adjust: exact;margin:0 auto;padding:0;}


</textarea>

<textarea id="bprinting-css-card" style="display:none;">
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
.main-div.idFront{
display: none;
}
@media print {
    .section {page-break-after: always;}
}
</textarea>



</body>
</html>
