
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
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="JS/backOffice/Library/Barcode.js"></script>
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


.input-group-btn {
    position: initial;
    font-size: 0;
    white-space: nowrap;
}

#barcode{float:left;height:100px;}
#barcode .bc_line{float:left;background:#000;height:100px;}
#barcode_num {text-align:center;font-size:20px;font-weight:bold;clear:left;}
</style>
<style>
.buttons {

	vertical-align: 5px;
}
#library {
	
	background-color: #f5f5f5;
}

.navbar-right span {
	margin-right: 3px;
}
.styleRadio {
    margin-top: 10px;
}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div id="dialog" style="display: none;">
		<div id="barcode"></div>
        <div id="barcode_num"></div>
		</div>
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;position: relative;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Barcode Generation</span>
			</p>
		</div>

		<form id="searchForm"
			action="teacherregistration.html?method=searchStaffDetails"
			method="post">
			<div class="input-group col-md-4" style="margin: 20px 0px;">

				<input type="hidden" name="searchname" id="searchname"
					class="form-control" Placeholder="Search......"
					value="<logic:present name="searchTerm"><bean:write name="searchTerm"/></logic:present>"/>
				<span class="input-group-btn" >
					<button class="" type="hidden" id="search" style="border: white;background-color: white">
						
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
						class="panel-title" style="color: #767676;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Barcode
						Generation
					</h3></a>
				<div class="navbar-right">
                    	<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBRDWL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
													<span class="buttons" id="deleteTeacher" style="cursor: pointer">Print Barcode </span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
					</logic:present>	 
				

				</div>
				<script>
					$(document).ready(function() {
						$('[data-toggle="tooltip"]').tooltip();
					});
				</script>
			</div>

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				 <div class="row"></div>
				 </br>
			     <div id="collapseOne" class="panel-collapse collapse in ">
					<div class="panel-body own-panel">
						<div class="row"></div>
                        
						<table class='table' id='allstudent' style="width: 100%">
							<thead>
								<tr>
									<th style="text-align: center"><input type='checkbox' name='select' class='select' id='selectall' style='text-align: center' /></th>
									<th>Accession Number</th>
									<th>Item Type</th>
									<th>Reg Date</th>
									<th>Book Title</th>
									<th>Author</th>
									<th>DDC</th>
									<th>No of Copies</th>
									<th>Location</th>
									<th>Current Status</th>
								</tr>
							</thead>
							<tbody>

							</tbody>

						</table>
						
					</div>

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
				<br />
			</div>
	
<textarea id="cssPrint" style="display: none;">
#barcode{float:left;height:100px;}
#barcode .bc_line{float:left;background:#000;height:100px;}
#barcode_num {text-align:center;font-size:20px;font-weight:bold;clear:left;}
body{-webkit-print-color-adjust: exact;margin:0;padding:0;}
</textarea>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script>
</body>