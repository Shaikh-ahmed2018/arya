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
<script type="text/javascript" src="JS/backOffice/Election/electionCategoryList.js"></script>




<style>
.pagebanner {
    margin-top: 20px;
   
    }
#editStudent:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
</style>
<style>
.download:hover{

cursor: pointer;
};

#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}


.glyphicon-trash:before {
    content: "\e020";
    margin-left: 15px;
}
.Not {
background-color:#FF0000;
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
.Set{
background-color:rgba(0, 158, 0, 0.66);
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
</style>
</head>
<body>
	<div class="col-md-10 col-md-offset-2" id="div1">
	<div class="searchWrap">
		<div  id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Election Category</span>
			</p>
		</div>
	</div>
		
		<input type="hidden" id="succmsg" value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />
		
			<div id="successmessages" class="successmessagediv" style="display: none;">
				<div class="message-item">
					 <a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"><logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present></span></a>
				</div>
			</div>
		
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><logic:present name='errorMessage'  scope='request' ><bean:write name='errorMessage'  /></logic:present></span></a>
				</div>
			</div>
	
		
		<div class="panel panel-primary clearfix">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" >
					<h3 class="panel-title" style="color: #767676; vertical-align: middle;"> <span class="glyphicon glyphicon-menu-hamburger"></span>
					&nbsp;&nbsp;Find Election Details
					</h3></a>
						
			
			</div>
			<!-- pop up -->


		<div id="filteration" class="filteration clearfix">
						<div class="panel-body clearfix" id="tabletxt" style="padding: 15px;">

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
						
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Group Name</label>
									<div class="col-xs-7">
											<select id="groupName" name="grpname" class="form-control">
											<option value="">----Select----</option>
										</select>
									</div>
							</div>
							
							</div>
			
							</div>
	</div>
	<div id="collapseOne" class="panel-collapse collapse in " >
				<table class='table' id='allstudent' style='width: 100%'>
					<thead>
						<tr>
							<th>Sl.No.</th>
							<th style="text-align: center">Academic Year</th>
							<th style="text-align: center">Group Name</th>
							<th style="text-align: center">Election Title</th>
							<th style="text-align: center">Status</th>

						</tr>
					</thead>
					<tbody>
						<logic:present name="DataList" scope="request">
							<logic:iterate id="DataList" name="DataList">

								<tr
									class='<bean:write name="DataList" property="accid"/> <bean:write name="DataList" property="groupid"/><bean:write name="DataList" property="electionTitle"/>'>
									<td><bean:write name="DataList" property="sno" /></td>
									<td class="accyear"><bean:write name="DataList"
											property="accyear" /></td>
									<td class="group"><bean:write name="DataList"
											property="groupName" /></td>
									<td class="title"><bean:write name="DataList"
											property="electionTitle" /></td>
								</tr>

							</logic:iterate>
						</logic:present>
					</tbody>

				</table>

				<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
					<span  class='numberOfItem'></span>	
					</div><div class='pagination pagelinks'></div>
						
						</div> 	
	
					
				</div>
		</div>


</body>
</html>