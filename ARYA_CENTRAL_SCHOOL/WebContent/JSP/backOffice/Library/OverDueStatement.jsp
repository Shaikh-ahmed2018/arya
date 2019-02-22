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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Library/OverDuestatement.js"></script>

<style>
#individualstudenttable th:nth-child(1),th:nth-child(2),th:nth-child(3),th:nth-child(4),th:nth-child(5),th:nth-child(6),th:nth-child(7),th:nth-child(8),th:nth-child(9),th:nth-child(10),th:nth-child(11){
  text-align: center;
  }
#individualstudenttable td:nth-child(1),td:nth-child(2),td:nth-child(3),td:nth-child(4),td:nth-child(5),td:nth-child(6),td:nth-child(7),td:nth-child(8),td:nth-child(9),td:nth-child(10),td:nth-child(11){
  text-align: center;
  }
.select {  
	padding: 5px;
	font-size: 14px;
	background-color: #f5f5f5;
	vertical-align: 5px;
	color: #fff;
	border-radius: 3px;
}
.radiostyle{
margin-left: 15px;
}
 .staffdetail{
display:none;
} 
.fieldset3{
border: 0.5px solid #ccc;
border-radius: 5px;
}
.field3{
border: 0.5px solid #ccc;
}

.fieldset{
margin-top: 15px;
}
legend {
	display: inline-block;
	width: auto;
	padding: 0;
	margin-bottom: 0px;
	font-size: 16px;
	line-height: inherit;
	color: #333;
	border: 0;
}
.field1{
border: 0.5px solid #ccc;
border-radius: 5px;
}
.field2{
border: 0.5px solid #ccc;
border-radius: 5px;
}
#editStudent:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
.nest{
    width: 200%;
    }
    
