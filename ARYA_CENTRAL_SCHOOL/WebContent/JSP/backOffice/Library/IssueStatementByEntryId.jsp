<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Library/GotoIssueStatementByEntryId.js"></script>
<title>eCampus Pro</title>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

#individualstudenttable th:nth-child(1),th:nth-child(2),th:nth-child(3),th:nth-child(4),th:nth-child(5){
  text-align: center;
  }
  
 #individualstudenttable td:nth-child(1),td:nth-child(2),td:nth-child(3),td:nth-child(4),td:nth-child(5){
  text-align: center;
  }

#trash:hover {
	cursor: pointer;
}

.download:hover {
	cursor: pointer;
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}

.navbar-right span {
	margin-right: -4px;
	position: relative;
	top: 6px;
	vertical-align: text-top;
}

.anchor a:hover {
	text-decoration: underline;
}

.tooltip {
	position: relative;
	display: inline-block;
	border-bottom: 1px dotted black;
}

.tooltip .tooltiptext {
	visibility: hidden;
	width: 120px;
	background-color: black;
	color: #fff;
	text-align: center;
	border-radius: 6px;
	padding: 5px 0;
	/* Position the tooltip */
	position: absolute;
	z-index: 1;
}

.buttonstyle {
	border: none;
	background: transparent;
}

.tooltip:hover .tooltiptext {
	visibility: visible;
}

#allstudent th:nth-child(1) {
	max-width: 50%;
}

#allstudent th:nth-child(2) {
	width: 410px;
}

#allstudent th:nth-child(3) {
	width: 300px;
}

#allstudent th:nth-child(4), td:nth-child(4) {
	text-align: center;
}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-6" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Issued Statement</span>
			</p>
		</div>



		<div class="input-group col-md-6">

			<label class="hedderstyle form-control"
				style="margin: 20px 0px; width: 20% !important; border: none; font-family: Roboto Medium; font-size: 14px; font-weight: lighter; background: transparent; color: #767676 !important;"></label>

			<label
				style="margin: 20px 0px; width: 21%; border: none; font-family: Roboto Medium; font-size: 14px; font-weight: lighter; background: transparent; color: #767676 !important;"
				class="form-control"></label>
		</div>



		<div class="successmessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessage"></span></a>
			</div>
		</div>

		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>



		<div class="panel panel-primary">
			<div class="panel-heading heading1">
				<a data-toggle="collapse" data-parent="#accordion2"
					href=".collapseOne1" style="color: #fff;"><h3
						class="panel-title"
						style="color: #767676; vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;
						Issued Details
					</h3></a>
				<div class="navbar-right" style="right: -8px;">

					<!-- <span class="buttons" id="iconsimg" data-toggle="modal"
						data-target="#myModal">Download</span> -->
					<span class="buttons" id="back" style="margin: 0px 12px 0px 0px;">Back</span>

				</div>
			</div>

			<!-- pop up -->

		<!-- 	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
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
			</div> -->

			<div class="col-md-6" id="txtstyle">

				<input type="hidden" id="EntryId"
					value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="entryId"/></logic:present>" />

				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">AccessionNo</label>
					<div class="col-xs-7">
						<input type="text" id="accesionno" name="accesionno"
							maxlength="25" class="form-control"
							value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="accessionNo"/></logic:present>' />
					</div>
				</div>

				<div class="form-group clearfix adminssionNo1">
					<label for="inputPassword" class="control-label col-xs-4"
						id="inputnames" style="text-align: right; line-height: 35px;">Title</label>
					<div class="col-xs-7">
						<input type="text" id="title" name="title"
							maxlength="25" class="form-control"
							value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="bookTitle"/></logic:present>' />
					</div>
				</div>

				<div class="form-group clearfix className1">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;"  id="inputnames" style="">CurrentStatus</label>
					<div class="col-xs-7">
						<input type="text" id="status" name="status"
							maxlength="25" class="form-control"
							value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="status"/></logic:present>' />
					</div>
				</div>

			</div>
			
			<div class="col-md-6 collapseOne" id="txtstyle">

				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;" id="inputnames">Author</label>
					<div class="col-xs-7">
						<input type="text" id="author" name="author" maxlength="25"
							class="form-control"
							value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="author"/></logic:present>' />
					</div>
				</div>

				<div class="form-group clearfix rollNo1">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;" id="inputnames">DDC</label>
					<div class="col-xs-7">
						<input type="text" id="ddc" name="ddc" maxlength="25"
							class="form-control"
							value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="ddc"/></logic:present>' />
					</div>
				</div>
				<input type="hidden" id="hidensize" value="<logic:present name="listsize"><bean:write name="listsize" /></logic:present>" />

				<div class="form-group clearfix othercontact1">
					<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Location</label>
					<div class="col-xs-7">
						<input type="text" id="othercontact" name="subscriberno"
							maxlength="25" class="form-control"
							value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="location"/></logic:present>' />
					</div>
				</div>

			</div>
			
			<!----------------------- table ------------------------>
                  <div class="allstudenttable" >
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<table class='table' style="margin-right: 250px;" id='allstudent'
							width='100%' style="margin-top:50px">
							
							<thead>
								<tr>
									<th>Sl.No</th>
									<th>SubscriberNo</th>
									<th>SubscriberType</th>
									<th>Issue Date</th>
								</tr>
							</thead>
							<tbody>
						<logic:present name="issuedetails">
						<logic:iterate name="issuedetails" id="issuedetails">
						<tr class ="issuedid" id="<bean:write name="issuedetails" property="issueId"/>">   <!---->
						<td> <bean:write name="issuedetails" property="slno"/></td>               
						<td> <bean:write name="issuedetails" property="subssciberNo"/></td>  
						<td> <bean:write name="issuedetails" property="subscriberType"/></td>
						<td> <bean:write name="issuedetails" property="issueDate"/></td>
						</tr>
						</logic:iterate>
						</logic:present>
				       </tbody>
				         
						</table>
					</div>
				</div>
				
				
		</div>
	</div>

</body>
</html>