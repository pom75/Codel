<%@ tag description="Page template!!" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<!DOCTYPE html>
<html>
<head>
<title><s:text name="title" /></title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/agency.css">
<script src="https://code.jquery.com/jquery.js"></script>
</head>
<body>
	<!-- Do some customisation here :) -->

	<jsp:doBody />



	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<footer>
		<s:url id="localeEN" namespace="/" action="locale">
			<s:param name="request_locale">en</s:param>
		</s:url>
		<s:url id="localeFR" namespace="/" action="locale">
			<s:param name="request_locale">fr</s:param>
		</s:url>
		<p>
			<s:text name="language.select" />
			<s:a href="%{localeEN}">En</s:a>
			<s:a href="%{localeFR}">Fr</s:a>
		</p>
	</footer>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
