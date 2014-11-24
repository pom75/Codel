<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<!--  On passera ptetre après à une tuile -->
<%@include file="templates/header.jsp"%>
<body>
	<div class="container">
	<h2>Connexion</h2>
 
	<s:form action="Welcome" class="form-horizontal">
		<s:textfield name="login" label="Username" />
		<s:password name="password" label="Password" />
		<s:submit class="btn btn-default" />
		<s:reset class="btn btn-default"/>
	</s:form>
 </div>
</body>
</html>