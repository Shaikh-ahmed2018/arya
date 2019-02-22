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


<script type="text/javascript" src="JS/backOffice/Exam/StudentPromotionList.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<style>
.form-group{
margin-bottom: 10px;
}
#editDesignationId:hover {
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
.Completed{
background-color:rgba(0, 158, 0, 0.66);
color:#fff;
padding: 0px 3px; 
}
.Pending{
background-color:#FF0000;  
color:#fff;
padding: 0px 3px; 
}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;
				<span id="pageHeading">Promotion</span>
			</p>
		
			<div class="academic_year clearfix">
			<div class="col-md-4">
				<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: left;font-size:16px;font-weight:300;color:#767676;line-height: 2; padding-right:0px;">Academic
						Year </label>
						<div class="col-xs-7" style=" padding: 0px;">
							<select name="academicYear" id="academicYear" class="form-control" >
						</select>
						</div>
					</div>
				</div>
				<div class="col-md-3"></div>
				
				<div class="col-md-5">
				<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: left;font-size:16px;font-weight:300;color:#767676;line-height: 2; padding-right:0px;">Next Academic
						Year </label>
						<div class="col-xs-7" style=" padding: 0px;">
							<input name="nextacademicYear" id="nextacademicYear" class="form-control"  readonly="readonly" />
					
						</div>
					</div>
				</div>
			</div>
		

		
			<div class="successmessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>

			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips"></span></a>
				</div>
			</div>
		


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;
					CLASS LIST</h3></a>
			
				<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>
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
				<div class="panel-body" id="promotionClass" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


					
				</div>
				<br />
			</div>
		</div>
	</div>


</body>
</html>