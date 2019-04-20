<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
	<link href="CSS/newUI/custome.css" rel="stylesheet">
<title>eCampus Pro</title>
<style type="text/css">
.panel-heading.leftNav a:hover{
color: #FFF !important;
}
</style>
<body>
		<div class="col-md-2 leftmenu">
			
			<div class="panel panel-primary" style="border: none;background-color: transparent;	margin: 1px 0px;box-shadow: none;">
			<div class="panel-heading  leftNav" style="background-color: #07AAB9;">
					<a data-toggle="collapse" data-parent="#accordion2" href="#menucollapseOne" style="color:#fff;"><h3 class="panel-title active" >Fee Management<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i></h3></a>		
				</div>
				<div id="menucollapseOne" class="accordion-body collapse in">
					<div class="panel-body" >
						<ul class="nav nav-pills nav-stacked">
						
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEEDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
						
										<li><a href="adminMenu.html?method=feeDetailsList" id="sub_module_4_1">Fee Setup</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
											
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="TRMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="adminMenu.html?method=termList" id="sub_module_4_2">Term Setup</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> 
							
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CLSFEEDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
										<li><a href="adminMenu.html?method=getClassFeeSetup" id="sub_module_4_3">Class Fee Setup</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
						
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CLSFEEDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
										<li><a href="adminMenu.html?method=specialFeeSetup" id="sub_module_4_9">Special Fee Setup</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEECOLLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
										<li><a href="adminMenu.html?method=feeCollection" id="sub_module_4_4">Fee Collection</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEECOLLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="adminMenu.html?method=fineConfiguration" id="sub_module_4_5">Fine Configuration</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="SCHOLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="feecollection.html?method=FeeScholorship" id="sub_module_4_7">Fee Concession</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEECOLLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="feecollection.html?method=feeCancellation" id="sub_module_4_8">Fee Cancellation</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
								
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEECOLLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="feeupload.html?method=feeCollectionUpload" id="sub_module_4_6">Fee Collection Upload</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
				
						</ul>
						
					</div>
				  <br/>
				</div>
			</div>
			
		</div>
</body>
</html>
