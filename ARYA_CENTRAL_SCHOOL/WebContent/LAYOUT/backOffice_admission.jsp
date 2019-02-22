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

		<div class="panel panel-primary"
			style="border: none; background-color: transparent; margin: 1px 0px; box-shadow: none;">
			<div class="panel-heading" style="background-color: #07AAB9;cursor: pointer;" id="AdmissionMenu">
				<h3 class="panel-title active" style="color:#fff" >
					Admission<i class="glyphicon glyphicon-triangle-bottom"
							style="float: right;"></i>
					</h3>
			</div>


						<div id="AdmissionMenuOne" class="accordion-body collapse in">
				<div class="panel-body">
					<ul class="nav nav-pills nav-stacked">
					<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=tempadmissionMenu" id="sub_module_6_1">Registration</a></li>

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
							var pathname = window.location.href
									.substr(window.location.href
											.lastIndexOf("/") + 1);
							$(".nav.nav-pills.nav-stacked a").each(
									function() {
										if (pathname === $(this).attr("href"))

										{
											$(this).closest(".panel-collapse")
													.attr("style",
															"display:block");

										}
									});
							$(document)
									.ready(
											function() {
												var pathname = window.location.href
														.substr(window.location.href
																.lastIndexOf("/") + 1);
												if (pathname === "communicationPath.html?method=sendRemarkAction"
														|| pathname === "remainder.html?method=addremainderdetails"
														|| pathname === "adminMenu.html?method=addJob"
														|| pathname === "AcademicYearPath.html?method=addAcademicyear") {
													$("#menucollapseTwo").attr(
															"style",
															"display:block");
												}
												if (pathname === "adminMenu.html?method=addStream"
														|| pathname === "classPath.html?method=addClass"
														|| pathname === "sectionPath.html?method=addSection"
														|| pathname === "QuotaMenu.html?method=AddQuota"
														|| pathname === "departmentMenu.html?method=Add"
														|| pathname === "subject.html?method=getsubject"
														|| pathname === "acadamicYearPlan.html?method=getAcadamicYearPlanEntry") {
													$("#menucollapseOne").attr(
															"style",
															"display:block");
												}

											});
							$("#AdmissionMenu").click(function(e) {

								$("#AdmissionMenuOne").toggle();
								
							});
							$("#headingTwo").click(function(e) {

								$("#menucollapseTwo").toggle();
								$("#menucollapseOne").slideUp();
								$("#menucollapseThree").slideUp();
							});
							$("#headingThree").click(function(e) {

								$("#menucollapseThree").toggle();
								$("#menucollapseOne").slideUp();
								$("#menucollapseTwo").slideUp();
							});
						});
	</script>

</body>
</html>
