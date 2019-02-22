<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<link href="CSS/newUI/custome.css" rel="stylesheet">

<title>eCampus Pro</title>
<body>
	<div class="col-md-2 leftmenu"
		style="background-color: #f5f2f2;">

		<div class="panel panel-primary"
			style="border: none; background-color: transparent ; margin: 1px 0px; box-shadow: none;">
			<div class="panel-heading leftNav" style="background-color: #07AAB9;">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#menucollapseOne" style="color: #fff;"><h3
						class="panel-title active">
						Admission Module<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
					</h3></a>
			</div>
			<div id="menucollapseOne" class="accordion-body collapse in">
				<div class="panel-body">
					<ul class="nav nav-pills nav-stacked">
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EXMDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="TestAction.html?method=inflowslist">New Registraton</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
						
						
						
						
						</ul>
					</div>
				<br />
			</div>
		</div>

					
			
			<div class="panel-heading" style="background-color: #07AAB9;">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#menucollapseOne" style="color: #fff;"><h3
						class="panel-title active">
						Equiry For<i class="glyphicon glyphicon-triangle-bottom"
							style="float: right;"></i>
					</h3></a>
			</div>
			<div id="menucollapseOne" class="accordion-body collapse in">
				<div class="panel-body">
					<ul class="nav nav-pills nav-stacked">
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EXMDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="TestAction.html?method=enquiryList">Enquiry For </a></li>
									</logic:equal>
							  </logic:equal>
							 </logic:iterate>
						   </logic:present>
						   
						   <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EXMDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="TestAction.html?method=shortAdmission">Short Admission </a></li>
									</logic:equal>
							  </logic:equal>
							 </logic:iterate>
						   </logic:present>
						   
						   
						   
						   
					    </ul>
					</div>
				<br />
			</div>
		</div>
	     </body>
</html>
