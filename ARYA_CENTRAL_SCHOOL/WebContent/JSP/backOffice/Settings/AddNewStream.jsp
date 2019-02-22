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
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Settings/streamDetails.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><style>
.buttons{

vertical-align: 0px;

}
.own-panel{
padding : 15px;
}

</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin-bottom: 5px;margin-top: 5px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="title"><bean:write name="title"></bean:write></logic:present></span>
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
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a  
							href="#" style="color: #767676"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Stream Details
						</h4></a>
					

					<div class="navbar-right">
						<span id="savestreamid" class="buttons" style="margin-right: 5px;">Save</span>
						<span id="back" class="buttons" style="margin-right: 7px;">Back</span>
					</div>
				</div>
				
			 <input type="hidden" name="streamId" class="streamidclass" id="streamId" value='<logic:present name="list"><bean:write name="list" property="streamId" /></logic:present>'></input>
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body own-panel" style="padding:0px;">
						<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;padding-bottom:9px;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">School Name </label>
								<div class="col-xs-7">
							<select id="locationname" name="location" class="form-control">
							<option value="">-------------Select-----------</option>
							<option value="all">All</option>
								<logic:present name="locationList">
									<logic:iterate id="Location" name="locationList">
										<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
									</logic:iterate>
								</logic:present>
						
						</select>
						</div>
								<input type="hidden" name="schoolId" class="form-control" id="schoolId" value='<logic:present name="list"><bean:write name="list" property="locationId" /></logic:present>'></input>
							</div>
					

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Description
								</label>
								<div class="col-xs-7">
								<textarea style="resize:none" rows="4" cols="25"  class="form-control" name="description" id="description" ><logic:present name="list"><bean:write name="list" property="description"/></logic:present></textarea>
								</div>
							</div>
				

						</div>
						<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;padding-bottom:9px;">
						<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Stream
									Name </label>
								<div class="col-xs-7">
									<input type="text" name="streamName" id="streamName" onkeypress="HideError()"
										class="form-control" placeholder="" maxlength="30"
										value='<logic:present name="list"><bean:write name="list" property="streamName" /></logic:present>'></input>
								</div>
								<input type="hidden" name="streamId" class="form-control"
									id="streamId"
									value='<logic:present name="list"><bean:write name="list" property="streamId" />
													</logic:present>'></input>
							</div>
						
						</div>
						
						
						
						
					</div>
				</div>


			</div>

		</div>

	</div>






</body>
</html:html>