<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<style>
.form-group {

}
.pagebanner{
	left: 20px !important;
}
.toggleMenus{
display: block;
}
</style>
<head>

	<link href="CSS/newUI/custome.css" rel="stylesheet">
	<script type="text/javascript" src="JS/login/tranport.js"></script>
	</head>
<body>
		<div class="col-md-2 leftmenu"
			style="background-color: #f5f2f2;">
			
			<div class="panel panel-primary" style="border: none;	margin: 1px 0px;box-shadow: none;">
				<div class="panel-heading leftNav" id="headingOne" style="background-color: #07AAB9;margin-bottom:8px;">
					<a class="toggleMenus" href="#menucollapseOne" style="color:#fff;"><h3 class="panel-title active" >Election Management<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i></h3></a>		
				</div>
				<div id="menucollapseOne" class="menusl accordion-body collapse in">
					<div class="panel-body" >
						<ul class="nav nav-pills nav-stacked">


						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ELECMD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="adminMenu.html?method=GroupList" id="sub_module_12_1">Group Setting</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
						
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ELECMD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=electionList" id="sub_module_12_2">Election Setting</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ELECMD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=electionCategoryList" id="sub_module_12_3">Category Setting</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>


						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ELECMD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="adminMenu.html?method=nominationRegister" id="sub_module_12_4">Nomination Registration</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ELECAP" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="adminMenu.html?method=nominationApproval" id="sub_module_12_8">Nomination Approval</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>


						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ELECMD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="adminMenu.html?method=addBoothDetais" id="sub_module_12_5">Booth Setting</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>


						<%-- <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="DRVDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=voterSearchList">Voter Search</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> --%>

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ELECVD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=electionListForVoting" id="sub_module_12_6">Voter Machine</a></li>

									</logic:equal>

								</logic:equal>
							</logic:iterate>
						</logic:present>
	

						</ul>
						
					</div>
				
				</div>
				
				
					<div class="panel-heading leftNav" id="headingTwo" style="background-color: #07AAB9;margin-bottom:8px;">
						<a class="toggleMenus" href="#menucollapseTwo" style="color:#fff;"><h3 class="panel-title active" >Reports<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i></h3></a>		
					</div>
				<div id="menucollapseTwo" class="menusl accordion-body collapse">
					<div class="panel-body" >
						<ul class="nav nav-pills nav-stacked">


						

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ELECRD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=countingListForVoting" id="sub_module_12_7">Election Report</a></li>

									</logic:equal>

								</logic:equal>
							</logic:iterate>
						</logic:present>
	

						</ul>
						
					</div>
				
				</div>
				
				
				
				
			</div>
			
		</div>
		
		<script>
		
						$(document)
									.ready(
											function() {
												
							$(".toggleMenus").click(function(e) {
								$(".menusl").not($(this).attr("href")).removeClass("in");
								$($(this).attr("href")).toggleClass("in");
								
							});
							
						}); 
	</script>
</body>
</html>
