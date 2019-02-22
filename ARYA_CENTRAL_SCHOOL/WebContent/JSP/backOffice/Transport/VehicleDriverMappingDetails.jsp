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

<script type="text/javascript" src="JS/backOffice/Transport/VehicleDriverMapping.js"></script> 
<script type="text/javascript" src="JS/backOffice/Transport/mapping.js"></script>

</head>

<style>

#copy{
	padding: 0;
    height: 270px;
    border: 1px solid #ddd;
    overflow-y: scroll;
    width: 100%;
}
#original{
	padding: 0;
    height: 270px;
    border: 1px solid #ddd;
    overflow-y: scroll;
    width: 100%;
}

.maparrows{
    border: 1px solid #AFAEAE;
    width: 35px;
	margin: 7px 16px;
}

#forpenal{
	padding:15px;    font-family: Roboto Medium;    font-size: 12pt;    color: #767676;
}
#forpenal span{
	font-family: Roboto Medium;    font-size: 10pt;padding:0 5px;
}

.mapnames{
margin:0px 0 6px;
}

#headertop{
padding: 12px 0 !important;
}



</style><style>
.buttons{

vertical-align: 0px;

}
</style>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Vehicle Driver Mapping</span>
		</p>
		
					<div class="errormessagediv" style="display: none;">
						<div class="message-item">
							<!-- Warning -->
							<a href="#" class="msg-warning bg-msg-warning"><span
								class="validateTips"></span></a>
						</div>
					</div>
					
					<div class="successmessagediv" style="display: none;">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>
						
			<form method="post" class="form-inline">
			
				<div class="panel-group" id="accordion">
				<div class="panel panel-default">

					<div class="panel-heading"  id="headingOne">
						
							
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;">
							<h4 class="panel-title" id="beforeparent"> <i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Mapping
							</h4></a>
									
								<div class="navbar-right">
								
									<span class="buttons" id="save" >Save</span>&nbsp;
									
									 
										 <span id="back" class="buttons" >Back</span>
									 </a>
								</div>
							
				
					</div>
					
					
					
					<input type="hidden" id="hvehiclecode" value='<logic:present name="MappingVO"><bean:write name="MappingVO" property="vehiclecode"></bean:write></logic:present>'/>
					<input type="hidden" id="hvehiclename" value='<logic:present name="MappingVO"><bean:write name="MappingVO" property="vehiclename"></bean:write></logic:present>'/>
					<input type="hidden" id="hdrivercode" value='<logic:present name="MappingVO"><bean:write name="MappingVO" property="drivercode"></bean:write></logic:present>'/>
					<input type="hidden" id="hdrivername" value='<logic:present name="MappingVO"><bean:write name="MappingVO" property="driverName"></bean:write></logic:present>'/>
					<input type="hidden" id="hsno" value='<logic:present name="MappingVO"><bean:write name="MappingVO" property="sno"></bean:write></logic:present>'/>
					
					<div id="collapseOne" class="panel-collapse collapse in">
						<div class="panel-body" id="forpenal">
							<div class="col-md-4 col-md-offset-4" id="txtstyle">
								<div class="form-group">
									<label for="exampleInputName2">Select Name</label>
									 <select class="form-control" id="driver" name="driver" onchange="getMappedVehicle(this.value)">
										<option value=""></option>
										<logic:present name="getDriverList">
											<logic:iterate id="vehicle" name="getDriverList">
												<option
													value='<bean:write name="vehicle" property="driverId" />'><bean:write
														name="vehicle" property="driverName" /></option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>
							
							<div class="col-md-3 col-md-offset-2" id="headertop">
								<p class="mapnames">Available Employees</p>
								<div class="col-md-12">
									<select class="form-control" multiple="multiple" id="original">
										
									</select>
								</div>
							</div>
							<div class="col-md-1 ">
								<br />
								<br />
								<br />
								<br />
								<br />
								<br /> 
								<input type="button" name="placement" value="&gt;"
										onclick="addIndivudualtext();" class="maparrows">
								
								<input type="button" name="placement" value="&lt;"
										onclick="deselectIndivivdualData()" class="maparrows">
								
								
							</div>
							<div class="col-md-3" id="headertop">
								<p class="mapnames">Mapped Employees</p>
								<div class="col-md-12">

									<select class="form-control " multiple="multiple" id="copy"
										style="height: 270px; width: 100%">
										
									</select>
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
