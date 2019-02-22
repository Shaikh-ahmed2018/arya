<!DOCTYPE html>
<!-- HTML -->
<html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!-- Head -->
<head>
<!-- Title -->
<title>Centris Infotech</title>
<!-- Bootstrap CSS -->
<link type="text/css" rel="stylesheet"
	href="CSS/login/bootstrap.min.css">
<!-- Bootstrap Theme CSS -->
<link type="text/css" rel="stylesheet"
	href="CSS/login/bootstrap-theme.min.css">
<link type="text/css" rel="stylesheet" href="CSS/login/nav_tabs.css">
<link rel="stylesheet" href="CSS/login/font-awesome.min.css">
<!-- Style CSS -->
<link rel="stylesheet" href="CSS/login/style.css">
<link rel="stylesheet" href="CSS/common.css">
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/JS/login/Admin.js"></script>
<script>
	function preventBack() {
		window.history.forward();
	}
	setTimeout("preventBack()", 0);
	window.onunload = function() {
		null
	};
</script>

</head>
<!-- Body -->
<body>

	<!-- UI - X Starts -->
	<div class="ui-x">
		<!-- Head Starts -->
		<div class="ui-head bg-green">
			<!-- Header -->
			<div class="ui-header">
				<div class="container-fluid">

					<!-- Collapse -->
					<div class="collapse navbar-collapse clearfix"
						id="bs-example-navbar-collapse-1">
						<div class="ui-logo pull-left">
							<img src="images/logo-crop.png" />
						</div>
						<!-- SignIn -->
						<ul class="nav navbar-nav navbar-right">
							<li><a href="#" data-toggle="modal" data-target="#myModal"><i
									class="fa fa-user"></i> &nbsp;Sign In</a></li>
						</ul>
						<!-- Menu Bar Starts -->
						<div class="ui-menu">
							<div class="container-fluid">
								<!-- Nav tabs -->
								<ul class="ui-nav clearfix">
									<li><a href="publicMenu.html?method=Home">Home</a></li>
									<li><a href="#aboutus" data-toggle="tab">About Us</a></li>
									<li><a href="#circulars" data-toggle="tab">Circulars</a></li>
									<li><a href="#facilities" data-toggle="tab">Facilities</a></li>
									<li><a href="#achievements" data-toggle="tab">Achievements</a></li>
									<li><a href="#infrastructure" data-toggle="tab">Infrastructure</a></li>


									<li><a href="publicMenu.html?method=careers">Careers</a></li>
									<li><a href="#infrastructure" data-toggle="tab">Events</a></li>
									<li><a href="#contactus" data-toggle="tab">Contact Us</a></li>
								</ul>
							</div>
						</div>
						<!-- Menu Bar Ends -->
					</div>
					<!-- /.navbar-collapse -->

					<!-- Modal SignIn -->
					<div class="modal fade" id="myModal">
						<div class="modal-dialog">
							<!-- Modal Content -->
							<div class="modal-content">
								<!-- Close Button -->
								<button type="button" class="close" data-dismiss="modal">
									<span>&times;</span>
								</button>
								<!-- Heading -->
								<h3>Sign In</h3>
								<!-- Form -->
								<form>
									<!-- User Type -->
									<div class="form-group">
										<!-- Label -->
										<label><b>User Type</b></label>
										<!-- Select Option -->
										<select class="form-control" id="user_type">
											<option value="">----- Select -----</option>
											<option value="admin">Admin</option>
											<option value="principle">Principal</option>
											<option value="teacher">Teachers</option>
											<option value="parent">Parents</option>
										</select>
									</div>
									<!-- Email Address -->
									<div class="form-group">
										<!-- Label -->
										<label><b>Username</b></label>
										<!-- Input Type -->
										<input type="email" class="form-control" id="user_name"
											placeholder="Username">
									</div>
									<!-- Password -->
									<div class="form-group">
										<!-- Label -->
										<label><b>Password</b></label>
										<!-- Input Type -->
										<input type="password" class="form-control" id="user_password"
											placeholder="Password">
									</div>
									<!-- Submit Button -->
									<button type="button" class="btn" onclick="validateFields()">Login</button>
									<span style="padding-left: 10%; color: red;" class="loginTips"></span>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</div>
		</div>

	</div>

	<!-- Bootstrap JS -->
	<script src="JS/login/jquery.min.js"></script>
	<!-- Bootstrap JS -->
	<script src="JS/login/bootstrap.min.js"></script>
	<script>
	<!-- ToolTip -->
		$(function() {
			$('.ui-icons a.icons').tooltip(); //ToolTip
		});
	</script>
</body>
</html>