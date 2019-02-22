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
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="JS/backOffice/Events/ProgramScheduling.js"></script> 
<script type="text/javascript">
function CheckIsNumeric(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode > 31  &&(charCode < 48 || charCode > 57)) {
        /* document.getElementById("maxmarks").style.backgroundColor = "#FFB2B2"; */
        return false;
    }
    else {
        /* document.getElementById("maxmarks").style.backgroundColor = "#B2D8B2"; */
        return true;
    }
}
function Check(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode > 31 && charCode!=32 && charCode != 46 && charCode != 45 &&(charCode < 48 || charCode > 57)) {
        /* document.getElementById("maxmarks").style.backgroundColor = "#FFB2B2"; */
        return false;
    }
    else {
        /* document.getElementById("maxmarks").style.backgroundColor = "#B2D8B2"; */
        return true;
    }
}
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
.Not{
font-size:14px;
background-color:#FF0000;  
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
 
}
.Scheduled{
background-color: rgba(0, 158, 0, 0.66);
    min-width: 92px;
    display: inline-block;
    text-align: center;
    color: #fff;
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
#allstudent thead tr,#allstudent thead th:nth-child(10){
border:none;
}
#allstudent tbody,#allstudent tbody td:nth-child(10){
border:none;
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

.multiple{
height: auto !important;
}
.glyphicon-plus:before {
	content: "\2b";
	margin-right: -6px;
}
.add-on{
position: absolute;
top: 0;
right: 0;
}
.bootstrap-datetimepicker-widget.dropdown-menu{
z-index: 99999;
}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div1">
		
		<div id="dialog2" style="display:none;"></div>
		<div class="searchWrap">
			<div id="div2"><p><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Program Scheduling</span></p>
			</div>
		</div>
		
		
		
		
		
		
<!-- Pop up starts -->
			<div id="programScheduling" style="display: none">
			<div class="col-md-12">
				<div class="row">
				</div>
				
			
			<div class="col-md-10" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4" 
										style="text-align: right;">Event Name</label>	
									<div class="col-xs-7" >
									
										<select id="eventName" name="eventName" class="form-control event"> 
											<option value="">----select----</option>
												<logic:present name="eventList">
													<logic:iterate id="name" name="eventList">
														<option value='<bean:write name="name" property="eventId"/>'><bean:write name="name" property="eventName"/></option>
													</logic:iterate>
												</logic:present>
											</select>
											
									</div>
								</div>
								<input type="hidden" id="hiddenCriteriaId" value=""/>
								<input type="hidden" id="hiddenEventId" value=""/>
								<input type="hidden" id="hiddenProgramId" value=""/>
								<input type="hidden" id="hiddenProgramDate" value=""/>
								<input type="hidden" id="hiddenStage" value=""/>
								<input type="hidden" id="hiddenGreenRoom" value=""/>
						        <input type="hidden" id="hiddenProgTime" value=""/>
					
					
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-4"
							style="text-align: right;">Programme Name</label>
							<div class="col-xs-7">
							<select id="programmeList" name="" class="form-control event" disabled="disabled"> 
											<option value="">----select----</option>
												
											</select>
							</div>
					</div>
					
					
					
						<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-4"
							style="text-align: right;">Stage</label>
							<div class="col-xs-7">
							<select id="stageNameList" name="stageNameList" class="form-control event" disabled="disabled"> 
											<option value="">----select----</option>
												
											</select>
							</div>
					</div>
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-4"
							style="text-align: right;">Green Room</label>
							<div class="col-xs-7">
							<select id="greenRoomList" name="greenRoomList" class="form-control event" disabled="disabled"> 
											<option value="">----select----</option>
												
											</select>
							</div>
					</div>
					
						<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-4"
							style="text-align: right;">Programme Date</label>
							<div class="col-xs-7">
							<input type="text" class="form-control " 
											name="fromdate" id="FromDate" disabled="disabled"/>
								</div>
					</div>
						<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Program Time<font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<div id="datetimepicker3" class="input-append">
										<input type="text" data-format="hh:mm:ss" size="8"
											readonly="readonly" name="starttime" id="programTime" onkeypress="HideError()"
											class="form-control"
											value=""/>
											<span class="add-on"><!-- <img src="images/time2.png" width="30" height="8"  align="top"/> -->
													<img src="./images/time1.jpg" width="30" height="30"
											class="add-on" />
												</span>
										
									</div>
								</div>
								
							</div>
					
							<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-4"
							style="text-align: right;">Makeup Report</label>
							<div class="col-xs-7">
							<textarea rows="3" id="makeup"cols="35"></textarea>
												
							</div>
					</div>
					
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-4"
							style="text-align: right;">Back Stage Report</label>
							<div class="col-xs-7">
							<textarea rows="3" id="backStage" cols="35"></textarea>
												
							</div>
					</div>
				</div>	
				
		</div>
</div>
<!--Pop Up Ends  -->		
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
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Program Scheduling </h3></a>

					<div class="navbar-right" style="margin-top: -3px;">
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
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4" align="right" id="inputnames" style="margin-top: 15px;">Event Name</label>
							<div class="col-xs-7">
							<select id="eventNameList" name="eventNameList" class="form-control" style="margin-top: 18px;"> 
							<option value="">All</option>
							<logic:present name="eventList">
													<logic:iterate id="name" name="eventList">
														<option value='<bean:write name="name" property="eventId"/>'><bean:write name="name" property="eventName"/></option>
													</logic:iterate>
												</logic:present>
							</select>
							</div>
						</div>

						
						</div>

	<div class="col-md-6" id="txtstyle">
	<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" id="inputnames" style="margin-top: 15px;">Programme Date<span style="color: red;"></span></label>
							<div class="col-xs-7">
							<input type="text" class="form-control " style="margin-top: 18px;" name="fromdate" id="Fromdate" />
								</div>
							</div>
						</div>
	
	</div>

					
			

		<div id="collapseOne" class="panel-collapse collapse in ">
						<table style="width: 100%;" class='table' id='allstudent'>
						<thead>	<tr>
								<th>Sl.No</th>
								<th>Event Name</th>
								<th>Program</th>
								<th style="min-width: 79px;">Stage</th>
								<th style="min-width: 150px;" >Green Room</th>
								<th style="min-width: 171px;">Date & Time</th>
								<th style="min-width: 86px;">Makeup Report</th>
								<th style="min-width: 129px;">Back Stage Report</th>
								<!-- <th style="min-width: 104px;">Time</th> -->
								<th style="min-width:79px;">Status</th>
								<th style="min-width: 37px;">	</th>
								
								<!-- <span id="addgroup" class="glyphicon glyphicon-plus"></span> -->
							
							</tr>
						</thead>
						<tbody>
						
						</tbody>
						</table>
						<div style="margin-top: 12px;">
					<div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select>
							<span  class='numberOfItem'></span>
					</div >
					<div class='pagination pagelinks'></div>
					</div>
				</div>
			</div>
</div>

<!-- <script>
$(document).ready(function(){
$(".close").click(function(){
	$("#myModals").removeClass("in");
	$("#myModals").hide();
});
	$("#iconsimg").click(function(){
		/* if(validateloc()==false)
			{ */
			$("#myModals").removeClass("in");
		
			/* } */
		/* else
			 */{
			$("#myModals").addClass("in");
			$("#myModals").show();
			/* } */
		
	});
});
</script> -->

		</div>
</body>
</html>