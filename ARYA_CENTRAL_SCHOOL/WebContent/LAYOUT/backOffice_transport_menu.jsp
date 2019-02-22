<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

	<link href="CSS/newUI/custome.css" rel="stylesheet">
	<script type="text/javascript" src="JS/login/tranport.js"></script>
	</head>
<body>
		<div class="col-md-2 leftmenu"
			style="background-color: #f5f2f2;">
			
			<div class="panel panel-primary" style="border: none;	margin: 1px 0px;box-shadow: none;">
			<div class="panel-heading leftNav transport" style="background-color: #07AAB9;margin-bottom:8px;">
					<a data-toggle="collapse" data-parent="#accordion2" href="#" style="color:#fff;"><h3 class="panel-title active" >Transport Management<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i></h3></a>		
				</div>
				<div id="menucollapseOne" class="accordion-body collapse in">
					<div class="panel-body" >
						<ul class="nav nav-pills nav-stacked">
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STGDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="adminMenu.html?method=StageList" id="sub_module_7_1">Stage
												Master </a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="adminMenu.html?method=separateTransportTermList" id="sub_module_7_2">Term
												Setup</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=routeMasterSettings" id="sub_module_7_3">Route
												Master</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>


						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="adminMenu.html?method=transportFeeCollection" id="sub_module_7_4">Transport
												Fee</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="transport.html?method=transportFeeSearch" id="sub_module_7_5">Transport Request/Cancel</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="adminMenu.html?method=transportCategory" id="sub_module_7_6">Transport Category</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>


						<%-- <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="adminMenu.html?method=destWiseStudentdetails" id="sub_module_7_7">Student
												Report Destination Details</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> --%>


						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="DRVDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=driverList" id="sub_module_7_8">Driver
												Master</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="VEHDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=vehicleList" id="sub_module_7_9">Vehicle
												Master</a></li>

									</logic:equal>

								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<%-- <logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="TTYDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=studentBusIDGeneration" id="sub_module_7_10">Print Bus Id Card</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>  --%>

							<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="FUELDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="feeupload.html?method=transportFeeCollectionUpload" id="sub_module_7_14">Upload Transport Fee Details</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 

							
							<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="FUELDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=stagefileupload" id="sub_module_7_11">Upload Stage Excel Data File</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 
						
						
						<logic:present name="UserDetails" scope="session">

							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="FUELDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=driverfileupload" id="sub_module_7_12">Upload Driver Excel Data File</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 


						<logic:present name="UserDetails" scope="session">

							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="FUELDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=studentBusCard" id="sub_module_7_13">Student Bus Card</a></li>

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
