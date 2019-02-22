<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Role Master</title>

<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JS/backOffice/Admin/RoleMaster.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JS/common.js"></script>


<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


<style>
.save:hover {
	cursor: pointer;
}
</style>

<style>
.buttons {
	vertical-align: 0px;
}
</style>
</head>

<body>


	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20px; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">

		<p>
			<img src="images/addstu.png" />&nbsp;<span><logic:present name="title"><bean:write name="title"></bean:write></logic:present></span>

		</p>


		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>



		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessage"></span></a>
			</div>
		</div>



		<form method="post" id="myform">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;<h4 class="panel-title">Role
								Details
							</h4></a>
						


						<div class="navbar-right">

							<span class="buttons" id="save">Save</span> 
							
							<span id="back" class="buttons"> Back</a></span>

						</div>
					</div>

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="clearfix">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<br />
								<div class="form-group clearfix">

									<input type="hidden" id="rolecode"
										value="<logic:present name="RoleDetails"><bean:write name="RoleDetails" property="roleCode"/></logic:present>" />
									<input type="hidden" id="hadministrator"
										value="<logic:present name="RoleDetails"><bean:write name="RoleDetails" property="isadministrator"/></logic:present>" />


									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Role
										Name </label>
									<div class="col-xs-7">
										<input type="text" name="rolename" id="rolename"
											class="form-control" onkeypress="HideError()" maxlength="25"
											value="<logic:present name="RoleDetails"><bean:write name="RoleDetails" property="roleName"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Role
										Description </label>
									<div class="col-xs-7">
										<textarea style="resize: none" rows="3" cols="10" class="form-control" name="roleDescription" id="description"><logic:present name="RoleDetails"><bean:write name="RoleDetails" property="roleDescription" />
										</logic:present></textarea>
									</div>
								</div>

							</div>


							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

								<div class="form-group clearfix" id="admissiondatelable">

									<div class="col-xs-7">
										<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" placeholder="Click Here"
											name="isadministrator" id="isadministrator"
											value='administrator' />&nbsp;&nbsp;&nbsp;<label
											for="inputPassword">Is Administartor </label>
									</div>
								</div>
							</div>

						</div>
					</div>

					<!-- <button type="reset" class="btn btn-info" id="clear">Clear</button> -->
					<br />
				</div>
		</form>
	</div>
</body>

</html>


