<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>
<meta charset="utf-8">
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Library/subscriberDetails.js"></script>
<script>
function cleartextboxes() {
	$(".subscriberDetailsWrapper input:text").val("");
	$('.subscriberDetailsWrapper textarea').val("");
	$('.subscriberDetailsWrapper img').attr('src', 'images/profile_photo.png');
	$('.subscriberDetailsWrapper select').val("");
};
</script>

<style>
#allstudent th:nth-child(1) {
	text-align: center;
	width: 70px;
}
.table {
	width: 70%;
	max-width: 100%;
	margin-bottom: 20px;
	margin: 0 auto;
}
input.radio-inline.radio-inline{
    margin-bottom: 3px;
}
.form-control[readonly] {
	background-color: #fff;
	opacity: 1;
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
.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all{
overflow: scroll;
max-height: 300px;
}
#radiostyle {
	margin-top: -2px;
	padding-bottom: 2px;
}
 .navbar-right span {
    margin-right: 0px;
    position: relative;
    top: 4.2px;
    vertical-align: text-top;
}

div[class^="paymentBy"] {
	/* hide div, whose classes begin with paymentBy  */
	display: none;
}
  .back{
  display: none;
  }
  
    
    #update{
    display: none;
   
    }
#updatesubscriber{
  display:none;
  }
  #others, #student, #teacher{
  border:none !important;
  }
/*   .navbar-right span:last-child {
    margin-right: 7px;
} */

#back1
{
    top: -26px;
    left: 59px;}
#reset{
    top: -26px;
    left: 102px;
}
</style>

</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
<p id="heading">
	<img src="images/addstu.png" /><span id="pageHeading">Subscriber
		Details</span>
</p>
<p id="updatesubscriber">
	<img src="images/addstu.png" /><span id="pageHeading">Update Subscriber
		Details</span>
</p>
<div class="panel-body clearfix"
	style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
</div>

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

<div class="panel-group subscriberDetailsWrapper" id="accordion" role="tablist" aria-multiselectable="true">
	<div class="panel panel-primary panel-list">
		<div class="panel-heading" role="tab" id="headingOne">
			
				<a data-toggle="collapse" data-parent="#accordion"
					href="#"
					style="color: #767676;vertical-align: text-top;"><h4 class="panel-title"> <i
			class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Subscriber Details
		</h4></a><!-- collapseOne -->


	<div class="navbar-right">
		<a href="LibraryMenu.html?method=subscriberdetailexcelfileupload">
			<span id="saveid" class="buttons">Upload </Span>
		</a> 
		<span id="save" class="buttons">Save</span> <span id="reset"
			class="buttons" onclick="cleartextboxes()">Reset</span>

		<span id="update" class="buttons">Update</span> 
		<a href="LibraryMenu.html?method=SearchSubscriberDetails">
		<span id="back1" class="buttons back">Back</span> </a>
	</div>
</div>

