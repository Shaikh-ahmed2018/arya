<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JS/common.js"></script>



<!-- <script type="text/javascript"
	src="JS/backOffice/Teacher/AttendaceUpload.js"></script> -->
<script type="text/javascript"
	src="JS/backOffice/Settings/RemarksList.js"></script>

<!-- <script type="text/javascript"
	src="JS/backOffice/Settings/BirthdaySms.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Settings/Meeting.js"></script>	 -->

<script type="text/javascript"
	src="JS/backOffice/Settings/Communicate.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css"> 


<title>eCampus Pro</title>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

#plus:hover {
	cursor: pointer;
}

#excelDownload :hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}

#iconsimg:hover {
	cursor: pointer;
}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-3" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Communication List</span>
			</p>
		</div>
		<div class="col-md-09">
			<form id="myForm" action="adminMenu.html?method=searchremark"
				method="post">
				<div class="input-group col-md-8" style="margin: 20px 0px;">


					<div class="form-group" style="margin-bottom: 0;">

						<div class="col-xs-3">
							<select name="remarks" id="remarktype" class="form-control">
								<option value="Remarks">Remarks</option>
								<option value="meeting">Meetings&Events</option>
								<option value="bday">Birthday Wishes</option>
								<option value="remainder">Circular Remainder</option>


							</select>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 0;">
						<label for="inputPassword" class="control-label col-xs-1"
							style="text-align: left; line-height: 35px; color: #797676; font-size: 16px; font-family: Open Sans Light;">From</label>
						<div class="col-xs-3">
							<input type="text" class="form-control" Placeholder="Click Here"
								name="fromdate" id="searchTextId"
								value='<logic:present name="searchlist"><bean:write name="searchlist" property="fromdate" /></logic:present>' />
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 0;">
						<label for="inputPassword" class="control-label col-xs-1"
							style="text-align: left; line-height: 35px; color: #797676; font-size: 16px; font-family: Open Sans Light; margin-right: -32px;">To</label>
						<div class="col-xs-3">
							<input type="text" class="form-control" Placeholder="Click Here"
								name="todate" id="searchTextId2"
								value='<logic:present name="searchlist"><bean:write name="searchlist" property="todate" /></logic:present>' />
						</div>
					</div>

					<button class="btn btn-default"
						style="margin-bottom: 0; margin-left: -15px;" type="button"
						id="search" onclick="myFunction()" value="Submitform">
						<i class="fa fa-search"></i>
					</button>
					<!-- 	<button
					class="btn btn-default" style="margin-bottom: 0;" type="button" id="searchButtonId"
					style="margin: 20px 0px;">
					<i class="fa fa-search"></i>
				</button></a>
				 -->

				</div>



				<input type="hidden" name="searchterm" class="searchtermclass"
					id="hremarksid"
					value='<logic:present name="communicatelist"><bean:write name="communicatelist" property="remarks"/>

													</logic:present>'></input>

				<input type="hidden" name="searchterm" class="searchtermclass"
					id="hfromdateid"
					value='<logic:present name="communicatelist"><bean:write name="communicatelist" property="fromdate"/>

													</logic:present>'></input>

				<input type="hidden" name="searchterm" class="searchtermclass"
					id="htodateid"
					value='<logic:present name="communicatelist"><bean:write name="communicatelist" property="todate"/>

													</logic:present>'></input>







				<input type="hidden" name="searchterm" class="searchtermclass"
					id="hremarksid1"
					value='<logic:present name="meetingitems"><bean:write name="meetingitems" property="title"/>

													</logic:present>'></input>

				<input type="hidden" name="searchterm" class="searchtermclass"
					id="hfromdateid1"
					value='<logic:present name="meetingitems"><bean:write name="meetingitems" property="fromdate"/>

													</logic:present>'></input>

				<input type="hidden" name="searchterm" class="searchtermclass"
					id="htodateid1"
					value='<logic:present name="meetingitems"><bean:write name="meetingitems" property="todate"/>

													</logic:present>'></input>






				<input type="hidden" name="searchterm" class="searchtermclass"
					id="hremarksid2"
					value='<logic:present name="bdayitems"><bean:write name="bdayitems" property="content"/>

													</logic:present>'></input>

				<input type="hidden" name="searchterm" class="searchtermclass"
					id="hfromdateid2"
					value='<logic:present name="bdayitems"><bean:write name="bdayitems" property="fromdate"/>

													</logic:present>'></input>

				<input type="hidden" name="searchterm" class="searchtermclass"
					id="htodateid2"
					value='<logic:present name="bdayitems"><bean:write name="bdayitems" property="todate"/>

													</logic:present>'></input>







			</form>
		</div>

		<logic:present name="successmessagediv1" scope="request">
			<div class="successmessagediv1">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv1" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<logic:present name="errormessagediv1" scope="request">
			<div class="errormessagediv1">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="errormessagediv1" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>


		<div class="errormessagediv2" align="center" display:none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Communication
						List

					</h3></a>

				<div class="navbar-right">

					<!--  <span id="plus" class="save2">

				<div class="navbar-right">

					<!--  <span id="plus" class="save2">

								 <img src="images/save.png" 
									 data-toggle="tooltip" data-placement="bottom" title="Save">
									</span>  -->


					<span id="plus" class="glyphicon glyphicon-plus3"
						data-toggle="tooltip" data-placement="bottom" title="Add"></span>


					<!--  <span
						class="glyphicon glyphicon-pencil" id="edit"
						data-toggle="tooltip" data-placement="bottom" title="Edit"></span> -->

					<img src="images/download.png" class="download3" id="iconsimg"
						data-toggle="modal" data-target="#myModal" data-toggle="tooltip"
						data-placement="bottom" title="Download">

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


			<input type="hidden" name="hremarhcode" id="hremarhcode" value="" />




			<input type="hidden" name="hidden" class="hiddenclass" id="hiddenid"
				value='<logic:present name="meeting"><bean:write name="meeting" />
													</logic:present>'></input>
			<input type="hidden" name="hiddenmeet" class="hiddenmeetclass"
				id="hiddenmeetid"
				value='<logic:present name="searchmeeting"><bean:write name="searchmeeting" />
													</logic:present>'></input>
			<input type="hidden" name="hiddenbday" class="hiddenmeetclass"
				id="hiddenbdayid"
				value='<logic:present name="birthday"><bean:write name="birthday" />
													</logic:present>'></input>



			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

					<logic:present name="remarkslist" scope="request">
						<display:table class="remarks table" pagesize="10"
							name="requestScope.remarkslist"
							requestURI="adminMenu.html?method=communicationRemarksList"
							decorator="com.centris.campus.decorator.RemarksConfirmationDecorator"
							id="allstudent">


							<display:column title="Select" headerClass="heading1">
								<input type="checkbox" name="getempid"
									onClick='getvaldetails(this)' value=""
									id="${allstudent.remarkcode}"> </>
							</display:column>


							<display:column property="dateId" sortable="true"
								title="Date <img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="name" sortable="true"
								title="Name<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="subjectName" sortable="true"
								title="Subject<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="classname" sortable="true"
								title="Class<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="sectionname" sortable="true"
								title="Section<img src='images/sort1.png' style='float: right'/>"></display:column>


							<display:column property="remarks" sortable="true"
								title="Remarks<img src='images/sort1.png' style='float: right'/>"></display:column>


							<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

							<display:setProperty name="paging.banner.placement"
								value="bottom"></display:setProperty>



						</display:table>

					</logic:present>


					<logic:present name="meetinglist" scope="request">
						<display:table class="meeting table" pagesize="10"
							name="requestScope.meetinglist"
							requestURI="adminMenu.html?method=getmeeting"
							decorator="com.centris.campus.decorator.MeetingDecorator"
							id="allstudent">





							<display:column property="check" sortable="true"
								title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" />

							<%-- 							   <display:column property="check" sortable="true" title='Select'></display:column>
 --%>
							<display:column property="title" sortable="true"
								title="Subject Name <img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="meetingDate" sortable="true"
								title="Meeting Date<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="startTime" sortable="true"
								title="Start Time<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="endTime" sortable="true"
								title="End Time<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="meetingwith" sortable="true"
								title="Name<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="classname" sortable="true"
								title="Class Name<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="sectionname" sortable="true"
								title="Section Name<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="subjectName" sortable="true"
								title="Class Subject<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="venueid" sortable="true"
								title="Venue Details<img src='images/sort1.png' style='float: right'/>"></display:column>


							<%-- <display:column property="description" sortable="true"
								title="Meeting Description<img src='images/sort1.png' style='float: right'/>"></display:column> --%>

							<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

							<display:setProperty name="paging.banner.placement"
								value="bottom"></display:setProperty>


						</display:table>

					</logic:present>







					<logic:present name="bdaylist" scope="request">
						<display:table class="birthday table" pagesize="10"
							name="requestScope.bdaylist"
							requestURI="adminMenu.html?method=getbdaylist"
							decorator="com.centris.campus.decorator.BdayDecorator"
							id="allstudent">

							<%-- 							   <display:column property="check" sortable="true" title='Select'></display:column>
 --%>
							<display:column property="check" sortable="true"
								title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" />
							<display:column property="bdayDate" sortable="true"
								title="Date <img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="bdayperson" sortable="true"
								title="Name<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="classname" sortable="true"
								title="Class Name<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="sectionname" sortable="true"
								title="Section Namee<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="content" sortable="true"
								title="Content<img src='images/sort1.png' style='float: right'/>"></display:column>

							<%-- 	<display:column property="classname" sortable="true"
								title="Class Name<img src='images/sort1.png' style='float: right'/>"></display:column>
								<display:column property="sectionname" sortable="true"
								title="Section Name<img src='images/sort1.png' style='float: right'/>"></display:column>
								
								<display:column property="subjectName" sortable="true"
								title="Subject Name<img src='images/sort1.png' style='float: right'/>"></display:column>
								
							
							
							
							<display:column property="description" sortable="true"
								title="Meeting Description<img src='images/sort1.png' style='float: right'/>"></display:column> --%>
							<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

							<display:setProperty name="paging.banner.placement"
								value="bottom"></display:setProperty>

						</display:table>

					</logic:present>





				</div>
				<br />
			</div>
		</div>
	</div>


</body>
</html>


