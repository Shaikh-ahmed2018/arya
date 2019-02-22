<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<title>eCampus Pro</title>
<body>
	<div class="col-md-2 leftmenu">

                         <div class="panel panel-default" style="margin: -1px 0px;">
							<div class="panel-heading leftNav" role="tab" id="headingTwo" style="background: #07AAB9;">
							  <h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#menucollapseTwo"  style="color:#767676" aria-expanded="false" aria-controls="collapseTwo">
								  <h3 class="panel-title active" style="    color: #fff;">Master<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i></h3>
								</a>
								
							  </h4>
							</div>
							<div id="menucollapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
							  <div class="panel-body">
								<ul class="nav nav-pills nav-stacked">
								
								
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBMSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
										<li><a href="LibraryMenu.html?method=libraryLocations" id="sub_module_11_1">Library location</a></li>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	
								
									
									
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBMSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
										<li><a href="LibraryMenu.html?method=categoryType" id="sub_module_11_2">Category Type</a></li>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBMSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
										<li><a href="LibraryMenu.html?method=subCategoryType" id="sub_module_11_3">Sub Category Type</a></li>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	
								
				            <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBMSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
										<li><a href="LibraryMenu.html?method=subCategoryType1" id="sub_module_11_4">Sub Category Type 1</a></li>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
								 <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBMSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
										<li><a href="LibraryMenu.html?method=subCategoryType2" id="sub_module_11_5">Sub Category Type 2</a></li>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							 <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBMSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
										<li><a href="LibraryMenu.html?method=subCategoryType3" id="sub_module_11_6">Sub Category Type 3</a></li>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBMSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="LibraryMenu.html?method=ListpublisherSettings" id="sub_module_11_7">Publisher Settings</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBMSDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="LibraryMenu.html?method=ListsupplierSettings" id="sub_module_11_8">Supplier Settings</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBMSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="LibraryMenu.html?method=ListgeneralSettings" id="sub_module_11_9">General Settings</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
								</ul>
								
							  </div>
							</div>
						  </div>
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default" style="margin: -1px 0px;">

				<div class="panel-heading leftNav" role="tab" id="headingOne"
					style="background: #07AAB9;">

					<h4 class="panel-title" id="beforeparent">
						<a href="#" style="color: #767676">
							<h3 class="panel-title active" style="color: #fff;">
								Library&nbsp;&nbsp;<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>

					</h4>
				</div>
				<div id="menucollapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBRDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="LibraryMenu.html?method=subscribersDetails" id="sub_module_11_24">Subscriber Details </a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
					<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBRDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="LibraryMenu.html?method=LibraryStockEntryDetailsList" id="sub_module_11_25">Stock Entry</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBRDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="LibraryMenu.html?method=BlackListSubscriber" id="sub_module_11_26">Block Listed Subscriber</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>



						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBRDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="LibraryMenu.html?method=GetBookSearch" id="sub_module_11_27">Book Search</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBRDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

								<li><a href="LibraryMenu.html?method=SearchSubscriberDetails" id="sub_module_11_28">Search Subscriber</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>


					<%-- 	<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBRDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="LibraryMenu.html?method=SearchIssueDetails" id="sub_module_11_29">Search Issue Details</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> --%>


						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBRDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="LibraryMenu.html?method=LibraryjournalSubscriptionList" id="sub_module_11_35">Journal Subscription </a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<%-- <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBRDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="LibraryMenu.html?method=JournalEntry" id="sub_module_11_33">Journal Entry </a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBRDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="LibraryMenu.html?method=JournalDailyEntry" id="sub_module_11_34">Journal Daily Entry </a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
 --%>
				<%-- 		<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBRDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="" id="sub_module_11_31">Library Card</a></li>

									</logic:equal>

								</logic:equal>
							</logic:iterate>
						</logic:present> --%>
						
								<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBRDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="LibraryMenu.html?method=barcodeGeneration" id="sub_module_11_32">Barcode</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
	
					<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBRDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">


										<li><a href="LibraryMenu.html?method=TransferSubscriberDetails" id="sub_module_11_10">Transfer Subscribers</a></li>

									</logic:equal>

								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBRDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="LibraryMenu.html?method=LibraryMostWantedDetails" id="sub_module_11_11">Most Wanted</a></li>

									</logic:equal>

								</logic:equal>
							</logic:iterate>
						</logic:present>
					</ul>

					</div>
				</div>
			</div> 
			
	

 <!-- -----------------------------TRANSACTIONS BEGIN-------------------------------------------------------- -->
			 <div class="panel panel-default" style="margin: -1px 0px;">
				<div class="panel-heading leftNav" role="tab" id="headingThree"
					style="background: #07AAB9;">
					<h4 class="panel-title">
						<a href="#"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapseTwo">
							<h3 class="panel-title active" style="color: #fff;">
								Transactions &nbsp;&nbsp;<i
									class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>
						
						

					</h4>
				</div>
				<div id="menucollapseThree" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBTDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

										<li><a href="LibraryMenu.html?method=issues" id="sub_module_11_12">Issued</a>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>


							<!-- <li><a href="adminMenu.html?method=remainderdetails">Remainder Details</a></li> -->

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBTDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="LibraryMenu.html?method=returns" id="sub_module_11_13">Returns</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBTDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="LibraryMenu.html?method=BookReservationDetailslist" id="sub_module_11_14">Reservations</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						</ul>
					</div>
				</div>
			</div>
