<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%><!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JS/common.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


<script type="text/javascript"
	src="JS/backOffice/Settings/AddDesignation.js"></script>

<script type="text/javascript">
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
</script>
<style>
.buttons {
	vertical-align: 0px;
}
</style>
</head>


<body>

	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present
					name="title">
					<bean:write name="title"></bean:write>
				</logic:present></span>
		</p>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<logic:present name="errormessagediv" scope="request">
			<div class="errormessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><bean:write name="errormessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="errormessagediv1" align="center"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 37%; font-size: 10pt; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>

		<div class="successmessagediv" style="display: none;" align="center">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"
					style="text-align: center;"><span class="successmessage"
					style="text-align: center;"></span></a>
			</div>
		</div>

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion" href="#"
							style="color: #767676"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Designation Details
						</h4></a>
					



					<div class="navbar-right">
						<span id="submit" class="buttons" style="margin-right: -10px;">Save
						</span> &nbsp;
						<!--  <span
									 id="submit"><img src="images/save.png"
									style="font-size: 20px; line-height: 34px; color: #989898; margin-top: -5px;"  data-toggle="tooltip" data-placement="bottom" title="Save" >
									
									</span>  -->

						<span id="back"  class="buttons">Back</span>
					</div>

				</div>
				

				<input type="hidden" id="designationid"
					value='<logic:present name="DesignationLIst"><bean:write name="DesignationLIst" property="designationid"/></logic:present>'></input>

				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<div class="col-md-6" id="txtstyle" style = "padding-bottom:10px;">

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Designation</label>
								<div class="col-xs-7">
									<input type="text" name="designationname" class="form-control"
										id="designation" onkeypress="HideError()"
										value='<logic:present name="DesignationLIst"><bean:write name="DesignationLIst" property="designation_name"/></logic:present>'></input>


								</div>
							</div>
					

							<div class="form-group clearfix">


								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Description</label>
								<div class="col-xs-7">
									<textarea style="resize: none" rows="4" cols="25" class="form-control" name="description" class="form-control"id="description"> <logic:present name="DesignationLIst"><bean:write name="DesignationLIst" property="designation_description" />
									</logic:present>
									</textarea>
								</div>
							</div>
					
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>


</body>

</html>
