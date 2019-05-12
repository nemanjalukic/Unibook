function passwordCheck(){
	
	var pwd = document.getElementById("pass1");
	var con = document.getElementById("pass2");
	
	if(pwd.value!="" && con.value!="" && pwd.value==con.value){
		pwd.style.border = "solid green";
		con.style.border = "solid green";
	}
	else if(pwd.value=="" && con.value==""){
		pwd.style.border = "none";
		con.style.border = "none";
		
	}
	else{
		pwd.style.border = "solid red";
		con.style.border = "solid red";
	}
}