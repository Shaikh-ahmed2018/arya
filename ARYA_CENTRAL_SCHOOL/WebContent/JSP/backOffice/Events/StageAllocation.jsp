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

<title>eCampus Pro</title>

<script type="text/javascript" src="JS/common.js"></script>


<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
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
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="JS/backOffice/Events/StageAllocation.js"></script> 
<script type="text/javascript">


</script>


<style>
.glyphicon:hover {
	cursor: pointer;
}
.buttons {
	top: 10px !important;
}
</style>

<style>

.controls.style {
	margin-top: -32px;
}

.searchWrap {
	padding: 6px;
}



#allstudent th:nth-child(8){
width:60px;
}

/* .deleteRow,#addgroup{
height:20px;
line-height: 16px;
position: absolute;
right: 0px;
top: 0;
bottom: 0;
margin: auto;
} */
#allstudent tbody td{
border: 1px solid #dedede;
    padding: 5px;
}
#allstudent thead th,#allstudent tbody td{
position: relative;
}
#addgroup,.deleteRow{
position: absolute;
left: 0;
right: 0;
top: 0;
bottom: 0;
margin: auto;
height: 20px;
width: 20px;
line-height: 20px;
font-size: 20px;
color: #989898;
padding: 0;	
}
#programNumbering select.multipleSelect{
width: 100%;
height: 250px !important;
}
.multiple{
height: auto !important;
}
.glyphicon-plus:before {
	content: "\2b";
	margin-right: -6px;
}
#programNumbering tbody tr td:first-child,#programNumbering thead tr th:first-child{
width: auto;
}
#programNumbering tbody tr td:nth-child(2){
 width: 100px;
    text-align: center;
    vertical-align: middle;
}
   
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div1">
		<div id="dialog"></div>
		<div class="searchWrap">
			<div id="div2"><p><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Stage Allocation</span></p>
			</div>
		</div>
		
		
		


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

		<div class="panel panel-primary clearfix">
			<div class="panel-heading clearfix">
	 			<a data-toggle="collapse" data-parent="#accordion2" href="#collapseOne"
					style="color: #fff;"><h3 class="panel-title class"
						style="color: rgb(118, 118, 118); vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Stage Allocation </h3></a>
						
						<div class="navbar-right" style="margin-top: -3px;display: none;">
						<span class="buttons" id="iconsimg" data-toggle="modal"
							data-target="#myModal" 
							data-placement="bottom"  style="top:-5px;margin-right:6px;">Download </span>
							<span class="buttons" id="print" data-toggle="modal"
							data-placement="bottom"  style="top:8px;">Print </span>
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

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body">

					<div class="col-md-6" id="txtstyle">
						</div>
</div>

<div id="collapseOne" class="panel-collapse collapse in ">
						<table style="width: 100%;" class='table allstudent' id='programNumbering'>
						<thead>	<tr>
								<th colspan="3"></th>
							</tr>
						</thead>
						<tbody>
						<tr>
						<td style="background-color: white;border-color: white;"> Event <select onchange=HideError(); id="event" ><option value="">-------select-------</option>
						<logic:present name="eventList">
													<logic:iterate id="name" name="eventList">
														<option value='<bean:write name="name" property="eventId"/>'><bean:write name="name" property="eventName"/></option>
													</logic:iterate>
												</logic:present>
												</select></td>
						<td style="background-color: white;border-color: white;"></td>
						<td style="background-color: white;border-color: white;"> Stage <select id="stage"><option value="">-------select-------</option>
						
												</select></td>
												
							</tr>				
						<tr>
						<td valign="top" style="background-color: white;text-align: left;"><span>
						<b>Unallocated Program</b></span><br>
						<select class="multipleSelect" id="original" multiple="multiple">
						<logic:present name="participantsList">
													<logic:iterate id="name" name="participantsList">
														<option value='<bean:write name="name" property="participantId"/>'><bean:write name="name" property="participantName"/></option>
													</logic:iterate>
												</logic:present>
												</select>
						
						
						<td style="background-color: white;"><input type="button" value="> " onclick="addIndividualParticipant();" style="width: 40px;"/><br><br>
						<input type="button" value="< " onclick="deselectIndivivdualParticipant();" style="width: 40px;" /><br><br>
							<input type="button" value="<<" onclick="deSelectParticipant();"  style="width: 40px;"/><br><br>
								<input type="button" value=">>" onclick="addParticipant();"  style="width: 40px;" />
						</td>
						
							<td valign="top" style="background-color: white;text-align: left;">
							<span>
							<b>Allocated Program</b>
							</span>
							<br>
							<select class="multipleSelect" id="copy" multiple="multiple"></select>
							</td>
							<tr>
							<td colspan="3" style="background-color: white;">
							<!-- <input type="button" value="Allocate" onclick="assign();" /> -->
							</td> 
							</tr>
							</td>
							
						
					
						</tr>
						<tr></tr>
						</tbody>
						</table>
					
				</div>
				
			</div>
</div>
		</div>
</body>
</html>