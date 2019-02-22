<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>


<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
	<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Settings/UpdateLab.js"></script> 
<script type="text/javascript">
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
	function isNumberKey(evt)
	{
	    var charCode = (evt.which) ? evt.which : event.keyCode;
	    if (charCode != 43 && charCode > 31 && charCode != 32 && charCode != 45 && (charCode < 48 || charCode > 57) && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122))
	        {
	        return false;
	        }
	    else{
	    return true;
	    }
	} 

</script>
<script>
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
</script><style>
.buttons{

vertical-align: 0px;

}

.navbar-right span{
margin-right:3px;
}

.navbar-right span:last-child{
margin-right:10px;
}

</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Update  Lab</span>
		</p>
		
		
					<logic:present name="successmessagediv" scope="request">
						<div class="successmessagediv" align="center">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"><span><bean:write
											name="successmessagediv" scope="request" /></span></a>
							</div>
						</div>
					</logic:present>
					
						<logic:present name="errormessagediv" scope="request">

							<div class="errormessagediv" align="center">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-warning bg-msg-warning"><span style="color:red ;">
											<bean:write name="errorMessage" scope="request" />
									</span></a>
								</div>

							</div>

						</logic:present>


					<div class="errormessagediv" align="center" style="display: none;">
						<div class="message-item">
							Warning
							<a href="#" class="msg-warning bg-msg-warning"><span
								class="validateTips"></span></a>
						</div>
					</div>
				
		      
		<form id="SubjectForm" action="subject.html?method=updateLab" method="post" enctype="multipart/form-data" >
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#" style="color: #767676"><h4 class="panel-title"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Lab Details
								</h4></a>
						

							<div class="navbar-right">
								 <span id="save" class="buttons">Save</span> 
								<span id="goback" class="buttons" style="margin-left: -5px;margin-right: 1px;">Back</span> 
							</div>
					</div>
					
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="clearfix">
							<div class="col-md-6" id="txtstyle" style="font-size: 11pt;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">School Name <span style="color: red;">*</span></label>
									<div class="col-xs-7">
									<select id="locationname" name="locationName" class="form-control">
										<option value="">-------------Select-----------</option>
										
										<logic:present name="locationList">
										<logic:iterate id="Location" name="locationList">
											<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
										</logic:iterate>
										</logic:present>
						
									</select>
									</div>
								<input type="hidden" name="schoolId" class="form-control" id="schoolId" value='<logic:present name="list"><bean:write name="list" property="locationId" /></logic:present>'></input>
							</div>
						
						<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Specialization</label>
									<div class="col-xs-7">
									<select id="specialization" name="singlelabdetails" class="form-control">
										
										<logic:present name="singlelabdetails">
											<option value="<bean:write name="singlelabdetails" property="specId" />"><bean:write name="singlelabdetails" property="specialization" /></option>
										</logic:present>
						
									</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Lab Name<span style="color: red;">*</span></label>
									<div class="col-xs-7">
											<input type="text" maxlength="25" style="text-transform: capitalize;" name="lab_name" id="lab_name"  onkeypress="return isNumberKey(event);"   class="form-control"    
											value='<logic:present name="singlelabdetails"><bean:write name="singlelabdetails" property="lab_name"/></logic:present>' /> 
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
					<textarea rows="3px" cols="3px" name="description" id="description" class="form-control" placeholder="" ><logic:present name="singlelabdetails"><bean:write name="singlelabdetails" property="description" /></logic:present></textarea>
									</div>
								</div>
							</div>
							
							<div class="col-md-6" id="txtstyle">
								
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class<span style="color: red;">*</span></label>
									<div class="col-xs-7">
										<select name="classname" id="classname" class="form-control" onkeypress="HideError()" >
									<option value=""></option>
									
							    </select>
									</div>
								</div>
								
									
                               <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Subject Name<span style="color: red;">*</span></label>
										<div class="col-xs-7">
										<select id="subject" name="subjtname" class="form-control">
										<option value="">-------------Select-----------</option>
										
				                        </select>
				                        </div>
									
								</div>
						
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Total Marks<span style="color: red;">*</span></label>
									<div class="col-xs-7">
											<input type="text" maxlength="4" name="totalMarks" id="totalMarks" onkeypress="HideError()" class="form-control"   placeholder="" 
											value='<logic:present name="singlelabdetails"><bean:write name="singlelabdetails" property="totalMarks" /></logic:present>' />
									</div>
								</div>
								
							
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Pass Marks<span style="color: red;">*</span></label>
									<div class="col-xs-7">
											<input type="text" maxlength="4"  name="passMarks" id="passMarks" onkeypress="HideError()" class="form-control"   placeholder="" 
											value='<logic:present name="singlelabdetails"><bean:write name="singlelabdetails" property="passMarks" /></logic:present>' />
									</div>
								</div>
						
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Lab Code<span style="color: red;">*</span></label>
									<div class="col-xs-7">
											<input type="text" maxlength="6" name="subjectCode" id="subjectCode" onkeypress="return isNumberKey(event);"  class="form-control"   placeholder="" 
											value='<logic:present name="singlelabdetails"><bean:write name="singlelabdetails" property="subjectCode" /></logic:present>' />
									</div>
								</div>
							
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Syllabus</label>
									<div class="col-xs-7">
										<input type="file" class="form-control" id="file" name="file"
											placeholder="Syllabus"> <input type="button"
											id="document1btn" name="profile" class="downloadDoc"
											value="Download" /> <span
											style="font-size: 20px; color: red; cursor: pointer;"
											id="deleteProfile"> x</span>

									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>
				
					
			      </div>
			   <input type="hidden" name="hiddenlocation" id="hiddenlocationid" value="<logic:present name="singlelabdetails" ><bean:write name="singlelabdetails" property="locationName"/></logic:present>"/>   
               <input type="hidden" name="hiddenclass" id="hiddenclassid" value="<logic:present name="singlelabdetails" ><bean:write name="singlelabdetails" property="classname"/></logic:present>"/>
               <input type="hidden" name="hiddensubject" id="hiddensubject" value="<logic:present name="singlelabdetails" ><bean:write name="singlelabdetails" property="subjtname"/></logic:present>"/>
               <input type="hidden" name= "hiddendescription" id="hiddendescription" value="<logic:present name="singlelabdetails" ><bean:write name="singlelabdetails" property="description"/></logic:present>"/>
               <input type="hidden" name="hiddenfile" id="hiddenfile" value="<logic:present name="singlelabdetails" ><bean:write name="singlelabdetails" property="syllabus"/></logic:present>"/>
               <input type="hidden" name="hiddensubjectId" id="hiddenlabid" value="<logic:present name="singlelabdetails" ><bean:write name="singlelabdetails" property="lab_id"/></logic:present>"/>
             <%--   <input type="hidden" name="status" id="statusid" value="<logic:present name="singlelabdetails" ><bean:write name="singlelabdetails" property="status"/></logic:present>"/> --%>
				
		</form>
	</div>
	
	
	
</body>

</html>
