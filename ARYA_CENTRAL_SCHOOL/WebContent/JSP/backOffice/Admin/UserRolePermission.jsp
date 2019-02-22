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

<script src="JS/newUI/jquery.js"></script>
<script src="JS/newUI/bootstrap.min.js"></script>
<script src="JS/backOffice/Admin/UserRolePermission.js"></script>
<script type="text/javascript" src="JS/common.js"></script>

<title>eCampus Pro</title>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}

</style><style>
.buttons{

vertical-align: -32px;

}
.modal{
width:620px;
max-height:500px; 
left:0;
right:0;
margin:auto;

}

.modal-header{

/* width:618px; */
    padding: 8px 15px;
    background: #07AAB9;
    text-align: center;
    color: #fff;
    border-radius: 5px 5px 0px 0


}


</style>
</head>

<body>

<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Assign Permissions</span>
		</p>
		
		
				<div class="successmessagediv" align="left" style="display: none;">
						<div class="message-item">
							<!-- Warning -->
							<a href="#" class="msg-success bg-msg-succes"><span
								class="validateTips"></span></a>
						</div>
					</div>	
					

					
				<div class="errormessagediv" align="center" style="display: none;">
					<div class="message-item">
					<!-- Warning -->
				    <a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
					</div>
					</div>	
					
				
				

					<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>
					

		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Role Permissions
							</h4></a>
						
							<div class="navbar-right" >
								
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="RPMADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
							<span id="PreviewPermission" class="buttons">Save</span>
							</logic:equal>
							</logic:equal>
							</logic:iterate>
							</logic:present>
								
								
					
							</div>
						
					</div>
					
				
	<!-- Preview Start -->
	
	
	
		<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		
		<div class = "modal-content" >
  			<div class = "modal-header">
  				
  				
  		<button type="button" id="closepre" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Preview</h4>
  				
  				
  			</div>
  			
  			
  			<table  class="table" id="allstudent" style="font-family: Open Sans Light;color: #897676;" >
	<tr>
	 <th  width="20px !important;">Module</th>
	 <th style="min-width : 150px; text-align : center" >Sub Module</th>
	 <th style="text-align: center;width:130px !important;">PageAccess</th>  
	 <th   style="text-align: center;width:130px !important;">Add</th>
	 <th  style="text-align: center;width:130px !important;">Update</th> 
	 <th style="text-align: center;width:130px !important;">Delete</th>
	  <th  style="text-align: center;width:130px !important;">Download</th>
	  
	
	</tr>
	
	<logic:present name="RolePermission" scope="session">
	<logic:iterate id="creation" name="RolePermission" property="permissionList">
	<logic:notEmpty name="creation" property="submodule">
	<tr>
	
	
	 <td width="20px !important;"><bean:write name="creation" property="module"/></td>
	 <td >&nbsp;<input type="checkbox" id='${creation.submodule}All' />&nbsp;&nbsp;<bean:write name="creation" property="submodule"/></td>
	
	<td style="text-align: center;"><logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	  <logic:iterate id="operation3" name="RolePermission" property="permissionList">
		<logic:match name="operation3" value="${creation.submodule} Display"  property="permissionName" >
		<input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation3" property="permissionId"/>' name='<bean:write name="operation3" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match>
	</td>
	
	<td style="text-align: center;">
	<logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	<logic:iterate id="operation" name="RolePermission" property="permissionList">
		<logic:match name="operation" value="${creation.submodule} Creation"  property="permissionName" >
		<input type="checkbox" class="permission permission${operation.submodule}" id='<bean:write name="operation" property="permissionId"/>' name='<bean:write name="operation" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match>
	</td>
	
	<td style="text-align: center;"><logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	  <logic:iterate id="operation1" name="RolePermission" property="permissionList">
		<logic:match name="operation1" value="${creation.submodule} Update"  property="permissionName" >
		<input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation1" property="permissionId"/>' name='<bean:write name="operation1" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match>
	</td>
	
	<td style="text-align: center;"><logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	  <logic:iterate id="operation2" name="RolePermission" property="permissionList">
		<logic:match name="operation2" value="${creation.submodule} Delete"  property="permissionName" >
		<input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation2" property="permissionId"/>' name='<bean:write name="operation2" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match>
	</td>
	
	
	<td style="text-align: center;"><logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	  <logic:iterate id="operation4" name="RolePermission" property="permissionList">
		<logic:match name="operation4" value="${creation.submodule} Download"  property="permissionName" >
		<input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation4" property="permissionId"/>' name='<bean:write name="operation4" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match></td>
	</tr>
	</logic:notEmpty>
	</logic:iterate>
	</logic:present>
	</table>
	
	
	
	
	<div class="modal-footer">
          <div class="errormessagediv1" style="display: none;float: left;">
						<div class="message-item1">
							<!-- Warning -->
							<a href="#" class="msg-warning1 bg-msg-warning1"><span style="100%" class="validateTips"></span></a>
						</div>
					</div>
        <button type="button" id="savePermission" class="btn btn-info">Save</button>
		<button type="button" id="closePreview" class="btn btn-info" data-dismiss="modal">Close</button>
      </div>
	
	
	</div>
	
