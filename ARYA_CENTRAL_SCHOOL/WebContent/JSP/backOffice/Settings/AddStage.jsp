<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="JS/backOffice/Settings/StageDetails.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
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
</script><style>
.buttons{

vertical-align: 0px;

}
</style>
</head>


<body>

	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="Stage" scope="request"><bean:write name="Stage"></bean:write></logic:present></span>
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
		
		
			<form  method="post" >
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Stage Details
							</h4></a>
							
							
							
							
							<div class="navbar-right">
							 <span id="savestage" class="buttons" >Save</span> 
							 <span id="back" class="buttons" >Back</span>
							</div>
							
							
					</div>
					
					<input type="hidden" id="stageid" value='<logic:present name="StageLIst"><bean:write name="StageLIst" property="stageid"/></logic:present>'></input>
					
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								id="txtstyle">
								
								<div class="form-group clearfix">
									<label for="inputEmail"  class="control-label col-xs-4"
										style="text-align: right;  line-height: 35px;">Stage<font color="red"> *</font></label>
									<div class="col-xs-7">
										<input type="text" name="stagename" class="form-control" id="stagename" onkeypress="HideError()"
											 value='<logic:present name="StageLIst"><bean:write name="StageLIst" property="stage_name"/></logic:present>'></input> 						
											
											<!-- stage_id,stage_name -->
									</div>
								</div>
								

								<div class="form-group clearfix ">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
										<textarea name="description" class="form-control" id="description"><logic:present name="StageLIst"><bean:write name="StageLIst" property="stage_description"/></logic:present></textarea>
									</div>
								</div>
								
							</div>
							
							<div class="col-md-6" id="txtstyle">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" >
										<logic:present name="AccyearId" scope="request">
											<logic:iterate id="AccyearId" name="AccyearId">
											<option value='<bean:write name="AccyearId" property="accId" />'><bean:write name="AccyearId" property="accName" /></option>
											</logic:iterate>
										</logic:present>
								
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix ">
									<label for="inputEmail"  class="control-label col-xs-4"
										style="text-align: right;  line-height: 35px;">Amount<font color="red"> *</font></label>
									<div class="col-xs-7">
										<input type="text" name="amount" class="form-control" id="amount" onkeypress="HideError()" maxlength="7"
											 value='<logic:present name="StageLIst"><bean:write name="StageLIst" property="amount"/></logic:present>'></input> 						
											
											<!-- stage_id,stage_name -->
									</div>
								</div>
								
								
								</div>
								</div>
								</div>
								
				</div>
				</div>				
								
		</form>
	</div>
	
</body>

</html>
