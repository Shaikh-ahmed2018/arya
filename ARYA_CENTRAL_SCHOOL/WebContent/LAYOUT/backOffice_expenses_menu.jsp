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
			style="border: none; background-color: transparent; margin: 1px 0px; box-shadow: none;">
			<div class="panel-heading leftNav" style="background-color: #07AAB9;">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#menucollapseOne" style="color: #fff;"><h3
						class="panel-title active">
						Accounts Management<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
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

										<li><a href="adminMenu.html?method=inflowslist">Inflows Accounts</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EXMDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=revelist">Revenue Details</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EXMDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=expenseslist">Expenses Details</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EXMTMDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=getTransactionDetails">Bank
												Transactions</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

					




						<%-- 	<logic:present name="UserDetails" scope="session">

					<logic:present name="UserDetails" scope="session">

								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EXMTMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
											<li><a href="adminMenu.html?method=getTransactionDetails">Bank Transactions</a></li>
											
											</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EXMTMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
											<li><a href="adminMenu.html?method=getSummaryDetails">Account Summary</a></li>
											
											</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
									
								<%-- 			
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="parentMenu.html?method=examdetails">View Exam Details</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>

						

					</ul>

				</div>
				<br />
			</div>
		</div>

	</div>
</body>
</html>
