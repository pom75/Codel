<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
	<div class="container">
		<h2>Ajout d'un nouveau contact</h2>
		
		<form action="contact/update" method="post" class="form-horizontal">
			<div class="form-group">
				<label  for="inputId">Id
					</label> <input type="text" id="inputId" name="id"
					Value="12" placeholder="Id Contact" class="form-control"/>
			</div>
			<div class="form-group">
				<label  for="inputSiretNum">Siret
					number</label> <input type="text" id="inputSiretNum" name="siretNum"
					Value="123456789" placeholder="Siret number" class="form-control"/>
			</div>

			<div class="form-group">
				<label  for="inputFname">First Name</label> <input
					type="text" id="inputFName" name="fname" Value="Test"
					placeholder="Firstname"class="form-control" />
			</div>

			<div class="form-group">
				<label  for="inputLname">Last Name</label> <input
					type="text" id="inputLName" name="lname" Value="Test"
					placeholder="Lastname" class="form-control"/>
			</div>

			<div class="form-group">
				<label  for="inputEmail">Email</label> <input
					type="text" name="email" id="inputEmail" Value="Test"
					placeholder="Email" class="form-control"/>
			</div>

			<div class="form-group">
				<label  for="inputStreet">Street</label> <input
					type="text" name="street" id="inputStreet" Value="Test"
					placeholder="Street" class="form-control"/>
			</div>

			<div class="form-group">
				<label  for="inputZip">Zip code</label> <input
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
					type="text" name="homepn" id="inputHomepn" Value="010101"
					placeholder="Home phone number"class="form-control" />

			</div>

			<div class="form-group">
				<label  for="inputOfficepn">Office</label> <input
					type="text" name="officepn" id="inputOfficepn" Value="010101"
					placeholder="Office phone number"  class="form-control"/>
			</div>


			<div class="form-group">
				<label  for="inputOfficepn">Mobile</label> <input
					type="text" name="mobilepn" id="inputOfficepn" Value="010101"
					placeholder="Mobile phone number" class="form-control"/>
			</div>

			<button type="reset" class="btn btn-primary">Reset</button>
			<button type="submit" class="btn btn-primary">Create</button>
		</form>
	</div>
</t:wrapper>