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
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript" src="JS/backOffice/Library/StockEntryDetails.js"></script>
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
function  CheckIsNumeric1(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode > 31 && charCode != 45  &&(charCode < 48 || charCode > 57)) {
        
        return false;
    }
    else {
       
        return true;
    }
}

function  CheckIsNumeric2(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode > 31 && charCode != 46  && (charCode < 48 || charCode > 57)) {
     
        return false;
    }
    else {
       
        return true;
    }
}

function  CheckIsAlpaNumeric(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode > 31 && charCode > 32 && charCode != 46  && charCode != 45 && charCode !=39 && charCode !=47 && charCode !=34
    		&&(charCode < 48 || charCode > 57)&& (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122)){
       
        return false;
    }
    else {
       
        return true;
    }
}

function  CheckIsAlphabet(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if ((charCode < 48 || charCode > 57)) {
     
        return false;
    }
    else {
       
        return true;
    }
}




</script>


<style>
.form-group{
margin-bottom: 5px;}
.save:hover {
	cursor: pointer;
}

fieldset { 
	width:512px;
    display: block;
   /*  margin-left: auto;
    margin-right: 2px; */
    margin-bottom: 5px;
    padding-bottom: 0.625em;
    padding-left: 7px;
    padding-right: 0px;
    border: 0.5px solid #ccc;
   
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
  .text2{  margin-left: 211px !important;
    width: 819px !important;
    }
</style>

<style>
.buttons{

vertical-align:-5px;

}
.navbar-right {
    top: -3px;
}
.panel-primary>.panel-heading{
margin-bottom: 0px;
}
form .panel.panel-primary.panel-list{
padding-bottom: 0px;

}    
.back{
display: none;
}

</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p><img src="images/addstu.png" /><span id="pageHeading"><logic:present name="msg"><bean:write name="msg"/></logic:present></span> <span id="pageHeading1" style="display:none">Stock Entry Details</span></p>
		<div id="admissionDialog" style="display: none">
			<div id="admissionclose" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				</div>
				<br />
			</div>
		</div>
		<logic:present name="result" scope="request">
			<div class="successmessagediv" style="width:auto !important;display:none" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write	name="result" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>
  
		

		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
			<logic:present name="fail" scope="request">
		 <div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips"><bean:write name="fail" scope="request" /></span></a>
			</div>
		</div>
			</logic:present>
			
   <form method="post" action="LibraryStockEntry.html?method=saveStockEnteryDetails" enctype="multipart/form-data" name="LibraryStockEntryForm" id="StockEntryDetails" >	
  	<input type="hidden" id="hiddenid" name="enteryid" value='<logic:present name="entryid"><bean:write name="entryid"/></logic:present>'>
 
 <input type="hidden" id="entryhiddenid" name="enteryid" value='<logic:present name="entryid"><bean:write name="entryid"/></logic:present>'>
			<div class="panel-group" id="accordion" >
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676;vertical-align: text-top;"> 
							<h4 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Stock Entry Details</h4></a>
						

						<div class="navbar-right">

							<span class="buttons" id="save">Save</span>&nbsp;
					 	<a href="LibraryMenu.html?method=LibraryStockEntryDetailsList">
						 <span class="buttons" id="back" >Back</span></a>&nbsp;
						 <a href="LibraryMenu.html?method=GetBookSearch">
                         <span class="buttons" class="back" id="backid" style="display:none;top:-23px;margin-left:62%;">Back</span>&nbsp;</a>

						</div>
					</div>
		
					
						<div class="row">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Accession No </label>
									<div class="col-xs-7">
										<input type="text" id="accessionNo"  name="accessionNo" tabindex="1" class="form-control"  onkeypress="return  CheckIsAlphabet(event);" value='<logic:present name="editlist"><bean:write name="editlist" property="accessionNo" /></logic:present>'></input>
											<input type="hidden" id="haccessionNo" name="haccessionNo" value='<logic:present name="editlist"><bean:write name="editlist" property="accessionNo" /></logic:present>'>
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Item Type</label>
									<div class="col-xs-7">
										<input type="text" id="itemType" name="itemType"  class="form-control" value='<logic:present name="editlist"><bean:write name="editlist" property="itemType"/></logic:present>'>
									</div>
									
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Reg Date</label>
										<div class="col-xs-7">
										<input type="text" name="regdate" class="form-control date" readonly="readonly" 
											 id="Fromdate" value='<logic:present name="editlist"><bean:write name="editlist" property="regdate" /></logic:present>'></input>
									 </div>								
									
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Book Title </label>
									<div class="col-xs-7">
										<input type="text" name="bookTitle" class="form-control" id="booktitle" value='<logic:present name="editlist"><bean:write name="editlist" property="bookTitle"/></logic:present>'>
									</div>
									
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Author </label>
									<div class="col-xs-7">
									<!-- 	<select class="form-control" name="author"  id="author"tabindex="4">
										<option value="">---------Select-------</option>
										<option value="AUTH1">Auth1</option>
										<option value="AUTH2">Auth2</option>
										<option value="AUTH3">Auth3</option>
										<option value="AUTH4">Auth4</option>
										
										</select> -->
										<input type="text" class="form-control" name="author"  id="author" value='<logic:present name="editlist"><bean:write name="editlist" property="author"/></logic:present>'>
									</div>
									
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Category
									</label>
									<div class="col-xs-7">
										<select  class="form-control" name="category" tabindex="5" id="category">
										<option value="">----------Select----------</option>
										<logic:present name="categoryDetails">
										<logic:iterate id="category" name="categoryDetails">
										   <option  value="<bean:write name="category" property="categorytypecode"/>"><bean:write name="category" property="categorytypename" /></option>
										</logic:iterate>
										
									</logic:present>
									 </select>
									</div>
									<input type="hidden" id="hiddencategory" value='<logic:present name="editlist"><bean:write name="editlist" property="category"/></logic:present>' />
								</div>
								  
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class Description </label>
									<div class="col-xs-7">
										<select name="classDescription" class="form-control" id="classdesc"> 
										<option value="Null">----------Select----------</option></select>
										
									</div>
									<input type="hidden" id="hiddenclassdescrip" value='<logic:present name="editlist"><bean:write name="editlist" property="classDescription"/></logic:present>'>
								</div>
								 
								
								<div class="form-group clearfix" >
									<label for="inputEmail" class="control-label col-xs-5" 
										style="text-align: right; line-height: 35px;">Section Description</label>
									<div class="col-xs-7">
										<select name="sectionDescription"  class="form-control" id="section">
											<option value="">----------Select----------</option>
											
										</select>
									</div>
									<input type="hidden" id="hiddensection" value='<logic:present name="editlist"><bean:write name="editlist" property="sectionDescription"/></logic:present>'>
									<input type="hidden" id="hiddenimgurl" value='<logic:present name="editlist"><bean:write name="editlist" property="bookPhoto"/></logic:present>'>
										<input type="hidden" id="photohiddenid" name="previousImage" value='<logic:present name="editlist"><bean:write name="editlist" property="bookPhoto"/></logic:present>'>
								</div>
								</div>
								
								<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
												<img id="imagePreview" class="setImage" alt="image"  style="height: 45mm; width: 45mm;">
												<div style="position: absolute;top: 0;height: 160px;">
												<input type="file"  id="bookPhoto"  name="bookimage" class="form-control" style=" height: 170px !important;width:170px; opacity: 0; z-index: 99999999;">
												</div>
												<span id="removeSpanId" class="close" style="position: absolute; top: 0px; right: 100px;color: red;" >X</span>
										</div>
									</div>
								</div>
								 
								<input type="hidden" class="form-control" name="test"  id="test" value='<logic:present name="editlist"><bean:write name="editlist" property="test"/></logic:present>'>
								
								
								<div class="form-group clearfix" style="padding-top: 0px; margin-top: -12px;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Language</label>
									<div class="col-xs-7">
										<select   class="form-control" name="languge" tabindex="6" id="language">
										<option value ="">----------Select----------</option>
										<option value ="English">English</option>
										<option value ="Hindi">Hindi</option>
										<option value ="Tamil">Tamil</option>
										</select>
									</div>
									<input type="hidden" id="hiddenlang" value='<logic:present name="editlist"><bean:write name="editlist" property="languge"/></logic:present>'>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division Description</label>
									<div class="col-xs-7">
										<select name="divisionDescription" class="form-control"  tabindex="7"  id="division">
										<option value="">----------Select----------</option>
										</select>
											
									</div>
									<input type="hidden" id="hiddendivision" value='<logic:present name="editlist"><bean:write name="editlist" property="divisionDescription"/></logic:present>'>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"  
										style="text-align: right; line-height: 35px;">DDC</label>
									<div class="col-xs-7">
										<input type="text" name="ddc" class="form-control" tabindex="8" name="DDC"  readonly id="ddl" value='<logic:present name="editlist"><bean:write name="editlist" property="ddc" /></logic:present>'></input>
											
									</div>
								</div>
								
								
								</div>
								<div class="col-md-12" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-2" 
										style="text-align: right; line-height: 35px;width:20% ">Publisher</label>	
									<div class="col-xs-10" style="width:80%">
										<select  name="publisher"  class="form-control" id="publisher" >
											<option value="">----------Select----------</option>
											<logic:present name="publicationlist">
										    <logic:iterate id="publisher" name="publicationlist">
										   <option  value="<bean:write name="publisher" property="entry_id"/>"><bean:write name="publisher" property="publisher" /></option>
										</logic:iterate>
										</logic:present>
										</select>
										
									</div>
									<input type="hidden" id="hiddenpub" value='<logic:present name="editlist"><bean:write name="editlist" property="publisher"/></logic:present>'>
									
								</div>
							</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix" >
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">No of Copies</label>
										<div class="col-xs-7">
										<div class="row">
										<div class="col-xs-8">
									  <input type = "text" name="no_of_Copies" class="form-control marks" id="nocopy" onkeypress="return CheckIsNumeric(event);" value='<logic:present name="editlist"><bean:write name="editlist" property="no_of_Copies" /></logic:present>'></input>
									</div>
									<div class="col-xs-4">
									Copies
									</div>
									</div>
									
								</div>
								</div>
								
								<div class="form-group clearfix" >
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Cost</label>
									<div class="col-xs-7">
									<input type = "text" name="cost" class="form-control marks" id="cost" onkeypress="return CheckIsNumeric2(event);" value='<logic:present name="editlist"><bean:write name="editlist" property="cost" /></logic:present>'></input>
											
									</div>
								</div> 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Edition</label>

									<div class="col-xs-7">
									
										<input type="text" id="edition" name="edition" class="form-control"  value='<logic:present name="editlist"><bean:write name="editlist" property="edition" /></logic:present>'></input>
											
										
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">ISBN No</label>

									<div class="col-xs-7">
										<input type="text" name="iSBNNo"  id="iSBNNo" class="form-control" value='<logic:present name="editlist"><bean:write name="editlist" property="iSBNNo" /></logic:present>'></input>
											
										
									</div>
								</div>
								 

								<div class="form-group clearfix" >
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Bill No</label>
										
									<div class="col-xs-7">
										<input  type="text" name="billNo" id="billNo" class="form-control" value='<logic:present name="editlist"><bean:write name="editlist" property="billNo" /></logic:present>'></input>
										
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Size</label>
									<div class="col-xs-7">
									<input type = "text" name="size" class="form-control marks" id="size" onkeypress="return CheckIsAlpaNumeric(event);" value='<logic:present name="editlist"><bean:write name="editlist" property="size" /></logic:present>'></input>
										
									</div>
								</div>
								 
								
								<div class="form-group clearfix" >
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Supplied By</label>
									<div class="col-xs-7">
									<%-- <input type="text" class="form-control" id="suppliedby" name="suppliedBy" value='<logic:present name="editlist"><bean:write name="editlist" property="suppliedBy" /></logic:present>'></input> --%>
											<select  name="suppliedBy"  class="form-control" id="suppliedby" >
											<option value="">----------Select----------</option>
											<logic:present name="suplist">
										    <logic:iterate id="suppliedby" name="suplist">
										   <option  value="<bean:write name="suppliedby" property="entry_id"/>"><bean:write name="suppliedby" property="suppliedBy" /></option>
										</logic:iterate>
										</logic:present>
										</select>	
									</div>
									<input type="hidden" id="hiddensup" value='<logic:present name="editlist"><bean:write name="editlist" property="suppliedBy"/></logic:present>'>
								</div>
							
							
							<div class="form-group clearfix" >
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Genral Info</label>
									<div class="col-xs-7">
							         	<textarea rows="3" id="generalinfo" cols="36" name="generalInfo" ><logic:present name="editlist"><bean:write name="editlist" property="generalInfo" /></logic:present></textarea>
											
									</div>
								</div>
							
								
							</div>
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Publication Year</label>
									<div class="col-xs-7">
										<input type = "text" name="publicationYear" class="form-control marks" id="pubyear" onkeypress="return CheckIsNumeric1(event);" value='<logic:present name="editlist"><bean:write name="editlist" property="publicationYear" /></logic:present>'></input>
										
									</div>
								</div>
							
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Total Cost</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="totalCost"  maxlength="25"  id =totalcost readonly value='<logic:present name="editlist"><bean:write name="editlist" property="totalCost" /></logic:present>'></input>
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 18px;">Editor
										 </label>
									<div class="col-xs-7">
										<input type="text"  id="editor" class="form-control" name="editor"  value='<logic:present name="editlist"><bean:write name="editlist" property="editor" /></logic:present>'></input>
									</div>
								</div>
							
								<div class="form-group clearfix">
									<label  for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Bill Date</label>
									<div class="col-xs-7">
										<input type="text" class="form-control bildate" readonly="readonly"
											name="billDate" id="billdate"  value='<logic:present name="editlist"><bean:write name="editlist" property="billDate" /></logic:present>'></input>
									</div>
								</div>
								
								<div class="form-group clearfix">
								  
									<label  for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">No of Pages
									</label>
									<div class="col-xs-7">
					               <input type = "text" name="no_of_Pages" class="form-control marks" id="pages" onkeypress="return CheckIsNumeric(event);"  value='<logic:present name="editlist"><bean:write name="editlist" property="no_of_Pages" /></logic:present>'></input>
			                     	</div>
								</div>
								
								<div class="form-group clearfix">
									<label  for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Content Search
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="contentSearch" name="contentSearch"  maxlength="25"  value='<logic:present name="editlist"><bean:write name="editlist" property="contentSearch" /></logic:present>'></input>
									</div>
								</div>
								<div class="form-group clearfix">
								  
									<label  for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Shelf No
									</label>
									<div class="col-xs-7">
										 <input type = "text" name="shelfNo" class="form-control marks" id="selfno" onkeypress="return CheckIsAlpaNumeric(event);" value='<logic:present name="editlist"><bean:write name="editlist" property="shelfNo" /></logic:present>'></input>
									</div>
								</div>
								<div class="form-group clearfix">
								  
									<label  for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> LIbrary Location
									</label>
									<div class="col-xs-7">
										<select  name="location" class="form-control" id="libloc"> <option value="">----------Select----------</option>
											<logic:present name="librarylocationsDetails">
											<logic:iterate id="librarylocationsDetails" name="librarylocationsDetails">
											<option value="<bean:write name="librarylocationsDetails" property="librarylocid"/>"><bean:write name="librarylocationsDetails" property="libraryLocations"/></option>
											</logic:iterate>
											</logic:present>
										
										</select>
									</div>
									<input type="hidden" id="hiddenloc" value='<logic:present name="editlist"><bean:write name="editlist" property="location"/></logic:present>'>
								</div>
								
									<div class="form-group clearfix">
									<label  for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Current Status
									</label>
									<div class="col-xs-7">
										<select class="form-control" id="currentstatus" name="currentStatus ">
											 <option value="Available">Available</option>
											 <option value="Unavailable">Unavailable</option>
										</select>
									</div>
									<input type="hidden" id="hiddenstatus" value='<logic:present name="editlist"><bean:write name="editlist" property="currentStatus"/></logic:present>'>
								</div>
								</div>

				</div>
            </div>
	
	</form>
	
	</div>
	
</body>
</html>
								