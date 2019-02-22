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
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<!-- <script type="text/javascript" src="JS/backOffice/Admin/Latheef.js"></script> -->
<script type="text/javascript"
	src="JS/backOffice/Inventory/AddPurchaseDetails.js"></script>

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
			<img src="images/addstu.png" />&nbsp;<span>Add Purchase
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
			
				<logic:present name="errorMessage" scope="request">
				<div class="errormessagediv" >
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
									name="errorMessage" scope="request" />
						</span></a>
				</div>
			</div>
			</logic:present>

		</center>



		<form action="inventorymenu.html?method=CreatingAddorModifyorDelete" id="myForm"
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

							<span id="saveid" class="buttons" style="cursor: pointer" >Save</span> 
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
										style="text-align: right; line-height: 35px;">Item For
										Department</label>
									<div class="col-xs-7">

										<select name="department" id="department"
											onkeypress="HideError()" class="form-control">
											<option value="">-----Select-----</option>
											<logic:present name="DepartmentDetails" scope="request">
												<logic:iterate id="DeoartmentId" name="DepartmentDetails"
													scope="request">
													<option
														value="<bean:write name="DeoartmentId" property="depId"/>">
														<bean:write name="DeoartmentId" property="depName" />
													</option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								<br />
								
								

								
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Item
										Name</label>
									<div class="col-xs-7">
										<input type="text" name="item_name" id="item_name"
											onkeypress="HideError()" class="form-control" placeholder=""
											value="<logic:present name="PURCHASE_TYPE_LIST" ><bean:write name="PURCHASE_TYPE_LIST" property="purchase_item_name"/></logic:present>" />
									</div>
								</div>

								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Total
										Quantity</label>
									<div class="col-xs-7">
										<input type="text" name="total_quantity" id="total_quantity"
											class="form-control" placeholder="" onkeypress="HideError()"
											value="<logic:present name="PURCHASE_TYPE_LIST" ><bean:write name="PURCHASE_TYPE_LIST" property="total_Quantity"/></logic:present>" />
									</div>
								</div>

								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Supplier</label>
									<div class="col-xs-7">
										<input type="text" name="manufacturer" id="manufacturer"
											onkeypress="HideError()" onblur="chassisnovalidation()"
											class="form-control" placeholder=""
											value="<logic:present name="PURCHASE_TYPE_LIST" ><bean:write name="PURCHASE_TYPE_LIST" property="manufacturer"/></logic:present>" />
									</div>
								</div>

								<br />
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Supplier Address</label>
									<div class="col-xs-7">
										<textarea style="font-size: 12px; resize: none" rows="6"
											class="form-control" name="address" id="address"><logic:present name="PURCHASE_TYPE_LIST"><bean:write name="PURCHASE_TYPE_LIST" property="address" /></logic:present></textarea>
									
									</div>
								</div>



							</div>

							<div class="col-md-6" id="txtstyle"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;"> Item
										Type</label>
									<div class="col-xs-7">
										<select name="item_type" id="item_type"
											onkeypress="HideError()" class="form-control">
											<option value=""></option>
										</select>
									</div>
								</div>

								<br />

								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Item Id</label>
									<div class="col-xs-7">
										<input type="text" name="item_id" id="item_id"
											onkeypress="HideError()" class="form-control" placeholder=""
											value="<logic:present name="PURCHASE_TYPE_LIST" ><bean:write name="PURCHASE_TYPE_LIST" property="purchase_item_id"/></logic:present>" />
									</div>
								</div>

								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Purchased
										Date </label>
									<div class="col-xs-7">
										<input type="text" placeholder="" onkeypress="HideError()" readonly="readonly"
											name="purchased_date" id="purchased_date"
											 class="form-control"
											value='<logic:present name="PURCHASE_TYPE_LIST"><bean:write name="PURCHASE_TYPE_LIST" property="purchased_date"></bean:write></logic:present>' />
									</div>
								</div>
	
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Purchased
										By</label>
									<div class="col-xs-7">
										<input type="text" name="purchased_by" id="purchased_by"
											onkeypress="HideError()" class="form-control"
											placeholder=""
											value="<logic:present name="PURCHASE_TYPE_LIST" ><bean:write name="PURCHASE_TYPE_LIST" property="purchased_by"/></logic:present>" />
									</div>
								</div>

								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Contact
										No</label>
									<div class="col-xs-7">
										<input type="text" name="contact_number" id="contact_number" maxlength="11"
											onkeypress="HideError()" class="form-control" placeholder=""
											value="<logic:present name="PURCHASE_TYPE_LIST" ><bean:write name="PURCHASE_TYPE_LIST" property="contact_no"/></logic:present>" />
									</div>
								</div>

							</div>

						</div>
					</div>
				</div>


				<input type="hidden" name="purchase_id" id="hpurchase_id"
					value="<logic:present name="PURCHASE_TYPE_LIST" ><bean:write name="PURCHASE_TYPE_LIST" property="purchase_id"/></logic:present>" />

				<input type="hidden" name="hdepartment" id="hdepartment"
					value="<logic:present name="PURCHASE_TYPE_LIST" ><bean:write name="PURCHASE_TYPE_LIST" property="department"/></logic:present>" />

				<input type="hidden" name="hitem_type" id="hitem_type"
					value="<logic:present name="PURCHASE_TYPE_LIST" ><bean:write name="PURCHASE_TYPE_LIST" property="item_type"/></logic:present>" />

			</div>



		</form>
	</div>

</body>

</html>
