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
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/backOffice/Fee/studenttransportlist.js"></script>
<script type="text/javascript" src="JS/common.js"></script>

<title>eCampus Pro</title>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

.glyphicon-pencil:hover {
	cursor: pointer;
}

#edit:hover {
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
</style>
</head>

<body class="feeconcession">

	<div class="col-md-10 col-md-offset-2" id="div1">
		<p style="margin-top: 5px; margin-bottom: 5px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Tranport Request</span>
		</p>
	<div class="searchWrap">
		<div class="col-md-8" id="div2">
            <p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Student Details</span>
			</p>
		</div>
		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;margin-left:-700px;"> 
							<h4 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Transport Request</h4></a>
						
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											<option value="all">ALL</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
									<input type="hidden" name="hiddenlocationId" id="hiddenlocationId" value="<bean:write name="locationId"/>">
								</div>
									
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
									
									<select class="form-control" onkeypress="HideError()" 
											name="classname" id="classname">
											<option value="all">ALL</option>
									</select>
									
										<%-- <select id="" name="" class="form-control" required>
											<option value="%%">All</option>

											<logic:present name="">
												<logic:iterate id="a" name="">
													<option	value="<bean:write name="" property=""/>">
														<bean:write name="" property=""/>
													</option>
												</logic:iterate>
											</logic:present>
										</select> --%>
									</div>
								</div>
									
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search By</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" 
										value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
									</div>
								</div>
								
							</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
									<input type="hidden" name="hiddenaccyear" id="hiddenaccyear" value="<bean:write name="accyear"/>">
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
								
								<p align="center"style="margin-left:17%">
								<button type="button" class="btn btn-info" id="search" >Search</button>
								<button type="reset" class="btn btn-info" id="resetbtn" >Reset</button></p>
							</div>
						<input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
						<%-- <input type="hidden" name="clasdetailids" id="classdetailid" value='<logic:present name="studentdetailslist" scope="request"><logic:iterate id="studentdetailslist" name="studentdetailslist"><bean:write name="studentdetailslist" property="classDetailId"/></logic:iterate></logic:present>'></input> --%>
						 


			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">	
				
					
						
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>S.No</th>
							<th>Admission No</th>
							<th>Student Name</th>
							<th>Class</th>
							<th>Division</th>
							</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
					
					
				
					
		<div class="pagebanner" style="margin-left: 30px;"><select id="ShowPerPage"><option value="100">100</option>
		<option value="200">200</option>
		<option value="300">300</option>
		<option value="400">400</option>
		<option value="500">500</option>
		</select>
			<span  class='numberOfItem'></span>	
			 </div>
		<div class="pagination pagelinks"></div>
				
				</div>
				<br />
			</div>
		</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	


</body>
</html>