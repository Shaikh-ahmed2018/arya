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
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
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
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script src="JS/newUI/bootstrap.min.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/JS/backOffice/Exam/rollnumbergenerationSetting.js"></script>
</head>  


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
.Pending{
background-color:#FF0000;
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
.Completed{
background-color:rgba(0, 158, 0, 0.66);
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
#allstudent th:nth-child(1){
width:100px;
text-align: left;
}
#allstudent td:nth-child(1){
text-align: left;
}
.form-group{
margin-bottom: 10px;}

.scrollbar{

	overflow-y:scroll; 
	height: 250px;
}

</style>


<body>
<div id="dialog"></div>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<p style="margin-top: 5px; margin-bottom: 5px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">General Settting</span>
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

		<%-- <div class="accademicyear clearfix" style="margin-bottom: 10px;margin-top: 10px;">
		<div class="col-md-4" style="font-size: 15px;color: #5d5d5d;" id="txtstyle">
			<div class="form-group">
				<label for="inputEmail" class="control-label col-xs-5"
					style="text-align: left; line-height: 35px;">Academic Year</label>
				<div class="col-xs-7">
					<select id="accyear" name="accyear" class="form-control" required>
						<logic:present name="AccYearList">
						<logic:iterate id="AccYear" name="AccYearList">
							<option value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
						</logic:iterate>
					</logic:present>
				   </select>
				</div>
			</div>
		</div>
		
		<input type ="hidden" id ="hiddenaccid" name="hiddenaccid" value="<logic:present name="accyear"><bean:write name="accyear"/></logic:present>"/>
		
		</div> --%>
		
		
		
		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<div class="navbar-right">
           <span class="buttons" id="save">Save</span>
     </div>
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#" style="color: #fff;"><h3
						class="panel-title grade" style="color: #767676; line-height: 18px;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;General Settings List
					</h3></a>
					<!-- <div class="navbar-right" >
									<span class="buttons"><a href="examTimetablePath.html?method=subjectmarksList">Back</a></span>
							</div> -->
			</div>
			
			
			<!-- pop up -->

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Download</h4>
						</div>
						<div class="modal-body">
							<span id="excelDownload"><img src="images/xl.png"
								class="xl"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
								id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div>
			

			<div id="gradeOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
				<!-- <div id = "markstable"></div> -->
					<div class="form-group">
						<table style="background: #fff;" class="table" id="allstudent">
						<tr>
						<th style="font-size: 15px; text-align: center;" >2nd Language</th>
						<th style="font-size: 15px; text-align: center;">3rd Language</th>
						<th style="font-size: 15px; text-align: center;">Field</th>
						<th style="font-size:15px; text-align: center;">Orderby</th>
						
					   </tr>
					  
	                    <logic:present name="languagelist" scope="request">
					   	<logic:iterate id="languagelist" name="languagelist" scope="request">
                         <tr>

					   		<td class="sno"><bean:write name="languagelist" property="secondlanguage"/></td>

					   		<td class="subjectcode"><bean:write name="languagelist" property="thirdlanguage"/></td>
					   		
					   		
					   		<td class="attandancestatus"><select class="languagelist">
					   		   <option value="present">Name</option>
							   <option value="absent">Admission No</option>
							   <option value="absent">Gender</option>
							</select></td>
					   		
					   		
					   		<td class="subjectname"><bean:write name="languagelist" property="orderedlist"/> </td>
						
							
					   </tr>
					   	</logic:iterate>
					   </logic:present>
					  </table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="JS/newUI/jquery.js"></script>
	<script src="JS/newUI/bootstrap.min.js"></script>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>
