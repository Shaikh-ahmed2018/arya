<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>eCampus Pro</title>
<script type="text/javascript" src="JQUERY/jquery-1.9.1.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JS/backOffice/Fee/ClassFeeSetup.js"></script>
<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>


<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


</head>


<style>
.glyphicon:hover{

cursor: pointer;
}
.navbar-right span{
margin-right: 3px;
}
.allstudent th{
font-weight: 300;
}
input[readonly="readonly"]{
background-color: #ccc;
}
input[readonly]{
background-color: #ccc;
} 
.allstudent tbody tr:nth-child(odd){
background-color: #f2fafc !important;
}
.allstudent tbody tr:nth-child(even){
background-color: #fff !important;
}
.heading{
cursor: pointer;
}
</style>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span><span id="pageHeading">Class Fee SetUp</span>
			</p>
		</div>

		<center>
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips"></span></a>
				</div>
			</div>
		

		
			<div class="successmessagediv" style="display: none;">
				<div class="message-item">
					<a href="#" class="msg-success bg-msg-succes"><span class="sucessmessage"></span></a>
				</div>
			</div>
		</center>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span> CLASS FEE SETUP DETAILS
					</h3></a>
				
				<div class="navbar-right">
					 <span class="buttons" id="save">Save </span>
					 <span id="back" class="buttons">Back</a></span>
				</div>
				
				
				<script>
					$(document).ready(function() {
						$('[data-toggle="tooltip"]').tooltip();
					});
				</script>
			</div>
		
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 10pt; font-weight: 700; color: #5d5d5d;">
					<div
						style="padding: 7px 170px; max-height: 500px; overflow: scroll;">
						<table class="table" id="allstudent" cellpadding="5"
							cellspacing="0" border="1" width="100%">
							<tr>
								<th colspan="2"
									style="font-size: 17px; font-family: Roboto Regular !important; background-color: #f5f5f5; text-align: center;">Arya
									Central School</th>
							</tr>
							<tr>
								<th colspan="2"
									style="font-size: 17px; font-family: Roboto Regular !important; background-color: #f5f5f5; text-align: center;">CLASS FEE SETUP FOR THE YEAR OF <b><logic:present name="acadamicYearVal"><bean:write name="acadamicYearVal" /></logic:present></b>
								</th>
							</tr>


							<tr>
								<td colspan="1"
									style="font-size: 10pt; font-weight: 700; text-align: right;width:50%;">Class Name</td>
								<td colspan="1"
									style="font-size: 10pt; font-weight: 700; text-align: left;">
								<logic:present name="classidVal"><bean:write name="classidVal" /></logic:present>

								</td>
							</tr>


							


						</table>
						<br />
			<input type="hidden" id="haccYear" value='<logic:present name="accYear" scope="request" ><bean:write name="accYear" /></logic:present>' />
			<input type="hidden" id="hclassId" value='<logic:present name="classId" scope="request" ><bean:write name="classId" /></logic:present>' />
			<input type="hidden" id="hlocationId" value='<logic:present name="locationId" scope="request" ><bean:write name="locationId" /></logic:present>' />
			
			
			<logic:present name="getActiveFeeMasterSetupDetails" scope="request">
				<logic:iterate id="getActiveFee" name="getActiveFeeMasterSetupDetails" scope="request">

						<table class="table classfeesetup" cellpadding="5" cellspacing="0" border="1" width="100%">
							<tr class="heading">
								<th colspan="3"
									style="font-size: 17px; text-align: left; background-color: #f5f5f5; font-family: Roboto Regular !important;"><span
									class="glyphicon glyphicon-menu-hamburger"
									style="vertical-align: -5px; margin-right: 5px; padding-left: 5px;"></span><bean:write name="getActiveFee" property="termname"  /> Fees</th>
							</tr>
							<tr>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;"><input type="checkbox" class="selectAll" /></th>
								<th style="font-size: 14px; text-align: left; background: #f9f9f9 !important; font-family: Roboto Medium;">Fee Description</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Fee Amount</th>
							</tr>
							<logic:iterate id="getFee" name="getActiveFee" property="feeNamelist">
							<tr class="collapsable">
								<td style="font-size: 10pt; font-weight: 700; text-align: center;">
									<input type="checkbox" class="select <bean:write name="getFee" property="status" /> <bean:write name="getFee" property="feesettingcode" />" value='<bean:write name="getFee" property="feecode" />,<bean:write name="getActiveFee" property="termid"  />' />
								</td>
								<td style="font-size: 10pt; font-weight: 700; text-align: left;"><bean:write name="getFee" property="feename" /></td>
								<td style="font-size: 10pt; font-weight: 700; text-align: center; width: 40%;">
									<input type="text" name="classFeeAmount" class="classFeeAmount" maxlength="50" value="<bean:write name="getFee" property="feeAmount" />" style="width: 54%; text-align: right;"  readonly='readonly'/>
								</td>
							</tr>
							</logic:iterate>
							<tr>
								<th style="font-size: 14px; background: #f9f9f9 !important;"></th>
								<th style="font-size: 14px; background: #f9f9f9 !important; font-family: Roboto Medium; text-align: right;"><bean:write name="getActiveFee" property="termname"  /> Fees Total</th>
								<th style="font-size: 14px; background: #f9f9f9 !important; font-family: Roboto Medium; text-align:center;">
									<input type="text" name="totalFeeAmount" class="totalFeeAmount" maxlength="50" value="" style="width: 54%; text-align: right;background-color: rgba(255, 224, 0, 0.22);" readonly="readonly" />
								</th>
							</tr>

						</table>
					
					</logic:iterate>
			</logic:present>
						
						
						
						
	
					


					</div>
				</div>
			
			</div>
		</div>
	</div>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script>
</body>
</html>



	