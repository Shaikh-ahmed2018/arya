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

<link rel="stylesheet"href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="CSS/Admin/StudentNew.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>
<script  type="text/javascript" src="JS/backOffice/Events/marksEntryNew.js"></script>
<script type="text/javascript">
</script>

<style>
.pagebanner {
    margin-top: 20px;
    }
#editStudent:hover {
	cursor: pointer;
}
.glyphicon-trash {
    margin-top: -2px;
    height: 20px;
}
#trash:hover {    
	cursor: pointer;
}
.download:hover{
cursor: pointer;
}
.allstudent thead th {
    border-top: 1px solid #dedede !important;
}

span.CLOSED{
background-color:#FF0000;
min-width:80px;
display:inline-block;
 color:#fff;
 font-weight: 900;
}
span.OPEN{
background-color:rgba(0, 158, 0, 0.66);
min-width:80px;
display:inline-block;
 color:#fff;
 font-weight: 900;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all{
overflow: scroll;
max-height: 300px;
}
.filteration{
padding: 15px;
}
textarea{
width:100%;
}
#allstudent tbody td{
border:1px solid #dedede
}
#allstudent tbody,#allstudent tbody td:nth-child(7){
border:none;
}
/* ************* table text css  */
table#judgemarks {
    border-bottom: 1px solid #DEDEDE;
    border-left: 1px solid #dedede;
}
input.judge, input.judgeT {
    font-size: medium;
    font-weight: 900;
    text-align: center;
}

input.form-control.text {
    width: 97px;
}
input[type="text"] { /* for inserting text in td   */
    border-bottom: 1px solid #939090;
    border-right: 1px solid #939090;
    width: 70px;
    }

</style>
<script>
function ShowTable(){
	$("#collapseOne").toggleClass("in");
}
</script>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
	<div id="dialog"></div>
	<div id="deleteDialog"></div>
	
	<div class="eventRegistration">
			<p><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Marks Entry New</span></p>
	</div>
<!--Pop Up Ends  -->		


		
		<input type="hidden" id="succmsg" value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />
	
			<div id="successmessages" class="successmessagediv" style="display: none;">
				<div class="message-item">
					 <a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"><logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present></span></a>
				</div>
			</div>
	
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><logic:present name='errorMessage'  scope='request' ><bean:write name='errorMessage'  /></logic:present></span></a>
				</div>
			</div>
	
		
		<div class="panel panel-primary clearfix">
			<div class="panel-heading">
				<a 	href="javascript:ShowTable();" >
					<h3 class="panel-title" style="color: #767676;vertical-align: text-top;"> <span class="glyphicon glyphicon-menu-hamburger"></span>
					&nbsp;&nbsp;Marks Entry Process
					</h3></a>
			<div class="navbar-right" style="margin-top:3px;">
					<a><span id="save" class="buttons">Save</span></a>
				</div>
			</div>
			<!-- pop up -->


<!-- Filteration Tabs  -->
<div id="filteration" class="filteration clearfix">
	<div class="panel-body" id="tabletxt">

		<div class="col-md-6" id="txtstyle">
				<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-4" align="right"
					id="inputnames">Event Name</label>	
					<div class="col-xs-7">
						<select id="eventName" name="eventName" class="form-control">
							<option value="">----------Select----------</option>
								<logic:present name="eventList">
									<logic:iterate id="name" name="eventList">
										<option value='<bean:write name="name" property="eventId"/>'><bean:write name="name" property="eventName"/></option>
									</logic:iterate>
							</logic:present>
						</select>
					</div>
				</div>
				
				<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-4" align="right"
					id="inputnames">Category Name</label>
					<div class="col-xs-7">
						<select id="categoryName" name="categoryName" class="form-control">
							<option value="">----------Select----------</option>
						</select>
					</div>
				</div>
				
				<!-- <div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-4" align="right"
					id="inputnames">Program Name</label>
					<div class="col-xs-7">
						<select id="progName" name="progName" class="form-control">
							<option value="">----select----</option>
						</select>
					</div>
				</div> -->
			</div>
		
		<div class="col-md-6" id="txtstyle">
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-4" align="right"
					id="inputnames">Stage Name</label>
					<div class="col-xs-7">
						<select id="stageName" name="stageName" class="form-control">
						<option value="">----------Select----------</option>
						</select>
					</div>
			</div>

						<!-- <div class="form-group clearfix wrap">
				<label for="inputPassword" class="control-label col-xs-4" align="right"
					id="inputnames">No.Of Judge</label>
					<div class="col-xs-7">
						<input type="text" id="No" name="judge" class="form-control text"/>
					</div>
			</div> -->

						<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-4" align="right"
					id="inputnames">Program Name</label>
					<div class="col-xs-7">
						<select id="progName" name="progName" class="form-control">
							<option value="">----------Select----------</option>
						</select>
					</div>
				</div>
		
					
					
					
			
		</div>
</div>
</div><!-- Filteration OVER  -->

<!--  <div id="collapseOne" class="panel-collapse collapse in table-responsive">
					<table class='table' id='allstudent' style="width: 100%">
						<thead>
							<tr>
								<th>Sl no</th>
								<th>School Name</th>
								<th>Academic year</th>
								<th>Event Name</th>
								<th>Event Date</th>
								<th>Registration Status</th> 
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
		                <div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select>
							<span  class='numberOfItem'></span>	

					</div>
					<div class='pagination pagelinks'></div>

</div>
 -->
<div id="collapseOne" class="panel-collapse collapse in table-responsive">
<div class="row">
	<div class="col-md-5">
				<table class='table allstudent' id='eventtotalmarks' style="width: 100%">
						<thead>
							<tr>
								<th>Sl no</th>
								<th>Chest Number</th>
								<th>TOTAL</th>
								<th>Marks Obtained</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
	</div><!-- col-md-5 -->

<div class="col-md-7">

		<table class='table allstudent' id='judgemarks' style="width: 100%">
						<thead>
							<tr>
								<th>Sl no</th>
								<th>Criteria</th>
								<th></th>
								
							</tr>
						</thead>
						<tbody>
						</tbody>
						<tfoot>
						<tr>
						<th colspan='2'>Submit Your Marks</th>
						<th><span class='buttons' id='submit'>OK</span>
						</tr>
						</tfoot>
					 <tfoot></tfoot>
					</table>


</div><!--col-md=7  -->










</div><!-- row  -->
</div><!--collapseOne  -->








</div>
</div>
</div>
</body>
</html>					
				
				
				

 
 