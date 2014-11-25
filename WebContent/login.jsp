<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<t:wrapper>
	<div class="container">
		<div class="container">
			<h2><s:text name="titleCo" /></h2>
			<s:form action="Welcome">
				<s:textfield name="username" label="Username" />
				<s:password name="password" label="Password" />
				<s:submit />
			</s:form>
		</div>
</t:wrapper>