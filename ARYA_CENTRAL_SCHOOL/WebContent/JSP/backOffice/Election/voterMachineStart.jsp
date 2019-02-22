<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html>
<head>
<title>Election</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>

<link href="JQUERY/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="JQUERY/css/jquery.ui.dialog.css" rel="stylesheet" type="text/css"/>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/backOffice/Election/voterMachine.js"></script>
<style type="text/css">
#fullScreen{
margin: auto;
}
.image{
width: 300px;
height:600px;
margin: auto auto;
position: absolute;
top:0;
left:0;
bottom:0;
right:0; 
}
.stuImage{
height: 200px;
width: 200px;
border: 1px solid #ccc;
margin: auto;
}
</style>

</head>
<body>


  
 <div id="fullScreen">
 <input type="hidden" id="electionaccyear" value='<logic:present name="accyear" scope="request"><bean:write name="accyear"  /></logic:present>' />
 <input type="hidden" id="electiongroup" value='<logic:present name="group" scope="request"><bean:write name="group"  /></logic:present>' />
 <input type="hidden" id="electiontitleID" value='<logic:present name="titleID" scope="request"><bean:write name="titleID"  /></logic:present>' />
 <div class="image">
 
 <div class="stuImage"></div>
 <img src="images/images.jpg" height="200" width="600">
 	<div style="margin-bottom:200px; margin-left:100px;">
 	
	<span class="buttons" style="margin-top:-10px; width:300px; height:600px !important;" id="startActivation"> Click on Photo to Cast Vote
	</span>
	</div>
				
 </div>
 </div>
<span id="public_ip" style="display:none"></span>
<span id="LocalIp" style="display:none"><logic:present name="IP" scope="session"><bean:write name="IP"  /></logic:present></span>
<div id="error"></div>
<script>
var publicIp = false;
function getip(ip) {
  publicIp = ip.ip;
  public_ip.textContent = publicIp;
  setTimeout(function(){extract_ipv4("")}, 1000);
}
</script>
</body>
</html>