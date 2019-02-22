<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Teacher/AssignmentUpload.js"></script>


<style>
.save:hover {
	cursor: pointer;
}
</style>

<style>
#list:hover {
	cursor: pointer;
}
</style><style>
.buttons{

vertical-align: 0px;

}
</style>


</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Assignment</span>
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

		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" style="color: #767676">
							<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;New Assignment
						</h4></a>
					
					<div class="navbar-right">
						<span class="buttons" style="margin-left: -41px; cursor: pointer;"id="save">Save</span> 
						<span id="back" class="buttons" >Back</span>
					</div>

				</div>
				
				<form id="assignmentform" action="assignmentupload.html?method=insertAssignment" method="post">
				<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Assignment Name	</label>
								<div class="col-xs-7">
									<input type="text" name="assname" id="assname" class="form-control"></input>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Completion Date</label>
								<div class="col-xs-7">
									<input type="text" name="compdate" id="compdate" readonly="readonly" class="form-control" placeholder="Click Here"></input>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">School Name</label>
								<div class="col-xs-7">
									<select id="locationname" name="locationnid" class="form-control">
										<option value="all">ALL</option>
										<logic:present name="locationList">
											<logic:iterate id="Location" name="locationList">
												<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;"> Class</label>
								<div class="col-xs-7">
									<select class="form-control" name="classname" id="classname">
										<option value="all">ALL</option>
									</select>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Division</label>
								<div class="col-xs-7">
									<select id="sectionid" name="sectionid" class="form-control">
										<option value="all">ALL</option>
									</select>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Subject</label>
								<div class="col-xs-7">
									<select name="subject" id="subjectid" class="form-control">
										<option value="all">ALL</option>
									</select>
								</div>
							</div>
							
						</div>
						<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Assignment Date</label>
								<div class="col-xs-7">
									<input type="text" name="assdate" id="assdate" readonly="readonly" class="form-control" placeholder="Click Here"></input>
								</div>
							</div>
							
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Max Point/Marks</label>
								<div class="col-xs-7">
									<input type="text" name="maxid" id="maxid" class="form-control"></input>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Description</label>
								<div class="col-xs-7">
									<textarea style="font-size: 12px;" rows="4"  name="description" class="form-control" id="description"></textarea>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Specialization</label>
								<div class="col-xs-7">
									<select name="specializationid" id="specializationid" class="form-control">
										<option value="all">ALL</option>
									</select>
								</div>
							</div>
						</div>
						<div></div>
						
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">	
					<table class="table" id="allstudent">
						<thead>
							<tr>
								<th><input type='checkbox' name='selectAll' id='selectAll'/></th>
								<th>Roll No</th>
								<th>Admission No</th>
								<th>Student Name</th>
								<th>Standard</th>
								<th>Division</th>
								<th>Specialization</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					
					
					
					</div>
						<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
							<span  class='numberOfItem'></span>	
								</div><div class='pagination pagelinks' style='top:-9px'></div>
					</div>
						
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>
