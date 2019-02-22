<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<link href="CSS/newUI/custome.css" rel="stylesheet">

<title>eCampus Pro</title>
<body>
		<div class="col-md-2 leftmenu">
			
			<div class="panel panel-primary" style="border: none;background-color: transparent;	margin: 1px 0px;box-shadow: none;">
				<div class="panel-heading leftNav" style="background-color: #07AAB9;">
					<a data-toggle="collapse" data-parent="#accordion2" href="#menucollapseOne" style="color:#fff;"><h3 class="panel-title active" >Exam Management<i class="glyphicon glyphicon-triangle-bottom dropdowns" ></i></h3></a>		
				</div>
				<div id="menucollapseOne" class="accordion-body collapse in">
					<div class="panel-body" >
						<ul class="nav nav-pills nav-stacked">
							
							<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="adminMenu.html?method=examList">Exam Details</a></li>
											</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							
							<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EXMTMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
											<li><a href="examTimetablePath.html?method=getExamDetails">Exam Timetable</a></li>
											
											</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
									
									
							<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="parentMenu.html?method=examdetails">View Exam Details</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="adminMenu.html?method=gradeList" id="sub_module_5_1">Grade Setting</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="examTimetablePath.html?method=gradeDependency" id="sub_module_5_2">Grade Dependency</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="examTimetablePath.html?method=getEaxmListYear" id="sub_module_5_3">Exam Setting</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="examTimetablePath.html?method=examdependency" id="sub_module_5_4">Exam Dependency</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							--%>
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="examTimetablePath.html?method=getEaxmTimeTableListYear" id="sub_module_5_5">Exam Timetable</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> 
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="examTimetablePath.html?method=getExamMarksStudentwise" id="sub_module_5_6">Exam Marks-Studentwise</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="uploadStudentXSL.html?method=excelUploadForStudentsMarks" id="sub_module_5_8">Excel Upload</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
						<%--	<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="examTimetablePath.html?method=subjectmarksList" id="sub_module_5_7">Exam Marks-Subjectwise</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							

						 <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUPROMDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="adminMenu.html?method=studentPromotionList">
												Promotion</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> --%>
							
							
							
							<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="">Revision Question Bank</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							
							
								
				
						</ul>
						
					</div>
				  <br/>
				</div>
			</div>
			
		</div>
</body>
</html>
