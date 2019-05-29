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
<script type="text/javascript" src="JS/backOffice/Fee/NewFeeCollectionView.js"></script>
<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>

<link href="JQUERY/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="JQUERY/css/jquery.ui.dialog.css" rel="stylesheet" type="text/css"/>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


</head>


<style>
.paiddate,.receipt{
margin-left: 20%;
}
.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons{
width:500px !important;
top:50px  !important;
bottom: 0;
margin: auto;
}
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
.allstudent tr th:nth-child(1),.allstudent tbody tr td:nth-child(1){
width: auto !important;
}
.allstudent tbody tr:nth-child(even){
background-color: #fff !important;
}
.heading{
cursor: pointer;
}
#myDialog #allstudent  tr td,#myDialog #allstudent tr th {
text-align: center !important;
}
.ui-dialog-titlebar{
color:#fff;
}
span.Paid{
background-color: rgba(0, 158, 0, 0.66);
padding:0px 2px;
color:#fff;
}
span.Not.Paid{
background-color:#f00;
color:#fff;
padding:0px 2px;
}
.print{
margin-left: 5px;
}
#myDialog{
display: none;
}
#paymentMode{
width:100%;
}
hr{
margin-top: 10px;
margin-bottom:10px;
}
.paymentType{
padding: 2px 0px;
}
#Dialog h3{
	margin: 0px;
	
}
#Dialog{
font-size: 12px;
}
</style>

<body>
<div class="mainDialog"><div id='Dialog'></div></div>
   <div id="myDialog">
   <div class="paymentMode clearfix">
	<div class="col-md-6">
	<div class="paymentMode paymentType">
		<select id="paymentMode">
			<option value="">Payment Mode</option>
			<option value="cash">Cash</option>
			<option value="DD">DD</option>
		</select>
	</div>
	<div class="dd_cheque_bank paymentType">
	<input type='text' id='dd_cheque_bank' class='dd_cheque_bank_input' name='dd_cheque_bank' value=''  style='display:none;background-color:rgba(255, 224, 0, 0.22);' placeholder='Enter Bank Name' />
	</div>
	</div>
	
	<div class="col-md-6">
	  <div class="dd_cheque_no paymentType">
		<input type='text' id='paymentParticulars' class='paymentParticulars' name='paymentParticulars' value=''  style='display:none;background-color:rgba(255, 224, 0, 0.22);' placeholder='Enter Cheque Number' />
	  </div>
	  <div class="dd_cheque_date paymentType">
		<input type='text' id='dd_cheque_date' class='dd_cheque_date_input' name='dd_cheque_date' value=''  style='display:none;background-color:rgba(255, 224, 0, 0.22);' placeholder='Enter Cheque/DD Date' readonly="readonly" />
	  </div>
	</div>
	</div>
	<div class="myDailogTable"></div>
	
