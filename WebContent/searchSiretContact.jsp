<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
	<div class="container">
		<h2>Chercher un contact</h2>
		
		<form action="GetSiret" method="post" class="form-horizontal">
			
			<div class="form-group">
				<label for="inputSiretNum">Siret</label> <input type="text"
					id="inputSiretNum" name="numSiret" Value="1" placeholder="Siret Contact"
					class="form-control" />
			</div>
			
			<button type="submit" class="btn btn-primary">Search</button>
		</form>
		
		
	</div>
</t:wrapper>