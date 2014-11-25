<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
	<div class="container">
		<h2>Chercher un contact</h2>
		
		<form action="contact/search" method="post" class="form-horizontal">
			<div class="form-group">
				<label for="inputSiretNum">First Name</label> <input type="text"
					id="inputSiretNum" name="fname" Value="Jean" placeholder="First Name Contact"
					class="form-control" />
			</div>
			<div class="form-group">
				<label for="inputSiretNum">Last Name</label> <input type="text"
					id="inputSiretNum" name="lname" Value="Dupont" placeholder="Last Name Contact"
					class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="inputSiretNum">Email Name</label> <input type="text"
					id="inputSiretNum" name="email" Value="zdzdz@kldnl.com" placeholder="Email Contact"
					class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="inputSiretNum">Id</label> <input type="text"
					id="inputSiretNum" name="id" Value="1" placeholder="ID Contact"
					class="form-control" />
			</div>
			
			<button type="submit" class="btn btn-primary">Search</button>
		</form>
	</div>
</t:wrapper>