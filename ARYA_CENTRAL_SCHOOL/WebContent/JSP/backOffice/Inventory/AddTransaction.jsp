<!DOCTYPE html>
<html lang="en">
<%@  taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<link href="JQUERY/css/timepicker.css" rel="stylesheet">
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<!-- <script type="text/javascript" src="JS/backOffice/Admin/Latheef.js"></script> -->
<script type="text/javascript"
	src="JS/backOffice/Inventory/InventoryTransactionList.js"></script>


<style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Add Issued Item
				Details</span>
		</p>

		<center>
			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write
									name="successmessagediv" scope="request" /></span></a>
					</div>
				</div>
			</logic:present>


			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>

		</center>



		<form action="transactionymenu.html?method=CreateTransactionDetails" name="InventoryTransactionForm" id="addtransaction"
			method="post" enctype="multipart/form-data">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;<span
								style="margin-top: -4px; position: absolute;">Add
									Purchase Details</span>
							</h4></a>
						
						<div class="navbar-right">
						

							<span id="saveid" class="buttons" style="cursor: pointer" >Save</span>&nbsp; 
								<span id="back" class="buttons" >Back</span></a>
						</div>

					</div>

					


					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6" id="txtstyle"
								style="font-size: 11pt; color: #5d5d5d;">


								



								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Item
										Type</label>
									<div class="col-xs-7">

										<select name="item_type" id="item_type"
											onkeypress="HideError()" class="form-control">
											<option value="">-----Select-----</option>
											<logic:present name="list" scope="request">
												<logic:iterate id="list"
													name="list" scope="request">
													<option
														value="<bean:write name="list" property="item_type_id" />">
														<bean:write name="list"
															property="item_type" />
													</option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								<br />
								
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Item Id</label>
									<div class="col-xs-7">

										<select name="item_id" id="item_id"
											onkeypress="HideError()" class="form-control">
											<option value="">-----Select-----</option>
											<logic:present name="list" scope="request">
												<logic:iterate id="list"
													name="list" scope="request">
													<option
														value="<bean:write name="list" property="purchase_item_id"/>">
														<bean:write name="list"
															property="purchase_item_id" />
													</option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
								<br />
								

								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px; padding: 0;">Requested
										By </label>
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
										<label><input type="radio" class="radio-inline"
											name="requested_by" class="cencession" id="teacher"
											value="Teacher" selected />&nbsp;Teacher&nbsp;&nbsp;&nbsp;</label><label>
											<input type="radio" class="radio-inline" name="requested_by"
											id="student" class="cencession" value="Student" />&nbsp;Student
										</label>
									</div>
								</div>

								<br />


								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Issued
										Date</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" name="issued_date"
											onkeypress="HideError()" id="issued_date"
											class="form-control" placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="issued_date"/></logic:present>" />
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Issued
										By</label>
									<div class="col-xs-7">
										<input type="text" name="issued_by" id="issued_by"
											onkeypress="HideError()" class="form-control" placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="vehicletype"/></logic:present>" />
									</div>
								</div>

								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Return
										By</label>
									<div class="col-xs-7">
										<input type="text" name="returned_by" id="returned_by"
											class="form-control" placeholder="" onkeypress="HideError()"
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="makersname"/></logic:present>" />
									</div>
								</div>

								<br />

								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Returned
										Date</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" name="returned_date"
											onkeypress="HideError()" id="returned_date"
											class="form-control" placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="manufacturerdate"/></logic:present>" />
									</div>
								</div>
							</div>
							
							

							<div class="col-md-6" id="txtstyle"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;"> Item
										Name</label>
										<div class="col-xs-7">
									<select name="item_name" id="item_name"
											onkeypress="HideError()" class="form-control">
											<option value=""></option>
										</select>
								</div>
							</div>
								<br />
								
								
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Item 
										Quantity</label>
									<div class="col-xs-7">
										<input type="number" name="issued_quantity" id="issued_quantity" 
											class="form-control"  required="required"
											 />
									</div>
								</div>

								<br />
								

								

								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Issued To</label>
									<div class="col-xs-7">
										<input type="text" name="issued_to" id="issued_to"
											onkeypress="HideError()" class="form-control" placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="typeofbody"/></logic:present>" />
									</div>
								</div>

								<br />
								 <div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;"> Issue Time</label>
								<div class="col-xs-7">
									<div id="datetimepicker3" class="input-append"	style="width: 100%;">
												<input type="text" data-format="hh:mm:ss" 
													readonly="readonly" name="issuedtime" id="issuedtime"  class="form-control"  onkeypress="HideError()" value="<logic:present name="AcadamicYearPlanDetails"  scope="request"><bean:write name="AcadamicYearPlanDetails" property="issuetime"/></logic:present>"/> <span class="add-on"> 
													<!-- <img src="images/time2.png" width="25"	height="8" /> -->
													<img
											src="./images/time1.jpg" width="30" height="30"
											class="add-on" style="margin-left: 90%; margin-top: -10%;" />
												</span>
											</div>	
								</div>
							</div> 
								<br />
								<%-- <div class="">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Purchased
										By</label>
									<div class="col-xs-7">
										<input type="text" name="settingcapacity" id="settingcapacity"
											onkeypress="HideError()" maxlength="3" class="form-control"
											placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="settingcapacity"/></logic:present>" />
									</div>
								</div> --%>

								<br />
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Return 
										Status</label>
									<div class="col-xs-7">
										<input type="text" name="status" id="status" class="form-control"
											onchange="HideError()" />
											


										
									</div>
								</div>
								
								<br />
								 <div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;"> Return Time</label>
								<div class="col-xs-7">
									<div id="datetimepicker4" class="input-append"	style="width: 100%;">
												<input type="text" data-format="hh:mm:ss" 
													readonly="readonly" name="returntime" id="returntime"  class="form-control"  onkeypress="HideError()" value="<logic:present name="AcadamicYearPlanDetails"  scope="request"><bean:write name="AcadamicYearPlanDetails" property="returntime"/></logic:present>"/> <span class="add-on"> 
													<!-- <img src="images/time2.png" width="25"	height="8" /> -->
													<img
											src="./images/time1.jpg" width="30" height="30"
											class="add-on" style="margin-left: 90%; margin-top: -10%;" />
												</span>
											</div>	
								</div>
							</div> 

							</div>
						</div>
					</div>
				</div>




<div id="overlay" style="display: none;"></div>


					
			</div>



		</form>
	</div>

</body>

</html>
