<!-- Written By Ratna -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<title>Latheef Science Technology Montessori and Primary School</title>
<script type="text/javascript" src="JQuery/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JS/Admin/Latheef.js"></script>
<script type="text/javascript" src="JS/Admin/CareersApply.js"></script>
<link href="CSS/common.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" type="icon" href="images/favicon.ico" />
</head>
<body>
	<br />
	<div class="ui-content">
		<!-- Tab panes -->
		<div class="tab-content">
			<div class="box">
				<div class="box-head">Apply Job</div>

				<span id="mandatory">* fields are mandatory </span>
	   
	   <center>
	         <logic:present name="errorMessage" scope="request">
			
						<div class="successmessagediv">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-warning bg-msg-warning"><span>
								<bean:write name="errorMessage" scope="request" /></span></a>
							</div>
							
							
						</div>
		</logic:present>
		</center>
				<html:form action="/applyJob" method="post"
					enctype="multipart/form-data" onsubmit="return Jobvalidate();">
					<input type="hidden" name="method" value="saveAppliedJob" />
					<table class="form">
						<tr>
							<td align="left" valign="middle"><label>Job Code </label></td>
							<td align="left" valign="middle"><input type="text"
								name="jobcode" value='<logic:present name="jobid" scope="request"><bean:write name="jobid" /></logic:present>'
								readonly="readonly"></input></td>
							<td align="left" valign="middle"><label>Name<font
									color="red">*</font></label></td>
							<td align="left" valign="middle"><input type="text"
								name="name" id="name"></input></td>

							<td align="left" valign="middle"><label> Email ID<font
									color="red">*</font></label></td>
							<td align="left" valign="middle" style="width: 22%;"><input
								type="text" name="email" id="emailID" /></td>

							<td align="left" valign="middle"><label> Mobile No<font
									color="red">*</font></label></td>
							<td align="left" valign="middle"><input type="text"
								name="mobno" id="mobno" maxlength="10"></input></td>
						</tr>
						<tr>
							<td align="left" valign="middle"><label>Address<font
									color="red">*</font></label></td>

							<td align="left" valign="middle"><textarea name="address"
									id="address" rows="2" cols="22" style="resize: none"></textarea></td>


							<td align="left" valign="middle"><label> Upload
									Resume<font color="red">*</font>
							</label></td>
							<td align="left" valign="middle" style="width: 20%;"><input
								type="file" name="resume" id="resume"
								class="custom-file-profile myFileInput1"></input></td>
						</tr>
					</table>
					<br />

					<center>
						<div class="errormessagediv1" style="display: none;">
							<div class="message-item1">
								<!-- Warning -->
								<a href="#" class="msg-warning1 bg-msg-warning1"
									style="width: 50%"><span class="validateTips"></span></a>
							</div>
						</div>
					</center>
					<br />
					<center>
						<table>
							<tr>
								<td colspan="2"><html:submit styleClass="btn btn-success"> Apply </html:submit>
								</td>
							</tr>
						</table>
					</center>
					<br />
				</html:form>
			</div>
			<br />
			 <span id="goBack" onclick="cancel()">Go back Home</span> 
			 	<br /><br />
		</div>

	</div>


</body>
</html>