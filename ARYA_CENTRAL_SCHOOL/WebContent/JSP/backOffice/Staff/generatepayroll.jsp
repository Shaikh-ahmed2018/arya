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
		<input type="hidden" value='<logic:present name="serverMessage" scope="request"><bean:write name="serverMessage"></bean:write></logic:present>' id="payslipmessage" />
		

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span	class="validateTips"></span></a>
			</div>
		</div>	
		
		<form method="post" action="teachermenuaction.html?method=generatePayrollList" id="submitPayrollForm">
			<div class="panel-group" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676;vertical-align: text-top"> 
								<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Generate Payroll
							</h4></a>
						
						
						 <div class="navbar-right">
							<span class="buttons" id="generatepayroll" >Generate</span>
							<span class="buttons" id="payslipid" >PaySlip</span>
							<span class="buttons" id="excelid" >Excel</span>
							<span class="buttons" id="pdfid" >PDF</span>
						</div> 
					</div>
				
					<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne" style="margin-bottom: 34px;">
						<div class="panel-body">
							<div class="col-md-4" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: left; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											<option value="all">ALL</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-4" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Financial Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
							</div>
							
							<div class="col-md-4" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Month</label>
									<div class="col-xs-7">
										<select id="monthId" name="month" class="form-control" required>
											<option>----------Select----------</option>
										</select>
									</div>
								</div>
							</div> 
						</div>
						
						
					<%-- 	<logic:present name="payroll_list_status" scope="request">
						<div class="col-md-12" style="font-size: 16px;padding:5px;clear:both;">
							<p class="theading" ><span class="displayno"></span></p>	
							<display:table export="false" id="tableid"  name="requestScope.payroll_list_status" 
							requestURI="teachermenuaction.do?method=generatePayroll" class="table table-bordered">
							
							<display:column title="Select" headerClass="heading1">
								<input type="radio" name="payrollid" value="${tableid.yearvalcode},${tableid.status},${tableid.monthvalcode},${tableid.locationId},${tableid.yearName}"
										id=""> </>
							</display:column>
							<display:column property="locationName" title="School Name" headerClass="heading1" sortable="false" />
							<display:column property="yearval" title="Year" headerClass="heading1" sortable="false" />
							<display:column property="monthval" title="Month" headerClass="heading1" sortable="false" />
							<display:column property="status" title="Status" headerClass="heading1" sortable="false" class="status" />
							<display:column property="createdby" title="Created By" headerClass="heading1" sortable="false" />
							<display:column property="createddate" title="Created Date" headerClass="heading1" sortable="false" />
							<display:column property="createdtime" title="Created Time" headerClass="heading1" sortable="false" />
					
						 	</display:table> --%>
						 	
						 	
						 <logic:present name="payroll_list_status" scope="request">
						<table class="table" id="tableid">
							<thead>
							<tr>
							<th>Select</th>
							<th>School Name</th>
							<th>Year</th>
							<th>Month</th>
							<th>Status</th>
							<th>Created By</th>
							<th>Created Date</th>
							<th>Created Time</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="payroll_list_status" name="payroll_list_status">
								<tr>
								<td><input type="radio" name="payrollid" value="<bean:write name="payroll_list_status" property='yearvalcode'/>,<bean:write name="payroll_list_status" property='status'/>,<bean:write name="payroll_list_status" property='monthvalcode'/>,<bean:write name="payroll_list_status" property='locationId'/>,<bean:write name="payroll_list_status" property='yearName'/> "/></td>
								<td><bean:write name="payroll_list_status" property='locationName'/></td>
								<td><bean:write name="payroll_list_status" property='yearval'/></td>
								<td><bean:write name="payroll_list_status" property='monthval'/></td>
								<td><bean:write name="payroll_list_status" property='status'/></td>
								<td><bean:write name="payroll_list_status" property='createdby'/></td>
								<td><bean:write name="payroll_list_status" property='createddate'/></td>
								<td><bean:write name="payroll_list_status" property='createdtime'/></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
						 	
					    <div style="margin-top: 27px;">
						 	<div class='pagebanner' ><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
						 		<span  class='numberOfItem'></span>	
						 		</div><div class='pagination pagelinks'></div>
						 	</div>
						</div>
			
						<div style="clear:both;"></div>
						<%-- </logic:present> --%>
					</div>
				</div>
			
		</form>
	</div>
</body>

</html>