</div>
	<div class="col-md-10 col-md-offset-2" id="div1">
		<div id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span><span id="pageHeading">Academic Fee Details</span>
			</p>
		</div>

		
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
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title" style="color: #767676;vertical-align: text-top;">
					<span class="glyphicon glyphicon-menu-hamburger"></span> ACADEMIC FEE DETAILS
				</h3>
				
				<div class="navbar-right">
				
					 <span id="back" class="buttons">Back</span>
				</div>
				
				
			
			</div>
		
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 10pt; font-weight: 700; color: #5d5d5d;">
					<div
						style="padding: 7px 170px; max-height: 500px; overflow: scroll;">
						<table class="table" id="allstudent" cellpadding="5" cellspacing="0" border="1" width="100%">
							<tr>
								<th class="schoolname" colspan="2" style="font-size: 17px; font-family: Roboto Regular !important; background-color: #f5f5f5; text-align: center;">
							<logic:present name="schoolName"><bean:write name="schoolName" /></logic:present>
								</th>
							</tr>
							<tr>
								<th colspan="2" style="font-size: 17px; font-family: Roboto Regular !important; background-color: #f5f5f5; text-align: center;">ACADEMIC FEE FOR THE YEAR OF  <b><logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="accYearname"/></logic:present></b> 
								</th>
							</tr>

							<tr>
								<td colspan="1" style="font-size: 10pt; font-weight: 700; text-align: right;width:50%;">Student Name</td>
								<td colspan="1" style="font-size: 10pt; font-weight: 700; text-align: left;">
									<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="studentname"/></logic:present>
								</td>
							</tr>
							
							<tr>
								<td colspan="1"
									style="font-size: 10pt; font-weight: 700; text-align: right;width:50%;">Class-Section Name</td>
								<td colspan="1"
									style="font-size: 10pt; font-weight: 700; text-align: left;">
								<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="classname"/></logic:present>-<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="sectionname"/></logic:present>

								</td>
							</tr>						


						</table>
						<br />
			
			<input type="hidden" id="hstudentid" value='<logic:present name="FeeCollectionVo" scope="request" ><bean:write name="FeeCollectionVo" property="studentid" /></logic:present>' />
			<input type="hidden" id="haddmissionno" value='<logic:present name="FeeCollectionVo" scope="request" ><bean:write name="FeeCollectionVo" property="addmissionno" /></logic:present>' />
			<input type="hidden" id="haccYear" value='<logic:present name="FeeCollectionVo" scope="request" ><bean:write name="FeeCollectionVo" property="accYear" /></logic:present>' />
			<input type="hidden" id="hclassId" value='<logic:present name="FeeCollectionVo" scope="request" ><bean:write name="FeeCollectionVo" property="classId" /></logic:present>' />
			<input type="hidden" id="hspecialization" value='<logic:present name="FeeCollectionVo" scope="request" ><bean:write name="FeeCollectionVo" property="specialization" /></logic:present>' />			
			<input type="hidden" id="hstuName" value='<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="studentname"/></logic:present>' />
			<input type="hidden" id="hclassname" value='<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="classname"/>-<bean:write name="FeeCollectionVo" property="sectionname"/></logic:present>' />
			<logic:present name="FeeCollectionVo" scope="request">
					
					
			
					<!-- Term Name -->
					
						<table class="table allstudent" cellpadding="5" cellspacing="0" border="1" width="100%" >
							<tr class="heading" >
								<th  colspan="4" style="font-size: 17px; text-align: left; background-color: #f5f5f5; font-family: Roboto Regular !important;"><span
									class="glyphicon glyphicon-menu-hamburger"
									style="vertical-align: -5px; margin-right: 5px; padding-left: 5px;"></span>Payable Fees</th>
							</tr>
							<tr class="collapsable">
								<th  style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Select</th>
								<th  style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Term Name</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Term Fee Amount</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Payment Status</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;"></th>
							</tr>
						<logic:iterate id="FeeCollection" name="FeeCollectionVo" property="feeNamelist" > 	
								<tr class="collapsable">
								<td style="font-size: 10pt; font-weight: 700; text-align: center;" ><input type="checkbox" class="selectmonth <bean:write name="FeeCollection" property="status" />" value="<bean:write name="FeeCollection" property="termId" />,<bean:write name="FeeCollection" property="monthName" />,<bean:write name="FeeCollection" property="actualAmt" />,<bean:write name="FeeCollection" property="recieptNo" />"  /></td>
								<td class="heading" style="font-size: 10pt; font-weight: 700; text-align: center;" id="<bean:write name="FeeCollection" property="termId" />"><bean:write name="FeeCollection" property="term" /></td>
								<td style="font-size: 10pt; font-weight: 700; text-align: center;"><bean:write name="FeeCollection" property="actualAmt" /></td>
								<td style="font-size: 10pt; font-weight: 700; text-align: center; " id='status'><span class='<bean:write name="FeeCollection" property="status" />'><bean:write name="FeeCollection" property="status" /></span></td>
								<td id='<bean:write name="FeeCollection" property="fineAmount" />,<bean:write name="FeeCollection" property="advanceCarry" />,<bean:write name="FeeCollection" property="dueCarry" />,<bean:write name="FeeCollection" property="concessionAmt" />'><input type="button" name="<bean:write name="FeeCollection" property="status" />" id="<bean:write name="FeeCollection" property="termId" />" class="pay buttons" maxlength="50" value="Pay Now" style="padding: 1px 5px;border: none;" /><input type="button" id="<bean:write name="FeeCollection" property="paidDate" />" name="<bean:write name="FeeCollection" property="paidAmt" />" class="buttons view" value="View" style="display:none;padding: 1px 5px;border: none;"/><input type="button" id="<bean:write name="FeeCollection" property="paidDate" />" name="<bean:write name="FeeCollection" property="recieptNo" />,<bean:write name="FeeCollection" property="paidAmt" />,<bean:write name="FeeCollection" property="paymode" />,<bean:write name="FeeCollection" property="ddno" />,<bean:write name="FeeCollection" property="dddate" />" class="buttons print" value="Print" style="display:none;padding: 1px 5px;border: none;"/></td>
								</tr>
						</logic:iterate>
							

						</table>
					
				</logic:present> 
			
					</div>
				</div>
			
			</div>
		</div>
	</div>
	<textarea id="printing-css" style="display:none;">
		#Dialog{
		width:400px;
		}
.mainDialog{padding:0px;margin:0px;}
body {-webkit-print-color-adjust: exact;margin:0 auto;padding:0; width:420px !important;}
	</textarea>
	
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script>
</body>
</html>



	