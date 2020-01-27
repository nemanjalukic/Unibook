function myFunction(x, objavaID, userID) {
	if (x.className == "btn-my glyphicon glyphicon-thumbs-up") {
		x.className = "like glyphicon glyphicon-thumbs-up";
		x.innerText = " " + (Number(x.innerText) + 1);
	} else {
		x.className = "btn-my glyphicon glyphicon-thumbs-up";
		x.innerText = " " + (Number(x.innerText) - 1);
	}
}

function myFunction2(x) {
	if (x.className == "btn-my glyphicon glyphicon-thumbs-down") {
		x.className = "like glyphicon glyphicon-thumbs-down";
		x.innerText = " " + (Number(x.innerText) + 1);
	} else {
		x.className = "btn-my glyphicon glyphicon-thumbs-down";
		x.innerText = " " + (Number(x.innerText) - 1)
	}
}

function getRequestObject() {
	if (window.XMLHttpRequest) {
		return (new XMLHttpRequest());
	} else if (window.ActiveXObject) {
		return (new ActiveXObject("Microsoft.XMLHTTP"));
	} else {
		return (null);
	}
}

function submitFormAjax(objavaID, userID) {
	var request = getRequestObject();
	var x = document.getElementById("like" + objavaID);
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200)
			if (x.className == "btn-my glyphicon glyphicon-thumbs-up") {
				x.className = "like glyphicon glyphicon-thumbs-up";
				x.innerText = " " + (Number(x.innerText) + 1);
				alert("Lajkovano");
			} else {
				x.className = "btn-my glyphicon glyphicon-thumbs-up";
				x.innerText = " " + (Number(x.innerText) - 1);
				//alert("Unlajkovano");
			}
	}

	if (x.className == "btn-my glyphicon glyphicon-thumbs-up") {
		//x.className = "like glyphicon glyphicon-thumbs-up";
		//x.innerText = " " + (Number(x.innerText) + 1);
		request.open("POST", "?action=like&id=" + objavaID + "&userID="
				+ userID, true);
		request.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		request.send(null);
	} else {
		//x.className = "btn-my glyphicon glyphicon-thumbs-up";
		//x.innerText = " " + (Number(x.innerText) - 1);
		request.open("POST", "?action=unlike&id=" + objavaID + "&userID="
				+ userID, true);
		request.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		request.send(null);
	}
}
function submitFormAjaxDislike(objavaID, userID) {
		var request = getRequestObject();
		var x = document.getElementById("dislike" + objavaID);
		request.onreadystatechange = function() {
			if (request.readyState == 4 && request.status == 200)
			if (x.className == "btn-my glyphicon glyphicon-thumbs-down") {
				x.className = "like glyphicon glyphicon-thumbs-down";
				x.innerText = " " + (Number(x.innerText) + 1);
				alert("Dislajkovano");
			} else {
				x.className = "btn-my glyphicon glyphicon-thumbs-down";
				x.innerText = " " + (Number(x.innerText) - 1)
			}
		}

		if (x.className == "btn-my glyphicon glyphicon-thumbs-down") {
			//x.className = "like glyphicon glyphicon-thumbs-down";
			//x.innerText = " " + (Number(x.innerText) + 1);
			request.open("POST", "?action=dislike&id=" + objavaID + "&userID="
					+ userID, true);
			request.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			request.send(null);
		} else {
			//x.className = "btn-my glyphicon glyphicon-thumbs-down";
			//x.innerText = " " + (Number(x.innerText) - 1);
			request.open("POST", "?action=undislike&id=" + objavaID
					+ "&userID=" + userID, true);
			request.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			request.send(null);
		}

}



function showFilename() {
	var thefile = document.getElementById("selectedFile");
	var x = (thefile.value).split("\\").length
	document.getElementById('nazivFajla').value = (thefile.value).split("\\")[x - 1];
}

