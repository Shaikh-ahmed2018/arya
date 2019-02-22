<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>

<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
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
<script type="text/javascript" src="JS/backOffice/Transport/routeMaster.js"></script>
<script type="text/javascript" src="JS/backOffice/Transport/routeMasterMapping.js"></script>

<style>
.save:hover {
	cursor: pointer;
}


.ui-dialog-buttonset button{
	 padding: 5px;
    font-size: 14px;
    background-color: #07AAB9;
    color: #fff;
    border-radius: 3px;
    cursor: pointer;

}

 #myDialog{
 height : 400px !important;
	overflow-y: scroll;

 } 
 





.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons{

width : 600px !important;
height : 500px !important;
}

</style>

<style>
#list:hover {
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



.stagebutton{ padding: 5px;
    font-size: 14px;
    background-color: #07AAB9;
    vertical-align: 5px;
    color: #fff;
    border-radius: 3px;
    cursor: pointer;
    font-family: Roboto Medium;
    }
    
.stagetable{
max-height:350px !important;
overflow-y: scroll;

}
</style>


</head>

<body>
<div id="myDialog" style="display: none;">
   
		
			<br/>
			<input type="checkbox" name="selectBoxD"  class="selectAll" id="displayid" value=""/> Select All Stages
			<table id="tableid" class="table table-bordered">
		 			<tr>
					<th class="headth">Select</th>
					<th class="headth">Stages</th>
					</tr>
					 <logic:present name="StageDetails" scope="request">
					 <logic:iterate id="StageDetails" name="StageDetails" scope="request">
					<tr>
					<td><input type="checkbox" name="selectBox"  class="selectBox"  value="<bean:write name="StageDetails" property="stageCode"  />"></input></td>
					<td><bean:write name="StageDetails" property="stageName" /></td>
					</tr>
					</logic:iterate>
					</logic:present>
					</table>

</div>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="Route" scope="request"><bean:write name="Route"></bean:write></logic:present></span>
		</p>

	
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


		

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" style="color: #767676;vertical-align: text;"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Route
							Master Details
						</h4></a>
					

						<div class="navbar-right">
						
							<span class="buttons"  id="save" >Save</span> 
							
		 					<span class="buttons" id="list" >Back</span>
						
						</div>
					
				</div>
				
				
				
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">

						<div class="col-md-6" id="txtstyle">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Route No  <font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<input type="text" name="routeNo" id="routeNo"  onkeypress="HideError()"
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="routeNo"></bean:write></logic:present>'
										 class="form-control" onblur="checkRouteNo()" />
								<input type="hidden" id="hRouteCode" value="" />
								<input type="hidden" id="haccyear" value='<logic:present name="haccyear" scope="request"><bean:write name="haccyear"></bean:write></logic:present>' />
								
								</div>
							</div>
							



							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Route
									Short Name </label>
								<div class="col-xs-7">
									<input type="text" name="routeLogicName" id="routeLogicName" onblur="myFunction2()"
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="routeLogicName"></bean:write></logic:present>'
										maxlength="45" class="form-control" />
								</div>
							</div>

						
						
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Start
									Time <font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<div id="datetimepicker3" class="input-append">
										<input type="text" data-format="hh:mm:ss" size="8"
											readonly="readonly" name="starttime" id="starttime" onkeypress="HideError()"
											class="form-control"
											value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="stratTime"></bean:write></logic:present>' /><span class="add-on"><!-- <img src="images/time2.png" width="30" height="8"  align="top"/> -->
													<img src="./images/time1.jpg" width="30" height="30"
											class="add-on" />
												</span>
												
												
											
  
									</div>
								</div>
								
							</div>
							
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Total
									Stops
								</label>
								<div class="col-xs-7">
									<input type="text" name="totalStops" id="totalStops" readonly
										maxlength="5" onkeypress="HideError()" placeholder="0"
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="totalStops"></bean:write></logic:present>'
										class="form-control" />
								</div>
							</div>
							<br />
							
						</div>
						
						<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Route
									Name <font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<input type="text" name="routeName" id="routeName" onkeypress="HideError()"
										maxlength="48" class="form-control"
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="routeName"></bean:write></logic:present>' />
								</div>
							</div>

							

						

							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Total
									Distance(km)
								</label>
								<div class="col-xs-7">
									<input type="text" name="totalDistance" id="totalDistance" onblur="myFunction3()"
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="totalDistance"></bean:write></logic:present>'
										maxlength="7" class="form-control" />
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
											value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="endTime"></bean:write></logic:present>' /><span class="add-on"><!-- <img src="images/time2.png"
													width="30" height="8"  align="top"/> -->
													<img
											src="./images/time1.jpg" width="30" height="30"
											class="-on" />
												</span>
									</div>
								</div>
							</div>
						
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Halt Time(min)
									
								</label>
								<div class="col-xs-7">
									<input type="text" name="halttime" id="halttime" maxlength="2" onblur="haltName1()" 
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="halttime"></bean:write></logic:present>'
										class="form-control" />
								</div>
							</div>
							<br />
				
							
							
						
							<span id="checklocations" class="buttons"  style="float: right; margin-right:11%; margin-top:-9px; margin-bottom:2%;font-family: Roboto Medium;">Add Stages</span>
							
							<!-- <span class="buttons"  id="save" >Save</span>   -->
							
						</div>
						<div class="stagetable col-md-12">
						<table id="myTable" class="table table-bordered " 
							style=" display: none;">

							<thead>
								<tr>
								<th class="headth" style="text-align: left;">Serial No.</th>
								<th class="headth" style="text-align: left;">Stage Name</th>
								</tr>
							</thead>
							<tbody>
							<logic:present name="unmappedStages" scope="request">
								<logic:iterate id="unmappedStages" name="unmappedStages" scope="request"> 
									<tr id="<bean:write name="unmappedStages" property="stage_id"></bean:write>">
										<td><bean:write name="unmappedStages" property="count"></bean:write></td>
										<td><bean:write name="unmappedStages" property="stage_name"></bean:write></td>
										
									</tr>
								</logic:iterate>
							
							</logic:present>
									
							</tbody>
						</table>
</div>
						
						
						<input type="hidden" name="routeid" id="routeid" 
							value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="routeCode"/></logic:present>' />
						
					</div>
				</div>

			</div>
		</div>
	</div>
</body>

</html>
