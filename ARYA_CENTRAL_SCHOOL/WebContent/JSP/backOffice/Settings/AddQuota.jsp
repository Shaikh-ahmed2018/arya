<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
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
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">





<script type="text/javascript" src="JS/backOffice/Settings/QuotaDetails.js"></script>
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
</script> --><style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="title"><bean:write name="title"/></logic:present></span>
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
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>
						
						

		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Quota Details
							</h4></a>
							

							<div class="navbar-right" >
								
								
								
								 <span id="saveid" class="buttons"
									 data-toggle="tooltip" data-placement="bottom" title="Save">Save
									</span> 
								&nbsp;
								
								
							 
									
							 <span id="back" class="buttons">Back</span>
							</div>
					</div>
					
			<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>

			
					
				<!-- <div id="registrationConfirmationDiv" align="center"
	     	      class="updateStreamDetails">
	            	<span id="dialogMandatory">* fields are mandatory </span>
	            	</div>	 -->
						
			<input type="hidden" id="updatedepartmentid" value='<logic:present name="QUOTA_DETAILS"><bean:write name="QUOTA_DETAILS" 
property="quotaid" />
		</logic:present>'></input>		
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Quota Name</label>
									<div class="col-xs-7">
									<input type="text" name="quotaname" class="form-control" id="quotaid" onblur="registernumberValidation()" onkeypress="HideError()"
									value='<logic:present name="QUOTA_DETAILS"><bean:write name="QUOTA_DETAILS" property="quotaName" /></logic:present>'></input>
									</div>
								</div>
								<br />
								
								
								 <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description
										</label>
									<div class="col-xs-7">
										<textarea style="resize:none" rows="6" cols="25"  class="form-control"
											name="description" id="descriptionid" ><logic:present name="QUOTA_DETAILS"><bean:write name="QUOTA_DETAILS" property="quotaDescription"/></logic:present></textarea>
									</div>
								</div>
                               
                               
							    
								
								 <br />
                               
                           
								
							</div>
							<%-- <div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								
                                <div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
									<input type="text" name="description" id="descriptionid" class="form-control" placeholder="Description" 
					value='<logic:present name="QUOTA_DETAILS"><bean:write name="QUOTA_DETAILS" property="quotaDescription" /></logic:present>'></input>
									</div>
								</div>
							</div> --%>
							
						
						</div>
					</div>
					
<!-- 						<div class="panel panel-default">
 -->					
			
					<input type="hidden" name="updatevehicleCode" id="updatevehicleCode" value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="vehiclecode"/></logic:present>"/>
<!-- 				</div>
 -->				</div>
									</div>
				
		</form>
	</div>
			     
</body>

</html>
