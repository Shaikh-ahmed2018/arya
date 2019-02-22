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
	src="JS/backOffice/Settings/Religion.js"></script> 

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
.ui-dialog .ui-dialog-buttonpane {
    text-align: left;
    border-width: 1px 0 0 0;
    background-image: none;
    margin: 0.5em 0 0 0;
 
}

</style>

</head>

<body>

<form id="myForm" action="adminMenu.html?method=religionDetails" method="post">
<div class="col-md-10 col-md-offset-2" id="div1">
<div id="dialog"></div>
	<div class="searchWrap clearfix">
	<div class="col-md-8" id="div2">
		<p>
			<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Religion Details</span>
		</p>
	</div>
	<div class="col-md-4" >
		<div class="input-group" style="margin: 10px 0px;">
			<input type="text" name="searchname" id="searchname" style="height: 35px;"
					class="form-control" Placeholder="Search......"
					value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'> 	
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" id="search"
						onclick="myFunction()" value="Submitform" style="margin-right: -13px;">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
			<input type="hidden" name="searchterm" class="searchtermclass" id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" /></logic:present>'></input>	
	</div>
</div>
	
		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item" style="margin-left: 10px;">
				<a href="#" class="msg-success bg-msg-succes"><span	class="validateTips"></span></a>
			</div>
		</div>
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item" style="margin-left: 10px;">
				<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips"></span></a>
			</div>
		</div>

	
	<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				
					<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" ><h3	class="panel-title" style="color: #767676;">
					<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Religion Details</h3></a>

				<div class="navbar-right">
                    
                       <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="RELCRE" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
					<a href="religionCastCasteCategory.html?method=addReligion"><span class="buttons">Add</span> </a>
					</logic:equal>
					</logic:equal>
					</logic:iterate>
					</logic:present> 
					
					
					   <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="RELUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
					<span class="buttons" id="editReligion">Edit</span>
					</logic:equal>
					</logic:equal>
					</logic:iterate>
					</logic:present>

                       <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="RELDEL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
					<span class="buttons" id="delete">Delete</span>
					</logic:equal>
					</logic:equal>
					</logic:iterate>
					</logic:present>


					 <!-- <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span> -->


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

					<logic:present name="religionList" scope="request">
						
				<table class="table" id="allstudent">
					 <thead>
						<tr>
						<th><input type='checkbox' name='selectall' id='selectall' style='text-align:center' onClick='selectAll()'/></th>
						<th>Religion</th>
						</tr>
						</thead>
					<tbody>
					<logic:iterate name='religionList' id="religionList">
						<tr>
						<td><input type='checkbox' name='select' class='select' style='text-align:center' id='<bean:write name='religionList' property="religionId"/>' /></td>
						<td><bean:write name='religionList' property="religion" /></td>
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
</form>
</body>
</html>