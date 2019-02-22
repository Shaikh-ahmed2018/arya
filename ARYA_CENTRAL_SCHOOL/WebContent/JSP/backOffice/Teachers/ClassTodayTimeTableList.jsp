<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<title>Campus School Stream</title>
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
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JS/backOffice/Teacher/TimeTableSubstitution.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript">
$(document).ready(function(){
	
	$("#allstudent tbody tr").click(function(){
		window.location.href="TimeTableActionPath.html?method=gettodaytimetableDetail&timetableId="+$(this).find(".hrowId").text();
		});
});

</script>

 <!-- <script type="text/javascript" src="JS/backOffice/Parents/StudentTimeTable.js"></script>  -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
#editAssId:hover {
	cursor: pointer;
}

.downloadlast:hover{

cursor: pointer;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
</style>
<style>
.buttons{

vertical-align:-28px;

}
.hideme{
display: none;
}
</style>


</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">



		<div class="" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Time Table Substitution</span>
			</p>
		</div>
		
		




		<div class="errormessagediv" align="left" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>



		<div class="successmessagediv" align="left" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<logic:present name="fail" scope="request">
			<div class="successmessagediv1">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
								name="fail" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>



		<div class="panel panel-primary" style="margin-top: 31px;">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span> Class Wise List 
						
					</h3></a>
					
					
				
				
			</div>
			<!-- pop up -->

			
			
	
     <input	type="hidden" name="asshidden" id="assgnmentid" value=""/>			
			
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
					
					
			
			
			<div class="col-md-12" id="scrolid" style="padding: 0; overflow: scroll;height: 250px;">
						
						
					
						
						
						
						
						<logic:present name="ClassTodayTimeTableList" scope="request">
					
						<display:table id="allstudent" name="ClassTodayTimeTableList" class="table"
							requestURI="/teachermenuaction.html?method=ClassTodayTimeTableList"
				            export="false" pagesize="100">

							<display:column property="timetableId" title="TimetableId" class="hidden hrowId"
								headerClass="hidden" />
								
							<display:column property="classname" title="Class" />
							<display:column property="sectionname" title="Section" />
							<display:column property="count" title="Vacant Period" />
							

						</display:table>
					
					</logic:present> 
						
			
					
			     </div>
				<br>
			</div>
		
			
		</div>
		</div>
		
		<div class="panel panel-primary" style="margin-top: 31px;">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion3"
					href="#collapseTwo" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span> <logic:present name="heading" scope="request"><bean:write name="heading" /></logic:present>
						
					</h3></a>
					
					<div class="navbar-right"> <logic:present name="notSubstituted" scope="request"><span class="buttons" id="substitute">Substitute</span></logic:present>
					<logic:present name="Substituted" scope="request"><span class="buttons" id="print">Print</span></logic:present>
					</div>
				
				
			</div>
			<!-- pop up -->

			
			
	
     	
			
			<div id="collapseTwo" class="accordion-body collapse">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
			
			<div class="col-md-12" id="teacherscrolid" style="padding: 0; overflow: scroll;height: 250px;position:relative">
					<div class="hideme"><logic:present name="heading" scope="request"><bean:write name="heading" /></logic:present></div>
						<logic:present name="teacherSustituteLis" scope="request">
					
						<display:table id="absentieeTeacher" name="teacherSustituteLis" class="table allstudent"
							requestURI="/teachermenuaction.html?method=ClassTodayTimeTableList"
				            export="false" pagesize="100">
						<display:column   class="hidden hrowId"
								headerClass="hidden" />
							<display:column property="teachername" title="Teacher Name"  />
								
							<display:column property="classname" title="Class" />
							<display:column property="period1" title='<logic:present name="periodT" scope="request"><bean:write name="periodT" /></logic:present> Period' />
							<display:column   class="hideme" title="Signature"
								headerClass="hideme" />

						</display:table>
					
					</logic:present> 
						
			
					
			     </div>
				<br>
			</div>
		
			
		</div>
		</div>
		
		
		
		</div>
		<textarea id="printing-css" style="display:none">
		#absentieeTeacher{
		border:1px solid #000;
		}
		#absentieeTeacher tr{display:table-row; }
		#absentieeTeacher th,#absentieeTeacher td{
		border:1px solid #000;
		}
		.pagebanner,.pagelinks{
		display:none;
		}
		.hideme{
		display:block;
		color:#098uyt;
		font-size:18px;
		}
		#absentieeTeacher .hideme{
		display:table-cell;
		}
		.hrowId{display:none;}
		body {-webkit-print-color-adjust: exact;margin:0;padding:0;}
		</textarea>
</body>
</html:html>