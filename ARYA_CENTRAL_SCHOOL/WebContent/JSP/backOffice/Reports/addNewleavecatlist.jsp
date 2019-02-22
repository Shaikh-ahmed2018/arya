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

<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>


<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


<script type="text/javascript" src="JS/backOffice/Reports/leaveNewCat.js"></script>

<script type="text/javascript">
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>
<style>
.glyphicon:hover {
	cursor: pointer;
}


#classSave:hover {
	cursor: pointer;
}
.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons{

width : 600px !important;

}
.buttons{

vertical-align: 0px;

}
.addmore{

text-decoration: underline;

}
.addmore a:link {
    color: #337ab7;;
}

.shortName input[readonly] {background-color: purple !important;}
#myDialog {
overflow-y : scroll;
height :400px !important;
} 

#tableid tr td:nth-child(5){
width:100px;
}
#tableid tr td:nth-child(4){
width:100px;
}
#tableid tr td:nth-child(4) input{
width:80px;
}
#tableid tr td:nth-child(5) input{
width:80px;
}
/* #errormsg{
	text-align : center;
	margin-top : -18px;
	color :red;

} */
.errormessagediv1 .message-item a.msg-warning:before {
    display: inline-block;
    content: "\f071";
    font-family: "FontAwesome";
    font-size: 13px;
    font-weight: 200;
    margin-right: 8px;
    background: #cc0000;
    width: 20px;
    font-size: 15px;
    line-height: 15px;
    text-align: center;
    border-radius: 100%;
    color: #fff;
    margin-left: 8px;
   
 
}
.errormessagediv1 .message-item a.msg-warning {
 
    display: inline-block;
    margin: 3px 0px; 
    text-decoration: none;
    font-size: 13px;
    line-height: 21px;
    font-weight: 200;
    border: 1px solid rgba(0, 0, 0, 0.3);
    border-radius: 9px;
    padding: 4px;

    color: red;
    padding-right: 10px;
    position:relative;
}

.bg-msg-warning {
    background-color: #ffffff;
}

</style>


</head>

