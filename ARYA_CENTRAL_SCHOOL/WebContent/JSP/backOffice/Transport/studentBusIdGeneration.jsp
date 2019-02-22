<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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

<script type="text/javascript" src="JS/common.js"></script>


<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>

<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JS/backOffice/Admin/Latheef.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script src="JS/newUI/bootstrap.min.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Transport/studentBusIDGeneration.js"></script>
<link href="CSS/BusIdCard.css" rel="stylesheet" type="text/css">


<style>
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
</style>
<style>
.buttons{

vertical-align: 7px;

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

span.buttons{
margin-right:0px;
}
#sortable {
	list-style-type: none;
	margin: 0;
	padding: 0;
	width: 60%;
}

#sortable li {
	margin: 0 3px 3px 3px;
	padding-left: 1.5em;
}

#idcard {
	border-radius: 10px;
	border: 3px solid #BADA55;
}

#sortable li span {
	position: absolute;
	margin-left: -1.3em;
}


.main-div header{
background-color: #132c4a;
}
#idcard {
	border-radius: 10px;
	border: 3px solid #BADA55;
}
#sortable li span {
	position: absolute;
	margin-left: -1.3em;
}
.main-div {
	border: 5px solid #4C1919;
	width:195px;
	max-height:422px;
}
.schoolName {
	font-size: 14px;
	color: #B9BF50;
	position: relative;
}
.phones{
	border: 1px;
    border-radius: 10px;
    width: 25px;
    height: 20px;
    font-size: 10px;
    color: #ffffff;
    background-color: #2F3871;
    text-align: center;
    position: relative;
}
.branch {
	font-size: 10px;
    text-align: center;
    position: relative;
    color: #448719;
	
}
.phone {
	font-size: 10px;
    text-align: center;
    color: #fff;
    position: relative;
	
}
.section{
margin-bottom:10px;
}
.name {
	border: 1px solid #4C1919;
	border-radius: 5px;
	width:50px;
	font-size: 13px;
	color: #ffffff;
	background-color: #4C1919;
	text-align: center;
	position: relative;
	
}
.addresss{
	border: 1px ;
	border-radius: 10px;
	width:45px;
	height:20px;
	font-size: 8px;
	color: #ffffff;
	background-color: #2F3871 !important;
	text-align: center;
}

.namediv{
	 border: 1px solid #4C1919;
    border-radius: 5px;
    padding-top: 1px;
    height: 30px;
    margin-bottom: 1px;
    position: relative
}
.photo,.photob{
	border: 1px solid #4C1919;
	height: 90px;
	width: 90px;
	text-align: center;
	display: inline-block; 
	vertical-align:top;
	position: relative;
}

.label{
    color: #ffffff;
    background-color: #2F3871 !important;
    padding: 0px 2px;
    border-radius: 15px 0 0 15px;
    display: inline-block;
    margin: 1px;
    line-height: 13px;
    text-align: left;
    font-size:13px;
}
.main-div header{
background-color: #132c4a;
}
section{
margin-bottom:10px;
}
.photob img{
width: 90px;
height: 90px;
}
section div[id]{
cursor:move;
}
.bus-id-main-div{
border: 5px solid #4C1919;
	height:195px;
	width:300px;
}
.bus-id-main-div header{
border:1px solid #000;
text-align: center;
padding: 5px;
font-weight:600;
}
.bus-id-main-div .route{
font-size: 42px;
font-weight:600;
}
.bus-id-main-div .routeNo,.namedivb,.classDivisionb,.studentdetailsb{
display: inline-block;
width:201px;
line-height: 1;
}
.bus-id-head,.id-head{
cursor: pointer;
}
.namedivb,.studentdetailsb,.classDivisionb,.point,.routeNo{
position: relative;
}
#myDialog{
width:1111px !important;
}
.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons{
    width: 1111px !important;
    left: 0 !important;
    overflow-y:scroll;
    right: 0 !important;
    margin: auto;
    height: 700px;
 
}
#allstudent  tr td{
padding: 5px;
}
.section span{font-size: 13px;}

</style>

</head>

<div id="myDialog">

<div id="div">
		

		</div>
