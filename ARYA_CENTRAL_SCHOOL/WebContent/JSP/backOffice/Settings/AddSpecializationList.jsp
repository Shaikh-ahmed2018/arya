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

<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JS/common.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
	

<script type="text/javascript" src="JS/backOffice/Settings/addSpecialization.js"></script>

<script type="text/javascript">
	
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>
<style>
.glyphicon:hover{

cursor: pointer;
}
#classSave:hover {
	cursor: pointer;
}

</style>

<style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>
<div>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="title" ><bean:write name="title"/></logic:present></span>
		</p>


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

		
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Specialization Details
							</h4></a>
							

							<div class="navbar-right" >
								
							 <span id="saveid" class="buttons">Save</span>
									
									<span id="back" class="buttons">Back</a></span>
									
							</div>
						
					</div>
					
					<!-- <center> -->
						
					
					<!-- </center> -->
					
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class=" clearfix">
							<div class="col-md-6" id="txtstyle"
								style="font-size: 11pt; color: #5d5d5d;">
								
								<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">School Name </label>
								<div class="col-xs-7">
							<select id="locationname" name="location" class="form-control">
							<option value="">-------------Select-----------</option>
							<option value="all">All</option>
								<logic:present name="locationList">
									<logic:iterate id="Location" name="locationList">
										<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
									</logic:iterate>
								</logic:present>
						
						</select>
						</div>
								<input type="hidden" name="schoolId" class="form-control" id="schoolId" value='<logic:present name="editlist"><bean:write name="editlist" property="locationId" /></logic:present>'></input>
							</div>
					
								
								
							<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Specialization Name</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="specialization" onkeypress="HideError()"
											placeholder="" value="<logic:present name="editlist" ><bean:write name="editlist" property="spec_Name"/></logic:present>"/>
											<input type="hidden" name="status" id="statusId" value="<logic:present name="editlist" ><bean:write name="editlist" property="spec_Id"/></logic:present>"/>
											<input type="hidden" name="updateClassCode" id="updateClassCode" value="<logic:present name="editlist" ><bean:write name="editlist" property="class_Id"/></logic:present>"/>
											<input type="hidden" name="hiddenStreamId" id="hiddenStreamId" value="<logic:present name="editlist" ><bean:write name="editlist" property="stream_Id"/></logic:present>"/>	
											<input type="hidden" name="hiddenspecname" id="hiddenspecname" value="<logic:present name="editlist" ><bean:write name="editlist" property="spec_Name"/></logic:present>"/>
									</div>
								</div>

							</div>
							<div class="col-md-6"  id="txtstyle"
								style="font-size: 11pt; color: #5d5d5d;">
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Select Stream</label>
									<div class="col-xs-7">
										<select class="form-control" name="stream"
											onChange="HideError()" id="streamId">
											<option value="">-------------Select-------------</option>
										</select>
									</div>
								</div>
						
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Select Class</label>
									<div class="col-xs-7">
									
					<select name="class" id="classId"
						class="form-control" onkeypress="HideError()">
						<option value=""></option>
					</select>
									</div>
								</div>
				

							</div>
						</div>
					</div>
				</div>
	</div>
	</div>
</body>

</html>
