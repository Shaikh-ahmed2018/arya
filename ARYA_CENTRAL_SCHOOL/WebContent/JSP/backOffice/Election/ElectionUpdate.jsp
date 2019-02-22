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

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
<link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript" src="JS/backOffice/Election/ElectionInsertUpdate.js"></script>



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
.add-on{
position: absolute;
top:1px;
right: 8px;
}

</style>

<style>
.buttons{

vertical-align: 0px;

}

#pdfDownload:hover {
	cursor: pointer;
}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
	<div class="searchWrap">
		<div  id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Modify Election Setting</span>
			</p>
		</div>
		
	</div>
		
		<input type="hidden" id="succmsg" value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />
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
				<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" >
					<h3 class="panel-title" style="color: #767676; vertical-align: middle;"> <span class="glyphicon glyphicon-menu-hamburger"></span>
					&nbsp;&nbsp; Add Election Details
					</h3></a>
					
					<div class="navbar-right" style="top:-3px; right:10px;">
					<a>
					<span class="buttons" id="update">Update</span> </a> 
				<span class="buttons" id="back">Back</span></a>			
				</div>
			</div>
			<!-- pop up -->


<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
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
											
																			
							<div class="form-group clearfix ">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Election Title</label>
									<div class="col-xs-7">
									<input type="text" id="electionTitle" name="electionTitle" onkeypress="HideError()" class="form-control" 
										value='<logic:present name="electionTitle" scope="request"><bean:write name="electionTitle" /></logic:present>' />
									
									<input type="hidden" name="TitleHidden" id="ElectionTitleh"
									value='<logic:present name="electionTitleIdHidden" scope="request"><bean:write name="electionTitleIdHidden" /></logic:present>' />		
									
									<input type="hidden" id="electionTitleHName" name="electionTitle" class="form-control" 
										value='<logic:present name="electionTitle" scope="request"><bean:write name="electionTitle" /></logic:present>' />
									
									</div>
								</div>
							
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">From Date
								</label>
								<div class="col-xs-7">
								<input type="text" name="fromdate" id="Fromdate" tabindex="3" readonly="readonly" onkeypress="HideError()" class="form-control" 
								value="<logic:present name="fromDate" scope="request"><bean:write name="fromDate" /></logic:present>"></input>
								
							</div>
							</div>
								<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">To Date</label>
								<div class="col-xs-7">
								<input type="text" name="todate" id="todate" tabindex="4"
										readonly="readonly" 
										class="form-control" onkeypress="HideError()"
										 value="<logic:present name="toDate"  scope="request" ><bean:write name="toDate" /></logic:present>" />
									
								</div>
							</div>
						
						
					</div>
								
								
			<div class="col-md-6" id="txtstyle" style="margin-top:120px">
		
						<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">StartTime <font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<div id="datetimepicker3" class="input-append">
										<input type="text" data-format="hh:mm:ss" size="8"
											readonly="readonly" name="starttime" id="starttime" onkeypress="HideError()"
											class="form-control"
											value='<logic:present name="startTime"  scope="request"><bean:write name="startTime" ></bean:write></logic:present>' /><span class="add-on"><!-- <img src="images/time2.png" width="30" height="8"  align="top"/> -->
													<img src="./images/time1.jpg" width="30" height="30"
											class="add-on" />
												</span>
											</div>
								</div>
							</div>
								
								<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">End Time <font color="red">*</font></label>
								<div class="col-xs-7">
									<div id="datetimepicker4" class="input-append DatePicker">
										<input type="text" data-format="hh:mm:ss" size="8"
											readonly="readonly" name="endtime" id="endtime"
											class="form-control" onkeypress="HideError()"
											value='<logic:present name="endTime"  scope="request"><bean:write name="endTime" ></bean:write></logic:present>' /><span class="add-on">
											<img src="./images/time1.jpg" width="30" height="30" class="add-on" /></span>
									</div>
								</div>
							</div>
		
								
							</div>

						</logic:present>

 <div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">

						</div>
					</div>
				</div>
		</div>
	</div>


</body>
</html>