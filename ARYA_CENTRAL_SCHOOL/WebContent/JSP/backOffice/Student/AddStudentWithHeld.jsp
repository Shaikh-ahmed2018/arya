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

<link href="CSS/Admin/Admission/StudentNew.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Student/AddStudentWithHeldDetail.js"></script>
<style>
.form-group{
margin-bottom: 5px;}
.save:hover {
	cursor: pointer;
}

.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons{
/* width:500px !important;
    height: 350.4px !important; */
}
#individualstudenttable th:nth-child(2),th:nth-child(3){
  text-align: center;
  }
  .Dialog{
      height: 239.4px;
    width: 100%;
  }
#individualstudenttable td:nth-child(2){
  text-align: center;
  width: 20%;
  }
   #individualstudentcanceltable th:nth-child(1){
  width:5%;
  }
  #individualstudentcanceltable th:nth-child(2),th:nth-child(4){
  text-align: center;
      width: 10%;
  }
  #individualstudentcanceltable td:nth-child(2),td:nth-child(4){
  text-align: center;
  }
  #individualstudentcanceltable th:nth-child(3){
  text-align: left;
  width:30%;
  }
   #individualstudentcanceltable td:nth-child(4){
  text-align:center;
  }
  #individualstudentcanceltable th:nth-child(5){
  text-align:left;
  width:30%;
  }
    #individualstudentcanceltable td:nth-child(5){
  text-align:left;

  }
#studenttable th:nth-child(2),th:nth-child(4),th:nth-child(5){
width:20%;
  }
#studenttable th:nth-child(2),th:nth-child(4),th:nth-child(5){
  text-align: center;
  }

#studenttable td:nth-child(4),td:nth-child(5){
text-align:center;
  }
#allstudent tbody tr{
position: relative;
}
.edit{
position: absolute;
right: 100px;
}
.delete{
position: absolute;
right: 40px;
}
.deletecancel{
position: absolute;

right: 30px;
}
.addtable{

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
.form-control[readonly] {
    background-color: #fff;
    opacity: 1;
}
</style>
<style>
.buttons{

vertical-align:-5px;

}
.navbar-right {
    margin-top: 3px
}
.edit.buttons {
	padding:3px;
    margin-top:-3px;
}
.delete.buttons {
	padding:3px;
    margin-top:-3px
}
.deletecancel.buttons {
	padding:3px;
    margin-top:-3px
}
.form-control[readonly] {
    background-color: #fff;
    opacity: 1;
}

.panel-primary>.panel-heading{
margin-bottom: 0px;
}
form .panel.panel-primary.panel-list{
padding-bottom: 0px;

}    

@media (min-width:320px) and (max-width:767px){
br{
display: none;
}

}
.slideTab{
	padding:10px;
	font-family: Roboto Medium;
    font-size: 14px;
    font-weight: lighter;
}
.fromdate{
display:none;
}
#editsectionid,#editfromdate,#sectionid{
width: 227px;
}

