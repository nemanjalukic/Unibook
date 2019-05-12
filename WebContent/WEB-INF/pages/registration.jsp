<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/bs.css" />
		<title>Kreiranje novog naloga</title>
		<script type="text/javascript" src="js/registracija.js"></script>
	</head>
	<body>

<div class="login-page">
  <div class="form">
    <form class="login-form" method="POST" action="?action=registration">
      <input type="text" placeholder="Korsničko ime" name="username" id="username" required>
      <input type="password" placeholder="Lozinka minimalno 8 karaktera" pattern=".{8,}" name="pass1" id="pass1" required>
      <input type="password" onkeyup="passwordCheck()" placeholder="Ponovite lozinku" pattern=".{8,}" name="pass2" id="pass2" required>
      <input type="email" placeholder="E-mail adresa" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" id="email" required/>
	  <input type="text" name="lastName" id="lastName" placeholder="Prezime" required>
	  <input type="text" name="firstName" id="firstName"  placeholder="Ime" required>
	  <h4><%=session.getAttribute("notification").toString() %></h4> <br />
      <button  type="submit" name="submit" id="regBtn">Kreiraj nalog</button>
      
      <p class="message">Već registrovan? <a href="?action=">Prijavi se</a></p>
    </form>
  </div>
</div>
	</body>
</html>