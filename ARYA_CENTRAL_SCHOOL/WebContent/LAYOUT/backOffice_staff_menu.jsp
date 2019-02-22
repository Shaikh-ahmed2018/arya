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
			<div class="panel-heading" style="background-color: #07AAB9;">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#menucollapseOne" style="color: #fff;"><h3
						class="panel-title active">
						Staff Management<i class="glyphicon glyphicon-triangle-bottom"
							style="float: right;"></i>
					</h3></a>
			</div>
			<div id="menucollapseOne" class="accordion-body collapse in">
				<div class="panel-body">
					<ul class="nav nav-pills nav-stacked">

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STAFFDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=staffList" id="sub_module_3_1">Staff
												Registration</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>


						 <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STFFEMPDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=staffEmployementList" id="sub_module_3_2">Staff
												Renumeration</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 




						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STFFATTDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=getStaffAttendance" id="sub_module_3_3">Staff
												Attendance</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STFFATTDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=StaffListForLeave" id="sub_module_3_15">Staff
												Leave</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STFFEMPDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=TDSComputationDetails" id="sub_module_3_6">IT Declaration</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STFFEMPDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="teachermenuaction.html?method=generatePayroll" id="sub_module_3_7">Generate Payroll </a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="TMTDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a
											href="adminMenu.html?method=getclassandteacherList" id="sub_module_3_8">ClassTeacher
												Mapping</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="UPASSDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="teachermenuaction.html?method=assignmentView" id="sub_module_3_4">Upload
												Assignment</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="UPASSDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="teachermenuaction.html?method=projectAssign" id="sub_module_3_12">Project</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
<%-- 						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="UPASSDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=stafffileupload">Upload
												Staff Data From File</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
 --%>

					<%-- 	<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STAFFPAYDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=getPayrollList" id="sub_module_3_5">Salary
												Details</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> --%>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="TTSBTND" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value"> 
						
						<li><a href="adminMenu.html?method=gettodaytimetablelist" id="sub_module_3_13">TimeTable Substitution</a></li>
						</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						 <li><a href="teachermenuaction.html?method=viewTeacherListForTimeTable" id="sub_module_3_9">View TimeTable</a></li>
					    	<li><a href="teachermenuaction.html?method=personalDetails" id="sub_module_3_14">Personal Details</a></li>
					
 
 						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STAFFDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="adminMenu.html?method=staffExcelFileUpload" id="sub_module_3_11">Upload Staff Excel Data File</a></li>
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
