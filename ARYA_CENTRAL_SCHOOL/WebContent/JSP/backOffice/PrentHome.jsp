<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html> 
<head>

<link href="CSS/newUI/DashBoardCss/custome.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<style>

.panel-default {
    border-radius: 5px;
}

.panel-body {
    padding: 15px;
}

</style>
</head>



<body>

	<div class="row" style="margin:0;">
		<br/>
	
		<div class="col-md-10 col-md-offset-2" style="padding: 0 10px;">
			<div class="col-md-12 centertxt">
				<div class="row">
				<div class="col-md-12">
				<h1>PARENT DASHBOARD</h1>
				</div>
				</div>
			
				
				
				<div class="row">
				<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="STUATTDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">				
										
											<div class="col-md-4">
												<div class="panel panel-default"> 
												<a href="teachermenuaction.html?method=attendaceStatus">
														<h4>&nbsp;&nbsp;&nbsp;&nbsp;STUDENT ATTENDANCE</h4>
													<div class="panel-body">
														<img src="images/attandence.png" style="float:right;width:35%;"><br/><br/><br/><br/><br/>
														<p>&nbsp;&nbsp;&nbsp;&nbsp; Go To The Students Attendance...</p>
													</div>
													</a>
												</div>
											</div>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
				
				
				
					
							
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEECOLLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">				
							
									<div class="col-md-4">
										<div class="panel panel-default"> 
											<a href="feecollectionsummaryreport.html?method=getFeeCollectionSummaryDetails">
										
												<h4>&nbsp;&nbsp;&nbsp;&nbsp;FEE COLLECTION </h4>
											<div class="panel-body">
												<img src="images/recipt.png" style="float:right;width:30%;"><br/><br/><br/><br/><br/>
												<p>&nbsp;&nbsp;&nbsp;&nbsp; Easy To Take The Report</p>
											</div>
											</a>
										</div>
									</div>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
				
				</div>
				<div class="row">
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LEAVEAPPROVE" name="daymap" property="key">
									
										<logic:equal value="true" name="daymap" property="value">
				
										<div class="col-md-4">
											<div class="panel panel-default"> 
											<a href="teachermenuaction.html?method=leaveApproval">
													<h4>&nbsp;&nbsp;&nbsp;&nbsp;LEAVE APPROVAL</h4>
												<div class="panel-body">
													<img src="images/performance.png" style="float:right;width:30%;    margin-top: -20px;"><br/><br/><br/><br/><br/>
													<p>&nbsp;&nbsp;&nbsp;&nbsp; 50% Out Of 100%</p>
												</div>
												</a>
											</div>
										</div>
				
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
			
					
				
							
							
							
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="AYPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">					
									
									<div class="col-md-4">
										<div class="panel panel-default"> 
											<a href="adminMenu.html?method=meetingslist">
										
												<h4>&nbsp;&nbsp;&nbsp;&nbsp; MEETINGS & EVENTS </h4>
											<div class="panel-body">
												<img src="images/dairy.png" style="float:right;width:35%;"><br/><br/><br/><br/><br/>
												<p>&nbsp;&nbsp;&nbsp;&nbsp; Go To The Activities... </p>
											</div>
											</a>
										</div>
									</div>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CMSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">				
							
								<div class="col-md-4">
									<div class="panel panel-default"> 
								<a href="adminMenu.html?method=acdamicYearPlanList">
									
											<h4>&nbsp;&nbsp;&nbsp;&nbsp; CALENDER </h4>
										<div class="panel-body">
											<img src="images/meeting.png" style="float:right;width:38%;"><br/><br/><br/><br/><br/>
											<p>&nbsp;&nbsp;&nbsp;&nbsp; Go To The Meetings/Events...</p>
										</div>
										</a>
									</div>
								</div>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							</div>
			</div>
		</div>
	</div>
</body>
</html>