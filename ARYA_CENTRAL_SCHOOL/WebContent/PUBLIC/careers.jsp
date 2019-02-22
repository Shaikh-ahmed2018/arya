<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="shortcut icon" type="icon" href="images/favicon.ico" />
<title>Latheef Science Technology Montessori and Primary School</title>

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
<script type="text/javascript" src="JS/Admin/Latheef.js"></script>
<script type="text/javascript" src="JS/Public/careers.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<link href="CSS/common.css" rel="stylesheet" type="text/css" />
</head>


<body style="background-color: white;">

	<br />
	<div class="ui-content">
		<!-- Tab panes -->
		<div class="tab-content">
			<div class="box">
				<div class="box-head">Careers</div>


				<center>


					<logic:present name="successMsg" scope="request">

						<div class="successmessagediv">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"><span>
										<bean:write name="successMsg" />
								</span></a>
							</div>
						</div>

					</logic:present>


				</center>



				<center>
					<logic:present name="career" scope="request">
						<display:table id="teacher" class="view"
							name="requestScope.career" requestURI="/publicMenu.html"
							export="false" pagesize="10"
							decorator="com.centris.campus.decorator.CareersApplyDecorator">


							<display:column property="jobcode" title="Job Code" />
							<display:column property="title" title="Title" />
							<display:column property="qualification" title="Qualification" />
							<display:column property="noofposition" title="No of Position" />
							<display:column property="experience" title="Experience" />
							<%-- <display:column property="description" title="Description" /> --%>
							<display:column property="status" title="Status" />
							<display:column property="apply" title=""
								style="height:40px !important;" />



						</display:table>

						<br />
						<!-- <input type="button" id="download" class="btn btn-success"
							value="Download">
 -->
					</logic:present>
				</center>



				<br />
			</div>
			<br />
		</div>
	</div>
</body>

</html>

