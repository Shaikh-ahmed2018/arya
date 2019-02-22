<!DOCTYPE html>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<div class="col-md-2 leftmenu" style="background-color: #f5f2f2;">
			
			
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						  <div class="panel panel-default" style="margin: -1px 0px;">
						  
							<div class="panel-heading" role="tab" id="headingOne" style="background: #07AAB9;">
							
							  <h4 class="panel-title" id="beforeparent">
								<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color:#767676" >
									<h3 class="panel-title active" style="font-size:18px !important;color: #fff;">Favorites&nbsp;&nbsp;<i class="glyphicon glyphicon-triangle-bottom" style="float:right;margin: 1px -8px 0px 0px;"></i></h3>
								</a>
								
							</h4>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
							  <div class="panel-body">
								<ul class="nav nav-pills nav-stacked">
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="adminMenu.html?method=studentList">Student Information</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STAFFDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=staffList">Staff Information</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>		
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEECOLLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
										<li><a href="adminMenu.html?method=feeCollection">Fee Collection</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>				
											<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="STRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="adminMenu.html?method=homeworklist">Home Works</a></li>
										
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
