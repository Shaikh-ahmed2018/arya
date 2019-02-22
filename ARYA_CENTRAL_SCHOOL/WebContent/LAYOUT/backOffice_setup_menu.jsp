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
	<div class="col-md-2 leftmenu"
		style="padding: 0;overflow: scroll;height: 100%; ">


		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default" style="margin: -1px 0px;">

				<div class="panel-heading leftNav" role="tab" id="headingOne"
					style="background: #07AAB9;">

					<h4 class="panel-title" id="beforeparent">
						<a href="#" style="color: #767676">
							<h3 class="panel-title active" style="color: #fff;">
								Master Setup&nbsp;&nbsp;<i
									class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>

					</h4>
				</div>
				<div id="menucollapseOne" class="panel-collapse collapse "
					role="tabpanel" aria-labelledby="headingOne" >
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">

								<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=locationList" id="sub_module_1_1">Location
													</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=streamList" id="sub_module_1_2">Stream
													Details</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>


							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="CLSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=classList" id="sub_module_1_3">Class
													Details</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SECDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=sectionList" id="sub_module_1_4">Division
													Details</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SECDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=SpecializationList" id="sub_module_1_5">Specialization
													Details</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
							
							<%-- 
							
							

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=QuotaDetails">Quota
													Details</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>




							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="DEPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="adminMenu.html?method=departmentDetails" id="sub_module_1_6">Department
													Details</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="DESDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="adminMenu.html?method=designationList" id="sub_module_1_7">Designation
													Details</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SUBDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="adminMenu.html?method=subjectdetails" id="sub_module_1_8">Subject
													Details</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SUBDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="adminMenu.html?method=laboratory" id="sub_module_1_9">Laboratory</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
								<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SUBDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="adminMenu.html?method=occupationDetails" id="sub_module_1_10">Occupation
													</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
								<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SUBDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="adminMenu.html?method=religionDetails" id="sub_module_1_11">Religion
													</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SUBDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="adminMenu.html?method=casteDetails" id="sub_module_1_12">Caste
													</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SUBDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="adminMenu.html?method=casteCategoryDetails" id="sub_module_1_13">Caste Category
													</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="TMTDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="adminMenu.html?method=gettimetable" id="sub_module_1_14">Time
													Table Management</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="AYPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=holidaymaster" id="sub_module_1_15">Holiday
											</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>


							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="AYPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=acdamicYearPlanList" id="sub_module_1_16">Late Coming Settings
											</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
							


							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="AYPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=changeBackground" id="sub_module_1_17">Change Background
											</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<%-- <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STUPROMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=studentIdCreation">
													Design ID Card</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							
							
							<%-- <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STUPROMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=rollnumbergenerationsettting" id="sub_module_1_18">
													General Settings</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							


						</ul>

					</div>
				</div>
			</div> 
<!-- ID CARD DESIGN PHASE begins---------------------------------------- -->
			<div class="panel panel-default" style="margin: -1px 0px;">
				<div class="panel-heading leftNav" role="tab" id="headingTwo"
					style="background: #07AAB9;">
					<h4 class="panel-title">
						<a href="#"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapseTwo">
							<h3 class="panel-title active" style="color: #fff;">
								Identity Card &nbsp;&nbsp;<i
									class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>

					</h4>
				</div>
				
				
				
				
				
				<div id="menucollapseTwo" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo" >
				
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">





							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STFIDDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=DesignStaffIDCard" id="sub_module_1_19"> Design Staff ID Card</a>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STUIDDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=NewstudentIdCardDesign" id="sub_module_1_20">	Design Student ID Card</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="TRNSIDDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=DesignTransportIDCard" id="sub_module_1_21">Design Transport ID Card</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
					
							
							
							<%-- 
									<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STFIDSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=StaffSingleIDCardDesign" id="sub_module_1_22">  Print Staff ID Card - Single</a>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							
												<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STFMULIDDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=printMultiStaffIDCardDesign" id="sub_module_1_23">  Print Staff ID Card</a>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							

							

							<!-- <li><a href="adminMenu.html?method=remainderdetails">Remainder Details</a></li> -->

							<%-- <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STUIDSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

								<li><a href="adminMenu.html?method=DesignStudentSingleIDCard" id="sub_module_1_24">Print Student ID Card - Single</a>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							
									<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUIDMULDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=PrintStudentMultipleIDCard" id="sub_module_1_25">Print Student ID Card</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
					
					<%-- <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="TRNSIDSDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=DesignTransportIDCardSingle" id="sub_module_1_26">Print Transport ID Card - Single</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						 --%>
									<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="TRNSIDMULDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=PrintTransportMultipleIDCard" id="sub_module_1_27">Print Transport ID Card</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
						</ul>

					</div>
				</div>
			</div>
<!-- END ID CARD DESIGN PHASE-------------------------------------------------------------------------- -->
			<div class="panel panel-default" style="margin: -1px 0px;">
				<div class="panel-heading leftNav" role="tab" id="headingThree"
					style="background: #07AAB9;">
					<h4 class="panel-title">
						<a href="#"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapseTwo">
							<h3 class="panel-title active" style="color: #fff;">
								Activities &nbsp;&nbsp;<i
									class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>

					</h4>
				</div>
				<div id="menucollapseThree" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="IJPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=careerupdate" id="sub_module_1_28">Internal
													Job Posting</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>


							<!-- <li><a href="adminMenu.html?method=remainderdetails">Remainder Details</a></li> -->

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="ACCYRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=academicyear" id="sub_module_1_29">Academic
													Year Details</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
									<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STRDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=roleList" id="sub_module_1_30">Role
												Master</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
					
						</ul>

					</div>
				</div>
			</div>


			<div class="panel panel-default" style="margin: -1px 0px;">
				<div class="panel-heading leftNav" role="tab" id="headingFour"
					style="background: #07AAB9;">
					<h4 class="panel-title">
						<a href="#"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapseFour">
							<h3 class="panel-title active" style="color: #fff;">
								Permissions &nbsp;&nbsp;<i
									class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>

					</h4>
				</div>
				<div id="menucollapseFour" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingFour">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">



							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="RPMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a
												href="adminMenu.html?method=getUserRolePermission" id="sub_module_1_31">Assign
													Permissions</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="PWMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="adminMenu.html?method=getUserRecords" id="sub_module_1_32">Password
													Maintenance</a></li>
													
													<li><a href="javascript:void(0)" id="sub_module_1_101">ETL</a></li>

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

	<script>
		
							$(document)
									.ready(
											function() {
							$("#sub_module_1_101").click(function(){
								$.ajax({
									type:"POST",
									url:"adminMenu.html?method=ETLM",
									async:false,
									success:function(response){
										
									}
								});
							});				
												
												
							$(".leftNav").click(function(e) {
								$("div[id^='menucollapse']").not($(this).next("div[id^='menucollapse']")).removeClass("in");
								$(this).next("div[id^='menucollapse']").toggleClass("in");
								
							});
							
						});
	</script>

</body>
</html>
