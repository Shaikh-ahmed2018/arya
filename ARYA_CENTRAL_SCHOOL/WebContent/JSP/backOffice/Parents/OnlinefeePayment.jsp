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
<script type="text/javascript" src="JS/backOffice/Parents/OnlineFeePayment.js"></script>
<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>

<link href="JQUERY/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="JQUERY/css/jquery.ui.dialog.css" rel="stylesheet" type="text/css">
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">


<title>Fee Payment</title>
<style>
div.dueAmount{
margin: 0 auto;
font-size: 20px;
}
#onlinefeepayment{
text-align: center;
margin-top: 10px;
font-size: 18px;

}
.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons {
    width: 500px !important;
}
#payment{
background-color: #07AAB9;
color: #fff;
border: 1px solid #07AAB9;
font-weight: 600;
 padding: 10px;
 border-radius: 4px
}
label[for="dueAmount"]{
color:#f00;
}
.alignment{
text-align:right;
}
#onlinefeepayment .form-group{
    margin-bottom: 5px;
    text-align: left;
    padding-left: 20px
}
.grandTotal{
padding: 0 5px;
font-weight: 600;
}
h3{
font-size: 20px !important;
}
input[type=checkbox]{
font-size: 20px !important;
}
</style>
</head>
<body>
<div id="myDialog" class="ui-dialog-content ui-widget-content" style="display: block;">
   <div class="paymentMode clearfix">
	<div class="col-md-6">
	<div class="paymentMode paymentType">
		<select id="paymentMode">
		<option value="">Select Bank</option>
			<logic:present name="bankdetails" scope="request">
				<logic:iterate id="bankdetails" name="bankdetails" scope="request">
				<option value='<bean:write name="bankdetails" property="bank_url"/>'><bean:write name="bankdetails" property="bank_name"/></option>
				</logic:iterate>
			</logic:present>
		</select>
	</div>
	
	</div>
	</div>
	<div class="myDailogTable"></div>
	<div class="totalAmt"><div class="row"><div class="col-xs-6"><span>GRAND TOTAL </span></div><div class="col-xs-6" style="text-align:right;"><span class="grandTotal"></span></div></div></div>
</div>
<div class="col-md-10 col-md-offset-2">
 <div id="onlinefeepayment">
 <logic:present name="OnlineFeePayment" scope="request">
		<logic:iterate id="OnlineFeePayment" name="OnlineFeePayment" > 
			<div class="row">
				<div class="col-md-11 col-sm-11">
				<div class="panel panel-default"> 
				
						<h4>&nbsp;&nbsp;&nbsp;&nbsp;STUDENT DETAILS</h4>
							<div class="panel-body stu">
							
							<div class="row">
								<div class="col-md-6">
								  <div class="form-group">
									<label for="StudentName">Student Name:</label><span class="studentName"> <bean:write name="OnlineFeePayment" property="studentName"/> </span>
								  </div>
								  <div class="form-group">
									<label for="classDetail">Class Name:</label><span class="classDetail"><bean:write name="OnlineFeePayment" property="classDetail"/></span>
								  </div>
								  <div class="form-group">
									<label for="StudentId">Admission No:</label><span class="studentId"><bean:write name="OnlineFeePayment" property="admissionNo"/></span>
								  </div>
								  <div class="form-group">
									<label for="StudentId">Stage:</label><span class="studentId"><bean:write name="OnlineFeePayment" property="stragename"/></span>
								  </div>
								  <div class="form-group">
									<label for="StudentId">Route:</label><span class="studentId"><bean:write name="OnlineFeePayment" property="routename"/></span>
								  </div>
								</div>
								<div class="col-md-6">
									<img src='<bean:write name="OnlineFeePayment" property="imgurl"/>' style="width:150px;height:150px"/>
								</div>
							</div>
								
							<div class="row">
								<div class="col-sm-6">
									<h3 style="color:#0f023a;">SCHOOL FEE</h3>		
								
										<table class="classfeetable table">
											<thead>
											<tr><th style="text-align: center;font-size: 7px;" >(Select All) <input type="checkbox" class="selectAll" /></th>
												<th>Term Name</th>
												<th>Amount</th>
											</tr>
											</thead>
											<tbody>
												<logic:iterate id="classFee" name="OnlineFeePayment" property="termlist" > 
												<tr><td><input type="checkbox" class="select" value='<bean:write name="classFee" property="termid"/>,<bean:write name="classFee" property="classId"/>,<bean:write name="classFee" property="spec"/>,<bean:write name="classFee" property="feeAmt"/>,<bean:write name="classFee" property="fineAmt"/>,<bean:write name="classFee" property="dueFee"/>,<bean:write name="classFee" property="feeCode"/>' /></td>
												<td><bean:write name="classFee" property="term"/></td>
												<td><bean:write name="classFee" property="dueFee"/></td>
												</tr>
											</logic:iterate>
											</tbody>
										</table>
									
								</div>
								<div class="col-sm-6">
								<h3>TRANSPORT FEE</h3>
								<table  class="transortfeetable table">
											<thead>
											<tr><th style="text-align: center;font-size: 7px;" >(Select All)<input type="checkbox" class="tselectAll" /></th>
												<th>Term Name</th>
												<th>Amount</th>
											</tr>
											</thead>
											<tbody>
												<logic:iterate id="transortFee" name="OnlineFeePayment" property="transporttermlist"> 
												<tr><td><input type="checkbox" class="tselect" value='<bean:write name="transortFee" property="termid"/>,<bean:write name="transortFee" property="classId"/>,<bean:write name="transortFee" property="spec"/>,<bean:write name="transortFee" property="feeAmt"/>,<bean:write name="transortFee" property="fineAmt"/>,<bean:write name="transortFee" property="dueFee"/>,<bean:write name="transortFee" property="feeCode"/>' /></td>
												<td><bean:write name="transortFee" property="term"/></td>
												<td><bean:write name="transortFee" property="dueFee"/></td>
												</tr>
											</logic:iterate>
											</tbody>
										</table>
								</div>
								
								</div>	
								<div class="form-group clearfix">
											<span class="payment buttons">Click Here For Payment</span> 		
											<input type="hidden" class="stuId" value='<bean:write name="OnlineFeePayment" property="studentId"/>' />
											
							</div>	
								
							
							</div>
					
				</div>
				</div>
			</div>
			
			
	</logic:iterate>
	</logic:present> 
</div> 


</div>
</body>
</html>