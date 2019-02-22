<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>eCampus Pro</title>
<script type="text/javascript" src="JQUERY/jquery-1.9.1.js"></script>
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
<script type="text/javascript" src="JS/backOffice/Admin/ClassFeeSetup.js"></script>
</head>


<style>
.glyphicon:hover {
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
.Fee.Set{
background-color:rgba(0, 158, 0, 0.66);
}
.Fee.Not.Set{
background-color:rgba(255, 0, 0, 0.66);
}
</style>


<body>
<div id="dialog"></div>

	<div class="col-md-10 col-md-offset-2" id="div1">
	<div class="searchWrap">
		

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Class Fee Setup</span>
			</p>


		
		
	</div>
		<logic:present name="success" scope="request">

			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
								name="success" scope="request" />
					</span></a>
				</div>
			</div>
		</logic:present>
		<logic:present name="error" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="error" scope="request" />
					</span></a>
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

		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676; line-height: 18px;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Class
						Fee Setup

					</h3></a>
			
				
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

			<input type="hidden" id="classfeesetupsearchid"
				value='<logic:present name="classfeesetupSerchTerm"><bean:write name="classfeesetupSerchTerm"  /></logic:present>'></input>



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
										
										<logic:present name="locationList">
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

					<%-- <display:table name="requestScope.classSetupList"
						requestURI="/adminMenu.html?method=getClassFeeSetup" 
						export="false" class="table" id="allstudent" decorator="com.centris.campus.decorator.ClassFeeSetupDecorator">

						<display:column property="sno" title="Sl.No." class="${allstudent.accyearid},${allstudent.classid},${allstudent.locationId}"></display:column>

						<display:column property="accyear" sortable="true" title="Academic Year <img src='images/sort1.png' style='float: right'/>" />
						<display:column property="locationName" sortable="true" title="School Name <img src='images/sort1.png' style='float: right'/>" />
						<display:column property="classname" sortable="true" title="Class Name <img src='images/sort1.png' style='float: right'/>" />
				
						<display:column property="status" sortable="true" title="Status <img src='images/sort1.png' style='float: right'/>" />
						
						

					</display:table> --%>
					
					<logic:present name="classSetupList" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Sl.No.</th>
							<th>Academic Year </th>
							<th>School Name</th>
							<th>Class Name </th>
							<th>Status</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="classSetupList" name="classSetupList">
								<tr>
								<td class='<bean:write name="classSetupList" property='accyearid'/> <bean:write name="classSetupList" property='classid'/> <bean:write name="classSetupList" property='locationId'/>'><bean:write name='classSetupList' property="sno"/></td>
								<td><bean:write name="classSetupList" property='accyear'/></td>
								<td><bean:write name="classSetupList" property='locationName'/></td>
								<td><bean:write name="classSetupList" property='classname'/></td>
									<td><bean:write name="classSetupList" property='status'/></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
					
                   <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
	               <span  class='numberOfItem'></span>	
	             </div><div class='pagination pagelinks'></div>


				</div>
				<br />
			</div>
		</div>
	</div>
	<script src="JS/newUI/jquery.js"></script>
	<script src="JS/newUI/bootstrap.min.js"></script>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>
