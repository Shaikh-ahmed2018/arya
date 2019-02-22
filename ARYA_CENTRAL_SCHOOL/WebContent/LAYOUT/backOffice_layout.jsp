<!-- Written By Ratna -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<!DOCTYPE HTML>

<html>
<head>
<link rel="apple-touch-icon" sizes="57x57" href="images/fevicon/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="images/fevicon/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="images/fevicon/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="images/fevicon/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="images/fevicon/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="images/fevicon/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="images/fevicon/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="images/fevicon/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="images/fevicon/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"  href="images/fevicon/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="images/fevicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="images/fevicon/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="images/fevicon/favicon-16x16.png">
<link rel="manifest" href="images/fevicon/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<tiles:insert attribute="header" />
	<section class="content">
	<div class="row">
	<tiles:insert attribute="menu" />
	<tiles:insert attribute="body" />
	</div>
	</section>
	<%-- <tiles:insert attribute="footer" /> --%>
</body>
</html>
