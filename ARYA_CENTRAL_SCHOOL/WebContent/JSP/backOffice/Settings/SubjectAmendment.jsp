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
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
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
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />


<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript"
	src="JS/backOffice/Settings/SubjectAmendment.js"></script>
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
</script>
<script>
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
</script>

<style>
.buttons {
	vertical-align: 0px;
}

.navbar-right span {
	margin-right: 3px;
}

.navbar-right span:last-child {
	margin-right: 10px;
}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20px; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span>Modify Subject</span>
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
					<a href="#" class="msg-warning bg-msg-warning"><span
						style="color: red;"> <bean:write name="errormessagediv"
								scope="request" />
					</span></a>
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

		<form id="SubjectForm" action="subject.html?method=updateSubject"
			method="post" enctype="multipart/form-data">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#"
								style="color: #767676"><h4 class="panel-title"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Subject Details
							</h4></a>
						
						<div class="navbar-right">

							<span id="save" class="buttons">Save</span>
						<span id="back" class="buttons">Back</span></a>
						</div>

					</div>

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">


						<div class="clearfix">
							<div class="col-md-6" id="txtstyle" style="font-size: 11pt;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">School
										Name <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationId"
											class="form-control">
											<option value="">-------------Select-----------</option>

											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option
														value="<bean:write name="Location" property="locationId"/>">
														<bean:write name="Location" property="locationName" />
													</option>
												</logic:iterate>
											</logic:present>

										</select>
									</div>
									<input type="hidden" name="schoolId" class="form-control"
										id="schoolId"
										value='<logic:present name="singlesubjectdetails"><bean:write name="singlesubjectdetails" property="locationId" /></logic:present>'></input>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Subject
										Name <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" name="subjtname" id="subjtname"
											class="form-control" placeholder="Subject Name"
											value='<logic:present name="singlesubjectdetails"><bean:write name="singlesubjectdetails" property="subjectname" /></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Is Language</label>
									<div class="col-xs-7">
									<select id="isLangauge" name="isLang" class="form-control">
										<option value="N">No</option>
										<option value="Y">Yes</option>
									</select>
									</div>
									<input type="hidden" id="hLanguage" value='<logic:present name="singlesubjectdetails"><bean:write name="singlesubjectdetails" property="isLanguage" /></logic:present>'></input>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Specialization</label>
									<div class="col-xs-7">
										<select id="specialization" name="specialization"
											class="form-control">
											<option value="">-------------Select-----------</option>
											<option value="-" selected="selected">General</option>
											<logic:present name="specialization">
												<logic:iterate id="specialization" name="specialization">
													<option
														value="<bean:write name="specialization" property="specializationId"/>">
														<bean:write name="Location" property="specializationName" />
													</option>
												</logic:iterate>
											</logic:present>

										</select> <input type="hidden" name="hspecialization"
											class="form-control" id="hspecialization"
											value='<logic:present name="singlesubjectdetails"><bean:write name="singlesubjectdetails" property="specializationId" /></logic:present>'></input>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
										<textarea rows="3px" cols="3px" name="description"
											id="description" class="form-control" placeholder="">
											<logic:present name="singlesubjectdetails">
												<bean:write name="singlesubjectdetails"
													property="description" />
											</logic:present>
										</textarea>
									</div>
								</div>


							</div>
							<div class="col-md-6" id="txtstyle">

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class<span
										style="color: red;">*</span></label>
									<div class="col-xs-7">
										<select name="classname" id="classnameid" class="form-control"
											onkeypress="HideError()">
											<option value=""></option>

										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Applicable for exam</label>
									<div class="col-xs-7">
									<select id="isExam" name="isExam" class="form-control">
										<option value="N">No</option>
										<option value="Y">Yes</option>
									</select>
									</div>
									<input type="hidden" id="hExam" value='<logic:present name="singlesubjectdetails"><bean:write name="singlesubjectdetails" property="isExam" /></logic:present>'></input>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Total
										Marks <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" name="totalMarks" id="totalMarks"
											class="form-control"
											value='<logic:present name="singlesubjectdetails"><bean:write name="singlesubjectdetails" property="totalMarks" /></logic:present>' />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Pass
										Marks <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" name="passMarks" id="passMarks"
											class="form-control"
											value='<logic:present name="singlesubjectdetails"><bean:write name="singlesubjectdetails" property="passMarks" /></logic:present>' />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Subject
										Code<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" name="subjectCode" id="subjectCode"
											onkeypress="HideError()" class="form-control" placeholder=""
											value='<logic:present name="singlesubjectdetails"><bean:write name="singlesubjectdetails" property="subjectCode" /></logic:present>' />
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

			<input type="hidden" name="hiddenclass" id="hiddenclassid"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="classname"/></logic:present>" />
			<input type="hidden" name="hiddensubject" id="hiddensubject"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="subjectname"/></logic:present>" />
			<input type="hidden" name="hiddendescription" id="hiddendescription"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="description"/></logic:present>" />
			<input type="hidden" name="hiddenfile" id="hiddenfile"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="path"/></logic:present>" />
			<input type="hidden" name="hiddensubjectId" id="hiddensubjectid"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="subjectid"/></logic:present>" />
			<input type="hidden" name="status" id="statusid"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="status"/></logic:present>" />

			<input type="hidden" name="status" id="hsuccessid"
				value="<logic:present name="success" ><bean:write name="success"/></logic:present>" />

		</form>
	</div>

</body>

</html>
