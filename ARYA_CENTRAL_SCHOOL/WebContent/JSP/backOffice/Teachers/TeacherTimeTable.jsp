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
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/backOffice/Staff/TeacherTimeTable.js"></script>
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
.dateColumn{width: auto !important;}
.teachedetails{
padding: 0 15px;
}
.header{display: none;}
</style>


</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">



		<div id="div2">
			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Time Table</span>
			</p>
		</div>
		
		


<input type="hidden" name="hidden" class="hiddenclass" id="hiddenid" value='<logic:present name="studentexam"><bean:write name="studentexam" /></logic:present>'></input>
													
<input	type="hidden" name="parenthidden" id="parenthidden" value="<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" property="parentID"/></logic:present>"/>







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
						class="panel-title" style="margin-left: -627px;color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Time Table
						
					</h3></a>
					
					
				
				<div class="navbar-right">
					   <span  class="buttons" id="print" >PRINT</span>
				</div>
				
			</div>
			<!-- pop up -->

			
			
	
     <input	type="hidden" name="asshidden" id="assgnmentid" value=""/>			
		<div id="collapseOne" class="accordion-body collapse in">
			<div class="panel-body" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				<div class="col-md-12" id="scrolid" style="padding: 0; overflow: scroll;">
				<logic:present name="teacherdetails" scope="request">
					<div class="teachedetails">
						<div class="header" style="text-align: center;">
							<h2><u>ARYA CENTRAL SCHOOL</u></h2>
						</div>
							
						<div class="teacherName">
							<b>Name : -</b><i> (<bean:write name="teacherdetails" property="abbreviate" />)-<bean:write name="teacherdetails" property="tfastname" /> <bean:write name="teacherdetails" property="tlastname" /></i>
						</div>
					</div>	
				</logic:present>	
						<table id="allstudent"  class="table">
						<thead>
							<tr>
							<th>Day</th>
							<th>Period 1</th>
							<th>Period 2</th>
							<th>Period 3</th>
							<th>Period 4</th>
							<th>Period 5</th>
							<th>Period 6</th>
							<th>Period 7</th>
							<th>Period 8</th>
							<th>Period 9</th>
							</tr>
						</thead>
							<tbody></tbody>
							
						</table>
					
					
					
			     </div>
				<br>
			</div>
		
			
		</div>
		</div>
		
		
		<div class="panel panel-primary" style="margin-top: 31px;">
			<div class="panel-heading">
			<h3 class="panel-title" style="color: #767676; vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Today Time Table
						
					</h3>
				
			</div>
			<!-- pop up -->

			
			
	
			
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
			
			<div class="col-md-12" id="scrolidtoday"
						style="padding: 0; overflow: scroll;">
						
					
						<table id="todayTimeTable" class="table allstudent">
						<thead>
							<tr>
							<th class="dateColumn">Date</th>
							<th>Day</th>
							<th>Period 1</th>
							<th>Period 2</th>
							<th>Period 3</th>
							<th>Period 4</th>
							<th>Period 5</th>
							<th>Period 6</th>
							<th>Period 7</th>
							<th>Period 8</th>
							<th>Period 9</th>
							</tr>
						</thead>
							<tbody></tbody>
							

						</table>
					
				
					
			     </div>
				<br>
			</div>
		
			
		</div>
		</div>
</div>
<textarea id="printing-css" Style="display: none;">

#scrolid td,#scrolid th{
border:1px solid #000;
}
</textarea>
</body>
</html:html>