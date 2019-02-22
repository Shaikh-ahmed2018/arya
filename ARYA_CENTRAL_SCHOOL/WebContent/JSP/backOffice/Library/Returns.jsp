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
<script type="text/javascript" src="JS/backOffice/Library/Returns.js"></script>

<script type="text/javascript" src="">
	
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
#back{
display: none}
#save{
   left:-4px;}
 #othersname{
   padding-left: 15px;
   }
   #hiddenclassId{
    width: 94px;
    margin-left: 11px;
}
#hiddendivissionId{
    width: 95px;
    margin-left: -4px;
}
#hiddendepartment{
  width: 94px;
    margin-left: 11px;
}
#hiddendesignation{
    width: 95px;
    margin-left: -4px;
}

</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" /><span id="pageHeading">Returns</span>
		</p>
		<div id="admissionDialog" style="display: none">
			<div id="admissionclose" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				</div>
				<br />
			</div>
		</div>

		<div class="successmessagediv"
				style="width: auto !important; display: none" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span class="validateTips"></span></a>
				</div>
			</div>

		
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<logic:present name="duplicateMessage">

			<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="duplicateMessage" scope="request" />
					</span></a>
				</div>
			</div>
		</logic:present>
		
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne"
							style="color: #767676;vertical-align: text-top;"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Returns Details
						</h4></a>
					

					<div class="navbar-right">
					     <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBTADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
						<span class="buttons" id="save">Save</span>
						<span class="buttons" id="back" style="margin: 0px 12px 0px 0px;left:4px;">Back</span>
						                 </logic:equal>
						            </logic:equal>
						         </logic:iterate>
						</logic:present>
						<!-- <span class="buttons">Save</span>
						   <span class="buttons">Back</a></span> -->
					</div>
		  		</div>

				<div id="collapseOne" class="panel-collapse collapse in "
					role="tabpanel" aria-labelledby="headingOne">
					<input type="hidden" id="subscriberId"  value="<logic:present name='subid' scope='request'><bean:write name='subid' property="subscriberId"/></logic:present>">
					<input type="hidden" id="hiddenusetype" 
							value="<logic:present name='subscriberType' scope='request'><bean:write name='subscriberType'/></logic:present>"/>	
					<div class="panel-body own-panel">
						<div class="row">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
								
								<div class="form-group">

									<label for="inputPassword" class="control-label col-xs-5" style="left:133px;top:2px;">
										Issued to</label>
									<div class="col-xs-7" id="radiostyle"> <!-- style="margin-top: -1px" -->
										<div>
											<label><span id="teacher"><input type="radio" class="radio-inline" style="top:-2px;"
												class="requested_by" name="requested_by" class="cencession"
												id="teachername" value="Teacher" />&nbsp;Staff&nbsp;&nbsp;&nbsp;
												<input type="hidden" name="TeacherName" value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberName"/></logic:present>"></span>
												</label>
												<label>
											   <span id="studentname"><input type="radio" class="radio-inline"
												name="requested_by" class="requested_by" id="student" style="top:-2px;"
												class="cencession" value="Student" />&nbsp;Student
												<input type="hidden" name="StudentName"
												value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberName"/></logic:present>" />
											</span>
											<span id="othersname"><input type="radio" class="radio-inline"
												name="requested_by" class="requested_by" id="other" style="top:-2px;"
												class="cencession" value="other"/>&nbsp;Other
												<input type="hidden" name="OtherstName"
												value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberName"/></logic:present>" />
											</span>
											</label>

										</div>

									</div>
								</div>
								
								<input type="hidden" class="form-control"
											id="issueId" name="issueId" 
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="issueId"/></logic:present>"/>
                             <input type="hidden" class="form-control" id="hiddenissueId" name="hiddenissueId" value="" />
								<div class="form-group clearfix" id="usertype"
									style="display: none;">
									<label for="inputPassword" class="control-label col-xs-5"
										id="radiotype" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<input type="text" class="form-control ui-autocomplete-input"
											autocomplete="off" name="usertype" id="userType" value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberName"/></logic:present>" />
                                          <input type="hidden" id="hiddenstuid"/>
									</div>
								</div>
								<div class="form-group clearfix admissionno" style="display:none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission No</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											id="admissionno" name="admissionno" 
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="adminssionNo"/></logic:present>"/>
									</div>
								</div>
						
								
					 <div class="col-md-6" id="class"
						style="font-size: 11pt;display: none;left:22%;">
						<div class="form-group">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Standard</label>
							<div class="col-xs-7">
								<input type="text" name="hiddenclassId" id="hiddenclassId" readonly="readonly"  class="form-control"
								value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="className"/></logic:present>"/>
							</div>
						</div>
					</div>
					
					<div class="col-md-6" id="divission"
						style="font-size: 11pt;display: none;left:54px;">
						<div class="form-group">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Divission</label>
							<div class="col-xs-7">
								<input type="text" name="hiddendivissionId" id="hiddendivissionId" readonly="readonly" class="form-control"
								value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="sectionName"/></logic:present>"/>
							</div>
						</div>
					</div>
						
						
						
						<div class="col-md-6" id="department"
						style="font-size: 11pt;display: none;left:22%;">
						<div class="form-group">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Department</label>
							<div class="col-xs-7">
								<input type="text" name="hiddendepartment" id="hiddendepartment" readonly="readonly" class="form-control"
								value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="department"/></logic:present>"/>
							</div>
						</div>
					</div>
					
					<div class="col-md-6" id="designation"
						style="font-size: 11pt;display: none; left:54px;">
						<div class="form-group">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Designation</label>
							<div class="col-xs-7">
								<input type="text" name="hiddendesignation" id="hiddendesignation" readonly="readonly" class="form-control"
								value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="designation"/></logic:present>"/>
							</div>
						</div>
					</div>		
					
				    	<div class="form-group clearfix" id="contactno" style="display:none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">ContactNo</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											id="hiddencontactno" name="hiddencontactno"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="contactNumber"/></logic:present>" />
									</div>
								</div>
								
								<div class="form-group clearfix" id="addr" style="display:none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Address</label>
									<div class="col-xs-7">
										<textarea  readonly="readonly" class="form-control"
											id="hiddenotheraddr" name="hiddenotheraddr"> </textarea>
                                      <input type="hidden" id="otheradderss" value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="otherUserAddr"/></logic:present>"/>
									</div>
								</div> 
								
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Accession
										No <font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<input type="text"  class="form-control ui-autocomplete-input" autocomplete="off"  id="accession_no" name="Accession No" class="form-control"
										value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="accessionNo"/></logic:present>" />
										<input type="hidden" name="accessionName" id="hidden_accessionNo" value=""/>
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">ItemType<font
										color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" onkeypress="HideError()"  class="form-control" id="itemtype" maxlength="20"
										value="<logic:present name="studentList"><bean:write name="studentList" property="itemType"></bean:write></logic:present>" />
									</div>
								</div>



								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Book
										Title <font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" name="Book Title" id="bookTitle" class="form-control"
										value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="bookTitle"/></logic:present>" />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Author <font
										color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control" id="author"name="Author" tabindex="4" maxlength="25"
										value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="author"/></logic:present>" />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right;left:-3px;">Category
									</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control" name="Category" id="category"tabindex="5" maxlength="25"
										value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="category"/></logic:present>" />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right;left:-3px;">DDC</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" tabindex="5" id="ddc" readonly="readonly"
											name="DDC" value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="ddc"/></logic:present>" />

									</div>
								</div>


								<div class="form-group clearfix">

									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right;left:-3px;">Current
										Status </label>
									<div class="col-xs-7">
										<input class="form-control" readonly="readonly" name="currentstatus" tabindex="5"
											id="currentstatus" value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="status"/></logic:present>" />
									</div>
								</div>

							</div>

							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div
											style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
											<img id="imagePreview" class="setImage" alt="image" src="#"
												style="height: 45mm; width: 45mm;">
											<div style="position: absolute; top: 0; height: 160px;">
												<input type="file" name="BookImage" class="form-control" value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='imageUrl'/></logic:present>"
													style="height: 170px !important; width: 170px; opacity: 0; z-index: 99999999;">
											</div>
											<span id="removeSpanId" class="close"
												style="position: absolute; top: 0px; right: 118px; color: red;">X</span>
										</div>
									</div>


								</div>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Shelf No</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="" tabindex="5" id="shelfNo" readonly="readonly"
										 maxlength="25" value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="shelfNno"/></logic:present>" />
									</div>
								</div>
								<div class="form-group clearfix">

									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Location</label>
									<div class="col-xs-7">
										<input type="text" id="locationname" class="form-control" name="locationnid"  readonly="readonly" 
										value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="locationName"/></logic:present>" />
										<input type="hidden" id="hiddenlocation" name="hiddenlocation" value="">
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Book
										Issue Date </label>
									<div class="col-xs-7">
										<input type="text" class="form-control" readonly="readonly"
											onkeypress="HideError()" name="fromdate" id="Fromdate" value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="issuedDate"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Book
										Return Date </label>
									<div class="col-xs-7">
										<input type="text" class="form-control" readonly="readonly"
											onkeypress="HideError()" name="Lastdate" id="Lastdate" value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="returnedDate"/></logic:present>" />
									</div>
								</div>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
</html>
