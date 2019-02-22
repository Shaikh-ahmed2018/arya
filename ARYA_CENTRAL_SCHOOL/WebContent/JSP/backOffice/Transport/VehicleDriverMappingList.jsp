
<!DOCTYPE html> 

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<title>eCampus Pro</title>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Transport/VehicleDriverMapping.js"></script> 

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">	<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Vehicle Driver Mapping</span>
			</p>
		</div>
		
		
		
			
			<form id="myForm" action="fuelMenu.html?method=searchFuelDetails" method="post">
		<div class="input-group col-md-4" style="margin: 20px 20px;">

			<input type="text" class="form-control" name="searchname" id="search" Placeholder="Search......" 
			 value="<logic:present name="SerchTerm"><bean:write name="SerchTerm"></bean:write></logic:present>"/>
		
			
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="searchname">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
		</form>
		
		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>
		
		
				<div class="errormessagediv" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips"></span></a>
							</div>
						</div>
			
					
						<div class="successmessagediv" style="display: none;">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>
		
			
		
		
			
			<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676; vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Vehicle Driver Mapping
						
					</h3></a>
			
				<div class="navbar-right" >
				
				<a href="vehicledrivermap.html?method=addVehicleDriverMapping">
					<span class="buttons">Add</span>
				</a>
						
				
				<span class="buttons" id="edit" style="cursor:pointer">Edit</span>
						
				<span class="buttons" id="delete" style="cursor:pointer">Delete</span>
					
				<span id="xls" class="buttons" >Download</span>

				</div>
			</div>
			
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				
					
				<logic:present name="VehicleDriverMapList" scope="request">
						
						<display:table id="allstudent" name="VehicleDriverMapList" class="table" 
							requestURI="/adminMenu.html?method=getVehicleDriverMapping" sort="list" export="false" pagesize="15" decorator="com.centris.campus.decorator.FuelMaintenanceDecorator">
						    
						<display:column
							title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />">
							<input type="checkbox" name="selectBox" id="selectBox" value="${allstudent.sno}" />
						</display:column>
							
							<%-- <display:column property="sno" title="Sno" sortable="true" headerClass="sortable"/> --%>
							<display:column property="vehiclename" title="Vehicle Name" sortable="true" />
							<display:column property="driverName" title="Driver Name" sortable="true" />
							
						</display:table>
					</logic:present>
				
				</div>
				<br />
			</div>
		</div>
	</div>

</body>
