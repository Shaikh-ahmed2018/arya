<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="manifest" href="images/fevicon/manifest.json">
<link rel="stylesheet" type="text/css" href="CSS/newUI/landing.css" />
<link type="text/css" rel="stylesheet"
	href="CSS/newUI/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="CSS/newUI/bootstrap.css">
<link rel="stylesheet" type="text/css" href="CSS/newUI/landing.css" />
<link rel="stylesheet" type="text/css"
	href="CSS/newUI/font-awesome/css/guest.css" />
<link href="CSS/newUI/custome.css" rel="stylesheet">

<link type="text/css" rel="stylesheet"
	href="CSS/newUI/bootstrap.min.css">

<link rel="stylesheet" href="CSS/login/font-awesome.min.css">
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JS/login/Admin.js"></script>


<script src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="CSS/newUI/landing.css" />
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>


<style>
.save2:hover {
	cursor: pointer;
}
</style>

<style>
html {
	margin: 0;
	padding: 0px;
	height: auto;
}

html body {
	height: 472px;
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
</style>
<title>eCampusPro</title>
</head>


<body>
	<div id="overlay"></div>
	<header>
		<div class="row">
			<div class="col-lg-2 col-md-2">
				<div id="main-logo">
					<a href="/ARYA_CENTRAL_SCHOOL"> <img src="images/logo.png"
						alt="logo" />
					</a>
				</div>
			</div>
			
			<div class="col-lg-8 col-md-8">
				<nav id="landing-nav">
					<ul class="main-nav">
						<li><a href="http://localhost:8080/ARYA_CENTRAL_SCHOOL/">Home</a></li>
						 <li><a class="sign_in" href="#">Enquiry</a></li> 
						<li><a href="#">Admission</a></li>
						<li><a href="#">Career</a></li>
						<li><a href="#">Events</a></li>
						<li><a href="#">Contact Us</a></li>


					</ul>
				</nav>
			</div>
			<div class="col-lg-2 col-md-2 top-sign">
				<div id="sign-in">
					<ul class="main-nav">
						<!-- <li><a class="sign_in" href="#">Sign In</a></li> -->

					</ul>
				</div>
			</div>
		</div>
	</header>


	<br />

	 <center>
		<logic:present name="successMessage" scope="request">

			<div class="successmessagediv">
				<div class="message-item">
					
					<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
								name="successMessage" scope="request" />
					</span></a>
				</div>
			</div>

		</logic:present>

		<logic:present name="errorMessage" scope="request">

			<div class="successmessagediv">
				<div class="message-item">
					
					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="errorMessage" scope="request" />
					</span></a>
				</div>

			</div>

		</logic:present>

		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

	</center> 
	


	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading" role="tab" id="headingOne">
				
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne"
						style="color: #767676; vertical-align: middle;"><h4 class="panel-title"><i
						class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;
						Registration
					</h4></a>
				
		<form action="login.html?method=login" method="post" ></form>

				<div class="navbar-right">

					<span class="save2" id="save"> <img src="images/save.png"
						class="save" style="margin-top: 8px;" data-toggle="tooltip"
						data-placement="bottom" title="Save" />
					</span>
				</div>

			</div>



		<%-- 	<input type="hidden" id="Myhiddenid"
				value="<logic:present name='myhiddenvariable'><bean:write name='myhiddenvariable'></bean:write></logic:present>"></input>    
 --%>

			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">

				<div class="panel-body">
					<div class="col-md-6"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
						<div class="form-group">
							<label for="parentfirstName" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">First Name
								<span style="color:red;">*</span> </label>
							<div class="col-xs-7">
								<input type="text" name="parentfirstName"
									onkeypress="HideError()" id="parentfirstName" maxlength="25"
									class="form-control" value='' />



							</div>
						</div>
						<br />
						<div class="form-group">
							<label for="mobile_number" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Parents Name
								<span style="color:red;">*</span> </label>
							<div class="col-xs-7">
								<input type="text" class="form-control" name="mobile_number"
									id="mobile_number" onkeypress="HideError()" maxlength="10"
									value='' />
							</div>
						</div>
						<br />

						<div class="form-group">
							<label for="parentEmailId" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Email Id<span style="color:red;">*</span> </label>
							<div class="col-xs-7">
								<input type="text" class="form-control" name="parentEmailId"
									onkeypress="HideError()" id="parentEmailId" maxlength="100"
									value='' />
							</div>
						</div>
						<br />
						<div class="form-group">
							<label for="address" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Address <span style="color:red;">*</span></label>
							<div class="col-xs-7">
								<textarea name="address" id="address" onkeypress="HideError()"
									class="form-control"></textarea>
							</div>
						</div>

						<br /> <br />
<!-- 						<div class="form-group"> -->
<!-- 							<label for="inputPassword" class="control-label col-xs-4" -->
<!-- 								style="text-align: right; line-height: 35px;">Password <span style="color:red;">*</span></label> -->
<!-- 							<div class="col-xs-7"> -->
<!-- 								<input type="password" class="form-control" name="password" -->
<!-- 									onkeypress="HideError()" id="password" maxlength="50" /> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<br /> <br /> -->

 
					</div>
					<div class="col-md-6"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
						<div class="form-group">


							<label for="parent_LastName" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Last Name
								</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" maxlength="50"
									name="parent_LastName" id="parent_LastName"
									onkeypress="HideError()" value='' />
							</div>
						</div>
						<br />
						
						<div class="form-group">
									<label for="relation" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Relationship</label>
									<div class="col-xs-7">
										<select class="form-control" name="relation"
											onkeypress="HideError()" id="relation">
											<option value="father">Father</option>
											<option value="mother">Mother</option>
											<option value="guardian">Guardian</option>
										</select>
									</div>
								</div>
								<br />
						
						
						<div class="form-group">
							<label for="inputPassword" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Mobile No
								<span style="color:red;">*</span> </label>
							<div class="col-xs-7">
								<input type="text" class="form-control" name="mobile_number"
									id="mobile_number" onkeypress="HideError()" maxlength="10"
									value='' />
							</div>
						</div>
						<br />
						
						
						
								
						<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;" id="inputnames">Stream <span style="color:red;">*</span></label>
									<div class="col-xs-7">
										<select id="stream" name="stream" class="form-control"
											required>
											<option value=""></option>
											<logic:present name="StreamList">

												<logic:iterate id="StreamRec" name="StreamList">

													<option
														value="<bean:write name="StreamRec" property="streamId"/>">
														<bean:write name="StreamRec" property="streamname" />
													</option>

												</logic:iterate>

											</logic:present>
										</select>
									</div>
								</div>
								<br />
								
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;" id="inputnames">Class <span style="color:red;">*</span></label>
									<div class="col-xs-7">
										<select id="class" name="classname" class="form-control"
											required>
											<option value=""></option>

										</select>
									</div>
								</div>
						     <br />
								

							

<!-- 						<div class="form-group"> -->
<!-- 							<label for="inputPassword" class="control-label col-xs-4" -->
<!-- 								style="text-align: right; line-height: 35px;">User Name -->
<!-- 								<span style="color:red;">*</span></label> -->
<!-- 							<div class="col-xs-7"> -->
<!-- 								<input type="text" class="form-control" name="user_name" -->
<!-- 									id="user_name" onkeypress="HideError()" maxlength="25" value='' /> -->
<!-- 							</div> -->
<!-- 						</div> -->

<!-- 						<br /> <br /> -->
<!-- 						<div class="form-group"> -->
<!-- 							<label for="inputPassword" class="control-label col-xs-4" -->
<!-- 								style="text-align: right; line-height: 35px;">Re Enter -->
<!-- 								Password <span style="color:red;">*</span></label> -->
<!-- 							<div class="col-xs-7"> -->
<!-- 								<input type="password" class="form-control" -->
<!-- 									name="re_enter_password" id="re_enter_password" -->
<!-- 									onkeypress="HideError()" maxlength="25" value='' /> -->
<!-- 							</div> -->
<!-- 						</div> -->

<!-- 						<br /> <br /> -->




					</div>

					<b style="padding-left: 878px;">NOTE : All <span style="color:red;">'*'</span> Fields are Mandatory</b>

				</div>
			</div>
		</div>
	</div>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	
	<footer>
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6">

					<div class="copyright">Copyright \A9 2015 Centris Infotech
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
	<!-- <div class="row">
		<div class="col-lg-4 col-md-4 col-sm-4 login-section">
			<div class="col-md-12"
				style="padding: 0; border: 1px solid #07aab9; border-radius: 5px 5px 0px 0px;">
				<h2 class="bar">Sign In</h2>
			</div>

			<div class="col-md-12 "
				style="border-right: 1px solid #07aab9; border-left: 1px solid #07aab9; border-radius: 0; background: #fff;">
			
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
					<button class="btn btn-lg btn-primary btn-block" type="submit"
						onclick="return validateFields()"
						style="border-radius: 5px; margin-top: 10px; font-family: Roboto Medium;">
						<i class="fa fa-sign-in"></i> Login
					</button>
					<br /> <a class=""
						href="registration.html?method=NewRegistrationEntryPage">Temporary
						Registration</a> <a class="" style="margin-left: 170px;" href="#">Forgot
						Password ?</a>

					<center>
						<span style="padding-left: 10%; color: red;" class="loginTips"></span>
					</center>
				</form>
			</div>
			<div class="col-md-12 "
				style="padding: 0; background: #fff; border-radius: 0 0 5px 5px; border-right: 1px solid #07aab9; border-left: 1px solid #07aab9; border-bottom: 1px solid #07aab9;">

			</div>
		</div>
	</div> -->

	<script>
		$(document).ready(function() {

			$(".sign_in").click(function(e) {
				e.preventDefault();
				$("#overlay").slideToggle(10);
				$(".login-section").slideToggle("fast");

			});

		});
		$(document).ready(function() {

			$(".Enquiry").click(function(e) {
				e.preventDefault();
				$("#overlay").slideToggle(10);
				$(".guestlog").slideToggle("fast");

			});

			$(".close").click(function(e) {
				e.preventDefault();
				$("#overlay").hide(10);
				$(".guestlog").slideUp("fast");

			});

		});
	</script>
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>


</body>
</html>