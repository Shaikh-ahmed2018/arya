<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
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
<script type="text/javascript" src="JS/backOffice/Fee/fineConfiguration.js"></script>
<style>
.save:hover {
	cursor: pointer;
}
#allstudent th,#allstudent td{ text-align: center;}
#allstudent td input{width: 100%;border: none;background: transparent;text-align: right;}
#allstudent{margin-bottom: 10px !important;}
</style>

</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		
		<div class="row">
			<div class="col-sm-7">
				<p>
					<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Fine
				Configuration </span>
			</p>
			</div>
			<div class="col-sm-5">
				
			
			<div class="form-group clearfix" style="margin-bottom: 10px; margin: 10px 0">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" >
										<logic:present name="AccyearId" scope="request">
											<logic:iterate id="AccyearId" name="AccyearId">
											<option value='<bean:write name="AccyearId" property="accId" />'><bean:write name="AccyearId" property="accName" /></option>
											</logic:iterate>
										</logic:present>
								
										</select>
									</div>
								</div>
			</div>
		</div>
		
	

			<div class="successmessagediv" align="center" style="display:none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
				</div>
			</div>


		

		

		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		<div class="fineConfuration">
		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Fee
						Fine Configuration
					</h3></a>
				<div class="navbar-right">


					 <span id="save" class="buttons">Save</span>
		 			<span id="back" class="buttons">Back</span>
				</div>



			</div>
			
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				
				<div class="fineConfigurationBox">
				<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-10 col-md-offset-2">
				<span style="margin-right: 5px">IsApplicable</span><span> <input id="IsApplicable" type="checkbox" value="Y" /></span>
				</div>
				</div>
				
				<table id="allstudent" width="85%" style="margin: 0 auto;">
					<thead>
						<tr>
							<th>Condition</th>
							<th>Academic Year</th>
							<th>School</th>
							<th>Class</th>
							<th>Term</th>
							<th>Date</th>
							<th>Amount</th>
						</tr>
					</thead>
					<tbody>
					<logic:present name="fineList" scope="request">
					<logic:iterate id="fineList" name="fineList">
					<tr>
							<td>>=</td>
							<td><select  class="accyear" ></select><input type="hidden" value='<bean:write name="fineList" property="accyearid" />' class="haccyearid" /></td>
							<td><select  class="school" ><option value="">------SELECT------</option></select><input type="hidden" value='<bean:write name="fineList" property="locationid" />' class="hlocationid" /></td>
							<td><select class="ClassList" ><option value="">------SELECT------</option></select><input type="hidden" value='<bean:write name="fineList" property="classnameid" />' class="hclassnameid" /></td>
							<td><select class="termlist" ><option value="">------SELECT------</option></select><input type="hidden" value='<bean:write name="fineList" property="termId" />' class="htermlist" /></td>
							<td><input type="text" class="date" value='<bean:write name="fineList" property="fineDate" />' readonly="readonly" /></td>
							<td><input type="number" class="amount" value='<bean:write name="fineList" property="fine" />' /></td>
							<input type="hidden" value='<bean:write name="fineList" property="isApplicable" />' class="isApply" />
						</tr>
					</logic:iterate>
					</logic:present>
						<tr>
							<td>>=</td>
							<td><select  class="accyear" >
								
							</select></td>
							<td><select  class="school" >
								<option value="">------SELECT------</option>
							</select></td>
							<td><select  class="ClassList" >
								<option value="">------SELECT------</option>
							</select></td>
							<td><select  class="termlist" >
								<option value="">------SELECT------</option>
							</select></td>
							<td><input type="text" class="date" readonly="readonly"/></td>
							<td><input type="number" class="amount" /></td>
						</tr>
						
					</tbody>
				</table>
				</div>


				</div>
				
			</div>
		</div>
		</div>
		
	</div>
	</body>
</html>
