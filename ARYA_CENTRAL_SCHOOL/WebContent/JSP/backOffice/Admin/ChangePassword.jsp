<!DOCTYPE html>
<html lang="en">
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JS/backOffice/Admin/ChangePassword.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<!-- <script type="text/javascript">
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>
<script>
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
</script> -->
<style>
.glyphicon:hover{

cursor: pointer;
}
</style><style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Change Password</span>
		
		</p>

						<div class="errormessagediv"	style="display: none; margin-left: 30%;width: 100%;" >
							<div class="message-item">
							       <!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning" style="text-align: center;"><span
									class="validateTips" style="text-align: center;"></span></a>
							</div>
						</div> 
							
						<div class="successmessagediv" style="display: none;margin-left: 30%;width: 100%;">
								<div class="message-item">
									<a href="#" class="msg-success bg-msg-succes" style="text-align: center;"><span class="successmessage" style="text-align: center;"></span></a>
								</div>
						</div>
					
					
				
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;<h4 class="panel-title">Change Password
							</h4></a>

							<!-- <div class="navbar-right" style="float: right; margin: -10px;">
							
								<img src="images/rightline.png" style="margin-top: -13px;">
								 <a	href="" id="save"><img src="images/save.png" 
									style="font-size: 20px; line-height: 34px; color: #989898; margin-top: -5px;" data-toggle="modal" data-target="#myModal" data-toggle="tooltip" data-placement="bottom" title="Save"><img
									src="images/rightline.png" style="margin-top: -13px;"></a> <span class="glyphicon glyphicon-list"  id="listing"
									style="font-size: 20px; line-height: 34px; color: #989898;" data-toggle="tooltip" data-placement="bottom" title="List"></span>
							</div> -->
							
<!-- 		<div class="navbar-right" >
				 <span id="save" class="buttons"   title="Save">Save</span> &nbsp;
				<span id="listing" class="buttons"  title="List">Back</span>
							
		</div> 
				 -->			
							
							
		 		<div class="navbar-right" >
					<span class="buttons" id="save">Save</span>&nbsp;
					<span class="buttons" id="back">Back</span>&nbsp;
				</div>
						 	
						
						<script>
							$(document).ready(function() {
								$('[data-toggle="tooltip"]').tooltip();
							});
						</script>
										
						
					</div>
					<br />
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">First
										Name</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="firstname" value="<logic:present name="userrecordVo" scope="request"><bean:write name="userrecordVo" property="firstName"></bean:write></logic:present>"
											placeholder="" readonly="readonly" >
									</div>
								</div>
							

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">New Password
										</label>
									<div class="col-xs-7">
										<input type="password" class="form-control" id="newpassword" onkeypress="HideError()"
											placeholder="">
									</div>
								</div>
							</div>
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
							
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">UserName
										</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="username" value="<logic:present name="userrecordVo" scope="request"><bean:write name="userrecordVo" property="email"></bean:write></logic:present>"
											placeholder="" readonly="readonly">
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Confirm Password
										</label>
									<div class="col-xs-7">
										<input type="password" class="form-control" id="confirmpassword" onkeypress="HideError()"
											placeholder="">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			<input type="hidden" id="hUser" value='<logic:present name="UserId" scope="request"><bean:write name="UserId" scope="request"></bean:write></logic:present>'></input>	
	</div>		
		
	</div>

</body>

</html>