#editcancelcomment,#editcomment,#comment{
    width: 227px;
    height: 82px;
}
#comment{
margin-left:22px;
}
#withhelddate{
}
#back{
top: 4px;}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p><img src="images/addstu.png" /><span id="pageHeading">Student WithHeld Details</span></p>
		
				<div class="panel-body clearfix"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<div id="admissionDialog" class="Dialog" style="display: none">
							<div class="col-md-12">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top:48px;margin-left:31px;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left;">WithHeld</label>
								<div class="col-xs-7">
									<select id="sectionid" name="sectionname" class="form-control" required style="margin-left:22px;">
											<option value="" disabled readonly>Select</option>
											<option value="yes">Yes</option>
											<!-- <option value="cancel">Cancel</option> -->
								    </select>
								</div>
							</div>
							<div class="form-group clearfix entrydate">
								<label for="inputEmail" class="control-label col-xs-5" id="withhelddate"
									style="text-align:left;">WithHeldDate</label>
								<div class="col-xs-7">
									<input class="form-control" onkeypress="HideError()" style="Width:242%;margin-left:23px;"  
									name="entryDate" id="entryDate" class="entryDates">
										
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left;top:25px;">Comment</label>
								<div class="col-xs-7">
									<textarea class="form-control" onkeypress="HideError()" name="comment" id="comment" rows="2" cols="30"></textarea>
										
									</div>
								</div>
								
								<%-- <div class="form-group clearfix fromdate">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Cancel Date</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" readonly="readonly"
											onkeypress="HideError()" name="fromdate" id="Fromdate" value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="issuedDate"/></logic:present>" />
									</div>
								</div>
								
								<div class="form-group clearfix" id="cancelreason">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left;">Cancel Reason</label>
								<div class="col-xs-7">
									<textarea style="border: 1px solid #ccc;"  name="comment" id="cancelcomment" rows="2" cols="30"></textarea>
										
									</div>
								</div> --%>
								</div>
							</div>
					</div>
					 <div id="editDialog" style="display: none">
							<div class="col-md-12">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top:29px;left:118px;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left;left:1px;">WithHeld</label>
								<div class="col-xs-7">
									<select id="editsectionid" name="sectionname" class="form-control" required  style="margin-left:-1px;">
											<option value="" disabled readonly>Select</option>
											<option value="yes">Yes</option>
											<option value="cancel">Cancel</option>
								    </select>
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left;">WithHeld Date</label>
								<div class="col-xs-7">
									<input class="form-control" onkeypress="HideError()" style="Width:227px;" readonly="readonly"
										 name="entryDate" id="editentryDate" class="entryDates">
										
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left;top:25px;left:0px;">Comment</label>
								<div class="col-xs-7">
									<textarea class="form-control" onkeypress="HideError()" name="comment" id="editcomment" rows="2" cols="30"></textarea>
									</div>
								</div>
								<div class="form-group clearfix" id="fromdate1" style="display: none">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height:35px;left:-23px;">Cancel Date</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" readonly="readonly" id="editfromdate"
											onkeypress="HideError()" name="fromdate"  value="" /> <!-- <logic:present name='studentList' scope='request'><bean:write name='studentList' property="issuedDate"/></logic:present> -->
									</div>
								</div>
								<div class="form-group clearfix" id="cancelreason" style="display: none">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left;top:25px;">Cancel Reason</label>
								<div class="col-xs-7">
									<textarea class="form-control" name="comment" onkeypress="HideError()" id="editcancelcomment" rows="2" cols="30" value=""></textarea>
									</div>
								</div>
								</div>
							</div>
					</div> 
					
					
					
					<div id="deleteDialog" style="display: none">
						<p>Are You Sure to Delete?</p>
					</div>
				</div>
	
			<div class="errormessagediv1" style="display: none;" align="center">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 30%;"><span class="validateTips1"></span></a>
				</div>
			</div>
	
		
			<div class="errormessagediv" style="display: none;" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>
			<div id="successmessages" class="successmessagediv" style="display: none;">
				<div class="message-item">
					 <a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>
													
			<!-- chiru -->

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Student WithHeld Detail</h4></a>
						

						<div class="navbar-right">  
							<span class="buttons" id="back">Back</span>
							
						</div>
					</div>
					<logic:iterate id="studentSearchList" name="studentSearchList">
					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
							
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="academicYear" tabindex="1"	onkeypress="HideError()" id="academicYear"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="academicYear"/></logic:present>' />
									</div>
								</div>
								
								
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<input type="text" name="schoolName"  id="schoolName"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="location"/></logic:present>' />
									</div>
								</div>
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" 
										style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentFullName" id="studentFullName"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentFullName"/></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission	No</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="admissionNo"  id="admissionNo"
											onchange="" maxlength="25" readonly="readonly"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentAdmissionNo"></bean:write></logic:present>' />
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Roll No</label>

									<div class="col-xs-7">
										<input type="text" name="studentRollNo" id="studentRollNo"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentrollno"/></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" name="classId" id="classId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="classname"/></logic:present>' />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<input type="text" name="sectionId" tabindex="1" id="sectionId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="sectionnaem"/></logic:present>' />
									</div>
								</div>
								
							</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
												<img id="imagePreview" class="setImage" alt="image" src="#" style="height: 45mm; width: 45mm;">
												<div style="position: absolute;top: 0;height: 160px;">
												<!-- <input type="file" id="studentImageId1" name="studentImage" class="form-control" style=" height: 170px !important;width:170px; opacity: 0; z-index: 99999999;"> -->
												</div>
												
										</div>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student Status</label>
									<div class="col-xs-7">
										<input type="text" name="studentStatusId"  id="studentStatusId"
											maxlength="25" class="form-control" readonly="readonly"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentStatus"/></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">House</label>
									<div class="col-xs-7">
										<input type="text" name="houseId" tabindex="1"	onkeypress="HideError()" id="houseId"
											maxlength="25" class="form-control" readonly="readonly"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="houseName"/></logic:present>' />
									</div>
								</div>
								
								 <input type="hidden" id="withheld" name="withheldId"
									value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="withheldId"/></logic:present>"/>
								
								 <input type="hidden" id="hstudentid" name="studentId"
									value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentId"/></logic:present>"/>
									
								<input type="hidden" id="hacademicYearId" name="academicYearId"
									value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="academicYearId"/></logic:present>"/>
									
								<input type="hidden" id="hschoolNameId" name="schoolNameId"
									value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="locationId"/></logic:present>"/>
								
								<input type="hidden" id="photohiddenid" name="previousImage"
									value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentPhoto" /></logic:present>"> 
							</div>
						</div>
					</logic:iterate>
				</div>
			</div>
					<hr style="height:1px;border:none;color:#333;background-color:#ddd;"/>
					
					<div>
						<div class="slideTab clearfix">
						<div class="tab">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#reportHistory" id="reportHistory">WithHeld Details</a></li>
								<li><a data-toggle="tab" href="#contacts" id="contacts">Cancel</a></li>
								
								<span class="buttons" id="addconfidential" style="position: absolute; right: 38px; margin-top: 6px;">Add</span>
							</ul>
							
												
						<div id="contacts" class="tab-pane">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap">
									<div class="col-md-8" id=div2></div>
									<div id="studenttable"></div>
									<div id="individualstudenttable"></div>	
									<div id="individualstudentcanceltable"></div>
								</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
</html>
