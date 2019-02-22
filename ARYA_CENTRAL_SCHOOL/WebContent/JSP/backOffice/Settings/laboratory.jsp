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
	src="JS/backOffice/Settings/laboratory.js"></script>

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
.download:hover{

cursor: pointer;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
#dwnd1 {
    width: 27px;

}
.pagination{
  top: -6px;
     margin-right: 3px;
 }
.pagebanner{
  left: 21px;
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
	<div id="dialog"></div>
	<div class="searchWrap">
		 <div class="col-md-8" id="div2">
	

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Laboratory Details</span>
			</p>
		</div>


		<div class="input-group col-md-4">
<%-- <div>
			<input type="text" name="searchname" value="<logic:present name="searchTerm" ><bean:write name="searchTerm" /></logic:present>" id="searchname" class="form-control" Placeholder="Search......">
			
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search"  value="Submitform">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
		
		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />
		</logic:present>'></input>	
		</div> --%>
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


		<div class="panel panel-primary" style="margin-top: 35px;">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Laboratory Details
					</h3></a>



				<div class="navbar-right" >
				   <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="SUBADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
				 <a href="adminMenu.html?method=addLaboratory"><span
						class="buttons">Add</span>
						</a>
						</logic:equal>
						</logic:equal>
						</logic:iterate>
						</logic:present>
						
						   <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="SUBUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
						<span id="editlab" class="buttons">Edit</span>
						</logic:equal>
						</logic:equal>
						</logic:iterate>
						</logic:present>


						   <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="SUBDEL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
						<span id="delete" class="buttons">Delete</span>
						</logic:equal>
						</logic:equal>
						</logic:iterate>
						</logic:present>

				</div>
				
			</div>
			<!-- pop up -->
			
			
			<div id="collapseOne" class="accordion-body collapse in">
		<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											<option value="">ALL</option>
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
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<select name="classname" id="classname" class="form-control" onkeypress="HideError()" >
									<option value="all">ALL</option>
									
							    </select>
									</div>
								</div>
								
								
								
										<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Specialization</label>
									<div class="col-xs-7">
									<select id="specialization" name="specialization" class="form-control">
										<option value="all">ALL</option>
				
									</select>
									</div>
								</div>
								
							</div>
			<div class="modal fade" id="myModal" tabindex="-1" >
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span >&times;</span>
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
                 <div id="subjectOne" class="accordion-body collapse in">
                 
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
              <div class="col-md-12">
					<logic:present name="laboratoryDetails" scope="request">
						<%-- <table id="regConfirmationtable">
							<tbody>
								<display:table class="table" id="allstudent"
									name="requestScope.laboratoryDetails"
									decorator="com.centris.campus.decorator.ViewAllSubjectDecorator"
									requestURI="adminMenu.html?method=laboratory">
									
									<tr>
										<display:column media="html"
									title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />">
									<input type='checkbox' name='select' class='select' id='${allstudent.lab_id},${allstudent.locationId}' onclick='selectAll()'  />
								</display:column>
										<display:column property="locationName" sortable="true"  title="School Name<img src='images/sort1.png' style='float: right'/>" />
										<display:column property="classname" sortable="true"  title="Class Name<img src='images/sort1.png' style='float: right'/>" />
										<display:column property="specialization" sortable="true"  title="Specialization<img src='images/sort1.png' style='float: right'/>" />
										<display:column property="subjtname" sortable="true"  title="Subject Name<img src='images/sort1.png' style='float: right'/>" autolink="true" />
										<display:column property="lab_name" sortable="true"  title="Lab  Name<img src='images/sort1.png' style='float: right'/>" autolink="true" />
										<display:column property="totalMarks" sortable="true"  title="Total Marks<img src='images/sort1.png' style='float: right'/>" />
										<display:column property="passMarks" sortable="true"  title="Pass Marks<img src='images/sort1.png' style='float: right'/>" />
										<display:column property="subjectCode" sortable="true"  title="Lab Code<img src='images/sort1.png' style='float: right'/>" autolink="true" />
										<display:column property="description" sortable="true" style="width:93px" title="Description<img src='images/sort1.png' style='float: right'/>" />
										<display:column property="labdownload"   title="Syllabus<img  style='float: right'/>" />
									
									
									</tr>
								</display:table>
							</tbody>
						</table> --%>
						
						<table class="table" id="allstudent">
						<thead>
						<tr>
						<th><input type='checkbox' name='selectall' id='selectall' style='text-align:center' onClick='selectAll()'/></th>
						<th>School Name</th>
						<th>Class Name</th>
						<th>Specialization</th>
						<th>Subject Name</th>
						<th>Lab Marks</th>
						<th>Total Marks</th>
						<th>Pass Marks</th>
						<th>Lab Code</th>
						<th>Description</th>
						<th>Syllabus</th>
						</tr>
						</thead>
						<tbody>
						<logic:iterate name='laboratoryDetails' id="laboratoryDetails">
						<tr>
						<td><input type='checkbox' name='select' class='select' style='text-align:center' id='<bean:write name='laboratoryDetails' property="lab_id"/>,<bean:write name='laboratoryDetails' property="locationId"/>' /></td>
						<td><bean:write name='laboratoryDetails' property="locationName" /></td>
						<td><bean:write name='laboratoryDetails' property="classname" /></td>
						<td><bean:write name='laboratoryDetails' property="specialization" /></td>
						<td><bean:write name='laboratoryDetails' property="subjtname" /></td>
						<td><bean:write name='laboratoryDetails' property="lab_name" /></td>
						<td><bean:write name='laboratoryDetails' property="totalMarks" /></td>
						<td><bean:write name='laboratoryDetails' property="passMarks" /></td>
						<td><bean:write name='laboratoryDetails' property="subjectCode" /></td>
						<td><bean:write name='laboratoryDetails' property="description" /></td>
						<td style='text-align:center'><a href='subject.html?method=getLabsyllabusdownload&labid=<bean:write name='laboratoryDetails' property="lab_id"/>'><img id='dwnd1' src='images/download.png'/></a></td>
						</tr>
						</logic:iterate>
						</tbody>
						
						</table>
						
	<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
		<span  class='numberOfItem'></span>	
		</div><div class='pagination pagelinks'></div>					
					</logic:present>
		 </div>	
			</div>	
			</div>
			
		</div>
	</div>
</div>
</div>

	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script>
</body>
</html>