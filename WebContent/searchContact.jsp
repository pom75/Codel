<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
	<div class="container">
		<h2>Chercher un contact</h2>
		
		<form action="Get" method="post" class="form-horizontal">
			<div class="form-group">
			
			<div class="form-group">
				<label for="inputSiretNum">Id</label> <input type="text"
					id="inputSiretNum" name="id" Value="1" placeholder="ID Contact"
					class="form-control" />
			</div>
			
			<button type="submit" class="btn btn-primary">Search</button>
		</form>
		
		
	</div>
</t:wrapper>