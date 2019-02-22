<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JS/FullCalendar.js"></script>
<script type="text/javascript" src="JS/backOffice/AdminHome.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
<link href="CSS/newUI/DashBoardCss/custome.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/fullcalendar.css" rel="stylesheet" type="text/css"/>


<style>
header{background-color: #cceefc; border-radius: 6px 6px 0px 0px;}
.panel-default {
    border-radius: 5px;
   
}

.panel-body {
    padding: 15px;
}
.dairy,.monthName,.year{
    font-size: 24px;
    font-family: roboto condensed;
    color: #56788e;
    }
.dairy h3{margin: 0;color: #333;font-weight: 600;}
#diaryTable{
width: 100%;
}
.dairyInput{width: 100%;}
.monthName,.monthNavigator{text-align: center;}
#prev,#next{
    padding: 3px;
    border-radius: 5px;
   margin-right: 3px;
   cursor: pointer;
   color:  #56788e;
}
.myDairy{
padding: 15px;

}
input[type="text"]{
background-color: transparent;
border:none;
border-bottom: 1px solid rgba(204, 204, 204, 0.20);
height: 20px !important;
font-size: 12px;
font-family: roboto;
 color: #555;

}
tbody{border:none;}
.allstudent th,.allstudent td{padding:0px;
border-color:rgba(204, 204, 204, 0.20) !important;
}
.allstudent  tr td:nth-child(2){
border-right:none;
}
.allstudent  tr td{

border-bottom: 1px solid rgba(204, 204, 204, 0.20);
font-size: 12px;
}
.hide-replaced.ws-inputreplace {
    display: none !important;
}
.fc-first.fc-last th {
    padding: 8px;
}
.exam{
margin-left: 10%;
padding-left: 22px;
 border-bottom: 1px solid rgba(204, 204, 204, 0.20);
 position: relative;
}
.exam::before{
	content: "";
    position: absolute;
    background: url("./images/examIcon.ico");
    width: 20px;
    height: 20px;
    background-size: 100%;
    background-repeat: no-repeat;
    left: 0;
}
.fc-grid .fc-day-number{
float: none;
}
.fc-border-separate th, .fc-border-separate td{border: none;
border-radius: 50%;
    text-align: center;
}

.dairyInput{border-bottom: 1px solid #ccc;}
.fc-header-title h2{font-size: 14px;padding: 10px 10px 0px;}
.fc-header-center h2{font-size: 18px;
    margin: -5px 0;
}
.fc-header tbody{border: none;}
.fc-header-left{width: 100%;}
.fc-state-default, .fc-state-default .fc-button-inner {
    border-style: solid;
    border-color: transparent;
    background: transparent;
    color: #000;
}
.fc-button-prev{position: absolute;
    left: 10px;
    top: 23px;
}
.fc-button-next{
position: absolute;
    right: 10px;
    top: 23px;
}
.fc-border-separate tr.fc-last th, .fc-border-separate tr.fc-last td{
font-weight: 300;
}
.exam:last-of-type{
border-bottom:none;
}
.ui-datepicker-inline.ui-datepicker.ui-widget.ui-widget-content.ui-helper-clearfix.ui-corner-all{
width: 100%;
height: 252px;
border: none;
}
.ui-datepicker .ui-datepicker-header{
background-color: #fff;
border: none;
}
.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default{
border: none;
background: none;
}
.ui-datepicker td a{text-align: center;}
.ui-datepicker-prev:hover{
 border-right:5px solid #000;   
}
.ui-datepicker-prev-hover{
border:none;
background:none;

border-right:5px solid #000; }
.ui-datepicker-prev{
 cursor:pointer;
 float:left;
 display:block;
 border-bottom:5px solid transparent;
 border-top:5px solid transparent;
 border-left:5px solid transparent;
 border-right:5px solid #000;
 text-indent:-9999px;
 height:0px;
 width:0px;
 margin-top:10px;
 margin-left:5px; 
}
.ui-datepicker-next{
 cursor:pointer;
 float:right;
 display:block;
 border-bottom:5px solid transparent;
 border-top:5px solid transparent;
 border-left:5px solid #000;
 border-right:5px solid transparent;
 text-indent:-9999px;
 height:0px;
 width:0px;
 margin-top:10px;
 margin-right:5px;   
}
.ui-state-hover{
border: none;
background: none;
background-image: none;
}
.ui-datepicker-next:hover{
 border-left:5px solid #002;   
 right: 2px;
}

.ui-datepicker .ui-datepicker-prev, .ui-datepicker .ui-datepicker-next{
width: auto;
height: auto;
}
.ui-state-hover, .ui-widget-content .ui-state-hover, .ui-widget-header .ui-state-hover, .ui-state-focus, .ui-widget-content .ui-state-focus, .ui-widget-header .ui-state-focus { border:none/*{borderColorHover}*/; background: #dadada/*{bgColorHover}*/ url(images/ui-bg_glass_75_dadada_1x400.png)/*{bgImgUrlHover}*/ 50%/*{bgHoverXPos}*/ 50%/*{bgHoverYPos}*/ repeat-x/*{bgHoverRepeat}*/; font-weight: normal/*{fwDefault}*/; color: #212121/*{fcHover}*/; }
.calendar{
position: fixed;
right: 35px;
width: 300px;
 box-shadow: 0px 0px 30px #ccc;
}
.calendar span{
font-family:roboto condensed;
color: #057781; 
}

</style>
</head>



<body>
<input type="hidden" id="startDate" value='<logic:present name="startDate" scope="request"><bean:write name="startDate" /></logic:present>' />
<input type="hidden" id="endDate" value='<logic:present name="endDate" scope="request"><bean:write name="endDate" /></logic:present>' />

<div class="errormessagediv" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning" style="text-align: center;"><span class="validateTips"></span></a>
							</div>
						</div>
	<div class="row" style="margin:0;">
		
	
		<div class="col-md-10 col-md-offset-2" style="padding: 0 10px;">
			<div class="col-md-12 centertxt">
				<div class="row">
					<div class="col-md-12">
						<h1>MY DAIRY</h1>
					</div>
				</div>
			<div class="row">
				<div class="col-md-8">
					<div class="myDairy">
						<header>
						<div class="row">
							<div class="col-md-3 col-sm-3">
								<div class="dairy"></div>
							</div>
							<div class="col-md-6 col-sm-6">
								<div class="monthName">
								</div>
							</div>
						<div class="col-md-1 col-sm-1"></div>
						<div class="col-md-2 col-sm-2">
							<div class="monthNavigator">
							<div class="year"></div>
							<div class="navigator"><span id="prev" class="glyphicon glyphicon-chevron-left"></span><span id="next" class="glyphicon glyphicon-chevron-right"></span></div>
							</div>
						</div>	
						</div>
						</header>
						<div class="dairyBody">
							<table class="allstudent" id="diaryTable">
								<thead>
									<tr><th>Day</th><th style="text-align:center;">Title</th></tr>
								</thead>
								<tbody>
								
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="calendar">
						<div id='calendar'></div>
						<div id='image'><img src="./images/Scenic11.PNG" width="100%" /></div>
					</div>
				</div>
			</div>

			</div>
		</div>
	</div>
</body>
</html>