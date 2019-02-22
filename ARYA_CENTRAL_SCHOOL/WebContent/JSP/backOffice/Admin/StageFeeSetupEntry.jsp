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
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JS/backOffice/Admin/StageFeeSetup.js"></script>
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
.glyphicon:hover{

cursor: pointer;
}


</style>



<body>


	<div class="col-md-10 col-md-offset-2" id="div1">
	 <div class="searchWrap">
		<div class="col-md-7" id="div2">

			<p >
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Transportation Fee Setup</span>
			</p>
			
			
		</div>
		<div align="right" class="input-group col-md-5">
		

			<input type="text" class="form-control" Placeholder="Search......" id="searchterm" value="<logic:present name="searchTerm"><bean:write name="searchTerm"></bean:write></logic:present>">
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
								<a href="#" class="msg-success bg-msg-succes"><span>
										<bean:write name="success" scope="request" />
								</span></a>
							</div>
						</div>
					</logic:present>
					<logic:present name="error" scope="request">
						<div class="successmessagediv" align="center">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span>
										<bean:write name="error" scope="request" />
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
		
			
			
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Transportation Fee Setup
						 
					</h3></a>
				<div class="navbar-right" >
					
						
					<span class="buttons" id="insert">Add
					</span>
					
					 <span id="savefee" class="buttons">Save
					 </span>
					 
					  <span id="back" class="buttons" >Back</a></span>
					
					
				</div>
			</div>
			
			<script>
	
	</script>
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
					<input type="hidden" id="hclassId" value="<logic:present name="classidVal"><bean:write name="classidVal"/></logic:present>"/>
					<input type="hidden" id="hyearId" value="<logic:present name="classidVal"><bean:write name="acadamicYearVal"/></logic:present>"/>
					<input type="hidden" id="htermId" value="<logic:present name="classidVal"><bean:write name="termVal"/></logic:present>"/>
						
						
						<display:table name="requestScope.getApprovedFeeMasterSetupDetails"  
							requestURI="/stagefeesetup.html?method=getEditedFeeSetupDetails"  export="false"
							class="main table" id="allstudent" decorator="com.centris.campus.decorator.StageFeeSetupDecorator">
						
							<display:column property="sno" title="S.No <img src='images/sort1.png' style='float: right'/>" sortable="true" />
							<display:column property="feecode" title="Stage code"  class="hidden" headerClass="hidden" sortable="true"/>	
							<display:column property="feename" title="Stage Name<img src='images/sort1.png' style='float: right'/>" sortable="true" />
							<display:column property="feeamount" title="Fee Amount<img src='images/sort1.png' style='float: right'/>" style="width:40%" sortable="true" />
							<display:column property="delete" title="Delete" sortable="true"/>

						</display:table>
						
						<br />
					
				
				</div>
				<br />
			</div>
			
	
			
			
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" id="registrationConfirmationDiv">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Add Fee</h4>
    
      </div>
      	<div class="modal-body" id="scroll">
			<div class="panel-body" id="tabletxt">
					
						<logic:present name="getActiveFeeMasterSetupDetails"
						scope="request">

						<display:table class="table" id="allstudent" 
							name="requestScope.getActiveFeeMasterSetupDetails" 
							requestURI="/stagefeesetup.html?parameter=getAllFees"
							decorator="com.centris.campus.decorator.StageFeeSetupDecorator"
							>
							<display:column property="feeSetupDetailsCheckBox" media="html"
								 sortable="true" 
								title='<input type="checkbox" id="AllSessCheckBox"  class="chk"/>' />
							<display:column property="sno" title="S.No <img src='images/sort1.png' style='float: right'/>"
								 sortable="false" />
							<display:column property="feecode" title="Fee ID"
								 sortable="false" class="hidden"
								headerClass="hidden" />
							<display:column property="feename" title="Fee Name <img src='images/sort1.png' style='float: right'/>" />

						</display:table>
					</logic:present>	
						
				  </div>
      </div>
      <div class="modal-footer">
          <div class="errormessagediv1" style="display: none;float: left;">
						<div class="message-item1">
							<!-- Warning -->
							<a href="#" class="msg-warning1 bg-msg-warning1"><span style="100%" class="validateTips"></span></a>
						</div>
					</div>
        <button type="button" id="approve" class="btn btn-info">Approval</button>
		<button type="button" id="close" class="btn btn-info" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
			
			
		</div>
	</div>
</div>
	
</body>
</html>



	