function choseFile() {
	var thefile = document.getElementById("selectedFile");
	thefile.click();
}
function actionUpload(userID) {
	var opis = document.getElementById("opis").value;
	document.getElementById("uploadForm").action = "?action=upload&userID="
			+ userID + "&opis=" + opis;
	document.getElementById("uploadForm").submit();
}

function actionAddTema(userID) {
	var tema = document.getElementById("tema").value;
	document.getElementById("temaForm").action = "?action=insertTema&userID="
			+ userID + "&tema=" + tema;
	document.getElementById("temaForm").submit();
}

function actionAddKomentar(userID, blogID) {
	var komentar = document.getElementById("komentar" + blogID).value;
	document.getElementById("blogform" + blogID).action = "?action=updateBlog&blogID="
			+ blogID + "&komentar=" + komentar + "&userID=" + userID;
	document.getElementById("blogform" + blogID).submit();
}
function isUrlValid(userInput) {
	var rgx = /^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&'\(\)\*\+,;=.]+$/;
	var res = userInput.match(rgx);
	if (res == null)
		return false;
	else
		return true;
}

function unosObjave() {
	var strobj = document.getElementById("objavaText").value;
	// console.log(""+testUrlExist().value);
	if (isUrlValid(strobj)) {
		if (strobj != undefined || strobj != '') {
			var regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=|\?v=)([^#\&\?]*).*/;
			var match = strobj.match(regExp);
			if (match && match[2].length == 11) {
				document.getElementById("ifpreview").setAttribute("src",
						("https://www.youtube.com/embed/" + match[2]));
				document.getElementById("preview").style.display = "block";
			} else {
				if (strobj.startsWith("https://")
						|| strobj.startsWith("http://"))
					document.getElementById("ifpreview").setAttribute("src",
							strobj);
				else {
					document.getElementById("ifpreview").setAttribute("src",
							"https://" + strobj);
				}
			}
		}

	} else {
		document.getElementById("preview").style.display = "none";
	}
}
function testUrlExist() {
	var strobj = document.getElementById("objavaText").value;
	var request = getRequestObject();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			console.log(request.status);
			document.getElementById("preview").style.display = "block";
		} else {
			console.log(request.status);
			document.getElementById("preview").style.display = "none";
		}
	}
	request.open("POST", "?action=checkURL&url=" + strobj, true);
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(null);

}

var timeout;
function objavaKeyUp() {
	clearTimeout(timeout);
	if (document.getElementById("objavaText").value) {
		timeout = setTimeout(testUrlExist(), 5000);
	}
}

function posaljiObjavu(userID) {
	var strobj = document.getElementById("objavaText").value;

	if (strobj != undefined || strobj != '') {
		if (isUrlValid(strobj)) {
			var regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=|\?v=)([^#\&\?]*).*/;
			var match = strobj.match(regExp);
			if (match && match[2].length == 11) {
				var ytlink = "https://www.youtube.com/embed/" + match[2];
				document.getElementById("objavaForm").action = "?action=insertObjava&userID="
						+ userID + "&sadrzaj=" + ytlink + "&tip=1";
				document.getElementById("objavaForm").submit();
			} else {
				document.getElementById("objavaForm").action = "?action=insertObjava&userID="
						+ userID + "&sadrzaj=" + strobj + "&tip=2";
				document.getElementById("objavaForm").submit();

			}
		} else {
			document.getElementById("objavaForm").action = "?action=insertObjava&userID="
					+ userID + "&sadrzaj=" + strobj + "&tip=3";
			document.getElementById("objavaForm").submit();
		}
	} else {
		alert("unesi nešto");

	}

}

function obrisiPrijatelja(userID) {
	var request = getRequestObject();
	document.getElementById("izbrisiBtn" + userID).disabled = true;
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200)
			alert("Izbrisano"); // Here is the response
	}
	request.open("POST", "?action=deleteFreind&freindID=" + userID, true);
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(null);
	return false;

}
function dodajPrijatelja(userID) {
	var request = getRequestObject();
	document.getElementById("dodajBtn" + userID).disabled = true;
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200)
			alert("Dodano"); // Here is the response
	}
	request.open("POST", "?action=addFreind&freindID=" + userID, true);
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(null);
	return false;

}
function prihvatiPrijatelja(userID) {
	var request = getRequestObject();
	document.getElementById("prihvatiBtn" + userID).disabled = true;
	document.getElementById("odbijBtn" + userID).disabled = true;
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200)
			alert("Dodano"); // Here is the response
	}
	request.open("POST", "?action=acceptFreind&freindID=" + userID, true);
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(null);
	return false;

}
function odbijPrijatelja(userID) {
	var request = getRequestObject();
	document.getElementById("prihvatiBtn" + userID).disabled = true;
	document.getElementById("odbijBtn" + userID).disabled = true;
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200)
			alert("Dodano"); // Here is the response
	}
	request.open("POST", "?action=rejectFreind&freindID=" + userID, true);
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(null);
	return false;

}