</div>
		
<textarea id="printing-css" style="display:none;">
	

.schoolNameb {
	font-size: 14px;
	color: #B9BF50;
	position: relative;
}

.phoneb {
	font-size: 10px;
    text-align: center;
    color: #fff;
    position: relative;
	
}
.sectionb{
margin-bottom:10px;
}
.name {
	border: 1px solid #4C1919;
	border-radius: 5px;
	width:50px;
	font-size: 13px;
	color: #ffffff;
	background-color: #4C1919;
	text-align: center;
	position: relative;
	
}

.photob{
	border: 1px solid #4C1919;
	height: 90px;
	width: 90px;
	text-align: center;
	display: inline-block; 
	vertical-align:top;
	position: relative;
}

.label{
    color: #ffffff;
    background-color: #2F3871 !important;
    padding: 0px 2px;
    border-radius: 15px 0 0 15px;
    display: inline-block;
    margin: 1px;
    line-height: 13px;
    text-align: left;
    font-size:13px;
}

section{
margin-bottom:10px;
}
.photob img{
width:90px;
height: 90px;
}
section div[id]{
cursor:move;
}
.bus-id-main-div{
border: 5px solid #4C1919;
	height:195px;
	width:300px;
}
.bus-id-main-div header{
border:1px solid #000;
text-align: center;
padding: 5px;
font-weight:600;
}
.bus-id-main-div .route{
font-size: 42px;
font-weight:600;
}
.bus-id-main-div .routeNo,.studentdetailsb{
display: inline-block;
width:201px;
}
.bus-id-head,.id-head{
cursor: pointer;
}
.namedivb,.studentdetailsb,.classDivisionb,.point,.routeNo{
position: relative;
}


.col-md-4 {
    width: 30.33333333%;
}

.col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9 {
    float: left;
}
.col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
    position: relative;
    min-height: 1px;
    padding-right: 10px;
    padding-left: 10px;
}
.section span{font-size: 13px;}
span1.name{font-size: 16px;}
.photob img{
    max-width: 90px;
    height: 90px;
}
@media print { 
.label{
    background-color: #2F3871 !important;
}
body {-webkit-print-color-adjust: exact;}
}

</textarea>


<iframe id="printing-frame" name="print_frame" src="about:blank" style="display:none;"></iframe>
	<div class="col-md-10 col-md-offset-2" id="div1">
		<div id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">ID Card Printing</span>
			</p>
		</div>

		<center>

			<logic:present name="success" scope="request">
				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
									name="success" scope="request" />
						</span></a>
					</div>
				</div>
			</logic:present>

			<logic:present name="error" scope="request">
				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
									name="error" scope="request" /></span></a>
					</div>
				</div>
			</logic:present>


		</center>


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676; vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;ID CARD
						Details
					</h3></a>
				<div class="navbar-right">
				
						<span class="buttons" id="print">Print</span>
				
				
					
				</div>
			</div>
			<form name="studentPramotionForm" action="" enctype="">
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">

						<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Academic Year </label>
								<div class="col-xs-7">
									<select name="acadamicyear" id="acadamicyear" class="form-control">
										<option value="">-----Select----------</option>
									</select>
								</div>
							</div>

							

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Class </label>
								<div class="col-xs-7">
									<select name="classname" id="classname0" class="form-control">
										<option value="">----Select----------</option>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6" id="txtstyle">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Stream</label>
								<div class="col-xs-7">
									<select name="category" id="category0" class="form-control">
										<option value="">----Select----------</option>

									</select>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Section </label>
								<div class="col-xs-7">
									<select name="section" id="section" class="form-control">
										<option value="">----Select----------</option>
									</select>
								</div>
							</div>

						</div>
					</div>

					<center>
						<input type="button" id="search" value="Search" class="buttons"
							style="width: 71px;font-size: 15px;border: none;"></input>
					</center>
					
					<center>

						<div class="errormessagediv" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips"></span></a>
							</div>
						</div>
					</center>
					<br />

					
						<table class="table" id="allstudent">
						</table>
					
					<br />
					


					
				</div>
		</div>
	</div>
</body>
</html>