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

<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<link href="CSS/IdCard/EventIdCard.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href='CSS/IdCard/<logic:present name="template" scope="request" ><bean:write name="template" /></logic:present>.css' rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="JS/backOffice/Events/eventIDPrint.js"></script>
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
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Print Student ID Card - Multiple</span>
			</p>
		</div>
		<div class="col-md-5">
		<div class="row">
			<div class="col-md-6"></div>
			<div class="col-md-6"><input type="radio" name="print" value="A3"/><span> A3 Extra </span><input type="radio" name="print" value="A4"/><span> A4 </span></div>
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
				<div class="navbar-right">
					
					<span id="print" class="buttons">Print</span>
					
					
				</div>
			</div>
			

 		<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
 		<div class="clearfix" id="studentpreview">
 		<logic:present name="list" scope="request">
 			<logic:iterate id="list" name="list">
 		
				
				
				<section class="col-md-4 section">
			<div class="main-div" id="main-div" >
			 <img src='images/IdCard/<logic:present name="template" scope="request" ><bean:write name="template" /></logic:present>.jpg' width="100%" height="100%" id="layoutImage" />
			 

				<div class="eventNameDive" id="eventNameDive">
					<span id="eventNameText"><bean:write name="list" property="eventName" /></span>
				</div>


				<div class="photo" id="photo">
					<img src='<bean:write name="list" property="imageUrl"/>' alt="image" width="100%" height="100%" />
				</div>
				
				<div class="nameDive" id="nameDive">
					<span id="nameText"><bean:write name="list" property="studentName"/></span>
				</div>
				
				<div class="classDivision" id="classDivision">
					<span id="classtext"><bean:write name="list" property="className" /></span>
				</div>
				<div class=programNamediv id="programNamediv">
					<span id="programnametext"><bean:write name="list" property="programmeName" /></span><span id="chestNotext">(<bean:write name="list" property="chestNumber" />)</span>
				</div>
				<div class="programDate" id="programDate">
					<span class="label" id="progDate">Program Date<span class="column">:</span></span><span id="progDateText"><bean:write name="list" property="prog_date" /></span>
				</div>
				<div class="stageDive" id="stageDive">
					<span class="label" id="stageLebel">Stage<span class="column">:</span></span><span id="validUptotext"><bean:write name="list" property="stageName" /></span>
				</div>
			 
			   <div class="makeUpTimeDiv" id="makeUpTimeDiv">
					<span class="label" id="makeUpTimeDivlabel">Makeup Report Time: </span> <span id="addresstext"><bean:write name="list" property="makeup" /></span>
				</div>
			   
			 	<div class=greenRoomReportTimeDiv id="greenRoomReportTimeDiv">
					<span class="label" id="greenRoomReportTimeDivlabel">Green Room Report Time: </span><span id="greenRoomReportTimeDivText"><bean:write name="list" property="backStage" /></span>
				</div>	
				<div class=backStageReportTimeDiv id="backStageReportTimeDiv">
					<span class="label" id="backStageReportTimelabel">Back Stage Report Time: </span><span id="backStageReportTimeText"><bean:write name="list" property="backStage" /></span>
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


body {-webkit-print-color-adjust: exact;margin:0;padding:0;}


</textarea>

<textarea id="printing-css-a3" style="display:none;">
.col-md-4 {
     width: 19.6%;
	margin-right:2px;

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

</body>
</html>
