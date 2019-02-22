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
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JS/backOffice/Exam/examtimetable.js"></script>

<title>eCampus Pro</title>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="CSS/newUI/examtimetable.css" rel="stylesheet">



<style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Exam Timetable Details</span>
			</p>
		</div>
		<!-- <div class="input-group col-md-5" style="margin: 20px 0px;">

			<input type="hidden" class="form-control" id="searchValue"
				Placeholder="Search......"> <span class="input-group-btn">
				<button class="btn btn-default" type="button" id="searchClass">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
 -->
		<br /> <br />



			<logic:present name="successMessage" scope="request">
				<div class="successmessagediv" align="center" >
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
									name="successMessage" scope="request" />
						</span></a>
					</div>
				</div>

			</logic:present>

			<logic:present name="errorMessage" scope="request">
				<div class="successmessagediv" align="center" >
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
									name="errorMessage" scope="request" />
						</span></a>
					</div>

				</div>
			</logic:present>
	
		<div class="errormessagediv" align="center"  style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>

					</h3></a>

			</div>
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

					<logic:present name="examList" scope="request">
						<logic:iterate id="calMap" name="examList" scope="request">
							<h3 class="accordHead" id="newstyleforaccordin">
								<span class="glyphicon glyphicon-eject"
									style="transform: rotate(180deg);    top: 1px; color: #A1A1A1"></span>&nbsp;&nbsp;
								<bean:write name="calMap" property="examName" />
							</h3>
							<div class="accBody">
								<table class="table" id="allstudent" style="text-align: center;">
									<tr class="headth">
										<th style="text-align: center; width: 30%;">Class Name</th>
										<th style="text-align: center; width: 30%;">Edit</th>
									</tr>
									<logic:present name="classList" scope="request">
										<logic:iterate name="classList" id="datalist" scope="request">
											<tr class="accordHead">
												<td><bean:write name="datalist" property="className" />
												</td>
												<td><span class="buttons addExam"
													style="color: #ffffff;border-right: 0px solid #ddd;"
													id="<bean:write name="datalist" property="classId" />,<bean:write name="calMap" property="examId"/>">Edit</span></td>
											</tr>
										</logic:iterate>
									</logic:present>
								</table>
							</div>
						</logic:iterate>
					</logic:present>
				</div>
				<br />

			</div>
		</div>
	</div>
	<script src="JS/newUI/jquery.js"></script>
	<script src="JS/newUI/bootstrap.min.js"></script>

</body>
</html>