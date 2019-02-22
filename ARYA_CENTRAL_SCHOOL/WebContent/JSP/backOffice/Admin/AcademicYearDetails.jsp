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


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<link href="CSS/newUI/custome.css" rel="stylesheet" type="text/css">
	

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"src="JS/backOffice/Admin/AcademicYearDetails.js"></script>
<script type="text/javascript">
function handle(e){
	
	
	var searchText = $("#searchname").val();
    if(e.keyCode === 13){
        e.preventDefault(); // Ensure it is only this code that rusn

        window.location.href ="adminMenu.html?method=academicyear&searchText="
			+ searchText;
    }
}
</script>
<title>eCampus Pro</title>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
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
		
	<div class="searchWrap">	
		<div class="" id="div2">
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Academic Year details</span>
			</p>
		</div>

	</div>
	<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />

													</logic:present>'></input>		
		
			<div class="successmessagediv" align="center" style="display: none;">
			
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>

			<div class="errormessagediv1" align="center" style="display: none;">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 30%;"><span class="validateTips1"></span></a>
				</div>
			</div>


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Academic
						Year Details
					</h3></a>
				<div class="navbar-right" >
				  
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="AYPADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
					 <a	href="AcademicYearPath.html?method=addAcademicyear" id="plus">
                      <span class="buttons">Add</span>
					  </a> 
					  </logic:equal>
					  </logic:equal>
					  </logic:iterate>
					  </logic:present>
					  

							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="AYPUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
				     <span id="editDesignationId" class="buttons">Edit</span>
				     </logic:equal>
				     </logic:equal>
				     </logic:iterate>
				     </logic:present>
				     
				     
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="AYPDEL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
				     <span  id="trash"class="buttons">Delete</span>
				     </logic:equal>
				     </logic:equal>
				     </logic:iterate>
				     </logic:present>
					 <!-- <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download </span> -->

					 
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
				
			
		
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<display:table class="table" id="allstudent"
						name="requestScope.academicyearlist" 
						requestURI="/adminMenu.html?method=academicyear?"
						decorator="com.centris.campus.decorator.AcademicYearMasterDecorator">
						<display:column property="check" title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" />
						<display:column property="acadamic_name" sortable="true" title="Academic Name<img src='images/sort1.png' style='float: right'/>" />
						<display:column property="startDate" sortable="true" title="Start Date<img src='images/sort1.png' style='float: right'/>" />
						<display:column property="endDate" sortable="true" title="End Date<img src='images/sort1.png' style='float: right'/>" />
						<display:column property="description" sortable="true" title="Description<img src='images/sort1.png' style='float: right'/>" />

	
						
				
					</display:table>
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