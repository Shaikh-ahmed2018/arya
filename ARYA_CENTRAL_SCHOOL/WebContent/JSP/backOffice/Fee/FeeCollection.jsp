<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
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
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
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

<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript" src="/JS/backOffice/Fee/FeeCollection.js"></script>

<head>



<style >
.navbar-right span {
    margin-right: 3px;
    }
.form-control{

width: 100%;
}

</style>
<style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Fee Collection</span>
		</p>
		
		
				<div class="successmessagediv" align="center" style="display: none;">
						<div class="message-item">
							<!-- Warning -->
							<a href="#" class="msg-success bg-msg-succes"><span
								class="validateTips"></span></a>
						</div>
				</div>	

					
				<div class="errormessagediv" align="center" style="display: none;">
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
		
		</center>		

		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Student Fee
							</h4></a>
						
					<div class="navbar-right">
							<span id="saveid" class="buttons"> Save
							</Span>	
							
							<span id="back" class="buttons">Back</a></span>
						
					</div>
					
					</div>	

			<div class="feebody panel-group" id="accordion" role="tablist" aria-multiselectable="true">

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne" align="center">
						<div class="panel-body">
						
						<input type="hidden" id="hyear" value="<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="accYear"/></logic:present>"/>
						<input type="hidden" id="hclass" value="<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="classId"/></logic:present>"/>
						<input type="hidden" id="hterm" value="<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="termid"/></logic:present>"/>
						<input type="hidden" id="hsection" value="<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="sectionId"/></logic:present>"/>

							
							<div class="col-md-6" id="txtstyle">

								<div class="form-group">
									<label class="control-label col-xs-6"
										style="text-align: right; line-height: 35px;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;" >Admission No &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Open Sans Light" id="addmissionNo"><logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="addmissionno"/></logic:present>
										</label>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-xs-6"
										style="text-align: right; font-family: sans-serif; line-height: 35px;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;">Accodamic Year
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Open Sans Light"><logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="accYearname"/></logic:present>
										</label>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-xs-6"
										style="text-align: right; font-family: sans-serif; line-height: 35px;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;">Class-Section
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Open Sans Light"><logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="classname"/></logic:present>-<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="sectionname"/></logic:present>
										</label>
									</div>
								</div>
								</div>

							<div class="col-md-6" id="txtstyle">

								<div class="form-group">
									<label class="control-label col-xs-6"
										style="text-align: right; font-family: sans-serif; line-height: 35px;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;">Student
										Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Open Sans Light"><logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="studentname"/></logic:present>
										</label>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-xs-6"
										style="text-align: right; font-family: sans-serif; line-height: 35px;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;">Period/Term
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Open Sans Light"><logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="term"/></logic:present>
										</label>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-xs-6"
										style="text-align: right; font-family: sans-serif; line-height: 35px;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;">Concession
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Open Sans Light" id="concessionPercent"><logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="concession"/></logic:present>%
										</label>
									</div>
								</div>
								

							</div>


						</div>
						
						
							<logic:present name="FeeCollectionVo" scope="request">

									<table  class="table notPaid" id="allstudent" style="font-family: Open Sans Light;color: #897676;" >
										
										<tr>
											<th class="headth" style="text-align: center;">Sno</th>
											<th class="headth" style="text-align: center;">Fee Name</th>
											<th class="headth" style="text-align: center;">Date Of Collection</th>
											<th class="headth" style="text-align: center;">Total Fee</th>
											<th class="headth" style="text-align: center;">Concession(in %)</th>
											<th class="headth" style="text-align: center;">Fee After Concession</th>
											<th class="headth" style="text-align: center;">Fee Collected</th>
											
											<th class="headth" style="text-align: center;">Outstanding Fee</th>
											
										</tr>
											<logic:iterate name="FeeCollectionVo" property="feeNamelist" id="dateVO">
												
												<tr>
												<input type="hidden" value="<bean:write name="dateVO" property="isconcession" />" class="isconcession"/>
												<input type="hidden" value="<bean:write name="dateVO" property="feecode" />" class="feecode"/>
												<input type="hidden"  value="<bean:write name="dateVO" property="feeAmountArray" />" class="feeBalanceAmount"/>
												<input type="hidden" value="<bean:write name="dateVO" property="feePayingAmountArray" />" class="feePaidAmount"/>
													<td class="sno" ><bean:write name="dateVO" property="sno" /></td>
													<td class="feename"><bean:write name="dateVO" property="feename" /></td>
													<td class="date_of_fee_collection"><input type="text"  name="dateofJoin"  onkeypress="HideError()"  class="form-control dateofJoinId" value=""/></td>
													<td class="actualamount" style="text-align: right;"><input type="text" class="feeAmount form-control" style="margin:-7px 0px;width: 100%; text-align: right;" value="<bean:write name="dateVO" property="actualAmt" />" />
													<input type="hidden" class="hfeeAmount form-control" style="margin:-7px 0px;width: 100%; text-align: right;" value="<bean:write name="dateVO" property="actualAmt" />" />
													</td>
													<td class="concession" style="text-align: right;"><input type="text" class="concession form-control" style="margin:-7px 0px;width: 100%; text-align: right;" value="<bean:write name="FeeCollectionVo" property="concession"/>" /></td>
													<td class="afterconcessionamount" style="text-align: right;"><input type="text" class="consfeeAmount form-control" style="margin:-7px 0px;width: 100%; text-align: right;" value="<bean:write name="dateVO" property="actualAmt" />" /></td>
													<td class="concessionamount" style="text-align: right;"><input type="text" class="feePayingAmount form-control" style="margin:-7px 0px;width: 100%; text-align: right;" value="0.0"/></td>
													<td class="paidamount" style="text-align: right;"><input type="text" class="outStandingAmount form-control" readonly="readonly" style="margin:-7px 0px;width: 100%; text-align: right;" value="<bean:write name="dateVO" property="paidAmt" />" /></td>
												
												</tr>
												
												
												
												
											</logic:iterate>
											<tr style="border-top: 1px solid;border-color: #ddd">
											
											<td colspan="3" align="center" style="text-align: center;">Total Payable</td>
											<td id="tot_actual_amt" style="text-align: right;"><bean:write name="FeeCollectionVo" property="tot_actual_amt" /></td>
											<td id="conse" style="text-align: right;"></td>
											<td id="tot_conse_amt" style="text-align: right;"></td>
											<td id="toatalPayingAmount" style="text-align: right;"><input type="text" readonly="readonly" class="toatalPayingAmount form-control" style="margin:-7px 0px;width: 100%; text-align: right;" value=""/></td>
											
											<input type="hidden" id="htot_concession_amt" value='<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="tot_concession_amt"/></logic:present>'></input>
											
											<td id="tot_paid_amt" style="text-align: right;"><bean:write name="FeeCollectionVo" property="tot_paid_amt" /></td>
											
											<input type="hidden" id="htot_paid_amt" value='<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="tot_paid_amt"/></logic:present>'></input>
											
											</tr>
											
											
											
											<%-- <tr style="border-top: 1px solid;border-color: #ddd">
											
											<td colspan="7" align="center" style="text-align: right;">Total Fees Amount Paid So Far</td>
											<td id="amount_paid_so_far" style="text-align: right;"><bean:write name="FeeCollectionVo" property="amount_paid_so_far" /></td>
											
											</tr> --%>
											<input type="hidden" id="hamount_paid_so_far" value='<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="amount_paid_so_far"/></logic:present>'></input>
																						
											
											<tr style="border-top: 1px solid;border-color: #ddd">
											
											<td colspan="7" align="center" style="text-align: right;">Opening Balance </td>
											<td id="opening_balance" style="text-align: right;"><bean:write name="FeeCollectionVo" property="opening_balance" /></td>
											
											</tr>
											<input type="hidden" id="hopening_balance" value='<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="opening_balance"/></logic:present>'></input>
											
											
											<tr style="border-top: 1px solid;border-color: #ddd">
											
											<td colspan="7" align="center" style="text-align: right;">Current Payment</td>
											<td id="currentpayment" style="text-align: right;"><input type="text" class="currentpayment1 form-control" id="currentpayment1" style="margin:-7px 0px;text-align: right;width: 100%;"  readonly="readonly"></td>
											</tr>
											
											
											<tr style="border-top: 1px solid;border-color: #ddd">
											
											<td colspan="7" align="center" style="text-align: right;">Outstanding Balance After The Current Payment </td>
											<td id="outstanding_amount_after_the_current_payment" style="text-align: right;"></td>
											
											</tr>
										</table>
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				<table  class="table pending" id="allstudent" style="font-family: Open Sans Light;color: #897676;" >
										
										<tr>
											<th class="headth" style="text-align: center;">Sno</th>
											<th class="headth" style="text-align: center;">Fee Name</th>
											<th class="headth" style="text-align: center;">Date Of Collection</th>
											<th class="headth" style="text-align: center;">Total Fee</th>
											<!-- <th class="headth" style="text-align: center;">Concession</th>
											<th class="headth" style="text-align: center;">Fee After Concession</th> -->
											<th class="headth" style="text-align: center;">Fee Collected</th>
											
											<th class="headth" style="text-align: center;">Outstanding Fee</th>
											
										</tr>
											<logic:iterate name="FeeCollectionVo" property="feeNamelist" id="dateVO">
												
												<tr>
												<input type="hidden" value="<bean:write name="dateVO" property="isconcession" />" class="isconcession"/>
												<input type="hidden" value="<bean:write name="dateVO" property="feecode" />" class="feecode"/>
												<input type="hidden"  value="<bean:write name="dateVO" property="feeAmountArray" />" class="feeBalanceAmount"/>
												<input type="hidden" value="<bean:write name="dateVO" property="feePayingAmountArray" />" class="feePaidAmount"/>
												<input type="hidden" class="houtStandingAmount" value='<bean:write name="dateVO" property="outStandingAmountArray"/>'></input>
												<input type="hidden" class="hconcessionpercentArray" value='<bean:write name="dateVO" property="concessionpercentArray"/>'></input>	
													<td class="sno" ><bean:write name="dateVO" property="sno" /></td>
													<td class="feename"><bean:write name="dateVO" property="feename" /></td>
													<td class="date_of_fee_collection"><input type="text"  name="dateofJoin"  onkeypress="HideError()"  class="form-control dateofJoinId" value="<bean:write name="dateVO" property="dateofJoinIdArray"/>"/></td>
													<td class="actualamount" style="text-align: right;"><input type="text" class="feeAmount form-control" style="margin:-7px 0px;width: 100%; text-align: right;" value="" />
													<input type="hidden" class="hfeeAmount form-control" style="margin:-7px 0px;width: 100%; text-align: right;" value="<bean:write name="dateVO" property="feeAmountArray" />" />
													</td>
													<%-- <td class="concession" style="text-align: right;"><input type="text" class="concession form-control" style="margin:-7px 0px;width: 100%; text-align: right;" value="<bean:write name="FeeCollectionVo" property="concession"/>" /></td> --%>
													<%-- <td class="afterconcessionamount" style="text-align: right;"><input type="text" class="consfeeAmount form-control" style="margin:-7px 0px;width: 100%; text-align: right;" value="<bean:write name="dateVO" property="actualAmt" />" /></td> --%>
													<td class="concessionamount" style="text-align: right;"><input type="text" class="feePayingAmount form-control" style="margin:-7px 0px;width: 100%; text-align: right;" value="0.0"/></td>
													<td class="paidamount" style="text-align: right;"><input type="text" class="outStandingAmount form-control" readonly="readonly" style="margin:-7px 0px;width: 100%; text-align: right;" value="<bean:write name="dateVO" property="outStandingAmountArray" />" /></td>
												
												</tr>
												
												
												
												
											</logic:iterate>
											<tr style="border-top: 1px solid;border-color: #ddd">
											
											<td colspan="3" align="center" style="text-align: center;">Total Payable</td>
											<td id="tot_actual_amt" style="text-align: right;"><bean:write name="FeeCollectionVo" property="tot_actual_amt" /></td>
											
											<td id="toatalPayingAmount" style="text-align: right;"><input type="text" readonly="readonly" class="toatalPayingAmount form-control" style="margin:-7px 0px;width: 100%; text-align: right;" value=""/>
											<input type="hidden" id="hamount_paid" value='<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="amount_paid_so_far"/></logic:present>'></input>
											</td>
											
											<input type="hidden" id="htot_concession_amt" value='<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="tot_concession_amt"/></logic:present>'></input>
											
											<td id="tot_paid_amt" style="text-align: right;"><bean:write name="FeeCollectionVo" property="tot_paid_amt" /></td>
											
											<input type="hidden" id="htot_paid_amt" value='<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="tot_paid_amt"/></logic:present>'></input>
											
											</tr>
											
											
											
											<%-- <tr style="border-top: 1px solid;border-color: #ddd">
											
											<td colspan="5" align="center" style="text-align: right;">Total Fees Amount Paid So Far</td>
											<td id="amount_paid_so_far" style="text-align: right;"><bean:write name="FeeCollectionVo" property="amount_paid_so_far" /></td>
											
											</tr> --%>
											<input type="hidden" id="hamount_paid_so_far" value='<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="amount_paid_so_far"/></logic:present>'></input>
																						
											
											<tr style="border-top: 1px solid;border-color: #ddd">
											
											<td colspan="5" align="center" style="text-align: right;">Opening Balance </td>
											<td id="opening_balance" style="text-align: right;"><bean:write name="FeeCollectionVo" property="opening_balance" /></td>
											
											</tr>
											<input type="hidden" id="hopening_balance" value='<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="opening_balance"/></logic:present>'></input>
											
											
											<tr style="border-top: 1px solid;border-color: #ddd">
											
											<td colspan="5" align="center" style="text-align: right;">Current Payment</td>
											<td id="currentpayment" style="text-align: right;"><input type="text" class="currentpayment1 form-control" id="currentpayment1" style="margin:-7px 0px;text-align: right;width: 100%;" readonly="readonly"></td>
											</tr>
											
											
											<tr style="border-top: 1px solid;border-color: #ddd">
											
											<td colspan="5" align="center" style="text-align: right;">Outstanding Balance After The Current Payment </td>
											<td id="outstanding_amount_after_the_current_payment" style="text-align: right;"></td>
											
											</tr>
										</table>
				
				
				
				
				
				
				
				
				
				
				
				
				
					</logic:present>
					
						
					</div>

			
				
			</div>
			
			</div>
					</div>

		</form>
				</div>
			
				
			
	
</body>

</html>
