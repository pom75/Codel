<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<t:wrapper>
	<div class="container">
		<h2>Supprimer un contact</h2>
		<s:form action="RM">
				<s:textfield name="id" label="Id USer" />
				<s:submit />
		</s:form>
	</div>
</t:wrapper>