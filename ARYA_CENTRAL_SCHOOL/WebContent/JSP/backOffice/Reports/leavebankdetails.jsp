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

<script type="text/javascript" src="JS/backOffice/Reports/leavebank.js"></script>
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


<title>eCampus Pro</title>
<style>
#editleavebank:hover {
	cursor: pointer;
}

#deleteleavebank:hover {
	cursor: pointer;
}
#addleavebank:hover {
	cursor: pointer;
}

#xlss:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
.download:hover {
	cursor: pointer;
}
.row{
	    /* margin-right: -15px; */
    margin-left: -0px;

}

</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		
		<div class="row">
		<div class="col-md-7" id="div2">
			<p style="">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Leave Bank Details</span>
			</p>
			</div>
		</div>


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;vertical-align: text-top"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-user"></span> Leave Bank Details
					</h3></a>
			</div>

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"	style="font-family: Open Sans Light; font-size: 11pt; ">

	    <logic:present name="ViewLeaveDetails" scope="request">
       	<div class="tablecss" align="center" >   
       <table style="width: 100%;" class="table" id="allstudent" >
          <tr>
              	<th style="background-color:#07AAB9;color:white;font-size:14px;padding-left: 5px; width:90px">Year</th>
             	<th style="background-color:#07AAB9;color:white;font-size:14px;">Leave Type</th>
              	<th style="background-color:#07AAB9;color:white;font-size:14px;">Opening Balance</th>
             	<th style="background-color:#07AAB9;color:white;font-size:14px;">Leaves Consumed</th>
              	<th style="background-color:#07AAB9;color:white;font-size:14px;">Closing Balance</th>
          </tr>
       <logic:iterate id="iterateid" name="ViewLeaveDetails">
           <tr>
              <td><bean:write name="iterateid" property="accyear"/></td>
              <td><bean:write name="iterateid" property="leavetype"/></td>
              <td><bean:write name="iterateid" property="total_leave_year"/></td>
               <td><bean:write name="iterateid" property="consumebal"/></td>
              <td><bean:write name="iterateid" property="totalleaves"/></td>
           </tr>
       </logic:iterate>
       </table>
        </div>
    </logic:present>
    
    	<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
   	<span  class='numberOfItem'></span>	
   	</div><div class='pagination pagelinks'></div>
				</div>
				<br />
		</div>
		</div>
		
	</div>
</body>
</html>