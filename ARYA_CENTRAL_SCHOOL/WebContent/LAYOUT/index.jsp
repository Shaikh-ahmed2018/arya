<!DOCTYPE html>
<html>
<head>

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
<link rel="stylesheet" type="text/css" href="CSS/newUI/landing.css" />
<link type="text/css" rel="stylesheet"
	href="CSS/newUI/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="CSS/newUI/bootstrap.css">
<link rel="stylesheet" type="text/css" href="CSS/newUI/landing.css" />
<link rel="stylesheet" type="text/css"
	href="CSS/newUI/font-awesome/css/guest.css" />
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">

<link type="text/css" rel="stylesheet" href="CSS/newUI/bootstrap.min.css">
<link rel="stylesheet" href="CSS/login/font-awesome.min.css">

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/jquery-1.9.1.js"></script>
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
<script>
var publicIp = false;
function getip(ip) {
  publicIp = ip.ip;
  public_ip.textContent = publicIp;
  setTimeout(function(){extract_ipv4("")}, 1000);
}
</script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JS/login/login.js"></script>
<script src="JS/login/SystemDetail.js"></script>
<script>
$(document).ready(function() {
$('input,textarea').bind('cut copy paste', function (e) {
    e.preventDefault(); //disable cut,copy,paste
});
});
function noBack() { window.history.forward(); }
</script>
<style>
a{
display: inline-block;
}
h2 {
	background: #07aab9;
	color: #fff;
	font-family: Roboto Regular;
	margin: 0;
	padding: 5px 35%;
	width: 100%;
	text-align: center
}

.up3:after {
	background: none repeat scroll 0 0 rgb(255, 255, 255);
	bottom: 0;
	content: "";
	display: block;
	height: 1px;
	margin-top: 4px;
	width: 100%;
	float: left;
	z-index: 9999;
}

.up4:after {
	background: none repeat scroll 0 0 rgb(255, 255, 255);
	bottom: 0;
	content: "";
	display: block;
	height: 1px;
	margin-top: -40px;
	width: 100%;
	z-index: 9999;
}

.btn-primary {
	background-color: #07AAB9;
	border-color: #07AAB9;
}

.btn-primary.active,.btn-primary.focus,.btn-primary:active,.btn-primary:focus,.btn-primary:hover,.open>.dropdown-toggle.btn-primary
	{
	background-color: #15848E;
	border-color: #15848E;
}

.successmessagediv {
	position: absolute;
	left: 40%;
	margin: 0 auto;
	color: #fff;
	font-size: 14px;
	font-weight: bold;
	top: 30%;
	z-index: 99999;
	background: #07aab9;
	padding:20px;
	}
	footer{
	margin-top: 0px;
	}
	
	
	</style>
		<title>eCampusPro</title>
	</head>
	


<body>

	<header>
    	<div class="row">
        	<div class="col-lg-2 col-md-2">
            	<div id="main-logo">
                	<a href="/ARYA_CENTRAL_SCHOOL">
                    	<img src="images/logo.png" alt="logo" />
                    </a>
                </div>
            </div>
            
            
        </div>
	</header>
	
	<div class="successmessagediv">
				
					<span> 
					</span>
					</div>
		
    <div id="main">
    <div id="overlay"></div>
    	<div class="container">
        	<div class="row"> 
				<div class="col-lg-4 col-md-4 col-sm-4 login-section">
					<div class="col-md-12"
						style=" border: 1px solid #07aab9; border-radius: 5px 5px 0px 0px;">
						<h2 class="bar">Sign In</h2>
					</div>

					<div class="col-md-12 "
						style="border-right: 1px solid #07aab9; border-left: 1px solid #07aab9; border-radius: 0; background: #fff;min-height:265px; ">
						<form action="login.html?method=login" method="post" method="post">
							<hr>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-user"></span></span> <input type="text"
									class="form-control" name="username" id="user_name"
									placeholder="User Name">
							</div>
							<br />
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-lock"></span></span> <input type="password"
									class="form-control" name="password" id="user_password"
									placeholder="Password">
							</div>
							<br />
							<span id="public_ip" style="display: none;"></span>
							<input type="hidden" id="internal_ip" name="internal_ip" value="" />
						
							<button class="btn btn-lg btn-primary btn-block" type="submit"
								onclick="return validateFields()"
								style="border-radius: 5px; margin-top: 10px; font-family: Roboto Medium;">
								<i class="fa fa-sign-in"></i> Login
							</button>
							<br />  <!-- <a class=""
								href="registration.html?method=NewRegistrationEntryPage">Temporary
								Registration</a> -->
							<!-- 	<a class="" href="#">Forgot
								Password ?</a> -->

							<center>
								<span style="padding-left: 10%; color: red;" class="loginTips"></span>
							</center>
						</form>
					</div>
					

				</div>
				

			</div>
			
		</div>
	</div>


	
	

	<footer>
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6">

					<div class="copyright">Copyright © 2015 Centris Infotech
						Services - All Rights Reserved</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="social-icon">
						<div class="row">
							<div class="col-md-6"></div>
							<div class="col-md-6">
								<ul class="footer-ul">
									<li><a href="#"><img src="images/facebook.png"
											alt="facebook" /></a></li>
									<li><a href="#"><img src="images/twitter.png"
											alt="twitter" /></a></li>
									<li><a href="#"><img src="images/linkedin.png"
											alt="linkedin" /></a></li>
									<li><a href="#"><img src="images/google-plus.png"
											alt="google plus" /></a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</footer>

</body>
</html>
