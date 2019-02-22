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
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script> 

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

<script type="text/javascript" src="JS/backOffice/Settings/Meeting.js"></script>  

<!-- <script >
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>
<script>
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
</script> -->
</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Add Meeting/Event</span>
		</p>
		
		
		
		
		
				<div class="successmessagediv" align="center" style="display: none;">
						<div class="message-item">
							<!-- Warning -->
							<a href="#" class="msg-success bg-msg-succes"><span
								class="validateTips"></span></a>
						</div>
					</div>	
					

					
				<div class="errormessagediv" align="center" style="display: none;">
					<div class="message-item">
					<!-- Warning -->
				    <a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
					</div>
					</div>	
				

		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Meeting/Event
							</h4></a>
							

							<div class="navbar-right" >
								
							
							  <span id="meetingsave" class="save2">
								 <img src="images/save.png"  data-toggle="modal"
									 data-toggle="tooltip" data-placement="bottom" title="Save">
									</span> 
							
							
							
							<!-- <a href="" id="meetingsave"><img src="images/save.png"
									style="font-size: 20px; line-height: 34px; color: #989898; margin-top: -5px;"></a>	 -->
									
									
						<a href="adminMenu.html?method=getmeeting"><span class="glyphicon glyphicon-list2"
									 data-toggle="modal"	data-toggle="tooltip" data-placement="bottom" title="List"></span></a>
							</div>
					</div>
					
				<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>

			<input	type="hidden" name="drivercode" id="drivercode" value="<logic:present name="driverlist" ><bean:write name="driverlist" property="driverCode"/></logic:present>"/>
		    <input	type="hidden" name="vehiclecode" id="vehiclecode" value="<logic:present name="fuellist" ><bean:write name="fuellist" property="vehicleCode"/></logic:present>"/>
            <input type="hidden" name="remarkcode" id="remarkcode"  value="<logic:present name="fuellist" ><bean:write name="fuellist" property="fuelCode"/></logic:present>"/>

			<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								id="txtstyle">
								
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">
										Date</label>
									<div class="col-xs-7">
										<input type="text"  class="form-control" readonly="readonly" name="date" id="dateId" onkeypress="HideError()"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dateofJoin"/></logic:present>"/>
									</div>
								</div>
								
								<br />
								
								
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Meeting/Event with</label>
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
									
									<input type="hidden" id="radio" value='<logic:present name="driverlist"><bean:write name="driverlist" property="" /></logic:present>'></input>
										<input type="radio" class="radioactive" name="gender" id="genderId" value="S" /><label for="Student">Student</label>
										
										<input type="radio" class="radioactive" name="gender" id="genderId" value="T" /><label for="Teacher">Teacher</label>
									
									</div>
								</div>
								<br>
								
								
								
								
								<%-- <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4" id="streamlableid"
										style="text-align: right; line-height: 35px;">Stream</label>
									<div class="col-xs-7">
										
								       <logic:present name="streamlist" scope="request">
								          <select name="streamname" id="streamname" class="form-control" >
									      <option value=""></option>
										    <logic:iterate id="stream" name="streamlist" scope="request">
											<option value='<bean:write name='stream' property='streamId'/>'>
											<bean:write name='stream' property='streamName'/></option>
										    </logic:iterate>
							                </select>
											</logic:present>
							   
									</div>
								</div>
								<br /> --%>
                                
							<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										
									  <logic:present name="classpojo" scope="request">
								        <select name="classname" multiple id="classid" onkeypress="HideError()" class="form-control" >
									     <!--  <option value=""></option> -->
										    <logic:iterate id="stream" name="classpojo" scope="request">
											<option value='<bean:write name='stream' property='classId'/>'>
											<bean:write name='stream' property='className'/></option>
										    </logic:iterate>
							                </select>
											</logic:present>     
										
							   
									</div>
								</div>
								
							    <br />
							    <br />
							     <br />
							    
							    
							     <br />
							    
                                <div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Start
									Time
								</label>
								<div class="col-xs-7">
									<div id="datetimepicker3" class="input-append">
										<input type="text" data-format="hh:mm:ss" size="8" onkeypress="HideError()"
											readonly="readonly" name="starttime" id="starttime"
											class="form-control"
											value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="stratTime"></bean:write></logic:present>' /><img
											src="./images/time1.jpg" width="30" height="30"
											class="add-on" style="margin-left: 90%; margin-top: -10%;" />
									</div>
								</div>
							</div>	
								
							
								
								<br />
								 
								 
								 
					<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4" id="streamlableid"
										style="text-align: right; line-height: 35px;">Class Subject</label>
									
									<div class="col-xs-7">
										
								       <logic:present name="subjectlist" scope="request">
								          <select name="accyear" id="subjectid" class="form-control"  onkeypress="HideError()">
								          <option value=""></option>
									      <option value="general">General</option>
										    <logic:iterate id="stream" name="subjectlist" scope="request">
											<option value='<bean:write name='stream' property='subjectId'/>'>
											<bean:write name='stream' property='subjectName'/></option>
										    </logic:iterate>
							                </select>
											</logic:present>
							   
									</div>
								</div>				 
								 
								 
								 
								 
								 
								 
								 <br />
								 
								 <%-- <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Select Type</label>
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
									
									<input type="hidden" id="radio" value='<logic:present name="driverlist"><bean:write name="driverlist" property="" /></logic:present>'></input>
									
									<input type="radio" class="istrans" name="selectType" id="selectType" value="G" /><label for="general">General</label>
										
										<input type="radio" class="istrans" name="selectType" id="selectType" value="S" /><label for="subjet">Subject</label>
									
									</div>
								</div>
								
								
								
								<br />	 --%>
								 
                               <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Student/Teacher</label>
									<div class="col-xs-7">
										<select name="accyear" multiple id="remarksid" class="form-control" onkeypress="HideError()" >
									<option value=""></option>
								<logic:notEmpty name="ALLACCYEARS" scope="request">
										<logic:iterate id="map" name="ALLACCYEARS" scope="request">
											<option value='<bean:write name='map' property='key'/>'>
												<bean:write name="map" property="value" />
											</option>

										</logic:iterate>
											
									</logic:notEmpty>
							    </select>
									</div>
								</div> 
								
								 
                               <%-- <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Student/Teacher</label>
									<div class="col-xs-7">
										<select name="accyear" id="remarksid" class="form-control" >
									<option value=""></option>
								<logic:notEmpty name="ALLACCYEARS" scope="request">
										<logic:iterate id="map" name="ALLACCYEARS" scope="request">
											<option value='<bean:write name='map' property='key'/>'>
												<bean:write name="map" property="value" />
											</option>

										</logic:iterate>
											
									</logic:notEmpty>
							    </select>
									</div>
								</div> --%>
							
								 <br />
									 <br />
									 	
								 <br />
								 <br />
															
								
								<%-- <div class="form-group">
									<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description
									</label>
									<div class="col-xs-7">
										<textarea name="remarks" id="remarks" class="form-control" style=" width: 100%; height: 64px;"><logic:present name="driverlist"><bean:write name="driverlist" property="address"/></logic:present></textarea>
										<span class="LblDialog" style="font-size: 13px !important">Character Remaining :&nbsp;</span><span id="maxlimit" class="LblDialog" style="font-size: 13px !important"></span>
									</div>
								</div> --%>
								
								
									
								
							</div>
							
							
							
							
							
							
							<div class="col-md-6"
								id="txtstyle">
								
			
								
								
								
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Subject Name</label>
									<div class="col-xs-7">
											<input type="text" name="meetingtitle" id="meetingtitle" class="form-control" onkeypress="HideError()"  value="<logic:present name="AcadamicYearPlanDetails"  scope="request"><bean:write name="AcadamicYearPlanDetails" property="weekday"/></logic:present>"/>
									</div>
								</div>
								
								<br />
								
								
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Venue</label>
									<div class="col-xs-7">
											<input type="text" name="venueid" id="venueid" class="form-control"  onkeypress="HideError()" value="<logic:present name="AcadamicYearPlanDetails"  scope="request"><bean:write name="AcadamicYearPlanDetails" property="weekday"/></logic:present>"/>
									</div>
								</div> 
								 
								 
								 
								 <br /> 
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Section</label>
									<div class="col-xs-7">
										<select name="sectionname" multiple id="sectionid" onkeypress="HideError()" class="form-control" >
									<option value=""></option>
								<logic:notEmpty name="ALLACCYEARS" scope="request">
										<logic:iterate id="map" name="ALLACCYEARS" scope="request">
											<option value='<bean:write name='map' property='key'/>'>
												<bean:write name="map" property="value" />
											</option>

										</logic:iterate>
											
									</logic:notEmpty>
							    </select>
									</div>
								</div>
								
								 <br />
								  <br />
								   <br />
								
								
								
								  <br />
								
								
								
								
								
								
								
							
									
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">End Time</label>
								<div class="col-xs-7">
									<div id="datetimepicker4" class="input-append">
										<input type="text" data-format="hh:mm:ss" size="8"
											readonly="readonly" name="endtime" id="endtime" onkeypress="HideError()"
											class="form-control"
											value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="endTime"></bean:write></logic:present>' /><img
											src="./images/time1.jpg" width="30" height="30"
											class="add-on" style="margin-left: 90%; margin-top: -10%;" />
									</div>
								</div>
							</div>	
								<br />	
							
								
								
								
							
								
								
								<%-- <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Subject</label>
									<div class="col-xs-7">
										<select name="accyear" id="subjectid" class="form-control" >
									<option value=""></option>
								<logic:notEmpty name="ALLACCYEARS" scope="request">
										<logic:iterate id="map" name="ALLACCYEARS" scope="request">
											<option value='<bean:write name='map' property='key'/>'>
												<bean:write name="map" property="value" />
											</option>

										</logic:iterate>
											
									</logic:notEmpty>
							    </select>
									</div>
								</div> --%>
								
							
								
								
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description
									</label>
									<div class="col-xs-7">
										<textarea name="remarks" id="remarks" class="form-control" onkeypress="HideError()" style=" width: 100%; height: 108px; margin-top: 0px; margin-bottom:"><logic:present name="driverlist"><bean:write name="driverlist" property="address"/></logic:present></textarea>
									</div>
								</div>
								
							</div>	
								
								
						
								
								
								
								 <br />		
								
								
								
								
								
								
							</div>
							
							
						
						</div>
					</div>
					</div>
					</div>
					
</form>
				</div>
			
				
			
	
</body>

</html>
