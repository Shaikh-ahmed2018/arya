<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<title>eCampus Pro</title>

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
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>

<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />

<link href="CSS/newUI/custome.css" rel="stylesheet">
<script src="JS/backOffice/Library/StockEntryStatement.js"></script>

<style>
.modal-body {
	text-align: center;
}
</style>
<style>
.buttons {
	vertical-align: -28px;
}
</style>
</head>



<body>


	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">

		<p>
			<img src="images/addstu.png" />&nbsp;<span>Stock Statement</span>
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
					class="successmessage"></span></a>
			</div>
		</div>

		<p id="parent1" style="display: none;">
			<a href="">Expand all</a>
		</p>
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading" role="tab" id="headingOne">


					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne"
						style="color: #767676; vertical-align: text-top;"><h4
							class="panel-title" id="beforeparent">
							<i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Stock Statement
						</h4></a>


					<div class="navbar-right">

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBRPDWD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<span class="buttons" id="iconsimg" data-toggle="modal"
											data-target="#myModal" data-toggle="tooltip"
											data-placement="bottom" title="Download">Download </span>&nbsp;
							</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						<span class="buttons" id="print">Print</span>

					</div>
					<!-- <script>
						$(document).ready(function() {
							$('[data-toggle="tooltip"]').tooltip();
						});
					</script> -->
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

				<!-- Filters -->

				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body" id="tabletxt" style="padding: 15px;">
						<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									align="right" id="inputnames">School Name</label>
								<div class="col-xs-7">
									<select id="schoolName" name="schoolName" class="form-control">
										<option value="">----------Select----------</option>
										<logic:present name="locationList1">
											<logic:iterate id="name" name="locationList1">
												<option
													value='<bean:write name="name" property="locationId"/>'>
													<bean:write name="name" property="locationName" />
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>


							<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"> Library
									Location 
								</label>
								<div class="col-xs-7">
									<select class="form-control" name="locationName" tabindex="5"
										id="locationName">
										<option value="">----------Select----------</option>
										
									</select>
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">ItemType</label>
								<div class="col-xs-7">
									<select class="form-control" name="itemType" tabindex="5"
										id="itemType">
										<option value="all">----------Select----------</option>
										<%-- 	<logic:present name="itemlist">
										<logic:iterate id="itemType" name="itemlist">
										   <option  value="<bean:write name="itemType" property="itemType"/>"><bean:write name="itemType" property="itemType" /></option>
										</logic:iterate>
										
									</logic:present> --%>
									</select>
								</div>
							</div>

							<!-- <div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">RegDate<font
									color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" class="form-control" tabindex="3" name="regDate" id="regDate"/>
										
								</div>
							</div> -->


							



						</div>

						<div class="col-md-6" id="txtstyle">


							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Book
									Title </label>
								<div class="col-xs-7">
									<select class="form-control" name="bookTitle" tabindex="5"
										id="bookTitle">
										<option value="all">----------Select----------</option>
										<%-- <logic:present name="booklist">
										<logic:iterate id="bookTitle" name="booklist">
										   <option  value="<bean:write name="bookTitle" property="bookTitle"/>"><bean:write name="bookTitle" property="bookTitle" /></option>
										</logic:iterate>
										
									</logic:present> --%>
									</select>
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Author </label>
								<div class="col-xs-7">
									<select class="form-control" name="author" tabindex="5"
										id="author">
										<option value="all">----------Select----------</option>
										<%-- 		<logic:present name="authorlist">
										<logic:iterate id="author" name="authorlist">
										   <option  value="<bean:write name="author" property="author"/>"><bean:write name="author" property="author" /></option>
										</logic:iterate>
										
									</logic:present> --%>
									</select>
								</div>
							</div>

                                       <div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Publisher
								</label>
								<div class="col-xs-7">
									<select class="form-control" name="publisher" tabindex="5"
										id="publisher">
										<option value="all">----------Select----------</option>
										<%-- 	<logic:present name="publicationlist">
										<logic:iterate id="publisher" name="publicationlist">
										   <option  value="<bean:write name="publisher" property="entry_id"/>"><bean:write name="publisher" property="publisher" /></option>
										</logic:iterate>
										
									</logic:present> --%>
									</select>
								</div>
							</div>




							<!-- <div class="col-md-12" align="center">
								<button type="button" class="btn btn-info " id="search">Search</button>
							</div>
 -->
						</div>

						<div class="row">
							<div id="collapseOne" class="panel-collapse collapse in ">
								<div class="panel-body own-panel">
									<div class="row"></div>

									<table class='table' id='allstudent'
										style="width: 100%; display: none">
										<thead>
											<tr>
												<th>Sl.No</th>
												<th>Accession Number</th>
												<th>Item Type</th>
												<th>Reg Date</th>
												<th>Book Title</th>
												<th>Author</th>
												<th>Publisher</th>
												<th>No of Copies</th>
												<th>Location</th>

											</tr>
										</thead>
										<tbody>

										</tbody>

									</table>
								</div>
							</div>

							<div class='pagebanner' class="panel panel-primary clearfix"
								style="display: none">
								<select id='show_per_page'>
									<option value='50'>50</option>
									<option value='100'>100</option>
									<option value='200'>200</option>
									<option value='300'>300</option>
									<option value='400'>400</option>
									<option value='500'>500</option>
								</select> <span class='numberOfItem' style="display: none"></span>
							</div>
							<div class='pagination pagelinks' style="display: none"></div>
						</div>

					</div>
				</div>

			</div>
		</div>
		<!-- Button trigger modal -->
		<span>&nbsp;</span>

		<!-- jQuery -->
		<script src="js/jquery.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="js/bootstrap.min.js"></script>
	</div>
</body>

</html>
