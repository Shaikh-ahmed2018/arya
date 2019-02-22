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
<body>
		<div class="col-md-2 leftmenu">
			
			
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						  <div class="panel panel-default" style="margin: -1px 0px;">
						  
							<div class="panel-heading leftNav" role="tab" id="headingOne" style="background: #07AAB9;">
							
							  <h4 class="panel-title" id="beforeparent">
								<a data-toggle="collapse" data-parent="#accordion" href="#menucollapseOne" style="color:#767676" >
									<h3 class="panel-title active" style="    color: #fff;">Inventory Master<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i></h3>
								</a>
								
							</h4>
							</div>
							<div id="menucollapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
							  <div class="panel-body">
								<ul class="nav nav-pills nav-stacked">
								
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="STRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="adminMenu.html?method=InventoryTypeList">Inventory Types</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CLSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
							
											<li><a href="adminMenu.html?method=InventoryList">Inventory List</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
										
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="SECDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="adminMenu.html?method=AddorModifyorDeleteList">Add/Delete/Modify</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
								
								</ul>
								
							  </div>
							</div>
						  </div>
						  
						  
						   <div class="panel panel-default" style="margin: -1px 0px;">
							<div class="panel-heading leftNav" role="tab" id="headingTwo" style="background: #07AAB9;">
							  <h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#menucollapseTwo"  style="color:#767676" aria-expanded="false" aria-controls="collapseTwo">
								  <h3 class="panel-title active" style="    color: #fff;">Inventory Transaction<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i></h3>
								</a>
								
							  </h4>
							</div>
							<div id="menucollapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
							  <div class="panel-body">
								<ul class="nav nav-pills nav-stacked">
								
									
									
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="IJPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
										<li><a href="adminMenu.html?method=TransactionList">Transaction</a></li>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	
									
								
				
								</ul>
								
							  </div>
							</div>
						  </div>
						  
						  
						   <div class="panel panel-default" style="margin: -1px 0px;">
							<div class="panel-heading leftNav" role="tab" id="headingThree" style="background: #07AAB9;">
							  <h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#menucollapseThree"  style="color:#767676" aria-expanded="false" aria-controls="collapseTwo">
								  <h3 class="panel-title active" style="color: #fff;">Inventory Report<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i></h3>
								</a>
								
							  </h4>
							</div>
							<div id="menucollapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
							  <div class="panel-body">
								<ul class="nav nav-pills nav-stacked">
								
									
							
								<logic:present name="UserDetails" scope="session">
									 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
										<logic:equal value="RPMDIS" name="daymap" property="key">
											<logic:equal value="true" name="daymap" property="value">	
									
												<li><a href="adminMenu.html?method=usageReport">Usage Report</a></li>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	
							
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="PWMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
										
											<li><a href="adminMenu.html?method=notReturnedReport">Not Returned Report</a></li>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	
								
				
								</ul>
								
							  </div>
							</div>
						  </div>
						  
						  </div>
			
		
		</div>
</body>
</html>
