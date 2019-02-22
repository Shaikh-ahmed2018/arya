<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JS/common.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
	

 <script type="text/javascript" src="JS/backOffice/Settings/ClassTeacherMapping.js"></script> 

<script type="text/javascript">
	
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>
<style>
.glyphicon:hover{

cursor: pointer;
}
#classSave:hover {
	cursor: pointer;
}

</style>
<style>
.buttons{

vertical-align: 0px;

}
</style>

</head>

<body>
<div>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">ClassTeacher Mapping</span>
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
					class="validateTips"></span></a>
			</div>
		</div>


<center><logic:present name="Nothingfoundmsg" scope="request"><span style="color: black;font-family: Segoe UI;font-size:12px;"><bean:write name="Nothingfoundmsg"/></span></logic:present></center>





		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;ClassTeacher Mapping
							</h4></a>
							

							<div class="navbar-right" >
							
							
							 <span id="saveid" class="buttons" data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="Save">
									Save
							</span>&nbsp;
							
							<span id="back" class="buttons" >Back</span>
						
							</div>
						
						
						
						
						
	<input type="hidden" name="hclassid" class="classididclass" id="hclassid" value='<logic:present name="getcalssteacher"><bean:write name="getcalssteacher" property="classId" /></logic:present>'></input>					
						
	<input type="hidden" name="hsectionid" class="sectionididclass" id="hsectionid" value='<logic:present name="getcalssteacher"><bean:write name="getcalssteacher" property="sectionId" /></logic:present>'></input>
	
	<%-- <input type="hidden" name="hteacherid" class="classididclass" id="hteacherid" value='<logic:present name="getcalssteacher"><bean:write name="getcalssteacher" property="teacherId" /></logic:present>'></input> --%>					
						
	<input type="hidden" id="hteachername" value='<logic:present name="getcalssteacher" scope="request"><bean:write name="getcalssteacher" scope="request" property="teacherName"/></logic:present>'>					
						
		
	<input type="hidden" name="hteacherid" class="classididclass" id="hteacherid" value='<logic:present name="teacherid"><bean:write name="teacherid"/></logic:present>'></input>
	<input type="hidden" name="hlocationid" class="classididclass" id="hlocationid" value='<logic:present name="locationid"><bean:write name="locationid"/></logic:present>'></input>		
		
				
				</div>
				
				
				
					
						
					
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6" id="txtstyle"
								style="font-size: 11pt; color: #5d5d5d;">
								
								
								
								
								
								
								<div class="form-group clearfix">
									
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Class Name
								</label>		
								
									<div class="col-xs-7">
									<input type="text" name="classname" id="classname"
										class="form-control" placeholder="" maxlength="30"
										value='<logic:present name="getcalssteacher"><bean:write name="getcalssteacher" property="className" /></logic:present>'></input>
								</div>
								
								</div>
						
							 
								
								
								
								
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Section Name</label>
									<div class="col-xs-7">
									<input type="text" name="sectionname" id="sectionname"
										class="form-control" placeholder="" maxlength="30"
										value='<logic:present name="getcalssteacher"><bean:write name="getcalssteacher" property="sectionName" /></logic:present>'></input>
								</div>
								</div>
								 

							</div>
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								
								
								
								
								<div class="form-group clearfix">
									
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Teacher Name
								</label>
								
								
								<div class="col-xs-7">
								
									<logic:present name="teacherlist" scope="request">
								         <select class="form-control" name="teachername" id="teachernameid" onkeypress="HideError()">
								           <option value=""> </option>
									       <logic:iterate id="stream" name="teacherlist" scope="request">
											<option value='<bean:write name='stream' property='teacherId'/>'>
											<bean:write name='stream' property='tfastname'/></option>
										    </logic:iterate>
							                </select>
											</logic:present>
								</div>
								
								
								
										
								<%-- <div class="col-xs-7">
									<input type="text" name="teachername" id="teachername"
										class="form-control" placeholder="" maxlength="30"
										value='<logic:present name="getcalssteacher"><bean:write name="getcalssteacher" property="teacherName" /></logic:present>'></input>
								</div> --%>
								</div>
								
								
								
								<%-- <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="className"
											placeholder="" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="className"/></logic:present>"/>
											<input type="hidden" name="status" id="statusId" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="status"/></logic:present>"/>
											<input type="hidden" name="updateClassCode" id="updateClassCode" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="classId"/></logic:present>"/>
											<input type="hidden" name="hiddenStreamId" id="hiddenStreamId" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="streamId"/></logic:present>"/>
									</div>
								</div> --%>
								 
						
							</div>
						</div>
					</div>
				</div>




			
		</form>
	</div>
	</div>
</body>

</html>
