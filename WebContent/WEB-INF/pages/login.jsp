<%@page import="net.etfbl.ip.projektni.beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Prijava</title>
<link rel="stylesheet" type="text/css" href="css/bs.css" />
</head>
<body>


	<div class="login-page">
		<div class="form">
			<form class="login-form" method="POST" action="?action=login">
				
				<input type="text" name="username" placeholder="Korisničko ime"
				id="username" required>
				<input type="password" placeholder="Lozinka"
				name="password" id="password" required>
				<p class="n">
					<%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString()
					: ""%>
				</p>
				<button type="submit" name="submit">Prijava</button>
				<p class="message">
					Niste registrovani? <a href="?action=registration">Kreirajte nalog</a>
				</p>
			</form>
		</div>
	</div>
</body>
</html>