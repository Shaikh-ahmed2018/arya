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

<script type="text/javascript" src=""></script>



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



</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p><img src="images/addstu.png" /><span id="pageHeading">Stock Entry</span></p>
		<div id="admissionDialog" style="display: none">
			<div id="admissionclose" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					

					
				</div>
				<br />
			</div>
		</div>
	
			<logic:present name="successMessage" scope="request">

				<div class="successmessagediv1" style="width:auto !important;display:none" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write	name="successMessage" scope="request" /></span></a>
					</div>
				</div>

			</logic:present>
	
			<logic:present name="errorMessage" scope="request">

				<div class="successmessagediv" align="center" style="display: none;" >
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write name="errorMessage" scope="request" /></span></a>
					</div>
				</div>

			</logic:present>
		
			<logic:present name="duplicateMessage" >

				<div class="successmessagediv" align="center" style="display: none;">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write name="duplicateMessage" scope="request" />
						</span></a>
					</div>

				</div>

			</logic:present>


			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Stock Entry Details</h4></a>
						

						<div class="navbar-right">
						
							<span class="buttons">Save</span>
						
							<span id="back" class="buttons">Back</span></a>
							
						</div>
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
						<div class="row">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Accession No <font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" name="Accession No " tabindex="1"
											maxlength="25" class="form-control" />
											
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Item Type<font color="red">*</font></label>
									<div class="col-xs-7">
										<select type="text" class="form-control" maxlength="20" tabindex="2" >
										<option value=""></option>
										</select>
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Reg Date<font color="red">*</font></label>
									<div class="col-xs-7">
										<select class="form-control" " tabindex="3">
											<option value=""></option>
										</select>
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Book Title <font color="red">*</font></label>
									<div class="col-xs-7">
										<select type="text" readonly="readonly" name="Book Title" class="form-control">
										<option value=" "></option></select>
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Author <font color="red">*</font></label>
									<div class="col-xs-7">
										<select type="text" class="form-control" name="Author" tabindex="4" maxlength="25">
										<option value=""></option></select>
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Category
									</label>
									<div class="col-xs-7">
										<select type="text" class="form-control" name="Category" tabindex="5" maxlength="25">
										<option value=""></option></select>
									</div>
								</div>
								  
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class Description </label>
									<div class="col-xs-7">
										<select name="" class="form-control"> 
										<option value=""></option></select>
									</div>
								</div>
								 
								
								<div class="form-group clearfix" >
									<label for="inputEmail" class="control-label col-xs-5" 
										style="text-align: right; line-height: 35px;">Section Description</label>
									<div class="col-xs-7">
										<select name=""  class="form-control">
											<option value="" ></option>
											
										</select>
									</div>
								</div>
								</div>
								<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
												<img id="imagePreview" class="setImage" alt="image" src="#" style="height: 45mm; width: 45mm;">
												<!-- <div style="position: absolute;top: 0;height: 160px;">
												<input type="file"  name="BookImage" class="form-control" style=" height: 170px !important;width:170px; opacity: 0; z-index: 99999999;">
												</div> -->
												<span id="removeSpanId" class="close" style="position: absolute; top: 0px; right: 124px;color: red;">X</span>
										</div>
									</div>
								</div>
								 
								
								<div class="form-group clearfix" style="padding-top: 0px; margin-top: -12px;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Language</label>
									<div class="col-xs-7">
										<input  type="text" class="form-control" name="Language" tabindex="6"/>
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division Description</label>
									<div class="col-xs-7">
										<input type="text" class="form-control"  tabindex="7" name=" " >
											
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"  
										style="text-align: right; line-height: 35px;">DDC</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" tabindex="8" name="DDC" >
											
									</div>
								</div>
								
								
								</div>
								<div class="col-md-12" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-2" 
										style="text-align: right; line-height: 35px;width:20% ">Publisher<font color="red">*</font></label>	
									<div class="col-xs-10" style="width:80%">
										<select type="text" name=" "  class="form-control">
										<option value=""></option>
										</select>
										
										
									</div>
									
									
								</div>
							</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix" >
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">No of Copies</label>
										<div class="col-xs-7">
										<div class="row">
										<div class="col-xs-8">
									<input type="text" name=" " class="form-control">
									</div>
									<div class="col-xs-4">
									Copies
									</div>
									</div>
									
								</div>
								</div>
								
								<div class="form-group clearfix" >
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Edition</label>
									<div class="col-xs-7">
										<input type="text" name=""  class="form-control"/>
										
									</div>
								</div> 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">ISBN No</label>

									<div class="col-xs-7">
										<input type="text" name=""  class="form-control"/>
											
										
									</div>
								</div>
								 

								<div class="form-group clearfix" >
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Bill NO</label>
										
									<div class="col-xs-7">
										<input  type="text" name="" class="form-control">
										
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Size</label>
									<div class="col-xs-7">
										<input type="text" class="form-control">
										
									</div>
								</div>
								 
								
								<div class="form-group clearfix" >
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Supplied By</label>
									<div class="col-xs-7">
									<input type="text" class="form-control">
											
									</div>
								</div>
							
							<div class="form-group clearfix" >
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Genral Info</label>
									<div class="col-xs-7">
									<input type="text" class="form-control">
											
									</div>
								</div>
								<div class="form-group clearfix">
								  
									<label  for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Current Status
									</label>
									<div class="col-xs-7">
										<select type="text" class="form-control" name=" "
											 maxlength="25" >
											 <option value="Available">Available</option>
											 <option value="unAvailable">unAvailable</option>
											 </select>
									</div>
								</div>
								
							</div>
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Publication Year</label>
									<div class="col-xs-7">
										<input type="text" class="form-control"  tabindex="9"
											name="Publication Year" >
										
									</div>
								</div>
							
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Editor</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="Editor"  maxlength="25"/>
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 18px;">cost
										 </label>
									<div class="col-xs-7">
										<input  type="text" name="cost"  class="form-control"/>
											
									</div>
								</div>
							
								<div class="form-group clearfix">
								
									<label  for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Bill Date</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name=""
											 maxlength="25"/>
									</div>
								</div>
								
								<div class="form-group clearfix">
								  
									<label  for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">No of Pages
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name=" "
											 maxlength="25"  />
									</div>
								</div>
								
								<div class="form-group clearfix">
								  
									<label  for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Content Search
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name=""
											 maxlength="25" />
									</div>
								</div>
								<div class="form-group clearfix">
								  
									<label  for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Shelf No
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name=""
											 maxlength="25" />
									</div>
								</div>
								<div class="form-group clearfix">
								  
									<label  for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Location
									</label>
									<div class="col-xs-7">
										<select type="text" class="form-control" name=""
											 maxlength="25" > <option value=""></option></select>
									</div>
								</div>
								</div>
												
							</div>
						</div>
					</div>
				</div>

	</div>
</html>
								