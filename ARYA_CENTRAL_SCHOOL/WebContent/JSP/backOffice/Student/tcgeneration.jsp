<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
<link href="CSS/Admin/Admission/StudentNew.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="JS/backOffice/Student/tcgeneration.js"></script>
<style>
.form-group{
margin-bottom: 5px;}  
.save:hover {
	cursor: pointer;
}

#individualstudenttable th:nth-child(2),th:nth-child(3),th:nth-child(6){
  text-align: center;
  }
#individualstudenttable td:nth-child(2),td:nth-child(4){
  text-align: center;
  width: 20%;
  }
#studenttable th:nth-child(2),th:nth-child(4),th:nth-child(5){
width:20%;
  }
#studenttable th:nth-child(2),th:nth-child(4),th:nth-child(5){
  text-align: center;

  }

#studenttable td:nth-child(2),td:nth-child(4),td:nth-child(5),td:nth-child(6){
text-align:center;
  }

fieldset { 
	width:512px;
    display: block;
   /*  margin-left: auto;
    margin-right: 2px; */
    margin-bottom: 5px;
    padding-bottom: 0.625em;
    padding-left: 7px;
    padding-right: 0px;
    border: 0.5px solid #ccc;
   
}
legend {
    display: inline-block;
    width: auto;
    padding: 0;
    margin-bottom: 0px;
    font-size: 16px;
    line-height: inherit;
    color: #333;
    border: 0;
   }
   .tab-pane.active{
display: block !important;
}
</style>
<style>
.buttons{

vertical-align:-5px;

}
.navbar-right {
    top: -3px;
}
.panel-primary>.panel-heading{
margin-bottom: 0px;
}
form .panel.panel-primary.panel-list{
padding-bottom: 0px;

}    
#course{
display: none;
}
@media (min-width:320px) and (max-width:767px){
br{
display: none;
}

.slideTab{
	padding:10px;
	font-family: Roboto Medium;
    font-size: 14px;
    font-weight: lighter;
}
}

</style>

<script>
function handle(e) {
	if (e.keyCode === 13) {
		$("input:checkbox").prop('checked', false);
		e.preventDefault(); // Ensure it is only this code that rusn
		searchList();
	}
}

function handle1(e) {
	if (e.keyCode === 13) {
		$("input:checkbox").prop('checked', false);
		e.preventDefault(); // Ensure it is only this code that rusn
		studentDemotedListSearch();
	}
}


</script>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">TC Generate</span>
			</p>
	
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;TC Generate</h4></a>
						
					</div>
					
					<div id="successmessages" class="successmessagediv"
			style="display: none;">
			<div class="message-item">
				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessage"></span></a>
			</div>
		</div>

		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

