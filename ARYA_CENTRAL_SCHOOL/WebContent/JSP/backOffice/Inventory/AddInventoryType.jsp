<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<title>Campus School Inventory Type</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Inventory/inventory_list.js"></script>


<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">




<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Add
				Inventory Type</span>
		</p>
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>
		<form action="inventorymenu.html?method=AddInventoryType"
			name="InventoryForm" id="inventory" method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Inventory Type Details
							</h4> </a>
						

						<div class="navbar-right">
							<span id="saveinventory" class="buttons" style="margin-right: -8px ; marging-left:45px"; data-placement="bottom" >Save</span>&nbsp;

							<!--  <span id="savestreamid" class="save2">
							 
							 <img src="images/save.png" data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="Save">
									
							</span>  -->

							<span id="back" class="buttons">Back</span></a>
						</div>
					</div>
					
					<input type="hidden" name="streamId" class="streamidclass"
						id="streamId"
						value='<logic:present name="list"><bean:write name="list" property="streamId" />
													</logic:present>'></input>
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix">

									<input type="hidden" name="inventory_id" id="inventory_id" value='<logic:present name="INVENTORY_TYPE_LIST"><bean:write name="INVENTORY_TYPE_LIST" property="item_type_id" /></logic:present>'></input>

									<input type="hidden" name="hdepartment" id="hdepartment"
									value="<logic:present name="INVENTORY_TYPE_LIST" ><bean:write name="INVENTORY_TYPE_LIST" property="department"/></logic:present>" />
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Inventory
										Type Name </label>
									<div class="col-xs-7">
										<input type="text" name="inventory_type_name"
											id="inventory_type_name" onkeypress="HideError()"
											class="form-control" placeholder="" maxlength="30"
											value='<logic:present name="INVENTORY_TYPE_LIST"><bean:write name="INVENTORY_TYPE_LIST" property="item_type_name" /></logic:present>'></input>
									</div>
								</div>
							
								
								<div class="form-group clearfix">
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
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description
									</label>
									<div class="col-xs-7">
										<textarea style="font-size: 12px; resize: none" rows="6" class="form-control" name="description" id="description"><logic:present name="INVENTORY_TYPE_LIST"><bean:write name="INVENTORY_TYPE_LIST" property="item_type_description" /></logic:present>
									</textarea>
									</div>
								</div>
								<br />

							</div>
						</div>
					</div>


				</div>

			</div>
		</form>
	</div>






</body>
</html:html>