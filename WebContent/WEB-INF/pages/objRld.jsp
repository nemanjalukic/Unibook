<%@page import="net.etfbl.ip.projektni.dto.User"%>
<%@page import="net.etfbl.ip.projektni.dto.Objava"%>
<%@page import="net.etfbl.ip.projektni.dto.ObjavaKorisnik"%>
<%@page import="net.etfbl.ip.projektni.dto.Dogadjaj"%>
<%@page import="net.etfbl.ip.projektni.dto.Vijest"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" type="net.etfbl.ip.projektni.beans.UserBean"
	scope="session" />
<jsp:useBean id="objavaBean"
	type="net.etfbl.ip.projektni.beans.ObjavaBean" scope="session" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/home.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/projektniGA.js"></script>
</head>
<body>
<%			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy hh:mm:ss");
			for (Objava objava : objavaBean.getObjave(userBean.getUser())) {
		%>
		<%
			if (objava instanceof ObjavaKorisnik) {
		%>
		<div class="panel panel-default">
		<div class="panel-body">
		<div class="row">
			<div class="col-sm-4">
				<div class="well">
					<p><a href="?action=profile&id=<%=((ObjavaKorisnik) objava).getUser().getId()%>"><img src="images/<%=((ObjavaKorisnik) objava).getUser().getSlika()%>" height="100%" width="100%"
						alt="Avatar"></a></p>
						<p><%=((ObjavaKorisnik) objava).getUser().getIme() + " "
							+ ((ObjavaKorisnik) objava).getUser().getPrezime()%></p>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="well text-left">
					<%if(((ObjavaKorisnik) objava).getTip()==3) {%>
					<p><%=((ObjavaKorisnik) objava).getSadrzaj()%></p>
					<%}
					else if(((ObjavaKorisnik) objava).getTip()==1){%>
						<div class="embed-responsive embed-responsive-16by9">
						<iframe class="embed-responsive-item"
							src=<%=((ObjavaKorisnik) objava).getSadrzaj()%>></iframe>
					</div>
					<%} else{%>
					<p><a><%=((ObjavaKorisnik) objava).getSadrzaj()%></a></p>
					<%}%>
					<div class="text-right">
					<small><i class="pull-left"><%=" " + formatter.format(objava.getVrijemeKreiranja())%></i></small>
						<%
							if (objavaBean.getLajkovanoOpcija(objava.getId(), userBean.getUser().getId()) == 1) {
						%>
						<form id="likeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjax(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="like<%=objava.getId()%>"
								class="like btn-my glyphicon glyphicon-thumbs-up"><%=objava.getLikes()%></button>
						</form>
						<form id="dislikeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjaxDislike(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="dislike<%=objava.getId()%>"
								class="btn-my glyphicon glyphicon-thumbs-down"><%=objava.getDislikes()%></button>
						</form>

						<%
							} else if (objavaBean.getLajkovanoOpcija(objava.getId(), userBean.getUser().getId()) == 2) {
						%>
						<form id="likeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjax(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="like<%=objava.getId()%>"
								class="btn-my glyphicon glyphicon-thumbs-up"><%=objava.getLikes()%></button>
						</form>
						<form id="dislikeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjaxDislike(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="dislike<%=objava.getId()%>"
								class="like btn-my glyphicon glyphicon-thumbs-down"><%=objava.getDislikes()%></button>
						</form>

						<%
							} else {
						%>
						<form id="likeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjax(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="like<%=objava.getId()%>"
								class="btn-my glyphicon glyphicon-thumbs-up"><%=objava.getLikes()%></button>
						</form>
						<form id="dislikeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjaxDislike(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="dislike<%=objava.getId()%>"
								class="btn-my glyphicon glyphicon-thumbs-down"><%=objava.getDislikes()%></button>
						</form>

						<%
							}
						%>
					</div>
				</div>
			</div>
			</div>
			</div>
		</div>
		<%
			} else if (objava instanceof Vijest) {
		%>
		<div class="panel panel-default">
		<div class="panel-body">
		<div class="row">
			<div class="col-sm-4">
				<div class="well">
					<p>Vijest</p>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="well">
					<p><%=((Vijest) objava).getSadrzaj()%></p>
				<div class="text-right">
				<small><i class="pull-left"><%=" " + formatter.format(objava.getVrijemeKreiranja())%></i></small>
						<%
							if (objavaBean.getLajkovanoOpcija(objava.getId(), userBean.getUser().getId()) == 1) {
						%>
						<form id="likeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjax(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="like<%=objava.getId()%>"
								class="like btn-my glyphicon glyphicon-thumbs-up"><%=objava.getLikes()%></button>
						</form>
						<form id="dislikeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjaxDislike(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="dislike<%=objava.getId()%>"
								class="btn-my glyphicon glyphicon-thumbs-down"><%=objava.getDislikes()%></button>
						</form>

						<%
							} else if (objavaBean.getLajkovanoOpcija(objava.getId(), userBean.getUser().getId()) == 2) {
						%>
						<form id="likeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjax(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="like<%=objava.getId()%>"
								class="btn-my glyphicon glyphicon-thumbs-up"><%=objava.getLikes()%></button>
						</form>
						<form id="dislikeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjaxDislike(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="dislike<%=objava.getId()%>"
								class="like btn-my glyphicon glyphicon-thumbs-down"><%=objava.getDislikes()%></button>
						</form>

						<%
							} else {
						%>
						<form id="likeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjax(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="like<%=objava.getId()%>"
								class="btn-my glyphicon glyphicon-thumbs-up"><%=objava.getLikes()%></button>
						</form>
						<form id="dislikeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjaxDislike(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="dislike<%=objava.getId()%>"
								class="btn-my glyphicon glyphicon-thumbs-down"><%=objava.getDislikes()%></button>
						</form>

						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
		</div>
		</div>
		<%
			} else if (objava instanceof Dogadjaj) {
					
		%>
		<div class="panel panel-default">
		<div class="panel-body">
		<div class="row">
			<div class="col-sm-4">
				<div class="well">
					
					<img src="<%=((Dogadjaj) objava).getSlika()%>"
						height="100%" width="100%" alt="Avatar">
						<p><%=((Dogadjaj) objava).getKategorija()%></p>
					
				</div>
			</div>
			<div class="col-sm-8">
				<div class="well text-left">
					<h4><%=((Dogadjaj) objava).getNaziv()%><small><i class="pull-right"><%="" + formatter.format(((Dogadjaj) objava).getDatumOdrzavanja())%></i></small>
					</h4>
					<p><%=((Dogadjaj) objava).getOpis()%></p>

					<div class="text-right">
					<small><i class="pull-left"><%=" " + formatter.format(((Dogadjaj) objava).getVrijemeKreiranja())%></i></small>
						<%
							if (objavaBean.getLajkovanoOpcija(objava.getId(), userBean.getUser().getId()) == 1) {
						%>
						<form id="likeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjax(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="like<%=objava.getId()%>"
								class="like btn-my glyphicon glyphicon-thumbs-up"><%=objava.getLikes()%></button>
						</form>
						<form id="dislikeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjaxDislike(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="dislike<%=objava.getId()%>"
								class="btn-my glyphicon glyphicon-thumbs-down"><%=objava.getDislikes()%></button>
						</form>

						<%
							} else if (objavaBean.getLajkovanoOpcija(objava.getId(), userBean.getUser().getId()) == 2) {
						%>
						<form id="likeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjax(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="like<%=objava.getId()%>"
								class="btn-my glyphicon glyphicon-thumbs-up"><%=objava.getLikes()%></button>
						</form>
						<form id="dislikeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjaxDislike(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="dislike<%=objava.getId()%>"
								class="like btn-my glyphicon glyphicon-thumbs-down"><%=objava.getDislikes()%></button>
						</form>

						<%
							} else {
						%>
						<form id="likeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjax(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="like<%=objava.getId()%>"
								class="btn-my glyphicon glyphicon-thumbs-up"><%=objava.getLikes()%></button>
						</form>
						<form id="dislikeForm<%=objava.getId()%>" method="post"
							class="inlineForm"
							onsubmit="submitFormAjaxDislike(<%=objava.getId()%>,<%=userBean.getUser().getId()%>);return false;">
							<button type="submit" id="dislike<%=objava.getId()%>"
								class="btn-my glyphicon glyphicon-thumbs-down"><%=objava.getDislikes()%></button>
						</form>

						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
		</div>
		</div>
		<%
			}
			}
		%>
</body>
</html>