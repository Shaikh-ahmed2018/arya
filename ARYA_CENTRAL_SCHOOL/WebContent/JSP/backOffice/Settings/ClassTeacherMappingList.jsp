<!DOCTYPE html>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Settings/ClassTeacherMapping.js"></script>

<script type="text/javascript" src="JS/common.js"></script>
<style>
#feeedit:hover {
	cursor: pointer;
}
#editdep:hover {
	cursor: pointer;
}
#deleteid:hover {
	cursor: pointer;
}
#xls:hover {
	cursor: pointer;
}
#iconsimg:hover{

cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}
</style><style>
.buttons{

vertical-align: 6px;

}
</style>
</head>

<body>



     <div class="errormessagediv1" style="display: none;">
				<div class="message-item1"></div></div>
	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">ClassTeacher List</span>
			</p>
		</div>
		<div align="right" class="input-group col-md-4" style="margin: 20px 20px;" >
		

			<input type="text" class="form-control" Placeholder="Search......" id="searchterm" value="<logic:present name="searchTerm"><bean:write name="searchTerm"></bean:write></logic:present>">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
		
					
	<%-- <!-- 	<form id="myForm" action="adminMenu.html?method=searchQuota" method="post" > -->
		
		

		<div class="input-group col-md-4" style="margin: 20px 0px; visibility:hidden;">
			 <input type="text" class="form-control" name="searchname" id="search" Placeholder="" 
			  value='<logic:present name="searchTerm"><bean:write name="searchTerm" />
		</logic:present>'>
			
			<span class="input-group-btn">
				<<!-- button class="btn btn-default" type="button" id="search" onclick="myFunction()" value="Submit form">
					<i class="fa fa-search"></i>
				</button> -->
			</span> 
		</div>
		<!-- </form> --> --%>
		
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
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>
		
		
		
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;ClassTeacher List
						
					</h3></a>
					
			   <div class="navbar-right" >
				<!-- 
					 <span id="editId" class="glyphicon glyphicon-pencil2"   data-toggle="tooltip" data-placement="bottom" title="Edit"></span>
				
				<img src="images/rightline.png" style="margin-top: 0px;"> 
				
				<img src="images/download.png" class="download" id="iconsimg"
						data-toggle="modal" data-target="#myModal" data-toggle="tooltip"
						data-placement="bottom" title="Download"> -->
				
				 <span
						class="buttons " id="editId">Edit</span>


							 <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download </span>

				
				
				</div> 
				
				
				<!-- <div class="navbar-right">
				
				
				
					<img src="images/rightline.png" style="margin-top: 0px;"> 
					<img src="images/download.png"
						style="margin-top: 1px; margin-left: -3px" class="downloadlast"
						id="iconsimg" data-toggle="modal" data-target="#myModal"
						data-toggle="tooltip" data-placement="bottom" title="Download">

				</div>
			 -->
			<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>
			
			
		 <input	type="hidden" name="classid" id="classid" value=""/>		
		 <input	type="hidden" name="sectionid" id="sectionid" value=""/>		
		  <input	type="hidden" name="teacherid" id="teacherid" value=""/>	
			
			
			
			
			
			
			
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
									<span id="xls"><img src="images/xl.png"
										class="xl"></span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
										id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
								</div>

							</div>
						</div>
					</div>








			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; margin-bottom: 18px;font-size: 11pt; color: #5d5d5d;">
												
				<%-- 	
					<logic:present name="classteacherlist" scope="request">
						<display:table class="meeting table" 
							name="requestScope.classteacherlist"
							requestURI="adminMenu.html?method=getclassandteacherList"
							id="allstudent">
							
							
							<display:column property="check" sortable="true"
								title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" />



	            <display:column title="Select" headerClass="heading1">
							<input type="checkbox" name="getempid" onClick='getvaldetails(this)' value="Get Salary Details"
							id="${allstudent.classId},${allstudent.sectionId},${allstudent.teacherId}"
							> </>
							</display:column>	




							<display:column property="className" sortable="true"
								title="Class Name <img src='images/sort1.png' style='float: right'/>"></display:column>
								
							<display:column property="sectionName" sortable="true"
								title="Section Name<img src='images/sort1.png' style='float: right'/>"></display:column>
								
							<display:column property="teacherName" sortable="true"
								title="ClassTeacher Name <img src='images/sort1.png' style='float: right'/>"></display:column>

								
							
						</display:table> --%>
						
						<logic:present name="classteacherlist" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Select</th>
							<th>Class Name</th>
							<th>Section Name</th>
							<th>ClassTeacher Name</th>
							
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="classteacherlist" name="classteacherlist">
								<tr>
								<td><input type="checkbox" name="getempid"  value="Get Salary Details"  class='<bean:write name="classteacherlist" property='classId'/> <bean:write name="classteacherlist" property='sectionId'/> <bean:write name="classteacherlist" property='teacherId'/> <bean:write name="classteacherlist" property='locationId'/>' /> </td>
								<td><bean:write name="classteacherlist" property='className'/></td>
								<td><bean:write name="classteacherlist" property='sectionName'/></td>
								<td><bean:write name="classteacherlist" property='teacherName'/></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
							
                            <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
                            	<span  class='numberOfItem'></span>	
                            	</div><div class='pagination pagelinks'></div>
					<%-- </logic:present> --%>
						
						
					
		
		<!-- 
					<p style="float: left; margin: 0;">&nbsp;&nbsp;Showing 1 to 10
						of 50 Entries</p> -->
					<!-- <ul class="pagination" style="float: right; margin: 0;">
						<li><a href="#">&laquo;</a></li>
						<li class="active"><a href="#">1</a></li>
						<li class=""><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul> -->
				</div>
		
			</div>
		</div>
	</div>
	<script src="JS/newUI/jquery.js"></script>
	<script src="JS/newUI/bootstrap.min.js"></script>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>