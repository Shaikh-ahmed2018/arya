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

<title>eCampus Pro</title>

<script type="text/javascript"
	src="JS/backOffice/Inventory/inventory_transaction_countlist.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<style>
.glyphicon:hover {
	cursor: pointer;
}

.download:hover {
	cursor: pointer;
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}
</style>
<style>
.buttons{

vertical-align: -28px;

}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Inventory List Details</span>
			</p>
		</div>


		<%-- <form id="myForm"
			action="" method="post">
			<div class="input-group col-md-4" style="margin: 20px 0px;">
				<input type="text" name="searchname" id="searchname"
					class="form-control" Placeholder="Search......"
					value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'>
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" id="search"
						onclick="myFunction()" value="Submitform">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form> --%>
		<div align="right" class="input-group col-md-4"
			style="margin: 20px 0px;">


			<input type="text" class="form-control" Placeholder="Search......"
				id="searchterm"
				value="<logic:present name="searchTerm"><bean:write name="searchTerm"></bean:write></logic:present>">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>

		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchexamlist"><bean:write name="searchexamlist" />

													</logic:present>'></input>


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

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseOne" style="color: #fff;"><h3
							class="panel-title" style="color: #767676;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Inventory
							List Details
						</h3></a>
					<div class="navbar-right">
						<!-- <a href="adminMenu.html?method=createinventory"><span
							class="glyphicon glyphicon-plus" data-toggle="tooltip"
							data-placement="bottom" title="Add"></span></a> -->
						<!-- <span
							class="glyphicon glyphicon-pencil" id="editexam"
							data-toggle="tooltip" data-placement="bottom" title="Edit"></span> -->

						<!-- <span class="glyphicon glyphicon-trash" id="delete"
							data-toggle="tooltip" data-placement="bottom" title="Delete"></span> -->

						<!-- <img src="images/download.png" class="download" id="iconsimg"
							style="margin-top: 7px;" data-toggle="modal"
							data-target="#myModal" data-toggle="tooltip"
							data-placement="bottom" title="Download"> -->
							<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span>

					</div>
					<script>
						$(document).ready(function() {
							$('[data-toggle="tooltip"]').tooltip();
						});
					</script>
				</div>
				<!-- pop up -->

				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">Download</h4>
							</div>
							<div class="modal-body">
								<span id="excelDownload"><img src="images/xl.png"
									class="xl"></span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
									id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
							</div>

						</div>
					</div>
				</div>

				<div id="collapseOne" class="accordion-body collapse in">
					<div class="panel-body"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">



						
					 <logic:present name="list" scope="request">
							<display:table class="table" pagesize="10"
								name="requestScope.list"
								requestURI="adminMenu.html?method=InventoryList"
								id="allstudent">

								<display:column property="purchase_item_id" sortable="true"
									title="Item Id <img src='images/sort1.png' style='float: right'/>"></display:column>
								<display:column property="item_type" sortable="true"
									title="Item Type <img src='images/sort1.png' style='float: right'/>"></display:column>
								<display:column property="purchase_item_name" sortable="true"
									title="Item Name <img src='images/sort1.png' style='float: right'/>"></display:column>
								<display:column property="total_Quantity" sortable="true"
									title="Total Quantity <img src='images/sort1.png' style='float: right'/>"></display:column>
								<display:column property="issued_quantity" sortable="true"
									title="Issued Quantity<img src='images/sort1.png' style='float: right'/>"></display:column>
								<display:column property="available_quantity" sortable="true"
									title="Availability Quantity <img src='images/sort1.png' style='float: right'/>"></display:column>

								<display:setProperty name="paging.banner.page.separator"
									value=""></display:setProperty>

								<display:setProperty name="paging.banner.placement"
									value="bottom"></display:setProperty>
							</display:table>

						</logic:present> 

					</div>
					<br />
				</div>
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