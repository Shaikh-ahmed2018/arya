<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<title>Campus School Stream</title>

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


<script type="text/javascript" src="JS/backOffice/Settings/AddCaste.js"></script>





<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.buttons {
	vertical-align: 0px;
}
.navbar-right span{
margin-right:3px;
}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="title"><bean:write name="title"></bean:write></logic:present></span>
		</p>
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<center>
		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div></center>

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne" style="margin-bottom: 5px";>
					
						<a href="#" style="color: #767676"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Caste
							Details
						</h4></a>
					

					<div class="navbar-right">
						<span id="saveReligionId" class="buttons">Save </span>
						<span id="back" class="buttons">Back</span>
					</div>
				</div>
				

				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="clearfix">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">



							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Religion</label>
									<div class="col-xs-7">
										<select name="main_religion" id="main_religion"
											class="form-control" onkeypress="HideError()">
											<option value="">------------------Select------------------</option>
											<logic:present name="religiondetails">
												<logic:iterate id="religiondetails" name="religiondetails">
													<option
														value='<bean:write name="religiondetails" property="religionId"/>'>
														<bean:write name="religiondetails" property="religion" />
													</option>
												</logic:iterate>
											</logic:present>
										</select> 
									</div>
								</div>
							<input type="hidden" name="hmain_religion" id="hmain_religion"
											value="<logic:present name="religionList"><bean:write name="religionList" property="religion" />
													</logic:present>" />

							<div class="form-group clearfix">

								<input type="hidden" name="religionId" class="streamidclass"
									id="relgnId"
									value='<logic:present name="religionList"><bean:write name="religionList" property="casteId" />
													</logic:present>'></input>

								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Caste</label>
								<div class="col-xs-7">
									<input type="text" name="religion" id="religionNameId"
										onkeypress="HideError()" class="form-control" placeholder=""
										maxlength="30"
										value='<logic:present name="religionList"><bean:write name="religionList" property="caste" /></logic:present>'></input>
								</div>
								
								<input type="hidden" id="hiddencaste" value="<logic:present name="religionList"><bean:write name="religionList" property="caste" /></logic:present>"/>
							</div>
							
						</div>
					</div>
				</div>


			</div>

		</div>

	</div>






</body>
</html:html>