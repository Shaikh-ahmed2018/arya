
<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
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
<script type="text/javascript" src="JS/Admin/Latheef.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>

<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/custome.css" rel="stylesheet">

<script type="text/javascript" src="JS/backOffice/Election/boothSetting.js"></script>


<style>
.buttons {
	vertical-align: 0px;
}
</style>
<style>
#allstudent tr {
	font-size: 14px;
	border-bottom: 1px solid #ddd;
	border-left: 1px solid #ddd;
}
.multiple{
height: auto !important;
}
#allstudent th {
	background: #f9f9f9 !important;
	border-bottom: 1px solid #ddd;
	border-top: 1px solid #ddd;
	border-right: 1px solid #ddd;
	font-size: 11pt;
	color: #767676 !important;
	text-align: center;
	font-family: Roboto Medium;
}

#addstaffsubject tr {
	font-size: 14px;
	border-bottom: 1px solid #ddd;
	border-left: 1px solid #ddd;
}

#addstaffsubject tr:nth-child(n) {
	background-color: #F2FAFC;
}

#addstaffsubject tr:nth-child(2n) {
	background-color: #FFFFFF;
}

#addstaffsubject tr:hover {
	background: #9CDDE3;
	color: #fff;
	cursor: pointer;
}

#addstaffsubject th {
	background: #f9f9f9 !important;
	border-bottom: 1px solid #ddd;
	border-top: 1px solid #ddd;
	border-right: 1px solid #ddd;
	font-size: 11pt;
	color: #767676 !important;
	text-align: center;
	font-family: Roboto Medium;
}

#addstaffsubject th a {
	color: #767676 !important;
}

#addstaffsubject tr :HOVER {
	color: #000 !important;
}

#addstaffsubject  tr td {
	border-right: 1px solid #ddd;
	border-left: 0;
	border-top: 0;
	border-bottom: 0;
	color: #000000;
}

.inputbox {
	display: block;
	width: 100%;
	height: 34px;
	padding: 6px 12px;
	font-size: 13px;
	line-height: 1.42857;
	color: #767676;
	background-color: #fff;
	background-image: none;
	border: 1px solid #cbd5dd;
	border-radius: 0;
	border: 0;
}

.select-control {
	border: 0;
	display: block;
	width: 100%;
	height: 34px;
	padding: 6px 12px;
	font-size: 13px;
	line-height: 1.42857;
	color: #767676;
	background-color: #fff;
	background-image: none;
	border-radius: 0;
}

.own-panel {
	padding: 10px 0px;
}

div.slide{
width:1500px !important;
height:300px !important;
margin-left:3px !important;

}
.DeActive{
cursor: pointer;
}
</style>


<!-- csss code -->


<!-- jquery code -->


</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"style="font-family: Roboto Medium; font-size: 16pt;
	 color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 0px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span>Booth Setting</span>
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


<form id="teacherform" action="teacherregistration.html?method=teacherregister" enctype="multipart/form-data" method="post">
	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
		<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#" style="color: #767676;vertical-align: text-top"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Add Booth Details
							</h4></a>
						
					</div>


			<div id="headOne" class="headOne">
				<div class="panel-body own-panel">
				
					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Academic Year</label>
									<div class="col-xs-7">
											<select id="academicYear" name="accyear" class="form-control">
											<option value="all">All</option>
										
										</select>
									</div>
								</div>
				
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Group Name</label>
									<div class="col-xs-7">
											<select id="groupName" name="grpname" class="form-control">
											<option value="all">----Select----</option>
										</select>
									</div>
							</div>	
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Election Title</label>
										<div class="col-xs-7">
											<select id="electionTitle" name="electionTitle" class="form-control">
											<option value="all">----Select----</option>
										</select>
									</div>
									</div>
							</div>
						</div>
					</div>
				</div><!-- end of first window  -->



<!-- Second POPUp statrs -->	
<div class="panel panel-primary">
						<div class="panel-heading" role="tab" id="headingFour">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseFour"
									style="color: #767676;vertical-align: text-top" aria-expanded="false"
									aria-controls="collapseFour"> <i
									class="glyphicon glyphicon-menu-hamburger"></i>
									&nbsp;&nbsp;Booth Details
								</a>
							</h4>
					
					<a class="btn"  style="position: absolute;right: 0;top: 0px;"><span id="save" onclick="add()" class="glyphicon glyphicon-plus" ></span></a>
  			
    	<div id="electionCategoryAddDialog" style="display: none">
			<div class="col-md-12">
			<div class="row">
				</div>
			<div class="col-md-12" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">

					<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right;">Booth Name</label>
							<div class="col-xs-6">
							<input type="text" name="boothName" id="boothName"  onkeypress="HideError()" class="form-control" />
							<input type="hidden" name="boothNameHidden"  id="boothNameHidden" class="form-control"  value="" /> 
							</div>
					</div>
					
					<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right;">Staff Incharge</label>
							<div class="col-xs-6">
							<select id="staffIncharge" name="staffIncharge" class="form-control multiple" >
											<option value="">---Select---</option>
										</select>
							
							</div>
					</div>
					
					<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-4" style="text-align: right;">Central System</label>
							<div class="col-xs-6">
							<input type="text" name="centralSystem" id="centralSystem"  onkeypress="HideError()" class="form-control" style="text-transform:uppercase"/> 
							</div>
					</div>
					
					
					<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right;">Central System IP</label>
							<div class="col-xs-6">
							<input type="text" name="centralSystemIp" id="centralSystemIp"  onkeypress="HideError()" class="form-control" style="text-transform:uppercase"/> 
	
							</div>
					</div>
					<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-4"	style="text-align: right;">Voter Class</label>
							<div class="col-xs-6">
										<select id="voterClass" name="voterClass" class="form-control multiple" multiple>
											<option value="">---Select---</option>
										</select>
										</div>
					</div>
					
						
					
				</div>
			</div>		
   </div><!--end popup data  -->

