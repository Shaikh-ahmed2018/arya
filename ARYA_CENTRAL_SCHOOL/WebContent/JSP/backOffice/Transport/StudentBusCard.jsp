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


<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.menu.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Transport/studentBusCard.js"></script>



<style>
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
</style>
<style>
.buttons{

vertical-align: 7px;

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

span.buttons{
margin-right:0px;
}
#sortable {
	list-style-type: none;
	margin: 0;
	padding: 0;
	width: 60%;
}

#sortable li {
	margin: 0 3px 3px 3px;
	padding-left: 1.5em;
}

#idcard {
	border-radius: 10px;
	border: 3px solid #BADA55;
}

#sortable li span {
	position: absolute;
	margin-left: -1.3em;
}


.main-div header{
background-color: #132c4a;
}
#idcard {
	border-radius: 10px;
	border: 3px solid #BADA55;
}
#sortable li span {
	position: absolute;
	margin-left: -1.3em;
}
.main-div {
	border: 5px solid #4C1919;
	width:195px;
	max-height:422px;
}
.schoolName {
	font-size: 14px;
	color: #B9BF50;
	position: relative;
}
.phones{
	border: 1px;
    border-radius: 10px;
    width: 25px;
    height: 20px;
    font-size: 10px;
    color: #ffffff;
    background-color: #2F3871;
    text-align: center;
    position: relative;
}
.branch {
	font-size: 10px;
    text-align: center;
    position: relative;
    color: #448719;
	
}
.phone {
	font-size: 10px;
    text-align: center;
    color: #fff;
    position: relative;
	
}
.section{
margin-bottom:10px;
}
.name {
	border: 1px solid #4C1919;
	border-radius: 5px;
	width:50px;
	font-size: 13px;
	color: #ffffff;
	background-color: #4C1919;
	text-align: center;
	position: relative;
	
}
.addresss{
	border: 1px ;
	border-radius: 10px;
	width:45px;
	height:20px;
	font-size: 8px;
	color: #ffffff;
	background-color: #2F3871 !important;
	text-align: center;
}

.namediv{
	    border: 1px solid #4C1919;
    border-radius: 5px;
    padding-top: 1px;
    height: 30px;
    margin-bottom: 1px;
    position: relative
}
.photo{
	border: 1px solid #4C1919;
	border-radius: 8px;
	height: 90px;
	width: 90px;
	text-align: center;
	display: inline-block; 
	vertical-align:top;
	position: relative;
}
.studentdetails,.addressdetails{
	border: 1px solid #4C1919;
	border-radius: 8px;
	height: 65px;
	display: inline-block;
	padding-top: 1px;
	width:90px;
	vertical-align:top;
	position: relative;
}

.phonedetails{
	border: 1px solid #4C1919;
    border-radius: 8px;
    width: 90px;
    height: 20px;
    display: inline-block;
    position: relative;
    vertical-align: top;
}

.label{ 
    color: #ffffff;
    background-color: #2F3871 !important;
    padding: 0px 2px;
    border-radius: 15px 0 0 15px;
    display: inline-block;
    margin: 1px;
    line-height: 13px;
   	width: 50px;
    text-align: left;
    font-size:8px;
}
.main-div header{
background-color: #132c4a;
}
section{
margin-bottom:10px;
}
.photo img{
    max-width: 90px;
    height: 90px;
    border-radius: 5px;
}
section div[id]{
cursor:move;
}
#myDialog{
width:1080px !important;
}
.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons{
    width: 1111px !important;
    left: 0 !important;
    overflow-y:scroll;
    right: 0 !important;
    margin: auto;
    height: 700px;
 
}
#allstudent  tr td{
padding: 5px;
}
section span{font-size: 9px;}
span1.name{font-size: 16px;}

.form-group{
margin-bottom: 10px;
}

</style>

</head>
<body>
	<div class="col-md-10 col-md-offset-2" id="div1">
			<div class="searchWrap">
			<div  id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Student Bus Card</span>
			</p>
		</div>

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
						class="successmessage"></span></a>
				</div>
			</div>
		


			<p id="parent1" style="display: none;">
				<a href="">Expand all</a>
			</p>
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">

					<div class="panel-heading" role="tab" id="headingOne" >

						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title" id="beforeparent" style="color: #767676; vertical-align: text-top;"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp; Find Student Details
							</h4></a>
						
							<div class="navbar-right">
						<span class="search" style="top:-3px">
							<label style="text-align: right;color:#767676;font-family:inherit;font-weight: 500;font-size: 16px;padding: 4px;">Term</label>	 
							 				<select id="termid" name="termid" class="nil" style="color:#767676;font-family:inherit;font-size: 14px;padding: 2px">
							 					<option value="">-------------Select-----------</option>
											 </select>
							</span>&nbsp;&nbsp;
							<span class="buttons" id="print" style="top:-8px;margin-right: 2px">Print</span><span class="buttons" id="download" style="top:-8px;">Download</span>
						</div>	
							
						

 
			</div>
					<!-- pop up -->

					<!-- Filters -->

<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" id="tabletxt" style="padding: 15px;">
								
							<div class="col-md-6" id="txtstyle">
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">School Name</label>
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
							
									
									
									<div class="form-group clearfix ">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()" 
											name="classname" id="classname">
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
																
							<div class="form-group clearfix ">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Search By</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" onkeypress="handle(event)"
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
											<option value="all">ALL</option>
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
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
								
						<div  style ="margin-left:185px; margin-top:">	
					<span class="buttons" id="search">Search</span>
					<span class="buttons" id="resetbtn">Reset</span>
					</div>
								<!-- 	<p align="center"style="margin-left:17%">
								<button type="button" class="btn btn-info" id="search" >Search</button>
								<button type="reset" class="btn btn-info" id="resetbtn" >Reset</button></p> -->
							</div>
						<input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
								
							</div>
							
							<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">	
					<%-- <display:table class="table" id="allstudent"
						name="requestScope.studentList"
						requestURI="/adminMenu.html?method=studentSearch?">
					
						<display:column  style="text-align:center" title="Select"><input type='radio' name='select' class='select' style='text-align:center' value='${allstudent.studentId} ${allstudent.academicYearId} ${allstudent.locationId} ${allstudent.classDetailId} ${allstudent.section_id}'/></display:column>
						<display:column property="studentAdmissionNo" class="studentid" sortable="true" title="Admission No <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="studentFullName" sortable="true" title="Student Name <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="studentrollno" sortable="true" title="Roll No <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="classname" class="${allstudent.classDetailId} classid" sortable="true" title="Class <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="sectionnaem" class="${allstudent.section_id} sectionid" sortable="true" title="Division <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						
						
					</display:table> --%>
					
					
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Select</th>
							<th>Admission No</th>
							<th>Student Name</th>
							<th>Roll No</th>
							<th>Class</th>
							<th>Division</th>
							</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
					
					
					
					
					<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
						</div><div class='pagination pagelinks'></div>
					</div>
					</div>

					</div>
			<!-- Button trigger modal -->


	</div>




	<span>&nbsp;</span>


	<!-- jQuery -->
	<script src="js/jquery.js"></script>


	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>


	<script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>
</body>
</html>