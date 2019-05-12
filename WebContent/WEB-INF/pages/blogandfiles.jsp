<%@page import="net.etfbl.ip.projektni.dto.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" type="net.etfbl.ip.projektni.beans.UserBean"
	scope="session" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
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
<div class="col-sm-4">
			<div class="well">
				<form method="post" id="temaForm" class="text-left">
					<div class="input-group">
						<input type="text" id="tema" class="form-control"
							placeholder="Unesite temu">

						<div class="input-group-btn">
							<input type="button" class="btn btn-primary" value="Dodaj"
								onclick="actionAddTema(<%=userBean.getUser().getId()%>)" />
						</div>
					</div>
				</form>
			</div>
			<div class="well blog">
				<jsp:include page="blog.jsp" />
			</div>
						<div class="well">
				<form id="uploadForm" method="post" class="text-left"
					enctype="multipart/form-data">
					<div class="form-group form-horizontal">
						<input type="file" id="selectedFile" name="guru_file"
							style="display: none;" onchange="showFilename()" />
						<div class="input-group">
							<div class="input-group-btn">
								<input type="button" class="btn btn-primary" value="Izaberi fajl"
									onclick="choseFile()" />
							</div>
							<input type="text" id="nazivFajla" class="form-control" readonly>
						</div>
					</div>
					<div class="input-group">
						<div class="input-group">
							<span class="input-group-addon">Opis</span> <input id="opis"
								type="text" class="form-control" name="opis"
								placeholder="Unesite opis fajla">
						</div>
						<div class="input-group-btn">
						<input type="button" class="btn btn-primary" value="Dodaj fajl"
							onclick="actionUpload(<%=userBean.getUser().getId()%>)" />
							</div>
					</div>
				</form>
				<p><%=request.getAttribute("gurumessage") != null ? request.getAttribute("gurumessage") : ""%></p>


			</div>
			<div class="well blog">
						<jsp:include page="files.jsp" />
					</div>
				</div>
</body>
</html>