</div>
						<div id="collapseFour" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingFour">
							<html:hidden property="count" styleId="count" value="0" />
							<div class="panel-body own-panel">
								<div class="col-md-11" id="dynamicdiv"
									style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
									<div class="col-xs-12">
									


  
  <div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
 <table class='table' style="margin-right:250px;" id='allstudent1' width='100%' style="margin-top:50px">
  
  
  										
 						<thead>
 						<tr><th>Sl.No.</th>
						<th>Booth Name</th>
						<th>Staff Incharge</th>
						<th>Central System</th>
						<th>Central System IP</th>
						<th> Voter Class</th>
				<!-- 
						//<th><span id="save" class="glyphicon glyphicon-plus" ></span></th> -->
						
						</tr>
							</thead>
				
					<tbody>	
					
					</tbody>
		
						</table>
						</div>							

				
									</div>
								</div>
							</div>
	
						</div>
					</div>


<!-- polling machine starts -->
					<div class="panel panel-primary">
						<div class="panel-heading" role="tab" id="headingFive">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseFive"
									style="color: #767676;vertical-align: text-top" aria-expanded="false"
									aria-controls="collapseFive"> <i
									class="glyphicon glyphicon-menu-hamburger"></i>
									&nbsp;&nbsp;Polling Machine Details
								</a>
							</h4>
<!-- pop Up poling machine -->

 		<a class="btn" data-popup-open="popup-1" href="#" style="position: absolute;right: 0;top: 0px;"><span  
  		id="savedata"  class="glyphicon glyphicon-plus" ></span></a>
  		
  		 <div id="electionCategoryAddDialog1" style="display: none">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
							
						</div>
					</div>
				</div>
			<div class="col-md-12" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">


			<%-- <logic:present name="PollingMachineId" scope="request"> --%>
					<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right;">Booth Name</label>
							<div class="col-xs-6">
							<select id="boothNameforPolling" name="boothNameforPolling" class="form-control">
											<option value="all">All</option>
							</select>
							
							<input type='hidden' id='pollingHiddenId' value=''>
							</div>
					</div>
			<%-- </logic:present> --%>
					<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right;">Polling Machine No.</label>
							<div class="col-xs-6">
			
						<input type="text" name="pollingMachineName"  id="pollingMachineName" class="form-control"  value="" /> 
							
							</div>
					</div>
					
					<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-4" style="text-align: right;"> System Name</label>
							<div class="col-xs-6">
							<input type="text" name="pollingSystemName" id="pollingSystemName"  onkeypress="HideError()" class="form-control"/> 
							</div>
					</div>
					
					
					<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right;"> System IP</label>
							<div class="col-xs-6">
							<input type="text" name="pollingSystemIp" id="pollingSystemIp"  onkeypress="HideError()" class="form-control"/> 
							</div>
					</div>
			</div>
		</div>
</div></div>


						<div id="collapseFive" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingFour">
							<html:hidden property="subjectcount" styleId="subjectcount"
								value="0" />
							<div class="panel-body own-panel">
					
						<div class="col-md-6" id="txtstyle">
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Booth Name</label>
									<div class="col-xs-7">
										<select id="boothNameforPolling2" name="boothNameforPolling2" class="form-control">
											<option value="all">All</option>
										</select>
									
									</div>
									</div>
						</div>
								
					
							<div class="col-md-11" id="dynamicdiv"
									style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								 <div class="col-xs-12"> 
									
   <div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
 <table class='table' style="margin-right:250px;" id='allstudent2' width='100%' style="margin-top:50px">
  
  
  										
 						<thead>
 						<tr><th>Sl.No.</th>
						<th>Polling Machine Number</th>
						<th>System Name</th>
						<th>System IP</th>
						</tr>
							</thead>
				
					<tbody>	
					</tbody>
		
						</table>
						</div>	
										
										
									</div>
								 </div> 
							</div>
						</div>
					</div>
					
			
				</div>
			</div>
		</form>

		<!-- </form> -->
	</div>

</body>

</html>
