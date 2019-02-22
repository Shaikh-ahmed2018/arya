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


<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
	
	
<script type="text/javascript" src="JS/backOffice/Fee/concession.js"></script>

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
.buttons{

vertical-align: 0px;

}
</style>
<style>
.save2:hover {
	cursor: pointer;
}

#view:hover {
	cursor: pointer;
}
s
</style><style>
.buttons{

vertical-align: 0px;

}
</style>
</head>


<body>

	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>New Concession</span>
		</p>
		
		
			<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>
		
		
		
		
		
		<%-- 	<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present> --%>

		<%-- <logic:present name="errormessagediv" scope="request">
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><bean:write name="errormessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present> --%>

		<div class="errormessagediv1"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 35%; font-size: 13px; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>
		
		<form  method="post" >
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Concession Details
							</h4></a>
							
							
							<div class="navbar-right">
							
								<span id="submit" class="buttons" data-toggle="tooltip" data-placement="bottom" title="Save" >Save
								</span> &nbsp;
									
								 <span id="back" class="buttons"  >Back</span>
							</div>
							
						
					</div>
				
				
					
					
					<input type="hidden" id="concessionId" value='<logic:present name="ConcessionList"><bean:write name="ConcessionList" property="concessionId"/></logic:present>'></input>
					
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6" id="txtstyle">
								
								<div class="form-group">
									<label for="inputEmail"  class="control-label col-xs-4"
										style="text-align: right;  line-height: 35px;">Concessionname</label>
									<div class="col-xs-7">
										<input type="text" name="Concessionname" class="form-control" id="concessionname"  onkeypress="HideError()"
											 value='<logic:present name="ConcessionList"><bean:write name="ConcessionList" property="concesionName"/></logic:present>'></input> 						
											
											
									</div>
								</div>
								<br />
								
									<div class="form-group">
									<label for="inputEmail"  class="control-label col-xs-4"
										style="text-align: right;  line-height: 35px;">Percentage</label>
									<div class="col-xs-7">
										<input type="text" name="percentage" class="form-control" id="percentage"  onkeypress="HideError()"
											 value='<logic:present name="ConcessionList"><bean:write name="ConcessionList" property="percentage"/></logic:present>'></input> 						
											
											
									</div>
								</div>
								<br />

								<div class="form-group">
								
								
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
										<textarea style="resize:none" rows="3" cols="25"  class="form-control"
										 name="description" class="form-control" id="description">
											 <logic:present name="ConcessionList"><bean:write name="ConcessionList" property="description"/></logic:present></textarea>
									</div>
								</div>
								<br />
								</div>
								</div>
								</div>
								
				</div>
				</div>				
								
		</form>
	</div>
	
	
</body>

</html>
