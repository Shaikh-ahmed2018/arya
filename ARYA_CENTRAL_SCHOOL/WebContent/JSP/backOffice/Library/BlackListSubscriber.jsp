<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<meta charset="utf-8">
<link rel="stylesheet"href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
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
<script type="text/javascript" src="JS/backOffice/Library/BlocklistSubscriber.js"></script>
<script>
function cleartextboxes(){ 
	 $("input:text").val("");
	 $('select').val("all");
	 $('textarea').val("");
	// $('#imagePreview').removeProp('src');
	 $('#imagePreview').attr('src','./images/profile_photo.png');
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
.form-control[readonly] {
    background-color: #fff;
    opacity: 1;
}

.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all{
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
div[class^="paymentBy"]{   /* hide div, whose classes begin with paymentBy  */
  display: none; 
}
/*   .form-control{
  text-transform: uppercase;
  } */
 #otherblck tbody,#otherblck tbody td:nth-child(6){
 border:none;
 } 
  #otherblck tbody td{
 border:1px solid #dedede;
 }
   #staff tbody td{
  border:1px solid #dedede;
  } 
  #staff tbody,#staff tbody td:nth-child(6){
  border:none;
  } 
#student tbody td{
 border:1px solid #dedede;
}
#student tbody,#student tbody td:nth-child(7){
border:none;
}
.navbar-right{
top:-3px;
}
#allstudent tbody td{
border:1px solid #dedede
}
#allstudent tbody,#allstudent tbody td:last-child{
border:none;
}
#allstudent tbody td:last-child:hover{
background-color: transparent !important;
}
#allstudent thead th:last-child{
	background-color: transparent !important;
}
 #allstudent thead tr{
border: 1px solid #dedede;
}
 #allstudent thead tr,#allstudent thead tr th:last-child{
border:none;
} 




#allstudent tr:hover, .allstudent tr:hover, #allstudents tr:hover{
background:transparent !important;
}
#allstudent tr:hover, .allstudent tr:hover, #allstudents tr:hover {
    background:transparent;
    cursor: pointer;
    }
#allstudent tr td:last-child, .allstudent tr td:last-child, #allstudents tr td:last-child{
background:transparent;
}
#allstudent tr:nth-child(n),.allstudent tr:nth-child(n),#allstudents tr:nth-child(n) {
	background-color: transparent;
}

#allstudent tr:nth-child(2n),.allstudent tr:nth-child(2n),#allstudents tr:nth-child(2n) {
	background-color: transparent;
}
#allstudent tr:nth-child(n) td,.allstudent tr:nth-child(n) td,#allstudents tr:nth-child(n) td {
	background-color: #F2FAFC;
}

#allstudent tr:nth-child(2n) td:last-child,.allstudent tr:nth-child(2n) td:last-child,#allstudents tr:nth-child(2n) td:last-child {
	background-color: transparent;
}
#allstudent tr:nth-child(n) td:last-child,.allstudent tr:nth-child(n) td:last-child,#allstudents tr:nth-child(n) td:last-child {
	background-color: transparent;
}
#allstudent tr:nth-child(2n) td,.allstudent tr:nth-child(2n) td,#allstudents tr:nth-child(2n) td {
	background-color: #FFFFFF;
}
</style>

</head>
<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
<div id="dialog"></div>
<p><img src="images/addstu.png" /><span id="pageHeading">Block Listed Subscriber</span></p>
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

	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
		<div class="panel panel-primary panel-list">
			<div class="panel-heading" role="tab" id="headingOne">
				
					<a data-toggle="collapse" data-parent="#accordion" href="#" style="color: #767676; vertical-align: text-top;"> 
	<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Subscriber Details
	</h4></a>


<div class="navbar-right">

<logic:present name="UserDetails" scope="session">
		 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
			<logic:equal value="LIBRADD" name="daymap" property="key">
				<logic:equal value="true" name="daymap" property="value">	
				<span id="block" class="buttons">Block</span>
				</logic:equal>
			</logic:equal>
		</logic:iterate>
	</logic:present>
	
	<logic:present name="UserDetails" scope="session">
		 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
			<logic:equal value="LIBRADD" name="daymap" property="key">
				<logic:equal value="true" name="daymap" property="value">	
			       <span id="reset" class="buttons"  onclick="cleartextboxes();">Reset</span>
				</logic:equal>
			</logic:equal>
		</logic:iterate>
	</logic:present>
	</div>
</div>