<!-- -----------------------------TRANSACTIONS END-------------------------------------------------------- -->
			<div class="panel panel-default" style="margin: -1px 0px;">
				<div class="panel-heading leftNav" role="tab" id="headingFour"
					style="background: #07AAB9;">
					<h4 class="panel-title">
						<a href="#"style="color: #767676" aria-expanded="false" aria-controls="collapseFour">
							<h3 class="panel-title active" style="color: #fff;">
								Reports &nbsp;&nbsp;<i
									class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>

					</h4>
				</div>
				<div id="menucollapseFour" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingFour">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">



				<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value"> 
											<li><a href="LibraryMenu.html?method=LibraryHome" id="sub_module_11_15">Over Due Statement</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>  

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value"> 

											<li><a href="LibraryMenu.html?method=StockStatements" id="sub_module_11_16">Stock Statements</a></li>

								</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value"> 

											 <li><a href="LibraryMenu.html?method=ReservationList" id="sub_module_11_17">Reservation List</a></li>

								</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
								<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value"> 
								<li><a href="LibraryMenu.html?method=issuestatements" id="sub_module_11_18">Issue/ReIssue Statements</a></li>
								</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
									<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value"> 
							<li><a href="LibraryMenu.html?method=journalreports" id="sub_module_11_19">Journal Report</a></li>
								</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value"> 
							<li><a href="LibraryMenu.html?method=newarrivalList" id="sub_module_11_20">New Arrival List</a></li>
								</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							

			                <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
							<li><a href="LibraryMenu.html?method=subscriberList" id="sub_module_11_21">Subscriber List</a></li>
								</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

	                    	<%-- <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
							<li><a href="LibraryMenu.html?method=reissueStatements" id="sub_module_11_22">ReIssue Statement</a></li>
								</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
	 --%>
							

		               <%--      <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
						
								<li><a href="LibraryMenu.html?method=searchCardprint" id="sub_module_11_23">Search Card Print</a></li> 
								</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
					</ul>

					</div>
				</div>
			</div>


			 
			
			
<!-- END ID CARD DESIGN PHASE-------------------------------------------------------------------------- -->
			<div class="panel panel-default" style="margin: -1px 0px;">
				<!-- <div class="panel-heading leftNav" role="tab" id="headingThree"
					style="background: #07AAB9;">
					<h4 class="panel-title">
						<a href="#"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapseTwo">
							<h3 class="panel-title active" style="color: #fff;">
								Settings &nbsp;&nbsp;<i
									class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>
						
						

					</h4>
				</div> -->
				<div id="menucollapseThree" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo" >
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">

							


							<!-- <li><a href="adminMenu.html?method=remainderdetails">Remainder Details</a></li> -->

							
							
						
						
					<%-- 	<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="BKUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="LibraryMenu.html?method=categorySettings">Category Settings</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
 --%>					
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="BKUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="LibraryMenu.html?method=groupregistration">Group Registration</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
					
						</ul>


					</div>
				</div>
			</div>

			




<!-- END ID CARD DESIGN PHASE-------------------------------------------------------------------------- -->
	<!-- 		<div class="panel panel-default" style="margin: -1px 0px;">
				<div class="panel-heading leftNav" role="tab" id="headingThree"
					style="background: #07AAB9;">
					<h4 class="panel-title">
						<a href="#"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapseTwo">
							<h3 class="panel-title active" style="color: #fff;">
								Select Library &nbsp;&nbsp;<i
									class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>

					</h4>
				</div>
				<div id="menucollapseThree" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo" style="display: none">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">

				
						</ul>

					</div>
				</div>
			</div>
 -->


<!-- END ID CARD DESIGN PHASE-------------------------------------------------------------------------- -->
		<!-- 	<div class="panel panel-default" style="margin: -1px 0px;">
				<div class="panel-heading leftNav" role="tab" id="headingThree"
					style="background: #07AAB9;">
					<h4 class="panel-title">
						<a href="#"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapseTwo">
							<h3 class="panel-title active" style="color: #fff;">
								Cascade View &nbsp;&nbsp;<i
									class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>

					</h4>
				</div>
				<div id="menucollapseThree" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo" style="display: none">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">

				
						</ul>

					</div>
				</div>
			</div> -->
		</div>


	</div>

	<script>
		
							
							$(document)
									.ready(
											function() {
												
							$("#headingOne").click(function(e) {

								$("#menucollapseOne").toggle();
								$("#menucollapseTwo").slideUp();
								$("#menucollapseThree").slideUp();
								$("#menucollapseFour").slideUp();
							});
							$("#headingTwo").click(function(e) {

								$("#menucollapseTwo").toggle();
								$("#menucollapseOne").slideUp();
								$("#menucollapseThree").slideUp();
								$("#menucollapseFour").slideUp();
							});
							$("#headingThree").click(function(e) {

								$("#menucollapseThree").toggle();
								$("#menucollapseOne").slideUp();
								$("#menucollapseTwo").slideUp();
								$("#menucollapseFour").slideUp();
							});
							$("#headingFour").click(function(e) {
								$("#menucollapseFour").toggle();
								$("#menucollapseThree").slideUp();
								$("#menucollapseOne").slideUp();
								$("#menucollapseTwo").slideUp();
								
							});
						});
	</script>

</body>
</html>


