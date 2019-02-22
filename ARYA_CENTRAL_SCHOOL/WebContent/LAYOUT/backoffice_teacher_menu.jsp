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
<body>
	<div class="col-md-2 leftmenu">

		<div class="panel panel-primary"
			style="border: none; background-color: transparent; margin: 1px 0px; box-shadow: none;">
			<div class="panel-heading leftNav" style="background-color: #07AAB9;">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#menucollapseOne" style="color: #fff;"><h3
						class="panel-title active">
						Leave Management<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
					</h3></a>
			</div>
			<div id="menucollapseOne" class="accordion-body collapse in">
				<div class="panel-body">
					<ul class="nav nav-pills nav-stacked">

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LEAVEAPPROVE" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=LeaveCategoryList" id="sub_module_14_1">Leave Types</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 


						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LEAVEREQ" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="teachermenuaction.html?method=leaveRequest" id="sub_module_14_2">Leave
												Request & Status</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LEAVEAPPROVE" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="teachermenuaction.html?method=leaveApproval" id="sub_module_14_3">Approve/Reject
												Leave</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<%-- <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LEAVEAPPROVE" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=LeaveBankList">Leave
												Bank</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> --%>
						
						
						
						
						
						
						
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LEAVEREQ" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="teachermenuaction.html?method=getviewLeaveDetails" id="sub_module_14_4">Leave Details</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
					</ul>

				</div>
				<br />
			</div>
		</div>


	</div>
</body>
</html>
