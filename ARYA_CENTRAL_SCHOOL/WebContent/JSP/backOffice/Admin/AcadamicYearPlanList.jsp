<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>eCampus Pro</title>
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Admin/AcadamicYearPlanList.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet"
	type="text/css">
	
	
	<script type="text/javascript">

function handle(e){
	
	
	var searchText = $("#searchname").val();
    if(e.keyCode === 13){
        e.preventDefault(); 
        
        window.location.href="adminMenu.html?method=acdamicYearPlanList&searchTerm="+searchText+"&school="+$("#school").val();
		
    }
}




</script>
</head>

<style>
.glyphicon:hover {
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

.navbar-right span {
	margin-right: 3px;
}
</style>
<body>
	<div class="col-md-10 col-md-offset-2" id="div1">

		<div id="dialog">
			
		</div>

		<div class="searchWrap">
			<div class="col-md-8" id="div2">

				<p 	>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Activities Plan Details</span>
				</p>


			</div>
			<%-- <div align="right" class="input-group col-md-4">


				<input type="text" class="form-control" Placeholder="Search......"
					id="searchterm"
					value="<logic:present name="searchTerm" scope="request"><bean:write name="searchTerm"></bean:write></logic:present>">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" id="search">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div> --%>
			
			
			<div class="input-group col-md-4" >
				<input type="text" name="searchname" id="searchname" onkeypress="handle(event)"
					class="form-control" Placeholder="Search......"
					value='<logic:present name="searchTerm"><bean:write name="searchTerm"/></logic:present>'> 	
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" id="search" onclick="myFunction()" value="Submitform">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</div>



		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />

													</logic:present>'></input>






		<div class="errormessagediv"
			style="display: none; margin-left: 0%; width: 100%;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"
					style="text-align: center;"><span class="validateTips"></span></a>
			</div>
		</div>


		<div class="successmessagediv"
			style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"
					style="text-align: center;"><span class="successmessage"
					style="text-align: center;"></span></a>
			</div>
		</div>


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Activities
						Plan Details
					</h3></a>

				<div class="navbar-right">
				
                     <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="ACTCRE" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
					<a href="acadamicYearPlan.html?method=getAcadamicYearPlanEntry">
                    <span class="buttons">Add </span>
					</a>
					</logic:equal>
					</logic:equal>
					</logic:iterate>
					</logic:present>
					
					 <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="ACTUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
					 <span class="buttons" id="Edit">Edit </span> 
					 </logic:equal>
					 </logic:equal>
					 </logic:iterate>
					 </logic:present>
					 
					  <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="ACTDEL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
					 <span class="buttons"
						id="delete">Delete</span>
						</logic:equal>
						</logic:equal>
						</logic:iterate>
						</logic:present>
						 <!-- <span class="buttons" id="iconsimg"
						style="margin-left: -6px;" data-toggle="modal"
						data-target="#myModal">Download </span> -->

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


			<div id="collapseOne" class="accordion-body collapse in" style="margin-bottom:-40px;">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
         
         <logic:present name="AcadamicYearPlanList">

			<table class="table" id="allstudent">
					 <thead>
						<tr>
						<th><input type='checkbox' name='selectall' style='text-align:center' id='selectall'/></th>
						<th>School Name</th>
						<th>Teacher Type</th>
						<th>Late Time</th>
						<th>Early Time</th>
						</tr>
						</thead>
					<tbody>
					<logic:iterate name='AcadamicYearPlanList' id="AcadamicYearPlanList">                       
						<tr>
						<td><input type='checkbox' name='select' class='select' style='text-align:center' id='<bean:write name='AcadamicYearPlanList' property="eventid"/>'/></td>
						<td><bean:write name='AcadamicYearPlanList' property="locationName" /></td>
						<td><bean:write name='AcadamicYearPlanList' property="title" /></td>
						<td><bean:write name='AcadamicYearPlanList' property="starttime" /></td>
						<td><bean:write name='AcadamicYearPlanList' property="endtime" /></td>
						</tr>
					</logic:iterate>
					</tbody>
			</table>
			
						<div class='pagebanner'>
						<select id='show_per_page'>
						<option value='50'>50</option><option value='100'>100</option>
						<option value='200'>200</option><option value='300'>300</option>
						<option value='400'>400</option><option value='500'>500</option>
						</select>
							<span  class='numberOfItem'></span>	
						</div>
							<div class='pagination pagelinks'></div>

						</div>
						</logic:present>
				<br />
			</div>
		</div>
	</div>
	<!-- 	<script src="JS/newUI/jquery.js"></script>
	<script src="JS/newUI/bootstrap.min.js"></script> -->
	<!-- <script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script> -->
</body>
</html>
