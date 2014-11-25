<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
	<div class="container">
		<h2>Sommaire :</h2>
		<div>
			<ul class="nav nav-pills nav-stacked span3">
				<!--  Rather use buttun style ;) -->
				<li><a href="addContact.jsp">Ajouter un contact</a></li>
				<li><a href="removeContact.jsp">Supprimer un contact</a></li>
				<li><a href="updateContact.jsp">Mettre un jour un contact</a></li>
				<li><a href="searchContact.jsp">Chercher un contact</a></li>
			</ul>
		</div>
	</div>
</t:wrapper>