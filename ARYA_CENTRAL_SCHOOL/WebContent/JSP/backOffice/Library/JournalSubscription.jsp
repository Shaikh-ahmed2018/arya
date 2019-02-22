<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
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
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Library/JournalSubscription.js"></script>
<script type="text/javascript">

function CheckIsNumeric(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode > 31  &&(charCode < 48 || charCode > 57)) {
        
        return false;
    }
    else {
       
        return true;
    }
}


function CheckIsNumeric1(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode > 31 && charCode!= 46 &&(charCode < 48 || charCode > 57)) {
        
        return false;
    }
    else {
       
        return true;
    }
}
</script>






<style>
.form-group {
	margin-bottom: 5px;
}

.save:hover {
	cursor: pointer;
}

fieldset {
	width: 512px;
	display: block;
	/*  margin-left: auto;
    margin-right: 2px; */
	margin-bottom: 5px;
	padding-bottom: 0.625em;
	padding-left: 7px;
	padding-right: 0px;
	border: 1.5px solid #ccc;
}

legend {
	display: inline-block;
	width: auto;
	padding: 0;
	margin-bottom: 0px;
	font-size: 16px;
	line-height: inherit;
	color: #333;
	border: 0;
}

.text2 {
	margin-left: 211px !important;
	width: 819px !important;
}
</style>

<style>
.buttons {
	vertical-align: -5px;
}

.navbar-right {
	top: -3px;
}

.panel-primary>.panel-heading {
	margin-bottom: 0px;
}

form .panel.panel-primary.panel-list {
	padding-bottom: 0px;
}

.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all {
	z-index: 1;
	display: block;
	left: 478px;
	width: 346px;
	height: 300px;
	overflow: scroll;
	position: absolute;
	top: 200px !important;
}

