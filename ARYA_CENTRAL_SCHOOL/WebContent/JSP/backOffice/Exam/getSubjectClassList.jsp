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
<script type="text/javascript" src="JS/backOffice/Exam/getSubjectClassMarks.js"></script>
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
margin-bottom: 10px;
}

.scrollbar{

	overflow-y:scroll; 
	height: 400px;
}
.form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control {
    background-color: #eee;
    opacity: 1;
    width: 196px;
}
</style>


<body>
<div id="dialog"></div>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<p style="margin-top: 5px; margin-bottom: 5px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Marks Entry - Subject Wise</span>
					
		</p>
		
		
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
			
		
		<div class="panel panel-primary">
		
			<div class="panel-heading clearfix">
			
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#" style="color: #fff;"><h3
						class="panel-title grade" style="color: #767676; line-height: 18px;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Subject Wise Marks
						List
					</h3></a>
					<div class="navbar-right">
		 				<span class="buttons" id="back" >Back</span>
					</div>

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
				<div class="panel-body" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				
				<div class='clearfix' id="inputcolor">
					<div class="col-md-6"  id="txtstyle" style="font-size: 11pt; color: #5d5d5d;">
			             <div class="form-group clearfix">
						 	<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">School Name</label>
									<div class="col-xs-5">
										<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear"
											class="form-control" value='<logic:present name="currentlocation" scope="request"><bean:write name="currentlocation" ></bean:write></logic:present>' />
									</div>
									<input type="hidden" name="hiddenlocid" id="hiddenlocid" value='<logic:present name="locid" scope="request"><bean:write name="locid"></bean:write></logic:present>'/>
						</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: left; line-height: 35px;">Academic Year</label>
									<div class="col-xs-5">
										<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear"
											class="form-control" value='<logic:present name="accyName" scope="request"><bean:write name="accyName" ></bean:write></logic:present>' />
									</div>
									<input type="hidden" name="hiddenaccyid" id="hiddenaccyid" value='<logic:present name="accyId" scope="request"><bean:write name="accyId"></bean:write></logic:present>'/>
								</div>
					</div>
				<div class="col-md-12">
					<div class="col-md-6"  id="txtstyle" style="font-size: 11pt; color: #5d5d5d;">
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Exam Code</label>
						<div class="col-xs-5">
						 <input type="text" readonly="readonly" name="examCode" onkeypress="HideError()" id="examCode" class="form-control" style="margin-left:-9px;" value='<logic:present name="examCode" scope="request"><bean:write name="examCode"></bean:write></logic:present>'/>
						</div>
						<input type="hidden" name="hiddenexamid" id="hiddenexamid" value='<logic:present name="examid" scope="request"><bean:write name="examid"></bean:write></logic:present>'/>
				</div>
			</div>	
			
			<div class="col-md-6"  id="txtstyle"
								style="font-size: 11pt; color: #5d5d5d;">
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Exam Name</label>
						<div class="col-xs-5">
						 <input type="text" readonly="readonly" name="examName" onkeypress="HideError()" id="examName" class="form-control" value='<logic:present name="examName" scope="request"><bean:write name="examName" ></bean:write></logic:present>'/>
						</div>
						
				</div>
			</div>	
			</div>	
		</div>
					
				<!-- <div id = "markstable"></div> -->
					<div class="form-group scrollbar">
						<table style="background: #fff;" class="table" id="allstudent">
						<thead>
						<tr>
						<th style="font-size: 15px; text-align: center;" >Sl.No</th>
						<th style="font-size: 15px; text-align: center;">Class</th>
						<th style="font-size: 15px; text-align: center;">Section</th>
						<th style="font-size:15px; text-align: center;">No.Of Subjects</th>
						<th style="font-size:15px; text-align: center;">Status</th>
					   </tr>
					   </thead>
					   <tbody>
					   <logic:present name="subjectClassList" scope="request">
					   	<logic:iterate id="subjectClassList" name="subjectClassList" scope="request">
					   		<tr>
					   		<td><bean:write name="subjectClassList" property="sno1" /></td>
					   		<td class="classId" id="<bean:write name="subjectClassList" property="classId"/>"><bean:write name="subjectClassList" property="classname"/></td>
					   		<td class="sectioId" id="<bean:write name="subjectClassList" property="section" />"><bean:write name="subjectClassList" property="sectionName"/></td>
					   		<td><bean:write name="subjectClassList" property="tot_strength"/></td>
							<td><span class="<bean:write name="subjectClassList" property="status"/>"><bean:write name="subjectClassList" property="status"/></span></td>
					   		</tr>
					   	</logic:iterate>
					   </logic:present>
					   </tbody>
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
