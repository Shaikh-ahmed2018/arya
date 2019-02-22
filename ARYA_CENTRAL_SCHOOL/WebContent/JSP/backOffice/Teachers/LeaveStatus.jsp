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

<script type="text/javascript"
	src="JS/backOffice/Teacher/LeaveApproval.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


<title>eCampus Pro</title>
<style>
#editleaveapproval:hover {
	cursor: pointer;
}

.download:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}

#excelDownload:hover {
	cursor: pointer;
}
</style>

<style>
.buttons{

vertical-align: 5px;

}
</style>
<script type="text/javascript">

function handle(e){
	
	var searchText = $("#searchterm").val();
    if(e.keyCode === 13){
        e.preventDefault(); 
        window.location.href ="teachermenuaction.html?method=leaveApproval&searchTerm="
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
					id="pageHeading">Leave Status details</span>
			</p>
		</div>
		

		<div align="right" class="input-group col-md-4"
			style="margin-bottom: 10px;margin-top: 15px;">
			<input type="text" class="form-control" Placeholder="Search......" onkeypress="handle(event)"
				id="searchterm"	style = "height:35px;">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>


	<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchid"
			value='<logic:present name="search"><bean:write name="search"/></logic:present>'></input>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>
		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">

				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips1"></span></a>
			</div>
		</div>

		<input type="hidden" name="attnhidden" id="snoid" value="" /> <input
			type="hidden" name="success" id="success"
			value="<logic:present name="success" ><bean:write name="success"/></logic:present>" />
			
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3 class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Leave
						Status Details
					</h3></a>
				<div class="navbar-right">
					<span id="editleaveapproval" class="buttons">Edit</span>

					 <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download </span>
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


					<%-- <logic:present name="leaveapproval" scope="request">


						<display:table id="allstudent" class="table"
							name="requestScope.leaveapproval" export="false"
							requestURI="/teachermenuaction.html?method=leaveApproval"
							pagesize="10"
							decorator="com.centris.campus.decorator.LeaveRequestDecorator">

							<display:column title="Select" headerClass="heading1">
								<input type="checkbox" name="getempid"
									onClick='getvaldetails(this)' value="Get Salary Details"
									id="${allstudent.sno}"> </>
							</display:column>
							<display:column property="requestby" sortable="true"
								title="Requested By<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="leavetype" sortable="true"
								title="Leave Type<img src='images/sort1.png' style='float: right'/>" /> 
							<display:column property="requesteddate" sortable="true"
								title="Requested Date<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="totalleave" sortable="true"
								title="No Of Leaves<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="fromdate" sortable="true"
								title="Start Date<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="todate" sortable="true"
								title="End Date<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="reason" sortable="true"
								title="Reason For Leave<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="status" sortable="true"
								title="Leave Status<img src='images/sort1.png' style='float: right'/>" />

							<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

							<display:setProperty name="paging.banner.placement"
								value="bottom"></display:setProperty>
						</display:table>


					</logic:present> --%>




                  <logic:present name="leaveapproval" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Select</th>
							<th>Requested By</th>
							<th>Leave Type</th>
							<th>Requested Date</th>
							<th>No Of Leaves</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Reason For Leave</th>
							<th>Leave Status</th>
							
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="leaveapproval" name="leaveapproval">
								<tr>
								<td><input type="checkbox" name="getempid" class="select" onClick='getvaldetails(this)' value='Get Salary Details' id='<bean:write name="leaveapproval" property='sno'/>'></td>
								<td><bean:write name="leaveapproval" property='requestby'/></td>
								<td><bean:write name="leaveapproval" property='leavetype'/></td>
								<td><bean:write name="leaveapproval" property='requesteddate'/></td>
								<td><bean:write name="leaveapproval" property='totalleave'/></td>
								<td><bean:write name="leaveapproval" property='fromdate'/></td>
								<td><bean:write name="leaveapproval" property='todate'/></td>
								<td><bean:write name="leaveapproval" property='reason'/></td>
								<td><bean:write name="leaveapproval" property='status'/></td>
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