.back {
	display: none;
}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
		<img src="images/addstu.png" /><span id="pageHeading"><logic:present name="msg"><bean:write name="msg"/></logic:present></span></p>
		</p>

		<div class="successmessagediv"
			style="width: auto !important; display: none" align="center">
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
<input type="hidden" id="hiddenid" name="journalId" value='<logic:present name="journalId"><bean:write name="journalId"/></logic:present>'>
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading" role="tab" id="headingOne">
						<a data-toggle="collapse" data-parent="#accordion" id="save11"
							href="#collapseOne"
							style="color: #767676; vertical-align: text-top;"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Add Journal Subscription
							</h4></a>
							
							<a data-toggle="collapse" data-parent="#accordion" id="edit11"
							href="#collapseOne"
							style="color: #767676; vertical-align: text-top; display: none"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Modify Journal Subscription
							</h4></a>

                     <div class="navbar-right">
					<span class="buttons" id="save">Save</span>&nbsp;
					<a href="LibraryMenu.html?method=LibraryjournalSubscriptionList">
					<span class="buttons" id="save">Back</span></a>&nbsp;
				
				</div>
			</div>
            <input type="hidden" id="hidenaccyear"
						value="<logic:present name='accademic_year' scope='request'><bean:write name='accademic_year'/></logic:present>" />
                      <div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; display: none">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Academic
									Year</label>
								<div class="col-xs-7">
									<select id="Acyearid" name="accyear" class="form-control"
										required>
										<!-- <option value="all"></option> -->
										<logic:present name="AccYearList">
											<logic:iterate id="AccYear" name="AccYearList">
												<option
													value="<bean:write name="AccYear" property="accyearId"/>"><bean:write
														name="AccYear" property="accyearname" /></option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>

						</div>

			<div id="collapseOne" class="panel-collapse collapse in "
				role="tabpanel" aria-labelledby="headingOne">


				<div class="panel-body own-panel">
					<div class="row">
                             <div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5 ui-autocomplete-input"
									style="text-align: right; line-height: 35px;">Name</label>
								<div class="col-xs-7">
								
								 <input type="text" name="name" id="categoryname" class="form-control"  value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="name" /></logic:present>'>
								</div>
								
							</div>
							
							
							<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Code
								</label>
								<div class="col-xs-7" >
								<input type="text" name="code" id="code" class="form-control"  value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="code" /></logic:present>'>
								</input>	
								</div>
							</div>
							
								<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Journal Type</label>
								<div class="col-xs-7">
                              <input type="text" id="journaltype" name="journaltype" class="form-control" value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="journaltype" /></logic:present>'> </input>
								</div>
							</div>
							
								<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Publisher</label>
								<div class="col-xs-7">
                              <select  name="publisher" class="form-control" id="publisher" >
								<option value="">----------Select----------</option>
								<logic:present name="publicationlist">
								  <logic:iterate id="publisher" name="publicationlist">
								   <option  value="<bean:write name="publisher" property="entry_id"/>"><bean:write name="publisher" property="publisher" /></option>
								 </logic:iterate>
								  </logic:present>
								 </select>
                            
								</div>
								<input type="hidden" id="hiddenpubname" value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="publisher"/></logic:present>'>
							</div>
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Supplier</label>
								<div class="col-xs-7">
                                <select  name="supplier" class="form-control" id="Supplier" >
								<option value="">----------Select----------</option>
								<logic:present name="supplist">
								  <logic:iterate id="Supplier" name="supplist">
								   <option  value="<bean:write name="Supplier" property="entry_id"/>"><bean:write name="Supplier" property="suppliedBy" /></option>
								 </logic:iterate>
								  </logic:present>
								 </select>
								</div>
								<input type="hidden" id="hiddenname" value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="supplier"/></logic:present>'>
							</div>
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Rate Per Copy</label>
								<div class="col-xs-7">
                            <input type="text" id="ratepercopy" name="ratepercopy" class="form-control" onkeypress="return CheckIsNumeric1(event);"value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="ratepercopy" /></logic:present>'></input>
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">No Of Copies</label>
								<div class="col-xs-7">
                            <input type="text" id="noofcopy" name="numberofcopy" class="form-control"  onkeypress="return CheckIsNumeric1(event);" value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="numberofcopy" /></logic:present>'> </input>
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Department</label>
								<div class="col-xs-7">
                            <input type="text" id="department" name="department" class="form-control" value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="department" /></logic:present>'> </input>
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Other Details</label>
								<div class="col-xs-7">
                            <input type="text" id="otherdetails" name="otherdetails" class="form-control" value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="otherdetails" /></logic:present>'> </input>
								</div>
							</div>
							
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"><input type='checkbox' name='accessionno' class='selectall' id='select' style='text-align: center' /></label>
								<div class="col-xs-7">
                          <label for="inputEmail" class="control-label " 
									style="text-align: right; line-height: 35px;">Accession Number Automatic Genrate</label>
								</div>
							</div>
							<input type="hidden" id="hiddenaccesion" value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="accessionno"/></logic:present>'>
						</div>

						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Registration On
									</label>
								<div class="col-xs-7">
								
									<input type="text" id="dateon" name="dateon" tabindex="1" class="form-control" value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="dateon" /></logic:present>'></input>
								</div>
							</div>
                           </div>

                     <div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
						<fieldset class="fieldset" Style="height:190px;width: 395px; margin-left: 135px;border-radius: 4px;">
								<legend
									style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 16px; color: #767676; margin-left: 56px;">
									Subscription Period:</legend>
							<div class="form-group clearfix" style="margin-top:7%;">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Subscription Period</label>
								<div class="col-xs-3">
                            <input type="text" id="subscription" name="subscriptionperiode" class="form-control"  onkeypress="return CheckIsNumeric1(event);"></input>
								</div>
							<div class="col-xs-4">
								<select  class="form-control" tabindex="2" name="" id="subscriptiondate" >
										<option value="">---Select---</option>
										<option value="Month">Month</option>
										<option value="Year">Year</option>
										
										</select>
								</div>
								<input type="hidden" id="hiddensubperiode" value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="subscriptionperiode"/></logic:present>'>
							</div>
								<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">From Date</label>
								<div class="col-xs-7">
                            <input type="text" id="todate" name="todate" class="form-control" value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="todate" /></logic:present>' ></input>
								</div>
							</div>
							
								<div class="form-group clearfix">

								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Due Date</label>
								<div class="col-xs-7">
							
									<input type="text" id="duedate" name="duedate" tabindex="1" class="form-control" value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="duedate" /></logic:present>' ></input>

								</div>
							</div>
							
							</fieldset>
				
							</div>
							
							 <div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
								<div class="form-group clearfix">

								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Total Amount</label>
								<div class="col-xs-7">
							
									<input type="text" id="totalrate" name="totalamount" tabindex="1" class="form-control" readonly value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="totalamount" /></logic:present>'></input>

								</div>
							</div>
								<div class="form-group clearfix">

								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Annual Rate Per Copy</label>
								<div class="col-xs-7">
							
									<input type="text" id="annualratepercopy" name="annualratepercopy" tabindex="1" class="form-control" readonly value='<logic:present name="journaleditlist"><bean:write name="journaleditlist" property="annualratepercopy" /></logic:present>'></input>

								</div>
							</div>
							</div>
							
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
