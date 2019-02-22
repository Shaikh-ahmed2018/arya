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
<script type="text/javascript"
	src="JS/backOffice/Library/Books_issue.js"></script>
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

#iconsimg:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}
</style>
</head>

<body>



	<div class="errormessagediv1" style="display: none;">
		<div class="message-item1"></div>
	</div>
	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Books Issued Details </span>
			</p>
		</div>



		<form >
			<div class="input-group col-md-4" style="margin: 20px 0px;">

			<input type="text" class="form-control" id="searchvalue" Placeholder="Search......"  >
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>

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
				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessage"></span></a>
			</div>
		</div>




		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Book Issued
						Details

					</h3></a>

				<div class="navbar-right">

					<a href="bookmenu.html?method=AddBookIssue">
					<span id="Addbookissuedetails" class="buttons" Onclick="Addbookissuedetails()" data-toggle="tooltip" data-placement="bottom" title="Add">Add</span></a> 
					<span id="editbookissuelist" class="buttons" data-toggle="tooltip" data-placement="bottom" title="Edit">Edit</span> 
					<span id="deletebookissuelist" class="buttons" data-toggle="tooltip" data-placement="bottom" title="Delete">Delete</span>

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
							<span id="xls"><img src="images/xl.png" class="xl"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
								id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div>



			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

					<logic:present name="booksnames" scope="request">
						<display:table class="table" pagesize="10"
							name="requestScope.booksnames"
							requestURI="adminMenu.html?method=bookissuedlist"
							id="allstudent">
							
							
						  <display:column title="Select" headerClass="heading1">
							<input type="checkbox" name="getempid" onClick='getvaldetails(this)' value=""
							id="${allstudent.book_issue_id}"> </>
							</display:column>	
							
						
							<display:column property="borrowertype" sortable="true"
								title="Borrower Type <img src='images/sort1.png' style='float: right'/>"></display:column>
							
							<display:column property="particularid" sortable="true"
								title="Borrower Name-Registration No<img src='images/sort1.png' style='float: right'/>"></display:column>
							
							<display:column property="bookname" sortable="true"
								title="Book Name-Barcode<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="book_issue_date" sortable="true"
								title="Issued Date<img src='images/sort1.png' style='float: right'/>"></display:column>
							
							<display:column property="actual_return_date" sortable="true"
								title="Actual Return Date<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="status" sortable="true"
								title="Status<img src='images/sort1.png' style='float: right'/>"></display:column>


						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>

					

						</display:table>

					</logic:present>


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