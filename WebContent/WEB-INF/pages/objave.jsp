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
<div class="col-sm-6">

		<div class="row">
			<div class="col-sm-12">
				<div class="panel panel-default">
					<div class="panel-body">

						<form id="objavaForm" method="post"> 
							<div class="input-group">
								<input type="text" id="objavaText" class="form-control" placeholder="Unesite text" oninput="unosObjave()" onkeyup="objavaKeyUp()">
								<div class="input-group-btn">
									<button class="btn btn-default" onclick="posaljiObjavu(<%=userBean.getUser().getId()%>)">Objavi</button>
								</div>
							</div>
						</form>
						<br>
						<div class="ifd" id="preview">
						<div class="embed-responsive embed-responsive-16by9 ifd">
							<iframe id="ifpreview" class="embed-responsive-item"></iframe>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="objaveReload">
		<jsp:include page="objRld.jsp" />
		</div>
		</div>

</body>
</html>