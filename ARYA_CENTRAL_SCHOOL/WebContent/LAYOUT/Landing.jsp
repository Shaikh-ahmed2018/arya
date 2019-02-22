<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>e-campusPro</title>
<link rel="stylesheet" type="text/css" href="CSS/newUI/landing.css" />
<link type="text/css" rel="stylesheet" href="CSS/newUI/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="CSS/newUI/bootstrap.css">
</head>




<body>
	<header>
    	<div class="row">
        	<div class="col-lg-2 col-md-2">
            	<div id="main-logo">
                	<a href="landingPage.html">
                    	<img src="images/logo.png" alt="logo" />
                    </a>
                </div>
            </div>
            <div class="col-lg-8 col-md-8">
            	<nav id="landing-nav">
                	<ul class="main-nav">
                    	<li><a href="#">Home</a></li>
                        <li><a href="#">About Us</a></li>
                        <li><a href="#">Infrastructure</a></li>
                        <li><a href="#">Career</a></li>
                        <li><a href="#">Events</a></li>
                        <li><a href="#">Contact Us</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-2 col-md-2">
            	<div id="sign-in">
                <ul class="main-nav">
                    	<li><a href="#">Sign In</a></li>
                    </ul>
                </div>
            </div>
        </div>
	</header>
    <div id="main">
    	<div class="container">
        	<div class="row">
            	<div class="col-lg-4 col-md-4 col-sm-6">
                   <div class="left-img">
                	<img src="images/image1.jpg" alt="Student Image" />
                   </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6">
                   <div class="flow-img">
                	<img src="images/flow.jpg" alt="Student Image" />
                   </div> 
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6">
                <div class="col-md-4 col-md-offset-4" style="padding:0;border: 1px solid #07aab9;border-radius: 5px 5px 0px 0px;">
		<span class="up3" style="
    margin: 0;
    float: left;
    width: 35%;">&nbsp;</span><h2>Sign In</h2><span class="up4" style="
    margin: 0;
    float: right;
    width: 35%;">&nbsp;</span>
	</div>
	
		<div class="col-md-4 col-md-offset-4" style="border-right: 1px solid #07aab9;border-left: 1px solid #07aab9;border-radius: 0;background: #fff;">
			<form action="login.html?method=login" method="post" method="post">
			 <hr> 
			
				<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
					<input type="text" class="form-control" name="username" id="user_name" placeholder="User Name" >
				</div><br/>
				
				<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
					<input type="password" class="form-control" name="password"  id="user_password" placeholder="Password" >
				</div>
					<br/>
					<button class="btn btn-lg btn-primary btn-block" type="submit" onclick="return validateFields()" style="border-radius: 5px;margin-top: 10px;font-family: Roboto Medium;"><i class="fa fa-sign-in"></i> Login</button><br/>
					<center><span style="padding-left: 10%; color: red;" class="loginTips"></span></center>
				</form>
		</div>
		<div class="col-md-4 col-md-offset-4" style="padding:0;background: #fff;    border-radius: 0 0 5px 5px;    border-right: 1px solid #07aab9;    border-left: 1px solid #07aab9;    border-bottom: 1px solid #07aab9;">
			
		</div>
			
                </div>
            </div>
            <div class="row">
            	<div class="col-lg-4 col-md-4 col-sm-4">
                	<div class="secretary-desk">
                    	<h1>From Secretary's Desk</h1>
                        <p>
                        "You educate a man; you educate a man. You educate a woman; you 
						educate a generation". 
						<b>- Brigham Young</b>
                        </p>
                        <p>
                        "Education is the manifestation of the perfection already in man. 
						All Knowledge, secular or spiritual is in the human mind. Man discovers it within himself, which is 
                        preexisting through eternity".
						<b>- Swami Vivekananda</b> 							
                        </p>
                    </div>
                </div>
            	<div class="col-lg-4 col-md-4 col-sm-4">
                	<div class="contact-us">
                    	<h1>About Us</h1>
                        	<ul class="contact-ul">
                            	 <li class="first">
                                 	Centris Infotech Services Pvt Ltd.
                                 </li>
                                 <li class="second">
                                 	#13 Amar Jyoti Layout
									Sanjay Nagar,Bangalore
                                 </li>
                                 <li class="third">
                                 	080-41160995/91-9844268099 
                                 </li>
                                 <li class="fourth">
                                 	info@centrisinfotech.com
                                 </li>
                            </ul>
                    </div>
                </div>
            	<div class="col-lg-4 col-md-4 col-sm-4">
                	<div class="our-campus">
                    	<h1>Our Campus</h1>
                        <div class="slider">
                        	<img src="images/our-campus.jpg" alt="our campus" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer>
    	<div class="container-fluid">
        	<div class="row">
            	<div class="col-lg-6 col-md-6 col-sm-6">
                	<div class="copyright">
                    	Copyright © 2015 Centris Infotech Services - All Rights Reserved
                    </div>
                </div>
            	<div class="col-lg-6 col-md-6 col-sm-6">
                	<div class="social-icon">
                    	<div class="row">
                        	<div class="col-md-6"></div>
                        	<div class="col-md-6">
                            	<ul class="footer-ul">
                                	<li><a href="#"><img src="images/facebook.png" alt="facebook" /></a></li>
                                    <li><a href="#"><img src="images/twitter.png" alt="twitter" /></a></li>
                                    <li><a href="#"><img src="images/linkedin.png" alt="linkedin" /></a></li>
                                    <li><a href="#"><img src="images/google-plus.png" alt="google plus" /></a></li>
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
