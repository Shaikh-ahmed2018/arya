
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
<script type="text/javascript" src="JS/backOffice/Transport/EditFuelMaintenance.js"></script> 
<style>
.glyphicon:hover{

cursor: pointer;
}

</style>
<style>

#excelDownload :hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
#iconsimg:hover {
	cursor: pointer;
}


</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">	<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Fuel Maintenance Details</span>
			</p>
		</div>
		
		
		
			
			<form id="myForm" action="fuelMenu.html?method=searchFuelDetails" method="post">
		<div class="input-group col-md-4" style="margin: 20px 20px;">

			<input type="text" class="form-control" name="searchname" id="searchname" Placeholder="Search......" 
			   />
		
			
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search" onclick="myFunction()" value="Submit form">
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
		
		
	<div class="errormessagediv" style="display: none; text-align: center;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips"></span></a>
							</div>
						</div>
			
					
						<div class="successmessagediv" style="display: none; text-align: center;	">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>
		
			
		
			
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;vertical-align: text;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Fuel Maintenance
						Details
					</h3></a>
				<div class="navbar-right" >
				
				<a href="fuelMenu.html?method=AddFuelDetails"><span
						class="buttons"
						 data-toggle="tooltip" data-placement="bottom" title="Add">Add</span></a>
						
						
					<span class="buttons" id="edit"
						style="cursor:pointer" data-toggle="tooltip" data-placement="bottom" title="Edit">Edit</span>
						
						
				   <span class="buttons" id="delete"
					style="cursor:pointer" data-toggle="tooltip" data-placement="bottom" title="Delete">Delete</span>
					
				
					   <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span>

				</div>
				<script>
							$(document).ready(function() {
								$('[data-toggle="tooltip"]').tooltip();
							});
						</script>
			</div>
			<!-- pop up -->

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Download</h4>
						</div>
						<div class="modal-body">
							<span id="excelDownload"><img src="images/xl.png"
								class="xl"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
								id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div>
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				
					
				<logic:present name="fuelList" scope="request">
						
						<display:table id="allstudent" name="fuelList" class="table" 
							requestURI="/adminMenu.html?method=fuelMaintenance" sort="list" export="false" pagesize="15" decorator="com.centris.campus.decorator.FuelMaintenanceDecorator">
						    
						   <display:column property="check" title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />"></display:column> 
							
							<display:column property="date" title="Fuel Date<img src='images/sort1.png' style='float: right'/>" sortable="true" headerClass="sortable"/>
							<display:column property="vehiclename" title="Vehicle Name<img src='images/sort1.png' style='float: right'/>" sortable="true" />
							<display:column property="vehiclenumber" title="Vehicle Number<img src='images/sort1.png' style='float: right'/>" sortable="true" />
							<display:column property="drivername" title="Driver Name<img src='images/sort1.png' style='float: right'/>" sortable="true" />
							<display:column property="fuelType" title="Fuel Type<img src='images/sort1.png' style='float: right'/>" sortable="true" />
							<display:column property="quantity" title="Quantity<img src='images/sort1.png' style='float: right'/>" sortable="true" />
							<display:column property="price" title="Price<img src='images/sort1.png' style='float: right'/>" sortable="true"/>
							<display:column property="meterReading" title="Meter Reading(km)<img src='images/sort1.png' style='float: right'/>" sortable="true" />
							<display:column property="location" title="Location<img src='images/sort1.png' style='float: right'/>" sortable="true"/>
							
					<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

					    	<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>
							
						</display:table>
					</logic:present>
				
						
					<!-- <p style="float: left; margin: 0;">&nbsp;&nbsp;Showing 1 to 10
						of 50 Entries</p>
					<ul class="pagination" style="float: right; margin: 0;">
						<li><a href="#">&laquo;</a></li>
						<li class="active"><a href="#">1</a></li>
						<li class=""><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul> -->
				</div>
				<br />
			</div>
		</div>
	</div>
	<script src="JS/newUI/jquery.js"></script>
	<script src="JS/newUI/bootstrap.min.js"></script>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script>
</body>