function fakultetPromijena() {
	var x = document.getElementById("sel1").value;
	document.getElementById("fakultetForm").action = "?action=fakultet&fakultetNaziv="
			+ x;
	document.getElementById("fakultetForm").submit();
}

setInterval(checkRequests, 10000);

function checkRequests() {
	var request = getRequestObject();
	var req = document.getElementById("requests").innerHTML;
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var response = request.responseText;
			var resi = parseInt(response);
			var reqi = parseInt(req);

			if (resi > reqi) {
				var zah = resi - reqi;
				if (zah == 1)
					alert("Imate jedan novi zahtijev za povezivanje");
				else
					alert("Imate " + zah + " zahtijva za povezivanje");
			}

			document.getElementById("requests").innerHTML = response;

		}
	}
	request.open("POST", "?action=checkRequests", true);
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(null);
}

function hide() {
	document.getElementById("toHide").style.display = "none";
	document.getElementById("usernameFormGroup").style.display = "none";
	document.getElementById("pictureButton").style.display = "none";
	document.getElementById("passButton").style.display = "none";
	document.getElementById("uploadButton").style.display = "none";
	document.getElementById("ime").readOnly = true;
	document.getElementById("prezime").readOnly = true;
}

function hideFreind() {
	document.getElementById("pictureButton").style.display = "none";
	document.getElementById("passButton").style.display = "none";
	document.getElementById("uploadButton").style.display = "none";
	document.getElementById("username").readOnly = true;
	document.getElementById("ime").readOnly = true;
	document.getElementById("prezime").readOnly = true;
	document.getElementById("email").readOnly = true;
	document.getElementById("selgodina").disabled = true;
	document.getElementById("selfakultet").disabled = true;
	document.getElementById("interesovanje").readOnly = true;
	document.getElementById("studprogram").readOnly = true;

}
function hideRegUser() {
	document.getElementById("homeNav").style.pointerEvents = "none";
	document.getElementById("usersNav").style.pointerEvents = "none";
	document.getElementById("reqNav").style.pointerEvents = "none";
	document.getElementById("profilNav").style.pointerEvents = "none";
}

function chosePicture() {
	var thefile = document.getElementById("selectedPicture");
	thefile.click();
}
function actionUploadData() {
	var username = document.getElementById("username").value;
	var ime = document.getElementById("ime").value;
	var prezime = document.getElementById("prezime").value;
	var email = document.getElementById("email").value;
	var interesovanje = document.getElementById("interesovanje").value;
	var studprogram = document.getElementById("studprogram").value;
	var fakultet = document.getElementById("selfakultet").value;
	var godina = document.getElementById("selgodina").value;
	document.getElementById("uploadFormSlika").action = "?action=uploadData&username="
			+ username
			+ "&ime="
			+ ime
			+ "&prezime="
			+ prezime
			+ "&email="
			+ email
			+ "&studprogram="
			+ studprogram
			+ "&godina="
			+ godina
			+ "&fakultet=" + fakultet + "&interesovanje=" + interesovanje;
	document.getElementById("uploadFormSlika").submit();
}

