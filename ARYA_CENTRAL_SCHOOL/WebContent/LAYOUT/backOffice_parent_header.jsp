<!DOCTYPE html>
<html lang="en">
<head>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<link rel="apple-touch-icon" sizes="57x57"
	href="images/fevicon/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60"
	href="images/fevicon/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="images/fevicon/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76"
	href="images/fevicon/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="images/fevicon/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120"
	href="images/fevicon/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144"
	href="images/fevicon/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152"
	href="images/fevicon/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180"
	href="images/fevicon/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"
	href="images/fevicon/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="images/fevicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="images/fevicon/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="images/fevicon/favicon-16x16.png">
<link rel="manifest" href="images/fevicon/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">

<title>Campus Pro</title>





<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script src="JQUERY/js/jquery-1.8.3.js"></script>
<script src="JS/backOffice/menuHighlight.js"></script>
<script src="JS/newUI/bootstrap.min.js"></script>
<script src="JS/login/Admin.js"></script>



<script src="JS/login/bootstrap.min.js"></script>
<script src="JS/login/bootstrap-hover-dropdown.min.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<script src="JS/newUI/jquery-1.9.1.min.js"></script>
<script src="JS/newUI/bootstrap.min.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$("#dropdown").click(function() {
			$("#hbox").slideToggle("slow");

		});

	});
	function appendAccYear() {
		var currentYear = $("#current_academicYear").val().trim();
		$("#accYear option[value=" + currentYear + "]")
				.attr('selected', 'true');
	}

	function setAcademicYear() {
		$(window.location).attr('href',
				'login.html?method=login&accYear=' + $("#accYear").val());
	}
	 function appendAccYear() {
			var currentYear = $("#globalAcademicYear").val().trim();
			$("#globalAcademicYear option[value=" + currentYear + "]")
					.attr('selected', 'true');
		} 

		function setAcademicYear() {
			$(window.location).attr('href','login.html?method=login&accYear=' + $("#globalAcademicYear").val()+'&school='+$("#school").val());
		}
		function setSchool(){
			$(window.location).attr('href','login.html?method=login&accYear=' + $("#globalAcademicYear").val()+'&school='+$("#school").val());
			}
</script>

<!-- for top icon -->

<script type="text/javascript">
	function clickHandler() {
		//$('#hide').toggle();
		// $('#show').toggle();
		$('.leftmenu').toggle('slide');
	}

	$(document).ready(function() {
		//$('#div1').css({"width":"83.33%"});

		$('#studenttxt').on('click', clickHandler);
		$('#studenttxt').click(function() {

			//if($(this).Class)
			//alert($("#div1").attr('class'));
			if ($("#div1").attr('class') == "col-md-10") {
				$('#div1').removeClass("col-md-10");
				$('#div1').addClass("col-md-12");
				//$("col-md-12").css({"padding-right":"13px"});

			} else {
				$('#div1').removeClass("col-md-12");
				$('#div1').addClass("col-md-10");
			}

		});
	});
</script>

<script>
	$(document).ready(function() {

		//Check to see if the window is top if not then display button
		$(window).scroll(function() {
			if ($(this).scrollTop() > 10) {
				$('.scrollToTop').fadeIn();
			} else {
				$('.scrollToTop').fadeOut();
			}
		});

		//Click event to scroll to top
		$('.scrollToTop').click(function() {
			$('html, body').animate({
				scrollTop : 0
			}, 800);
			return false;
		});

	});
</script>


<style>
.scrollToTop {
	width: 100px;
	height: 130px;
	padding: 10px;
	text-align: center;
	font-weight: bold;
	color: #444;
	text-decoration: none;
	position: fixed;
	top: 590px;
	right: -24px;
	display: none;
	z-index: 999;
	/* 	background: url('arrow_up.png') no-repeat 0px 20px; */
}

.scrollToTop:hover {
	text-decoration: none;
}
</style>



<style type="text/css">
.hoverColor:HOVER {
	text-decoration: underline;
	color: blue;
	cursor: pointer;
}
</style>
</head>
<body onload="appendAccYear()">
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">

		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><img src="images/logo.png"
				style="margin: -6px -3px"></a>


		</div>
		<div class="row">
		<div class="container col-md-7">

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar">

									<li><a href="parentMenu.html?method=studentList"
										id="module_2">Student</a></li>

									<li><a href="parentMenu.html?method=onlinefeePayment"
										id="module_9">Fee Payment</a></li>

									
				</ul>
			</div>

		</div>

		<div class="col-md-3 " id="dropdown"
			style="font-family: Roboto light; color: white; font-size: 13pt; cursor: pointer;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img
				src="images/rightline.png" style="margin-top: 5px;"><img
				src="images/person1.png" width="20px" height="20px">&nbsp;&nbsp;<span id="username" style="font-size:14px;"><logic:present name="TEACHERNAMEDETAILS" scope="session"><bean:write name="TEACHERNAMEDETAILS" scope="session" /></logic:present></span>
		</div>
