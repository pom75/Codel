<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
	<div class="container">
		<h2>Supprimer un contact</h2>
		<form action="Rm" method="post" class="form-horizontal">
			<div class="form-group">
				<label for="inputSiretNum">Id Contact</label> <input type="text"
					id="inputSiretNum" name="id" Value="12" placeholder="Id Contact"
					class="form-control" />
			</div>
			<button type="submit" class="btn btn-primary">Remove</button>
		</form>
	</div>
</t:wrapper>