<%@page import="net.etfbl.ip.projektni.dto.User"%>
<%@page import="net.etfbl.ip.projektni.dto.Fajl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" type="net.etfbl.ip.projektni.beans.UserBean"
	scope="session" />
<jsp:useBean id="fajlBean"
	type="net.etfbl.ip.projektni.beans.FajlBean" scope="session" />
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
</head>
<body>

	<div class="row">
		
<%
			for (Fajl fajl : fajlBean.getAll()) {
		%>

			<!-- Nested media object -->
			<div class="media">
				<div class="media-left">
					<p><a href="?action=profile&id=<%=fajl.getUserID()%>"><img src="images/<%=userBean.getByID(fajl.getUserID()).getSlika()%>" class="media-object" width="45"></a></p>
				</div>
				<div class="media-body">
					<h4 class="media-heading">
						<a href="?action=download&fajlPutanja=<%=fajl.getPutanja()%>"><%=fajl.getPutanja()%></a>
					</h4>
					<p><%=fajl.getOpis()%></p>
				</div>
			</div>
			<%} %>
	</div>
</body>
</html>
