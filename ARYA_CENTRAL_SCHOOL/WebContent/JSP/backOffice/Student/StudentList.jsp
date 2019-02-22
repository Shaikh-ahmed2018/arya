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

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Student/StudentRegistrationlist.js"></script>
<script type="text/javascript">
function handle(e){
	var searchText = $("#searchvalue").val().trim();
    if(e.keyCode === 13){
        e.preventDefault(); // Ensure it is only this code that rusn
        window.location.href = "adminMenu.html?method=studentList&searchname="+ searchText;
    }
}
</script>

<style>
#editStudent:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
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
	<div class="searchWrap">
		<div class="" id="div2">
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Student Registration</span>
			</p>
		</div>
		
	</div>
		
		<input type="hidden" id="succmsg" value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />
		
			<div id="successmessages" class="successmessagediv" style="display: none;">
				<div class="message-item">
					 <a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"><logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present></span></a>
				</div>
			</div>
		
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><logic:present name='errorMessage'  scope='request' ><bean:write name='errorMessage'  /></logic:present></span></a>
				</div>
			</div>
			
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" >
					<h3 class="panel-title" style="color: #767676; vertical-align: text-top;"> <span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Student Registration
					</h3></a>
				<div class="navbar-right">
					<span class="buttons"><a href="adminMenu.html?method=addStudent">Add</a></span>
					<span id="editStudent" class="buttons">Edit</span>
					 <span id="trash" class="buttons">Delete</span> 
					
				</div>
			</div>
			<!-- pop up -->

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
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
					
				

							<div class="col-md-6" id="txtstyle">
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">School Name</label>
											<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											<option value="">-----Select------</option>
											<option value="all" selected>ALL</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
									
									
									<div class="form-group clearfix ">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()" 
											name="classname" id="classname">
											<option value="">ALL</option>
										</select>
									</div>
								</div>
						
																			
							<div class="form-group clearfix ">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Search By</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" 
										value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
									</div>
								</div>
					
							
							</div>
							<div class="col-md-6" id="txtstyle">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
								
									
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="">ALL</option>
										</select>
									</div>
								</div>
									<div  style ="margin-left:185px; margin-top:5px">	
										<span class="buttons" id="search">Search</span>
										<span class="buttons" id="resetbtn">Reset</span>
									</div>
							</div>
						<input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
								
							
				<%-- 	<display:table class="table" id="allstudent"
						name="requestScope.studentdetailslist"
						requestURI="/adminMenu.html?method=studentList?"
						decorator="com.centris.campus.decorator.StudentRegistrationDecorator">
						<display:column title="Select">
    						  <input type="radio" name="selectBox" id="selectBox"   value="${allstudent.studentId } ${allstudent.academicYearId}" /></display:column>
						


						<display:column property="check" style="text-align:center"
							sortable="true"
							title="<input type='checkbox' name='selectall' style='text-align:center' id='selectall' onClick='selectAll()' />"></display:column>


							<display:column property="academicYear" sortable="true"
							title="Academic Year<img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column property="studentAdmissionNo" sortable="true"
							title="Admission No	<img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>


						<display:column property="studentnamelabel" sortable="true"
							title="Student Name <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>



						<display:column property="classSectionId" sortable="true"
							title="Class and Division <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>



						<display:column property="dateofBirth" sortable="true"
							title="DOB <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>


						<display:column property="studentStatus" sortable="true"
							title="Status <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
												
					</display:table> --%>
					
					
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Select</th>
							<th>Academic Year</th>
							<th>Admission No</th>
							<th>Student Name</th>
							<th>Class and Division</th>
							<th>DOB</th>
							<th>Status</th>
							
							</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
					
					
					
		
<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
	<span  class='numberOfItem'></span>	
	</div><div class='pagination pagelinks'></div>
				
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