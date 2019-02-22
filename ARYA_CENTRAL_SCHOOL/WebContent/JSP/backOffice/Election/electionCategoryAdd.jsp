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
<link rel="stylesheet"href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="CSS/Admin/StudentNew.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript" src="JS/backOffice/Election/electionCategoryAdd.js"></script>



<style>
#editStudent:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
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
.glyphicon-trash:before {
    content: "\e020";
    margin-left: 15px;
}
.multiple{
height: auto !important;
}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
	<div class="searchWrap">
		<div  id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Election Details</span>
			</p>
			
			
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
			
	<!-- pop up -->		
			<div id="electionCategoryAddDialog" style="display: none">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
						</div>
					</div>
				</div>
				<div class="col-md-6"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
					<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right;">Category Name <span style="color: red;">*</span></label>
							<div class="col-xs-7">
							<input type="text" name="categoryName" 
								id="categoryName" class="form-control" style="text-transform:uppercase" onchange="HideError()" /> 
							 <input type="hidden" name="categoryNameIdHidden"  id="categoryNameIdHidden" class="form-control"  value="" /> 
								
							</div>
							</div>
							
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">Participate Class<span
								style="color: red;">*</span></label>
							<div class="col-xs-3">
										<select id="participateClass" name="participateClass" class="form-control multiple"  onchange="HideError()"  multiple>
											<option value="">---Select---</option>

											
										</select>
										
										</div>
					</div>
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">Gender Wise</label>
							<div class="col-xs-7">
										<select id="genderWise" name="genderWise" class="form-control" onchange="HideError()" 
											required>
											<option value="">----select----</option>
											<option value="MALE">MALE</option>
											<option value="FEMALE">FEMALE</option>
											<option value="Both">Both</option>

											
										</select>
										
										</div>
					</div>

					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">House Wise</label>
								<div class="col-xs-7">
									<select id="houseWise" name="houseWise" class="form-control"
										onchange="HideError()" required>
										<option value="">----select----</option>
										<option value="YES">YES</option>
										<option value="NO">NO</option>


									</select>

								</div>
							</div>
							
				<div class="form-group clearfix houseListDiv" style="display: none;">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">House</label>
								<div class="col-xs-7">
									<select id="houseList" name="houseList" class="form-control" >
										<option value="All">----select----</option>
										<logic:present name="houseList" scope="request">
											<logic:iterate id="houseList" name="houseList">
												<option value='<bean:write name="houseList" property="houseId" />'><bean:write name="houseList" property="houseName" /></option>
											</logic:iterate>
										</logic:present>
									</select>

								</div>
							</div>			
							
				</div>


							<div class="col-md-6"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">

					<div class="form-group clearfix "> 
								<label for="inputEmail" class="control-label col-xs-5" onchange="HideError()" 
									style="text-align: right;">Priority <span
								style="color: red;">*</span></label>
							<div class="col-xs-7">
							<input type="number" name="priority" id="priority" onchange="HideError()" class="form-control" min="1" />
							</div>
							</div>
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">Nomination level<span style="color: red;">*</span></label>
							<div class="col-xs-7">
									<select id="nominLevel" name="nominLevel" class="form-control multiple"  onchange="HideError()" multiple>
											<option value="">---Select---</option>
											</select>
										
										</div>
					</div>
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">Nomination For</label>
							<div class="col-xs-7">
										<select id="nominFor" name="nominFor" class="form-control"  onchange="HideError()"  required>
											<option value="">----select----</option>
											<option value="MALE">MALE</option>
											<option value="FEMALE">FEMALE</option>
										</select>
										</div>
							</div>

					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">Class Wise</label>
							<div class="col-xs-7">
										<select id="classWise" name="classWise" class="form-control"  onchange="HideError()"  required>
											<option value="">----select----</option>
											<option value="YES">YES</option>
											<option value="NO">NO</option>
						
										</select>
										
										</div>
					</div>
				</div>
				<span style="color:red">* Feilds are mandatory</span>
		</div>
		
</div>
<!--Pop Up Ends  -->		
		</div>

	</div>
		
		<input type="hidden" id="succmsg" value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />
		
		
		<div class="panel panel-primary">
		
			<div class="panel-heading">
				<a href="#" >
					<h3 class="panel-title" style="color: #767676; vertical-align: middle;"> <span class="glyphicon glyphicon-menu-hamburger"></span>
					&nbsp;&nbsp;Find Election Details
					</h3></a>
					
					
					<div class="navbar-right" style="margin-top:3px;">
					
					<span id="back" class="buttons">Back</span>
					</a>
				</div>
			
			</div>
			
			<!-- pop up -->


		<div id="collapseOne" class="panel-collapse collapse in"role="tabpanel" aria-labelledby="headingOne">
						
					
<div class="panel-body" id="tabletxt" style="padding: 15px;">

	<logic:present name="accGrpDetails" scope="request">

							<div class="col-md-6" id="txtstyle">
									
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="accyear" tabindex="1" id="accyear" class="form-control" readonly="readonly"
											value='<logic:present name="yearId"  scope="request"><bean:write name="yearId" /></logic:present>' />
											
									
									<input type="hidden" name="accyearHidden" tabindex="1" id="accyearh" class="form-control" readonly="readonly"
									value='<logic:present name="yearIdHidden" scope="request"><bean:write name="yearIdHidden"/></logic:present>' />
									</div>
								</div>
								
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Group Name</label>
									<div class="col-xs-7">
										<input type="text" name="groupname" tabindex="2" id="groupname" class="form-control" readonly="readonly"
									value='<logic:present name="GroupName" scope="request"><bean:write name="GroupName" /></logic:present>' />	
									
									<input type="hidden" name="groupnameHidden" tabindex="2" id="groupnameh" class="form-control"
									value='<logic:present name="GroupIdHidden" scope="request"><bean:write name="GroupIdHidden" /></logic:present>' />		
									
									<input type="hidden"  id="hiddengroupname" value='<logic:present name="GroupName" scope="request"><bean:write name="GroupName" /></logic:present>' />	
									
									</div>
								</div>
											
																			
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Election Title</label>
									<div class="col-xs-7">
									<input type="text" id="electionTitle" name="electionTitle" onkeypress="HideError()" class="form-control" readonly="readonly" 
										value='<logic:present name="electionTitle" scope="request"><bean:write name="electionTitle" /></logic:present>' />
									
									<input type="hidden" name="TitleHidden" id="ElectionTitleh"
									value='<logic:present name="electionTitleIdHidden" scope="request"><bean:write name="electionTitleIdHidden" /></logic:present>' />		
									
									<input type="hidden" id="electionTitleHName" name="electionTitle" class="form-control" 
										value='<logic:present name="electionTitle" scope="request"><bean:write name="electionTitle" /></logic:present>' />
									
									</div>
								</div>
								</div>
							</logic:present>

	</div>

 <div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
<!--  <table style="width:100%;margin-top:-300px;table-layout:auto; margin-right:10px;" class='table' id='allstudent'> -->
  <table class='table' style="margin-right:250px;" id='allstudent' width='100%' style="margin-top:50px">											
 						<thead>
 						<tr><th>Sl.No.</th>
						<th>Election Category</th>
						<th>Priority</th>
						<th>Participate Class</th>
						<th>Gender Wise</th>
						<th>House Wise</th>
						<th>Class Wise</th>
						<th>Nomination For</th>
						<th>Nomination Level</th>
						<th><span id="save" class="glyphicon glyphicon-plus" ></span></th>
						
						</tr>
							</thead>
				
					<tbody>	
					</tbody>
		
						</table>
						</div>
					</div>
				</div>
		</div>


</body>
</html>

