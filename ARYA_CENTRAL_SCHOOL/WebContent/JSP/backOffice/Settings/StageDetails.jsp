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
<script type="text/javascript" src="JS/backOffice/Settings/StageDetails.js"></script>
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
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
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet" type="text/css">

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">



<style>
.glyphicon:hover {
	cursor: pointer;
}
/* .modal-body {
	text-align: center;
} */
</style>

<style>
.download:hover{

cursor: pointer;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
.btn {
    font-size: 15px;
    padding: 3px;
    height: 28px;
}
</style>

</head>

<body>

	 <div class="col-md-10 col-md-offset-2" id="div1">
	                <div id="dialog"></div>
	               <div class="searchWrap">
		                    <div class="col-md-3" id="div2">
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Stage details</span>
			</p>
		</div>

		<form id="myForm" action="adminMenu.html?method=StageList" method="post">
		<div class="col-md-5">
		
		<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;font-size: 14px;">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearlist" name="accyear" class="form-control" >
										<logic:present name="AccyearId" scope="request">
											<logic:iterate id="AccyearId" name="AccyearId">
											<option value='<bean:write name="AccyearId" property="accId" />'><bean:write name="AccyearId" property="accName" /></option>
											</logic:iterate>
										</logic:present>
								
										</select>
										<input type="hidden" id="haccyearstage" value='<logic:present name="stageAccyear" ><bean:write name="stageAccyear" /></logic:present>' />
									</div>
								</div>
		</div>
		<div class="input-group col-md-4">

			<input type="text" class="form-control" name="searchvalue" id="searchvalue" Placeholder="Search......" style="height:35px;" value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'> 
			<span class="input-group-btn">
				<button class="btn btn-default" type="submit"  value="Submitform">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
		
		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />

													</logic:present>'></input>	
		
		</form>
		
		</div>
		
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
					class="validateTips"></span></a>
			</div>
		</div>
	
		
		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Stage
						Details
					</h3></a>



				<div class="navbar-right">

					
					<a	href="adminMenu.html?method=addstage">
					 <span class="buttons" >Add</span></a>  </span>
					 <span id="editStageId" class="buttons" >Edit</span>


					<span class="buttons" id="delete">Delete</span>


					<!--  <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span> -->


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
									<span id="xls"><img src="images/xl.png"
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

				
					
					<logic:present name="StageDetails" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' name='selectall' style='text-align:center' id='selectall'/></th>
							<th>Stage Name</th>
							<th>Amount</th>
							<th>Description</th>
							
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="StageDetails" name="StageDetails">
								<tr>
								<td><input type='checkbox' name='select' class='select'  style='text-align:center' id='<bean:write name="StageDetails" property='stageCode'/>' /></td>								
								<td><bean:write name="StageDetails" property='stageName'/></td>
								<td><bean:write name="StageDetails" property='amount'/></td>
								<td><bean:write name="StageDetails" property='description'/></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
					<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
						</div><div class='pagination pagelinks'></div>
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
		})
	</script>
</body>
</html>