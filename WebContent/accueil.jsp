<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="includes/header.jsp" %>
<body>
	<div class="container">
		<h2>Sommaire :</h2>
		<div>
			<ul class="nav nav-pills nav-stacked span3">
				<li><a href="addContact.jsp">Ajouter un contact</a></li>
				<li><a href="removeContact.jsp">Supprimer un contact</a></li>
				<li><a href="updateContact.jsp">Mettre un jour un contact</a></li>
				<li><a href="searchContact.jsp">Chercher un contact</a></li>
			</ul>
		</div>
	</div>
</body>
</html>