<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="user">
<p>Id : <s:property value="id" /></p>
		<p>Prenom : ${fname}</p>
		<p>Nom : ${lname}</p>
		<p>email : ${email}</p>
		<p>street : ${street}</p>
		<p>city : ${city}</p>
		<p>zip :${zip} </p>
		<p>country : ${country}</p>
		<p>Siret num : ${siretNum}</p>
			<ul class="nav nav-pills nav-stacked span3">
				<li><a href="/Codel/accueil.jsp">Accueil</a></li>
			</ul>
</div>
