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

<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>


<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
	
<script type="text/javascript" src="JS/backOffice/Staff/DownloadPayslip.js"></script>
<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript">
	
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>
<style>
.glyphicon:hover{

cursor: pointer;
}
#classSave:hover {
	cursor: pointer;
}

.buttons{

vertical-align: -28px;

}
</style>


</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Download Payslip</span>
		</p>
		
		<center>
				<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<logic:present name="error" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
								name="error" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>


		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="errormessagediv1"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 50%; font-size: 11pt; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>
	</center>

		<form method="post">
			<div class="panel-group" id="accordion">
				<div class="panel panel-primary" style="margin-top: -14px;">
					<div class="panel-heading" >
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"> <h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;PayslipDetails
							</h4></a>
							

							<div class="navbar-right" >
								
								<!-- <img src="images/rightline.png" style=" margin-top: -5px;"> -->
								<span class="buttons" id="download" data-toggle="modal" data-target="#myModal">	Download</span>
							</div>
					</div>
					
		
					<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
						
						<div class="row" style="margin-top: 23px;margin-bottom: 23px;">
							<div class="col-md-6"  id="txtstyle"
								style="font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Select Month</label>
									<div class="col-xs-7">
									<!-- 	<input type="text" class="form-control" id="inputEmail"
											placeholder="First Name" required> -->
					<select name="monthname" id="monthid"
						class="form-control">
							<option value=""></option>
							<option value="Jan">January</option>
						<option value="Feb">February</option>
						<option value="Mar">March</option>
						<option value="Apr">April</option>
						<option value="May">May</option>
						<option value="Jun">June</option>
						<option value="Jul">July</option>
						<option value="Aug">August</option>
						<option value="Sep">September</option>
						<option value="Oct">October</option>
						<option value="Nov">November</option>
						<option value="Dec">December</option>
					</select>
									</div>
								</div>
								<br />

							</div>
							
							<div class="col-md-6"  id="txtstyle"
								style="font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Select Year</label>
									<div class="col-xs-7">
									
					<select name="accyear" id="accyear"
						class="form-control">
						
						<option value=""></option>
						<option value="2016">2016</option>
						<option value="2017">2017</option>
						<option value="2018">2018</option>
						<option value="2019">2019</option>
						<option value="2020">2020</option>
						
					</select>
									</div>
								</div>
								<br />

							</div>
							</div>
							</div>
						</div>
					
				</div>

	<input type="hidden" id="hmonth" value='<logic:present name="month"><bean:write name="month"/></logic:present>'/>
	<input type="hidden" id="hyear" value='<logic:present name="year"><bean:write name="year"/></logic:present>'/>


				
		</form>
		
	</div>
	</div>

</body>

</html>