<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
	<div class="panel-body own-panel">
		<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
	
	
		<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5"
					align="right" id="inputnames">School Name</label>
				<div class="col-xs-7">
					<select id="schoolName" name="schoolName"
						class="form-control" onchange="HideError()">
						<option value="">----------Select----------</option>
						<logic:present name="locationList">
						<logic:iterate id="name" name="locationList">
							<option value='<bean:write name="name" property="locationId"/>'><bean:write name="name" property="locationName"/></option>
						</logic:iterate>
					</logic:present>
					</select>
				</div>
			</div>
	
<div class="form-group clearfix">
			<label for="inputPassword" class="control-label col-xs-5" align="right" 
				id="inputnames">Library Location</label>
			<div class="col-xs-7">
					<select id="locationName" onchange="HideError()" name="locationName" class="form-control">
					<option value="">----------Select----------</option>
				</select>
			</div>
		</div> 
	
		
		
		
<!-- for autocomplete code -->					
								
<div class="form-group clearfix" id="usertype"
									style="display: none;">
			<label for="inputPassword" class="control-label col-xs-5"
				id="radiotype" style="text-align: right; line-height: 35px;"></label>
			<div class="col-xs-7">
				<input type="text" class="form-control ui-autocomplete-input" autocomplete="off" name="usertype" id="userType">

			</div>
		</div>
		
		
			<div class="form-group">
		
			<label for="inputPassword" class="control-label col-xs-5"
				style="text-align: center; padding-left: 89px !important;"> Subscriber Type</label>
			<div class="col-xs-7" id="radiostyle" style="margin-top: -2px;margin-bottom: 7px;">
			<div>
			<label><input type="radio" class="radio-inline"
					name="requested_by" class="cencession" id="teacher"
					value="teacher"  checked="checked" />&nbsp;Staff&nbsp;&nbsp;&nbsp;&nbsp;
				</label>
				<label>
					<input type="radio"  class="radio-inline" name="requested_by"
					id="student" class="cencession" value="student" />&nbsp;Student&nbsp;&nbsp;&nbsp;&nbsp;
				</label>
				<label>
					<input type="radio"  class="radio-inline" name="requested_by"
					id="others" class="" value="others" />&nbsp;Others
				</label>
			</div>
				
			</div>
		</div>
		
	
		<div class="form-group clearfix">
			<label for="inputPassword" class="control-label col-xs-5"
				style="text-align: right; line-height: 35px;">Subscriber Number</label>
		
			<!-- 	<input type="text" class="form-control" name="subscriberNo"  onkeypress="HideError()" id="subscriberNo" name="subscriberNo"/> -->
			<div class="col-xs-7">
					<select id="subscriberNo" name="subscriberNo" class="form-control">
					<option val="">----------select----------</option>
				</select>
			</div>
		</div>
		
		
	<div class="student"> 
	<div class="form-group clearfix">
	<label for="inputPassword" class="control-label col-xs-5"style="text-align: right; line-height: 35px;">Admission Number</label>
		<div class="col-xs-7">
		<input type="text" class="form-control"  onkeypress="HideError()"   id="adminssionNo"/>
			</div>
	</div>
	</div>
	
		<div class="student">
		<div class="form-group clearfix">
			<label for="inputPassword" class="control-label col-xs-5"style="text-align: right; line-height: 35px;">Student Name</label>
			<div class="col-xs-7">
				<input type="text" class="form-control" name="studentName"   id="studentName" name="subscriberNo"/>
				<input type="hidden" class="form-control" name="studentIdHidden" id="studentIdHidden"

				value=""></input>
			</div>
		</div>
		</div>


	<div class="student">	
	<div class="form-group clearfix">
	<label for="inputPassword" class="control-label col-xs-5"style="text-align: right; line-height: 35px;">Gender</label>
		<div class="col-xs-7">
		<input type="text" class="form-control" name="gender"   id="gender" />
	</div>
	</div>
	</div>
	
	<div class="student">
	<div class="form-group clearfix">
			<label for="inputPassword" class="control-label col-xs-5"
				style="text-align: right; line-height: 35px;">Roll Number</label>
			<div class="col-xs-7">
			<input type="text" class="form-control" name="rollNo"  id ="rollNo" />
			</div>
		</div>
		</div>
		
	
	<div class="student">
	<div class="form-group clearfix">
	<label for="inputPassword" class="control-label col-xs-5"style="text-align: right; line-height: 35px;">Class</label>
		<div class="col-xs-7">
			<input type="text" class="form-control" name="className" id="className"   />
			<input type="hidden" class="form-control" name="classId" id="classId" />
			</div>
	</div>
	</div>
		
	<div class="student">
	<div class="form-group clearfix">
	<label for="inputPassword" class="control-label col-xs-5"style="text-align: right; line-height: 35px;">Division</label>
		<div class="col-xs-7">
			<input type="text" class="form-control" name="divisionName"  id ="divisionName" />
			<input type="hidden" class="form-control" name="divisionId"  id ="divisionId" />
			</div>
	</div>
	</div>
	
	<div class="student">
	<div class="form-group clearfix">
			<label for="inputPassword" class="control-label col-xs-5"
				style="text-align: right; line-height: 35px;"> Contact Number</label>
			<div class="col-xs-7">
			<input type="text" class="form-control" name="contactNumber"  id ="contactNumber" />
			</div>
		</div>
		</div>
	
	<div class="student">
	<div class="form-group clearfix">
			<label for="inputPassword" class="control-label col-xs-5"
				style="text-align: right; line-height: 35px;"> Email ID</label>
			<div class="col-xs-7">
			<input type="text" class="form-control" name="email"  id ="email" />
			</div>
		</div>
		</div>
	

	

		

