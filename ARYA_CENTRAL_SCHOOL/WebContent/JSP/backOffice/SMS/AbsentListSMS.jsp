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
<link rel="stylesheet"	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script> 
<script type="text/javascript"
	src="JS/backOffice/SMS/AbsentSMSList.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">



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
<style>
.buttons{

vertical-align:-28px;

}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Absent List Details</span>
			</p>
		</div>


		<form id="myForm"
			action="adminMenu.html?method=streamList" method="post">

			<%-- <div class="input-group col-md-4" style="margin: 20px 0px;">
				<input type="text" name="searchname" id="searchname"
					class="form-control" Placeholder="Search......"
					value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'>
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" id="search"
						onclick="myFunction()" value="Submitform">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div> --%>
			
			<br /><br />
			
		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" /></logic:present>'></input>
		</form>
		

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
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Absent List 						
					</h3></a>



				<div class="navbar-right">

					<span id="add" class="buttons" 
					data-placement="bottom" >Add</span> 

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


			<div id="collapseOne" class="accordion-body collapse in clearfix">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

			<%-- 		<logic:present name="absentList" scope="request">
						<display:table class="table" id="allstudent"
							name="absentList" pagesize="10"
							requestURI="/adminMenu.html?method=absentlist" 	>

							
							<display:column property="streamId" title="Stream Id"></display:column>
							
							<display:column title="Select" headerClass="heading1">
							<input type="checkbox" name="getempid" onClick='getvaldetails(this)' value=""
							> </>
							</display:column>
							
							
							<display:column title="<input type='checkbox' name='selectall' id='selectall'/>" headerClass="heading1" media="html" sortable="true">
							<input type='checkbox' name='selectall' class='select' id='select' onClick='selectAll()' />
							</display:column>	
							
							
							
							<display:column property="date" sortable="true"
								title="Absent Date<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="studentName" sortable="true"
								title="Student Name<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="classname" sortable="true"
								title="Class<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="section" sortable="true"
								title="Section<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="smstext" sortable="true"
								title="SMS Text<img src='images/sort1.png' style='float: right'/>" />


					


						</display:table>

					</logic:present> --%>
            <logic:present name="absentList" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' name='selectall' id='selectall'/></th>
							<th>Absent Date</th>
							<th>Student Name</th>
							<th>Class</th>
							<th>Section</th>
							<th>SMS Text</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="absentList" name="absentList">
								<tr>
								<td><input type='checkbox' name='selectall' class='select' id='select' onClick='selectAll()' /></td>
								<td><bean:write name="absentList" property='date'/></td>
								<td><bean:write name="absentList" property='studentName'/></td>
								<td><bean:write name="absentList" property='classname'/></td>
								<td><bean:write name="absentList" property='section'/></td>
								<td><bean:write name="absentList" property='smstext'/></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
            	</div>
				<div class="pagebanner"><select id="show_per_page"><option value="50">50</option><option value="100">100</option><option value="200">200</option><option value="300">300</option><option value="400">400</option><option value="500">500</option></select>
					<span class="numberOfItem"></span>	
				</div>
				<div class="pagination pagelinks"></div>
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