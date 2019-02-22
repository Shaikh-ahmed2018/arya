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

<script type="text/javascript" src="JS/backOffice/Reports/leaveCategory.js"></script>
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


<title>eCampus Pro</title>
<style>
#editleavebank:hover {
	cursor: pointer;
}

#deleteleavebank:hover {
	cursor: pointer;
}
#addleavebank:hover {
	cursor: pointer;
}

#xlss:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
.download:hover {
	cursor: pointer;
}
.accyear{

	border : none;
	background: transparent;

}
#anchortag a:hover {
    text-decoration: underline !important;
    color : blue;
    font-weight: bold;
}

#allstudent th:nth-child(1) {
    text-align: center;
    width: 100px;
}


</style>
<script>

function handle(e){
	
	
	var searchText = $("#searchvalue").val();
    if(e.keyCode === 13){
        e.preventDefault(); // Ensure it is only this code that rusn

        window.location.href ="adminMenu.html?method=LeaveCategoryList&searchvalue="
			+ searchText;
    }
}

</script>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">
			<p style="">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Leave Type</span>
			</p>
		</div>


		<div class="input-group col-md-4" style="margin-top:15px;margin-bottom:10px;">

			<input type="text" class="form-control" id="searchvalue" onkeypress="handle(event)"
				Placeholder="Search......" style="height : 35px;" value ="<logic:present name="searchname"><bean:write name="searchname"/></logic:present>"> <span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>

	<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />
		</logic:present>'></input>	



		<logic:present name="successmessagediv"  scope="request">
			<div class="successmessagediv"  align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>



		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">

				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessagediv"></span></a>
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
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3 class="panel-title" style="color: #767676;vertical-align: text-top">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Leave
						Type Listing
					</h3></a>
				<div class="navbar-right">




					<span id="addleavebank" class="buttons">Add</span>

					<!-- </a> <span id="editleavebank" class="buttons"
						data-toggle="tooltip" data-placement="bottom" title="Edit">Edit</span>



					<span  id="deleteleavebank" onclick="deleteLeave()"  class="buttons"
						data-toggle="tooltip" data-placement="bottom" title="Delete">Delete</span>
 -->
<!-- onclick="deleteLeave()" -->

						<!-- <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span>
						  -->
						 
						 
				</div>

				<script>
					$(document).ready(function() {
						$('[data-toggle="tooltip"]').tooltip();
					});
				</script>

			</div>

			<input type="hidden" name="attnhidden" id="snoid" value="" />

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
				
				
					<%-- <logic:present name="LeaveListDetails" scope="request">
						
						<display:table class="table" id="allstudent"
							name="LeaveListDetails" pagesize="10"
							requestURI="adminMenu.html?method=LeaveCategoryList"
							decorator="">

							<display:column property="locationCheckBox" media="html"
								sortable="true"
								title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />"></display:column>

							<display:column property="count" sortable="true"
								title="Serial No<img src='images/sort1.png' style='float: right'/>" />

							
							
							<display:column sortable="true"
								title="Accademic Year<img src='images/sort1.png' style='float: right'/>"><a id = "anchortag"><input type='button' name='accyear' class='accyear' style='text-align:center' value ="${allstudent.academicyear}" id ="${allstudent.accyearcode},${allstudent.locId}"/></a></display:column>
							<display:column property="locationName" sortable="true"
								title="Location<img src='images/sort1.png' style='float: right'/>" />



						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>

						</display:table>
						
					</logic:present> --%>
					
					
					<logic:present name="LeaveListDetails" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Serial No</th>
							<th>Accademic Year</th>
							<th>Location</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="LeaveListDetails" name="LeaveListDetails">
								<tr>
								<td><bean:write name='LeaveListDetails' property="count"/></td>
								<td><input type='button' name='accyear' class='accyear' style='text-align:center' value ='<bean:write name="LeaveListDetails" property='academicyear'/>'  id='<bean:write name="LeaveListDetails" property='accyearcode'/> <bean:write name="LeaveListDetails" property='locId'/>'/></td>
								<td><bean:write name="LeaveListDetails" property='locationName'/></td>
								
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
	<script src="JS/newUI/bootstrap.min.js"></script>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script> -->
</body>
</html>