<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title><s:text name="title" /></title>
</head>
<body>
	<h1>
		<s:text name="hello" />
	</h1>

	<h4>
		Hello
		<s:property value="username" />
	</h4>

</body>
</html>