<body>


	<div id="myDialog" style="display: none;">
			<br/>
			<!-- <div class ="col-md-6">
				<input type="checkbox" name="selectBoxD"  class="selectAll" id="displayid" value=""/> Select All
			</div> -->
		     	<div class="errormessagediv1" align="center" style = "display:none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips1"></span></a>
							</div>
				</div>
			<input type="checkbox" name="selectBoxD"  class="selectAll" id="displayid" value=""/> Select All 
			
			<table id="tableid" class="table table-bordered">
		 			<tr>
		 			<th class="headth">Select</th>
					<th class="headth">Leave Type</th>
					<th class="headth">Description</th>
					<th class="headth">Short Name</th>
					<th class="headth">No Of Leaves</th>
					</tr>
					
					<logic:present name="LeaveCatList" scope="request">
					 <logic:iterate id="LeaveCatList" name="LeaveCatList" scope="request">
					<tr id="rowid1">
					<td><input type="checkbox" name="select" class="select"  value="<bean:write name="LeaveCatList" property="cat_id"  />"/></td>
					<td><bean:write name="LeaveCatList" property="cat_name"/></td>
					<td><input type="text"  disabled="disabled" name="CatType" class="CatType"   value="<bean:write name="LeaveCatList" property="leaveName" />"/></td>
					<td><input type="text"  disabled="disabled" name="shortName" class="shortName"  value="<bean:write name="LeaveCatList" property="shortName" />"  /></td>
					<td><input type="text"  disabled="disabled" name="noofleaves" class="noofleaves"  value="<bean:write name="LeaveCatList" property="noofleaves" />" /></td>
					<input type ="hidden" name = "hiddenleaveId" value = "<bean:write name="LeaveCatList" property= "leaveID" />"/>
					</tr>
					</logic:iterate>
					</logic:present>
			</table>
	</div>
	<div id="dialog" style ="display: none;"><p>Leave Types Once Saved Cannot be Modified or Deleted.</p><p>Do You Want to Continue?</p></div>
	<div>
		<div class="col-md-10 col-md-offset-2"
			style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
			<p style="">
				<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="title"><bean:write name="title"/></logic:present>
					</span>
			</p>

			<form method="post">
			
				<logic:present name="successmessagediv" scope="request">
							<div class="successmessagediv">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-success bg-msg-succes"><span><bean:write
												name="successmessagediv" scope="request" /></span></a>
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
			
			
						<div class="successmessagediv" align="center" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"><span
									class="validateTips"></span></a>
							</div>
						</div>
			
			
			
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					<div class="panel panel-primary">
						<div class="panel-heading" role="tab" id="headingOne">
							
								<a data-toggle="collapse" data-parent="#accordion2"
									href="#" style="color: #767676;vertical-align: text-top"><h4 class="panel-title"><i
									class="glyphicon glyphicon-menu-hamburger"></i>
									&nbsp;&nbsp;Leave Type Details
								</h4></a>
							
							
							
							<input type="hidden" id="hiddenAccYear"
								value='<logic:present name="editleavebank" scope="request"><bean:write name="editleavebank" scope="request" property="academicyear"/></logic:present>'>
			               <input type="hidden" name="editleavebank" id="snoid" value="<logic:present name="editleavebank" scope="request"><bean:write name="attnhidden" scope="request" /></logic:present>" />





							<div class="navbar-right">
								<span id="submit" class="buttons" style = "top:10px;margin-right: 5px">Save
								</span> <span id="back" class="buttons" style = "top:10px;"> Back</a></span>
						</div>

						</div>
						


						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">
								<div class="col-md-5" id="txtstyle"
									style="font-size: 11pt; color: #5d5d5d; padding-top :8px;">
									<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Academic
											Year</label>
										<div class="col-xs-6">
											<select id="academicyear" name="academicyear" onkeypress="HideError()"
												class="form-control">
												<option value="">-----------Select------------</option>
												<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option value="<bean:write name="AccYear" property="accyearId"/>">
														<bean:write name="AccYear" property="accyearname" />
													</option>
												</logic:iterate>
												</logic:present>
											</select>
										</div>
										<br /> <br />
								</div>

							</div>
							
						<input type="hidden" name="hiddenaccyear" id="hiddenaccyear" 
							value='<logic:present name="Accyear"><bean:write name="Accyear"/></logic:present>'/>
						
						<input type="hidden" name="hiddenloc" id="hiddenloc" 
							value='<logic:present name="loc"><bean:write name="loc"/></logic:present>'/>
						
							
							<div class="col-md-5" id="txtstyle" style="font-size: 11pt; color: #5d5d5d; padding-top :8px;">
							
								<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Location</label>
										<div class="col-xs-6">
											<select id="locationname" name="location" class="form-control">
												<option value="">-----------Select-----------</option>
												<!-- <option value="%%">All</option> -->
												<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>">
													<bean:write name="Location" property="locationName" />
													</option>
												</logic:iterate>
												</logic:present>
											</select>
										</div>
										<br /> <br />
								</div>
							
							</div>
							
							
							<div class="col-md-2" id="txtstyle" style="font-size: 11pt; color: #5d5d5d; padding-top :15px;">
							
								<span id="addnewCategory" class="buttons "  style="margin-right: 5px;">Add Leave Types</span>
							
							</div>
							<% int count =0; %> 
							<table id="myTable" class="table table-bordered" style=" display: none;">
							
							<thead>
								<tr>
								<th class="headth" style="text-align: left;">Serial No.</th>
								<th class="headth" style="text-align: left;">Leave Type</th>
								<th class="headth" style="text-align: left;">Short Name</th>
								<th class="headth" style="text-align: left;">No.Of Leaves</th>
								</tr>
							</thead>
							<tbody>
							
					<logic:present name="editList" scope="request">
					 <logic:iterate id="editList" name="editList" scope="request">
					
					<tr id="rowid1">
					<td><%=count+1 %></td>
					<td><bean:write name="editList" property="leaveName" /></td>
					<td><bean:write name="editList" property="shortName" /></td>
					<td><bean:write name="editList" property="noofleaves" /></td>
					</tr>
					
					<div style="display: none;"><%=count++ %></div>
					</logic:iterate>
					</logic:present>
															
							</tbody>
							
							</table>
							
							
							
						</div>
					</div>	
			</form>
		</div>
	</div>
</body>

</html>
