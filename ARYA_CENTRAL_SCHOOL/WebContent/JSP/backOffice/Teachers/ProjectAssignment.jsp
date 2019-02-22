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
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
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

<script type="text/javascript" src="JS/backOffice/Teacher/Project.js"></script>

<title>eCampus Pro</title>

<script type="text/javascript">

function handle(e){
	var searchText = $("#searchname").val();
    if(e.keyCode === 13){
        e.preventDefault(); // Ensure it is only this code that rusn

        window.location.href ="teachermenuaction.html?method=projectAssign&searchTerm="
			+searchText;
    }
}


</script>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
h4.panel-title {
    display: inline !important;
    vertical-align: text-top;
    color: #767676;
    cursor: pointer;
}
h4.panel-title:active {
background: transparent;}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
	<div id="dialog"></div>
	<div class="searchWrap">
		<div class="col-md-8" id="div2">

			<p >
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Project Details</span>
			</p>
		</div>

		<div class="input-group col-md-4">

			<input type="text" name="searchname" id="searchname"  onkeypress="handle(event)" 
					class="form-control" Placeholder="Search......" style = "height:35px;"
					value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'> 	
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" id="search" onclick="myFunction()" value="Submitform">
						<i class="fa fa-search"></i>
					</button>
				</span>
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
					class="validateTips"></span></a>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">
				
					<h4 class="panel-title">
						<i class="glyphicon glyphicon-menu-hamburger"></i>
						&nbsp;&nbsp;Project Details</h4>
				<div class="navbar-right">
					 <a href="teachermenuaction.html?method=addProject" id="plus">
					 	<span class="buttons">Add</span>
					 </a>
					  <span class="buttons" style="cursor: pointer;" id="edit">Edit</span>
					  	 <span id="trash" class="buttons" style="cursor: pointer;">Delete</span>
				</div>
			</div>
		<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					  
         <div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

				<%-- 	<display:table class="table" id="allstudent"
						name="requestScope.ProjectList"
						requestURI="/teachermenuaction.html?method=projectAssign"
						decorator="com.centris.campus.decorator.ProjectDecorator">

						<display:column property="check" style="text-align:center"
							title="<input type='checkbox' class='select' name='selectAll' style='text-align:center' id='selectAll'/>">
						</display:column>
						
						<display:column property="projectname" title="Project Name"
							media="html"></display:column>

						<display:column property="classname" title="Class"
							media="html"></display:column>

						<display:column property="sectionname" title="Section"
							media="html"></display:column>

						<display:column property="assigneddate" title="Assigned Date"
							media="html"></display:column>

						<display:column property="submissiondate" title="Submission Date"
							media="html"></display:column>
							
						<display:column property="maxmarks" title="Max Marks"
							media="html"></display:column>	
					</display:table> --%>
					
					<logic:present name="ProjectList" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' class='select' name='selectAll' style='text-align:center' id='selectAll'/></th>
							<th>Project Name</th>
							<th>Class</th>
							<th>Section</th>
							<th>Assigned Date</th>
							<th>Submission Date</th>
							<th>Max Marks</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="ProjectList" name="ProjectList">
								<tr>
								<td><input type='checkbox' class='select' name='selectAll' style='text-align:center' id='selectAll'/></td>
								<td><bean:write name="ProjectList" property='projectname'/></td>
								<td><bean:write name="ProjectList" property='classname'/></td>
								<td><bean:write name="ProjectList" property='sectionname'/></td>
								<td><bean:write name="ProjectList" property='assigneddate'/></td>
								<td><bean:write name="ProjectList" property='submissiondate'/></td>
								<td><bean:write name="ProjectList" property='maxmarks'/></td>
								
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
					
					
					</div>
					<br />
					<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
						</div><div class='pagination pagelinks'></div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>