.scrollbar{
    overflow-x: scroll;
    width:1073px;
}
.tablediv{
margin-top: 10px;
margin-bottom: 10px;
}
#mytable{
width: 1250px;
}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="searchWrap">
			<div id="div2">

				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Over Due Statement</span>
				</p>
			</div>

		</div>

		<input type="hidden" id="succmsg"
			value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />

		<div id="successmessages" class="successmessagediv"
			style="display: none;">
			<div class="message-item">
				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessage"></span></a>
			</div>
		</div>

		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne">
					<h3 class="panel-title"
						style="color: #767676; vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>
						&nbsp;&nbsp;Over Due Statement
					</h3>
				</a>

				<div class="navbar-right"  style="display: none;">
				     <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBRPDWD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
					<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" style="left:-9px;" >Download </span>
					<span  class="buttons" id="print" >Print </span>
					</logic:equal>
					</logic:equal>
					</logic:iterate>
					</logic:present>

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


			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt" style="padding: 15px;">
					<div class="row">
						<div class="col-md-12" id="txtstyle">
							<div class='col-md-6'>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right;">School Name</label>
										<div class="col-xs-7">
											<select id="locationName" name="locationName" class="form-control" >
												<option value="all">----------Select----------</option>
												<logic:present name="locationList">
													<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
												</logic:present>
										  </select>
									 </div>
							   </div>
							</div>
							</div>
					</div>
					<div class='row'>
						<div class='col-md-12'>
							<fieldset class='field2'>
								<legend style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 15px; color: #767676;">Subscriber:</legend>
								<div class='col-md-5'>
                                   		<label style="width: 100px;padding-left: 20px;"><input type="radio" class="radio-inline" style="margin-bottom: 5px;"name="requested_by" id="all" class="cencession"
											value="all" checked/>&nbsp;&nbsp;All
										</label>
										<label style="width: 100px;"> <input type="radio"
											class="radio-inline" style="margin-bottom: 5px;"
											name="requested_by" id="student" class="cencession"
											value="Student" />&nbsp;Student
										</label> 
										<label style="width: 100px;"><input type="radio"
											class="radio-inline" style="margin-bottom: 5px;"
											name="requested_by" class="cencession" id="staff"
											value="staff" />&nbsp;Staff&nbsp;&nbsp;&nbsp; </label> <label
											style="width: 86px;"> 
											<input type="radio"class="radio-inline" name="requested_by"
											style="margin-bottom: 5px;" id="Others" class="cencession"
											value="Others" />&nbsp;Others
										</label>
								
								</div>
								<div class='col-md-4'>
									<div class="form-group clearfix class" >
										<label for="inputPassword" class="control-label col-xs-5" id="stand" style="text-align: right;">Class</label>
											<div class="col-xs-7">
												<select id="classname" name="" class="form-control">
													<option value="all">ALL</option>
												</select>
											</div>
									</div>
									<div class="form-group clearfix staffdetail department" id="department" style="display: none;">
										<label for="inputPassword" class="control-label col-xs-5" class="stand" style="text-align: right;">Department</label>
											<div class="col-xs-7">
												<select id="dept" name="" class="form-control">
													<option value="all">ALL</option>
														<logic:present name="deplist">
												      	<logic:iterate id="deplist" name="deplist">
													   	<option value="<bean:write name="deplist" property="depId"/>"><bean:write name="deplist" property="depName"/></option>
												     	</logic:iterate>
											      		</logic:present>
												</select>
											</div>
									</div>
								</div>
								<div class='col-md-3'>
									<div class="form-group clearfix class divission" >
										<label for="inputPassword" class="control-label col-xs-5" class="stand" style="text-align: right;">Division</label>
										<div class="col-xs-7">
											<select id="section" name="" class="form-control">
												<option value="ALL">ALL</option>
											</select>
										</div>
									</div>
									<div class="form-group clearfix staffdetail designation" style="display: none;">
										<label for="inputPassword" class="control-label col-xs-5" class="stand" style="text-align: right;">Designation</label>
										<div class="col-xs-7">
											<select id="desg" name="" class="form-control">
												<option value="all">ALL</option>
												<logic:present name="DESIGNATIONLIST">
												<logic:iterate id="DESIGNATIONLIST" name="DESIGNATIONLIST">
													<option value="<bean:write name="DESIGNATIONLIST" property="desgid"/>"><bean:write name="DESIGNATIONLIST" property="desgname" /></option>
												</logic:iterate>
												</logic:present>
											</select>
										</div>
									</div>
							</div>
						</fieldset>			
					</div>
					</div>
				<div class="row fieldset">
					<div  class="col-md-12">
						<fieldset class='fieldset3'>
									<div class="col-md-6">
										<div class="form-group clearfix">
											<label for="inputPassword" class="control-label col-xs-7" style="text-align: left; line-height: 35px;margin-top: 8px;">List Of Item Overdue For More Than</label>
												<div class="col-xs-5" style="margin-top: 10px;">
													<input type="text" id="days" name="days" class="form-control" ></input>
												</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group clearfix">
											<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px; margin-top:8px; padding:0px">days as On</label>
												<div class="col-xs-7" style="margin-top: 10px;" >
													<input type="text" id="dates" name="days" class="form-control"></input>
												</div>
										</div>
									</div>
						</fieldset>
					</div>
				</div>
				<div class='row' style="margin-top: 5px;">
					<div class="col-md-12">
						<fieldset class="fieldset3">
							<legend style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 15px; color: #767676;">Order By</legend>
									<div class="col-md-3"></div>
									<div class="col-md-6">
											<label class='radiostyle'>
												<input type="radio" class="radio-inline" name="order_by" style="top: -3px;" id="subscriberno" class="cencession" checked="checked"
												value="subscriberno" />&nbsp;SubscriberNo
											</label>
											<label class='radiostyle'>
												<input type="radio" class="radio-inline" name="order_by"
												style="top: -3px;" id="name" class="cencession"
												value="name" />&nbsp;Name
											</label>
											<label class='radiostyle'>
												<input type="radio" class="radio-inline" name="order_by"
												style="top: -3px;" id="accno" class="cencession"
												value="accno" />&nbsp;AccessionNo
											</label>
											<label class='radiostyle'>
												<input type="radio" class="radio-inline" name="order_by"
												style="top: -3px;" id="title" class="cencession"
												value="title" />&nbsp;Title
											</label>
											<label class='radiostyle'>
												<input type="radio" class="radio-inline" name="order_by"
												style="top: -3px;" id="author" class="cencession"
												value="author" />&nbsp;Author
											</label>
										
									    </div>
								  	<div class="col-md-3"></div>
								      </fieldset>
									</div>
						</div>
                <div class='tablediv'>
               	 	<div class='scrollbar' id="scrollbars" style="display: none;">
						<div id="mytable" ></div>
					</div>
				</div>
		<div class='row'>
		<div class='col-md-12'>
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
	</div>
	</div>
</body>
</html>