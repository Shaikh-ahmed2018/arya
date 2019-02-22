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

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Library/SearchIssueDetails.js"></script>

<style>

.select{
    padding: 5px;
    font-size: 14px;
    background-color: #f5f5f5;
    vertical-align: 5px;
    color: #fff;
    border-radius: 3px;
    }
.fsetRight{
	width:610px;
	margin-bottom: 15px;
	border: 0.5px solid #ccc;
  	padding: 5px;
}

.fieldset { 
	width:445px;
    display: block;
   /*  margin-left: auto;
    margin-right: 2px; */
    margin-bottom: 13px;
    padding-bottom: 0.625em;
    padding-left: 7px;
    padding-right: 0px;
    border: 0.5px solid #ccc;
    height: 99px;
    margin-top: -11px;
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
#editStudent:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="searchWrap">
			<div id="div2">

				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Search Issue Details</span>
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
						style="color: #767676;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>
						&nbsp;&nbsp;Search Issue Details
					</h3>
				</a>

				<div class="navbar-right">
				
				
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBRUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
										
										<span class="select" id="library" style="cursor: pointer; color: #767676;"> 
										<select style="background-color: white; margin-top: -3px; margin-left: -153px;">
												<option>---GoTo---</option>
												<option>SubscriberDetails</option>
												<option>StockDetails</option>
												<option>IssueStatement</option>
												<option>IssueReturn</option>
											</select>
											
											</span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
				
		<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBRDWL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
												 <span id="print" class="buttons">Print</span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
					</logic:present>	 
										 
												 
						<span id="back" class="buttons">Back</span>
					
					
					
					
					
				</div>
			</div>
			<!-- pop up -->


			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt" style="padding: 15px;">
					<div class="row">
						<div class="col-md-5">
							<fieldset class="fieldset">
								<legend
									style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 15px; color: #767676;">Subscriber:</legend>
								<div class="form-group clearfix"
									style="margin-left: 48px; max-height: 106px; margin-top: 20px; font-size: 11pt;">
									<label style="width: 100px;"> <input type="radio" class="radio-inline" style="margin-bottom: 5px;"
										name="requested_by" id="student" class="cencession"
										value="student" />&nbsp;Student
									</label> <label style="width: 100px;"><input type="radio"
										class="radio-inline" style="margin-bottom: 5px;"
										name="requested_by" class="cencession" id="employee"
										value="employee" />&nbsp;Employee&nbsp;&nbsp;&nbsp; </label> <label
										style="width: 86px;"> <input type="radio"
										class="radio-inline" name="requested_by"
										style="margin-bottom: 5px;" id="other" class="cencession"
										value="other" />&nbsp;Others
									</label>

								</div>
							</fieldset>

							<fieldset class="fieldset">
								<legend
									style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 14px; color: #767676;">Search
									By:</legend>
								<div class="col-xs-9" id="radiostyle" style="margin-left: 11px">
									<label style="width: 160px;"><input type="radio"
										class="radio-inline" name="requested_by" class="cencession"
										id="accession" value="accession" selected />&nbsp;Accession
										No &nbsp;&nbsp;&nbsp; </label> <label> <input type="radio"
										style="margin-bottom: 5px;" class="radio-inline"
										name="requested_by" id="title" class="cencession"
										value="title" />&nbsp;Title
									</label> <label style="width: 160px;"> <input type="radio"
										class="radio-inline" style="margin-bottom: 5px;"
										name="requested_by" id="subscriber" class="cencession"
										value="subscriber" />&nbsp;Subscriber No
									</label> <label> <input type="radio" class="radio-inline"
										style="margin-bottom: 5px;" name="requested_by" id="name"
										class="cencession" value="name" />&nbsp;Name
									</label>

								</div>
							</fieldset>

						</div>

						<div class="col-md-7" id="txtstyle">

							<fieldset class="fsetRight" style="min-height: 88px;">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group clearfix">
											<label for="inputPassword" class="control-label col-xs-5"
												style="text-align: left; line-height: 35px;">School
												Name</label>
											<div class="col-xs-7">
												<select id="locationname" name="locationnid" class="form-control">
													<option value="all">All</option>
													<logic:present name="locationList">
												      <logic:iterate id="Location" name="locationList">
													   <option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												     </logic:iterate>
											      </logic:present>
												</select>
											</div>
										</div>

										<div class="form-group clearfix">
											<label for="inputPassword" class="control-label col-xs-5"
												style="text-align: right; line-height: 35px;" id="classnamess">Class</label>
											<div class="col-xs-7">
												<select class="form-control" onkeypress="HideError()" 
											              name="classname" id="classname">
											               <option value="all">ALL</option>
										         </select>
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group clearfix">
											<label for="inputPassword" class="control-label col-xs-5"
												style="text-align: right; line-height: 35px; padding: 0px">Academic
												Year</label>
											<div class="col-xs-7">
												<select id="Acyearid" name="accyear" class="form-control">
													<option value="all">All</option>
													<logic:present name="AccYearList">
												         <logic:iterate id="AccYear" name="AccYearList">
													      <option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
												</select>
											</div>
										</div>

										<div class="form-group clearfix">
											<label for="inputPassword" class="control-label col-xs-5"
												style="text-align: right; line-height: 35px; padding: 0px" id="sectionidname">Division</label>
											<div class="col-xs-7">
												<select id="sectionid" name="sectionid" class="form-control">
													<option value="all">All</option>
												</select>
											</div>
										</div>
									</div>
								</div>
							</fieldset>

							<div class="row">
								<div class="col-md-12">
									<fieldset class="fsetRight" style="margin-top: -14px;">
										<legend
											style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 15px; color: #767676;">Search</legend>
										<div class="form-group clearfix"
											style="margin-left: 35px; max-height: 106px; margin-top: -7px; font-size: 15px;">

											<label style="width: 150px;"> <input type="radio"
												class="radio-inline" style="margin-bottom: 5px;"
												name="requested_by" id="start" class="cencession"
												value="start" />&nbsp;Starts With
											</label> <label style="width: 150px;"> <input type="radio"
												class="radio-inline" style="margin-bottom: 5px;"
												name="requested_by" id="any" class="cencession" value="any" />&nbsp;Any
												Where
											</label> <label style="width: 120px;"><input type="radio"
												class="radio-inline" style="margin-bottom: 5px;"
												name="requested_by" class="cencession" id="end" value="end" />&nbsp;End
												With&nbsp;&nbsp;&nbsp; </label>

										</div>

										<div class="form-group clearfix" style="margin-left: 23px;">
											<label for="inputPassword" class="control-label col-xs-5"
												style="text-align: left; line-height: 35px; width: 67px;">Search</label>
											<div class="col-xs-7">
												<input type="text" style="width: 315px" class="form-control"
													id="searchvalue" Placeholder="Search......"
													onkeypress="handle(event)"
													value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
											</div>
										</div>
									</fieldset>
								</div>
							</div>
						</div>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in "
						role="tabpanel" aria-labelledby="headingOne">
						<table class='table' style="margin-right: 250px;" id='allstudent'
							width='100%' style="margin-top:50px">

							<thead>
								<tr>
									<th><input type="checkbox" id="selectAll" /></th>
									<th>SubscriberNo.</th>
									<th>Name</th>
									<th id="classrow">Class</th>
									<th id="divrow">Division</th>
									<th>AccessionNo</th>
									<th>Title</th>
									<th>Author</th>
									<th>IssueDate</th>
								</tr>
							</thead>

							<tbody>

							</tbody>

						</table>
						<div class='pagebanner'>
							<select id='show_per_page'><option value='50'>50</option>
								<option value='100'>100</option>
								<option value='200'>200</option>
								<option value='300'>300</option>
								<option value='400'>400</option>
								<option value='500'>500</option>
								<option value='500'>500</option></select>
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