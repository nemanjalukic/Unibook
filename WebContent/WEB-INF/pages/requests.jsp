<%@page import="net.etfbl.ip.projektni.dto.User"%>
<%@page import="net.etfbl.ip.projektni.dto.Fakultet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" type="net.etfbl.ip.projektni.beans.UserBean"
	scope="session" />
<jsp:useBean id="fakultetBean"
	type="net.etfbl.ip.projektni.beans.FakultetBean" scope="session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Zahtijevi za povezivanje</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/home.css" />
<script type="text/javascript" src="js/projektniGA.js"></script>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"></a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="?action=home">Početna</a></li>
					<li><a href="?action=users">Korisnici</a></li>
					<li class="active"><a href="?action=requests">Zahtijevi za
							povezivanje <span class="badge" id="requests"><%=userBean.getRequests().size()%></span>
					</a></li>
					<li><a href="?action=profile&id=<%=userBean.getUser().getId()%>">Profil</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="?action=logout"><span
							class="	glyphicon glyphicon-log-out"></span> Odjava</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-sm-8">

				<h3>Zahtijevi za povezivanje</h3>
				<div class="conn">
					<%
						for (User user : userBean.getRequests()) {
					%>
					<div class="well">
						<div class="row rowAllign">
							<div class="col-sm-3">
								<p><a href="?action=profile&id=<%=user.getId()%>"><img src="images/<%=user.getSlika()%>" class="img-circle"
									style="width: 50px"></a></p>
							</div>
							<div class="col-sm-5 text-left">
								<h4><%=user.getIme() + " " + user.getPrezime()%></h4>
							</div>
							<div class="col-sm-2 text-right">

								<button class="btn btn-primary"
									id="prihvatiBtn<%=user.getId()%>"
									onclick="prihvatiPrijatelja(<%=user.getId()%>)">Prihvati</button>
							</div>
							<div class="col-sm-2 text-right">
								<button class="btn btn-primary" id="odbijBtn<%=user.getId()%>"
									onclick="odbijPrijatelja(<%=user.getId()%>)">Odbij</button>
							</div>
						</div>
					</div>
					<%
						}
					%>

				</div>
			</div>
		<jsp:include page="blogandfiles.jsp" />

		</div>
	</div>
	<br>
	<br>
<footer class="container-fluid text-center">
		<p>UNI-BL</p>
	</footer>
</body>
</html>