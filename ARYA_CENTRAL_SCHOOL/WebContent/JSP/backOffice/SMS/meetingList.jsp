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

<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="JS/backOffice/SMS/meetingList.js"></script>
<script type="text/javascript" src="JS/common.js"></script>



<style>
.glyphicon:hover {
	cursor: pointer;
}
/* .modal-body {
	text-align: center;
} */
</style>
<style>
.buttons{

vertical-align: -28px;

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

	<div class="col-md-10 col-md-offset-2"  id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Absenties List</span>
			</p>
		</div>


		<!-- <form id="myForm"
			action="adminMenu.html?method=homeworklist" method="post"> -->

			<%-- <div class="input-group col-md-4" style="margin: 20px 0px;">
				<input type="text" name="searchname" id="searchname"
					class="form-control" Placeholder="Search......"
					value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'>
				<span class="input-group-btn">
					<!-- <button class="btn btn-default" type="button" id="search"
						onclick="myFunction()" value="Submitform">
						<i class="fa fa-search"></i>
					</button> -->
				</span>
			</div> --%>
			
			<br /><br />
			
		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />

													</logic:present>'></input>	
			
			
			
			
			
		<!-- </form> -->


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
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Absenties List
						
					</h3></a>



				<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>
			</div>
			<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name<font color="red">*</font></label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											<option value="all">ALL</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
									
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class <font color="red">*</font></label>
									<div class="col-xs-7">
									
									<select class="form-control" onkeypress="HideError()" 
											name="classname" id="classname">
											<option value="">----------Select----------</option>
										</select>
									
									</div>
								</div>
								
						<div class="form-group clearfix start">
							<label for="inputPassword" class="control-label col-xs-5"
								id="inputnames" style="text-align: right;">Date<font color="red">*</font></label>
							<div class="col-xs-7">
								<input type="text" id="startDate"  class="form-control" readonly="readonly" />
							</div>
						</div>
											

						</div>
						<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year <font color="red">*</font></label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
											<option value="all">----------Select----------</option>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
						
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division <font color="red">*</font></label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="">----------Select----------</option>
										</select>
									</div>
								</div>
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Message</label>
									<div class="col-xs-7">
										<textarea  id="Message"name='StudentMessage' class="form-control" style=" width: 100%; height: 7%;"></textarea>
									
									</div>
								</div>
								
							
								<div class="form-group clearfix">
									<div class="col-xs-12">
									<p align="right">
								
								<button type="button" class="btn btn-info" id="absentsend" >Send</button>
								
								</p>
								</div>
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




<input type="hidden" name="searchterm" class="searchtermclass" id="hmeetingid" />

													


			<div id="collapseOne" class="accordion-body collapse in clearfix">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

			<%-- 		<logic:present name="meetinglist" scope="request">
						<display:table class="table" id="allstudent"
							name="meetinglist" pagesize="10"
							requestURI="/adminMenu.html?method=meetingslist">

							  <display:column title="Select" headerClass="heading1">
							<input type="checkbox" name="getempid" onClick='getvaldetails(this)' value=""
							id="${allstudent.meetingid}"> </>
							</display:column>	
							
							<display:column title="<input type='checkbox' name='selectall' id='selectall'/>" headerClass="heading1" media="html" sortable="true">
							<input type='checkbox' name='selectall' class='select' id='select' onClick='selectAll()' />
							</display:column>	
							

							<display:column property="streamId" title="Stream Id"></display:column>
							
							<display:column property="meetingDate" sortable="true"
								title="Date<img src='images/sort1.png' style='float: right'/>" />
								
							<display:column property="title" sortable="true"
								title="Title<img src='images/sort1.png' style='float: right'/>" />
								
								<display:column property="startTime" sortable="true"
								title="Start Time<img src='images/sort1.png' style='float: right'/>" />
								
								<display:column property="endTime" sortable="true"
								title="End Time<img src='images/sort1.png' style='float: right'/>" />
								
								<display:column property="classname" sortable="true"
								title="Class Name<img src='images/sort1.png' style='float: right'/>" />
								
								<display:column property="sectionname" sortable="true"
								title="Section Name<img src='images/sort1.png' style='float: right'/>" />

								<display:column property="meetingwith" sortable="true"
								title="Student Name<img src='images/sort1.png' style='float: right'/>" />
								
								<display:column property="subjectName" sortable="true"
								title="Subject Name<img src='images/sort1.png' style='float: right'/>" />
								
								<display:column property="venueid" sortable="true"
								title="Venue Details<img src='images/sort1.png' style='float: right'/>" />


						





						</display:table>

					</logic:present> --%>
					
					
						<table class="table" id="allstudent">
							<thead>
							<tr>
							
							<th>AdmissionNo</th>
							<th>Roll No </th>
							<th>Name</th>
							<th>Class</th>
							<th>Status</th>
							<th>SMS No</th>
							<th>Attendance Date</th>
							<th>Parent Name</th>
							</tr>
							</thead>
							<tbody>
						
							</tbody>
						</table>
				
					
					

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