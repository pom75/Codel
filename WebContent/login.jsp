<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<t:wrapper>
	<div class="container">
	<h2>Connexion</h2>
 
	<s:form action="Welcome" class="form-horizontal">
		
		
		<s:submit class="btn btn-default" />
		<s:reset class="btn btn-default"/>
	</s:form>
 </div>
 	<div class="container">
		<h2>Connexion</h2>
		<s:form method="post" action="login" class="form-horizontal">
			<div class="form-group">
				<s:label for="login">Login</s:label>
				<s:textfield class="form-control" name="username" label="Username" />
			</div>
			<div class="form-group">
				<s:label for="password">Password</s:label> 
				<s:password name="password" label="Password" />
			</div>
			<s:button type="submit" class="btn btn-default">Submit</s:button>
			<s:button type="reset" class="btn btn-default">Reset</s:button>
		</s:form>
	</div>
</t:wrapper>