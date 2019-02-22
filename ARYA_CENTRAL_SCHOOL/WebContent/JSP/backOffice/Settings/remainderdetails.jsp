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



<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>

<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>

<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>

<script type="text/javascript" src="JS/backOffice/Settings/remainderdetails.js"></script>
<script type="text/javascript" src="JS/common.js"></script>


<title>eCampus Pro</title>
<style>
#feeedit:hover {
	cursor: pointer;
}
</style>

<style>
#delete:hover {
	cursor: pointer;
}
</style>
</head>

<body>




	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Remainder details</span>
			</p>
		</div>
		
		<div class="input-group col-md-4" style="margin: 20px 0px;">

			<input type="text" class="form-control" id="searchvalue"
				Placeholder="Search......"> <span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div> 
		
		

<%-- <div class="input-group col-md-4" style="margin: 20px 0px;">

			<!-- <input type="text" class="form-control" id="searchValue" Placeholder="Search......" > -->
			
			<input type="text" name="searchname" id="searchValue"
					class="form-control" Placeholder="Search....."
					value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'>
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="searchClass">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>  --%>

 




	
		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searremainderid"
			value='<logic:present name="searchremainder"><bean:write name="searchremainder" />

													</logic:present>'></input>	


		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<logic:present name="errormessagediv" scope="request">
			<div class="errormessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><bean:write name="errormessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="errormessagediv1" align="center"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 50%; font-size: 11pt; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>



		<div class="errormessagediv" align="center" style="display: none;">
							<div class="message-item">
								
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips"></span></a>
							</div>
						</div>










		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Remainder
						Details
					</h3></a>
				<div class="navbar-right">
					 <a
						href="remainder.html?method=addremainderdetails"><span
						class="buttons"
						 data-toggle="tooltip" data-placement="bottom" title="Add">Add</span>
						
						</a> 
						<span
						id="termedit" class="buttons"
						 data-toggle="tooltip" data-placement="bottom" title="Edit">Edit</span>


						
						<span id="delete" class="buttons"
						 data-toggle="tooltip" data-placement="bottom" title="Delete">Delete</span>

						 <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span>


				</div>
				
				
				<script>
							$(document).ready(function() {
								$('[data-toggle="tooltip"]').tooltip();
							});
						</script>
				
				
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


					<logic:present name="remainderlist" scope="request">

						<display:table id="allstudent" class="table"
							name="requestScope.remainderlist"
							requestURI="/adminMenu.html?method=remainderdetails"
							decorator="com.centris.campus.decorator.RemainderMasterDecorator"
							pagesize="10">
					
				
				   			<display:column property="view" title='Select' /> 
							<%-- <display:column property="id" title=" ID " /> --%>
							<display:column property="name" title="Tittle" />
							<display:column property="remtype" title="Remainder To" />
							<display:column property="description" title="Description" />
							
							<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

					    	<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>

						</display:table>


					</logic:present>



				</div>
				<br />
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