<!DOCTYPE html>
<html lang="en">
<head>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<style type="text/css">
.modulenamecl{
   text-align: center;
}

</style>
</head>
<body>
	<div class="col-md-10 col-md-offset-2" id="div-main">
	
		<div class="modulenamecl"><h2><logic:present name="moduleName" scope="request"><bean:write name="moduleName" scope="request" /></logic:present></h2></div>
	</div>
</html>