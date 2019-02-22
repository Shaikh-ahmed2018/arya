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
	src="JS/backOffice/Admission/TemporaryAdmissionDetails.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">



<style>
.glyphicon:hover {
	cursor: pointer;
}
/* .modal-body {
	text-align: center;
} */
</style>

<style>
.download:hover {
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

	<div id="issued" class="tab-pane">
      
      <div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Issued Forms</span>
			</p>
		</div>


		<form id="myForm" action="adminMenu.html?method=tempadmissionMenu" method="post">


		<div class="input-group col-md-4" style="margin: 20px 0px;">
			<input type="text" class="form-control" id="searchvalue"
				Placeholder="Search......"> <span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>


		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />

		</logic:present>'></input>
		</form>


		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>



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
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Issued Forms
					</h3></a>



				<div class="navbar-right">

				     <span
						class="buttons" id="edit" 
						data-placement="bottom" title="Edit">Edit</span>
				


					

				</div>
				
			</div>
			<!-- pop up -->

			


			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
					<logic:present name="issuedList" scope="request">
						<display:table class="table" id="allstudent"
							name="issuedList" pagesize="10"
					
							requestURI="/parentrequiresappointment.html?method=Issuedformlist"
							decorator="com.centris.campus.decorator.issuedFormDetailsDecorator">

		
							<display:column  style="text-align:center"
									
								title="<input type='checkbox' name='selectall' style='text-align:center' id='selectall'  />"><input type='checkbox' name='select' class='select' style='text-align:center' id='${allstudent.enquiryid}'/></display:column> 
							


							<display:column property="parentfirstName"  href="adminMenu.html?method=issuedformEdit"
							    sortable="true" 
			
								title="Student Name<img src='images/sort1.png' style='float: right' />" />

							

                             <display:column property="parents_name" sortable="true"
								title="Parents Name<img src='images/sort1.png' style='float: right'/>" />

							<display:column property="parentEmailId" sortable="true"
								title="Email<img src='images/sort1.png' style='float: right'/>" />
								
							<display:column property="mobile_number" sortable="true" 
								title="Mobile No<img src='images/sort1.png' style='float: right'/>" /> 

	                          <display:column property="address" sortable="true" 
								title="Address<img src='images/sort1.png' style='float: right'/>" />   
								
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

	
      
      
      
      
    </div>
</body>
</html>