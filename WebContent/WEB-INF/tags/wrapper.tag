<%@ tag description="Page template!!" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<!DOCTYPE html>
<html>
<head>
<title><s:text name="title" /></title>
<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<link href="css/agency.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery.js"></script>
</head>
<body>
	<!-- Do some customisation here :) -->

	<jsp:doBody />



	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<footer>
	 
<s:url id="localeEN" namespace="/" action="locale" >
   <s:param name="request_locale" >en</s:param>
</s:url>
<s:url id="localezhCN" namespace="/" action="locale" >
   <s:param name="request_locale" >zh_CN</s:param>
</s:url>
<s:url id="localeDE" namespace="/" action="locale" >
   <s:param name="request_locale" >de</s:param>
</s:url>
	<p> <s:text name="language.select" />
	<s:a href="%{localeEN}" >En</s:a>
    <s:a href="%{localeFR}" >Fr</s:a>
    </p>
	</footer>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
