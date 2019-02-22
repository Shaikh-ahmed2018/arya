 <!-- Written By Swathi -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
<script type="text/javascript"	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Admin/AcadamicYearPlanMaster.js"></script>
<link rel="stylesheet"	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
<link href="CSS/newUI/modern-business.css" rel="stylesheet"/>
<link href="CSS/newUI/custome.css" rel="stylesheet"/>
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	


<style>

.save:hover {
	cursor: pointer;
}
.navbar-right span{
margin-right: 3px;
}
.Dtetimepicker{
position: relative;
}
.Dtetimepicker .add-on{
position: absolute;
right: 0;
top: 1px;
}

</style>



</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="title"><bean:write name="title"></bean:write></logic:present></span>
			
		</p>
			
						<div class="errormessagediv" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning" style="text-align: center;"><span
									class="validateTips" style="text-align: center;"></span></a>
							</div>
						</div>
				
			
				<center>
					<logic:present name="successMessage" scope="request" >
						<div class="successmessagediv" style="width:auto !important">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"><span><bean:write	name="successMessage" scope="request" /></span></a>
							</div>
						</div>
					</logic:present>
					
						<logic:present name="errorMessage" scope="request">
							<div class="successmessagediv" style="width:auto !important">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-warning bg-msg-warning"><span>
											<bean:write name="errorMessage" scope="request" />
									</span></a>
								</div>

							</div>
						</logic:present>
		          </center>
						
	
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
						<a data-toggle="collapse" data-parent="#accordion"
								href="#" style="color: #767676;"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								<h4 class="panel-title"	>Late Coming/Early Going</h4></a>
						

					<div class="navbar-right">
						<span class="buttons" id="save">Save</span>  
						<span id="back" class="buttons">Back</a></span>
					</div>


				</div>

					
			<form id="AcadamicYearPlanForm" action="acadamicYearPlan.html?method=insertAcadamicYearPlan" 
			method="post" enctype="multipart/form-data">	
		       <div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-size: 11pt; font-family: Open Sans Light;color: #5d5d5d;">
								
								<input type="hidden" name="heventId"  id="heventId" value="<logic:present name="AcadamicYearPlanDetails"  scope="request"><bean:write name="AcadamicYearPlanDetails" property="eventid"/></logic:present>"/>
								<input type="hidden" name="hFile"  id="hFile" value="<logic:present name="AcadamicYearPlanDetails"  scope="request"><bean:write name="AcadamicYearPlanDetails" property="file"/></logic:present>"/>
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">School Name <label style="color: red;">*</label></label>
								<div class="col-xs-7">
							<select id="locationname" name="locationId" class="form-control">
							<option value="">-------------Select-----------</option>
								<logic:present name="locationList">
									<logic:iterate id="Location" name="locationList">
										<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
									</logic:iterate>
								</logic:present>
						
						</select>
						</div>
								<input type="hidden" name="schoolId" class="form-control" id="schoolId" value='<logic:present name="AcadamicYearPlanDetails"><bean:write name="AcadamicYearPlanDetails" property="locationId" /></logic:present>'></input>
							</div>	
					
								
                                
                                <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Late Time <label  style="color: red;">*</label></label>
									<div class="col-xs-7">
											<div id="datetimepicker3"	class="input-append Dtetimepicker" style="width:100%;">
												<input type="text" data-format="hh:mm:ss" readonly="readonly" name="starttime " onkeypress="HideError()" id="starttime" class="form-control" value="<logic:present name="AcadamicYearPlanDetails"  scope="request"><bean:write name="AcadamicYearPlanDetails" property="starttime"/></logic:present>"/><span class="add-on"><!-- <img src="images/time2.png"
													width="30" height="8"  align="top"/> -->
													<img
											src="./images/time1.jpg" width="30" height="30"
											class="add-on" />
												</span>
							               </div>
									</div>
								</div>
						
								 
							
                            
						
								
							</div>
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								
							
								<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Staff Type <label style="color: red;">*</label></label>
								<div class="col-xs-7">
								<select name="teachertype" id="teachertype" class="form-control">
										<option value="">----------Select----------</option>
											<option value="TEACHING">TEACHING</option>
											<option value="NON TEACHING">NON TEACHING</option>
											<option value="OFFICE STAFF">OFFICE STAFF</option>
											<option value="SUBJECT TEACHER">SUBJECT TEACHER</option>
											<option value="MISC">MISC</option>
											<option value="PROBATION">PROBATION</option>
											<option value="GENERAL">GENERAL</option>
											<option value="TEMPORARY">TEMPORARY</option>
											<option value="HOUSEKEEPING">HOUSEKEEPING</option>
									</select>
						</div>
								<input type="hidden"  class="form-control" id="hiddenteachertype" value='<logic:present name="AcadamicYearPlanDetails"><bean:write name="AcadamicYearPlanDetails" property="locationId" /></logic:present>'></input>
							</div>
								
                                
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Early Time <label  style="color: red;">*</label></label>
									<div class="col-xs-7">
				                          <div id="datetimepicker4" class="input-append Dtetimepicker"	style="width: 100%;">
												<input type="text" data-format="hh:mm:ss" 
													readonly="readonly" name="endtime" id="endtime"  class="form-control"  onkeypress="HideError()" value="<logic:present name="AcadamicYearPlanDetails"  scope="request"><bean:write name="AcadamicYearPlanDetails" property="endtime"/></logic:present>"/> <span class="add-on"> 
													<!-- <img src="images/time2.png" width="25"	height="8" /> -->
													<img
											src="./images/time1.jpg" width="30" height="30"
											class="add-on" style="margin-left: 88%; margin-top: -12%;" />
												</span>
											</div>	
									</div>
								</div>
                                
					
								
									</div>
							
						
						</div>
					</div>
				</form>
					
				</div>
		</div>
		
	</div>
</body>

</html>