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
	src="JS/backOffice/Settings/Occupation.js"></script> 

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
#dialog p{
font-size: 13px;
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
					id="pageHeading">Occupation Details</span>
			</p>
		</div>


		<form id="myForm"
			action="" method="post">

			<div class="input-group col-md-4">
				<input type="text" name="searchname" id="searchname"
					class="form-control" Placeholder="Search......"
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
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />

													</logic:present>'></input>	
			
			
			
			
			
		</form>
</div>

		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Occupation Details
					</h3></a>



				<div class="navbar-right">
				
				<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="OCUCRE" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

					<a href="religionCastCasteCategory.html?method=addOccupation"><span
						class="buttons">Add</span> </a> 
						</logic:equal>
						</logic:equal>
						</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="OCUUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
						<span
						class="buttons" id="editOccupation">Edit</span>
						</logic:equal>
						</logic:equal>
						</logic:iterate>
						</logic:present>
						

                    <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="OCUDEL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
					<span class="buttons" id="delete">Delete</span>
					</logic:equal>
					</logic:equal>
					</logic:iterate>
					</logic:present>

						
					 <!-- <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span> -->


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
							<div id="dialog-confirm"></div>
					<logic:present name="religionList" scope="request">
						<%-- <display:table class="table" id="allstudent"
							name="religionList"
							requestURI="/adminMenu.html?method=occupationDetails">

								<display:column media="html"
							
								title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />"><input type='checkbox' name='select' class='select' value='${allstudent.occupationId}' />
								</display:column>

						
							<display:column property="occupation" sortable="true"
								title="Occupation<img src='images/sort1.png' style='float: right'/>" />
		
						</display:table> --%>
						
				<table class="table" id="allstudent">
					 <thead>
						<tr>
						<th><input type='checkbox' name='selectall' id='selectall' style='text-align:center' onClick='selectAll()'/></th>
						<th>Occupation</th>
						</tr>
						</thead>
					<tbody>
					<logic:iterate name='religionList' id="religionList">
						<tr>
						<td><input type='checkbox' name='select' class='select' style='text-align:center' id='<bean:write name='religionList' property="occupationId"/>' /></td>
						<td><bean:write name='religionList' property="occupation" /></td>
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

	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>