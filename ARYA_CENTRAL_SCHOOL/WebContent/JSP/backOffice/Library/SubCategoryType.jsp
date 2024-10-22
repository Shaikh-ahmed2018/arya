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
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
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
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>

<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script src="JS/newUI/bootstrap.min.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="JS/backOffice/Library/SubCategoryType.js"></script>

<script type="text/javascript">
	function handle(e) {
		
		var searchname = $("#searchname").val().trim();
		var catcode = $("#cattype").val();
		var subcatcode = $("#subcatname").val();
		var status = $("#classname").val();
		var searchname = $("#searchname").val().trim();	
		if (e.keyCode == 13) {
			$("#selectall").prop("checked", false);
			e.preventDefault(); // Ensure it is only this code that rusn
			
			SearchCategoryTypeList(catcode,subcatcode,status,searchname);
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
						id="pageHeading">Sub Category Type Details</span>
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
						style="color: #767676;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>
						&nbsp;&nbsp;Sub Category Type Details
					</h3>
				</a>

				<div class="navbar-right">
				
				<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBMADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
					<span class="buttons"><a href="LibraryMenu.html?method=addSubCategoryType">Add</a></span>
					</logic:equal>
					</logic:equal>
					</logic:iterate>
					</logic:present>
					
					<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBMUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
						<span id="edit" class="buttons">Edit</span>
						</logic:equal>
						</logic:equal>
						</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBMDEL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
						<span hidden="hidden" id="active"	class="buttons">Active</span>
						</logic:equal>
						</logic:equal>
						</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBMDEL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
						<span id="inactive"	class="buttons">Inactive</span>
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

					<div class="col-md-6"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; padding-bottom: 10px">

						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Category Type Name</label>
							<div class="col-xs-7" >
								<select id="cattype" name="locationnid" class="form-control"
									required>
									<option value="all">ALL</option>
									<logic:present name="SubcategoryDetails1"><logic:iterate id="Location" name="SubcategoryDetails1">
									<option	value="<bean:write name="Location" property="category_id"/>"><bean:write name="Location" property="categoryname" />
									</option></logic:iterate></logic:present>
								</select>
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;"> Status</label>
							<div class="col-xs-7">
								<select class="form-control" onkeypress="HideError()"
									name="classname" id="classname">
									<option value="Active" selected="selected">Active</option>
									<option value="Inactive">Inactive</option>
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
								Category Type Name</label>
							<div class="col-xs-7">
								<select id="subcatname" name="locationnid" class="form-control"
									required>
									<option value="all">ALL</option>
									<logic:present name="SubcategoryDetails2"><logic:iterate id="Location" name="SubcategoryDetails2">
											<option value="<bean:write name="Location" property="category_id"/>"><bean:write name="Location" property="categorytypename" /></option>
										</logic:iterate></logic:present>
								</select>
							</div>
						</div>
						
				
						<div class="form-group clearfix">
						<div class="control-label col-xs-5"></div>
						<div class="col-xs-7">
							<button class="btn btn-info" type="button" id="search"
							style="margin-right: 15px;margin-top: 38px;">
								Search
							</button>
							<button type="reset" class="btn btn-info" id="resetbtn"
							 style="margin-left: -17px;margin-top: 38px;">Reset</button>
						</div>
						</div>

					
					</div>



<div id="dialog"></div>

					<div class="row">
					<table class="table" id="allstudent" width="100%" >
					
					<thead>
						<tr>
							<th><input type='checkbox' name='selectall'  style='text-align:center' id='selectall'  /></th>
							<th>Sub Category Code</th>
							<th>Category Type Name</th>
							<th>Sub Category Type Name</th>
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