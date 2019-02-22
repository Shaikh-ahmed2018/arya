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
<script type="text/javascript" src="JS/backOffice/Events/PrizeLevelSetting.js"></script> 
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
table#allstudent thead tr {
    background-color: #f5f5f5 !important;
}
</style>

<style>

.controls.style {
	margin-top: -32px;
}

.searchWrap {
	padding: 6px;
}

#allstudent thead tr,#allstudent thead th:nth-child(9){
border:none;
}
#allstudent tbody,#allstudent tbody td:nth-child(9){
border:none;
}

#allstudent th:nth-child(8){
width:60px;
}
#allstudent thead tr, #allstudent thead th:last-child
{
background-color:#fff !important;
}

#allstudent tbody tr, #allstudent tbody tr td:last-child
{
background-color:#fff !important;
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

.multiple{
height: auto !important;
}
.glyphicon-plus:before {
	content: "\2b";
	margin-right: -6px;
}
</style>
<body>
	<div class="col-md-10 col-md-offset-2" id="div1">
		<div id="dialog2"></div>
		<div class="searchWrap">
			<div id="div2"><p><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Prize Level Setting</span></p>
			</div>
		</div>
		
		
		
<!-- Pop up starts -->
			<div id="stageSetting" style="display: none">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
						</div>
					</div>
				 </div>



					<div class="col-md-12" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
						<div class="col-md-6">
						<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right;">Event Name</label>
							<div class="col-xs-7">
					<input type="hidden"  id="eventIdHidden" value=""/>
						<select id="eventName" style="width:200px;" onchange="HideError();" name="eventName" class="form-control event"> 
							<option value="">----------Select----------</option>
								<logic:present name="eventList">
									<logic:iterate id="name" name="eventList" >
										<option value='<bean:write name="name" property="eventId"/>'><bean:write name="name" property="eventName"/></option>
									</logic:iterate>
								</logic:present>
							</select>
							<input type="hidden" id="hiddenCriteriaId" value=""/>
							</div>
						</div>
			
			
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5" style="text-align: right;"> Category</label>
						<div class="col-xs-7">
							<select id="category" style="width:200px;"  onchange="HideError();" name="" class="form-control cat"> 
											<option value="">----------Select----------</option>
							</select>
							<input type="hidden" id="hiddenCateg" value=""/>
						</div>
					</div> 
					
						<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5" style="text-align: right;"> Program Name</label>
						<div class="col-xs-7">
							<select id="programmeName" style="width:200px;" onchange="HideError();" name="" class="form-control prog"> 
											<option value="">----------Select----------</option>
						</select>
						<input type="hidden" id="hiddenprog" value=""/>
						</div>
					</div>  
					
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right;">Description </label>
							<div class="col-xs-7">
								<textarea rows="4" id="description" cols="19"></textarea>
							</div>
					</div>
				</div>




			<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; ">
							<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-5" style="text-align: right;">Seq No</label>
								<div class="col-xs-5">
									<input type="text" name="" id="seqNo" onchange="HideError();"  maxlength="4" onkeypress="return CheckIsNumeric(event);" class="form-control" />
									<input type="hidden" id="hiddenSeq" value=""/>	
								</div>
							</div>
							
								<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-5" style="text-align: right;">Prize Level</label>
								<div class="col-xs-5">
									<input type="text" name="" id="prizelevel" onchange="HideError();"  maxlength="4" onkeypress="return CheckIsNumeric(event);" class="form-control" />
									<input type="hidden" id="hiddenprize" value=""/>	
								</div>
							</div>
							
							<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-5" style="text-align: right;">Points</label>
								<div class="col-xs-5">
									<input type="text" name="" id="points" onchange="HideError();"  maxlength="4" onkeypress="return CheckIsNumeric(event);" class="form-control" />
										<input type="hidden" id="hiddenpoints" value=""/>
								</div>
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
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Prize Level Setting </h3></a></div>

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

						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								align="right" id="inputnames" >Category<span style="color: red;"></span></label>
							<div class="col-xs-7">
									<select name="categoryList" id="categoryList" class="form-control">
									<option value="">All</option>
									</select>
							</div>
							</div>
							
							
							
							
						</div>

	<div class="col-md-6" id="txtstyle">
	
	
	<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								align="right" id="inputnames" style="margin-top: 15px;">Programme Name<span style="color: red;"></span></label>
							<div class="col-xs-7">
									<select name="programmeList" id="programmeList" class="form-control" style="margin-top: 18px;">
									<option value="">All</option>
									</select>
							</div>
							</div>
							
	<!-- <div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								align="right" id="inputnames">Prize Level<span style="color: red;"></span></label>
							<div class="col-xs-7">
									<select name="" id="prizelist" class="form-control">
									<option value="">All</option>
									</select>
							</div>
							</div> -->
							
						</div>
						
						
	
	    </div>

					
			

		<div id="" class="panel-collapse collapse in ">
						<table style="width: 100%;" class='table' id='allstudent'>
						<thead>	<tr>
								<th>Sl.No.</th>
								<th>Event Name</th>
								<th>Category</th>
								<th>Programme Name</th>
								<th>Prize Level</th>
								<th>Seq No</th>
								<th>Points</th>
								<th>Description</th>
								<th style="min-width: 33px;"><span id="addgroup" class="glyphicon glyphicon-plus"></span></th>
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
					<div class='pagination pagelinks' style='margin-top: 15px;'></div>
					</div>
				</div>
			</div>
</div>
		</div>
</body>
</html>