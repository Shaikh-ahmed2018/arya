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

<title>eCampus Pro</title>
<script type="text/javascript" src="JQUERY/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Admin/StageFeeSetup.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
</head>

<style>
.glyphicon:hover {
	cursor: pointer;
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
</style>


<body>


	<div class="col-md-10 col-md-offset-2" id="div1">
	 <div class="searchWrap">	
		<div class="col-md-8" id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Transportation Fee Setup</span>
			</p>


		</div>
		<div align="right" class="input-group col-md-4">


			<input type="text" class="form-control" Placeholder="Search......"
				id="searchterm"
				value="<logic:present name="searchTerm"><bean:write name="searchTerm"></bean:write></logic:present>">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
	</div>
		<logic:present name="success" scope="request">

			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
								name="success" scope="request" />
					</span></a>
				</div>
			</div>
		</logic:present>
		<logic:present name="error" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="error" scope="request" />
					</span></a>
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

		<input type="hidden" id="SerchTermstagesetupid"
			value='<logic:present name="SerchTermstagesetup"><bean:write name="SerchTermstagesetup"  /></logic:present>'></input>
			
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Transportation Fee Setup

					</h3></a>
				<div class="navbar-right">

					<span class="buttons" id="edit">Edit</span>

					<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download </span>

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
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


					<display:table name="requestScope.classSetupList"
						requestURI="/adminMenu.html?method=getStageFeeSetup" pagesize="10"
						export="false" class="table" id="allstudent">


						<display:column
							title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />">
							<input type="checkbox" name="selectBox" id="selectBox"
								value="${allstudent.accyearid},${allstudent.classid},${allstudent.termid}" />
						</display:column>



						<%-- 		<display:column property="sno" sortable="true"
							title="Sno <img src='images/sort1.png' style='float: right'/>" /> --%>

						<display:column property="accyear" sortable="true"
							title="Academic Year <img src='images/sort1.png' style='float: right'/>" />

						<display:column property="classname" sortable="true"
							title="Class Name <img src='images/sort1.png' style='float: right'/>" />

						<display:column property="termname" sortable="true"
							title="Term Name <img src='images/sort1.png' style='float: right'/>" />

						<display:column property="stagecount" sortable="true"
							title="Stage Count <img src='images/sort1.png' style='float: right'/>" />


						<%-- 			<display:setProperty	name="paging.banner.no_items_found" value="<span class="pagebanner">No {0} found.</span>"></display:setProperty>
						<display:setProperty	name="paging.banner.one_item_found" value="<span class="pagebanner">One {0} found.</span>"></display:setProperty>
						<display:setProperty	name="paging.banner.all_items_found" value="<span class="pagebanner">{0} {1} found, displaying all {2}.</span>"></display:setProperty>
						<display:setProperty	name="paging.banner.some_items_found" value="<span class="pagebanner">{0} {1} found, displaying {2} to {3}.</span>"></display:setProperty>
						<display:setProperty	name="paging.banner.full" value="<div class="pagination"><ul><li class="prev disabled"><a href="{1}" class="next" title="first"><img src="static/images/first.png" alt="first" /></a></li><li class="prev disabled"><a href="{2}" class="next" title="previous"><img src="static/images/previous.png" alt="previous" /></a></li>{0}<li><a href="{3}" class="next" title="next"><img src="static/images/next.png" alt="next" /></a></li><li class="next"><a href="{4}" class="next" title="lest"><img src="static/images/last.png" alt="last" /></a></li></ul></div>"></display:setProperty>
						<display:setProperty	name="paging.banner.first" value="<div class="pagination"><ul><li class="prev"><a href="{1}" class="next" title="first"><img src="static/images/first.png" alt="first" /></a></li><li class="prev"><a href="{2}" class="next" title="previous"><img src="static/images/previous.png" alt="previous" /></a></li>{0}<li><a href="{3}" class="next" title="next"><img src="static/images/next.png" alt="next" /></a></li><li class="next"><a href="{4}" class="next" title="lest"><img src="static/images/last.png" alt="last" /></a></li></ul></div>"></display:setProperty>
						<display:setProperty	name="paging.banner.last" value="<div class="pagination"><ul><li class="prev"><a href="{1}" class="next" title="first"><img src="static/images/first.png" alt="first" /></a></li><li class="prev"><a href="{2}" class="next" title="previous"><img src="static/images/previous.png" alt="previous" /></a></li>{0}<li class="next disabled"><a href="{3}" class="next" title="next"><img src="static/images/next.png" alt="next" /></a></li><li class="next disabled"><a href="{4}" class="next" title="lest"><img src="static/images/last.png" alt="last" /></a></li></ul></div>"></display:setProperty>
						<display:setProperty	name="paging.banner.onepage" value="<div class="pagination"><ul>{0}</ul></div>"></display:setProperty>
 --%>

						<!-- paging.banner.page.selected=<li><strong>{0}</strong></li>
paging.banner.page.link=<li><a href="{1}" title="Go to page {0}">{0}</a></li>
paging.banner.page.separator= -->


						<%--   <display:setProperty name="paging.banner.first" value="<div class='pagelinks'><a href='{1}' class='next' title='first'>First</a>{0}<a href='/adminMenu.html?d-2374500-p=2&method=getStageFeeSetup' class='next' title='next'>Next</a><a href='/adminMenu.html?d-2374500-p=2&method=getStageFeeSetup' class='next' title='lest'>Last</a></div> "></display:setProperty>
					    
					     <display:setProperty name="paging.banner.last" value="<div class='pagelinks'><a href='/adminMenu.html?d-2374500-p=1&method=getStageFeeSetup' class='next' title='first'>First</a>{0}<a href='/adminMenu.html?d-2374500-p=2&method=getStageFeeSetup' class='next' title='next'>Next</a><a href='/adminMenu.html?d-2374500-p=2&method=getStageFeeSetup' class='next' title='lest'>Last</a></div> "></display:setProperty>
					     
					     <display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty> --%>

						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>


					</display:table>



				</div>
				<br />
			</div>
		</div>
	</div>
	<script src="JS/newUI/jquery.js"></script>
	<script src="JS/newUI/bootstrap.min.js"></script>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>
