<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
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
<script type="text/javascript" src="JS/backOffice/Election/voterMachine.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<style>
.form-group{
margin-bottom: 5px;}
.save:hover {
	cursor: pointer;
}
body{
padding-top: 0px;
}
fieldset { 
	width:512px;
    display: block;
   /*  margin-left: auto;
    margin-right: 2px; */
    margin-bottom: 5px;
    padding-bottom: 0.625em;
    padding-left: 7px;
    padding-right: 0px;
    border: 0.5px solid #ccc;
   
}
legend {
    display: inline-block;
    width: auto;
    padding: 0;
    margin-bottom: 0px;
    font-size: 16px;
    line-height: inherit;
    color: #333;
    border: 0;
   }
</style>
<style>
.slideTab{
	padding:20px;
	font-family: Roboto Medium;
    font-size: 14px;
    font-weight: lighter;
}
.buttons{

vertical-align:-5px;

}
.navbar-right {
    top: -3px;
}
.panel-primary>.panel-heading{
margin-bottom: 0px;
}
form .panel.panel-primary.panel-list{
padding-bottom: 0px;

}    

@media (min-width:320px) and (max-width:767px){
br{
display: none;
}
}
.candidate{
cursor: pointer;
border: 3px solid transparent;
width: 150px;
margin: 0 auto;
}
.candidate label{
font-size: 14px;
line-height: 18px !important;
}
.candidate:hover{
border: 3px solid #ddd;
overflow: hidden;
}
div[id^='collapse']{
background-color:lightsteelblue;
}
</style>
</head>

<body>
<input type="hidden" id="helectionaccyear" value='<logic:present name="accyear" scope="request"><bean:write name="accyear"  /></logic:present>' />
 <input type="hidden" id="helectiongroup" value='<logic:present name="group" scope="request"><bean:write name="group"  /></logic:present>' />
 <input type="hidden" id="helectiontitleID" value='<logic:present name="titleID" scope="request"><bean:write name="titleID"  /></logic:present>' />

<span id="LocalIp" style="display:none"><logic:present name="IP" scope="session"><bean:write name="IP"  /></logic:present></span>
	<div class="" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p><img src="images/addstu.png" /><span id="pageHeading">Category</span></p>
		<div id="admissionDialog" style="display: none">
			<div id="admissionclose" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				</div>
				<br/>
			</div>
		</div>
	<div id="successmessages" class="successmessagediv" style="display: none;">
				<div class="message-item">
					 <a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"><logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present></span></a>
				</div>
			</div>
	
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><logic:present name='errorMessage'  scope='request' ><bean:write name='errorMessage'  /></logic:present></span></a>
				</div>
			</div>
			
			
		<logic:present name="CollectionVo" scope="request">
			<logic:iterate id="CollectionVo" name="CollectionVo" scope="request">
				
				
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					
					
					
					<div class="panel-heading" role="tab" id="heading<bean:write name="CollectionVo" property="electionCategoryId" />">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapse<bean:write name="CollectionVo" property="electionCategoryId" />" style="color: #767676; vertical-align: 7px;"> 
							<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;<bean:write name="CollectionVo" property="electionCategory" />
							</h4></a>
						
				</div>

					<div id="collapse<bean:write name="CollectionVo" property="electionCategoryId" />" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="heading<bean:write name="CollectionVo" property="electionCategoryId" />">
						<div class="panel-body own-panel">
					<div class="row">
					
					<logic:iterate id="candidateList" name="CollectionVo" property="candidateList">	
						<div class="col-md-2">
						<div class="candidate">
							<div style="width:150px; height:150px; background-color:blue;margin:0 auto;">
							<img src='./<bean:write name="candidateList" property="image" />' width="100%" height="100%" />
							</div>
							<div  style="width:150px; height:75px; background-color:yellow;text-align: center;margin: 0 auto;">
								<input type="hidden" class="hadmissionNo" value='<bean:write name="candidateList" property="admissionNo" />' />
								<div class="name">
									<label><bean:write name="candidateList" property="studentnamelabel" /></label>
								</div>	
								<div class="className">
									<label><bean:write name="candidateList" property="classname" />-<bean:write name="candidateList" property="sectionnaem" /></label>
									
								</div>
								
							</div>
							</div>
						</div>
					</logic:iterate>
					</div>	
					</div>
					</div>
			</div>
		</div>
		</logic:iterate>
	</logic:present>
		
		
	</div>
		
</html>


	
