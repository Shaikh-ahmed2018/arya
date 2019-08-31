<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link href="CSS/newUI/custome.css" rel="stylesheet">

<title>eCampus Pro</title>
<style type="text/css">
.pagebanner {
    margin-top: 20px;
}
.leftNav h3.panel-title{
	cursor: pointer;
	color:#fff;
}

#allstudent tbody td{
border:1px solid #dedede
}
#allstudent tbody,#allstudent tbody td:last-child{
border:none;
}
#allstudent tbody td:last-child:hover{
background-color: transparent !important;
}
#allstudent thead th:last-child{
	background-color: transparent !important;
}

 #allstudent thead tr{
border: 1px solid #dedede;
}
 #allstudent thead tr,#allstudent thead tr th:last-child{
border:none;
} 




#allstudent tr:hover, .allstudent tr:hover, #allstudents tr:hover{
background:transparent !important;
}
#allstudent tr:hover, .allstudent tr:hover, #allstudents tr:hover {
    background:transparent;
    cursor: pointer;
    }
#allstudent tr td:last-child, .allstudent tr td:last-child, #allstudents tr td:last-child{
background:transparent;
}
#allstudent tr:nth-child(n),.allstudent tr:nth-child(n),#allstudents tr:nth-child(n) {
	background-color: transparent;
}

#allstudent tr:nth-child(2n),.allstudent tr:nth-child(2n),#allstudents tr:nth-child(2n) {
	background-color: transparent;
}
#allstudent tr:nth-child(n) td,.allstudent tr:nth-child(n) td,#allstudents tr:nth-child(n) td {
	background-color: #F2FAFC;
}

#allstudent tr:nth-child(2n) td:last-child,.allstudent tr:nth-child(2n) td:last-child,#allstudents tr:nth-child(2n) td:last-child {
	background-color: transparent;
}
#allstudent tr:nth-child(n) td:last-child,.allstudent tr:nth-child(n) td:last-child,#allstudents tr:nth-child(n) td:last-child {
	background-color: transparent;
}

#allstudent tr:nth-child(2n) td,.allstudent tr:nth-child(2n) td,#allstudents tr:nth-child(2n) td {
	background-color: #FFFFFF;
}



#allstudent tbody tr td,#allstudent thead tr th{
position: relative;
min-width: 30px;
}
.deleteRow,#addgroup{
height:20px;
line-height: 16px;
position: absolute;
right: 0px;
top: 0;
bottom: 0;
margin: auto;
}
#allstudent tbody td{
border: 1px solid #dedede;
    padding: 5px;
}
.glyphicon-plus {
	font-size: 20px;
	line-height: 34px;
	color: #989898;
	padding: 2px 12px;
	margin-top: -6px;
	height: 0px;
	position: relative;
}

