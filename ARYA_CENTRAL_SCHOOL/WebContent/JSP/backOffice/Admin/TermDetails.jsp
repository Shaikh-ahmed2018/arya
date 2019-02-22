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

<script type="text/javascript" src="JS/common.js"></script>


<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
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
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>

<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script src="JS/newUI/bootstrap.min.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Admin/termdetails.js"></script>
<link href="CSS/IdCard.css" rel="stylesheet" type="text/css">


<style>
#feeedit:hover {
	cursor: pointer;
}

#termedit:hover {
	cursor: pointer;
}

#delete:hover {
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
#allstudent tbody tr td{position:relative}
.feeconcession .navbar-right span:last-child {
    margin-right: 10px;
}
.note{
color:#f00;
font-size: 12px;
margin-left: 10px;

}
.glyphicon-trash{ line-height: 1}
</style>

</head>
<body class="feeconcession">


<div id="dialog"></div>

	<div class="col-md-10 col-md-offset-2" id="div1">
	  <div class="searchWrap">
		<div class="" id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Term Setup</span>
			</p>
		</div>
		




		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<logic:present name="errormessagediv" scope="request">
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><bean:write name="errormessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="errormessagediv1"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 35%; font-size: 13px; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>



		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips"></span></a>
			</div>
		</div>



		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Term
						List
					</h3></a>
				<div class="navbar-right">
					<a href="termfee.html?method=addtermdetails"><span
						class="buttons">Add</span> </a> 
						
				</div>
				
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
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

			<div class=row>
				<div class="col-md-6">
					<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">School Name <span style="color: red;">*</span></label>
									<div class="col-xs-7">
									<select id="locationname" name="locationId" class="form-control">
										<option value="">ALL</option>
										
										
							<logic:present name="locationList" scope="request">
										<logic:iterate id="Location" name="locationList">
											<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
										</logic:iterate>
										</logic:present>		
									</select>
									</div>
								<input type="hidden" name="schoolId" class="form-control" id="schoolId" value='<logic:present name="list"><bean:write name="list" property="locationId" /></logic:present>'></input>
							</div>
							
				</div>
				<div class="col-md-6">
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
								
				</div>
				
			</div>
					<%-- <logic:present name="termlist" scope="request">

						<display:table id="allstudent" class="table"
							name="requestScope.termlist"
							requestURI="/adminMenu.html?method=termList"
							decorator="com.centris.campus.decorator.TermMasterDecorator"
							pagesize="10">


							<display:column property="view"
								title="Sl.No." />
							<display:column property="accyear" sortable="true"
								title="Academic Year  <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="locationName" sortable="true"
								title="School Name  <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="termname" sortable="true"
								title=" Term Name  <img src='images/sort1.png' style='float: right'/>" />
							
							<display:column property="startdate" sortable="true"
								title="Start Date  <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="enddate" sortable="true"
								title="End Date  <img src='images/sort1.png' style='float: right'/>" />
							<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

							<display:setProperty name="paging.banner.placement"
								value="bottom"></display:setProperty>

						</display:table>


					</logic:present> --%>
					
					<logic:present name="termlist" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Sl.No.</th>
							<th>Academic Year </th>
							<th>School Name</th>
							<th>Term Name</th>
							<th>Start Date</th>
							<th>End Date</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="termlist" name="termlist">
								<tr>
								<td><span name='select' class='academic_Checkbox_Class' id='academicCheckBox_"+result.termlist[i].termid+",'></span><bean:write name="termlist" property='sno'/></td>
								<td><bean:write name="termlist" property='accyear'/></td>
								<td><bean:write name="termlist" property='locationName'/></td>
								<td><bean:write name="termlist" property='termname'/></td>
								<td><bean:write name="termlist" property='startdate'/></td>
								<td><bean:write name="termlist" property='enddate'/></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
			<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
   	<span  class='numberOfItem'></span>	
   	</div><div class='pagination pagelinks'></div>

				</div>
				<span class="note">Note : </span>
				<div class="note">1 - Modification not allowed in between records.</div>
				<span class="note">2 - Delete last record and enter new record.</span>			

			</div>
			<br />
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