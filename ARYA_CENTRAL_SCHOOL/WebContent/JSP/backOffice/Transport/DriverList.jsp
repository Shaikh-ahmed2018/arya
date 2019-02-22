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
<script type="text/javascript" src="JS/backOffice/Transport/DriverEditingPage.js"></script> 

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


<style>
.glyphicon:hover{

cursor: pointer;
}

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

	<div class="col-md-10 col-md-offset-2" id="div1">
	<div id="dialog"></div>
	               <div class="searchWrap">
	
		<div class="col-md-8" id="div2">

			<p>
				<span class="glyphicon glyphicon-user" ></span>&nbsp;&nbsp;<span id="pageHeading">Drivers List</span>
			</p>
		</div>
		
		
		<form id="myForm" action="driverDetailsPath.html?method=searchDriverDetails" method="post">
		<div class="input-group col-md-4 ">
			<input type="text" name="searchname" id="searchname" class="form-control" Placeholder="Search......"style="height: 35px;"
			value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'>
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search" onclick="myFunction()"value="Submitform">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
		</form>
		</div>
			


	<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>
	<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		
		
		<div class="errormessagediv1" align="center"
				style="display: none; text-align: center;">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 37%; font-size: 10pt; color: red;"><span
						class="validateTips1"></span></a>
				</div>
			</div>

		<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
		<div class="panel panel-primary">
			<div class="panel-heading" role="tab" id="headingOne">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Driver
						Details
					</h3></a>
					
				
				<div class="navbar-right" >
					<a href="driverDetailsPath.html?method=addDriver"><span class="buttons">Add</span></a>
						<span class="buttons" id="editdriver">Edit</span>
						<span class="buttons" id="delete">Delete</span>
						
						<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 >Download </span>
						
				</div>
				
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
					
				
				<%-- 	<logic:present name="driverList" scope="request">
					<div style="margin-top: 12px;">
							<display:table class="table" 
								name="requestScope.driverList"
								requestURI="adminMenu.html?method=driverList"
								decorator="com.centris.campus.decorator.DriverMasterDecorator"
								id="allstudent" >

							

								<display:column style="text-align:center"
									title="<input type='checkbox' name='selectall' style='text-align:center' id='selectall'  />">
									<input type='checkbox' name='select' class='select'
										style='text-align: center' id='${allstudent.driverCode }' />
								</display:column>

								<display:column property="driverName" sortable="true"
									title="Driver Name <img src='images/sort1.png' style='float: right'/>"></display:column>
								<display:column property="dateofJoin" sortable="true"
									title="Date Of Joining <img src='images/sort1.png' style='float: right'/>"></display:column>
								<display:column property="mobile" sortable="true"
									title="Mob NO <img src='images/sort1.png' style='float: right'/>"></display:column>
								<display:column property="drivingliecenseNo" sortable="true"
									title="Driving License No <img src='images/sort1.png' style='float: right'/>"></display:column>
								<display:column property="dl_issued_date" sortable="true"
									title="DL Issued Date<img src='images/sort1.png' style='float: right'/>"></display:column>
								<display:column property="dl_validity" sortable="true"
									title="DL Experied Date<img src='images/sort1.png' style='float: right'/>"></display:column>



							</display:table>
</div>
						</logic:present> --%>
						
						<logic:present name="driverList" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' name='selectall' style='text-align:center' id='selectall'  /></th>
							<th>Driver Name</th>
							<th>Date Of Joining</th>
							<th>Mob NO</th>
							<th>Driving License No</th>
							<th>DL Issued Date</th>
							<th>DL Experied Date</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="driverList" name="driverList">
								<tr>
								<td><input type='checkbox' name='select' class='select' style='text-align: center' id='<bean:write name="driverList" property='driverCode'/>'/></td>
								<td><bean:write name="driverList" property='driverName'/></td>
								<td><bean:write name="driverList" property='dateofJoin'/></td>
								<td><bean:write name="driverList" property='mobile'/></td>
								<td><bean:write name="driverList" property='drivingliecenseNo'/></td>
								<td><bean:write name="driverList" property='dl_issued_date'/></td>
								<td><bean:write name="driverList" property='dl_validity'/></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
						
					
					<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
						</div><div class='pagination pagelinks'></div>
				
				</div>
			
			</div>
		</div>
	</div>
</div>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>