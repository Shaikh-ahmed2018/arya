<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<title>eCampus Pro</title>

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
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />

<link href="CSS/newUI/custome.css" rel="stylesheet">
<script src="JS/backOffice/Reports/ReportCard.js"></script>

<style>
.modal-body {
	text-align: center;
}
</style>
<style>
.buttons {
	vertical-align: -28px;
}
</style>
</head>
<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">

		<p>
			<img src="images/addstu.png" />&nbsp;<span style="font-size: 16pt;">Report Card</span>
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
					class="successmessage"></span></a>
			</div>
		</div>
		
		<p id="parent1" style="display: none;">
			<a href="">Expand all</a>
		</p>
		<div class=" -group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">

					
						<a data-toggle="collapse" data-parent="#accordion2"
							href="#collapseOne" style="color: #767676"><h4 class="panel-title" id="beforeparent"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Report Card
						</h4></a>
					

					<div class="navbar-right">
						<span class="buttons" id="download" style="top:6px;">Download</span>
					</div>
				</div>

			<!-- Filters -->
			<input type='hidden' id = 'hiddenaccyear' value = '<logic:present name = 'accYear' scope = 'request'><bean:write name = 'accYear'></bean:write></logic:present>'/>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt" style="padding: 15px;">
				<div class="tab-row" style="margin-bottom: 125px;">
					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="location" name="location" class="form-control" required>
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
									name="classname" id="class">
									<option value="all">ALL</option>
							</select>
							</div>
						</div>
						
						<div class="form-group clearfix" id="termsid">
							<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Scholastic  Areas</label>
							<div class="col-xs-7" id="checkboxlist" style="margin-top: 6px;">
								<span id="spanterms"><input type="checkbox" id="term1" style="margin-right: 5px;" >Term I
								<input type="checkbox" id="term2" style="margin-right: 5px; margin-left: 9px;" >Term II</span>
								<span id="spanaccyear"><input type="checkbox" id="reportaccyear" style="margin-right: 5px; margin-left: 9px;" >Academic Year</span>
								<input type="hidden" id="examstypeid"/>
								<input type="hidden" id="examstypeidterm2"/>
								<input type="hidden" id="examstypeidaccyear"/>
								<input type="hidden" id="examstypeprefix"/>
								<input type="hidden" id="examstypeprefixforterm2"/>
								<input type="hidden" id="examstypeprefixforterm2"/>
							</div>
						</div>
					</div>

					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
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
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Division</label>
							<div class="col-xs-7">
								<select id="section" name="section" class="form-control"
									required>
									<option value="all">ALL</option>
								</select>
							</div>
						</div>
						
						<!-- Scholastic  Areas: -->
						
					
						<div class="col-xs-4"></div>
					<div class="col-xs-8">
						<button type="button" class="btn btn-info " id="search" >Search</button>
						<button type="reset" class="btn btn-info " id="reset" >Reset</button>
					</div>
					</div>
					</div>
				</div>
			</div>
			
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">	
					<table class="table" id="allstudent">
						<thead>
							<tr>
								<th>Select</th>
								<th>Admission No</th>
								<th>Student Name</th>
								<th>Roll No</th>
								<th>Class</th>
								<th>Division</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
	<span  class='numberOfItem'></span>	
	</div><div class='pagination pagelinks' style='top:-9px'></div>
					
				</div>
				
			</div>
			
			</div>
		</div>
		</div>
	<!-- Button trigger modal -->
	<span>&nbsp;</span>
	
	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>

</html>
