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

<title>Transport Type Master</title>
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JS/backOffice/Admin/TransportTypeMaster.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
	

<style>

.save2:hover {
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
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		
		<p style="margin: 10px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Add Transport Type</span>
			
		</p>
		
	
			
						<div class="errormessagediv" style="display: none;margin-left: 30%;width: 100% !important;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning" style="text-align: center;"><span
									class="validateTips" style="text-align: center;"></span></a>
							</div>
						</div>
				
			
					
						<div class="successmessagediv" style="display: none;margin-left: 30%;width: 100%;">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-success bg-msg-succes" style="text-align: center;"><span class="successmessage" style="text-align: center;"></span></a>
								</div>
						</div>
		
						
	
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Add Transport Type
							</a>
						</h4>

							<div class="navbar-right">
								
								<span class="buttons"  id="save" >Save
									
								</span>&nbsp;
								
								 
								
								<span id="back" class="buttons" >Back</span></a>
							
							</div>
						
				
				</div>

					<br/>
					
							
				<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-size: 11pt;font-family: Open Sans Light; color: #5d5d5d;">
								
								<div class="form-group">
								<input type="hidden" id="typeId" value="<logic:present name="transportTypeVO" scope="request"><bean:write name="transportTypeVO" property="transptyId"></bean:write></logic:present>" />
								<input type="hidden" id="hcollectionType" value="<logic:present name="transportTypeVO" scope="request"><bean:write name="transportTypeVO" property="type"></bean:write></logic:present>" />
								
									<label for="inputName" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Name
										</label>
									<div class="col-xs-7">
										<input type="text" class="form-control"  onkeypress="HideError()" id="name" value="<logic:present name="transportTypeVO" scope="request"><bean:write name="transportTypeVO" property="transptyname"></bean:write></logic:present>"
											  maxlength="30">
									</div>
								</div>
                                 <br />
								<div class="form-group">
									<label for="inputDescription" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;margin-right: 1px;">Description
										</label>
									<div class="col-xs-7">
										<textarea style="resize:none" rows="4" class="form-control" maxlength="250"
											name="description" id="description"  ><logic:present name="transportTypeVO" scope="request"><bean:write name="transportTypeVO" property="transptydes"></bean:write></logic:present></textarea>
									</div>
								</div>
							
								
								
							</div>
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
							
								<div class="form-group">
								      <label for="inputtype" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Concession Type
										</label>
									<div class="col-xs-7" id="radioDiv" style="margin-top: 8px">
										<input type="radio" name="transporttype" value="Y" class="transporttype"  />&nbsp;&nbsp;&nbsp;Yes
											 <input type="radio" name="transporttype" class="transporttype"
												value="N" />&nbsp;&nbsp;&nbsp;No
									</div>
								</div>
								<br />

								
								<br />
								
								<br />
								
								<br />
								
								<br />
								
								<br />
								
								<br />
							</div>
						</div>
					</div>
				
					<!-- <button type="reset" class="btn btn-info" id="clear">Clear</button> -->
					<br />
				</div>
		</div>
	</div>
</body>

</html>


