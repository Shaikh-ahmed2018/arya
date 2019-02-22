<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Library/GotoIssueStatementDetails.js"></script>
<title>eCampus Pro</title>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}

.download:hover{

cursor: pointer;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}

.navbar-right span {
    margin-right: -4px;
    position: relative;
    top: 6px;
    vertical-align: text-top;
}

.anchor a:hover {
    text-decoration: underline;
}
.tooltip {
    position: relative;
    display: inline-block;
    border-bottom: 1px dotted black;
}

.tooltip .tooltiptext {
    visibility: hidden;
    width: 120px;
    background-color: black;
    color: #fff;
    text-align: center;
    border-radius: 6px;
    padding: 5px 0;

    /* Position the tooltip */
    position: absolute;
    z-index: 1;
}
.buttonstyle{
border:none;
background: transparent;

}

.tooltip:hover .tooltiptext {
    visibility: visible;
}
#allstudent th:nth-child(1){
max-width : 50%;
}
#allstudent th:nth-child(2){
width :410px;
}

#allstudent th:nth-child(3){
width :300px;
}
#allstudent th:nth-child(4),td:nth-child(4){
text-align: center;
}
</style>
</head>

<body>
 
	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-6" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Issued Statement</span>
			</p>
		</div>

		
		
		<div class="input-group col-md-6">
			
			<label  class="hedderstyle form-control" style="margin: 20px 0px; width: 20% !important; border: none;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;    background: transparent;
    				color: #767676 !important;"></label> 
		
			
			<label style="margin: 20px 0px; width: 21%; border: none;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;    background: transparent;
    		color: #767676 !important;"	class="form-control"></label>
				


		</div>



			<div class="successmessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>

			<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>



		<div class="panel panel-primary">
			<div class="panel-heading heading1">
				<a data-toggle="collapse" data-parent="#accordion2"
					href=".collapseOne1" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp; Issued Details
					</h3></a>
				<div class="navbar-right" style="right:-8px;">
				<a href="LibraryMenu.html?method=SearchSubscriberDetails">
               <span class="buttons" id="back1" style="left:-15px;" >Back</span> </a>
                    
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
		
			<div class="col-md-6" id="txtstyle" style="margin-top:8px;">
				<input type="hidden" id="subscriberId"  value="<logic:present name='subId' scope='request'><bean:write name='subId'/></logic:present>" />
					
					<input type="hidden" id="IssueId"  value="<logic:present name='issueId' scope='request'><bean:write name='issueId'/></logic:present>" />
					<input type="hidden" id="hiddenusetype" 
							value="<logic:present name='subscriberType' scope='request'><bean:write name='subscriberType'/></logic:present>" />		
						
							<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Subscriber No</label>
									<div class="col-xs-7">
									<input type="text" id="subscriberno" name="subscriberno" maxlength="25" class="form-control"
									value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberNo"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix department1">
									<label for="inputEmail" class="control-label col-xs-4" id="department1"
										style="text-align: right; line-height: 35px;">Department</label>
									<div class="col-xs-7">
									<input type="text" id="department" name="subscriberno" maxlength="25" class="form-control"
									value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="department"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix adminssionNo1">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Admission No</label>
									<div class="col-xs-7">
										<input type="text" id="adminssionNo" name="subscriberno" maxlength="25" class="form-control"
										value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="adminssionNo"/></logic:present>' />
									</div>
								</div>
							
								<div class="form-group clearfix className1">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Class</label>
									<div class="col-xs-7">
										<input type="text" id="className" name="subscriberno" maxlength="25" class="form-control"
										value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="className"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Status</label>
									<div class="col-xs-7">
										<input type="text" id="status" name="subscriberno" maxlength="25" class="form-control"
										value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="status"/></logic:present>' />
											
									</div>
									<input type="hidden" id="teacherid"></input>
								</div>
								
							</div>
							<div class="col-md-6 collapseOne" id="txtstyle" style="margin-top:8px;">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Name</label>
										  <div class="col-xs-7">
										   <input type="text" id="name" name="subscriberno" maxlength="25" class="form-control" 
										   value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberName"/></logic:present>' />
									      </div>
							   </div>
							   
							   <div class="form-group clearfix rollNo1">
									<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;"	id="inputnames">Roll No</label>
									<div class="col-xs-7">
									  <input type="text" id="rollNo" name="subscriberno" maxlength="25" class="form-control"
									  value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="rollNumber"/></logic:present>' />
									</div>
							</div>
							
							<div class="form-group clearfix othercontact1">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Contact</label>
									<div class="col-xs-7">
									<input type="text" id="othercontact" name="subscriberno" maxlength="25" class="form-control"
									value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="contactNumber"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix designation1">
									<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;"	id="inputnames">Designation</label>
									<div class="col-xs-7">
									  <input type="text" id="designation" name="designation" maxlength="25" class="form-control"
									  value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="designation"/></logic:present>' />
									</div>
							</div>
								
								<div class="form-group clearfix divisionName1">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Section</label>
									<div class="col-xs-7">
										<input type="text" id="divisionName" name="subscriberno" maxlength="25" class="form-control"
										value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="sectionName"/></logic:present>' />
									</div>
								</div>
								
							</div>
							<input type="hidden" id="hidensize" value="<logic:present name="listsize"><bean:write name="listsize" /></logic:present>" />
			            
			  <!----------------------- Student table ------------------------>
                  
				<div class="allstudenttable" >
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<table class='table' style="margin-right: 250px;" id='allstudent'
							width='100%' style="margin-top:50px">
							
							<thead>
								<tr>
									<th>Sl.No</th>
									<th>Book</th>
									<th>Author</th>
									<th>Issue Date</th>
								</tr>
							</thead>
							<tbody>
						<logic:present name="issuedetails">
						<logic:iterate name="issuedetails" id="issuedetails">
						<tr class ="issuedid" id="<bean:write name="issuedetails" property="issueId"/>">   <!---->
						<td> <bean:write name="issuedetails" property="slno"/></td>               
						<td> <bean:write name="issuedetails" property="bookTitle"/></td>  
						<td> <bean:write name="issuedetails" property="bookAuthor"/></td>
						<td> <bean:write name="issuedetails" property="issueDate"/></td>
						</tr>
						</logic:iterate>
						</logic:present>
				       </tbody>
				         
						</table>
					</div>
				</div>
		  </div>
	</div>

</body>
</html>