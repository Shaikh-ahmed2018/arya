<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
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
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/custome.css" rel="stylesheet">

<script type="text/javascript" src="JS/backOffice/Events/EventIdCardDesign.js"></script>

<style>
.modal-body {
	text-align: center; 
}
</style>
</head>
<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">


		
	<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Design Event ID Card</span>
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
		

		
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				
				<div class="panel panel-primary">

					<div class="panel-heading" role="tab" id="headingOne" >

						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title" style="vertical-align: text-top;" id="beforeparent"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp; Find Event Details
							</h4></a>
						


 
					</div>
					<!-- pop up -->

					<!-- Filters -->

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" id="tabletxt" style="padding: 15px;">

							<div class="col-md-6" id="txtstyle">
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Event Name</label>
									<div class="col-xs-7">
										<select id="eventList" name="eventList" class="form-control"
											required>
											<option value="">All</option>
											<logic:present name="eventList" scope="request">
												<logic:iterate id="event" name="eventList" scope="request">
													<option value='<bean:write name="event" property="eventId" />'><bean:write name="event" property='eventName'/></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
							
									
									
								
						
							</div>
							
							<div class="col-md-6" id="txtstyle">
								
								
							
						
								
							</div>
							
							
							
 				<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
					 <table class='table' id='allstudent' width='100%'>
					 <thead>
					 <tr><th>SL.NO.</th>
						<th style="text-align:center">EVENT NAME</th>
						
						<th style="text-align:center">EVENT ID CARD TYPE</th>
						</tr>
						</thead>
						<tbody>
						
						</tbody>
			
						</table>
						<span  class='numberOfItem'></span>	
						</div><div class='pagination pagelinks'></div>
						</div>
					</div>
				</div>
			</div>
			<!-- Button trigger modal -->

		
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

