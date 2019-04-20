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




<script type="text/javascript"
	src="JS/backOffice/Fee/SpecialFeeSetup.js"></script>
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
table tr[class^='STU'] td{
vertical-align: middle;
}
.download:hover {
	cursor: pointer;
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}
.paymentStatus span {
     display: inline-block;
    padding: 0 5px;
    margin: 0 2px;
    min-width: 80px;
    text-align: center;
    color: #fff;
    font-weight: 600;
    border-radius: 6px;
    box-shadow: 5px 5px 5px #ccc;
}
span.Not.Paid{
background-color: #f00;
}
span.Paid{
background-color: rgba(0, 158, 0, 0.66);
}
</style>
</head>

<body class="feeconcession">

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="searchWrap">
			<div class="" id="div2">
				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Special Fee Setup</span>
				</p>
			</div>
		</div>
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">

						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne"
							style="color: #767676; vertical-align: text-top; ">
							<h4 class="panel-title">
								<i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Special Fee Setup
							</h4>
						</a>

					</div>
		</div>
					<div id="collapseOne" class="panel-collapse collapse in "
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 20px;">

								


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Search
										By</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue"
											Placeholder="Search......"
											value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
									</div>
								</div>
								<div class="form-group clearfix">
									<div class="col-xs-5">
									</div>
									<div class="col-xs-7">
										<button type="button" class="btn btn-info" id="search">Search</button>
										<button type="reset" class="btn btn-info" id="resetbtn">Reset</button>
									</div>
								</div>
							</div>

							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic
										Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>

											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option
														value="<bean:write name="AccYear" property="accyearId"/>"><bean:write
															name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>

								

								
							</div>
							<input type="hidden" name="Acyearid" id="Acyearid"
								value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
							<%-- <input type="hidden" name="clasdetailids" id="classdetailid" value='<logic:present name="studentdetailslist" scope="request"><logic:iterate id="studentdetailslist" name="studentdetailslist"><bean:write name="studentdetailslist" property="classDetailId"/></logic:iterate></logic:present>'></input> --%>



							<div id="collapseOne" class="accordion-body collapse in">
								<div class="panel-body"
									style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


									<table class="table" id="allstudent" width="100%">
										<thead>
											<tr>
												<th>Sl.No.</th>
												<th class="sortable">Academic Year</th>
												<th class="sortable">Admission No</th>
												<th class="sortable">Name</th>
												<th class="sortable">Class & Div.</th>
												<th class="sortable">Fee Name</th>
												<th class="sortable">Term</th>
												<th class="sortable">Amount</th>
												<th class="sortable">Action</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>

								</div>
								<br />
							</div>
						</div>
					</div>
					</div>
					</div>
					
</body>
</html>