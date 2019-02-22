<!DOCTYPE html>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">



<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>

<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>

<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>

<script type="text/javascript" src="JS/backOffice/Admin/AddDisplayDetails.js"></script>



<title>eCampus Pro</title>
<style>
#mapedit:hover {
	cursor: pointer;
}
</style>

<style>
#delete:hover {
	cursor: pointer;
}
</style>


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
</head>

<body>





	<div class="col-md-10 col-md-offset-2"
		style="font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Class Teacher Mapping</span>
		</p>
		
		
		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<logic:present name="errormessagediv" scope="request">
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><bean:write name="errormessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="errormessagediv1"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 50%; font-size: 11pt; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>



		<div class="errormessagediv" style="display: none;">
							<div class="message-item">
								
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips"></span></a>
							</div>
						</div>


		
		
		
		
		
		
		

		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-value="Aaa" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;<h4 class="panel-title"></h4></a>
								
								<div class="navbar-right">
		 							<a href="teachmap.html?method=adddisplaydetails"><span class="buttons">Add</span></a> 
									<span id="mapedit" class="buttons">Edit</span>
									<span id="delete" class="buttons">Delete</span>
					 				 <span id="download" class="buttons">Download</span>
							</div>
						
					</div>
								<logic:present name="mappinglist" scope="request">
								<logic:iterate id="calMap" name="mappinglist" scope="request">
								<h3 class="accordHead" style="background: #D8D8D8;color: #424242;padding-left: 10px;size: 12px">
								<bean:write name="calMap" property="key" />
								</h3>
										
				<div id="collapseOne" class="accordion-body collapse in">
				<div class="accordBody"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<table class="table" id="allstudent">
						<tr class="headth">
							
							<th>Select<img src="images/sort1.png"
								style="float: right"></th>
							<th>SectionName<img src="images/sort1.png"
								style="float: right"></th>
							<th>TeacherName<img src="images/sort1.png"
								style="float: right"></th>
							
						</tr>
						<logic:iterate name="calMap" property="value" id="dateVO">
							<tr>
												
								<td><input type="checkbox" class="check" id="<bean:write name="dateVO" property="classteacherid" />" value="<bean:write name="dateVO" property="classteacherid" />"/></td>
								<td><bean:write name="dateVO" property="sectionname" /></td>
								<td><bean:write name="dateVO" property="teachername" /></td>
													
							</tr>
						</logic:iterate>
					</table>
					
				</div>
				<br />
			</div>
		</logic:iterate>
		
	</logic:present></a>

				<input type="hidden" id=checkval value="">
				
		</form>
	</div>
	</div>
</body>

</html>
