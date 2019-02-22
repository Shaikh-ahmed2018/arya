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
<script type="text/javascript" src="JS/backOffice/Library/ListGenarelSettings.js"></script>
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
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div id="dialog"></div>
		<div class="col-md-8" id="div2">

			<p style="">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">General Settings</span>
			</p>
		</div>


		
			<div class="input-group col-md-4" style="margin-bottom: 10px;margin-top: 12px;">
			<!-- 	<input type="text" name="searchname" id="searchname" style="height: 35px;"
					class="form-control" Placeholder="Search......"
					value=""/>  -->	
					<div class= "form-group clearfix" style="margin-bottom:33px;"></div>
				
			</div>
			
				
		
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		<div class="successmessagediv"  style="display: none;" >
			<div class="message-item" style="text-align: center;">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="panel panel-primary clearfix">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;vertical-align: text-top;"><h3 class="panel-title"  style="color: #767676;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp; General Details
					</h3></a>
					

				<div class="navbar-right">

							<a href="LibraryMenu.html?method=generalSettings"> 
							<span class="buttons" id="add">Add</span></a> 
							<span class="buttons" id="editId">Edit</span>
							<span class="buttons" id="delete">Delete</span>
										
											
				</div>
			</div>
						
				<!-- 	<div class="col-md-6"></div> -->
			<!-- pop up -->


			<div id="collapseOne" class="accordion-body collapse in">
		
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 16px; color: #5d5d5d;margin-top: 20px;">
									<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Search </label>
							<div class="input-group col-xs-7">
								<input type="text" name="searchname" id="searchValue"
									style="margin-left: 15px;width: 275px;" class="form-control"
									Placeholder="Search......" 
									value="<logic:present name='searchnamelistValue' scope='request'><bean:write name='searchnamelistValue'/></logic:present>">
								
							</div>
						</div>
							
							</div>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="" style="padding: 15px;">
				
					<div class="row">
			<div id="collapseOne" class="panel-collapse collapse in ">
					<div class="panel-body own-panel">
						<div class="row"></div>
                        
						<table class='table' id='allstudent' style="width: 100%">
							<thead>
								<tr>
									<th style="text-align: center"><input type='checkbox' name='select' class='selectall' id='selectall' style='text-align: center' /></th>
									<th>Item Description</th>
									<th>Number Of Day Book Lend</th>
									<th>Amount Per Day</th>
									<th>Fine Amount</th>
									<th>Fine Increment onces in day</th>
									</tr>
							</thead>
							<tbody>

							</tbody>

						</table>
						
					

			</div>
     	 </div>
     	 
					
					<div class='pagebanner' class="panel panel-primary clearfix">
				
					<select id='show_per_page' >
					<option value='50'>50</option>
					<option value='100'>100</option>
					<option value='200'>200</option>
					<option value='300'>300</option>
					<option value='400'>400</option>
					<option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
					<div class='pagination pagelinks' style="margin-left:650px; padding-bottom:20px;"></div>
					
				</div>
				<br />
			</div>
		</div>
	</div>
</div>
</div>
</div>


<!-- 
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script> -->
</body>
</html>