
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript"	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Admin/ChangeBackgroundPage.js"></script>
<link rel="stylesheet"	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<!-- <link href="/CSS/Admin/bootstrap-datetimepicker.min.css" rel="stylesheet"	type="text/css" />
<link href="CSS/Admin/bootstrap-combined.min.css" rel="stylesheet"	type="text/css" /> -->
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
<link href="CSS/newUI/modern-business.css" rel="stylesheet"/>
<link href="CSS/newUI/custome.css" rel="stylesheet"/>
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css"/>
	
	
	
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

.save:hover {
	cursor: pointer;
}
</style>
<style>
.buttons{

vertical-align: 0px;

}
</style>


</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 13pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Change Background</span>
			
		</p>
			
						<div class="errormessagediv" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning" style="text-align: center;"><span
									class="validateTips" style="text-align: center;"></span></a>
							</div>
						</div>
				
			
				<center>
					
						<div class="successmessagediv" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"><span class="validateTips" style="text-align: center;"></span></a>
							</div>
						</div>
				
					
						
		          </center>
						
	
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" 
								href="#collapseOne" style="color: #767676"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;<h4 class="panel-title">Change Backgournd
							</h4></a>
						
				
							<div class="navbar-right">
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CNGCRE" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
								 <span class="buttons" id="save" style="margin-right: 3px;" >Change</span>								
								
								<a	href="adminMenu.html?method=changeBackground"></a>
								</logic:equal>
								</logic:equal>
								</logic:iterate>
								</logic:present>
								
								
								<!-- <span class="buttons" data-toggle="tooltip" data-placement="bottom" title="List"> Back
							
								</span> -->
								
								
							</div>
				
						
					</div>

					
			<form  enctype="multipart/form-data" method="post" name="InventoryTransactionForm">	
		       <div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-size: 11pt; font-family: Open Sans Light;color: #5d5d5d;">
								
							
								
									<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Add
										Photo</label>
									<div class="col-xs-7">
										<input type="file" id="studentImageId1" name="inputfile" 
											class="form-control" />
									</div>
								</div>
								<br />
								<div class="form-group">

									<div class="col-xs-7">
										<img id="imagePreview" src="#" alt="image" width="80px"
											height="70px" style="margin-left: 63%;padding: 1px" />
									</div>
								</div>

								<br />
                                
								
							</div>
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								
								
								
							</div>
							
						
						</div>
					</div>
				</form>
					<!-- <button type="reset" class="btn btn-info" id="clear">Clear</button> -->
					<br />
				</div>
		</div>
		
	</div>
</body>
</html>