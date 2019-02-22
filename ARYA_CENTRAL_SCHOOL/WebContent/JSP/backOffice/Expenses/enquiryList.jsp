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

<script type="text/javascript"
	src="JS/backOffice/Expenses/Expenses.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
	<link href="CSS/newUI/font-awesome/css/styless.css"
	rel="stylesheet" type="text/css">
	
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
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 8px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">ENQUIRY FORM</span>
			</p>
		</div>

		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchexamlist"><bean:write name="searchexamlist" />

													</logic:present>'></input>


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
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary" id="panel-primary-set">
				<div class="panel-heading" role="tab" id="headingOne">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseOne" style="color: #fff;"><h3
							class="panel-title" style="color: #767676;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Enquiry Form</h3></a>
					<div class="navbar-right">
						
						<img src="images/submit-click.png" class="download" id="iconsimg"
							data-toggle="modal" data-target="#myModal" data-toggle="tooltip"
							data-placement="bottom" title="Download">

						<img src="images/refresh.png" class="download" id="iconsimg"
							data-toggle="modal" data-target="#myModal" data-toggle="tooltip"
							data-placement="bottom" title="Download">

					</div>
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
					<div class="panel-body"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<div class="container" id="div4">
					<form>
					<fieldset>
					
					<legend>Student Info</legend>
				<table class="studentinfo">
					<tr>
					<td style="text-align:right">
					<label for="studentname"> Student Name:</label>
					</td>
					<td>
					<input name="sname" type="text"  />
					</td>
					<td style="text-align:right">
					<label for="classs">Class :</label>
					</td>
					<td>
					<select style="width:100%">
 						<option value="1">1</option>
  						<option value="2">2</option>
  						<option value="3">3</option>
 						<option value="4">4</option>
					</select>
					</td>
						</tr>
							<tr>
							<td style="text-align:right">
							<label for="csname">Current School Name:</label></td>
							<td>
							<input name="csname" type="text" />	
							</td>
							
							<td style="text-align:right">
						<label for="lastyeargrade">Last Year Grade :</label>
						</td>
						<td>
						<input name="lastyeargrade" type="text" />
					</td>
						
						</tr>	
					
							
					</table>
					
		</fieldset>


	<fieldset>
					
						<legend>Parents Info</legend>
					<table class="studentinfo">
					
					<tr>
					<td style="text-align:right">
					<label for="guardianname">Guardian Name :</label>
					</td>
					<td>
					<input name="guardianname" type="text"  />
					</td>
					<td style="text-align:right">
							<label for="relation">Relation :</label></td>
							<td>
							<input name="relation" type="text" />	
							</td>
							</tr>
							<tr>
							<td style="text-align:right">
					<label for="fathersname">Father's Name :</label>
					</td>
					<td>
					
					<input name="fathersname" type="text" />
					</td>
					
				
				
							<td style="text-align:right">
						 	<label for="address">Address :</label>
						 	</td>
							 <td>
							<input name="address" type="text" />
							</td>
							</tr>
							
							<tr>
							
							
							<td style="text-align:right">
					<label for="mobnumber">Mobile Number :</label>
					</td>
					<td>
					<input name="usrmob" type="tel" />
					</td>
					
					
					
					<td style="text-align:right">
						 	<label for="email">Email :</label>
						 	</td>
							 <td>
							<input name="email" type="email" />
							</td>
							</tr>
					
					</table>
					
				</fieldset>


				<fieldset>
					
						<legend>Marketing</legend>
				<table class="marketing">
				<tr>
				<td><input type="checkbox" name="advertisement"> Advertisement 
				</td>
				<td>
				<input type="checkbox" name="parents" > Parents
				
				</td>
			
				</tr>
				<tr>
				<td>
				<input type="checkbox" name="paper" > Paper </td>
				<td>
				<input type="checkbox" name="channels" > Channels 
				</td>
				</tr>
				<tr>
				<td>
				<input type="checkbox" name="websites">Websites</td>
				<td>
				<input type="checkbox" name="other"> Others 
				</td>
				
								 </tr>
			                   </table>
                             </fieldset>
                           </form>
						  </div>
						</div>
					<br />
				</div>
			</div>
		</div>
	</div>

	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script>
</body>
</html>