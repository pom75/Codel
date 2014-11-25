<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
	<div class="container">
		<h2><s:text name="majC" /></h2>
		
		<form action="Up" method="post" class="form-horizontal">
			<div class="form-group">
				<label  for="inputId"><s:text name="IDC" />
					</label> <input type="text" id="inputId" name="id"
					Value="12" placeholder="Id Contact" class="form-control"/>
			</div>
			<div class="form-group">
				<label  for="inputSiretNum"><s:text name="SIRC" />
					</label> <input type="text" id="inputSiretNum" name="siretNum"
					Value="123456789" placeholder="Siret number" class="form-control"/>
			</div>

			<div class="form-group">
				<label  for="inputFname"><s:text name="FNAME" /></label> <input
					type="text" id="inputFName" name="fname" Value="Test"
					placeholder="Firstname"class="form-control" />
			</div>

			<div class="form-group">
				<label  for="inputLname"><s:text name="LNAME" /></label> <input
					type="text" id="inputLName" name="lname" Value="Test"
					placeholder="Lastname" class="form-control"/>
			</div>

			<div class="form-group">
				<label  for="inputEmail">Mail</label> <input
					type="text" name="email" id="inputEmail" Value="Test"
					placeholder="Email" class="form-control"/>
			</div>

			<div class="form-group">
				<label  for="inputStreet"><s:text name="STREET" /></label> <input
					type="text" name="street" id="inputStreet" Value="Test"
					placeholder="Street" class="form-control"/>
			</div>

			<div class="form-group">
				<label  for="inputZip"><s:text name="ZIP" /></label> <input
					type="text" name="zip" id="inputZip" Value="Test"
					placeholder="Zip code" class="form-control"/>
			</div>

			<div class="form-group">
				<label  for="inputCity">City</label> <input
					type="text" name="city" id="inputCity" Value="Test"
					placeholder="City" class="form-control"/>
			</div>

			<div class="form-group">
				<label  for="inputCountry">Country</label> <input
					type="text" name="country" id="inputCountry" Value="Test"
					placeholder="Country" class="form-control"/>
			</div>

			<div class="form-group">
				<label  for="inputCountry">Home</label> <input
					type="text" name="homeNum" id="inputHomepn" Value="010101"
					placeholder="Home phone number"class="form-control" />

			</div>

			<div class="form-group">
				<label  for="inputOfficepn">Office</label> <input
					type="text" name="officeNum" id="inputOfficepn" Value="010101"
					placeholder="Office phone number"  class="form-control"/>
			</div>


			<div class="form-group">
				<label  for="inputOfficepn">Mobile</label> <input
					type="text" name="mobileNum" id="inputOfficepn" Value="010101"
					placeholder="Mobile phone number" class="form-control"/>
			</div>

			<button type="reset" class="btn btn-primary">Reset</button>
			<button type="submit" class="btn btn-primary">Create</button>
		</form>
	</div>
</t:wrapper>