</div>
	

	<!-- <div id="hbox" class="col-md-2" style="" >
		
		<div class="gb_Wa"></div>
		<div class="gb_Va"></div>
		<p>&nbsp;</p>
		    <img src="img/11.jpg" style="width:100%"/> 
		   <p style="margin:0;">Name:</p>
		   <p style="margin:0;">Log In time:</p>
		   <p style="margin:0;">Date;</p><br/>
		   
	</div> -->

	<!-- Page Content -->
	<div class="row" style="border-bottom: 1px solid #c0c0c0; background-color: #f9f9f9;">
		<div class="col-md-2" style="padding-right: 0;left:7px;">
			<div class="input-group">

			<input type="hidden" id="hschoolLocation" value="<logic:present name="current_schoolLocation" scope="session"><bean:write name="current_schoolLocation"  scope="session"/></logic:present>"/>
				<input type="hidden" id="hacademicyaer" value="<logic:present name="current_academicYear" scope="session"><bean:write name="current_academicYear"  scope="session"/></logic:present>"/>
				<input type="hidden" id="huserType" value="<logic:present name="user" scope="session"><bean:write name="user"  scope="session"/></logic:present>"/>
				<input type="hidden" id="hPriveliges" value="<logic:present name="Priveliges" scope="session"><bean:write name="Priveliges" scope="session" /></logic:present>"/>

				<div class="form-group">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: center;line-height: 30px;font-size: 12px;">Year </label>
					<div class="col-xs-8" >
						<select name="academicYear" onkeypress="HideError()"
							id="globalAcademicYear" class="form-control" style="min-width:113px; max-height:30px; padding:0px 10px; -webkit-appearance:none;-moz-appearance:none;border:none;background:transparent;" onchange="setAcademicYear()" >
						</select>
					</div>
				</div>

			</div>
		</div>
		<div class="col-md-8"
			style="font-family: Roboto light; font-size: 13pt;">
			<span class="glyphicon glyphicon-menu-hamburger" id="studenttxt"
				style="font-size: 20px; line-height: 34px; color: #989898;"></span>&nbsp;&nbsp;&nbsp;
			<a href="parentMenu.html?method=Home"><span class="glyphicon glyphicon-home" style="font-size: 20px; line-height: 34px; color: #989898;"></span></a>&nbsp;&nbsp;
			<logic:present name="moduleName" scope="request"><bean:write name="moduleName" scope="request" /></logic:present>
			<logic:present name="highlightName" scope="request"><input id="highlightName" type="hidden"value='<bean:write name="highlightName" scope="request"/>' /></logic:present>
		</div>

		<div id="hbox" class="col-md-2"
			style="border: 1px solid #D7D8D6; border-radius: 5px;; z-index: 1000; position: absolute; float: right; margin-top: 14px; margin: 7px 81%; background: #fff; width: 18%;">
			<div class="gb_Wa"></div>
			<div class="gb_Va"></div>
			<p>&nbsp;</p>
			
			
			<p style="margin-bottom: 5%; font-size: 12px;" class="hoverColor"
				data-toggle="modal" data-target="#change_password">Change
				Password</p>
			<p style="margin-bottom: 5%; font-size: 12px;" data-toggle="modal"
				data-target="#logout" class="hoverColor">Logout</p>
			<br />
		</div>
		</div>
		</nav>
		<div class="modal fade" id="change_password">
			<div class="modal-dialog">
				<!-- Modal Content -->
				<div class="modal-content" style="margin-left: 15%; width: 50%;">
					<!-- Close Button -->
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<!-- Heading -->
					<h3>Change Password</h3>
					<!-- Form -->
					<form>
						<div class="errormessagediv1" style="display: none;">
							<div class="message-item1">
								<!-- Warning -->
								<a href="#" class="msg-warning1 bg-msg-warning1"
									style="width: 100%; text-align: center;"><span
									class="validateTips"></span></a>
							</div>
						</div>

						<div class="successmessagediv" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"
									style="width: 100%; text-align: center;"><span
									id="sucessmessage"> </span></a>
							</div>
						</div>

						<!-- Password -->
						<div class="form-group">
							<label>Old Password</label> <input type="password"
								id="oldpassword" class="form-control" placeholder="Password">
						</div>
						<!-- Password -->
						<div class="form-group">
							<label>New Password</label> <input type="password"
								id="newPassword" class="form-control" placeholder="Password">
						</div>
						<!-- Confirm Password-->
						<div class="form-group">
							<label>Confirm Password</label> <input type="password"
								id="confirmPassword" class="form-control"
								placeholder="Confirm Password">
						</div>
						<!-- Submit Button -->
						<div style="text-align: center;">
							<button type="button" class="btn btn-info"
								onclick="changePassword()">Submit</button>
						</div>
						<br />
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="logout">
			<div class="modal-dialog">
				<!-- Modal Content -->
				<div class="modal-content"
					style="margin-left: 25%; width: 50%; margin-top: 20%;">
					<!-- Close Button -->
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<br />
					<center>
						<p>Are You sure, You want to Logout?</p>
					</center>
					<br />
					<button type="button" class="btn btn-info" onclick="gotoLogout()"
						data-dismiss="modal" style="margin-left: 40%;">OK</button>
					<br /> <br />
				</div>
			</div>
		</div>

	

	<div class="pull-right" id="back-top ">
		<a href="#firstrow" class="scrollToTop"><img src="images/top.png"></a>
	</div>



</body>
</html>