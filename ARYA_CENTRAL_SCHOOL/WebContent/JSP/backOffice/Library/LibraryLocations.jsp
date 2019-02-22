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
<script type="text/javascript" src="JS/backOffice/Library/LibraryLocations.js"></script>
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
					id="pageHeading">Library location Details</span>
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
					href="#collapseOne" style="color: #fff;vertical-align: text-top;"><h3 class="panel-title"  style="color: rgb(118, 118, 118); vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp; Library location Details
					</h3></a>
					

				<div class="navbar-right">

							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBRADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									

											<a href="LibraryMenu.html?method=addLibraryLocations">
											<span class="buttons">Add</span> </a> 
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBMUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">			
											<span class="buttons" id="edit">Edit</span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBMDEL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">				
											<span class="buttons" id="deletelib">Delete</span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>				
				</div>
			</div>
						
				<!-- 	<div class="col-md-6"></div> -->
			<!-- pop up -->

			<div class="modal fade clearfix" id="myModal" tabindex="-1" role="dialog"
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
		
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 16px; color: #5d5d5d;margin-top: 20px;">
							
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="locationname"  name="locationnid" class="form-control" required>
											<option value="all">ALL</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
							</div>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="" style="padding: 15px;">
				
					<div class="row">
<div style="font-style: inherit; color: slategray; margin-top: 59px; font-size: 17px;">
				
					
					<div id="" class="panel-collapse collapse in ">
						<table class='table' id='allstudent'>
						<thead>	<tr>
						
						<th><input type='checkbox' name='checkbox_id' style='text-align:center' id='checkbox_id'  onclick='selectAll()'/></th>
								<th>School Name</th>
								<th>Library Locations</th>
								<th>Discription</th>
								
							</tr>
						</thead>
						<tbody>
						<logic:present name="librarylocationsDetails" scope="request">
						<logic:iterate id="librarylocationsDetails" name="librarylocationsDetails">
						<tr>
						<td>
						<input type='checkbox' name="checkbox_id" class="select" style='text-align:center' value='<bean:write name="librarylocationsDetails" property="librarylocid"/>'  onclick='selectAll()' />
						
						</td>
						<td>
						<bean:write name="librarylocationsDetails" property="schoolName"/>
						</td>
						<td>
						<bean:write name="librarylocationsDetails" property="libraryLocations"/>
						</td>
						<td>
						<bean:write name="librarylocationsDetails" property="description"/>
						</td>
						</tr>
						</logic:iterate>
						</logic:present>
						
						</tbody>
						</table>
					</div>
					
					<div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select>
								<span  class='numberOfItem'></span>	

					</div>
				<div class='pagination pagelinks'></div>
				<br />
			</div>
			
		</div>
	</div>


	
</body>
</html>