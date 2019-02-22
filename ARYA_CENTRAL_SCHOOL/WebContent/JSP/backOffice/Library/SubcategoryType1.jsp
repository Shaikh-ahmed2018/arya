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


<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
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
<script type="text/javascript" src="JS/backOffice/Library/SubCategoryType1.js"></script>
<script>
function handle(e) {
	if (e.keyCode === 13) {
		$("input:checkbox").prop('checked', false);
		e.preventDefault(); // Ensure it is only this code that rusn
		SearchCategoryType1List();
	}
}
</script>
<style>


#editStudent:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}

.navbar-right{
width: 13%; 
margin-right: -6px;
}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="searchWrap">
			<div id="div2">

				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Sub Category Type1 Details</span>
				</p>
			</div>

		</div>

		<input type="hidden" id="succmsg"
			value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />

		<div id="successmessages" class="successmessagediv"
			style="display: none;">
			<div class="message-item">
				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessage"></span></a>
			</div>
		</div>

		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne">
					<h3 class="panel-title"
						style="color: #767676; vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>
						&nbsp;&nbsp;Sub Category Type1 Details
					</h3>
				</a>

				<div class="navbar-right">

					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="LIBMADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<a href="LibraryMenu.html?method=addSubCatagoryType1"> <span
										class="buttons">Add</span>
									</a>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>

					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="LIBMUPD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span id="edit" class="buttons">Edit</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>

					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="LIBMDEL" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span id="inactive" class="buttons">Inactive</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
					<logic:present name="UserDetails" scope="session">
				<logic:iterate id="daymap" name="UserDetails"
					property="permissionmap" scope="session">
					<logic:equal value="LIBMDEL" name="daymap" property="key">
						<logic:equal value="true" name="daymap" property="value">

							<span style="display: none;" id="active" class="buttons">Active</span>
						</logic:equal>
					</logic:equal>
				</logic:iterate>
			</logic:present>

				</div>
			</div>

			
			<!-- pop up -->



			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt" style="padding: 15px;">
					<div class="row">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; padding-bottom: 10px">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Category
									Type Name</label>
								<div class="col-xs-7">

									<select id="categorytype" name="categorytype"
										class="form-control">
										<logic:present name="CategoryDetails">
											<option value="all">ALL</option>
											<logic:iterate id="Category" name="CategoryDetails">
												<option
													value="<bean:write name="Category" property="categorytypecode"/>">
													<bean:write name="Category" property="categorytypename" />
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Sub
									Category Type Name</label>
								<div class="col-xs-7">

									<select class="form-control" onkeypress="HideError()"
										name="subcategory" id="subcategory">
										<option value="all">ALL</option>
									</select>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Search By</label>
								<div class="col-xs-7">
									<input type="text" name="searchname" id="searchname"
										 class="form-control"
										Placeholder="Search......" onkeypress="handle(event)"
										value="<logic:present name="searchTerm"><bean:write name="searchTerm" /></logic:present>">
									<!-- <span class="input-group-btn">
										<button class="btn btn-default" type="button" id="search"
											style="margin-left: 16px; margin-right: 15px;">
											<i class="fa fa-search"></i>
										</button>
									</span> -->
								</div>
							</div>

						</div>
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; padding-bottom: 10px">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Sub
									Category Type1 Name</label>
								<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()"
										name="subcategorytype1" id="subcategorytype1">
										<option value="all">ALL</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Status</label>
								<div class="col-xs-7">
									<select id="status" name="status" class="form-control">
										<option value="Active">Active</option>
										<option value="Inactive">Inactive</option>
									</select>
								</div>
							</div>
							
							<div class="form-group clearfix">
						<div class="control-label col-xs-6"></div>
						<div class="col-xs-6">
							<button class="btn btn-info" type="button" id="search"
							style="margin-right: 15px;">
								Search
							</button>
							<button type="reset" class="btn btn-info" id="resetbtn"
							 style="margin-left: -17px;">Reset</button>
						</div>
						</div>

							
						</div>

						<div id="dialog"></div>
						<%-- <logic:present name="SubcategoryType1Details" scope="request">
							<display:table class="table" id="allstudent"
								name="SubcategoryType1Details"
								requestURI="LibraryMenu.html?method=subCategoryType1"
								decorator="com.centris.campus.decorator.LocationDecorator">
								<display:column style="text-align:center"
									title="<input type='checkbox' name='selectall' style='text-align:center' id='selectall'  />">
									<input type='checkbox' name='select' class='select'
										style='text-align: center'
										id='${allstudent.subcategorytype1_id }' />
								</display:column>
								<display:column property="subcategorytype1code" sortable="true"
									title="Sub Category Type1 Code<img src='images/sort1.png' style='float: right'/>" />
								<display:column property="categorytypename" sortable="true"
									title="Category Type Name<img src='images/sort1.png' style='float: right'/>" />
								<display:column property="subcategorytypename" sortable="true"
									title="Sub Category Type Name<img src='images/sort1.png' style='float: right'/>" />
								<display:column property="subcategorytype1name" sortable="true"
									title="Sub Category Type1 Name<img src='images/sort1.png' style='float: right'/>" />
								<display:column property="description" sortable="true"
									title="Description<img src='images/sort1.png' style='float: right'/>" />
								<display:column property="status" sortable="true"
									title="Status<img src='images/sort1.png' style='float: right'/>" />
							</display:table>
						</logic:present> --%>
                 
					<table class="table" id="allstudent" width="100%" >
					
					<thead>
						<tr>
							<th><input type='checkbox' name='selectall'  style='text-align:center' id='selectall'  /></th>
							<th>Sub Category Type 1 Code</th>
							<th>Category Type Name</th>
							<th>Sub Category Type Name</th>
							<th>Sub Category Type 1 Name</th>
							<th>Description</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
					</tbody>					
					</table>
			

					</div>
					<div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select>
								<span  class='numberOfItem'></span>	

					</div>
					<div class='pagination pagelinks'></div>
				</div>
			</div>
		</div>
		</div>
</body>
</html>