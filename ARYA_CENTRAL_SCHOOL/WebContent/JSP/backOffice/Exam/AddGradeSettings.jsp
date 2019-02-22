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

<script type="text/javascript" src="JS/backOffice/Settings/addGradeSettings.js"></script>
<script type="text/javascript">
	
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
	
	function CheckIsNumeric(objEvt) {
        var charCode = (objEvt.which) ? objEvt.which : event.keyCode
        if (charCode > 31 && charCode != 46 && charCode != 45 &&(charCode < 48 || charCode > 57)) {
            /* document.getElementById("maxmarks").style.backgroundColor = "#FFB2B2"; */
            return false;
        }
        else {
            /* document.getElementById("maxmarks").style.backgroundColor = "#B2D8B2"; */
            return true;
        }
    }
	
	function isNumberKey(evt)
	{
	    var charCode = (evt.which) ? evt.which : event.keyCode;
	    if (charCode != 43 && charCode > 31 && charCode != 32 && charCode != 45 && (charCode < 48 || charCode > 57) && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122))
	        return false;
	    else
	    return true;
	} 
	
</script>
<style>
.glyphicon:hover{

cursor: pointer;
}
#classSave:hover {
	cursor: pointer;
}
.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons{
width : 500px !important;
}


</style>

<style>
.buttons{
vertical-align: 0px;
}
.form-group{
margin-bottom: 10px;
}

.marks{
	max-width: 100px;
}
.gradename{
	max-width: 150px;
}
#allstudent {
	width : 100%;
}
#allstudent td{
	text-align: center;
}

/* .aligning{
	margin-right: -25px;
} */
#allstudent th:nth-child(1){
width : 150px;

.glyphicon-edit{
top:5px;
}
#allstudent{
color : #333;
}

</style>
</head>

<body>

	<div id="mydialog1" style="display:none;">
	
		<div class="col-md-12" id="txtstyle"
								style="font-size: 11pt; color: #5d5d5d;">
		<div class="form-group clearfix">
			<label for="inputEmail" class="control-label col-xs-4"
			style="text-align: left; line-height: 35px;">Grade Name</label>
		<div class="col-xs-8">
			<input type = "text" name="gradeName1" class="form-control gradename" id="gradeName1" onkeypress ="return isNumberKey(event);" value="" />
		</div>
		</div>
			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
				style="text-align: left; line-height: 35px;">Comments</label>
				<div class="col-xs-8">
					<input type = "text" class="form-control" id="comments1" onkeypress ="return isNumberKey(event);"/>
				</div>
			</div>
			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
				style="text-align: left; line-height: 35px;">Min Marks</label>
				<div class="col-xs-8">
					<input type = "text" class="form-control marks" id="minmarks1" onkeypress="return CheckIsNumeric(event);"/>
				</div>
			</div>

			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
				style="text-align: left; line-height: 35px;">Max Marks</label>
				<div class="col-xs-8">
					<input type = "text" class="form-control marks" id = "maxmarks1" onkeypress="return CheckIsNumeric(event);"/>
				</div>
			</div>
		</div>
	
	</div>
	<div id="mydialog" style="display:none; max-width: 200px;">
	<p>Are You Sure to Delete?</p>
	</div>
	<div id="dialog" style="display:none;">
			
	<div class="col-md-12" id="txtstyle" style="font-size: 11pt; color: #5d5d5d;">
								
			<!-- <div class=" form-group errormessagediv1" align="center" style="display: none;">
				<div class="message-item">
					Warning
					<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips1"></span></a>
				</div>
			</div> -->
		<div class="form-group clearfix">
			<label for="inputEmail" class="control-label col-xs-4"
			style="text-align: left; line-height: 35px;">Grade Name</label>
		<div class="col-xs-8">
			<input type = "text" class="form-control gradename" id="gradeName" onkeypress ="return isNumberKey(event);"/>
		</div>
		</div>
			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
				style="text-align: left; line-height: 35px;">Comments</label>
				<div class="col-xs-8">
					<input type = "text" class="form-control" id="comments" onkeypress ="return isNumberKey(event);"/>
				</div>
			</div>
			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
				style="text-align: left; line-height: 35px;">Min Marks</label>
				<div class="col-xs-8">
					<input type = "text" class="form-control marks" id="minmarks" onkeypress="return CheckIsNumeric(event);"/>
				</div>
			</div>

			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
				style="text-align: left; line-height: 35px;">Max Marks</label>
				<div class="col-xs-8">
					<input type = "text" class="form-control marks" id = "maxmarks" onkeypress="return CheckIsNumeric(event);"/>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Grade Setting</span>
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

	
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a href="#" style="color: #767676">
							<h4 class="panel-title grade"><i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Grade Details
							</h4></a>

							<div class="navbar-right" >
									<span class="buttons" id="addgrade" style="line-height: 40px;top:-3px;">Add</span>
									<span id="back" class="buttons">Back</a></span>
							</div>
					</div>
					
					<!-- <center> -->
						
					
					<!-- </center> -->
					
					<div id="gradeOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"  id="txtstyle"
								style="font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix" id="inputcolor">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Academic Year</label>
								<div class="col-xs-5">
								<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear"
											class="form-control" value='<logic:present name="accyName" scope="request"><bean:write name="accyName" ></bean:write></logic:present>' />
								</div>
								<input type="hidden" name="hiddenaccyid" id="hiddenaccyid" value='<logic:present name="accyId" scope="request"><bean:write name="accyId"></bean:write></logic:present>'/>
								<br />
							</div>
							<div class="form-group clearfix" id="inputcolor">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">School Name</label>
								<div class="col-xs-5">
								<input type="text" readonly="readonly" name="location" onkeypress="HideError()" id="location"
											class="form-control" value='<logic:present name="locName" scope="request"><bean:write name="locName" ></bean:write></logic:present>' />
								</div>
								<input type="hidden" name="hiddenloc" id="hiddenloc" value='<logic:present name="locId" scope="request"><bean:write name="locId"></bean:write></logic:present>'/>
								<br />
							</div>
						</div>
						<div class="form-group">
							<div id="markstable">
							</div>
						</div>
				</div>
				</div>
			
		
	</div>
	</div>
</body>

</html>
