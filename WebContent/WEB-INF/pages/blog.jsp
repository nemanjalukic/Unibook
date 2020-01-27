<%@page import="net.etfbl.ip.projektni.dto.User"%>
<%@page import="net.etfbl.ip.projektni.dto.Blog"%>
<%@page import="net.etfbl.ip.projektni.dto.Komentar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" type="net.etfbl.ip.projektni.beans.UserBean"
	scope="session" />
<jsp:useBean id="blogBean" type="net.etfbl.ip.projektni.beans.BlogBean"
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
<script type="text/javascript" src="js/projektniGA.js"></script>
</head>
<body>

	<div class="row">


		<%
			for (Blog blog : blogBean.getBlogovi()) {
		%>

		<div class="media text-left well">
			<div class="media-left">
				<p><a href="?action=profile&id=<%=userBean.getByID(blog.getUserID()).getId()%>"><img src="images/<%=userBean.getByID(blog.getUserID()).getSlika()%>" class="media-object" style="width: 45px"></a></p>
			</div>
			<div class="media-body">
				<h4 class="media-heading"><%=userBean.getByID(blog.getUserID()).getIme() + " "
						+ userBean.getByID(blog.getUserID()).getPrezime()%><small><i><%=blog.getStringVijemeKreiranja()%></i></small>
				</h4>
				<p><%=blog.getTema()%></p>
				<%
					if(blog.getKomentari()!=null)
					for (Komentar kom : blog.getKomentari()) {
				%>
				<!-- Nested media object -->
				<div class="media">
					<div class="media-left">
						<p><a href="?action=profile&id=<%=userBean.getByID(kom.getUserID()).getId()%>"><img src="images/<%=userBean.getByID(kom.getUserID()).getSlika()%>" class="media-object"
							style="width: 45px"></a></p>
					</div>
					<div class="media-body">
						<h4 class="media-heading">
							<%=userBean.getByID(kom.getUserID()).getIme() + " "
							+ userBean.getByID(kom.getUserID()).getPrezime()%>
						</h4>
						<p><%=kom.getKomentar()%></p>
					</div>

				</div>
				<%
					}
				%>
				<form id="blogform<%=blog.getId()%>" method="post" class="text-left">
					<div class="input-group">
						<input type="text" id="komentar<%=blog.getId()%>" class="form-control"
							placeholder="Unesite komentar"/> 
							<div class="input-group-btn">
							<input type="button" class="btn btn-primary" value="Posalji" onclick="actionAddKomentar(<%=userBean.getUser().getId()%>,'<%=blog.getId()%>')"/>
					</div>
					</div>
				</form>
			</div>

		</div>
		<%
			}
		%>
	</div>
</body>
</html>
