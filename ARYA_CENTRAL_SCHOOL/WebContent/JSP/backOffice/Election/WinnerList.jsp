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

<link rel="stylesheet"href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Election/WinnerList.js"></script>




<style>
.winner{
font-size: 14px;
font-family: Roboto Regular !important;
color: #767676;
}
fieldset{
 
    display: block;
    margin-bottom: 5px;
    padding-bottom: 0.625em;
    padding-left: 7px;
    padding-right: 0px;
    border: 0.5px solid #ccc;
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
  .winnerbox{padding: 15px;
  border: 1px solid #DEDEDE; 
  margin-bottom: 10px;
  }
  .winnerbox h3{
  margin-top: 0px;
  }
</style>

</head>

<body>

<input type="hidden" id="academicYearId"  value='<logic:present name="academicYearId" scope="request"><bean:write name="academicYearId" /></logic:present>' />
<input type="hidden" id="groupId"  class="form-control" value='<logic:present name="groupId" scope="request"><bean:write name="groupId" /></logic:present>'  />
<input type="hidden" id="electionId" value='<logic:present name="electionId" scope="request"><bean:write name="electionId" /></logic:present>' />

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="searchWrap">
			<div  id="div2">
			 <div class="row"style="margin-left: 0px;margin-right: 0px;">
			 	<div class="col-md-9">
			 		<p>
						<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Winner List</span>
					</p>
			 	</div>
			 	<div class="col-md-3">
			 	<span class="buttons"  id="Candidate" >Candidate List</span>
			 	<span class="buttons"  id="Winner">Winner List</span>
			 		<span class="buttons"  id="print" style="position: absolute; right: 0">Print</span>
			 	</div>
			 </div>	
		</div>
	  </div>
	 
	  <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
		<div class="panel-body" id="tabletxt" style="padding: 15px;">
			<div class="col-md-6" id="txtstyle">	
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4" align="right" id="inputnames">Academic Year</label>
					<div class="col-xs-7">
							<input id="academicYear" name="accyear" class="form-control" value='<logic:present name="academicYear" scope="request"><bean:write name="academicYear" /></logic:present>' readonly="readonly" />
					</div>
				</div>
						
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4" align="right" id="inputnames">Group Name</label>
					<div class="col-xs-7">
						<input id="groupName" name="groupName" class="form-control" value='<logic:present name="groupName" scope="request"><bean:write name="groupName" /></logic:present>' readonly="readonly" />
					</div>
				</div>
				
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4" align="right" id="inputnames">Election Name</label>
					<div class="col-xs-7">
						<input id="electionName" name="electionName" class="form-control" value='<logic:present name="electionName" scope="request"><bean:write name="electionName" /></logic:present>' readonly="readonly" />
					</div>
				</div>
			</div>
	  </div>

	  <div  id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
	  	<div class='winnerList' id='winnerList' >
			<logic:present name="DataList" scope="request">
				<logic:iterate id="DataList" name="DataList">
				<div class="winnerbox">		
				<h3><bean:write name="DataList" property="sno"/> <bean:write name="DataList" property="categoryName"/></h3>	 
				<div class="row">
					<div class="col-md-8">
						<div class="row">
							<div class="col-md-4">
								<div class="candidateImg">
								<img src='./<bean:write name="DataList" property="imgUrl"/>' width="150" height="150" />
								</div>
							</div>
							<div class="col-md-8">
								<div class="candidateDetails winner">
									<div class="admissionNo"><span>Admission No. : </span><span> <bean:write name="DataList" property="admissionNo"/></span></div>
									<div class="candidateName"><span>Name : </span><span> <bean:write name="DataList" property="studentName"/></span></div>
									<div class="candidateClass"><span>Class : </span><span> <bean:write name="DataList" property="className"/> <bean:write name="DataList" property="sectionName"/></span></div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="voteCount winner">
						<div class="status"><bean:write name="DataList" property="status"/></div>
						
						<fieldset>
							<legend>Vote Count</legend>
							<h1 style="text-align: center;"><bean:write name="DataList" property="voteCount"/></h1>
						</fieldset>
						</div>
					</div>
				</div>	
				</div>
				
					
				</logic:iterate>
			</logic:present>		
			</div>
		</div> 	
	</div>
 </div>
</body>
</html>
