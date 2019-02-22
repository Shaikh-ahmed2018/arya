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
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"rel="stylesheet" type="text/css">




<script type="text/javascript" src="JS/backOffice/Fee/FeeCollection.js"></script>
<script type="text/javascript" src="JS/common.js"></script>


<title>eCampus Pro</title>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

.glyphicon-pencil:hover {
	cursor: pointer;
}

#edit:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
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
		<div class="col-md-3" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Fee Collection List</span>
			</p>
		</div>
		
		<input type="hidden" id="hclassid" value="<logic:present name="ClassId"><bean:write name="ClassId"/></logic:present>"/>
		<input type="hidden" id="hSectionId" value="<logic:present name="SectionId"><bean:write name="SectionId"/></logic:present>"/>
		<input type="hidden" id="htermId" value="<logic:present name="termId"><bean:write name="termId"/></logic:present>"/>

		<div class="input-group col-md-9">
		
		<label 
				class=" hedderstyle form-control">Term</label>
					<select id="term" name="term" class="form-control" style="width: 15%; margin: 20px 0px; margin-left: -5.5%;">
						<option value=""></option>
							<logic:present name="TermList">
								<logic:iterate id="termid" name="TermList">
									<option value="<bean:write name="termid" property="termid"/>"><bean:write name="termid" property="termname" /></option>
								</logic:iterate>
							</logic:present>
					</select>
			
			<label 	class="hedderstyle form-control">Class</label> 
			<select name="classname" id="classname" class="form-control" onchange="selectClass()" style="width: 16%; margin: 20px 0px; margin-left: -5.5%;">
				<option value=""></option>
			<logic:present name="ClassList">
			
			<logic:iterate id="classId" name="ClassList">
			
			<option value="<bean:write name="classId" property="classId"/>"><bean:write name="classId" property="className"/></option>
			
			</logic:iterate>
			
			</logic:present>
			
			</select> 
			
			<label style="margin: 20px 0px; width: 15%; border: none;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;"
				class="hedderstyle form-control">Section</label>
				
			 <select name="sectionname" id="sectionname" class="form-control" 
				style="width: 14%; margin: 20px 0px; margin-left: -4%;">
				
				<option value=""></option>
				
			</select>
			
			<label style="margin: 20px 0px; width: 15%; border: none;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;"
				class="hedderstyle form-control">Student</label>
				
			 <select name="student" id="student" class="form-control" 
				style="width: 20%; margin: 20px 0px; margin-left: -3.5%;">
				
				<option value=""></option>
				
			</select>
			
			
			
			<%-- <label style="margin: 20px 0px; width: 15%; border: none;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;"
				class="form-control">Term</label>
										<select id="term" name="term" class="form-control" style="width: 30%; margin: 20px 0px; margin-left: 0%;">
											<option value=""></option>
											<logic:present name="TermList">
											
												<logic:iterate id="termid" name="TermList">
													
													<option value="<bean:write name="termid" property="termid"/>"><bean:write name="termid" property="termname" />
													</option>
												
												</logic:iterate>
											
											</logic:present>

										</select> --%>
			

			<button class="btn btn-default" type="button" id="searchButtonId"
				style="margin: 20px 2px;">
				<i class="fa fa-search"></i>
			</button>

		</div>

		
			
			<div class="errormessagediv" align="center" style="display: none;">
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
						class="panel-title" style="color: #767676; vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Fee Collection
					</h3></a>
				<div class="navbar-right">
				
					
							<span class="buttons" id="edit"  data-toggle="tooltip" data-placement="bottom" title="Edit Fee">Edit</span>
							
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
					
					
					
					
					<display:table  name="requestScope.FeeCollectionList" requestURI="adminMenu.html?method=acdamicYearPlanList"
						
						pagesize="10" export="false" class="table" id="allstudent">

<display:column title="Select">
    						  <input type="radio" name="selectBox"         
   id="selectBox"   value="${allstudent.studentid},${allstudent.classId},${allstudent.termid},${allstudent.accYear},${allstudent.status}" />      
</display:column>

					<%-- <display:column 
							title="<input type='checkbox' name='selectall' id='selectall'  onClick='selectAll()' />" >
							<input type="checkbox" name="selectBox" id="selectBox"  value="${allstudent.addmissionno},${allstudent.classId},${allstudent.termid},${allstudent.accYear}" />
					</display:column> --%>
						<display:column property="accYearname" sortable="true" title=" Academic Year  <img src='images/sort1.png' style='float: right'/>" />
						<display:column property="addmissionno" sortable="true" title=" Addmission No  <img src='images/sort1.png' style='float: right'/>" />
						<display:column property="studentname" sortable="true" title="Student Name  <img src='images/sort1.png' style='float: right'/>" />
						<display:column property="classname" sortable="true" title="Class & Section  <img src='images/sort1.png' style='float: right'/>" />
						
						<display:column property="term" sortable="true" title="Term  <img src='images/sort1.png' style='float: right'/>" />
		<%-- 				<display:column property="amount" sortable="true" title="Actual Amount <img src='images/sort1.png' style='float: right'/>" /> --%>
						
						<display:column property="amount_paid_so_far" sortable="true" title="Paid Amount <img src='images/sort1.png' style='float: right'/>" />
						<display:column property="outstanding_balance" sortable="true" title="Balance Amount <img src='images/sort1.png' style='float: right'/>" />
							
						<display:column property="status" sortable="true" title="Status <img src='images/sort1.png' style='float: right'/>" />	
	                   <display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>
						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>
					
					</display:table>

				</div>
				<br />
			</div>
		</div>
	</div>


</body>
</html>