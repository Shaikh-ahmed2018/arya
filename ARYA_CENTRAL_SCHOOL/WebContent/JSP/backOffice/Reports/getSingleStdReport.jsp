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

<!-- <link href="CSS/newUI/examtimetable.css" rel="stylesheet"> -->

<style>
.modal-body {
	text-align: center;
}

#allstudents tbody tr{
display:none;
}
#allstudents tbody tr td,#allstudents tbody tr th{
    width: 270px;
    text-align: right;
}
#allstudents tbody tr:first-child,#allstudents tbody tr:nth-child(2){
display: block;
}
#allstudent tbody tr:nth-child(2){
display: none;
}
</style>
</head>



<body>


	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Student Fee Payment</span>
		</p>

		<div class="navbar-right">

					<a href="reportaction.html?method=downloadPDFfeeStatusReport"> <span class="buttons" data-toggle="tooltip" data-placement="bottom" title="Download" style = "margin-right: 0px;">PDF</span>
					</a>
					<a href="reportaction.html?method=downloadXLfeeStatusReport"> <span class="buttons" data-toggle="tooltip" data-placement="bottom" title="Download" style = "margin-right: 40px;">EXCEL</span>
					</a>
					
				</div>

		

								
						<div class="col-md-12 selecteditems">
								<br /> 
								
								<input type="hidden" id="haccyear" name="haccyear" value="" />
									
								<input type="hidden" id="hclass" name="hclass" value="" />
									
								<input type="hidden" id="hsection" name="hsection" value="" />
									
								<input type="hidden" id="hterm" name="hterm" value="" />
								
								
								
							
							
		<div id="diaplytable">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

					<logic:present name="feeStatusReport" scope="request">
					
					<input type="hidden" id="hideenId" value="studentlist" />
					<div class="accBody">
								<table class="table" id="allstudents" style="text-align: center;">
								
								<tr>
								<th>Student Name</th>
										<th>Total Opening Balance</th>
										<th>Total Paid Amount</th>
										<th>Total Balance</th>
								</tr>
								<logic:iterate name="feeStatusReport"  id="datalist" scope="request">
									<tr>
								 <td id="<bean:write name="datalist" property="studentId" />"><bean:write name="datalist" property="studentName" /></td> 
												<td><bean:write name="datalist" property="totalAmount" /></td>
											
												
												<td><bean:write name="datalist" property="paidAmount" /></td>
												<td><bean:write name="datalist" property="balanceAmount" /></td>
										</tr>		
												</logic:iterate>
							</table>
							</div>
							<div class="accBody">
								<table class="table" id="allstudent" style="text-align: center;">
									<tr class="headth">
										<th>Sno</th>
										<th>Fee Name</th>
										<th>Opening Balance</th>
										<th>Paid Amount</th>
										<th>Balance</th>
										<th>Paid Date</th>
									</tr>
									
									
										<logic:iterate name="feeStatusReport"  id="datalist" scope="request">
											<tr class="accordHead">
											
												<td><bean:write name="datalist" property="sno" /></td>
												<td><bean:write name="datalist" property="feeName" /></td>
												
												<td><bean:write name="datalist" property="openingfeeAmount" /></td>
												<td><bean:write name="datalist" property="feeAmountCollected" /></td>
												<td><bean:write name="datalist" property="blancefeeAmount" /></td>
												<td><bean:write name="datalist" property="paidDate" /></td>
												
												
												
											</tr>
										</logic:iterate>
									
									
									 
								</table>
							</div>
					
					</logic:present>
				</div>
				<br />
		</div>
							
					

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
