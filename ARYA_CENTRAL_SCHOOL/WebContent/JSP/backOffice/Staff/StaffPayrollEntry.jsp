<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>

<title>eCampus Pro</title>

<link href="CSS/newUI/custome.css" rel="stylesheet">
<script type="text/javascript" src="JS/backOffice/Staff/StaffPayroll.js"></script>
<script type="text/javascript" src="JS/common.js"></script>


<style>
#editDesignationId:hover {
	cursor: pointer;
}


#generatePayslip:HOVER{
	cursor: pointer;
}

#iconsimg:HOVER{

cursor: pointer;
}


.modal-body{text-align:center;}
.xl{width: 18%;}
.pdf{width: 15%;}



</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-7" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Staff Salary Details</span>
			</p>
		</div>
	
		<div class="input-group col-md-5">
			
			<label  class="hedderstyle form-control" style="margin: 20px 0px; width: 15%; border: none;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;">Month</label> 
			
			
			<input type="text" name="emppfno" readonly="readonly" style="width: 30%; margin: 20px 0px; margin-left: 0%;"
											id="emppfno" maxlength="25" class="form-control" 
											value="<logic:present name="month"><bean:write name="monthname"/></logic:present>" />
			
			<label style="margin: 20px 0px; width: 15%; border: none;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;"
				class="form-control">Year</label>
				
			<input type="text" name="year" style="width: 30%; margin: 20px 0px; margin-left: 0%;"
											id="year" maxlength="25" class="form-control"  readonly="readonly"
											value="<logic:present name="month"><bean:write name="year"/></logic:present>" />


		</div>
		
		<center>
		
	
						<div class="successmessagediv" style="display: none;width: 100%">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"><span
											id="successmessagediv" scope="request" ></span></a>
							</div>
						</div>
		
		
		
						<logic:present name="errorMessage" scope="request">

							<div class="successmessagediv">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-warning bg-msg-warning"><span>
											<bean:write name="errorMessage" scope="request" />
									</span></a>
								</div>

							</div>

						</logic:present>
		
			<div class="errormessagediv" style="display: none;width: 60%">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>
			
		</center>
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;vertical-align: middle;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Staff Salary Details
					</h3></a>
				<div class="navbar-right" >
				
				<!-- <img src="images/rightline.png" style=" margin-top: -11px;"> -->
								
							
					<span class="buttons" id="generatePayslip" style="width: 22px;height: 22px;margin-bottom: 5px"   data-target="#myModal" data-toggle="tooltip" data-placement="bottom" title="Generate Payslip" >Generate Payslip</span>
						
				 
					 <span id="back" class="buttons"  >Back</span>&nbsp;
				</a>
							
<!-- 					<img src="images/rightline.png" style=" margin-top: -11px;">
 -->					
					 <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span>

				</div>
			
			</div>
				<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>
			
			<!-- pop up -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				  <div class="modal-dialog" role="document">
					<div class="modal-content">
					  <div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Download</h4>
					  </div>
					  <div class="modal-body">
							<span id="excelDownload"><img src="images/xl.png" class="xl" ></span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="pdfDownload"><img src="images/pdf.png" class="pdf" ></span>
					  </div>
					  
					</div>
				  </div>
				</div>
			
				<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<!-- <table class="table" id="allstudent"> -->
					
					<div class="col-md-12" id="scrolid"
						style="padding: 0; overflow: scroll;">
					
					<input type="hidden" id="hmonth" value="<logic:present name="month"><bean:write name="month"/></logic:present>"/>


					<logic:present name="payrollList" scope="request">

									<table  class="table" id="allstudent" style="font-family: Open Sans Light;color: #897676;" >
										
										<tr>
											<th><input type="checkbox"/></th>
											<th>Id</th>
											<th>Name</th>
											<th>AccNumber</th>
											<th>ActualDays</th>
											<th>PayableDays</th>
											<th>Leave Count</th>
											<th>Basic</th>
											<th>Da</th>
											<th>Hra</th>
											<th>Ca</th>
											<th>Medical Allowances</th>
											<th>Convenience</th>
											<th>Others</th>
											<th>Gross</th>
											<th>Emp Pf</th>
											<th>Empr Pf</th>
											<th>P.Tax</th>
											<th>Income Tax</th>
											<th>Leave Deductions</th>
											<th>Other Deductions</th>
										<!-- 	<th>Advance Salary</th> -->
											<th>Total Deductions</th>
											<th>Net Salary</th>
											
											
										</tr>
											<logic:iterate name="payrollList"  id="dateVO">
												
												<tr>
													<td ><input type="checkbox" class="sno" id="<bean:write name="dateVO" property="empId" />,<bean:write name="dateVO" property="month" />,<bean:write name="dateVO" property="year" />"/></td>
													<td class="empId"><bean:write name="dateVO" property="regId" /></td>
													<td class="empName"><bean:write name="dateVO" property="empName" /></td>
													<td class="accountnumber"><bean:write name="dateVO" property="accountnumber" /></td>
													<td class="monthDays"><bean:write name="dateVO" property="monthDays" /></td>
													<td class="payabledays" ><bean:write name="dateVO" property="payabledays" /></td>
													<td class="no_of_leaves"><bean:write name="dateVO" property="no_of_leaves" /></td>
													<td class="basic"><bean:write name="dateVO" property="basic" /></td>
													<td class="da"><bean:write name="dateVO" property="da" /></td>
													<td class="hra"><bean:write name="dateVO" property="hra" /></td>
													<td class="ca" ><bean:write name="dateVO" property="ca" /></td>
													<td class="medicalallowances"><bean:write name="dateVO" property="medicalallowances" /></td>
													<td class="convieance"><bean:write name="dateVO" property="convieance" /></td>
													<td class="others"><bean:write name="dateVO" property="others" /></td>
													<td class="grosssalary"><bean:write name="dateVO" property="grosssalary" /></td>
													<td class="employee_pf" ><bean:write name="dateVO" property="employee_pf" /></td>
													<td class="employer_pf"><bean:write name="dateVO" property="employer_pf" /></td>
													<td class="pt"><bean:write name="dateVO" property="pt" /></td>
													<td class="incomeTax"><bean:write name="dateVO" property="incomeTax" /></td>
													<td class="leavededuction"><bean:write name="dateVO" property="leavededuction" /></td>
													<td class="otherdeduction"><bean:write name="dateVO" property="otherdeduction" /></td>
													<%-- <td class="advanceTaken"><input type="text" class="advanceTaken form-control" style="margin:-7px 0px" value="<bean:write name="dateVO" property="advanceTaken" />"/></td> --%>
													<td class="totaldeductions"><bean:write name="dateVO" property="totaldeductions" /></td>
													<td class="netsalary"><bean:write name="dateVO" property="netsalary" /></td>
												
												</tr>
												
												
												
												
											</logic:iterate>
											
											
										</table>
				
					</logic:present>
						
				</div>
				</div>
				<br />
			</div>
			
		
		</div>
	</div>
	
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>