function showPicture() {
	var preview = document.getElementById("profilePicture");
	var file = document.getElementById("selectedPicture").files[0];
	var reader = new FileReader();

	reader.addEventListener("load", function() {
		preview.src = reader.result;
	}, false);

	if (file) {
		reader.readAsDataURL(file);
	}
}
function showPasswordDialog() {
	document.getElementById("passdialog").style.display = "block";
}

function checkPassword() {
	var old = document.getElementById("pwdold");
	var fgnew = document.getElementById("fgnew");
	var fgcon = document.getElementById("fgcon");
	var spnnew = document.getElementById("spnnew");
	var inew = document.getElementById("inew");
	var icon = document.getElementById("icon");
	var spncon = document.getElementById("spncon");
	var pwd = document.getElementById("pwd1");
	var con = document.getElementById("pwd2");
	var msg = document.getElementById("alertmsg");

	if (old.value != "" && pwd.value != "" && pwd.value.length>=8) {
		if (old.value == pwd.value) {
			msg.innerHTML = "Stari i novi password su isti";
			fgnew.className = "form-group has-error has-feedback";
			spnnew.display = "block";
			inew.className = "glyphicon glyphicon-remove";
			document.getElementById("chpassbtn").disabled = true;
		} else if (con.value != "" && old.value != pwd.value
				&& pwd.value != con.value) {
			msg.innerHTML = "Passwordi nisu isti";
			fgnew.className = "form-group has-success has-feedback";
			spnnew.display = "block";
			inew.className = "glyphicon glyphicon-ok";
			fgcon.className = "form-group has-error has-feedback";
			spncon.display = "block";
			icon.className = "glyphicon glyphicon-remove";
			document.getElementById("chpassbtn").disabled = true;
		} else if (con.value != "" && old.value != pwd.value
				&& pwd.value == con.value) {
			msg.innerHTML = "";
			fgnew.className = "form-group has-success has-feedback";
			spnnew.display = "block";
			inew.className = "glyphicon glyphicon-ok";
			fgcon.className = "form-group has-success has-feedback";
			spncon.display = "block";
			icon.className = "glyphicon glyphicon-ok";
			document.getElementById("chpassbtn").disabled = false;

		} else {
			msg.innerHTML = "";
			fgnew.className = "form-group";
			spnnew.display = "none";
			inew.className = "";
			fgcon.className = "form-group";
			spncon.display = "none";
			icon.className = "";
			document.getElementById("chpassbtn").disabled = true;
		}

	} else {
		msg.innerHTML = "";
		fgnew.className = "form-group";
		spnnew.display = "none";
		inew.className = "";
		fgcon.className = "form-group";
		spncon.display = "none";
		icon.className = "";
		document.getElementById("chpassbtn").disabled = true;
	}
}

function changePassword() {

	var request = getRequestObject();
	var old = document.getElementById("pwdold");
	var pwd = document.getElementById("pwd1");
	var con = document.getElementById("pwd2");
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var response = request.responseText;
			old.value = "";
			pwd.value = "";
			con.value = "";
			checkPassword();
			alert(response);
		}
	}
	request.open("POST", "?action=changePassword", true);
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request
			.send("old=" + old.value + "&pwd=" + pwd.value + "&con="
					+ con.value);
	return false;

}

function hidePassChanger() {
	document.getElementById("passdialog").style.display = "none";
}

setInterval(updateDiv, 30000);
function updateDiv() {
	var request = getRequestObject();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			 document.getElementById('objaveReload').innerHTML=request.responseText;
		}
	}
	request.open("POST",
			"?action=reloadObjave", true);
	request.setRequestHeader("Content-type",
	"application/x-www-form-urlencoded");
request.send(null);
}