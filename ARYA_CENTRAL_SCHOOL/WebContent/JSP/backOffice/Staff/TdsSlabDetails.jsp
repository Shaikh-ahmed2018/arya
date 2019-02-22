<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>

<head>  

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>eCampus Pro</title>
<script type="text/javascript" src="JQUERY/jquery-1.9.1.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>


<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="JS/backOffice/Staff/TdsSlabDetails.js"></script>


<style>
.download:hover {
	cursor: pointer;
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}
td, th {
    padding: 0;
    border: 2px solid #ccc;
    
}
</style>
<style>
.buttons {
	vertical-align: 5px;
}
input{
max-width: 100px;}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span><span
					id="pageHeading">Slab Details</span>
			</p>
		</div>

		<form id="searchForm"
			action="teacherregistration.html?method=searchStaffDetails"
			method="post">
			<div class="input-group col-md-4" style="margin: 20px 0px;">

				<input type="text" name="searchname" id="searchname"
					class="form-control" Placeholder="Search......"> <span
					class="input-group-btn">
					<button class="btn btn-default" type="button" id="search">
						<i class="fa fa-search"></i>
					</button>
				</span>

			</div>
		</form>
		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>Slab
						Details
					</h3></a>
				<div class="navbar-right">



					<a href="teacherregistration.html?method=getTeachers"> <span
						class="buttons" data-toggle="tooltip" data-placement="bottom"
						title="Add">Save </span>
					</a>

				</div>
				<script>
					$(document).ready(function() {
						$('[data-toggle="tooltip"]').tooltip();
					});
				</script>
			</div>
			<!-- pop up -->

			<!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
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
							 <span
								id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div> -->
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<div   style="  padding: 12px;" >
						<table cellpadding="5" cellspacing="0" border="1" id=""
							width="100%" style="border: 2px solid #212121;">
							<tr>
								<th colspan="4" style="font-size: 16px; text-align: center;">Up
									To 60 Years Of Age</th>
							</tr>
							<tr>
								<th style="font-size: 16px; text-align: center;">Net Income Range</th>
								<th style="font-size: 16px; text-align: center;">I/Taxes Rates</th>
								<th  style="font-size: 16px; text-align: center;">Education Cess</th>
								<th  style="font-size: 16px; text-align: center;">Secondary and Higher Secondary Education Cess</th>
							</tr>
							
							
							
							
							
							<tr>
								<th style="font-size: 16px; text-align: center;">Up To <input type="text"
									name="tax_up_to" id="tax_up_to" onchange ="ClickTaxUpTo()"
									maxlength="50"
									value=""/></th>
								<th style="font-size: 16px; text-align: center;">NIL</th>
								<th  style="font-size: 16px; text-align: center;">NIL</th>
								<th  style="font-size: 16px; text-align: center;">NIL</th>
							</tr>
							
							
							
							
							
							
							
							<tr>
								<th style="font-size: 16px; text-align: center;">Rs.<input type="text"
									name="tax_up_to_from1" id="tax_up_to_from1"
									maxlength="50"
									value=""/>to<input type="text"
									name="tax_up_to_to1" id="tax_up_to_to1"
									maxlength="50" style =" max-width: 140px;"
									value="" onchange="ClickTaxUpToSecondline()"/></th>
									
								<th style="font-size: 16px; text-align: center ;">5% Of Total Income - <input type="text"
									name="incometax1" id="incometax1"
									maxlength="50"
									value=""/></th>
									
								<th  style="font-size: 16px; text-align: center;     padding: 0px;"><input type="text"
									name="secondcolumnFirstTax" id="secondcolumnFirstTax"
									maxlength="50"  placeholder="%"
									value=""/> On Income Tax</th>
								<th  style="font-size: 16px; text-align: center;"><input type="text"
									name="thirdcolumnFirstTax" id="thirdcolumnFirstTax"
									maxlength="50"  placeholder="%"
									value=""/> On Income Tax</th>
							</tr>
							
							
							
							
							
							
							
							<tr>
								<th style="font-size: 16px; text-align: center;">Rs.<input type="text"
									name="tax_up_to_from2" id="tax_up_to_from2"
									maxlength="50"
									value=""/>to<input type="text"
									name="tax_up_to_to2" id="tax_up_to_to2"
									maxlength="50" style ="    max-width: 140px;"
									value="" onchange="ClickTaxUpToThirdline()"/></th>
									
								<th style="font-size: 16px; text-align: center;">12500+20% Of Total Income - <input type="text"
									name="incometax2" id="incometax2"
									maxlength="50"
									value=""/></th>
									
								<th  style="font-size: 16px; text-align: center;     padding: 0px;"><input type="text"
									name="thirdcolumnsecondTax" id="thirdcolumnsecondTax"
									maxlength="50"  placeholder="%"
									value=""/> On Income Tax</th>
								<th  style="font-size: 16px; text-align: center;"><input type="text"
									name="fourthcolumnsecondTax" id="fourthcolumnsecondTax"
									maxlength="50"  placeholder="%"
									value=""/> On Income Tax</th>
							</tr>
							
							
							
							
							
							
							
							
							
							<tr>
								<th style="font-size: 16px; text-align: center;">Above Rs.<input type="text"
									name="tax_up_to_from3" id="tax_up_to_from3"
									maxlength="50"
									value=""/></th>
									
								<th style="font-size: 16px; text-align: center;">112500+30% Of Total Income - <input type="text"
									name="incometax3" id="incometax3"
									maxlength="50"
									value=""/></th>
									
								<th  style="font-size: 16px; text-align: center;     padding: 0px;"><input type="text"
									name="secondcolumnThirdTax" id="secondcolumnThirdTax"
									maxlength="50"
									value="" placeholder="%"/> On Income Tax</th>
								<th  style="font-size: 16px; text-align: center;"><input type="text"
									name="thirdcolumnThirdTax" id="thirdcolumnThirdTax"
									maxlength="50"  placeholder="%"
									value=""/> On Income Tax</th>
							</tr>
							
							<tr>
								<th colspan="4" style="font-size: 16px; text-align: center;">NOTE:  Surcharge 10 % for taxable income between Rs. 50 lacs to 1 Crore and @ 15 % for taxable incoe <1 crore applicable			
</th>
							</tr>
							
							
						</table>
					</div>
				</div>
				<br />
			</div>
		</div>
	</div>

	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script>
</body>
