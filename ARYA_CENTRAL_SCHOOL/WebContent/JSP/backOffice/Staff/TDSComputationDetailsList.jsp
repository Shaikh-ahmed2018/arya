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

<title>eCampus Pro</title>

<script type="text/javascript" src="JS/common.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="JS/backOffice/Staff/TDSComputationDetails.js"></script>
<style>
.download:hover {
	cursor: pointer;
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}
</style>
<style>
.buttons {
	vertical-align: 5px;
}

.navbar-right span {
	margin-right: 3px;
}
.buttons {
	vertical-align: 0px;
}


table th,table td {
	border: 1px solid #ddd !important;
	color: #767676;
	font-weight: 300;
	font-family: Open Sans Light;
}

table th:first-child,table td:first-child {
	width: 400px;
	line-height: 30px;
}

button,input,select,textarea {
	border: none;
}

#allstudent td:nth-child(1) {
	width: 413px;
}

#collapseOne label {
    line-height: 23px !important;
    margin-bottom: 0px;
}

#collapseOne #allstudent  tr td,#collapseOne .allstudent  tr td {
	padding: 0;
	margin: 0;
	padding-left: 5px;
}
#collapseOne .form-group{
	margin-bottom: 0px ! important;
}

#collapseOne input[type="text"],#collapseOne select {
    height: 23px !important;
    border-radius: 4px !important;
    padding: 0 5px !important;
    background: transparent;
}

.thheaderfont{
	font-size: 14px; 
	text-align: center; 
	background: #f9f9f9 !important; 
	font-family: Lucida Sans;
}

.tdfirstchild{
	font-size: 9pt; 
	font-weight: 700; 
	text-align: left !important;
	line-height: 0px;
}

.tdsecondchildon{
	width: 100%; 
	text-align: right; 
	background-color: rgba(255, 224, 0, 0.22);
	line-height: 0px;
}
.tdthirdchildon{
	width: 100%; 
	text-align: right;
	line-height: 0px; 
}

#allstudent  tr td, .allstudent  tr td, #allstudents  tr td{
	font-family: Lucida Sans;
	font-weight: normal;
	
}

</style>
</head>

