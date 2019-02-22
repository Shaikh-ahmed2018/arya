<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>


<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>
<style>
.glyphicon:hover {
	cursor: pointer;
}

#classSave:hover {
	cursor: pointer;
}

.navbar-right {
	margin: 0px;
}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Assignment
				Details</span>
		</p>
		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<logic:present name="error" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
								name="error" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>


		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="errormessagediv1"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 50%; font-size: 11pt; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>


		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Assignment Details
						</h4></a>
					
					<div class="navbar-right">
						 <span id="back" class="buttons" style="top: 9px; margin-right: 10px;">Back</span>
						</a>
					</div>
				</div>


				
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">

						<div class="col-md-6" id="txtstyle"
							style="font-size: 11pt; color: #5d5d5d; margin-top: 13px; margin-bottom: 10px;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;"> Actual Date</label>
								<div class="col-xs-7">
									<input type="text" class="form-control"  readonly="readonly" value="<logic:present name="assdetails" ><bean:write name="assdetails" property="actualcompletedate"/></logic:present>" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Remarks </label>
								<div class="col-xs-7">
									<textarea style="font-size: 12px; resize: none" rows="6" class="form-control" readonly="readonly" name="remarks" ><logic:present name="assdetails"><bean:write name="assdetails" property="remarks" />
									</logic:present></textarea>
								</div>
							</div>
						</div>
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 13px; margin-bottom: 10px;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Secured
									Marks </label>
								<div class="col-xs-7">
									<input type="text" class="form-control" readonly="readonly" value="<logic:present name="assdetails" ><bean:write name="assdetails" property="acquiredmarks"/></logic:present>" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Description
								</label>
								<div class="col-xs-7">
									<textarea style="font-size: 12px; resize: none" rows="6" class="form-control" readonly="readonly" ><logic:present name="assdetails"><bean:write name="assdetails" property="description" />
									</logic:present></textarea>
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
