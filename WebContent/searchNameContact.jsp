<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
	<div class="container">
		<h2>Chercher un contact</h2>
		
		<form action="GetName" method="post" class="form-horizontal">
			
			<div class="form-group">
				<label for="inputSiretNum">Last Name</label> <input type="text"
					id="inputSiretNum" name="lname" Value="1" placeholder="Laste Name Contact"
					class="form-control" />
			</div>
			<div class="form-group">
				<label for="inputSiretNum">First Name</label> <input type="text"
					id="inputSiretNum" name="fname" Value="1" placeholder="First Name Contact"
					class="form-control" />
			</div>
			
			<button type="submit" class="btn btn-primary">Search</button>
		</form>
		
		
	</div>
</t:wrapper>