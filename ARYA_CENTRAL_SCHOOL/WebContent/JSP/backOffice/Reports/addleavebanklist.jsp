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


<script type="text/javascript" src="JS/backOffice/Reports/leavebank.js"></script>

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
.glyphicon:hover {
	cursor: pointer;
}

#classSave:hover {
	cursor: pointer;
}
</style>

<style>
.buttons{

vertical-align: 0px;

}
</style>


</head>

<body>
	<div>
		<div class="col-md-10 col-md-offset-2"
			style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
			<p style="margin: 20px 0px;">
				<img src="images/addstu.png" />&nbsp;<span id="pageHeading">New
					Leave Bank</span>
			</p>

			<form method="post">
			
			
				<logic:present name="successmessagediv" scope="request">
							<div class="successmessagediv">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-success bg-msg-succes"><span><bean:write
												name="successmessagediv" scope="request" /></span></a>
								</div>
							</div>
						</logic:present>

						<div class="errormessagediv" align="center" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips"></span></a>
							</div>
						</div>
			
			
			
			
				<div class="panel-group" id="accordion" role="tablist"
					aria-multiselectable="true">
					<div class="panel panel-primary">
						<div class="panel-heading" role="tab" id="headingOne">
							
								<a data-toggle="collapse" data-parent="#accordion2"
									href="#collapseOne" style="color: #767676;vertical-align: text-top"><h4 class="panel-title"><i
									class="glyphicon glyphicon-menu-hamburger"></i>
									&nbsp;&nbsp;Leave Bank Details
								</h4></a>
							
							
							
							<input type="hidden" id="hiddenAccYear"
								value='<logic:present name="editleavebank" scope="request"><bean:write name="editleavebank" scope="request" property="academicyear"/></logic:present>'>
			               <input type="hidden" name="editleavebank" id="snoid" value="<logic:present name="editleavebank" scope="request"><bean:write name="attnhidden" scope="request" /></logic:present>" />





							<div class="navbar-right">


								<span id="submit" class="buttons" data-toggle="tooltip"
									data-placement="bottom" title="Save">Save
								</span>&nbsp;  <span id="back" class="buttons" >Back</span></a>

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
								<div class="col-md-6" id="txtstyle"
									style="font-size: 11pt; color: #5d5d5d;">
									<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Academic
											Year</label>
										<div class="col-xs-7">


											<select id="academicyear" name="academicyear" onkeypress="HideError()"
												class="form-control">
												<option value="----Select----"></option>
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
										<br /> <br />
										<div class="form-group">
											<label for="inputEmail" class="control-label col-xs-4"
												style="text-align: right; line-height: 35px;">Paid
												Leave(PL)</label>
											<div class="col-xs-7">
												<input type="text" name="paidleave" class="form-control" onkeypress="HideError()"
													id="paidleave"
													value='<logic:present name="editleavebank"><bean:write name="editleavebank" property="paidleave"/></logic:present>'></input>


											</div>
										</div>

										<br />
										<div class="form-group">
											<label for="inputEmail" class="control-label col-xs-4"
												style="text-align: right; line-height: 35px;">Total
												Leave(TL)</label>
											<div class="col-xs-7">
												<input type="text" class="form-control" name="totalleave"
													id="totalleave" placeholder="" readonly="readonly"
													value="<logic:present name="editleavebank"><bean:write name="editleavebank" property="totalleaves"/></logic:present>" />
											</div>
										</div>
									</div>
								</div>

								<div class="col-md-6"
									style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
									<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Sick
											Leave(SL)</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" id="sickleave" onkeypress="HideError()"
												name="sickleave" placeholder=""
												value="<logic:present name="editleavebank"><bean:write name="editleavebank" property="sickleave"/></logic:present>" />
										</div>
									</div>
									<br />

									<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Casual
											Leave(CL)</label>
										<div class="col-xs-7">
											<input type="text" name="casualleave" class="form-control" onkeypress="HideError()"
												id="casualleave"
												value='<logic:present name="editleavebank"><bean:write name="editleavebank" property="casualleave"/></logic:present>'></input>


										</div>
									</div>
									<br />
									<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Allowed
											Leave(AL)</label>
										<div class="col-xs-7">
											<input type="text" name="allowedleave" class="form-control"
												id="allowedleave" readonly="readonly"
												value='<logic:present name="editleavebank"><bean:write name="editleavebank" property="permonth"/></logic:present>'></input>
										</div>
									</div>
								</div>

							</div>
						</div>
			</form>
		</div>
	</div>
</body>

</html>
