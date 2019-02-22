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
<script type="text/javascript"
	src="JS/backOffice/Settings/AddDesignation.js"></script>
	
	<script type="text/javascript"> 

function handle(e){
	
	
	var searchText = $("#searchname").val();
    if(e.keyCode === 13){
        e.preventDefault(); // Ensure it is only this code that rusn

        
        window.location.href = "adminMenu.html?method=designationList&searchvalue="
			+ searchText;
		
    }
}




</script>





<title>eCampus Pro</title>
<style>
#editDesignationId:hover {
	cursor: pointer;
}

#deleteDesignationId:hover {
	cursor: pointer;
}

#xlss:hover {
	cursor: pointer;
}

.download:hover {
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


		<div class="col-md-8" id="div2">
			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Designation Details</span>
			</p>
		</div>


		<!-- <div class="input-group col-md-4" style="margin: 20px 0px;">

			<input type="text" class="form-control" id="searchvalue"
				Placeholder="Search......"> <span class="input-group-btn">
				<button class="btn btn-default" type="button" id="searchbutton">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div> -->

		<div class="input-group col-md-4" style="margin: 20px 0px;">
			<input type="text" name="searchname" id="searchname"
				onkeypress="handle(event)" class="form-control"
				Placeholder="Search......"
				value='<logic:present name="searchvalue"><bean:write name="searchvalue"/></logic:present>'>
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search"
					onclick="myFunction()" value="Submitform">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>



		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>



		<!-- 	<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">

				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessagediv"></span></a>
			</div>
		</div> -->

		<div class="successmessagediv"
			style="display: none;" align="center">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"
					style="text-align: center;"><span class="successmessage"
					style="text-align: center;"></span></a>
			</div>
		</div>


		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">

				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="errormessagediv1" align="center" style="display: none;">
			<div class="message-item1"></div>
		</div>












		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Designation
						Details
					</h3></a>
				<div class="navbar-right">


                 <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="DESCRE" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

					<a href="adminMenu.html?method=adddesignation"> <span
						class="buttons">Add</span>
						</a> 
						</logic:equal>
						</logic:equal>
						</logic:iterate>
						</logic:present>
						
						
                     <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="DESUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
					<span id="editDesignationId" class="buttons">Edit</span> 
					</logic:equal>
					</logic:equal>
					</logic:iterate>
					</logic:present>
					
					   <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="DESDEL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
					<span id="deleteDesignationId" class="buttons">Delete</span>
					</logic:equal>
					</logic:equal>
					</logic:iterate>
					</logic:present>



					<!--  <span class="glyphicon glyphicon-print" style="font-size: 20px; line-height: 34px; color: #989898;"></span>
					 <img src="images/rightline.png" style="margin-top: -5px;">
					 </a>  -->

					<!-- <span id="xlss" >
						 <img src="images/download.png" class="download"  data-toggle="tooltip" data-placement="bottom" title="Download" >
					 </span> -->


					<!-- <span class="buttons" id="iconsimg" data-toggle="modal"
						data-target="#myModal" data-toggle="tooltip"
						data-placement="bottom" title="Download">Download </span> -->





				</div>

				<script>
					$(document).ready(function() {
						$('[data-toggle="tooltip"]').tooltip();
					});
				</script>

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
							<span id="xlss"><img src="images/xl.png" class="xl"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
								id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div>






			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


					<logic:present name="DesignationDetails" scope="request">


					<table class="table" id="allstudent">
						<thead>
						<tr>
						<th><input type='checkbox' name='selectall' id='selectall' style='text-align:center' onClick='selectAll()'/></th>
						<th>Designation Name</th>
						<th>Description</th>
						</tr>
						</thead>
						<tbody>
						<logic:iterate name='DesignationDetails' id="DesignationDetails">
						<tr>
						<td><input type='checkbox' name='select' class='select' style='text-align:center' id='<bean:write name='DesignationDetails' property="desgid"/>' /></td>
						<td><bean:write name='DesignationDetails' property="desgname" /></td>
						<td><bean:write name='DesignationDetails' property="desgdes" /></td>
						</tr>
						</logic:iterate>
						</tbody>
						
						</table>
					
<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
	<span  class='numberOfItem'></span>	
	</div><div class='pagination pagelinks'></div>

					</logic:present>



				</div>
				<br />
			</div>
		</div>
	</div>

	<!-- <script src="JS/newUI/jquery.js"></script>
	<script src="JS/newUI/bootstrap.min.js"></script>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script> -->
</body>
</html>