<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<t:wrapper>
	<div class="container">
		<div class="container">
			<h2>
				<s:text name="titleCo" />
			</h2>

			<form action="Welcome" method="post" class="form-horizontal">

				<div class="form-group">
					<label for="inputSiretNum">Last Name</label> <input type="text"
						id="inputSiretNum" name="unsername" Value="1"
						placeholder="Laste Name Contact" class="form-control" />
				</div>
				<div class="form-group">
					<label for="inputSiretNum">First Name</label> <input
						type="password" id="inputSiretNum" name="password" Value="1"
						placeholder="First Name Contact" class="form-control" />
				</div>

				<button type="submit" class="btn btn-primary">Search</button>
			</form>
		</div>
	</div>
</t:wrapper>