</div>
	<!-- Preview End -->			
					<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="filters">

					<div class="col-md-12" style="padding:0; ">
					<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
							  <div class="panel-body"  id="tabletxt" style="padding:15px 0;">
							
								<div class="col-md-6" id="txtstyle">
									<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4" id="inputnames">Select Role<font color="red"></font></label>
									<div class="col-xs-7" id="selecti">
									<select name="roleName" id="roleId" class="form-control">
									<option value=""></option>
									<logic:present name="RolePermission" scope="session">
									<logic:iterate id="role" name="RolePermission" property="roleList" scope="session">
									<option value='<bean:write name="role" property="roleCode"/>,<bean:write name="role" property="roleName"/>'><bean:write name="role" property="roleName"/></option>
									</logic:iterate>
									</logic:present>
									</select>	
										</div> 
									</div><br/>
								</div>
							
								<div class="col-md-6" id="txtstyle">
									
									<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4" id="inputnames"><input type="checkbox" id='selectAll' name="" >&nbsp;&nbsp;&nbsp;&nbsp;Select All<font color="red"></font></label>
										
									</div><br/>
								</div>
								
			</div></div></div>
					</div>
					</div>
	
	<div class="col-md-12" style="padding:0;height:300px; !important;overflow-y:scroll !important; ">				
	<table  class="table" id="allstudents" style="font-family: Open Sans Light;color: #897676;" >
		<tr>
	 		<th  width="20px !important;">Module</th>
			 <th >Sub Module</th>
			 
			 
			 <th style="text-align: center;width:130px !important;">PageAccess</th>  
			 <th style="text-align: center;width:130px !important;">Add</th>
	 		 <th style="text-align: center;width:130px !important;">Update</th> 
	 	 	 <th style="text-align: center;width:130px !important;">Delete</th>
	 		 <th style="text-align: center;width:130px !important;">Download</th>
		</tr>
	
	<logic:present name="RolePermission" scope="session">
	<logic:iterate id="creation" name="RolePermission" property="permissionList">
	<logic:notEmpty name="creation" property="submodule">
	<tr>
	
	
	 <td width="20px !important;"><bean:write name="creation" property="module"/></td>
	 <td >&nbsp;<input type="checkbox" id='${creation.submodule}All_base' class='submoduleCheck' />&nbsp;&nbsp;<bean:write name="creation" property="submodule"/></td>
	
	<td style="text-align: center;"><logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	  <logic:iterate id="operation3" name="RolePermission" property="permissionList">
		<logic:match name="operation3" value="${creation.submodule} Display"  property="permissionName" >
		<input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation3" property="permissionId"/>_base' name='<bean:write name="operation3" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match>
	</td>
	
	<td style="text-align: center;">
	<logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	<logic:iterate id="operation" name="RolePermission" property="permissionList">
		<logic:match name="operation" value="${creation.submodule} Creation"  property="permissionName" >
		<input type="checkbox" class="permission permission${operation.submodule}" id='<bean:write name="operation" property="permissionId"/>_base' name='<bean:write name="operation" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match>
	</td>
	
	<td style="text-align: center;"><logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	  <logic:iterate id="operation1" name="RolePermission" property="permissionList">
		<logic:match name="operation1" value="${creation.submodule} Update"  property="permissionName" >
		<input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation1" property="permissionId"/>_base' name='<bean:write name="operation1" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match>
	</td>
	
	<td style="text-align: center;"><logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	  <logic:iterate id="operation2" name="RolePermission" property="permissionList">
		<logic:match name="operation2" value="${creation.submodule} Delete"  property="permissionName" >
		<input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation2" property="permissionId"/>_base' name='<bean:write name="operation2" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match>
	</td>
	
	
	<td style="text-align: center;"><logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	  <logic:iterate id="operation4" name="RolePermission" property="permissionList">
		<logic:match name="operation4" value="${creation.submodule} Download"  property="permissionName" >
		<input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation4" property="permissionId"/>_base' name='<bean:write name="operation4" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match></td>
	
	</tr>
	</logic:notEmpty>
	</logic:iterate>
	</logic:present>
	

	
	</table>
	</div>				
					
					
					</div>
					</div>
					</form>
					</div>
					


</body>
</html>