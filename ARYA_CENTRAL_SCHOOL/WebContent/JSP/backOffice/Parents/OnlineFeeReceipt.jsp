<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
#onlinefeeReceipt .form-group{
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

<div class="col-md-10 col-md-offset-2">
 <div id="onlinefeeReceipt">
 	<div class="heading" Style="text-align: center;"><h2><logic:present name="studentInfo"  scope="request"><bean:write name="studentInfo" property="schoolLocation"></bean:write></logic:present></h2></div>
		
			<div class="row">
				<div class="col-md-11 col-sm-11">
				<div class="panel panel-default"> 
				
						<h4>&nbsp;&nbsp;&nbsp;&nbsp;ONLINE PAYMENT FEE RECEIPT</h4>
							<div class="panel-body">
							
							<div class="row">
								
								<div class="col-md-6">
								  <div class="form-group">
									<label for="StudentName">Student Name:</label><span class="studentName"><logic:present name="studentInfo"  scope="request"><bean:write name="studentInfo" property="studentFirstName"></bean:write></logic:present> </span>
								  </div>
								  <div class="form-group">
									<label for="classDetail">Class Name:</label><span class="classDetail"><logic:present name="studentInfo"  scope="request"><bean:write name="studentInfo" property="classname"></bean:write></logic:present></span>
								  </div>
								  <div class="form-group">
									<label for="StudentId">Admission No:</label><span class="studentId"><logic:present name="studentInfo"  scope="request"><bean:write name="studentInfo" property="studentAdmissionNo"></bean:write></logic:present></span>
								  </div>
								  <div class="form-group">
									<label for="StudentId">Stage:</label><span class="studentId"><logic:present name="studentInfo"  scope="request"><bean:write name="studentInfo" property="transportlocationName"></bean:write></logic:present></span>
								  </div>
								  <div class="form-group">
									<label for="StudentId">Route:</label><span><logic:present name="studentInfo"  scope="request"><bean:write name="studentInfo" property="route"></bean:write></logic:present></span>
								  </div>
								</div>
								<div class="col-md-6">
									<img src='<logic:present name="studentInfo"  scope="request"><bean:write name="studentInfo" property="studentPhoto"></bean:write></logic:present>' style="width:150px;height:150px"/>
								</div>
								
							
						</div>
						<div class="row">
						<logic:present name="SchoolFee"  scope="request">
							<div class="col-sm-6">
							
								<logic:iterate id="terrep" name="SchoolFee" >
								<table class="table">
									<thead>
										<tr>
											<th colspan="3"><bean:write name="terrep" property="termname" /> Fee Description</th>
										</tr>
										<tr>
											<th>S.No</th><th>Fee Name</th><th>Fee Amount</th>
										</tr>
									</thead>
									<tbody>
									  <logic:iterate id="feedetail" name="terrep" property="feeNameVo">
										<tr>
											<td><bean:write name="feedetail" property="sno" /></td>
											<td><bean:write name="feedetail" property="feename" /></td>
											<td><bean:write name="feedetail" property="actualAmt" /></td>
										</tr>
									  </logic:iterate>
										<tr><td></td><td>Fine </td><td><bean:write name="terrep" property="fine" /></td></tr>
									</tbody>
								</table>
								</logic:iterate>
								
							</div>
							</logic:present>
							<logic:present name="TransportFee"  scope="request">
							<div class="col-sm-6">
								
								<logic:iterate id="transportterm" name="TransportFee" >
								<table class="table">
									<thead>
										<tr>
										<th colspan="3"><bean:write name="transportterm" property="termname" /> Fee Description</th>
										</tr>
										<tr><th>S.No</th><th>Fee Name</th><th>Fee Amount</th></tr>
									</thead>
									<tbody>
									 
										<tr>
											<td><bean:write name="transportterm" property="sno" /></td>
											<td><bean:write name="transportterm" property="stream" /></td>
											<td><bean:write name="transportterm" property="totalAmount" /></td>
										</tr>
									 
									
									</tbody>
								</table>
								</logic:iterate>
								
							
							</div>
							</logic:present>
							<div class="totalPaid" style="text-align: center"><span style="font-weight: 600">TOTAL PAID AMOUNT : </span><span style="font-weight: 600"> RS. <logic:present name="totalPaid" scope="request"><bean:write name="totalPaid" /></logic:present></span></div>
						</div>		
							
			</div>
					
		</div>
	</div>
</div>
	
			
	
</div> 

<div class="print"><span class="buttons" id="printreciept">Print</span></div>		
</div>
<textarea id="printing-css" style="display:none">
#onlinefeeReceipt .form-group{
    margin-bottom: 5px;
    text-align: left;
    padding-left: 20px
}
.col-md-6,.col-sm-6,.col-xs-6{
width:48%;
float:left;
}
</textarea>
</body>
</html>