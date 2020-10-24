<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>TRAFFIC TASK</title>
	</head>
	<body>
		ID(최대10자) : <input type="text" id="mbrId" maxlength="10"/>
		PW(최대10자) : <input type="password" id="mbrPwd" maxlength="10"/>
		</br>
		<button onclick="login();">로그인</button>
		</br>
		<button type="button" onclick="regist();">회원가입</button>
	</body>
</html>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
function login() {
	if(validation()) {	
		$.ajax({
			url: "/member/login",
			data: JSON.stringify({
				memberId: $("#mbrId").val(),
				memberPwd: $("#mbrPwd").val()
			}),
			contentType: "application/json",
			dataType: "json",
	        type: "POST"
		}).done(function(data) {
			alert(data.msg);
			$(location).attr("href", "info/infoMain");
	    }).fail(function(request, status, error) {
	    	alert(request.responseText);
	    });
	} else {
		return false;
	}
}

function regist() {
	if(validation()) {	
		$.ajax({
			url: "/member/insertMember",
			data: JSON.stringify({
				memberId: $("#mbrId").val(),
				memberPwd: $("#mbrPwd").val()
			}),
			contentType: "application/json",
			dataType: "json",
	        type: "POST"
		}).done(function(data) {
			alert(data.msg);
			$("#mbrId").val("");
			$("#mbrPwd").val("");
	    }).fail(function(request, status, error) {
	    	alert(request.responseText);
			$("#mbrId").val("");
			$("#mbrPwd").val("");
	    });
	} else {
		return false;
	}
}

function validation() {
	if($("#mbrId").val() == "" || $("#mbrId").val() == null) {
		alert("ID를 입력해주세요");
		return false;
	}
	if($("#mbrPwd").val() == "" || $("#mbrPwd").val() == null) {
		alert("PW를 입력해주세요");
		return false;
	}
	return true;
}
</script>