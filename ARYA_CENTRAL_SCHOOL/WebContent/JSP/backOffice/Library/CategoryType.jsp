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

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

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

<script type="text/javascript" src="JS/backOffice/Library/CategoryType.js"></script>
<script type="text/javascript">
	function handle(e) {
		$("input:checkbox").prop('checked', false);
		if (e.keyCode === 13) {
			e.preventDefault(); // Ensure it is only this code that runs
			SearchCategoryType();
		}
	}
</script>

<style>

/* .navbar-right{
width: 18%; 
} */
#editStudent:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
#saveid{
    margin-right:4px;
}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="searchWrap">
			<div id="div2">

				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Category Type Details</span>
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
						&nbsp;&nbsp;Category Type Details
					</h3>
				</a>

				<div class="navbar-right">
				
						<a href="LibraryMenu.html?method=categorytypeexcelfileupload">
						<span id="saveid" class="buttons">Upload
							</Span> </a>

					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="LIBMADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">

									<a href="LibraryMenu.html?method=addCategoryType"> <span
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
					<logic:iterate id="daymap" name="UserDetails"
						property="permissionmap" scope="session">
						<logic:equal value="LIBMDEL" name="daymap" property="key">
							<logic:equal value="true" name="daymap" property="value">

								<span style="display: none;" id="active" class="buttons">Active</span>
							</logic:equal>
						</logic:equal>
					</logic:iterate>
				</div>
			</div>
			<!-- pop up -->
			
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt" style="padding: 15px;">

					<div class="col-md-6"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
						<div class="form-group clearfix ">
							<label for="inputEmail" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Category Type
								Name</label>
							<div class="col-xs-7">
								<select type="text" name="categoryName" tabindex="1"
									id="category" maxlength="25" class="form-control">
									<option value=ALL>ALL</option>
									<logic:present name="categoryDetails">
										<logic:iterate id="category" name="categoryDetails">
											<option
												value="<bean:write name="category" property="categorytypecode"/>">
												<bean:write name="category" property="categorytypename" />
											</option>
										</logic:iterate>
									</logic:present>
								</select>
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Search By</label>
							<div class="col-xs-7">
								<input type="text" name="searchname" id="searchname" class="form-control"
									Placeholder="Search......" onkeypress="handle(event)"
									value="<logic:present name="searchTerm"><bean:write name="searchTerm" /></logic:present>">
							</div>
						</div>

					</div>

					<div class="col-md-6"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
						<div id="dialog"></div>

						<div class="form-group clearfix ">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Status</label>
							<div class="col-xs-7">
								<select type="text" name="Accession No " tabindex="1"
									id="status" maxlength="25" class="form-control">
									<option value=Active checked>Active</option>
									<option value=Inactive checked>Inactive</option>
								</select>
							</div>
						</div>

						<div class="form-group clearfix">
						<div class="control-label col-xs-4"></div>
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



					<%-- <div class="row">
						<logic:present name="categoryDetails" scope="request">
						<display:table class="table" id="allstudent"
							name="categoryDetails"
							requestURI="LibraryMenu.html?method=categoryType"
							decorator="com.centris.campus.decorator.LocationDecorator">
							<display:column  style="text-align:center"	title="<input type='checkbox' name='selectall' style='text-align:center' id='selectall'/>">
							<input type='checkbox' name='select' class='select' style='text-align:center' id='${allstudent.category_id }'  /></display:column>
							
							    <display:column property="categorytypecode" sortable="true"
								title="Category Code<img src='images/sort1.png' style='float: right'/>" />
								<display:column property="categorytypename" sortable="true"
								title="Category Type Name<img src='images/sort1.png' style='float: right'/>" />
								<display:column property="description" sortable="true"
								title="Description<img src='images/sort1.png' style='float: right'/>" />
							    <display:column property="status" sortable="true"
								title="Status<img src='images/sort1.png' style='float: right'/>" />

						</display:table>
						</logic:present>

						
					</div> --%>
					<div class="row">
					<table class="table" id="allstudent" width="100%" >
					
					<thead>
						<tr>
							<th><input type='checkbox' name='selectall'  style='text-align:center' id='selectall'  /></th>
							<th>Category Code</th>
							<th>Category Type Name</th>
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
	</div>
</body>
</html>