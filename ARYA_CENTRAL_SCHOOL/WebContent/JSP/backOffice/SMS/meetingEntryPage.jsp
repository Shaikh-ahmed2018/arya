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

<script type="text/javascript" src="JS/backOffice/SMS/meetingEntry.js"></script> 

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
</script> --><style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Meeting/Event</span>
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
						
							<a data-toggle="collapse" data-parent="#accordion2"
								href="#collapseOne" style="color: #767676"> <h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Meeting/Event
							</h4></a>
							

							<div class="navbar-right" >
								
							
							  <span id="save" class="buttons" data-toggle="modal"
								data-placement="bottom">Save
									</span> 
							
							
							&nbsp;
							<!-- <a href="" id="meetingsave"><img src="images/save.png"
									style="font-size: 20px; line-height: 34px; color: #989898; margin-top: -5px;"></a>	 -->
									
									
						<span id="back" class="buttons">Back</span></a>
							</div>
					</div>
					
				

			<input	type="hidden" name="drivercode" id="drivercode" value="<logic:present name="driverlist" ><bean:write name="driverlist" property="driverCode"/></logic:present>"/>
		    <input	type="hidden" name="vehiclecode" id="vehiclecode" value="<logic:present name="fuellist" ><bean:write name="fuellist" property="vehicleCode"/></logic:present>"/>
            <input type="hidden" name="remarkcode" id="remarkcode"  value="<logic:present name="fuellist" ><bean:write name="fuellist" property="fuelCode"/></logic:present>"/>

			<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								id="txtstyle">
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">
										Date</label>
									<div class="col-xs-7">
										<input type="text"  class="form-control" readonly="readonly" name="date" id="dateId" onkeypress="HideError()"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dateofJoin"/></logic:present>"/>
									</div>
								</div>
								
								
								
							<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class</label>
								
											<div class="col-xs-7">
										<select name="sectionname" multiple id="classid" onkeypress="HideError()" class="form-control" >
									<!-- <option value=""></option> -->
								<logic:notEmpty name="classList" scope="request">
										<logic:iterate id="map" name="classList" scope="request">
											<option value='<bean:write name='map' property='classid'/>'>
												<bean:write name="map" property="className" />
											</option>

										</logic:iterate>
											
									</logic:notEmpty>
							    </select>
									</div>
									</div>
								
							 
							     
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
								
							
								
							
								 
								 
								 
					<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" id="streamlableid"
										style="text-align: right; line-height: 35px;">Subject</label>
									
									<div class="col-xs-7">
										<select name="sectionname"  id="subjectid" onkeypress="HideError()" class="form-control" >
									<option value="general"></option>
									
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
								 
							
								 
								 <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Student</label>
									<div class="col-xs-7">
										<select name="accyear" multiple id="studentid" class="form-control" onkeypress="HideError()" >
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
								
							</div>
							
							<div class="col-md-6"
								id="txtstyle">
								
								<%--  <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Name</label>
									<div class="col-xs-7">
											<input type="text" name="venueid" id="venueid" class="form-control"  onkeypress="HideError()" value="<logic:present name="AcadamicYearPlanDetails"  scope="request"><bean:write name="AcadamicYearPlanDetails" property="weekday"/></logic:present>"/>
									</div>
								</div>  --%>
							
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Section</label>
									<div class="col-xs-7">
										<select name="sectionname" multiple id="sectionid" onkeypress="HideError()" class="form-control" >
									<!-- <option value=""></option> -->
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
								
							
										
							<div class="form-group clearfix">
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
								
								 
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Title</label>
									<div class="col-xs-7">
									
									<select name="titlename" id="titleid" onkeypress="HideError()" class="form-control" >
									<option value=""></option>
									 <option value="meeting">Meeting</option> 
									 <option value="event">Events</option>

							    </select>
									
									
									</div>
								</div>
								
							
								
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">SMS Text
									</label>
									<div class="col-xs-7">
										<textarea name="remarks" id="description" class="form-control" onkeypress="HideError()" style=" width: 100%; height: 108px; margin-top: 0px; margin-bottom:"><logic:present name="driverlist"><bean:write name="driverlist" property="address"/></logic:present></textarea>
									</div>
								</div>
								
								
							</div>	
								
							</div>
						
						</div>
						
						</div>
					</div>
					
						</form>
					
					</div>
	
</body>

</html>
