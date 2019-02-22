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


<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Staff/generatePayroll.js"></script>
<script type="text/javascript" src="JS/common.js"></script>


<style>
.glyphicon:hover{

cursor: pointer;
}
#classSave:hover {
	cursor: pointer;
}

.buttons{

vertical-align: -28px;

}
</style>


</head>

<body>
	

	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Generate Payroll</span>
		</p>
		
	
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips"></span></a>
			</div>
		</div>


		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span	class="validateTips"></span></a>
			</div>
		</div>	
		
		<form method="post" >
			<div class="panel-group" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676"> 
								<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Generate Payroll
							</h4></a>
						
						
						 <div class="navbar-right">
							<span class="buttons" id="generatepayrollid" >Generate Payroll</span>
							<span id="back" class="buttons">Back</span>
						</div> 
					</div>
				
					<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-4" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: left; line-height: 35px;">School Name:</label>
									<label><logic:present name="locationname"><bean:write name="locationname" /></logic:present></label>
								</div>
							</div>
							<div class="col-md-4" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Financial Year:</label>
									<label><logic:present name="accyear"><bean:write name="accyear" /></logic:present></label>
								</div>
							</div>
							
							<div class="col-md-4" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Month:</label>
									<label><logic:present name="monthname"><bean:write name="monthname" /></logic:present></label>
								</div>
							</div>
							<input type="hidden" id="hiddenaccyearId" value="<logic:present name="accyearId"><bean:write name="accyearId" /></logic:present>"/>
							<input type="hidden" id="hiddenmonthvalcode" value="<logic:present name="monthvalcode"><bean:write name="monthvalcode" /></logic:present>"/>
							<input type="hidden" id="hiddenlocationId" value="<logic:present name="locationId"><bean:write name="locationId" /></logic:present>"/>
							<input type="hidden" id="hiddenyearname" value="<logic:present name="yearname"><bean:write name="yearname" /></logic:present>"/>
							
						</div>
						
							<div class="col-md-4" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: left; line-height: 35px;">Status:</label>
									<div class="col-xs-7" style="padding: 0px;width: 160px;">
										<select id="payrollstatus" name="payrollstatus" class="form-control" required>
											<option value="NotGenerated">Not Generated</option>
											<option value="Generated">Generated</option>
										</select>
									</div>
								</div>
							</div>
						
						<div style="overflow: scroll;width: 100%;height: 350px;">
						<logic:present name="EmployeePayroll" scope="request">
						<display:table class="allstudent" export="false" id="depRecords"  style="font-family: Segoe UI;width:4500px;"  name="requestScope.EmployeePayroll" >
							<display:column  style="text-align:center" title="<input type='checkbox' name='selectall' style='text-align:center' id='selectall'  />"><input type='checkbox' name='select' class='select' style='text-align:center' value='${depRecords.empId}'  /></display:column>
							<%-- <display:column property="teacherId" title="SL No"  style="text-align:center;" sortable="false"  /> --%>
							<display:column property="pfnumber" title="PF A/c" style="text-align:center;" sortable="false"  />
							<display:column property="esinumber" title="Esi Code" style="text-align:center;" sortable="false"  />
							<display:column property="regId" title="Reg Id"        style="text-align:center;" sortable="false"  />
							<display:column property="empName"  title="Name"    style="text-align:left;width:750px;" sortable="false"/>
							<display:column property="designationname" title="Designation" style="text-align:center;" sortable="false"/>
							<display:column property="deptName" title="Department" style="text-align:center;" sortable="false"/>
							
							<display:column property="monthDays" class="monthdays" title="No Of Days"  style="text-align:center;width:80px" sortable="false"/>
							<display:column property="basic" title="Basic" style="width: 80px;text-align: center !important;" sortable="false"/>
							<display:column property="da"     title="Da" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="hra"     title="HRA"            style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="convieance" title="Convey" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="performanceIncentive" title="Performance Incentive" style="text-align:center;width: 154px;" sortable="false"/>
							<display:column property="foodAllowance" title="Food Allowance" style="text-align:center;width: 107px;" sortable="false"/>
							<display:column property="specialAllowance"   title="Spl Allowences" style="text-align:center;width: 100px;" sortable="false"/>
							<display:column property="childEdu"   title="ChildEdu" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="arrears"   title="Arrears" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="reimbursement"   title="Reimbursement" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="internetExpense"   title="InternetExpense" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="driverSalary"   title="DriverSalary" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="leaveEncash"   title="LeaveEncash" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="medicalReimbursement"   title="Medical" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="totalsalary"  title="Total" style="text-align:center;width: 80px;" sortable="false"/>
							
							<display:column property="payabledays" class="payabledays" title="Payable Days"  style="text-align:center;width:90px" sortable="false"/>
							<display:column property="basic_earned" class="earnbasic" title="Basic" style="text-align:center;" sortable="false"/>
							<display:column property="da_earned"   class="earnda"  title="Da" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="hra_earned"   class="earnhra"  title="HRA"            style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="convinience_earned" class="earnconvience" title="Convey" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="performanceIncentive_earned" class="earnperform" title="Performance Incentive" style="text-align:center;width: 152px;" sortable="false"/>
							<display:column property="foodAllowance_earned" class="earnfood" title="Food Allowance" style="text-align:center;width: 106px;" sortable="false"/>
							<display:column property="specialAllowance_earned" class="earnspecial"  title="Spl Allowences" style="text-align:center;width: 100px;" sortable="false"/>
							<display:column property="childEdu_earned" class="earnchild"  title="ChildEdu" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="arrears_earned" class="earnarrears"  title="Arrears" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="reimbursement_earned" class="earnreimburse"  title="Reimbursement" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="internetExpense_earned" class="earninternet"  title="InternetExpense" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="driverSalary_earned" class="earndriver"  title="DriverSalary" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="leaveEncash_earned" class="earnleave"  title="LeaveEncash" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="medicalReimbursement_earned" class="earnmedical"  title="Medical" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="totalsalary_earned" class="earntotal" title="Total" style="text-align:center;width: 80px;" sortable="false"/>
							
							<display:column property="pfempr_earned" class="earnpfempr" title="EmprPf" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="pfempy_earned" class="earnpfempy" title="EmpyPf" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="esiempr_earned" class="earnesiempr" title="EmprEsi" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="esiempy_earned" class="earnesiempy" title="EmpyEsi" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="ptax_earned" title="Ptax" class="ptax" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="incometax_earned" title="IncomeTax" class="incometax" style="text-align:center;width: 80px;" sortable="false"/>
							<display:column property="totaldeductions" title="Total Deduction" class="totaldeduction" style="text-align:center;width: 105px;" sortable="false"/>
							
							<display:column property="takehome" class="takehome" title="Take Home"  style="text-align:center;" sortable="false"/>
							<display:column property="netpay" class="salpending" title="SalaryPending"  style="text-align:center;" sortable="false"/>
							<display:column value="0.0" title="Advance Salary" class="advance" style="text-align:center;" sortable="false"/>
							<display:column property="netpay" class="netpay" title="Net Pay" style="text-align:center;" sortable="false"/>
						</display:table>
					</logic:present>
		 			</div>
					
					</div>
				</div>
			</div>
		</form>
	</div>
</body>

</html>
