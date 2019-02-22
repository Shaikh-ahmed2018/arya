<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script src="JS/newUI/jquery.js"></script>
<script src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JS/backOffice/Admin/career.js"></script>
<title>eCampus Pro</title>
<script type="text/javascript">
function handle(e){
	
	
	var searchText = $("#searchname").val();
    if(e.keyCode === 13){
        e.preventDefault(); // Ensure it is only this code that rusn

        window.location.href ="adminMenu.html?method=careerupdate&searchText="
			+ searchText;
    }
}
</script>

<style>
.headClass{
cursor: pointer;
}
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}

#plus:hover {
	cursor: pointer;
}

#excelDownload :hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}

#iconsimg:hover {
	cursor: pointer;
}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="searchWrap">
			<div class="" id="div2">
				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;
					<span id="pageHeading">Internal Job Posting</span>
				</p>
			</div>
		
		</div>



		<input type="hidden" name="searchterm" class="searchtermclass" id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" /></logic:present>'></input>

		<div class="successmessagediv" style="display: none;">
			<div class="message-item" align="center">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes">
				<span class="successmessage"></span></a>
			</div>
		</div>


		<div class="errormessagediv1" align="center"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1" style="width: 35%; font-size: 11pt; color: red;">
				<span class="validateTips1"></span></a>
			</div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<h3 class="panel-title headClass" style="color: #767676;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Internal
						Job Posting
					
					</h3>



				<div class="navbar-right">
  
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="IJPADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
					                        <a href="adminMenu.html?method=addJob">
					                           <span class="buttons" style="margin-right: 1px">Add</span> </a>
							</logic:equal>
									</logic:equal>
										</logic:iterate>
												</logic:present>
					<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="IJPUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	 
					<span class="buttons" id="editDesignationId">Edit</span>
					</logic:equal>
					</logic:equal>
					</logic:iterate>
					</logic:present>
					
					<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="IJPDEL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
					<span class="buttons" id="trash">Deactive</span>
					</logic:equal>
					</logic:equal>
					</logic:iterate>
					</logic:present>
					
					<!-- <span class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download</span> -->
				</div>
			</div>
					
					
		
			<!-- pop up -->

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Download</h4>
						</div>
						<div class="modal-body">
							<span id="excelDownload"><img src="images/xl.png"
								class="xl"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<span id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div>
			<div id="collapsable" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<display:table class="table" id="allstudent"
						name="requestScope.career" pagesize="10"
						requestURI="/adminMenu.html?method=careerupdate" defaultsort="3"
						decorator="com.centris.campus.decorator.CareerUpdateDecorator">
						<display:column property="check" sortable="true"
							title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" />
						<display:column property="title" sortable="true"
							title="Title<img src='images/sort1.png' style='float: right'/>" />
						<display:column property="qualification" sortable="true"
							title="Qualification<img src='images/sort1.png' style='float: right'/>" />
						<display:column property="noofposition" sortable="true"
							title="No of Position<img src='images/sort1.png' style='float: right'/>"></display:column>
						<display:column property="experience" sortable="true"
							title="Experience<img src='images/sort1.png' style='float: right'/>"></display:column>
						<display:column property="status" sortable="true"
							title="Status<img src='images/sort1.png' style='float: right'/>"></display:column>



						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>



					</display:table>

				</div>
				<br />
			</div>
		</div>
	</div>


</body>
</html>