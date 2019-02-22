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
<script type="text/javascript" src="JS/common.js"></script>
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
		var currentYear = $("#globalAcademicYear").val().trim();
		$("#globalAcademicYear option[value=" + currentYear + "]")
				.attr('selected', 'true');
	} 

	function setAcademicYear() {
		$(window.location).attr('href',
				'login.html?method=login&accYear=' + $("#globalAcademicYear").val());
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
			if ($("#div-main").attr('class') == "col-md-10") {
				$('#div-main').removeClass("col-md-10");
				$('#div-main').addClass("col-md-12");
				//$("col-md-12").css({"padding-right":"13px"});

			} else {
				$('#div-main').removeClass("col-md-12");
				$('#div-main').addClass("col-md-10");
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
			<a class="navbar-brand" href="#"><span id='label' style="text-align:center;color:white;font-size:26px"></span>
				</a>


		</div>
		
 		
 		

		
		
		<div class="container col-md-8">

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar">



				 


			


					

					

	
					<%-- <logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal name="daymap" property="key" value="EXMMODDIS">
								<logic:equal value="true" name="daymap" property="value">

									<li><a href="adminMenu.html?method=inflowslist"
										id="module_21">Accounts</a></li>

								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present> --%>


				


					


					

					


					<%--  <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal  name="daymap" property="key" value="BKUDIS">
										<logic:equal value="true" name="daymap" property="value">
					
											<li><a href="adminMenu.html?method=booksmasterlist" id="module_11">Library</a></li>
					
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	 --%>



					


					


					<!-- <li><a href="TestAction.html?method=inflowslist">Admission</a></li>	 -->
					<!-- <li><a href="teachermenuaction.html?method=leaveRequest" id="module_9">Leave</a></li> -->
					<!-- <li><a href="parentMenu.html?method=studentinformation" id="module_10">Parent</a></li> -->


				</ul>
			</div>

		</div>

		

	</nav>

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
	
	<br>
    <br>
    <br>
    <br>
	

	<p style="font-size:22px;text-align:center;color:red;">Your Application Already Submitted<p>	

</body>
</html>