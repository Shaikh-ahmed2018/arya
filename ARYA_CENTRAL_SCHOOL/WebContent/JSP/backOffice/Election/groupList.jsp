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

<script type="text/javascript" src="JS/backOffice/Election/GroupList.js"></script>
<script type="text/javascript">

</script>

<style>
.glyphicon:hover {
	cursor: pointer;
}

.searchWrap {
    padding: 6px;
}
.pagination{
    margin: 2px 5px;
    padding: 0;
    vertical-align: top;
}
.pagebanner{
    margin: 15px 5px;
}
   
</style>

<style>
.download:hover{

cursor: pointer;
}

</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
	<div id="dialog"></div>
	<div class="searchWrap">
		<div id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Group Setting</span>
			</p>
			
		</div>


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


		<div class="panel panel-primary clearfix">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" style="color: #fff;"><h3 class="panel-title class" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Group Details
					</h3></a>
					
					<div class="navbar-right">
						<span class="buttons"  id="addnew">Add New</span>
					</div>
		
		</div>
			<!-- pop up -->


			
			<div id="classOne">
				<div class="panel-body">
				
					<div class="col-md-6" id="txtstyle">
					
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Academic Year</label>
									<div class="col-xs-7">
											<select id="academicYear" name="accyear" class="form-control">
											<option value="all">All</option>
										</select>
									</div>
								</div> 
								
							</div>
						</div>

					</div>

	<div  id="collapseOne" class="panel-collapse collapse in " >
					<table class='table' id='allstudent' style="width:100%">
						<thead>
							<tr>
								<th>Sl.No.</th>
								<th style="text-align: center">Academic Year</th>
								<th style="text-align: center">Group Name</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>

					</table>

					<div class='pagebanner'>
						<select id='show_per_page'>
							<option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option>
						</select>
							<span  class='numberOfItem'></span>	
					</div>
					<div class='pagination pagelinks'></div>

				</div> 	


				</div>
		</div>
	
	
</body>
</html>