<!-- ------------------------------ TEACHER ---------------------------------------------------------- -->

<div class="teacher">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Staff ID</label>

			<div class="col-xs-7">
				<input type="text" class="form-control" onkeypress="HideError()" id="staffRegId" name="staffRegId"  />
			</div>
		</div>
		</div>
		
	<div class="teacher"> 
	<div class="form-group clearfix">
	<label for="inputPassword" class="control-label col-xs-5"style="text-align: right; line-height: 35px;">Abbrivate ID</label>
		<div class="col-xs-7">
			<input type="text" class="form-control" id="abbrId" name="abbrId"  />
			</div>
	</div>
	</div>
	
	<div class="teacher">	
	<div class="form-group clearfix">
	<label for="inputPassword" class="control-label col-xs-5"style="text-align: right; line-height: 35px;">Staff Name</label>
		<div class="col-xs-7">
			<input type="text" class="form-control" id="staffName" name="staffName"  />
			<input type="hidden" class="form-control" id="staffId" name="staffId"  />
	</div>
	</div>
	</div>
	
	<div class="teacher">	
	<div class="form-group clearfix">
	<label for="inputPassword" class="control-label col-xs-5"style="text-align: right; line-height: 35px;">Gender</label>
		<div class="col-xs-7">
			<input type="text" class="form-control" name="gender1" id="gender1"    />
	</div>
	</div>
	</div>
	
	<div class="teacher">
	<div class="form-group clearfix">
	<label for="inputPassword" class="control-label col-xs-5"style="text-align: right; line-height: 35px;">Department</label>
		<div class="col-xs-7">
			<input type="text" class="form-control" id="department" name="department"  />
			<input type="hidden" class="form-control" id="deptId" name="deptId"  />
			</div>
	</div>
	</div>
		
	<div class="teacher">
	<div class="form-group clearfix">
	<label for="inputPassword" class="control-label col-xs-5"style="text-align: right; line-height: 35px;">Designation</label>
		<div class="col-xs-7">
			<input type="text" class="form-control" id="designation" name="designation"  />
			<input type="hidden" class="form-control" id="desigId" name="desigId"  />
			</div>
	</div>
	</div>
	

<div class="teacher">
	<div class="form-group clearfix">
	<label for="inputPassword" class="control-label col-xs-5"style="text-align: right; line-height: 35px;">Mobile Number</label>
			<div class="col-xs-7">
			<input type="text" class="form-control" id="mobileNo" name="mobileNo"  />
				</div>
		</div>
		</div>
		
<div class="teacher">
		<div class="form-group clearfix">
		<label for="inputPassword" class="control-label col-xs-5"style="text-align: right; line-height: 35px;">Email ID</label>
		<div class="col-xs-7">
		<input type="text" class="form-control" id="email1" name="email1"  />
			</div>
	</div>
	</div> 


<!-- ---------------------------------OTHERS ------------------------------->	

<div class="others">
	<div class="form-group clearfix">
		<label  class="control-label col-xs-5" style="text-align: right; line-height: 35px;" >Name<font color="red">*</font></label>
		<div class="col-xs-7">
	<input type="text" class="form-control"  onkeypress="HideError()" id="otherUserName" name="otherUserName"  />
		</div>
	
	</div>
