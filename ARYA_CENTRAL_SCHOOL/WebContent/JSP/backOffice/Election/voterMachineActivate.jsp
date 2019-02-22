<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html>
<head>
<title>Election</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="CSS/Admin/StudentNew.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>

<link href="JQUERY/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="JQUERY/css/jquery.ui.dialog.css" rel="stylesheet" type="text/css"/>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/backOffice/Election/voterMachineActivate.js"></script>
<style type="text/css">
body{
margin: 0 auto;
padding-top: 0px;
background-color:#07aab9;
}
header{
padding: 15px;
background-color: #f5f5f5;
}
#fullScreen{
margin: auto;
}
.image{
width: 300px;
margin: auto auto; 
}
.boothName{
text-align: left;
}
.staffInCharge{
text-align: right;
}
.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all{
overflow: scroll;
max-height: 300px;
}
#startActivation,#StudentVerificatio{
    padding: 10px;
    box-shadow: 4px 3px 5px #B5B0B0;
    border: 1px solid #B5B0B0;
    margin-top: 2px;
}
#StudentVerificatio label{
color: #fff;
}
span.labelName{
font-size: 18px;
}
span.valueName{
font-size: 16px;
}
.info{
width: 80%;
margin: 0 auto;
}
#startActivation{
cursor: pointer;
}
.errormessagedivRequest,.successmessagedivRequest{
    width: 400px;
    height: 400px;
    background: #fff;
    position: absolute;
    left: 0;
    right: 0;
    margin: auto;
    bottom: 0;
    top: 0;
    z-index: 999;
    box-shadow:5px 7px 9px rgba(225, 226, 207, 0.56);
    text-align: center;
    padding: 100px 20px;
    border-radius:12px;
}
.message-item{
padding: 20px 0px;
}
.message-item .validateTips{
    font-size: 16px;
    font-weight: 600;
}
</style>

</head>
<body>
<input type="hidden" id="LocalIp" value='<logic:present name="IP" scope="session"><bean:write name="IP"  /></logic:present>' />
<input type="hidden" id="academicYear" value='<logic:present name="current_academicYear" scope="session"><bean:write name="current_academicYear"  /></logic:present>' />
<input type="hidden" id="accyear" value='<logic:present name="accyear" scope="request"><bean:write name="accyear"  /></logic:present>' />
<input type="hidden" id="group" value='<logic:present name="group" scope="request"><bean:write name="group"  /></logic:present>' />
<input type="hidden" id="titleID" value='<logic:present name="titleID" scope="request"><bean:write name="titleID"  /></logic:present>' />
<header>
	<div class="container">
	<logic:present name="boothDetails" scope="request">
 	 <logic:iterate id="boothDetails" name="boothDetails" scope="request">
		<div class="row">
			<div class="col-md-4">
				<div id='<bean:write name="boothDetails" property="boothNameId" />' class="boothName">
					<span class="labelName">Booth Name : </span><span class="valueName"><bean:write name="boothDetails" property="boothName" /></span>
				</div>
			</div>
			<div class="col-md-4">
				<div id="systemIp">
				<span class="labelName">Central System IP : </span><span class="valueName"><bean:write name="boothDetails" property="centralSystemIp" /></span>
				</div>
			</div>
			<div class="col-md-4">
				<div id='<bean:write name="boothDetails" property="staffIncharge" />' class="staffInCharge">
					<span class="labelName">Staff In Charge : </span><span class="valueName"><bean:write name="boothDetails" property="staffName" /></span>
				</div>
			</div>
		</div>
	
	 </logic:iterate>	
	</logic:present>
	</div>		
</header>
  
 <div class="container">
 		<div id="successmessages" class="successmessagedivRequest" style="display: none;">
				
				<img class="imgsrc" src="./images/pollingMachine.png" width="100" height="100" />
				<div class="message-item">
					 <a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"><logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present></span></a>
				</div>
			</div>
	
			<div class="errormessagedivRequest" style="display: none;">
				
					<img class="imgsrc" src="./images/polling.png" width="100" height="100" />
					<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><logic:present name='errorMessage'  scope='request' ><bean:write name='errorMessage'  /></logic:present></span></a>
				</div>
			</div>
 
	<div class="row">
		<div class="col-md-8">
		   <div id="StudentVerificatio">
			 <div class="row">	
				<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<div class="form-group clearfix ">
						<label for="inputEmail" class="control-label col-xs-5" style="text-align: right;">Admission No</label>
						<div class="col-xs-7">
							<input type="text" name="admissionNo" onkeypress="HideError()" id="admissionNo" class="form-control" />
						</div>
					</div>
							
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">Student Name</label>
						<div class="col-xs-7">
							<input type="text" name="studentName" id="studentName" class="form-control" value="" readonly="readonly" />
							<input type="hidden" name="hStudentId" id="hStudentId" class="form-control" value="" />
						</div>
					</div>
					
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">Class</label>
						<div class="col-xs-7">
							<input type="text" name="className"  id="className" class="form-control" readonly="readonly" />
							<input type="hidden" name="hclassId" id="hclassId" class="form-control" />
						</div>
					</div>
			
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">Division</label>
						<div class="col-xs-7">
							<input type="text" name="division" id="division"class="form-control" readonly="readonly" />
							<input type="hidden" name="hdivisionId" id="hdivisionId" class="form-control" />
						</div>
					</div>
				
				<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">House</label>
						<div class="col-xs-7">
							<input type="text" name="house" id="house"class="form-control" readonly="readonly" />
							<input type="hidden" name="hhouseId" id="hhouseId" class="form-control" />
						</div>
					</div>
				</div>


			<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
						<div class="col-xs-7">
							<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 150px;">
												<img id="imagePreview" class="setImage" alt="image" src="images/girl.png" style="height:100%; width:100%;">
							</div>
						</div>
			</div> 
		  </div>
		</div>
	</div>
	<div class="col-md-4">
	<div class="image" id="startActivation">
 			<img src="images/goImage.png" width="100%"/>
 			<div class="info">
				<span class="buttons"> Click to Activate Voter Machine
			</span>
			</div>
 		</div>
	</div>
</div>
	<div class="row">
	  <div class="col-md-8">
	  
		 <div class="pollingMachine">
		   
	 		<table id="pollingMachine" class="allstudent" style="width:100%;">
				<thead>
					<tr><th>Select<th>Polling Machine No.</th><th>Polling Machine Name</th><th>Polling Machine IP</th></tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>	
	</div>
	<div class="col-md-4">
	  <div class="categoryList">
			<table id="categoryList" class="allstudent" style="width:100%;">
				<thead>
					<tr><th>Sl. No.</th><th>Category Name</th></tr>
				</thead>
				<tbody>
				</tbody>
			</table>
	  </div>
	</div>
   </div>	 
 </div>
</body>
</html>