.multiple{
height: auto !important;
}
.glyphicon-plus:before {
	content: "\2b";
	margin-right: -3px;
}
table#allstudent thead tr {
    background-color: #f5f5f5 !important;
}
</style>
</head>
<body>

			<div class="col-md-2 leftmenu">

				  
						  
						  
		 			 
			 <div class="panel panel-default" style="margin: -1px 0px;">

				<div class="panel-heading leftNav"  id="headingThree"
					style="background: #07AAB9;">
				 <h3 class="panel-title active">
						Settings &nbsp;&nbsp;<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
					</h3>
				</div>
				
				<div id="menucollapseThree" class="navigation panel-collapse collapse">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="EVENMD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="EventsMenu.html?method=EventRegistration" id="sub_module_13_8">Event Registration</a>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>


							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="EVENMD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="EventsMenu.html?method=categorySetting" id="sub_module_13_9">Category Setting</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EVENMD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="EventsMenu.html?method=stageSetting" id="sub_module_13_10">Stage Setting</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EVENMD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="EventsMenu.html?method=programSetting" id="sub_module_13_11">Program Setting</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EVENMD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="EventsMenu.html?method=GreenRoomSetting" id="sub_module_13_12">Green Room Setting</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EVENMD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="EventsMenu.html?method=PrizeLevelSetting" id="sub_module_13_13">Prize Level Setting</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						  <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EVENMD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="EventsMenu.html?method=CriteriaSetting" id="sub_module_13_14">Criteria Setting</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="panel panel-default" style="margin: -1px 0px;">
							<div class="panel-heading leftNav"  id="headingOne" style="background: #07AAB9;">
							 <h3 class="panel-title active">Registration<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i></h3>
							</div>
							
							<div id="menucollapseOne" class="navigation panel-collapse collapse in">
							  <div class="panel-body">
								<ul class="nav nav-pills nav-stacked">
								
								
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EVENMD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
										<li><a href="EventsMenu.html?method=EventstudentRegistration" id="sub_module_13_1">Student Registration</a></li>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	
								
									
									
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EVENMD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
										<li><a href="EventsMenu.html?method=VolunteerReg" id="sub_module_13_2">Volunteer Registration</a></li>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	
							

							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EVENMD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
										<li><a href="EventsMenu.html?method=StageAllocation" id="sub_module_13_3">Stage Allocation</a></li>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	
								</ul>
								
							  </div>
							</div>
						  </div> 
				  
						  
						  
		 			 <div class="panel panel-default" style="margin: -1px 0px;">
							<div class="panel-heading leftNav" id="headingTwo" style="background: #07AAB9;">
							  <h3 class="panel-title">Scheduling<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
				              </h3>
					</div>
					
				<div id="menucollapseTwo" class="navigation panel-collapse collapse">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EVENMD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="EventsMenu.html?method=ProgramNumbering" id="sub_module_13_4">Program Numbering </a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
					<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EVENMD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="EventsMenu.html?method=ProgramScheduling" id="sub_module_13_5">Program Scheduling</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EVENMD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="EventsMenu.html?method=ChestNoGeneration" id="sub_module_13_19">Chest Number Generation</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EVENMD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="EventsMenu.html?method=IdCardDesign" id="sub_module_13_23">Event Id Design</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="EVENMD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="EventsMenu.html?method=eventIdCardPrintSingle" id="sub_module_13_7">Print Event ID Card</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						

					</ul>

					</div>
				</div>
			</div> 
		
 				


			<div class="panel panel-default" style="margin: -1px 0px;">
				<div class="panel-heading leftNav" id="headingFour"
					style="background: #07AAB9;">
					<h3 class="panel-title">Marks Process &nbsp;&nbsp;
					<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
			        </h3>
				</div>
				
				
				<div id="menucollapseFour" class="navigation panel-collapse collapse">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">


<%-- 
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="EVENMD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value"> 
											<li><a href="EventsMenu.html?method=marksEntry" id="sub_module_13_15">Marks Entry old</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> 
							 --%>
							 <!--  marks entry new -->
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="EVENMD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value"> 
											<li><a href="EventsMenu.html?method=marksEntryNew" id="sub_module_13_15">Marks Entry </a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>  
							 
						</ul>

					</div>
				</div>
			</div>

			

			<div class="panel panel-default" style="margin: -1px 0px;">
				<div class="panel-heading leftNav" style="background: #07AAB9;">
				<h3 class="panel-title active" >Report &nbsp;&nbsp;
								<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
					</div>
					
				<div id="menucollapseFive" class="navigation panel-collapse collapse">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">


					<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="EVENMD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value"> 
											<li><a href="EventsMenu.html?method=ParticipantsList" id="sub_module_13_16">Participants List</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>  


							
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="EVENMD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value"> 
											<li><a href="EventsMenu.html?method=ResultPrinting" id="sub_module_13_20">Result Printing</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>  
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="EVENMD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value"> 
											<li><a href="EventsMenu.html?method=JudgementSheet" id="sub_module_13_18">Selected Student Merit List</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> 
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="EVENMD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value"> 
											<li><a href="EventsMenu.html?method=CertificatePrinting" id="sub_module_13_21">Certificate Printing</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>  
							
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="EVENMD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value"> 
											<li><a href="EventsMenu.html?method=OverallResult" id="sub_module_13_22">Overall result</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>  
								
						</ul>

					</div>
				</div>
			</div>
			
		</div>


	
	<script>
		$(document)
				.ready(
						function() {
							$(".leftNav").click(function(){
								var toggleDive=$(this).next(".navigation.panel-collapse");
							$(".navigation.panel-collapse").not(toggleDive).slideUp();	
								toggleDive.toggle();
								});
						var currentTab=$("a[href='"+window.location.href.substring(window.location.href.lastIndexOf("/")+1)+"']");
						$(".navigation").removeClass("in");	
						currentTab.closest(".navigation").addClass("in");
								
							});
							
						
	</script>

</body>
</html>