<div id="dialog"></div>	
					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
					
						<div>
						<div class="slideTab clearfix">
						<div class="tab">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#notGeneratedDiv"  id="notGenerated" class="notGenerated">TC not Generated</a></li>
								<li><a data-toggle="tab"  href="#GeneratedDiv" id="Generated" class="Generated">TC Generated</a></li>
								<span class="buttons" id="generatetc" style="float: right;margin-right: 20px;margin-top: 5px;">Generate TC/Download</span>
								<!-- <span class="buttons" id="notgeneratetc" data-target="#myModal1" data-toggle="tooltip" data-placement="bottom" title="Download"
								style="float: right;margin-right: 20px;margin-top: 5px;width:124px;text-align: center;" hidden="hidden">Download</span> -->
								<span class="buttons" id="course" style="float: right;margin-right: 20px;margin-top: 5px;width:124px;text-align: center;" hidden="hidden">Course & Conduct </span>
								<span class="buttons" id="download" style="float: right;margin-right: 20px;margin-top: 5px;width:124px;text-align: center;" hidden="hidden">Download </span>
							 	<span class="buttons" id="TCCancel" style="float: right;margin-right: 20px;margin-top: 5px;width:124px;text-align: center;" hidden="hidden">TC Cancel </span>
							</ul>

									<div class="modal fade" id="myModal" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel">
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
													<span id="excelDownload" hidden><img src="images/xl.png"
														class="xl"></span>
													<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --> <span
														id="pdfDownload"><img src="images/pdf.png"
														class="pdf"></span>
												</div>

											</div>
										</div>
									</div>



									<div id="notGeneratedDiv" class="tab-pane active" style="display: none;">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap" style="margin-top: 10px; margin-bottom: -5px;">
									<div class="col-md-8 clearfix" id=div2></div>
									<div class="col-md-6 clearfix" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											<option value="all">ALL</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
									
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
									
									<select class="form-control" onkeypress="HideError()" 
											name="classname" id="classname">
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Sort by</label>
								<div class="col-xs-7">

									<select class="form-control" onkeypress="HideError()"
										name="sortingby" id="sortingby">
										<option value="Alphabetical">Alphabetical Order</option>
										<option value="Gender">Gender Wise</option>
										<option value="Admission">Admission No.</option>
									</select>

								</div>
							</div>
							
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search By</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" onkeypress="handle(event)"
										value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
									</div>
								</div>

								
							</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
						
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
							
								<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"></label>
								<div class="col-xs-7" id="orderby">
									<input type="radio" value="ASC" name="sorting" class="sorting" id="ASC" checked><p1>Ascending</p1>
									<input type="radio" value="DESC" style="margin-left:30px" name="sorting" class="sorting" id="DESC" title="Male"><p2>Descending</p2>
									
									<input type="radio" value="ASC" name="sorting1" class="sorting" id="Female" checked hidden><p3 hidden>Female</p3>
									<input type="radio" value="DESC" style="margin-left:30px" name="sorting1" class="sorting" id="Male" hidden><p4 hidden>Male</p4>
								</div>
							</div>
							
								<p align="center"style="margin-left:17%">
								<button type="button" class="btn btn-info" id="search" >Search</button>
								<button type="reset" class="btn btn-info" id="resetbtn" style="height:28px;" >Reset</button></p>
							</div>
	
			<input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
				<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">	
					
					
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type="checkbox" name="selectall" style="text-align:center" id="selectall"></th>
							<th>Admission No</th>
							<th>Student Name</th>
							<th>Roll No</th>
							<th>Class and Division</th>
							<th>Admission Class</th>
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
						
							
							<div id="GeneratedDiv" class="tab-pane" style="display: none;">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap" style="margin-top: 10px; margin-bottom: -5px;">
									<div class="col-md-8" id=div2></div>
									<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								 
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="locationname1" name="locationnid" class="form-control" required>
											<option value="all">ALL</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
									
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
									
									<select class="form-control" onkeypress="HideError()" 
											name="classname" id="classname1">
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Sort by</label>
								<div class="col-xs-7">

									<select class="form-control" onkeypress="HideError()"
										name="sortingby" id="sortingby1">
										<option value="Alphabetical">Alphabetical Order</option>
										<option value="Gender">Gender Wise</option>
										<option value="Admission">Admission No.</option>
									</select>

								</div>
							</div>
							
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search By</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue1" Placeholder="Search......" onkeypress="handle1(event)"
										value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
									</div>
								</div>
								
							</div>
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
										<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid1" name="accyear" class="form-control" required>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
						
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid1" name="sectionid" class="form-control" required>
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
							
								<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"></label>
								<div class="col-xs-7" id="orderby1">
									<input type="radio" value="ASC1" name="sorting2" class="sorting" id="ASC1" checked><p5>Ascending</p5>
									<input type="radio" value="DESC1" style="margin-left:30px" name="sorting2" class="sorting" id="DESC1" title="Male"><p6>Descending</p6>
									
									<input type="radio" value="ASC1" name="sorting3" class="sorting" id="Female1" checked hidden><p7 hidden>Female</p7>
									<input type="radio" value="DESC1" style="margin-left:30px" name="sorting3" class="sorting" id="Male1" hidden><p8 hidden>Male</p8>
								</div>
							</div>
							
								<p align="center"style="margin-left:17%">
								<button type="button" class="btn btn-info" id="search1" >Search</button>
								<button type="reset" class="btn btn-info" id="resetbtn1" style="height:28px;" >Reset</button></p>
							</div>
							
						</div>
						<div id="demotedTable"></div>
						
					</div>
					
				</div>
				
				
					</div>
					
					
			</div>
		</div>
	</div>
</div>
</div>
</div>
</div>
</body>
					
</html>
