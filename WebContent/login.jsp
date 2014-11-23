<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<!--  On passera ptetre après à une tuile -->
<%@include file="templates/header.jsp"%>
<body>
	<div class="container">
		<h2>Connexion</h2>

		<form method="post" action="login" class="form-horizontal">
			<div class="form-group">
				<label for="login">Login</label> <input type="login"
					class="form-control" id="login" name="login"
					placeholder="Enter Login">
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control" id="password" placeholder="Password"
					name="password">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
			<button type="reset" class="btn btn-default">Reset</button>
		</form>
	</div>
</body>
</html>