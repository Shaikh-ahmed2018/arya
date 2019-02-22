<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">  

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>eCampus Pro</title>

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Transport/VehicleList.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
<script type="text/javascript">


</script>

<style>
.glyphicon:hover {
	cursor: pointer;
}
/* .modal-body {
	text-align: center;
} */






</style>

<style>
.download:hover{

cursor: pointer;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
	 <div id="dialog"></div>
	<div class="searchWrap">
		<div class="col-md-8" id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Vehicle
					Details</span>
			</p>
		</div>


			<form id="myForm" action="adminMenu.html?method=vehicleList"
				method="post">

				<div class="input-group col-md-4">
					<input type="text" name="searchname" id="searchname"style="height: 35px;" class="form-control"Placeholder="Search......"
						value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'>
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" id="search"
							onclick="myFunction()" value="Submitform">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>

				<input type="hidden" name="searchterm" class="searchtermclass"
					id="searchexamid"
					value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" /></logic:present>'></input>

			</form>
		</div>

		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		
		

		<!-- <div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				Warning
				<span
					class="validateTips"><a href="#" class="msg-success bg-msg-succes"></span></a>
			</div>
		</div> -->
		
		
		<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a href="#" style="color: #fff;"><h3
						class="panel-title" id="vehicle" style="color: #767676; vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Vehicle 
						Details
					</h3></a>
				<div class="navbar-right" >

					<a href="transport.html?method=addvehicledetails"> 
						<span class="buttons">Add</span>	</a>
						<span class="buttons" id="editVehicle" style="cursor: pointer">Edit</span>
						<span class="buttons" id="deleteVehicle">Delete</span>

				</div>
			</div>
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


			<div id="vehicleclose" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<!-- <table class="table" id="allstudent"> -->


					<%-- <logic:present name="getvehiclelist" scope="request">
						<display:table class="table" id="allstudent" name="getvehiclelist"
							decorator="com.centris.campus.decorator.VehicleDecorator"
							
							requestURI="adminMenu.html?method=vehicleList">

						 <display:column property="check" sortable="true"
								title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" /> 
								
								 <display:column  
									
									title="<input type='checkbox' name='selectall' style='text-align:center' id='selectall'/>"><input type='checkbox' name='select' class='select' style='text-align:center' id='${allstudent.vehiclecode}'  /></display:column> 
								
								
								
								
							<display:column property="vehicleregno" sortable="true"
								title="Registration No<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="vehiclename" sortable="true"
								title="Vehicle Name<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="enginenumber" sortable="true"
								title="Engine Number<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="vehicletype" sortable="true"
								title="Vehicle Type<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="taxpaid" sortable="true"
								title="Tax Issued Date<img src='images/sort1.png' style='float: right'/>" />
								
								<display:column property="taxexpirydate" sortable="true"
								title="Tax Expiry Date<img src='images/sort1.png' style='float: right'/>" />
								
					 		
					

						</display:table>
						
						<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
							<span  class='numberOfItem'></span>	
							</div><div class='pagination pagelinks'></div>
					
					</logic:present> --%>
					
					<logic:present name="getvehiclelist" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' name='selectall' style='text-align:center' id='selectall'/></th>
							<th>Registration No</th>
							<th>Vehicle Name</th>
							<th>Engine Number</th>
							<th>Vehicle Type</th>
							<th>Tax Issued Date</th>
							<th>Tax Expiry Date</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="getvehiclelist" name="getvehiclelist">
								<tr>
								<td><input type='checkbox' name='select' class='select' style='text-align:center' id='<bean:write name="getvehiclelist" property='vehiclecode'/>' /></td>
								<td><bean:write name="getvehiclelist" property='vehicleregno'/></td>
								<td><bean:write name="getvehiclelist" property='vehiclename'/></td>
								<td><bean:write name="getvehiclelist" property='enginenumber'/></td>
								<td><bean:write name="getvehiclelist" property='vehicletype'/></td>
								<td><bean:write name="getvehiclelist" property='taxpaid'/></td>
								<td><bean:write name="getvehiclelist" property='taxexpirydate'/></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
					<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
							<span  class='numberOfItem'></span>	
							</div><div class='pagination pagelinks'></div>
					
					
					
				</div>
				<br />
			</div>
		</div>
	</div>
<!-- <script src="JS/newUI/jquery.js"></script>
	<script src="JS/newUI/bootstrap.min.js"></script> -->
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>