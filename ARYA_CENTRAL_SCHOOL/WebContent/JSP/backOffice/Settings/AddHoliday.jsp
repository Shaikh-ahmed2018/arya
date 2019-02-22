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

<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.menu.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>	

<script type="text/javascript" src="JS/backOffice/Settings/addHoliday.js"></script>
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
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Add New Holiday</span>
		</p>


                  <logic:present name="successmessagediv" scope="request">
						<div class="successmessagediv" align="center">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes" ><span class="validateTips"><bean:write
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

		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a href="#" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Holiday Details
							</h4></a>
							

							<div class="navbar-right" >
								
							 <span id="saveid" class="buttons">Save</span>
									
									<span id="back"  class="buttons">Back</span>
									
							</div>
						
					</div>
					
					<!-- <center> -->
						
					
					<!-- </center> -->
					
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
						
							<%-- <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Admission
										Date </label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" name="dateofJoin"
											onkeypress="HideError()" id="dateofJoinId"
											class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="dateofJoin"></bean:write></logic:present>' />
									</div>
								</div>
							
							 --%>
						
						
							<div class="col-md-6" id="txtstyle"
								style="font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Location</label>
									<div class="col-xs-7">
										
					
						<select id="locationname" name="location" class="form-control">
							<option value="">-------------Select-----------</option>
							<option value="%%">All</option>
								<logic:present name="locationList">
								
									<logic:iterate id="Location" name="locationList">
									
									<option value="<bean:write name="Location" property="locationId"/>">
										<bean:write name="Location" property="locationName" />
									</option>
									
									</logic:iterate>
								
								
								</logic:present>
						
						</select>
									</div>
								</div>
								<br />
								
							
							</div>
							<div class="col-md-6"  id="txtstyle"
								style="font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
									
					<select name="accyYear" id="accyYear"
						class="form-control" onkeypress="HideError()">
							<option value="">-------------Select-----------</option>
							<logic:present name ="AccYearList">
								<logic:iterate id="AccYear" name="AccYearList">
									<option value="<bean:write name="AccYear" property="accyearId"/>">
										<bean:write name="AccYear" property="accyearname" />
									</option>
								</logic:iterate>
							</logic:present>
					</select>
									</div>
								</div>
								<br />

							</div>
							
							<div class="form-group">
					<table style="background: #fff;" class="table" id="addholiday">
					<tr>
						<th style="font-size: 13px; text-align: center;" >Date</th>
						<th style="font-size: 13px; text-align: center;">Weekday</th>
						<th style="font-size: 13px; text-align: center; ">Holiday Name</th>
						<th style="font-size:13px; text-align: center;">Holiday Type
						
						</th>
						<th align="center" style="text-align:center;"><span style="cursor: pointer;"
							class="glyphicon glyphicon-plus" onclick="addMoreHoliday(this.form);"></span></th>
					</tr>
					
				</table>
			</div>
							
							
							
						</div>
					</div>
				</div>




			
		</form>
	</div>
	</div>
</body>

</html>