<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
	<div class="panel-body own-panel">
		<div class="col-md-6"
			style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
			
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5"
					align="right" id="inputnames">School Name</label>
				<div class="col-xs-7">
					<select id="schoolName" name="schoolName"
						class="form-control">
						<option value="">----------Select----------</option>
						<logic:present name="locationList">
						<logic:iterate id="name" name="locationList">
							<option value='<bean:write name="name" property="locationId"/>'>
							<bean:write name="name" property="locationName"/></option>
						</logic:iterate>
					</logic:present>
					</select>
				</div>
			</div>
			
			<input type="hidden" id="hiddenmainloc"  value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="mainLoc"/></logic:present>" />
			
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5"
					align="right" id="inputnames">Library Location</label>
				<div class="col-xs-7">
					<select id="locationName" name="locationName"
						class="form-control">
						<option value="all">----------Select----------</option>
					</select>
				</div>
			</div>
			<input type="hidden" id="hiddenlocation"  value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="location"/></logic:present>" />
			
			<input type="hidden" id="subscriberId"  value="<logic:present name='subId' scope='request'><bean:write name='subId'/></logic:present>" />
            
            <input type="hidden" id="hiddenusetype" value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberType"/></logic:present>"/>

			<div class="form-group">
				<label for="inputPassword" class="control-label col-xs-5"
					style="text-align: center; padding-left: 89px !important;">
					Subscriber Type</label>
				<div class="col-xs-7" id="radiostyle" style="margin-top: -2px;    margin-bottom: 5px;">
					<div3> <label id="teacher"><input type="radio"
						class="radio-inline Gender" name="requested_by" class="cencession"
						id="teacher" value="teacher" />&nbsp;Staff&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					</label> <label id="student"> <input type="radio" 
						class="radio-inline" name="requested_by" id="student" checked="checked"
						class="cencession" value="student" />
						&nbsp;Student&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</label> <label id="others"> <input type="radio" class="radio-inline Gender"
						name="requested_by" id="others" class="" value="others" />&nbsp;Others
					</label> </div3>

				</div>
			</div>

			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5"
					style="text-align: right; line-height: 35px;">Subscriber
					Number</label>
				<div class="col-xs-7">
					<input type="text" class="form-control" name="subscriberNo"
						onkeypress="HideError()" id="subscriberNo" name="subscriberNo" 
						value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberNo"/></logic:present>" />
				</div>
			</div>



			<div class="student">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Admission Number</label>
					<div class="col-xs-7">
						<input type="text" class="form-control"
							onkeypress="HideError()" id="adminssionNo" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="adminssionNo"/></logic:present>" />
					</div>
				</div>
			</div>

			<div class="student">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Student
						Name</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" name="studentName"
							id="studentName" name="subscriberNo" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberName"/></logic:present>" />
						<input type="hidden" class="form-control"
							name="studentIdHidden" id="studentIdHidden" value=""></input>
					</div>
				</div>
			</div>


			<div class="student">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Gender</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" name="gender"
							id="gender" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="gender"/></logic:present>" />
					</div>
				</div>
			</div>

			<div class="student">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Roll
						Number</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" name="rollNo"
							id="rollNo" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="rollNumber"/></logic:present>" />
					</div>
				</div>

			</div>


			<div class="student">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Class</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" name="className"
							id="className" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='className'/></logic:present>" />
						<input type="hidden" class="form-control" name="classId"
							id="classId" />
					</div>
				</div>
			</div>

			<div class="student">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Division</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" name="divisionName"
							id="divisionName" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='sectionName'/></logic:present>" />
						<input type="hidden" class="form-control" name="divisionId"
							id="divisionId" />
					</div>
				</div>
			</div>

			<div class="student">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;"> Contact
						Number</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" name="contactNumber"
							id="contactNumber" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='contactNumber'/></logic:present>" />
					</div>
				</div>
			</div>

			<div class="student">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;"> Email
						ID</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" name="email"
							id="email" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='email'/></logic:present>" />
					</div>
				</div>
			</div>

			<!-- ------------------------------ TEACHER ---------------------------------------------------------- -->

			<div class="teacher">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Staff ID</label>

					<div class="col-xs-7">
						<input type="text" class="form-control"
							onkeypress="HideError()" id="staffRegId" name="staffRegId" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='staffID'/></logic:present>"/>
					</div>
				</div>
			</div>

			<div class="teacher">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Abbrivate
						ID</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" id="abbrId"
							name="abbrId"
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='abbrId'/></logic:present>" />
					</div>
				</div>
			</div>

			<div class="teacher">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Staff
						Name</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" id="staffName"
							name="staffName" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='subscriberName'/></logic:present>"/>
							 <input type="hidden"
							class="form-control" id="staffId" name="staffId" />
					</div>
				</div>
			</div>

			<div class="teacher">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Gender</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" name="gender1"
							id="gender1" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='gender'/></logic:present>"/>
					</div>
				</div>
			</div>

			<div class="teacher">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Department</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" id="department"
							name="department" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='department'/></logic:present>"/>
							 <input type="hidden"
							class="form-control" id="deptId" name="deptId" />
					</div>
				</div>
			</div>

			<div class="teacher">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Designation</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" id="designation"
							name="designation" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='designation'/></logic:present>"/> 
							<input type="hidden"
							class="form-control" id="desigId" name="desigId" />
					</div>
				</div>
			</div>


			<div class="teacher">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Mobile
						Number</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" id="mobileNo"
							name="mobileNo" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='contactNumber'/></logic:present>"/>
					</div>
				</div>
			</div>

			<div class="teacher">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Email ID</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" id="email1"
							name="email1" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='email'/></logic:present>"/>
					</div>
				</div>
			</div>


			<!-- ---------------------------------OTHERS ------------------------------->

			<div class="others">
				<div class="form-group clearfix">
					<label class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Name<font
						color="red">*</font></label>
					<div class="col-xs-7">
						<input type="text" class="form-control"
							onkeypress="HideError()" id="otherUserName"  text
							 name="otherUserName" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='subscriberName'/></logic:present>"/>
					</div>

				</div>
			</div>
			
	

	<div class="others">
				<div class="form-group clearfix">
					<label class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Gender</label>
					<div class="col-xs-7">
					<label id="male"><input type="radio"
						class="radio-inline" name="otherGender" class="cencession" id="maleGender"
						 value="MALE" checked="checked"/>&nbsp;Male&nbsp;&nbsp;&nbsp;&nbsp;

					</label>
					<label id="female"><input type="radio"
						class="radio-inline" name="otherGender" class="cencession" id="femaleGender"
						 value="FEMALE"/>&nbsp;Female&nbsp;&nbsp;&nbsp;&nbsp;

					</label>
					</div>
				</div>
			</div>
			<input type="hidden" id="hiddenOthersType"
			value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="gender"/></logic:present>">

			<div class="others">
				<div class="form-group clearfix">
					<label class="control-label col-xs-5" 
						style="text-align: right; line-height: 35px;">Contact
						Number  <font color="red">*</font>
					</label>
					<div class="col-xs-7">
						<input type="text" size="10" maxlength="10" class="form-control"
							onkeypress="HideError()" id="otherUserContact"
							name="otherUserContact" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="contactNumber"/></logic:present>">
					</div>
				</div>
			</div>

			<div class="others">
				<div class="form-group clearfix">
					<label class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Email ID</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" id="otherUserEmail"
							name="otherUserEmail" 
							value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="email"/></logic:present>"/>
					</div>
				</div>
			</div>
		</div>

		<div class="col-md-6"
			style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
			<div class="student">
				<div class="form-group clearfix" style="height: 87px;">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;"></label>
					<div class="col-xs-7">
						<div
							style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
							<img id="studentImageId1" class="setImage" alt="image" src='./images/profile_photo.png'  
								style="height: 45mm; width: 45mm;">
								<input type="hidden" id="hidenimage" value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='imageUrl'/></logic:present>" />
						</div>
					</div>
				</div>
			</div>

			<div class="teacher">
				<div class="form-group clearfix" style="height: 87px;">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;"></label>
					<div class="col-xs-7">
						<div
							style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
							<img id="teacherTeacher" class="setImage" alt="image" src='images/profile_photo.png'
								style="height: 45mm; width: 45mm;">
						</div>
					</div>
				</div>
			</div>
			<!-- --------------------------------------------------------------------------------------------------------------- -->
			<div class="paymentType">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Deposit
						Type</label>
					<div class="col-xs-7">
						<select name="depositType" id="depositType"
							class="form-control">
							<option value="">Select Payment Mode</option>
							<option value="Cash">By Cash</option>
							<option value="Card">By Card</option>
							<option value="Check">By Check</option>
						</select>
					</div>
				</div>
			</div>
                         <input type="hidden" id="hiddendeposittype" value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='depositType'/></logic:present>"/>

			<div class="paymentByCash">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Enter
						Amount</label>
					<div class="col-xs-7">
						<input type="text" class="form-control numbersOnly"
							id="enterAmount" name="enterAmount" />
					</div>
				</div>
				
				
				
			</div>

			<div class="paymentByCard">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Enter
						Card No</label>
					<div class="col-xs-7">
						<input type="text" class="form-control"
							maxlength="16" id="cardNo" name="cardNo" />

					</div>
				</div>
				
					<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Enter
						Amount Rs.</label>
					<div class="col-xs-7">
						<input type="text" class="form-control"
							maxlength="16" id="cardNoAmount" name="cardNoAmount" />
					</div>
				</div>
			</div>

			<div class="paymentByCheck">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Enter
						Check No</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" id="checkNoAmount"
							name="checkNoAmount" />
					</div>
				</div>
				
					<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Enter
						Amount Rs.</label>
					<div class="col-xs-7">
						<input type="text" class="form-control" id="checkNoAmount"
							name="checkNoAmount" />
					</div>
				</div>
			</div>



			<div class="">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Status</label>
					<div class="col-xs-7">
					
					<select class="form-control" id="status">
					<option value="">----------Select----------</option>
					<option value="PAID">PAID</option>
					<option value="UNPAID">UNPAID</option>
					</select>
						
					</div> 
				</div>
			</div>
			
			<input type="hidden" id="hiddenstatus" value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='status'/></logic:present>"/>

			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-4"
					style="text-align: right; line-height: 35px;">Date</label>
				<div class="col-xs-7">
					<input type="text" name="paymentDate" id="paymentDate"
						readonly="readonly" class="form-control"
						value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='paymentDate'/></logic:present>" />

				</div>
			</div>

			<div class="student">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Address</label>
					<div class="col-xs-7">
						<textarea rows="4" cols="35" id="studentAddress" class="form-control" name="studentAddress"><logic:present name='studentList' scope='request'><bean:write name='studentList' property='otherUserAddr' /></logic:present>
						</textarea>
					</div>
				</div>
			</div>

			<div class="teacher">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Address</label>
					<div class="col-xs-7">
						<textarea rows="4" cols="35" id="teacherAddress" class="form-control" name="teacherAddress"><logic:present name='studentList' scope='request'><bean:write name='studentList' property='presentAddress' /></logic:present>
						</textarea>
					</div>
				</div>
			</div>

			<div class="othersAddr">

				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Address<font
						color="red">*</font></label>
					<div class="col-xs-7">
						<textarea rows="4" cols="35" class="form-control" onkeypress="HideError()" id="otherUserAddr">
						<logic:present name='studentList' scope='request'><bean:write name='studentList' property='otherUserAddr' /></logic:present>
						</textarea>
					</div>
				</div>
			</div>



		<!-- 	<div class="form-group clearfix">
				<label style="text-align: right; hover: line-height: 35px;">
							<a href="#"><u>Library Card Details >>></u></a></label>
						</div> -->

					</div>
				</div>



			</div>
		</div>
	</div>
</div>
</html>



