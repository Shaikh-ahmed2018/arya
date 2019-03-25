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

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="CSS/Admin/StudentNew.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Student/individualStudentSearch.js"></script>
<script type="text/javascript" src="JS/backOffice/Student/StudentSearch.js"></script>
<link href="CSS/newUI/custome.css" rel="stylesheet">

<script>

$(document).ready(function(){
	alert("hiii")
	$("#class").change(function(){
		getSection();
		
	});
	
	$("#location").change(function(){
		
      	getClassList();
    	getTerm();
	});		
	

});

	
function getSection(){
	
	datalist={
			"classidVal" : $("#class").val(),
			"locationId":$("#location").val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#section').html("");
			
			$('#section').append('<option value="ALL">' + " ALL "	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#section').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});

	
}
function getClassList(){
	var locationid=$("#location").val();
	datalist={
			"locationid" : locationid
	},

	$.ajax({

		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#class').html("");

			$('#class').append('<option value="ALL">' + "ALL"+'</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#class').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}
function getTerm(){
	datalist={
			"locId" : $("#location").val(),
			"accId" : $("#accyear").val(), 
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getTerm",
		data : datalist,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			var j;
			var len= result.data.length;
			$('#termName').html("");
			$('#termName').append('<option value="all">' +"ALL"+ '</option>');
			for ( j = 0; j <len; j++) {
				$('#termName').append('<option value="'
						+ result.data[j].termname+ '">'
						+ result.data[j].termId
						+ '</option>');
			}
		}
	});
}
</script>



<style>
.glyphicon:hover {
	cursor: pointer;
}
/* .modal-body {
	text-align: center;
} */
</style><style>
.buttons{

vertical-align: -28px;

}
</style>

<style>
.download:hover{

cursor: pointer;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
</style>
<style type="text/css">
.table-striped tbody td,.table-striped thead th{
 width:177px;
  
   
}
#pageLoader{
position: absolute;
width: 900px;
height: 600px;
margin: auto;
top: 0;
left: 0;
right: 0;
bottom: 0;
z-index: 999;
overflow-y:auto; 
}
</style>

</head>

<body>

<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		
		<p><img src="images/addstu.png" /><span id="pageHeading">General SMS</span></p>
				<div class="panel-body clearfix"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
				</div>

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title" style="vertical-align: super;"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;General SMS</h4></a>
						
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="location" name="location" class="form-control" required>
											<option value="all">----------Select----------</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
					
									
								
								<!--div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
									
									<select class="form-control" onkeypress="HideError()" 
											name="classname" id="classname">
											<option value="all">ALL</option>
										</select>
									</div>
								</div-->
								<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;"> Class</label>
							<div class="col-xs-7">
							<select class="form-control" onkeypress="HideError()" 
									name="classname" id="class">
									<option value="all">----------Select----------</option>
							</select>
							</div>
						</div>
								<!--div class="form-group clearfix">
								
								<div class="col-xs-9" id="smsid" style="margin-left: 155px;">
									<input type="radio" value="stud" name="stud" class="sorting" id="stud" checked="checked">Studying
									<input type="radio" value="tc" name="tc"  class="sorting1" id="tc">TC
									<input type="radio" value="all" name="all"  class="sorting2" id="all">ALL
									
									
								</div>
							</div-->
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Message</label>
									<div class="col-xs-7">
										<textarea  id="Message"name='StudentMessage' class="form-control" style=" width: 100%; height: 7%;"></textarea>
									
									</div>
								</div>
											

						</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								id="inputnames" style="text-align: right;">Academic Year</label>
							<div class="col-xs-7">
								<select id="accyear" name="accyear" class="form-control"
									required>
									
									<logic:present name="AccYearList">
										<logic:iterate id="AccYear" name="AccYearList">
											<option
												value="<bean:write name="AccYear" property="accyearId"/>">
												<bean:write name="AccYear" property="accyearname" />
											</option>
										</logic:iterate>
									</logic:present>
								</select>
							</div>
						</div>
						
								
								<!--div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="all">ALL</option>
										</select>
									</div>
								</div-->
							
							<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Division</label>
							<div class="col-xs-7">
								<select id="section" name="section" class="form-control">
									<option value="all">ALL</option>
								</select>
							</div>
						</div>
						
								<!-- div class="form-group clearfix">
								
								<div class="col-xs-9" id="orderby" style="margin-left: 138px;">
									<input type="radio" value="MS" name="sorting" class="sorting" id="MS" checked="checked"style="margin-left:10px">Multiple Standard
									<input type="radio" value="GS"  name="sorting" class="sorting"style="margin-left:10px" id="GS">Group SMS
									<input type="radio" value="GS"  name="sorting" class="sorting"style="margin-left:10px" id="IS">Individual SMS
									
									
								</div>
							</div-->
							
								<div class="form-group clearfix">
									<div class="col-xs-12">
									<p align="right">
								<button type="button" class="btn btn-info" id="preview" >Preview</button>
								<button type="button" class="btn btn-info" id="send" >Send</button>
								<button type="reset" class="btn btn-info" id="resetbtn" >Reset</button>
								</p>
								</div>
								</div>
							
							
							
							</div>
						<input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
							
				<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 20pt; color: #5d5d5d;">	
					
					
						<table class="table table-striped" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' name='selectall' id='selectall'/>All</th>
							<th>S.No</th>
							<th>Admission No</th>
							<th>Name</th>
							<th>Father Name</th>
							<th>SMS Number</th>
							<th>Message</th>
							</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
				
					
					
					</div>
       
	   
					</div>
					
						</div>
					</div>
				</div>
			</div>
	</div>

	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>