<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="templates/header.jsp"%>
<body>
	<div class="container">
		<h2>Supprimer un contact</h2>
		<form action="contact/delete" method="post" class="form-horizontal">
			<div class="form-group">
				<label for="inputSiretNum">Id Contact</label> <input type="text"
					id="inputSiretNum" name="id" Value="12" placeholder="Id Contact"
					class="form-control" />
			</div>
			<button type="submit" class="btn btn-primary">Remove</button>
		</form>
	</div>
</body>
</html>