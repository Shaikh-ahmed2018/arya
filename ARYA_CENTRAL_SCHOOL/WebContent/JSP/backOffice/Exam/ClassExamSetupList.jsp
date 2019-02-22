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

<title>eCampus Pro</title>
<script type="text/javascript" src="JQUERY/jquery-1.9.1.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script src="JS/newUI/jquery.js"></script>
<script src="JS/newUI/bootstrap.min.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />

</head>
<script type="text/javascript">
	$(document).ready(function(){
		$("#allstudent tbody tr").click(function(){
			var splitVal=$(this).find("td:first").attr("class").split(",");
			window.location.href="examTimetablePath.html?method=setClassExamTimeTableDetails&accYear="+splitVal[0]+"&classId="+splitVal[1];
		});
	});
</script>
<style>
.glyphicon:hover {
	cursor: pointer;
}

.download:hover {
	cursor: pointer;
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}
.button-right{
	position: absolute;
    right: 15px;
    top: 82px;
    float: none !important;
    display: block;
    bottom: 0;
    margin: auto auto;
    vertical-align: middle;
}

</style>


<body>
<div id="dialog"></div>
	<div class="col-md-10 col-md-offset-2" id="div1">
	<div class="searchWrap">
		<p>
			<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Exam Time Table</span>
		</p>
	</div>
	
	<div style="margin-bottom: 10px;vertical-align: top;line-height: 0;">
		<span style="font-size: 18px; color: #000; font-weight: normal;">Academic Year</span>
		<label style="font-size: 18px; color: #767676; font-weight: normal;"><input readonly="readonly" style="font-size: 17px;margin-left: 20px; width: 125px;text-align: center;" value="<logic:present name="accYear"  scope="request"><bean:write name="accYear" /></logic:present>" /></label>
		<div class="button-right">
		<span id="back" class="buttons">Back</a></span>
		</div>
	</div>
		<logic:present name="success" scope="request">

			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
								name="success" scope="request" />
					</span></a>
				</div>
			</div>
		</logic:present>
		<logic:present name="error" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="error" scope="request" />
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
		
		<input type="hidden" id="hiddenstartaccyear" value='<logic:present name="startDate"  scope="request"><bean:write name="startDate" /></logic:present>' />
				
		<input type="hidden" id="hiddenendaccyear" value='<logic:present name="endDate" scope="request"><bean:write name="endDate"  /></logic:present>' />		
		
		<div class="panel panel-primary">
			<div class="panel-heading clearfix" style="margin-bottom: -1px;">
			<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" style="color: #fff;">
				<h3 class="panel-title" style="color: #767676; line-height: 18px;">
					<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Exam Time Table
				</h3></a>
			</div>

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
					<display:table name="requestScope.classTimeTableList"
						requestURI="/examTimetablePath.html?method=getEaxmTimeTableClassList" pagesize="8"
						export="false" class="table" id="allstudent" decorator="com.centris.campus.decorator.ExamAccYearDecorator">

						<display:column property="sno1" title="Sl.No." class="${allstudent.accyearid},${allstudent.classid}"></display:column> 

						<%-- <display:column property="accyear" sortable="true" title="Academic Year <img src='images/sort1.png' style='float: right;'/>" /> --%>

						<display:column property="classname" sortable="true" title="Class Name <img src='images/sort1.png' style='float: right'/>" /> 
				
						<display:column property="status" sortable="true" title="Status <img src='images/sort1.png' style='float: right'/>" />
						
						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>

					</display:table>
					
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
