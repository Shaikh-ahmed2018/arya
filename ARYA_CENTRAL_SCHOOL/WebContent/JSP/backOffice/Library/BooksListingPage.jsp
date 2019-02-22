<!DOCTYPE html>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
<script type="text/javascript" src="JS/backOffice/Settings/EditQuotaDetails.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<style>
#feeedit:hover {
	cursor: pointer;
}
#editdep:hover {
	cursor: pointer;
}
#deleteid:hover {
	cursor: pointer;
}
#xls:hover {
	cursor: pointer;
}
#iconsimg:hover{

cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}
</style>

<style>
.buttons{

vertical-align: 0px;

}
</style>

</head>

<body>



     <div class="errormessagediv1" style="display: none;">
				<div class="message-item1"></div></div>
	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Book Details
					</span>
			</p>
		</div>
		
		
					
		<form id="myForm" action="adminMenu.html?method=QuotaDetails" method="post">
		<div class="input-group col-md-4" style="margin: 20px 0px;">

			<input type="text" class="form-control" name="searchname" id="searchname" Placeholder="Search......" 
			  value='<logic:present name="searchdetails"><bean:write name="searchdetails" />
		</logic:present>'>
			
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search" onclick="myFunction()" value="Submit form">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
		
		
		
			
		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />

													</logic:present>'></input>		
		
		
		
		
		
		
		</form>
		
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
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>
		
		
		
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Book Details
						
					</h3></a>
					
				<div class="navbar-right" >
								
					
					
					 <a	href="bookmenu.html?method=Addbook"><span
						class="buttons"
						  data-toggle="tooltip" data-placement="bottom" title="Add">Add</span></a>
				
				
					
				
				  <span id="editdep" class="buttons"
			 data-toggle="tooltip" data-placement="bottom" title="Edit">Edit</span>
								
				
				  <span id="deleteid" class="buttons"
					  data-toggle="tooltip" data-placement="bottom" title="Delete">Delete</span>
						
					
				
						
		<!-- 	 <a	href=""><span class="glyphicon glyphicon-print"
						style="font-size: 20px; line-height: 34px; color: #989898;"></span><img
						src="images/rightline.png" style="margin-top: -5px;"></a>  -->
						
				 <!-- <span id="xls" ><img src="images/download.png" class="download"
			  data-toggle="tooltip" data-placement="bottom" title="Download"></span> -->
			  
			  
			  
			 <!--  <img src="images/rightline.png" style="margin-top: -2px;margin-right: 11px;"> -->
							
					<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span>
				

				</div>
			
			<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>
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
									<span id="xls"><img src="images/xl.png"
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
												
					
					<logic:present name="Quotalist" scope="request">
					
					<display:table id="allstudent" name="Quotalist" class="table"
					export="false" requestURI="/adminMenu.html?method=QuotaDetails"
					pagesize="10" decorator="com.centris.campus.decorator.QuotaDecorator">
					
				
			        <display:column property="check" media="html"
								 sortable="true" title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" />
				    <display:column property="quotaName" sortable="true" title="Quota Name<img src='images/sort1.png' style='float: right'/>" />
				    <display:column property="quotadescription" sortable="true" title="Description<img src='images/sort1.png' style='float: right'/>" />
					
					
						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>
					
					
					</display:table>
					
					
					</logic:present>
						
						
					
		
		<!-- 
					<p style="float: left; margin: 0;">&nbsp;&nbsp;Showing 1 to 10
						of 50 Entries</p> -->
					<!-- <ul class="pagination" style="float: right; margin: 0;">
						<li><a href="#">&laquo;</a></li>
						<li class="active"><a href="#">1</a></li>
						<li class=""><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul> -->
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