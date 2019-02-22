<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
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
<link href="CSS/Admin/StudentNew.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Student/AcademicyearHouseSetting.js"></script>

<script type="text/javascript">
function isNumberKey(evt)
{
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (((charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122)))
        return false;
    else
    return true;
} 

</script>

<style>
#allstudent {
	width : 100%;
}
#allstudent td{
	text-align: left;
}
#allstudent td:nth-child(3){
	padding: 0 7px;
}

#allstudent th:nth-child(1){
	width:100px;
}
.glyphicon-edit{
	
	font-size: 17px;
    line-height: 27px;
    color: #989898;
    padding: 0;
    top: 9px;
    height: 30px;
    right: 0;
    position: relative;
	
}
.glyphicon-plus{
font-size: 15px;
line-height: 43px;
color: #989898;
padding: 2px 12px;
margin-top: -15px;
height: 39px;
position: relative;
}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p><img src="images/addstu.png" /><span id="pageHeading">House Setting</span></p>
				<div class="panel-body clearfix"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
					<div id="editDialog" style="display: none">
							<div class="col-md-12" style="margin-top:50px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">House Name</label>
									<div class="col-xs-7">
										<input type="text" id="housenameedit" class="form-control" onkeypress ="return isNumberKey(event);"/>
										<input type="hidden" id="hiddenhousename" class="form-control" />
									</div>
								</div>
							</div>
					</div>
					
					<div id="addDialog" style="display: none">
							<div class="col-md-12" style="margin-top:50px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: left; line-height: 35px;">House Name</label>
									<div class="col-xs-7">
										<input type="text" name="housename" id="housename" class="form-control" onkeypress ="return isNumberKey(event);"/>
									</div>
								</div>
							</div>
					</div>
					
					<div id="deleteDialog" style="display: none">
					<p>Are You Sure to Delete?</p>
					</div>
				</div>
				
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
				
				
			<!-- <div class="clearfix">
				
			</div> -->
			
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne" style="margin-bottom: 10px;">
						
							<a  href="#" style="color: #767676;"> 
							<h4 class="panel-title grade"><i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;House Setting
							</h4></a>
						
							<div class="navbar-right" >
									<span class="buttons" id="addgrade">Add</span>
									<span id="back" class="buttons">Back</a></span>
							</div>
						</div>
				

				<div id="gradeOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
						
						<div class="col-md-6" id="inputcolor"style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	
											maxlength="25" class="form-control" readonly="readonly"
											value='<logic:present name="locName"><bean:write name="locName"/></logic:present>' />
									</div>
									<input type="hidden" name="hiddenlocid" id="hiddenlocid" value='<logic:present name="locid"><bean:write name="locid"></bean:write></logic:present>'/>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="accyName" tabindex="1"	maxlength="25" class="form-control" readonly="readonly"
											value='<logic:present name="accyName"><bean:write name="accyName"/></logic:present>' />
									</div>
									<input type="hidden" name="hiddenaccyear" id="hiddenaccyear" value='<logic:present name="accid"><bean:write name="accid"></bean:write></logic:present>'/>
								</div>
				</div>
				<div class ="col-md-6"></div>
						<%-- <div class="form-group">
						<table style="background: #fff;" class="allstudent" id="allstudent">
			
						<tr>
						<th style="font-size: 15px; text-align: center;" >Sl.No</th>
						<th style="font-size: 15px; text-align: center;">House Name</th>
						
						<th align="center" style="text-align:center;"><span class="buttons" id="addgrade" style="line-height: 40px;">Add</span>
							 <!-- onclick="addMoreGrades(this.form);" -->
						</th>
					   </tr>
					
					   <logic:present name="gethouseSettingsList" scope="request">
					   	<logic:iterate id="gethouseSettingsList" name="gethouseSettingsList" scope="request">
					   		<tr>
					   		<td><bean:write name="gethouseSettingsList" property="slno" /></td>
					   		<td class="housename"><bean:write name="gethouseSettingsList" property="housename"/></td>
							<td style="text-align:center;"><span class="buttons editing" id='<bean:write name="gethouseSettingsList" property="houseid"/>'>Edit</span><span id='<bean:write name="gethouseSettingsList" property="houseid"/>' class="buttons delete" style="line-height: 40px;margin-left: 10px;">Delete</span></td>
					   		</tr>
					   	</logic:iterate>
					   </logic:present>
				
					  </table>
					</div> --%>
			<div class = "col-md-12">
					
					<%-- <display:table  class="table" id="allstudent"
						name="gethouseSettingsList" pagesize="10"
						requestURI="/houseSettings.html?method=AcademicYearHouseSetting">
	
                        <display:column property="slno" sortable="true" title="Sl.No <img src='images/sort1.png' style='float: right'/>" />
						<display:column class="housename" property="housename" sortable="true" title="HouseName <img src='images/sort1.png' style='float: right'/>" />
						<display:column title=""><span class="buttons editing" id='${allstudent.houseid}'>Edit</span><span id='${allstudent.houseid}' class="buttons delete" style="line-height: 40px;margin-left: 10px;">Delete</span></display:column>
						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>
				
					</display:table> --%>
		        <logic:present name="gethouseSettingsList" scope="request">			
					<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Sl.No </th>
							<th>HouseName</th>
							<th></th>
							
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="gethouseSettingsList" name="gethouseSettingsList">
								<tr>
								<td><bean:write name='gethouseSettingsList' property="slno"/></td>
								<td class="housename"><bean:write name='gethouseSettingsList' property="housename"/></td>
								<td><span class="buttons editing" id='<bean:write name="gethouseSettingsList" property='houseid'/>'>Edit</span><span id='<bean:write name="gethouseSettingsList" property='houseid'/>' class="buttons delete" style="line-height: 40px;margin-left: 10px;">Delete</span></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					
					
					
					</logic:present>
		
			</div>	
					
					</div>
				<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
						</div><div class='pagination pagelinks' id="page"></div>
			
				<br />
				</div>
		</div>
	</div>
</div>
</html>


	
