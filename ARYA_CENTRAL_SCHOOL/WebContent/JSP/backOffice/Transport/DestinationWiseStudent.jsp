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
<!-- <script type="text/javascript" src="/JS/backOffice/Student/StudentRegistration.js"></script> -->
<script type="text/javascript"
	src="JS/backOffice/Transport/DestinationwiseStudent.js"></script>


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
		<!-- <p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>All Student</span>
		</p> -->


		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Destination Wise
				Student Report </span>
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


		<form action="adminMenu.html?method=studentDestList" method="post">
			<p id="parent1" style="display: none;">
				<a href="">Expand all</a>
			</p>
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">

					<div class="panel-heading" role="tab" id="headingOne">

						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676;vertical-align: text-top;"><h4 class="panel-title" id="beforeparent"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Destination Wise Student Details
							</h4></a>
						

						<div class="navbar-right">

							<span class="buttons" id="iconsimg" data-toggle="modal"
								data-target="#myModal" data-toggle="tooltip"
								data-placement="bottom" title="Download">Download </span>


						</div>
						<script>
							$(document).ready(function() {
								$('[data-toggle="tooltip"]').tooltip();
							});
						</script>
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


					<!-- 					<div class="col-md-12 selecteditems"> -->
					<!-- 								<br /> <input type="hidden" id="haccyear" -->
					<%-- 									value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="transport"/></logic:present>" /> --%>
					<!-- 								<input type="hidden" id="hstream" -->
					<%-- 									value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="transporttypeId"/></logic:present>" /> --%>
					<!-- 								<input type="hidden" id="hclass" -->
					<%-- 									value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="transportlocationId"/></logic:present>" /> --%>





					<!-- Filters -->

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" id="tabletxt" style="padding: 15px;">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 50px;">Transport</label>
                                     <div class="col-xs-4" id="radiostyle" style="margin-top: -2px">
									<label><input type="radio" class="radio-inline" name="transport" class="transport" id="transportIdY" value="Y"/>Yes&nbsp;&nbsp;&nbsp;</label><label>
										<input type="radio" class="radio-inline" name="transport" id="transportIdN" class="transport" value="N"/>&nbsp;No</label>
								</div>
							</div>

							<div class="col-md-6" id="txtstyle">
								<div class="form-group clearfix" id="transportcategorylabel">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 50px;">Transport Category </label>
									<div class="col-xs-7">
										<select name="transcategory" id="transcategory" class="form-control"><option value=""></option>
                                        </select>
									</div>
								</div>
							
								<div class="form-group clearfix" id="transportlocationlabel">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">
										Transport Location </label>
									<div class="col-xs-7">
										<select name="translocation" id="translocation"
											class="form-control">
											<option value=""></option>

										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6" id="txtstyle"></div>
							<button type="submit" class="btn btn-info col-md-offset-5" id="search">Search</button>
							<!-- data-toggle="modal" data-target="#myModal" -->
							<br />




						</div>
						<button type="button" class="btn btn-info col-md-offset-5"
							id="searchs" style="display: none;">Search</button>
						<br /> <br />


						<logic:present name="desstudentlist">
							<input type="hidden" id="hideenId" value="studentDestList" />

							<display:table class="table" id="allstudent"
								name="requestScope.desstudentlist"
								requestURI="/adminMenu.html?method=studentDestList?"
								decorator="com.centris.campus.decorator.StudentRegistrationDecorator">




								<display:column property="sno" sortable="true" title="S.No"
									media="html"></display:column>

								<display:column property="studentLastName" sortable="true"
									title="Student Name <img src='images/sort1.png' style='float: right'/>"
									media="html"></display:column>

								<display:column property="classname" sortable="true"
									title="Class Name <img src='images/sort1.png' style='float: right'/>"
									media="html"></display:column>


								<%--                                        <display:column property="studClassId" sortable="true" --%>
								<%-- 										title="Student Class <img src='images/sort1.png' style='float: right'/>" --%>
								<%-- 										media="html"></display:column> --%>



								<display:column property="stage_name" sortable="true"
									title="Place of Destination <img src='images/sort1.png' style='float: right'/>"
									media="html"></display:column>




							</display:table>

						</logic:present>

					</div>
				</div>
			</div>
	</div>
	<!-- Button trigger modal -->

	</form>
	</div>

	<span>&nbsp;</span>


	<!-- jQuery -->
	<script src="js/jquery.js"></script>


	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>


	<script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>

</body>

</html>
