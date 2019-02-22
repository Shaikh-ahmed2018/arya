<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JS/backOffice/Admin/Latheef.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script src="JS/newUI/jquery.js"></script>
<script src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Exam/StudentPromotion.js"></script>

<title>eCampus Pro</title>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
</style>
<style>
.buttons{

vertical-align: 7px;

}
.buttons{
margin-right: 5px;
}
.form-group{
margin-bottom: 10px;
}
.tableWrap{
overflow-y:scroll;
max-height: 280px; 
}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Promotion</span>
			</p>
		</div>


		

		

			<logic:present name="success" scope="request">
				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
									name="success" scope="request" />
						</span></a>
					</div>
				</div>
			</logic:present>

			<logic:present name="error" scope="request">
				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
									name="error" scope="request" /></span></a>
					</div>
				</div>
			</logic:present>
		<input type="hidden" id="haccyaear" value='<logic:present name="acadamicYear" scope="request"><bean:write name="acadamicYear"  /></logic:present>' />
		<input type="hidden" id="nhaccyaear" value='<logic:present name="nextaccyearid" scope="request"><bean:write name="nextaccyearid"  /></logic:present>' />
		<input type="hidden" id="hclassid" value='<logic:present name="classId"  scope="request"><bean:write name="classId" /></logic:present>' />
		<input type="hidden" id="hsectionid" value='<logic:present name="sectionId" scope="request"><bean:write name="sectionId"  /></logic:present>' />
		<input type="hidden" id="hspecializationid" value='<logic:present name="specializationId"  scope="request"><bean:write name="specializationId" /></logic:present>' />
		<input type="hidden" id="hclassStearm" value='<logic:present name="classStearm"  scope="request"><bean:write name="classStearm" /></logic:present>' />
		
		
		
		<div class="academic_year clearfix">
			<div class="col-md-4">
				<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: left;font-size:16px;font-weight:300;color:#767676;line-height: 2; padding-right:0px;">Academic
						Year </label>
						<div class="col-xs-7" style=" padding: 0px;">
							<input id="accyear" class="form-control" type="text" value='<logic:present name="accyearname" scope="request"><bean:write name="accyearname" /></logic:present>' readonly="readonly" />
						
						</div>
					</div>
				</div>
				
				
				<div class="col-md-5">
				<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: left;font-size:16px;font-weight:300;color:#767676;line-height: 2; padding-right:0px;">Next Academic Year </label>
						<div class="col-xs-7" style=" padding: 0px;">
							<input name="nextaccYear" id="nextaccYear" class="form-control" value='<logic:present name="nextaccyearname"  scope="request"><bean:write name="nextaccyearname" /></logic:present>' readonly="readonly" />
					
						</div> 
					</div>
				</div>
				<div class="col-md-3"><span class="buttons" id="save" >Save</span><span class="buttons" id="Back" >Back</span></div>
			</div>
			<div class="class_section clearfix">
			<div class="col-md-4">
				<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: left;font-size:16px;font-weight:300;color:#767676;line-height: 2; padding-right:0px;">Class </label>
						<div class="col-xs-7" style=" padding: 0px;">
							<input id="classname" class="form-control" type="text" value='<logic:present name="classname" scope="request"><bean:write name="classname" /></logic:present>' readonly="readonly" />
						
						</div>
					</div>
				</div>
				
				
				<div class="col-md-5">
				<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: left;font-size:16px;font-weight:300;color:#767676;line-height: 2; padding-right:0px;">Section </label>
						<div class="col-xs-7" style=" padding: 0px;">
							<input name="sectionname" id="sectionname" class="form-control" value='<logic:present name="sectionname"  scope="request"><bean:write name="sectionname" /></logic:present>' readonly="readonly" />
					
						</div>
					</div>
				</div>
			</div>
			<div class="specName clearfix">
				<div class="col-md-4">
				<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: left;font-size:16px;font-weight:300;color:#767676;line-height: 2; padding-right:0px;">Specialization </label>
						<div class="col-xs-7" style=" padding: 0px;">
							<input name="specializationname" id="specializationname" class="form-control" value='<logic:present name="specializationname"  scope="request"><bean:write name="specializationname" /></logic:present>' readonly="readonly" />
					
						</div>
					</div>
				
				</div>
			</div>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Student List</h3></a>
			
			</div>
			
				<div id="collapseOne" class="panel-collapse collapse in tableWrap" role="tabpanel" aria-labelledby="headingOne">
				

						<logic:present name="studentList" scope="request">
							<display:table id="allstudent" name="studentList" class="table" export="false">
								<display:column property="count" title="Sl.No" class="${allstudent.studentid}"></display:column>
								<display:column property="admissionno" title="Roll No." />
								<display:column property="admissionno" title="Admission No." />
								<display:column property="stname" title="Student Name" />
								<display:column title="Status" ><select class="promotion"><option value="Promoted" selected="selected">Promoted</option><option value="Demoted">Demoted</option></select></display:column>
							</display:table>
						</logic:present>
				</div>
		</div>
	</div>
</body>
</html>