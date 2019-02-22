<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>

<title>eCampus Pro</title>  

<link href="CSS/newUI/custome.css" rel="stylesheet">
<script type="text/javascript" src="JS/backOffice/Staff/StaffPayroll.js"></script>
<script type="text/javascript" src="JS/common.js"></script>


<style>
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
.download:hover{

cursor: pointer;
}
#excel:hover {
	cursor: pointer;
}
#pdf:hover {
	cursor: pointer;
}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Staff Salary Details</span>
			</p>
		</div>
		<div class="input-group col-md-4" style="margin: 20px 0px;">

			<input type="text" class="form-control" id="searchname" Placeholder="Search......">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="searchbtn" >
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
		
		<center>
		
		<logic:present name="successmessagediv" scope="request">
						<div class="successmessagediv">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"><span><bean:write
											name="successmessagediv" scope="request" /></span></a>
							</div>
						</div>
		</logic:present>
		
		
						<logic:present name="errorMessage" scope="request">

							<div class="successmessagediv">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-warning bg-msg-warning"><span>
											<bean:write name="errorMessage" scope="request" />
									</span></a>
								</div>

							</div>

						</logic:present>
		
			<div class="errormessagediv" style="display: none;width: 60%">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>
		</center>
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;vertical-align: middle;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Staff Salary Details
					</h3></a>
				<div class="navbar-right" style="">
				
					
					
					
					 	<span id="editPayroll" class="buttons">EDit</span>
					  
			
                      <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download </span>


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
							 <span id="excel"><img src="images/xl.png"
								class="xl"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
								id="pdf"><img src="images/pdf.png" class="pdf"></span>
						</div>
					</div>
				</div>
			</div>
			
				<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<!-- <table class="table" id="allstudent"> -->


					<logic:present name="PayrollList" scope="request">
						<display:table class="table" id="allstudent"
							name="requestScope.PayrollList"
							requestURI="/adminMenu.html?method=staffEmployementList" export="false"
							decorator="com.centris.campus.decorator.StaffPayrollDecorator">
							
							<display:column property="check" sortable="true"
								title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" />


							<display:column property="year" sortable="true"
								title="Year <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="month" sortable="true"
								title="Month<img src='images/sort1.png' style='float: right'/>" />
							
							<display:column property="status" sortable="true"
								title="Status <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="createdby" sortable="true"
								title="Created By <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="createTime" sortable="true"
								title="Create Time <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="updatedby" sortable="true"
								title="Updated By <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="updatetime" sortable="true"
								title="Updated Time <img src='images/sort1.png' style='float: right'/>" />
					
	

						</display:table>
                           <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
                           	<span  class='numberOfItem'></span>	
                           	</div><div class='pagination pagelinks'></div>
					</logic:present>
				
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