</div>

<div class="others">
	<div class="form-group clearfix">
		<label  class="control-label col-xs-5" style="text-align: right; line-height: 35px;" >Gender</label>
		<div class="col-xs-7">
	 <input type="text" readonly class="form-control" id="otherUserGender" name="otherUserGender"  /> 
		</div>
	
	</div>
</div>

<div class="others">
 <div class="form-group clearfix">
 	<label  class="control-label col-xs-5" style="text-align: right; line-height: 35px;" >Contact Number<font color="red">*</font></label>
		<div class="col-xs-7">
		<input type="text"  maxlength="10" class="form-control" onkeypress="HideError()" id="otherUserContact" name="otherUserContact"  />
 </div>
</div>
</div>

<div class="others">
 <div class="form-group clearfix">
 	<label  class="control-label col-xs-5" style="text-align: right; line-height: 35px;" >Email ID</label>
		<div class="col-xs-7">
		<input type="text" class="form-control" id="otherUserEmail" name="otherUserEmail"  />
 </div>
</div>
</div>

			
	</div>
	
	<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
	<div class="student">
		<div class="form-group clearfix" style="height: 87px;">
			<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;"></label>
			<div class="col-xs-7">
				<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
						<img id="imagePreview" class="setImage" alt="image" src="images/profile_photo.png" style="height: 45mm; width: 45mm;">
				</div>
			</div>
		</div>
		</div>
		
		<div class="teacher">
		<div class="form-group clearfix" style="height: 87px;">
			<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;"></label>
			<div class="col-xs-7">
				<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
						<img id="imagePreviewstff" class="setImage" alt="image" src="images/profile_photo.png" style="height: 45mm; width: 45mm;">
				</div>
			</div>
		</div>
		</div>
		
		

<!-- --------------------------------------------------------------------------------------------------------------- -->							 

		 
		<div class="form-group clearfix">
		<label for="inputPassword" class="control-label col-xs-4"
			style="text-align: right; line-height: 35px;">Date</label>
		<div class="col-xs-7">
			<input type="text" name="paymentDate" id="paymentDate"  class="form-control" 
				 value="<logic:present name="" ><bean:write name=""  property="todate"/></logic:present>" />
				
			</div>
		</div>
			
<div class="student">
		<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-4"
					style="text-align: right; line-height: 35px;">Address</label>
			<div class="col-xs-7">
				<textarea rows="4" cols="35" id="studentAddress" class="form-control" name="studentAddress">
				</textarea>
					
			</div>
		</div>
		</div> 
	
<div class="teacher">
	<div class="form-group clearfix">
			<label for="inputPassword" class="control-label col-xs-4"
				style="text-align: right; line-height: 35px;">Address</label>
			<div class="col-xs-7">
				<textarea rows="4"  readonly cols="35" id="teacherAddress" class="form-control" name="teacherAddress">
				</textarea>
					
			</div>
		</div>
		</div> 
		
		
	<div class="othersAddr">
	
	<div class="form-group clearfix">
			<label for="inputPassword" class="control-label col-xs-4"
				style="text-align: right; line-height: 35px;">Address<font color="red">*</font></label>
			<div class="col-xs-7">
				<textarea  readonly rows="4" cols="35" class="form-control" onkeypress="HideError()" id="otherUserAddr">
				</textarea>
					
			</div>
		</div>
		</div>
		
		
<!-- 		
	<div class="form-group clearfix">
			<label 
				style="text-align: right;hover: line-height: 35px;"><a href=""></a><u>Library Card Details > ></u></a></label>
							</div> -->
						
					</div>
				</div>
	
			





<hr style="height:1px;border:none;color:#333;background-color:#ddd;"/>

<!-- small listing page ::: start -->
<div>
		<div class="slideTab clearfix">
		<div class="tab">
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#contacts"  id="staffTab">Staff</a></li>
				<li><a data-toggle="tab" href="#classHistory" id="studentsTab">Student</a></li>
				<li><a data-toggle="tab" href="#classHistory" id="othersTab">Others</a></li>
			</ul>
		
			<div id="contacts" class="tab-pane">
			<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
			<div class="searchWrap">
				<div class="col-md-8" id=div2></div>
				<div id="staffTable"></div>
				<div id="studentTable"></div>	
				<div id="othersTable"></div>	
			</div>
			</div>
		</div>
		
	</div>
</div>
</div>
<!-- small listing page ::: end -->
		
		</div>
		</div>
	</div>
</div>
</html>



