<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajout d'un contact</title>
<link type="text/css" rel="stylesheet" href="css/bootstrap.css"/>
<!-- Custom CSS -->
<link href="css/agency.css" rel="stylesheet">
</head>
<body>
<h1>Ajout d'un nouveau contact :</h1>

<form action="addcontact" method="post"
	class="form-horizontal">
	<div class="control-group">
		<label class="control-label" for="inputSiretNum">Siret number</label>
		<div class="controls">
			<input type="text" id="inputSiretNum" name="siretNum"
				Value="123456789" placeholder="Siret number" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputFname">First Name</label>
		<div class="controls">
			<input type="text" id="inputFName" name="fname" Value="Test"
				placeholder="Firstname" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputLname">Last Name</label>
		<div class="controls">
			<input type="text" id="inputLName" name="lname" Value="Test"
				placeholder="Lastname" />
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="inputEmail">Email</label>
		<div class="controls">
			<input type="text" name="email" id="inputEmail" Value="Test"
				placeholder="Email" />
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="inputStreet">Street</label>
		<div class="controls">
			<input type="text" name="street" id="inputStreet" Value="Test"
				placeholder="Street" />
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="inputZip">Zip code</label>
		<div class="controls">
			<input type="text" name="zip" id="inputZip" Value="Test"
				placeholder="Zip code" />
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="inputCity">City</label>
		<div class="controls">
			<input type="text" name="city" id="inputCity" Value="Test"
				placeholder="City" />
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="inputCountry">Country</label>
		<div class="controls">
			<input type="text" name="country" id="inputCountry" Value="Test"
				placeholder="Country" />
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="inputCountry">Home</label>
		<div class="controls">
			<input type="text" name="homepn" id="inputHomepn" Value="010101"
				placeholder="Home phone number" />
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="inputOfficepn">Office</label>
		<div class="controls">
			<input type="text" name="officepn" id="inputOfficepn" Value="010101"
				placeholder="Office phone number" />
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="inputOfficepn">Mobile</label>
		<div class="controls">
			<input type="text" name="mobilepn" id="inputOfficepn" Value="010101"
				placeholder="Mobile phone number" />
		</div>
	</div>

	<div class="control-group">
		<div class="controls">
			<button type="reset" class="btn btn-primary">Reset</button>
			<button type="submit" class="btn btn-primary">Create</button>
		</div>
	</div>
</form>
<script src="js/bootstrap.js"></script>
</body>
</html>