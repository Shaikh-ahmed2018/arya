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

<script src="JS/newUI/jquery.js"></script>
<script src="JS/newUI/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>

<script type="text/javascript"
	src="JS/backOffice/Teacher/LeaveRequest.js"></script>

<!-- <script type="text/javascript"
	src="JS/backOffice/Teacher/LeaveRequestTeacher.js"></script>  -->

<title>eCampus Pro</title>

<script type="text/javascript">

	function handle(e){
	
	var searchText = $("#searchterm").val();
    if(e.keyCode === 13){
        e.preventDefault(); 

        window.location.href ="teachermenuaction.html?method=leaveRequest&searchTerm="
			+ searchText;
    }
}

</script>

<style>
#plus:hover {
	cursor: pointer;
}

#editID:hover {
	cursor: pointer;
}

#delete:hover {
	cursor: pointer;
}

.download:hover {
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

	<div id="dialog" style="display:none;"><p>Are You Sure to Cancel?</p></div>
	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">
			<p style="">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Leave Request Details</span>
			</p>
		</div>

		<div class="input-group col-md-4" style="margin-top:15px ;margin-bottom: 9px;">

			<input type="text" class="form-control" Placeholder="Search......" onkeypress="handle(event)" style = "height:35px;" id="searchterm" value="<logic:present name="searchterm"><bean:write name="searchterm"/></logic:present>" /> 
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span>


			<div class="form-group">
			</div>
		</div>


		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips"></span></a>
			</div>
		</div>

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span class="validateTips"></span></a>
			</div>
		</div>

		<input type="hidden" name="userhidden" class="userhiddenclass" id="userhiddenid" value='<logic:present name="parentid"><bean:write name="parentid" /></logic:present>'></input>
		
		<input type="hidden" name="searchterm" class="searchtermclass" id="searchterms" value='<logic:present name="searchterm"><bean:write name="searchterm" />
		</logic:present>'></input>

		 <input type="hidden" name="hleavelist" id="leavestatusid" value=''/>	

		<input type="hidden" name="attnhidden" id="snoid" value="" /> <input
			type="hidden" name="attnhidden1" id="requesttoid" value="" />

		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3 class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Leave
						Request Details
					</h3></a>
				<div class="navbar-right">
					<!--  <a href="parentMenu.html?method=requestLeavescreenadd"  id="plus" > -->
					<a onClick='sendrequest()' id="plus"> <span
						class="buttons">Add</span>
					</a>
					<!--  <a href="teachermenuaction.html?method=requestLeavescreen" id="editID"> -->
					<a onClick='editequest()' id="editID"> <span
						class="buttons">Edit</span>
					</a>
					<!--  </a> -->

					<!--  <a href="teachermenuaction.html?method=leaveRequest">  -->
					<span id="delete" class="buttons">Cancel</span>
					<!--  </a> -->

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

					<%-- <logic:present name="leavelist" scope="request">
						<display:table class="meeting table" pagesize="10"
							name="requestScope.leavelist"
							requestURI="teachermenuaction.html?method=leaveRequest"
							id="allstudent">

							<display:column title="Select" headerClass="heading1" class='selectall'>
								<input type="checkbox" name="getempid" class="select"
									onClick='getvaldetails(this)' value="${allstudent.status}"
									id="${allstudent.sno}"> </>
							</display:column>
							
							<display:column property="requestto" sortable="true"
								title="Staff Name<img src='images/sort1.png' style='float: right'/>"></display:column>
							
							<display:column property="leavetype" sortable="true"
								title="Leave Type<img src='images/sort1.png' style='float: right'/>"></display:column>
							
							<display:column property="totalleave" sortable="true"
								title="No Of Leaves<img src='images/sort1.png' style='float: right'/>"></display:column>
							
							<display:column property="fromdate" sortable="true"
								title="Start Date<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="todate" sortable="true"
								title="End Date<img src='images/sort1.png' style='float: right'/>"></display:column>
							
							<display:column property="status" sortable="true"
								title="Leave Status<img src='images/sort1.png' style='float: right'/>"></display:column>
							
							<display:column property="leavesapproved" sortable="true"
								title="Leaves Approved<img src='images/sort1.png' style='float: right'/>"></display:column>
							
								id="${tableid.yearvalcode},${tableid.status},${tableid. }"	


								<display:column property="check" sortable="true"
								title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" /> 
							 <display:column property="check" sortable="true" title='Select'></display:column>

							<display:column property="requestby" sortable="true"
								title="RequestBy <img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="requestto" sortable="true"
								title="RequestTo<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="starttime" sortable="true"
								title="Start Time<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="endtime" sortable="true"
								title="End Time<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

							<display:setProperty name="paging.banner.placement"
								value="bottom"></display:setProperty>

							<display:column property="reason" sortable="true"
								title="Reason<img src='images/sort1.png' style='float: right'/>"></display:column>		
							<display:column property="description" sortable="true"
								title="Meeting Description<img src='images/sort1.png' style='float: right'/>"></display:column>
						</display:table>

					</logic:present> --%>

					<!-- <table class="table" id="allstudent">
						<tr>
							<th>Check</th>
							<th>Requested By</th>
							<th>No of Leaves</th>
							<th>Reason For Leave</th>
							<th>StartDate</th>
							<th>End Date</th>
							<th>Requested Date</th>
							<th>Leave Status</th>
							<th>Leave Type</th>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>chiru</td>
							<td>2</td>
							<td>fever</td>
							<td>11-11-2015</td>
							<td>12-11-2015</td>
							<td>10-11-2015</td>
							<td>NOT APPROVED</td>
							<td>SL</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>sathish</td>
							<td>2</td>
							<td>fever</td>
							<td>11-11-2015</td>
							<td>12-11-2015</td>
							<td>10-11-2015</td>
							<td>APPROVED</td>
							<td>SL</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Guru</td>
							<td>2</td>
							<td>fever</td>
							<td>11-11-2015</td>
							<td>12-11-2015</td>
							<td>10-11-2015</td>
							<td>NOT APPROVED</td>
							<td>SL</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>priya</td>
							<td>2</td>
							<td>fever</td>
							<td>11-11-2015</td>
							<td>12-11-2015</td>
							<td>10-11-2015</td>
							<td>NOT APPROVED</td>
							<td>SL</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>shiva</td>
							<td>2</td>
							<td>fever</td>
							<td>11-11-2015</td>
							<td>12-11-2015</td>
							<td>10-11-2015</td>
							<td>NOT APPROVED</td>
							<td>SL</td>
						</tr>
					</table> -->

					<!-- <ul class="pagination" style="float: right; margin: 0;">
						<li><a href="#">&laquo;</a></li>
						<li class="active"><a href="#">1</a></li>
						<li class=""><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul> -->
					
					
					<logic:present name="leavelist" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Select</th>
							<th>Staff Name</th>
							<th>Leave Type</th>
							<th>No Of Leaves</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Leave Status</th>
							<th>Leaves Approved</th>
							
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="leavelist" name="leavelist">
								<tr>
								<td><input type="checkbox" name="getempid" class="select" onClick='getvaldetails(this)' value='<bean:write name="leavelist" property='status'/>' id='<bean:write name="leavelist" property='sno'/>' > </></td>
								<td><bean:write name="leavelist" property='requestto'/></td>
								<td><bean:write name="leavelist" property='leavetype'/></td>
								<td><bean:write name="leavelist" property='totalleave'/></td>
								<td><bean:write name="leavelist" property='fromdate'/></td>
								<td><bean:write name="leavelist" property='todate'/></td>
								<td><bean:write name="leavelist" property='status'/></td>
								<td><bean:write name="leavelist" property='leavesapproved'/></td>
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


</body>
</html>