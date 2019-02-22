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
<link href="CSS/IdCard.css" rel="stylesheet" type="text/css">
<link href="CSS/BusIdCard.css" rel="stylesheet" type="text/css">
<link href="CSS/spectrum.css" rel="stylesheet" type="text/css">
<style>
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
	border-radius: 8px;
	height: 90px;
	width: 90px;
	text-align: center;
	display: inline-block; 
	vertical-align:top;
	position: relative;
}
.studentdetails,.addressdetails{
	border: 1px solid #4C1919;
	border-radius: 8px;
	height: 65px;
	display: inline-block;
	padding-top: 1px;
	width:90px;
	vertical-align:top;
	position: relative;
}

.phonedetails{
	border: 1px solid #4C1919;
    border-radius: 8px;
    width: 90px;
    height: 20px;
    display: inline-block;
    position: relative;
    vertical-align: top;
}
.sectionb .label{
    color: #ffffff;
    padding: 0px 2px;
    border-radius: 15px 0 0 15px;
    display: inline-block;
    margin: 1px;
    line-height: 13px;
    text-align: left;
    font-size:13px;
    width: auto;
}
.label{
        color: #ffffff;
    padding: 0px 2px;
    border-radius: 15px 0 0 15px;
    display: inline-block;
    margin: 1px;
    line-height: 13px;
    width: 40px;
    text-align: left;
    font-size: 7px;
    font-weight: 100;
}
.main-div header{
background-color: #132c4a;
}
section{
margin-bottom:10px;
}
.photo img{
max-width: 140px;
max-height: 140px;
}
.photob img{
max-width:90px;
max-height:90px;
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
.bus-id-main-div .routeNo,.namedivb,.studentdetailsb{
display: inline-block;
width:200px;
}
.bus-id-head,.id-head{
cursor: pointer;
}
.namedivb,.studentdetailsb,.classDivisionb,.point,.routeNo{
position: relative;
}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/jquery-ui.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="JS/backOffice/Settings/StudentIdCreation.js"></script>
<script type="text/javascript" src="JS/spectrum.js"></script>
</head>

<body>


	<div class="col-md-10 col-md-offset-2" id="div-main">
		<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Design Id Card</span>
			</p>
			
			
			<div class="successmessagediv" style="display: none;">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span></span></a>
					</div>
				</div>
			
		<div class="panel panel-primary clearfix">
			<div class="panel-heading">
				<h3 class="panel-title id-head" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp; Design ID Card
					</h3>
				<div class="navbar-right">
						<span class="buttons" id="saveChanges">Save</span>
				</div>
			</div>	
		<section class="col-md-4 section">
			<div class="main-div" id="main-div" >
			  <header>
				<div class="schoolName" id="schoolName">ARYA CENTRAL SCHOOL</div>
				<div class="branch" id="branch">PATTOM,THIRUVANANTHAPURAM</div>
				<div class="phone" id="phone">Tel.1234567</div>
			</header>
				<div class="namediv" id="namediv">
					<span class="name" id="name">Name : </span><span></span>
				</div>


				<div class="photo" id="photo">
					<img src="" alt="image" />
				</div>


				
					<div class="studentdetails" id="studentdetails">
						<div class="classDivision" id="classDivision">
							<span class="label">Class/Div</span><span></span>
						</div>
						<div class="admission" id="admission">
							<span class="label">Adm. No.</span>
						</div>
						<div class="house" id="house">
							<span class="label">House</span>
						</div>
						<div class="valid" id="valid">
							<span class="label">Valid upto</span>
						</div>
					</div>



					<div class="addressdetails" id="address">
						<span class="addresss">Address :</span><span></span>
					</div>


					<div class="phonedetails" id="phonedetails">
						<span class="phones">Ph:</span><span></span>
					</div>

				
			</div>
		</section>
		</div>

	<div class="panel panel-primary clearfix">
			<div class="panel-heading">
				<h3 class="panel-title bus-id-head" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp; Design Bus ID Card
					</h3>
				<div class="navbar-right">
						<span class="buttons" id="busSaveChanges">Save</span>
						
				
				
					
				</div>
			</div>	
		<section class="col-md-4 sectionb">
			<div class="bus-id-main-div" id="bus-id-main-div" >
			  <header>
				<div class="schoolNameb" id="schoolNameb">ARYA CENTRAL SCHOOL</div>
			</header>
				<div class="photob" id="photob">
					<img src="" alt="image" />
				</div>
				<div class="namedivb" id="namedivb">
					<span class="nameb" id="nameb">Name :</span><span></span>
				</div>
				<div class="classDivisionb" id="classDivisionb">
					<span class="label">Class:</span><span></span>
				</div>
				<div class="point" id="point">
					<span class="label">Point Name:</span>
				</div>
				
				
					
						
					

					<div class="routeNo" id="routeNo">
					<div class="routetext">Route No</div>
						<span id="route" class="route">4A</span>
					</div>

				
			</div>
		</section>
		</div>


</div>
	
</body>
</html>