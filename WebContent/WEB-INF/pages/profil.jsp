<%@page import="net.etfbl.ip.projektni.dto.User"%>
<%@page import="net.etfbl.ip.projektni.dto.Fakultet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" type="net.etfbl.ip.projektni.beans.UserBean"
	scope="session" />
<jsp:useBean id="fakultetBean"
	type="net.etfbl.ip.projektni.beans.FakultetBean" scope="session" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>Profil</title>
<meta charset="utf-8">
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
					<li id="homeNav"><a href="?action=home">Početna</a></li>
					<li><a href="?action=users" id="usersNav">Korisnici</a></li>
					<li><a href="?action=requests" id="reqNav">Zahtijevi za
							povezivanje <span class="badge" id="requests"><%=userBean.getRequests().size()%></span>
					</a></li>
					<li class="active"><a
						href="?action=profile&id=<%=userBean.getUser().getId()%>"
						id="profilNav">Profil</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="?action=logout"><span
							class="	glyphicon glyphicon-log-out"></span> Odjava</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container text-left">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div class="row well">
					<div class="text-center">
						<img src="images/<%=userBean.getProfileOfUser().getSlika()%>" height="50%"
							width="50%" id="profilePicture" alt="Avatar"> <br> <br>
					</div>
					<form id="uploadFormSlika" method="post" class="text-left"
						enctype="multipart/form-data">
						<div class="form-group form-horizontal">
							<input type="file" id="selectedPicture" name="guru_file"
								accept="image/*" style="display: none;" onchange="showPicture()" />
							<div class="text-center">
								<input type="button" id="pictureButton" class="btn btn-primary" value="Izaberi sliku"
									onclick="chosePicture()" />
							</div>

						</div>





						<div class="form-group" id="usernameFormGroup">
							<label>Korisničko ime:</label> <input type="text" class="form-control"
								value="<%=userBean.getProfileOfUser().getUsername()%>"
								id="username">
						</div>
						<div class="form-group">
							<label>Ime:</label> <input type="text" class="form-control"
								value="<%=userBean.getProfileOfUser().getIme()%>" id="ime">
						</div>
						<div class="form-group">
							<label>Prezime:</label> <input type="text" class="form-control"
								value="<%=userBean.getProfileOfUser().getPrezime()%>"
								id="prezime">
						</div>
						<div id="toHide">
							<div class="form-group">
								<label>Email adresa:</label> <input type="email"
									value="<%=userBean.getProfileOfUser().getEmail()%>"
									class="form-control" id="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
							</div>
							<div class="form-group">
								<label>Studijski program:</label> <input type="text"
									value="<%=userBean.getProfileOfUser().getStudijskiProgram() != null
					? userBean.getProfileOfUser().getStudijskiProgram()
					: ""%>"
									class="form-control" id="studprogram">
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label>Godina studija:</label> <select class="form-control"
											id="selgodina">
											<%
												for (int i = 1; i < 7; i++) {
													if (i == userBean.getProfileOfUser().getGodinaStudija()) {
											%>
											<option selected="selected"><%=i%></option>
											<%
												} else {
											%>
											<option><%=i%></option>
											<%
												}
												}
											%>
										</select>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<label>Fakultet:</label> <select class="form-control"
											id="selfakultet">
											<%
												for (Fakultet fakultet : fakultetBean.getAllFakulteti()) {
													if (userBean.getProfileOfUser().getFakultet()!=null && fakultet.getId() == userBean.getProfileOfUser().getFakultet().getId()) {
											%>
											<option selected="selected"><%=fakultet.getNaziv()%></option>
											<%
												} else {
											%>
											<option><%=fakultet.getNaziv()%></option>
											<%
												}
												}
											%>
										</select>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="interesovanje">Interesovanje</label> <input
									type="text" class="form-control"
									value="<%=userBean.getProfileOfUser().getInteresovaanje() != null
					? userBean.getProfileOfUser().getInteresovaanje()
					: ""%>"
									id="interesovanje">
							</div>
							<p><%=request.getAttribute("gurumessage") != null ? request.getAttribute("gurumessage") : ""%></p>

						</div>

						<div class="text-left col-sm-6">
							<input type="button" class="btn btn-primary" id="passButton"
								value="Promijeni lozinku" onclick="showPasswordDialog()" />
						</div>
						<div class="text-right col-sm-6">
							<input type="button" class="btn btn-primary" value="Dodaj promijene" id="uploadButton"
								onclick="actionUploadData()" />
						</div>


					</form>
				</div>
				<div class="row displaynone" id="passdialog">
					<div class="col-sm-12 well">
						
						<div class="col-sm-12">
							<button type="button" class="close" aria-label="Close" onclick="hidePassChanger()">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="form-group">
							<label>Stara lozinka:</label> <input type="password"
								class="form-control" id="pwdold" required>
						</div>
						<div class="form-group" id="fgnew">
							<label>Nova lozinka:</label>
							<div class="col-sm-12 input-group">

								<input type="password" class="form-control" id="pwd1"
									onkeyup="checkPassword()" placeholder="Minimalno 8 karaktera" pattern=".{8,}" required> <span
									class="input-group-addon" id="spnnew" ><i id="inew"></i></span>
							</div>
						</div>
						<div class="form-group" id="fgcon">
							<label>Potvrdite novu lozinku:</label>
							<div class="col-sm-12 input-group">

								<input type="password" class="form-control" id="pwd2"
									onkeyup="checkPassword()" placeholder="Minimalno 8 karaktera" pattern=".{8,}" required> <span
									class="input-group-addon" id="spncon"><i id="icon"></i></span>
							</div>
						</div>

						<p class="msgAlert" id="alertmsg">
						
						<p>
						<div class="text-right">
							<input type="button" class="btn btn-primary" id="chpassbtn"
								value="Promijeni lozinku" onclick="changePassword()" disabled/>
						</div>

					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
		<br>
	<br>
	<footer class="container-fluid text-center">
		<p>UNI-BL</p>
	</footer>

	<%
		if (userBean.getUser().getId() != userBean.getProfileOfUser().getId()) {
			if (userBean.isFreind()) {
	%>
	<script>
		hideFreind();
	</script>

	<%
		}

			else {
	%>

	<script>
		hide();
	</script>
	<%
		}
		} else {
			if (userBean.getUser().getStatus() == 0) {
	%>
	<script>
		hideRegUser();
	</script>
	<%
		}
		}
	%>
</body>
</html>