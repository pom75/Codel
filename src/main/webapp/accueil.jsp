<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<t:wrapper>
	<div class="container">
		<h1>Bienvenue <s:property value="username" /></h1>
		<h2>Sommaire :</h2>
		<div>
			<ul class="nav nav-pills nav-stacked span3">
				<!--  Rather use buttun style ;) -->
				<li><a href="/Codel/Contact/PrepAdd.action">Ajouter un contact</a></li>
				<li><a href="/Codel/Contact/PrepRm.action">Supprimer un contact</a></li>
				<li><a href="/Codel/Contact/PrepUp.action">Mettre un jour un contact</a></li>
				<li><a href="/Codel/Contact/PrepGet.action">Chercher un contact by id</a></li>
				<li><a href="/Codel/Contact/PrepGetName.action">Chercher un contact by Name</a></li>
				<li><a href="/Codel/Contact/PrepGetSiret.action">Chercher un contact by Siret</a></li>
				<li><a href="/Codel/Contact/AllGet.action">Mes contacts</a></li>
				<li><a href="/Codel/contact/generate">Generer Stub</a></li>
			</ul>
		</div>
	</div>
</t:wrapper>