<body>

	<div class="col-md-10" id="div1">
		<div  id="div2">
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">IT Declaration Details</span>
			</p>
		</div>

			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>

			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write
									name="successmessagediv" scope="request" /></span></a>
					</div>
				</div>
			</logic:present>
			
			<div class="successmessagediv" align="center" style="display: none;"> 
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>
			
		<input type="hidden" id="itdeclaration" value='<logic:present name="itdeclaraton" scope="request"><bean:write name="itdeclaraton" scope="request" /></logic:present>' />
		
		
		
		<div class="panel panel-primary">
		<form action="staffemployement.html?method=saveStaffIncomeTaxTDSDeductionDetails" id="staffform" method="post">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3 class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;IT Declaration Details
					</h3></a>
					
				<div class="navbar-right">
					<span class="buttons" id="submit" class="save">Save	Changes</span>
					<span class="buttons" id="add">Add </span>
				 	<!-- <span class="buttons" id="editTeacher" style="cursor: pointer">Edit </span>
					<span class="buttons" id="iconsimg" data-toggle="modal"	data-target="#myModal">Download </span> -->

				</div>
				
				<div id="hiddenItDecleartionForm">
				
				</div>
			</div>
		
		<div class="panel panel-primary" style="margin-top: -32px;">
			<!-- <div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger" style="vertical-align: -5px; margin-right: 5px; padding-left: 5px;"></span>IT Declaration Details
					</h3></a>
				<div class="navbar-right">
					<span class="buttons" id="submit" class="save">Save	</span>&nbsp; 
					<span class="buttons"><a href="adminMenu.html?method=TDSComputationDetails">Back</a></span>
				</div>
			</div> -->
			<div id="displayId" style="display: none;color: burlywood; font-size: 18px; padding-left: 15px;">
				<logic:present name="itdeclaraton" scope="request">
					<bean:write name="itdeclaraton" scope="request" />
				</logic:present>
			</div>
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 10pt; font-weight: 700; color: #5d5d5d;">
					<div style="padding: 5px 32px; ">
						<table class="employee" id="allstudent" cellpadding="5" cellspacing="0" border="1" width="100%">
							<tr>
								<th colspan="3"
									style="font-size: 17px; font-family: Roboto Regular !important; background-color: #f5f5f5; text-align: center;">Arya
									Central School</th>
							</tr>
							<tr>
								<th colspan="3"
									style="font-size: 17px; font-family: Roboto Regular !important; background-color: #f5f5f5; text-align: center;">
									IT DECLARATION SHEET FOR THE YEAR OF <logic:present
										name="academic_year">
										<bean:write name="academic_year" />
									</logic:present>
								</th>
							</tr>

							<tr>
								<td 
									class="tdfirstchild" style="line-height: 0px;width: 685px;">Employee
									Name</td>
								<td 
									style="font-size: 10pt; font-weight: 700; text-align: center;">

									<input type="text" name="employeeId" id="employeeId"
									  readonly="readonly"
									style="width: 100%; background-color: rgb(242, 250, 252); background: transparent;"
									value="<logic:present name="list"><bean:write name="list" property="name"  />
									</logic:present>" />

								<input type="hidden" name="hemloyeeid" id="hemloyeeid"
									value="<logic:present name="list"><bean:write name="list" property="empid"  /></logic:present>" />
								</td>
								
							</tr>


							<tr>
								<td 
									class="tdfirstchild" style="line-height: 0px;">Employee
									Id</td>
								<td 
									style="font-size: 10pt; font-weight: 700; text-align: center;"><input
									type="text" name="employeeId" id="employeeId"  
									readonly="readonly" style="width: 100%;"
									value="<logic:present name="list"><bean:write name="list" property="teacherId" />
									</logic:present>" /></td>
							</tr>


							<tr>
								<td 
									class="tdfirstchild" style="line-height: 0px;">Date
									Of Joining</td>
								<td 
									style="font-size: 10pt; font-weight: 700; text-align: center;"><input
									type="text" name="dateofJoining" id="dateofJoining"
									readonly="readonly"
									style="width: 100%; background-color: rgb(242, 250, 252);background: transparent;"
									 
									value="<logic:present name="list"><bean:write name="list" property="doj" />
									</logic:present>" /></td>
							</tr>

							<tr>
								<td 
									class="tdfirstchild" style="line-height: 0px;">Pan
									Number</td>
								<td 
									style="font-size: 10pt; font-weight: 700; text-align: center;"><input
									type="text" name="pan_number" id="pan_number"  
									readonly="readonly" style="width: 100%;"
									value="<logic:present name="list"><bean:write name="list" property="pannumber" />
									</logic:present>" /></td>
							</tr>



						</table>
						<br />

						<table class="salary" id="allstudent" cellpadding="5" cellspacing="0" border="1" width="100%">
							<tr>
								<th colspan="4"	style="font-size: 17px; text-align: left; background-color: #f5f5f5; font-family: Roboto Regular !important;">
								<span class="glyphicon glyphicon-menu-hamburger" style="vertical-align: -5px; margin-right: 5px; padding-left: 5px;"></span>SALARY BREAKUP</th>
							</tr>
							
							<tr>
								<th	class="thheaderfont" style="width:685px;">Particulars</th>
								<th	class="thheaderfont">Monthly Amount</th>
								<th	class="thheaderfont">Months</th>
								<th class="thheaderfont">Total Per Year </th>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Basic</td>
								<td	class="tdfirstchild">
									<input type="text" name="basic" id="basic" class="tdsecondchildon floatval"
										value="<logic:present name="list"><bean:write name="list" property="basic" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild">
									<input type="text" name="basicperMonth" id="basicperMonth" style="text-align: center;" class="tdsecondchildon"
										value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" readonly="readonly"/></td>
								<td class="tdfirstchild">
									<input type="text" name="basicperYear" id="basicperYear" class="tdthirdchildon" readonly="readonly" /></td>
							</tr>


							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">DA</td>
								<td class="tdfirstchild">
									<input type="text" name="da" id="da"   class="tdsecondchildon floatval"
										value="<logic:present name="list"><bean:write name="list" property="da" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild">
									<input type="text" name="daPerMonth" id="daPerMonth" style="text-align: center;" class="tdsecondchildon"
										value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild">
									<input type="text" name="daperYear" id="daperYear" class="tdthirdchildon" readonly="readonly" /></td>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">HRA</td>
								<td class="tdfirstchild">
									<input type="text" name="hra" id="hra"   class="tdsecondchildon floatval"
										value="<logic:present name="list"><bean:write name="list" property="hra" /></logic:present>" readonly="readonly"/></td>
								<td class="tdfirstchild">
									<input type="text" name="hraPerMonth" id="hraPerMonth" style="text-align: center;" class="tdsecondchildon"
										value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild">
									<input type="text" name="hraperYear" id="hraperYear"  
										value="" readonly="readonly" class="tdthirdchildon"/></td>
							</tr>
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Transport Allowance/Conveyance</td>
								<td class="tdfirstchild">
									<input	type="text" name="transport" id="transport" class="tdsecondchildon floatval"
										value="<logic:present name="list"><bean:write name="list" property="allowance" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild">
									<input type="text" name="transportperMonth" style="text-align: center;" id="transportperMonth" class="tdsecondchildon"
										value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild">
									<input type="text" name="transportYear" id="transportYear" class="tdthirdchildon"	
										value="" readonly="readonly" /></td>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Child Education</td>
								<td class="tdfirstchild"><input	type="text" name="childeducation" id="childeducation" class="tdsecondchildon floatval" 
									value="<logic:present name="list"><bean:write name="list" property="childedu" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild">
									<input type="text" name="childeducationperMonth" style="text-align: center;" id="childeducationperMonth" class="tdsecondchildon"
										value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild">
									<input type="text" name="childeducationYear" id="childeducationYear" class="tdthirdchildon"
									  	value="" readonly="readonly" /></td>
							</tr> 

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Special Allowance</td>
								<td class="tdfirstchild">
									<input	type="text" name="special" id="special" class="tdsecondchildon floatval"
										value="<logic:present name="list"><bean:write name="list" property="special" /></logic:present>" readonly="readonly"/></td>
								<td class="tdfirstchild">
									<input type="text" name="specialperMonth" style="text-align: center;" id="specialperMonth" class="tdsecondchildon"
										value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild">
									<input type="text" name="specialYear" id="specialYear" class="tdthirdchildon"
										value="" readonly="readonly" /></td>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Arrears</td>
								<td class="tdfirstchild">
									<input	type="text" name="arrears" id="arrears" class="tdsecondchildon floatval"
									  	value="<logic:present name="list"><bean:write name="list" property="arrears" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild">
									<input type="text" name="arrearsperMonth" style="text-align: center;" id="arrearsperMonth" class="tdsecondchildon"
										value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild"><input type="text" name="arrearsYear" id="arrearsYear" class="tdthirdchildon" 
									value="" readonly="readonly" /></td>
							</tr> 
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Performance Incentive</td>
								<td class="tdfirstchild">
									<input	type="text" name="performanceiIncentive" class="tdsecondchildon floatval" id="performanceiIncentive"   
										value="<logic:present name="list"><bean:write name="list" property="perfincentive" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild">
									<input type="text" name="performanceiIncentiveperMonth" style="text-align: center;" id="performanceiIncentiveperMonth" class="tdsecondchildon" readonly="readonly" 
										value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" /></td>
								<td class="tdfirstchild">
									<input	type="text" name="performanceiIncentiveYear" id="performanceiIncentiveYear" class="tdthirdchildon"
									   	value="" readonly="readonly" /></td>
							</tr> 

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Medical
									Reimbursement</td>
								<td class="tdfirstchild"><input	type="text" name="medicalReimbursment" id="medicalReimbursment" class="tdsecondchildon floatval" 
									value="<logic:present name="list"><bean:write name="list" property="medical" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild">
									<input type="text" name="medicalReimbursmentperMonth" style="text-align: center;" id="medicalReimbursmentperMonth" class="tdsecondchildon" readonly="readonly" 
										value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" /></td>
								<td class="tdfirstchild">
									<input	type="text" name="medicalReimbursmentYear" id="medicalReimbursmentYear" class="tdthirdchildon"
										value="" readonly="readonly" /></td>
							</tr>


							 <tr>
								<td class="tdfirstchild" style="line-height: 0px;">Leave
									Encashment</td>
								<td class="tdfirstchild">
									<input	type="text" name="leaveEncashment" id="leaveEncashment"	class="tdsecondchildon floatval"
									  	value="<logic:present name="list"><bean:write name="list" property="leave" /></logic:present>" readonly="readonly"  /></td>
								<td class="tdfirstchild">
									<input type="text" name="leaveEncashmentperMonth" style="text-align: center;" id="leaveEncashmentperMonth" class="tdsecondchildon" readonly="readonly" 
										value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" /></td>
								<td class="tdfirstchild">
									<input type="text" name="leaveEncashmentYear" id="leaveEncashmentYear" class="tdthirdchildon"
										value="" readonly="readonly" /></td>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Food Coupons</td>
								<td class="tdfirstchild">
									<input	type="text" name="foodcoupons" id="foodcoupons" class="tdsecondchildon floatval"
									 	value="<logic:present name="list"><bean:write name="list" property="foodallowance" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild">
									<input type="text" name="foodcouponspermonth" style="text-align: center;" id="foodcouponspermonth" class="tdsecondchildon" readonly="readonly" 
										value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" /></td>
								<td class="tdfirstchild">
									<input	type="text" name="foodcouponsYear" id="foodcouponsYear" class="tdthirdchildon"
									 	value="" readonly="readonly" /></td>
							</tr>


							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Reimbursements
								</td>
								<td class="tdfirstchild">
									<input type="text" name="reimbursments"	id="reimbursments" class="tdsecondchildon floatval"
									   value="<logic:present name="list"><bean:write name="list" property="reimburse" /></logic:present>" readonly="readonly"  /></td>
								<td class="tdfirstchild">
									<input type="text" name="reimbursmentspermonth" style="text-align: center;" id="reimbursmentspermonth" class="tdsecondchildon" readonly="readonly" 
										value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" /></td>
								<td class="tdfirstchild">
									<input type="text" name="reimbursmentsYear" id="reimbursmentsYear" class="tdthirdchildon"
										 value="" readonly="readonly" /></td>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Internet Expenses</td>
								<td class="tdfirstchild">
									<input type="text" name="internetExpenses" id="internetExpenses" class="tdsecondchildon floatval"
										value="<logic:present name="list"><bean:write name="list" property="internet" /></logic:present>" readonly="readonly"/></td>
								<td class="tdfirstchild">
									<input type="text" name="internetExpensespermonth" style="text-align: center;" id="internetExpensespermonth" class="tdsecondchildon" readonly="readonly" 
										value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" /></td>
								<td class="tdfirstchild">
									<input type="text" name="internetexpensesYear"	id="internetexpensesYear"   readonly="readonly"
										value="" class="tdthirdchildon" /></td>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Driver Salary</td>
								<td class="tdfirstchild">
									<input type="text" name="driversalary" id="driversalary" class="tdsecondchildon floatval" 
									 value="<logic:present name="list"><bean:write name="list" property="driver" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild">
									<input type="text" name="driversalarypermonth" style="text-align: center;" id="driversalarypermonth" class="tdsecondchildon" readonly="readonly" 
										value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" /></td>
								<td class="tdfirstchild">
									<input type="text" name="driversalaryYear" id="driversalaryYear" class="tdthirdchildon" 
									 	value="" readonly="readonly" /></td>
							</tr>


							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Other</td>
								<td class="tdfirstchild">
									<input type="text" name="other" id="other" class="tdsecondchildon floatval"	
									 	value="<logic:present name="list"><bean:write name="list" property="others" /></logic:present>" readonly="readonly" /></td>
								<td class="tdfirstchild">
									<input type="text" name="otherpermonth" style="text-align: center;" id="otherpermonth" class="tdsecondchildon" readonly="readonly" 
										value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" /></td>
								<td class="tdfirstchild">
									<input type="text" name="otherperyear" id="otherperyear" class="tdthirdchildon"
										  value=""	readonly="readonly" /></td>
							</tr>


							<tr>
								<th 
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: left; font-weight: bold;padding-left: 5px">Total
								</th>


								<th
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: left;"><input
									type="text" name="totalsal" id="totalsal"
									  value="<logic:present name="list"><bean:write name="list" property="totalsalary" /></logic:present>" readonly="readonly" class="tdthirdchildon floatval"
									  /></th>
								
								<th
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: left;"><input
									type="text" name="totalsalpermonth" id="totalsalpermonth"
									  value="" class="tdthirdchildon" readonly="readonly" 
									  /></th>
								
								
								<th
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: left;"><input
									type="text" name="totalsalperyear" id="totalsalperyear"
									  value="" class="tdthirdchildon"
									readonly="readonly" /></th>
							</tr>

						</table>
						<br />
						
						<table class="monthlydeduction" id="allstudent" cellpadding="5" cellspacing="0" border="1" width="100%">
							<tr>
								<th colspan="2"
									style="font-size: 17px; text-align: left; background-color: #f5f5f5; font-family: Roboto Regular !important;"><span
									class="glyphicon glyphicon-menu-hamburger"
									style="vertical-align: -5px; margin-right: 5px; padding-left: 5px;"></span>MONTHLY DEDUCTION</th>
							</tr>

							<tr>
								<th
									class="thheaderfont" style="width:685px;">Particulars</th>
								<!-- <th
									class="thheaderfont"></th> -->
								<th	class="thheaderfont">Monthly Amount</th>
							</tr>




							<tr>

								<td class="tdfirstchild" style="line-height: 0px;">TDS Deducted from salary</td>
								<!-- <td class="tdfirstchild"><input
									type="text" name="TDSDeduction" id="TDSDeduction"
									  value=""
									class="tdsecondchildon"
									  /></td> -->
								<!-- <td class="tdfirstchild"></td> -->
								<td class="tdfirstchild"><input
									type="text" name="TDSDeductionfromsal" id="TDSDeductionfromsal"
									  value="<logic:present name="list"><bean:write name="list" property="tdspayabletax" /></logic:present>" class="tdthirdchildon"
									readonly="readonly" /></td>
							</tr>

							<tr>

								<td class="tdfirstchild" style="line-height: 0px;">Employee's
									PF Contribution</td>
								<!-- <td class="tdfirstchild"><input
									type="text" name="employeesPFContributionpermonth"
									id="employeesPFContributionpermonth"   value=""
									class="tdsecondchildon"
									 /></td> -->
								<!-- <td class="tdfirstchild"></td> -->
								<td class="tdfirstchild"><input
									type="text" name="employeesPFContribution"
									id="employeesPFContribution"   value="<logic:present name="list"><bean:write name="list" property="pfamount" /></logic:present>"
									class="tdthirdchildon" readonly="readonly" /></td>
							</tr>


							<tr>

								<td class="tdfirstchild" style="line-height: 0px;">Professional
									Tax</td>
								<!-- <td class="tdfirstchild"><input
									type="text" name="professionaltaxpermonth"
									id="professionaltaxpermonth"   value=""
									class="tdsecondchildon"
									  /></td> -->
								<!-- <td class="tdfirstchild"></td> -->
								<td class="tdfirstchild"><input
									type="text" name="professionaltax"
									id="professionaltax"   value="<logic:present name="list"><bean:write name="list" property="pt" /></logic:present>"
									class="tdthirdchildon" readonly="readonly" /></td>
							</tr>

							<tr>
								<th colspan=""
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: left; font-weight: bold;padding-left: 5px">Total</th>
								<!-- <th
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: right;"><input
									type="text" name="totaldeductionspermonth"
									id="totaldeductionspermonth"   value=""
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: right;"
									  /></th>
 -->
								<th
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: right;"><input
									type="text" name="totaldeductionsperyear"
									id="totaldeductionsperyear"   value=""
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: right;"
									readonly="readonly" /></th>
							</tr>

						</table>
						<br />
					<!-- Entering Manually -->
					
						<table class="exemption" id="allstudent" cellpadding="5" cellspacing="0" border="1" width="100%">
							<tr>
								<th colspan="3"
									style="font-size: 17px; text-align: left; background-color: #f5f5f5; font-family: Roboto Regular !important;">
									<span class="glyphicon glyphicon-menu-hamburger" style="vertical-align: -5px; margin-right: 5px; padding-left: 5px;"></span>
									INCOME FROM OTHER SOURCES</th>
							</tr>

							<tr>
								<th	class="thheaderfont" style="width:685px;">Particulars</th>
								
								<th class="thheaderfont">Maximum Limit</th>
								<th	class="thheaderfont">
									Declared Amount</th>
								
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Income from other than Salary</td>
								
								<td class="tdfirstchild"><input
									type="text" name="maxhouseincomefromother" id="maxhouseincomefromother"
									value="No Limit" class="tdthirdchildon" style="text-align: left;"
									readonly="readonly" /></td>
									
								<td class="tdfirstchild"><input   tabindex="1"
									type="text" name="houseincomefromother" id="houseincomefromother"
									value="<logic:present name="list"><bean:write name="list" property="otherIncome" /></logic:present>"
									class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" /></td>
							</tr>
							
							<tr>

								<td class="tdfirstchild" style="line-height: 0px;">Interest on Housing Loan (Letout)</td>
								
								<td class="tdfirstchild"><input
									type="text" name="maxhouseintrestloan" id="maxhouseintrestloan"
									value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="incomeOtherHousingLoanLetoutMax" /></logic:present>" class="tdthirdchildon"
									readonly="readonly" /></td>
									
								<td class="tdfirstchild"><input tabindex="2"
									type="text" name="houseintrestloan"
									id="houseintrestloan"   value="<logic:present name="list"><bean:write name="list" property="incomeOtherHousingLoanSelf" /></logic:present>"
									class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);"
									  /></td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Interest on housing loan (Self)</td>
								<td class="tdfirstchild"><input 
									type="text" name="maxotherIncomeSelf" id="maxotherIncomeSelf"
									value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="incomeOtherHousingLoanSelfMax" /></logic:present>" class="tdthirdchildon"
									readonly="readonly" /></td>
								<td class="tdfirstchild"><input tabindex="3"
									type="text" name="otherIncomeSelf" id="otherIncomeSelf"
									value="<logic:present name="list"><bean:write name="list" property="incomeOtherHousingLoanLetout" /></logic:present>" 
									class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);"
									  /></td>
							</tr>
							
							<tr style="line-height: 0;">
								<th colspan="1"
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: left; font-weight: bold;padding-left: 5px">Total
								</th>
								
								<th	class="tdfirstchild"><input
									type="text" name="totalOtherIncomeperyear"
									id="totalOtherIncomeperyear"   value=""
									class="tdthirdchildon"
									readonly="readonly" /></th>
									
								<th
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: right;"><input
									type="text" name="totalofOtherIncome"
									id="totalofOtherIncome"   value="" readonly="readonly"
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: right;"
									  /></th>
							</tr>
						</table>
						<br />

						<table class="exemption" id="allstudent" cellpadding="5" cellspacing="0" border="1" width="100%">
							<tr>
								<th colspan="3"
									style="font-size: 17px; text-align: left; background-color: #f5f5f5; font-family: Roboto Regular !important;">
									<span class="glyphicon glyphicon-menu-hamburger" style="vertical-align: -5px; margin-right: 5px; padding-left: 5px;"></span>
									Less : Exemption (Section 10 & 17)</th>
							</tr>

							<tr>
								<th	class="thheaderfont" style="width:685px;">Particulars</th>
								
								<th class="thheaderfont">Maximum Limit</th>
								<th	class="thheaderfont">
									Declared Amount</th>
								
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">HRA
									Exemption (Sec10 (13A))</td>

								<td class="tdfirstchild"><input
									type="text" name="maxhraExemption"
									id="maxhraExemption"   value=""
									class="tdthirdchildon" readonly="readonly" /></td>
								<td class="tdfirstchild"><input tabindex="4"
									type="text" name="hraExemptionSection"
									id="hraExemptionSection"   value="<logic:present name="list"><bean:write name="list" property="exemptHra" /></logic:present>" readonly="readonly"
									class="tdsecondchildon" 
									  /></td>
							</tr>

							<tr>

								<td class="tdfirstchild" style="line-height: 0px;">Child
									Education (Sec10 (14))</td>

								<td class="tdfirstchild"><input
									type="text" name="maxchildeducationexemption"
									id="maxchildeducationexemption"   value=""
									class="tdthirdchildon" readonly="readonly" /></td>
								
								<td class="tdfirstchild"><input tabindex="5"
									type="text" name="childeducationexemption"
									id="childeducationexemption"   value="<logic:present name="list"><bean:write name="list" property="exemptChildEdu" /></logic:present>"
									class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);"
									  /></td>
							</tr>


							<tr>

								<td class="tdfirstchild" style="line-height: 0px;">Medi
									Reimbursement (Sec 17 (2))</td>

								<td class="tdfirstchild"><input
									type="text" name="maxmedicalsectionexemption"
									id="maxmedicalsectionexemption"   value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="exemptMedReimbMax" /></logic:present>"
									class="tdthirdchildon" readonly="readonly" /></td>
								
								<td class="tdfirstchild"><input tabindex="6"
									type="text" name="medicalsectionexemption"
									id="medicalsectionexemption"   value="<logic:present name="list"><bean:write name="list" property="exemptMedReimb" /></logic:present>"
									class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);"
									  /></td>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Transport
									Allowance (Sec10 (14))</td>
								
								<td class="tdfirstchild"><input
									type="text" name="maxtransportsectionexemption"
									id="maxtransportsectionexemption"   value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="exemptTransAllowanceMax" /></logic:present>"
									class="tdthirdchildon" readonly="readonly" /></td>
								
								<td class="tdfirstchild"><input tabindex="7"
									type="text" name="transportsectionexemption"
									id="transportsectionexemption"   value="<logic:present name="list"><bean:write name="list" property="exemptTransAllowance" /></logic:present>"
									class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);"
									  /></td>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">LTA
									(Sec 10 (5))</td>
								
								<td class="tdfirstchild"><input
									type="text" name="maxltasection" id="maxltasection"
									  value="" class="tdthirdchildon"
									readonly="readonly" /></td>
									
								<td class="tdfirstchild"><input tabindex="8"
									type="text" name="ltasectionexemption" id="ltasectionexemption"
									value="<logic:present name="list"><bean:write name="list" property="exemptLTA" /></logic:present>"
									class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);"
									  /></td>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Food
									Coupons</td>
								
								<td class="tdfirstchild"><input
									type="text" name="maxfoodsection" id="maxfoodsection"
									  value="" class="tdthirdchildon"
									readonly="readonly" /></td>
									
								<td class="tdfirstchild"><input tabindex="9"
									type="text" name="foodsectionexemption" id="foodsectionexemption"
									value="<logic:present name="list"><bean:write name="list" property="exemptFoodCoupon" /></logic:present>"
									class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);"
									  /></td>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Telephone
									Reimbursement</td>
								
								<td class="tdfirstchild"><input
									type="text" name="maxtelephoneexemption"
									id="maxtelephoneexemption"   value=""
									class="tdthirdchildon" readonly="readonly" /></td>
								
								<td class="tdfirstchild"><input tabindex="10"
									type="text" name="telephoneexemption"
									id="telephoneexemption"   value="<logic:present name="list"><bean:write name="list" property="exemptTelephoneReimb" /></logic:present>"
									class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);"
									  /></td>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Car
									Expense Reimbursement (Sec10 (14))</td>
								
								<td class="tdfirstchild"><input
									type="text" name="maxreimburseexemption"
									id="maxreimburseexemption"   value=""
									class="tdthirdchildon" readonly="readonly" /></td>
								
								<td class="tdfirstchild"><input tabindex="11"
									type="text" name="carreimburseexemption"
									id="carreimburseexemption"   value="<logic:present name="list"><bean:write name="list" property="exemptCarExpReimb" /></logic:present>"
									class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);"
									  /></td>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Internet
									Expenses</td>
								
								<td class="tdfirstchild"><input
									type="text" name="maxinternetsectionexp"
									id="maxinternetsectionexp"   value=""
									class="tdthirdchildon" readonly="readonly" /></td>
									
								<td class="tdfirstchild"><input tabindex="12"
									type="text" name="internetsectionexemption"
									id="internetsectionexemption"   value="<logic:present name="list"><bean:write name="list" property="exemptInternetExp" /></logic:present>"
									class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);"
									  /></td>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Driver
									Salary</td>
								
								<td class="tdfirstchild"><input
									type="text" name="driversectionperyear"
									id="driversectionperyear"   value=""
									class="tdthirdchildon" readonly="readonly" /></td>
									
								<td class="tdfirstchild"><input tabindex="13"
									type="text" name="driversectionexemption"
									id="driversectionexemption"   value="<logic:present name="list"><bean:write name="list" property="exemptDriverSal" /></logic:present>"
									class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);"
									  /></td>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Other
									Expenses</td>
								
								<td class="tdfirstchild"><input
									type="text" name="maxothersection" id="maxothersection"
									  value="" class="tdthirdchildon"
									readonly="readonly" /></td>
									
								<td class="tdfirstchild"><input tabindex="14"
									type="text" name="otherssectionexpense"
									id="otherssectionexpense"   value="<logic:present name="list"><bean:write name="list" property="exemptOtherExp" /></logic:present>"
									class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);"
									  /></td>
							</tr>

							<tr>
								<th colspan="1"
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: left; font-weight: bold;padding-left: 5px">Total
								</th>

								<th
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: right;"></th>

								<th
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: right;"><input
									type="text" name="totalSectionAmount" id="totalSectionAmount"
									value=""
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: right;"
									readonly="readonly" /></th>
							</tr>

						</table>
						<br />

						<table class="section" id="allstudent" cellpadding="5" cellspacing="0" border="1" width="100%">
							<thead>
							<tr>
								<th colspan="3"
									style="font-size: 17px; text-align: left; background-color: #f5f5f5; font-family: Roboto Regular !important;"><span
									class="glyphicon glyphicon-menu-hamburger"
									style="vertical-align: -5px; margin-right: 5px; padding-left: 5px;"></span>CHAPTER VIA - SECTION 80C (MAXIMUM OF RS 1,50,000)</th>
							</tr>

							<tr>
								<th	class="thheaderfont" style="width:685px;">Particulars</th>
								
								<th	class="thheaderfont">Maximum Limit</th>
									
								<th	class="thheaderfont">Declared Amount</th>
							</tr>
							</thead>
							<tbody>
							<tr>
							<input type="hidden" name="hrapercentage" id="hrapercentage" value="<logic:present name="list"><bean:write name="list" property="hrapercentage" /></logic:present>" />
							<input type="hidden" name="hmonth" id="hmonth" value="<logic:present name="list"><bean:write name="list" property="month" /></logic:present>" />
							<input type="hidden" name="tdsctc" id="tdsctc" value="<logic:present name="list"><bean:write name="list" property="tdsctc" /></logic:present>" />
							<input type="hidden" name="tdspt" id="tdspt" value="<logic:present name="list"><bean:write name="list" property="tdspt" /></logic:present>" />
							<input type="hidden" name="taxableExemption" id="taxableExemption" value="" />
							<input type="hidden" name="taxableAnyOtherIncome" id="taxableAnyOtherIncome" value="" />
							<input type="hidden" name="taxworkUnderCapterVIA" id="taxworkUnderCapterVIA" value="" />
							<input type="hidden" name="taxableTotalAmount" id="taxableTotalAmount" value="" />
							<input type="hidden" name="totalTaxAmount" id="totalTaxAmount" value="" />
							<input type="hidden" name="netTax" id="netTax" value="" />
							<input type="hidden" name="taxworkPerMonth" id="taxworkPerMonth" value="" />
							
								<td class="tdfirstchild" style="line-height: 0px;">Employee Provident Fund</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxemployeeprovidentfund" id="maxemployeeprovidentfund"   
										value=" "
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="empprovidentfund"	id="empprovidentfund"  tabindex="15"
										value=""
										class="tdsecondchildon" readonly="readonly" />
									<input type="hidden" id="pfamount" value="<logic:present name="list"><bean:write name="list" property="pfamount" /></logic:present>"/>
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Payent of Tution Fees (Maximum up to two children)</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxtutionfee" id="maxtutionfee"   value=" "
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="tutionfee"	id="tutionfee"  tabindex="16"
										value="<logic:present name="list"><bean:write name="list" property="section80CTutionFee" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Fixed deposit (FD) 5 Years and above </td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxfixeddeposit" id="maxfixeddeposit"   value=" "
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="fixeddeposit"	id="fixeddeposit" tabindex="17"
										value="<logic:present name="list"><bean:write name="list" property="section80CFixedDeposit" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Life Insurance Premium </td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxlip" id="maxlip"   value=" "
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="lifeinspremium" id="lifeinspremium" tabindex="18"
										value="<logic:present name="list"><bean:write name="list" property="section80CLIC" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Mutual Fund </td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxmutualfund" id="maxmutualfund"   value=" "
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="mutualfund"	id="mutualfund" tabindex="19"
										value="<logic:present name="list"><bean:write name="list" property="section80CMutualFund" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">National Saving Certificates </td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxnac" id="maxnac"   value=" "
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="nationalsavecertificate"	id="nationalsavecertificate" tabindex="20"
										value="<logic:present name="list"><bean:write name="list" property="section80CNationalSaveCerti" /></logic:present>"
										class="tdsecondchildon"  style="background-color: rgba(255, 224, 0, 0.22);"/>
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Accrued NSC Interest </td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxaccruednsci" id="maxaccruednsci"   value=" "
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="accruednsci"	id="accruednsci"  tabindex="21"
										value="<logic:present name="list"><bean:write name="list" property="section80CAccruNSC" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Public Provident Fund </td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxpublicpf" id="maxpublicpf"   value=" "
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="publicpf"	id="publicpf" tabindex="22"
										value="<logic:present name="list"><bean:write name="list" property="section80CPublicPF" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Repayment of Housing Loan - 100000 </td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxrepaymenthouseloan" id="maxrepaymenthouseloan"   value=" "
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="repaymenthouseloan"	id="repaymenthouseloan" tabindex="23"
										value="<logic:present name="list"><bean:write name="list" property="section80CRepayHousingLoan" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Unit Linked Insurance Plan (ULIP)</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxulip" id="maxulip"   value=" "
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="unitLinkInsPlan"	id="unitLinkInsPlan" tabindex="24"
										value="<logic:present name="list"><bean:write name="list" property="section80CULIP" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Senior Citizen Saving Scheme</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxscss" id="maxscss"   value=" "
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="seniorcitizensave"	id="scss" tabindex="25"
										value="<logic:present name="list"><bean:write name="list" property="section80CSeniorCitizenSaving" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Post office Saving Bank (Cumulative time deposit)</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxpostsavingbank" id="maxpostsavingbank"   value=" "
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="postsavingbank" id="postsavingbank" tabindex="26"
										value="<logic:present name="list"><bean:write name="list" property="section80CPostSaving" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Other</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxothersection" id="maxothersection"   value=" "
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="othersection"	id="othersection" tabindex="27"
										value="<logic:present name="list"><bean:write name="list" property="section80COther" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							</tbody>
							<tfoot>
							<tr>
								<th colspan="1"
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: left; font-weight: bold;padding-left: 5px">Total
								</th>

								<th	style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: right;"></th>

								<th	style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: right;"><input
									type="text" name="totalChapter" id="totalChapter"
									value="<logic:present name="list"><bean:write name="list" property="section80CTotal" /></logic:present>"
									style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: right;"
									readonly="readonly" /></th>
							</tr>
							</tfoot>
							
						</table>
						<br />

						<table class="section80d" id="allstudent" cellpadding="5" cellspacing="0" border="1" width="100%">
							<tr>
								<th colspan="3"	style="font-size: 17px; text-align: left; background-color: #f5f5f5; font-family: Roboto Regular !important;"><span
									class="glyphicon glyphicon-menu-hamburger"
									style="vertical-align: -5px; margin-right: 5px; padding-left: 5px;"></span>CHAPTER VIA - SECTION 80D TO 80U</th>
							</tr>


							<tr>
								<th	class="thheaderfont" style="width:685px;">Particulars</th>
								<th	class="thheaderfont">Maximum Limit</th>
								<th	class="thheaderfont">Declared Amount</th>
							</tr>

							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">80D: Mediclaim Premium of Self, Spouse and Children</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxmediclaimself" id="maxmediclaimself"  value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="section80DMediClaim1Max" /></logic:present>"
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="mediclaimself"	id="mediclaimself" tabindex="28"
										value="<logic:present name="list"><bean:write name="list" property="section80DMediClaim1" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<%-- <tr>
								<td class="tdfirstchild" style="line-height: 0px;">80D: Mediclaim Premium of Parents (Age below 60 years)</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxmediclaimparents" id="maxmediclaimparents"   value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="section80DMediClaim2Max" /></logic:present>"
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="mediclaimparents"	id="mediclaimparents" tabindex="29"
										value="<logic:present name="list"><bean:write name="list" property="section80DMediClaim2" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr> --%>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">80D: Mediclaim Premium of Parents (Age 60 years and above but below 80 years)</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxmediclaimparentsabove" id="maxmediclaimparentsabove"   value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="section80DMediClaim3Max" /></logic:present>"
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="mediclaimparentsabove"	id="mediclaimparentsabove" tabindex="29"
										value="<logic:present name="list"><bean:write name="list" property="section80DMediClaim3" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
						<%--<tr>
								<td class="tdfirstchild" style="line-height: 0px;">80DDB: Medi treatment of Specified Diseases for self or dependent of age 60 yrs and above but below 80 yrs</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxmeditreatbeloweighty" id="maxmeditreatbeloweighty"   value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="section80DDBMedTreatment1Max" /></logic:present>"
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="meditreatbeloweighty"	id="meditreatbeloweighty" tabindex="30"
										value="<logic:present name="list"><bean:write name="list" property="section80DDBMedTreatment1" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">80DDB: Medi treatment of Specified Diseases for self or dependent of age 80 years and above</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxmeditreataboveeighty" id="maxmeditreataboveeighty"   value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="section80DDBMedTreatment2Max" /></logic:present>"
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="meditreataboveeighty"	id="meditreataboveeighty" tabindex="31"
										value="<logic:present name="list"><bean:write name="list" property="section80DDBMedTreatment2" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr> --%>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">80E: Interest of Education Loan</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxinteresteduloan" id="maxinteresteduloan" value="No Limit" style="text-align: left;"
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="interesteduloan"	id="interesteduloan" tabindex="32"
										value="<logic:present name="list"><bean:write name="list" property="section80EEduLoanInterest" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">Additional Contribution u/s 80CCD(1B)-NPS</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxadditionalcontribute" id="maxadditionalcontribute"   value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="section80CCDNPSMax" /></logic:present>"
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="additionalcontribute"	id="additionalcontribute" tabindex="33"
										value="<logic:present name="list"><bean:write name="list" property="section80CCDNPS" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<%-- <tr>
								<td class="tdfirstchild" style="line-height: 0px;">80U: Deduction in case of self physical disability (Disability 40% and above but less than 80%)</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxdeductionofphysicalbelow" id="maxdeductionofphysicalbelow"   value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="section80UDeduction1Max" /></logic:present>"
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="deductionofphysicalbelow"	id="deductionofphysicalbelow"  tabindex="34"
										value="<logic:present name="list"><bean:write name="list" property="section80UDeduction1" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">80U: Deduction in case of self physical disability (Disability 80% and above)</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxdeductionofphysicalabove" id="maxdeductionofphysicalabove"   value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="section80UDeduction2Max" /></logic:present>"
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="deductionofphysicalabove"	id="deductionofphysicalabove" tabindex="35"
										value="<logic:present name="list"><bean:write name="list" property="section80UDeduction2" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr> --%>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">80CCG: Rajiv Gandhi Equity Saving Scheme</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxrajivgandhiequity" id="maxrajivgandhiequity"   class="tdthirdchildon" 
										value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="section80CCGRajivEquitySavingMax" /></logic:present>" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="rajivgandhiequity"	id="rajivgandhiequity"   class="tdsecondchildon" tabindex="36"
										value="<logic:present name="list"><bean:write name="list" property="section80CCGRajivEquitySaving" /></logic:present>" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">80TTA: Interest of Saving Bank Account</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxeightyttainterestsaving" id="maxeightyttainterestsaving"   value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="section80TTASavingBankInterestMax" /></logic:present>"
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="eightyttainterestsaving"	id="eightyttainterestsaving" tabindex="37"
										value="<logic:present name="list"><bean:write name="list" property="section80TTASavingBankInterest" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">80DD: Maintenance including medi treatment of disabled denendent with disability 40% and above but less that 80%</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxeightyddmedicaltreatbelow" id="maxeightyddmedicaltreatbelow"   value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="section80DDMedTreatment1Max" /></logic:present>"
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="eightyddmedicaltreatbelow"	id="eightyddmedicaltreatbelow" tabindex="38"
										value="<logic:present name="list"><bean:write name="list" property="section80DDMedTreatment1" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<td class="tdfirstchild" style="line-height: 0px;">80DD: Maintenance including medi treatment of disabled denendent with disability 80% and above</td>
								
								<td class="tdfirstchild">
									<input type="text" name="maxeightyddmedicaltreatabove" id="maxeightyddmedicaltreatabove"   value="<logic:present name="maximumlist"><bean:write name="maximumlist" property="section80DDMedTreatment2Max" /></logic:present>"
										class="tdthirdchildon" readonly="readonly" />
								</td>
									
								<td class="tdfirstchild">
									<input type="text" name="eightyddmedicaltreatabove"	id="eightyddmedicaltreatabove" tabindex="39"
										value="<logic:present name="list"><bean:write name="list" property="section80DDMedTreatment2" /></logic:present>"
										class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);" />
								</td>
							</tr>
							
							<tr>
								<th colspan="1"	style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: left; font-weight: bold;padding-left: 5px">Total
								</th>

								<th	style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: right;">
									</th>

								<th	style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: right;">
									<input type="text" name="totalChapter80Up" id="totalChapter80U"  value=""
										style="font-size: 14px; background: #f9f9f9 !important; font-family: Lucida Sans; text-align: right;"
										readonly="readonly" /></th>
							</tr>
							
						</table>
						<br/>
						<table class="houserent" id="allstudent" cellpadding="5" cellspacing="0" border="1" width="100%">
							<tr>
								<th colspan="2"	style="font-size: 17px; text-align: left; background-color: #f5f5f5; font-family: Roboto Regular !important;"><span
									class="glyphicon glyphicon-menu-hamburger"
									style="vertical-align: -5px; margin-right: 5px; padding-left: 5px;"></span>HOUSE RENT DETAILS</th>
							</tr>
							
							<tr>
								<td	class="tdfirstchild" style="text-align: left;line-height: 2;">Sl.No.</td>
								<td	class="tdfirstchild"><input name="sno" value='1' readonly="readonly" style="background: transparent;"/></td>
							</tr>
							<tr>
								<td	class="tdfirstchild" style="text-align: left;line-height: 0;">From Date</td>
								<td	class="tdfirstchild"><input type="text" name="housefromdate" style="background-color: rgba(255, 224, 0, 0.22);border: 0;" 
													id="startdate" maxlength="25" class="form-control"  readonly="readonly" 
													value="<logic:present name="list"><bean:write name="list" property="housefromdate" /></logic:present>" /></td>
							</tr>
							<tr> 
								<td	class="tdfirstchild" style="text-align: left;line-height: 0;">To Date</td>
								<td	class="tdfirstchild"><input type="text" name="housetodate" style="background-color: rgba(255, 224, 0, 0.22);border: 0;"
													id="enddate" maxlength="25" class="form-control"  readonly="readonly" 
													value="<logic:present name="list"><bean:write name="list" property="housetodate" /></logic:present>" /></td>
							</tr>
							<tr>
								<td	class="tdfirstchild" style="text-align: left; line-height: 0; ">City</td>
								<td	class="tdfirstchild"><input type="text" name="housecity"  style="background-color: rgba(255, 224, 0, 0.22);width: 100%;"
													value="<logic:present name="list"><bean:write name="list" property="housecity" /></logic:present>"/></td>
							</tr>
							<tr>
								<td	class="tdfirstchild" style="text-align: left; line-height: 0;  ">Landlord Name</td>
								<td	class="tdfirstchild"><input type="text" name="landlordname" style="background-color: rgba(255, 224, 0, 0.22); width: 100%;"
													value="<logic:present name="list"><bean:write name="list" property="landlordname" /></logic:present>"/></td>
							</tr>
							<tr>
								<td	class="tdfirstchild" style="text-align: left;width: 100%;line-height: 0;">Address</td>
								<td	class=""><textarea name="houseaddress" style="background-color: rgba(255, 224, 0, 0.22);"><logic:present name="list"><bean:write name="list" property="houseaddress" /></logic:present></textarea></td>
							</tr>
							<tr>
								<td	class="tdfirstchild" style="text-align: left;line-height: 0;">PANNo.</td>
								<td	class="tdfirstchild"><input type="text" name="pancardno" class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);text-align: left;"
													value="<logic:present name="list"><bean:write name="list" property="pancardno" /></logic:present>"/></td>
							</tr>
							<tr>
								<td	class="tdfirstchild" style="text-align: left;line-height: 0;">Declared Amount</td>
								<td	class="tdfirstchild"><input type="text" name="houseamount" id="houseamount" class="tdsecondchildon" style="background-color: rgba(255, 224, 0, 0.22);text-align: left;"
													value="<logic:present name="list"><bean:write name="list" property="houseamount" /></logic:present>"/></td>
							</tr>
						</table>
					</div>
				</div>
				<br />
			</div>
		</div>
		</form>
		</div>
	</div>
</body>
