<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JS/backOffice/Admin/TimeTable.js"></script>
<script type="text/javascript" src="JS/common.js"></script>


<title>eCampus Pro</title>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

.glyphicon-pencil:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}

.glyphicon:hover {
	cursor: pointer;
}
.download:hover{

cursor: pointer;
}
/* #xls:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
} */

#excelDownload :hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
#iconsimg:hover {
	cursor: pointer;
}




.scrollBar{
max-height: 440px;
overflow-y:scroll; 
}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="searchWrap">
		<div class="col-md-4" id="div2">
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Time Table</span>
			</p>
		</div>
		
		<input type="hidden" id="hviewBy" value="<logic:present name="ViewBy"><bean:write name="ViewBy"/></logic:present>"/>

		<div class="input-group col-md-8" >
			<label class="hedderstyle form-control" style="background-color:transparent;text-align: right;">School</label>
			<select class="form-control" onkeypress="HideError()" name="SchoolID" id="SchoolID" style="width: 15%; margin: 2px 0px; margin-left: 0%;">
				<option value="">----SELECT------</option>
				<logic:present name="SchoolLocation" scope="request">
					<logic:iterate id="SchoolLocation" name="SchoolLocation" scope="request">
						<option value='<bean:write name="SchoolLocation" property="locationId" />'><bean:write name="SchoolLocation" property="locationName" /></option>
					</logic:iterate>
				</logic:present>
			</select>
			
			<label class="hedderstyle form-control" style="background-color:transparent;text-align: right;">Class</label>
			<select class="form-control" onkeypress="HideError()" name="ClassId" id="ClassId" style="width: 15%; margin: 2px 0px; margin-left: 0%;">
				<option value=""></option>
			</select>
			
			<label class="hedderstyle form-control" style="width: 10% !important;margin-left: 1% !important;background-color:transparent; font-family: Roboto Medium;font-size: 14px;
    font-weight: lighter;">Section</label>
			<select class="form-control" onkeypress="HideError()" name="sectionid" id="sectionid" style="width: 15%; margin: 2px 0px; margin-left: 0%;">
				<option value="">--Select--</option>
			</select>
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
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3 class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Time Table Details</h3>
					</a>
				
				<div class="navbar-right">
					<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="TMTUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
				<span class="buttons" id="edit">Edit</span>
				</logic:equal>
				</logic:equal>
				</logic:iterate>
				</logic:present>
				
					<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="TMTDWD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
				<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download </span>
				</logic:equal>
				</logic:equal>
				</logic:iterate>
				</logic:present>
				
				</div>
				
			</div>







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


			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
					
					
					<logic:present name="ClassTimeTableList">
					
				<table class="table" id="allstudent">
				   <thead>
					  <tr>
						<th>Select</th>
						<th>Class Name</th>
						<th>Section Name</th>
						<th>Teacher Name</th>
						<th>Status</th>
						<th>Created By</th>
						<th>Create Date</th>
						<th>Updated By</th>
						<th>Updated Date</th>
					  </tr>
					</thead>
				<tbody>
					
					</tbody>
			</table>
					
				<div class='pagebanner'>
					<select id='show_per_page'>
					     <option value='50'>50</option>
					     <option value='100'>100</option>
					     <option value='200'>200</option>
					     <option value='300'>300</option>
					     <option value='400'>400</option>
					     <option value='500'>500</option>
					 </select>
						<span class='numberOfItem'></span>	
				</div>
						<div class='pagination pagelinks mypage' style="top:-4px;"></div>
				</logic:present>
				
				<logic:present name="TeacherTimeTableList">
					<table class="table" id="allstudent">
					 <thead>
						<tr>
						<th><input type='checkbox' name='selectall' id='selectall'  onClick='selectAll()' /></th>
						<th>Teacher Id</th>
						<th>Teacher Name</th>
						<th>Status</th>
						<th>Created By</th>
						<th>Create Date</th>
						<th>Updated By</th>
						<th>Updated Date</th>
						</tr>
						</thead>
					<tbody>
					<logic:iterate name='TeacherTimeTableList' id="TeacherTimeTableList">                       
						<tr>
						<td><input type='checkbox' name='selectBox' class='selectBox' style='text-align:center' value='<bean:write name='TeacherTimeTableList' property="timetableId"/>,<bean:write name='TeacherTimeTableList' property="classid"/>'/></td>
						<td><bean:write name='TeacherTimeTableList' property="regno" /></td>
						<td><bean:write name='TeacherTimeTableList' property="teachername" /></td>
						<td><bean:write name='TeacherTimeTableList' property="timetableStatus" /></td>
						<td><bean:write name='TeacherTimeTableList' property="createdby" /></td>
						<td><bean:write name='TeacherTimeTableList' property="createddate" /></td>
						<td><bean:write name='TeacherTimeTableList' property="lastupdatedby" /></td>
						<td><bean:write name='TeacherTimeTableList' property="lastupdated" /></td>
						</tr>
					</logic:iterate>
					</tbody>
			</table>
					
				<!-- <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select></div><div class='pagination pagelinks'></div> -->
				</logic:present>
				
				
				</div>
			
			</div>
		</div>
	</div>


</body>
</html>